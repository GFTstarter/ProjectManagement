package br.com.gft.managementSupport.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;

import br.com.gft.managementSupport.entity.Absence;

public class AbsenceDaoJpa implements AbsenceDao{

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
	public List<Absence> findAll() {

		final CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<Absence> criteriaQuery = builder.createQuery(Absence.class);

		Root<Absence> root = criteriaQuery.from(Absence.class);
		criteriaQuery.orderBy(builder.asc(root.get("idAbsence")));

		TypedQuery<Absence> typedQuery = this.getEntityManager().createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public Absence find(Long id) {
		
		return this.getEntityManager().find(Absence.class, id);
	}

	@Override
	@Transactional
	public Absence save(Absence obj) {
		
		return this.getEntityManager().merge(obj);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		if (id == null) {
			return;
		}

		Absence obj = this.find(id);
		if (obj == null) {
			return;
		}

		this.getEntityManager().remove(obj);
		
	}

}
