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
					self.location.href = ctx + '/news/index.html';
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

var ue = UE.getEditor('container', {
	initialStyle: 'body{font-family: 宋体, SimSun; font-size: 16px;}',
	initialFrameWidth: 972,
	initialFrameHeight: 400
});




