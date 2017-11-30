//文字廣告跑馬燈
$('#cooper_run').cycle({
	fx : 'scrollRight',
	delay : -1000000
});

$('#cooper_run2').cycle({
	fx : 'scrollRight',
	delay : -1000000
});
//文字廣告跑馬燈結束





////model，彈出來用的
//function setBstModalMaxHeight(element) {
//	this.$element = $(element);
//	this.$modalContent = this.$element.find('.modal-content');
//	var $window = $(window);
//	var $modalContentOH = this.$modalContent.outerHeight();
//	var $modalContentIH = this.$modalContent.innerHeight();
//	var _modalBorderWidth = $modalContentOH - $modalContentIH;
//	var _modalDialogMargin = $window.width() < 768 ? 20 : 60;
//	var _modalContentHeight = $window.height()
//			- (_modalDialogMargin + _modalBorderWidth);
//	var _modalHeaderHeight = this.$element.find('.modal-header').outerHeight() || 0;
//	var _modalFooterHeight = this.$element.find('.modal-footer').outerHeight() || 0;
//	var _modalMaxHeight = _modalContentHeight
//			- (_modalHeaderHeight + _modalFooterHeight);
//
//	this.$modalContent.css({
//		'overflow' : 'hidden'
//	});
//
//	this.$element.find('.modal-body').css({
//		'max-height' : _modalMaxHeight,
//		'overflow-y' : 'auto'
//	});
//}
//
//$('.modal').on('show.bs.modal', function() {
//	$(this).show();
//	setBstModalMaxHeight(this);
//});
//
//$(window).resize(function() {
//	if ($('.modal.in').length != 0) {
//		setBstModalMaxHeight($('.modal.in'));
//	}
//});
//// end_model，彈出來用的