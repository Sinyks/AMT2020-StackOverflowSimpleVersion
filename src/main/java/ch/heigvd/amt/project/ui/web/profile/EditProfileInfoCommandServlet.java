package ch.heigvd.amt.project.ui.web.profile;

import ch.heigvd.amt.project.application.ServiceRegistry;
import ch.heigvd.amt.project.application.authenticationmgmt.CurrentUserDTO;
import ch.heigvd.amt.project.application.profilemgmt.ProfileManagementFacade;
import ch.heigvd.amt.project.application.profilemgmt.info.ProfileInfoCommand;
import ch.heigvd.amt.project.application.profilemgmt.info.ProfileInfoFailedException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/profileUpdate.do", name = "ProfileInfoCommandServlet")
public class EditProfileInfoCommandServlet extends HttpServlet {

    @Inject
    ServiceRegistry serviceRegistry;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProfileManagementFacade profileManagementFacade = serviceRegistry.getProfileManagementFacade();
        req.getSession().removeAttribute("errors");

        ProfileInfoCommand profileInfoCommand = ProfileInfoCommand.builder()
                .id(((CurrentUserDTO)req.getSession().getAttribute("currentUser")).getId())
                .newUsername(req.getParameter("username"))
                .newEmail(req.getParameter("email"))
                .newAboutMe(req.getParameter("aboutMe"))
                .build();

        try{
            profileManagementFacade.updateInfo(profileInfoCommand);
            resp.sendRedirect("/profile");
        } catch (ProfileInfoFailedException e){
            req.getSession().setAttribute("errors", List.of(e.getMessage()));
            resp.sendRedirect("/profile");
        }

    }


}
