/**
 * VehGroupMainRestServiceImpl.java	  V1.0   2019年10月9日 下午5:15:56
 *
 * Copyright 2019 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.tscloud.portal.restful.trueMapCamera.impl;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ws.rs.Path;

import org.springframework.stereotype.Controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tscloud.common.framework.dubbo.DubboBaseInterface;
import com.tscloud.common.framework.rest.impl.BaseRestServerInterfaceImpl;
import com.tscloud.common.utils.StringUtils;
import com.tscloud.domain.resource.entity.trueMapCamera.TrueMapCamera;
import com.tscloud.domain.resource.provider.trueMapCamera.ITrueMapCameraProvider;
import com.tscloud.domain.resource.service.trueMapCamera.ITrueMapCameraService;
import com.tscloud.portal.dto.trueMapCamera.TrueMapCameraDTO;
import com.tscloud.portal.restful.trueMapCamera.ITrueMapCameraRestServer;
import com.tscloud.portal.utils.ConstantsUtil;
import com.tscloud.portal.utils.MessageConst;
import com.tscloud.portal.utils.ResultHelp;
/**
 * 
 * 功能描述：
 *
 * @author ：何佳杰
 *
 * 修改历史：(修改人，修改时间，修改原因/内容)
 */
@Controller
@Path(ConstantsUtil.RestPathPrefix.IMAGE3DRESOURCESERVICE)
public class TrueMapCameraRestServerImpl extends BaseRestServerInterfaceImpl<TrueMapCamera> implements ITrueMapCameraRestServer{
	
	@Reference
    private ITrueMapCameraProvider provider;

	@Override
	public DubboBaseInterface<TrueMapCamera> getDubboBaseInterface() {
		return provider;
	}
	@Override
	public String GetImageInfo(String imageID) {
	    String result = "";
	    List<TrueMapCamera> list = new ArrayList<TrueMapCamera>() ;
	    JSONObject object = new JSONObject() ;
        object = ResultHelp.setResult("0",MessageConst.FAILUREMESSAGE,MessageConst.GetImageInfo.SIZES,list) ;
	    try {
	      if (StringUtils.isNotBlank(new String[] { imageID })) {
	    	imageID = imageID.split("-")[0]  ;
	    	Map<String,Object> map = new HashMap<String,Object>() ;
	    	map.put("vid", imageID) ;
	        list = provider.findByMap(map);
	        object = ResultHelp.setResult("1",MessageConst.GetImageInfo.SUCCESSMESSAGE,MessageConst.GetImageInfo.SIZES,list) ;
	      }
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	this.log.info("TrueMapCameraRestServiceImpl："+e.getMessage());
	    }
	    result = JSON.toJSONString(object);
	    return result;
	}
	
	@Override
	public void GetImageTile(String jsonStr) {
		PrintWriter writer = null ;
		Date dt = new Date();
		dt.setYear(dt.getYear() + 10);
		try {
			response.setHeader("Expires", dt.toGMTString());
			TrueMapCameraDTO trueMapCameraDTO = JSON.parseObject(jsonStr,TrueMapCameraDTO.class) ;
			String imageId = trueMapCameraDTO.getImageId() ;
			String row = trueMapCameraDTO.getRow() ;
			String col = trueMapCameraDTO.getCol() ;
			String zoom = trueMapCameraDTO.getZoom() ;
			String clientType = trueMapCameraDTO.getClientType() ;
			//参数验证
			if (StringUtils.isBlank(imageId ) || StringUtils.isBlank(row) || StringUtils.isBlank(col) ||
					StringUtils.isBlank(zoom)|| StringUtils.isBlank(clientType)
					) {
				writer = response.getWriter();
				writer.println("{\"Status\":\"0\",\"Message\":\"请求参数填写不完整！\"}");
				writer.flush();
				writer.close();
				return ;
			}
			byte[] bts = provider.getImageTile(imageId, Integer.valueOf(zoom), Integer.valueOf(col), Integer.valueOf(row));
			//将输入流返回
			if (bts != null) {//获取影像成功
				response.setContentType("image/png");
				OutputStream out = response.getOutputStream();
				out.write(bts);
				out.flush();
				out.close();
			} else {
				//获取影像失败
				writer = response.getWriter();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				writer.println("{\"Status\":\"0\",\"Message\":\"获取切片失败！\"}");
				writer.flush();
				writer.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Override
	public void GetImageTileSize(String clientType) {
		PrintWriter writer = null ;
		try{
			writer = response.getWriter();
			//参数验证
			if (StringUtils.isBlank(clientType )){
				writer.println("{\"Status\":\"0\",\"Message\":\"请求参数填写不完整！\",\"ImageTileSize\":\"\"}");
			}
			if("1".equals(clientType)){
				//手机用户
				writer.println("{\"Status\":\"1\",\"Message\":\"影像切片尺寸获取成功。\",\"ImageTileSize\":\"256*256\"}");
			}else{
				//互联网用户
				writer.println("{\"Status\":\"1\",\"Message\":\"影像切片尺寸获取成功。\",\"ImageTileSize\":\"512*512\"}");
			}
			writer.flush();
			writer.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Override
	public String GetImageSize(String ImageID) {
		String result = "";
	    JSONObject object = new JSONObject() ;			
        object = ResultHelp.setResult("0",MessageConst.GetImageSize.FAILUREMESSAGE,MessageConst.GetImageSize.IMAGESIZE,"") ;
		try {
			if(StringUtils.isNotBlank(new String[] { ImageID })){
				String[] ss = ImageID.split("-") ;
				if(ss.length == 3){
					String vid =ss[0] ;
					String cameraNo =ss[1] ;
					String dateFormat =ss[2] ;
					//日期验证下长度，防止长度不够,正常是不会出问题
					if(dateFormat.length() >=14){
						String size = provider.getImageSize(vid,cameraNo,dateFormat) ;
						object = ResultHelp.setResult("1",MessageConst.GetImageSize.SUCCESSMESSAGE,MessageConst.GetImageSize.IMAGESIZE,size) ;
					}
				}
			}
		} catch (Exception e) {
	    	e.printStackTrace();
	    	this.log.info("TrueMapMarkerRestServiceImpl："+e.getMessage());
	    }
	 	result = JSON.toJSONString(object);
	 	return result;
	}
	
}
