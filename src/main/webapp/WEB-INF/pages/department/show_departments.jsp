<%--
  Created by IntelliJ IDEA.
  User: Kaier
  Date: 2019/5/16
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="../css/department/departments.css" rel="stylesheet" type="text/css">
</head>
<body>
    <c:forEach items="${departments}" var="department">
        <div class="department">
            <div style="float:left">
                <img src="../images/department/select.jpg" class="department_img">
            </div>
            <div style="float:left">
                <ul class="info">
                    <li>部门号:${department.id}</li>
                    <li>名称:${department.name}</li>
                    <li>领导工号:${department.leaderId}</li>
                    <li>领导姓名:${department.leaderName}</li>
                    <li>人数:${department.personnelNum}</li>
                </ul>
            </div>
        </div>
    </c:forEach>
</body>
</html>
