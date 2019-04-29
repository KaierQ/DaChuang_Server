<%--
  Created by IntelliJ IDEA.
  User: Kaier
  Date: 2019/4/27
  Time: 20:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
    <link href="../css/employee/update_detail.css" rel="stylesheet" type="text/css"/>
    <title>更新信息</title>
    <script>
        $(document).ready(function(){
            $("#submit_btn").click(function(){
                var uid = $("#uid").val().trim();
                var name = $("#name").val().trim();
                var title = $("#title").val().trim();
                var salary = $("#salary").val().trim();
                var urlStr="../employee/update_info";
                console.log("listen....");
                console.log(urlStr);
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
            工号:<input id="uid" type="text" name="uid"/>
        </div>
        <div class="input_div">
            姓名:<input id="name" type="text" name="name"/>
        </div>
        <div class="input_div">
            职位:<input id="title" type="text" name="title"/>
        </div>
        <div class="input_div">
            薪水:<input id="salary" type="text" name="salary"/>
        </div>
        <div class="input_div">
            <input  id="submit_btn" class="submit_btn" type="button" value="更改" style="color:brown"/>
        </div>
    </form>
</div>
</body>
</html>
