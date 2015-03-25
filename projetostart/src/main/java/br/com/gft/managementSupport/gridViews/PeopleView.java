package br.com.gft.managementSupport.gridViews;

import java.io.Serializable;


@SuppressWarnings("serial")
public class PeopleView implements Serializable{

	private Long idResource;
	private String resource;
	private String concept;
	private String entity;
	private String location;
	
	
	public PeopleView (){}
	
	public PeopleView (String resource, String concept, String entity, String location){
		this.resource = resource;
		this.concept = concept;
		this.entity = entity;
		this.location = location;
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
	
	public String getConcept() {
		return concept;
	}
	
	public void setConcept(String concept) {
		this.concept = concept;
	}
	
	public String getEntity() {
		return entity;
	}
	
	public void setEntity(String entity) {
		this.entity = entity;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "PeopleView [resource=" + resource + ", concept=" + concept
				+ ", entity=" + entity + ", location=" + location
				+ ", getResource()=" + getResource() + ", getConcept()="
				+ getConcept() + ", getEntity()=" + getEntity()
				+ ", getLocation()=" + getLocation() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
}