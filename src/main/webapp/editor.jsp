<%@page isELIgnored="false" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <title>kindeditor的使用</title>

    <script charset="utf-8" src="kindeditor/kindeditor-all-min.js"></script>
    <script charset="utf-8" src="kindeditor/lang/zh-CN.js"></script>
    <script>
        KindEditor.ready(function(K) {
            window.editor = K.create('#editor_id',{
                //相关参数
                width : '1000px',
                height: "500px",
                //展示图片空间
                allowFileManager:true,
                //图片空间对应的地址
                fileManagerJson:"${pageContext.request.contextPath}/article/browser",
                //上传图片的地址
                uploadJson:"${pageContext.request.contextPath}/article/upload",
                //上传图片时接受的参数
                filePostName:"articleImage"
            });
        });
    </script>


</head>
<body>

<textarea id="editor_id" name="content" style="width:700px;height:300px;">
    &lt;strong&gt;HTML内容&lt;/strong&gt;
</textarea>


</body>
</html>