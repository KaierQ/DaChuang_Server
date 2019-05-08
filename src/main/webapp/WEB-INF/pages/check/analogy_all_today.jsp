<%--
  Created by IntelliJ IDEA.
  User: Kaier
  Date: 2019/4/30
  Time: 12:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>数据分析</title>
</head>
<body>
<div id="title" style="text-align:center">
    <h3>今日打卡一览表</h3>
</div>
<div>
    <h4>总人数:${numberOfPersonnel} &nbsp;&nbsp;&nbsp;迟到人数:${numOfLate} &nbsp;&nbsp;&nbsp;未到人数:${numOfNoArrive}</h4>
</div>
<div id="head01" style="text-align:center">
    <h3>今日上班打卡</h3>
</div>
<div id="checkInbar" style="height:500px;border:1px solid #ccc;padding:10px;"></div>
<div id="checkInPie" style="height:500px;border:1px solid #ccc;padding:10px;"></div>
<c:if test="${checkOut!=null}">
    <div>
        <h4>总人数:${numberOfPersonnel} &nbsp;&nbsp;&nbsp;早退人数或没有打卡人数:${numOfBadCheckout}</h4>
    </div>
    <div id="head02" style="text-align:center">
        <h3>今日下班打卡</h3>
    </div>
    <div id="checkOutbar" style="height:500px;border:1px solid #ccc;padding:10px;"></div>
    <div id="checkOutPie" style="height:500px;border:1px solid #ccc;padding:10px;"></div>
</c:if>
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
            'echarts/chart/pie'
        ],
        function(ec){
            //--- 折柱 ---
            var myCheckInBar = ec.init(document.getElementById('checkInbar'));
            myCheckInBar.setOption({
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:['上班打卡人数']
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
                        data : ['8:00之前','8:00~8:15','8:15~8:30',
                            '8:30~8:45','8:45~9:00','9:00~9:15(迟到)','9:15~9:30(迟到)','9:30之后(迟到)']
                    }
                ],
                yAxis : [
                    {
                        type : 'value',
                        splitArea : {show : true}
                    }
                ],
                series : [
                    {
                        name:'考勤打卡人数',
                        type:'bar',
                        data:[${checkInAnalogy.BEFORE_8_00},
                            ${checkInAnalogy._8_00_8_15},
                            ${checkInAnalogy._8_15_8_30},
                            ${checkInAnalogy._8_30_8_45},
                            ${checkInAnalogy._8_45_9_00},
                            ${checkInAnalogy._9_00_9_15},
                            ${checkInAnalogy._9_15_9_30},
                            ${checkInAnalogy.AFTER_9_30}]
                    }
                ]
            });

            //--- 饼图打卡 ---
            var myCheckInPie = ec.init(document.getElementById('checkInPie'));
            myCheckInPie.setOption({
                title : {
                    // text: '打卡人数比列',
                    // subtext: '描述上班打卡',
                    x:'center'
                },
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient : 'vertical',
                    x : 'left',
                    data:['8:00之前','8:00~8:15','8:15~8:30',
                        '8:30~8:45','8:45~9:00','9:00~9:15(迟到)','9:15~9:30(迟到)','9:30之后(迟到)']
                },
                toolbox: {
                    show : true,
                    feature : {
                        mark : {show: true},
                        dataView : {show: true, readOnly: false},
                        magicType : {
                            show: true,
                            type: ['pie', 'funnel'],
                            option: {
                                funnel: {
                                    x: '25%',
                                    width: '50%',
                                    funnelAlign: 'left',
                                    max: 1548
                                }
                            }
                        },
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                calculable : true,
                series : [
                    {
                        name:'访问来源',
                        type:'pie',
                        radius : '55%',
                        center: ['50%', '60%'],
                        data:[
                            {value:${checkInAnalogy.BEFORE_8_00}, name:'8:00之前'},
                            {value:${checkInAnalogy._8_00_8_15}, name:'8:00~8:15'},
                            {value:${checkInAnalogy._8_15_8_30}, name:'8:15~8:30'},
                            {value:${checkInAnalogy._8_30_8_45}, name:'8:30~8:45'},
                            {value:${checkInAnalogy._8_45_9_00}, name:'8:45~9:00'},
                            {value:${checkInAnalogy._9_00_9_15}, name:'9:00~9:15(迟到)'},
                            {value:${checkInAnalogy._9_15_9_30}, name:'9:15~9:30(迟到)'},
                            {value:${checkInAnalogy.AFTER_9_30}, name:'9:30之后(迟到)'}
                        ]
                    }
                ]
            });

            <c:if test="${checkOut!=null}">
            //--- 折柱 ---
            var myCheckOutBar = ec.init(document.getElementById('checkOutbar'));
            myCheckOutBar.setOption({
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:['下班打卡人数']
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
                        data : ['17:00之前(早退)','17:00~17:15','17:15~17:30','17:30~17:45','17:45之后']
                    }
                ],
                yAxis : [
                    {
                        type : 'value',
                        splitArea : {show : true}
                    }
                ],
                series : [
                    {
                        name:'考勤打卡人数',
                        type:'bar',
                        data:[${dataAnalogyCheckOut.BEFORE_5_00},
                            ${dataAnalogyCheckOut._17_00_17_15},
                            ${dataAnalogyCheckOut._17_15_17_30},
                            ${dataAnalogyCheckOut._17_30_17_45},
                            ${dataAnalogyCheckOut.AFTER_17_45}
                            ]
                    }
                ]
            });

            //--- 饼图打卡 ---
            var myCheckOutPie = ec.init(document.getElementById('checkOutPie'));
            myCheckOutPie.setOption({
                title : {
                    // text: '打卡人数比列',
                    // subtext: '描述下班打卡',
                    x:'center'
                },
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient : 'vertical',
                    x : 'left',
                    data:['17:00之前(早退)','17:00~17:15','17:15~17:30','17:30~17:45','17:45之后']
                },
                toolbox: {
                    show : true,
                    feature : {
                        mark : {show: true},
                        dataView : {show: true, readOnly: false},
                        magicType : {
                            show: true,
                            type: ['pie', 'funnel'],
                            option: {
                                funnel: {
                                    x: '25%',
                                    width: '50%',
                                    funnelAlign: 'left',
                                    max: 1548
                                }
                            }
                        },
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                calculable : true,
                series : [
                    {
                        name:'访问来源',
                        type:'pie',
                        radius : '55%',
                        center: ['50%', '60%'],
                        data:[
                            {value:${dataAnalogyCheckOut.BEFORE_5_00}, name:'17:00之前(早退)'},
                            {value:${dataAnalogyCheckOut._17_00_17_15}, name:'17:00~17:15'},
                            {value:${dataAnalogyCheckOut._17_15_17_30}, name:'17:15~17:30'},
                            {value:${dataAnalogyCheckOut._17_30_17_45}, name:'17:30~17:17:45'},
                            {value:${dataAnalogyCheckOut.AFTER_17_45},name:'17:45之后'}
                        ]
                    }
                ]
            });
            </c:if>
        }
    )
</script>

</body>
</html>