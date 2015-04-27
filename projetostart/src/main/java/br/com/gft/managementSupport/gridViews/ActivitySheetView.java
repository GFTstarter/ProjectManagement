package br.com.gft.managementSupport.gridViews;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ActivitySheetView implements Serializable{

	private Integer hours;
	private Integer month;
	
	public Integer getHours() {
		return hours;
	}
	public void setHours(Integer hours) {
		this.hours = hours;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}

}