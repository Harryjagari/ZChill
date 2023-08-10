package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.Products;
import resources.MyConstants;
import controller.Dao.ProductDao;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet(asyncSupported = true,urlPatterns = {"/UpdateProduct"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class UpdateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String  product_name = request.getParameter("pname");
		double price =  Double.parseDouble(request.getParameter("price"));
		String  description = request.getParameter("desc");
		//Part imagePart = request.getPart("pimage");
		System.out.println("Product ID: " + id);
		System.out.println("Quantity: " + product_name);
		System.out.println("User ID: " + price);
		System.out.println("User ID: " + description);

		Products productModel = new Products(id,product_name,price, description);
		
//	    String savePath = MyConstants.IMAGE_DIR_SAVE_PATH;
//	    String fileName = productModel.getImageUrlFromPart();
//	    if(!fileName.isEmpty() && fileName != null)
//    		imagePart.write(savePath + fileName);
        System.out.println("The Product ID: " + productModel.getProduct_id());
        System.out.println("The user ID: " + productModel.getProduct_name());
        System.out.println("The Product ID: " + productModel.getPrice());
        System.out.println("The user ID: " + productModel.getDescription());
        ProductDao productDao = new ProductDao();
        boolean success = productDao.updateProduct(MyConstants.UPDATE_PRODUCT,productModel);
        System.out.println("The Product ID: " + productModel.getProduct_id());
        System.out.println("The user ID: " + productModel.getProduct_name());
        System.out.println("The Product ID: " + productModel.getPrice());
        System.out.println("The user ID: " + productModel.getDescription());
		
        if (success) {
			System.out.print("Success");
		    request.setAttribute("successMessage", "Product deleted successfully!");
		    request.getRequestDispatcher("/pages/adminProduct.jsp").forward(request, response);
		} else {
			System.out.print("NoSuccess");
		    request.setAttribute("errorMessage", "Failed to delete product!");
		    request.getRequestDispatcher("/pages/adminProductUpdate.jsp").forward(request, response);
		}
		System.out.print("No");
//  request.getRequestDispatcher("/pages/adminProduct.jsp").forward(request, response);
//		if(result == 1) {
//			request.setAttribute("addMessage", "Successfully added");
//			request.getRequestDispatcher("/pages/adminProduct.jsp").forward(request, response);
//		}else if(result == -1) {
//			request.setAttribute("addMessage", "product already exist");
//			request.getRequestDispatcher("/pages/adminProductUpdate.jsp").forward(request, response);
//		}else {
//			request.getRequestDispatcher("/pages/adminProductUpdate.jsp").forward(request, response);
		}
		
	}




