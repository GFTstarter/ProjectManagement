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

import br.com.gft.managementSupport.entity.Project;

public class ProjectDaoJpa implements ProjectDao {
	
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
	public List<Project> findAll() {

		final CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<Project> criteriaQuery = builder.createQuery(Project.class);

		Root<Project> root = criteriaQuery.from(Project.class);
		criteriaQuery.orderBy(builder.asc(root.get("idProject")));

		TypedQuery<Project> typedQuery = this.getEntityManager().createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}


	@Override
	@Transactional(readOnly = true)
	public Project find(Long id) {

		return this.getEntityManager().find(Project.class, id);
	}


	@Override
	@Transactional
	public Project save(Project obj) {
		return this.getEntityManager().merge(obj);
	}


	@Override
	@Transactional
	public void delete(Long id) {

		if (id == null) {
			return;
		}

		Project obj = this.find(id);
		if (obj == null) {
			return;
		}

		this.getEntityManager().remove(obj);
	}


	@Override
	@Transactional
	public List<Project> findByUserId(int idUser) {
				
		final CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<Project> criteriaQuery = builder.createQuery(Project.class);

		Root<Project> root = criteriaQuery.from(Project.class);
		
		criteriaQuery.where(builder.equal(root.get("idUser"), builder.parameter(Integer.class, "idUser")));
		TypedQuery<Project> q = entityManager.createQuery(criteriaQuery);
		q.setParameter("idUser", idUser);
		
		criteriaQuery.orderBy(builder.asc(root.get("name")));

		return q.getResultList();
			
	}


	@Override
	@Transactional
	public Project findByProjectCode(String projectCode) {
		
		final CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<Project> criteriaQuery = builder.createQuery(Project.class);

		Root<Project> root = criteriaQuery.from(Project.class);
		
		criteriaQuery.where(builder.equal(root.get("projectCode"), builder.parameter(String.class, "projectCode")));
		TypedQuery<Project> q = entityManager.createQuery(criteriaQuery);
		q.setParameter("projectCode", projectCode);
		
		try {
			return (Project) q.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
		
	}
	
}
