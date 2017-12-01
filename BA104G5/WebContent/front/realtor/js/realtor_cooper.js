//隱藏表格用
$(document).ready(function() {
	$("#realtor_info").hide();
	$("#realtor_info2").hide();
	$(".tabs-menu a").click(function(event) {
		event.preventDefault();
		var tab = $(this).attr("href");
		$(".tab-grid").not(tab).css("display", "none");
		$(tab).fadeIn("slow");
	});
});

// modale用
function setBstModalMaxHeight(element) {
	this.$element = $(element);
	this.$modalContent = this.$element.find('.modal-content');
	var $window = $(window);
	var $modalContentOH = this.$modalContent.outerHeight();
	var $modalContentIH = this.$modalContent.innerHeight();
	var _modalBorderWidth = $modalContentOH - $modalContentIH;
	var _modalDialogMargin = $window.width() < 768 ? 20 : 60;
	var _modalContentHeight = $window.height()
			- (_modalDialogMargin + _modalBorderWidth);
	var _modalHeaderHeight = this.$element.find('.modal-header').outerHeight() || 0;
	var _modalFooterHeight = this.$element.find('.modal-footer').outerHeight() || 0;
	var _modalMaxHeight = _modalContentHeight
			- (_modalHeaderHeight + _modalFooterHeight);

	this.$modalContent.css({
		'overflow' : 'hidden'
	});

	this.$element.find('.modal-body').css({
		'max-height' : _modalMaxHeight,
		'overflow-y' : 'auto'
	});
}

$('.modal').on('show.bs.modal', function() {
	$(this).show();
	setBstModalMaxHeight(this);
});

$(window).resize(function() {
	if ($('.modal.in').length != 0) {
		setBstModalMaxHeight($('.modal.in'));
	}
});
// modale用

// 房仲神奇小按鈕的script 開始
$(document).ready(function() {
	$("#magicBtn1").click(function() {
		$("#rtr_name").val("大俠");
		$("#rtr_id").val("realtor000@gmail.com");
		$("#rtr_psw").val("123456");
		$("#rtr_psw2").val("123456");
		$("#rtr_area").val("中正區");
		$("#rtr_idno").val("A146945341");
		$("#rtr_intro").val("大俠愛吃漢堡堡");
		$("#aggrement2").attr("checked", "enable");
	});
});
// 房仲神奇小按鈕的script 結束

// 房仲照片預覽用
function readURL(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();

		reader.onload = function(e) {
			$('#imgpreview').attr('src', e.target.result);
		};

		reader.readAsDataURL(input.files[0]);
	}
}

//房仲搜尋用
// 加入最愛
(function() {
	$(document).on("click", ".heart_main", function(e) {
		e.preventDefault();
		$(this).toggleClass("active");
	});
})();

// 解決文字overflow
$(document).ready(function() {
	// 限制字符个数
	$(".text_overflow").each(function() {
		var maxwidth = 100;
		if ($(this).text().length > maxwidth) {
			$(this).text($(this).text().substring(0, maxwidth));
			$(this).html($(this).html() + '...');
		}
	});
});
