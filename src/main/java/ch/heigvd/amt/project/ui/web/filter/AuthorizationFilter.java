package ch.heigvd.amt.project.ui.web.filter;

import ch.heigvd.amt.project.application.authenticationmgmt.AuthenticationManagementFacade;
import ch.heigvd.amt.project.application.authenticationmgmt.CurrentUserDTO;

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
        // HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if(isPublicResource(req.getRequestURI())){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        CurrentUserDTO currentUser = (CurrentUserDTO)req.getSession().getAttribute("currentUser");



        if(currentUser == null){
            String targetUrl=req.getRequestURI();
            if(req.getQueryString() != null){
                targetUrl = "?"+req.getQueryString();
            }

            req.getSession().setAttribute("targetUrl",targetUrl);

            ((HttpServletResponse) servletResponse).sendRedirect("/login");
            return;
        }

        filterChain.doFilter(servletRequest,servletResponse);

    }



    boolean isPublicResource(String uri){
        if(uri.startsWith("/login")) {
            return true;
        } else if (uri.startsWith("/register")){
            return true;
        }  else if (uri.equals("/")){
            return true;
        } else if (uri.startsWith("/favicon.ico")){ // to fix the mysterious favicon.ico
            return true;
        }else if (uri.startsWith("/GzaPage")){
            return true;
        } else if (uri.equals("/questions")){ // to extend accordingly with the questions modification
            return true;
        } else if (uri.startsWith("/assets")){
                return true;
        } else if (uri.startsWith("/arquillian-managed")){
            return true;
        }
        return true;
    }
}
