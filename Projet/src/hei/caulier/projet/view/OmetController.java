package hei.caulier.projet.view;

import java.awt.Checkbox;
import java.io.IOException;
import java.time.LocalDate;

import hei.caulier.projet.ConvertStringToDate;
import hei.caulier.projet.PDFGenerator_Flexo;
import hei.caulier.projet.PDFGenerator_Omet;
import hei.caulier.projet.daos.CommandeDao;
import hei.caulier.projet.daos.LigneCommandeDao;
import hei.caulier.projet.entities.Article;
import hei.caulier.projet.entities.Commande;
import hei.caulier.projet.entities.LigneCommande;
import hei.caulier.services.AdresseService;
import hei.caulier.services.ArticleService;
import hei.caulier.services.MachineService;
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

	private CommandeDao commandeDao = new CommandeDao();
	private LigneCommandeDao ligneCommandeDao = new LigneCommandeDao();
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
	private TextField chEtiquetteTotal;
	@FXML
	private TextField chEtiquetteTotal2;
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
		System.out.println(combo.getValue());
		//SaveToDbOmet();
	}
	
	private void SaveToDbOmet() {
		Integer adresseId = AdresseService.getInstance().getAdresseIdFromString(chAdresse.getText());
		LocalDate dateCommande = ConvertStringToDate.StringToDate(chDateCom.getText());
		String typeImpression = combo.getValue();
		Integer sensImpressionRecto = null;
		Integer sensImpressionVerso = null;
		Integer coteImpression = null;
		String matiere = null;
		Integer idArticle1 = ArticleService.getInstance().getArticleIdFromString(chCodeArticle.getText());
		Integer idArticle2 = ArticleService.getInstance().getArticleIdFromString(chCodeArticle2.getText());
		if (chSensImpr1.isSelected()) { sensImpressionRecto = 1; }
		if (chSensImpr2.isSelected()) { sensImpressionRecto = 2; }
		if (chSensImpr3.isSelected()) { sensImpressionRecto = 3; }
		if (chSensImpr4.isSelected()) { sensImpressionRecto = 4; }
		if (chSensImpr5.isSelected()) { sensImpressionVerso = 5; }
		if (chSensImpr6.isSelected()) { sensImpressionVerso = 6; }
		if (chSensImpr7.isSelected()) { sensImpressionVerso = 7; }
		if (chSensImpr8.isSelected()) { sensImpressionVerso = 8; }
		if (chMatiere1.isSelected()) { matiere = "DIVIPA THERM 200µ"; }
		if (chMatiere2.isSelected()) { matiere = "GLOSS DIVIPA"; }
		if (chMatiere3.isSelected()) { matiere = "DIVIPA THERM 220µ"; }
		if (chMatiere4.isSelected()) { matiere = "GLOSS SEALA"; }
		if (chMatiere5.isSelected()) { matiere = "SEALA THERM"; }
		if (chRecto.isSelected()) { coteImpression = 1; }
		if (chRectoVerso.isSelected()) { coteImpression = 2; }
		if (chVerso.isSelected()) { coteImpression = 3; }
		Commande newCommande = new Commande(null, AdresseService.getInstance().getAdresse(adresseId), 
				MachineService.getInstance().getMachine(1), dateCommande, chDepart.getText(), Integer.valueOf(chCodeAchat.getText()), chModeLivraison.getText(), 
				typeImpression, sensImpressionRecto, sensImpressionVerso, null, Float.valueOf(chDiamMandrin.getText()), 
				Float.valueOf(chDiamExtBobine.getText()), chDeveloppement.getText(), chCliche.getText(), null, matiere, 
				chObservations.getText(), Integer.valueOf(chNbEtiquettes.getText()), chRectoMatiere.getText(), chVersoMatiere.getText(), 
				null, null, null, coteImpression);
		commandeDao.addCommande(newCommande);
		
		LigneCommande newLigneCommande = new LigneCommande(null, newCommande, ArticleService.getInstance().getArticle(idArticle1), Float.valueOf(chLargeur.getText()), 
				Integer.valueOf(chNbCouleurs.getText()), chModele.getText(), chRefPantones.getText(), Integer.valueOf(chNbBobines.getText()), null, 
				null, null, null, null, null, null, null, null, null, null, null, null, Float.valueOf(chEtiquetteTotal.getText()));
		ligneCommandeDao.addLigneCommande(newLigneCommande);
		if ((chCodeArticle2.getText() != null && !chCodeArticle2.getText().isEmpty())) {
			LigneCommande newLigneCommande2 = new LigneCommande(null, newCommande, ArticleService.getInstance().getArticle(idArticle2), Float.valueOf(chLargeur2.getText()), 
				Integer.valueOf(chNbCouleurs2.getText()), chModele2.getText(), chRefPantones2.getText(), Integer.valueOf(chNbBobines2.getText()), null, 
				null, null, null, null, null, null, null, null, null, null, null, null, Float.valueOf(chEtiquetteTotal2.getText()));
			ligneCommandeDao.addLigneCommande(newLigneCommande2);
		}
	}





}