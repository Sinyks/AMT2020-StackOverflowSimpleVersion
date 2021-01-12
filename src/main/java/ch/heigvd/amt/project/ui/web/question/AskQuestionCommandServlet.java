package ch.heigvd.amt.project.ui.web.question;

import ch.heigvd.amt.project.application.ServiceRegistry;
import ch.heigvd.amt.project.application.authenticationmgmt.CurrentUserDTO;
import ch.heigvd.amt.project.application.questionmgmt.QuestionsManagementFacade;
import ch.heigvd.amt.project.application.questionmgmt.ask.AskCommand;
import ch.heigvd.amt.project.application.questionmgmt.ask.AskFailedException;
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

@WebServlet(name = "AskCommandServlet", urlPatterns = "/askQuestion.do")
public class AskQuestionCommandServlet extends HttpServlet {

    @Inject
    ServiceRegistry serviceRegistry;

    @SneakyThrows
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        QuestionsManagementFacade questionsManagementFacade = serviceRegistry.getQuestionManagementFacade();

        req.getSession().removeAttribute("errors");

        AskCommand askCommand = AskCommand.builder()
                .ownerId(((CurrentUserDTO)req.getSession().getAttribute("currentUser")).getId())
                .title(req.getParameter("title"))
                .body(req.getParameter("body"))
                .build();

        try {
            questionsManagementFacade.ask(askCommand);
        } catch (AskFailedException e) {
            req.getSession().setAttribute("errors", List.of(e.getMessage()));
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
                .append("\"eventType\":\"question\",")
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

        resp.sendRedirect("/questions");
    }
}
