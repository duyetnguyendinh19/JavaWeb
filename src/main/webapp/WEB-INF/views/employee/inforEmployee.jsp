<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee</title>
</head>
<body>
	<div id="navbar" class="navbar navbar-default">


		<div class="navbar-container" id="navbar-container">
			<button type="button" class="navbar-toggle menu-toggler pull-left"
				id="menu-toggler" data-target="#sidebar">
				<span class="sr-only">Toggle sidebar</span> <span class="icon-bar"></span>

				<span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>

			<div class="navbar-header pull-left">
				<a href="${pageContext.request.contextPath}/employee/inforEmployee"
					class="navbar-brand"> <small><img alt="Admin"
						src="${pageContext.request.contextPath}/images/boss.png"
						width="20px" height="20px"> <font style="margin-left: 10px"
						size="4px">Giao diện nhân viên</font> </small>
				</a>
			</div>

			<div class="navbar-buttons navbar-header pull-right"
				role="navigation">
				<ul class="nav ace-nav">

					<li class="light-blue"><a data-toggle="dropdown"
						class="dropdown-toggle"> <img class="nav-user-photo"
							src="${avatar}" /> <span class="user-info"> <small>Chào,</small>
								${account.username}
						</span>
					</a></li>

					<li class="light-red"><a
						href="${pageContext.request.contextPath}/logout">Đăng xuất</a></li>
				</ul>
			</div>
		</div>
		<!-- /.navbar-container -->
	</div>

	<div class="main-container" id="main-container"></div>
</body>
</html>