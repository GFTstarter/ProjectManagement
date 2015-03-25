package br.com.gft.managementSupport.dao;

import java.util.List;

import br.com.gft.managementSupport.entity.Holiday;

public interface HolidayDao {

	List<Holiday> findAll();
	Holiday find(Long id);
	Holiday save(Holiday obj);
	void delete(Long id);
	
}
