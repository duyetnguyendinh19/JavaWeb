<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee</title>
</head>
<style>
.panel {
	height: auto;
	width: 100%;
	float: left;
}

input[type=text] {
	height: 40px;
	border-radius: 5px !important;
	padding: 10px;
}

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
<body>
	<div id="navbar" class="navbar navbar-default">


		<div class="navbar-container" id="navbar-container">
			<button type="button" class="navbar-toggle menu-toggler pull-left"
				id="menu-toggler" data-target="#sidebar">
				<span class="sr-only">Toggle sidebar</span> <span class="icon-bar"></span>

				<span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>

			<div class="navbar-header pull-left">
				<a href="${pageContext.request.contextPath}/employee/inforEmployee"
					class="navbar-brand"> <small><img alt="Admin"
						src="${pageContext.request.contextPath}/images/boss.png"
						width="20px" height="20px"> <font style="margin-left: 10px"
						size="4px">Giao diện nhân viên</font> </small>
				</a>
			</div>

			<div class="navbar-buttons navbar-header pull-right"
				role="navigation">
				<ul class="nav ace-nav">

					<li class="light-blue"><a
						href="${pageContext.request.contextPath}/employee/changePassword">Đổi mật khẩu</a></li>

					<li class="light-blue"><a data-toggle="dropdown"
						class="dropdown-toggle"> <img class="nav-user-photo"
							src="${avatar}" /> <span class="user-info"> <small>Chào,</small>
								${account.username}
						</span>
					</a></li>

					<li class="light-red"><a
						href="${pageContext.request.contextPath}/logout">Đăng xuất</a></li>
				</ul>
			</div>
		</div>
		<!-- /.navbar-container -->
	</div>

	<div class="main-container" id="main-container">
		<br />
		<div class="row">
			<div class="col-xs-14 col-sm-12 col-md-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h2 class="panel-title">Thông tin</h2>
					</div>

					<br />
					<form>

						<br />
						<div class="row">
							<div class="col-md-4 mb-3">
								<div class="wrap-custom-file"
									style="margin-top: 15px; margin-left: 120px !important; margin-bottom: 20px">
									<label for="avatar1" id="avatar1-background"
										style="width: 300px; height: 300px;background-image: url(' ${urlimage}')">

									</label>
								</div>
							</div>
							<div class="col-md-3 mb-3" style="margin-top: 5px">
								<label for="validationDefault02">Tên nhân viên <font
									color="red">*</font>
								</label> <input type="text" class="form-control"
									value="${employee.name}" placeholder="Tên nhân viên"
									readonly="readonly" />
							</div>
							<div class="col-md-3 mb-3"
								style="margin-bottom: 20px; margin-top: 5px;">
								<div class="ui calendar" id="rangestart">
									<label for="validationDefault3">Ngày sinh <font
										color="red">*</font></label> <input class="form-control" type="text"
										value="${birthday}	" placeholder="Ngày sinh"
										readonly="readonly" />

								</div>
							</div>
							<div class="col-md-3 mb-3">
								<label for="validationDefault3">Chức vụ</label> <br /> <input
									class="form-control" type="text" value="${employee.level}"
									readonly="readonly">
							</div>
							<div class="col-md-3 mb-3">
								<label for="validationDefault3">Phòng ban</label> <br /> <input
									class="form-control" type="text" style="margin-bottom: 20px;"
									value="${employee.department.name}" readonly="readonly">
							</div>

							<div class="col-md-3 mb-3" style="margin-bottom: 30px">
								<label for="validationDefault3">Số điện thoại <font
									color="red">*</font></label> <input type="text" class="form-control"
									value="${employee.phone}" placeholder="Số điện thoại"
									readonly="readonly" />
							</div>

							<div class="col-md-3 mb-3" style="margin-bottom: 30px">
								<label for="validationDefault3">Email <font color="red">*</font></label>
								<input type="text" class="form-control"
									value="${employee.email}" placeholder="Email"
									readonly="readonly" />
							</div>
						</div>

						<div class="row"
							style="float: left; width: 100%; margin: 0px !important; margin-left: 510px !important; padding-bottom: 25px;">
							<div class="col-md-3 mb-3">
								<label for="validationDefault3">Chứng minh nhân dân <font
									color="red">*</font></label> <input type="text" class="form-control"
									placeholder="Chứng minh nhân dân"
									value="${employee.identitycard}" readonly="readonly" />
							</div>




							<div class="col-md-3 mb-3">
								<label for="validationDefault3">Tài khoản <font
									color="red">*</font></label> <input type="text" class="form-control"
									value="${employee.account.username}" readonly="readonly" />
							</div>

						</div>

						<div class="row"
							style="float: left; width: 100%; margin: 0px !important; margin-left: 510px !important; padding-bottom: 25px;">
							<div class="col-md-6 mb-6">
								<label for="validationDefault3">Địa chỉ <font
									color="red">*</font></label> <input type="text" class="form-control"
									placeholder="Địa chỉ" value="${employee.address}"
									readonly="readonly" />
							</div>
						</div>

					</form>

				</div>


			</div>

		</div>

		<div class="row">
			<div class="col-xs-14 col-sm-12 col-md-12">
				<div class="panel panel-warning">
					<div class="panel-heading">
						<h2 class="panel-title">Hợp đồng</h2>
					</div>

					<div class="panel-body">
						<table class="table table-bordered table-hover">
							<thead>
								<tr
									style="text-align: center; border-bottom: 2px solid #dddddd;">
									<th>Mã hợp đồng</th>
									<th>Ngày bắt đầu</th>
									<th>Ngày hết hạn</th>
									<th>Lương</th>

								</tr>
							</thead>
							<tbody>
								<c:forEach items="${listContract}" var="contract"
									varStatus="loop">
									<tr style="text-align: center;">
										<td>${contract.id}</td>
										<td><fmt:formatDate pattern="dd-MM-yyyy"
												value="${contract.startday}" /></td>
										<td><fmt:formatDate pattern="dd-MM-yyyy"
												value="${contract.expirationday}" /></td>
										<td><fmt:formatNumber type="number" maxIntegerDigits="10"
												value="${contract.salary}" /></td>
									</tr>
								</c:forEach>
								<c:if test="${empty listContract}">
									<tr>
										<td colspan="7">Danh sách rỗng</td>
									</tr>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-xs-14 col-sm-12 col-md-12">
				<div class="panel panel-success">
					<div class="panel-heading">
						<h2 class="panel-title">Khen thưởng</h2>
					</div>

					<div class="panel-body">
						<table class="table table-bordered table-hover">
							<thead>
								<tr
									style="text-align: center; border-bottom: 2px solid #dddddd;">
									<th style="width: 7%;">Mã khen thưởng</th>
									<th style="width: 14%;">Loại khen thưởng</th>
									<th style="width: 15%;">Mô tả khen thưởng</th>
									<th style="width: 20%;">Lý do khen thưởng</th>
									<th style="width: 14%;">Ngày khen thưởng</th>

								</tr>
							</thead>
							<tbody>
								<c:forEach items="${bonusList}" var="bonusList" varStatus="loop">
									<tr style="text-align: center;">
										<td>${bonusList.id}</td>
										<td>${bonusList.type}</td>
										<td>${bonusList.descent}</td>
										<td>${bonusList.reason}</td>
										<td><fmt:formatDate pattern="dd-MM-yyyy"
												value="${bonusList.date}" /></td>
									</tr>
								</c:forEach>
								<c:if test="${empty bonusList}">
									<tr>
										<td colspan="7">Danh sách rỗng</td>
									</tr>
								</c:if>
							</tbody>
						</table>

					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-xs-14 col-sm-12 col-md-12">
				<div class="panel panel-danger">
					<div class="panel-heading">
						<h2 class="panel-title">Kỷ luật</h2>
					</div>

					<div class="panel-body">
						<table class="table table-bordered table-hover">
							<thead>
								<tr
									style="text-align: center; border-bottom: 2px solid #dddddd;">
									<th style="width: 7%">Mã kỷ luật</th>
									<th style="width: 14%">Loạt kỷ luật</th>
									<th style="width: 15%">Mô tả</th>
									<th style="width: 20%">Lý do</th>
									<th style="width: 14%">Ngày kỷ luật</th>

								</tr>
							</thead>
							<tbody>
								<c:forEach items="${disciplineList}" var="discipline"
									varStatus="loop">
									<tr style="text-align: center;">
										<td>${discipline.id}</td>
										<td>${discipline.type}</td>
										<td>${discipline.descent}</td>
										<td>${discipline.reason}</td>
										<td><fmt:formatDate pattern="dd-MM-yyyy"
												value="${discipline.date}" /></td>
									</tr>
								</c:forEach>
								<c:if test="${empty disciplineList}">
									<tr>
										<td colspan="7">Danh sách rỗng</td>
									</tr>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-xs-14 col-sm-12 col-md-12">
				<div class="panel panel-warning">
					<div class="panel-heading" style="height: 45px; line-height: 25px;">
						<h2 class="panel-title" style="float: left;">Điểm danh</h2>
					</div>

					<div class="panel-body">
						<table class="table table-bordered table-hover">
							<thead>
								<tr
									style="text-align: center; border-bottom: 2px solid #dddddd;">
									<th>STT</th>
									<th>Ngày</th>
									<th>Thời gian bắt đầu</th>
									<th>Thời gian kết thúc</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${listAttendance}" var="listAttendance"
									varStatus="loop">
									<tr style="text-align: center;">
										<td>${loop.count}</td>
										<td><fmt:formatDate pattern="dd-MM-yyyy"
												value="${listAttendance.date}" /></td>
										<td>${listAttendance.starttime}</td>
										<td>${listAttendance.endtime}</td>
									</tr>
									<input type="text" name="idEmployee" hidden
										value="${listAttendance.employee.id}" />
								</c:forEach>
								<c:if test="${empty listAttendance}">
									<tr>
										<td colspan="5">Danh sách rỗng</td>
									</tr>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-xs-14 col-sm-12 col-md-12">
				<div class="panel panel-warning">
					<div class="panel-heading">
						<h2 class="panel-title">Thống kê lương</h2>
					</div>
					<div class="panel-body">
						<table class="table table-bordered table-hover">
							<thead>
								<tr
									style="text-align: center; border-bottom: 2px solid #dddddd;">
									<th>STT</th>
									<th>Tháng</th>
									<th>Năm</th>
									<th>Số công</th>
									<th>Ngày nghỉ</th>
									<th>Tổng lương</th>
									<th>Ngày tạo</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${listSalary}" var="listSalary"
									varStatus="loop">
									<tr style="text-align: center;">
										<td>${loop.count}</td>
										<td>Tháng ${listSalary.month}</td>
										<td>Năm ${listSalary.year}</td>
										<td>${listSalary.count}</td>
										<td>${listSalary.dayoff}</td>
										<td><fmt:formatNumber type="number" maxIntegerDigits="10"
												value="${listSalary.total}" /></td>
										<td><fmt:formatDate pattern="dd-MM-yyyy"
												value="${listSalary.createDate}" /></td>
									</tr>
								</c:forEach>
								<c:if test="${empty listSalary}">
									<tr>
										<td colspan="8">Danh sách rỗng</td>
									</tr>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>

	</div>
</body>
</html>