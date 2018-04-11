package hei.caulier.projet.view;

import java.io.IOException;
import java.net.URL;

import hei.caulier.projet.MainApp;
import hei.caulier.projet.PDFGenerator_Flexo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class FlexotecnicaController {

  
	private Stage dialogStage;
	Parent root;
	
	@FXML
	private TextField chDateCom;
	@FXML
	private TextField chDepart;
	@FXML
	private TextField chAdresse;
	@FXML
	private TextField chModeLivraison;
	@FXML
	private TextField chClient;
	@FXML
	private TextField chTailleBobine;
	@FXML
	private TextField chDiamMandrin;
	@FXML
	private TextField chDiamExtBobine;
	@FXML
	private TextField chDeveloppement;
	@FXML
	private TextField chObservations;
	@FXML
	private TextField chCodeAchat;
	@FXML
	private TextField chMatiereImpression;
	@FXML
	private TextField chMatiereCollage;
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
	private TextField chMetreTotal;
	@FXML
	private CheckBox chTypeImpr1;
	@FXML
	private CheckBox chTypeImpr2;
	@FXML
	private CheckBox chTypeImpr3;
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
	
	


	/**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     * @throws IOException 
     */
    
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        try {
			root = FXMLLoader.load(getClass().getResource("Flexotecnica.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        this.dialogStage.getIcons().add(new Image("file:resources/images/F-icon.png"));
        
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        
        this.dialogStage.setX(primaryScreenBounds.getMinX());
        this.dialogStage.setY(primaryScreenBounds.getMinY());
        this.dialogStage.setMinWidth(primaryScreenBounds.getWidth());
        this.dialogStage.setMinHeight(primaryScreenBounds.getHeight());
        this.dialogStage.setMaximized(true);
    }
    
    
    @FXML
	private void handleCreatePDF() {
    	System.out.println(chAdresse.getText());
		PDFGenerator_Flexo.createPDF(chAdresse.getText());
	}

 

   
}