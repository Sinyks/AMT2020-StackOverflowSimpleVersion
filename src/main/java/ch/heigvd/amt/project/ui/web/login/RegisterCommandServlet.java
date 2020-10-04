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


@WebServlet(name = "RegisterCommand", urlPatterns = "/register.do")
public class RegisterCommandServlet extends HttpServlet {

    private ServiceRegistry serviceRegistry = ServiceRegistry.getServiceRegistry();
    private AuthenticationManagementFacade authenticationManagementFacade = serviceRegistry.getAuthenticationManagementFacade();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, java.io.IOException {

        req.getSession().removeAttribute("errors");

        RegisterCommand registerCommand = RegisterCommand.builder()
                .username(req.getParameter("username"))
                .clearTextPassword(req.getParameter("password"))
                .clearTextPasswordConfirm(req.getParameter("confirmPassword"))
                .build();

        CurrentUserDTO currentUser = null;

        try{
            currentUser = authenticationManagementFacade.register(registerCommand);
            req.getSession().setAttribute("currentUser",currentUser);
            String targetUrl = (String) req.getSession().getAttribute("targetUrl");
            targetUrl = (targetUrl != null) ? targetUrl : "/";
            resp.sendRedirect(targetUrl);
            return;

        } catch (RegisterFailedException e){
            req.getSession().setAttribute("errors", List.of(e.getMessage()));
            resp.sendRedirect("/register");
            return;
        }

    }

}
