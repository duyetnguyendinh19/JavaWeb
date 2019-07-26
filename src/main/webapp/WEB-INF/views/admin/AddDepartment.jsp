<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset= UTF-8"
	pageEncoding="UTF-8"%>


<div class="container">
	<div class="row">
		<div class="col-xs-14 col-sm-12 col-md-11 col-lg-0">
			<div class="panel">
				<div class="panel-heading">
					<br>

					<form:form modelAttribute="department"
						action="${pageContext.request.contextPath}/admin/saveDepartment"
						method="POST">

						<form:input class="text-left" type="hidden" path="id"
							placeholder=" Prodcut Name" minlength="3" maxlength="64" />

						<h4>
							Tên Phòng Ban <font color="red">*</font>
						</h4>
						<br />
						<form:input class="text-left" type="text" path="name"
							placeholder="Tên phòng ban" minlength="3" maxlength="64" />
						<br />
						<br />
						<span style="color: red">${error}</span>

						<br />
						<br />

						<form:button type="submit" class="btn btn-primary">Lưu</form:button>

					</form:form>
					<br /> <a href="${pageContext.request.contextPath}/admin/listDepartment">
						<button class="btn">Quay Lại</button>
					</a>
				</div>
			</div>
		</div>
	</div>
</div>

