package br.com.gft.managementSupport.gridViews;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class ProfileParameterizedRowMapper implements ParameterizedRowMapper<ProfileView> {

	public ProfileView mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		ProfileView profile = new ProfileView();
		profile.setIdConceptLegalEntity(rs.getLong("id_concept_legal_entity")); 		
		profile.setConcept(rs.getString("concept"));
		profile.setBlpCost(rs.getDouble("blp_cost"));
		profile.setBlpCostSimulation(rs.getDouble("blp_cost_simulator"));
		profile.setEntity(rs.getString("legal_entity"));
		return profile;
		
	}
}
