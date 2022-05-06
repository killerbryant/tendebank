package tw.com.tendebank.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * 問答Bean
 * @author Edison
 *
 */
public class QAndA implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String qandaNo; // Q&A編號
	
	private String title; // Q&A標題
	
	private String contents; // Q&A內容
	
	private String category; // Q&A分類
	
	private String creator; // Q&A建立者
	
	private Date createDate; // Q&A建立時間

	public String getQandaNo() {
		return qandaNo;
	}

	public void setQandaNo(String qandaNo) {
		this.qandaNo = qandaNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
