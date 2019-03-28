package cn.edu.swpu.info.college_website.common.tools;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.jd.image.common.ImageUpload;

/**
 * <b>描述：</b> <br/>
 * @author<b>创建人：</b> lishuangquan@jd.com<br/>
 * <b>创建时间：</b> 2016/1/15 10:45 <br/>
 * <b>修改人：</b> cdshenjian@jd.com<br/>
 * <b>时间：</b> 2016/1/15 10:45 <br/>
 * <b>修改内容：</b>  <br/>
 * @param
 */
public class ImageUtils {
	private static final Logger LOG = LoggerFactory.getLogger(ImageUtils.class);

	//申请图片上传服务码。
	 private static final String IMG_AUTH_CODE = "627916e50fbac04bf7090000d187905d";
	 private static final String IMG_APP_CODE = "picFridgeServer";
	 //smartcloud线上
	 //private static final String IMG_AUTH_CODE = "fca187170947704f0c08f910f6ac2079";
	 //private static final String IMG_APP_CODE = "smartcloud";
	//测试用
	//private static final String IMG_AUTH_CODE = "d3a313cb0d444049260ab6c0a6535f3c";
	//private static final String IMG_APP_CODE = "test";

	private static final String DOMAIN = "https://img30.360buyimg.com/";

	public static String uploadImg(byte[] buf, String size) {
		String imageUrl = null;
		try {
			LOG.info("图片上传:app-code="+IMG_APP_CODE);
			//imageUrl = ImageUpload.uploadFile(buf, IMG_AUTH_CODE);
			JSONArray array = JSONArray.parseArray(imageUrl);
			JSONObject obj = array.getJSONObject(0);
			if (!Strings.isNullOrEmpty(imageUrl)) {
				imageUrl = (String) obj.get("msg");
				if (Strings.isNullOrEmpty(size)) {
					//访问原图
					imageUrl = DOMAIN + IMG_APP_CODE + "/" + imageUrl;
				} else {
					imageUrl = DOMAIN + IMG_APP_CODE + "/" + size + "_" + imageUrl;
				}
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return imageUrl;
	}

	public static String resize(String imgUrl, int width, int height) {
		if (Strings.isNullOrEmpty(imgUrl)) {
			return null;
		}

		if (!imgUrl.startsWith(DOMAIN + IMG_APP_CODE)) {
			throw new IllegalArgumentException("invalid image url, imgUrl=" + imgUrl);
		}

		return DOMAIN + IMG_APP_CODE + "/" + "s" + width + "x" + height + "_" + imgUrl.substring(imgUrl.indexOf("jfs/"));
	}

}
