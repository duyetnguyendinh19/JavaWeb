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
						<h2 class="panel-title">Thêm Hợp Đồng</h2>
					</div>
					<form:form modelAttribute="product"
						action="${pageContext.request.contextPath}/admin/saveproduct"
						method="POST">

						<div class="col-md-4 mb-3">
							<label for="validationDefault3">Mã hợp đồng</label> <input
								type="text" class="form-control" id="validationDefault03"
								placeholder="Mã hợp đồng" value="Mã hợp đồng" required>
						</div>

						<div class="col-md-4 mb-3">
							<label for="validationDefault3">Mã nhân viên</label> <input
								type="text" class="form-control" id="validationDefault03"
								placeholder="Mã nhân viên" value="Mã nhân viên" required>
						</div>
						<div class="col-md-4 mb-3">
							<label for="validationDefault3">Tên nhân viên</label> <input
								type="text" class="form-control" id="validationDefault03"
								placeholder="tên nhân viên" value="Tên nhân viên" required>
						</div>

						<div class="col-md-6 mb-3">
							<label for="validationDefault3">Ngày bắt đầu</label> <input
								type="text" class="form-control" id="validationDefault03"
								placeholder="Ngày bắt đầu" value="Ngày bắt đầu" required>
						</div>

						<div class="col-md-6 mb-3">
							<label for="validationDefault3">Ngày kết thúc</label> <input
								type="text" class="form-control" id="validationDefault03"
								placeholder="Ngày kết thúc" value="Ngày kết thúc" required>
						</div>

						<br />
						<hr>

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



	<!-- Bootstrap core JavaScript -->
	<script src="templates/jquery/jquery.min.js"></script>
	<script src="templates/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>