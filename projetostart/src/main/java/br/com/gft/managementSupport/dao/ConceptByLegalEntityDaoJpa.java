package br.com.gft.managementSupport.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;

import br.com.gft.managementSupport.entity.Concept;
import br.com.gft.managementSupport.entity.ConceptByLegalEntity;
import br.com.gft.managementSupport.entity.LegalEntity;

public class ConceptByLegalEntityDaoJpa implements ConceptByLegalEntityDao {

	private EntityManager entityManager;

	public EntityManager getEntityManager() {

		return this.entityManager;
	}


	@PersistenceContext
	public void setEntityManager(final EntityManager entityManager) {

		this.entityManager = entityManager;
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public List<ConceptByLegalEntity> findAll() {

		final CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<ConceptByLegalEntity> criteriaQuery = builder.createQuery(ConceptByLegalEntity.class);

		Root<ConceptByLegalEntity> root = criteriaQuery.from(ConceptByLegalEntity.class);
		criteriaQuery.orderBy(builder.asc(root.get("idConceptLegalEntity")));

		TypedQuery<ConceptByLegalEntity> typedQuery = this.getEntityManager().createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}


	@Override
	@Transactional(readOnly = true)
	public ConceptByLegalEntity find(Long id) {

		return this.getEntityManager().find(ConceptByLegalEntity.class, id);
	}


	@Override
	@Transactional
	public ConceptByLegalEntity save(ConceptByLegalEntity obj) {
		return this.getEntityManager().merge(obj);
	}


	@Override
	@Transactional
	public void delete(Long id) {

		if (id == null) {
			return;
		}

		ConceptByLegalEntity obj = this.find(id);
		if (obj == null) {
			return;
		}

		this.getEntityManager().remove(obj);
	}


	@Override
	@Transactional
	public ConceptByLegalEntity findByConcept(Concept concept, LegalEntity legalEntity) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ConceptByLegalEntity> c = cb.createQuery(ConceptByLegalEntity.class);
        Root<ConceptByLegalEntity> conceptByLegalEntity = c.from(ConceptByLegalEntity.class);
        
        Predicate predicate1 = cb.equal(conceptByLegalEntity.get("concept"), cb.parameter(Object.class, "concept"));
        Predicate predicate2 = cb.equal(conceptByLegalEntity.get("legalEntity"), cb.parameter(Object.class, "legalEntity"));
        
        c.where(cb.and(predicate1, predicate2));
        TypedQuery<ConceptByLegalEntity> q = entityManager.createQuery(c);
        q.setParameter("concept", concept);
        q.setParameter("legalEntity", legalEntity);
        
        try {
        	return (ConceptByLegalEntity) q.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
        
	}

}
