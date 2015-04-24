package br.com.gft.managementSupport.gridViews;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DashboardView implements Serializable {

	private Long idBaseline;
	private Double costRate;
	private Integer blpHours;
	private Integer eac;
	private Double eacCost;	
		
	public DashboardView (){}
	
	public DashboardView ( Long idBaseline,
						   Double costRate,
						   Integer blpHours,
						   Integer eac,
						   Double eacCost){
		
		this.idBaseline = idBaseline;
		this.costRate = costRate;
		this.blpHours = blpHours;
		this.eac = eac;
		this.eacCost = eacCost;
	}

	public Long getIdBaseline() {
		return idBaseline;
	}

	public void setIdBaseline(Long idBaseline) {
		this.idBaseline = idBaseline;
	}

	public Double getCostRate() {
		return costRate;
	}

	public void setCostRate(Double costRate) {
		this.costRate = costRate;
	}

	public Integer getBlpHours() {
		return blpHours;
	}

	public void setBlpHours(Integer blpHours) {
		this.blpHours = blpHours;
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

	
	
	
	
}
