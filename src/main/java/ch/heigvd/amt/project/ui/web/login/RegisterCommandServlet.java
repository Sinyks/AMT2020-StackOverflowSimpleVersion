package ch.heigvd.amt.project.ui.web.login;


import ch.heigvd.amt.project.application.ServiceRegistry;
import ch.heigvd.amt.project.application.authenticationmgmt.AuthenticationManagementFacade;
import ch.heigvd.amt.project.application.authenticationmgmt.CurrentUserDTO;
import ch.heigvd.amt.project.application.authenticationmgmt.register.RegisterCommand;
import ch.heigvd.amt.project.application.authenticationmgmt.register.RegisterFailedException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "RegisterCommandServlet", urlPatterns = "/register")
public class RegisterCommandServlet extends HttpServlet {

    private ServiceRegistry serviceRegistry = ServiceRegistry.getServiceRegistry();
    private AuthenticationManagementFacade authenticationManagementFacade = serviceRegistry.getAuthenticationManagementFacade();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/Registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, java.io.IOException {

        req.getSession().removeAttribute("errors"); // why does the professor do this?

        RegisterCommand registerCommand = RegisterCommand.builder() // creer ces trucs
                .username(req.getParameter("username"))
                .clearTextPassword(req.getParameter("password"))
                .clearTextPasswordConfirm(req.getParameter("confirmPassword"))
                .build();

        CurrentUserDTO currentUser = null;

        try{
            currentUser = authenticationManagementFacade.register(registerCommand);
            req.getSession().setAttribute("currentUser",currentUser);
            String targetUrl = (String) req.getSession().getAttribute("targetUrl"); // recup l'url cible
            targetUrl = (targetUrl != null) ? targetUrl : "/"; // redirection vers home si user a pas de cible
            resp.sendRedirect(targetUrl);
            return;

        } catch (RegisterFailedException e){
            req.getSession().setAttribute("errors", List.of(e.getMessage()));
            resp.sendRedirect("/register"); // login get
            return;
        }

    }

}
