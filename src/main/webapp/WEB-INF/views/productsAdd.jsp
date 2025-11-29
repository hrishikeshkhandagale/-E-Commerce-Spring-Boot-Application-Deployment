<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<meta name="viewport"
    content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
    crossorigin="anonymous">
<link rel="stylesheet"
    href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
    crossorigin="anonymous">
<title>Document</title>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#"> <img
            th:src="@{/images/logo.png}" src="../static/images/logo.png"
            width="auto" height="40" class="d-inline-block align-top" alt="" />
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse"
            data-target="#navbarSupportedContent">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto"></ul>
            <ul class="navbar-nav">
                <li class="nav-item active"><a class="nav-link" href="/admin/Dashboard">Home Page</a></li>
                <li class="nav-item active"><a class="nav-link" href="/admin/logout">Logout</a></li>
            </ul>
        </div>
    </div>
</nav><br>

<div class="jumbotron container border border-info">
    <h3>Add a new Product</h3>

    <!-- ❗ Important: products loop काढले (ID auto increment DB करत असल्यामुळे) -->
    <form action="/admin/products/add" method="post">
        <div class="row">
            <div class="col-sm-5">

                <div class="form-group">
                    <label for="name">Name</label>
                    <input type="text" class="form-control border border-warning" required name="name" placeholder="Enter name">
                </div>

                <div class="form-group">
                    <label for="category">Select Category</label>
                    <select class="form-control border border-warning" name="categoryid" required>
                        <option selected disabled>Select a Category</option>
                        <c:forEach var="category" items="${categories}">
                            <option value="${category.id}">${category.name}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="price">Price</label>
                    <input type="number" class="form-control border border-warning" required name="price" min="1" placeholder="Price">
                </div>

                <div class="form-group">
                    <label for="weight">Weight in grams</label>
                    <input type="number" class="form-control border border-warning" required name="weight" min="1" placeholder="Weight">
                </div>

                <div class="form-group">
                    <label for="weight">Available Quantity</label>
                    <input type="number" class="form-control border border-warning" required name="quantity" min="1" placeholder="Quantity">
                </div>

            </div>

            <div class="col-sm-5"><br>

                <div class="form-group">
                    <label for="description">Product Description</label>
                    <textarea class="form-control border border-warning" rows="4"
                        name="description" placeholder="Product Details"></textarea>
                </div>

                <div class="form-group">
                    <label for="Image">Image Link</label>
                    <input type="text" class="form-control border border-warning" required name="productImage" placeholder="Enter Short Image Link">
                </div>

                <div class="form-group">
                    <img id="imgPreview" height="100px" width="100px" style="margin-top: 20px" >
                </div>

                <input type="submit" class="btn btn-primary">
            </div>
        </div>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
<script>
/* future image preview ठेवले आहे, जर file upload जोडणार असाल तर */
var loadFile = function(event) {
    var image = document.getElementById('imgPreview');
    image.src = URL.createObjectURL(event.target.files[0]);
};
</script>
</body>
</html>
