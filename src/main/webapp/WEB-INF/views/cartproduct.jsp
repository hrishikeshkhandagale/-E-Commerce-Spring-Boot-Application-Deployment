<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width">
    <title>Your Cart</title>

    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>

<body class="bg-light">

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="/home">🛒 E-Commerce</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarSupportedContent">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item"><a class="nav-link" href="/home">Products</a></li>
                <li class="nav-item"><a class="nav-link" href="/profileDisplay">Profile</a></li>
                <li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container mt-4">

    <h3 class="text-center mb-4">🛍 Your Cart</h3>

    <c:if test="${not empty msg}">
        <div class="alert alert-info text-center">${msg}</div>
    </c:if>

    <table class="table table-bordered table-hover text-center">
        <thead class="thead-dark">
        <tr>
            <th>#</th>
            <th>Product</th>
            <th>Price</th>
            <th>Description</th>
            <th>Remove</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="c" items="${cartItems}" varStatus="loop">
            <tr>
                <td>${loop.index + 1}</td>
                <td>${c.product.name}</td>
                <td>₹ ${c.product.price}</td>
                <td>${c.product.description}</td>

                <td>
                    <form action="/cart/delete" method="get">
                        <input type="hidden" name="id" value="${c.id}">
                        <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="text-right">
        <a href="/user/products" class="btn btn-primary">🔙 Continue Shopping</a>
    </div>

</div>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

</body>
</html>
