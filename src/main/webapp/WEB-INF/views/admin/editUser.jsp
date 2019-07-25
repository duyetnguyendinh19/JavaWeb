<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>
	    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js" type="text/javascript"></script>
	    <script src="demoValidation.js" type="text/javascript"></script>
<link href="template/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<style type="text/css">
*[id$=errors]{
	color:red;
	font-style: italic;
}
</style>
</head>
<body>


	<div class="container">
	<div class="row">
			<div class="col-xs-14 col-sm-12 col-md-11">
				<div class="panel panel-danger">
					<div class="panel-heading">
						<h2 class="panel-title">Sửa Tài Khoản</h2>
					</div>
	
		<form:form modelAttribute="user"
			action="#"
			method="POST">

							<div class="col-md-6 mb-3">
							<label for="validationDefault3">Tên Tài khoản</label> <input
								type="text" class="form-control" id="validationDefault03"
								placeholder="Tên Tài khoản" value="Tên Tài khoản" required>
						</div>
						
						<div class="col-md-6 mb-3">
							<label for="validationDefault3">Mật Khẩu</label> <input
								type="text" class="form-control" id="validationDefault03"
								placeholder="Mật Khẩu" value="Mật Khẩu" required>
						</div>
							
							 <div class="col-md-11 mb-3">
							<label for="validationDefault3">Họ tên</label> <input
								type="text" class="form-control" id="validationDefault03"
								placeholder="Họ tên" value="Họ tên" required>
						</div>
							  <div class="col-md-1 mb-1">
							<label for="validationDefault3">Chức vụ</label> <input
								type="checkbox" class="form-control" id="validationDefault03"
								 required>
						</div>

			<br>
			<form:button type="name" class="btn btn-success"   >Lưu</form:button>
		</form:form>
		<br>
		<a href="#"><button
				class="btn btn-warning">Quay Lại</button></a>
	</div>
	</div>
	</div>
	</div>


<!-- Bootstrap core JavaScript -->
	<script src="templates/jquery/jquery.min.js"></script>
	<script src="templates/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>