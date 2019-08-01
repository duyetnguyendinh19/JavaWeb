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
    <title>Quản lý công</title>
</head>
<style>
    tr th {
        text-align: center;
    }

    .ui.modal {
        margin-top: 50px;
        width: 28%;
        height: 30%;
        margin-left: auto;
        margin-right: auto;
    }

    form button {
        height: 36px;
        color: white;
        background: #0ea432;
        border: none;
        border-radius: 4px;
        width: 90px;
    }

    form button:hover {
        background: #68a458;
    }

    .tennv {
        height: 36px;
    }

    .page button {
        height: 30px;
        color: white;
        background: #4e4ee6;
        border: none;
        font-size: 11px;
        border-radius: 3px;
        width: 30px;
    }

    .page button:hover {
        background: #a7a7ef;
    }

    .ui.dropdown {
        margin-left: 15px;
        margin-right: 15px;
    }

    .ui.black.deny.button {
        width: 25%;
        height: 40px;
    }
    @media (min-width: 1200px) {
        .col-lg-3 {
            margin-left: 5px;
        }
    }
    @media (max-width: 500px) {
        .col-xs-4,.col-xs-6{
            margin-top: 8px!important;
        }
        form{
            width: 100%!important;
        }
        .btn.btn-success{
            margin-bottom: 15px;
            width: 92%!important;
        }
    }
</style>
<c:if test="${not empty searchFail}">
    <label class="alert alert-danger" id="name_errors"
           style="margin-left: 25px;width: 91.3%;color: red;font-size: 18px;">${searchFail}</label>
</c:if>
<div class="container">
    <div class="row">
        <div class="col-lg-9">
            <div class="container">
                <div class="row">
                    <div class="col-xs-14 col-sm-12 col-md-11">
                        <div class="panel panel-warning">
                            <div class="panel-heading">
                                <h2 class="panel-title">Quản lý công</h2>
                            </div>
                            <form action="${pageContext.request.contextPath}/admin/listSalary" method="POST"
                                  style="float: left;padding: 16px;width: 81.8%;">
                                <div class="row">
                                    <div class="col-12 col-sm-12 col-xs-12 col-md-12 col-lg-5">
                                        <label class="col-12 col-sm-2 col-xs-12  col-md-4 col-lg-4" style="margin-top: 8px;padding-left: 0px!important">Tên nhân viên:</label>
                                        <input type="text" name="tennv" class="col-12 col-sm-10 col-xs-12  col-md-7 col-lg-8 tennv" placeholder="Tên nhân viên..."
                                               style="border-radius: 5px!important;" value="${nameSearch}"/>
                                    </div>
                                    <div class="col-12 col-sm-12 col-xs-12 col-md-12 col-lg-7">
                                        <label class="col-12 col-sm-2 col-xs-12  col-md-4 col-lg-2" style="margin-top: 8px;float:left;padding-left: 0px">Ngày:</label>
                                        <div class="ui calendar" id="rangestart">
                                            <input class="col-12 col-sm-2 col-xs-12  col-md-4 col-lg-6" type="text" class="datepicker"
                                                   style="border-radius: 5px!important;height: 35px!important;"
                                                   id="startDate"
                                                   name="date"
                                                   placeholder="Ngày">
                                        </div>
                                        <button class="col-12 col-sm-2 col-xs-4  col-md-4 col-lg-3" type="submit" style="font-family: Tahoma;float:left;">
                                            Tìm kiếm
                                        </button>
                                    </div>
                                </div>
                            </form>
                            <a href="${pageContext.request.contextPath}/admin/addAttendance">
                                <button class="btn btn-success col-12 col-sm-12 col-xs-12 col-md-4 col-lg-2"
                                        style="font-family: Lato,Helvetica Neue,Arial,Helvetica,sans-serif;float:right;margin-top: 12px;margin-right: 15px;">
                                    Thêm chấm công
                                </button>
                            </a>
                            <div class="panel-body">
                                <table class="table table-bordered table-hover">
                                    <thead>
                                    <tr style="text-align: center;border-bottom: 2px solid #dddddd;">
                                        <th>STT</th>
                                        <th>Tên nhân viên</th>
                                        <th>Ngày</th>
                                        <th>Thời gian bắt đầu</th>
                                        <th>Thời gian kết thúc</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${listAttendance}" var="listAttendance" varStatus="loop">
                                        <tr style="text-align: center;">
                                            <td>${loop.count}</td>
                                            <td>${listAttendance.employee.name}</td>
                                            <td><fmt:formatDate pattern="dd-MM-yyyy"
                                                                value="${listAttendance.date}"/></td>
                                            <td>${listAttendance.starttime}</td>
                                            <td>${listAttendance.endtime}</td>
                                        </tr>
                                    </c:forEach>
                                    <c:if test="${not empty searchFail}">
                                        <tr>
                                            <td colspan="3">Danh sách rỗng</td>
                                        </tr>
                                    </c:if>
                                    </tbody>
                                </table>
                                <div class="row">
                                    <div class="col-6 col-sm-6 col-xs-8 col-md-6 col-lg-10 page">
                                        <a>
                                            <button><<</button>
                                            <button><</button>
                                            <input type="text"
                                                   style="width: 30px;border-radius: 3px!important;margin-left: 3px;">
                                            of <input type="text"
                                                      style="width: 30px;border-radius: 3px!important;margin-left:2px;margin-right: 3px;"
                                                      readonly="true">
                                            <button>></button>
                                            <button>>></button>
                                        </a>
                                    </div>
                                    <div class="col-6 col-sm-6 col-xs-4 col-md-6 col-lg-2">
                                        <a style=float:right;">
                                            <label>View</label>
                                            <label>1</label>
                                            <label>-</label>
                                            <label>5</label>
                                            <label>of</label>
                                            <label>12</label>
                                        </a>
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
    var toDate = new Date();
    var date = toDate.getDate();
    var endDate = date;
    var month = toDate.getMonth() + 1;
    var endMonth = month + 1;
    var year = toDate.getFullYear();
    $('#startDate').val(month + "/" + date + "/" + year);
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
    $('.ui.dropdown').dropdown();
</script>
<script>
    $("#name_errors").hide().fadeIn(0).delay(2000).fadeOut(500);
</script>
<script>
    function loadModal(name, id) {
        $('.ui.modal').modal('show');
        $('#name').text(name);
        var $deleteConfirm = $('#deleteComfirm');
        document.getElementById('deleteComfirm').href = $deleteConfirm.attr('data-hrefbefore') + id;
    }
</script>
<script>
    function deleteTooltip() {
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