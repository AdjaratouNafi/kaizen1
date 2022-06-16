/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.util;

/**
 *
 * @author wilfried
 */
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import sn.accelsolution.entities.Devis;
import sn.accelsolution.entities.Newfacture;

public class HeaderFooterPageEventFacture extends PdfPageEventHelper {
    
    private Newfacture facture;
    
    public HeaderFooterPageEventFacture(Newfacture facture){
        this.facture =  facture;
    }
    
    @Override
    public void onStartPage(PdfWriter pdfWriter, Document document) {

        PdfPTable header = new PdfPTable(4);

        try {

            Font boldFontTitle = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
            Font boldFontTitleTable = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
            Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD);
            Font boldFontCG = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLDITALIC, BaseColor.GRAY);
            Font normalFontCG = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.ITALIC, BaseColor.GRAY);
            Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
            Font normalFontP = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL);
            Font normalFontEmail = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLUE);
            Font boldFontTitleExp1 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.RED);
            Font boldFontTitleExp2 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
            Font myFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLUE);

            // set defaults
            //header.setWidths(new int[]{6, 40});
            header.setTotalWidth(500);
            header.setWidthPercentage(100);
            header.setLockedWidth(true);
            /*header.getDefaultCell().setFixedHeight(40);
            header.getDefaultCell().setBorder(Rectangle.BOTTOM);
            header.getDefaultCell().setBorderColor(BaseColor.BLUE);*/

            //MarginInfo margin = new MarginInfo();
            // add image
            String logoPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/rapport/logoAccel.png");
            Image logo = Image.getInstance(logoPath);
            //logo.scaleAbsolute(95f, 95f);
            logo.scaleAbsolute(60f, 60f);
            //logo.setBottom(15);

            PdfPCell cell = new PdfPCell();
            cell.setBorder(Rectangle.BOTTOM);
            cell.setBorderColor(BaseColor.BLUE);
            cell.addElement(logo);

            header.addCell(cell);

            // add text
            PdfPCell text = new PdfPCell();
            text.setPaddingTop(35);
            //text.setPaddingBottom(15);
            text.setPaddingLeft(10);
            text.setBorder(Rectangle.BOTTOM);
            text.setBorderColor(BaseColor.BLUE);

            header.addCell(text);

            // add text
            PdfPCell text1 = new PdfPCell();
            text1.setPaddingTop(35);
            //text.setPaddingBottom(15);
            text1.setPaddingLeft(10);
            text1.setBorder(Rectangle.BOTTOM);
            text1.setBorderColor(BaseColor.BLUE);

            header.addCell(text1);

            // add text
            PdfPCell text2 = new PdfPCell();
            text2.setPaddingTop(35);
            //text.setPaddingBottom(15);
            text2.setPaddingLeft(10);
            text2.setBorder(Rectangle.BOTTOM);
            text2.setBorderColor(BaseColor.BLUE);

            header.addCell(text2);

            //Second column !!!
            PdfPCell cell1 = new PdfPCell();

            Chunk c1 = new Chunk("ACCEL ", boldFontTitleExp1);
            Chunk c2 = new Chunk("TECHNOLOGIES", boldFontTitleExp2);
            Phrase p1 = new Phrase();
            p1.add(c1);
            p1.add(c2);
            Paragraph p = new Paragraph();
            p.add(p1);

            Paragraph paragraphSp1 = new Paragraph("165 Virage, route de l'aéroport", normalFont);
            Paragraph paragraphSp2 = new Paragraph("Dakar, Sénégal", normalFont);
            Paragraph paragraph1 = new Paragraph("Tél : 33 820 83 83", normalFont);
            Paragraph paragraphElementBas2 = new Paragraph("NINEA: 005310652 2Y2", normalFontP);

            Chunk underlineEmail = new Chunk("Email : info@accel-tech.net", normalFontEmail);
            underlineEmail.setUnderline(0.1f, -2f);
            Paragraph paragraph2 = new Paragraph(underlineEmail);

            cell1.addElement(p);
            cell1.addElement(paragraphSp1);
            cell1.addElement(paragraphSp2);
            cell1.addElement(paragraph1);
            cell1.addElement(paragraph2);
            cell1.addElement(paragraphElementBas2);
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell1.setVerticalAlignment(Element.ALIGN_LEFT);
            cell1.setBorder(Rectangle.NO_BORDER);
            cell1.setColspan(2);
            header.addCell(cell1);

//            PdfPCell cell2;
//            cell2 = new PdfPCell(new Phrase(" "));
//            cell2.setBorder(Rectangle.NO_BORDER);
//            header.addCell(cell2);

            PdfPCell cell3;
            cell3 = new PdfPCell(new Phrase(" "));
            cell3.setBorder(Rectangle.NO_BORDER);
            header.addCell(cell3);

            // the cell object
            PdfPCell cell4 = new PdfPCell();
            Paragraph paragraph4 = new Paragraph(" CLIENT", boldFont);
            Paragraph paragraph5 = new Paragraph(" " + facture.getIdDevis().getIdClient().getNomClient(), boldFont);
            Paragraph paragraphBs1 = new Paragraph(" " + facture.getIdDevis().getIdClient().getAdresse(), boldFont);
            Paragraph paragraphBs2 = new Paragraph(" " + facture.getIdDevis().getIdClient().getTelephoneClient(), boldFont);
            Paragraph paragraphBs3 = new Paragraph(" " + facture.getIdDevis().getIdClient().getMailClient(), boldFont);

            cell4.addElement(paragraph4);
            cell4.addElement(paragraph5);
            cell4.addElement(paragraphBs1);
            cell4.addElement(paragraphBs2);
            cell4.addElement(paragraphBs3);
            cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell4.setVerticalAlignment(Element.ALIGN_LEFT);
            //cell4.setBorder(Rectangle.BOX);
            cell4.setBorder(Rectangle.NO_BORDER);
            cell4.setCellEvent(new RoundRectangle());
            header.addCell(cell4);

            // write content
            header.writeSelectedRows(0, -1, 46, 803, pdfWriter.getDirectContent());
        } catch (DocumentException de) {
            throw new ExceptionConverter(de);
        } catch (IOException ex) {
            Logger.getLogger(HeaderFooterPageEventFacture.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void onEndPage(PdfWriter pdfWriter, Document document) {

        PdfPTable footer = new PdfPTable(2);
        try {
            // set defaults
            footer.setWidths(new int[]{24, 2});
            footer.setTotalWidth(500);
            footer.setLockedWidth(true);
        } catch (DocumentException ex) {
            Logger.getLogger(HeaderFooterPageEventFacture.class.getName()).log(Level.SEVERE, null, ex);
        }

        // add copyright
        PdfPCell cell0 = new PdfPCell(new Phrase("ADRESSE: 156 Virage, route de l'aéroport - BANQUE: BOA 005003810004 / IBAN: SN08 SN10 0010 2200", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD)));
        cell0.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell0.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell0.setFixedHeight(40);
        cell0.setBorder(Rectangle.TOP);
        cell0.setBorderColor(BaseColor.BLUE);
        //cell0.setColspan(2);
        footer.addCell(cell0);

        // add current page count
        PdfPCell cell1 = new PdfPCell(new Phrase(String.format("Page %d", pdfWriter.getPageNumber()), new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD)));
        cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell1.setBorder(Rectangle.TOP);
        cell1.setBorderColor(BaseColor.BLUE);
        footer.addCell(cell1);

        footer.writeSelectedRows(0, -1, 50, 50, pdfWriter.getDirectContent());

    }
}
