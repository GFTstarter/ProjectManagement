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

public class ExpensesDaoJpa implements ExpensesDao {
	
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
	public List<Expenses> findAll() {
		
		final CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<Expenses> criteriaQuery = builder.createQuery(Expenses.class);

		Root<Expenses> root = criteriaQuery.from(Expenses.class);
		criteriaQuery.orderBy(builder.asc(root.get("idExpenses")));

		TypedQuery<Expenses> typedQuery = this.getEntityManager().createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}


	@Override
	@Transactional(readOnly = true)
	public Expenses find(Long id) {
		return this.getEntityManager().find(Expenses.class, id);
	}


	@Override
	@Transactional
	public Expenses findByExpensesCode(String ExpensesCode) {
		
		final CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<Expenses> criteriaQuery = builder.createQuery(Expenses.class);

		Root<Expenses> root = criteriaQuery.from(Expenses.class);
		
		criteriaQuery.where(builder.equal(root.get("expensesCode"), builder.parameter(String.class, "expensesCode")));
		TypedQuery<Expenses> q = entityManager.createQuery(criteriaQuery);
		q.setParameter("expensesCode", ExpensesCode);
		
		try {
			return (Expenses) q.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}


	@Override
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
}
