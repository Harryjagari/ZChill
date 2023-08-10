<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="controller.Dao.ProductDao"%>
<%@page import="model.*"%>
<%@page import="resources.MyConstants"%>
<%@page import="java.util.*"%>
<%@page import="controller.dbconnection.DbConnection"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@page import="java.sql.Connection"%>
<%
    // Check if the user is logged in
    String username = (String) session.getAttribute("username");
    if (username == null) {
        // User is not logged in, redirect to the login page
        response.sendRedirect(request.getContextPath()+"/pages/login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style-cart.css">

<title>Website.Cart</title>
<link rel="shortcut icon" type="image/jpg"
	href="${pageContext.request.contextPath}/css/images/nepal.png" />
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

	<!-- this is the header part  -->

	<header>



		<!-- group div starts -->
		<div class="group">
			<!-- navigation start -->
			<nav class="nav_bar">
				<ul>
					<li><a href="${pageContext.request.contextPath}/pages/index.jsp">home</a></li>
					<li><a href="${pageContext.request.contextPath}/pages/shop.jsp">shop</a></li>
					<li><a href="${pageContext.request.contextPath}/pages/cart.jsp">cart</a></li>
					<li><a href="${pageContext.request.contextPath}/pages/contact.jsp">contact</a></li>
				</ul>
			</nav>

			<!-- menulines -->
			<div class="menuline">
				<button class="btn">
					<span class="icon"> <svg viewBox="0 0 175 80" width="40"
							height="40">
              <rect width="80" height="15" fill="#f0f0f0" rx="10"></rect>
              <rect y="30" width="80" height="15" fill="#f0f0f0" rx="10"></rect>
              <rect y="60" width="80" height="15" fill="#f0f0f0" rx="10"></rect>
            </svg>
					</span>
				</button>
			</div>
		</div>


		<!-- search starts -->
		<form class="form">
			<button>
				<svg width="17" height="16" fill="none"
					xmlns="http://www.w3.org/2000/svg" role="img"
					aria-labelledby="search">
          <path
						d="M7.667 12.667A5.333 5.333 0 107.667 2a5.333 5.333 0 000 10.667zM14.334 14l-2.9-2.9"
						stroke="currentColor" stroke-width="1.333" stroke-linecap="round"
						stroke-linejoin="round"></path>
        </svg>
			</button>
			<input class="input" placeholder="Search" required="" type="text">
			<button class="reset" type="reset">
				<svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none"
					viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
          <path stroke-linecap="round" stroke-linejoin="round"
						d="M6 18L18 6M6 6l12 12"></path>
        </svg>
			</button>
		</form>
		<!-- search ends  -->

		<!-- sign in  -->
		<%
          if (username != null) {
                // If the user is logged in, show the profile image
                // You may replace "profile.jpg" with the actual path to the user's profile image
                %>
		<div class="dropdown">
			<button class="dropdown-button"><%= session.getAttribute("username") %></button>
			<div class="dropdown-content">
				<a href="${pageContext.request.contextPath}/pages/profile.jsp">Profile</a>
				<form action="${pageContext.request.contextPath}/LogoutServlet"
					method="post">
					<input type="submit" value="Logout">
				</form>
			</div>
		</div>


		<!-- <img src=""xampp\\tomcat\\webapps\\images\\? alt="Profile Image">-->
		<%} else {
			
            // If the user is not logged in, show the sign-in button
            %>
		<!-- sign in  -->
		<a href="${pageContext.request.contextPath}/pages/login.jsp"><button
				class="sign_in">
				<span>Sign in</span>
			</button> </a>
		<%
		}
		%>		

	</header>
	<!-- header ends -->


	<section id="cart">
		<div class="cart-wrapper">

			<h1 class="cart-heading">Cart</h1>

			<!-- cart starts  -->

			<!-- Step 1: Create a database connection -->
			<sql:setDataSource var="dbConnection" driver="com.mysql.jdbc.Driver"
				url="jdbc:mysql://localhost:3306/ZChill" user="root" password="" />


			<sql:query var="registerId" dataSource="${dbConnection}">
  		SELECT register_Id FROM register WHERE username = '${sessionScope.username}'
	</sql:query>
			<c:set var="register_Id" value="${registerId.rows[0].register_Id}" />
			<!-- Executing Query Using SQL Tag Library -->
			<sql:query var="allCart" dataSource="${dbConnection}">
		SELECT c.cart_id,p.product_id,p.product_name, p.price, c.quantity, p.product_image1 
		FROM cart c 
		JOIN product p ON c.product_id = p.product_id 
		JOIN register r ON c.register_Id = r.register_Id 
		WHERE c.register_Id = ${register_Id}
	</sql:query>


				<div class="cart">

					<table>
						<tr>
							<th>Product</th>
							<th>Quantity</th>

						</tr>
						<c:forEach var="cart" items="${allCart.rows}">
							<!-- products  -->

							<tr>
								<td>
									<div class="cart-info">
										<img src="http://localhost:8080/images/${cart.product_image1}"
											alt="product">
										<div>
											<p>${cart.product_name}</p>
											<small>${cart.price}</small> <br>
											<a href="${pageContext.request.contextPath}/RemoveItemFromCart?cart_id=${cart.cart_id}">Remove</a>
										</div>
									</div>

								</td>
								<td>${cart.quantity}</td>

							</tr>
						</c:forEach>
					</table>


						<div class="edit">
							<form action="${pageContext.request.contextPath}/AddOrder" method="post">
							<input type="hidden" name="cart_id" value="${sessionScope.cart_id}">
							<input type="text" placeholder="Delivery address" name="address"
								required>
							<div class="proceed-btn">
								<a href=""><button type="submit">Place Order</button></a>		
							</div>
							</form>
						</div>



				</div>




	</section>



	<!-- cart ends  -->



	<!-- footer starts  -->
	<footer class="footer">
		<div class="footer-container">
			<div class="row">
				<div class="footer-col">
					<h4>company</h4>
					<ul>
						<li><a href="#">about us</a></li>
						<li><a href="#">our services</a></li>
						<li><a href="#">privacy policy</a></li>
						<li><a href="#">affiliate program</a></li>
					</ul>
				</div>
				<div class="footer-col">
					<h4>nav</h4>
					<ul>
						<li><a href="${pageContext.request.contextPath}/pages/index.jsp">Home</a></li>
						<li><a href="${pageContext.request.contextPath}/pages/shop.jsp">Shop</a></li>
						<li><a href="${pageContext.request.contextPath}/pages/contact.jsp">Contact</a></li>
					</ul>
				</div>
				<div class="footer-col">
					<h4>Address</h4>
					<p>Kathmandu,Nepal</p>
					<p>Basundhara</p>
				</div>
				<div class="footer-col">
					<h4>follow us</h4>
					<div class="social-links">
						<a href="#"><img
							src="${pageContext.request.contextPath}/css/images/other/facebook.png"
							alt=""></a> <a href="#"><img
							src="${pageContext.request.contextPath}/css/images/other/linkedin.png"
							alt=""></a>
					</div>
				</div>
			</div>
		</div>
	</footer>

	<!-- footer ends  -->



</body>

<script src="${pageContext.request.contextPath}/css/js/script.js"></script>

</html>