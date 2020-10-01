package ch.heigvd.amt.project.ui.web.login;


import ch.heigvd.amt.project.application.authenticationmgmt.AuthenticationManagementFacade;
import ch.heigvd.amt.project.application.authenticationmgmt.register.RegisterCommand;
import ch.heigvd.amt.project.application.authenticationmgmt.register.RegisterFailedException;
import ch.heigvd.amt.project.infrastructure.persistence.FakeDataBase;

import javax.imageio.spi.ServiceRegistry;
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
    /*
    @Inject
    ServiceRegistry serviceRegistry;
     */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/Registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, java.io.IOException {

        // req.getSession().removeAttribute("errors");

        RegisterCommand registerCommand = RegisterCommand.builder() // creer ces trucs
                .username(req.getParameter("userName"))
                .clearTextPassword(req.getParameter("password"))
                // passwordconfirm ?
                .build();


        // WebZoneUser loggedInUser = null;

        try{
            authenticationManagementFacade.register(registerCommand);
            req.getRequestDispatcher("/login").forward(req,resp); // login post
            return;

        } catch (RegisterFailedException e){
            req.getSession().setAttribute("error", List.of(e.getMessage()));
            resp.sendRedirect("login"); // login get
            return;
        }

    }

}
