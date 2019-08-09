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
<title>Quản lý công</title>
</head>
<style>
tr th {
	text-align: center;
}

.ui.modal {
	margin-top: 50px;
	/*width: 28%;*/
	height: 80%;
	margin-left: auto;
	margin-right: auto;
}

form button {
	height: 36px;
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
	height: 36px;
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

.ui.dropdown {
	margin-left: 15px;
	margin-right: 15px;
}

.ui.black.deny.button {
	width: 12%;
	height: 40px;
}

#totalAtten ::-webkit-scrollbar {
	width: 20px;
}

#totalAtten ::-webkit-scrollbar-track {
	box-shadow: inset 0 0 5px grey;
	border-radius: 10px;
}

#totalAtten ::-webkit-scrollbar-thumb {
	background: #e8f2e3;
	border-radius: 10px;
}

#totalAtten ::-webkit-scrollbar-thumb:hover {
	background: #b30000;
}

.chotcong {
	float: right;
	font-size: 16px;
	border: none;
	background: none;
	margin-top: 2px;
	font-weight: bold;
}

@media ( min-width : 1200px) {
	.col-lg-3 {
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
					<div class="col-xs-14 col-sm-12 col-md-11">
						<div class="panel panel-warning">
							<div class="panel-heading"
								style="height: 45px; line-height: 25px;">
								<h2 class="panel-title" style="float: left;">Quản lý công</h2>
								<button onclick="loadModal()" class="chotcong">Chốt
									công</button>
							</div>
							<form
								action="${pageContext.request.contextPath}/admin/listAttendance"
								method="POST" style="float: left; padding: 16px; width: 81.8%;">
								<div class="row">
									<div class="col-12 col-sm-12 col-xs-12 col-md-12 col-lg-5">
										<label class="col-12 col-sm-2 col-xs-12  col-md-4 col-lg-4"
											style="margin-top: 8px; padding-left: 0px !important">Tên
											nhân viên:</label> <input type="text" name="name"
											class="col-12 col-sm-10 col-xs-12  col-md-7 col-lg-8 tennv"
											placeholder="Tên nhân viên..."
											style="border-radius: 5px !important;" value="${name}" />
									</div>
									<div class="col-12 col-sm-12 col-xs-12 col-md-12 col-lg-7">
										<label class="col-12 col-sm-2 col-xs-12  col-md-4 col-lg-2"
											style="margin-top: 8px; float: left; padding-left: 0px">Ngày:</label>
										<div class="ui calendar" id="rangestart">
											<input class="col-12 col-sm-2 col-xs-12  col-md-4 col-lg-6"
												type="text" class="datepicker"
												style="border-radius: 5px !important; height: 35px !important;"
												id="startDate" name="date" placeholder="Ngày" value=""/>
										</div>
										<button class="col-12 col-sm-2 col-xs-4  col-md-4 col-lg-3"
											type="submit" style="font-family: Tahoma; float: left;">
											Tìm kiếm</button>
									</div>
								</div>
							</form>
							<a href="${pageContext.request.contextPath}/admin/addAttendance">
								<button
									class="btn btn-success col-12 col-sm-12 col-xs-12 col-md-4 col-lg-2"
									style="font-family: Lato, Helvetica Neue, Arial, Helvetica, sans-serif; float: right; margin-top: 12px; margin-right: 15px;">
									Thêm chấm công</button>
							</a>
							<div class="panel-body">
								<table class="table table-bordered table-hover">
									<thead>
										<tr
											style="text-align: center; border-bottom: 2px solid #dddddd;">
											<th>Mã nhân viên</th>
											<th>Tên nhân viên</th>
											<th>Ngày</th>
											<th>Thời gian bắt đầu</th>
											<th>Thời gian kết thúc</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${listAttendance}" var="listAttendance">
											<tr style="text-align: center;">
												<td>${listAttendance.employee.id}</td>
												<td>${listAttendance.employee.name}</td>
												<td><fmt:formatDate pattern="dd-MM-yyyy"
														value="${listAttendance.date}" /></td>
												<td>${listAttendance.starttime}</td>
												<td>${listAttendance.endtime}</td>
											</tr>
											<input type="text" name="idEmployee" hidden
												value="${listAttendance.employee.id}" />
										</c:forEach>
										<c:if test="${not empty searchFail}">
											<tr>
												<td colspan="5">Danh sách rỗng</td>
											</tr>
										</c:if>
									</tbody>
								</table>

								<div class="row">
									<div class="col-6 col-sm-6 col-xs-8 col-md-6 col-lg-8 page">

										<button onclick="firstpage()"><<</button>
										<button onclick="previous()"><</button>

										<input type="number" id="page" value="1"
											style="width: 40px; border-radius: 3px !important; margin-left: 3px;">

										of <input type="text" id="totalPage" value="${totalPage}"
											style="width: 30px; border-radius: 3px !important; margin-left: 2px; margin-right: 3px;"
											readonly="readonly">
										<button onclick="next()">></button>
										<button onclick="lastpage()">>></button>

									</div>
									<div class="col-6 col-sm-6 col-xs-4 col-md-6 col-lg-4">
										<a style="float: right;"> <label>View</label> <label>${firstAtten}</label>
											<label>-</label> <label>${lastAtten}</label> <label>of</label>
											<label>${totalAtten}</label>
										</a>
									</div>
								</div>

								<div class="ui modal">
									<i class="close icon"></i>
									<div class="header">Chốt công</div>
									<div class="content">
										<table class="table table-bordered table-hover"
											id="totalAtten">
											<thead>
												<tr
													style="text-align: center; border-bottom: 2px solid #dddddd;">
													<th>Mã nhân viên</th>
													<th>Tên nhân viên</th>
													<th>Lương</th>
													<th>Tháng</th>
													<th>Số công</th>
													<th>Ngày nghỉ</th>
													<th>Tổng lương</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${listTotal}" var="listTotal"
													varStatus="loop">
													<tr style="text-align: center;">
														<td>${listTotal.idEmployee}</td>
														<td>${listTotal.nameEmployee}</td>
														<td><fmt:formatNumber type="number"
																maxIntegerDigits="10" value="${listTotal.salary}" /></td>
														<td>${listTotal.month}</td>
														<td>${listTotal.count}</td>
														<td>${listTotal.day_off}</td>
														<td><fmt:formatNumber type="number"
																maxIntegerDigits="10" value="${listTotal.totalSalary}" /></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
									<div class="actions" style="display: list-item; height: 70px;">
										<form
											action="${pageContext.request.contextPath}/admin/addSalary"
											method="POST" style="float: right;">
											<button class="ui black deny button"
												style="background: green; width: 100% !important;">
												Tính lương</button>
										</form>
										<button class="ui black deny button" style="float: right;">
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
	function loadModal() {
		$('.ui.modal1').modal('show');
	}
</script>

<script>
	var toDate = new Date();
	var date = toDate.getDate();
	var endDate = date;
	var month = toDate.getMonth() + 1;
	var endMonth = month + 1;
	var year = toDate.getFullYear();
	$('#startDate').val(month + "/" + date + "/" + year );

	$('#rangestart').calendar({
				type : 'date',
				text : {
					days : [ 'CN', 'T2', 'T3', 'T4', 'T5', 'T6', 'T7' ],
					months : [ 'Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4',
							'Tháng 5', 'Tháng 6', 'Tháng 7', 'Tháng 8',
							'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12' ],
				},
				formatter : {
					date : function(date, setting) {
						if (!date)
							return '';
						var day = date.getDate();
						var month = date.getMonth() + 1;
						var year = date.getFullYear();
						return (day < 10 ? '0' + day : day) + '/'
								+ (month < 10 ? '0' + month : month) + '/'
								+ year;
					}
				},
				today : true,
			});
</script>
<script>
	$("#name_errors").hide().fadeIn(0).delay(2000).fadeOut(500);
</script>
<script>
	function loadModal(name, id) {
		$('.ui.modal').modal('show');
		$('#name').text(name);
		var accept = $('#accept');
		document.getElementById('accept').href = accept.attr('data-hrefbefore');
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
										.assign("http://localhost:9999/Manager/admin/listAttendance/" + 1);
							} else if (number > numbertotal) {
								window.location
										.assign("http://localhost:9999/Manager/admin/listAttendance/"
												+ numbertotal);
							} else {
								window.location
										.assign("http://localhost:9999/Manager/admin/listAttendance/"
												+ number);
							}
						}
					});

	function firstpage() {
		window.location
				.assign("http://localhost:9999/Manager/admin/listAttendance/" + 1);
	}

	function previous() {
		var number = Number($('#page').val());
		if (number > 1) {
			var newNumber = number - 1;
			window.location
					.assign("http://localhost:9999/Manager/admin/listAttendance/"
							+ newNumber);
		} else {
			var newNumber = number - 1;
			window.location
					.assign("http://localhost:9999/Manager/admin/listAttendance/" + 1);
		}

	}

	function next() {
		var number = Number($('#page').val());
		if (number < numbertotal) {
			var newNumber = number + 1;
			window.location
					.assign("http://localhost:9999/Manager/admin/listAttendance/"
							+ newNumber);
		} else {
			window.location
					.assign("http://localhost:9999/Manager/admin/listAttendance/"
							+ numbertotal);
		}

	}

	function lastpage() {
		window.location
				.assign("http://localhost:9999/Manager/admin/listAttendance/"
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