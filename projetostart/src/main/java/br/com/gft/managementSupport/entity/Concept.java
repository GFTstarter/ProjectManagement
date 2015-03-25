package br.com.gft.managementSupport.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@SuppressWarnings("serial")
public class Concept implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name = "id_concept")
	private Long idConcept;	
	
	@Column (name = "concept", nullable = false)
	private String concept;
	
	@OneToMany (mappedBy = "concept")
	private List<Resource> resources;

	public Long getIdConcept() {
		return idConcept;
	}

	public void setIdConcept(Long idConcept) {
		this.idConcept = idConcept;
	}

	public String getConcept() {
		return concept;
	}

	public void setConcept(String concept) {
		this.concept = concept;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	@Override
	public String toString() {
		return "Concept [idConcept=" + idConcept + ", concept=" + concept
				+ ", resources=" + resources + "]";
	}
		
}
