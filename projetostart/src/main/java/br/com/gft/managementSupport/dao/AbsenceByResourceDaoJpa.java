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

import br.com.gft.managementSupport.entity.AbsenceByResource;

public class AbsenceByResourceDaoJpa implements AbsenceByResourceDao {
	
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
	public List<AbsenceByResource> findAll() {
		
		final CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<AbsenceByResource> criteriaQuery = builder.createQuery(AbsenceByResource.class);

		Root<AbsenceByResource> root = criteriaQuery.from(AbsenceByResource.class);
		criteriaQuery.orderBy(builder.asc(root.get("idAbsenceByResource")));

		TypedQuery<AbsenceByResource> typedQuery = this.getEntityManager().createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}


	@Override
	@Transactional(readOnly = true)
	public AbsenceByResource find(Long id) {
		return this.getEntityManager().find(AbsenceByResource.class, id);
	}


	@Override
	@Transactional
	public AbsenceByResource findByAbsenceByResourceCode(String absencebyresourceCode) {
		
		final CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<AbsenceByResource> criteriaQuery = builder.createQuery(AbsenceByResource.class);

		Root<AbsenceByResource> root = criteriaQuery.from(AbsenceByResource.class);
		
		criteriaQuery.where(builder.equal(root.get("absencebyresourceCode"), builder.parameter(String.class, "absencebyresourceCode")));
		TypedQuery<AbsenceByResource> q = entityManager.createQuery(criteriaQuery);
		q.setParameter("absencebyresourceCode", absencebyresourceCode);
		
		try {
			return (AbsenceByResource) q.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}


	@Override
	@Transactional
	public AbsenceByResource save(AbsenceByResource obj) {
		return this.getEntityManager().merge(obj);
	}


	@Override
	@Transactional
	public void delete(Long id) {
		
		if (id == null) {
			return;
		}

		AbsenceByResource obj = this.find(id);
		if (obj == null) {
			return;
		}

		this.getEntityManager().remove(obj);
		
	}

	
	
		
	


	

}
