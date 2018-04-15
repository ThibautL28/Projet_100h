package hei.caulier.projet;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import hei.caulier.projet.entities.Commande;
import hei.caulier.projet.entities.LigneCommande;
import hei.caulier.services.ClientService;
import hei.caulier.services.CommandeService;
import hei.caulier.services.LigneCommandeService;

public class PDFGenerator_Omet {
	private static String FILE = "C:\\AppBonsFab\\Omet\\Pdf_Omet.pdf";
    

    public static void createPDF() {
        try {
        	Integer newCommandeId = CommandeService.getInstance().getNewCommandeId();
        	Commande newCommande = CommandeService.getInstance().getCommande(newCommandeId);
        	LigneCommande newLigneCommande1 = LigneCommandeService.getInstance().listLignesCommande(newCommandeId).get(0);
        	Boolean OnlyOneLigneCommande = true;
        	LigneCommande newLigneCommande2 = new LigneCommande(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,null, null, null, null, null, null);
        	if (LigneCommandeService.getInstance().listLignesCommande(newCommandeId).size()==2) {
        		newLigneCommande2 = LigneCommandeService.getInstance().listLignesCommande(newCommandeId).get(1);
        		OnlyOneLigneCommande = false;
        	}
        	String coteimpression = "";
        	if (newCommande.getCoteImpression()==1) {
				coteimpression = "RECTO";
			}
        	if (newCommande.getCoteImpression()==2) {
				coteimpression = "RECTO/VERSO";
			}
        	if (newCommande.getCoteImpression()==3) {
				coteimpression = "VERSO";
			}
        	File destination = new File("C:\\AppBonsFab\\Omet"); 
        	destination.mkdirs();
            Document document = new Document(PageSize.A4.rotate(),5,5,5,5);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            addMetaData(document);
            
          //définition des polices
            Font f=new Font(FontFamily.TIMES_ROMAN,15f,Font.NORMAL,BaseColor.BLACK);
            Font f_u=new Font(FontFamily.TIMES_ROMAN,15f,Font.UNDERLINE,BaseColor.BLACK);
            Font f_bu=new Font(FontFamily.TIMES_ROMAN,15f,Font.UNDERLINE|Font.BOLD,BaseColor.BLACK);
            Font f_b=new Font(FontFamily.TIMES_ROMAN,14f,Font.BOLD,BaseColor.BLACK);
            Font f_i=new Font(FontFamily.TIMES_ROMAN,14f,Font.ITALIC,BaseColor.BLACK);
            
            
            PdfPTable table = new PdfPTable(4);
            table.setWidths(new int[]{ 1, 1, 2, 2});
            table.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.setWidthPercentage(100);
            
            
            //infos à compléter depuis BDD
            PdfPCell cell = new PdfPCell(new Phrase("DATE : "  + String.valueOf(newCommande.getDateCom()), f));
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("DEPART : " + String.valueOf(newCommande.getDepart()), f));            
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("TYPE IMPRESSION : " + String.valueOf(newCommande.getTypeImpression()), f_b));            
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("MODE DE LIVRAISON : " + String.valueOf(newCommande.getModeLivraison()), f)); 
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            
            cell.setRowspan(2);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("CLIENT : " + String.valueOf(ClientService.getInstance().getClient(newCommande.getAdresse().getClient().getIdClient()).getNomClient()), f));            
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("ADRESSE LIVRAISON : " + String.valueOf(newCommande.getAdresse().getAdresseClient()), f));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(2);
            table.addCell(cell);
            
            document.add(table);
            
         //image sens d'impression
            Image sensImpr = Image.getInstance("resources/images/sensFlexo.png");
            sensImpr.setAbsolutePosition(250f, 480f);
            sensImpr.scaleToFit(300f, 300f);
            document.add(sensImpr);
            
          //image bobine finie
            Image bobFinie = Image.getInstance("resources/images/bobfinie.png");
            bobFinie.setAbsolutePosition(30f, 127f);
            bobFinie.scaleToFit(100f, 100f);
            document.add(bobFinie);
 
            PdfContentByte canvas = writer.getDirectContent();
            
            canvas.saveState();
            canvas.setLineWidth(1f);
                       
            //traçage du carré "bobine"
            canvas.moveTo(200, 520);
            canvas.lineTo(200, 425);
            canvas.lineTo(5, 425);
            canvas.lineTo(5, 520);
            canvas.lineTo(200, 520);
            canvas.moveTo(5, 425);
            canvas.lineTo(5, 400);
            canvas.lineTo(200, 400);
            canvas.lineTo(200, 425);
                        
            //carré "commande"
            canvas.moveTo(560, 150);
            canvas.lineTo(652, 150);
            canvas.lineTo(652, 125);
            canvas.lineTo(560, 125);
            canvas.lineTo(560, 150);
            
            //carré partie de droite pas à remplir
            canvas.moveTo(652, 150);
            canvas.lineTo(652, 510);
            canvas.lineTo(835, 510);
            canvas.lineTo(835, 125);
            canvas.moveTo(743, 125);
            canvas.lineTo(743, 510);
            
            
            //carré imprimeur
            canvas.moveTo(525, 350);
            canvas.lineTo(652, 350);
            canvas.lineTo(652, 275);
            canvas.lineTo(525, 275);
            canvas.lineTo(525, 350);
            
            canvas.stroke();
            canvas.restoreState();
            
           //remplir sens impression
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("SENS D'IMPRESSION : " + String.valueOf(newCommande.getSensImpressionRecto()) +  ", " + String.valueOf(newCommande.getSensImpressionVerso()),f), 300, 465, 0);
            
            
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("MATIERE : " + String.valueOf(newCommande.getMatiere()),f_b), 250, 375, 0);
            
            
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Observations : " ,f), 20, 320, 0);
            //création d'une case observations
            PdfPTable obs = new PdfPTable(1);
            //remplir infos observations
            PdfPCell obscell = new PdfPCell(new Phrase(String.valueOf(newCommande.getObservations()), f));
            obs.addCell(obscell);
            obs.setTotalWidth(280);
            obs.writeSelectedRows(0,1, 120, 330, canvas);
            
            //la case bobine et ses infos à remplir
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Bobine : ",f_u), 10, 507, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Nb étiquettes : " + String.valueOf(newCommande.getNbEtiquettes()),f), 10, 490, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Diam. Mandrin : " + String.valueOf(newCommande.getDiamMandrin()),f), 10, 470, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Diam. Ext. Bob. : " + String.valueOf(newCommande.getDiamExtBobine()),f), 10, 450, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Développement : " + String.valueOf(newCommande.getDeveloppement()),f_i), 10, 430, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Cliché : " + String.valueOf(newCommande.getCliche()),f_i), 10, 410, 0);
            
            //infos à remplir
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Recto : Matière : " + String.valueOf(newCommande.getRectoMatiere()),f), 150, 170, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Verso : Matière : " + String.valueOf(newCommande.getVersoMatiere()),f), 150, 140, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(coteimpression,f_b), 440, 150, 0);
            
            
          //création d'une case code achat
            PdfPTable tableCAchat = new PdfPTable(1);
            //remplir infos observations
            PdfPCell acell = new PdfPCell(new Phrase("CODE ACHAT : ", f));
            tableCAchat.addCell(acell);
            
            acell = new PdfPCell(new Phrase(String.valueOf(newCommande.getCodeAchat()), f));
            tableCAchat.addCell(acell);
            
            tableCAchat.setTotalWidth(120);
            tableCAchat.writeSelectedRows(0,2, 520, 440, canvas);
            
            //pas d'info à remplir
            
            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, new Phrase("COMMANDE",f_b), 606, 135, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Bobine finie: ",f_b), 10, 200, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, new Phrase("Imprimeur : ",f), 588, 330, 0);
            
            //partie de droite pas à remplir
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Nbre Colis",f), 660, 490, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Nbre Colis",f), 751, 490, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Poids",f), 660, 350, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Poids",f), 751, 350, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("1ère livr",f), 660, 210, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Solde livr",f), 751, 210, 0);
            
            
            //création du tableau du bas
            PdfPTable tabbas = new PdfPTable(11);
            tabbas.setWidths(new int[]{ 3, 1, 2, 3, 3, 1, 1, 1, 1, 1, 1});
            tabbas.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabbas.setWidthPercentage(100);
            
            
            PdfPCell cbas = new PdfPCell(new Phrase("CODE", f_b));
            cbas.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase("LARG", f_b)); 
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase("NBRE COULEURS", f_b)); 
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase("MODELE", f_b));
            cbas.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase("REF. PANTONE", f_b));
            cbas.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase("B", f_b)); 
            cbas.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase("E", f_b)); 
            cbas.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase("B", f_b)); 
            cbas.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase("E", f_b)); 
            cbas.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase("B", f_b)); 
            cbas.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase("E", f_b)); 
            cbas.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabbas.addCell(cbas);
            
            //2e ligne
            cbas = new PdfPCell(new Phrase(String.valueOf(newLigneCommande1.getArticle().getRefArticle()), f)); //CODE
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase(String.valueOf(newLigneCommande1.getLargeur()), f)); //LARG
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase(String.valueOf(newLigneCommande1.getNbCouleurs()), f)); //NBRE COULEURS
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase(String.valueOf(newLigneCommande1.getModele()), f)); //MODELE
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase(String.valueOf(newLigneCommande1.getRefPantones()), f)); //REF PANTONE
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase(String.valueOf(newLigneCommande1.getNbBobines()), f)); //B
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase(String.valueOf(newLigneCommande1.getMetreTotal()), f)); //M
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase(" ", f)); //rien à remplir
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase(" ", f)); //rien à remplir
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase(" ", f)); //rien à remplir
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase(" ", f)); //rien à remplir
            tabbas.addCell(cbas);
            
            //3e ligne
            cbas = new PdfPCell(new Phrase(OnlyOneLigneCommande == true ? "" : String.valueOf(newLigneCommande2.getArticle().getRefArticle()), f)); //CODE 2
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase(OnlyOneLigneCommande == true ? "" : String.valueOf(newLigneCommande2.getLargeur()), f)); //LARG 2
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase(OnlyOneLigneCommande == true ? "" : String.valueOf(newLigneCommande2.getNbCouleurs()), f)); //NBRE COULEURS 2
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase(OnlyOneLigneCommande == true ? "" : String.valueOf(newLigneCommande2.getModele()), f)); //MODELE 2
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase(OnlyOneLigneCommande == true ? "" : String.valueOf(newLigneCommande2.getRefPantones()), f)); //REF PANTONE 2
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase(OnlyOneLigneCommande == true ? "" : String.valueOf(newLigneCommande2.getNbBobines()), f)); //B 2
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase(OnlyOneLigneCommande == true ? "" : String.valueOf(newLigneCommande2.getEtiquetteTotal()), f)); //METRES 2
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase(" ", f)); //rien à remplir
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase(" ", f)); //rien à remplir
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase(" ", f)); //rien à remplir
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase(" ", f)); //rien à remplir
            tabbas.addCell(cbas);
            
            tabbas.setTotalWidth(825);
            tabbas.writeSelectedRows(0, 3, 10, 125, canvas);
            
                  
            
            document.close();
            
            try {

        		if ((new File("C:\\AppBonsFab\\Omet\\Pdf_Omet.pdf")).exists()) {

        			Process p = Runtime
        			   .getRuntime()
        			   .exec("rundll32 url.dll,FileProtocolHandler C:\\AppBonsFab\\Omet\\Pdf_Omet.pdf");
        			p.waitFor();
        				
        		} else {

        			System.out.println("File is not exists");

        		}
        		System.out.println("Done");

        	  } catch (Exception ex) {
      		ex.printStackTrace();
      	  }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // iText allows to add metadata to the PDF which can be viewed in your Adobe
    // Reader
    // under File -> Properties
    private static void addMetaData(Document document) {
        document.addTitle("Bon de fabrication");
        document.addSubject("Omet");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("CAULIER");
        document.addCreator("CAULIER");
    }

}