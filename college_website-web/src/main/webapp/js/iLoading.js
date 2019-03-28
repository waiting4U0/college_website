/*--------------------------------------------
* Desc:   加载提示等待窗口;
* Date:   2014/10/28;
* Author: luojinsong@jd.com;
---------------------------------------------*/
var iLoading = iLoading || {
	/*--------------------------------------------
	 * Desc: 显示提示框;
	 * arg: 无;
	 * ret: 无
	 * Date:   2014/10/28;
	 ---------------------------------------------*/
	show : (function()
	{
		if($(".overlay").css('display') != "none")
		{
			return;
		}
		$("#AjaxLoading").css("display", "");
		var h = $(document).height();
		$(".overlay").css({"height": h });	
		$(".overlay").css({'display':'block','opacity':'0.5','zIndex':'999998'});
		//$(".showbox").stop(true).animate({'margin-top':'250px','opacity':'1'},200);
		$(".showbox").css({'margin-top':'250px','opacity':'1','zIndex':'999999'});
		$("#AjaxLoading").css("display", "");
	}),
	/*--------------------------------------------
	 * Desc: 关闭加载窗口;
	 * arg: 无;
	 * ret: 无
	 * Date:   2014/10/28;
	 ---------------------------------------------*/
	close : (function ()
	{
		try
		{
			$(".showbox").css({'display':'none','opacity':'0'});
			$(".overlay").css({'display':'none','opacity':'0'});
		}
		catch(e)
		{
		}
	})
};