<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>User Products</title>

<link rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>

<body class="bg-light">

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="/home">
            <img src="/images/logo.png" height="40" />
        </a>

        <button class="navbar-toggler" type="button" data-toggle="collapse"
            data-target="#navbarSupportedContent">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto"></ul>
            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link" href="/home">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="/profileDisplay">Profile</a></li>
                <li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid mt-4">

    <table class="table table-bordered">
        <tr>
            <th>Sr No.</th>
            <th>Product</th>
            <th>Category</th>
            <th>Preview</th>
            <th>Qty</th>
            <th>Price</th>
            <th>Weight</th>
            <th>Description</th>
            <th>Buy</th>
        </tr>

        <tbody>
        <c:forEach var="product" items="${products}">
            <tr>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.category.name}</td>
                <td><img src="${product.image}" width="100" height="100"></td>
                <td>${product.quantity}</td>
                <td>₹ ${product.price}</td>
                <td>${product.weight} g</td>
                <td>${product.description}</td>

                <td>
                    <form action="/addtocart" method="post">
                        <input type="hidden" name="id" value="${product.id}">
                        <input type="submit" class="btn btn-warning" value="Add To Cart">
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>

</body>
</html>
