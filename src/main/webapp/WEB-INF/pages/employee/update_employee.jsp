<%--
  Created by IntelliJ IDEA.
  User: Kaier
  Date: 2019/4/27
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>更新员工信息</title>
    <link href="../css/employee/update_delete.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <div id="employees" class="profile">
        <div class="employee">
            <div><img src="../images/employee/update.jpg"></div>
            <ul>
                <li class="option">修改员工信息</li>
                <li class="optionDecription"><font>(可更改员工职位和薪水)</font></li>
            </ul>
            <div class="go">
                <span><a href="../employee/update_detail?cid=${requestScope.cid}">&gt;</a></span>
            </div>
        </div>
        <div class="employee">
            <div><img src="../images/employee/delete.jpg"></div>
            <ul>
                <li class="option">删除员工</li>
                <li class="optionDecription"><font>(根据员工号和姓名删除)</font></li>
            </ul>
            <div class="go">
                <span><a href="../employee/delete_detail?cid=${requestScope.cid}">&gt;</a></span>
            </div>
        </div>
    </div>

</body>
</html>
