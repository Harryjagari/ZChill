package controller.servlets.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Clear cookie
		Cookie[] cookies = request.getCookies();
    	if(cookies != null){
	    	for(Cookie cookie : cookies){
	    		cookie.setMaxAge(0);
	    		response.addCookie(cookie);
	    	}
    	}
        // Clear session
    	HttpSession session = request.getSession(false);
    	if(session != null){
    		session.invalidate();
    	}
    	response.sendRedirect(request.getContextPath()+"/pages/login.jsp");
	}

}
