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
    <title>Thống kê lương</title>
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
        margin-right: 15px;
    }

    .ui.black.deny.button {
        width: 25%;
        height: 40px;
    }

    @media ( min-width: 1200px) {
        .col-lg-3 {
            margin-left: 5px;
        }
    }

    @media ( max-width: 500px) {
        .col-xs-4, .col-xs-6 {
            margin-top: 8px !important;
        }

        form {
            width: 100% !important;
        }

        .btn.btn-success {
            margin-bottom: 15px;
            width: 92% !important;
        }
    }
</style>
<c:if test="${not empty searchFail}">
    <label class="alert alert-danger" id="name_errors"
           style="margin-left: 25px;width: 91.3%;color: red;font-size: 18px;">${searchFail}</label>
</c:if>
<c:if test="${not empty alertWriteExcel}">
    <label class="alert alert-success" id="name_errors"
           style="margin-left: 25px;width: 91.3%;color: red;font-size: 18px;">${alertWriteExcel}</label>
</c:if>
<div class="container">
    <div class="row">
        <div class="col-lg-9">
            <div class="container">
                <div class="row">
                    <div class="col-xs-14 col-sm-12 col-md-11">
                        <div class="panel panel-warning">
                            <div class="panel-heading">
                                <h2 class="panel-title">Thống kê lương</h2>
                            </div>
                            <form
                                    action="${pageContext.request.contextPath}/admin/listSalary"
                                    method="POST" style="float: left; padding: 16px; width: 90%;">
                                <div class="row">
                                    <div class="col-12 col-sm-12 col-xs-12 col-md-12 col-lg-4">
                                        <label class="col-12 col-sm-2 col-xs-12  col-md-4 col-lg-5"
                                               style="margin-top: 8px; padding-left: 0px !important">Tên
                                            nhân viên:</label> <input type="text" name="nameEmployee"
                                                                      class="col-12 col-sm-10 col-xs-12  col-md-7 col-lg-7 tennv"
                                                                      placeholder="Tên nhân viên..."
                                                                      style="border-radius: 5px !important;"
                                                                      value="${nameSearch}"/>
                                    </div>
                                    <div class="col-12 col-sm-12 col-xs-12 col-md-12 col-lg-8">
                                        <label class="col-12 col-sm-2 col-xs-12  col-md-4 col-lg-1"
                                               style="margin-top: 8px; float: left; padding-left: 0px;">Tháng:</label>
                                        <select
                                                class="ui dropdown col-12 col-sm-2 col-xs-12  col-md-3 col-lg-2"
                                                name="month" id="month"
                                                style="height: 35px; border-radius: 5px !important;">
                                            <option value="0" ${month == 0 ? 'selected' : ''}
                                            ${empty month ? 'selected' : '' }>Tất cả tháng
                                            </option>
                                            <option value="1" ${month == 1 ? 'selected' : ''}
                                            ${empty month ? 'selected' : '' }>Tháng 1
                                            </option>
                                            <option value="2" ${month == 2 ? 'selected' : ''}>Tháng
                                                2
                                            </option>
                                            <option value="3" ${month == 3 ? 'selected' : ''}>Tháng
                                                3
                                            </option>
                                            <option value="4" ${month == 4 ? 'selected' : ''}>Tháng
                                                4
                                            </option>
                                            <option value="5" ${month == 5 ? 'selected' : ''}>Tháng
                                                5
                                            </option>
                                            <option value="6" ${month == 6 ? 'selected' : ''}>Tháng
                                                6
                                            </option>
                                            <option value="7" ${month == 7 ? 'selected' : ''}>Tháng
                                                7
                                            </option>
                                            <option value="8" ${month == 8 ? 'selected' : ''}>Tháng
                                                8
                                            </option>
                                            <option value="9" ${month == 9 ? 'selected' : ''}>Tháng
                                                9
                                            </option>
                                            <option value="10" ${month == 10 ? 'selected' : ''}>Tháng
                                                10
                                            </option>
                                            <option value="11" ${month == 11 ? 'selected' : ''}>Tháng
                                                11
                                            </option>
                                            <option value="12" ${month == 12 ? 'selected' : ''}>Tháng
                                                12
                                            </option>
                                        </select>
                                        <div class="col-12 col-sm-12 col-xs-12 col-md-12 col-lg-4">
                                            <label class="col-12 col-sm-2 col-xs-12 col-md-4 col-lg-3"
                                                   style="margin-top: 8px; padding-left: 0px !important">Năm:</label>
                                            <input type="text" name="year" id="year"
                                                   class="col-12 col-sm-10 col-xs-12 col-md-7 col-lg-8 tennv"
                                                                          placeholder="Năm..."
                                                                          style="border-radius: 5px !important;"
                                                                          value="${year}"/>
                                        </div>
                                        <button class="col-12 col-sm-2 col-xs-4  col-md-4 col-lg-2"
                                                type="submit" style="font-family: Tahoma; float: left;">
                                            Tìm kiếm
                                        </button>
                                    </div>
                                </div>
                            </form>
                            <a href="${pageContext.request.contextPath}/admin/writeExcel">
                                <button
                                        class="btn btn-success col-12 col-sm-12 col-xs-12 col-md-4 col-lg-1"
                                        style="float: right; margin-top: 12px; margin-right: 15px; padding-left: 5px; border-radius: 5px; height: 40px !important;">
                                    Xuất excel
                                </button>
                            </a>
                            <div class="panel-body">
                                <table class="table table-bordered table-hover">
                                    <thead>
                                    <tr
                                            style="text-align: center; border-bottom: 2px solid #dddddd;">
                                        <th>Mã nhân viên</th>
                                        <th>Tên nhân viên</th>
                                        <th>Tháng</th>
                                        <th>Năm</th>
                                        <th>Số công</th>
                                        <th>Ngày nghỉ</th>
                                        <th>Tổng lương</th>
                                        <th>Ngày tạo</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${listSalary}" var="listSalary"
                                               varStatus="loop">
                                        <tr style="text-align: center;">
                                            <td>${listSalary.idEmployee}</td>
                                            <td>${listSalary.nameEmployee}</td>
                                            <td>Tháng ${listSalary.month}</td>
                                            <td>Năm ${listSalary.year}</td>
                                            <td>${listSalary.count}</td>
                                            <td>${listSalary.dayoff}</td>
                                            <td><fmt:formatNumber type="number"
                                                                  maxIntegerDigits="10"
                                                                  value="${listSalary.total}"/></td>
                                            <td><fmt:formatDate pattern="dd-MM-yyyy"
                                                                value="${listSalary.createDate}"/></td>
                                        </tr>
                                    </c:forEach>
                                    <c:if test="${not empty searchFail}">
                                        <tr>
                                            <td colspan="8">Danh sách rỗng</td>
                                        </tr>
                                    </c:if>
                                    </tbody>
                                </table>
                                <div class="row">
                                    <div class="col-6 col-sm-6 col-xs-9 col-md-6 col-lg-8 page">
                                        <button onclick="firstpage()"><<</button>
                                        <button onclick="previous()"><</button>
                                        <input type="number" id="page" value="1"
                                               style="width: 40px; border-radius: 3px !important; margin-left: 3px;">
                                        of <input type="text" id="totalPage" value="${totalPage}"
                                                  style="width: 30px; border-radius: 3px !important; margin-left: 2px; margin-right: 3px;"
                                                  readonly="readonly">
                                        <button onclick="next()">></button>
                                        <button onclick="lastpage()">>></button>

                                    </div>
                                    <div class="col-6 col-sm-6 col-xs-4 col-md-6 col-lg-4">
                                        <a style="float: right;"> <label>View</label> <label>${firstSalary}</label>
                                            <label>-</label> <label>${lastSalary}</label> <label>of</label>
                                            <label>${totalSalary}</label>
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
    if (isFormatNumber !== false) {
        $('#year').on('keyup', function () {
            var formattedVal = formatNumberString($('#year').val().replace(/,/g, ''));
            $('#salary').val(formattedVal);
        });
    }
    $('#year').on("input keydown keyup mousedown mouseup select contextmenu drop", function () {
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
<script>
    $('.ui.dropdown').dropdown();
    var date = new Date();
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