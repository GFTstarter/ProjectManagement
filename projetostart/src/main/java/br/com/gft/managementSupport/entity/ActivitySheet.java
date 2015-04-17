package br.com.gft.managementSupport.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity (name = "activity_sheet")
@SuppressWarnings("serial")
public class ActivitySheet implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name = "id_activity_sheet")
	private Long idActivitySheet;
	
	@ManyToOne
	@JoinColumn (name = "id_project", nullable = false)
	private Project project;
	
	@Column (name = "description")
	private String description;
	
	@Column (name = "activity_type", nullable = false)
	private String activityType;
	
	@Column (name = "task", nullable = true)
	private String task;
	
	@Temporal(TemporalType.DATE)
	@Column (name = "date", nullable = false)
	private Date date;
	
	@Column (name = "hours", nullable = false)
	private int hours;
		
	@Column (name = "status", nullable = false)
	private String status;
	
	@Temporal(TemporalType.DATE)
	@Column (name = "appr_date", nullable = false)
	private Date apprDate;
	
	@Column (name = "remarks", nullable = true)
	private String remarks;
	
	@Column (name = "month", nullable = false)
	private int month;
	
	@Column (name = "year", nullable = false)
	private int year;
	/*
	@ManyToMany(mappedBy = "activitySheets", cascade = CascadeType.ALL)
	@JoinTable(name="resource_activity_sheet", joinColumns=@JoinColumn(name="id_activity_sheet"), inverseJoinColumns=@JoinColumn(name="id_resource"))
	private List<Resource> resources;*/
	
	@ManyToMany(mappedBy = "activitySheets", cascade = CascadeType.ALL)
	private List<Resource> resources;
	
	public ActivitySheet() {
		
	}
	
	public ActivitySheet(Long id, Date date, int hours) {
		super();
		this.idActivitySheet = id; 
		this.date = date;
		this.hours = hours;
	}

	public Long getIdActivitySheet() {
		return idActivitySheet;
	}

	public void setIdActivitySheet(Long idActivitySheet) {
		this.idActivitySheet = idActivitySheet;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getApprDate() {
		return apprDate;
	}

	public void setApprDate(Date apprDate) {
		this.apprDate = apprDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((activityType == null) ? 0 : activityType.hashCode());
		result = prime * result
				+ ((apprDate == null) ? 0 : apprDate.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + hours;
		result = prime * result
				+ ((idActivitySheet == null) ? 0 : idActivitySheet.hashCode());
		result = prime * result + month;
		result = prime * result + ((project == null) ? 0 : project.hashCode());
		result = prime * result + ((remarks == null) ? 0 : remarks.hashCode());
		result = prime * result
				+ ((resources == null) ? 0 : resources.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((task == null) ? 0 : task.hashCode());
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActivitySheet other = (ActivitySheet) obj;
		if (activityType == null) {
			if (other.activityType != null)
				return false;
		} else if (!activityType.equals(other.activityType))
			return false;
		if (apprDate == null) {
			if (other.apprDate != null)
				return false;
		} else if (!apprDate.equals(other.apprDate))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (hours != other.hours)
			return false;
		if (idActivitySheet == null) {
			if (other.idActivitySheet != null)
				return false;
		} else if (!idActivitySheet.equals(other.idActivitySheet))
			return false;
		if (month != other.month)
			return false;
		if (project == null) {
			if (other.project != null)
				return false;
		} else if (!project.equals(other.project))
			return false;
		if (remarks == null) {
			if (other.remarks != null)
				return false;
		} else if (!remarks.equals(other.remarks))
			return false;
		if (resources == null) {
			if (other.resources != null)
				return false;
		} else if (!resources.equals(other.resources))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (task == null) {
			if (other.task != null)
				return false;
		} else if (!task.equals(other.task))
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ActivitySheet [idActivitySheet=" + idActivitySheet
				+ ", project=" + project + ", description=" + description
				+ ", activityType=" + activityType + ", task=" + task
				+ ", date=" + date + ", hours=" + hours + ", status=" + status
				+ ", apprDate=" + apprDate + ", remarks=" + remarks
				+ ", month=" + month + ", year=" + year + ", resources="
				+ resources + "]";
	}
	
}
