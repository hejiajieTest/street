package com.tscloud.resource.service.manualInfo.impl;



import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tscloud.common.framework.mapper.BaseInterfaceMapper;
import com.tscloud.common.framework.service.impl.BaseInterfaceServiceImpl;
import com.tscloud.domain.resource.entity.manualInfo.ManualInfo;
import com.tscloud.domain.resource.service.manualInfo.IManualInfoService;
import com.tscloud.resource.mapper.manualInfo.ManualInfoMapper;

@Service 
public class ManualServiceImpl extends BaseInterfaceServiceImpl<ManualInfo> implements IManualInfoService{

    @Resource
	public ManualInfoMapper manualInfoMapper ;

	@Override
	public BaseInterfaceMapper<ManualInfo> getBaseInterfaceMapper() {
		return manualInfoMapper;
	}

}
