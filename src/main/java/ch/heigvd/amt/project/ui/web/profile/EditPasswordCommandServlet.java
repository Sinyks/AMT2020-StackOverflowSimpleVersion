package ch.heigvd.amt.project.ui.web.profile;

import ch.heigvd.amt.project.application.ServiceRegistry;
import ch.heigvd.amt.project.application.authenticationmgmt.CurrentUserDTO;
import ch.heigvd.amt.project.application.profilemgmt.ProfileManagementFacade;
import ch.heigvd.amt.project.application.profilemgmt.password.ProfilePasswordCommand;
import ch.heigvd.amt.project.application.profilemgmt.password.ProfilePasswordFailedException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/profilePassword.do", name = "ProfilePasswordCommandServlet")
public class EditPasswordCommandServlet extends HttpServlet{

    @Inject
    ServiceRegistry serviceRegistry;

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProfileManagementFacade profileManagementFacade = serviceRegistry.getProfileManagementFacade();

        ProfilePasswordCommand profilePasswordCommand = ProfilePasswordCommand.builder()
                .id(((CurrentUserDTO)req.getSession().getAttribute("currentUser")).getId())
                .currentClearPassword(req.getParameter("oldPassword"))
                .newClearTextPassword(req.getParameter("newPassword"))
                .newClearTextPasswordConfirm(req.getParameter("newPasswordConfirm"))
                .build();

        try{
            profileManagementFacade.updatePassword(profilePasswordCommand);
            req.setAttribute("success","Password changed successfully!");
            req.getRequestDispatcher("/WEB-INF/views/Profile.jsp").forward(req, resp);
        } catch (ProfilePasswordFailedException e){
            req.setAttribute("failure",e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/Profile.jsp").forward(req, resp);
        }
    }

}
