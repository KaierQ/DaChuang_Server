<%--
  Created by IntelliJ IDEA.
  User: Kaier
  Date: 2019/5/4
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>分析</title>
</head>
<body>
<br id="title" style="text-align:center">
    <h2>此员工近期打卡一览表</h2></br>
    <h3>${dataAnalogyResult}</h3>
</div>
<div id="head01" style="text-align:center">
    <h3>近期上班打卡</h3>
</div>
<div id="RecentCheckInPie" style="height:500px;border:1px solid #ccc;padding:10px;"></div>
<div id="head02" style="text-align:center">
    <h3>近期下班打卡</h3>
</div>
<div id="RecentCheckOutPie" style="height:500px;border:1px solid #ccc;padding:10px;"></div>
<div id="CheckVenn" style="height:500px;border:1px solid #ccc;padding:10px;"></div>

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
            'echarts/chart/pie',
            'echarts/chart/venn',
        ],
        function(ec){
            //--- 饼图 ---
            var myCheckInBar = ec.init(document.getElementById('RecentCheckInPie'));
            myCheckInBar.setOption({
                title : {
                    // text: '此员工打卡比列',
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
                    data:['正常打卡','迟到','未到']
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
                            {value:${normalCheckIn}, name:'正常打卡'},
                            {value:${numOfLate}, name:'迟到'},
                            {value:${numOfNoArrive}, name:'未打卡'},
                        ]
                    }
                ]

            });
            //--- 饼图 ---
            var myCheckOutBar = ec.init(document.getElementById('RecentCheckOutPie'));
            myCheckOutBar.setOption({
                title : {
                    // text: '此员工打卡比列',
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
                    data:['正常打卡','早退','未打卡']
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
                            {value:${normalCheckOut}, name:'正常打卡'},
                            {value:${numOfEarlyLeave}, name:'早退'},
                            {value:${numOfNoCheckOut}, name:'未到'},
                        ]
                    }
                ]
            });

            //--- 韦恩图 ---
            var checkVenn = ec.init(document.getElementById('CheckVenn'));
            checkVenn.setOption({
                title : {
                    text: '迟到 vs 最退',
                    subtext: '各个数据的集合'
                },
                tooltip : {
                    trigger: 'item',
                    formatter: "{b}: {c}"
                },
                toolbox: {
                    show : true,
                    feature : {
                        mark : {show: true},
                        dataView : {show: true, readOnly: false},
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                calculable : false,
                series : [
                    {
                        name:'韦恩图',
                        type:'venn',
                        itemStyle: {
                            normal: {
                                label: {
                                    show: true,
                                    textStyle: {
                                        fontFamily: 'Arial, Verdana, sans-serif',
                                        fontSize: 16,
                                        fontStyle: 'italic',
                                        fontWeight: 'bolder'
                                    }
                                },
                                labelLine: {
                                    show: false,
                                    length: 10,
                                    lineStyle: {
                                        // color: 各异,
                                        width: 1,
                                        type: 'solid'
                                    }
                                }
                            },
                            emphasis: {
                                color: '#cc99cc',
                                borderWidth: 3,
                                borderColor: '#996699'
                            }
                        },
                        data:[
                            {value:${numOfLate+numOfNoArrive}, name:'迟到/未到'},
                            {value:${numOfEarlyLeave+numOfNoCheckOut}, name:'早退/未打卡'},
                            {value:${publicData}, name:'公共'}
                        ]
                    }
                ]
            });
        }
    );

</script>

<div id="details" style="text-align:center;border:1px solid #ccc;padding:10px;">
    <table border="3" style="text-align:center;">
        <tr>
            <th style="width:70%">日期</th>
            <th style="width:70%">上班</th>
            <th style="width:70%">下班</th>
        </tr>
        <c:forEach items="${details}" var="item">
            <tr>
                <td style="width:70%">${item.createdDate}</td>
                <td style="width:70%">${item.arriveTime.hours}:${item.arriveTime.minutes}:${item.arriveTime.seconds}</td>
                <td style="width:70%">${item.leftTime.hours}:${item.leftTime.minutes}:${item.leftTime.seconds}</td>
            </tr>
        </c:forEach>

    </table>
</div>

</body>
</html>
