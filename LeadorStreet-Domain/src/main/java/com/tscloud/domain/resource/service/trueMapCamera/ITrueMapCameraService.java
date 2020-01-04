/**
 * IUserPermissionService.java	  V1.0   2019年9月20日 下午2:34:03
 *
 * Copyright 2019 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.tscloud.domain.resource.service.trueMapCamera;

import java.util.List;
import java.util.Map;

import com.tscloud.common.framework.Exception.ServiceException;
import com.tscloud.common.framework.service.IBaseInterfaceService;
import com.tscloud.domain.resource.entity.trueMapCamera.MeasurePara;
import com.tscloud.domain.resource.entity.trueMapCamera.TrueMapCamera;

public interface ITrueMapCameraService extends IBaseInterfaceService<TrueMapCamera> {


	byte[] getImageTile(String imageID, int zoom, int col, int row) throws ServiceException;

	String queryMatchImage(String vid,String cameraNo,String dateFormat,String stationGuid) throws ServiceException;

	List<Map<String, Object>> pixel2Coord(String coortype, String prjtype, List<MeasurePara> list) throws ServiceException;

	String getImageSize(String vid, String cameraNo, String dateFormat) throws ServiceException;


}
