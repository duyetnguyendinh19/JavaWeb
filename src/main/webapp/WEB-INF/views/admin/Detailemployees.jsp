
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<style>
.ui.dropdown {
	width: 100%;
}

.panel.panel-warning {
	height: auto;
	width: 100%;
	float: left;
}

input[type=text] {
	height: 40px;
	border-radius: 5px !important;
	padding: 10px;
}
</style>
<div class="container">

	<br>
	<div class="row">
		<div class="col-xs-14 col-sm-12 col-md-11">
			<div class="panel panel-warning">
				<div class="panel-heading">
					<h2 class="panel-title">Thông tin Nhân Viên</h2>
				</div>
				<br /> <span style="color: red; margin-left: 10px">${error}</span>
				<br />
				<form>

					<br />
					<div class="row">
						<div class="col-md-3 mb-3">
							<div class="wrap-custom-file"
								style="margin-top: 15px; margin-left: 20px; margin-bottom: 20px">
								<label for="avatar1" id="avatar1-background"
									style="width: 190px; height: 160px;background-image: url(' ${urlimage}')">

								</label>
							</div>
						</div>
						<div class="col-md-5 mb-3" style="margin-top: 5px">
							<label for="validationDefault02">Tên nhân viên <font
								color="red">*</font>
							</label> <input type="text" class="form-control" value="${employee.name}"
								placeholder="Tên nhân viên" readonly="readonly" />
						</div>
						<div class="col-md-4 mb-3"
							style="margin-bottom: 30px; margin-top: 5px;">
							<div class="ui calendar" id="rangestart">
								<label for="validationDefault3">Ngày sinh <font
									color="red">*</font></label> <input
									class="form-control
									type="
									text" value="${employee.birthday}" placeholder="Ngày sinh"
									readonly="readonly" />
							</div>
						</div>
						<div class="col-md-5 mb-3">
							<label for="validationDefault3">Chức vụ</label> <br /> <input
								class="form-control type=" text" value="${employee.level}"
								readonly="readonly">
						</div>
						<div class="col-md-4 mb-3">
							<label for="validationDefault3">Phòng ban</label> <br /> <input
								class="form-control type="
								text" value="${employee.department.name}" readonly="readonly">
						</div>
					</div>
					<div class="row"
						style="float: left; width: 100%; margin: 0px !important; margin-left: 22px !important; padding-right: 25px;">
						<div class="col-md-4 mb-3" style="margin-bottom: 30px">
							<label for="validationDefault3">Số điện thoại <font
								color="red">*</font></label> <input type="text" class="form-control"
								value="${employee.phone}" placeholder="Số điện thoại"
								readonly="readonly" />
						</div>

						<div class="col-md-4 mb-3" style="margin-bottom: 30px">
							<label for="validationDefault3">Email <font color="red">*</font></label>
							<input type="text" class="form-control" value="${employee.email}"
								placeholder="Email" readonly="readonly" />
						</div>

						<div class="col-md-4 mb-3">
							<label for="validationDefault3">Chứng minh nhân dân <font
								color="red">*</font></label> <input type="text" class="form-control"
								placeholder="Chứng minh nhân dân"
								value="${employee.identitycard}" readonly="readonly" />
						</div>
					</div>
					<div class="row"
						style="float: left; width: 85%; margin: 0px !important; margin-left: 22px !important; padding-bottom: 25px;">
						<div class="col-md-5 mb-3">
							<label for="validationDefault3">Địa chỉ <font color="red">*</font></label>
							<input type="text" class="form-control" placeholder="Địa chỉ"
								value="${employee.address}" readonly="readonly" />
						</div>

						<div class="col-md-5 mb-3">
							<label for="validationDefault3">Tài khoản <font color="red">*</font></label>
							<input type="text" class="form-control" 
								value="${employee.account.username}" readonly="readonly" />
						</div>

					</div>
				</form>
				<a href="${pageContext.request.contextPath}/admin/listEmployee">
					<button
						style="margin-bottom: 10px; float: left; border-radius: 5px; margin-top: 20px;"
						class="btn btn-warning">Hủy bỏ</button>
				</a>
			</div>


		</div>

	</div>

</div>

