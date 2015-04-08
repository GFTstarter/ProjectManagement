package br.com.gft.managementSupport.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.gft.managementSupport.dao.ActivitySheetDao;
import br.com.gft.managementSupport.dao.ProjectDao;
import br.com.gft.managementSupport.dao.ResourceDao;
import br.com.gft.managementSupport.entity.ActivitySheet;
import br.com.gft.managementSupport.entity.Project;
import br.com.gft.managementSupport.entity.Resource;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;


@Path("/uploadActivity")
@Component
public class UploadActivitySheetResource {
	
	@Autowired
	private ActivitySheetDao activitySheetDao;
	
	@Autowired
	private ProjectDao projectDao;
	
	@Autowired
	private ResourceDao resourceDao;

	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());	
		
	@POST
	@Path("/readActivity")
	@Consumes(MediaType.MULTIPART_FORM_DATA)	
	@Transactional
	public String uploadFile(@FormDataParam("file") InputStream uploadedInputStream,
							 @FormDataParam("file") FormDataContentDisposition fileDetail) {
 
		
		String status = readActivitySheetFile(uploadedInputStream);
		String type[] = status.split("\\|");
		String message = type[1];
		
		// 0| indicates error ; 1| indicates success
		if (type[0].equals("0")) {
			return message;
		} else {
			return "{ \"status\": \"" + message + "\"}";
		}		
		
	}
	
	
	public String readActivitySheetFile(InputStream uploadedInputStream) {  
		BufferedReader br = null;  
		String line = "";  
		String splitBy = ";";		
			  
		try {  
	  
			br = new BufferedReader(new InputStreamReader(uploadedInputStream));
			
			Map<Resource, Map<Date, ActivitySheet>> map = new HashMap<Resource, Map<Date,ActivitySheet>>();
			Project objProject = null;

			int header = 0;			
			while ((line = br.readLine()) != null) {  
	  
				String[] column = line.split(splitBy);  
				
				if(header > 0) {		
					
					String projectCode = column[0];
					String description = column[1];
					String activityType = column[2];
					String task = column[3];
					String date = column[4];
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date Date = sdf.parse(date);
					Integer hours = Integer.parseInt(column[5]);
					String resource = column[6];
					String status = column[7];
					String apprDate = column[8];					
					Date ApprDate = sdf.parse(apprDate);
					String remarks = column[9];
					Integer month = Integer.parseInt(column[10]);
					Integer year = Integer.parseInt(column[11]);
					
					Resource objResource = resourceDao.findByResourceName(resource);
					if (objResource == null) {
						return "0|Resource not found!";
					}
					if (objProject == null) {
						objProject = projectDao.findByProjectCode(projectCode);
						if (objProject == null) {
							return "0|Project not found!";
						}
					} else if (!objProject.getProjectCode().equals(projectCode)) {
						return "0|Project not found!";
					} else {
						//populates Map
						for (ActivitySheet a : objProject.getActivitySheets()) {
							for (Resource r : a.getResources()) {
								Map<Date, ActivitySheet> activityMap = map.get(r); //Busca de valor (MAP) por index (Resource_name)
								if (activityMap == null) { 
									activityMap = new HashMap<Date, ActivitySheet>(); 
									map.put(r, activityMap);
								}
								activityMap.put(a.getDate(), a);
							}
						}
					}
					
					
					
					
				/*if (objProject == null) {
						objProject = projectDao.findByProjectCode(projectCode);
						if (objProject == null) {
							return "0|Project not found!";
						}
						//populates Map
						for (ActivitySheet a : objProject.getActivitySheets()) {
							for (Resource r : a.getResources()) {
								Map<Date, ActivitySheet> activityMap = map.get(r); //Busca de valor (MAP) por index (Resource_name)
								if (activityMap == null) { 
									activityMap = new HashMap<Date, ActivitySheet>(); 
									map.put(r, activityMap);
								}
								activityMap.put(a.getDate(), a);
							}
						}
					} else if (!objProject.getProjectCode().equals(projectCode)) {
						return "0|Project not found!";
					}*/
					
				/*	if (objProject == null) {
						objProject = projectDao.findByProjectCode(projectCode);
					//	objProject = projectDao.findByProjectCode(projectCode);
					//	return "0|Project not found!";
					}
					else{
						//populates Map
						for (ActivitySheet a : objProject.getActivitySheets()) {
							for (Resource r : a.getResources()) {
								Map<Date, ActivitySheet> activityMap = map.get(r); //Busca de valor (MAP) por index (Resource_name)
								if (activityMap == null) { 
									activityMap = new HashMap<Date, ActivitySheet>(); 
									map.put(r, activityMap);
								}
								activityMap.put(a.getDate(), a);
							}
						}
					}*/
					
					if (!objProject.getProjectCode().equals(projectCode)) 
						return "0|Project not found!";
					
					//com.sun.jdi.InvocationException occurred invoking method.
					Map<Date, ActivitySheet> activityMap = map.get(objResource);
					if (activityMap == null) {						//Não ocorre ao repetir um resource			

						activityMap = new HashMap<Date, ActivitySheet>();
						List<ActivitySheet> list = objResource.getActivitySheets(); 
						
						for (ActivitySheet a : list) {					
							activityMap.put(a.getDate(), a);
						}
						map.put(objResource, activityMap);				//inserindo activityMap	nulo	
					}

					ActivitySheet objAct = activityMap.get(Date); //Busca se há registro de atividade na data do registro atual 
					if (objAct == null) {
						
						ActivitySheet objActivitySheet = new ActivitySheet();
						objActivitySheet.setDescription(description);
						objActivitySheet.setActivityType(activityType);
						objActivitySheet.setTask(task);					
						objActivitySheet.setDate(Date);					
						objActivitySheet.setHours(hours);
						objActivitySheet.setStatus(status);					
						objActivitySheet.setApprDate(ApprDate);
						objActivitySheet.setRemarks(remarks);
						objActivitySheet.setMonth(month);
						objActivitySheet.setYear(year);										
						objActivitySheet.setProject(objProject);
						
						List<ActivitySheet> activityList = new ArrayList<ActivitySheet>();
						List<Resource> resourceList = new ArrayList<Resource>();
						
						resourceList.add(objResource);
						
						objActivitySheet.setResources(resourceList);
						
						activityList.add(objActivitySheet);
						
						objResource.setActivitySheets(activityList);
												
						objProject.setActivitySheets(activityList);
												
						activityMap.put(Date, objActivitySheet);
						
						activitySheetDao.save(objActivitySheet);						
						
					} else {
						// update fields
					}					
	
				}
				
				header++;
			}  
						
	  
			} catch (FileNotFoundException e) {  
				e.printStackTrace(); 
				return "0|Upload failed!";
			} catch (IOException e) {  
				e.printStackTrace();
				return "0|Upload failed!";
			} catch (Exception e) {
				e.printStackTrace();
				return "0|Upload failed!";
			} finally {  
				if (br != null) {  
					try {  
						br.close();  
					} catch (IOException e) {  
						e.printStackTrace();
						return "0|Upload failed!";
					}  
				}  
			}  
	  
		logger.info("Activity sheet file read completed!");
		return "1|Upload Successful";
	 } 

}