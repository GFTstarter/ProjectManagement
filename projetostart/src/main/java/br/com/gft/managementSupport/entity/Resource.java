package br.com.gft.managementSupport.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;


@Entity
@SuppressWarnings("serial")
public class Resource implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name = "id_resource")
	private Long idResource;
	
	@Column(name = "resource", nullable = false)
	private String resource;
		
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)    
    @JoinColumn(name="id_concept")      
	private Concept concept;
	
	@JsonIgnore
	@ManyToMany(cascade = {CascadeType.ALL})
	private List<ActivitySheet> activitySheets;
	
	/*@JsonIgnore						//TERCEIRA TABELA ENTRE RESOURCE E EXPENSES ADICIONADO
	@ManyToMany
	private List<Expenses> expenses;*/
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_location")
	private Location location;
	
	@JsonIgnore
	@Column (name = "hire_date", nullable = true) //HIREDATE ADICIONADO //campo não incluso na planilha importada
    @Temporal(TemporalType.DATE)
    private Date hireDate;
	
/*	@OneToMany(mappedBy = "resource")
    private List<BaselineByResource> baselines; //ADICIONADO CONTROLE DE DATA E/S PROJETO
	*/
	/*
	 * 
	 * @OneToMany(mappedBy = "resource")		//ADICIONA LIGAÇÃO AUSENTE ENTRE (RESOURCE) 1----N (ABSENCE_RESOURCE )
       private List<AbsenceByResource> absence;
	 * 
	 * */
	

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

	public Concept getConcept() {
		return concept;
	}

	public void setConcept(Concept concept) {
		this.concept = concept;
	}

	public List<ActivitySheet> getActivitySheets() {
		return activitySheets;
	}

	public void setActivitySheets(List<ActivitySheet> activitySheets) {
		this.activitySheets = activitySheets;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Resource [idResource=" + idResource + "]";
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	
	
	
	
}
