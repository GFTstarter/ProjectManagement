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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import br.com.gft.managementSupport.JsonViews;
import br.com.gft.managementSupport.dao.ExpensesDao;
import br.com.gft.managementSupport.entity.Expenses;
import br.com.gft.managementSupport.entity.Resource;

@Component
@Path("/expenses")
public class ExpensesResource {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ExpensesDao expensesDao;
	
	@Autowired
	private ObjectMapper mapper;


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String list() throws JsonGenerationException, JsonMappingException, IOException {

		this.logger.info("list()");

		ObjectWriter viewWriter = createViewWriter();
		
		List<Resource> allEntries = this.expensesDao.findAll();

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
	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Expenses read(@PathParam("id") Long id) {

		this.logger.info("getEntry(id)");

		Expenses newsEntry = this.expensesDao.find(id);
		if (newsEntry == null) {
			throw new WebApplicationException(404);
		}
		return newsEntry;
	}


	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Expenses create(Expenses newsEntry) {

		this.logger.info("create(): " + newsEntry);

		return this.expensesDao.save(newsEntry);
	}


	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Expenses update(@FormParam("idExpenses") Long id,
						  @FormParam("endDate") Date endExpensesDate
						  ) {
		
		Expenses objExpenses = expensesDao.find(id);
		
		
		Expenses newsEntry = new Expenses();
		newsEntry.setIdExpenses(id);
		//newsEntry.setResource(objExpenses.getResource());
		newsEntry.setCostRate(objExpenses.getCostRate());
		newsEntry.setIdLegalEntity(objExpenses.getIdLegalEntity());
		newsEntry.setBeginExpensesDate(objExpenses.getBeginExpensesDate());
		newsEntry.setEndExpensesDate(endExpensesDate);
		newsEntry.setDescription(objExpenses.getDescription());
		
		this.logger.info("update(): " + newsEntry);

		return this.expensesDao.save(newsEntry);
	}


	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@FormParam("idExpenses") Long id) {

		this.logger.info("deleteEntry(id)");

		this.expensesDao.delete(id);
	}
	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/byUser/{id}")
	public String listReadExpensesByUser(@PathParam("id") int id) throws JsonGenerationException, JsonMappingException, IOException {
		
		this.logger.info("list(id)");
		
		ObjectWriter viewWriter = createViewWriter();
		
		List<Resource> allEntries = this.expensesDao.findByUserId(id);
		
		return viewWriter.writeValueAsString(allEntries);
		
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
	
}
