<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link href="template/css/bootstrap.min.css" rel="stylesheet">
<style>
.ui.dropdown {
	width: 100%;
}

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
	<br>
	<div class="row">
		<div class="col-xs-14 col-sm-12 col-md-11">
			<div class="panel panel-warning">
				<div class="panel-heading">
					<h2 class="panel-title">Thêm Nhân Viên</h2>
				</div>
				<br /> <span style="color: red; margin-left: 10px">${error}</span> <br />
				<form:form modelAttribute="employee"
					action="${pageContext.request.contextPath}/admin/saveEmployee"
					method="POST" enctype="multipart/form-data">
					<input type="text" name="image" id="image" value="" hidden="hidden" />
					<br />
					<div class="row">
						<div class="col-md-3 mb-3" >
							<div class="wrap-custom-file"
								style="margin-top: 15px; margin-left: 20px;margin-bottom:20px">
								<input type="file" name="avatar1" class="imgUpload" id="avatar1"
									accept=".gif,.jpg,.png,.jpeg" /> <label for="avatar1"
									id="avatar1-background" style="width: 190px; height: 160px;"
									:style="{'background-image' : 'url(' + dataValue.avatar1 +')'}">
									<span id="chooseImage">Chọn ảnh</span>
								</label>
							</div>
						</div>
						<div class="col-md-5 mb-3" style="margin-top: 5px">
							<label for="validationDefault02">Tên nhân viên <font
								color="red">*</font>
							</label>
							<form:input path="name" type="text" class="form-control"
								placeholder="Tên nhân viên" />
						</div>
						<div class="col-md-4 mb-3"
							style="margin-bottom: 30px; margin-top: 5px;">
							<div class="ui calendar" id="rangestart">
								<label for="validationDefault3">Ngày sinh <font
									color="red">*</font></label>
								<form:input path="birthday" class="form-control datepicker"
									type="text" id="startDate" name="startday"
									placeholder="Ngày sinh" />
							</div>
						</div>
						<div class="col-md-5 mb-3">
							<label for="validationDefault3">Chức vụ</label> <br />
							<form:select path="level" class="ui dropdown">
								<option value="Trưởng phòng">Trưởng phòng</option>
								<option value="Nhân Viên" selected>Nhân Viên</option>
							</form:select>
						</div>
						<div class="col-md-4 mb-3">
							<label for="validationDefault3">Phòng ban</label> <br /> <select
								name="idDepartment" class="ui dropdown">
								<c:forEach var="item" items="${deparment}">
									<option value="${item.id}"
										${emplyee.department.id == item.id ? 'selected=""' : ''}>${item.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="row"
						style="float: left; width: 100%; margin: 0px !important; margin-left: 22px !important; padding-right: 25px;">
						<div class="col-md-4 mb-3" style="margin-bottom: 30px">
							<label for="validationDefault3">Số điện thoại <font
								color="red">*</font></label>
							<form:input path="phone" type="text" class="form-control"
								placeholder="Số điện thoại" />
						</div>

						<div class="col-md-4 mb-3" style="margin-bottom: 30px">
							<label for="validationDefault3">Email <font color="red">*</font></label>
							<form:input type="text" path="email" class="form-control"
								placeholder="Email" />
						</div>

						<div class="col-md-4 mb-3">
							<label for="validationDefault3">Chứng minh nhân dân <font
								color="red">*</font></label>
							<form:input type="text" path="identitycard" class="form-control"
								placeholder="Chứng minh nhân dân" />
						</div>
					</div>
					<div class="row"
						style="float: left; width: 85%; margin: 0px !important; margin-left: 22px !important; padding-bottom: 25px;">
						<div class="col-md-5 mb-3">
							<label for="validationDefault3">Địa chỉ <font color="red">*</font></label>
							<form:input type="text" path="address" class="form-control"
								placeholder="Địa chỉ" />
						</div>
						
						<div class="col-md-5 mb-3">
							<label for="validationDefault3">Tài khoản<font color="red">*</font></label>
							<input type="text" name="username" class="form-control"
								placeholder="Tài khoản" />
						</div>

						<div class="col-md-2" style="text-align: center;">
							<form:button type="name" class="btn btn-success"
								style="margin-top:20px;width: 80px;font-weight: bold;border-radius:5px;">Lưu</form:button>
						</div>
					</div>
				</form:form>
				<a href="${pageContext.request.contextPath}/admin/listEmployee">
					<button
						style="margin-bottom: 10px; float: left; border-radius: 5px; margin-top: 20px;"
						class="btn btn-warning">Hủy bỏ</button>
				</a>
			</div>


		</div>

	</div>

</div>

<script>
    $('.ui.dropdown').dropdown();
</script>
<script type="text/javascript">
    let that = this;
    $('input[type="file"].imgUpload').each(function () {
        let $file = $(this),
            $label = $file.next('label'),
            $labelText = $label.find('span'),
            labelDefault = $labelText.text();
        $file.on('change', function (event) {
            let fileName = $file.val().split('\\').pop(),
                tmppath = URL.createObjectURL(event.target.files[0]);
            if (fileName) {
                $label
                    .addClass('file-ok')
                    .css('background-image', 'url(' + tmppath + ')');
                $labelText.removeClass('red');
                $labelText.text(fileName);
                that.dataValueClone.avatar1 = fileName;
            } else {
                $label.removeClass('file-ok');
                $labelText.text(labelDefault);
            }
        });
    });
</script>

<script>
    var toDate = new Date();
    var date = toDate.getDate();
    var endDate = date;
    var month = toDate.getMonth() + 1;
    var endMonth = month + 1;
    var year = toDate.getFullYear();
    /* 	$('#startDate').val(month + "/" + date + "/" + year);
     */
    $('#endDate').val(endMonth + "/" + endDate + "/" + year);
    $('#rangestart').calendar(
        {
            type: 'date',
            endCalendar: $('#rangeend'),
            text: {
                days: ['CN', 'T2', 'T3', 'T4', 'T5', 'T6', 'T7'],
                months: ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4',
                    'Tháng 5', 'Tháng 6', 'Tháng 7', 'Tháng 8',
                    'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12'],
            },
            formatter: {
                date: function (date, setting) {
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
            today: true,
        });
    $('#rangeend').calendar(
        {
            type: 'date',
            startCalendar: $('#rangestart'),
            text: {
                days: ['CN', 'T2', 'T3', 'T4', 'T5', 'T6', 'T7'],
                months: ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4',
                    'Tháng 5', 'Tháng 6', 'Tháng 7', 'Tháng 8',
                    'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12']
            },
            formatter: {
                date: function (date, setting) {
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
            today: true,
            popupOptions: {
                position: 'bottom right',
                lastResort: 'bottom right',
                prefer: 'opposite',
                hideOnScroll: false
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
