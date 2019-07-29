<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Admin</title>
</head>
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
<body>
<form action="${pageContext.request.contextPath}/admin/upload" method="post" enctype="multipart/form-data">
<%--    <div class="wrap-custom-file">--%>
<%--        <input type="file" name="avatar" class="imgUpload" id="avatar" accept=".gif,.jpg,.png,.jpeg"/>--%>
<%--        <label for="avatar" style="display:flex;justify-content:center;align-items:center;" class="imageFile">--%>
<%--        <span id="loading-add-image-span" class="fa fa-spinner fa-spin"--%>
<%--              style="display:none;margin-top:0!important"></span>--%>
<%--            <span id="chooseImage" style="margin: 0px;">Chọn ảnh</span>--%>
<%--            <span class="nameFile"--%>
<%--                  style="position:absolute;bottom:0;left:0;width:100%;height:26px;padding:0.3rem;font-size:1.1rem;color:#6a0700;background-color: rgba(255, 255, 255, 0.7);height:28px;"></span>--%>
<%--        </label>--%>
<%--    </div>--%>
    <div class="wrap-custom-file" style="margin-top: 15px;margin-left: 50px;">
        <input type="file" name="avatar" class="imgUpload" id="avatar" accept=".gif,.jpg,.png,.jpeg" />
        <label for="avatar" id="avatar-background"
               style="width: 190px;height: 160px;"
               :style="{'background-image' : 'url(' + dataValue.avatar +')'}">
            <span id="chooseImage">Chọn ảnh</span>
        </label>
    </div>
<%--    <div class="inline-block">--%>
<%--        <div class="ui calendar" id="rangestart">--%>
<%--            <input type="text" class="datepicker" id="startDate" name="startDate"/>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div class="inline-block">--%>
<%--        <div class="ui calendar" id="rangeend">--%>
<%--            <input type="text" class="datepicker" id="endDate" name="rangeend"/>--%>
<%--        </div>--%>
<%--    </div>--%>
    <br><br>
    <button>submit</button>
</form>
</body>
<script>
        let that = this;
        $('input[type="file"].imgUpload').each(function(){
            let $file = $(this),
                $label = $file.next('label'),
                $labelText = $label.find('span'),
                labelDefault = $labelText.text();
            $file.on('change', function(event){
                let fileName = $file.val().split( '\\' ).pop(),
                    tmppath = URL.createObjectURL(event.target.files[0]);
                if( fileName ){
                    $label
                        .addClass('file-ok')
                        .css('background-image', 'url(' + tmppath + ')');
                    $labelText.removeClass('red');
                    $labelText.text(fileName);
                    that.dataValueClone.avatar = fileName;
                }else{
                    $label.removeClass('file-ok');
                    $labelText.text(labelDefault);
                }
            });
        });
</script>
<%--<script type="text/javascript">--%>
<%--    $('#avatar').on('change', function (event) {--%>
<%--        var that = this;--%>
<%--        $('#chooseImage').css('display', "none");--%>
<%--        var reader = new FileReader();--%>
<%--        var objectResult = {};--%>
<%--        var dataFile = this.files[0];--%>
<%--        reader.readAsDataURL(dataFile);--%>
<%--        reader.onload = function () {--%>
<%--            setTimeout(function () {--%>
<%--                objectResult.fileName = dataFile.name;--%>
<%--                var readerResult = reader.result;--%>
<%--                objectResult.content = readerResult.split(',')[1];--%>
<%--                var tmppath = URL.createObjectURL(dataFile);--%>
<%--                $('#loading-add-image-span').remove();--%>
<%--                $('#chooseImage').remove();--%>
<%--                $('.imageFile').css('background-image', 'url("' + readerResult + '")');--%>
<%--                $('.nameFile').text(dataFile.name);--%>
<%--            }, 200);--%>
<%--        };--%>
<%--        reader.onerror = function (error) {--%>
<%--            console.log('Error: ', error);--%>
<%--        };--%>
<%--    })--%>
<%--</script>--%>
<script>
    var toDate = new Date();
    var date = toDate.getDate();
    var endDate = date + 7;
    var month = toDate.getMonth() + 1;
    var year = toDate.getFullYear();
    $('#startDate').val(month + "/" + date + "/" + year);
    $('#endDate').val(month + "/" + endDate + "/" + year);
    $('#rangestart').calendar({
        type: 'date',
        endCalendar: $('#rangeend'),
        text: {
            days: ['CN', 'T2', 'T3', 'T4', 'T5', 'T6', 'T7'],
            months: ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6', 'Tháng 7', 'Tháng 8', 'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12'],
        },
        formatter: {
            date: function (date, settings) {
                if (!date) return '';
                var day = date.getDate();
                var month = date.getMonth() + 1;
                var year = date.getFullYear();
                return day + '/' + month + '/' + year;
            }
        },
        today: true,
    });
    $('#rangeend').calendar({
        type: 'date',
        startCalendar: $('#rangestart'),
        text: {
            days: ['CN', 'T2', 'T3', 'T4', 'T5', 'T6', 'T7'],
            months: ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6', 'Tháng 7', 'Tháng 8', 'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12']
        },
        formatter: {
            date: function (date, settings) {
                if (!date) return '';
                var day = date.getDate();
                var month = date.getMonth() + 1;
                var year = date.getFullYear();
                return day + '/' + month + '/' + year;
            }
        },
        today: true,
        popupOptions: {
            position: 'bottom right',
            lastResort: 'bottom right',
            prefer: 'opposite',
            hideOnScroll: false
        }
    });
</script>
</html>


