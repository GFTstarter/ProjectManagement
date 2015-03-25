package br.com.gft.managementSupport.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity (name = "legal_entity")
@SuppressWarnings("serial")
public class LegalEntity implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name = "id_legal_entity")
	private Long idLegalEntity;	
	
	@Column (name = "legal_entity", nullable = false)
	private String legalEntity;
	
	@OneToMany (mappedBy = "legalEntity")
	private List<Location> locations;
	
	@OneToMany(mappedBy = "legalEntity")
    private List<ConceptByLegalEntity> concepts;

	public Long getIdLegalEntity() {
		return idLegalEntity;
	}

	public void setIdLegalEntity(Long idLegalEntity) {
		this.idLegalEntity = idLegalEntity;
	}

	public String getLegalEntity() {
		return legalEntity;
	}

	public void setLegalEntity(String legalEntity) {
		this.legalEntity = legalEntity;
	}

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	public List<ConceptByLegalEntity> getConcepts() {
		return concepts;
	}

	public void setConcepts(List<ConceptByLegalEntity> concepts) {
		this.concepts = concepts;
	}

	@Override
	public String toString() {
		return "LegalEntity [idLegalEntity=" + idLegalEntity + ", legalEntity="
				+ legalEntity + ", locations=" + locations + ", concepts="
				+ concepts + "]";
	}


}
