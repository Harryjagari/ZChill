package controller.servlets;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Dao.CartDao;
import model.*;
import resources.MyConstants;


@WebServlet(name = "AddToCartServlet", urlPatterns = "/AddToCart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession(false);
	    if (session == null || session.getAttribute("username") == null) {
	        response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
	        return;
	    }
		int productId = Integer.parseInt(request.getParameter("productId"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		int  user_id = Integer.parseInt(request.getParameter("register_Id"));
		//System.out.println("Product ID: " + productId);
		//System.out.println("Quantity: " + quantity);
		//System.out.println("User ID: " + user_id);
		
		//Works perfectly fine till here but after making cartModel object the productId becomes zero
		Cart cartModel = new Cart(productId,quantity,user_id);
		
        // print the productId value
        //System.out.println("The Product ID: " + cartModel.getProduct_id());
        //System.out.println("The user ID: " + cartModel.getUserId());
        //System.out.println("The quantity ID: " + cartModel.getQuantity());
	    CartDao add= new CartDao();
		int result = add.addCart(MyConstants.ADD_CART, cartModel);
		if(result == 1) {
			int cart_id = add.getCartId(cartModel); // get the cart_id from the database
	        session.setAttribute("cart_id", cart_id); // set the cart_id attribute in the session
	        session.setMaxInactiveInterval(30 * 60);
			request.setAttribute("addMessage", "Successfully added");
			request.getRequestDispatcher("/pages/shop.jsp").forward(request, response);
		}else if(result == -1) {
			request.setAttribute("addMessage", "Error while adding");
			request.getRequestDispatcher("/pages/cart.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/pages/cart.jsp").forward(request, response);
		}
	}
}
