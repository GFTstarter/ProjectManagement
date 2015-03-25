package br.com.gft.managementSupport.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity (name = "historic")
@SuppressWarnings("serial")
public class Historic implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name = "id_historic")
	private Long idHistoric;
	
	@ManyToOne
	@JoinColumn (name = "id_resource", nullable = false)
	private Resource resource;
	
	@ManyToOne
	@JoinColumn (name = "id_concept", nullable = false)
	private Concept concept;
	
	@Column (name = "start_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startDate;
        
    @Column (name = "end_date", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date endDate;

	public Long getIdHistoric() {
		return idHistoric;
	}

	public void setIdHistoric(Long idHistoric) {
		this.idHistoric = idHistoric;
	}
	
	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public Concept getConcept() {
		return concept;
	}

	public void setConcept(Concept concept) {
		this.concept = concept;
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