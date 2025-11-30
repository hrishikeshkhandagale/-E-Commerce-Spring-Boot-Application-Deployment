<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>User Registration</title>

    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">

    <style>
        body {
            background: #f3f6ff;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .register-box {
            max-width: 500px;
            width: 100%;
            background: white;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.15);
        }
    </style>
</head>

<body>

<div class="register-box">
    <h3 class="text-center mb-3">Create Your Account 🚀</h3>
    <p class="text-center text-muted mb-4">Fill the details to continue</p>

    <form action="/newuserregister" method="post">

        <div class="form-group">
            <label><b>Username</b></label>
            <input type="text" name="username" required placeholder="Enter username"
                   class="form-control form-control-lg">
        </div>

        <div class="form-group">
            <label><b>Email Address</b></label>
            <input type="email" name="email" required minlength="6"
                   placeholder="Enter email"
                   class="form-control form-control-lg">
        </div>

        <div class="form-group">
            <label><b>Password</b></label>
            <input type="password" name="password" required minlength="4"
                   placeholder="Enter password"
                   class="form-control form-control-lg">
        </div>

        <div class="form-group">
            <label><b>Address</b></label>
            <textarea name="address" rows="3"
                      class="form-control form-control-lg"
                      placeholder="Enter your address"></textarea>
        </div>

        <button type="submit" class="btn btn-primary btn-block btn-lg mt-2">
            <i class="fas fa-user-plus"></i> Register
        </button>

        <!-- 🔥 Error message -->
        <c:if test="${not empty msg}">
            <div class="alert alert-danger text-center mt-3">${msg}</div>
        </c:if>

        <p class="mt-3 text-center">
            Already have an account?
            <a href="/login">Login here</a>
        </p>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

</body>
</html>
