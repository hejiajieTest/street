package com.tscloud.portal.restful.trueMapCamera;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.tscloud.common.framework.rest.BaseRestServerInterface;

/**
 * 
 * 功能描述：
 *
 * @author ：何佳杰
 *
 * 修改历史：(修改人，修改时间，修改原因/内容)
 */
public interface ITrueMapCameraRestServer extends BaseRestServerInterface{
	
	  @GET
	  @Path("GetImageInfo")
	  public String GetImageInfo(@QueryParam("ImageID") String paramString);
	  
	  @POST
	  @Path("GetImageTile")
	  @Consumes({"application/json"})
	  @Produces({"application/json"})
	  public void GetImageTile(String jsonStr);
	
	  @GET
	  @Path("GetImageTileSize")
	  public void GetImageTileSize(@QueryParam("clientType") String clientType);
	  
	  @GET
	  @Path("GetImageSize")
	  public String GetImageSize(@QueryParam("ImageID") String paramString);
}
