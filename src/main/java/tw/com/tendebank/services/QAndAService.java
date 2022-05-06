package tw.com.tendebank.services;

import java.util.List;

import tw.com.tendebank.beans.QAndA;

public interface QAndAService {
	public List<QAndA> selectByCategory(String key);
}
