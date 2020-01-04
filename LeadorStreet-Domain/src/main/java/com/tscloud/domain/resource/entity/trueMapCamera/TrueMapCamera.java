/**
 * TrueMapCamera.java	  V1.0   2019年11月13日 下午12:51:10
 *
 * Copyright 2019 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.tscloud.domain.resource.entity.trueMapCamera;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.tscloud.common.framework.domain.TrackableEntity;

public class TrueMapCamera extends TrackableEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4823791181823961591L;

	private String vid ;
	
	private Date checkdate ;
	@JSONField(name="CameraNo")
	private String camerano ;
	
	private String pixel ;
	
	private Long hview ;
	private Long wview ;
	private Long purview ;
	@JSONField(name="Width")
	private Integer col ;
	@JSONField(name="Height")
	private Integer row ;
	private Long f ;
	private Long x0 ;
	private Long y0 ;
	private Long ky ;
	private Long dx ;
	
	private Long dy ;
	private Long k1 ;
	private Long k2 ;
	private Long k3 ;
	private Long p1 ;
	private Long p2 ;
	private Long p3 ;
	private Long p4 ;
	private Long ax ;
	private Long ay ;
	private Long az ;
	private Long ayaw ;
	private Long apitch ;
	private Long aroll ;
	private Long rx ;
	private Long ry ;
	private Long rz ;
	private Long ryaw ;
	private Long rpitch ;
	private Long rroll ;
	@JSONField(name="Pair")
	private String pair ;
	
	public Long getApitch() {
		return apitch;
	}
	public void setApitch(Long apitch) {
		this.apitch = apitch;
	}
	public String getVid() {
		return vid;
	}
	public Date getCheckdate() {
		return checkdate;
	}
	public String getCamerano() {
		return camerano;
	}
	public String getPixel() {
		return pixel;
	}
	public Long getHview() {
		return hview;
	}
	public Long getWview() {
		return wview;
	}
	public Long getPurview() {
		return purview;
	}
	public Integer getCol() {
		return col;
	}
	public Integer getRow() {
		return row;
	}
	public Long getF() {
		return f;
	}
	public Long getX0() {
		return x0;
	}
	public Long getY0() {
		return y0;
	}
	public Long getKy() {
		return ky;
	}
	public Long getDx() {
		return dx;
	}
	public Long getDy() {
		return dy;
	}
	public Long getK1() {
		return k1;
	}
	public Long getK2() {
		return k2;
	}
	public Long getK3() {
		return k3;
	}
	public Long getP1() {
		return p1;
	}
	public Long getP2() {
		return p2;
	}
	public Long getP3() {
		return p3;
	}
	public Long getP4() {
		return p4;
	}
	public Long getAx() {
		return ax;
	}
	public Long getAy() {
		return ay;
	}
	public Long getAz() {
		return az;
	}
	public Long getAyaw() {
		return ayaw;
	}
	public Long getAroll() {
		return aroll;
	}
	public Long getRx() {
		return rx;
	}
	public Long getRy() {
		return ry;
	}
	public Long getRz() {
		return rz;
	}
	public Long getRyaw() {
		return ryaw;
	}
	public Long getRpitch() {
		return rpitch;
	}
	public Long getRroll() {
		return rroll;
	}
	public String getPair() {
		return pair;
	}
	public void setVid(String vid) {
		this.vid = vid;
	}
	public void setCheckdate(Date checkdate) {
		this.checkdate = checkdate;
	}
	public void setCamerano(String camerano) {
		this.camerano = camerano;
	}
	public void setPixel(String pixel) {
		this.pixel = pixel;
	}
	public void setHview(Long hview) {
		this.hview = hview;
	}
	public void setWview(Long wview) {
		this.wview = wview;
	}
	public void setPurview(Long purview) {
		this.purview = purview;
	}
	public void setCol(Integer col) {
		this.col = col;
	}
	public void setRow(Integer row) {
		this.row = row;
	}
	public void setF(Long f) {
		this.f = f;
	}
	public void setX0(Long x0) {
		this.x0 = x0;
	}
	public void setY0(Long y0) {
		this.y0 = y0;
	}
	public void setKy(Long ky) {
		this.ky = ky;
	}
	public void setDx(Long dx) {
		this.dx = dx;
	}
	public void setDy(Long dy) {
		this.dy = dy;
	}
	public void setK1(Long k1) {
		this.k1 = k1;
	}
	public void setK2(Long k2) {
		this.k2 = k2;
	}
	public void setK3(Long k3) {
		this.k3 = k3;
	}
	public void setP1(Long p1) {
		this.p1 = p1;
	}
	public void setP2(Long p2) {
		this.p2 = p2;
	}
	public void setP3(Long p3) {
		this.p3 = p3;
	}
	public void setP4(Long p4) {
		this.p4 = p4;
	}
	public void setAx(Long ax) {
		this.ax = ax;
	}
	public void setAy(Long ay) {
		this.ay = ay;
	}
	public void setAz(Long az) {
		this.az = az;
	}
	public void setAyaw(Long ayaw) {
		this.ayaw = ayaw;
	}
	public void setAroll(Long aroll) {
		this.aroll = aroll;
	}
	public void setRx(Long rx) {
		this.rx = rx;
	}
	public void setRy(Long ry) {
		this.ry = ry;
	}
	public void setRz(Long rz) {
		this.rz = rz;
	}
	public void setRyaw(Long ryaw) {
		this.ryaw = ryaw;
	}
	public void setRpitch(Long rpitch) {
		this.rpitch = rpitch;
	}
	public void setRroll(Long rroll) {
		this.rroll = rroll;
	}
	public void setPair(String pair) {
		this.pair = pair;
	}
	
}
