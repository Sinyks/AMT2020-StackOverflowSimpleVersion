package ch.heigvd.amt.project.ui.web.answer;

import ch.heigvd.amt.project.application.ServiceRegistry;
import ch.heigvd.amt.project.application.answermgmt.AnswerManagementFacade;
import ch.heigvd.amt.project.application.answermgmt.answer.AnswerCommand;
import ch.heigvd.amt.project.application.answermgmt.answer.AnswerFailedException;
import ch.heigvd.amt.project.application.authenticationmgmt.CurrentUserDTO;
import ch.heigvd.amt.project.domain.answer.Answer;
import ch.heigvd.amt.project.domain.question.QuestionId;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "AnswerCommandServlet", urlPatterns = "/answerQuestion.do")
public class AnswerCommandServlet extends HttpServlet {

    @Inject
    ServiceRegistry serviceRegistry;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AnswerManagementFacade answerManagementFacade = serviceRegistry.getAnswerManagementFacade();

        final String questionId = req.getParameter("questionId");

        AnswerCommand answComm = AnswerCommand.builder()
                .ownerID(((CurrentUserDTO) req.getSession().getAttribute("currentUser")).getId())
                .body(req.getParameter("answerBody"))
                .questionId((new QuestionId(questionId)))
                .build();

        try {
            answerManagementFacade.answer(answComm);
        } catch (AnswerFailedException e){
            req.getSession().setAttribute("error", List.of(e.getMessage()));
            resp.sendRedirect("/questions");
        }

        resp.sendRedirect("/question?questionId=" + questionId);

    }
}
