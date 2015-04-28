package br.com.gft.managementSupport.gridViews;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class ActivitySheetParameterizedRowMapper implements ParameterizedRowMapper<ActivitySheetView> {

	public ActivitySheetView mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		ActivitySheetView activitySheet = new ActivitySheetView();
		
		activitySheet.setHours(rs.getInt("hours"));
		activitySheet.setMonth(rs.getInt("month"));
		activitySheet.setYear(rs.getInt("year"));

		return activitySheet;

	}
}
