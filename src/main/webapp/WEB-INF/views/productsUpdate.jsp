<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
    content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<link rel="stylesheet"
    href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
<title>Update Product</title>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#"> 
            <img src="../static/images/logo.png" width="auto" height="40">
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse"
            data-target="#navbarSupportedContent">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto"></ul>
            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link" href="/admin/Dashboard">Home Page</a></li>
                <li class="nav-item"><a class="nav-link" href="/admin/logout">Logout</a></li>
            </ul>
        </div>
    </div>
</nav><br>

<div class="jumbotron container border border-info">
    <h3>Update Existing Product</h3>
    
    <!-- IMPORTANT: dynamic ID inside action -->
    <form action="/admin/products/update/${product.id}" method="post">
        <div class="row">
            <div class="col-sm-5">

                <div class="form-group">
                    <label>Id</label>
                    <input type="number" class="form-control border border-success"
                        readonly name="id" value="${product.id}">
                </div>

                <div class="form-group">
                    <label>Name</label>
                    <input type="text" required class="form-control border border-success"
                        name="name" value="${product.name}">
                </div>

                <div class="form-group">
                    <label>Select Category</label>
                    <select class="form-control border border-success" name="categoryid" required>
                        <c:forEach var="category" items="${categories}">
                            <option value="${category.id}" 
                                <c:if test="${category.id == product.category.id}">selected</c:if>>
                                ${category.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label>Price</label>
                    <input type="number" class="form-control border border-success"
                        required name="price" min="1" value="${product.price}">
                </div>

                <div class="form-group">
                    <label>Weight in grams</label>
                    <input type="number" class="form-control border border-success"
                        required name="weight" min="1" value="${product.weight}">
                </div>

                <div class="form-group">
                    <label>Available Quantity</label>
                    <input type="number" class="form-control border border-success"
                        required name="quantity" min="1" value="${product.quantity}">
                </div>
            </div>

            <div class="col-sm-5">
                <div class="form-group">
                    <label>Description</label>
                    <textarea class="form-control border border-success"
                        rows="4" name="description">${product.description}</textarea>
                </div>

                <div class="form-group">
                    <label>Product Image (Insert link)</label>
                    <input type="text" class="form-control border border-success"
                        required name="productImage" value="${product.image}">
                </div>

                <div class="form-group">
                    <img id="imgPreview" src="${product.image}"
                        height="100px" width="100px" style="margin-top: 15px">
                </div>

                <input type="submit" value="Update Details" class="btn btn-primary">
            </div>
        </div>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
<script>
function loadFile(event) {
    document.getElementById('imgPreview').src = URL.createObjectURL(event.target.files[0]);
}
</script>

</body>
</html>
