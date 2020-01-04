/**
 * MeasurePara.java	  V1.0   2019年12月13日 下午5:23:13
 *
 * Copyright 2019 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.tscloud.domain.resource.entity.trueMapCamera;

public class MeasurePara {

	private String imageid ;
	private String x ;
	private String y ;
	
	//拼装查询条件和拆分成查询条件查询
	private String ZeroImageId ;
	
	private String cameraNo ;
	
	private String dateFormat ;
	
	private String vid ;
	
	
	public MeasurePara(){}
	
	public MeasurePara(String imageid,String x,String y){
		this.imageid = imageid ;
		this.x = x ;
		this.y = y ;
	}
	
	public String getZeroImageId() {
		return ZeroImageId;
	}

	public String getCameraNo() {
		return cameraNo;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public String getVid() {
		return vid;
	}

	public void setZeroImageId(String zeroImageId) {
		ZeroImageId = zeroImageId;
	}

	public void setCameraNo(String cameraNo) {
		this.cameraNo = cameraNo;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}

	public String getImageid() {
		return imageid;
	}

	public void setImageid(String imageid) {
		this.imageid = imageid;
	}

	public String getX() {
		return x;
	}
	public String getY() {
		return y;
	}
	public void setX(String x) {
		this.x = x;
	}
	public void setY(String y) {
		this.y = y;
	}
	
}
