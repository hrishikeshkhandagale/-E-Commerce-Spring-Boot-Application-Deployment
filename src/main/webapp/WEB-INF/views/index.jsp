<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>User Dashboard</title>

<link rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<link rel="stylesheet"
    href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">

<style>
    .card-body { height: 260px; }
    .card-img-top { max-height: 120px; object-fit: contain; }
</style>
</head>

<body class="bg-light">

<!-- 🔥 NAVBAR -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">

        <a class="navbar-brand" href="/home">
            <img src="/images/logo.png" height="40">
        </a>

        <button class="navbar-toggler" type="button" data-toggle="collapse"
            data-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNav">

            <h5 class="text-white ml-3 mt-1">Welcome ${username}</h5>
            <ul class="navbar-nav ml-auto">

                <li class="nav-item">
                    <a class="nav-link" href="/cart">Cart</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="/profileDisplay">Profile</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="/logout">Logout</a>
                </li>

            </ul>

        </div>

    </div>
</nav>

<!-- 🔥 PRODUCTS GRID -->
<div class="container mt-4">
    <h2 class="mb-4 text-center">Available Products</h2>

    <div class="row">

        <c:forEach var="product" items="${products}">
            <div class="col-md-3">
                <div class="card mb-4 shadow-sm">
                    <img class="card-img-top" src="${product.image}" alt="product">

                    <div class="card-body">
                        <h5><b>${product.name}</b></h5>
                        <p class="mb-1">Category: ${product.category.name}</p>
                        <p class="mb-1">Price: ₹ ${product.price}</p>
                        <p class="text-muted small">${product.description}</p>

                        <form action="/addtocart" method="post">
                            <input type="hidden" name="id" value="${product.id}">
                            <button type="submit" class="btn btn-primary btn-block">
                                <i class="fas fa-shopping-cart"></i> Add to Cart
                            </button>
                        </form>
                    </div>

                </div>
            </div>
        </c:forEach>

    </div>

</div>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

</body>
</html>
