$(document).ready(function() {
	// 初始化
	loadInit();

	$("form").submit(function () {
		// 操作
		var loading = showLoading();

		$.ajax({
			type: 'post',
			url: $('form').attr('action'),
			data: $('form').serialize(),
			success: function (result) {
				layer.close(loading);
				if (protocols.isSuccess(result)) {
					self.location.href = ctx + '/column/index.html';
				} else {
					swal({title:"保存失败!", type: "error"});
					console.log(protocols.getMessage(result));
				}
			},
			dataType: 'json'
		});
	});
});

loadInit = function() {
	// do something
}




