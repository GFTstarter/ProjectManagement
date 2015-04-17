package br.com.gft.managementSupport.gridViews;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class PlanningParameterizedRowMapper implements ParameterizedRowMapper<PlanningView> {

	public PlanningView mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		
		
		PlanningView planning = new PlanningView();
		
		planning.setIdActivity(rs.getLong("id_activity_sheet"));
		planning.setIdResource(rs.getLong("id_resource"));
		planning.setResource(rs.getString("resource"));
		planning.setHiredate(rs.getDate("hire_date"));
		planning.setConcept(rs.getString("concept"));
		planning.setDate(rs.getDate("date"));
		planning.setHours(rs.getInt("hours"));
		//Iterar mapa de HorasTrab com campos (Resource, <Data, Horas>)
		
		//System.out.println("LOGGGG: " + rs.getString("resource"));
		return planning;
		
	}
}


