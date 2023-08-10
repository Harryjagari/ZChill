package controller.servlets;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import controller.Dao.CartDao;
import model.*;
import resources.MyConstants;


@WebServlet(name = "AddOrderServlet", urlPatterns = "/AddOrder")
public class AddOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int Cart_id = Integer.parseInt(request.getParameter("cart_id"));
		String Address = request.getParameter("address");


		orders orderModel = new orders(Address,Cart_id);
        // print the productId value
        System.out.println("The Product ID: " + orderModel.getCart_id());
        System.out.println("The user ID: " + orderModel.getAddress());

	    CartDao add= new CartDao();
		int result = add.addOrders(MyConstants.ADD_ORDER, orderModel);
		if(result == 1) {
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
