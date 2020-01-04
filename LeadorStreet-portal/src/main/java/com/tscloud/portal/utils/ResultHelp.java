/**
 * JsonViewContentObject.java	  V1.0   2019年11月18日 下午4:58:44
 *
 * Copyright 2019 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.tscloud.portal.utils;

import com.alibaba.fastjson.JSONObject;

public class ResultHelp {


	public static JSONObject setResult(String Status, String Message,String content,Object object) {
		JSONObject jSONObject = new JSONObject() ;
		jSONObject.put("Status", Status);
		jSONObject.put("Message", Message);
		jSONObject.put(content,object );
		return jSONObject;
	}
	
}
