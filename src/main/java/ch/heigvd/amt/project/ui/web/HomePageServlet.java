package ch.heigvd.amt.project.ui.web;

import ch.heigvd.amt.project.application.ServiceRegistry;
import ch.heigvd.amt.project.utils.DotenvManager;
import ch.heigvd.amt.project.utils.UserReputation;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.SneakyThrows;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

@WebServlet(urlPatterns = "", name = "HomePage")
public class HomePageServlet extends HttpServlet {

    @Inject
    ServiceRegistry serviceRegistry;

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int userCount = (serviceRegistry.getAuthenticationManagementFacade()).getUserCount();
        int questionCount = (serviceRegistry.getQuestionManagementFacade()).getQuestionCount();
        int answerCount = (serviceRegistry.getAnswerManagementFacade()).getAnswerCount();

        //Get ENV variables for setting the requests
        Dotenv dotenv = DotenvManager.getDotenv();

        String api_key = dotenv.get("API_KEY");
        String api_host = dotenv.get("API_HOST");
        String api_port = dotenv.get("API_PORT");

        String address = "http://" + api_host + ":" + api_port + "/users/reputations";

        //GET user reputation request
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(address))
                .setHeader("X-API-KEY", api_key)
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> apiResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();

        Type listType = new TypeToken<List<UserReputation>>(){}.getType();
        List<UserReputation> userReputations = gson.fromJson(apiResponse.body(), listType);

        request.setAttribute("userCount", userCount);
        request.setAttribute("questionCount", questionCount);
        request.setAttribute("answerCount", answerCount);
        request.setAttribute("userReputations", userReputations);
        request.getRequestDispatcher("/WEB-INF/views/Home.jsp").forward(request, response);
    }
}
