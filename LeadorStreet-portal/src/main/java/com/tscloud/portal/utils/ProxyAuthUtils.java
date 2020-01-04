//package com.tscloud.portal.utils;
//
//import com.tscloud.common.utils.StringUtils;
//import com.tscloud.domain.resource.entity.ResAuth;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * 代理认证
// * Created by shanghuiming on 2016/1/21.
// */
//@Component
//public class ProxyAuthUtils {
//    public static int passAuth(HttpServletRequest request,String key) throws Exception{
//        return 0;
//    }
//
//    private static boolean isCompare(String ipStr,String domainName,String userId,ResAuth resAuth){
//        String ipStrs = "";
//        String domainNames = "";
//        String userIds = "";
//        int count  = 0;
//        if(StringUtils.isNotBlank(resAuth.getIpStrs())){
//            //计算ip段范围
//            ipStrs = calculateIP(resAuth.getIpStrs());
//            if(ipStrs.split(",").length>1){
//                for(String k:ipStrs.split(",")){
//                    if(k.equals(ipStr)){
//                        count++;
//                    }
//                }
//            }else{
//                if(domainNames.equals(domainName)){
//                    count++;
//                }
//            }
//        }
//        if(StringUtils.isNotBlank(resAuth.getDomainNames())){
//            domainNames = resAuth.getDomainNames();
//            if(domainNames.split(",").length>1){
//                for(String v:domainNames.split(",")){
//                    if(domainName.contains(v)){
//                        count++;
//                    }
//                }
//            }else{
//                if(domainName.contains(domainNames)){
//                    count++;
//                }
//            }
//        }
//        if(StringUtils.isNotBlank(resAuth.getUserIds())){
//            userIds = resAuth.getUserIds();
//            if(userIds.split(",").length>1){
//                for(String u:userIds.split(",")){
//                    if(userId.equals(u)){
//                        count++;
//                    }
//                }
//            }else {
//                if(userId.equals(userIds)){
//                    count++;
//                }
//            }
//        }
//        if(count>0){
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * 计算ip段范围
//     * @param ips
//     * @return
//     */
//    private static String calculateIP(String ips){
//        String sStr = "";
//        String eStr = "";
//        for(String ip:ips.split(",")){
//            String newStr = "";
//            String newStr2 = "";
//            if(ip.indexOf("~")!=-1){
//                String v = ip.split("~")[0];
//                String n = ip.split("~")[1];
//                String startStr = n.substring(0, n.lastIndexOf("."));
//                String calculateStr = "";
//                int a = Integer.parseInt(v.substring(v.lastIndexOf(".")+1));
//                int b = Integer.parseInt(n.substring(n.lastIndexOf(".")+1));
//                for(int k=a;k<=b;k++){
//                    calculateStr = calculateStr + startStr + "." + k + ",";
//                }
//                newStr = calculateStr.substring(0,calculateStr.lastIndexOf(","));
//            }else{
//                newStr2 = newStr2 + ip + ",";
//            }
//            if(!newStr.equals("")){
//                sStr = sStr + newStr;
//            }
//            if(!newStr2.equals("")){
//                eStr = eStr + newStr2;
//            }
//        }
//        if(!eStr.equals("")){
//            eStr = eStr.substring(0,eStr.lastIndexOf(","));
//        }
//        if(!sStr.equals("")&&!eStr.equals("")){
//            return sStr + "," + eStr;
//        }
//        if(eStr.equals("")){
//            return sStr;
//        }
//        if(sStr.equals("")){
//            return eStr;
//        }
//        return "";
//    }
//}
