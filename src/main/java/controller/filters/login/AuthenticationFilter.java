package controller.filters.login;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/authenticationfilter")
public class AuthenticationFilter implements Filter {
    private ServletContext context;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
    
        // Check if the request is a login or logout request
        String uri = req.getRequestURI();
        boolean isLoginJsp = uri.toLowerCase().endsWith("login.jsp");
        boolean isLoginServlet = uri.endsWith("LoginServlet");
        boolean isLogoutServlet = uri.endsWith("LogoutServlet");
        boolean isUserRegisterServlet = uri.endsWith("UserRegister");
        boolean isCartServlet = uri.endsWith("AddToCart");

        HttpSession session = req.getSession(false);
        boolean loggedIn = session != null && session.getAttribute("username") != null;

        if (!loggedIn && !((isLoginJsp && req.getMethod().equalsIgnoreCase("GET")) || isLogoutServlet || isUserRegisterServlet)) {
            chain.doFilter(request, response);
        } else if (!loggedIn && isCartServlet) {
            res.sendRedirect(req.getContextPath() + "/pages/login.jsp");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }
}
