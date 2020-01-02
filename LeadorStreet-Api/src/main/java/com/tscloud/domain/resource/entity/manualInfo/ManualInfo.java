package com.tscloud.domain.resource.entity.manualInfo;


import java.io.Serializable;
import java.util.Date;

import com.tscloud.common.framework.domain.TrackableEntity;


public class ManualInfo extends TrackableEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String manualName ;
	private String manualDesc ;
	private String dowloadUrl ;
	private String autoEnterpriseCode ;
	private Date updateDate ;
	private String manualType ;
	public String getManualName() {
		return manualName;
	}
	public void setManualName(String manualName) {
		this.manualName = manualName;
	}
	public String getManualDesc() {
		return manualDesc;
	}
	public void setManualDesc(String manualDesc) {
		this.manualDesc = manualDesc;
	}
	public String getDowloadUrl() {
		return dowloadUrl;
	}
	public void setDowloadUrl(String dowloadUrl) {
		this.dowloadUrl = dowloadUrl;
	}
	public String getAutoEnterpriseCode() {
		return autoEnterpriseCode;
	}
	public void setAutoEnterpriseCode(String autoEnterpriseCode) {
		this.autoEnterpriseCode = autoEnterpriseCode;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getManualType() {
		return manualType;
	}
	public void setManualType(String manualType) {
		this.manualType = manualType;
	}
	
}
