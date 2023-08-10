<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-css/product-up.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-css/dashboard.css">

    <title>Dashboard.update-product</title>
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
                                <path
                                    d="M7.667 12.667A5.333 5.333 0 107.667 2a5.333 5.333 0 000 10.667zM14.334 14l-2.9-2.9"
                                    stroke="currentColor" stroke-width="1.333" stroke-linecap="round"
                                    stroke-linejoin="round"></path>
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
                        <li><a href="${pageContext.request.contextPath}/pages/order.jsp">Orders</a></li>
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

	<c:set var="id" value="${param.id}"/>

    <div class="edit-user">
        <form action="${pageContext.request.contextPath}/UpdateProduct"  method="post" class="edit-form">
            <h1>EDIT PRODUCT</h1>



            <!-- profile pic ends  -->
            <div class="formcontainer">
                <div class="container">
					<input type="hidden" name="id" value="${id}">
                    <label for="uname"><strong> Product Name</strong></label>
                    <input type="text" placeholder="Product Name" name="pname">
                    <label for="mail"><strong>Product Description</strong></label>
                    <input type="text" placeholder="Product Description" name="desc">
                    <label for="mail"><strong>Product Price</strong></label>
                    <input type="text" placeholder="Product Price" name="price">
                </div>
            </div>

            <button type="submit">Update</button>

        </form>
    </div>


    <!-- updating the profile pic -->
    <script>
        var loadFile = function (event) {
            var image = document.getElementById("output");
            image.src = URL.createObjectURL(event.target.files[0]);
        };

    </script>

    <script src="${pageContext.request.contextPath}/css/admin-js/style.js"></script>

</body>

</html>