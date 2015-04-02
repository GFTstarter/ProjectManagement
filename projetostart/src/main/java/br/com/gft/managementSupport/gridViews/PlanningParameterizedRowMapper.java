package br.com.gft.managementSupport.gridViews;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class PlanningParameterizedRowMapper implements ParameterizedRowMapper<PlanningView> {

	public PlanningView mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		PlanningView planning = new PlanningView();
		planning.setidBaselineResource(rs.getLong("id_baseline_resource")); //?? id_baseline_resource ou id_resource ??
		planning.setResource(rs.getString("resource"));
		planning.setHiredate(rs.getDate("hire_date"));
		planning.setConcept(rs.getString("concept"));
		planning.setDtBeginOnProject(rs.getDate("dt_beginonproject"));
		planning.setDtEndOnProject(rs.getDate("dt_endonproject"));
		
		return planning;
		
	}
}


