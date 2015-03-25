package br.com.gft.managementSupport.entity;

import java.io.Serializable;
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

@Entity
@SuppressWarnings("serial")
public class Location implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name = "id_location")
	private Long idLocation;
	
	@Column (name = "location", nullable = false, unique = true)
	private String location;
	
	@ManyToOne(fetch = FetchType.LAZY)    
    @JoinColumn(name="id_legal_entity")      
	private LegalEntity legalEntity;
	
	@ManyToMany(cascade = {CascadeType.ALL})
	private List<Holiday> holidays;
	
	
	public Long getIdLocation() {
		return idLocation;
	}

	public void setIdLocation(Long idLocation) {
		this.idLocation = idLocation;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public LegalEntity getLegalEntity() {
		return legalEntity;
	}

	public void setLegalEntity(LegalEntity legalEntity) {
		this.legalEntity = legalEntity;
	}

	public List<Holiday> getHolidays() {
		return holidays;
	}

	public void setHolidays(List<Holiday> holidays) {
		this.holidays = holidays;
	}

	@Override
	public String toString() {
		return "Location [idLocation=" + idLocation + ", location=" + location
				+ ", legalEntity=" + legalEntity + ", holidays=" + holidays
				+ "]";
	}

}
