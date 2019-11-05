package dad.javafx.dogs.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.dogs.client.DogService;
import dad.javafx.dogs.client.DogServiceException;
import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class DogsController implements Initializable {

	DogService service;
	
	// FXML : View
	//--------------------------------------------------------
	
	@FXML
	private VBox view;
	
	@FXML
	private Button refreshBt;
	
	@FXML
	private ComboBox<String> breedCombo;
	
	@FXML
	private ImageView dogImg;
	
	//--------------------------------------------------------
	
	// Model
	
	private ListProperty<String> dogList = new SimpleListProperty<>();
	
	private ObjectProperty<Image> dogImage = new SimpleObjectProperty<>(); 
	
	private StringProperty dogBreed = new SimpleStringProperty();
	
	
	public DogsController() throws IOException {
		
		FXMLLoader loader = new FXMLLoader( getClass().getResource("/fxml/DogsUI.fxml"));
		loader.setController(this);
		loader.load();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// Main Setup
		// --Iniciamos el procedimiento para conectar con la API Rest
		service = new DogService();
		
		// La lista de las diferentes razas
		try {
			
			dogList.set(FXCollections.observableArrayList(service.listBreeds()));
			
		} catch (DogServiceException e) {
			
			sendConnectionError("No se pudo cargar la lista de perros");
		}
		
		breedCombo.itemsProperty().bind(dogList);
		
		// Bindings
		dogBreed.bind( breedCombo.getSelectionModel().selectedItemProperty() );
		dogImg.imageProperty().bind(dogImage);
		
		// Ponemos una imagen por defecto
		dogImage.set( new Image(getClass().getResource("/images/dog_64px.png").toString()));
		
		// Events
		dogBreed.addListener( (o, ov, nv) -> onBreedChanged(nv));
		refreshBt.setOnAction( evt -> onRefreshImage() );
	}
	
	private void onBreedChanged(String dogBreed) {
		
		try {
			
			if( dogBreed != null )
				dogImage.set( new Image(service.randomImageByBreed(dogBreed).toString()) );
			
		} catch (DogServiceException e) {

			sendConnectionError("No se pudo cargar la imagen del perro " + dogBreed);
		}
	}

	private void sendConnectionError(String msg) {
		
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Perros");
		alert.setHeaderText("Error en la conexión");
		alert.setContentText(msg);
		
		alert.showAndWait();
		Platform.exit();
	}
	
	private void onRefreshImage() {
		onBreedChanged(dogBreed.get());
	}

	public VBox getRootView() {
		return view;
	}
	
}
