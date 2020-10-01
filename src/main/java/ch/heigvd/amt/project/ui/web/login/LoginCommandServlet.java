package ch.heigvd.amt.project.ui.web.login;


import ch.heigvd.amt.project.application.ServiceRegistry;
import ch.heigvd.amt.project.application.authenticationmgmt.AuthenticationManagementFacade;
import ch.heigvd.amt.project.application.authenticationmgmt.CurrentUserDTO;
import ch.heigvd.amt.project.application.authenticationmgmt.login.LoginCommand;
import ch.heigvd.amt.project.application.authenticationmgmt.login.LoginFailedException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "LoginCommandServlet", urlPatterns = "/login")
public class LoginCommandServlet extends HttpServlet {

    private ServiceRegistry serviceRegistry = ServiceRegistry.getServiceRegistry();
    private AuthenticationManagementFacade authenticationManagementFacade = serviceRegistry.getAuthenticationManagementFacade();

     @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/Login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, java.io.IOException {

        LoginCommand loginCommand = LoginCommand.builder() // lié a lambok
            .username(req.getParameter("userName"))
            .clearTextPassword(req.getParameter("password"))
            .build();

        CurrentUserDTO currentUser = null;
        try {
            currentUser = authenticationManagementFacade.login(loginCommand);
            req.getSession().setAttribute("currentUser",currentUser);
            String targetUrl = (String) req.getSession().getAttribute("targetUrl"); // recup l'url cible
            targetUrl = (targetUrl != null) ? targetUrl : "/"; // redirection vers home si user a pas de cible (définir home)
            resp.sendRedirect(targetUrl);
            return;

        } catch (LoginFailedException e){
            //req.getSession().setAttribute("errors", List.of(e.getMessage()));
            resp.sendRedirect("/login"); // login get
        } // si mauvaise co

    }

}
