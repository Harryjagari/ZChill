package controller.servlets;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Dao.CartDao;

import resources.MyConstants;

/**
 * Servlet implementation class DeleteProduct
 */
@WebServlet(name = "RemoveItemFromCartServlet", urlPatterns = "/RemoveItemFromCart")
public class RemoveItemFromCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int carttId = Integer.parseInt(request.getParameter("cart_id"));
        System.out.print("Success" + carttId);
        CartDao productDao = new CartDao();
        boolean success = productDao.deleteCartItem(MyConstants.DELETE_CART,carttId);

		if (success) {
			System.out.print("Success");
		    request.setAttribute("successMessage", "Product deleted successfully!");
		} else {
			System.out.print("NoSuccess");
		    request.setAttribute("errorMessage", "Failed to delete product!");
		}
		System.out.print("No");
        request.getRequestDispatcher("/pages/cart.jsp").forward(request, response);
    }

}
