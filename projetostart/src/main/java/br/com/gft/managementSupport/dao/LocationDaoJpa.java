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

import br.com.gft.managementSupport.entity.Location;

public class LocationDaoJpa implements LocationDao {

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
	public List<Location> findAll() {

		final CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<Location> criteriaQuery = builder.createQuery(Location.class);

		Root<Location> root = criteriaQuery.from(Location.class);
		criteriaQuery.orderBy(builder.asc(root.get("idLocation")));

		TypedQuery<Location> typedQuery = this.getEntityManager().createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}


	@Override
	@Transactional(readOnly = true)
	public Location find(Long id) {

		return this.getEntityManager().find(Location.class, id);
	}


	@Override
	@Transactional
	public Location save(Location obj) {
		return this.getEntityManager().merge(obj);
	}


	@Override
	@Transactional
	public void delete(Long id) {

		if (id == null) {
			return;
		}

		Location obj = this.find(id);
		if (obj == null) {
			return;
		}

		this.getEntityManager().remove(obj);
	}


	@Override
	@Transactional
	public Location findByLocationName(String location) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Location> c = cb.createQuery(Location.class);
        Root<Location> loc = c.from(Location.class);
        c.where(cb.equal(loc.get("location"), cb.parameter(String.class, "location")));
        TypedQuery<Location> q = entityManager.createQuery(c);
        q.setParameter("location", location);
        try {
        	return (Location) q.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		} 
        
	}

}
