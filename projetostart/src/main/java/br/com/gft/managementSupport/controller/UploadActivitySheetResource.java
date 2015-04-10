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
			
			Project objProject = null;

			int header = 0;			
			while ((line = br.readLine()) != null) {  
	  
				String[] column = line.split(splitBy);  
				
				Map<String, Resource> resourceMap = new HashMap<String, Resource>();
				Map<String, Map<Date, ActivitySheet>> map = new HashMap<String, Map<Date,ActivitySheet>>();
				
				
				
				if(header > 0){		
					
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
					
					Resource objResource = resourceMap.get(resource);
					if (objResource == null) {
						objResource = resourceDao.findByResourceName(resource);
						if (objResource == null) {
							return "0|Resource not found!";
						}
						resourceMap.put(resource, objResource);
					}
					if (objProject == null) {
						objProject = projectDao.findByProjectCode(projectCode);
						if (objProject == null) {
							return "0|Project not found!";
						}
					} 
					if (!objProject.getProjectCode().equals(projectCode)) {
						return "0|Project not found!";
					} 
					
					Map<Date, ActivitySheet> dateMap = map.get(resource);
					if (dateMap == null){
						dateMap = new HashMap<Date, ActivitySheet>();
						List<ActivitySheet> list = objResource.getActivitySheets();
						
						for (ActivitySheet a : list){
							dateMap.put(a.getDate(), a);
						}
						
						map.put(resource, dateMap);
					}
					
					
					ActivitySheet objAct = dateMap.get(Date);
					if(objAct == null){
						
						ActivitySheet objActivity = new ActivitySheet();
						List<ActivitySheet> activityList = new ArrayList<ActivitySheet>();
						
						objActivity.setDescription(description);
						objActivity.setActivityType(activityType);
						objActivity.setTask(task);					
						objActivity.setDate(Date);					
						objActivity.setHours(hours);
						objActivity.setStatus(status);					
						objActivity.setApprDate(ApprDate);
						objActivity.setRemarks(remarks);
						objActivity.setMonth(month);
						objActivity.setYear(year);										
						objActivity.setProject(objProject);
						
						
						activityList = objResource.getActivitySheets();
						if(activityList == null){
							activityList = new ArrayList<ActivitySheet>();
							objResource.setActivitySheets(activityList);
						}
						
						activityList.add(objActivity);
						dateMap.put(Date, objActivity);
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