package br.com.gft.managementSupport.rowMapper;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import br.com.gft.managementSupport.gridViews.PlanningParameterizedRowMapper;
import br.com.gft.managementSupport.gridViews.PlanningView;

public class PlanningDaoRowMapper extends JdbcDaoSupport implements PlanningDao {

	
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
	public PlanningView find(Long id) {

		return this.getEntityManager().find(PlanningView.class, id);
	}
	
	
	@Override
	@Transactional
	public PlanningView save(PlanningView obj) {
		
		return this.getEntityManager().merge(obj);
	}
	
	
	@Override
	@Transactional
	public void delete(Long id) {

		if (id == null) {
			return;
		}

		PlanningView obj = this.find(id);
		if (obj == null) {
			return;
		}

		this.getEntityManager().remove(obj);
	}
		
	
	public List<PlanningView> findAll(){
		
		String sql = "SELECT * FROM VW_PLANNING";
		 
		List<PlanningView> planning = getJdbcTemplate().query(sql, new PlanningParameterizedRowMapper());
		
		return planning;
	}
}
	
