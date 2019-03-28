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
		listPageJS.getPageList("/analysisuserbh01/listfriendsop",pageNum * 1,init_pageSize);
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
			startIndex:_page,
			pageSize:_pageSize,
			s_uuid:$("#s_uuid").val(),
			s_user_pin:$("#s_user_pin").val(),
			s_op_type:$("#s_op_type").val(),
			s_start_date:$("#s_start_date").val(),
			s_end_date:$("#s_end_date").val()
	};
    main.menuGoTo(_url,jsonData);  
};