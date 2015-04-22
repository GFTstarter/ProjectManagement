package br.com.gft.managementSupport.rowMapper;

import java.util.List;

import br.com.gft.managementSupport.entity.ConceptByLegalEntity;
import br.com.gft.managementSupport.gridViews.DashboardView;

public interface DashboardDao {
	
	DashboardView find(Long id);
	ConceptByLegalEntity save (ConceptByLegalEntity newsEntry);
	void delete(Long id);
	public List<DashboardView> findAll();
	public int findTotalDashboard();
}
