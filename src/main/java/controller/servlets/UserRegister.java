package controller.servlets;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.User;
import resources.MyConstants;
import controller.Dao.UserDao;

@WebServlet(asyncSupported = true, urlPatterns = { "/UserRegister" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
	maxFileSize = 1024 * 1024 * 10, // 10MB
	maxRequestSize = 1024 * 1024 * 50)
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String  username = request.getParameter("username");
		String email = request.getParameter("email");
		String  password = request.getParameter("password");
		Part imagePart = request.getPart("image");	
		User userModel = new User(username, email, password , imagePart);
		
	    String savePath = MyConstants.IMAGE_DIR_SAVE_PATH;
	    String fileName = userModel.getImageUrlFromPart();
	    if(!fileName.isEmpty() && fileName != null)
    		imagePart.write(savePath + fileName);
		
		UserDao con = new UserDao();
		int result = con.registerUser(MyConstants.USER_REGISTER, userModel);
		if(result == 1) {
			request.setAttribute("registerMessage2", "Successfully Registered");
			request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
		}else if(result == -1) {
			request.setAttribute("registerMessage1", "User Already Exists");
			request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
		}
		
	}

}
