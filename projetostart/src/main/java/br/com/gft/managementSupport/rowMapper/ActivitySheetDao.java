package br.com.gft.managementSupport.rowMapper;

import java.util.List;

import br.com.gft.managementSupport.entity.ActivitySheet;
import br.com.gft.managementSupport.gridViews.ActivitySheetView;

public interface ActivitySheetDao {
	
	ActivitySheet find(Long id);
	ActivitySheet save (ActivitySheet newsEntry);
	void delete(Long id);
	public List<ActivitySheet> findAll();
	public int findTotalActivitySheet();
	List<ActivitySheetView> getHours();
}
