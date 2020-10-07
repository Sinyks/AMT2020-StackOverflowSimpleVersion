package ch.heigvd.amt.project.ui.web.question;

import ch.heigvd.amt.project.application.ServiceRegistry;
import ch.heigvd.amt.project.application.authenticationmgmt.CurrentUserDTO;
import ch.heigvd.amt.project.application.questionmgmt.QuestionsManagementFacade;
import ch.heigvd.amt.project.application.questionmgmt.ask.AskCommand;
import ch.heigvd.amt.project.application.questionmgmt.ask.AskFailedException;

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
    private QuestionsManagementFacade questionsManagementFacade = serviceRegistry.getQuestionManagementFacade();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getSession().removeAttribute("errors");

        AskCommand askCommand = AskCommand.builder()
                .ownerName(((CurrentUserDTO)req.getSession().getAttribute("currentUser")).getUsername())
                .title(req.getParameter("title"))
                .body(req.getParameter("body"))
                .build();

        try {
            questionsManagementFacade.ask(askCommand);
            resp.sendRedirect("/questions");
        } catch (AskFailedException e) {
            req.getSession().setAttribute("errors", List.of(e.getMessage()));
            resp.sendRedirect("/questions");
        }
    }
}
