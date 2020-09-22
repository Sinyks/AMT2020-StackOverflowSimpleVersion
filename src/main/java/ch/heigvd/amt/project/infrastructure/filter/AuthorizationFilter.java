package ch.heigvd.amt.project.infrastructure.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AuthorizationFilter", urlPatterns = "/*")
public class AuthorizationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //?? auto généré par intellij
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if(isPublicRessource(req.getRequestURI())){ // on peut imméditement y acceder
            chain.doFilter(servletRequest,servletResponse); // definir chain quelquepart
            return;
        }

        WebZoneUser loggedUser = (WebZoneUser)req.getSession().getAttribute("currentUser"); // definir le user quelque part

        if(loggedUser == null){ // si pas loggé
            String targetUrl=req.getRequestURI();
            if(req.getQueryString() != null){
                targetUrl = "?"+req.getQueryString(); // memoire de query?
            }

            req.getSession().setAttribute("targetUrl",targetUrl); // la mémoire de ou il voulait aller

            ((HttpServletResponse) servletResponse).sendRedirect("/login"); // il doit se log
            return;
        }

        chain.doFilter(servletRequest,servletResponse); // on est co on peut y aller

    }

    @Override
    public void destroy() {
         //?? auto généré par intellij
    }

    boolean isPublicRessource(String uri){
        if(uri == "/login" || uri == "/register"){
            return true;
        }
        return false;
    }
}
