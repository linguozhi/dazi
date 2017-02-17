/**
 * Created by linguozhi@52tt.com on 2016/11/30.
 */

$(function(){
    init();
})

init = function() {
    $("#btnSearch").click(doSearch);
    $("#btnExport").click(doExport);
}

/**
 * 导出Excel
 */
doExport = function() {
    var props = $("#dataTable").getProps();
    // 去掉第一个id
    props = props.replace(/^id,/, "").replace(/,operate$/, "");
    $("#props").val(props);

    //
    $("#beginTime").val($("#beginTime").val() ? $("#beginTime").val()+" 00:00:00" : null);
    $("#endTime").val( $("#endTime").val() ? $("#endTime").val() + " 23:59:59" : null);

    jQuery("#searchForm").attr("action", ctx +"/statisVoucherBuy/export.shtml");
    jQuery("#searchForm").submit();
}

doSearch = function() {
    $('#dataTable').bootstrapTable('refresh');
}

/**
 * table 按钮列
 * @param value
 * @param row
 * @returns {string}
 */
operateFormatter =  function (value, row) {
	if(row.id){
		return '<button class="btn btn-sm btn-info op-edit">编辑</button> '
        + '<button class="btn btn-sm btn-info op-detail">查看详情</button> ';
	}else{
		return "";
	}
}

operateEvents = {
    'click .op-detail': function (e, value, row, index) {
        layer_show_full('detail.html?id=' + row.id);
    },
    'click .op-edit' : function(e, value, row, index) {
        layer_show_full('edit.html?id=' + row.id );
    }
}

/**
 * table search
 */
queryParams = function(params){
    return $.extend(params, {
        activeId : $("#activeId").val(),
        activeName : $("#activeName").val(),
        gameId : $("#gameId").val(),
        dayTimeStart : $("#beginTime").val() ? $("#beginTime").val()+" 00:00:00" : null,
        dayTimeEnd : $("#endTime").val() ? $("#endTime").val() + " 23:59:59" : null
    })
}

