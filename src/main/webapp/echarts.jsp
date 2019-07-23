<%@page pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <%--引入echarts的js文件--%>
    <script src="incubator-echarts-4.2.1/incubator-echarts-4.2.1/dist/echarts.min.js"></script>

    <script src="statics/boot/js/jquery-3.3.1.min.js"></script>


    <script src="https://www.echartsjs.com/gallery/vendors/echarts/map/js/china.js"></script>

</head>
<body>


<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 600px;height:400px;"></div>

<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));    // 必须通过js语法获取当前div进行初始化

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '上半年用户注册趋势',
            subtext:"这是个子标题"
        },
        tooltip: {},  // 数据的提示
        legend: {
            data:['男',"女","小孩"]
        },
        xAxis: {
            data: ["一月份","二月份","三月份","四月份","五月份","六月"]
        },
        yAxis: {},
        series: [{
            name: '男',
            type: 'line',//bar：柱状图  line:折线图     pie:饼图
            data: [0, 44, 36, 10, 100, 20]
        },{
            name: '女',
            type: 'line',//bar：柱状图  line:折线图     pie:饼图
            data: [0, 34, 78, 40, 30, 66]
        },{
            name: '小孩',
            type: 'line',//bar：柱状图  line:折线图     pie:饼图
            data: [0, 64, 28, 50, 10, 88]
        }]
    };


    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    
    
    <%--$.ajax({--%>
        <%--url:"${pageContext.request.contextPath}/json/json-bar.json",--%>
        <%--type:"get",--%>
        <%--datetype:"json",--%>
        <%--success:function (response) {--%>
            <%--myChart.setOption({--%>
                <%--series: [{--%>
                        <%--name: '销量',--%>
                        <%--type: 'bar',//bar：柱状图  line:折线图     pie:饼图--%>
                        <%--data: response.data--%>
                    <%--}],--%>
                <%--xAxis: {--%>
                    <%--data: response.name--%>
                <%--}--%>
            <%--})--%>
        <%--}--%>
    <%--})--%>
    
    
    
    
    
</script>


</body>
</html>