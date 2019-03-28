// JavaScript Document
var main={};

main.menuGoTo=function(url,param){
	$.post(url, param, function(data) {
	  $('#main-page-content').html(data);	

	  document.body.scrollTop=0;
	});
};