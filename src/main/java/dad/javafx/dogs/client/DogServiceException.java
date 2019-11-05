package dad.javafx.dogs.client;

// Creamos nuestra propia excepción para no depender de Unirest a la hora de realizar la App.
@SuppressWarnings("serial")
public class DogServiceException extends Exception {

	public DogServiceException() {
		// TODO Auto-generated constructor stub
	}

	public DogServiceException(String message) {
		super(message);
	}

	public DogServiceException(Throwable cause) {
		super(cause);
	}

	public DogServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public DogServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
