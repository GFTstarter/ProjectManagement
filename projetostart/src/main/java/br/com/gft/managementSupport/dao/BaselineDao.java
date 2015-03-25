package br.com.gft.managementSupport.dao;

import java.util.List;

import br.com.gft.managementSupport.entity.Baseline;

public interface BaselineDao {

	List<Baseline> findAll();
	Baseline find(Long id);
	Baseline save(Baseline obj);
	void delete(Long id);
	
}
