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

import br.com.gft.managementSupport.entity.Expenses;
import br.com.gft.managementSupport.entity.Resource;

public class ExpensesDaoJpa implements ExpensesDao {
	
	private EntityManager entityManager;

	public EntityManager getEntityManager() {

		return this.entityManager;
	}


	@PersistenceContext
	public void setEntityManager(final EntityManager entityManager) {

		this.entityManager = entityManager;
	}

//Exibe lista de Resource
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
	public Expenses find(Long id) {

		return this.getEntityManager().find(Expenses.class, id);
	}


	@Transactional
	public Expenses save(Expenses obj) {
		return this.getEntityManager().merge(obj);
	}


	@Override
	@Transactional
	public void delete(Long id) {

		if (id == null) {
			return;
		}

		Expenses obj = this.find(id);
		if (obj == null) {
			return;
		}

		this.getEntityManager().remove(obj);
	}


	@Override
	@Transactional
	public List<Resource> findByUserId(int idUser) {
				
		final CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<Resource> criteriaQuery = builder.createQuery(Resource.class);

		Root<Expenses> root = criteriaQuery.from(Expenses.class);
		
		criteriaQuery.where(builder.equal(root.get("idUser"), builder.parameter(Integer.class, "idUser")));
		TypedQuery<Resource> q = entityManager.createQuery(criteriaQuery);
		q.setParameter("idUser", idUser);
		
		criteriaQuery.orderBy(builder.asc(root.get("name")));

		return q.getResultList();
			
	}

	@Override
	@Transactional
	public Resource findByResourceCode(String resourceCode) {
		
		final CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<Resource> criteriaQuery = builder.createQuery(Resource.class);

		Root<Resource> root = criteriaQuery.from(Resource.class);
		
		criteriaQuery.where(builder.equal(root.get("resourceCode"), builder.parameter(String.class, "resourceCode")));
		TypedQuery<Resource> q = entityManager.createQuery(criteriaQuery);
		q.setParameter("resourceCode", resourceCode);
		
		try {
			return (Resource) q.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
		
	}
	
}
