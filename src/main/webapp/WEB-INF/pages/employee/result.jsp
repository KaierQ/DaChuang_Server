<%--
  Created by IntelliJ IDEA.
  User: Kaier
  Date: 2019/4/27
  Time: 21:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script type="text/javascript" src="../js/jquery.bxslider.js"></script>

    <title>结果</title>
    <link href="../css/employee/result.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<div class="profile">
    <div class="employee">
        <c:choose>
            <c:when test="${ret=='1'}">
                <img src="../images/employee/success.jpg"/>
            </c:when>
            <c:otherwise>
                <img src="../images/employee/fail.jpg"/>
            </c:otherwise>
        </c:choose>
    </div>
    <div>
        <h3>${info}</h3>
    </div>
</div>

</body>
</html>