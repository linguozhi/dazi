/**
 * DataTable控件共用处理类
 *
 * @author guice_lin@126.com
 * @since 2016-05-31
 * @version 1.0.0
 */

/**
 * 右边操作区域的Iframe对象
 * @type
 */
function refreshRightIframe(url) {
    parent.document.getElementById("iframeMain").src = url;
}

/**
 * 继承控制
 */
$.extend($.fn.dataTable.defaults, {
    "searching": false,
    "language": {
        "lengthMenu": "每页显示 _MENU_ 条记录",
        "processing": "加载中……",
        "info": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
        "infoEmpty": "没有数据",
        "infoFiltered": "(从 _MAX_ 条数据中检索)",
        "zeroRecords": "没有检索到数据",
        "sZeroRecords": "没有检索到数据",
        "sEmptyTable": "没有检索到数据",
        "search": "搜索:",
        "paginate": {
            "first": "首页",
            "previous": "前一页",
            "next": "后一页",
            "last": "尾页"
        }
    }

});

/**
 * DataTable自定义事件
 */
var myDataTable = {
    /**
     * checkbox全选/反选事件
     */
    checkboxAll: function (datatable) {
        var $ckAll = $('[role="ckAll"]'),
            $ck = $('[role="ck"]');

        var isCheckAll;

        // 点击全选的checkbox
        $ckAll.click(function () {
            isCheckAll = $ckAll.prop('checked');
            $ck.prop('checked', isCheckAll);
            $('[role="ck"]:disabled').prop('checked', false);

            if (isCheckAll) {
                $ck.closest('tr').addClass('selected');
            } else {
                $ck.closest('tr').removeClass('selected');
            }
        });

        // 点击复选框
        $ck.each(function () {
            var $curCk = $(this);
            $curCk.click(function () {
                isChecked = $curCk.prop('checked');

                if (isChecked) {
                    isCheckAll = true;
                    $curCk.closest('tr').addClass('selected');

                    $ck.each(function () {
                        if (!$(this).attr('disabled') && !$(this).prop('checked')) {
                            isCheckAll = false;
                        }
                    });

                } else {
                    isCheckAll = false;
                    $curCk.closest('tr').removeClass('selected');
                }

                $ckAll.prop('checked', isCheckAll);

            }).closest('tr').click(function (event) {
                if ($(event.target).is("td")) {
                    $curCk.trigger('click');
                }
                ;

            });
        });
    },
    /**
     * 获取复选框选中ID
     * @returns {Array}
     */
    getCheckedIds: function () {
        var ids = "";
        $ck = $('[role="ck"]');
        $ck.each(function () {
            if ($(this).prop('checked')) {
                //ids.push($(this).val());
                if (ids != null && ids != "") {
                    ids = ids + "," + $(this).val();
                } else {
                    ids = $(this).val();
                }
            }
        });
        return ids;
    },
    /**
     * 获取被选中的复选框
     */
    getCheckeds: function () {
        var checkedList = new CheckedList();
        $ck = $('[role="ck"]');
        $ck.each(function () {
            if ($(this).prop('checked')) {
                checkedList.push($(this).clone());
            }
        });
        return checkedList;
    },
    /**
     * 获取复选框选中ID
     * @returns {Array}
     */
    reloads: function () {
        var $ckAll = $('[role="ckAll"]');
        $ckAll.attr("checked", false);
        datatable.ajax.reload(null, false);
    },

    /**
     *  获取表单属性
     */
    getProps: function () {
        var props = [];
        var headers = datatable.context[0].aoColumns;
        headers.forEach(function (head) {
            props.push(head.mData);
        })

        return props.join(",");

    },
    /**
     * 规范参数名
     * @param arg
     */
    convertProp: function (arg) {
        var re = /-(\w)/g;
        return arg.replace(re, function($0,$1){
            return $1.toUpperCase();
        })
    }


};

//zhengqb check的选中对象
function CheckedList() {
    this.list = new Array();
}
CheckedList.prototype.push = function (checked) {
    this.list.push(checked);
};
CheckedList.prototype.get = function (attr) {
    return this.getArray(attr).join(",");
};
CheckedList.prototype.getArray = function (attr) {
    var attrs = new Array();
    if (this.list.length <= 0) {
        return attrs;
    }
    for (var i = 0; i < this.list.length; i++) {
        attrs.push(this.list[i].attr(attr));
    }
    return attrs;
};
