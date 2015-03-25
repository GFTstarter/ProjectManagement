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

import br.com.gft.managementSupport.entity.LegalEntity;

public class LegalEntityDaoJpa implements LegalEntityDao {
	
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
	public List<LegalEntity> findAll() {

		final CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<LegalEntity> criteriaQuery = builder.createQuery(LegalEntity.class);

		Root<LegalEntity> root = criteriaQuery.from(LegalEntity.class);
		criteriaQuery.orderBy(builder.asc(root.get("idLegalEntity")));

		TypedQuery<LegalEntity> typedQuery = this.getEntityManager().createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}


	@Override
	@Transactional(readOnly = true)
	public LegalEntity find(Long id) {

		return this.getEntityManager().find(LegalEntity.class, id);
	}


	@Override
	@Transactional
	public LegalEntity save(LegalEntity obj) {
		return this.getEntityManager().merge(obj);
	}


	@Override
	@Transactional
	public void delete(Long id) {

		if (id == null) {
			return;
		}

		LegalEntity obj = this.find(id);
		if (obj == null) {
			return;
		}

		this.getEntityManager().remove(obj);
	}


	@Override
	@Transactional
	public LegalEntity findByLegalEntityName(String legalEntity) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<LegalEntity> c = cb.createQuery(LegalEntity.class);
        Root<LegalEntity> legalEnt = c.from(LegalEntity.class);
        c.where(cb.equal(legalEnt.get("legalEntity"), cb.parameter(String.class, "legalEntity")));
        TypedQuery<LegalEntity> q = entityManager.createQuery(c);
        q.setParameter("legalEntity", legalEntity);
        
        try {
        	return (LegalEntity) q.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
        
	}
	
}
