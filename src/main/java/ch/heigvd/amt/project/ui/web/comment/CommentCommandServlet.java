package ch.heigvd.amt.project.ui.web.comment;

import ch.heigvd.amt.project.application.ServiceRegistry;
import ch.heigvd.amt.project.application.answermgmt.answer.CommentFailedException;
import ch.heigvd.amt.project.application.authenticationmgmt.CurrentUserDTO;
import ch.heigvd.amt.project.application.commentmgmt.CommentManagementFacade;
import ch.heigvd.amt.project.application.commentmgmt.comment.CommentCommand;
import ch.heigvd.amt.project.domain.answer.AnswerId;
import ch.heigvd.amt.project.domain.question.QuestionId;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CommentCommandServlet", urlPatterns = "/comment.do")
public class CommentCommandServlet extends HttpServlet {

    @Inject
    ServiceRegistry serviceRegistry;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommentManagementFacade commentManagementFacade = serviceRegistry.getCommentManagementFacade();

        final String questionId = req.getParameter("questionId");
        final String answerString = req.getParameter("answerId");

        AnswerId answerId = (answerString != null) ? new AnswerId(answerString) : null;

        CommentCommand commentCommand = CommentCommand.builder()
                .ownerID(((CurrentUserDTO) req.getSession().getAttribute("currentUser")).getId())
                .body(req.getParameter("commentBody"))
                .questionId(new QuestionId(questionId))
                .answerId(answerId)
                .build();
        try {
            commentManagementFacade.comment(commentCommand);
        } catch (CommentFailedException e){
            req.getSession().setAttribute("error", List.of(e.getMessage()));
            resp.sendRedirect("/questions");
        }

        resp.sendRedirect("/question?questionId=" + questionId);
    }
}
