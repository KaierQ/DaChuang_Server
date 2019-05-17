<%--
  Created by IntelliJ IDEA.
  User: Kaier
  Date: 2019/5/16
  Time: 21:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
    <link href="../css/department/update_detail.css" rel="stylesheet" type="text/css"/>
    <title>更新信息</title>
    <script>
        $(document).ready(function(){
            $("#submit_btn").click(function(){
                var urlStr="../department/add_info";
                $("#fmV").attr("action",urlStr).submit();
            });
        });
    </script>

</head>
<body>
<div class="profile">
    <form id="fmV" class="fm" method="post">
        <div class="input_div">
            <input id="cid" type="text" name="cid" value="${cid}" style="display:none"/>
        </div>
        <div class="input_div">
            部门名称:<input id="name" type="text" name="name"/>
        </div>
        <div class="input_div">
            领导工号:<input id="leaderId" type="text" name="leaderId"/>
        </div>
        <div class="input_div">
            领导姓名:<input id="leaderName" type="text" name="leaderName"/>
        </div>
        <div class="input_div">
            <input  id="submit_btn" class="submit_btn" type="button" value="添加" style="color:brown"/>
        </div>
    </form>
</div>
</body>
</html>
