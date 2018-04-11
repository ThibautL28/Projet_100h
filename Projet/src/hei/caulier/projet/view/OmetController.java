package hei.caulier.projet.view;

import java.awt.Checkbox;
import java.io.IOException;

import hei.caulier.projet.PDFGenerator_Flexo;
import hei.caulier.projet.PDFGenerator_Omet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.print.PrinterJob;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class OmetController {


	private Stage dialogStage;
	Parent root;

	@FXML
	private TextField chDateCom;
	@FXML
	private TextField chClient;
	@FXML
	private TextField chDepart;
	@FXML
	private TextField chAdresse;
	@FXML
	private TextField chModeLivraison;
	@FXML
	private TextField chCodeAchat;
	@FXML
	private TextField chNbEtiquettes;
	@FXML
	private TextField chDiamMandrin;
	@FXML
	private TextField chDiamExtBobine;
	@FXML
	private TextField chDeveloppement;
	@FXML
	private TextField chCliche;
	@FXML
	private TextField chObservations;
	@FXML
	private TextField chRectoMatiere;
	@FXML
	private TextField chVersoMatiere;
	@FXML
	private TextField chCodeArticle;
	@FXML
	private TextField chLargeur;
	@FXML
	private TextField chNbCouleurs;
	@FXML
	private TextField chModele;
	@FXML
	private TextField chRefPantones;
	@FXML
	private TextField chNbBobines;
	@FXML
	private TextField chE;
	@FXML
	private CheckBox chSensImpr1;
	@FXML
	private CheckBox chSensImpr2;
	@FXML
	private CheckBox chSensImpr3;
	@FXML
	private CheckBox chSensImpr4;
	@FXML
	private CheckBox chSensImpr5;
	@FXML
	private CheckBox chSensImpr6;
	@FXML
	private CheckBox chSensImpr7;
	@FXML
	private CheckBox chSensImpr8;
	@FXML
	private CheckBox chRecto;
	@FXML
	private CheckBox chVerso;
	@FXML
	private CheckBox chRectoVerso;
	@FXML
	private CheckBox chMatiere1;
	@FXML
	private CheckBox chMatiere2;
	@FXML
	private CheckBox chMatiere3;
	@FXML
	private CheckBox chMatiere4;
	@FXML
	private CheckBox chMatiere5;
	
	
	
	

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