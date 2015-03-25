package br.com.gft.managementSupport.serializer;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import br.com.gft.managementSupport.entity.Project;

public class ProjectSerializer extends JsonSerializer<Project> {

	@Override
	public void serialize(Project value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		jgen.writeStartObject();
        jgen.writeNumberField("idUser", value.getIdUser());
        jgen.writeNumberField("idProject", value.getIdProject());
        jgen.writeStringField("projectCode", value.getProjectCode());
        jgen.writeStringField("name", value.getName());
        jgen.writeObjectField("startDate", value.getStartDate());
        jgen.writeObjectField("endDate", value.getEndDate());
        
        jgen.writeObjectField("endDate", value.getEndDate());
        try {
            jgen.writeObjectField("activitySheets", value.getActivitySheets());
        } catch (IOException e) {
            jgen.writeEndArray();
        }
        jgen.writeEndObject();
	}

}