package br.com.gft.managementSupport.gridViews;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DashboardView implements Serializable {

	private Long idConceptLegalEntity;
	private String concept;
	private Double blpCost;
	private Double blpCostSimulation;
	private String entity;
	
	public DashboardView (){}
	
	public DashboardView (Long idConceptLegalEntity, 
						String concept, 
						Double blpCost, 
						Double blpCostSimulation, 
						String entity){
		
		this.idConceptLegalEntity = idConceptLegalEntity;
		this.concept = concept;
		this.blpCost = blpCost;
		this.blpCostSimulation = blpCostSimulation;
		this.entity = entity;
	}
	
	public Long getIdConceptLegalEntity() {
		return idConceptLegalEntity;
	}

	public void setIdConceptLegalEntity(Long idConceptLegalEntity) {
		this.idConceptLegalEntity = idConceptLegalEntity;
	}

	public String getConcept() {
		return concept;
	}

	public void setConcept(String concept) {
		this.concept = concept;
	}

	public Double getBlpCost() {
		return blpCost;
	}

	public void setBlpCost(Double blpCost) {
		this.blpCost = blpCost;
	}

	public Double getBlpCostSimulation() {
		return blpCostSimulation;
	}

	public void setBlpCostSimulation(Double blpCostSimulation) {
		this.blpCostSimulation = blpCostSimulation;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}	
	
}
