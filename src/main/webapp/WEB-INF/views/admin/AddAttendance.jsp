<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <style type="text/css">
        *[id$=errors] {
            color: red;
            font-style: italic;
        }

        .btn.btn-success, .btn.btn-warning {
            width: 8%;
            height: 40px;
            font-weight: bold;
            margin: 15px 0px;
        }

        .form-control {
            height: 38px !important;
        }

        input[type=email], input[type=password], input[type=search], input[type=text] {
            border-radius: 5px !important;
        }

        #fromTimeInput, #toTimeInput {
            border-bottom: 1px solid #e2e2e2;
            width: 100%;
            font-size: 16px;
            text-align: center;
            padding: 7px 0;
        }
    </style>
</head>
<body>
<div class="container">
    <br>
    <div class="row">
        <div class="col-xs-14 col-sm-12 col-md-11">
            <div class="panel panel-warning">
                <div class="panel-heading">
                    <h2 class="panel-title">Thêm công nhân viên</h2>
                </div>
                <form action="${pageContext.request.contextPath}/admin/addAttendance" method="POST">
                    <div class="col-md-6 mb-6" style="margin-top: 15px;">
                        <label>Nhân viên</label>
                        <select class="ui dropdown form-control" id="SelectEmployee" name="employee">
                            <c:forEach items="${employee}" var="employee">
                                <option value="${employee.id}">${employee.id} - ${employee.name}</option>
                            </c:forEach>
                        </select>
                        <label>${errorEmployee}</label>
                    </div>
                    <%--                    <input type="text" name="employee" value="" hidden="hidden"/>--%>
                    <div class="col-md-6 mb-6" style="margin-top: 15px;">
                        <label>Ngày</label>
                        <div class="ui calendar" id="rangestart">
                            <input type="text" class="form-control datepicker" id="startDate" value="${startday}"
                                   name="startday"
                                   placeholder="Ngày">
                            <label style="color: red;margin-top: 5px;">${errorstartday}</label>
                        </div>
                    </div>
                    <div class="col-md-6 mb-3" style="margin-top: 15px;">
                        <label>Thời gian bắt đầu</label>
                        <div class="fromTime animated slideInRight">
                            <input type="text" id="fromTimeInput" style="cursor: pointer;" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="col-md-6 mb-3" style="margin-top: 15px;">
                        <label>Thời gian kết thúc</label>
                        <div class="toTime animated slideInLeft">
                            <input type="text" id="toTimeInput" style="cursor: pointer;" readonly="readonly"/>
                        </div>
                    </div>
                    <br/>
                    <div style="width: 100%;text-align: center;margin-top: 16%;"><br>
                        <button type="submit" class="btn btn-success" style="float:left;margin-left: 41%;">Lưu</button>
                    </div>
                </form>
                <button class="btn btn-warning" id="close"
                        style="background: #aaa;border: #aaa;margin-left: 20px;">Hủy bỏ
                </button>
            </div>
        </div>
    </div>
</div>
<script>
    let currDate = new Date()
    let hour = currDate.getHours()
    let hourTo = hour + 4
    let minute = currDate.getMinutes()
    hour = hour < 10 ? '0' + hour : hour
    hourTo = hourTo < 10 ? '0' + hourTo : hourTo
    minute = minute < 10 ? '0' + minute : minute
    $('#fromTimeInput').val(hour + ':' + minute);
    $('#toTimeInput').val(hourTo + ':' + minute);
    $('#fromTimeInput, #toTimeInput').bootstrapMaterialDatePicker({
        date: false,
        format: 'HH:mm',
    });
</script>
<script>
    if (${idEmployee}) {
        $('#SelectEmployee').val('${idEmployee}').trigger('change');
    }
</script>
<script>
    $('#close').on('click', function () {
        window.location.assign('/Manager/admin/listAttendance');
    })
</script>
<script>
    $('.ui.dropdown').dropdown();
</script>
<script>
    var toDate = new Date();
    var date = toDate.getDate();
    var endDate = date;
    var month = toDate.getMonth() + 1;
    var endMonth = month + 1;
    var year = toDate.getFullYear();
    $('#startDate').val(month + "/" + date + "/" + year);
    $('#rangestart').calendar({
        type: 'date',
        text: {
            days: ['CN', 'T2', 'T3', 'T4', 'T5', 'T6', 'T7'],
            months: ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6', 'Tháng 7', 'Tháng 8', 'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12'],
        },
        formatter: {
            date: function (date, setting) {
                if (!date) return '';
                var day = date.getDate();
                var month = date.getMonth() + 1;
                var year = date.getFullYear();
                return (day < 10 ? '0' + day : day) + '/' + (month < 10 ? '0' + month : month) + '/' + year;
            }
        },
        today: true,
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
<script>
    var that = this;
    var isFormatNumber = true;
    // var $input = $('#' + 'salary');
    if (isFormatNumber !== false) {
        $('#salary').on('keyup', function () {
            var formattedVal = formatNumberString($('#salary').val().replace(/,/g, ''));
            $('#salary').val(formattedVal);
        });
    }
    $('#salary').on("input keydown keyup mousedown mouseup select contextmenu drop", function () {
        if (/^\d*$/.test(this.value.replace(/,/g, ''))) {
            this.oldValue = this.value;
            this.oldSelectionStart = this.selectionStart;
            this.oldSelectionEnd = this.selectionEnd;
        } else if (this.hasOwnProperty("oldValue")) {
            this.value = this.oldValue;
            this.setSelectionRange(this.oldSelectionStart, this.oldSelectionEnd);
        }
    });
</script>
</body>
</html>