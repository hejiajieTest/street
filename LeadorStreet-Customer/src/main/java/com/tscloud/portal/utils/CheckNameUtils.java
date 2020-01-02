package com.tscloud.portal.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by shulb on 2015/7/21.
 */
public class CheckNameUtils {
    /**
     * 验证名称重复时的公共部分
     * @param flag
     * @return
     */
    public static JSONObject CheckNameUtils(boolean flag ){
        JSONObject rsJson = new JSONObject();
        if(flag){
            rsJson.put("info","已存在，请重新输入");
            rsJson.put("status","n");
        }else {
            rsJson.put("info","验证通过");
            rsJson.put("status","y");
        }
        return rsJson;
    }
}
