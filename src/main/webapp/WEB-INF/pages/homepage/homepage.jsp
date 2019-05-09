<%--
  Created by IntelliJ IDEA.
  User: Kaier
  Date: 2019/4/26
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <script type="text/javascript" src="../../js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="../../js/jquery.bxslider.js"></script>

    <title>主页</title>
    <link href="../../css/homepage/jquery.bxslider.css" rel="stylesheet" type="text/css">
    <style>
        p{ width:100%; height:40px; font-size:20px; color:#333; line-height:40px; text-align:center; font-family:"΢���ź�"}
    </style>
</head>

<body>
<!-- ---------------------------------slider7--------------------------------------------- -->
<!-- <p>slider7(infiniteLoop hideControlOnEnd)</p> -->
<div class="slider7">
    <div class="slide"><a href="../../salary/getUrlPage?cid=${cid}"><img src="../../images/homepage/001.jpg"></a></div>
    <div class="slide"><a href="../../employee/getNews"><img src="../../images/homepage/003.jpg"></a></div>
    <div class="slide"><a href="../../attendance/getReport?cid=${cid}"><img src="../../images/homepage/002.jpg"></a></div>
    <div class="slide"><a><img src="../../images/homepage/003.jpg"></a></div>
</div>
<div class="m-icon-nav f-cb">
    <div>
        <li>
            <a href="/final_project/employee/selectAll?cid=${cid}">
                <i class="u-icon-nav u-icon-nav-1"></i>我的员工
            </a>
        </li>
        <li>
            <a href="/final_project/employee/update_homepage?cid=${cid}">
                <i class="u-icon-nav u-icon-nav-2"></i>更新员工
            </a>
        </li>
        <li>
            <a href="/final_project/attendanceDetail/getAllToday?cid=${cid}">
                <i class="u-icon-nav u-icon-nav-3"></i>今日考勤
            </a>
        </li>
        <li>
            <a href="/final_project/attendanceDetail/getEmployeeTodayDetail?cid=${cid}" target="_blank">
                <i class="u-icon-nav u-icon-nav-4"></i>考勤详情
            </a>
        </li>
        <li>
            <a href="game/list_1.html" target="_blank">
                <i class="u-icon-nav u-icon-nav-5"></i>考勤终端
            </a>
        </li>
    </div>
    <div>
        <li>
            <a href="game/list_1.html" target="_blank">
                <i class="u-icon-nav u-icon-nav-6"></i>职能部门
            </a>
        </li>
        <li>
            <a href="game/list_1.html" target="_blank">
                <i class="u-icon-nav u-icon-nav-7"></i>实时监控
            </a>
        </li>
        <li>
            <a href="game/list_1.html" target="_blank">
                <i class="u-icon-nav u-icon-nav-8"></i>智能感知
            </a>
        </li>
        <li>
            <a href="game/list_1.html" target="_blank">
                <i class="u-icon-nav u-icon-nav-9"></i>发布通知
            </a>
        </li>
        <li>
            <a href="game/list_1.html" target="_blank">
                <i class="u-icon-nav u-icon-nav-10"></i>备忘录
            </a>
        </li>
    </div>

</div>
<script type="text/javascript">
    $(document).ready(function(){
        $('.slider7').bxSlider({
            slideWidth: 600,
            infiniteLoop: false,
            hideControlOnEnd: true,
            slideMargin: 10
        });
    });
</script>
</body>

</html>
