package it.univaq.disim.oop.roc;

import it.univaq.disim.oop.roc.exception.ViewException;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import javafx.application.Application;
import javafx.stage.Stage;

public class RepublicOfConcertsApplication extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		try{
			ViewDispatcher viewDispatcher = ViewDispatcher.getInstance();
			viewDispatcher.loginView(stage);
		} 
		catch (ViewException e) {
			e.printStackTrace();
		}
		
	}

	
}