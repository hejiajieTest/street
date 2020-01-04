/**
 * NearItems.java	  V1.0   2019年11月14日 上午10:12:39
 *
 * Copyright 2019 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.tscloud.domain.resource.entity.trueMapStation;

public class NearItems {

	private String Guid ;
	private String X ;
	private String Y ;
	private String RoadName ;
	private Long Yaw ;
	public String getGuid() {
		return Guid;
	}
	public String getX() {
		return X;
	}
	public String getY() {
		return Y;
	}
	public String getRoadName() {
		return RoadName;
	}
	public void setGuid(String guid) {
		Guid = guid;
	}
	public void setX(String x) {
		X = x;
	}
	public void setY(String y) {
		Y = y;
	}
	public void setRoadName(String roadName) {
		RoadName = roadName;
	}
	public Long getYaw() {
		return Yaw;
	}
	public void setYaw(Long yaw) {
		Yaw = yaw;
	}
	
}
