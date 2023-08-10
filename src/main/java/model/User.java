package model;

import java.io.File;

import javax.servlet.http.Part;

import resources.MyConstants;

public class User {
	int register_id;

	String username,email, password,imageUrlFromPart;

	public User() {}
	public User(int register_id,String username, String email,String password,Part part) {
		this.register_id = register_id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.imageUrlFromPart = getImageUrl(part);	
	}
	
	public User(String username, String email,
			String password , Part part){
		this.username = username;
		this.email = email;
		this.password = password;
		this.imageUrlFromPart = getImageUrl(part);
	}
	
	public void setRegister_id(int register_id) {
		this.register_id = register_id;
	}
	public int getRegister_id() {
		return register_id;
	}
	
	public String getImageUrlFromPart() {
		return imageUrlFromPart;
	}
	
	public void setImageUrlFromPart(Part part) {
		this.imageUrlFromPart = getImageUrl(part);
	}
	public String getUsername() {
		return username	;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email	;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
