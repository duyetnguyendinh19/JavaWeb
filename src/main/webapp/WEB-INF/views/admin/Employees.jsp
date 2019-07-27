
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



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
						<div class="col-xs-11 col-sm-11 col-md-11">
							<div class="panel panel-danger">
								<div class="panel-heading">
									<h2 class="panel-title">Nhân Viên</h2>
								</div>

								<a href="${pageContext.request.contextPath}/admin/addEmployee">
									<button class="btn btn-primary">Thêm Nhân Viên</button>
								</a>

								<div class="panel-body">

									<table class="table table-bordered table-hover">
										<thead>
											<tr>
												<th>Mã Nhân Viên</th>
												<th>Tên Nhân Viên</th>	
												<th>Chức Vụ</th>
												<th>Bộ Phận</th>
												<th>Chức năng</th>

											</tr>
										</thead>
										<tbody>
											<c:forEach var="item" items="#">
												<tr>
													<th></th>
													<th></th>
													<th></th>
													<th></th>

													<td><a href="#"><button class="btn btn-warning">
																<i class="fa fa-pencil-square-o" aria-hidden="true"
																	title="Sửa" data-toggle="tooltip"></i>
															</button></a>
														<button class="btn btn-danger" >
															<i class="fa fa-trash" aria-hidden="true" title="Xóa"></i>

														</button></td>
												</tr>
											</c:forEach>

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
