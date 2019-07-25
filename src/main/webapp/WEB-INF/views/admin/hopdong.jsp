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
</style>
<div class="container">
    <div class="row">
        <div class="col-lg-9">
            <div id="carouselExampleIndicators" class="carousel slide my-4"
                 data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#carouselExampleIndicators" data-slide-to="0"
                        class="active"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                </ol>
                <div class="container">
                    <div class="row">
                        <div class="col-xs-14 col-sm-12 col-md-11">
                            <div class="panel panel-warning">
                                <div class="panel-heading">
                                    <h2 class="panel-title">Quản lý hợp đồng</h2>
                                </div>
                                <a href="${pageContext.request.contextPath}/admin/add">
                                    <button class="btn btn-success"
                                            style="font-family: Lato,Helvetica Neue,Arial,Helvetica,sans-serif;float:right;margin: 10px 15px; ">
                                        Thêm hợp đồng
                                    </button>
                                </a>
                                <div class="panel-body">
                                    <table class="table table-bordered table-hover">
                                        <thead>
                                        <tr style="text-align: center;border-bottom: 2px solid #dddddd;">
                                            <th>STT</th>
                                            <th>Tên NV</th>
                                            <th>Ngày bắt đầu</th>
                                            <th>Ngày hết hạn</th>
                                            <th>Lương</th>
                                            <th class="fas fa-cogs" style="margin-top: 12px;"></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${listContract}" var="contract" varStatus="loop">
                                            <tr style="text-align: center;">
                                                <td>${loop.count}</td>
                                                <td>${contract.employee.name}</td>
                                                <td><fmt:formatDate pattern="dd-MM-yyyy"
                                                                    value="${contract.startday}"/></td>
                                                <td><fmt:formatDate pattern="dd-MM-yyyy"
                                                                    value="${contract.expirationday}"/></td>
                                                <td><fmt:formatNumber type="number" maxIntegerDigits="10"
                                                                      value="${contract.salary}"/></td>
                                                <td>
                                                    <a href="${pageContext.request.contextPath}/admin/update/${contract.id}">
                                                        <button class="btn btn-warning" data-toggle="tooltip"
                                                                title="Sửa hợp đồng">
                                                            <i class="fas fa-user-edit" aria-hidden="true"></i>
                                                        </button>
                                                    </a>
                                                    <a href="${pageContext.request.contextPath}/admin/remove/${contract.id}">
                                                        <button class="btn btn-danger" data-toggle="tooltip"
                                                                title="Xóa hợp đồng" onclick="deleteDialog()">
                                                            <i class="fa fa-trash" aria-hidden="true"></i>
                                                        </button>
                                                    </a></td>
                                            </tr>
                                        </c:forEach>
                                        <%--                                        <tr style="text-align: center;">--%>
                                        <%--                                            <td>2</td>--%>
                                        <%--                                            <td>Bùi Văn Tấn</td>--%>
                                        <%--                                            <td>10-05-2019</td>--%>
                                        <%--                                            <td>10-06-2019</td>--%>
                                        <%--                                            <td>formatNumberString(5000000)</td>--%>
                                        <%--                                            <td><a--%>
                                        <%--                                                    href="${pageContext.request.contextPath}/admin/suahopdong">--%>
                                        <%--                                                <button class="btn btn-warning" data-toggle="tooltip" title="Sửa hợp đồng" >--%>
                                        <%--                                                    <i class="fas fa-user-edit" aria-hidden="true"></i>--%>
                                        <%--                                                </button>--%>
                                        <%--                                            </a> <a href="${pageContext.request.contextPath}/admin/remove/${item.id_dm}">--%>
                                        <%--                                                <button class="btn btn-danger" data-toggle="tooltip" title="Xóa hợp đồng" onclick="deleteDialog()">--%>
                                        <%--                                                    <i class="fa fa-trash" aria-hidden="true"></i>--%>
                                        <%--                                                </button>--%>
                                        <%--                                            </a></td>--%>
                                        <%--                                        </tr>--%>
                                        </tbody>
                                    </table>
                                    <%--                                    <nav aria-label="Page navigation example">--%>
                                    <%--                                        <ul class="pagination justify-content-center">--%>
                                    <%--                                            <li class="page-item disabled"><a class="page-link"--%>
                                    <%--                                                                              href="#" tabindex="-1">Previous</a></li>--%>
                                    <%--                                            <li class="page-item"><a class="page-link" href="#">1</a></li>--%>
                                    <%--                                            <li class="page-item"><a class="page-link" href="#">2</a></li>--%>
                                    <%--                                            <li class="page-item"><a class="page-link" href="#">3</a></li>--%>
                                    <%--                                            <li class="page-item"><a class="page-link" href="#">Next</a>--%>
                                    <%--                                            </li>--%>
                                    <%--                                        </ul>--%>
                                    <%--                                    </nav>--%>
                                </div>

                            </div>
                        </div>
                    </div>

                </div>
            </div>


        </div>


    </div>


</div>
<%--<script>--%>
<%--    function getValueFromInputNumberRequired(inputId) {--%>
<%--        return $('#' + inputId).val().replace(/,/g, '')--%>
<%--    }--%>
<%--</script>--%>
<%--<script type="text/javascript">--%>
<%--    function formatNumberString(numberStr) {--%>
<%--        return numberStr.replace(/(?=(?:\d{3})+$)(?!^)/g, ',');--%>
<%--    }--%>
<%--</script>--%>

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