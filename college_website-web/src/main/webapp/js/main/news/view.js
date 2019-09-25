$(function(){
	var $actions = $('.actions')
	
	$actions.hover(function(){
		$(this).removeClass('action2s')
	},function(){
		$(this).addClass('action2s')
	})
})
