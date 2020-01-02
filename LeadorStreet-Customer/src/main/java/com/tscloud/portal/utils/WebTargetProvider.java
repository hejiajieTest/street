package com.tscloud.portal.utils;

import com.tscloud.common.framework.config.ConfigHelper;
import com.tscloud.portal.utils.debug.WebTargetMocker;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;


public class WebTargetProvider {

    private static String URL = "http://[ip]:[port]/[service]/rest/";

    public static WebTarget getWebResource(String ip, String port) {
        String url = URL.replace("[ip]", ip).replace("[port]", port).replace("[service]","cloud");
        return ClientBuilder.newClient().target(url);
    }

    public static WebTarget getWebResource(String ip, String port,String service) {
        if ("DEV".equals(ConfigHelper.getValue("App.RunMode"))) {
            return new WebTargetMocker();
        } else {
            String url = URL.replace("[ip]", ip).replace("[port]", port).replace("[service]",service);
            return ClientBuilder.newClient().target(url);
        }
    }

    public static WebTarget getWebResource(String url) {
        return ClientBuilder.newClient().target(url);
    }

}
