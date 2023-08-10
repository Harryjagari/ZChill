<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@page import="resources.MyConstants"%>
<%@page import="model.Products"%>
<%@page import="java.util.List"%>
<%@page import="controller.dbconnection.DbConnection"%>
<%@page import="controller.statemanagement.SessionManage"%>
<!DOCTYPE html>
<html lang="en">

<head>
   <meta charset="UTF-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">

   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-css/product.css">
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-css/dashboard.css">

   <title>Dashboard.product</title>
   <link rel="shortcut icon" type="image/jpg" href="${pageContext.request.contextPath}/css/images/nepal.png" />


</head>

<body>
<style>
  .dropdown-button {
    background-color: #4CAF50;
    color: white;
    padding: 10px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }

  .dropdown {
    position: relative;
    display: inline-block;
  }

   .dropdown-content {
    display: none;
    position: absolute;
    z-index: 1;
    width:4rem;
    background-color: #8ddee9;
  }

  .dropdown:hover .dropdown-content {
    display: block;
  }
</style>

   <!-- admin header starts  -->

   <header class="header-area">
      <!-- site-navbar start -->
      <div class="navbar-area">
         <div class="container">
            <nav class="site-navbar">
               <!-- site logo -->
               <a href="${pageContext.request.contextPath}/pages/dashboard.jsp" class="site-logo">Admin</a>

               <form class="form">
                  <button>
                     <svg width="17" height="16" fill="none" xmlns="http://www.w3.org/2000/svg" role="img"
                        aria-labelledby="search">
                        <path d="M7.667 12.667A5.333 5.333 0 107.667 2a5.333 5.333 0 000 10.667zM14.334 14l-2.9-2.9"
                           stroke="currentColor" stroke-width="1.333" stroke-linecap="round" stroke-linejoin="round">
                        </path>
                     </svg>
                  </button>
                  <input class="input" placeholder="Search" required="" type="text">
                  <button class="reset" type="reset">
                     <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24"
                        stroke="currentColor" stroke-width="2">
                        <path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12"></path>
                     </svg>
                  </button>
               </form>


               <!-- site menu/nav -->
               <ul>
                  <li><a href="${pageContext.request.contextPath}/pages/dashboard.jsp">Dashboard</a></li>
                  <li><a href="${pageContext.request.contextPath}/pages/product.jsp">Product</a></li>
                  <li><a href="${pageContext.request.contextPath}/pages/adminorder.jsp">Orders</a></li>
                  <li><a href="${pageContext.request.contextPath}/pages/admin.jsp">Admins</a></li>
                  <li><a href="${pageContext.request.contextPath}/pages/customer.jsp">Customer</a></li>
                  <li><a href="${pageContext.request.contextPath}/pages/message.jsp">Message</a></li>
                           <% String user = (String) session.getAttribute("user");
            if (user != null) {
                // If the user is logged in, show the profile image
                // You may replace "profile.jpg" with the actual path to the user's profile image
                %>
                <div class="dropdown">
                  	<button class="dropdown-button"><%= session.getAttribute("user") %></button>
  					<div class="dropdown-content">
    					<form action="${pageContext.request.contextPath}/LogoutServlet" method="post">
  							<input type="submit" value="Logout">
						</form>
    				</div>
  				</div>
               
    			
               <!-- <img src=""xampp\\tomcat\\webapps\\images\\? alt="Profile Image">-->
		<%} else {
            // If the user is not logged in, show the sign-in button
            %>
        <!-- sign in  -->
        <a href="${pageContext.request.contextPath}/pages/adminLogin.jsp"><button class="sign_in">
                <span>Sign in</span>
            </button>
        </a>
		<%
		}
		%> 
               </ul>

               <!-- nav-toggler for mobile version only -->
               <button class="nav-toggler">
                  <span></span>
               </button>
            </nav>
         </div>
      </div><!-- navbar-area end -->


   </header>

   <!-- admin header ends -->


   <!-- add products section starts  -->

   <section class="add-products">

      <form action="${pageContext.request.contextPath}/AddProduct" method="POST" enctype="multipart/form-data">
         <h1>add product</h1>
         <input type="text" required placeholder="enter product name" name="product_name" maxlength="100" class="box">
         <input type="number" required placeholder="enter product price" name="price" class="box">
         <input type="text" required placeholder="enter product description" name="description" class="box">
         <input type="text" required placeholder="enter product type" name="product_type" class="box">
	   <input type="text" required placeholder="enter product stock" name="product_stock" class="box">
	   <input type="text" required placeholder="enter product brand" name="product_brand" class="box">
	   <input type="text" required placeholder="enter product rating" name="product_rating" class="box">
         <input type="file" name="product_image1" class="box" accept="image/jpg, image/jpeg, image/png, image/webp" required>
         <input type="submit" value="add product" name="add_product" class="btn">
      </form>

   </section>

   <!-- add products section ends -->

   <!-- show products section starts  -->


                    <!-- Step 1: Create a database connection -->
    <sql:setDataSource var="dbConnection" driver="com.mysql.jdbc.Driver"
	url="jdbc:mysql://localhost:3306/ZChill" user="root" password=""/>

	<!-- Executing Query Using SQL Tag Library -->
	<sql:query var="allProduct" dataSource="${dbConnection}">
		SELECT * FROM product
	</sql:query>

   <div class="user-table">
      <table>
         <tr>
            <th class="product">Product Name</th>
            <th class="product">Product Description</th>
            <th class="product">Product Price</th>
            <th class="product"> Product Photo</th>
            <th class="product"> Update</th>
            <th class="product" > Delete</th>
         </tr>
         <c:forEach var="product" items="${allProduct.rows}">
         <tr>
            <td>${product.product_name}</td>
            <td>${product.description}</td>
            <td>Rs <span>${product.price}</span></td>
            <td><img src="http://localhost:8080/images/${product.product_image1}" alt="Product"></td>
            
            <td><button type="submit"><a href="${pageContext.request.contextPath}/pages/adminProductUpdate.jsp?id=${product.product_id}">Update</button></td>
            
            	<input type="hidden" name="product_id" value="${product.product_id}" />
            	<td><button type="submit"><a href="${pageContext.request.contextPath}/DeleteProduct?product_id=${product.product_id}"  id="delete">Delete</button></td>
            
         </tr>
		</c:forEach>
      </table>
   </div>




</body>

<!-- deleting js  -->
<script>

</script>


<script src="${pageContext.request.contextPath}/css/admin-js/style.js"></script>

</html>