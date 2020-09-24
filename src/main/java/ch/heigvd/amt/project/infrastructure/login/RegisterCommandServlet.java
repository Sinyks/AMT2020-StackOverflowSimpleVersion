package ch.heigvd.amt.project.infrastructure.login;


import ch.heigvd.amt.project.application.*;

import javax.imageio.spi.ServiceRegistry;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "RegisterCommandServlet", urlPatterns = "/register")
public class RegisterCommandServlet extends HttpServlet {

    @Inject
    ServiceRegistry serviceRegistry;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/Registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, java.io.IOException {
        /*
        RegisterCommand command = RegisterCommand.builder() // creer ces trucs
                .userName(req.getParameter("userName"))
                .password(req.getParameter("password"))
                .passwordConfirmation(req.getParameter("passwordConfirmation"))
                .build();

         */
        // WebZoneUser loggedInUser = null;

         System.out.println("wer in");
        try{
            FakeDataBase.addToDataBase(req.getParameter("username"),req.getParameter("password"));
            req.getSession().setAttribute("currentUser",req.getParameter("username"));
            String targetUrl = (String) req.getSession().getAttribute("targetUrl"); // recup l'url cible
            targetUrl = (targetUrl != null) ? targetUrl : "/stackoverflow-simplified"; // redirection vers home si user a pas de cible (définir home)
            resp.sendRedirect(targetUrl);
            return;

        } catch (IllegalArgumentException e){
            // req.setAttribute("errors", List.of("Already registered"));
            req.getRequestDispatcher("/WEB-INF/views/Login.jsp").forward(req, resp);
        }


    }

}