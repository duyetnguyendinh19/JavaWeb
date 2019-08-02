<%--<%@page import="shop.newshop.DaoImpl.CategoryDAOImpl"%>--%>
<%--<%@page import="shop.newshop.Service.CategoryService"%>--%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Quản lý hợp đồng</title>
</head>
<style>
tr th {
	text-align: center;
}

.ui.modal {
	margin-top: 50px;
	width: 28%;
	height: 30%;
	margin-left: auto;
	margin-right: auto;
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

.tennv {
	height: 35px;
}

.page button {
	height: 30px;
	color: white;
	background: #4e4ee6;
	border: none;
	font-size: 11px;
	border-radius: 3px;
	width: 30px;
}

.page button:hover {
	background: #a7a7ef;
}

.ui.black.deny.button {
	width: 25%;
	height: 40px;
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
<%--<script src="templates/js/jquery.min.js"></script>--%>
<c:if test="${not empty searchFail}">
	<label class="alert alert-danger" id="name_errors"
		style="margin-left: 25px; width: 91.3%; color: red; font-size: 18px;">${searchFail}</label>
</c:if>
<div class="container">
	<div class="row">
		<div class="col-lg-9">
			<div class="container">
				<div class="row">
					<div class="col-xs-14 col-sm-12 col-md-11">
						<div class="panel panel-warning">
							<div class="panel-heading">
								<h2 class="panel-title">Quản lý hợp đồng</h2>
							</div>
							<form
								action="${pageContext.request.contextPath}/admin/listContract"
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
							<a href="${pageContext.request.contextPath}/admin/addContract">
								<button class="btn btn-success"
									style="font-family: Lato, Helvetica Neue, Arial, Helvetica, sans-serif; float: right; margin: 10px 15px;">
									Thêm hợp đồng</button>
							</a>
							<div class="panel-body">
								<table class="table table-bordered table-hover">
									<thead>
										<tr
											style="text-align: center; border-bottom: 2px solid #dddddd;">
											<th>Mã Nhân Viên</th>
											<th>Tên Nhân Viên</th>
											<th>Ngày bắt đầu</th>
											<th>Ngày hết hạn</th>
											<th>Lương</th>
											<th class="fas fa-cogs" style="margin-top: 12px;"></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${listContract}" var="contract"
											varStatus="loop">
											<tr style="text-align: center;">
												<td>${contract.employee.id}</td>
												<td>${contract.employee.name}</td>
												<td><fmt:formatDate pattern="dd-MM-yyyy"
														value="${contract.startday}" /></td>
												<td><fmt:formatDate pattern="dd-MM-yyyy"
														value="${contract.expirationday}" /></td>
												<td><fmt:formatNumber type="number"
														maxIntegerDigits="10" value="${contract.salary}" /></td>
												<td><a
													href="${pageContext.request.contextPath}/admin/updateContract/${contract.id}">
														<button class="btn btn-warning" data-toggle="tooltip"
															title="Sửa hợp đồng">
															<i class="fas fa-user-edit" aria-hidden="true"></i>
														</button>
												</a>
													<button class="btn btn-danger" id="delete"
														onclick="loadModal('${contract.employee.name}', '${contract.id}')"
														data-toggle="tooltip" title="Xóa hợp đồng">
														<i class="fa fa-trash" aria-hidden="true"></i>
													</button></td>
											</tr>
										</c:forEach>
										<c:if test="${not empty searchFail}">
											<tr>
												<td colspan="7">Danh sách rỗng</td>
											</tr>
										</c:if>
									</tbody>
								</table>

								<div class="row">
									<div class="col-6 col-sm-6 col-xs-8 col-md-6 col-lg-9 page">

										<button onclick="firstpage()"><<</button>
										<button onclick="previous()"><</button>

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
										<a style="float: right;"> <label>View</label> <label>${firstContract}</label>
											<label>-</label> <label>${lastContract}</label> <label>of</label>
											<label>${totalContract}</label>
										</a>
									</div>
								</div>

								<div class="ui modal">
									<i class="close icon"></i>
									<div class="header">Xóa hợp đồng</div>
									<div class="content">
										<h4>
											Bạn có muốn xóa <span id="name"></span> không?
										</h4>
									</div>
									<div class="actions">
										<a id="deleteComfirm"
											data-hrefbefore="${pageContext.request.contextPath}/admin/deleteContract/">
											<button class="ui black deny button"
												style="background: green;">Ok</button>
										</a>
										<button class="ui black deny button" onclick="deleteTooltip()">
											Hủy bỏ</button>
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
<script>
	$("#name_errors").hide().fadeIn(0).delay(2000).fadeOut(500);
</script>
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
<script>
	function deleteTooltip() {
		$('.ui-tooltip.ui-widget.ui-corner-all.ui-widget-content').remove();
	}
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$('[data-toggle="tooltip"]').tooltip();
	});
</script>
<script type="text/javascript">
	var url = window.location.href.split('/')[6];
	var numbertotal = Number($('#totalPage').val());
	$("#page")
			.keypress(
					function(event) {
						var number = Number($('#page').val());
						if (event.keyCode === 13) {
							if (number < 1) {
								window.location
										.assign("http://localhost:9999/Manager/admin/listContract/" + 1);
							} else if (number > numbertotal) {
								window.location
										.assign("http://localhost:9999/Manager/admin/listContract/"
												+ numbertotal);
							} else {
								window.location
										.assign("http://localhost:9999/Manager/admin/listContract/"
												+ number);
							}
						}
					});

	function firstpage() {
		window.location
				.assign("http://localhost:9999/Manager/admin/listContract/" + 1);
	}

	function previous() {
		var number = Number($('#page').val());
		if (number > 1) {
			var newNumber = number - 1;
			window.location
					.assign("http://localhost:9999/Manager/admin/listContract/"
							+ newNumber);
		} else {
			var newNumber = number - 1;
			window.location
					.assign("http://localhost:9999/Manager/admin/listContract/" + 1);
		}

	}

	function next() {
		var number = Number($('#page').val());
		if (number < numbertotal) {
			var newNumber = number + 1;
			window.location
					.assign("http://localhost:9999/Manager/admin/listContract/"
							+ newNumber);
		} else {
			window.location
					.assign("http://localhost:9999/Manager/admin/listContract/"
							+ numbertotal);
		}

	}

	function lastpage() {
		window.location
				.assign("http://localhost:9999/Manager/admin/listContract/"
						+ numbertotal);
	}

	if (typeof url !== 'undefined') {
		$('#page').val(url);
	} else {
		$('#page').val(1);
	}
</script>
</body>
</html>