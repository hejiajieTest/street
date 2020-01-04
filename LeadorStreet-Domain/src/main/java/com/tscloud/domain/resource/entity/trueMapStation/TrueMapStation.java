/**
 * TrueMapStation.java	  V1.0   2019年11月13日 下午7:17:14
 *
 * Copyright 2019 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.tscloud.domain.resource.entity.trueMapStation;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.tscloud.common.framework.domain.TrackableEntity;

/**
 * 
 * 功能描述：
 *
 * @author ：何佳杰
 *
 * 修改历史：(修改人，修改时间，修改原因/内容)
 */
public class TrueMapStation extends TrackableEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7149107265532625518L;
	private String guid ;
	
	private Long x ;
	
	private Long y ;
	
	private Long l ;
	
	private Long b ;
	
	private Long h ;
	
	private Long yaw ;
	private Long pitch ;
	private Long roll ;
	private Date time ;
	private String segmentguid ;
	private Long sortIDX ;
	private String linkguid ;
	private Long direction ;
	private Long datatype ;
	private String extend ;
	private String roadname ;
	private Long markernum ;
	private Long history ;
	private Long narrowimg ;
	private Long wideimg ;
	private Long sequencepano ;
	private Long singlepano ;
	private Long portableimg ;
	private Long reverseimg ;
	private Long streetside ;
	private Long lidaroffse ;
	private String facets ;
	private String citycode ;
	private String previous ;
	private String next ;
	private String vid ;
	private String hasmarker ;
	private Long versionnum ;
	private Long imgnos ;
	
	public Long getVersionnum() {
		return versionnum;
	}
	public Long getImgnos() {
		return imgnos;
	}
	public void setVersionnum(Long versionnum) {
		this.versionnum = versionnum;
	}
	public void setImgnos(Long imgnos) {
		this.imgnos = imgnos;
	}
	@JSONField(name="Links")
	private Links links = new Links();
	
	public Links getLinks() {
		return links;
	}
	public void setLinks(Links links) {
		this.links = links;
	}
	public String getGuid() {
		return guid;
	}
	public Long getX() {
		return x;
	}
	public Long getY() {
		return y;
	}
	public Long getL() {
		return l;
	}
	public Long getB() {
		return b;
	}
	public Long getH() {
		return h;
	}
	public Long getYaw() {
		return yaw;
	}
	public Long getPitch() {
		return pitch;
	}
	public Long getRoll() {
		return roll;
	}
	public Date getTime() {
		return time;
	}
	public Long getSortIDX() {
		return sortIDX;
	}
	public String getLinkguid() {
		return linkguid;
	}
	public Long getDirection() {
		return direction;
	}
	public String getExtend() {
		return extend;
	}
	public Long getMarkernum() {
		return markernum;
	}
	public Long getHistory() {
		return history;
	}
	public Long getNarrowimg() {
		return narrowimg;
	}
	public Long getWideimg() {
		return wideimg;
	}
	public Long getSinglepano() {
		return singlepano;
	}
	public Long getReverseimg() {
		return reverseimg;
	}
	public Long getLidaroffse() {
		return lidaroffse;
	}
	public String getFacets() {
		return facets;
	}
	public String getCitycode() {
		return citycode;
	}
	public String getPrevious() {
		return previous;
	}
	public String getNext() {
		return next;
	}
	public String getVid() {
		return vid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public void setX(Long x) {
		this.x = x;
	}
	public void setY(Long y) {
		this.y = y;
	}
	public void setL(Long l) {
		this.l = l;
	}
	public void setB(Long b) {
		this.b = b;
	}
	public void setH(Long h) {
		this.h = h;
	}
	public void setYaw(Long yaw) {
		this.yaw = yaw;
	}
	public void setPitch(Long pitch) {
		this.pitch = pitch;
	}
	public void setRoll(Long roll) {
		this.roll = roll;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public void setSortIDX(Long sortIDX) {
		this.sortIDX = sortIDX;
	}
	public void setLinkguid(String linkguid) {
		this.linkguid = linkguid;
	}
	public void setDirection(Long direction) {
		this.direction = direction;
	}
	public void setExtend(String extend) {
		this.extend = extend;
	}
	public void setMarkernum(Long markernum) {
		this.markernum = markernum;
	}
	public void setHistory(Long history) {
		this.history = history;
	}
	public void setNarrowimg(Long narrowimg) {
		this.narrowimg = narrowimg;
	}
	public void setWideimg(Long wideimg) {
		this.wideimg = wideimg;
	}
	public void setSinglepano(Long singlepano) {
		this.singlepano = singlepano;
	}
	
	public Long getSequencepano() {
		return sequencepano;
	}
	public Long getPortableimg() {
		return portableimg;
	}
	public void setSequencepano(Long sequencepano) {
		this.sequencepano = sequencepano;
	}
	public void setPortableimg(Long portableimg) {
		this.portableimg = portableimg;
	}
	public void setReverseimg(Long reverseimg) {
		this.reverseimg = reverseimg;
	}
	public void setLidaroffse(Long lidaroffse) {
		this.lidaroffse = lidaroffse;
	}
	public void setFacets(String facets) {
		this.facets = facets;
	}
	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}
	public void setPrevious(String previous) {
		this.previous = previous;
	}
	public void setNext(String next) {
		this.next = next;
	}
	public void setVid(String vid) {
		this.vid = vid;
	}
	public String getHasmarker() {
		return hasmarker;
	}
	public void setHasmarker(String hasmarker) {
		this.hasmarker = hasmarker;
	}
	public String getSegmentguid() {
		return segmentguid;
	}
	public Long getDatatype() {
		return datatype;
	}
	public String getRoadname() {
		return roadname;
	}
	public void setSegmentguid(String segmentguid) {
		this.segmentguid = segmentguid;
	}
	public void setDatatype(Long datatype) {
		this.datatype = datatype;
	}
	public void setRoadname(String roadname) {
		this.roadname = roadname;
	}
	public Long getStreetside() {
		return streetside;
	}
	public void setStreetside(Long streetside) {
		this.streetside = streetside;
	}
	
}
