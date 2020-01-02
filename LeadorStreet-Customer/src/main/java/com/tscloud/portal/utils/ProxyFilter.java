//package com.tscloud.portal.utils;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.tscloud.common.framework.config.ConfigHelper;
//import com.tscloud.common.framework.rest.view.JsonViewObject;
//import com.tscloud.common.utils.StringUtils;
//import com.tscloud.domain.resource.entity.HttpClientEntity;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.OutputStream;
//
///**
// * Created by Administrator on 2015/11/26 0026.
// */
//public class ProxyFilter implements Filter {
//    private  static  final Logger logger = LoggerFactory.getLogger(ProxyFilter.class);
//    private static String startDate;
//    private static String endDate;
//    private static String kafkaIp;
//    private static String ip;
//    private static String serviceName;
//    private static String orgName;
//    private static String orgId;
//    private static String username;
//    private static boolean status = false;
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest servletRequest = (HttpServletRequest) request;
//        HttpServletResponse servletResponse = (HttpServletResponse)response;
//        HttpClientEntity hce = null;
//        OutputStream out = null;
//        username = servletRequest.getRemoteUser();
//        kafkaIp = ConfigHelper.getValue("kafka_producer");
//        ip = getIp(servletRequest);
//        if(StringUtils.isNotBlank(username)&&StringUtils.isNotBlank(ip)){
//            Object userJson = servletRequest.getSession().getAttribute("userJsonStr");//用户信息
//            JsonViewObject jsonObj = JSON.parseObject(userJson.toString(), JsonViewObject.class);
//            JSONObject obj = JSON.parseObject(jsonObj.getContent());
//            orgName = obj.get("orgName").toString();
//            orgId = obj.get("orgId").toString();
//        }
//        try {
//            if(servletRequest.getRequestURI().indexOf("gisInfoRestServer")!=-1){
//                hce = getHttpClientEntity(servletRequest, "g");
//            }
//            if(servletRequest.getRequestURI().indexOf("serviceInfoRestServer")!=-1){
//                hce = getHttpClientEntity(servletRequest, "s");
//            }
//            if (null != hce) {
//                out = setOut(hce,servletResponse);
//                if(hce.getBytes()==null){
//                    String returnStr = "没有数据";
//                    out.write(returnStr.getBytes());
//                }else{
//                    out.write(hce.getBytes());
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            if (out != null) {
//                try {
//                    out.flush();
//                    out.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            chain.doFilter(request,response);
//        }
//    }
//
//    private HttpClientEntity getHttpClientEntity(HttpServletRequest servletRequest,String flag){
//        return null;
//    }
//
//    private OutputStream setOut(HttpClientEntity hce,HttpServletResponse servletResponse) throws IOException {
//        OutputStream out = null;
//        //创建输出流
//        if (null != hce.getMap()) {
//            servletResponse.setHeader("Content-Type", hce.getMap().get("Content-Type"));
//        }
//        servletResponse.setHeader("Pragma", "no-cache");
//        servletResponse.setHeader("Cache-Control", "no-cache");
//        servletResponse.addHeader("expires", "0");
//        servletResponse.setCharacterEncoding("UTF-8");
//        out = servletResponse.getOutputStream();
//        return out;
//    }
//
//    /**
//     * 是否需要缓存
//     * @return
//     */
//    private boolean isCache(String key){
//
//        return false;
//    }
//
//    /**
//     * 获取真实ip
//     * @param request
//     * @return
//     */
//    public static String getIp(HttpServletRequest request) {
//        String ip = request.getHeader("X-Forwarded-For");
//        if(StringUtils.isNotBlank(ip) && !"unKnown".equalsIgnoreCase(ip)){
//            //多次反向代理后会有多个ip值，第一个ip才是真实ip
//            int index = ip.indexOf(",");
//            if(index != -1){
//                return ip.substring(0,index);
//            }else{
//                return ip;
//            }
//        }
//        ip = request.getHeader("X-Real-IP");
//        if(StringUtils.isNotBlank(ip) && !"unKnown".equalsIgnoreCase(ip)){
//            return ip;
//        }
//        return request.getRemoteAddr();
//    }
//
//    public static void ProducerMessage(){
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
