package ch.heigvd.amt.project.ui.web.filter;

import ch.heigvd.amt.project.application.authenticationmgmt.CurrentUserDTO;
import ch.heigvd.amt.project.infrastructure.*;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AuthorizationFilter", urlPatterns = "/*")
public class AuthorizationFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if(isPublicResource(req.getRequestURI())){ // on peut imméditement y acceder
            filterChain.doFilter(servletRequest,servletResponse); // definir chain quelquepart
            return;
        }

        //String loggedUser = (String) req.getSession().getAttribute("currentUser"); // definir le user quelque part
        CurrentUserDTO currentUser = (CurrentUserDTO)req.getSession().getAttribute("currentUser");



        if(currentUser == null){ // si pas loggé
            String targetUrl=req.getRequestURI();
            if(req.getQueryString() != null){
                targetUrl = "?"+req.getQueryString(); // memoire de query?
            }

            req.getSession().setAttribute("targetUrl",targetUrl); // la mémoire de ou il voulait aller
            // the professor here remove attribute target url ???

            ((HttpServletResponse) servletResponse).sendRedirect("/login"); // il doit se log
            return;
        }

        filterChain.doFilter(servletRequest,servletResponse); // on est co on peut y aller

    }


    // si on passe a un systeme avec un REST par servlet changer equals par begins
    boolean isPublicResource(String uri){
        if(uri.equals("/login")) {
            return true;
        } else if (uri.equals("/register")){
            return true;
        }  else if (uri.equals("/")){
            return true;
        } else if (uri.equals("/favicon.ico")){
            return true;
        } else if (uri.startsWith("/assets")){ // unreacheable code in not root mode
                return true;
        }
        return false;
    }
}
