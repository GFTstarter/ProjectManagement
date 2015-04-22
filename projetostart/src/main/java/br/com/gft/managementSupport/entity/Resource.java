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
	
	//GERA ID AUTOMATICO PARA ID_RESOURCE
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name = "id_resource")
	private Long idResource;
	
	//RECEBE O NOME DO RESOURCE
	@Column(name = "resource", nullable = false)
	private String resource;
		
	//CHAVE ESTRANGEIRA DO CAMPO ID_CONCEPT DA TABELA RESOURCE COM ID_CONCEPT DA TABELA CONCEPT
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)    
    @JoinColumn(name="id_concept")      
	private Concept concept;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_location")
	private Location location;

	@JsonIgnore
	@Column (name = "hire_date", nullable = true) //HIREDATE ADICIONADO //campo não incluso na planilha importada
    @Temporal(TemporalType.DATE)
    private Date hireDate;
	
	/*@JsonIgnore						//TERCEIRA TABELA ENTRE RESOURCE E EXPENSES ADICIONADO
	@ManyToMany
	private List<Expenses> expenses;*/
	
	@JsonIgnore
	@ManyToMany(cascade = {CascadeType.ALL})
	private List<ActivitySheet> activitySheets;
	
	@JsonIgnore
	@OneToMany(mappedBy = "resource")
    private List<BaselineByResource> baselines; //ADICIONADO CONTROLE DE DATA E/S PROJETO

	/*@JsonIgnore
	@OneToMany(mappedBy = "resource")		//FK TIRADA POIS DADOS SAO INSERIDOS ATRAVEZ DE COMBO BOX, EVITANDO ERROS DE INSERÇÃO
    private List<AbsenceByResource> absence;*/
	
	
	public Resource() {
		
	}

	public Resource(String resource) {
		super();
		this.resource = resource;
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

	public Concept getConcept() {
		return concept;
	}

	public void setConcept(Concept concept) {
		this.concept = concept;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public List<ActivitySheet> getActivitySheets() {
		return activitySheets;
	}

	public void setActivitySheets(List<ActivitySheet> activitySheets) {
		this.activitySheets = activitySheets;
	}

	public List<BaselineByResource> getBaselines() {
		return baselines;
	}

	public void setBaselines(List<BaselineByResource> baselines) {
		this.baselines = baselines;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((activitySheets == null) ? 0 : activitySheets.hashCode());
		result = prime * result
				+ ((baselines == null) ? 0 : baselines.hashCode());
		result = prime * result + ((concept == null) ? 0 : concept.hashCode());
		result = prime * result
				+ ((hireDate == null) ? 0 : hireDate.hashCode());
		result = prime * result
				+ ((idResource == null) ? 0 : idResource.hashCode());
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result
				+ ((resource == null) ? 0 : resource.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Resource other = (Resource) obj;
		if (activitySheets == null) {
			if (other.activitySheets != null)
				return false;
		} else if (!activitySheets.equals(other.activitySheets))
			return false;
		if (baselines == null) {
			if (other.baselines != null)
				return false;
		} else if (!baselines.equals(other.baselines))
			return false;
		if (concept == null) {
			if (other.concept != null)
				return false;
		} else if (!concept.equals(other.concept))
			return false;
		if (hireDate == null) {
			if (other.hireDate != null)
				return false;
		} else if (!hireDate.equals(other.hireDate))
			return false;
		if (idResource == null) {
			if (other.idResource != null)
				return false;
		} else if (!idResource.equals(other.idResource))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (resource == null) {
			if (other.resource != null)
				return false;
		} else if (!resource.equals(other.resource))
			return false;
		return true;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getResource();
	}


	

	
	
	
	
}
