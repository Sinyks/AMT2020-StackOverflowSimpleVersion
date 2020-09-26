package ch.heigvd.amt.project.ui.web.filter;


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

        String loggedUser = (String) req.getSession().getAttribute("currentUser"); // definir le user quelque part

        if(loggedUser == null){ // si pas loggé
            String targetUrl=req.getRequestURI();
            if(req.getQueryString() != null){
                targetUrl = "?"+req.getQueryString(); // memoire de query?
            }

            req.getSession().setAttribute("targetUrl",targetUrl); // la mémoire de ou il voulait aller

            ((HttpServletResponse) servletResponse).sendRedirect("/login"); // il doit se log
            return;
        }

        filterChain.doFilter(servletRequest,servletResponse); // on est co on peut y aller

    }


    boolean isPublicResource(String uri){
        if(uri.equals("/login")) {
            return true;
        } else if (uri.equals("/register")){
            return true;
        } else if (uri.equals("/")){
            return true;
        }
        return false;
    }
}
