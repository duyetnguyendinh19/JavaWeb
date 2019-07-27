
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
						<div class="col-xs-14 col-sm-12 col-md-14 col-lg-0">
							<div class="panel panel-warning">
								<div class="panel-heading">
									<h2 class="panel-title">Phòng Ban</h2>
								</div>

								<a
									style="float: right; margin-right: 15px; margin-top: 10px; margin-bottom: 10px"
									href="${pageContext.request.contextPath}/admin/addDepartment">
									<button class="btn btn-primary">Thêm Phòng Ban</button>
								</a>

								<div class="panel-body">

									<table class="table table-bordered table-hover">
										<thead>
											<tr>
												<th>Mã Phòng Ban</th>
												<th>Tên Phòng Ban</th>
												<th></th>

											</tr>
										</thead>
										<tbody>
											<c:forEach var="depart" items="${listDepart}">
												<tr>

													<th>${depart.id}</th>
													<td>${depart.name}</td>

													<td width="120px"><a
														href="${pageContext.request.contextPath}/admin/editDepartment/${depart.id}"><button
																class="btn btn-warning">
																<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
															</button></a>
														<button name="deleteDepart" class="btn btn-danger"
															data-toggle="modal"
															data-target="#deleteDepartment_${depart.id}">
															<i class="fa fa-trash" aria-hidden="true"></i>
														</button></td>
												</tr>
												<!-- Modal -->
												<div class="modal fade" id="deleteDepartment_${depart.id}"
													tabindex="-1" role="dialog"
													aria-labelledby="exampleModalLabel" aria-hidden="true">
													<div class="modal-dialog" role="document">
														<div class="modal-content">
															<div class="modal-header">
																<h5 class="modal-title" title="Xóa phòng ban"
																	id="exampleModalLabel">Xóa phòng ban</h5>
															</div>
															<div class="modal-body">
																Mã phòng ban: ${depart.id} <br /> Tên phòng ban:
																${depart.name}
															</div>
															<div class="modal-footer">
																<a
																	href="${pageContext.request.contextPath}/admin/deleteDepart?id=${depart.id}"
																	title="Xóa phòng ban"><button type="button"
																		class="btn btn-danger">Đồng ý</button> </a>
																<button type="button" class="btn btn-secondary"
																	data-dismiss="modal">Hủy bỏ</button>
															</div>
														</div>
													</div>
												</div>
											</c:forEach>

										</tbody>
									</table>
								</div>

								<nav id="client-paginator" aria-label="Page navigation example">
									<ul class="pagination justify-content-center">
										<li class="page-item disabled"><a class="page-link"
											href="#" tabindex="-1">Previous</a></li>
										<c:forEach var="page" begin="1" end="${totalPage}">
											<li class="page-item"><a class="page-link"
												href="${pageContext.request.contextPath}/admin/listDepartment/${page}">${page}</a></li>
										</c:forEach>
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

