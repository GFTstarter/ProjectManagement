package br.com.gft.managementSupport.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.gft.managementSupport.dao.BaselineDao;
import br.com.gft.managementSupport.dao.ConceptByLegalEntityDao;
import br.com.gft.managementSupport.dao.ConceptDao;
import br.com.gft.managementSupport.dao.LegalEntityDao;
import br.com.gft.managementSupport.dao.LocationDao;
import br.com.gft.managementSupport.dao.ProjectDao;
import br.com.gft.managementSupport.dao.ResourceDao;
import br.com.gft.managementSupport.entity.Baseline;
import br.com.gft.managementSupport.entity.Concept;
import br.com.gft.managementSupport.entity.ConceptByLegalEntity;
import br.com.gft.managementSupport.entity.LegalEntity;
import br.com.gft.managementSupport.entity.Location;
import br.com.gft.managementSupport.entity.Project;
import br.com.gft.managementSupport.entity.Resource;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;


@Path("/uploadBaseline")
@Component
public class UploadBaselineResource {
	
	@Autowired
	private BaselineDao baselineDao;
	
	@Autowired
	private ProjectDao projectDao;
	
	@Autowired
	private LocationDao locationDao;
	
	@Autowired
	private ResourceDao resourceDao;
	
	@Autowired
	private ConceptDao conceptDao;
	
	@Autowired
	private LegalEntityDao legalEntityDao;
	
	@Autowired
	private ConceptByLegalEntityDao conceptByLegalEntityDao;
		
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
		
	@POST
	@Path("/readBaseline")
	@Transactional
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public String uploadFile(@FormDataParam("file") InputStream uploadedInputStream,
						   	 @FormDataParam("file") FormDataContentDisposition fileDetail) {
 
		
		String status = readBaselineFile(uploadedInputStream);
		String type[] = status.split("\\|");
		String message = type[1];
		
		if (type[0].equals("0")) {
			return message;
		} else {
			return "{ \"status\": \"" + message + "\"}";
		}
		 
	}
	
	
	public String readBaselineFile(InputStream uploadedInputStream) {  
		BufferedReader br = null;  
		String line = "";  
		String splitBy = ";";		
		
		try {  
	  
			br = new BufferedReader(new InputStreamReader(uploadedInputStream));
			
			int header = 0;
			while ((line = br.readLine()) != null) {  
	  
				String[] column = line.split(splitBy);  
				
				if(header > 0) {
					
					String projectCode = column[0];
					Integer year = Integer.parseInt(column[1]);
					String legalEntityName = column[2];
					String conceptName = column[3];
					Double costRate = Double.parseDouble(column[4]);
					Integer blpHours = Integer.parseInt(column[5]);
					Double blpCost = Double.parseDouble(column[6]);
					Double actualHours = Double.parseDouble(column[7]);
					Double actualCost = Double.parseDouble(column[8]);
					Integer eac = Integer.parseInt(column[9]);
					Double eacCost = Double.parseDouble(column[10]);
					String resourceName = column[11];
					String locationName = column[12];
					
					
					Project objProject = projectDao.findByProjectCode(projectCode);
					LegalEntity objLegalEntity = legalEntityDao.findByLegalEntityName(legalEntityName);
					Concept objConcept = conceptDao.findByConceptName(conceptName);
					Location objLocation = locationDao.findByLocationName(locationName);
					Resource objResource = resourceDao.findByResourceName(resourceName);

					if (objProject == null) {
						return "0|Project not found!";
					} else if (objLegalEntity == null) {
						return "0|Legal Entity not found!";
					} else if (objConcept == null) {
						return "0|Concept not found!";
					} else if (objLocation == null) {
						return "0|Location not found!";
					}					
					
					if (objResource == null) {
						
						Resource objInsertResource = new Resource();
						objInsertResource.setResource(resourceName);
						objInsertResource.setConcept(objConcept);
						objInsertResource.setLocation(objLocation);
						resourceDao.save(objInsertResource);

					} else if (objResource.getConcept().getIdConcept() != objConcept.getIdConcept() ||
							   objResource.getLocation().getIdLocation() != objLocation.getIdLocation()) {								
								return "0|Resource concept or location invalid!";
					} 
					
					
					Baseline objBaseline = new Baseline();					
					List<Baseline> baselineList = new ArrayList<Baseline>();
					List<Location> locationList = new ArrayList<Location>();
					List<Resource> resourceList = new ArrayList<Resource>();
					
					
					objBaseline.setProject(objProject);
					objBaseline.setYear(year);
					objBaseline.setCostRate(costRate);
					objBaseline.setBlpHours(blpHours);				
					objBaseline.setActualHours(actualHours);
					objBaseline.setActualCost(actualCost);
					objBaseline.setEac(eac);
					objBaseline.setEacCost(eacCost);
					

					baselineList.add(objBaseline);
					locationList.add(objLocation);
					resourceList.add(objResource);
					
					objBaseline.setLocations(locationList);
					objBaseline.setResources(resourceList);
					
					baselineDao.save(objBaseline);
					
					
					ConceptByLegalEntity objConceptByLegEnt = conceptByLegalEntityDao.findByConcept(objConcept, objLegalEntity);
					ConceptByLegalEntity objConceptByLegalEntity = new ConceptByLegalEntity();
					if (objConceptByLegEnt != null) {
						
						objConceptByLegalEntity.setIdConceptLegalEntity(objConceptByLegEnt.getIdConceptLegalEntity());						
						
					} 
					
					objConceptByLegalEntity.setBlpCost(blpCost);
					objConceptByLegalEntity.setLegalEntity(objLegalEntity);
					objConceptByLegalEntity.setConcept(objConcept);
					
					conceptByLegalEntityDao.save(objConceptByLegalEntity);
					
				}
				
				header++;
			}  
			
	  
			} catch (FileNotFoundException e) {  
				e.printStackTrace();  
				return "0|Upload failed";
			} catch (IOException e) {  
				e.printStackTrace();
				return "0|Upload failed";
			} catch (Exception e) {
				e.printStackTrace();
				return "0|Upload failed";
			} finally {  
				if (br != null) {  
					try {  
						br.close();  
					} catch (IOException e) {  
						e.printStackTrace();
						return "0|Upload failed";
					}  
				}  
			}  
	  
		logger.info("Baseline file read completed!"); 
		return "1|Upload Successful";
	 } 
		
}