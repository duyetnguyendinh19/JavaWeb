<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="${pageContext.request.contextPath}/">

    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>Admin</title>
    <link rel="icon"
          href="${pageContext.request.contextPath}/images/boss.png"/>

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
    <link rel="stylesheet"
          href="templates/bootstrap-material-datetimepicker/css/bootstrap-material-datetimepicker.css"/>

    <script src="templates/js/moment.min.js"></script>
    <script src="templates/js/bootstrap.min.js"></script>
    <script src="templates/js/ace-extra.min.js"></script>
    <script src="templates/js/all.min.js"></script>
    <script src="templates/js/jquery.min.js"></script>
    <script src="templates/js/jquery-ui.min.js"></script>
    <script src="templates/js/semantic.min.js"></script>
    <script src="templates/js/calendar.min.js"></script>
    <script
            src="templates/bootstrap-material-datetimepicker/js/bootstrap-material-datetimepicker.js"></script>
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

        .changePass {
            color: white;
        }
		.changePass:hover{
			cursor: pointer;
		}

        tbody>tr>td{
            word-break: break-all;
        }

    </style>
<body class="no-skin">


<div id="navbar" class="navbar navbar-default">


    <div class="navbar-container" id="navbar-container">
        <button type="button" class="navbar-toggle menu-toggler pull-left"
                id="menu-toggler" data-target="#sidebar">
            <span class="sr-only">Toggle sidebar</span> <span class="icon-bar"></span>

            <span class="icon-bar"></span> <span class="icon-bar"></span>
        </button>

        <div class="navbar-header pull-left">
            <a href="${pageContext.request.contextPath}/admin/listDepartment" class="navbar-brand"> <small><img
                    alt="Admin"
                    src="${pageContext.request.contextPath}/images/boss.png"
                    width="20px" height="20px"> <font style="margin-left: 10px" size="4px">Quản lý nhân viên</font>
            </small>
            </a>
        </div>

        <div class="navbar-buttons navbar-header pull-right"
             role="navigation">
            <ul class="nav ace-nav">

                <li class="light-blue"><a data-toggle="dropdown"
                                          class="dropdown-toggle"> <img class="nav-user-photo"
                                                                        src="${avatar}"/> <span class="user-info">
								<small>Chào,</small> ${account.username}
						</span>
                </a></li>

                <li class="light-red"><a href="${pageContext.request.contextPath}/logout">Đăng xuất</a></li>
            </ul>
        </div>
    </div>
    <!-- /.navbar-container -->
</div>
<div class="main-container" id="main-container">


    <div id="sidebar" class="sidebar responsive">


        <ul class="nav nav-list">

            <li
                    class="${(fn:contains(requestScope['javax.servlet.forward.request_uri'] , 'Account')) ? 'active' : ''}">
                <a
                        href="${pageContext.request.contextPath}/admin/listAccount"> <i
                        class="menu-icon fa fa-list-alt"></i> <span class="menu-text">
							Quản lý tài khoản </span>
                </a> <b class="arrow"></b></li>

            <li
                    class="${(fn:contains(requestScope['javax.servlet.forward.request_uri'] , 'Department')) ? 'active' : ''}">
                <a
                        href="${pageContext.request.contextPath}/admin/listDepartment">
                    <i class="menu-icon fa fa-list-alt"></i> <span class="menu-text">
							Quản lý phòng ban </span>
                </a> <b class="arrow"></b></li>

            <li
                    class="${(fn:contains(requestScope['javax.servlet.forward.request_uri'] , 'Employee')) ? 'active' : ''}">
                <a
                        href="${pageContext.request.contextPath}/admin/listEmployee"> <i
                        class="menu-icon fa fa-list-alt"></i> <span class="menu-text">
							Quản lý nhân viên </span>
                </a> <b class="arrow"></b></li>

            <li
                    class="${(fn:contains(requestScope['javax.servlet.forward.request_uri'] , 'Contract')) ? 'active' : ''}">
                <a
                        href="${pageContext.request.contextPath}/admin/listContract"> <i
                        class="menu-icon fa fa-list-alt"></i> <span class="menu-text">
							Quản lý hợp đồng </span>
                </a> <b class="arrow"></b></li>
            <li
                    class="${(fn:contains(requestScope['javax.servlet.forward.request_uri'] , 'Bonus')) ? 'active' : ''}">
                <a
                        href="${pageContext.request.contextPath}/admin/listBonus"> <i
                        class="menu-icon fa fa-list-alt"></i> <span class="menu-text">
							Quản lý khen thưởng</span>
                </a> <b class="arrow"></b></li>
            <li
                    class="${(fn:contains(requestScope['javax.servlet.forward.request_uri'] , 'Discipline')) ? 'active' : ''}">
                <a
                        href="${pageContext.request.contextPath}/admin/listDiscipline">
                    <i class="menu-icon fa fa-list-alt"></i> <span class="menu-text">
							Quản lý kỷ luật </span>
                </a> <b class="arrow"></b></li>

            <li
                    class="${(fn:contains(requestScope['javax.servlet.forward.request_uri'] , 'Attendance')) ? 'active' : ''}">
                <a
                        href="${pageContext.request.contextPath}/admin/listAttendance">
                    <i class="menu-icon fa fa-list-alt"></i> <span class="menu-text">
							Quản lý công</span>
                </a> <b class="arrow"></b></li>

            <li
                    class="${(fn:contains(requestScope['javax.servlet.forward.request_uri'] , 'Salary')) ? 'active' : ''}">
                <a
                        href="${pageContext.request.contextPath}/admin/listSalary"> <i
                        class="menu-icon fa fa-list-alt"></i> <span class="menu-text">
							Thống kê lương </span>
                </a> <b class="arrow"></b></li>

			<li
					class="${(fn:contains(requestScope['javax.servlet.forward.request_uri'] , 'Password')) ? 'active' : ''}">
				<a
						href="${pageContext.request.contextPath}/admin/changePassword"> <i
						class="menu-icon fa fa-list-alt"></i> <span class="menu-text">
							Đổi mật khẩu </span>
				</a> <b class="arrow"></b></li>

            <!-- /.nav-list -->

            <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
                <i class="fa fa-angle-double-left"
                   data-icon1="fa fa-angle-double-left"
                   data-icon2="fa fa-angle-double-right"></i>
            </div>

        </ul>
    </div>

    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs" id="breadcrumbs">
                <ul class="breadcrumb">
                    <!-- 	<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Home</a>
                    </li>
                    <li class="active">Dashboard</li> -->
                </ul>
                <!-- /.breadcrumb -->

                <!-- 	<div class="nav-search" id="nav-search">
                    <form class="form-search">
                        <span class="input-icon"> <input type="text"
                            placeholder="Search ..." class="nav-search-input"
                            id="nav-search-input" autocomplete="off" /> <i
                            class="ace-icon fa fa-search nav-search-icon"></i>
                        </span>
                    </form>
                </div> -->
                <!-- /.nav-search -->
            </div>

            <div class="page-content">
                <div class="ace-settings-container" id="ace-settings-container">
                    <%--						<div class="btn btn-app btn-xs btn-warning ace-settings-btn"--%>
                    <%--							id="ace-settings-btn">--%>
                    <%--							<i class="ace-icon fa fa-cog bigger-130"></i>--%>
                    <%--						</div>--%>

                    <div class="ace-settings-box clearfix" id="ace-settings-box">
                        <div class="pull-left width-50">
                            <div class="ace-settings-item">
                                <div class="pull-left">
                                    <select id="skin-colorpicker" class="hide">
                                        <option data-skin="no-skin" value="#438EB9">#438EB9</option>
                                        <option data-skin="skin-1" value="#222A2D">#222A2D</option>
                                        <option data-skin="skin-2" value="#C6487E">#C6487E</option>
                                        <option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
                                    </select>
                                </div>
                                <span>&nbsp; Choose Skin</span>
                            </div>

                            <div class="ace-settings-item">
                                <input type="checkbox" class="ace ace-checkbox-2"
                                       id="ace-settings-navbar"/> <label class="lbl"
                                                                         for="ace-settings-navbar"> Fixed Navbar</label>
                            </div>

                            <div class="ace-settings-item">
                                <input type="checkbox" class="ace ace-checkbox-2"
                                       id="ace-settings-sidebar"/> <label class="lbl"
                                                                          for="ace-settings-sidebar"> Fixed
                                Sidebar</label>
                            </div>

                            <div class="ace-settings-item">
                                <input type="checkbox" class="ace ace-checkbox-2"
                                       id="ace-settings-breadcrumbs"/> <label class="lbl"
                                                                              for="ace-settings-breadcrumbs"> Fixed
                                Breadcrumbs</label>
                            </div>

                            <div class="ace-settings-item">
                                <input type="checkbox" class="ace ace-checkbox-2"
                                       id="ace-settings-rtl"/> <label class="lbl"
                                                                      for="ace-settings-rtl"> Right To Left
                                (rtl)</label>
                            </div>

                            <div class="ace-settings-item">
                                <input type="checkbox" class="ace ace-checkbox-2"
                                       id="ace-settings-add-container"/> <label class="lbl"
                                                                                for="ace-settings-add-container"> Inside
                                <b>.container</b>
                            </label>
                            </div>
                        </div>
                        <!-- /.pull-left -->

                        <div class="pull-left width-50">
                            <div class="ace-settings-item">
                                <input type="checkbox" class="ace ace-checkbox-2"
                                       id="ace-settings-hover"/> <label class="lbl"
                                                                        for="ace-settings-hover"> Submenu on
                                Hover</label>
                            </div>

                            <div class="ace-settings-item">
                                <input type="checkbox" class="ace ace-checkbox-2"
                                       id="ace-settings-compact"/> <label class="lbl"
                                                                          for="ace-settings-compact"> Compact
                                Sidebar</label>
                            </div>

                            <div class="ace-settings-item">
                                <input type="checkbox" class="ace ace-checkbox-2"
                                       id="ace-settings-highlight"/> <label class="lbl"
                                                                            for="ace-settings-highlight"> Alt. Active
                                Item</label>
                            </div>
                        </div>
                        <!-- /.pull-left -->
                    </div>
                    <!-- /.ace-settings-box -->
                </div>
                <!-- /.ace-settings-container -->

                <div class="page-header">
                    <!-- <h1>
                        Dashboard <small><i
                            class="ace-icon fa fa-angle-double-right"></i> </small>
                    </h1> -->
                </div>

                <div class="container">
                    <jsp:include page="${param.view}"/>
                </div>
                <!-- /.page-header -->

                <div class="row">
                    <div class="col-xs-12"></div>
                    <!-- /.col -->
                </div>
                <!-- /.row -->
            </div>
            <!-- /.page-content -->
        </div>

    </div>
    <!-- /.main-content -->

    <a href="#" id="btn-scroll-up"
       class="btn-scroll-up btn btn-sm btn-inverse"> <i
            class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
    </a>

</div>

<script type="text/javascript">
    try {
        ace.settings.check('breadcrumbs', 'fixed')
    } catch (e) {
    }
</script>
<script type="text/javascript">
    try {
        ace.settings.check('sidebar', 'collapsed')
    } catch (e) {
    }
</script>

<script type="text/javascript">
    try {
        ace.settings.check('main-container', 'fixed')
    } catch (e) {
    }
</script>

<%--<script src="templates/js/jquery.2.1.1.min.js"></script>--%>

<!-- <![endif]-->

<!--[if IE]>
<script src="templates/js/jquery.1.11.1.min.js"></script>
<![endif]-->

<!--[if !IE]> -->
<script type="text/javascript">
    window.jQuery
    || document.write("<script src='templates/js/jquery.min.js'>"
        + "<" + "/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
    window.jQuery || document.write("<script src='templates/js/jquery1x.min.js'>" + "<" + "/script>");
</script>
<![endif]-->
<script type="text/javascript">
    if ('ontouchstart' in document.documentElement)
        document
            .write("<script src='templates/js/jquery.mobile.custom.min.js'>"
                + "<" + "/script>");
</script>
<%--<script src="templates/js/bootstrap.min.js"></script>--%>

<!-- page specific plugin scripts -->

<!--[if lte IE 8]>
<script src="templates/js/excanvas.min.js"></script>
<![endif]-->
<%--<script src="templates/js/jquery-ui.custom.min.js"></script>--%>
<%--<script src="templates/js/jquery.ui.touch-punch.min.js"></script>--%>
<%--<script src="templates/js/jquery.easypiechart.min.js"></script>--%>
<%--<script src="templates/js/jquery.sparkline.min.js"></script>--%>
<script src="templates/js/jquery.flot.min.js"></script>
<script src="templates/js/jquery.flot.pie.min.js"></script>
<script src="templates/js/jquery.flot.resize.min.js"></script>

<!-- ace scripts -->
<script src="templates/js/ace-elements.min.js"></script>
<script src="templates/js/ace.min.js"></script>

<!-- inline scripts related to this page -->
<script type="text/javascript">
    try {
        ace.settings.check('navbar', 'fixed')
    } catch (e) {
    }
</script>
<script type="text/javascript">
    jQuery(function ($) {
        $('.easy-pie-chart.percentage')
            .each(
                function () {
                    var $box = $(this).closest('.infobox');
                    var barColor = $(this).data('color')
                        || (!$box.hasClass('infobox-dark') ? $box
                                .css('color')
                            : 'rgba(255,255,255,0.95)');
                    var trackColor = barColor == 'rgba(255,255,255,0.95)' ? 'rgba(255,255,255,0.25)'
                        : '#E2E2E2';
                    var size = parseInt($(this).data('size')) || 50;
                    $(this)
                        .easyPieChart(
                            {
                                barColor: barColor,
                                trackColor: trackColor,
                                scaleColor: false,
                                lineCap: 'butt',
                                lineWidth: parseInt(size / 10),
                                animate: /msie\s*(8|7|6)/
                                    .test(navigator.userAgent
                                        .toLowerCase()) ? false
                                    : 1000,
                                size: size
                            });
                })

        $('.sparkline').each(
            function () {
                var $box = $(this).closest('.infobox');
                var barColor = !$box.hasClass('infobox-dark') ? $box
                    .css('color') : '#FFF';
                $(this).sparkline('html', {
                    tagValuesAttribute: 'data-values',
                    type: 'bar',
                    barColor: barColor,
                    chartRangeMin: $(this).data('min') || 0
                });
            });

        //flot chart resize plugin, somehow manipulates default browser resize event to optimize it!
        //but sometimes it brings up errors with normal resize event handlers
        $.resize.throttleWindow = false;

        var placeholder = $('#piechart-placeholder').css({
            'width': '90%',
            'min-height': '150px'
        });
        var data = [{
            label: "social networks",
            data: 38.7,
            color: "#68BC31"
        }, {
            label: "search engines",
            data: 24.5,
            color: "#2091CF"
        }, {
            label: "ad campaigns",
            data: 8.2,
            color: "#AF4E96"
        }, {
            label: "direct traffic",
            data: 18.6,
            color: "#DA5430"
        }, {
            label: "other",
            data: 10,
            color: "#FEE074"
        }]

        function drawPieChart(placeholder, data, position) {
            $.plot(placeholder, data, {
                series: {
                    pie: {
                        show: true,
                        tilt: 0.8,
                        highlight: {
                            opacity: 0.25
                        },
                        stroke: {
                            color: '#fff',
                            width: 2
                        },
                        startAngle: 2
                    }
                },
                legend: {
                    show: true,
                    position: position || "ne",
                    labelBoxBorderColor: null,
                    margin: [-30, 15]
                },
                grid: {
                    hoverable: true,
                    clickable: true
                }
            })
        }

        drawPieChart(placeholder, data);

        /**
         we saved the drawing function and the data to redraw with different position later when switching to RTL mode dynamically
         so that's not needed actually.
         */
        placeholder.data('chart', data);
        placeholder.data('draw', drawPieChart);

        //pie chart tooltip example
        var $tooltip = $(
            "<div class='tooltip top in'><div class='tooltip-inner'></div></div>")
            .hide().appendTo('body');
        var previousPoint = null;

        placeholder.on('plothover', function (event, pos, item) {
            if (item) {
                if (previousPoint != item.seriesIndex) {
                    previousPoint = item.seriesIndex;
                    var tip = item.series['label'] + " : "
                        + item.series['percent'] + '%';
                    $tooltip.show().children(0).text(tip);
                }
                $tooltip.css({
                    top: pos.pageY + 10,
                    left: pos.pageX + 10
                });
            } else {
                $tooltip.hide();
                previousPoint = null;
            }

        });

        /////////////////////////////////////
        $(document).one('ajaxloadstart.page', function (e) {
            $tooltip.remove();
        });

        var d1 = [];
        for (var i = 0; i < Math.PI * 2; i += 0.5) {
            d1.push([i, Math.sin(i)]);
        }

        var d2 = [];
        for (var i = 0; i < Math.PI * 2; i += 0.5) {
            d2.push([i, Math.cos(i)]);
        }

        var d3 = [];
        for (var i = 0; i < Math.PI * 2; i += 0.2) {
            d3.push([i, Math.tan(i)]);
        }

        var sales_charts = $('#sales-charts').css({
            'width': '100%',
            'height': '220px'
        });
        $.plot("#sales-charts", [{
            label: "Domains",
            data: d1
        }, {
            label: "Hosting",
            data: d2
        }, {
            label: "Services",
            data: d3
        }], {
            hoverable: true,
            shadowSize: 0,
            series: {
                lines: {
                    show: true
                },
                points: {
                    show: true
                }
            },
            xaxis: {
                tickLength: 0
            },
            yaxis: {
                ticks: 10,
                min: -2,
                max: 2,
                tickDecimals: 3
            },
            grid: {
                backgroundColor: {
                    colors: ["#fff", "#fff"]
                },
                borderWidth: 1,
                borderColor: '#555'
            }
        });

        $('#recent-box [data-rel="tooltip"]').tooltip({
            placement: tooltip_placement
        });

        function tooltip_placement(context, source) {
            var $source = $(source);
            var $parent = $source.closest('.tab-content')
            var off1 = $parent.offset();
            var w1 = $parent.width();

            var off2 = $source.offset();
            //var w2 = $source.width();

            if (parseInt(off2.left) < parseInt(off1.left)
                + parseInt(w1 / 2))
                return 'right';
            return 'left';
        }

        $('.dialogs,.comments').ace_scroll({
            size: 300
        });

        //Android's default browser somehow is confused when tapping on label which will lead to dragging the task
        //so disable dragging when clicking on label
        var agent = navigator.userAgent.toLowerCase();
        if ("ontouchstart" in document && /applewebkit/.test(agent)
            && /android/.test(agent))
            $('#tasks').on('touchstart', function (e) {
                var li = $(e.target).closest('#tasks li');
                if (li.length == 0)
                    return;
                var label = li.find('label.inline').get(0);
                if (label == e.target || $.contains(label, e.target))
                    e.stopImmediatePropagation();
            });

        $('#tasks').sortable({
            opacity: 0.8,
            revert: true,
            forceHelperSize: true,
            placeholder: 'draggable-placeholder',
            forcePlaceholderSize: true,
            tolerance: 'pointer',
            stop: function (event, ui) {
                //just for Chrome!!!! so that dropdowns on items don't appear below other items after being moved
                $(ui.item).css('z-index', 'auto');
            }
        });
        $('#tasks').disableSelection();
        $('#tasks input:checkbox').removeAttr('checked').on('click',
            function () {
                if (this.checked)
                    $(this).closest('li').addClass('selected');
                else
                    $(this).closest('li').removeClass('selected');
            });

        //show the dropdowns on top or bottom depending on window height and menu position
        $('#task-tab .dropdown-hover').on('mouseenter', function (e) {
            var offset = $(this).offset();

            var $w = $(window)
            if (offset.top > $w.scrollTop() + $w.innerHeight() - 100)
                $(this).addClass('dropup');
            else
                $(this).removeClass('dropup');
        });

    })
</script>
<script type="text/javascript">
    try {
        ace.settings.check('sidebar', 'fixed')
    } catch (e) {
    }
</script>

</body>
</html>