package dad.javafx.dogs.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DogsApp extends Application {

	private DogsController dogsController;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		dogsController = new DogsController();
		
		Scene scene = new Scene(dogsController.getRootView(), 320, 200);
		
		primaryStage.setTitle("Dogs");
		primaryStage.setScene(scene);
		primaryStage.show();

	}


	public static void main(String[] args) {
		launch(args);

	}

}
