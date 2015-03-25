package br.com.gft.managementSupport.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity (name = "concept_legal_entity")
@SuppressWarnings("serial")
public class ConceptByLegalEntity implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name = "id_concept_legal_entity")
	private Long idConceptLegalEntity;	
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_concept")
    private Concept concept;

    @Column (name = "blp_cost", nullable = false)
	private Double blpCost;
    
    @Column (name = "blp_cost_simulator")
	private Double blpCostSimulator;	
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_legal_entity")
    private LegalEntity legalEntity;

	public Long getIdConceptLegalEntity() {
		return idConceptLegalEntity;
	}

	public void setIdConceptLegalEntity(Long idConceptLegalEntity) {
		this.idConceptLegalEntity = idConceptLegalEntity;
	}

	public Concept getConcept() {
		return concept;
	}

	public void setConcept(Concept concept) {
		this.concept = concept;
	}

	public Double getBlpCost() {
		return blpCost;
	}

	public void setBlpCost(Double blpCost) {
		this.blpCost = blpCost;
	}

	public Double getBlpCostSimulator() {
		return blpCostSimulator;
	}

	public void setBlpCostSimulator(Double blpCostSimulator) {
		this.blpCostSimulator = blpCostSimulator;
	}

	public LegalEntity getLegalEntity() {
		return legalEntity;
	}

	public void setLegalEntity(LegalEntity legalEntity) {
		this.legalEntity = legalEntity;
	}

	@Override
	public String toString() {
		return "ConceptByLegalEntity [idConceptLegalEntity="
				+ idConceptLegalEntity + "]";
	}
        
}
