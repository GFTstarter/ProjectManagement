package br.com.gft.managementSupport.dao;

import java.util.List;

import br.com.gft.managementSupport.entity.Absence;



public interface AbsenceDao {
	
	List<Absence> findAll();
	Absence find(Long id);
	Absence save(Absence obj);
	void delete(Long id);

}
