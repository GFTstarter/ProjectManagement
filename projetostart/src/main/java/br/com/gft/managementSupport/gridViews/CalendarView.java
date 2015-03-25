package br.com.gft.managementSupport.gridViews;

import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("serial")
public class CalendarView implements Serializable{

	private Long idHoliday;
	private Long idLocation;
	private String description;
	private Double hours;
	private Date date;
	private String weekDay;
	private String location;
	
	public Long getIdHoliday() {
		return idHoliday;
	}
	
	public void setIdHoliday(Long idHoliday) {
		this.idHoliday = idHoliday;
	}
	
	public Long getIdLocation() {
		return idLocation;
	}

	public void setIdLocation(Long idLocation) {
		this.idLocation = idLocation;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Double getHours() {
		return hours;
	}
	
	public void setHours(Double hours) {
		this.hours = hours;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getWeekDay() {
		return weekDay;
	}
	
	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}	
	
}