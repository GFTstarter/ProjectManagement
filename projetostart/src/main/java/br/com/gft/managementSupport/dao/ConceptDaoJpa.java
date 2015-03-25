package br.com.gft.managementSupport.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;

import br.com.gft.managementSupport.entity.Concept;

public class ConceptDaoJpa implements ConceptDao {

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
	public List<Concept> findAll() {

		final CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<Concept> criteriaQuery = builder.createQuery(Concept.class);

		Root<Concept> root = criteriaQuery.from(Concept.class);
		criteriaQuery.orderBy(builder.asc(root.get("idConcept")));

		TypedQuery<Concept> typedQuery = this.getEntityManager().createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}


	@Override
	@Transactional(readOnly = true)
	public Concept find(Long id) {

		return this.getEntityManager().find(Concept.class, id);
	}


	@Override
	@Transactional
	public Concept save(Concept obj) {
		return this.getEntityManager().merge(obj);
	}


	@Override
	@Transactional
	public void delete(Long id) {

		if (id == null) {
			return;
		}

		Concept obj = this.find(id);
		if (obj == null) {
			return;
		}

		this.getEntityManager().remove(obj);
	}


	@Override
	@Transactional
	public Concept findByConceptName(String concept) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Concept> c = cb.createQuery(Concept.class);
        Root<Concept> conc = c.from(Concept.class);
        c.where(cb.equal(conc.get("concept"), cb.parameter(String.class, "concept")));
        TypedQuery<Concept> q = entityManager.createQuery(c);
        q.setParameter("concept", concept);
        
        try {
        	return (Concept) q.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
        
	}
}
