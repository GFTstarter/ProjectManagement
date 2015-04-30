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
	
	//AMANDA 30/04/2015
	public List<DashboardView> findAll(){
		
		String sql = "SELECT * FROM VW_DASHBOARD order by id_concept";
		
		List<DashboardView> dashboards = getJdbcTemplate().query(sql, new DashboardParameterizedRowMapper());
		
		return dashboards;
	}
	
	
	
	/*//AMANDA 30/04/215
	public List<DashboardView> findAll(){
		
		String sql = "select c.id_concept, c.concept, sum(b.actual_cost) as actual_cost , sum(b.actual_hours)," +
					" ((sum(b.blp_hours)) * (sum(b.cost_rate))) as blptotalcost, sum(b.eac), sum(b.eac_cost)" +
					" from baseline b JOIN baseline_resources bs ON b.id_baseline = bs.id_baseline" +
					" JOIN resource r ON bs.id_resource = r.id_resource"+
					" JOIN concept c ON r.id_concept = c.id_concept" +
					" group by c.id_concept";
		
		List<DashboardView> dashboards = getJdbcTemplate().query(sql, new DashboardParameterizedRowMapper());
		
		return dashboards;
	}*/

	
	public int findTotalDashboard(){
		
		String sql = "SELECT COUNT(*) FROM VW_DASHBOARD";
		 
		int total = getJdbcTemplate().queryForInt(sql);
				
		return total;
	}
	
}