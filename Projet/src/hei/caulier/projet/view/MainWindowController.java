package hei.caulier.projet.view;

import hei.caulier.projet.MainApp;
import javafx.fxml.FXML;



public class MainWindowController {


	// Reference to the main application.
	private MainApp mainApp;

	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	public MainWindowController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {        
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	/**
	 * Called when the user clicks the flexotecnica button
	 */
	@FXML
	private void handleFlexo() {
		mainApp.showFlexotecnica();
		
	}



}