<%--
  Created by IntelliJ IDEA.
  User: Kaier
  Date: 2019/4/27
  Time: 20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
    <link href="../css/department/delete_detail.css" rel="stylesheet" type="text/css"/>
    <title>删除</title>

    <script>
        $(document).ready(function(){
            $("#submit_btn").click(function(){
                var urlStr="../department/delete_info";
                $("#fmV").attr("action",urlStr).submit();
            });
        });
    </script>

</head>
<body>
<div class="profile">
    <form id="fmV" class="fmV" action="" method="post">
        <div class="input_div">
            <input id="cid" type="text" name="cid" value="${cid}" style="display:none"/>
        </div>
        <div class="input_div">
            部门号:<input type="text" name="departmentId"/>
        </div>
        <div class="input_div">
            部门名称:<input type="text" name="name"/>
        </div>
        <div class="sbmit_btn">
            <input id="submit_btn" type="button" value="删除" style="color:brown;border-radius: 5px;">
        </div>
    </form>
</div>
</body>
</html>
