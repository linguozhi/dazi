(function() {
    $('#sidebar [data-target]').on('click.nav', function() {
//        var $this = $(this);
//
//        $this.closest('.nav-list').find('.active').removeClass('active');
//        $this.closest('li').addClass('active').parents('li').addClass('open active');
//        $this.closest('li').parents('li').siblings().find('.submenu').slideUp(200);
//        $this.closest('li').parents('li').siblings().removeClass('open').find('.open').removeClass('open');
//
//        $('.main').html('<iframe src="' + $(this).data('target') + '" id="iframeMain" name="iframeMain" frameBorder="0" scrolling="no" onload="updateFrameHeight()"></iframe>');

        goto_menu(this);
    });

    $(window).resize(function() {
        updateFrameHeight();
    });
})();

function goto_menu(menu) {
    var $this = $(menu);

    $this.closest('.nav-list').find('.active').removeClass('active');
    $this.closest('li').addClass('active').parents('li').addClass('open active');
    $this.closest('li').parents('li').siblings().find('.submenu').slideUp(200);
    $this.closest('li').parents('li').siblings().removeClass('open').find('.open').removeClass('open');

    $('.main').html('<iframe src="' + $(menu).data('target') + '" id="iframeMain" name="iframeMain" frameBorder="0" scrolling="no" onload="updateFrameHeight()"></iframe>');
}

/**
 * 跳转到指定的URL
 * @param url
 */
function goto_menu_from_main(url) {
    var menu = jQuery("a[data-target='" + url + "']");
    goto_menu(menu);
}

// iframe自适应高度
function updateFrameHeight() {
    var iframe = document.getElementById("iframeMain");
    var iframePage = document.frames ? document.frames["iframeMain"].document : iframe.contentDocument;

    if(iframe != null && iframePage != null) {
        var viewHeight = $(window).height() - $('.header').outerHeight() - 4;
        var pageHeight = $(iframePage.body).outerHeight(true);
        iframe.height = viewHeight > pageHeight ? viewHeight : pageHeight;
    }
}