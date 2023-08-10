package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.User;
import resources.MyConstants;
import controller.Dao.UserDao;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet(asyncSupported = true,urlPatterns = {"/UpdateUser"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Retrieve updated data from the form
		int id = Integer.parseInt(request.getParameter("register_Id"));
		String  username = request.getParameter("user");
		String email =  request.getParameter("email");
		String password =  request.getParameter("password");
		Part imagePart = request.getPart("image");

		// Create a User object with the updated data
		User userModel = new User(id,username,email,password,imagePart);

		// Update the user's information in the database
		UserDao userDao = new UserDao();
		boolean success = userDao.updateUser(MyConstants.UPDATE_USER_INFO, userModel);

		// Update the session data, if necessary
	    HttpSession session = request.getSession(false); // Get the session object, but don't create a new one if it doesn't exist
	    if (session != null) {
	    	session.removeAttribute("username");
	        session.invalidate(); // Invalidate the session
	    }
		// Redirect the user to the updated profile page
		if (success) {
			session = request.getSession(true); // Create a new session object
		    session.setAttribute("username", username);		   
		    request.setAttribute("successMessage", "Profile updated successfully!");
		    request.getRequestDispatcher("/pages/index.jsp").forward(request, response);
		} else {
		    request.setAttribute("errorMessage", "Failed to update profile!");
		    request.getRequestDispatcher("/pages/profileUpdate.jsp").forward(request, response);
		}
	}
}


