package ch.heigvd.amt.project.ui.web;

import ch.heigvd.amt.project.infrastructure.ConstantStrings;

import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;

@WebServlet(urlPatterns = ConstantStrings.CURRENT_PATH+"/private", name = "PrivatePage")
public class PrivatePageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/Private.jsp").forward(request, response);
    }
}
