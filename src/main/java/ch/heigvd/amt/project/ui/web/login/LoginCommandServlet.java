package ch.heigvd.amt.project.ui.web.login;


import ch.heigvd.amt.project.infrastructure.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "LoginCommandServlet", urlPatterns = "/app/login")
public class LoginCommandServlet extends HttpServlet {


     @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/Login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, java.io.IOException {
        /*
        LoginCommand command = LoginCommand.builder() // lié a lambok
            .userName(req.getParameter("userName"))
            .password(req.getParameter("password"))
            .build();

         */
        //WebZoneUser loggedInUser = null;


        try {
            FakeDataBase.isAuth(req.getParameter("username"),req.getParameter("password")); // verif si user existe
            req.getSession().setAttribute("currentUser",req.getParameter("username")); // ajouter user comme user connecté
            String targetUrl = (String) req.getSession().getAttribute("targetUrl"); // recup l'url cible
            targetUrl = (targetUrl != null) ? targetUrl : "/app"; // redirection vers home si user a pas de cible (définir home)
            resp.sendRedirect(targetUrl);
            return;
            /*
            loggedInUser = serviceRegistry.getIiiii; // recup identité, trop avancé pour mtn
            req.getSession().setAttribute("currentUser",loggedInUser); // pas oublier de definir webzoneuser quelquepart
            String targetUrl = (String) req.getSession().getAttribute("targetUrl");
            //targetUrl = (targetUrl != null) ? targetUrl : "/jeej" // redirection vers ??? si user a pas de cible
            resp.sendRedirect(targetUrl);
            return;
             */

        } catch (IllegalArgumentException e){
            // req.setAttribute("errors", List.of("invalid Login"));
            req.getRequestDispatcher("/WEB-INF/views/Login.jsp").forward(req, resp);
        } // si mauvaise co

    }

}
