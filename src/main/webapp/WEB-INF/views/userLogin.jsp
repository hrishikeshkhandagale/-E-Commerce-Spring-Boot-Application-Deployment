<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>User Login</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">

    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f8f9fa;
        }
        .login-container {
            max-width: 400px;
            width: 100%;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            border-radius: 8px;
            background: white;
        }
    </style>
</head>
<body>

<div class="login-container p-4">
    <div class="jumbotron border p-4">
        <h2 class="text-center mb-4">User Login</h2>

        <!-- 🔥 Error message (if wrong password) -->
        <c:if test="${not empty msg}">
            <div class="alert alert-danger text-center">${msg}</div>
        </c:if>

        <form action="/userloginvalidate" method="post">
            <div class="form-group">
                <label for="username">Username</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fas fa-user"></i></span>
                    </div>
                    <input type="text" name="username" id="username"
                           placeholder="Username*" required class="form-control form-control-lg">
                </div>
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fas fa-lock"></i></span>
                    </div>
                    <input type="password" name="password" id="password"
                           placeholder="Password*" required class="form-control form-control-lg">
                </div>
            </div>

            <button type="submit" class="btn btn-primary btn-block mt-3">Login</button>

            <p class="text-center mt-3">
                Don't have an account? <a href="/register">Register here</a>
            </p>
        </form>
    </div>
</div>

</body>
</html>
