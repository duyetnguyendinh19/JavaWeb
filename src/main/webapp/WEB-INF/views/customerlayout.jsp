<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${title}</title>
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/img/yotel-logo.png"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/daterangepicker.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/semantic.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/calendar.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css"
          integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">

</head>
<body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/daterangepicker.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/semantic.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar.min.js"></script>
<div class="container">
    <jsp:include page="${param.view}"/>
</div>

</body>
</html>