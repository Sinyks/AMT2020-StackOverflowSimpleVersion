package ch.heigvd.amt.project.ui.web.filter;

import ch.heigvd.amt.project.infrastructure.ConstantStrings;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "HomePageRedirectFilter", urlPatterns ="/")
public class HomePageRedirectFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(ConstantStrings.CURRENT_PATH.equals("")){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        ((HttpServletResponse) servletResponse).sendRedirect(ConstantStrings.CURRENT_PATH);
    }
}
