<%--
  Created by IntelliJ IDEA.
  User: Kaier
  Date: 2019/5/16
  Time: 19:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>职能部门</title>
    <link href="../css/department/update_delete.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div id="employees" class="profile">

    <div class="employee">
        <div><img src="../images/department/select.jpg"></div>
        <ul>
            <li class="option">我的部门</li>
            <li class="optionDecription"><font>(点击查看)</font></li>
        </ul>
        <div class="go">
            <span><a href="../department/select_all?cid=${requestScope.cid}">&gt;</a></span>
        </div>
    </div>

    <div class="employee">
        <div><img src="../images/department/update.jpg"></div>
        <ul>
            <li class="option">修改部门信息</li>
            <li class="optionDecription"><font>(可更改部门名称和领导)</font></li>
        </ul>
        <div class="go">
            <span><a href="../department/update_detail?cid=${requestScope.cid}">&gt;</a></span>
        </div>
    </div>

    <div class="employee">
        <div><img src="../images/department/delete.jpg"></div>
        <ul>
            <li class="option">删除部门</li>
            <li class="optionDecription"><font>(根据部门号和名称删除)</font></li>
        </ul>
        <div class="go">
            <span><a href="../department/delete_detail?cid=${requestScope.cid}">&gt;</a></span>
        </div>
    </div>

    <div class="employee">
        <div><img src="../images/department/add.jpg"></div>
        <ul>
            <li class="option">添加部门</li>
            <li class="optionDecription"><font>(新增信息等条目)</font></li>
        </ul>
        <div class="go">
            <span><a href="../department/add_detail?cid=${requestScope.cid}">&gt;</a></span>
        </div>
    </div>

</div>


</body>
</html>
