package br.com.gft.managementSupport.gridViews;

import java.io.Serializable;
import java.sql.Date;
import java.util.Map;

@SuppressWarnings("serial")
public class PlanningView  implements Serializable{

	private Long idActivity;
	private Long idResource;
	private String resource;
	private Date hiredate;
	private String concept;
	private Date date;
	private Integer hours;
	private Map<Date, Integer> activity;
	private Map<String, Map<Date, Integer>> horasResource;
	
	
	
		public PlanningView (){}
		
		public PlanningView (String resource, Date hiredate, String concept, Date date, Integer hours){
			this.resource = resource;
			this.hiredate = hiredate;
			this.concept = concept;
			this.date = date;
			this.hours = hours;
			
		}
		
		

		
		
		public Long getIdActivity() {
			return idActivity;
		}

		public void setIdActivity(Long idActivity) {
			this.idActivity = idActivity;
		}

		public Map<Date, Integer> getActivity() {
			return activity;
		}

		public void setActivity(Map<Date, Integer> activity) {
			this.activity = activity;
		}

		public Map<String, Map<Date, Integer>> getHorasResource() {
			return horasResource;
		}

		public void setHorasResource(Map<String, Map<Date, Integer>> horasResource) {
			this.horasResource = horasResource;
		}

		public Long getIdResource() {
			return idResource;
		}

		public void setIdResource(Long idResource) {
			this.idResource = idResource;
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
	
		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public Integer getHours() {
			return hours;
		}

		public void setHours(Integer hours) {
			this.hours = hours;
		}

		@Override
		public String toString() {
			return "PlanningView [resource=" + resource + ", hiredate=" + hiredate 
					+ ", concept=" + concept +", date=" + date 
					+ ", hours=" + hours 
					+ ", getResource()=" + getResource() + ", getHiredate()="
					+ getHiredate() + ", getConcept()=" + getConcept() +", getDate()="
					+ getDate() + ", getHours()=" + getHours()
					+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
					+ super.toString() + "]";
		}
	
	
	
}
