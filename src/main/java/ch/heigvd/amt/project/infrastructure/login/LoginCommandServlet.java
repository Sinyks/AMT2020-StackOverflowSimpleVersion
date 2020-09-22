package ch.heigvd.amt.project.infrastructure.login;


import ch.heigvd.amt.project.application.*;

import javax.imageio.spi.ServiceRegistry;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "LoginCommandServlet", urlPatterns = "/login")
public class LoginCommandServlet extends HttpServlet {

    @Inject
    ServiceRegistry serviceRegistry;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, java.io.IOException {
        LoginCommand command = LoginCommand.builder() // creer ces trucs
            .userName(req.getParameter("userName"))
            .password(req.getParameter("password"))
            .build();

        WebZoneUser loggedInUser = null;
        try {
            loggedInUser = serviceRegistry.getIiiii; // recup identité, trop avancé pour mtn
            req.getSession().setAttribute("currentUser",loggedInUser); // pas oublier de definir webzoneuser quelquepart
            String targetUrl = (String) req.getSession().getAttribute("targetUrl");
            //targetUrl = (targetUrl != null) ? targetUrl : "/jeej" // redirection vers ??? si user a pas de cible
            resp.sendRedirect(targetUrl);
            return;
        } catch (LoginFailedException e){ // trouver dou vient cette exception
            req.setAttribute("errors", List.of("invalid Login"));
            req.getRequestDispatcher("/WEB-INF/views/Login.jsp").forward(req, resp);
        }
    }

}
