<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="${pageContext.request.contextPath}/">

    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>Admin</title>
    <link rel="icon" href="${pageContext.request.contextPath}/images/boss.png">

    <meta name="description" content="overview &amp; stats"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>

    <link rel="stylesheet" href="templates/css/bootstrap.min.css"/>
    <link rel="stylesheet"
          href="templates/font-awesome/4.2.0/css/font-awesome.min.css"/>

    <link rel="stylesheet" href="templates/fonts/fonts.googleapis.com.css"/>

    <link rel="stylesheet" href="templates/css/ace.min.css"
          class="ace-main-stylesheet" id="main-ace-style"/>

    <link rel="stylesheet" href="templates/css/all.min.css"/>
    <link rel="stylesheet" href="templates/css/jquery-ui.min.css"/>
    <link rel="stylesheet" href="templates/css/semantic.min.css"/>
    <link rel="stylesheet" href="templates/css/calendar.min.css"/>

    <script src="templates/js/bootstrap.min.js"></script>
    <script src="templates/js/ace-extra.min.js"></script>
    <script src="templates/js/all.min.js"></script>
    <script src="templates/js/jquery.min.js"></script>
    <script src="templates/js/jquery-ui.min.js"></script>
    <script src="templates/js/semantic.min.js"></script>
    <script src="templates/js/calendar.min.js"></script>

<body class="no-skin">
<div class="container">
    <jsp:include page="${param.view}"/>
</div>
</body>
</html>