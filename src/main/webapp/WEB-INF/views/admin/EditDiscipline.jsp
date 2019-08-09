<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý kỷ luật</title>
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
            height: 38px;

        }

        input[type=email], input[type=password], input[type=search], input[type=text] {
            border-radius: 5px !important;
            padding-left: 10px;
            padding-right: 10px;
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
                    <h2 class="panel-title">Sửa kỷ luật</h2>
                </div>
                <form action="${pageContext.request.contextPath}/admin/updateDiscipline" method="POST">
                    <input type="text" value="${discipline.employee.id}" name="employee" hidden="hidden"/>
                    <input type="text" value="${discipline.id}" name="id" hidden="hidden"/>
                    <div class="col-md-4 mb-6" style="margin-top: 15px;">
                        <label>Nhân viên</label>
                        <input type="text" class="form-control"
                               value="${discipline.employee.id} - ${discipline.employee.name}" readonly/>
                        <%--						<label>${errorEmployee}</label>--%>
                    </div>
                    <div class="col-md-4 mb-6" style="margin-top: 15px;">
                        <label>Loại kỷ luật</label>
                        <select class="ui dropdown form-control" id="typeDiscipline" name="typeDiscipline">
                            <option value="Cảnh cáo">Cảnh cáo</option>
                            <option value="Phạt tiền">Phạt tiền</option>
                            <option value="Đình chỉ">Đình chỉ</option>
                            <option value="Đuổi việc">Đuổi việc</option>
                        </select>
                    </div>
                    <div class="col-md-4 mb-3" style="margin-top: 15px;">
                        <label>Ngày kỷ luật</label>
                        <div class="ui calendar" id="rangestart">
                            <input type="text" class="form-control datepicker" id="startDate" readonly
                                   name="date" value="<fmt:formatDate pattern="dd-MM-yyyy"
                                                                value="${discipline.date}"/>"
                                   placeholder="Ngày kỷ luật">
                            <label style="color: red;margin-top: 5px;">${errorDate}</label>
                        </div>
                    </div>
                    <div class="col-md-6 mb-3" style="margin-top: 15px;">
                        <label style="width: 100%;">Mô tả kỷ luật</label>
                        <textarea rows="6" name="descent" placeholder="Mô tả kỷ luật"
                                  style="width: 100%;border-radius: 5px!important;padding: 10px;float:left;">${discipline.descent}</textarea>
                        <label style="color: red;margin-top: 5px;">${errorDescent}</label>
                    </div>
                    <div class="col-md-6 mb-3" style="margin-top: 15px;">
                        <label>Lý do kỷ luật</label>
                        <textarea rows="6" name="reason" placeholder="Lý do kỷ luật"
                                  style="width: 100%;border-radius: 5px!important;padding: 10px;">${discipline.reason}</textarea>
                        <label style="color: red;margin-top: 5px;">${errorReason}</label>
                    </div>
                    <br/>
                    <div style="width: 100%;text-align: center;margin-top: 23%;"><br>
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
    $('#typeDiscipline').val('${discipline.type}').trigger('change');
</script>
<script>
    $('#close').on('click', function () {
        window.location.assign('/Manager/admin/listDiscipline');
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
    <%--if (${discipline.date} = null) {--%>
    <%--    $('#startDate').val(month + "/" + date + "/" + year);--%>
    <%--}--%>
    // $('#endDate').val(endMonth + "/" + endDate + "/" + year);
    $('#rangestart').calendar({
        type: 'date',
        // endCalendar: $('#rangeend'),
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