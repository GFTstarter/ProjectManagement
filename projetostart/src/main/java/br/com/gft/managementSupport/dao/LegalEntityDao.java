package br.com.gft.managementSupport.dao;

import java.util.List;

import br.com.gft.managementSupport.entity.LegalEntity;

public interface LegalEntityDao {

	List<LegalEntity> findAll();
	LegalEntity find(Long id);
	LegalEntity findByLegalEntityName(String legalEntity);
	LegalEntity save(LegalEntity obj);
	void delete(Long id);

}
