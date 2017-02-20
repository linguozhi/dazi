
/**
 * 系统共用类
 *
 * @author guice_lin@126.com
 * @since 2016-05-31
 * @version 1.0.0
 */

var windows = {
	// URL跳转
	go : function(src) {
		window.location.href = src;
	}
};

/**
 * 跳转到指定
 * @param url
 */
goto_menu = function(url) {
    parent.goto_menu_from_main(url);
}

/**
 * 弹窗效果，更多弹窗效果可直接使用dialog
 * http://aui.github.io/artDialog/doc/index.html#option-fixed
 */
var popup = {
	/**
	 * 加载框
	 */
	loading : function(t) {
		var text = t || '努力加载中...';
		var html = $('<div id="ajaxTip" class="ajax-tip"><img src="' + WEBROOT +  '/public/images/common/loading.gif" align="absmiddle">&nbsp;<span></span></div>');

		var $ajaxTip = $('#ajaxTip');

		if (!$ajaxTip.length) {
			$('body').append(html);
			$ajaxTip = $('#ajaxTip');
		}

		$ajaxTip.find('span').html(text);

		return $ajaxTip;
	},
	/**
	 * 提示框
	 */
	tip : function(c) {
		var content = c || '欢迎使用';
		var d = dialog({
			title : '提示',
			content : content
		});
		// d.show();  // 没有遮盖层
		d.showModal();
	},

	conver : function(c, clk) {
		var d = dialog({
			content : c
		});

		d.showModal();
		$("body").delegate("div", "click", function() {
			d.close().remove();
			clk();
		});
	},

	block : function(c) {
		var d = dialog({
			content : c
		});
		return d;
	},

	/**
	 * 提示框并自动关闭
	 */
	tipAndClose : function(c) {
		var content = c || '欢迎使用';
		var d = dialog({
			title : '提示',
			content : content
		});
		// d.show(); // 没有遮盖层
		d.showModal();
		setTimeout(function() {
			d.close().remove();
		}, 2000);
	},
	/**
	 * 确认弹窗
	 */
	confirm : function(title, content, ok, cancel, okValue, cancelValue) {
		var d = dialog({
			title : title,
			content : content,
			okValue : okValue || '确定',
			ok : function() {
				if (ok)
					return ok();
			},
			cancelValue : cancelValue || '取消',
			cancel : function() {
				if (cancel)
					return cancel();
			},
			zIndex: 2000
		});
		d.showModal();
	},
	/**
	 * 气泡浮层
	 *
	 * @param id
	 *            浮点元素ID
	 * @param content
	 *            显示内容
	 * @returns
	 */
	bubble : function(id, content) {
		if (utils.empty(id)) {
			return;
		}
		var d = dialog({
			content : content,
			quickClose : true
		// 点击空白处快速关闭
		});
		d.show(document.getElementById(id));
	},
	/**
	 * 气泡浮层
	 * @param id	浮点元素ID
	 * @param content	显示内容
	 * @returns
	 */
	model : function(id, content) {
		if (utils.empty(id)) {
			return;
		}
		var d = dialog({
			content : content,
			quickClose : true
		// 点击空白处快速关闭
		});
		d.show(document.getElementById(id));
	}
}

//更新iframe高度
function updateFrameHeight() {
    var iframe = window.parent.document.getElementById('iframeMain');

    if(iframe != null) {
        var viewHeight = $(window.top).height() - 54;
        var pageHeight = $(document.body).outerHeight(true);
        iframe.height = viewHeight > pageHeight ? viewHeight : pageHeight;
    }
}

/**
 * 获得已经选择checkbox的ids
 * @param element      checkbox元素的name属性
 * @returns {string}
 */
function getCheckboxs(element) {
    var ids = "";
    $ck = $("input[name='" + element+"']");
    $ck.each(function() {
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
}