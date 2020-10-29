package ch.heigvd.amt.project.ui.web.vote;

import ch.heigvd.amt.project.application.ServiceRegistry;
import ch.heigvd.amt.project.application.authenticationmgmt.CurrentUserDTO;
import ch.heigvd.amt.project.application.votemgmt.VoteManagementFacade;
import ch.heigvd.amt.project.application.votemgmt.vote.VoteCommand;
import ch.heigvd.amt.project.domain.answer.AnswerId;
import ch.heigvd.amt.project.domain.question.QuestionId;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "VoteCommandServlet", urlPatterns = "/vote.do")
public class VoteCommandServlet extends HttpServlet {

    @Inject
    ServiceRegistry serviceRegistry;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        VoteManagementFacade voteManagementFacade = serviceRegistry.getVoteManagementFacade();

        final String questionId = req.getParameter("questionId");
        final String answerString = req.getParameter("answerId");

        final boolean isUpVote = (req.getParameter("isUpVote") == "true");

        AnswerId answerId = (answerString != null) ? new AnswerId(answerString) : null;

        VoteCommand voteCommand = VoteCommand.builder()
                .ownerId(((CurrentUserDTO) req.getSession().getAttribute("currentUser")).getId())
                .questionId(new QuestionId(questionId))
                .answerId(answerId)
                .isUpVote(isUpVote)
                .build();
    }
}
