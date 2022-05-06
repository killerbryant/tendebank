package tw.com.tendebank.beans;

import java.io.Serializable;

public class StreetName implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int uid;
	
	private String mailcode;
	
	private String city;
	
	private String country;
	
	private String road;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getMailcode() {
		return mailcode;
	}

	public void setMailcode(String mailcode) {
		this.mailcode = mailcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRoad() {
		return road;
	}

	public void setRoad(String road) {
		this.road = road;
	}

}
