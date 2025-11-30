<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>User Profile</title>

<link rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>

<body class="bg-light">

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="/home">
            <img src="/images/logo.png" height="40">
        </a>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item"><a class="nav-link" href="/home">Home</a></li>
            <li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
        </ul>
    </div>
</nav>

<div class="container mt-5">
    <div class="col-sm-6 mx-auto">
        <h3 class="mb-4 text-center">Update Profile</h3>

        <form action="/updateuser" method="post">
            <input type="hidden" name="userid" value="${userid}">

            <div class="form-group">
                <label>Username</label>
                <input type="text" name="username" value="${username}" required class="form-control form-control-lg">
            </div>

            <div class="form-group">
                <label>Email ID</label>
                <input type="email" name="email" value="${email}" required class="form-control form-control-lg">
            </div>

            <div class="form-group">
                <label>Password</label>
                <input type="password" name="password" value="${password}" required class="form-control form-control-lg">
            </div>

            <div class="form-group">
                <label>Address</label>
                <textarea name="address" rows="3" class="form-control form-control-lg">${address}</textarea>
            </div>

            <input type="submit" value="Save Changes" class="btn btn-primary btn-block">
        </form>
    </div>
</div>

</body>
</html>
