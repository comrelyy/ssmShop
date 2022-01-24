var prefix = "/common/generator/genColumns";
var columnsData = [];
var tableName = "";
$(function () {
    load();
    tableName = $('#tableName').val();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: prefix + "/list?tableName=" + $("#tableName").val(), // 服务器数据的加载地址
                //	showRefresh : true,
                //	showToggle : true,
                //	showColumns : true,
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: false, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect: false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize: 50, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                //search : true, // 是否显示搜索框
                showColumns: false, // 是否显示内容下拉框（选择显示的列）
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求
                responseHandler: function (rs) {

                    if (rs.code == 0) {
                        //columnsData[0]=rs.data.rows[0];
                        //tableName=columnsData[0].tableName;
                       // // rs.data.rows.splice(0,1);
                        return rs.data;
                    } else {
                        parent.layer.alert(rs.msg)
                        return {total: 0, rows: []};
                    }
                },
                onPostBody: function() {
                    loadDict();
                    loadDictType();
                },
                columns: [
                    // {
                    //     title: '序号',
                    //     formatter: function () {
                    //         return arguments[2] + 1;
                    //     }
                    // },
                    {
                        field: 'columnSort',
                        title: '列排序（升序）',
                        formatter: function (value, row, index) {
                            return "<input style='width: 100px' class=\"form-control\" type='text' value='"+value+"'/>";
                        }
                    },
                    {
                        field: 'columnName',
                        title: '列名',
                        formatter: function (value, row, index) {
                            return "<input style='width: 100px' class=\"form-control\" type='text' value='"+value+"'/>";
                        }
                    },
                    {
                        field: 'columnType',
                        title: '列类型',
                        formatter: function (value, row, index) {
                            return "<input style='width: 100px' class=\"form-control\" type='text' value='"+value+"'/>";
                        }
                    },
                    {
                        field: 'columnKey',
                        title: '索引类型',
                        formatter: function (value, row, index) {
                            return "<input style='width: 100px' class=\"form-control\" type='text' value='"+value+"'/>";
                        }
                    },
                    {
                        field: 'javaType',
                        title: '映射java类型',
                        formatter: function (value, row, index) {

                            return "<select style='width: 100px' class=\"form-control chosen-select\" tabindex=\"2\" dict-value='"+value+"' dict-type=\"java_type\" >\n" +
                                "                        </select>";
                        }
                    },
                    {
                        field: 'columnComment',
                        title: '列注释',
                        formatter: function (value, row, index) {
                            return "<input style='width: 100px' class=\"form-control\" type='text' value='"+value+"'/>";
                        }
                    },
                    {
                        field: 'columnLabel',
                        title: '列标签名',
                        formatter: function (value, row, index) {
                            return "<input style='width: 100px' class=\"form-control,checkbox-inline\" type='text' value='"+value+"'/>";
                        }
                    },
                    { /*<select data-placeholder="--选择类别--" name="catid" id="catid"
                    class="form-control chosen-select" tabindex="2" dict-type="novel_category" >
                        </select>*/
                        field: 'pageType',
                        title: '页面显示类型',
                        formatter: function (value, row, index) {

                            return "<select style='width: 100px' class=\"form-control chosen-select\" tabindex=\"2\" dict-value='"+value+"' dict-type=\"page_type\" >\n" +
                                "                        </select>";
                        }
                    },
                    {
                        field: 'dictType',
                        title: '字典类型',
                        formatter: function (value, row, index) {
                            return "<select name='dictType' style='width: 150px' class=\"form-control chosen-select\" tabindex=\"2\" select-value='"+value+"' >\n" +
                                "                        </select>";

                        }
                    },
                    {
                        field: 'isRequired',
                        title: '是否必填',
                        formatter: function (value, row, index) {
                            return "<input class=\"form-control\" type='checkbox' "+(value==1?'checked':'')+"/>";
                        }
                    },
                    {
                        field: 'defaultValue',
                        title: '默认值',
                        formatter: function (value, row, index) {
                            return "<input style='width: 100px' class=\"form-control\" type='text' value='"+value+"'/>";
                        }
                    },
                    {
                        field: 'status',
                        title: '状态',
                        formatter: function (value, row, index) {
                            return "<input style='width: 100px' class=\"form-control\" type='text' value='"+value+"'/>";
                        }
                    },
                    {
                        field: 'extra',
                        title: '其他',
                        formatter: function (value, row, index) {
                            return "<input style='width: 100px' class=\"form-control\" type='text' value='"+value+"'/>";
                        }
                    }]
            });
}

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

function add() {
    layer.open({
        type: 2,
        title: '增加列',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/addColumn/' + tableName // iframe的url
    });
}

function detail(id) {
    layer.open({
        type: 2,
        title: '详情',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/detail/' + id // iframe的url
    });
}

function edit(id) {
    layer.open({
        type: 2,
        title: '编辑',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/edit/' + id // iframe的url
    });
}

function remove(id) {
    layer.confirm('确定要删除选中的记录？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/remove",
            type: "post",
            data: {
                'id': id
            },
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    })
}

function resetPwd(id) {
}

function batchRemove() {
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要删除的数据");
        return;
    }
    layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
        btn: ['确定', '取消']
        // 按钮
    }, function () {
        var ids = new Array();
        // 遍历所有选择的行数据，取每条数据对应的ID
        $.each(rows, function (i, row) {
            ids[i] = row['id'];
        });
        $.ajax({
            type: 'POST',
            data: {
                "ids": ids
            },
            url: prefix + '/batchRemove',
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    }, function () {

    });



}


function exec() {
    $('#exampleTable').find("tbody").find("tr").each(function (index, trEle){
        var columnData = {};
        columnData.tableName = tableName;
        columnsData[index+1]=columnData;
        $(trEle).find("td").each(function (index, tdEle){
            switch (index) {
                case 0:{
                    columnData.columnSort = $(tdEle).find("input").eq(0).val();
                    break;
                }
                case 1:{
                    columnData.columnName = $(tdEle).find("input").eq(0).val();
                    break;
                }
                case 2:{
                    columnData.columnType = $(tdEle).find("input").eq(0).val();
                    break;
                }
                case 3:{
                    columnData.columnKey = $(tdEle).find("input").eq(0).val();
                    break;
                }
                case 4:{
                    columnData.javaType = $(tdEle).find("select").eq(0).val();
                    break;
                }
                case 5:{
                    columnData.columnComment = $(tdEle).find("input").eq(0).val();
                    break;
                }
                case 6:{
                    columnData.columnLabel = $(tdEle).find("input").eq(0).val();
                    break;
                }
                case 7:{
                    columnData.pageType = $(tdEle).find("select").eq(0).val();
                    break;
                }
                case 8:{
                    columnData.dictType = $(tdEle).find("input").eq(0).val();
                    break;
                }
                case 9:{
                    columnData.isRequired = $(tdEle).find("input").eq(0).is(':checked')?1:0;
                    break;
                }
                case 10:{
                    columnData.defaultValue = $(tdEle).find("input").eq(0).val();
                    break;
                }
                case 11:{
                    columnData.extra = $(tdEle).find("input").eq(0).val();
                    break;
                }
            }
        });
    });
console.log(columnsData)
    $.ajax({
        cache : true,
        type : "POST",
        url : prefix+"/execGen",
        headers : {
            "Content-Type": "application/json"
        },
        data : JSON.stringify(columnsData),
        async : false,
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(data) {
            if (data.code == 0) {
                parent.layer.msg("操作成功");
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);
            } else {
                parent.layer.alert(data.msg)
            }

        }
    });

}