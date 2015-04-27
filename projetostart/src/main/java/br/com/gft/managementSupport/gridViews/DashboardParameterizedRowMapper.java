package br.com.gft.managementSupport.gridViews;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class DashboardParameterizedRowMapper implements ParameterizedRowMapper<DashboardView> {

	public DashboardView mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		DashboardView dashboard = new DashboardView();
		dashboard.setIdBaseline(rs.getLong("id_baseline")); 		
		dashboard.setCostRate(rs.getDouble("cost_rate"));
		dashboard.setBlptotalcost(rs.getDouble("blptotalcost"));
		dashboard.setBlpHours(rs.getInt("blp_hours"));
		dashboard.setEac(rs.getInt("eac"));
		dashboard.setEacCost(rs.getDouble("eac_cost"));
		dashboard.setCostdiff(rs.getDouble("costdiff"));
		return dashboard;

	}
}
