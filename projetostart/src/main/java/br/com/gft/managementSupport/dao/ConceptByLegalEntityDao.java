package br.com.gft.managementSupport.dao;

import java.util.List;

import br.com.gft.managementSupport.entity.Concept;
import br.com.gft.managementSupport.entity.ConceptByLegalEntity;
import br.com.gft.managementSupport.entity.LegalEntity;

public interface ConceptByLegalEntityDao {

	List<ConceptByLegalEntity> findAll();
	ConceptByLegalEntity find(Long id);
	ConceptByLegalEntity findByConcept(Concept concept, LegalEntity legalEntity);
	ConceptByLegalEntity save(ConceptByLegalEntity obj);
	void delete(Long id);
}
