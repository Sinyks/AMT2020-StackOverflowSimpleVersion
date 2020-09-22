package ch.heigvd.amt.project.infrastructure.login;


import ch.heigvd.amt.project.application.*;

import javax.imageio.spi.ServiceRegistry;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "RegisterCommandServlet", urlPatterns = "/register")
public class RegisterCommandServlet extends HttpServlet {

    @Inject
    ServiceRegistry serviceRegistry;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, java.io.IOException {
        RegisterCommand command = RegisterCommand.builder() // creer ces trucs
                .userName(req.getParameter("userName"))
                .password(req.getParameter("password"))
                .build();

        WebZoneUser loggedInUser = null;

        // stocker user dans la liste des user, puis le set comme loggedUser puis rediriger vers la cible
    }

}
