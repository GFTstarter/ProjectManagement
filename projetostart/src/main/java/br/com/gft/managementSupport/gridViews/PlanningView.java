package br.com.gft.managementSupport.gridViews;

import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("serial")
public class PlanningView  implements Serializable{

	private Long idBaselineResource;
	private String resource;
	private Date hiredate;
	private String concept;
	private Date dtBeginOnProject;
	private Date dtEndOnProject;
	
	public PlanningView (){}
		
		public PlanningView (String resource, Date hiredate, String concept, Date dtBeginOnProject, Date dtEndOnProject){
			this.resource = resource;
			this.hiredate = hiredate;
			this.concept = concept;
			this.dtBeginOnProject = dtBeginOnProject;
			this.dtEndOnProject = dtEndOnProject;
		}

		public Long getidBaselineResource() {
			return idBaselineResource;
		}

		public void setidBaselineResource(Long idBaselineResource) {
			this.idBaselineResource = idBaselineResource;
		}

		public String getResource() {
			return resource;
		}

		public void setResource(String resource) {
			this.resource = resource;
		}

		public Date getHiredate() {
			return hiredate;
		}

		public void setHiredate(Date hiredate) {
			this.hiredate = hiredate;
		}

		public String getConcept() {
			return concept;
		}

		public void setConcept(String concept) {
			this.concept = concept;
		}

		public Date getDtBeginOnProject() {
			return dtBeginOnProject;
		}

		public void setDtBeginOnProject(Date dtBeginOnProject) {
			this.dtBeginOnProject = dtBeginOnProject;
		}

		public Date getDtEndOnProject() {
			return dtEndOnProject;
		}

		public void setDtEndOnProject(Date dtEndOnProject) {
			this.dtEndOnProject = dtEndOnProject;
		}
		
		@Override
		public String toString() {
			return "PeopleView [resource=" + resource + ", hiredate=" + hiredate 
					+ ", concept=" + concept +", dtBeginOnProject=" + dtBeginOnProject 
					+ ", dtEndOnProject=" + dtEndOnProject 
					+ ", getResource()=" + getResource() + ", getHiredate()="
					+ getHiredate() + ", getConcept()=" + getConcept() +", getDtBeginOnProject()="
					+ getDtBeginOnProject() + ", getDtEndOnProject()=" + getDtEndOnProject()
					+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
					+ super.toString() + "]";
		}
	
	
	
}
