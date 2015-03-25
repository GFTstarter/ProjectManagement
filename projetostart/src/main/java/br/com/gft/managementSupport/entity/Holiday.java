package br.com.gft.managementSupport.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@SuppressWarnings("serial")
public class Holiday implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name = "id_holiday")
	private Long idHoliday;
	
	@Column (name = "description", nullable = false)
	private String description;
	
	@Temporal(TemporalType.DATE)
	@Column (name = "ref_date", nullable = false)
	private Date refDate;
	
	@Column (name = "hours_holiday", nullable = false)
	private Double hoursHoliday;
	
	@Column (name = "week_day", nullable = false)
	private String weekDay;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "holidays", cascade = {CascadeType.ALL})
	private List<Location> locations;

	public Long getIdHoliday() {
		return idHoliday;
	}

	public void setIdHoliday(Long idHoliday) {
		this.idHoliday = idHoliday;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getRefDate() {
		return refDate;
	}

	public void setRefDate(Date refDate) {
		this.refDate = refDate;
	}

	public Double getHoursHoliday() {
		return hoursHoliday;
	}

	public void setHoursHoliday(Double hoursHoliday) {
		this.hoursHoliday = hoursHoliday;
	}

	public String getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	@Override
	public String toString() {
		return "Holiday [idHoliday=" + idHoliday + "]";
	}
		
}