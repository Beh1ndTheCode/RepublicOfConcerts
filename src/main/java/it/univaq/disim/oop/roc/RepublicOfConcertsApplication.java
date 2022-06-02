package it.univaq.disim.oop.roc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RepublicOfConcertsApplication extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/viste/login.fxml"));
		Parent login = loader.load();
		Scene scene= new Scene(login);
		stage.setScene(scene);
		stage.show();
		
	}

	
}