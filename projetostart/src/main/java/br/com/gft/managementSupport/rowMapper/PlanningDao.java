package br.com.gft.managementSupport.rowMapper;

import java.util.List;

import br.com.gft.managementSupport.gridViews.PlanningView;

public interface PlanningDao {
	
	PlanningView find(Long id);
	PlanningView save (PlanningView obj);
	void delete(Long id);
	public List<PlanningView> findAll();

}
