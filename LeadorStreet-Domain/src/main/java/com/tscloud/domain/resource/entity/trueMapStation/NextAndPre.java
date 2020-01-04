/**
 * NextAndPre.java	  V1.0   2019年11月14日 上午9:55:53
 *
 * Copyright 2019 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.tscloud.domain.resource.entity.trueMapStation;

/**
 * 
 * 功能描述：
 *
 * @author ：何佳杰
 *
 * 修改历史：(修改人，修改时间，修改原因/内容)
 */
public class NextAndPre {
	
	private String ID ;
	private String RoadName ;
	private Long Yaw ;

	public String getID() {
		return ID;
	}

	public String getRoadName() {
		return RoadName;
	}

	public Long getYaw() {
		return Yaw;
	}

	public void setYaw(Long yaw) {
		Yaw = yaw;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public void setRoadName(String roadName) {
		RoadName = roadName;
	}

	
}
