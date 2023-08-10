package controller.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Dao.ProductDao;
import resources.MyConstants;

/**
 * Servlet implementation class DeleteProduct
 */

@WebServlet(name = "DeleteProductServlet", urlPatterns = "/DeleteProduct")
public class DeleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("product_id"));
        System.out.print("Success" + productId);
        ProductDao productDao = new ProductDao();
        boolean success = productDao.deleteProduct(MyConstants.DELETE_PRODUCT,productId);

		if (success) {
			System.out.print("Success");
		    request.setAttribute("successMessage", "Product deleted successfully!");
		} else {
			System.out.print("NoSuccess");
		    request.setAttribute("errorMessage", "Failed to delete product!");
		}
		System.out.print("No");
        request.getRequestDispatcher("/pages/adminProduct.jsp").forward(request, response);
    }

}
