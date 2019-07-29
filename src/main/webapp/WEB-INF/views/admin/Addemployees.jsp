
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link href="template/css/bootstrap.min.css" rel="stylesheet">
<div class="container">
	<br>
	<div class="row">
		<div class="col-xs-14 col-sm-12 col-md-11">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h2 class="panel-title">Thêm Nhân Viên</h2>
				</div>
				<br /> <span style="color: red; margin-left: 10px">${error}</span>
				<br />
				<form:form modelAttribute="employee"
					action="${pageContext.request.contextPath}/admin/saveEmployee"
					method="POST">
					<input type="text" name="image" id="image" value="" hidden="hidden" />
					<br />
					<div class="col-md-4 mb-3">
						<label for="validationDefault01">Mã nhân viên</label>
						<form:input path="id" type="text" class="form-control"
							id="validationDefault01" placeholder="Mã nhân viên"
							readonly="true" />
					</div>

					<div class="col-md-5 mb-3">
						<label for="validationDefault02">Tên nhân viên <font
							color="red">*</font>
						</label>
						<form:input path="name" type="text" class="form-control"
							placeholder="Tên nhân viên" />
					</div>
					<div class="col-md-3 mb-3" style="margin-bottom: 30px">
						<div class="ui calendar" id="rangestart">
							<label for="validationDefault3">Ngày sinh <font
								color="red">*</font></label>
							<form:input path="birthday" class="form-control datepicker"
								type="text" id="startDate" name="startday"
								placeholder="Ngày sinh" />
						</div>
					</div>

					<br />
					<div class="col-md-4 mb-3">
						<label for="validationDefault3">Chức vụ</label> <br />
						<form:select path="level" class="custom-select custom-select-sm">
							<option value="Trưởng phòng">Trưởng phòng</option>
							<option value="Nhân Viên" selected>Nhân Viên</option>
						</form:select>
					</div>
					<div class="col-md-5 mb-3">
						<label for="validationDefault3">Phòng ban</label> <br /> <select
							name="idDepartment" class="custom-select custom-select-sm">
							<c:forEach var="item" items="${deparment}">
								<option value="${item.id}"
									${emplyee.department.id == item.id ? 'selected=""' : ''}>${item.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-md-3 mb-3" style="margin-bottom: 30px">
						<label for="validationDefault3">Số điện thoại <font
							color="red">*</font></label>
						<form:input path="phone" type="text" class="form-control"
							placeholder="Số điện thoại" />
					</div>

					<div class="col-md-6 mb-3">
						<label for="validationDefault3">Chứng minh nhân dân <font
							color="red">*</font></label>
						<form:input type="text" path="identitycard" class="form-control"
							placeholder="Chứng minh nhân dân" />
					</div>

					<div class="col-md-6 mb-3" style="margin-bottom: 30px">
						<label for="validationDefault3">Email <font color="red">*</font></label>
						<form:input type="text" path="email" class="form-control"
							placeholder="Email" />
					</div>


					<div class="col-md-12 mb-3">
						<label for="validationDefault3">Địa chỉ <font color="red">*</font></label>
						<form:input type="text" path="address" class="form-control"
							placeholder="Địa chỉ" />
					</div>



					<div class="col-md-12 mb-4"
						style="margin-top: 10px; margin-bottom: 20px">
						<div class="wrap-custom-file">
							<input type="file" name="avatar" class="imgUpload" id="avatar"
								accept=".gif,.jpg,.png,.jpeg" /> <label for="avatar"
								style="display: flex; justify-content: center; align-items: center;"
								class="imageFile"> <span id="loading-add-image-span"
								class="fa fa-spinner fa-spin"
								style="display: none; margin-top: 0 !important"></span> <span
								id="chooseImage" style="margin: 0px;">Chọn ảnh</span> <span
								class="nameFile"
								style="position: absolute; bottom: 0; left: 0; width: 100%; height: 26px; padding: 0.3rem; font-size: 1.1rem; color: #6a0700; background-color: rgba(255, 255, 255, 0.7); height: 28px;"></span>
							</label>
						</div>
					</div>

					<%-- 	<div class="col-md-12" style="margin-bottom: 20px">
						<img class="card-img-top"
							style="width: 150px; height: 150px; margin-top: 15px;"
							src="${employee.avatar}">

					</div> --%>


					<div class="col-md-1">
						<form:button type="name" class="btn btn-success">Lưu</form:button>
					</div>
				</form:form>

				<a href="#"><button style="margin-bottom: 10px"
						class="btn btn-warning">Quay Lại</button></a>

			</div>


		</div>

	</div>

</div>


<script type="text/javascript">
	$('#avatar').on(
			'change',
			function(event) {
				var that = this;
				$('#chooseImage').css('display', "none");
				var reader = new FileReader();
				var objectResult = {};
				var dataFile = this.files[0];
				reader.readAsDataURL(dataFile);
				reader.onload = function() {
					setTimeout(function() {
						objectResult.fileName = dataFile.name;
						var readerResult = reader.result;
						objectResult.content = readerResult.split(',')[1];
						var tmppath = URL.createObjectURL(dataFile);
						$('#loading-add-image-span').remove();
						$('#chooseImage').remove();
						$('.imageFile').css('background-image',
								'url("' + readerResult + '")');
						$('.nameFile').text(dataFile.name);
						$('#image').attr("value", readerResult);
					}, 200);
				};
				reader.onerror = function(error) {
					console.log('Error: ', error);
					jor
				};
			})
</script>

<script>
	var toDate = new Date();
	var date = toDate.getDate();
	var endDate = date;
	var month = toDate.getMonth() + 1;
	var endMonth = month + 1;
	var year = toDate.getFullYear();
/* 	$('#startDate').val(month + "/" + date + "/" + year);
 */	$('#endDate').val(endMonth + "/" + endDate + "/" + year);
	$('#rangestart').calendar(
			{
				type : 'date',
				endCalendar : $('#rangeend'),
				text : {
					days : [ 'CN', 'T2', 'T3', 'T4', 'T5', 'T6', 'T7' ],
					months : [ 'Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4',
							'Tháng 5', 'Tháng 6', 'Tháng 7', 'Tháng 8',
							'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12' ],
				},
				formatter : {
					date : function(date, setting) {
						if (!date)
							return '';
						var day = date.getDate();
						var month = date.getMonth() + 1;
						var year = date.getFullYear();
						return (day < 10 ? '0' + day : day) + '/'
								+ (month < 10 ? '0' + month : month) + '/'
								+ year;
					}
				},
				today : true,
			});
	$('#rangeend').calendar(
			{
				type : 'date',
				startCalendar : $('#rangestart'),
				text : {
					days : [ 'CN', 'T2', 'T3', 'T4', 'T5', 'T6', 'T7' ],
					months : [ 'Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4',
							'Tháng 5', 'Tháng 6', 'Tháng 7', 'Tháng 8',
							'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12' ]
				},
				formatter : {
					date : function(date, setting) {
						if (!date)
							return '';
						var day = date.getDate();
						var month = date.getMonth() + 1;
						var year = date.getFullYear();
						return (day < 10 ? '0' + day : day) + '/'
								+ (month < 10 ? '0' + month : month) + '/'
								+ year;
					}
				},
				today : true,
				popupOptions : {
					position : 'bottom right',
					lastResort : 'bottom right',
					prefer : 'opposite',
					hideOnScroll : false
				}
			});
</script>
<script>
	function formatNumberString(numberStr) {
		if (typeof numberStr === 'number') {
			numberStr = numberStr.toString();
		}
		return numberStr.replace(/(?=(?:\d{3})+$)(?!^)/g, ',');
	}
</script>
