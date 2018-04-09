package hei.caulier.projet.view;

import java.io.IOException;

import hei.caulier.projet.PDFGenerator_Flexo;
import hei.caulier.projet.PDFGenerator_Omet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.print.PrinterJob;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class OmetController {


	private Stage dialogStage;
	Parent root;


	@FXML
	private ComboBox<String> combo;
	ObservableList<String> listCombo = FXCollections.observableArrayList();


	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 * @throws IOException 
	 */

	@FXML
	private void initialize() {
		listCombo.add("ETIQUETTES");
		listCombo.add("POLYBANDE");
		listCombo.add("BOLDUC");
		combo.setItems(listCombo);
	}

	/**
	 * Sets the stage of this dialog.
	 *
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
		try {
			root = FXMLLoader.load(getClass().getResource("Omet.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.dialogStage.getIcons().add(new Image("file:resources/images/O-icon.png"));

		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

		this.dialogStage.setX(primaryScreenBounds.getMinX());
		this.dialogStage.setY(primaryScreenBounds.getMinY());
		this.dialogStage.setMinWidth(primaryScreenBounds.getWidth());
		this.dialogStage.setMinHeight(primaryScreenBounds.getHeight());
		this.dialogStage.setMaximized(true);
	}

	@FXML
	private void handleCreatePDF() {
		PDFGenerator_Omet.createPDF();
	}





}