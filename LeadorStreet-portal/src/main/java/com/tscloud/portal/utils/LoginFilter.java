package com.tscloud.portal.utils;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.WebTarget;

import com.alibaba.fastjson.JSON;
import com.tscloud.common.framework.Constants;
import com.tscloud.common.framework.config.ConfigHelper;
import com.tscloud.common.framework.domain.entity.manager.User;
import com.tscloud.common.framework.rest.view.JsonViewObject;
import com.tscloud.common.utils.StringUtils;

/**
 * Created by Administrator on 2015/11/26 0026.
 */
public class LoginFilter implements Filter {
    private Pattern pattern;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String ignoreUrl = filterConfig.getInitParameter("ignoreUrl");
        pattern = Pattern.compile(ignoreUrl);
        filterConfig.getServletContext().setAttribute("staticfileurl", ConfigHelper.getValue("staticfileurl"));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        String requestPath = request.getServletPath();
        HttpSession session = request.getSession();
        String username = request.getRemoteUser();
        String managerIp = ConfigHelper.getValue("TrueCloudManager.ip");
        String managerPort = ConfigHelper.getValue("TrueCloudManager.port");
        String managerServer = ConfigHelper.getValue("TrueCloudManager.server");

        //不需要处理的静态文件直接忽略
        if (pattern.matcher(requestPath).find()) {
            chain.doFilter(servletRequest, servletResponse);
            return;
        }
        if (!StringUtils.isBlank(username)) {
            Object user = session.getAttribute("user");

            if (null == user) {

                //调用CA提供的接口获取这个用户的信息,并存入session
                WebTarget client = WebTargetProvider.getWebResource(managerIp, managerPort, managerServer)
                        .path(Constants.RestPathPrefix.CA + "caUserRestServer")
                        .path("getByLoginName")
                        .queryParam("loginName", username);

                String userJsonStr = client.request().get(String.class);
                JsonViewObject jsonObj = JSON.parseObject(userJsonStr, JsonViewObject.class);
                User loginUser = JSON.parseObject(jsonObj.getContent(), User.class);

                session.setAttribute(Constants.user.COOKIE_USER, loginUser);
                session.setAttribute("userJsonStr",userJsonStr);

                session.setAttribute("user", username);
            }
        } else {
            //跳转到登陆页面
            //由于受到CAS Filter的控制,此处的username不可能为空,不需要处理此流程
        }

        chain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
