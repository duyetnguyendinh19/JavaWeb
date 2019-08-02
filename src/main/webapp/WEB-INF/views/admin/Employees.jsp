
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<style>
tr th {
	text-align: center;
}

.tennv {
	height: 35px;
}

form button {
	height: 35px;
	color: white;
	background: #0ea432;
	border: none;
	border-radius: 4px;
	width: 90px;
}

form button:hover {
	background: #68a458;
}

.page button {
	height: 35px;
	color: white;
	background: #4e4ee6;
	border: none;
	font-size: 11px;
	border-radius: 3px;
	width: 90px;
}

.page button:hover {
	background: #a7a7ef;
}

@media ( min-width : 1200px) {
	.col-lg-2 {
		margin-left: 5px;
	}
}

@media ( max-width : 500px) {
	.col-xs-4, .col-xs-6 {
		margin-top: 8px !important;
	}
	form {
		width: 100% !important;
	}
	.btn.btn-success {
		margin-bottom: 15px;
		width: 92% !important;
	}
	.table.table-bordered.table-hover {
		width: 80%;
	}
}
</style>
<c:if test="${not empty searchFail}">
	<label class="alert alert-danger" id="name_errors"
		style="margin-left: 25px; width: 91.3%; color: red; font-size: 18px;">${searchFail}</label>
</c:if>
<div class="container">
	<div class="row">

		<div class="col-lg-9">

			<div class="container">

				<div class="row">
					<div class="col-xs-11 col-sm-11 col-md-11">
						<div class="panel panel-warning">
							<div class="panel-heading">
								<h2 class="panel-title">Quản lý nhân viên</h2>
							</div>

							<form
								action="${pageContext.request.contextPath}/admin/listEmployee"
								method="POST" style="float: left; padding: 16px; width: 82.8%;">
								<div class="row">
									<div class="col-12 col-sm-12 col-xs-12 col-md-12 col-lg-10">
										<label class="col-12 col-sm-3 col-xs-12  col-md-4 col-lg-2"
											style="margin-top: 8px; padding-left: 0px;">Tên nhân
											viên:</label> <input type="text" name="nameEmployee"
											class="col-12 col-sm-9 col-xs-12  col-md-7 col-lg-5 tennv"
											placeholder="Tên nhân viên..."
											style="border-radius: 5px !important;" value="${nameSearch}" />
										<button class="col-12 col-sm-2 col-xs-4  col-md-4 col-lg-2"
											type="submit" style="font-family: Tahoma">Tìm kiếm</button>
									</div>
								</div>
							</form>

							<span style="color: red">${error}</span> <a
								href="${pageContext.request.contextPath}/admin/addEmployee"
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
											<th width="180px">Chức năng</th>

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
													href="${pageContext.request.contextPath}/admin/inforEmployee/${item.id}"><button
															class="btn btn-success" data-toggle="tooltip"
															title="Thông tin nhân viên">
															<i class="fa fa-list-alt"></i>
														</button></a> <a
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
											Nhân viên <span id="name"></span> có thể còn hợp đồng, khen
											thưởng, kỷ luật bạn có chắc chắn không?
										</h4>
									</div>
									<div class="actions">
										<a id="deleteComfirm"
											data-hrefbefore="${pageContext.request.contextPath}/admin/deleteEmployee/">
											<button class="ui black deny button"
												style="background: green;">Ok</button>
										</a>
										<button class="ui black deny button" onclick="deleteTooltip()">Hủy
											bỏ</button>
									</div>
								</div>
								<div class="row">
									<div class="col-6 col-sm-6 col-xs-8 col-md-6 col-lg-9 page">

										<button onclick="firstpage()"><<</button>
										<button onclick="previous()"><</button>
										<%-- <form onsubmit="pagination()"> --%>
										<input type="number" id="page" value="1"
											style="width: 40px; border-radius: 3px !important; margin-left: 3px;">
										<%-- </form> --%>
										of <input type="text" id="totalPage" value="${totalPage}"
											style="width: 30px; border-radius: 3px !important; margin-left: 2px; margin-right: 3px;"
											readonly="readonly">
										<button onclick="next()">></button>
										<button onclick="lastpage()">>></button>

									</div>
									<div class="col-6 col-sm-6 col-xs-4 col-md-6 col-lg-3">
										<a style="float: right;""> <label>View</label> <label>1</label>
											<label>-</label> <label>5</label> <label>of</label> <label>12</label>
										</a>
									</div>
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



<script>
	function loadModal(name, id) {
		$('.ui.modal').modal('show');
		$('#name').text(name);
		var $deleteConfirm = $('#deleteComfirm');
		document.getElementById('deleteComfirm').href = $deleteConfirm
				.attr('data-hrefbefore')
				+ id;
	}

	var url = window.location.href.split('/')[6];
	var numbertotal = Number($('#totalPage').val());
	$("#page")
			.keypress(
					function(event) {
						var number = Number($('#page').val());
						if (event.keyCode === 13) {
							if (number < 1) {
								window.location
										.assign("http://localhost:9999/Manager/admin/listEmployee/" + 1);
							} else if (number > numbertotal) {

								window.location
										.assign("http://localhost:9999/Manager/admin/listEmployee/"
												+ numbertotal);

							} else {
								window.location
										.assign("http://localhost:9999/Manager/admin/listEmployee/"
												+ number);
							}
						}
					});

	function firstpage() {
		window.location
				.assign("http://localhost:9999/Manager/admin/listEmployee/" + 1);
	}

	function previous() {
		var number = Number($('#page').val());
		if (number > 1) {
			var newNumber = number - 1;
			window.location
					.assign("http://localhost:9999/Manager/admin/listEmployee/"
							+ newNumber);
		} else {
			var newNumber = number - 1;
			window.location
					.assign("http://localhost:9999/Manager/admin/listEmployee/" + 1);
		}

	}

	function next() {
		var number = Number($('#page').val());
		if (number < numbertotal) {
			var newNumber = number + 1;
			window.location
					.assign("http://localhost:9999/Manager/admin/listEmployee/"
							+ newNumber);
		} else {
			window.location
					.assign("http://localhost:9999/Manager/admin/listEmployee/"
							+ numbertotal);
		}

	}

	function lastpage() {
		window.location
				.assign("http://localhost:9999/Manager/admin/listEmployee/"
						+ numbertotal);
	}

	if (typeof url !== 'undefined') {
		$('#page').val(url);
	} else {
		$('#page').val(1);
	}
</script>



