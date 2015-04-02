package br.com.gft.managementSupport.dao;

import java.util.List;

import br.com.gft.managementSupport.entity.AbsenceByResource;

public interface AbsenceByResourceDao {
	
	List<AbsenceByResource> findAll();
	AbsenceByResource find(Long id);
	AbsenceByResource findByAbsenceByResourceCode(String absenceCode);
	AbsenceByResource save(AbsenceByResource obj);
	void delete(Long id);

}
