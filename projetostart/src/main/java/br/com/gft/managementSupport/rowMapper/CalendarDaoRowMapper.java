package br.com.gft.managementSupport.rowMapper;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import br.com.gft.managementSupport.entity.Holiday;
import br.com.gft.managementSupport.gridViews.CalendarParameterizedRowMapper;
import br.com.gft.managementSupport.gridViews.CalendarView;

public class CalendarDaoRowMapper extends JdbcDaoSupport implements CalendarDao {

	
	private EntityManager entityManager;

	public EntityManager getEntityManager() {

		return this.entityManager;
	}


	@PersistenceContext
	public void setEntityManager(final EntityManager entityManager) {

		this.entityManager = entityManager;
	}
	
	public CalendarView find(Long id) {
		
		return this.getEntityManager().find(CalendarView.class, id);
	}

	public Holiday save(Holiday obj) {
		
		return this.getEntityManager().merge(obj);
	}

	public void delete(Long id) {
		
		if (id == null) {
			return;
		}

		CalendarView obj = this.find(id);
		if (obj == null) {
			return;
		}

		this.getEntityManager().remove(obj);
	}

	public List<CalendarView> findAll() {
		String sql = "SELECT * FROM vw_calendar";
		
		List<CalendarView> holidays = getJdbcTemplate().query(sql, new CalendarParameterizedRowMapper());
		
		return holidays;
	}

	public int findTotalHoliday() {
		String sql = "SELECT COUNT(*) FROM vw_calendar";
		 
		int total = getJdbcTemplate().queryForInt(sql);
				
		return total;
	}

}
