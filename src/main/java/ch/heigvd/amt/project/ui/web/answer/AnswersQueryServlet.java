package ch.heigvd.amt.project.ui.web.answer;

import ch.heigvd.amt.project.application.ServiceRegistry;
import ch.heigvd.amt.project.domain.question.Question;
import ch.heigvd.amt.project.domain.question.QuestionId;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AnswerQueryHandler", urlPatterns = "/Answers")
public class AnswersQueryServlet extends HttpServlet {

    @Inject
    ServiceRegistry serviceRegistry;

    // private AnswerManagementFacade answerManagementFacade;

    @Override
    public void init() throws ServletException {
        super.init();
         // answerManagementFacade = serviceRegistry.getAnswerManagementFacade();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // get Answers related to the question
        QuestionId questionId = new QuestionId(req.getParameter("questionId"));
        // TODO
        // retrieve all answers for this question
        // AnswersDTO answers = answerManagementFacade.getAnswers(questionId);

        // TODO
        // req.setAttribute("answers",answers);

        super.doGet(req, resp);
    }
}
