package br.com.gft.managementSupport.gridViews;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class PeopleParameterizedRowMapper implements ParameterizedRowMapper<PeopleView>{

	public PeopleView mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		PeopleView people = new PeopleView();
		people.setIdResource(rs.getLong("id_resource"));
		people.setResource(rs.getString("resource"));
		people.setConcept(rs.getString("concept"));
		people.setEntity(rs.getString("legal_entity"));
		people.setLocation(rs.getString("location"));
		return people;
		
	}
}
