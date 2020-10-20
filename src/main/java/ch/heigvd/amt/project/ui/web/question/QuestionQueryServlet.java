package ch.heigvd.amt.project.ui.web.question;

import ch.heigvd.amt.project.application.ServiceRegistry;
import ch.heigvd.amt.project.application.questionmgmt.QuestionsDTO;
import ch.heigvd.amt.project.application.questionmgmt.QuestionsManagementFacade;
import ch.heigvd.amt.project.domain.question.QuestionId;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "QuestionPageHandler", urlPatterns = "/question")
public class QuestionQueryServlet extends HttpServlet {

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
        //get questionDTO from request parameter
        QuestionId questionId = new QuestionId(req.getParameter("questionId"));
        QuestionsDTO.QuestionDTO questionDTO = questionsManagementFacade.getQuestion(questionId);

        //redirect to specific question
        req.setAttribute("question", questionDTO);
        req.getRequestDispatcher("/WEB-INF/views/Question.jsp").forward(req, resp);
    }
}
