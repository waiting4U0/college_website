// JavaScript Document
var main={};

main.homepageGoTo=function(url1,url2,url3,param){
	$.post(url1, param, function(data) {
		$('#main-page-content').html(data);

		document.body.scrollTop=0;
	});
	$.post(url2, param, function(data) {
		$('#main-page-content').append(data);

		document.body.scrollTop=0;
	});
	$.post(url3, param, function(data) {
		$('#main-page-content').append(data);

		document.body.scrollTop=0;
	});
};

main.menuGoTo=function(url,param){
	$.post(url, param, function(data) {
	  $('#main-page-content').html(data);	

	  document.body.scrollTop=0;
	});
};