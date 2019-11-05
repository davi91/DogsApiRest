package dad.javafx.dogs.client;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UnirestObjectMapper implements com.mashape.unirest.http.ObjectMapper {

	// Para hacer las conversiones, pero para eso tenemos Jackson, que nos lo convierte en objetos, o podemos usar otras librerias como GSon
	private ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public <T> T readValue(String value, Class<T> valueType) {
		
		try {
			return mapper.readValue(value, valueType);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public String writeValue(Object value) {	
		
		try {
			return mapper.writeValueAsString(value);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	
}
