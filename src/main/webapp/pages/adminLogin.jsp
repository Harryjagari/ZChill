<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-css/login.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-css/dashboard.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10.16.0/dist/sweetalert2.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10.16.0/dist/sweetalert2.min.css">
    <title>Admin.login</title>
    <link rel="shortcut icon" type="image/jpg" href="${pageContext.request.contextPath}/css/images/nepal.png" />


</head>

<body>


    <!-- login starts  -->
    <div class="login-admin">
        <div class="login">
            <h2>Admin Login</h2>
            <form action="${pageContext.request.contextPath}/AdminLoginServlet" method="post">
                <input type="text" name="username" placeholder="Username" value="" class="text" required>
                <input type="Password" name="password" placeholder="Password" value="" class="text" required>
                <button type="submit" value="Login" class="btn">Login</button>
            </form>
        </div>
    </div>
 <script>
	var message = "${Message}";
	if (message) {
    Swal.fire({
        text: message,
        icon: 'error',
        confirmButtonText: 'OK'
    });
}
</script>
</body>



</html>