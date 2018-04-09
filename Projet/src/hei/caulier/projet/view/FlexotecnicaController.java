package hei.caulier.projet.view;

import java.io.IOException;
import java.net.URL;

import hei.caulier.projet.MainApp;
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
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class FlexotecnicaController {

  
	private Stage dialogStage;
	Parent root;
	
	
   

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
    
    public void handlePrintSave() {
		System.out.println("To Printer!");
		PrinterJob job = PrinterJob.createPrinterJob();
				
		if(job != null){
			job.showPrintDialog(dialogStage); 
			job.printPage(root);
			job.endJob();
		}
	}

   
 

   
}