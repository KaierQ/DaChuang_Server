<%--
  Created by IntelliJ IDEA.
  User: Kaier
  Date: 2019/4/25
  Time: 13:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <base href="" />
    <meta charset="utf-8" />
    <title>企业注册</title>
    <link rel="stylesheet" href="../css/register.css" type="text/css">
    <script type="text/javascript" src="../js/jquery-1.8.3.min.js">
    </script>
    <script type="text/javascript" src="../js/jquery.validate.js">
    </script>
    <script type="text/javascript" src="../js/jquery.validate.messages_cn.js">
    </script>

    <script type="text/javascript">
        $(document).ready(function(){
            //鼠标点击后文本框变色
            $(".textInput").focus(function() {
                $(this).addClass("input_focus");
            });
            //鼠标点击后文本框恢复原来的背景
            $(".textInput").blur(function() {
                $(this).removeClass("input_focus");
            });

            $("#frmV").validate({
                //自定义验证规则
                rules : {
                    companyName : {
                        required : true,
                        minlength : 0
                    },
                    ceoName : {
                        required : true
                    },
                    ceoID:{
                        required : true
                    },
                    username : {
                        required : true,
                        minlength : 6
                    },
                    password : {
                        required : true,
                        minlength : 6
                    },
                    sysCheckPassword : {
                        required : true,
                        minlength : 6
                    },
                    tel : {
                        required : true,
                        minlength : 7
                    },
                    textarea:{
                        required : true
                    }
                },
                /*错误提示位置*/
                errorPlacement : function(error, element) {
                    error.appendTo(element.siblings("span"));
                } });

            $("#sbt").click(function () {
                //表单提交时的验证和url的修改
                var companyName = $("#companyName").val().trim();
                var ceoName = $("#ceoName").val().trim();
                var ceoId = $("#ceoId").val().trim();
                var username = $("#username").val().trim();
                var password = $("#password").val().trim();
                var sysCheckPassword = $("#sysCheckPassword").val().trim();
                var tel = $("#tel").val().trim();
                var intro = $("#intro").val().trim();

                var urlStr = "/final_project/company/addCompany/"+companyName
                    +"/"+ceoName+"/"+ceoId+"/"+username+"/"+password+"/"+sysCheckPassword+"/"+tel+"/"+intro;
                console.log(urlStr);
                console.log("123");
                $("#frmV").attr("action",urlStr).submit();
            });
        });
    </script>

</head>
<body>
    <h2 class="constaces">欢迎注册</h2>

    <form id="frmV" action="" method="post">
        <div id="register">
            <div>
                <label class="constaces">&nbsp;公司名称:</label>
                    <input type="text" class="textInput" id="companyName" name="companyName"/>
                    <font color="red">&nbsp;*</font><span></span>
                    <br/>
            </div>
            <br/>
            <div>
                <label class="constaces">&nbsp;企业法人:</label>
                    <input type="text" class="textInput" id="ceoName" name="ceoName"/>
                    <font color="red">&nbsp;*</font><span></span>
                    <br/>
            </div>
            <br/>
            <div>
                <label class="constaces">&nbsp;身份证号:</label>
                <input type="text" class="textInput" id="ceoId" name="ceoId"/>
                <font color="red">&nbsp;*</font><span></span>
                <br/>
            </div>
            <br/>
            <div>
                <label class="constaces">&nbsp;管理用户名:</label>
                    <input type="text" class="textInput" id="username" name="username"/>
                    <font color="red">&nbsp;*</font><span></span>
                    <br/>
            </div>
            <br/>
            <div>
                <label class="constaces">&nbsp;管理密码:</label>
                    <input type="password" class="textInput" id="password" name="password"/>
                    <font color="red">&nbsp;*</font><span></span>
                    <br/>
            </div>
            <br/>
            <div>
                <label class="constaces">&nbsp;考勤密码:</label>
                    <input type="password" class="textInput" id="sysCheckPassword" name="sysCheckPassword"/>
                    <font color="red">&nbsp;*</font><span></span>
                    <br/>
            </div>
            <br/>
            <div>
                <label class="constaces">&nbsp;企业电话:</label>
                    <input type="text" class="textInput" id="tel" name="tel"/>
                    <font color="red">&nbsp;*</font><span></span>
                    <br/>
            </div>
            <br/>
            <div>
                <label class="constaces">公司简介(必填):</label><br>
                <textarea name="intro" id="intro" cols="30" rows="10"
                          class="textInput"></textarea>
                <font color="red">&nbsp;*</font><span></span> <br/>
                <input type="button" name="submitRegister" id="sbt" class="btn" value="注册提交">
            </div>
        </div>
    </form>
</body>

</html>
