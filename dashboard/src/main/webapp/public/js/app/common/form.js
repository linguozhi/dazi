/**
 * 全局常用的表单行为
 *
 * @author guice_lin@126.com
 * @date 2016-05-31
 */

(function($) {

	$(function() {

		/**
		 * 给所有搜索栏的表单文本框增加回车提交事件
		 * 注意提交使用的是默认的"doQuery"函数
		 */
		$(".searchbar>form[role=form]>input[type=text]").keypress( function(event) {
			if ( event.keyCode == 13 && $.isFunction(doQuery)) {
				doQuery();
				return false;
			};
		});

	});

})(jQuery);
