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

import br.com.gft.managementSupport.dao.HolidayDao;
import br.com.gft.managementSupport.dao.LocationDao;
import br.com.gft.managementSupport.entity.Holiday;
import br.com.gft.managementSupport.entity.Location;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;


@Path("/uploadHoliday")
@Component
public class UploadHolidayResource {
	
	@Autowired
	private HolidayDao holidayDao;
	
	@Autowired
	private LocationDao locationDao;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());	
			
	@POST
	@Path("/readHoliday")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Transactional
	public String uploadFile (@FormDataParam("file") InputStream uploadedInputStream,
							  @FormDataParam("file") FormDataContentDisposition fileDetail) {
 
		String status = readHolidayFile(uploadedInputStream);
		String type[] = status.split("\\|");
		String message = type[1];
		
		if (type[0].equals("0")) {
			return message;
		} else {
			return "{ \"status\": \"" + message + "\"}";
		}		
		
	}
	
	
	public String readHolidayFile(InputStream uploadedInputStream) { 
		
		BufferedReader br = null;  
		String line = "";  
		String splitBy = ";";		
				
		try {  
	  
			br = new BufferedReader(new InputStreamReader(uploadedInputStream));

			int header = 0;
			while ((line = br.readLine()) != null) { 
	  
				String[] column = line.split(splitBy);
				
				Map<String, Location> locationMap = new HashMap<String, Location>();
				Map<String,Map<Date,Holiday>> map = new HashMap<String, Map<Date,Holiday>>(); 
								
				if (header > 0) {		
				
					String locationName = column[0];
					String description = column[1];
					String date = column[3];
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date refDate = sdf.parse(date);
					Double hours = Double.parseDouble(column[4].replace(":", "."));
					String weekDay = column[5];
					
					Location objLocation = locationMap.get(locationName);
					if (objLocation == null) {
						objLocation = locationDao.findByLocationName(locationName);
						
						if (objLocation == null) {
							return "0|Location not found!";
						}
						
						locationMap.put(locationName, objLocation);
					}
					
					Map<Date,Holiday> dateMap = map.get(locationName);
					if (dateMap == null) {
						dateMap = new HashMap<Date, Holiday>();
						List<Holiday> list = objLocation.getHolidays();
						
						for (Holiday h : list) {
							dateMap.put(h.getRefDate(), h);
						}
						
						map.put(locationName, dateMap);
					}
										
					
					Holiday objHol = dateMap.get(refDate);					
					if (objHol == null) {
					
						Holiday objHoliday = new Holiday();
						List<Holiday> holidayList = new ArrayList<Holiday>();
						
						objHoliday.setDescription(description);
						objHoliday.setRefDate(refDate);									
						objHoliday.setHoursHoliday(hours);						
						objHoliday.setWeekDay(weekDay);
						
						
						holidayList = objLocation.getHolidays();
						if (holidayList == null) {
							holidayList = new ArrayList<Holiday>();
							objLocation.setHolidays(holidayList);
						}
						
						holidayList.add(objHoliday);
						dateMap.put(refDate, objHoliday);
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
	  
		logger.info("Holiday file read completed!");
		return "1|Upload Successful";
	 } 
	 
}