package br.com.gft.managementSupport.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import br.com.gft.managementSupport.entity.ActivitySheet;
import br.com.gft.managementSupport.entity.Project;
import br.com.gft.managementSupport.gridViews.ActivitySheetParameterizedRowMapper;
import br.com.gft.managementSupport.gridViews.ActivitySheetView;

public class ActivitySheetDaoJpa extends JdbcDaoSupport implements ActivitySheetDao {

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
	public List<ActivitySheet> findAll() {

		final CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<ActivitySheet> criteriaQuery = builder.createQuery(ActivitySheet.class);

		Root<ActivitySheet> root = criteriaQuery.from(ActivitySheet.class);
		criteriaQuery.orderBy(builder.asc(root.get("idActivitySheet")));

		TypedQuery<ActivitySheet> typedQuery = this.getEntityManager().createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}
	


	@Override
	@Transactional(readOnly = true)
	public ActivitySheet find(Long id) {

		return this.getEntityManager().find(ActivitySheet.class, id);
	}
	


	@Override
	@Transactional
	public ActivitySheet save(ActivitySheet obj) {
		
		return this.getEntityManager().merge(obj);
		
	}


	@Override
	@Transactional
	public void delete(Long id) {

		if (id == null) {
			return;
		}

		ActivitySheet obj = this.find(id);
		if (obj == null) {
			return;
		}

		this.getEntityManager().remove(obj);
	}


	@Override
	@Transactional
	public ActivitySheet findByProjectId(Project project) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ActivitySheet> c = cb.createQuery(ActivitySheet.class);
        Root<ActivitySheet> activity = c.from(ActivitySheet.class);
        c.where(cb.equal(activity.get("project"), cb.parameter(Object.class, "project")));
        TypedQuery<ActivitySheet> q = entityManager.createQuery(c);
        q.setParameter("project", project);
        
        try {
        	return (ActivitySheet) q.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
        
	}


	@Override
	public List<ActivitySheetView> getHours() {
		String sql = "select sum(hours) as hours, month, year from activity_sheet group by month, year order by month";
		
		List<ActivitySheetView> activitysheet = getJdbcTemplate().query(sql, new ActivitySheetParameterizedRowMapper());
		
		return activitysheet;
	}

}