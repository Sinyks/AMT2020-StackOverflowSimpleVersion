package ch.heigvd.amt.project.ui.web.login;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LogoutCommand", urlPatterns = "/logout.do")
public class LogoutCommandServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, java.io.IOException {
        req.getSession().invalidate();
        resp.sendRedirect("");
    }

}
