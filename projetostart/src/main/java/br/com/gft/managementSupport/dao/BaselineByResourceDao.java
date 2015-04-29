package br.com.gft.managementSupport.dao;

import java.util.List;

import br.com.gft.managementSupport.entity.BaselineByResource;

public interface BaselineByResourceDao {

	List<BaselineByResource> findAll();
	BaselineByResource find(Long id);
	BaselineByResource findByBaselineByResourceCode(Long baselineByResourceCode);
	BaselineByResource save(BaselineByResource obj);
	void delete(Long id);
}
