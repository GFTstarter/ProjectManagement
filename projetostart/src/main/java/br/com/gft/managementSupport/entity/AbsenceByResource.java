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

@Entity (name = "absence_resource")
@SuppressWarnings("serial")
public class AbsenceByResource implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name = "id_absence_resource")
	private Long idAbsenceResource;
	
  /*@ManyToOne(fetch = FetchType.LAZY)	//FK TIRADA POIS DADOS SAO INSERIDOS ATRAVEZ DE COMBO BOX, EVITANDO ERROS DE INSERÇÃO
    @JoinColumn(name="id_absence")
    private Absence absence;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_resource")
    private Resource resource;
  */
	
	@Column (name = "id_absence")
    private int idAbsence;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_resource")
    private Resource resource;
    
    @Column (name = "start_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startDate;
        
    @Column (name = "end_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date endDate;
    
    @Column (name = "work_days", nullable = false)
    private int workDays;
    
    @Column (name = "work_hours", nullable = true)
    private int workHours;
    
	@Column (name = "description", nullable = true)
    private String description;
    


	

	public Long getIdAbsenceResource() {
		return idAbsenceResource;
	}





	public void setIdAbsenceResource(Long idAbsenceResource) {
		this.idAbsenceResource = idAbsenceResource;
	}





	public int getIdAbsence() {
		return idAbsence;
	}





	public void setIdAbsence(int idAbsence) {
		this.idAbsence = idAbsence;
	}





	public Resource getResource() {
		return resource;
	}





	public void setResource(Resource resource) {
		this.resource = resource;
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





	public int getWorkDays() {
		return workDays;
	}





	public void setWorkDays(int workDays) {
		this.workDays = workDays;
	}





	public int getWorkHours() {
		return workHours;
	}





	public void setWorkHours(int workHours) {
		this.workHours = workHours;
	}





	public String getDescription() {
		return description;
	}





	public void setDescription(String description) {
		this.description = description;
	}





	@Override
	public String toString() {
		return "AbsenceByResource [idAbsenceResource=" + idAbsenceResource
				+ ", absence=" + idAbsence + ", resource=" + resource
				+ ", startDate=" + startDate + ", endDate=" + endDate
				+ ", workDays=" + workDays + "]";
	}
    
}
