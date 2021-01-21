package ch.heigvd.amt.project.ui.web.profile;

import ch.heigvd.amt.project.application.authenticationmgmt.CurrentUserDTO;
import ch.heigvd.amt.project.utils.DotenvManager;
import ch.heigvd.amt.project.utils.UserReputation;
import com.google.gson.Gson;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.SneakyThrows;

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

@WebServlet(urlPatterns = "/profile", name = "ProfilePage")
public class ProfilePageServlet extends HttpServlet {


    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        //Get ENV variables for setting the requests
        Dotenv dotenv = DotenvManager.getDotenv();

        String api_key = dotenv.get("API_KEY");
        String api_host = dotenv.get("API_HOST");
        String api_port = dotenv.get("API_PORT");

        String username = ((CurrentUserDTO) req.getSession().getAttribute("currentUser")).getUsername();
        String address = "http://" + api_host + ":" + api_port + "/users/" + username + "/reputation";

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

        UserReputation userReputation = gson.fromJson(apiResponse.body(), UserReputation.class);

        req.setAttribute("userReputation", userReputation);
        req.getRequestDispatcher("/WEB-INF/views/Profile.jsp").forward(req, resp);
    }
}
