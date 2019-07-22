<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
*[id$=errors] {
	color: red;
	font-style: italic;
}
</style>
</head>
<body>
	<div class="container">
		<br>
		<div class="row">
			<div class="col-xs-14 col-sm-12 col-md-11">
				<div class="panel panel-danger">
					<div class="panel-heading">
						<h2 class="panel-title">Product Management</h2>
					</div>
					<form:form modelAttribute="product">
		Mã NV<form:input class="form-control" type="number" name="id_dm"
							path="id_dm" placeholder="Mã nv" minlength="0" />
						<form:errors path="id_dm" />
						<br />
							Tên NV<form:input class="form-control" type="number" name="id_dm"
							path="id_dm" placeholder="Tên nhân viên" minlength="0" />
						<form:errors path="id_dm" />
						<br />
							Ngày sinh<form:input class="form-control" type="text"
							placeholder="Ngày sinh" />
						
						Địa chỉ<form:input class="form-control" type="number"
							placeholder="Địa chỉ" minlength="3" maxlength="100" />
						<form:errors path="price_pr" />
						<br />
							Chức vụ<form:input class="form-control" type="String"
							placeholder="Chức vụ" />
						<form:errors path="img" />
							 Số Điện Thoại<form:input class="form-control" type="number"
							name="price_pr" path="price_pr" placeholder="SĐT" minlength="5"
							maxlength="10" />
							 Bộ phận<form:input class="form-control" type="number"
							name="price_pr" path="price_pr" placeholder="Bộ phận"
							minlength="3" maxlength="30" />



						<form:button class="btn btn-success">Save</form:button>

					</form:form>

					<br> <a
						href="${pageContext.request.contextPath}/admin/listUser">
						<button class="btn btn-warning">Back</button>
					</a>
				</div>


			</div>
		</div>

		<div class="row">
			<div class="col-xs-14 col-sm-12 col-md-11">
				<div class="panel panel-danger">
					<div class="panel-heading">
						<h2 class="panel-title">Sua Hop Dong</h2>
					</div>
					<form:form modelAttribute="product"
						action="${pageContext.request.contextPath}/admin/saveproduct"
						method="POST">
	Mã NV<form:input class="form-control" type="number" name="id_dm"
							path="id_dm" placeholder="Mã nv" minlength="0" />
						<form:errors path="id_dm" />
						<br />
							Tên NV<form:input class="form-control" type="number" name="id_dm"
							path="id_dm" placeholder="Tên nhân viên" minlength="0" />
						<form:errors path="id_dm" />
						<br />
							Ngày sinh<form:input class="form-control" type="text"
							placeholder="Ngày sinh" />
						
						Địa chỉ<form:input class="form-control" type="number"
							placeholder="Địa chỉ" minlength="3" maxlength="100" />
						<form:errors path="price_pr" />
						<br />
							Chức vụ<form:input class="form-control" type="String"
							placeholder="Chức vụ" />
						<form:errors path="img" />
							 Số Điện Thoại<form:input class="form-control" type="number"
							name="price_pr" path="price_pr" placeholder="SĐT" minlength="5"
							maxlength="10" />
							 Bộ phận<form:input class="form-control" type="number"
							name="price_pr" path="price_pr" placeholder="Bộ phận"
							minlength="3" maxlength="30" />


						<br>
						<form:button type="name" class="btn btn-success">Save</form:button>
					</form:form>
					<br> <a
						href="${pageContext.request.contextPath}/admin/listProduct"><button
							class="btn btn-warning">Back</button></a>
				</div>

			</div>

		</div>
	</div>

</body>
</html> --%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset= UTF8"
	pageEncoding="UTF8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>
<script
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"
	type="text/javascript"></script>
<script src="demoValidation.js" type="text/javascript"></script>
<link href="template/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<style type="text/css">
*[id$=errors] {
	color: red;
	font-style: italic;
}
</style>

</head>
<body>


	<div class="container">
		<br>
		<div class="row">
			<div class="col-xs-14 col-sm-12 col-md-11">
				<div class="panel panel-danger">
					<div class="panel-heading">
						<h2 class="panel-title">Thêm Lý Lịch</h2>
					</div>
					<form:form modelAttribute="product"
						action="${pageContext.request.contextPath}/admin/saveproduct"
						method="POST">

						<div class="col-md-4 mb-3">
							<label for="validationDefault01">Mã nhân viên</label> <input
								type="text" class="form-control" id="validationDefault01"
								placeholder="Mã nhân viên" value="Mã nhân viên" required>
						</div>
						<%-- Mã NV<form:input class="form-control" style="" type="number"
							name="id_dm" path="id_dm" placeholder="Mã Nhân Viên"
							minlength="0" /> --%>
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
							<label for="validationDefault3">Địa chỉ</label> <input
								type="text" class="form-control" id="validationDefault03"
								placeholder="Địa chỉ" value="Địa chỉ" required>
						</div>
						

						<div class="col-md-1 mb-1">
							<label for="validationDefault3">Chức Vụ</label> <input
								type="checkbox" class="form-control" id="validationDefault03"
								 required>
						</div>
					
					 <div class="col-md-3 mb-3">
							<label for="validationDefault3">SĐT</label> <input
								type="text" class="form-control" id="validationDefault03"
								placeholder="Số điện thoại" value="Số điện thoại" required>
						</div>



						<div class="col-md-3 mb-3">
							<label for="validationDefault3">Bộ phận</label> <input
								type="text" class="form-control" id="validationDefault03"
								placeholder="Bộ phận" value="Bộ phận" required>
						</div>
						
						<form:button type="name" class="btn btn-success">Save</form:button>
					</form:form>
					 <a
						href="${pageContext.request.contextPath}/admin/listProduct"><button
							class="btn btn-warning">Back</button></a>
				</div>

			</div>

		</div>
	</div>
	<!-- Bootstrap core JavaScript -->
	<script src="templates/jquery/jquery.min.js"></script>
	<script src="templates/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>