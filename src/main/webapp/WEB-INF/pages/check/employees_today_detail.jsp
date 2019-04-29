<%--
  Created by IntelliJ IDEA.
  User: Kaier
  Date: 2019/4/29
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="../css/check/employees_today_detail.css" rel="stylesheet" type="text/css"/>
    <title>员工信息表</title>
</head>
<body>
<div id="employees" class="profile">

    <c:forEach items="${employeeTodayDetails}" var="item">
        <div class="employee">
            <div style="float:left">
                <img src="../images/check/person.jpg" class="person_img">
            </div>
            <div style="float:left">
                <ul class="info">
                    <li>工号:${item.eId}</li>
                    <li>姓名:${item.name}</li>
                    <li>职位:${item.title}</li>
                    <li>上班打卡:
                        <c:choose>
                            <c:when test="${item.arriveTime!=null}">
                                ${item.arriveTime.hours}:${item.arriveTime.minutes}:${item.arriveTime.seconds}
                            </c:when>
                            <c:otherwise>
                                未打卡
                            </c:otherwise>
                        </c:choose>
                    </li>
                    <li>下班打卡:
                        <c:choose>
                            <c:when test="${item.leftTime!=null}">
                                ${item.leftTime.hours}:${item.leftTime.minutes}:${item.leftTime.seconds}
                            </c:when>
                            <c:otherwise>
                                未打卡
                            </c:otherwise>
                        </c:choose>
                    </li>
                </ul>
            </div >
            <div class="link_analogy" style="float:left">
                <a href="https://www.baidu.com">查看分析 ></a>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>