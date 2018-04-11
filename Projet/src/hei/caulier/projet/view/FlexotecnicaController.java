package hei.caulier.projet.view;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;

import org.omg.CORBA.PRIVATE_MEMBER;

import com.itextpdf.text.pdf.GrayColor;

import hei.caulier.projet.ConvertStringToDate;
import hei.caulier.projet.MainApp;
import hei.caulier.projet.PDFGenerator_Flexo;
import hei.caulier.projet.daos.AdresseDao;
import hei.caulier.projet.daos.CommandeDao;
import hei.caulier.projet.daos.LigneCommandeDao;
import hei.caulier.projet.entities.Commande;
import hei.caulier.projet.entities.LigneCommande;
import hei.caulier.projet.entities.Adresse;
import hei.caulier.services.AdresseService;
import hei.caulier.services.ArticleService;
import hei.caulier.services.CommandeService;
import hei.caulier.services.MachineService;
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
import jdk.management.resource.internal.inst.FileChannelImplRMHooks;


public class FlexotecnicaController {

	private CommandeDao commandeDao = new CommandeDao();
	private LigneCommandeDao ligneCommandeDao = new LigneCommandeDao();
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
	private TextField chCodeArticle2;
	@FXML
	private TextField chLargeur;
	@FXML
	private TextField chLargeur2;
	@FXML
	private TextField chNbCouleurs;
	@FXML
	private TextField chNbCouleurs2;
	@FXML
	private TextField chModele;
	@FXML
	private TextField chModele2;
	@FXML
	private TextField chRefPantones;
	@FXML
	private TextField chRefPantones2;
	@FXML
	private TextField chNbBobines;
	@FXML
	private TextField chNbBobines2;
	@FXML
	private TextField chMetreTotal;
	@FXML
	private TextField chMetreTotal2;
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
	@FXML
	private CheckBox chDecoupe1;
	@FXML
	private CheckBox chDecoupe2;
	


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
		SaveToDbFlexo();
	}

	private void SaveToDbFlexo() {
		Integer adresseId = AdresseService.getInstance().getAdresseIdFromString(chAdresse.getText());
		LocalDate dateCommande = ConvertStringToDate.StringToDate(chDateCom.getText());
		Integer articleId = ArticleService.getInstance().getArticleIdFromString(chCodeArticle.getText());
		Integer articleId2 = ArticleService.getInstance().getArticleIdFromString(chCodeArticle2.getText());
		String typeImpression = null;
		Integer sensImpressionRecto = null;
		Integer sensImpressionVerso = null;
		String decoupe = null;
		if (chTypeImpr1.isSelected()) { typeImpression = "FILMSAB"; }
		if (chTypeImpr2.isSelected()) { typeImpression = "POLYFILM NON COMPLEXE"; }
		if (chTypeImpr3.isSelected()) { typeImpression = "POLYFILM COMPLEXE"; }
		if (chSensImpr1.isSelected()) { sensImpressionRecto = 1; }
		if (chSensImpr2.isSelected()) { sensImpressionRecto = 2; }
		if (chSensImpr3.isSelected()) { sensImpressionRecto = 3; }
		if (chSensImpr4.isSelected()) { sensImpressionRecto = 4; }
		if (chSensImpr5.isSelected()) { sensImpressionVerso = 5; }
		if (chSensImpr6.isSelected()) { sensImpressionVerso = 6; }
		if (chSensImpr7.isSelected()) { sensImpressionVerso = 7; }
		if (chSensImpr8.isSelected()) { sensImpressionVerso = 8; }
		if (chDecoupe1.isSelected()) { decoupe = "Direct"; }
		if (chDecoupe2.isSelected()) { decoupe = "A part"; }
		Commande newCommande = new Commande(null, AdresseService.getInstance().getAdresse(adresseId), 
				MachineService.getInstance().getMachine(3), dateCommande, chDepart.getText() , Integer.valueOf(chCodeAchat.getText()), chModeLivraison.getText(), 
				typeImpression, sensImpressionRecto, sensImpressionVerso, Float.valueOf(chTailleBobine.getText()), 
				Float.valueOf(chDiamMandrin.getText()), Float.valueOf(chDiamExtBobine.getText()), chDeveloppement.getText(), null, null, null, chObservations.getText(), 
				null, null, null, chMatiereImpression.getText(), chMatiereCollage.getText(), decoupe, null);
		commandeDao.addCommande(newCommande);
		
		LigneCommande newLigneCommande = new LigneCommande(null, newCommande, ArticleService.getInstance().getArticle(articleId), Float.valueOf(chLargeur.getText()),
				Integer.valueOf(chNbCouleurs.getText()), chModele.getText(), chRefPantones.getText(), Integer.valueOf(chNbBobines.getText()), Float.valueOf(chMetreTotal.getText()), 
				null, null, null, null, null, null, null, null, null, null, null, null, null);
		ligneCommandeDao.addLigneCommande(newLigneCommande);
		 if ((chCodeArticle2.getText() != null && !chCodeArticle2.getText().isEmpty())) {
			 LigneCommande newLigneCommande2 = new LigneCommande(null, newCommande, ArticleService.getInstance().getArticle(articleId2), Float.valueOf(chLargeur2.getText()),
				Integer.valueOf(chNbCouleurs2.getText()), chModele2.getText(), chRefPantones2.getText(), Integer.valueOf(chNbBobines2.getText()), Float.valueOf(chMetreTotal2.getText()), 
				null, null, null, null, null, null, null, null, null, null, null, null, null);
			 ligneCommandeDao.addLigneCommande(newLigneCommande2);
		 }
	}

	  

   
 

   
}