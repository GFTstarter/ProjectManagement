package br.com.gft.managementSupport.dao;

import java.util.List;

import br.com.gft.managementSupport.entity.Resource;

public interface ResourceDao {

	List<Resource> findAll();
	Resource find(Long id);
	Resource findByResourceName(String resource);
	Resource save(Resource obj);
	void delete(Long id);
}
