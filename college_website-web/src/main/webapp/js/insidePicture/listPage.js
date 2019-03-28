/**
 * listPageJS对象;
 * @type {{}}
 * @author fengsonghao
 * @since 2016-01-05
 */
 
var listPageJS = {};
var init_pageSize=100;
/**
 * 分页;
 * @param pageNum
 */
listPageJS.turnPage =  function(pageNum)
{
	if(!isNaN(pageNum))
	 {
		listPageJS.getPageList("/insidePicture/listInsidePictures",pageNum * 1,init_pageSize);
	 }
};
listPageJS.turnPageCommon =  function(url,pageNum,pageSize)
{
	 if(!isNaN(pageNum))
	 {
		  listPageJS.getPageList(url,pageNum * 1,pageSize*1);
	 }
};
listPageJS.getPageList = function(_url,_page,_pageSize) 
{
	var jsonData =  {
		startIndex : _page,
		pageSize : _pageSize,
		s_pad_sn:$("#s_pad_sn").val(),
		s_device_sn:$("#s_device_sn").val(),
		s_uuid:$("#s_uuid").val(),
		s_city:$("#s_city").val(),
		s_start_upload_date:$("#s_start_upload_date").val(),
		s_end_upload_date:$("#s_end_upload_date").val()
    };
    main.menuGoTo(_url,jsonData);  
};
