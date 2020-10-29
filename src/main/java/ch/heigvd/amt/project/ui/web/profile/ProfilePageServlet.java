package ch.heigvd.amt.project.ui.web.profile;


import ch.heigvd.amt.project.application.authenticationmgmt.CurrentUserDTO;

import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;

@WebServlet(urlPatterns = "/profile", name = "ProfilePage")
public class ProfilePageServlet extends HttpServlet {


    //we already have all info in CurrentUserDTO

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        CurrentUserDTO currentUserDTO = (CurrentUserDTO)req.getSession().getAttribute("currentUser");

        req.setAttribute("profileInfo", currentUserDTO);
        req.getRequestDispatcher("/WEB-INF/views/Profile.jsp").forward(req, resp);
    }
}
