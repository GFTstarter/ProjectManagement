package br.com.gft.managementSupport.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.gft.managementSupport.entity.BaselineByResource;
import br.com.gft.managementSupport.entity.Resource;

public class BaselineByResourceDaoJpa implements BaselineByResourceDao{

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
	public List<BaselineByResource> findAll() {

		final CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<BaselineByResource> criteriaQuery = builder.createQuery(BaselineByResource.class);

		Root<BaselineByResource> root = criteriaQuery.from(BaselineByResource.class);
		criteriaQuery.orderBy(builder.asc(root.get("idBaselineResource")));

		TypedQuery<BaselineByResource> typedQuery = this.getEntityManager().createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}


	@Override
	@Transactional(readOnly = true)
	public BaselineByResource find(Long id) {
		return this.getEntityManager().find(BaselineByResource.class, id);
	}
	
	@Override
	@Transactional
	public BaselineByResource findByBaselineByResourceCode(Long baselinebyresourceCode) {
		
	//	Resource res = this.getEntityManager().find(Resource.class, baselinebyresourceCode);
		
		final CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<BaselineByResource> criteriaQuery = builder.createQuery(BaselineByResource.class);

		Root<BaselineByResource> root = criteriaQuery.from(BaselineByResource.class);
		
		criteriaQuery.where(builder.equal(root.get("resource"), builder.parameter(Integer.class, "idBaselineResource")));
		TypedQuery<BaselineByResource> q = entityManager.createQuery(criteriaQuery);
		q.setParameter("idBaselineResource", baselinebyresourceCode);
		
		try {
			return (BaselineByResource) q.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}


/*AMANDA
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public BaselineByResource save(BaselineByResource obj) {
		return this.getEntityManager().merge(obj);
	}
*/

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public BaselineByResource save(BaselineByResource obj) {
		return this.getEntityManager().merge(obj);
	}
	

	@Override
	@Transactional
	public void delete(Long id) {

		if (id == null) {
			return;
		}

		BaselineByResource obj = this.find(id);
		if (obj == null) {
			return;
		}

		this.getEntityManager().remove(obj);
	}
}
