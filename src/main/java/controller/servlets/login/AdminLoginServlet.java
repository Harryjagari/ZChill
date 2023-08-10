package controller.servlets.login;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Dao.AdminDao;
import resources.MyConstants;

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("username");
		String pwd = request.getParameter("password");

		AdminDao con = new AdminDao();
		
		Boolean isAdminRegistered = con.isAdminRegistered(MyConstants.CHECK_ADMIN_LOGIN_INFO , user, pwd);
		if(isAdminRegistered != null && isAdminRegistered){
			HttpSession session = request.getSession();
			session.setAttribute("user", user);

			//setting session to expiry in 30 mins
			session.setMaxInactiveInterval(30*60);

			Cookie username = new Cookie("user", user);
			username.setMaxAge(30*60);
			response.addCookie(username);
			response.sendRedirect(request.getContextPath()+"/pages/dashboard.jsp");
		}else{
			// set error message
		    request.setAttribute("Message", "Invalid username or password");
		    // forward request to login page
		    RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/adminLogin.jsp");
		    dispatcher.include(request, response);
		}
	}

}