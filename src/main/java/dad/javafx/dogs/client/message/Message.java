package dad.javafx.dogs.client.message;

// En la API Rest de perros, esto lo tenemos en común, lo que cambia el mensaje
public class Message {

	private String status;
	private Integer code; // Hay que añadir TODO lo que te devuelve el JSON, aunque no lo usemos.
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	
	public boolean isSuccess() {
		return getStatus().equals("success");
	}

}
