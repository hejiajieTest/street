package com.tscloud.portal.restful.manualInfo.impl;

import javax.ws.rs.Path;

import org.springframework.stereotype.Controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tscloud.common.framework.Constants;
import com.tscloud.common.framework.dubbo.DubboBaseInterface;
import com.tscloud.common.framework.rest.impl.BaseRestServerInterfaceImpl;
import com.tscloud.domain.resource.entity.manualInfo.ManualInfo;
import com.tscloud.domain.resource.provider.manualInfo.IManualInfoProvider;
import com.tscloud.portal.restful.manualInfo.IManualInfoRestServer;

@Controller
@Path(Constants.RestPathPrefix.SYSTEM + "manualInfoRestServer")
public class ManualInfoRestServerImpl extends BaseRestServerInterfaceImpl<ManualInfo> implements IManualInfoRestServer {

    @Reference
    private IManualInfoProvider provider;

    @Override
    public DubboBaseInterface<ManualInfo> getDubboBaseInterface() {
        return provider;
    }

}
