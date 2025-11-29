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
<title>Admin Dashboard</title>
</head>

<body style="background:#f0f2f5;">
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="/admin/">
				<img src="/images/logo.png" height="40">
			</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto"></ul>
				<ul class="navbar-nav">
					<li class="nav-item active"><a class="nav-link" href="/admin/">Home</a></li>
					<li class="nav-item active"><a class="nav-link" href="/admin/logout">Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="jumbotron text-center bg-white border border-info">
		<h1 class="display-4">Welcome Back, Admin 👋</h1>
		<hr>
		<p>Manage the entire system using this admin panel</p>
	</div>

	<div class="container-fluid">
		<div class="row justify-content-center">

			<div class="col-sm-3 pt-4">
				<div class="card border border-info">
					<div class="card-body text-center">
						<h4 class="card-title">Categories</h4>
						<p class="card-text">Manage all product categories.</p>
						<a href="/admin/categories" class="btn btn-primary">Manage</a>
					</div>
				</div>
			</div>

			<div class="col-sm-3 pt-4">
				<div class="card border border-info">
					<div class="card-body text-center">
						<h4 class="card-title">Products</h4>
						<p class="card-text">Manage all the products.</p>
						<a href="/admin/products" class="btn btn-primary">Manage</a>
					</div>
				</div>
			</div>

			<div class="col-sm-3 pt-4">
				<div class="card border border-info">
					<div class="card-body text-center">
						<h4 class="card-title">Customers</h4>
						<p class="card-text">Manage all the registered users.</p>
						<a href="/admin/customers" class="btn btn-primary">Manage</a>
					</div>
				</div>
			</div>

		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</body>
</html>
