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

import javafx.scene.text.TextAlignment;


public class PDFGenerator_Flexo {
    private static String FILE = "C:\\AppBonsFab\\Flexo\\Pdf_Flexo.pdf";
    
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);

    public static void createPDF() {
        try {
        	File destination = new File("C:\\AppBonsFab\\Flexo"); 
        	destination.mkdirs();
            Document document = new Document(PageSize.A4.rotate(),5,5,5,5);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            
            Font f1=new Font(FontFamily.UNDEFINED,15f,Font.NORMAL,BaseColor.BLACK);
            Font f=new Font(FontFamily.TIMES_ROMAN,15f,Font.NORMAL,BaseColor.BLACK);
            Font f3=new Font(FontFamily.SYMBOL,15f,Font.NORMAL,BaseColor.BLACK);
            Font f4=new Font(FontFamily.HELVETICA,15f,Font.NORMAL,BaseColor.BLACK);
            Font f5=new Font(FontFamily.COURIER,15f,Font.NORMAL,BaseColor.BLACK);
            BaseFont bf_f = f.getCalculatedBaseFont(false);
            
            PdfPTable table2 = new PdfPTable(4);
            table2.setWidths(new int[]{ 1, 1, 2, 2});
            table2.setHorizontalAlignment(Element.ALIGN_LEFT);
            table2.setWidthPercentage(100);
            
            PdfPCell cell = new PdfPCell(new Phrase("DATE : " + 1 + "", f));
            table2.addCell(cell);
            
            cell = new PdfPCell(new Phrase("DEPART : " + 2 + "", f));            
            table2.addCell(cell);
            
            cell = new PdfPCell(new Phrase("TYPE IMPRESSION : " + "oui" + "", f));            
            table2.addCell(cell);
            
            cell = new PdfPCell(new Phrase("MODE DE LIVRAISON : " + "idk" + "", f)); 
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            
            cell.setRowspan(2);
            table2.addCell(cell);
            
            cell = new PdfPCell(new Phrase("CLIENT : " + 5 + "", f));            
            table2.addCell(cell);
            
            cell = new PdfPCell(new Phrase("ADRESSE LIVRAISON : " + 6 + "", f));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(2);
            table2.addCell(cell);
            
            document.add(table2);
            
         
            Image img = Image.getInstance("resources/images/sensFlexo.png");
            img.setAbsolutePosition(250f, 515f);
            img.scaleToFit(300f, 300f);;
            document.add(new Paragraph("Sample 1: This is simple image demo."));
            
            
            
            
            document.add(img);

            // Drawing lines to see where the text is added
            PdfContentByte canvas = writer.getDirectContent();
            // More lines to see where the text is added
            canvas.saveState();
            canvas.setLineWidth(0.05f);
            canvas.moveTo(200, 590);
            canvas.lineTo(200, 410);
            canvas.moveTo(400, 590);
            canvas.lineTo(400, 410);
            canvas.moveTo(80, 572);
            canvas.lineTo(520, 572);
            canvas.moveTo(80, 536);
            canvas.lineTo(520, 536);
            canvas.moveTo(80, 500);
            canvas.lineTo(520, 500);
            canvas.moveTo(80, 464);
            canvas.lineTo(520, 464);
            canvas.moveTo(80, 428);
            canvas.lineTo(520, 428);
            canvas.stroke();
            canvas.restoreState();
            // Adding text with ColumnText.showTextAligned()
            Phrase phrase = new Phrase("fou connard", f);
            Phrase zcent = new Phrase("0 ; 0", f);
            Phrase cent = new Phrase("100 ; 100", f);
            Phrase dcent = new Phrase("200 ; 200", f);
            Phrase tcent = new Phrase("300 ; 300", f);
            Phrase qcent = new Phrase("400 ; 400", f);
            Phrase ccent = new Phrase("500 ; 500", f);
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase, 200, 572, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase, 200, 536, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, phrase, 200, 500, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, phrase, 200, 464, 30);
            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, phrase, 200, 428, -30);
            
            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, zcent, 0, 0, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, cent, 100, 100, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, dcent, 200, 200, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, tcent, 300, 300, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, qcent, 400, 400, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, ccent, 500, 500, 0);
            
           
            
            document.close();
            
            try {

        		if ((new File("C:\\AppBonsFab\\Flexo\\Pdf_Flexo.pdf")).exists()) {

        			Process p = Runtime
        			   .getRuntime()
        			   .exec("rundll32 url.dll,FileProtocolHandler C:\\AppBonsFab\\Flexo\\Pdf_Flexo.pdf");
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
        document.addSubject("Flexo");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("CAULIER");
        document.addCreator("CAULIER");
    }

    
   /* 
    public static PdfPTable createFirstTable() {
    	// a table with three columns
        PdfPTable table = new PdfPTable(3);
        
        
        // the cell object
        PdfPCell cell;
        // we add a cell with colspan 3
        cell = new PdfPCell(new Phrase("Cell with colspan 3"));
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        // now we add a cell with rowspan 2
        cell = new PdfPCell(new Phrase("Cell with rowspan 2"));
        cell.setRowspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        // we add the four remaining cells with addCell()
        table.addCell("row 1; cell 1");
        table.addCell("row 1; cell 2");
        table.addCell("row 2; cell 1");
        table.addCell("row 2; cell 2");
        return table;
    }

*/

    private static void createList(Section subCatPart) {
        List list = new List(true, false, 10);
        list.add(new ListItem("First point"));
        list.add(new ListItem("Second point"));
        list.add(new ListItem("Third point"));
        subCatPart.add(list);
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}