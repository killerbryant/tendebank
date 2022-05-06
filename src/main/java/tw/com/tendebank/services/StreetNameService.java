package tw.com.tendebank.services;

import java.util.List;

import tw.com.tendebank.beans.StreetName;

/**
 * 台灣地址業務邏輯 Service 介面
 * @author Edison
 *
 */
public interface StreetNameService {
	
	public List<StreetName> selectCity();
	
	public List<StreetName> selectCountry(String city);
	
	public List<StreetName> selectRoad(String city, String country);
	
}
