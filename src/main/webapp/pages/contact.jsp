<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.PreparedStatement" %>
<%@page import="controller.dbconnection.DbConnection" %>
<%@page import="java.sql.Connection" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- css link  -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style-contact.css">
    <title>Website.Contact</title>
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
                  stroke="currentColor" stroke-width="1.333" stroke-linecap="round" stroke-linejoin="round"></path>
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

          
        <!-- sign in  -->
		<% String user = (String) session.getAttribute("username");
            if (user != null) {
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




    <!-- contact starts  -->

    <section id="contact">
        <div class="contact-wrapper">

            <h1 class="contact-heading">Contact</h1>



            <div class="container">
                <div class="content">
                    <div class="left-side">
                        <div class="address details">
                            <div class="topic">Address</div>
                            <div class="text-one">Kathmandu</div>
                            <div class="text-two">KamalPokhari 10</div>
                        </div>
                        <div class="phone details">
                            <div class="topic">Phone</div>
                            <div class="text-one">+977-9849521364</div>
                        </div>
                        <div class="email details">
                            <div class="topic">Email</div>
                            <div class="text">zchill.company@gmail.com</div>
                        </div>
                    </div>
                    <div class="right-side">
                        <div class="topic-text">Message Us</div>
                        <p>Give us some feedback. If you have some designs then you can send us the link and we will
                            contact you later.</p>
                        <form action="contact.jsp" method="post">
                            <div class="input-box">
                                <input type="text" placeholder="Enter your name" name="username"required>
                            </div>
                            <div class="input-box">
                                <input type="text" placeholder="Enter your email" name="email" required>
                            </div>
                            <div class="input-box message-box">
                                <input type="text" placeholder="Enter the Message" name="message" required>
                            </div>
                            <div class="button">
                                <input type="submit" value="Send Now">
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </div>

    </section>

    <!-- contact ends  -->
	<jsp:useBean id="Feedback" class="model.Feedback" scope="page"/>
	<jsp:setProperty property="*" name="Feedback"/>
	
	<%
	Connection con = DbConnection.getConnection();
	if(con==null){
		out.println("Cannot connect to database");
	}else{
		try{
			String query = "Insert into feedback(username,email,message)"+"values(?,?,?)";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1,Feedback.getUsername());
			st.setString(2,Feedback.getEmail());
			st.setString(3,Feedback.getMessage());
			int result = st.executeUpdate();
			if(result>0){
				out.print("Data Inserted");	
			}else{
				out.print("Data Insert failed");	
			}
		}catch(SQLException ex){
			out.println(ex.getMessage());
			}
		}
	%>



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