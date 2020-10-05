package ch.heigvd.amt.project.ui.web.question;

import ch.heigvd.amt.project.application.ServiceRegistry;
import ch.heigvd.amt.project.application.questionmgmt.QuestionManagementFacade;
import ch.heigvd.amt.project.application.questionmgmt.ask.AskCommand;
import ch.heigvd.amt.project.application.questionmgmt.ask.AskFailedException;
import ch.heigvd.amt.project.domain.exceptions.PersistenceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AskCommandServlet", urlPatterns = "/askQuestion.do")
public class AskQuestionCommandServlet extends HttpServlet {

    private ServiceRegistry serviceRegistry = ServiceRegistry.getServiceRegistry();
    private QuestionManagementFacade questionManagementFacade = serviceRegistry.getQuestionManagementFacade();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getSession().removeAttribute("errors");



        AskCommand askCommand = AskCommand.builder()
                .label(req.getParameter("label"))
                .content(req.getParameter("content"))
                .build();

        try {
            questionManagementFacade.ask(askCommand);
            resp.sendRedirect("/questions");
        } catch (AskFailedException | PersistenceException e) {
            req.getSession().setAttribute("errors", List.of(e.getMessage()));
            resp.sendRedirect("/questions");
        }
    }
}
