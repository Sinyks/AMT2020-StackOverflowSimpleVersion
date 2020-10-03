package ch.heigvd.amt.project.ui.web.question;

import ch.heigvd.amt.project.application.ServiceRegistry;
import ch.heigvd.amt.project.application.questionmgmt.QuestionManagementFacade;
import ch.heigvd.amt.project.application.questionmgmt.QuestionsDTO;
import ch.heigvd.amt.project.application.questionmgmt.QuestionsQuery;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "QuestionsPageHandler", urlPatterns = "/questions")
public class QuestionsQueryServlet extends HttpServlet {

    private ServiceRegistry serviceRegistry;
    private QuestionManagementFacade questionManagementFacade;

    @Override
    public void init() throws ServletException {
        super.init();
        serviceRegistry = ServiceRegistry.getServiceRegistry();
        questionManagementFacade = serviceRegistry.getQuestionManagementFacade();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        QuestionsDTO questionsDTO = questionManagementFacade.getQuestions(QuestionsQuery.builder()
                .build());
        req.setAttribute("questions", questionsDTO);
        req.getRequestDispatcher("/WEB-INF/views/Questions.jsp").forward(req, resp);
    }
}
