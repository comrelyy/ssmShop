var prefix = "/backend/dict"
$(function () {
    load();
});

function selectLoad() {
    var html = "";
    $.ajax({
        url: '/backend/dict/type',
        success: function (res) {
            //加载数据
            if (res.code == 0) {
                var data = res.data;
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i].type + '">' + data[i].description + '</option>'
                }
                $(".chosen-select").append(html);
                $(".chosen-select").chosen({
                    maxHeight: 200
                });
                //点击事件
                $('.chosen-select').on('change', function (e, params) {
                    console.log(params.selected);
                    var opt = {
                        query: {
                            type: params.selected,
                        }
                    }
                    $('#exampleTable').bootstrapTable('refresh', opt);
                });
            }
        }
    });
}

function load() {
    selectLoad();
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: prefix + "/list", // 服务器数据的加载地址
                //	showRefresh : true,
                //	showToggle : true,
                //	showColumns : true,
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect: false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize: 25, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                //search : true, // 是否显示搜索框
                showColumns: false, // 是否显示内容下拉框（选择显示的列）
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                queryParams: function (params) {
                    //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                    return {
                        limit: params.limit,
                        offset: params.offset,
                        type: $('#searchName').val(),
                        // queryParams.limit = params.limit;
                        // queryParams.offset = params.offset;
                        // return queryParams;
                    };
                },
                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求
                responseHandler: function (rs) {
                    if (rs.code == 0) {
                        return rs.data;
                    } else {
                        parent.layer.alert(rs.msg)
                        return {total: 0, rows: []};
                    }
                },
                columns: [
                    {
                        checkbox: true
                    },
                    // {
                    //     title: '序号',
                    //     formatter: function () {
                    //         return arguments[2] + 1;
                    //     }
                    // },
                    {
                        field: 'id',
                        title: '编号'
                    },
                    {
                        field: 'name',
                        title: '标签名',
                        sortable:"true",
                        searchType:"text"
                    },
                    {
                        field: 'value',
                        title: '数据值'
                    },
                    {
                        field: 'type',
                        title: '类型'
                    },
                    {
                        field: 'description',
                        title: '描述'
                    },
                    {
                        field: 'sort',
                        title: '排序（升序）'
                    },
                    {
                        field: 'parentId',
                        title: '父级编号'
                    },
                    {
                        field: 'createBy',
                        title: '创建者'
                    },
                    {
                        field: 'createDate',
                        title: '创建时间'
                    },
                    {
                        field: 'updateBy',
                        title: '更新者'
                    },
                    {
                        field: 'updateDate',
                        title: '更新时间'
                    },
                    {
                        field: 'remarks',
                        title: '备注信息'
                    },
                    {
                        field: 'delFlag',
                        title: '删除标记'
                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var d = '<a class="btn btn-primary btn-sm ' + s_detail_h + '" href="#" mce_href="#" title="详情" onclick="detail(\''
                                + row.id
                                + '\')"><i class="fa fa-file"></i></a> ';
                            var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + row.id
                                + '\')"><i class="fa fa-edit"></i></a> ';
                            var r = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + row.id
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            return d + e + r;
                        }
                    }]
            });
}

function reLoad() {
    var opt = {
        query: {
            type: $('.chosen-select').val(),
        }
    }
    $('#exampleTable').bootstrapTable('refresh', opt);
}

function add() {
    layer.open({
        type: 2,
        title: '增加',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/add' // iframe的url
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