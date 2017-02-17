// bootstrap-table: 日期格式化
function dateFormatter(value){
    if (value == null) return '-';
    return moment(value).format('l');
}

// bootstrap-table: 时间格式化
function timeFormatter(value){
    if (value == null) return '-';
    return moment(value).format('YYYY-MM-DD HH:mm:ss');
}

/**
 * 订单号格式化
 * @param value
 * @param row
 * @returns {string}
 */
function payNoFormatter(value, row) {
    return  "'" + value ;
}

// 合计列格式化
function sumFormatter(value, row) {
    if (value == null) {
        return "-合计-";
    } else {
        return dateFormatter(value);
    }
}

// bootstrap-table: 空值格式化
function emptyFormatter(value) {
    return value != null && value != 0 ? value : '';
}

// bootstrap-table: 百分比格式化
function percentFormatter(value){
    if(value != null && value != "" && value != "undefined"){
        return value + "%";
    }
    return '-';
}

// bootstrap-table: 价格格式化
function priceFormatter(value) {
    return isNaN(value) ? '-' : value.format(2, 3);
}

// bootstrap-table: 数字格式化
function numsFormatter(value) {
    return isNaN(value) ? '-' : value.format(0, 3);
}

// bootstrap-table: ajax数据处理方法，返回的数据需要修改字段名称，否则table无法正常显示
function resHandler(res) {
    res['rows'] = res['list'];
    delete res['list'];

    return res;
}

// bootstrap-table: 隔行样式
function rowStyle(row, index) {
    if (index % 2 === 0) {
        return {
            classes : 'odd'
        };
    }
    return {};
}

// bootstrap-table: 不换行样式
function nowrap() {
    return {
        classes: 'text-nowrap'
    };
}

// layer弹框
function layer_alert(msg, icon, callback) {
    var option = {time: 1500, title: false, btn: false, closeBtn: false};
    if (icon) {
        option.icon = icon;
    }
    var index = layer.alert(msg, option);

    if (callback) {
        setTimeout(callback, option.time);
    }
    return index;
}
function layer_show(url, title, w, h, full) {
    if (title == null || title == '') {
        title = false;
    };
    if (w == null || w == '') {
        w = '90%';
    };
    if (h == null || h == '') {
        h = '80%';
    };
    var index = layer.open({
        type: 2,
        area: [w, h],
        fix: false,
        maxmin: true,
        shade: 0.4,
        title: title,
        content: url
    });
    if (full) {
        layer.full(index);
    }
    return index;
}

// layer全屏弹框
function layer_show_full(url, title) {
    var index = layer.open({
        type: 2,
        fix: false,
        title: title || '信息',
        content: url
    });
    layer.full(index);
    return index;
}

// layer显示loading
function showLoading(title) {
    return layer.msg(title || '请求中...', {icon: 16, time: 0, shade: [0.3, '#000']});
}

// 删除确认框，确定后调用ajax请求url删除数据，完成后调用bootstrap-table方法删除记录
function confirmDelete(url, title, callback) {
    swal({
        title: title || "确定删除?",
        type: "warning",
        showCancelButton: true,
        cancelButtonText: '取消',
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "删除!",
        showLoaderOnConfirm: true,
        closeOnConfirm: false,
    }, function(){
        $.get(url, function(id) {
            swal({title:"删除成功!", text: '', type:"success"}, function() {
                $table.bootstrapTable('remove', {field:'id', values: [id]});
                if (callback != null) callback(id);
            });
        });
    });

}

// 编辑页面操作完成后，回调页面列表函数
// 注意：需要在table上配置data-uniue-id="id"选项
function tableCallback(action, data) {
    if (action == 'add') {
        $('#dataTable').bootstrapTable('insertRow', {index: 0, row: data});
    } else if (action == 'edit') {
        $('#dataTable').bootstrapTable('updateByUniqueId', {id: data.id, row: data});
    } else if (action == 'delete') {
        $('#dataTable').bootstrapTable('removeByUniqueId', data.id);
    }
    if (layer) {
        layer.closeAll();
    }
}

// 格式化数字，支持小数点后几位
Number.prototype.format = function(n, x) {
    var re = '(\\d)(?=(\\d{' + (x || 3) + '})+' + (n > 0 ? '\\.' : '$') + ')';
    return this.toFixed(Math.max(0, ~~n)).replace(new RegExp(re, 'g'), '$1,');
};

// form序列化成url参数，并去除空值
$.fn.toParams = function() {
    return $(this).find(":input").filter(function () {
        return $.trim(this.value).length > 0
    }).serialize();
};

// 设置默认验证配置样式
/*$.validator.setDefaults({
    debug: true,
    errorElement: 'small',
    errorPlacement: function(error,element) {
        error.appendTo(element.parent("div").parent("div"));
    },
    highlight: function(element) {
        $(element).parent().parent().parent().addClass("has-error");
    },
    unhighlight: function(element) {
        $(element).parent().parent().parent().removeClass("has-error");
    }
});*/

// ajax验证登录超时
$.ajaxSetup({
    dataFilter: function(response) {
        if (response.indexOf('loginscreen') !== -1) {
            swal({
                title: "登录超时",
                type: "warning"
            }, function () {
                top.location.href = ctx + '/login';
            });
            return "";
        } else {
            return response;
        }
    }
});

$(function() {
    // 初始化日期组件
    //$('.datepicker')[0] && $('.datepicker').datepicker({
    //    //format : "yyyy-mm-dd",
    //    minViewMode : 'days',
    //    maxViewMode : 'years',
    //    language : "zh-CN",
    //    autoclose : true,
    //    todayHighlight: true,
    //    clearBtn : true
    //});

    // 初始化treeselect
    $('select.data-tree').each(function (i, select) {
        $(select).find('option[data-tree-parent]').each(function (i, n) {
            var prefix = '|';
            var len = $(n).attr('data-tree-parent').split('/').length - 1;
            for (var i = 1; i < len; i++) {
                prefix+= '--';
            }
            prefix += ' ';
            $(n).text(prefix + $(n).text());
        });
    });

    // 初始化select
    $('.chosen-select').chosen({
        width : "120px"
    });

    $.fn.getProps = function() {
        var columns = $(this).bootstrapTable('getOptions').columns[0];
        var props = [];
        columns.forEach(function(item) {
            props.push(item.field);
        })

        return props.join(",");
    }

});

/* 自定义对象  */
var UMALL = UMALL || {};
//正则校验
UMALL.regx = {
    isEmpty: function(value) {
        return value == "" || /^((\s.*)|(.*\s))$/.test(value);
    },

    digital: function(value) {
        return /^\+?[1-9][0-9]*$/.test(value);
    },

    number: function (value) {
        return /^(?:-?\d+|-?\d{1,3}(?:,\d{3})+)?(?:\.\d+)?$/.test(value);
    },

    isEmail: function(value) {
        return /^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/.test(value);
    },

    isIp: function(value) {
        if (value == "") {
            return true;
        }
        var ip = /^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;
        return (ip.test(value) && (RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256));
    }
};

UMALL.date = {
    //日期比较 begin < end => true
    compare : function (begin, end) {
        if (begin == undefined || end == undefined) {
            return true;
        }
        
        var ds = new Date(begin.replace(/\-/g, "\/"));
        var de = new Date(end.replace(/\-/g, "\/"));
        if (ds < de) {
            return true;
        } else {
            return false;
        }
    },
    //日期差值
    diffDay: function (begin, end) {
        if (begin == undefined || begin == "" || end == undefined || end == "") {
            return 0;
        }
        
        var ds = new Date(begin.replace(/\-/g, "\/"));
        var de = new Date(end.replace(/\-/g, "\/"));
        var diff = de.getTime() - ds.getTime()  //时间差的毫秒数  
        var days = Math.floor(diff / (24 * 3600 * 1000))
        return days;
    }
}


