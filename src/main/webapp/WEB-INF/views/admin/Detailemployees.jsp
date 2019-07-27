
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link href="template/css/bootstrap.min.css" rel="stylesheet">
<div class="container">
	<br>
	<div class="row">
		<div class="col-xs-14 col-sm-12 col-md-11">
			<div class="panel panel-danger">
				<div class="panel-heading">
					<h2 class="panel-title">Chi tiết nhân viên</h2>
				</div>
				<form:form modelAttribute="employee" action="#" method="POST">

					<div class="col-md-4 mb-3">
						<label for="validationDefault01">Mã nhân viên</label> <input
							type="text" class="form-control" id="validationDefault01"
							placeholder="Mã nhân viên" value="Mã nhân viên" required
							readonly="true">
					</div>

					<div class="col-md-5 mb-3">
						<label for="validationDefault02">Tên nhân viên</label> <input
							type="text" class="form-control" id="validationDefault02"
							placeholder="Tên nhân viên" value="Tên nhân viên" required
							readonly="true">
					</div>
					<div class="col-md-3 mb-3">
						<label for="validationDefault3">Ngày sinh</label> <input
							type="text" class="form-control" id="validationDefault03"
							placeholder="Ngày sinh" value="Ngày sinh" required
							readonly="true">
					</div>

					<div class="col-md-4 mb-3">
						<label for="validationDefault3">Chức vụ</label> <input type="text"
							class="form-control" id="validationDefault03" required readonly="true">
					</div>
					<div class="col-md-4 mb-3">
						<label for="validationDefault3">Bộ phận</label> <input type="text"
							class="form-control" id="validationDefault03" required readonly="true">
					</div>
					<div class="col-md-3 mb-3">
						<label for="validationDefault3">SĐT</label> <input type="text"
							class="form-control" id="validationDefault03"
							placeholder="Số điện thoại" value="Số điện thoại" required
							readonly="true">
					</div>


					<div class="col-md-9 mb-3">
						<label for="validationDefault3">Địa chỉ</label> <input type="text"
							class="form-control" id="validationDefault03"
							placeholder="Địa chỉ" value="Địa chỉ" required readonly="true">
					</div>


					</form>
					<div style="margin-left: 830px;">
						<img class="card-img-top"
							style="width: 150px; height: 150px; margin-top: 15px;"
							src="https://pbs.twimg.com/profile_images/3572978953/8c522d3ea384cd42e46a4a2498300c35_200x200.jpeg"
							alt="Card image cap">

					</div>



					<form:button type="name" class="btn btn-success">Lưu</form:button>
				</form:form>
				<a href="#"><button class="btn btn-warning">Quay Lại</button></a>
			</div>


		</div>

	</div>

</div>
