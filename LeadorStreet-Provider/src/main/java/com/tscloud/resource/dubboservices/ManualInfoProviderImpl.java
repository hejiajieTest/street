package com.tscloud.resource.dubboservices;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.tscloud.common.framework.dubbo.impl.DubboBaseInterfaceImpl;
import com.tscloud.common.framework.service.IBaseInterfaceService;
import com.tscloud.domain.resource.entity.manualInfo.ManualInfo;
import com.tscloud.domain.resource.provider.manualInfo.IManualInfoProvider;
import com.tscloud.domain.resource.service.manualInfo.IManualInfoService;


@Service
public class ManualInfoProviderImpl extends DubboBaseInterfaceImpl<ManualInfo> implements IManualInfoProvider {

    @Autowired
    private IManualInfoService manualInfoService;
    
    @Override
    public IBaseInterfaceService<ManualInfo> getBaseInterfaceService() {
        return manualInfoService;
    }

	
}
