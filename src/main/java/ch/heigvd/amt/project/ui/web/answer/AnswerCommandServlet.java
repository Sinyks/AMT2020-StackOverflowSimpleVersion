package ch.heigvd.amt.project.ui.web.answer;

import ch.heigvd.amt.project.application.ServiceRegistry;
import ch.heigvd.amt.project.application.answermgmt.AnswerManagementFacade;
import ch.heigvd.amt.project.application.answermgmt.answer.AnswerCommand;
import ch.heigvd.amt.project.application.answermgmt.answer.AnswerFailedException;
import ch.heigvd.amt.project.application.authenticationmgmt.CurrentUserDTO;
import ch.heigvd.amt.project.domain.question.QuestionId;
import ch.heigvd.amt.project.utils.DotenvManager;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.SneakyThrows;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

@WebServlet(name = "AnswerCommandServlet", urlPatterns = "/answerQuestion.do")
public class AnswerCommandServlet extends HttpServlet {

    @Inject
    ServiceRegistry serviceRegistry;

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AnswerManagementFacade answerManagementFacade = serviceRegistry.getAnswerManagementFacade();

        final String questionId = req.getParameter("questionId");

        AnswerCommand answComm = AnswerCommand.builder()
                .ownerID(((CurrentUserDTO) req.getSession().getAttribute("currentUser")).getId())
                .body(req.getParameter("answerBody"))
                .questionId((new QuestionId(questionId)))
                .build();

        try {
            answerManagementFacade.answer(answComm);
        } catch (AnswerFailedException e){
            req.getSession().setAttribute("error", List.of(e.getMessage()));
            resp.sendRedirect("/questions");
        }

        //Get ENV variables for setting the requests
        Dotenv dotenv = DotenvManager.getDotenv();

        String api_key = dotenv.get("API_KEY");
        String api_host = dotenv.get("API_HOST");
        String api_port = dotenv.get("API_PORT");

        String address = "http://" + api_host + ":" + api_port + "/events";
        String username = ((CurrentUserDTO) req.getSession().getAttribute("currentUser")).getUsername();

        //POST event request
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        String json = new StringBuilder()
                .append("{")
                .append("\"eventType\":\"answer\",")
                .append("\"eventparams\": {")
                .append("\"username\":\"" + username + "\"")
                .append("}")
                .append("}").toString();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .uri(URI.create(address))
                .setHeader("X-API-KEY", api_key)
                .header("Content-Type", "application/json")
                .build();

        httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        resp.sendRedirect("/question?questionId=" + questionId);

    }
}
