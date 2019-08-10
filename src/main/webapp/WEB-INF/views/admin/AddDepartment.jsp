<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset= UTF-8"
	pageEncoding="UTF-8"%>


<style>
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
	<div class="row">
		<div class="col-xs-14 col-sm-12 col-md-11 col-lg-0">
			<div class="panel panel-warning">
				<div class="panel-heading">
					<h2 class="panel-title">${title}</h2>
				</div>

				<div class="panel-body">
					<form:form modelAttribute="department" 
						action="${pageContext.request.contextPath}/admin/saveDepartment"
						method="POST">

						<form:input class="text-left" type="hidden" path="id"
							 minlength="3" maxlength="64" />

						<h4 style="margin-left: 30px">
							Tên Phòng Ban <font color="red">*</font>
						</h4>
						<br />
						<form:input class="text-left" type="text" path="name" style="margin-left: 40px"
							placeholder="Tên phòng ban" minlength="3" maxlength="64" />
						<br />
						<br />
						<span style="color: red">${error}</span>

						<br />
						<br />

						<div class="col-md-2" style="text-align: center; margin-left: -10px;">
							<form:button type="submit" class="btn btn-success"
								style="margin-top:10px;width: 80px;font-weight: bold;border-radius:5px;">Lưu</form:button>
						</div>

					</form:form>
					<br /> <a
						href="${pageContext.request.contextPath}/admin/listDepartment">
						<button
							style="margin-bottom: 10px; float: left; border-radius: 5px; margin-top: -10px;"
							class="btn btn-warning">Hủy bỏ</button>
					</a>
				</div>

			</div>
		</div>
	</div>
</div>


