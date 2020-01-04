package com.tscloud.resource.utils;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 * Created by sticver on 14-12-22.
 */
public class WebTargetProvider {

    private static String URL = "http://[ip]:[port]/[service]/rest/";

    public static WebTarget getWebResource(String ip, String port) {
        String url = URL.replace("[ip]", ip).replace("[port]", port).replace("[service]","cloud");
        return ClientBuilder.newClient().target(url);
    }

    public static WebTarget getWebResource(String ip, String port,String service) {
        String url = URL.replace("[ip]", ip).replace("[port]", port).replace("[service]",service);
        return ClientBuilder.newClient().target(url);
    }

}
