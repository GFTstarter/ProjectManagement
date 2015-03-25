package br.com.gft.managementSupport.dao;

import java.util.List;

import br.com.gft.managementSupport.entity.Concept;

public interface ConceptDao {

	List<Concept> findAll();
	Concept find(Long id);
	Concept findByConceptName(String concept);
	Concept save(Concept obj);
	void delete(Long id);
}
