package br.com.gft.managementSupport.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity (name = "travel")
@SuppressWarnings("serial")
public class Travel implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name = "id_travel")
	private Long idTravel;
	
	@ManyToOne
	@JoinColumn(name="id_legal_entity")
	private LegalEntity idLegalEntity;
	
	@Column (name = "cost_rate", nullable = false)
	private Double costRate;
	
	@ManyToMany(mappedBy = "travels")
	private List<Resource> resources;
	
	@Column (name = "start_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Column (name = "end_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date endDate;
    
	@Column (name = "description", nullable = true)
    private String description;
    	
	
	public Long getIdTravel() {
		return idTravel;
	}

	public void setIdTravel(Long idTravel) {
		this.idTravel = idTravel;
	}

	public LegalEntity getIdLegalEntity() {
		return idLegalEntity;
	}

	public void setIdLegalEntity(LegalEntity idLegalEntity) {
		this.idLegalEntity = idLegalEntity;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
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