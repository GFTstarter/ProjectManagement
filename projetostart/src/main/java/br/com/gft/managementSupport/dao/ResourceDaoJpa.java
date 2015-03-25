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

import br.com.gft.managementSupport.entity.Resource;

public class ResourceDaoJpa implements ResourceDao {

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
	public List<Resource> findAll() {

		final CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<Resource> criteriaQuery = builder.createQuery(Resource.class);

		Root<Resource> root = criteriaQuery.from(Resource.class);
		criteriaQuery.orderBy(builder.asc(root.get("idResource")));

		TypedQuery<Resource> typedQuery = this.getEntityManager().createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}


	@Override
	@Transactional(readOnly = true)
	public Resource find(Long id) {

		return this.getEntityManager().find(Resource.class, id);
	}


	@Override
	@Transactional
	public Resource save(Resource obj) {
		return this.getEntityManager().merge(obj);
	}


	@Override
	@Transactional
	public void delete(Long id) {

		if (id == null) {
			return;
		}

		Resource obj = this.find(id);
		if (obj == null) {
			return;
		}

		this.getEntityManager().remove(obj);
	}


	@Override
	@Transactional
	public Resource findByResourceName(String resource) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Resource> c = cb.createQuery(Resource.class);
        Root<Resource> res = c.from(Resource.class);
        c.where(cb.equal(res.get("resource"), cb.parameter(String.class, "resource")));
        TypedQuery<Resource> q = entityManager.createQuery(c);
        q.setParameter("resource", resource);
        try {
        	return (Resource) q.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}        
		
	}
}
