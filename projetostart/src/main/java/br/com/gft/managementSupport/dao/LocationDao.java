package br.com.gft.managementSupport.dao;

import java.util.List;

import br.com.gft.managementSupport.entity.Location;

public interface LocationDao {

	List<Location> findAll();
	Location find(Long id);
	Location findByLocationName(String location);
	Location save(Location obj);
	void delete(Long id);
	
}
