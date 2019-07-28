
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
										<h2 class="panel-title">Tài Khoản</h2>
									</div>
									<a
										href="#">
										<button class="btn btn-primary">Thêm Tài Khoản</button>
									</a>
									
									<div class="panel-body">
										<c:forEach var="i" begin="0" end="${totalUser}">
											
										</c:forEach>
										<table class="table table-bordered table-hover">
											<thead>
												<tr>
													<th>Mã Tài Khoản</th>
													<th>Tên Tài Khoản</th>
													<th>Họ Tên</th>
													<th>Chức Vụ</th>
													<th></th>

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
															href="#"><button
																	class="btn btn-warning">
																	<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
																</button></a> <a
															href="#">
																<button class="btn btn-danger" onclick="deleteDialog()">
																	<i class="fa fa-trash" aria-hidden="true"></i>
																</button>
														</a></td>
													</tr>
												</c:forEach>

											</tbody>
										</table>
										<div style="margin-left: 400px;">
										<a>
											<button><<</button>
											<button><</button> <input type="text" style="width: 30px">
											of <input type="text" style="width: 30px" readonly="true">
											<button>></button>
											<button>>></button>
										</a> <a style="margin-left: 400px"> <label>View</label> <label>1</label>
											<label>-</label>
											<label>5</label>
											<label>of</label>
											<label>12</label>

										</a>
									</div>
									<!--  -->

								</div>
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