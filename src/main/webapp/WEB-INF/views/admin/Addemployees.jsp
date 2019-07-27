
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="container">
	<br>
	<div class="row">
		<div class="col-xs-14 col-sm-12 col-md-11">
			<div class="panel panel-danger">
				<div class="panel-heading">
					<h2 class="panel-title">Thêm Nhân Viên</h2>
				</div>
				<form:form modelAttribute="employee" action="#" method="POST">

					<div class="col-md-4 mb-3">
						<label for="validationDefault01">Mã nhân viên</label> <input
							type="text" class="form-control" id="validationDefault01"
							placeholder="Mã nhân viên" value="Mã nhân viên" required>
					</div>

					<div class="col-md-5 mb-3">
						<label for="validationDefault02">Tên nhân viên</label> <input
							type="text" class="form-control" id="validationDefault02"
							placeholder="Tên nhân viên" value="Tên nhân viên" required>
					</div>
					<div class="col-md-3 mb-3">
						<label for="validationDefault3">Ngày sinh</label> <input
							type="text" class="form-control" id="validationDefault03"
							placeholder="Ngày sinh" value="Ngày sinh" required>
					</div>


					<div class="col-md-8 mb-3">
						<label for="validationDefault3">Địa chỉ</label> <input type="text"
							class="form-control" id="validationDefault03"
							placeholder="Địa chỉ" value="Địa chỉ" required>
					</div>


					<div class="col-md-1 mb-1">
						<label for="validationDefault3">Chức Vụ</label> <input
							type="checkbox" class="form-control" id="validationDefault03"
							required>
					</div>

					<div class="col-md-3 mb-3">
						<label for="validationDefault3">SĐT</label> <input type="text"
							class="form-control" id="validationDefault03"
							placeholder="Số điện thoại" value="Số điện thoại" required>
					</div>



					<div class="col-md-3 mb-3">
						<label for="validationDefault3">Bộ phận</label> <input type="text"
							class="form-control" id="validationDefault03"
							placeholder="Bộ phận" value="Bộ phận" required>
					</div>

					<form:button type="name" class="btn btn-success">Lưu</form:button>
				</form:form>
				<a href="#"><button class="btn btn-warning">Quay Lại</button></a>
			</div>

		</div>

	</div>
</div>
