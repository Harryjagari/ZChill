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
@WebServlet(asyncSupported = true,urlPatterns = {"/AddProduct"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//int id = Integer.parseInt(request.getParameter("product_id"));
		String  product_name = request.getParameter("product_name");
		double price =  Double.parseDouble(request.getParameter("price"));
		String  description = request.getParameter("description");
		String  type = request.getParameter("product_type");
		Part imagePart = request.getPart("product_image1");	
		String  stock = request.getParameter("product_stock");
		String  brand = request.getParameter("product_brand");
		String  rating = request.getParameter("product_rating");
		Products productModel = new Products(product_name,price, description ,type, imagePart,stock,brand,rating);
		
	    String savePath = MyConstants.IMAGE_DIR_SAVE_PATH;
	    String fileName = productModel.getImageUrlFromPart();
	    if(!fileName.isEmpty() && fileName != null)
    		imagePart.write(savePath + fileName);
		
	    ProductDao add= new ProductDao();
		int result = add.addProduct(MyConstants.ADD_PRODUCT, productModel);
		if(result == 1) {
			request.setAttribute("addMessage", "Successfully added");
			request.getRequestDispatcher("/pages/adminProduct.jsp").forward(request, response);
		}else if(result == -1) {
			request.setAttribute("addMessage", "product already exist");
			request.getRequestDispatcher("/pages/adminProduct.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/pages/adminProduct.jsp").forward(request, response);
		}
		
	}

}


