<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%-- <%@include file="/common/taglib.jsp" %> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>Login Page - Ace Admin</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/index.css"/>

    <meta name="description" content="User login page"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>

    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="templates/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="templates/font-awesome/4.2.0/css/font-awesome.min.css"/>

    <!-- text fonts -->
    <link rel="stylesheet" href="templates/fonts/fonts.googleapis.com.css"/>

    <!-- ace styles -->
    <link rel="stylesheet" href="templates/css/ace.min.css"/>

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="assets/css/ace-part2.min.css"/>
    <![endif]-->
    <link rel="stylesheet" href="templates/css/ace-rtl.min.css"/>

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="assets/css/ace-ie.min.css"/>
    <![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

    <!--[if lt IE 9]>
    <script src="assets/js/html5shiv.min.js"></script>
    <script src="assets/js/respond.min.js"></script>
    <![endif]-->
</head>
<style>
    .main-container:before {
        width: 85%;
    }
	#showRegister:hover{
		text-decoration: underline;
	}
	input[type=text],input[type=password],input[type=email]{
		height: 40px;
		border-radius: 5px!important;
	}
</style>
<body class="login-layout">
<div class="main-container" style="width: 100%;margin-top: 12%;">
    <div class="main-content">
        <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
                <div class="login-container">
                    <div class="center">
                       	<img alt="Admin" src="${pageContext.request.contextPath}/images/boss.png"  width="50px" height="50px" />
                        <h4 class="blue" id="id-company-text">&copy; Quản lý nhân viên</h4>
                    </div>

                    <div class="space-6"></div>

                    <div class="position-relative">
                        <div id="login" class="login-box visible widget-box no-border">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <h4 class="header blue lighter bigger">
                                        <i class="ace-icon fa fa-coffee green"></i>
                                        Vui lòng nhập thông tin đăng nhập
                                    </h4>

                                    <div class="space-6"></div>

                                    <form>
                                        <fieldset>
                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control"
                                                                   placeholder="Tài khoản"/>
															<i class="ace-icon fa fa-user" style="width: 25px;margin-top: 9px;margin-right: 5px;"></i>
														</span>
                                            </label>

                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control"
                                                                   placeholder="Mật khẩu"/>
															<i class="ace-icon fa fa-lock" style="width: 25px;margin-top: 9px;margin-right: 5px;"></i>
														</span>
                                            </label>

                                            <div class="space"></div>

                                            <div class="clearfix">
                                                <label class="inline">
                                                    <span style="cursor: pointer;margin-top: 5px;float:left;color:blue;"
														  id="showRegister">Quên mật khẩu</span>
                                                </label>

                                                <button type="button"
                                                        class="width-35 pull-right btn btn-sm btn-primary">
                                                    <i class="ace-icon fa fa-key"></i>
                                                    <span class="bigger-110">Đăng nhập</span>
                                                </button>
                                            </div>

                                            <div class="space-4"></div>
                                        </fieldset>
                                    </form>

                                  <!--   <div class="social-or-login center">
												<span class="bigger-110" style="cursor: pointer; border: none;border-radius: 5px;color: red;background: white;
												font-size: 18px!important;font-weight: bold;">Đăng ký</span>
                                    </div> -->

                                    <div class="space-6"></div>


                                </div><!-- /.widget-main -->


                            </div><!-- /.widget-body -->
                        </div><!-- /.login-box -->

                        <div id="forgot" class="forgot-box widget-box no-border" style="display: none;">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <h4 class="header red lighter bigger">
                                        <i class="fa fa-key"></i>
                                       	Quên mật khẩu
                                    </h4>

                                    <div class="space-6"></div>
                                    <p>
                                        Vui lòng nhập email để nhận lại mật khẩu
                                    </p>

                                    <form>
                                        <fieldset>
                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="email" class="form-control"
                                                                   placeholder="Email"/>
															<i class="ace-icon fa fa-envelope" style="width: 25px;margin-top: 10px;margin-right: 5px;"></i>
														</span>
                                            </label>

                                            <div class="clearfix">
                                                <button type="button" class="width-35 pull-right btn btn-sm btn-danger">
                                                    <i class="ace-icon fa fa-lightbulb-o" ></i>
                                                    <span class="bigger-110">Gửi!</span>
                                                </button>
                                            </div>
                                        </fieldset>
                                    </form>
                                </div><!-- /.widget-main -->

                                <div class="toolbar center">
                                    <span id="showLogin" class="back-to-login-link" style="cursor: pointer">
                                        Quay lại đăng nhập
                                        <i class="ace-icon fa fa-arrow-right"></i>
                                    </span>
                                </div>
                            </div><!-- /.widget-body -->
                        </div><!-- /.forgot-box -->


                    </div><!-- /.position-relative -->
                </div>
            </div><!-- /.col -->
        </div><!-- /.row -->
    </div><!-- /.main-content -->
</div><!-- /.main-container -->

<script>
    $('#showRegister').on('click', function () {
        $('#forgot').css('display', 'block');
        $('#login').css('display', 'none');
    })
</script>
<script>
	$('#showLogin').on('click', function () {
		$('#forgot').css('display', 'none');
		$('#login').css('display', 'block');
	})
</script>
</body>
</html>
