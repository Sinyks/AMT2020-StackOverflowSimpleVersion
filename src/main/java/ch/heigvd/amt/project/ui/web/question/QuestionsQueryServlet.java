package ch.heigvd.amt.project.ui.web.question;

import ch.heigvd.amt.project.application.ServiceRegistry;
import ch.heigvd.amt.project.application.questionmgmt.QuestionsManagementFacade;
import ch.heigvd.amt.project.application.questionmgmt.QuestionsDTO;
import ch.heigvd.amt.project.application.questionmgmt.QuestionsQuery;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "QuestionsPageHandler", urlPatterns = "/questions")
public class QuestionsQueryServlet extends HttpServlet {

    @Inject
    ServiceRegistry serviceRegistry;

    private QuestionsManagementFacade questionsManagementFacade;

    @Override
    public void init() throws ServletException {
        super.init();
        questionsManagementFacade = serviceRegistry.getQuestionManagementFacade();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        QuestionsDTO questionsDTO = questionsManagementFacade.getQuestions(QuestionsQuery.builder()
                .build());
        req.setAttribute("questions", questionsDTO);
        req.getRequestDispatcher("/WEB-INF/views/Questions.jsp").forward(req, resp);
    }
}
