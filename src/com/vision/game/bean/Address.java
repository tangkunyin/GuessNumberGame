package com.vision.game.bean;
/**
 * 通过淘宝API接口获取的地址
 * @author tangkunyin
 */
public class Address {
	private String country;
	private String region;
	private String city;
	private String isp;
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getIsp() {
		return isp;
	}
	public void setIsp(String isp) {
		this.isp = isp;
	}
}
