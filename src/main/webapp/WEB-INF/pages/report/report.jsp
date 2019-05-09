<%--
  Created by IntelliJ IDEA.
  User: Kaier
  Date: 2019/5/9
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>月度报告</title>
</head>
<body>

    <div id="title" style="text-align:center">
        <h2>员工考勤月度报告</h2>
    </div>
    <div id="head01" style="text-align:center">
        <h4>员工上班到勤率</h4>
    </div>
    <div id="recentCheckInLine" style="height:500px;border:1px solid #ccc;padding:10px;"></div>
    <div id="head02" style="text-align:center">
        <h4>员工下班合格率</h4>
    </div>
    <div id="recentCheckOutLine" style="height:500px;border:1px solid #ccc;padding:10px;"></div>
    <!-- 引入echarts.js -->
    <script src="../js/echarts.js"></script>
    <script type="text/javascript">
        //为模块加载器配置echarts的路径，从当前页面链接到echarts.js，定义所需图表路径
        require.config({
            paths:{
                echarts:'../js'
            }
        });
        require(
            [
                'echarts',
                'echarts/chart/bar',
                'echarts/chart/line',
            ],
            function(ec){
                var recentCheckInLine = ec.init(document.getElementById('recentCheckInLine'));
                recentCheckInLine.setOption({
                    tooltip : {
                        trigger: 'axis'
                    },
                    legend: {
                        data:['上班打卡比例']
                    },
                    toolbox: {
                        show : true,
                        feature : {
                            mark : {show: true},
                            dataView : {show: true, readOnly: false},
                            magicType : {show: true, type: ['line', 'bar']},
                            restore : {show: true},
                            saveAsImage : {show: true}
                        }
                    },
                    calculable : true,
                    xAxis : [
                        {
                            type : 'category',
                            data : [
                                <c:forEach items="${dates}" var="item" varStatus="status">
                                    <c:choose>
                                        <c:when test="${status.index==fn:length(dates)}">
                                            '${item}'
                                        </c:when>
                                        <c:otherwise>
                                            '${item}',
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            ]
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value',
                            splitArea : {show : true},
                            axisLabel : {
                                formatter: '{value} %'
                            }
                        }
                    ],
                    series : [
                        {
                            name:'考勤打卡比例',
                            type:'line',
                            data:[
                                <c:forEach items="${checkPercent.percentCheckIn}" var="item" varStatus="status">
                                    <c:choose>
                                        <c:when test="${status.index==fn:length(checkPercent.percentCheckIn)}">
                                            ${item}
                                        </c:when>
                                        <c:otherwise>
                                            ${item},
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            ]
                        }
                    ]
                });

                var recentCheckOutLine = ec.init(document.getElementById('recentCheckOutLine'));
                recentCheckOutLine.setOption({
                    tooltip : {
                        trigger: 'axis'
                    },
                    legend: {
                        data:['下班打卡比例']
                    },
                    toolbox: {
                        show : true,
                        feature : {
                            mark : {show: true},
                            dataView : {show: true, readOnly: false},
                            magicType : {show: true, type: ['line', 'bar']},
                            restore : {show: true},
                            saveAsImage : {show: true}
                        }
                    },
                    calculable : true,
                    xAxis : [
                        {
                            type : 'category',
                            data : [
                                <c:forEach items="${dates}" var="item" varStatus="status">
                                <c:choose>
                                <c:when test="${status.index==fn:length(dates)}">
                                '${item}'
                                </c:when>
                                <c:otherwise>
                                '${item}',
                                </c:otherwise>
                                </c:choose>
                                </c:forEach>
                            ]
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value',
                            splitArea : {show : true},
                            axisLabel : {
                                formatter: '{value} %'
                            }
                        }
                    ],
                    series : [
                        {
                            name:'考勤打卡比例',
                            type:'line',
                            data:[
                                <c:forEach items="${checkPercent.percentCheckOut}" var="item" varStatus="status">
                                <c:choose>
                                <c:when test="${status.index==fn:length(checkPercent.percentCheckOut)}">
                                ${item}
                                </c:when>
                                <c:otherwise>
                                ${item},
                                </c:otherwise>
                                </c:choose>
                                </c:forEach>
                            ]
                        }
                    ]
                });

            }
        );
    </script>


</body>
</html>
