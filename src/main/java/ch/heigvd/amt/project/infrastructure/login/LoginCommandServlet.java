package ch.heigvd.amt.project.infrastructure.login;


import javax.imageio.spi.ServiceRegistry;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
            req.getSession().setAttribute("currentUser",loggedInUser);
            String targetUrl = (String) req.getSession().getAttribute("targetUrl");
        }
    }

}
