package it.univaq.disim.oop.roc.controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class HomeController {
	
	@FXML
	private Text testoBenvenuto;
	
	public void initialize() {
		testoBenvenuto.setText("Benvenuto docente!");
	}
}
