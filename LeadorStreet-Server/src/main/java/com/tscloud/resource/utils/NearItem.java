package com.tscloud.resource.utils;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NearItem {
	//测站ID
	@JsonProperty
	private String Guid;
	//道路名称
	@JsonProperty
	private String RoadName;
	//X坐标
	@JsonProperty
	private double X;
	//Y坐标
	@JsonProperty
	private double Y;
	//方位角
	@JsonProperty
	private double Yaw;

	public NearItem() {
		X = 0.0;
		Y = 0.0;
		Yaw = 0.0;
	}

	@JsonIgnore
	public String getGuid() {
		return Guid;
	}

	@JsonIgnore
	public void setGuid(String value) {
		this.Guid = value;
	}

	@JsonIgnore
	public String getRoadName() {
		return RoadName;
	}

	@JsonIgnore
	public void setRoadName(String value) {
		this.RoadName = value;
	}

	@JsonIgnore
	public double getX() {
		return X;
	}

	@JsonIgnore
	public void setX(double value) {
		this.X = value;
	}

	@JsonIgnore
	public double getY() {
		return Y;
	}

	@JsonIgnore
	public void setY(double value) {
		this.Y = value;
	}

	@JsonIgnore
	public double getYaw() {
		return Yaw;
	}

	@JsonIgnore
	public void setYaw(double value) {
		this.Yaw = value;
	}
}
