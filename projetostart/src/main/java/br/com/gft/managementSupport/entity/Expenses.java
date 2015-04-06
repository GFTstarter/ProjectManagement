package br.com.gft.managementSupport.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity (name = "expenses")
@SuppressWarnings("serial")
public class Expenses implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name = "id_expenses")
	private Long idExpenses;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_resource")
    private Resource resource;

	@ManyToOne
	@JoinColumn(name="id_legal_entity")
	private LegalEntity idLegalEntity;
	
	@Column (name = "cost_rate", nullable = false)
	private Double costRate;
	
	
	@Column (name = "dt_beginExpenses", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date beginExpensesDate;
	
	@Column (name = "dt_endExpenses", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date endExpensesDate;
    
	@Column (name = "description", nullable = true)
    private String description;
    	
	
	public Long getIdExpenses() {
		return idExpenses;
	}

	public void setIdExpenses(Long idExpenses) {
		this.idExpenses = idExpenses;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public LegalEntity getIdLegalEntity() {
		return idLegalEntity;
	}

	public void setIdLegalEntity(LegalEntity idLegalEntity) {
		this.idLegalEntity = idLegalEntity;
	}


	public Double getCostRate() {
		return costRate;
	}

	public void setCostRate(Double costRate) {
		this.costRate = costRate;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getBeginExpensesDate() {
		return beginExpensesDate;
	}

	public void setBeginExpensesDate(Date beginExpensesDate) {
		this.beginExpensesDate = beginExpensesDate;
	}

	public Date getEndExpensesDate() {
		return endExpensesDate;
	}

	public void setEndExpensesDate(Date endExpensesDate) {
		this.endExpensesDate = endExpensesDate;
	}

	
	
	
	
	
}