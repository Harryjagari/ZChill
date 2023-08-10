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

import controller.Dao.UserDao;
import resources.MyConstants;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("username");
		String pwd = request.getParameter("password");
        System.out.println("The Product ID: " + user);
        System.out.println("The user ID: " + pwd);
        //System.out.println("The quantity ID: " + cartModel.getQuantity());

		UserDao con = new UserDao();
		
		Boolean isUserRegistered = con.isUserRegistered(MyConstants.CHECK_LOGIN_INFO, user, pwd);
		if(isUserRegistered != null && isUserRegistered){
			HttpSession session = request.getSession();
			session.setAttribute("username", user);

			//setting session to expiry in 30 mins
			session.setMaxInactiveInterval(30*60);

			Cookie username = new Cookie("username", user);
			username.setMaxAge(30*60);
			response.addCookie(username);
			response.sendRedirect(request.getContextPath()+"/pages/index.jsp");
		}else{
			// set error message
		    request.setAttribute("errorMessage", "Invalid username or password");
		    // forward request to login page
		    RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/login.jsp");
		    dispatcher.include(request, response);
		}
	}

}