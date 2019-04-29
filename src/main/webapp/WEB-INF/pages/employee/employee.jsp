<%--
  Created by IntelliJ IDEA.
  User: Kaier
  Date: 2019/4/27
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>员工信息表</title>
    <link href="../css/employee/employees.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="employees" class="profile">

    <c:forEach items="${employees}" var="employee">
        <div class="employee">
            <div style="float:left">
                <img src="../images/employee/person.jpg" class="person_img">
            </div>
            <div style="float:left">
                <ul class="info">
                    <li>工号:${employee.id}</li>
                    <li>姓名:${employee.name}</li>
                    <li>职位:${employee.title}</li>
                    <li>部门号:${employee.departmentId}</li>
                    <li>工资:${employee.salary}/月</li>
                    <li>入职时间:${employee.date}</li>
                </ul>
            </div>
        </div>
    </c:forEach>

</div>
</body>
</html>
