package br.com.gft.managementSupport.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import br.com.gft.managementSupport.dao.ActivitySheetDao;
import br.com.gft.managementSupport.dao.BaselineByResourceDao;
import br.com.gft.managementSupport.entity.ActivitySheet;
import br.com.gft.managementSupport.entity.BaselineByResource;
import br.com.gft.managementSupport.entity.Resource;
import br.com.gft.managementSupport.gridViews.PlanningView;
import br.com.gft.managementSupport.rowMapper.PlanningDao;

@Component
@Path("/planning")
public class PlanningResource {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PlanningDao planningDao;
	
	@Autowired
	private ActivitySheetDao activitySheetDao;
	
	@Autowired
	private ObjectMapper mapper;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String list() throws JsonGenerationException, JsonMappingException, IOException {

		this.logger.info("list()");
		
		int cont = 0;

		Map<Integer, ActivitySheet> objTestAct = new HashMap<Integer, ActivitySheet>();
		Map<Resource, Map<Integer, ActivitySheet>> objTestHR = new HashMap<Resource, Map<Integer, ActivitySheet>>();
		Map<Resource, Map<Integer, ActivitySheet>> objTestHorasR = new HashMap<Resource, Map<Integer, ActivitySheet>>();
		
		ObjectWriter viewWriter = createViewWriter();
		
		List<PlanningView> allEntries = this.planningDao.findAll();
		System.out.println(allEntries);
		
		for(PlanningView pv : allEntries){
			objTestHR.put(new Resource(pv.getResource().toString()), null);
		}
		
		for(Entry<Resource, Map<Integer, ActivitySheet>> entry : objTestHR.entrySet()){
			objTestAct = new HashMap<Integer, ActivitySheet>();
			for (PlanningView pv : allEntries ){
				
				if (pv.getResource().equals(entry.getKey().getResource())){
					objTestAct.put(cont, new ActivitySheet(pv.getIdActivity(), pv.getDate(), pv.getHours()));
				}
				cont++;
			}
			objTestHorasR.put(new Resource (entry.getKey().getResource()), objTestAct); //Contém um Map com os registros de atividades agrupados pela chave (objeto Resource)
		}																				//Kendo não acessa dados desse tipo de resposta
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

//	@POST
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public BaselineByResource create(BaselineByResource newsEntry) {
//
//		this.logger.info("create(): " + newsEntry);
//
//		return this.baselineByResourceDao.save(newsEntry);
//	}
//
//
//	@PUT
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public BaselineByResource update(@FormParam("idBaselineResource") Long id,
//			  					@FormParam("dtBeginOnProject") Date beginOnProjDate,
//			  					@FormParam("dtEndOnProject") Date endOnProjDate) {
//
//		BaselineByResource update = baselineByResourceDao.find(id);
//		
//		update.setIdBaselineResource(id);
//		update.setBeginOnProjectDate(beginOnProjDate);
//		update.setEndOnProjectDate(endOnProjDate);
//		
//		
//		this.logger.info("update(): " + id);
//
//		return this.baselineByResourceDao.save(update);
//	}
//
//
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@FormParam("idActivitySheet") Long idActivitySheet) {

		this.logger.info("deleteEntry(id)");

		this.activitySheetDao.delete(idActivitySheet);
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
