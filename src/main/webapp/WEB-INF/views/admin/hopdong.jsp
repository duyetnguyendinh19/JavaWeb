<%@page import="shop.newshop.DaoImpl.CategoryDAOImpl"%>
<%@page import="shop.newshop.Service.CategoryService"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>Insert title here</title>
<script type="text/javascript">
	function deleteDialog() {
		var result = confirm("Do you want to delete this product?");
		if (result) {
			alert("Delete success");
		} else {
			alert("Bye");
		}
	}
</script>
<link href="templates/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<script>
	window.fbAsyncInit = function() {
		FB.init({
			xfbml : true,
			version : 'v3.3'
		});
	};

	(function(d, s, id) {
		var js, fjs = d.getElementsByTagName(s)[0];
		if (d.getElementById(id))
			return;
		js = d.createElement(s);
		js.id = id;
		js.src = 'https://connect.facebook.net/vi_VN/sdk/xfbml.customerchat.js';
		fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));
</script>
<body>
	<!-- Load Facebook SDK for JavaScript -->
	<div id="fb-root"></div>


	<!-- Your customer chat code -->
	<div class="fb-customerchat" attribution=setup_tool
		page_id="753600888389568" theme_color="#ff7e29"></div>
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
							<div class="col-xs-14 col-sm-12 col-md-11">
								<div class="panel panel-danger">
									<div class="panel-heading">
										<h2 class="panel-title">Hop Dong</h2>
									</div>

									<a
										href="${pageContext.request.contextPath}/admin/themhopdong">
										<button class="btn btn-primary">Them Hop Dong</button>
									</a>
									<li class="nav-item" style="list-style: none;"><app-addproduct
											[dataProduct]="products"></app-addproduct></li>
									<div class="panel-body">

										<table class="table table-bordered table-hover">
											<thead>
												<tr>
													<th>Mã Hợp Đồng</th>
													<th>Ma NV</th>
													<th>Ten NV</th>
													<th>Ngày bắt đầu</th>
													<th>Ngày hết hạn</th>
													<th>Lương</th>
													<th>Action</th>

												</tr>
											</thead>
											<tbody>
													
													<tr>
														<th></th>
														<th></th>
														<th></th>
														<td></td>
														<th></th>
														<th></th>
														<td><a
															href="${pageContext.request.contextPath}/admin/suahopdong"><button
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
										<nav aria-label="Page navigation example">
										<ul class="pagination justify-content-center">
											<li class="page-item disabled"><a class="page-link"
												href="#" tabindex="-1">Previous</a></li>
											<li class="page-item"><a class="page-link" href="#">1</a></li>
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


	</div>

	<script src="templates/jquery/jquery.min.js"></script>
	<script src="templates/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>