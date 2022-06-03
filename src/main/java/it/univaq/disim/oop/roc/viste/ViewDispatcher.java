package it.univaq.disim.oop.roc.viste;

import java.io.IOException;

import it.univaq.disim.oop.roc.exception.ViewException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ViewDispatcher {
	
	private static ViewDispatcher instance = new ViewDispatcher();
	
	private ViewDispatcher() {
	}
	
	public static ViewDispatcher getInstance() {
		return instance;
	}
	
	private Stage stage;
	public void loginView(Stage stage) throws ViewException{
		this.stage = stage;
		Parent loginView = loadView("login");
		Scene scene = new Scene(loginView);
		stage.setScene(scene);stage.show();
	}
	
	private BorderPane layout;
	
	public void loggedIn() {
		try{
			layout = (BorderPane) loadView("layout");
			Parent home = loadView("home");
			layout.setCenter(home);
			Scene scene = new Scene(layout);
			stage.setScene(scene);
		}
		catch (ViewException e){
			e.printStackTrace();
			renderError(e);
		}
	}
	
	public void logout() {
		try{
			Parent loginView = loadView("login");
			Scene scene = new Scene(loginView);
			stage.setScene(scene);
		} catch (ViewException e) {
				renderError(e);
		}
	}
	
	public void renderView(String viewName) {
		try{
			Parent view = loadView(viewName);
			layout.setCenter(view);
		} catch (ViewException e) {
			renderError(e);
		}
	}
	
	private static final String FXML_SUFFIX = ".fxml";
	private static final String RESOURCE_BASE = "/viste/";
	private void renderError(ViewException e) {
		e.printStackTrace();
		System.exit(1);
	}
	
	private Parent loadView(String view) throws ViewException{
		try{
			FXMLLoader loader = new FXMLLoader(getClass().getResource(RESOURCE_BASE + view+ FXML_SUFFIX));
			
			return loader.load();
		} catch (IOException e) {
			e.printStackTrace();
			throw new ViewException(e);
		} 
	}
}
