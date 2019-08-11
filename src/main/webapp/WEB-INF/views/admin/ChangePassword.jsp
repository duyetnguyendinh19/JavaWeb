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

    .ui.modal {
        margin-top: 50px;
        width: 28%;
        height: 30%;
        margin-left: auto;
        margin-right: auto;
    }

    form button {
        height: 35px;
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
        height: 35px;
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

    .ui.black.deny.button {
        width: 25%;
        height: 40px;
    }

    @media ( min-width: 1200px) {
        .col-lg-2 {
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

        .table.table-bordered.table-hover {
            width: 80%;
        }
    }
</style>
<c:if test="${not empty success}">
    <label class="alert alert-success" id="name_errors"
           style="margin-left: 25px; width: 91.3%; color: red; font-size: 18px;">${success}</label>
</c:if>
<div class="container">
    <div class="row">
        <div class="col-lg-9">
            <div class="container">
                <div class="row">
                    <div class="col-xs-14 col-sm-12 col-md-5">
                        <div class="panel panel-warning">
                            <div class="panel-heading">
                                <h2 class="panel-title">Đổi mật khẩu</h2>
                            </div>
                            <div class="panel-body">
                                <form action="${pageContext.request.contextPath}/admin/resetPassword" method="POST">
                                    <label>Mật khẩu cũ</label>
                                    <input type="password" name="oldPass" class="form-control" value="${oldPass}"/>
                                    <label style="color: red;margin-top: 5px;">${errorOldPass}</label>
                                    <br>
                                    <label>Mật khẩu mới</label>
                                    <input type="password" name="newPass" class="form-control" value="${newPass}"/>
                                    <label style="color: red;margin-top: 5px;">${errorNewPass}</label>
                                    <br>
                                    <label>Nhập lại mật khẩu</label>
                                    <input type="password" name="confirmPass" class="form-control" value="${confirmPass}"/>
                                    <label style="color: red;margin-top: 5px;float:left;width: 100%;" >${errorComfirmPass}</label>
                                    <button class="btn btn-success" style="margin-top: 5px;height:42px;float:left;">Lưu</button>
                                </form>
                                <button class="btn btn-grey" style="margin-top: 5px;margin-left: 5px;" id="clear">Làm mới</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $("#name_errors").hide().fadeIn(0).delay(1500).fadeOut(500);
</script>
<script>
    if($('#name_errors').text() != null && $('#name_errors').text() != ""){
        setTimeout(function(){
            window.location.assign('/Manager/logout');
        },2000);
    }
</script>
<script>
    $('#clear').on('click',function(){
        $('.form-control').val("");
    })
</script>
</body>
</html>