package com.tscloud.resource.dubboservices.trueMapCamera;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.tscloud.common.framework.Exception.DubboProviderException;
import com.tscloud.common.framework.Exception.ServiceException;
import com.tscloud.common.framework.dubbo.impl.DubboBaseInterfaceImpl;
import com.tscloud.common.framework.service.IBaseInterfaceService;
import com.tscloud.domain.resource.entity.manualInfo.ManualInfo;
import com.tscloud.domain.resource.entity.trueMapCamera.MeasurePara;
import com.tscloud.domain.resource.entity.trueMapCamera.TrueMapCamera;
import com.tscloud.domain.resource.provider.manualInfo.IManualInfoProvider;
import com.tscloud.domain.resource.provider.trueMapCamera.ITrueMapCameraProvider;
import com.tscloud.domain.resource.service.manualInfo.IManualInfoService;
import com.tscloud.domain.resource.service.trueMapCamera.ITrueMapCameraService;


@Service
public class TrueMapCameraProviderImpl extends DubboBaseInterfaceImpl<TrueMapCamera> implements ITrueMapCameraProvider {

    @Autowired
    private ITrueMapCameraService trueMapCameraService;
    
    @Override
    public IBaseInterfaceService<TrueMapCamera> getBaseInterfaceService() {
        return trueMapCameraService;
    }

	@Override
	public byte[] getImageTile(String imageID, int zoom, int col, int row) throws DubboProviderException {
		byte[] b = null ;
        try {
        	b = this.trueMapCameraService.getImageTile(imageID,zoom,col,row) ;
        } catch (ServiceException e) {
        	throw new DubboProviderException(e.getMessage(), e);
        }
        return b;
	}

	@Override
	public String queryMatchImage(String vid, String cameraNo, String dateFormat, String stationGuid)
			throws DubboProviderException {
		String str = "" ;
        try {
        	str = this.trueMapCameraService.queryMatchImage(vid,cameraNo,dateFormat,stationGuid) ;
        } catch (ServiceException e) {
        	throw new DubboProviderException(e.getMessage(), e);
        }
        return str;
	}

	@Override
	public List<Map<String, Object>> pixel2Coord(String coortype, String prjtype, List<MeasurePara> list)
			throws DubboProviderException {
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>() ;
        try {
        	returnList = this.trueMapCameraService.pixel2Coord(coortype,prjtype,list) ;
        } catch (ServiceException e) {
        	throw new DubboProviderException(e.getMessage(), e);
        }
        return returnList;
	}

	@Override
	public String getImageSize(String vid, String cameraNo, String dateFormat) throws DubboProviderException {
		String str = "" ;
        try {
        	str = this.trueMapCameraService.getImageSize(vid,cameraNo,dateFormat) ;
        } catch (ServiceException e) {
        	throw new DubboProviderException(e.getMessage(), e);
        }
        return str;
	}

	
}
