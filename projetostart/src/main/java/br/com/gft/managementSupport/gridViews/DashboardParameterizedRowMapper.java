package br.com.gft.managementSupport.gridViews;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class DashboardParameterizedRowMapper implements ParameterizedRowMapper<DashboardView> {

	public DashboardView mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		DashboardView dashboard = new DashboardView();
		dashboard.setIdConceptLegalEntity(rs.getLong("id_concept_legal_entity")); 		
		dashboard.setConcept(rs.getString("concept"));
		dashboard.setBlpCost(rs.getDouble("blp_cost"));
		dashboard.setBlpCostSimulation(rs.getDouble("blp_cost_simulator"));
		dashboard.setEntity(rs.getString("legal_entity"));
		return dashboard;
		
	}
}
