package br.com.gft.managementSupport.gridViews;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class ProjectParameterizedRow implements ParameterizedRowMapper<ProjectView>{

	@Override
	public ProjectView mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProjectView project = new ProjectView();
		
		project.setIdProject(rs.getLong("id_project"));
		project.setProjectCode(rs.getString("project_code"));
		project.setName(rs.getString("name"));
		project.setStartDate(rs.getDate("start_date"));
		project.setEndDate(rs.getDate("end_date"));
		
		return project;
	}

}
