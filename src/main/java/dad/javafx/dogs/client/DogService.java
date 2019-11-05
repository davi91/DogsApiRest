package dad.javafx.dogs.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import dad.javafx.dogs.client.message.BreedsMessage;
import dad.javafx.dogs.client.message.ImageMessage;

public class DogService {

	public DogService() {
		
		// Cargamos nuestro objeto de mapeo -> Para JSON
		Unirest.setObjectMapper(new UnirestObjectMapper());
	}
	
	public List<String> listBreeds() throws DogServiceException {
		
			try {
				// Métodos estáticos -> Fluent API
				BreedsMessage breeds = 
									Unirest
										.get("https://dog.ceo/api/breeds/list") // Solicitamos mediante GET
										.asObject(BreedsMessage.class)
										.getBody();
				
				if( !breeds.isSuccess()) 
					throw new DogServiceException("Error retrieving breeds list");
				
				return breeds.getMessage();
				
			} catch (UnirestException e) {
				throw new DogServiceException(e);
			}
	}
	
	public URL randomImageByBreed(String breed) throws DogServiceException {
		
		try {
			// Métodos estáticos -> Fluent API
			ImageMessage img = 
								Unirest
									.get("https://dog.ceo/api/breed/" + breed + "/images/random") // Solicitamos mediante GET
									.asObject(ImageMessage.class)
									.getBody();
			
			if( !img.isSuccess()) 
				throw new DogServiceException("Error retrieving " + breed + " random image");
			
			return new URL(img.getMessage());
			
		} catch (UnirestException | MalformedURLException e) {
			throw new DogServiceException(e);
		}
	}
	
	public URL randomImage() throws DogServiceException {
		
		try {
			ImageMessage img = 
							 Unirest
							 .get("https://dog.ceo/api/breeds/image/random")
							 .asObject(ImageMessage.class)
							 .getBody();
			
			if( !img.isSuccess() ) {
				throw new DogServiceException("Error al obtener una imagen aleatoria");
			}
			
			return new URL(img.getMessage());
			
		} catch (UnirestException | MalformedURLException e) {
			throw new DogServiceException();
		}
	}
	
	public List<URL> imageByBreeds(String breed) throws DogServiceException {
		
		try {

			BreedsMessage breeds = 
								Unirest
									.get("https://dog.ceo/api/breed/" + breed + "/images") 
									.asObject(BreedsMessage.class)
									.getBody();
			
			if( !breeds.isSuccess()) 
				throw new DogServiceException("Error retrieving breeds list");
			
			
			ArrayList<URL> urlList = new ArrayList<>();
			for( String msg : breeds.getMessage() ) {
				urlList.add(new URL(msg));
			}
			
			return urlList;
			

		} catch (UnirestException | MalformedURLException e) {
			throw new DogServiceException(e);
		}
	}
	
	public List<String> subBreed(String breed) throws DogServiceException {
		
		try {

			BreedsMessage breeds = 
								Unirest
									.get("https://dog.ceo/api/breed/" + breed + "/list") 
									.asObject(BreedsMessage.class)
									.getBody();
			
			if( !breeds.isSuccess()) 
				throw new DogServiceException("Error retrieving breeds list");
			
			return breeds.getMessage();
			
		} catch (UnirestException e ) {
			throw new DogServiceException(e);
		}
	}
	
	public static void main(String[] args) throws DogServiceException {
		
		DogService service = new DogService();
		
		System.out.println(service.listBreeds());
		System.out.println(service.randomImageByBreed("akita"));
		System.out.println(service.randomImage());
		System.out.println(service.imageByBreeds("akita"));
		System.out.println(service.subBreed("hound"));
	}
}
