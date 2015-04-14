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

@Entity (name = "baseline_resources")
@SuppressWarnings("serial")
public class BaselineByResource implements Serializable {
	
	//ADICIONADO CONTROLE DE DATA E/S PROJETO
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name = "id_baseline_resource")
	private Long idBaselineResource;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_baseline")
    private Baseline baseline;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_resource")
    private Resource resource;
        
    @Column (name = "dt_beginOnProject", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date beginOnProjectDate;
        
    @Column (name = "dt_endOnProject", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date endOnProjectDate;

	public Long getIdBaselineResource() {
		return idBaselineResource;
	}

	public void setIdBaselineResource(Long idBaselineResource) {
		this.idBaselineResource = idBaselineResource;
	}

	public Baseline getBaseline() {
		return baseline;
	}

	public void setBaseline(Baseline baseline) {
		this.baseline = baseline;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public Date getBeginOnProjectDate() {
		return beginOnProjectDate;
	}

	public void setBeginOnProjectDate(Date beginOnProjectDate) {
		this.beginOnProjectDate = beginOnProjectDate;
	}

	public Date getEndOnProjectDate() {
		return endOnProjectDate;
	}

	public void setEndOnProjectDate(Date endOnProjectDate) {
		this.endOnProjectDate = endOnProjectDate;
	}   
	


@Override
public String toString() {
	return "BaselineByResource [id_baseline_resource=" + idBaselineResource
			+ ", id_baseline=" + baseline 
			+ ", id_resource=" + resource
			+ ", dt_beginOnProject=" + beginOnProjectDate
			+ ", dt_endOnProject=" + endOnProjectDate + "]";
	}
}