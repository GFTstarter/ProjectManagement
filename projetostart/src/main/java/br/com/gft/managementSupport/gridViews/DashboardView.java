package br.com.gft.managementSupport.gridViews;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DashboardView implements Serializable {

	private Long idBaseline;
	private Double costRate;
	private Integer blpHours;
	private Double blptotalcost;
	private Integer eac;
	private Double eacCost;	
	private Double costdiff;
		
	public DashboardView (){}
	
	public DashboardView ( Long idBaseline,
						   Double costRate,
						   Integer blpHours,
						   Double blptotalcost,
						   Integer eac,
						   Double eacCost,
						   Double costdiff){
		
		this.idBaseline = idBaseline;
		this.costRate = costRate;
		this.blpHours = blpHours;
		this.blptotalcost = blptotalcost;
		this.eac = eac;
		this.eacCost = eacCost;
		this.costdiff = costdiff;
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

	public Double getBlptotalcost() {
		return blptotalcost;
	}

	public void setBlptotalcost(Double blptotalcost) {
		this.blptotalcost = blptotalcost;
	}

	public Integer getEac() {
		return eac;
	}

	public void setEac(Integer eac) {
		this.eac = eac;
	}

	public Double getEacCost() {
		return eacCost;
	}

	public void setEacCost(Double eacCost) {
		this.eacCost = eacCost;
	}

	public Double getCostdiff() {
		return costdiff;
	}

	public void setCostdiff(Double costdiff) {
		this.costdiff = costdiff;
	}

	
	
	
	
}
