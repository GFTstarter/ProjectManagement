package br.com.gft.managementSupport.gridViews;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class CalendarParameterizedRowMapper implements ParameterizedRowMapper<CalendarView> {

	/*
	 * THIS METHOD IS GOING TO BE CALLED FOR EVERY RECORD THAT JDBCTAMPLETE GETS
	 * WHEN IT EXECUTES DE SQL QUERY
	 * 
	 * HIBERNATE ALSO PROVIDES A MAPPER FOR THE SAME PORPUSE
	 * */
	public CalendarView mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		CalendarView calendar = new CalendarView();
		calendar.setIdHoliday(rs.getLong("id_holiday"));
		calendar.setIdLocation(rs.getLong("id_location"));
		calendar.setDescription(rs.getString("description"));
		calendar.setHours(rs.getDouble("hours_holiday"));
		calendar.setDate(rs.getDate("ref_date"));
		calendar.setWeekDay(rs.getString("week_day"));
		calendar.setLocation(rs.getString("location"));

		return calendar;

	}

}
