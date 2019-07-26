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
			<div class="col-xs-14 col-sm-12 col-md-11 col-lg-0">
				<div class="panel panel-danger">
					<div class="panel-heading">
						<center>
							<h3>Sửa Phòng Ban</h3>
						</center>
						<form:form modelAttribute="#"
							action="#"
							method="POST">
			Mã Phòng Ban
			<form:input class="form-control" type="text" path="id_dm"
								value="${category.id_dm}" readonly="true" />
							<br>
							Tên Phòng Ban
							<form:input class="form-control" type="text" path="ten_dm"
								value="${category.ten_dm}" minlength="3" maxlength="24" />
							<form:errors path="ten_dm" />
							</br/>
							<form:button class="btn btn-success">Lưu</form:button>

						</form:form>

						<br> <a
							href="#"><button
								class="btn btn-warning">Quay Lại</button></a>
					</div>
				</div>
			</div>
		</div>

	</div>
</body>
</html> --%>