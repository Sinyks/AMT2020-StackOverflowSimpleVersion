package ch.heigvd.amt.project.ui.web.login;


import ch.heigvd.amt.project.application.ServiceRegistry;
import ch.heigvd.amt.project.application.authenticationmgmt.AuthenticationManagementFacade;
import ch.heigvd.amt.project.application.authenticationmgmt.CurrentUserDTO;
import ch.heigvd.amt.project.application.authenticationmgmt.login.LoginCommand;
import ch.heigvd.amt.project.application.authenticationmgmt.login.LoginFailedException;
import ch.heigvd.amt.project.domain.exceptions.DataCorruptionException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@WebServlet(name = "LoginCommand", urlPatterns = "/login.do")
public class LoginCommandServlet extends HttpServlet {

    private ServiceRegistry serviceRegistry = ServiceRegistry.getServiceRegistry();
    private AuthenticationManagementFacade authenticationManagementFacade = serviceRegistry.getAuthenticationManagementFacade();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, java.io.IOException {

        LoginCommand loginCommand = LoginCommand.builder()
            .username(req.getParameter("userName"))
            .clearTextPassword(req.getParameter("password"))
            .build();

        CurrentUserDTO currentUser = null;
        try {
            currentUser = authenticationManagementFacade.login(loginCommand);
            req.getSession().setAttribute("currentUser",currentUser);
            String targetUrl = (String) req.getSession().getAttribute("targetUrl");
            targetUrl = (targetUrl != null) ? targetUrl : "/";
            resp.sendRedirect(targetUrl);
            return;

        } catch (LoginFailedException | DataCorruptionException e){
            req.getSession().setAttribute("errors", List.of(e.getMessage()));
            resp.sendRedirect("/login");
        }

    }

}
