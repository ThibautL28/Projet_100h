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
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import hei.caulier.projet.view.FlexotecnicaController;
import javafx.scene.text.TextAlignment;


public class PDFGenerator_Flexo {
    private static String FILE = "C:\\AppBonsFab\\Flexo\\Pdf_Flexo.pdf";


    public static void createPDF(String test) {
        try {
        	File destination = new File("C:\\AppBonsFab\\Flexo"); 
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
            PdfPCell cell = new PdfPCell(new Phrase("DATE : "  + " test date ", f));
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("DEPART : " + " test départ", f));            
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("TYPE IMPRESSION : " + "POLYFILM NON COMPLEXE", f_b));            
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("MODE DE LIVRAISON : " + "test de mode de livraison", f)); 
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            
            cell.setRowspan(2);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("CLIENT : " + "test de client", f));            
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("ADRESSE LIVRAISON : " + "test d'une adresse de livraison aléatoire", f));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(2);
            table.addCell(cell);
            
            document.add(table);
            
         //image sens d'impression
            Image img = Image.getInstance("resources/images/sensFlexo.png");
            img.setAbsolutePosition(250f, 480f);
            img.scaleToFit(300f, 300f);
            document.add(img);
 
            PdfContentByte canvas = writer.getDirectContent();
            
            canvas.saveState();
            canvas.setLineWidth(1f);
                       
            //traçage du carré "bobine"
            canvas.moveTo(200, 520);
            canvas.lineTo(200, 425);
            canvas.lineTo(5, 425);
            canvas.lineTo(5, 520);
            canvas.lineTo(200, 520);
            
            //traçage du carré etiquette tracabilité
            canvas.moveTo(10, 395);
            canvas.lineTo(250, 395);
            canvas.lineTo(250, 195);
            canvas.lineTo(10, 195);
            canvas.lineTo(10, 395);
            
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
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("SENS D'IMPRESSION : " +"1" +  ", " +" 5",f), 300, 465, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Observations : " ,f), 220, 445, 0);
            
            //création d'une case observations
            PdfPTable obs = new PdfPTable(1);
            //remplir infos observations
            PdfPCell obscell = new PdfPCell(new Phrase("observations aléatoires pour tester le comportement de la case contenant ces observations ", f));
            obs.addCell(obscell);
            obs.setTotalWidth(150);
            obs.writeSelectedRows(0,1, 320, 450, canvas);
            
            //la case bobine et ses infos à remplir
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Bobine : ",f_u), 10, 507, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Mètres : " +"15 1515",f), 10, 490, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Diam. Mandrin : " +"15 1515",f), 10, 470, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Diam. Ext. Bob. : " +"15 1515",f), 10, 450, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Développement : " +"test ",f_i), 10, 430, 0);
            
            //infos à remplir
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("MATIERE IMPRESSION : " +"cela est un test de matière",f), 10, 170, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("MATIERE COLLAGE : " +"cela est un autre test",f), 10, 140, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Découpe : " + " directe / à part" ,f), 380, 150, 0);
            
            
          //création d'une case code achat
            PdfPTable tableCAchat = new PdfPTable(1);
            //remplir infos observations
            PdfPCell acell = new PdfPCell(new Phrase("CODE ACHAT : ", f));
            tableCAchat.addCell(acell);
            
            acell = new PdfPCell(new Phrase("25418 515", f));
            tableCAchat.addCell(acell);
            
            tableCAchat.setTotalWidth(120);
            tableCAchat.writeSelectedRows(0,2, 520, 440, canvas);
            
            //pas d'info à remplir
            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, new Phrase("COMMANDE",f_b), 606, 135, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("ETIQUETTE TRACABILITE : ",f_bu), 10, 405, 0);
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
            
            cbas = new PdfPCell(new Phrase("m", f_b)); 
            cbas.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase("B", f_b)); 
            cbas.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase("m", f_b)); 
            cbas.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase("B", f_b)); 
            cbas.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase("m", f_b)); 
            cbas.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabbas.addCell(cbas);
            
            //2e ligne
            cbas = new PdfPCell(new Phrase("15989 59 4 951 959 FAP", f)); //CODE
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase("24789", f)); //LARG
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase("6497", f)); //NBRE COULEURS
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase("ici MODELE relativement long", f)); //MODELE
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase(" ici REFERENCE relativement longue", f)); //REF PANTONE
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase("648795", f)); //B
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase("878666000", f)); //M
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
            cbas = new PdfPCell(new Phrase("159 51 5 1 95 15 5995 DEZ", f)); //CODE 2
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase("18558", f)); //LARG 2
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase("752", f)); //NBRE COULEURS 2
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase(" ici MODELE long", f)); //MODELE 2
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase("ici REFERENCE longue", f)); //REF PANTONE 2
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase(" 150 000", f)); //B 2
            tabbas.addCell(cbas);
            
            cbas = new PdfPCell(new Phrase("800 000 000", f)); //METRES 2
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

        		if ((new File("C:\\AppBonsFab\\Flexo\\Pdf_Flexo.pdf")).exists()) {

        			Process p = Runtime
        			   .getRuntime()
        			   .exec("rundll32 url.dll,FileProtocolHandler C:\\AppBonsFab\\Flexo\\Pdf_Flexo.pdf");
        			p.waitFor();
        				
        		} else {

        			System.out.println("File do not exists");
        		}
        		System.out.println("PDF done");

        	  } catch (Exception ex) {
      		ex.printStackTrace();
      	  }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // iText allows to add metadata to the PDF which can be viewed in your Adobe Reader
    // under File -> Properties
    private static void addMetaData(Document document) {
    	document.addTitle("Bon de fabrication");
        document.addSubject("Flexo");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("CAULIER");
        document.addCreator("CAULIER");
    }
}