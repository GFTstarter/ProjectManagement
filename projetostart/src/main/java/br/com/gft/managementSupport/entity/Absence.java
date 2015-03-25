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
public class Absence implements Serializable {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name = "id_absence")
	private Long idAbsence;
	
	@Column (name = "absence", nullable = false, unique = true)
	private String absence;
	
	@OneToMany(mappedBy = "absence")
    private List<AbsenceByResource> resources;

	public Long getIdAbsence() {
		return idAbsence;
	}

	public void setIdAbsence(Long idAbsence) {
		this.idAbsence = idAbsence;
	}

	public String getAbsence() {
		return absence;
	}

	public void setAbsence(String absence) {
		this.absence = absence;
	}

	public List<AbsenceByResource> getResources() {
		return resources;
	}

	public void setResources(List<AbsenceByResource> resources) {
		this.resources = resources;
	}

	
	
	@Override
	public String toString() {
		return "Absence [idAbsence=" + idAbsence + ", absence=" + absence
				+ ", resources=" + resources + "]";
	}
	
}
