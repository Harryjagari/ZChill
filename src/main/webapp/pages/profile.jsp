<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@page import="resources.MyConstants"%>
<%@page import="java.util.List"%>
<%@page import="controller.dbconnection.DbConnection" %>
<%@page import="java.sql.Connection" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style-user.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

    <title>Website.profile</title>
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
                    <span class="icon">
                        <svg viewBox="0 0 175 80" width="40" height="40">
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
        <!-- search ends  -->
        	<% String username = (String) session.getAttribute("username");
            if (username != null) {
                // If the user is logged in, show the profile image
                // You may replace "profile.jpg" with the actual path to the user's profile image
                %>
                <div class="dropdown">
                  	<button class="dropdown-button"><%= session.getAttribute("username") %></button>
  					<div class="dropdown-content">
    					<a href="${pageContext.request.contextPath}/pages/profile.jsp">Profile</a>
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
        <a href="${pageContext.request.contextPath}/pages/login.jsp"><button class="sign_in">
                <span>Sign in</span>
            </button>
        </a>
		<%
		}
		%>



    </header>
    <!-- header ends -->
                 <!-- Step 1: Create a database connection -->
    <sql:setDataSource var="dbConnection" driver="com.mysql.jdbc.Driver"
	url="jdbc:mysql://localhost:3306/ZChill" user="root" password=""/>
	
	
	<sql:query var="registerId" dataSource="${dbConnection}">
  		SELECT register_Id FROM register WHERE username = '${sessionScope.username}'
	</sql:query>
			<c:set var="register_Id" value="${registerId.rows[0].register_Id}" />
	<sql:query var="email" dataSource="${dbConnection}">
  		SELECT email FROM register WHERE username = '${sessionScope.username}'
	</sql:query>
			<c:set var="email" value="${email.rows[0].email}" />

	<!-- Executing Query Using SQL Tag Library -->
	<sql:query var="allOrders" dataSource="${dbConnection}">
	SELECT r.username,p.product_name, c.quantity, p.price, o.address
	FROM register r
	INNER JOIN cart c ON r.register_Id = c.register_Id
	INNER JOIN orders o ON c.cart_id = o.cart_id
	INNER JOIN product p ON c.product_id = p.product_id
	WHERE r.register_Id = ${register_Id}
	</sql:query>


    <!-- main section starts  -->
    <div class="profile">
        <div class="description">
            <div class="image-des">
                <img src="${pageContext.request.contextPath}/css/images/user.jpg" alt="Avatar" class="profile-pic">
                <div class="name">
                    <h2><%= session.getAttribute("username") %></h2>
                    <p><c:out value="${email}" /></p>
                    <div class="edit-button">
                        <a href="${pageContext.request.contextPath}/pages/profileUpdate.jsp"><img src="${pageContext.request.contextPath}/css/images/edit-pen.png" alt=""></a>
                    </div>
                </div>
            </div>



        </div>

        <div class="right-side">
            <div class="order-history">
                <h2> Your Orders </h2>

                <table>
   			<tr>
                <th class="heading">Customer Name</th>
                <th class="heading">Product</th>
                <th class="heading">Price</th>
                <th class="heading">Quantity</th>
                <th class="heading">Address</th>
            </tr>
            <!-- 1st  -->
            <c:forEach var="orders" items="${allOrders.rows}">
            <tr>
                <td>${orders.username}</td>
                <div class="p-name">
                    <td>
                        <p> <span class="order-name">${orders.product_name}</span> </p>
                    </td>
                </div>
                <td>${orders.price}</td>
                <td>${orders.quantity}</td>
                <td>${orders.address}</td>
            </tr>
		 	</c:forEach>
  		</table>
            </div>

        </div>

    </div>

    <!-- main section ends  -->






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
                        <a href="#"><img src="${pageContext.request.contextPath}/css/images/other/facebook.png" alt=""></a>
                        <a href="#"><img src="${pageContext.request.contextPath}/css/images/other/linkedin.png" alt=""></a>
                    </div>
                </div>
            </div>
        </div>
    </footer>

    <!-- footer ends  -->


</body>

<script src="${pageContext.request.contextPath}/css/js/script.js"></script>

</html>