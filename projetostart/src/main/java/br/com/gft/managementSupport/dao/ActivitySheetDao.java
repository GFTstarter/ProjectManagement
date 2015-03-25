package br.com.gft.managementSupport.dao;

import java.util.List;

import br.com.gft.managementSupport.entity.ActivitySheet;
import br.com.gft.managementSupport.entity.Project;

public interface ActivitySheetDao {

	List<ActivitySheet> findAll();
	ActivitySheet find(Long id);
	ActivitySheet findByProjectId(Project idProject);
	ActivitySheet save(ActivitySheet obj);
	void delete(Long id);
	
}
