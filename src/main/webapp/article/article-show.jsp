<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<script>
    $("#article-table").jqGrid({
        url: "${pageContext.request.contextPath}/guru/selectAllAlbum",
        editurl: "${pageContext.request.contextPath}/guru/edit",
        datatype: "json",
        height: 190,
        colNames: ['编号', '上师名称', '图片', '状态', '性别'],
        colModel: [
            {name: 'id', hidden: true,},
            {name: 'name', editable: true},
            {
                name: 'photo', editable: true, edittype: "file", formatter: function (value, option, rows) {
                    return "<img class='img-circle' style='width:50%;height:12%;' src='${pageContext.request.contextPath}/article/img/" + rows.photo + "'/>";
                }
            },
            {name: 'status', editable: true},
            {name: 'sex', editable: true},
        ],
        autowidth: true,
        styleUI: "Bootstrap",
        rowNum: 4,
        height: "500px",
        rowList: [4, 8, 10, 20, 30],
        pager: '#album-pager',
        sortname: 'id',
        viewrecords: true,
        sortorder: "desc",
        multiselect: false,
        caption: "展示所有的文章",
        subGrid: true,
        subGridRowExpanded: function (subgrid_id, guruId) {// 1.装子表的容器id    2.关系属性
            var subgrid_table_id = subgrid_id + "_t";   //子表的table  id
            var pager_id = "p_" + subgrid_table_id;     //子表的div  id
            $("#" + subgrid_id).html(
                "<table id='" + subgrid_table_id + "' class='scroll'></table>" +
                "<div id='" + pager_id + "' class='scroll'></div>");
            $("#" + subgrid_table_id).jqGrid(
                {
                    url: "${pageContext.request.contextPath}/article/selectAllArticle?guruId=" + guruId,  //查询当前专辑下的所有章节？当前专辑的id
                    editurl: "${pageContext.request.contextPath}/article/edit?guruId=" + guruId,
                    rowNum: 2,
                    rowList: [2, 4, 8, 3, 5, 9],
                    datatype: "json",
                    colNames: ['编号', '标题', '内容', "上传文章的时间", "上师ID", "操作", "操作2"],
                    colModel: [
                        {name: "id", hidden: true},
                        {name: "title", editable: true, width: "70"},
                        {name: "content", width: "150%", editable: true},
                        {name: "createDate", width: "80%",},
                        {name: "guruId", width: "80",},
                        {
                            name: "aa",width:"50px",formatter: function (value, options, row) {
                                return "<a class='btn btn-warning' id='article' onclick=\"operModal('edit','" + row.id + "','" + subgrid_table_id + "','" + guruId + "')\">修改文章</a>";
                            }
                        },
                        {
                            name: "ab", width:"50px",formatter: function (value, options, row) {
                                return "<a class='btn btn-warning' id='article' onclick=\"operModal('add','" + row.id + "','" + subgrid_table_id + "','" + guruId + "')\">添加文章</a>";
                            }
                        }

                    ],
                    autowidth: true,
                    styleUI: "Bootstrap",
                    pager: pager_id,
                    sortname: 'num',
                    sortorder: "asc",
                    height: '100%'
                }).jqGrid('navGrid',
                "#" + pager_id, {
                    edit: false,
                    add: true,
                    del: false
                }, {
                    //控制子表的修改
                }, {
                    closeAfterAdd: true,
                    /* //控制子表的添加
                     afterSubmit: function (response) {
                         var status = response.responseJSON.status;
                         var id = response.responseJSON.message;
                         if (status) {
                             $.ajaxFileUpload({
                                 url: "<%--${pageContext.request.contextPath}--%>/guru/upload",
                                fileElementId: "name",
                                data: {id: id},
                                type: "post",
                                success: function () {
                                    $("#" + subgrid_table_id).trigger("reloadGrid");
                                }
                            });
                        }
                        return "123";
                    }*/
                });
        },

    }).navGrid("#album-pager", {edit: false, add: true, del: false, search: false}, {}, {
        //控制添加
        //关闭添加对话框
        closeAfterAdd: close,
        afterSubmit: function (response) {
            var status = response.responseJSON.status;
            var id = response.responseJSON.message;
            console.log(id, status)
            if (status) {
                $.ajaxFileUpload({
                    url: "${pageContext.request.contextPath}/guru/upload",
                    fileElementId: "photo",
                    data: {id: id},
                    type: "post",
                    success: function () {
                        $("#album-table").trigger("reloadGrid");
                    }
                });
            }
            return "12312";
        }
    }, {
        //控制删除
    });

    //打开模态框
    function operModal(oper, id, subgrid_table_id, guruId) {
        KindEditor.html("#editor_id", "");
        var guru = $("#article-table").jqGrid("getRowData", guruId);//guru的id
        var article = $("#" + subgrid_table_id).jqGrid("getRowData", id);//article的id
        //给表单设置默认值
        var val = $("#article-id").val(article.id);
        var val1 = $("#article-title").val(article.title);
        var val2 = $("#article-author").val(guru.name);
        var val3 = $("#article-guruId").val(guru.id);

        if (oper === "add") {
            $("#article-id").val(null);
            $("#article-title").val(null);
            KindEditor.html("#editor_id", guruId.content);
        }
        console.log(val)
        console.log(val1)
        console.log(val2)
        console.log(val3)
        if (oper === "edit") {
            KindEditor.html("#editor_id", article.content);
        }
        $("#article-oper").val(oper);

        $("#article-modal").modal("show");

    }

    //初始化kindeditor
    KindEditor.create('#editor_id', {
        //展示图片空间
        allowFileManager: true,
        //图片空间对应的地址
        fileManagerJson: "${pageContext.request.contextPath}/article/browser",
        //上传图片的地址
        uploadJson: "${pageContext.request.contextPath}/article/upload",
        //上传图片时接受的参数
        filePostName: "articleImage",
        //设置只能改变宽度，不能改变高度
        resizeType: 1,
        //集成项目时必须添加,同步KindEditor的值到textarea文本框
        afterBlur: function () {
            this.sync();
        }
    });

    function dealSave() {
        //    文件添加
        $.ajax({
            url: "${pageContext.request.contextPath}/article/edit",
            type: "post",
            data: $("#article-form").serialize(),
            dataType: "json",
            success: function () {
                //    关闭模态框
                $("#article-modal").modal("hide");
                //    刷新jqgrid
                $("#article-table").trigger("reloadGrid");
            }
        })

    }


</script>

<%--<div>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                  data-toggle="tab">所有文章</a></li>
        <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab"
                                   onclick="operModal('add')">添加文章</a></li>
    </ul>
</div>--%>
<table id="article-table"></table>
<div id="article-pager" style="height: 40px"></div>

<%--添加文章的模态框--%>
<div class="modal fade" tabindex="-1" role="dialog" id="article-modal">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width: 702px;">
            <%--头--%>
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">我的文章</h4>
            </div>
            <%--内容--%>
            <div class="modal-body">

                <form class="form-horizontal" id="article-form">
                    <input id="article-id" name="id" readonly>
                    <input id="article-oper" name="oper" readonly>
                    <input id="article-guruId" name="guruId" readonly>

                    <%--输入文章标题--%>
                    <div class="form-group">
                        <label for="article-title" class="col-sm-3 control-label">文章标题：</label>
                        <div class="col-sm-9">
                            <input type="text" name="title" class="form-control" id="article-title" placeholder="文章标题">
                        </div>
                    </div>
                    <%--输入文章作者--%>
                    <div class="form-group">
                        <label for="article-author" class="col-sm-3 control-label">文章作者：</label>
                        <div class="col-sm-9">
                            <input type="text" name="author" class="form-control" id="article-author"
                                   placeholder="文章作者" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <%--输入文章内容--%>
                        <%--kindeditor--%>
                        <textarea id="editor_id" name="content" style="width:700px;height:300px;"></textarea>
                    </div>
                </form>


            </div>
            <%--脚--%>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="dealSave()">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<table id="album-table"></table>
<div id="album-pager" style="height: 40px"></div>