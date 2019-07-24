<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset= UTF8"
	pageEncoding="UTF8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
.error {
	color: red;
}
</style>
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
		<div class="row">
			<div class="col-xs-14 col-sm-12 col-md-11 col-lg-0">
				<div class="panel panel-danger">
					<div class="panel-heading">
						<br>
						<center>
							<h3>Add Category</h3>
						
						<form:form modelAttribute="category"
							action="${pageContext.request.contextPath}/admin/saveCate"
							method="POST">

							
							Ten PB<br />
							<form:input class="text-left" type="text" name="ten_dm"
								path="ten_dm" placeholder=" Prodcut Name" minlength="3"
								maxlength="24" />
							<form:errors path="ten_dm" />

							<br>
							<form:button type="name" class="btn btn-success">Save</form:button>
						</form:form>
					 <a
							href="${pageContext.request.contextPath}/admin/listCategory">
							<button class="btn btn-warning">Back</button>
						</a>
						</center>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript -->
	<script src="templates/jquery/jquery.min.js"></script>
	<script src="templates/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>