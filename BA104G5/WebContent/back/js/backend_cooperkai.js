//model，彈出來用的
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
// end_model，彈出來用的

// 管理促銷資訊裡面的月曆js
// 開始
$(function() {
	$("#promo_period_from").datepicker(
			{
				// defaultDate: "+1w",
				changeMonth : true,
				numberOfMonths : 2,
				showButtonPanel : true,
				maxDate : "+1M +10D",
				minDate : 0,
				changeYear : true,
				dateFormat : 'yy-mm-dd', // 自己加的轉格式用，預設是//yy/mm/dd
				onClose : function(selectedDate) {
					$("#promo_period_to").datepicker("option", "minDate",
							selectedDate);
				}
			});
	// 到什麼時候
	$("#promo_period_to").datepicker(
			{
				// defaultDate: "+1w",
				changeMonth : true,
				numberOfMonths : 2,
				showButtonPanel : true,
				maxDate : "+1M +10D",
				minDate : 0,
				changeYear : true,
				dateFormat : 'yy-mm-dd', // 自己加的轉格式用，預設是//yy/mm/dd
				onClose : function(selectedDate) {
					$("#promo_period_from").datepicker("option", "maxDate",
							selectedDate);
				}
			});
});
// end_管理促銷資訊裡面的月曆js

// 切換管理新聞不同表格用
// 管理促銷資訊
$(document).ready(function() {
	$(".promo_table").click(function() {
		$(".promo_top").toggle();
	});
});
// 管理房市最新消息
$(document).ready(function() {
	$(".house_table").click(function() {
		$(".house_top").toggle();
	});
});
// 管理系統公告
$(document).ready(function() {
	$(".ann_table").click(function() {
		$(".ann_top").toggle();
	});
});
// end_切換管理新聞不同表格用

// 字數過多，縮減字數用
$(document).ready(function() {
	$(".text_overflow").each(function() {
		var maxwidth = 10;// 限制字符个数
		if ($(this).text().length > maxwidth) {
			$(this).text($(this).text().substring(0, maxwidth));
			$(this).html($(this).html() + '...');
		}
	});
});

//促銷照片預覽用
function readURL(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();

		reader.onload = function(e) {
			$('#imgpreview').attr('src', e.target.result);
		};

		reader.readAsDataURL(input.files[0]);
	}
}

