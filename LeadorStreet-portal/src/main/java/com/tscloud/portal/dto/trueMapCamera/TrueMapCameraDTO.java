/**
 * TrueMapCameraDTO.java	  V1.0   2020年1月2日 下午8:10:21
 *
 * Copyright 2020 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.tscloud.portal.dto.trueMapCamera;

import java.io.Serializable;

public class TrueMapCameraDTO implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3060274907050202187L;
	
	private String imageId ;
	
	private String row ;
	
	private String col ;
	
	private String zoom ;
	
	private String clientType ;

	public String getImageId() {
		return imageId;
	}

	public String getRow() {
		return row;
	}

	public String getCol() {
		return col;
	}

	public String getZoom() {
		return zoom;
	}

	public String getClientType() {
		return clientType;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public void setRow(String row) {
		this.row = row;
	}

	public void setCol(String col) {
		this.col = col;
	}

	public void setZoom(String zoom) {
		this.zoom = zoom;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}
	
}
