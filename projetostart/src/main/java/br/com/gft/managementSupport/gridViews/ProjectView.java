package br.com.gft.managementSupport.gridViews;

import java.sql.Date;

public class ProjectView {

	private Long idProject;
	private String projectCode;
	private String name;
	private Date startDate;
	private Date endDate;
	
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
	
}