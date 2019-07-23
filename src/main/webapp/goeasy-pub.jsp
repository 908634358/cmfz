<%@page pageEncoding="UTF-8" %>

<%--引入js   cdn host --%>
<script type="text/javascript" src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>

<script>


    var goEasy = new GoEasy({
        //接收的appkey
        appkey: "BC-268c25f44f5841ff93a8f4e6aa24193a"
    });

    goEasy.publish({
        channel: "cmfz-article",
        message: "Hello, GoEasy!"
    });




</script>


