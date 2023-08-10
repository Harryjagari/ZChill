<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style-register.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10.16.0/dist/sweetalert2.min.js"></script>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10.16.0/dist/sweetalert2.min.css">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <title>Website.Login</title>
  <link rel="shortcut icon" type="image/jpg" href="${pageContext.request.contextPath}/css/images/nepal.png" />
</head>
<body>
<%
  // Check if a session exists
  HttpSession loginsession = request.getSession(false);
  if (loginsession != null && loginsession.getAttribute("username") != null) {
     // Redirect to index.jsp if a session already exists
    response.sendRedirect("index.jsp");
  }
%>
	
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


  </header>
  <!-- header ends -->


  <section id="register">
    <div class="register-wrapper">
  

      <h1 class="register-heading">Welcome</h1>

	  	
      <div class="form">

        <div class="container" id="container">
          <div class="form-container sign-up-container">
            <form action="${pageContext.request.contextPath}/UserRegister" method="post" enctype="multipart/form-data">
              <h1>Create Account</h1>

              <input type="text" placeholder="Name" name="username" required>
              <input type="email" placeholder="Email" name="email" required>
              <input type="password" placeholder="Password" name="password" required>

              <input type="file" id="img" name="image" accept="image/*" required >
              <a href="#"><button type="submit">Sign Up</button></a>
            </form>
          </div>

          <!-- sign -in starts  -->
         
          <div class="form-container sign-in-container">
            <form action="${pageContext.request.contextPath}/LoginServlet" method="post">
              <h1>Login</h1>

              <input type="text" placeholder="username" name="username" required>
              <input type="password" placeholder="Password" name="password" required>
              
              <a href=""><button type="submit">Sign In</button></a>
              <a href="adminLogin.jsp">Use as Admin</a>
            </form>
          </div>
          <!-- sign -in ends  -->


          <!-- overlay starts  -->
          <div class="overlay-container">
            <div class="overlay">
              <div class="overlay-panel overlay-left">
                <h1>Welcome Back!</h1>
                <p>To keep connected with us please login with your personal info</p>
                <button class="ghost" id="signIn">Sign In</button>
              </div>
              <div class="overlay-panel overlay-right">
                <h1>Hello, Friend!</h1>
                <p>Enter your personal details and start journey with us</p>
                <button class="ghost" id="signUp">Sign Up</button>
              </div>
            </div>
          </div>
          <!-- overlay ends  -->


        </div>

      </div>

    </div>
  </section>

<!-- register ends  -->

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
 <script>
	var message = "${errorMessage}";
	if (message) {
    Swal.fire({
        text: message,
        icon: 'error',
        confirmButtonText: 'OK'
    });
}

	var message = "${registerMessage1}";
	if (message) {
    Swal.fire({
        text: message,
        icon: 'error',
        confirmButtonText: 'OK'
    });
}
	var message = "${registerMessage2}";
	if (message) {
    Swal.fire({
        text: message,
        icon: 'success',
        confirmButtonText: 'OK'
    });
}
</script>
 

  <!-- footer ends  -->
	
</body>

<script src="${pageContext.request.contextPath}/css/js/script-register.js"></script>
<script src="${pageContext.request.contextPath}/css/js/script.js"></script>

</html>