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
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import br.com.gft.managementSupport.serializer.ProjectSerializer;


@Entity
@JsonSerialize(using=ProjectSerializer.class)
@SuppressWarnings("serial")
public class Project implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name = "id_project")
	private Long idProject;	

	@Column (name = "project_code", unique = true, nullable = false)
	private String projectCode; 

	@Column (name = "name", nullable = false)
	private String name;

	@Column (name = "start_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Column (name = "end_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	@Column (name = "id_user")
	private int idUser;
		
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
	private List<ActivitySheet> activitySheets;
	
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
	private List<Baseline> baselines;
	

	public Long getIdProject() {
		return idProject;
	}

	public void setIdProject(Long idProject) {
		this.idProject = idProject;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public List<ActivitySheet> getActivitySheets() {
		return activitySheets;
	}

	public void setActivitySheets(List<ActivitySheet> activitySheets) {
		this.activitySheets = activitySheets;
	}

	public List<Baseline> getBaselines() {
		return baselines;
	}

	public void setBaselines(List<Baseline> baselines) {
		this.baselines = baselines;
	}

	@Override
	public String toString() {
		return "Project [idProject=" + idProject + ", projectCode="
				+ projectCode + ", name=" + name + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", idUser=" + idUser
				+ ", activitySheets=" + activitySheets + ", baselines="
				+ baselines + "]";
	}
			
}
