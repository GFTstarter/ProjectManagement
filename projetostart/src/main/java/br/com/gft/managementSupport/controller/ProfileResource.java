package br.com.gft.managementSupport.controller;

import java.io.IOException;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import br.com.gft.managementSupport.JsonViews;
import br.com.gft.managementSupport.dao.ConceptByLegalEntityDao;
import br.com.gft.managementSupport.dao.ConceptDao;
import br.com.gft.managementSupport.entity.ConceptByLegalEntity;
import br.com.gft.managementSupport.gridViews.ProfileView;
import br.com.gft.managementSupport.rowMapper.ProfileDao;

@Component
@Path("/profile")
public class ProfileResource {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProfileDao profileDao;
	
	@Autowired
	private ConceptByLegalEntityDao conceptByLegalEntityDao;
	
	@Autowired
	private ConceptDao conceptDao;
	
	@Autowired
	private ObjectMapper mapper;


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String list() throws JsonGenerationException, JsonMappingException, IOException {

		this.logger.info("list()");

		ObjectWriter viewWriter = createViewWriter();
		
		List<ProfileView> allEntries = this.profileDao.findAll();


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
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ConceptByLegalEntity create(ConceptByLegalEntity newsEntry) {

		this.logger.info("create(): " + newsEntry);

		return this.conceptByLegalEntityDao.save(newsEntry);
	}


	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ConceptByLegalEntity update(@FormParam("idConceptLegalEntity") Long idConceptLegalEntity,
							  		   @FormParam("blpCostSimulator") Double simulator
							 ){
		
		ConceptByLegalEntity objCon = conceptByLegalEntityDao.find(idConceptLegalEntity);
		
		ConceptByLegalEntity newsEntry = new ConceptByLegalEntity();
		
		newsEntry.setIdConceptLegalEntity(idConceptLegalEntity);
		newsEntry.setBlpCost(objCon.getBlpCost()); 
		newsEntry.setBlpCostSimulator(simulator);
		
		this.logger.info("update(): " + newsEntry);
		return this.conceptByLegalEntityDao.save(newsEntry);
		
	}


	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@FormParam("idConceptLegalEntity") Long idConceptLegalEntity) {
		
		this.logger.info("deleteEntry(id)");

		this.profileDao.delete(idConceptLegalEntity);
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
