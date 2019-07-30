<%--<%@page import="shop.newshop.DaoImpl.CategoryDAOImpl"%>--%>
<%--<%@page import="shop.newshop.Service.CategoryService"%>--%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Quản lý hợp đồng</title>
</head>
<style>
	tr th {
		text-align: center;
	}
	.ui.modal{
		margin-top: 50px;
		width: 28%;
		height: 30%;
		margin-left: auto;
		margin-right: auto;
	}
	form button{
		margin-left: 5px;
		height: 35px;
		color: white;
		background: #0ea432;
		border: none;
		border-radius: 4px;
		width: 90px;
	}
	form button:hover{
		background: #68a458;
	}
	.tennv{
		width: 50%;
		height: 35px;
		margin-left: 10px;
	}
	.page button{
		height: 30px;
		color: white;
		background: #4e4ee6;
		border: none;
		font-size: 11px;
		border-radius: 3px;
		width: 30px;
	}
	.page button:hover{
		background: #a7a7ef;
	}
	.ui.black.deny.button{
		width: 25%;
		height: 40px;
	}
</style>
<c:if test="${not empty searchFail}">
	<label class="alert alert-danger" id="name_errors" style="margin-left: 25px;width: 91.3%;color: red;font-size: 18px;">${searchFail}</label>
</c:if>
<div class="container">
	<div class="row">
		<div class="col-lg-9">
			<div class="container">
				<div class="row">
					<div class="col-xs-14 col-sm-12 col-md-11">
						<div class="panel panel-warning">
							<div class="panel-heading">
								<h2 class="panel-title">Quản lý khen thưởng</h2>
							</div>
							<form action="${pageContext.request.contextPath}/admin/listBonus" method="POST" style="float: left;padding: 16px;width: 50%;">
								<label>Tên nhân viên:</label>
								<input type="text" name="tennv" class="tennv" placeholder="Tên nhân viên..." style="border-radius: 5px!important;
" value="${nameSearch}"/>
								<button type="submit" style="font-family: Tahoma">Tìm kiếm</button>
							</form>
							<a href="${pageContext.request.contextPath}/admin/addBonus">
								<button class="btn btn-success"
										style="font-family: Lato,Helvetica Neue,Arial,Helvetica,sans-serif;float:right;margin: 10px 15px; ">
									Thêm khen thưởng
								</button>
							</a>
							<div class="panel-body">
								<table class="table table-bordered table-hover">
									<thead>
									<tr style="text-align: center;border-bottom: 2px solid #dddddd;">
										<th>STT</th>
										<th>Tên NV</th>
										<th>Loạt khen thưởng</th>
										<th>Mô tả khen thưởng</th>
										<th>Lý do khen thưởng</th>
										<th>Ngày khen thưởng</th>
										<th class="fas fa-cogs" style="margin-top: 12px;"></th>
									</tr>
									</thead>
									<tbody>
									<c:forEach items="${bonusList}" var="bonusList" varStatus="loop">
										<tr style="text-align: center;">
											<td>${loop.count}</td>
											<td>${bonusList.employee.name}</td>
											<td>${bonusList.type}</td>
											<td>${bonusList.descent}</td>
											<td>${bonusList.reason}</td>
											<td><fmt:formatDate pattern="dd-MM-yyyy" value="${bonusList.date}"/></td>
											<td>
												<a href="${pageContext.request.contextPath}/admin/updateBonus/${bonusList.id}">
													<button class="btn btn-warning" data-toggle="tooltip"
															title="Sửa khen thưởng">
														<i class="fas fa-user-edit" aria-hidden="true"></i>
													</button>
												</a>
												<button class="btn btn-danger" id="delete" onclick="loadModal('${bonusList.employee.name}', '${bonusList.id}')" data-toggle="tooltip"
														title="Xóa khen thưởng">
													<i class="fa fa-trash" aria-hidden="true"></i>
												</button>
											</td>
										</tr>
									</c:forEach>
									<c:if test="${not empty searchFail}">
										<tr>
											<td colspan="7">Danh sách rỗng</td>
										</tr>
									</c:if>
									</tbody>
								</table>
								<div style="margin-left: 400px;" class="page">
									<a>
										<button><<</button>
										<button><</button>
										<input type="text" style="width: 30px;border-radius: 3px!important;margin-left: 3px;">
										of <input type="text" style="width: 30px;border-radius: 3px!important;margin-left:2px;margin-right: 3px;" readonly="true">
										<button>></button>
										<button>>></button>
									</a> <a style=float:right;">
									<label>View</label>
									<label>1</label>
									<label>-</label>
									<label>5</label>
									<label>of</label>
									<label>12</label>
								</a>
								</div>
								<div class="ui modal">
									<i class="close icon"></i>
									<div class="header">
										Xóa khen thưởng
									</div>
									<div class="content">
										<h4>Bạn có muốn xóa <span id="name"></span> không?</h4>
									</div>
									<div class="actions">
										<a id="deleteComfirm" data-hrefbefore="${pageContext.request.contextPath}/admin/deleteBonus/">
											<button class="ui black deny button" style="background: green;">
												Ok
											</button>
										</a>
										<button class="ui black deny button" onclick="deleteTooltip()">
											Hủy bỏ
										</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	$("#name_errors").hide().fadeIn(0).delay(2000).fadeOut(500);
</script>
<script>
	function loadModal(name, id){
		$('.ui.modal').modal('show');
		$('#name').text(name);
		var $deleteConfirm =  $('#deleteComfirm');
		document.getElementById('deleteComfirm').href=$deleteConfirm.attr('data-hrefbefore') + id;
	}
</script>
<script>
	function deleteTooltip(){
		$('.ui-tooltip.ui-widget.ui-corner-all.ui-widget-content').remove();
	}
</script>
<script type="text/javascript">
	$(document).ready(function () {
		$('[data-toggle="tooltip"]').tooltip();
	});
</script>
<script type="text/javascript">
	function deleteDialog() {
		var result = confirm("Do you want to delete this product?");
		if (result) {
			alert("Delete success");
		} else {
			alert("Bye");
		}
	}
</script>
</body>
</html>