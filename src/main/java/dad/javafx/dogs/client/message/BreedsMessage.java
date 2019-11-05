package dad.javafx.dogs.client.message;

import java.util.List;

// Donde se va a mapear el JSON
public class BreedsMessage extends Message {

	private List<String> message;
	
	public List<String> getMessage() {
		return message;
	}
	public void setMessage(List<String> message) {
		this.message = message;
	}
	
}
