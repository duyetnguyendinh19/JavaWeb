<%@page import="shop.newshop.DaoImpl.CategoryDAOImpl"%>
<%@page import="shop.newshop.Service.CategoryService"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
	function deleteDialog() {
		var result = confirm("Do you want to delete this user?");
		if (result) {
			alert("Delete success");
		}
	}
</script>

<link href="templates/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

	<div class="container">
		<div class="row">

			<div class="col-lg-9">

				<div id="carouselExampleIndicators" class="carousel slide my-4"
					data-ride="carousel">
					<ol class="carousel-indicators">
						<li data-target="#carouselExampleIndicators" data-slide-to="0"
							class="active"></li>
						<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
						<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
					</ol>
					<div class="container">
						<div id="carouselExampleControls" class="carousel slide"
							data-ride="carousel">

							<a class="carousel-control-prev" href="#carouselExampleControls"
								role="button" data-slide="prev"> <span
								class="carousel-control-prev-icon" aria-hidden="true"></span> <span
								class="sr-only">Previous</span>
							</a> <a class="carousel-control-next" href="#carouselExampleControls"
								role="button" data-slide="next"> <span
								class="carousel-control-next-icon" aria-hidden="true"></span> <span
								class="sr-only">Next</span>
							</a>
						</div>
						<div class="row">
							<div class="col-xs-14 col-sm-12 col-md-11 col-lg-0">
								<div class="panel panel-danger">
									<div class="panel-heading">
										<h2 class="panel-title">User Management</h2>
									</div>
									<a
										href="${pageContext.request.contextPath}/admin/addUser/${item.id_pr}">
										<button class="btn btn-primary">Add User</button>
									</a>
									<li class="nav-item" style="list-style: none;"><app-addproduct
											[dataProduct]="products"></app-addproduct></li>
									<div class="panel-body">
										<c:forEach var="i" begin="0" end="${totalUser}">
											<a class="btn btn-primary"
												href="${pageContext.request.contextPath}/admin/listUser/${i+1}"><c:out
													value="${i+1}" /></a>
										</c:forEach>
										<table class="table table-bordered table-hover">
											<thead>
												<tr>
													<th>Id</th>
													<th>Username</th>
													<th>FullName</th>
													<th>Status</th>
													<th>Action</th>

												</tr>
											</thead>
											<tbody>
												<c:forEach var="item" items="${listUser}">
													<tr>

														<th>${item.id_user}</th>
														<td>${item.username}</td>
														<td>${item.fullname}</td>
														<td>${item.status? 'Admin':'Member'}</td>

														<td><a
															href="${pageContext.request.contextPath}/admin/editUser/${item.id_user}"><button
																	class="btn btn-warning">
																	<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
																</button></a> <a
															href="${pageContext.request.contextPath}/admin/removeuser/${item.id_user}">
																<button class="btn btn-danger" onclick="deleteDialog()">
																	<i class="fa fa-trash" aria-hidden="true"></i>
																</button>
														</a></td>
													</tr>
												</c:forEach>

											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>


			</div>


		</div>


	</div>
	<script src="templates/jquery/jquery.min.js"></script>
	<script src="templates/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>