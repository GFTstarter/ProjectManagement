package br.com.gft.managementSupport.rowMapper;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import br.com.gft.managementSupport.entity.BaselineByResource;
import br.com.gft.managementSupport.gridViews.DashboardParameterizedRowMapper;
import br.com.gft.managementSupport.gridViews.DashboardView;

public class DashboardDaoRowMapper  extends JdbcDaoSupport implements DashboardDao {

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
	public DashboardView find(Long idBaseline) {

		return this.getEntityManager().find(DashboardView.class, idBaseline);
	}
	
	
	@Override
	@Transactional
	public BaselineByResource save(BaselineByResource obj) {
		
		return this.getEntityManager().merge(obj);
	}
	
	
	@Override
	@Transactional
	public void delete(Long id) {

		if (id == null) {
			return;
		}

		DashboardView obj = this.find(id);
		if (obj == null) {
			return;
		}

		this.getEntityManager().remove(obj);
	}
	
	
	public List<DashboardView> findAll(){
		
		String sql = "SELECT * FROM VW_DASHBOARD";
		
		List<DashboardView> dashboards = getJdbcTemplate().query(sql, new DashboardParameterizedRowMapper());
		
		return dashboards;
	}
	
	
	public int findTotalDashboard(){
		
		String sql = "SELECT COUNT(*) FROM VW_DASHBOARD";
		 
		int total = getJdbcTemplate().queryForInt(sql);
				
		return total;
	}
	
}