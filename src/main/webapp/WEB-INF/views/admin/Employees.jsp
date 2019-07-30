
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<style>
	.page button{
		height: 30px;
		color: white;
		background: #4e4ee6;
		border: none;
		font-size: 11px;
		border-radius: 3px;
		width: 30px;
	}
	.page button:hover{
		background: #a7a7ef;
	}
	.ui.modal{
		margin-top: 50px;
		width: 28%;
		height: 30%;
		margin-left: auto;
		margin-right: auto;
	}
	.ui.black.deny.button{
		width: 25%;
		height: 40px;
	}
</style>
<div class="container">
	<div class="row">

		<div class="col-lg-9">

				<div class="container">

					<div class="row">
						<div class="col-xs-11 col-sm-11 col-md-11">
							<div class="panel panel-warning">
								<div class="panel-heading">
									<h2 class="panel-title">Nhân Viên</h2>
								</div>
								
								<span style="color:red">${error}</span>

								<a href="${pageContext.request.contextPath}/admin/addEmployee"
									style="float: right; margin-top: 10px; margin-right: 15px; margin-bottom: 10px">
									<button class="btn btn-success">Thêm Nhân Viên</button>
								</a>

								<div class="panel-body">

									<table class="table table-bordered table-hover">
										<thead>
											<tr>
												<th>Mã Nhân Viên</th>
												<th>Tên Nhân Viên</th>
												<th>Chức Vụ</th>
												<th>Bộ Phận</th>
												<th width="120px">Chức năng</th>

											</tr>
										</thead>
										<tbody>
											<c:forEach var="item" items="${listEmployee}">
												<tr>
													<th>${item.id}</th>
													<th>${item.name}</th>
													<th>${item.level}</th>
													<th>${item.department.name}</th>


													<td><a
														href="${pageContext.request.contextPath}/admin/editEmployee/${item.id}"><button
																class="btn btn-warning" data-toggle="tooltip"
																title="Sửa nhân viên">
																<i class="fa fa-pencil-square-o" aria-hidden="true"
																	title="Sửa" data-toggle="tooltip"></i>
															</button></a>
														<button class="btn btn-danger" id="delete"
															onclick="loadModal('${item.name}', '${item.id}')"
															data-toggle="tooltip" title="Xóa nhân viên">
															<i class="fa fa-trash" aria-hidden="true" title="Xóa"></i>

														</button></td>

												</tr>
											</c:forEach>

										</tbody>
									</table>

									<div class="ui modal">
										<i class="close icon"></i>
										<div class="header">Xóa nhân viên</div>
										<div class="content">
											<h4>
												Bạn có muốn xóa nhân viên <span id="name"></span> không?
											</h4>
										</div>
										<div class="actions">
											<a id="deleteComfirm"
												data-hrefbefore="${pageContext.request.contextPath}/admin/deleteEmployee/">
												<button class="ui black deny button"
													style="background: green;">Ok</button>
											</a>
											<button class="ui black deny button"
												onclick="deleteTooltip()">Hủy bỏ</button>
										</div>
									</div>
									<div style="margin-left: 400px;" class="page">
										<a>
											<button><<</button>
											<button><</button>
											<input type="text" style="width: 30px;border-radius: 3px!important;margin-left: 3px;">
											of <input type="text" style="width: 30px;border-radius: 3px!important;margin-left:2px;margin-right: 3px;" readonly="true">
											<button>></button>
											<button>>></button>
										</a> <a style=float:right;">
										<label>View</label>
										<label>1</label>
										<label>-</label>
										<label>5</label>
										<label>of</label>
										<label>12</label>
									</a>
									</div>
									<!--  -->

								</div>
								<!-- <nav aria-label="Page navigation example">
										<ul class="pagination justify-content-center">
											<li class="page-item disabled"><a class="page-link"
												href="#" tabindex="-1">Previous</a></li>
											<li class="page-item"><a class="page-link" href="#">1</a></li>
											<li class="page-item"><a class="page-link" href="#">2</a></li>
											<li class="page-item"><a class="page-link" href="#">3</a></li>
											<li class="page-item"><a class="page-link" href="#">Next</a>
											</li>
										</ul>
									</nav> -->
							</div>

						</div>
					</div>
				</div>

			</div>
		</div>


	</div>


</div>
<script>
	function loadModal(name, id) {
		$('.ui.modal').modal('show');
		$('#name').text(name);
		var $deleteConfirm = $('#deleteComfirm');
		document.getElementById('deleteComfirm').href = $deleteConfirm
				.attr('data-hrefbefore')
				+ id;
	}
</script>


