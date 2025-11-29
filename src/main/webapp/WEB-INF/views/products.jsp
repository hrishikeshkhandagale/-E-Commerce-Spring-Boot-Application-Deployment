<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin | Products</title>

    <!-- Bootstrap -->
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">

    <style>
        body { background-color: #f4f6f9; }
        .table td, .table th { vertical-align: middle; }
        .product-img {
            height: 70px;
            width: 70px;
            object-fit: cover;
            border-radius: 8px;
            border: 2px solid #ddd;
        }
        .navbar-brand { font-weight: 700; }
        .btn-add {
            float: right;
            margin-bottom: 12px;
        }
        .badge-category {
            background: #007bff;
            padding: 4px 8px;
            border-radius: 6px;
            font-size: 13px;
            font-weight: 600;
            color: white;
        }
        .table-hover tbody tr:hover {
            background: #e9f5ff;
        }
    </style>
</head>

<body>

<nav class="navbar navbar-dark bg-dark navbar-expand-lg">
    <div class="container-fluid">
        <a class="navbar-brand" href="/admin/Dashboard">Admin Panel</a>
        <ul class="navbar-nav ms-auto">
            <li class="nav-item"><a class="nav-link text-white" href="/admin/Dashboard">🏠 Home</a></li>
            <li class="nav-item"><a class="nav-link text-white" href="/admin/logout">🚪 Logout</a></li>
        </ul>
    </div>
</nav>

<div class="container mt-4">
    <div class="d-flex align-items-center">
        <h3 class="fw-bold">📦 Product Management</h3>
        <a href="/admin/products/add" class="btn btn-primary btn-add">➕ Add Product</a>
    </div>
    <hr>

    <table class="table table-bordered table-hover shadow-sm bg-white">
        <thead class="table-dark">
        <tr>
            <th>#</th>
            <th>Preview</th>
            <th>Name</th>
            <th>Category</th>
            <th>Qty</th>
            <th>Price</th>
            <th>Weight</th>
            <th width="20%">Description</th>
            <th>Delete</th>
            <th>Update</th>
        </tr>
        </thead>

        <tbody>
        <c:if test="${empty products}">
            <tr>
                <td colspan="10" class="text-center py-4 text-danger fw-bold fs-5">
                    ❗ No Products Available
                </td>
            </tr>
        </c:if>

        <c:forEach var="product" items="${products}" varStatus="loop">
            <tr>
                <td><b>${loop.index + 1}</b></td>
                <td><img src="${product.image}" class="product-img"></td>
                <td>${product.name}</td>
                <td><span class="badge-category">${product.category.name}</span></td>
                <td>${product.quantity}</td>
                <td>₹ ${product.price}</td>
                <td>${product.weight}</td>
                <td>${product.description}</td>

                <td>
                    <form action="/admin/products/delete" method="get"
                          onsubmit="return confirm('Are you sure you want to delete this product?');">
                        <input type="hidden" name="id" value="${product.id}">
                        <button type="submit" class="btn btn-danger btn-sm">🗑 Delete</button>
                    </form>
                </td>

                <td>
                    <a href="/admin/products/update/${product.id}" class="btn btn-warning btn-sm">✏ Update</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
