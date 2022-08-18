package it.univaq.disim.oop.roc.viste;

import java.io.IOException;

import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.domain.Utente;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ViewDispatcher {

	private static final String VISTE_BASE = "/viste/";
	private static final String FINESTRE_BASE = "/finestre/";
	private static final String FXML_SUFFIX = ".fxml";

	private static ViewDispatcher instance = new ViewDispatcher();

	private Stage stage,window;
	private BorderPane layout;

	private ViewDispatcher() {
	}

	public void loginView(Stage stage) throws ViewException {
		this.stage = stage;
		Parent loginView = loadView("login").getView();
		Scene scene = new Scene(loginView);
		stage.setScene(scene);
		stage.show();
	}

	public void loginView() throws ViewException {
		Parent loginView = loadView("login").getView();
		Scene scene = new Scene(loginView);
		stage.setScene(scene);
	}

	public void loggedIn(Utente utente) {
		try {
			View<Utente> layoutView = loadView("layout");
			DataInitializable<Utente> layoutController = layoutView.getController();
			layoutController.initializeData(utente);
			layout = (BorderPane) layoutView.getView();
			renderHome(false,utente);
			Scene scene = new Scene(layout);
			stage.setScene(scene);
		} catch (ViewException e) {
			renderError(e);
		}
	}

	public void logout() {
		try {
			Parent loginView = loadView("login").getView();
			Scene scene = new Scene(loginView);
			stage.setScene(scene);
		} catch (ViewException e) {
			renderError(e);
		}
	}

	public <T> void renderView(String viewName, T data) {
		try {
			View<T> view = loadView(viewName);
			DataInitializable<T> controller = view.getController();
			controller.initializeData(data);
			layout.setCenter(view.getView());
		} catch (ViewException e) {
			renderError(e);
		}
	}

	public void renderError(Exception e) {
		e.printStackTrace();
		System.exit(1);
	}

	public static ViewDispatcher getInstance() {
		return instance;
	}

	private <T> View<T> loadView(String viewName) throws ViewException {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(VISTE_BASE + viewName + FXML_SUFFIX));
			Parent parent = (Parent) loader.load();
			return new View<>(parent, loader.getController());

		} catch (IOException ex) {
			throw new ViewException(ex);
		}
	}

	public void signupView() throws ViewException {
		Parent SignupView = loadView("signup").getView();
		Scene scene = new Scene(SignupView);
		stage.setScene(scene);
	}

	public void signedUp(Utente utente) {
		try {
			View<Utente> layoutView = loadView("layout");
			DataInitializable<Utente> layoutController = layoutView.getController();
			layoutController.initializeData(utente);
			layout = (BorderPane) layoutView.getView();
			renderHome(true,utente);
			Scene scene = new Scene(layout);
			stage.setScene(scene);
		} catch (ViewException e) {
			renderError(e);
		}
	}
	
	public <T> void renderHome(boolean registrazione, T data) {
		try {
			View<T> view = loadView("home");
			DataInitializable<T> controller = view.getController();
			controller.initializeData(data);
			controller.initializeBool(registrazione);
			layout.setCenter(view.getView());
		} catch (ViewException e) {
			renderError(e);
		}
	}
	
	public void windowView(String windowName) throws ViewException {
		window = new Stage();
		Parent windowView = loadWindow(windowName).getView();
		Scene scene = new Scene(windowView);
		window.setScene(scene);
		window.show();
	}
	
	private <T> View<T> loadWindow(String windowName) throws ViewException {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(FINESTRE_BASE + windowName + FXML_SUFFIX));
			Parent parent = (Parent) loader.load();
			return new View<>(parent, loader.getController());
		} catch (IOException ex) {
			throw new ViewException(ex);
		}
	}
}