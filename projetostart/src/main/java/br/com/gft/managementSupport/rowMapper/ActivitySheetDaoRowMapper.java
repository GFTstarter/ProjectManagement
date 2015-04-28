package br.com.gft.managementSupport.rowMapper;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import br.com.gft.managementSupport.entity.ActivitySheet;
import br.com.gft.managementSupport.gridViews.ActivitySheetParameterizedRowMapper;
import br.com.gft.managementSupport.gridViews.ActivitySheetView;

public class ActivitySheetDaoRowMapper  extends JdbcDaoSupport implements ActivitySheetDao {

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
	public ActivitySheet find(Long idActivitySheet) {

		return this.getEntityManager().find(ActivitySheet.class, idActivitySheet);
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
	
	//LISTA AS HORAS NO EIXO Y DO CHART
	@Override
	@Transactional(readOnly = true)
	public List<ActivitySheetView> getHours(){
		
		String sql = "select sum(hours) as hours, month from activity_sheet group by month, year order by month";
		
		List<ActivitySheetView> activitysheet = getJdbcTemplate().query(sql, new ActivitySheetParameterizedRowMapper());
		
		return activitysheet;
	}


	@Override
	public List<ActivitySheet> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int findTotalActivitySheet() {
		// TODO Auto-generated method stub
		return 0;
	}	


	/*
	public List<ActivitySheetView> findAll(){
		
		String sql = "SELECT * FROM VW_DASHBOARD";
		
		List<DashboardView> dashboards = getJdbcTemplate().query(sql, new DashboardParameterizedRowMapper());
		
		return dashboards;
	}
	
	
	public int findTotalDashboard(){
		
		String sql = "SELECT COUNT(*) FROM VW_DASHBOARD";
		 
		int total = getJdbcTemplate().queryForInt(sql);
				
		return total;
	}*/
	
}