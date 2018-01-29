package ru.job4j.servlets.application.methods;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 29.01.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class AuthFilter implements Filter {
    private static final Logger LOG = LogManager.getLogger(AuthFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        if (req.getRequestURI().contains("/signin")) {
            chain.doFilter(request, response);
        } else {
            HttpSession session = req.getSession();
            synchronized (session) {
                if (session.getAttribute("login") == null) {
                    ((HttpServletResponse) response).sendRedirect(String.format("%s/signin", req.getContextPath()));
                }
            }
           chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
