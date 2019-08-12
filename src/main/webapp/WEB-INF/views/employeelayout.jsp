<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${pageContext.request.contextPath}/">

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>Employee</title>
<link rel="icon"
	href="${pageContext.request.contextPath}/images/boss.png">

<meta name="description" content="overview &amp; stats" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

<link rel="stylesheet" href="templates/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="templates/font-awesome/4.2.0/css/font-awesome.min.css" />

<link rel="stylesheet" href="templates/fonts/fonts.googleapis.com.css" />

<link rel="stylesheet" href="templates/css/ace.min.css"
	class="ace-main-stylesheet" id="main-ace-style" />

<link rel="stylesheet" href="templates/css/all.min.css" />
<link rel="stylesheet" href="templates/css/jquery-ui.min.css" />
<link rel="stylesheet" href="templates/css/semantic.min.css" />
<link rel="stylesheet" href="templates/css/calendar.min.css" />

<script src="templates/js/bootstrap.min.js"></script>
<script src="templates/js/ace-extra.min.js"></script>
<script src="templates/js/all.min.js"></script>
<script src="templates/js/jquery.min.js"></script>
<script src="templates/js/jquery-ui.min.js"></script>
<script src="templates/js/semantic.min.js"></script>
<script src="templates/js/calendar.min.js"></script>
<style>
/*CSS Ảnh*/
.wrap-custom-file {
	position: relative;
	display: inline-block;
	width: 150px;
	height: 150px;
	margin: 10px 10px 5px 20px;
	text-align: center;
}

.wrap-custom-file input[type="file"] {
	position: absolute;
	top: 0;
	left: 0;
	width: 2px;
	height: 2px;
	overflow: hidden;
	opacity: 0;
}

.wrap-custom-file label {
	z-index: 1;
	position: absolute;
	left: 0;
	top: 0;
	bottom: 0;
	right: 0;
	width: 100%;
	overflow: hidden;
	padding: 0 0.5rem;
	cursor: pointer;
	background-color: #fff;
	border-radius: 4px;
	-webkit-transition: -webkit-transform 0.4s;
	transition: -webkit-transform 0.4s;
	transition: transform 0.4s;
	transition: transform 0.4s, -webkit-transform 0.4s;
}

.wrap-custom-file label span {
	display: block;
	margin-top: 2rem;
	font-size: 1.4rem;
	color: #777;
	-webkit-transition: color 0.4s;
	transition: color 0.4s;
}

.wrap-custom-file label:hover {
	-webkit-transform: translateY(-0.5rem);
	transform: translateY(-0.5rem);
	transition: transform .5s, box-shadow .5s;
	box-shadow: 2px 2px 5px #b1b1b1;
}

.wrap-custom-file label:hover span {
	color: #333;
}

.wrap-custom-file label {
	background-size: cover;
	background-position: center;
	border: 1px dashed cornflowerblue;
	border-radius: 10px;
}

.wrap-custom-file label.file-ok {
	border: none;
	box-shadow: 2px 2px 5px #b1b1b1;
}

.wrap-custom-file label.file-ok span {
	position: absolute;
	bottom: 0;
	left: 0;
	height: 27px;
	width: 100%;
	padding: 0.3rem;
	font-size: 1.1rem;
	color: #6a0700;
	background-color: rgba(255, 255, 255, 0.7);
}

/*kết thúc CSS ảnh*/
</style>
<body class="no-skin">


	<jsp:include page="${param.view}" />



</body>
</html>