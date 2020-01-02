package com.tscloud.resource.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tscloud.common.framework.Constants;
import com.tscloud.common.framework.config.ConfigHelper;
import com.tscloud.common.framework.domain.entity.manager.Org;
import com.tscloud.common.framework.domain.entity.manager.OrgUser;
import com.tscloud.common.framework.domain.entity.manager.User;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import java.util.List;
import java.util.Map;

/**
 * Created by shanghuiming on 2015/12/4.
 * 获取CA工程相关数据
 */
public class GetCAUtil {
//    /**
//     * 根据用户id查询是否为租户,是,就返回orgId(作为租户id)
//     */
//    public static String getTenantId(String userId){
//        String tenantId = "";
//        List<OrgUser> list = getOrgUserlist(userId);
//        for(OrgUser ou:list){
//            Org org = getOrgById(ou.getOrgId());
//            if(org.getFlag().equals("1")){
//                tenantId =  org.getId();
//            }
//        }
//        return tenantId;
//    }


    /**
     * orgUser的findByMap
     * @return
     */
    public static List<OrgUser> getOrgUserlist(Map<String,Object> map){
        OrgUser orgUser = new OrgUser();
        orgUser.setUserId(map.get("userId").toString());
        orgUser.setOrgId(map.get("orgId").toString());
        String jsonStr = JSON.toJSONString(orgUser);
        WebTarget client = CAWebTargetProvider.getWebResource(getProValue("TrueCloudManager.ip"),getProValue("TrueCloudManager.port"))
                .path(Constants.RestPathPrefix.CA + "caOrgUserRestServer")
                .path("getByWhere");

        String result = client.request()
                .post(Entity.entity(jsonStr, "application/json"), String.class);
        JSONObject object = JSON.parseObject(result);
        List<OrgUser> list = JSON.parseArray(object.getString("content"), OrgUser.class);
        return list;
    }

    /**
     * 根据id查询org
     * @param orgId
     * @return
     */
    public static Org getOrgById(String orgId){
        System.out.print(CAWebTargetProvider.getWebResource(getProValue("TrueCloudManager.ip"),getProValue("TrueCloudManager.port")));
        String result = CAWebTargetProvider.getWebResource(getProValue("TrueCloudManager.ip"),getProValue("TrueCloudManager.port"))
                .path(Constants.RestPathPrefix.CA + "caOrgRestServer")
                .path("getById")
                .queryParam("id",orgId)
                .request()
                .get(String.class);
        JSONObject object = JSON.parseObject(result);
        Org org = JSON.parseObject(object.getString("content"),Org.class);
        return org;
    }

    /**
     * 根据id查询User
     * @param userId
     * @return
     */
    public static User getUserById(String userId){
        String result = CAWebTargetProvider.getWebResource(getProValue("TrueCloudManager.ip"),getProValue("TrueCloudManager.port"))
                .path(Constants.RestPathPrefix.CA + "caUserRestServer")
                .path("getById")
                .queryParam("id", userId)
                .request()
                .get(String.class);
        JSONObject object = JSON.parseObject(result);
        User user = JSON.parseObject(object.getString("content"), User.class);
        return user;
    }

    /**
     * 根据id查询User
     * @param loginName
     * @return
     */
    public static User getUserByLoginName(String loginName){
        String result = CAWebTargetProvider.getWebResource(getProValue("ca.ip"),getProValue("ca.port"))
                .path(Constants.RestPathPrefix.CA + "caUserRestServer")
                .path("findByLoginName")
                .queryParam("loginName", loginName)
                .request()
                .get(String.class);
        JSONObject object = JSON.parseObject(result);
        User user = JSON.parseObject(object.getString("content"), User.class);
        return user;
    }

    public static List<Org> findUserListByOrgId(String orgId){
        String result = CAWebTargetProvider.getWebResource(getProValue("TrueCloudManager.ip"),getProValue("TrueCloudManager.port"))
                .path(Constants.RestPathPrefix.CA + "caOrgRestServer")
                .path("findUserListByOrgId")
                .queryParam("orgId",orgId)
                .request()
                .get(String.class);
        JSONObject object = JSON.parseObject(result);
        List<Org> list = JSON.parseArray(object.getString("content"),Org.class);
        return list;
    }

    public static List<Org> getOrgGetAll(){
        String result = CAWebTargetProvider.getWebResource(getProValue("TrueCloudManager.ip"),getProValue("TrueCloudManager.port"))
                .path(Constants.RestPathPrefix.CA + "caOrgRestServer")
                .path("getAll")
                .request()
                .get(String.class);
        JSONObject object = JSON.parseObject(result);
        List<Org> list = JSON.parseArray(object.getString("content"),Org.class);
        return list;
    }

    public static List<Org> getOrgByUserId(String userId){
        String result = CAWebTargetProvider.getWebResource(getProValue("TrueCloudManager.ip"),getProValue("TrueCloudManager.port"))
                .path(Constants.RestPathPrefix.CA + "caOrgRestServer")
                .path("findOrgByCurrentUserId")
                .queryParam("currentUserId",userId)
                .request()
                .get(String.class);
        JSONObject object = JSON.parseObject(result);
        List<Org> list = JSON.parseArray(object.getString("content"),Org.class);
        return list;
    }

    public static void main(String[] args){
        //List<User> list = findUserListByOrgId("D86E37257FD1416FB5C3BE594009DBF2");
        //System.out.print(list.size());
    }


    /**
     * 读取配置文件
     *
     * @param key
     * @return
     */
    public static String getProValue(String key) {
        /*Properties props = new Properties();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(GetCAUtil.class.getResource("/").getPath() +"configs/config.properties"));
            props.load(in);
            String value = props.getProperty(key);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }*/
        return ConfigHelper.getValue(key);
    }
}
