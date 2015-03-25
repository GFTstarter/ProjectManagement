package br.com.gft.managementSupport.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import br.com.gft.managementSupport.JsonViews;
import br.com.gft.managementSupport.dao.HolidayDao;
import br.com.gft.managementSupport.dao.LocationDao;
import br.com.gft.managementSupport.entity.Holiday;
import br.com.gft.managementSupport.gridViews.CalendarView;
import br.com.gft.managementSupport.rowMapper.CalendarDao;

@Component
@Path("/calendar")
public class CalendarResource {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CalendarDao calendarDao;
	
	@Autowired
	private HolidayDao holidayDao;
	
	@Autowired
	private LocationDao locationDao;
	
	@Autowired
	private ObjectMapper mapper; //Object that can be used for per-serialization configuration of serialization parameters
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String list() throws JsonGenerationException, JsonMappingException, IOException {

		this.logger.info("list()");

		ObjectWriter viewWriter = createViewWriter();
		
		List<CalendarView> allEntries = this.calendarDao.findAll();


		return viewWriter.writeValueAsString(allEntries);
	}
	
	private ObjectWriter createViewWriter() {
		ObjectWriter viewWriter;
		if (this.isAdmin()) {
			viewWriter = this.mapper.writerWithView(JsonViews.Admin.class);
		} else {
			viewWriter = this.mapper.writerWithView(JsonViews.User.class);
		}
		return viewWriter;
	}
	
	private boolean isAdmin() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		if (principal instanceof String && ((String) principal).equals("anonymousUser")) {
			return false;
		}
		UserDetails userDetails = (UserDetails) principal;

		for (GrantedAuthority authority : userDetails.getAuthorities()) {
			if (authority.toString().equals("admin")) {
				return true;
			}
		}
		
		return false;
	}
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Holiday create(Holiday newsEntry) {

		this.logger.info("create(): " + newsEntry);

		return this.holidayDao.save(newsEntry);
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@OnDelete(action=OnDeleteAction.CASCADE)
	public void delete(@FormParam("idHoliday") Long idHoliday) {
		
		this.logger.info("deleteEntry(id)");


		this.holidayDao.delete(idHoliday);
		
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Holiday update(@FormParam("idHoliday") Long id,
					      @FormParam("description") String description,
					      @FormParam("hours") Double hours,
					      @FormParam("date") Date date,
					      @FormParam("weekDay") String weekDay
					      ){
		
		Holiday update = holidayDao.find(id);
		
		update.setIdHoliday(id);
		update.setDescription(description);
		update.setHoursHoliday(hours);
		update.setRefDate(date);
		update.setWeekDay(weekDay);

		this.logger.info("update(): " + update);
		return this.holidayDao.save(update);
				
		
		
	}
	
	
}
