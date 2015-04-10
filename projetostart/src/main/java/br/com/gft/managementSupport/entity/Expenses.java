package br.com.gft.managementSupport.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity (name = "expenses")
@SuppressWarnings("serial")
public class Expenses implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name = "id_expenses")
	private Long idExpenses;
	
	@Column (name = "id_resource")
	private int idResource;
	
	@Column
	private String legalEntity;
	
	@Column (name = "dt_beginExpenses", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date beginExpensesDate;
	
	
	@Column (name = "dt_endExpenses", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date endExpensesDate;
	
	@Column (name = "cost_rate", nullable = false)
	private Double costRate;
	    
	
	@Column (name = "description", nullable = true)
    private String description;

	public Long getIdExpenses() {
		return idExpenses;
	}


	public void setIdExpenses(Long idExpenses) {
		this.idExpenses = idExpenses;
	}

	public int getIdResource() {
		return idResource;
	}


	public void setIdResource(int idResource) {
		this.idResource = idResource;
	}


 

	public String getLegalEntity() {
		return legalEntity;
	}


	public void setLegalEntity(String legalEntity) {
		this.legalEntity = legalEntity;
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


	@Override
	public String toString() {
		return "Expenses [idExpenses=" + idExpenses
				+ ", idExpenses=" + idExpenses + ", resource=" + idResource +", idLegalEntity=" + legalEntity
				+ ", beginExpensesDate=" + beginExpensesDate + ", endExpensesDate=" + endExpensesDate
				+ ", costRate=" + costRate + ", ]";
	}
    
   		
}