<%@page import="shop.newshop.DaoImpl.CategoryDAOImpl"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="shop.newshop.Service.CategoryService"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function deleteDialog() {
		var result = confirm("Do you want to delete this category?");
		if (result) {
			alert("Delete success");
		} else {
			alert("Bye");
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
										<h2 class="panel-title">Khen Thuong</h2>
									</div>
									<a
										href="${pageContext.request.contextPath}/admin/themkhenthuong">
										<button class="btn btn-primary">Them Khen Thuong</button>
									</a>
									<li class="nav-item" style="list-style: none;"><app-addproduct
											[dataProduct]="products"></app-addproduct></li>
									<div class="panel-body">

										<table class="table table-bordered table-hover">
											<thead>
												<tr>
													<tr>
													<!-- <th>Ma PB</th> -->
													<th>Ma KT</th>
													<th>Ma NV</th>
													<th>Mo ta</th>
													<th>Ly Do Kt</th>
													<!-- <th>Loai Kt</th> -->
													<th>Hinh Thuc Khen Thuong</th>
													<th>Ngay KT</th>
													
													<th>Action</th>
												</tr>

									
											</thead>
											
											<tbody>
												
													<tr>
														
														<td></td>
														<th></th>
														<td></td>
														<td></td>
														<th></th>
														<th></th>
														<td><a
															href="${pageContext.request.contextPath}/admin/suakhenthuong"><button
																	class="btn btn-warning">
																	<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
																</button></a> <a
															href="${pageContext.request.contextPath}/admin/remove/${item.id_dm}">
																<button class="btn btn-danger" onclick="deleteDialog()">
																	<i class="fa fa-trash" aria-hidden="true"></i>
																</button>
														</a></td>
														
														
													</tr>
				

											</tbody>
										</table>
									</div>
									<nav aria-label="Page navigation example">
									<ul class="pagination justify-content-center">
										<li class="page-item disabled"><a class="page-link"
											href="#" tabindex="-1">Previous</a></li>
										<li class="page-item"><a class="page-link" href="#"  >1</a></li>
										<li class="page-item"><a class="page-link" href="#">2</a></li>
										<li class="page-item"><a class="page-link" href="#">3</a></li>
										<li class="page-item"><a class="page-link" href="#">Next</a>
										</li>
									</ul>
									</nav>
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