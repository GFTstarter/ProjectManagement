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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
@SuppressWarnings("serial")
public class Baseline implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name = "id_baseline")
	private Long idBaseline;
	
	@ManyToOne
	@JoinColumn (name = "id_project", nullable = false)
	private Project project;
	
	@Column (name = "year", nullable = false)
	private int year;
		
	@Column (name = "cost_rate", nullable = false)
	private Double costRate;
	
	@Column (name = "blp_hours", nullable = false)
	private int blpHours;
		
	@Column (name = "actual_hours", nullable = false)
	private Double actualHours;
	
	@Column (name = "actual_cost", nullable = false)
	private Double actualCost;
	
	@Column (name = "eac", nullable = false)
	private int eac;
	
	@Column (name = "eac_cost", nullable = false)
	private Double eacCost;	
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(name="baseline_location", joinColumns=@JoinColumn(name="id_baseline"), inverseJoinColumns=@JoinColumn(name="id_location"))
	private List<Location> locations;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(name="baseline_resources", joinColumns=@JoinColumn(name="id_baseline"), inverseJoinColumns=@JoinColumn(name="id_resources"))
	private List<Resource> resources;
	
	/*@OneToMany(mappedBy = "baseline")
    private List<BaselineByResource> resources;   //ADICIONADO CONTROLE DE DATA E/S PROJETO
	*/
	
	public Long getIdBaseline() {
		return idBaseline;
	}

	public void setIdBaseline(Long idBaseline) {
		this.idBaseline = idBaseline;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Double getCostRate() {
		return costRate;
	}

	public void setCostRate(Double costRate) {
		this.costRate = costRate;
	}

	public int getBlpHours() {
		return blpHours;
	}

	public void setBlpHours(int blpHours) {
		this.blpHours = blpHours;
	}

	public Double getActualHours() {
		return actualHours;
	}

	public void setActualHours(Double actualHours) {
		this.actualHours = actualHours;
	}

	public Double getActualCost() {
		return actualCost;
	}

	public void setActualCost(Double actualCost) {
		this.actualCost = actualCost;
	}

	public int getEac() {
		return eac;
	}

	public void setEac(int eac) {
		this.eac = eac;
	}

	public Double getEacCost() {
		return eacCost;
	}

	public void setEacCost(Double eacCost) {
		this.eacCost = eacCost;
	}

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	@Override
	public String toString() {
		return "Baseline [idBaseline=" + idBaseline + ", project=" + project
				+ ", year=" + year + ", costRate=" + costRate + ", blpHours="
				+ blpHours + ", actualHours=" + actualHours + ", actualCost="
				+ actualCost + ", eac=" + eac + ", eacCost=" + eacCost
				+ ", locations=" + locations + ", resources=" + resources + "]";
	}
		
}
