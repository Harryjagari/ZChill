package model;

import java.io.File;

import javax.servlet.http.Part;

import resources.MyConstants;

public class Products {
	private int product_id;

	double price;

	String product_name,description,product_type,imageUrlFromPart,product_stock,product_brand,product_rating;
	
	public Products(double price) {
		this.price = price;
	}
	public Products(int product_id) {
		this.product_id = product_id;
	}
	
	public Products() {}
	public Products(String product_name, double price, String description,String product_type, Part part,
			 String product_stock, String product_brand, String product_rating) {
			this.product_name = product_name;
			this.price = price;
			this.description = description;
			this.product_type = product_type;
			this.imageUrlFromPart = getImageUrl(part);
			this.product_stock = product_stock;
			this.product_brand = product_brand;
			this.product_rating = product_rating;
		}
	
	public Products(int product_id,String product_name, double price, String description,String product_type, Part part,
		 String product_stock, String product_brand, String product_rating) {
		this.product_name = product_name;
		this.description = description;
		this.product_type = product_type;
		this.imageUrlFromPart = getImageUrl(part);
		this.product_stock = product_stock;
		this.product_brand = product_brand;
		this.product_rating = product_rating;
	}
	public Products(int product_id,String product_name, double price, String description) {
		this.product_id = product_id;
		this.product_name = product_name;
		this.description = description;
		this.price = price;
	}
	
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProduct_type() {
		return product_type;
	}


	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}


	public String getProduct_name() {
		return product_name;
	}


	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getImageUrlFromPart() {
		return imageUrlFromPart;
	}


	public void setImageUrlFromPart(Part part) {
		this.imageUrlFromPart = getImageUrl(part);
	}

	public String getProduct_stock() {
		return product_stock;
	}


	public void setProduct_stock(String product_stock) {
		this.product_stock = product_stock;
	}


	public String getProduct_brand() {
		return product_brand;
	}


	public void setProduct_brand(String product_brand) {
		this.product_brand = product_brand;
	}


	public String getProduct_rating() {
		return product_rating;
	}


	public void setProduct_rating(String product_rating) {
		this.product_rating = product_rating;
	}



	private String getImageUrl(Part part) {
		String savePath = MyConstants.IMAGE_DIR_SAVE_PATH;
		File fileSaveDir = new File(savePath);
		String imageUrlFromPart = null;
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				imageUrlFromPart = s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		if(imageUrlFromPart == null || imageUrlFromPart.isEmpty()) {
			imageUrlFromPart= "download.png";
		}
		return imageUrlFromPart;
	}
	
	
}
