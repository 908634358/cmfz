<%@page pageEncoding="UTF-8" isELIgnored="false" %>

<script>
    $("#user-table").jqGrid({
        url: "${pageContext.request.contextPath}/user/selectAll",
        datatype: "json",
        colNames: ['编号', '用户名', '密码', '照片','电话','盐值','法名','省份','城市','签名','创建时间'],
        colModel: [
            {name: 'id', hidden: true},
            {name: 'username',editable: true},
            {name: 'password', editable: true},
            {
                name: 'photo', editable: true, edittype: "file", formatter: function (value, option, rows) {
                    return "<img style='width:50%;height:12%;' src='${pageContext.request.contextPath}/user/img/" + rows.photo + "'/>";
                }
            },
            {name: 'phone', editable: true},
            {name: 'salt', editable: true},
            {name: 'dharma', editable: true, edittype: "select", editoptions: {value: "正常:正常;冻结:冻结"}},
            {name: 'province', editable: true},
            {name: 'city', editable: true},
            {name: 'sign', editable: true},
            {name: 'createDate'}
        ],
        styleUI: "Bootstrap",
        autowidth: true,
        height: "300px",
        rowNum: 3,
        pager: '#user-pager',
        rowList: [3, 5, 10],
        viewrecords: true,
        caption: "轮播图的详细信息",
        editurl: "${pageContext.request.contextPath}/user/edit"
    }).navGrid("#user-pager", {edit: true, add: true, del: true, search: false}, {
        //控制修改
        closeAfterEdit: close,
        beforeShowForm: function (frm) {
            frm.find("#cover").attr("disabled", true);
        }
    }, {
        //控制添加
        //关闭添加对话框
        closeAfterAdd: close,
        afterSubmit: function (response) {
            var status = response.responseJSON.status;
            var id = response.responseJSON.message;
            console.log(id,status);
            if (status) {
                $.ajaxFileUpload({
                    url: "${pageContext.request.contextPath}/user/upload",
                    fileElementId: "photo",
                    data: {id: id},
                    type: "post",
                    success: function () {
                        $("#user-table").trigger("reloadGrid");
                    }
                });
            }
            return "12312";
        }
    }, {
        //控制删除
    });
</script>


<script>
    function out() {
        window.location.href="${pageContext.request.contextPath}/user/out";
    }
</script>

<div class="page-header">
    <h2>展示所有的用户</h2>
</div>

<div>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">所有用户</a></li>
        <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab" onclick="out()">导出所有用户信息</a></li>
    </ul>
</div>
<table id="user-table"></table>
<div id="user-pager" style="height: 40px"></div>
