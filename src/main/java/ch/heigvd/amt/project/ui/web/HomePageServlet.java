package ch.heigvd.amt.project.ui.web;

import ch.heigvd.amt.project.application.ServiceRegistry;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "", name = "HomePage")
public class HomePageServlet extends HttpServlet {

    @Inject
    ServiceRegistry serviceRegistry;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int userCount = (serviceRegistry.getAuthenticationManagementFacade()).getUserCount();
        int questionCount = (serviceRegistry.getQuestionManagementFacade()).getQuestionCount();
        int answerCount = (serviceRegistry.getAnswerManagementFacade()).getAnswerCount();

        request.setAttribute("userCount", userCount);
        request.setAttribute("questionCount", questionCount);
        request.setAttribute("answerCount", answerCount);
        request.getRequestDispatcher("/WEB-INF/views/Home.jsp").forward(request, response);
    }
}
