/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.text.Text;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import sn.accelsolution.dao.PrixFacade;
import sn.accelsolution.entities.Chantier;
import sn.accelsolution.entities.Commande;
import sn.accelsolution.entities.Contrat;
import sn.accelsolution.entities.Decomptep;
import sn.accelsolution.entities.Detaillechantier;
import sn.accelsolution.entities.DetailleCommande;
import sn.accelsolution.entities.Devis;
import sn.accelsolution.entities.Expressionbesoin;
import sn.accelsolution.entities.Livraison;
import sn.accelsolution.entities.Marche;
import sn.accelsolution.entities.Newfacture;
import sn.accelsolution.entities.Prix;
import sn.accelsolution.entities.Salaire;
import sn.accelsolution.entities.Sortiestock;
import sn.accelsolution.entities.Utilisateur;

/**
 *
 * @author DELL
 */
public class ReportAccelModel implements Serializable {

    private EntetePath entetePath;

    public ReportAccelModel() {
        entetePath = new EntetePath();
    }

    public EntetePath getEntetePath() {
        return entetePath;
    }

    public void setEntetePath(EntetePath entetePath) {
        this.entetePath = entetePath;
    }

    public void GenererModel() throws DocumentException {

        File directory = new File("./");
        String urlAbsolut = directory.getAbsolutePath();
        String url = "";

        try {
            String recuname = "ModelPdfAccel.pdf";
            url = urlAbsolut + recuname;

            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "inline=filename=file.pdf");

            Document document = new Document();
            document.setPageSize(PageSize.A4);
            OutputStream file = new FileOutputStream(new File(url));
            // step 2
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            PdfWriter.getInstance(document, file);
            // step 3
            document.open();

            Font boldFontTitle = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
            Font boldFontTitleTable = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
            Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD);
            Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
            Font normalFontEmail = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLUE);
            Font boldFontTitleExp = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD, BaseColor.RED);

            String img = entetePath.getUrlAccel();

            Image logoProjet = Image.getInstance(img);
            logoProjet.scaleAbsolute(60f, 40f);
            logoProjet.setAlignment(Image.ALIGN_LEFT);
            document.add(logoProjet);

            //Espace
            // Creating text object       
            /*Text text1 = new Text("ACCEL");
             Text text2 = new Text("ACCEL TECHNOLOGIES");
            
             Font boldFontTitleSpecial1 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
             boldFontTitleSpecial1.setColor(BaseColor.RED);*/
            Chunk c1 = new Chunk("ACCEL", boldFontTitleExp);
            Chunk c2 = new Chunk("TECHNOLOGIES", boldFontTitle);
            Phrase p1 = new Phrase();
            p1.add(c1);
            p1.add(c2);
            Paragraph p = new Paragraph();
            p.add(p1);
            document.add(p);
            //Espace
            document.add(new Paragraph("4 Route de l’aéroport", normalFont));
            //Espace
            document.add(new Paragraph("Dakar, Sénégal", normalFont));

            // A table1 with 4 columns
            PdfPTable table1 = new PdfPTable(4);
            table1.setSpacingBefore(2f);
            table1.setWidthPercentage(100);
            PdfPCell cell1 = new PdfPCell();
            Paragraph paragraph1 = new Paragraph("Tél : 33 820 83 83", normalFont);

            Chunk underlineEmail = new Chunk("Email : info@accel-tech.net", normalFontEmail);
            underlineEmail.setUnderline(0.1f, -2f);
            Paragraph paragraph2 = new Paragraph(underlineEmail);

            cell1.addElement(paragraph1);
            cell1.addElement(paragraph2);
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell1.setVerticalAlignment(Element.ALIGN_LEFT);
            cell1.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell1);

            PdfPCell cell2;
            cell2 = new PdfPCell(new Phrase(" "));
            cell2.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell2);

            PdfPCell cell3;
            cell3 = new PdfPCell(new Phrase(" "));
            cell3.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell3);

            // the cell object
            PdfPCell cell4 = new PdfPCell();
            Paragraph paragraph4 = new Paragraph("CLIENT", boldFont);
            Paragraph paragraph5 = new Paragraph("ENTEPRISE SARR TECHNOBAT", boldFont);
            cell4.addElement(paragraph4);
            cell4.addElement(paragraph5);
            cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell4.setVerticalAlignment(Element.ALIGN_LEFT);
            cell4.setBorder(Rectangle.BOX);
            table1.addCell(cell4);

            // Defiles the relative width of the columns
            float[] columnWidths = new float[]{20f, 10f, 20f, 30f};
            table1.setWidths(columnWidths);
            document.add(table1);

            //Espace
            document.add(new Paragraph("  "));

            // A table1 with 4 columns
            PdfPTable table2 = new PdfPTable(4);
            table2.setSpacingBefore(10f);
            table2.setWidthPercentage(100);
            // the cell object
            PdfPCell cell5;
            // we add a cell with colspan 4
            cell5 = new PdfPCell(new Phrase("PROFORMA", boldFontTitle));
            cell5.setColspan(4);

            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
            //border
            cell5.setBorder(0);
            table2.addCell(cell5);
            document.add(table2);

            // A table1 with 4 columns
            PdfPTable table3 = new PdfPTable(4);
            table3.setSpacingBefore(10f);
            table3.setWidthPercentage(100);
            PdfPCell cell6 = new PdfPCell();
            Chunk underline1 = new Chunk("DATE : 20/12/2018", normalFont);
            underline1.setUnderline(0.1f, -2f);
            cell6.addElement(underline1);
            cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell6.setVerticalAlignment(Element.ALIGN_LEFT);
            cell6.setBorder(Rectangle.NO_BORDER);
            table3.addCell(cell6);

            PdfPCell cell7;
            cell7 = new PdfPCell(new Phrase(" "));
            cell7.setBorder(Rectangle.NO_BORDER);
            table3.addCell(cell7);

            PdfPCell cell8;
            cell8 = new PdfPCell(new Phrase(" "));
            cell8.setBorder(Rectangle.NO_BORDER);
            table3.addCell(cell8);

            // the cell object
            PdfPCell cell9 = new PdfPCell();
            Chunk underline2 = new Chunk("PROFORMA NUMERO", boldFont);
            underline2.setUnderline(0.1f, -2f);
            Paragraph paragraph8 = new Paragraph("N°ABM201218/A", boldFont);
            cell9.addElement(underline2);
            cell9.addElement(paragraph8);
            cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell9.setVerticalAlignment(Element.ALIGN_LEFT);
            cell9.setBorder(Rectangle.NO_BORDER);
            table3.addCell(cell9);

            // Defiles the relative width of the columns
            float[] columnWidthss = new float[]{20f, 10f, 20f, 30f};
            table3.setWidths(columnWidthss);
            document.add(table3);

            //Espace
            document.add(new Paragraph("     "));

            PdfPTable table4 = new PdfPTable(6);
            table4.setSpacingBefore(10f);
            table4.setWidthPercentage(100);

            table4.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);

            PdfPCell cellT1 = new PdfPCell();
            cellT1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellT1.setVerticalAlignment(Element.ALIGN_LEFT);
            Paragraph paragraph9 = new Paragraph("Désignation", boldFontTitleTable);
            cellT1.addElement(paragraph9);
            table4.addCell(cellT1);

            PdfPCell cellT2 = new PdfPCell();
            cellT2.setHorizontalAlignment(Element.ALIGN_CENTER);
            Paragraph paragraph10 = new Paragraph("Ref", boldFontTitleTable);
            cellT2.addElement(paragraph10);
            table4.addCell(cellT2);

            PdfPCell cellT3 = new PdfPCell();
            cellT3.setHorizontalAlignment(Element.ALIGN_CENTER);
            Paragraph paragraph11 = new Paragraph("U.mesure", boldFontTitleTable);
            cellT3.addElement(paragraph11);
            table4.addCell(cellT3);

            PdfPCell cellT4 = new PdfPCell();
            cellT4.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellT4.setVerticalAlignment(Element.ALIGN_MIDDLE);
            Paragraph paragraph12 = new Paragraph("Qte", boldFontTitleTable);
            cellT4.addElement(paragraph12);
            table4.addCell(cellT4);

            PdfPCell cellT5 = new PdfPCell();
            cellT5.setHorizontalAlignment(Element.ALIGN_CENTER);
            Paragraph paragraph13 = new Paragraph("P.U", boldFontTitleTable);
            cellT5.addElement(paragraph13);
            table4.addCell(cellT5);

            PdfPCell cellT6 = new PdfPCell();
            cellT6.setHorizontalAlignment(Element.ALIGN_CENTER);
            Paragraph paragraph14 = new Paragraph("P.Total", boldFontTitleTable);
            cellT6.addElement(paragraph14);
            table4.addCell(cellT6);

            table4.setHeaderRows(1);
            PdfPCell[] cells = table4.getRow(0).getCells();
            for (int j = 0; j < cells.length; j++) {
                cells[j].setBackgroundColor(BaseColor.WHITE);
            }

            for (int i = 0; i < 2; i++) {

                table4.addCell("Designation " + i);
                table4.addCell(" ");
                table4.addCell(" ");
                table4.addCell(" ");
                table4.addCell(" ");
                table4.addCell(" ");
            }

            document.add(table4);

            //Espace
            document.add(new Paragraph("     "));

            PdfPTable table5 = new PdfPTable(6);
            table5.setSpacingBefore(10f);
            table5.setWidthPercentage(100);

            PdfPCell cellTabBas1 = new PdfPCell();
            Paragraph paragraphTabBas1 = new Paragraph(" ");
            cellTabBas1.addElement(paragraphTabBas1);
            cellTabBas1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas1.setVerticalAlignment(Element.ALIGN_LEFT);
            cellTabBas1.setBorder(Rectangle.NO_BORDER);
            cellTabBas1.setColspan(3);
            table5.addCell(cellTabBas1);

            PdfPCell cellTabBas2 = new PdfPCell();
            Paragraph paragraphTabBas2 = new Paragraph("TOTAL HT", boldFontTitleTable);
            cellTabBas2.addElement(paragraphTabBas2);
            cellTabBas2.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas2.setVerticalAlignment(Element.ALIGN_LEFT);
            cellTabBas2.setBorder(Rectangle.BOX);
            cellTabBas2.setColspan(2);
            table5.addCell(cellTabBas2);

            PdfPCell cellTabBas3;
            cellTabBas3 = new PdfPCell(new Phrase("-"));
            cellTabBas3.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellTabBas3.setVerticalAlignment(Element.ALIGN_RIGHT);
            cellTabBas3.setBorder(Rectangle.BOX);
            table5.addCell(cellTabBas3);
            document.add(table5);

            //Espace
            document.add(new Paragraph("     "));

            // A table with 3 columns
            PdfPTable tableBas3 = new PdfPTable(4);
            tableBas3.setSpacingBefore(10f);
            tableBas3.setWidthPercentage(100);

            // the cell object
            PdfPCell cell37;
            Chunk underline = new Chunk("Directeur général", normalFont);
            underline.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            cell37 = new PdfPCell(new Phrase(underline));
            cell37.setBorder(0);
            cell37.setColspan(4);
            cell37.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell37.setVerticalAlignment(Element.ALIGN_RIGHT);
            tableBas3.addCell(cell37);
            document.add(tableBas3);

            //Espace
            document.add(new Paragraph("  "));
            //Espace
            document.add(new Paragraph("  "));

            // A table1 with 4 columns
            PdfPTable table6 = new PdfPTable(4);
            table6.setSpacingBefore(10f);
            table6.setWidthPercentage(100);
            PdfPCell cellBottom;
            cellBottom = new PdfPCell(new Phrase("ACCEL TECHNOLOGIE SARL \n RCCM : SN DKR 2014 B 21849 NINEA : 005310652 2Y2", normalFont));
            cellBottom.setColspan(4);
            cellBottom.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellBottom.setBorder(0);
            table6.addCell(cellBottom);
            document.add(table6);

            // POUR POUVOIR GENERER LE DOCUMENT
            document.close();
            file.close();
            System.out.println("PDF Created!");

            // setting some response headers
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control",
                    "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            // setting the content type
            response.setContentType("application/pdf");
            // the contentlength
            response.setContentLength(baos.size());
            // write ByteArrayOutputStream to the ServletOutputStream
            OutputStream os = response.getOutputStream();
            baos.writeTo(os);
            os.flush();
            os.close();

            context.responseComplete();

        } catch (Exception e) {

        }

    }

    public void GenererDevis(List<FactureUtil> allFactureUtils, Devis devis, String totalHT, String montantTVA, String totalTTC) throws DocumentException {

        File directory = new File("./");
        String urlAbsolut = directory.getAbsolutePath();
        String url = "";

        try {
            String recuname = "Devis.pdf";
            url = urlAbsolut + recuname;

            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "inline=filename=file.pdf");

            Document document = new Document();
            document.setPageSize(PageSize.A4);
            OutputStream file = new FileOutputStream(new File(url));
            // step 2
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            PdfWriter.getInstance(document, file);

            // Add Header and Footer
//            PdfWriter writer = PdfWriter.getInstance(document, baos);
//            HeaderFooterPageEvent event = new HeaderFooterPageEvent();
//            writer.setPageEvent(event);
            // step 3
            document.open();

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

            String img = entetePath.getUrlAccel();

            Image logoProjet = Image.getInstance(img);
            logoProjet.scaleAbsolute(60f, 40f);
            logoProjet.setAlignment(Image.ALIGN_LEFT);
            document.add(logoProjet);

            Paragraph ph = new Paragraph(new Phrase(" ", myFont));
            PdfPCell cell = new PdfPCell(ph);
            cell.setBorder(Rectangle.BOTTOM);
            cell.setBorderColor(new BaseColor(44, 67, 144));
            cell.setBorderWidth(2f);

            PdfPTable table = new PdfPTable(1);
            table.addCell(cell);
            table.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.setWidthPercentage(100);
            document.add(table);

            // A table1 with 4 columns
            PdfPTable table1 = new PdfPTable(4);
            table1.setSpacingBefore(2f);
            table1.setWidthPercentage(100);
            PdfPCell cell1 = new PdfPCell();

            Chunk c1 = new Chunk("ACCEL ", boldFontTitleExp1);
            Chunk c2 = new Chunk("TECHNOLOGIES", boldFontTitleExp2);
            Phrase p1 = new Phrase();
            p1.add(c1);
            p1.add(c2);
            Paragraph p = new Paragraph();
            p.add(p1);

            Paragraph paragraphSp1 = new Paragraph("4 Route de l’aéroport", normalFont);
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
            table1.addCell(cell1);

            PdfPCell cell2;
            cell2 = new PdfPCell(new Phrase(" "));
            cell2.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell2);

            PdfPCell cell3;
            cell3 = new PdfPCell(new Phrase(" "));
            cell3.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell3);

            // the cell object
            PdfPCell cell4 = new PdfPCell();
            Paragraph paragraph4 = new Paragraph("CLIENT", boldFont);
            Paragraph paragraph5 = new Paragraph(devis.getIdClient().getNomClient(), boldFont);
            Paragraph paragraphBs1 = new Paragraph(devis.getIdClient().getAdresse(), boldFont);
            Paragraph paragraphBs2 = new Paragraph(devis.getIdClient().getTelephoneClient(), boldFont);
            Paragraph paragraphBs3 = new Paragraph(devis.getIdClient().getMailClient(), boldFont);

            cell4.addElement(paragraph4);
            cell4.addElement(paragraph5);
            cell4.addElement(paragraphBs1);
            cell4.addElement(paragraphBs2);
            cell4.addElement(paragraphBs3);
            cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell4.setVerticalAlignment(Element.ALIGN_LEFT);
            cell4.setBorder(Rectangle.BOX);
            table1.addCell(cell4);

            // Defiles the relative width of the columns
            float[] columnWidths = new float[]{20f, 10f, 20f, 30f};
            table1.setWidths(columnWidths);
            document.add(table1);

            //Espace
            document.add(new Paragraph("  "));

            // A table1 with 4 columns
            PdfPTable table2 = new PdfPTable(4);
            table2.setSpacingBefore(10f);
            table2.setWidthPercentage(100);
            // the cell object
            PdfPCell cell5;
            // we add a cell with colspan 4
            cell5 = new PdfPCell(new Phrase("PROFORMA", boldFontTitle));
            cell5.setColspan(4);

            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
            //border
            cell5.setBorder(0);
            table2.addCell(cell5);
            document.add(table2);

            // A table1 with 4 columns
            PdfPTable table3 = new PdfPTable(4);
            table3.setSpacingBefore(10f);
            table3.setWidthPercentage(100);
            PdfPCell cell6 = new PdfPCell();
            Chunk underline1 = new Chunk("DATE : " + devis.getDateDevis(), normalFont);
            underline1.setUnderline(0.1f, -2f);
            cell6.addElement(underline1);
            cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell6.setVerticalAlignment(Element.ALIGN_LEFT);
            cell6.setBorder(Rectangle.NO_BORDER);
            table3.addCell(cell6);

            PdfPCell cell7;
            cell7 = new PdfPCell(new Phrase(" "));
            cell7.setBorder(Rectangle.NO_BORDER);
            table3.addCell(cell7);

            PdfPCell cell8;
            cell8 = new PdfPCell(new Phrase(" "));
            cell8.setBorder(Rectangle.NO_BORDER);
            table3.addCell(cell8);

            // the cell object
            PdfPCell cell9 = new PdfPCell();
            Chunk underline2 = new Chunk("PROFORMA NUMERO", boldFont);
            underline2.setUnderline(0.1f, -2f);
            Paragraph paragraph8 = new Paragraph(devis.getNumeroDevis(), boldFont);
            cell9.addElement(underline2);
            cell9.addElement(paragraph8);
            cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell9.setVerticalAlignment(Element.ALIGN_LEFT);
            cell9.setBorder(Rectangle.NO_BORDER);
            table3.addCell(cell9);

            // Defiles the relative width of the columns
            float[] columnWidthss = new float[]{20f, 10f, 20f, 30f};
            table3.setWidths(columnWidthss);
            document.add(table3);

            //Espace
            document.add(new Paragraph("     "));

            // A table1 with 4 columns
            PdfPTable tableDevis = new PdfPTable(4);
            tableDevis.setSpacingBefore(10f);
            tableDevis.setWidthPercentage(100);
            PdfPCell cell10 = new PdfPCell();
            Chunk underline3 = new Chunk("REFERENCE", boldFont);
            underline3.setUnderline(0.1f, -2f);
            Paragraph paragraphExp = new Paragraph(devis.getNommarche(), boldFont);
            cell10.addElement(underline3);
            cell10.addElement(paragraphExp);
            cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell10.setVerticalAlignment(Element.ALIGN_LEFT);
            cell10.setBorder(Rectangle.NO_BORDER);
            cell10.setColspan(2);
            tableDevis.addCell(cell10);

            PdfPCell cell11;
            cell11 = new PdfPCell(new Phrase(""));
            cell11.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell11.setVerticalAlignment(Element.ALIGN_RIGHT);
            cell11.setBorder(Rectangle.NO_BORDER);
            cell11.setColspan(2);
            tableDevis.addCell(cell11);

            document.add(tableDevis);

            //Espace
            document.add(new Paragraph("     "));

            Paragraph preface = new Paragraph("Devise : " + devis.getIdDevise().getSymbole(), boldFont);
            preface.setAlignment(Element.ALIGN_RIGHT);
            document.add(preface);

            /*Debut tableau item*/
            PdfPTable table4 = new PdfPTable(6);
            table4.setSpacingBefore(10f);
            // Defiles the relative width of the columns
            float[] columnWidthsTab = new float[]{125f, 125f, 25f, 25f,100f, 100f};
            table4.setWidths(columnWidthsTab);
            table4.setWidthPercentage(100);

            PdfPCell cellTab1 = new PdfPCell(new Phrase("Désignation",boldFontTitleTable));
            cellTab1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTab1.setPaddingBottom(5);
            table4.addCell(cellTab1);
            PdfPCell cellTab2 = new PdfPCell(new Phrase("Ref",boldFontTitleTable));
            cellTab2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTab2.setPaddingBottom(5);
            table4.addCell(cellTab2);
            PdfPCell cellTab3 = new PdfPCell(new Phrase("U.M",boldFontTitleTable));
            cellTab3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTab3.setPaddingBottom(5);
            table4.addCell(cellTab3);
            PdfPCell cellTab4 = new PdfPCell(new Phrase("Qte",boldFontTitleTable));
            cellTab4.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTab4.setPaddingBottom(5);
            table4.addCell(cellTab4);
            PdfPCell cellTab5 = new PdfPCell(new Phrase("P.U",boldFontTitleTable));
            cellTab5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTab5.setPaddingBottom(5);
            table4.addCell(cellTab5);
            PdfPCell cellTab6 = new PdfPCell(new Phrase("P.Total",boldFontTitleTable));
            cellTab6.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTab6.setPaddingBottom(5);
            table4.addCell(cellTab6);
            table4.completeRow();

            for (FactureUtil f : allFactureUtils) {

                PdfPCell cellInterTab1 = new PdfPCell(new Phrase(f.getDesignation(),normalFont));
                cellInterTab1.setPaddingBottom(5);
                cellInterTab1.setHorizontalAlignment(Element.ALIGN_LEFT);
                table4.addCell(cellInterTab1);

                PdfPCell cellInterTab2 = new PdfPCell(new Phrase(f.getRef(),normalFont));
                cellInterTab2.setHorizontalAlignment(Element.ALIGN_LEFT);
                table4.addCell(cellInterTab2);

                PdfPCell cellInterTab3 = new PdfPCell(new Phrase(f.getUnite(),normalFont));
                cellInterTab3.setHorizontalAlignment(Element.ALIGN_CENTER);
                table4.addCell(cellInterTab3);

                String qt = String.valueOf(f.getQuantite());

                PdfPCell cellInterTab4 = new PdfPCell(new Phrase(qt,normalFont));
                cellInterTab4.setHorizontalAlignment(Element.ALIGN_CENTER);
                table4.addCell(cellInterTab4);

                PdfPCell cellInterTab5 = new PdfPCell(new Phrase(f.getPu(),normalFont));
                cellInterTab5.setHorizontalAlignment(Element.ALIGN_CENTER);
                table4.addCell(cellInterTab5);

                PdfPCell cellInterTab6 = new PdfPCell(new Phrase(f.getPrixtotal(),normalFont));
                cellInterTab6.setHorizontalAlignment(Element.ALIGN_CENTER);
                table4.addCell(cellInterTab6);

            }

            table4.completeRow();

            document.add(table4);

            //Espace
            //document.add(new Paragraph("     "));
            PdfPTable table5 = new PdfPTable(6);
            table5.setSpacingBefore(10f);
            float[] columnWidthsTabP = new float[]{125f, 125f, 25f, 25f,100f, 100f};
            table5.setWidths(columnWidthsTabP);
            table5.setWidthPercentage(100);

            PdfPCell cellTabBas1 = new PdfPCell();
            Paragraph paragraphTabBas1 = new Paragraph(" ");
            cellTabBas1.addElement(paragraphTabBas1);
            cellTabBas1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas1.setBorder(Rectangle.NO_BORDER);
            cellTabBas1.setColspan(3);
            table5.addCell(cellTabBas1);

            PdfPCell cellTabBas2 = new PdfPCell();
            Paragraph paragraphTabBas2 = new Paragraph("TOTAL HT", boldFontTitleTable);
            cellTabBas2.addElement(paragraphTabBas2);
            cellTabBas2.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas2.setBorder(Rectangle.BOX);
            cellTabBas2.setColspan(2);
            table5.addCell(cellTabBas2);

            PdfPCell cellTabBas3;
            cellTabBas3 = new PdfPCell(new Phrase(totalHT,normalFont));
            cellTabBas3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTabBas3.setBorder(Rectangle.BOX);
            table5.addCell(cellTabBas3);

            PdfPCell cellTabBas4 = new PdfPCell();
            Paragraph paragraphTabBas4 = new Paragraph(" ");
            cellTabBas4.addElement(paragraphTabBas4);
            cellTabBas4.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas4.setBorder(Rectangle.NO_BORDER);
            cellTabBas4.setColspan(3);
            table5.addCell(cellTabBas4);

            PdfPCell cellTabBas5 = new PdfPCell();
            Paragraph paragraphTabBas5 = new Paragraph("TVA 18%", boldFontTitleTable);
            cellTabBas5.addElement(paragraphTabBas5);
            cellTabBas5.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas5.setBorder(Rectangle.BOX);
            cellTabBas5.setColspan(2);
            table5.addCell(cellTabBas5);

            PdfPCell cellTabBas6;
            cellTabBas6 = new PdfPCell(new Phrase(montantTVA,normalFont));
            cellTabBas6.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTabBas6.setBorder(Rectangle.BOX);
            table5.addCell(cellTabBas6);

            PdfPCell cellTabBas7 = new PdfPCell();
            Paragraph paragraphTabBas7 = new Paragraph(" ");
            cellTabBas7.addElement(paragraphTabBas7);
            cellTabBas7.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas7.setBorder(Rectangle.NO_BORDER);
            cellTabBas7.setColspan(3);
            table5.addCell(cellTabBas7);

            PdfPCell cellTabBas8 = new PdfPCell();
            Paragraph paragraphTabBas8 = new Paragraph("TOTAL TTC", boldFontTitleTable);
            cellTabBas8.addElement(paragraphTabBas8);
            cellTabBas8.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas8.setBorder(Rectangle.BOX);
            cellTabBas8.setColspan(2);
            table5.addCell(cellTabBas8);

            PdfPCell cellTabBas9;
            cellTabBas9 = new PdfPCell(new Phrase(totalTTC,normalFont));
            cellTabBas9.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTabBas9.setBorder(Rectangle.BOX);
            table5.addCell(cellTabBas9);

            document.add(table5);

            //Espace
            document.add(new Paragraph("     "));

            //Espace
            document.add(new Paragraph("PROFORMA ARRETE A LA SOMME DE : ", normalFontCG));
            if (devis.getMontantlettre().equals("")) {

            } else {
                //Espace
                document.add(new Paragraph(devis.getMontantlettre()+" "+devis.getIdDevise().getSymbole(), normalFontCG));
            }

            //Espace
            document.add(new Paragraph("     "));

            Chunk CBas1 = new Chunk("CONDITIONS GÉNÉRALES", boldFontCG);
            CBas1.setUnderline(0.1f, -2f);
            Phrase pBas1 = new Phrase();
            pBas1.add(CBas1);
            Paragraph paragarphBas1 = new Paragraph();
            paragarphBas1.add(pBas1);
            document.add(paragarphBas1);

            //Espace
            document.add(new Paragraph("     "));

            //Espace
            if (devis.getTerme().equals("")) {

            } else {
                document.add(new Paragraph("TERMES DU CONTRAT: " + devis.getTerme(), normalFontCG));
            }

            //Espace
            GenerationCodePdf acronim = new GenerationCodePdf();
            String acrovalue = "";
            if (devis.getIdUtilisateur() != null) {
                acrovalue = acronim.genererInitiales(devis.getIdUtilisateur().getNomUtilisateur(), devis.getIdUtilisateur().getPrenomUtilisateur());
            }

            if (acrovalue.equals("")) {

            } else {
                document.add(new Paragraph("DOSSIER SUIVI PAR: " + acrovalue, normalFontCG));
            }

            //Espace
            if (devis.getNbjours().equals("")) {

            } else {
                document.add(new Paragraph("DISPONIBILITE: " + devis.getNbjours(), normalFontCG));
            }

            //Espace
            document.add(new Paragraph("     "));

            document.add(new Paragraph("1. Toutes marchandises livrées ou services rendus demeurent la propriété de", normalFontCG));
            document.add(new Paragraph("   ACCEL TECHNOLOGIES jusqu'au paiement intégral de la facture.", normalFontCG));
            document.add(new Paragraph("2. Veuillez mettre le chèque à l'ordre de: ACCEL TECHNOLOGIES", normalFontCG));

            //Espace
            document.add(new Paragraph("     "));

            // A table with 3 columns
            PdfPTable tableBas3 = new PdfPTable(4);
            tableBas3.setSpacingBefore(10f);
            tableBas3.setWidthPercentage(100);

            // the cell object
            PdfPCell cell37;
            Chunk underline = new Chunk("Directeur général", boldFontTitleTable);
            underline.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            cell37 = new PdfPCell(new Phrase(underline));
            cell37.setBorder(0);
            cell37.setColspan(4);
            cell37.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell37.setVerticalAlignment(Element.ALIGN_RIGHT);
            tableBas3.addCell(cell37);
            document.add(tableBas3);

            // POUR POUVOIR GENERER LE DOCUMENT
            document.close();
            file.close();
            System.out.println("PDF Created!");

            // setting some response headers
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control",
                    "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            // setting the content type
            response.setContentType("application/pdf");
            // the contentlength
            response.setContentLength(baos.size());
            // write ByteArrayOutputStream to the ServletOutputStream
            OutputStream os = response.getOutputStream();
            baos.writeTo(os);
            os.flush();
            os.close();

            context.responseComplete();

        } catch (Exception e) {

        }

    }

    public void GenererFacture1(List<FactureUtil> allFactureUtils, Newfacture newfacture, String totalHT, String montantTVA, String totalTTC) throws DocumentException {

        File directory = new File("./");
        String urlAbsolut = directory.getAbsolutePath();
        String url = "";

        try {
            String recuname = "Facture.pdf";
            url = urlAbsolut + recuname;

            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "inline=filename=file.pdf");

            Document document = new Document();
            document.setPageSize(PageSize.A4);
            OutputStream file = new FileOutputStream(new File(url));
            // step 2
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            PdfWriter.getInstance(document, file);

            // Add Header and Footer
//            PdfWriter writer = PdfWriter.getInstance(document, baos);
//            HeaderFooterPageEvent event = new HeaderFooterPageEvent();
//            writer.setPageEvent(event);
            // step 3
            document.open();

            Font boldFontTitle = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
            Font boldFontTitleTable = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
            Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD);
            Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
            Font normalFontEmail = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLUE);
            Font boldFontTitleExp1 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.RED);
            Font boldFontTitleExp2 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
            Font myFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLUE);

            String img = entetePath.getUrlAccel();

            Image logoProjet = Image.getInstance(img);
            logoProjet.scaleAbsolute(60f, 40f);
            logoProjet.setAlignment(Image.ALIGN_LEFT);
            document.add(logoProjet);

            Paragraph ph = new Paragraph(new Phrase(" ", myFont));
            PdfPCell cell = new PdfPCell(ph);
            cell.setBorder(Rectangle.BOTTOM);
            cell.setBorderColor(new BaseColor(44, 67, 144));
            cell.setBorderWidth(2f);

            PdfPTable table = new PdfPTable(1);
            table.addCell(cell);
            table.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.setWidthPercentage(100);
            document.add(table);

            //Espace
            // Creating text object       
            /*Text text1 = new Text("ACCEL");
             Text text2 = new Text("ACCEL TECHNOLOGIES");
            
             Font boldFontTitleSpecial1 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
             boldFontTitleSpecial1.setColor(BaseColor.RED);*/
            // A table1 with 4 columns
            PdfPTable table1 = new PdfPTable(4);
            table1.setSpacingBefore(2f);
            table1.setWidthPercentage(100);
            PdfPCell cell1 = new PdfPCell();

            Chunk c1 = new Chunk("ACCEL ", boldFontTitleExp1);
            Chunk c2 = new Chunk("TECHNOLOGIES", boldFontTitleExp2);
            Phrase p1 = new Phrase();
            p1.add(c1);
            p1.add(c2);
            Paragraph p = new Paragraph();
            p.add(p1);
//            document.add(p);
            //Espace
//            document.add(new Paragraph("", normalFont));
//            //Espace
//            document.add(new Paragraph("Dakar, Sénégal", normalFont));

            Paragraph paragraphSp1 = new Paragraph("4 Route de l’aéroport", normalFont);
            Paragraph paragraphSp2 = new Paragraph("Dakar, Sénégal", normalFont);
            Paragraph paragraph1 = new Paragraph("Tél : 33 820 83 83", normalFont);

            Chunk underlineEmail = new Chunk("Email : info@accel-tech.net", normalFontEmail);
            underlineEmail.setUnderline(0.1f, -2f);
            Paragraph paragraph2 = new Paragraph(underlineEmail);

            cell1.addElement(p);
            cell1.addElement(paragraphSp1);
            cell1.addElement(paragraphSp2);
            cell1.addElement(paragraph1);
            cell1.addElement(paragraph2);
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell1.setVerticalAlignment(Element.ALIGN_LEFT);
            cell1.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell1);

            PdfPCell cell2;
            cell2 = new PdfPCell(new Phrase(" "));
            cell2.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell2);

            PdfPCell cell3;
            cell3 = new PdfPCell(new Phrase(" "));
            cell3.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell3);

            // the cell object
            PdfPCell cell4 = new PdfPCell();
            Paragraph paragraph4 = new Paragraph("CLIENT", boldFont);
            Paragraph paragraph5 = new Paragraph(newfacture.getIdDevis().getIdClient().getNomClient(), boldFont);
            Paragraph paragraphBs1 = new Paragraph(newfacture.getIdDevis().getIdClient().getAdresse(), boldFont);
            Paragraph paragraphBs2 = new Paragraph(newfacture.getIdDevis().getIdClient().getTelephoneClient(), boldFont);
            Paragraph paragraphBs3 = new Paragraph(newfacture.getIdDevis().getIdClient().getMailClient(), boldFont);
            cell4.addElement(paragraph4);
            cell4.addElement(paragraph5);
            cell4.addElement(paragraphBs1);
            cell4.addElement(paragraphBs2);
            cell4.addElement(paragraphBs3);
            cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell4.setVerticalAlignment(Element.ALIGN_LEFT);
            cell4.setBorder(Rectangle.BOX);
            table1.addCell(cell4);

            // Defiles the relative width of the columns
            float[] columnWidths = new float[]{20f, 10f, 20f, 30f};
            table1.setWidths(columnWidths);
            document.add(table1);

            //Espace
            document.add(new Paragraph("  "));

            // A table1 with 4 columns
            PdfPTable table2 = new PdfPTable(4);
            table2.setSpacingBefore(10f);
            table2.setWidthPercentage(100);
            // the cell object
            PdfPCell cell5;
            // we add a cell with colspan 4
            cell5 = new PdfPCell(new Phrase("FACTURE", boldFontTitle));
            cell5.setColspan(4);

            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
            //border
            cell5.setBorder(0);
            table2.addCell(cell5);
            document.add(table2);

            // A table1 with 4 columns
            PdfPTable table3 = new PdfPTable(4);
            table3.setSpacingBefore(10f);
            table3.setWidthPercentage(100);
            PdfPCell cell6 = new PdfPCell();
            Chunk underline1 = new Chunk("DATE : " + newfacture.getDateNewfacture(), normalFont);
            underline1.setUnderline(0.1f, -2f);
            cell6.addElement(underline1);
            cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell6.setVerticalAlignment(Element.ALIGN_LEFT);
            cell6.setBorder(Rectangle.NO_BORDER);
            table3.addCell(cell6);

            PdfPCell cell7;
            cell7 = new PdfPCell(new Phrase(" "));
            cell7.setBorder(Rectangle.NO_BORDER);
            table3.addCell(cell7);

            PdfPCell cell8;
            cell8 = new PdfPCell(new Phrase(" "));
            cell8.setBorder(Rectangle.NO_BORDER);
            table3.addCell(cell8);

            // the cell object
            PdfPCell cell9 = new PdfPCell();
            Chunk underline2 = new Chunk("FACTURE NUMERO", boldFont);
            underline2.setUnderline(0.1f, -2f);
            Paragraph paragraph8 = new Paragraph(newfacture.getNumeroNewfacture(), boldFont);
            cell9.addElement(underline2);
            cell9.addElement(paragraph8);
            cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell9.setVerticalAlignment(Element.ALIGN_LEFT);
            cell9.setBorder(Rectangle.NO_BORDER);
            table3.addCell(cell9);

            // Defiles the relative width of the columns
            float[] columnWidthss = new float[]{20f, 10f, 20f, 30f};
            table3.setWidths(columnWidthss);
            document.add(table3);

            //Espace
            document.add(new Paragraph("     "));

            // A table1 with 4 columns
            PdfPTable tableDevis = new PdfPTable(4);
            tableDevis.setSpacingBefore(10f);
            tableDevis.setWidthPercentage(100);
            PdfPCell cell10 = new PdfPCell();
            Chunk underline3 = new Chunk("REFERENCE", boldFont);
            underline3.setUnderline(0.1f, -2f);
            Paragraph paragraphExp = new Paragraph(newfacture.getIdDevis().getNommarche(), boldFont);
            cell10.addElement(underline3);
            cell10.addElement(paragraphExp);
            cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell10.setVerticalAlignment(Element.ALIGN_LEFT);
            cell10.setBorder(Rectangle.NO_BORDER);
            cell10.setColspan(2);
            tableDevis.addCell(cell10);

            PdfPCell cell11;
            cell11 = new PdfPCell(new Phrase(newfacture.getIdMarche().getIdDevise().getSymbole(), boldFont));
            cell11.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell11.setVerticalAlignment(Element.ALIGN_RIGHT);
            cell11.setBorder(Rectangle.NO_BORDER);
            cell11.setColspan(2);
            tableDevis.addCell(cell11);

            document.add(tableDevis);

            //Espace
            document.add(new Paragraph("     "));

            PdfPTable table4 = new PdfPTable(6);
            table4.setSpacingBefore(10f);
            table4.setWidthPercentage(100);

            table4.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);

            PdfPCell cellT1 = new PdfPCell();
            Paragraph paragraph9 = new Paragraph("Désignation", boldFontTitleTable);
            cellT1.addElement(paragraph9);
            table4.addCell(cellT1);

            PdfPCell cellT2 = new PdfPCell();
            Paragraph paragraph10 = new Paragraph("Ref", boldFontTitleTable);
            cellT2.addElement(paragraph10);
            table4.addCell(cellT2);

            PdfPCell cellT3 = new PdfPCell();
            Paragraph paragraph11 = new Paragraph("U.mesure", boldFontTitleTable);
            cellT3.addElement(paragraph11);
            table4.addCell(cellT3);

            PdfPCell cellT4 = new PdfPCell();
            Paragraph paragraph12 = new Paragraph("Qte", boldFontTitleTable);
            cellT4.addElement(paragraph12);
            table4.addCell(cellT4);

            PdfPCell cellT5 = new PdfPCell();
            Paragraph paragraph13 = new Paragraph("P.U", boldFontTitleTable);
            cellT5.addElement(paragraph13);
            table4.addCell(cellT5);

            PdfPCell cellT6 = new PdfPCell();
            Paragraph paragraph14 = new Paragraph("P.Total", boldFontTitleTable);
            cellT6.addElement(paragraph14);
            table4.addCell(cellT6);

            table4.setHeaderRows(1);
            PdfPCell[] cells = table4.getRow(0).getCells();
            for (int j = 0; j < cells.length; j++) {
                cells[j].setBackgroundColor(BaseColor.WHITE);
            }

            for (FactureUtil f : allFactureUtils) {

                table4.addCell(f.getDesignation());
                table4.addCell(f.getRef());
                table4.addCell(f.getUnite());
                String qt = String.valueOf(f.getQuantite());
                table4.addCell(qt);
                table4.addCell(f.getPu());
                table4.addCell(f.getPrixtotal());
            }

            document.add(table4);

            //Espace
            document.add(new Paragraph("     "));

            PdfPTable table5 = new PdfPTable(6);
            table5.setSpacingBefore(10f);
            table5.setWidthPercentage(100);

            PdfPCell cellTabBas1 = new PdfPCell();
            Paragraph paragraphTabBas1 = new Paragraph(" ");
            cellTabBas1.addElement(paragraphTabBas1);
            cellTabBas1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas1.setVerticalAlignment(Element.ALIGN_LEFT);
            cellTabBas1.setBorder(Rectangle.NO_BORDER);
            cellTabBas1.setColspan(3);
            table5.addCell(cellTabBas1);

            PdfPCell cellTabBas2 = new PdfPCell();
            Paragraph paragraphTabBas2 = new Paragraph("TOTAL HT", boldFontTitleTable);
            cellTabBas2.addElement(paragraphTabBas2);
            cellTabBas2.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas2.setVerticalAlignment(Element.ALIGN_LEFT);
            cellTabBas2.setBorder(Rectangle.BOX);
            cellTabBas2.setColspan(2);
            table5.addCell(cellTabBas2);

            PdfPCell cellTabBas3;
            cellTabBas3 = new PdfPCell(new Phrase(totalHT));
            cellTabBas3.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellTabBas3.setVerticalAlignment(Element.ALIGN_RIGHT);
            cellTabBas3.setBorder(Rectangle.BOX);
            table5.addCell(cellTabBas3);

            PdfPCell cellTabBas4 = new PdfPCell();
            Paragraph paragraphTabBas4 = new Paragraph(" ");
            cellTabBas4.addElement(paragraphTabBas4);
            cellTabBas4.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas4.setVerticalAlignment(Element.ALIGN_LEFT);
            cellTabBas4.setBorder(Rectangle.NO_BORDER);
            cellTabBas4.setColspan(3);
            table5.addCell(cellTabBas4);

            PdfPCell cellTabBas5 = new PdfPCell();
            Paragraph paragraphTabBas5 = new Paragraph("TVA 18%", boldFontTitleTable);
            cellTabBas5.addElement(paragraphTabBas5);
            cellTabBas5.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas5.setVerticalAlignment(Element.ALIGN_LEFT);
            cellTabBas5.setBorder(Rectangle.BOX);
            cellTabBas5.setColspan(2);
            table5.addCell(cellTabBas5);

            PdfPCell cellTabBas6;
            cellTabBas6 = new PdfPCell(new Phrase(montantTVA));
            cellTabBas6.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellTabBas6.setVerticalAlignment(Element.ALIGN_RIGHT);
            cellTabBas6.setBorder(Rectangle.BOX);
            table5.addCell(cellTabBas6);

            PdfPCell cellTabBas7 = new PdfPCell();
            Paragraph paragraphTabBas7 = new Paragraph(" ");
            cellTabBas7.addElement(paragraphTabBas7);
            cellTabBas7.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas7.setVerticalAlignment(Element.ALIGN_LEFT);
            cellTabBas7.setBorder(Rectangle.NO_BORDER);
            cellTabBas7.setColspan(3);
            table5.addCell(cellTabBas7);

            PdfPCell cellTabBas8 = new PdfPCell();
            Paragraph paragraphTabBas8 = new Paragraph("TOTAL TTC", boldFontTitleTable);
            cellTabBas8.addElement(paragraphTabBas8);
            cellTabBas8.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas8.setVerticalAlignment(Element.ALIGN_LEFT);
            cellTabBas8.setBorder(Rectangle.BOX);
            cellTabBas8.setColspan(2);
            table5.addCell(cellTabBas8);

            PdfPCell cellTabBas9;
            cellTabBas9 = new PdfPCell(new Phrase(totalTTC));
            cellTabBas9.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellTabBas9.setVerticalAlignment(Element.ALIGN_RIGHT);
            cellTabBas9.setBorder(Rectangle.BOX);
            table5.addCell(cellTabBas9);

            document.add(table5);

            //Espace
            document.add(new Paragraph("     "));

            //Espace
            document.add(new Paragraph("FACTURE ARRETEE A LA SOMME DE : ", normalFontEmail));
            //Espace
            document.add(new Paragraph(newfacture.getMintantlettreNewfacture(), normalFontEmail));

            //Espace
            document.add(new Paragraph("     "));
            //Espace
            document.add(new Paragraph("     "));

            // A table with 3 columns
            PdfPTable tableBas3 = new PdfPTable(4);
            tableBas3.setSpacingBefore(10f);
            tableBas3.setWidthPercentage(100);

            // the cell object
            PdfPCell cell37;
            Chunk underline = new Chunk("Directeur général", normalFont);
            underline.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            cell37 = new PdfPCell(new Phrase(underline));
            cell37.setBorder(0);
            cell37.setColspan(4);
            cell37.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell37.setVerticalAlignment(Element.ALIGN_RIGHT);
            tableBas3.addCell(cell37);
            document.add(tableBas3);

            //Espace
            document.add(new Paragraph("  "));

            // POUR POUVOIR GENERER LE DOCUMENT
            document.close();
            file.close();
            System.out.println("PDF Created!");

            // setting some response headers
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control",
                    "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            // setting the content type
            response.setContentType("application/pdf");
            // the contentlength
            response.setContentLength(baos.size());
            // write ByteArrayOutputStream to the ServletOutputStream
            OutputStream os = response.getOutputStream();
            baos.writeTo(os);
            os.flush();
            os.close();

            context.responseComplete();

        } catch (Exception e) {

        }

    }

    public void GenererFacture2(List<FactureUtil> allFactureUtils, Newfacture newfacture, String totalHT, String montantTVA, String totalTTC) throws DocumentException {

        File directory = new File("./");
        String urlAbsolut = directory.getAbsolutePath();
        String url = "";

        try {
            String recuname = "Facture_chantier.pdf";
            url = urlAbsolut + recuname;

            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "inline=filename=file.pdf");

            Document document = new Document();
            document.setPageSize(PageSize.A4);
            OutputStream file = new FileOutputStream(new File(url));
            // step 2
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            PdfWriter.getInstance(document, file);

            // Add Header and Footer
//            PdfWriter writer = PdfWriter.getInstance(document, baos);
//            HeaderFooterPageEvent event = new HeaderFooterPageEvent();
//            writer.setPageEvent(event);
            // step 3
            document.open();

            Font boldFontTitle = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
            Font boldFontTitleTable = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
            Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD);
            Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
            Font normalFontEmail = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLUE);
            Font boldFontTitleExp1 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.RED);
            Font boldFontTitleExp2 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
            Font myFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLUE);

            String img = entetePath.getUrlAccel();

            Image logoProjet = Image.getInstance(img);
            logoProjet.scaleAbsolute(60f, 40f);
            logoProjet.setAlignment(Image.ALIGN_LEFT);
            document.add(logoProjet);

            Paragraph ph = new Paragraph(new Phrase(" ", myFont));
            PdfPCell cell = new PdfPCell(ph);
            cell.setBorder(Rectangle.BOTTOM);
            cell.setBorderColor(new BaseColor(44, 67, 144));
            cell.setBorderWidth(2f);

            PdfPTable table = new PdfPTable(1);
            table.addCell(cell);
            table.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.setWidthPercentage(100);
            document.add(table);

            //Espace
            // Creating text object       
            /*Text text1 = new Text("ACCEL");
             Text text2 = new Text("ACCEL TECHNOLOGIES");
            
             Font boldFontTitleSpecial1 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
             boldFontTitleSpecial1.setColor(BaseColor.RED);*/
            // A table1 with 4 columns
            PdfPTable table1 = new PdfPTable(4);
            table1.setSpacingBefore(2f);
            table1.setWidthPercentage(100);
            PdfPCell cell1 = new PdfPCell();

            Chunk c1 = new Chunk("ACCEL ", boldFontTitleExp1);
            Chunk c2 = new Chunk("TECHNOLOGIES", boldFontTitleExp2);
            Phrase p1 = new Phrase();
            p1.add(c1);
            p1.add(c2);
            Paragraph p = new Paragraph();
            p.add(p1);
//            document.add(p);
            //Espace
//            document.add(new Paragraph("", normalFont));
//            //Espace
//            document.add(new Paragraph("Dakar, Sénégal", normalFont));

            Paragraph paragraphSp1 = new Paragraph("4 Route de l’aéroport", normalFont);
            Paragraph paragraphSp2 = new Paragraph("Dakar, Sénégal", normalFont);
            Paragraph paragraph1 = new Paragraph("Tél : 33 820 83 83", normalFont);

            Chunk underlineEmail = new Chunk("Email : info@accel-tech.net", normalFontEmail);
            underlineEmail.setUnderline(0.1f, -2f);
            Paragraph paragraph2 = new Paragraph(underlineEmail);

            cell1.addElement(p);
            cell1.addElement(paragraphSp1);
            cell1.addElement(paragraphSp2);
            cell1.addElement(paragraph1);
            cell1.addElement(paragraph2);
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell1.setVerticalAlignment(Element.ALIGN_LEFT);
            cell1.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell1);

            PdfPCell cell2;
            cell2 = new PdfPCell(new Phrase(" "));
            cell2.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell2);

            PdfPCell cell3;
            cell3 = new PdfPCell(new Phrase(" "));
            cell3.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell3);

            // the cell object
            PdfPCell cell4 = new PdfPCell();
            Paragraph paragraph4 = new Paragraph("CLIENT", boldFont);
            Paragraph paragraph5 = new Paragraph(newfacture.getIdDevis().getIdClient().getNomClient(), boldFont);
            Paragraph paragraphBs1 = new Paragraph(newfacture.getIdDevis().getIdClient().getAdresse(), boldFont);
            Paragraph paragraphBs2 = new Paragraph(newfacture.getIdDevis().getIdClient().getTelephoneClient(), boldFont);
            Paragraph paragraphBs3 = new Paragraph(newfacture.getIdDevis().getIdClient().getMailClient(), boldFont);
            cell4.addElement(paragraph4);
            cell4.addElement(paragraph5);
            cell4.addElement(paragraphBs1);
            cell4.addElement(paragraphBs2);
            cell4.addElement(paragraphBs3);
            cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell4.setVerticalAlignment(Element.ALIGN_LEFT);
            cell4.setBorder(Rectangle.BOX);
            table1.addCell(cell4);

            // Defiles the relative width of the columns
            float[] columnWidths = new float[]{20f, 10f, 20f, 30f};
            table1.setWidths(columnWidths);
            document.add(table1);

            //Espace
            document.add(new Paragraph("  "));

            // A table1 with 4 columns
            PdfPTable table2 = new PdfPTable(4);
            table2.setSpacingBefore(10f);
            table2.setWidthPercentage(100);
            // the cell object
            PdfPCell cell5;
            // we add a cell with colspan 4
            cell5 = new PdfPCell(new Phrase("FACTURE", boldFontTitle));
            cell5.setColspan(4);

            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
            //border
            cell5.setBorder(0);
            table2.addCell(cell5);
            document.add(table2);

            // A table1 with 4 columns
            PdfPTable table3 = new PdfPTable(4);
            table3.setSpacingBefore(10f);
            table3.setWidthPercentage(100);
            PdfPCell cell6 = new PdfPCell();
            Chunk underline1 = new Chunk("DATE : " + newfacture.getDateNewfacture(), normalFont);
            underline1.setUnderline(0.1f, -2f);
            cell6.addElement(underline1);
            cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell6.setVerticalAlignment(Element.ALIGN_LEFT);
            cell6.setBorder(Rectangle.NO_BORDER);
            table3.addCell(cell6);

            PdfPCell cell7;
            cell7 = new PdfPCell(new Phrase(" "));
            cell7.setBorder(Rectangle.NO_BORDER);
            table3.addCell(cell7);

            PdfPCell cell8;
            cell8 = new PdfPCell(new Phrase(" "));
            cell8.setBorder(Rectangle.NO_BORDER);
            table3.addCell(cell8);

            // the cell object
            PdfPCell cell9 = new PdfPCell();
            Chunk underline2 = new Chunk("FACTURE NUMERO", boldFont);
            underline2.setUnderline(0.1f, -2f);
            Paragraph paragraph8 = new Paragraph("N°" + newfacture.getIdDevis().getNumeroDevis(), boldFont);
            cell9.addElement(underline2);
            cell9.addElement(paragraph8);
            cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell9.setVerticalAlignment(Element.ALIGN_LEFT);
            cell9.setBorder(Rectangle.NO_BORDER);
            table3.addCell(cell9);

            // Defiles the relative width of the columns
            float[] columnWidthss = new float[]{20f, 10f, 20f, 30f};
            table3.setWidths(columnWidthss);
            document.add(table3);

            //Espace
            document.add(new Paragraph("     "));

            // A table1 with 4 columns
            PdfPTable tableDevis = new PdfPTable(4);
            tableDevis.setSpacingBefore(10f);
            tableDevis.setWidthPercentage(100);
            PdfPCell cell10 = new PdfPCell();
            Chunk underline3 = new Chunk("PROJET", boldFont);
            underline3.setUnderline(0.1f, -2f);
            Paragraph paragraphExp = new Paragraph(newfacture.getIdDevis().getNommarche(), boldFont);
            cell10.addElement(underline3);
            cell10.addElement(paragraphExp);
            cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell10.setVerticalAlignment(Element.ALIGN_LEFT);
            cell10.setBorder(Rectangle.NO_BORDER);
            cell10.setColspan(2);
            tableDevis.addCell(cell10);

            PdfPCell cell11;
            cell11 = new PdfPCell(new Phrase(newfacture.getIdMarche().getIdDevise().getSymbole(), boldFont));
            cell11.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell11.setVerticalAlignment(Element.ALIGN_RIGHT);
            cell11.setBorder(Rectangle.NO_BORDER);
            cell11.setColspan(2);
            tableDevis.addCell(cell11);

            document.add(tableDevis);

            //Espace
            document.add(new Paragraph("     "));

            PdfPTable table4 = new PdfPTable(6);
            table4.setSpacingBefore(10f);
            table4.setWidthPercentage(100);

            table4.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);

            PdfPCell cellT1 = new PdfPCell();
            cellT1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellT1.setVerticalAlignment(Element.ALIGN_LEFT);
            Paragraph paragraph9 = new Paragraph("Désignation", boldFontTitleTable);
            cellT1.addElement(paragraph9);
            table4.addCell(cellT1);

            PdfPCell cellT2 = new PdfPCell();
            cellT2.setHorizontalAlignment(Element.ALIGN_CENTER);
            Paragraph paragraph10 = new Paragraph("Ref", boldFontTitleTable);
            cellT2.addElement(paragraph10);
            table4.addCell(cellT2);

            PdfPCell cellT3 = new PdfPCell();
            cellT3.setHorizontalAlignment(Element.ALIGN_CENTER);
            Paragraph paragraph11 = new Paragraph("U.mesure", boldFontTitleTable);
            cellT3.addElement(paragraph11);
            table4.addCell(cellT3);

            PdfPCell cellT4 = new PdfPCell();
            cellT4.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellT4.setVerticalAlignment(Element.ALIGN_MIDDLE);
            Paragraph paragraph12 = new Paragraph("Qte", boldFontTitleTable);
            cellT4.addElement(paragraph12);
            table4.addCell(cellT4);

            PdfPCell cellT5 = new PdfPCell();
            cellT5.setHorizontalAlignment(Element.ALIGN_CENTER);
            Paragraph paragraph13 = new Paragraph("Qte livrée", boldFontTitleTable);
            cellT5.addElement(paragraph13);
            table4.addCell(cellT5);

            PdfPCell cellT6 = new PdfPCell();
            cellT6.setHorizontalAlignment(Element.ALIGN_CENTER);
            Paragraph paragraph14 = new Paragraph("% Execution", boldFontTitleTable);
            cellT6.addElement(paragraph14);
            table4.addCell(cellT6);

            table4.setHeaderRows(1);
            PdfPCell[] cells = table4.getRow(0).getCells();
            for (int j = 0; j < cells.length; j++) {
                cells[j].setBackgroundColor(BaseColor.WHITE);
            }

            for (FactureUtil f : allFactureUtils) {

                table4.addCell(f.getDesignation());
                table4.addCell(f.getRef());
                table4.addCell(f.getUnite());
                String qt = String.valueOf(f.getQuantite());
                table4.addCell(qt);
                table4.addCell("");
                table4.addCell("");
            }

            document.add(table4);

            //Espace
            document.add(new Paragraph("     "));

            //Espace
            document.add(new Paragraph("     "));

            // A table with 3 columns
            PdfPTable tableBas3 = new PdfPTable(4);
            tableBas3.setSpacingBefore(10f);
            tableBas3.setWidthPercentage(100);

            // the cell object
            PdfPCell cell37;
            Chunk underline = new Chunk("Chef de projet", normalFont);
            underline.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            cell37 = new PdfPCell(new Phrase(underline));
            cell37.setBorder(0);
            cell37.setColspan(4);
            cell37.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell37.setVerticalAlignment(Element.ALIGN_RIGHT);
            tableBas3.addCell(cell37);
            document.add(tableBas3);

            //Espace
            document.add(new Paragraph("  "));
            //Espace
            document.add(new Paragraph("  "));

            // POUR POUVOIR GENERER LE DOCUMENT
            document.close();
            file.close();
            System.out.println("PDF Created!");

            // setting some response headers
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control",
                    "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            // setting the content type
            response.setContentType("application/pdf");
            // the contentlength
            response.setContentLength(baos.size());
            // write ByteArrayOutputStream to the ServletOutputStream
            OutputStream os = response.getOutputStream();
            baos.writeTo(os);
            os.flush();
            os.close();

            context.responseComplete();

        } catch (Exception e) {

        }

    }

    public void GenererDecompte(List<FactureUtil> allFactureUtils, Decomptep decomptep, String totalHT, String montantTVA, String totalTTC, String valeurXPourcent, String avancementHt, String avancementTVA, String avancementTTC, String avanceRecu, String reliquantDemande) throws DocumentException {

        File directory = new File("./");
        String urlAbsolut = directory.getAbsolutePath();
        String url = "";

        try {
            String recuname = "Decompte.pdf";
            url = urlAbsolut + recuname;

            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "inline=filename=file.pdf");

            Document document = new Document();
            document.setPageSize(PageSize.A4);
            OutputStream file = new FileOutputStream(new File(url));
            // step 2
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            PdfWriter.getInstance(document, file);

            // Add Header and Footer
//            PdfWriter writer = PdfWriter.getInstance(document, baos);
//            HeaderFooterPageEvent event = new HeaderFooterPageEvent();
//            writer.setPageEvent(event);
            // step 3
            document.open();

            Font boldFontTitle = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
            Font boldFontTitleTable = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
            Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD);
            Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
            Font normalFontP = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL);
            Font normalFontEmail = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLUE);
            Font boldFontTitleExp1 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.RED);
            Font boldFontTitleExp2 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
            Font myFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLUE);

            String img = entetePath.getUrlAccel();

            Image logoProjet = Image.getInstance(img);
            logoProjet.scaleAbsolute(60f, 40f);
            logoProjet.setAlignment(Image.ALIGN_LEFT);
            document.add(logoProjet);

            Paragraph ph = new Paragraph(new Phrase(" ", myFont));
            PdfPCell cell = new PdfPCell(ph);
            cell.setBorder(Rectangle.BOTTOM);
            cell.setBorderColor(new BaseColor(44, 67, 144));
            cell.setBorderWidth(2f);

            PdfPTable table = new PdfPTable(1);
            table.addCell(cell);
            table.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.setWidthPercentage(100);
            document.add(table);

            //Espace
            // Creating text object       
            /*Text text1 = new Text("ACCEL");
             Text text2 = new Text("ACCEL TECHNOLOGIES");
            
             Font boldFontTitleSpecial1 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
             boldFontTitleSpecial1.setColor(BaseColor.RED);*/
            // A table1 with 4 columns
            PdfPTable table1 = new PdfPTable(4);
            table1.setSpacingBefore(2f);
            table1.setWidthPercentage(100);
            PdfPCell cell1 = new PdfPCell();

            Chunk c1 = new Chunk("ACCEL ", boldFontTitleExp1);
            Chunk c2 = new Chunk("TECHNOLOGIES", boldFontTitleExp2);
            Phrase p1 = new Phrase();
            p1.add(c1);
            p1.add(c2);
            Paragraph p = new Paragraph();
            p.add(p1);
//            document.add(p);
            //Espace
//            document.add(new Paragraph("", normalFont));
//            //Espace
//            document.add(new Paragraph("Dakar, Sénégal", normalFont));

            Paragraph paragraphSp1 = new Paragraph("4 Route de l’aéroport", normalFont);
            Paragraph paragraphSp2 = new Paragraph("Dakar, Sénégal", normalFont);
            Paragraph paragraph1 = new Paragraph("Tél : 33 820 83 83", normalFont);
            Paragraph paragraphElementBas1 = new Paragraph("RCCM: SN DKR 2014 B 21849", normalFontP);
            Paragraph paragraphElementBas2 = new Paragraph("NINEA: 005310652 2Y2", normalFontP);
            Paragraph paragraphElementBas11 = new Paragraph("Banque: BOA 005003810004", normalFontP);
            Paragraph paragraphElementBas22 = new Paragraph("IBAN: SN08 SN10 0010 2200 5003 8100 0418", normalFontP);

            Chunk underlineEmail = new Chunk("Email : info@accel-tech.net", normalFontEmail);
            underlineEmail.setUnderline(0.1f, -2f);
            Paragraph paragraph2 = new Paragraph(underlineEmail);

            cell1.addElement(p);
            cell1.addElement(paragraphSp1);
            cell1.addElement(paragraphSp2);
            cell1.addElement(paragraph1);
            cell1.addElement(paragraph2);
            cell1.addElement(paragraphElementBas1);
            cell1.addElement(paragraphElementBas2);
            cell1.addElement(paragraphElementBas11);
            cell1.addElement(paragraphElementBas22);
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell1.setVerticalAlignment(Element.ALIGN_LEFT);
            cell1.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell1);

            PdfPCell cell2;
            cell2 = new PdfPCell(new Phrase(" "));
            cell2.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell2);

            PdfPCell cell3;
            cell3 = new PdfPCell(new Phrase(" "));
            cell3.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell3);

            // the cell object
            PdfPCell cell4 = new PdfPCell();
            Paragraph paragraph4 = new Paragraph("CLIENT", boldFont);
            Paragraph paragraph5 = new Paragraph(decomptep.getIdNewfacture().getIdDevis().getIdClient().getNomClient(), boldFont);
            Paragraph paragraphBs1 = new Paragraph(decomptep.getIdNewfacture().getIdDevis().getIdClient().getAdresse(), boldFont);
            Paragraph paragraphBs2 = new Paragraph(decomptep.getIdNewfacture().getIdDevis().getIdClient().getTelephoneClient(), boldFont);
            Paragraph paragraphBs3 = new Paragraph(decomptep.getIdNewfacture().getIdDevis().getIdClient().getMailClient(), boldFont);
            Paragraph paragraphBs22 = new Paragraph(" ", boldFont);
            Paragraph paragraphBs33 = new Paragraph(" ", boldFont);
            Paragraph paragraphBs222 = new Paragraph(" ", boldFont);
            Paragraph paragraphBs333 = new Paragraph(" ", boldFont);
            cell4.addElement(paragraph4);
            cell4.addElement(paragraph5);
            cell4.addElement(paragraphBs1);
            cell4.addElement(paragraphBs2);
            cell4.addElement(paragraphBs3);
            cell4.addElement(paragraphBs22);
            cell4.addElement(paragraphBs33);
            cell4.addElement(paragraphBs222);
            cell4.addElement(paragraphBs333);
            cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell4.setVerticalAlignment(Element.ALIGN_LEFT);
            cell4.setBorder(Rectangle.BOX);
            table1.addCell(cell4);

            // Defiles the relative width of the columns
            float[] columnWidths = new float[]{20f, 10f, 20f, 30f};
            table1.setWidths(columnWidths);
            document.add(table1);

            //Espace
            document.add(new Paragraph("  "));

            // A table1 with 4 columns
            PdfPTable table2 = new PdfPTable(4);
            table2.setSpacingBefore(10f);
            table2.setWidthPercentage(100);
            // the cell object
            PdfPCell cell5;
            // we add a cell with colspan 4
            cell5 = new PdfPCell(new Phrase("FACTURE", boldFontTitle));
            cell5.setColspan(4);

            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
            //border
            cell5.setBorder(0);
            table2.addCell(cell5);
            document.add(table2);

            // A table1 with 4 columns
            PdfPTable table3 = new PdfPTable(4);
            table3.setSpacingBefore(10f);
            table3.setWidthPercentage(100);
            PdfPCell cell6 = new PdfPCell();
            Chunk underline1 = new Chunk("DATE : " + decomptep.getDateDecomptep(), normalFont);
            underline1.setUnderline(0.1f, -2f);
            cell6.addElement(underline1);
            cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell6.setVerticalAlignment(Element.ALIGN_LEFT);
            cell6.setBorder(Rectangle.NO_BORDER);
            table3.addCell(cell6);

            PdfPCell cell7;
            cell7 = new PdfPCell(new Phrase(" "));
            cell7.setBorder(Rectangle.NO_BORDER);
            table3.addCell(cell7);

            PdfPCell cell8;
            cell8 = new PdfPCell(new Phrase(" "));
            cell8.setBorder(Rectangle.NO_BORDER);
            table3.addCell(cell8);

            // the cell object
            PdfPCell cell9 = new PdfPCell();
            Chunk underline2 = new Chunk("DECOMPTE NUMERO", boldFont);
            underline2.setUnderline(0.1f, -2f);
            Paragraph paragraph8 = new Paragraph(decomptep.getNumeroDecomptep(), boldFont);
            cell9.addElement(underline2);
            cell9.addElement(paragraph8);
            cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell9.setVerticalAlignment(Element.ALIGN_LEFT);
            cell9.setBorder(Rectangle.NO_BORDER);
            table3.addCell(cell9);

            // Defiles the relative width of the columns
            float[] columnWidthss = new float[]{20f, 10f, 20f, 30f};
            table3.setWidths(columnWidthss);
            document.add(table3);

            //Espace
            document.add(new Paragraph("     "));

            // A table1 with 4 columns
            PdfPTable tableDevis = new PdfPTable(4);
            tableDevis.setSpacingBefore(10f);
            tableDevis.setWidthPercentage(100);
            PdfPCell cell10 = new PdfPCell();
            Chunk underline3 = new Chunk("PROJET", boldFont);
            underline3.setUnderline(0.1f, -2f);
            Paragraph paragraphExp = new Paragraph(decomptep.getIdNewfacture().getIdDevis().getNommarche(), boldFont);
            cell10.addElement(underline3);
            cell10.addElement(paragraphExp);
            cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell10.setVerticalAlignment(Element.ALIGN_LEFT);
            cell10.setBorder(Rectangle.NO_BORDER);
            cell10.setColspan(2);
            tableDevis.addCell(cell10);

            PdfPCell cell11;
            cell11 = new PdfPCell(new Phrase(decomptep.getIdNewfacture().getIdMarche().getIdDevise().getSymbole(), boldFont));
            cell11.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell11.setVerticalAlignment(Element.ALIGN_RIGHT);
            cell11.setBorder(Rectangle.NO_BORDER);
            cell11.setColspan(2);
            tableDevis.addCell(cell11);

            document.add(tableDevis);

            //Espace
            document.add(new Paragraph("     "));

            PdfPTable table4 = new PdfPTable(7);
            table4.setSpacingBefore(10f);
            table4.setWidthPercentage(100);

            table4.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);

            PdfPCell cellT1 = new PdfPCell();
            cellT1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellT1.setVerticalAlignment(Element.ALIGN_LEFT);
            Paragraph paragraph9 = new Paragraph("Désignation", boldFontTitleTable);
            cellT1.addElement(paragraph9);
            table4.addCell(cellT1);

            PdfPCell cellT2 = new PdfPCell();
            cellT2.setHorizontalAlignment(Element.ALIGN_CENTER);
            Paragraph paragraph10 = new Paragraph("Ref", boldFontTitleTable);
            cellT2.addElement(paragraph10);
            table4.addCell(cellT2);

            PdfPCell cellT3 = new PdfPCell();
            cellT3.setHorizontalAlignment(Element.ALIGN_CENTER);
            Paragraph paragraph11 = new Paragraph("U.mesure", boldFontTitleTable);
            cellT3.addElement(paragraph11);
            table4.addCell(cellT3);

            PdfPCell cellT4 = new PdfPCell();
            cellT4.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellT4.setVerticalAlignment(Element.ALIGN_MIDDLE);
            Paragraph paragraph12 = new Paragraph("Qte", boldFontTitleTable);
            cellT4.addElement(paragraph12);
            table4.addCell(cellT4);

            PdfPCell cellT5 = new PdfPCell();
            cellT5.setHorizontalAlignment(Element.ALIGN_CENTER);
            Paragraph paragraph13 = new Paragraph("P.U", boldFontTitleTable);
            cellT5.addElement(paragraph13);
            table4.addCell(cellT5);

            PdfPCell cellT6 = new PdfPCell();
            cellT6.setHorizontalAlignment(Element.ALIGN_CENTER);
            Paragraph paragraph14 = new Paragraph("P.Total", boldFontTitleTable);
            cellT6.addElement(paragraph14);
            table4.addCell(cellT6);

            PdfPCell cellT7 = new PdfPCell();
            cellT7.setHorizontalAlignment(Element.ALIGN_CENTER);
            Paragraph paragraph15 = new Paragraph("% Execution", boldFontTitleTable);
            cellT7.addElement(paragraph15);
            table4.addCell(cellT7);

            table4.setHeaderRows(1);
            PdfPCell[] cells = table4.getRow(0).getCells();
            for (int j = 0; j < cells.length; j++) {
                cells[j].setBackgroundColor(BaseColor.WHITE);
            }

            for (FactureUtil f : allFactureUtils) {

                table4.addCell(f.getDesignation());
                table4.addCell(f.getRef());
                table4.addCell(f.getUnite());
                String qt = String.valueOf(f.getQuantite());
                table4.addCell(qt);
                table4.addCell(f.getPu());
                table4.addCell(f.getPrixtotal());
                String pct = String.valueOf(f.getExecution());
                table4.addCell(pct);
            }

            document.add(table4);

            //Espace
            document.add(new Paragraph("     "));

            PdfPTable table5 = new PdfPTable(6);
            table5.setSpacingBefore(10f);
            table5.setWidthPercentage(100);

            PdfPCell cellTabBas1 = new PdfPCell();
            Paragraph paragraphTabBas1 = new Paragraph(" ");
            cellTabBas1.addElement(paragraphTabBas1);
            cellTabBas1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas1.setVerticalAlignment(Element.ALIGN_LEFT);
            cellTabBas1.setBorder(Rectangle.NO_BORDER);
            cellTabBas1.setColspan(3);
            table5.addCell(cellTabBas1);

            PdfPCell cellTabBas2 = new PdfPCell();
            Paragraph paragraphTabBas2 = new Paragraph("TOTAL HT", boldFontTitleTable);
            cellTabBas2.addElement(paragraphTabBas2);
            cellTabBas2.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas2.setVerticalAlignment(Element.ALIGN_LEFT);
            cellTabBas2.setBorder(Rectangle.BOX);
            cellTabBas2.setColspan(2);
            table5.addCell(cellTabBas2);

            PdfPCell cellTabBas3;
            cellTabBas3 = new PdfPCell(new Phrase(totalHT));
            cellTabBas3.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellTabBas3.setVerticalAlignment(Element.ALIGN_RIGHT);
            cellTabBas3.setBorder(Rectangle.BOX);
            table5.addCell(cellTabBas3);

            PdfPCell cellTabBas4 = new PdfPCell();
            Paragraph paragraphTabBas4 = new Paragraph(" ");
            cellTabBas4.addElement(paragraphTabBas4);
            cellTabBas4.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas4.setVerticalAlignment(Element.ALIGN_LEFT);
            cellTabBas4.setBorder(Rectangle.NO_BORDER);
            cellTabBas4.setColspan(3);
            table5.addCell(cellTabBas4);

            PdfPCell cellTabBas5 = new PdfPCell();
            Paragraph paragraphTabBas5 = new Paragraph("TVA 18%", boldFontTitleTable);
            cellTabBas5.addElement(paragraphTabBas5);
            cellTabBas5.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas5.setVerticalAlignment(Element.ALIGN_LEFT);
            cellTabBas5.setBorder(Rectangle.BOX);
            cellTabBas5.setColspan(2);
            table5.addCell(cellTabBas5);

            PdfPCell cellTabBas6;
            cellTabBas6 = new PdfPCell(new Phrase(montantTVA));
            cellTabBas6.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellTabBas6.setVerticalAlignment(Element.ALIGN_RIGHT);
            cellTabBas6.setBorder(Rectangle.BOX);
            table5.addCell(cellTabBas6);

            PdfPCell cellTabBas7 = new PdfPCell();
            Paragraph paragraphTabBas7 = new Paragraph(" ");
            cellTabBas7.addElement(paragraphTabBas7);
            cellTabBas7.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas7.setVerticalAlignment(Element.ALIGN_LEFT);
            cellTabBas7.setBorder(Rectangle.NO_BORDER);
            cellTabBas7.setColspan(3);
            table5.addCell(cellTabBas7);

            PdfPCell cellTabBas8 = new PdfPCell();
            Paragraph paragraphTabBas8 = new Paragraph("TOTAL TTC", boldFontTitleTable);
            cellTabBas8.addElement(paragraphTabBas8);
            cellTabBas8.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas8.setVerticalAlignment(Element.ALIGN_LEFT);
            cellTabBas8.setBorder(Rectangle.BOX);
            cellTabBas8.setColspan(2);
            table5.addCell(cellTabBas8);

            PdfPCell cellTabBas9;
            cellTabBas9 = new PdfPCell(new Phrase(totalTTC));
            cellTabBas9.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellTabBas9.setVerticalAlignment(Element.ALIGN_RIGHT);
            cellTabBas9.setBorder(Rectangle.BOX);
            table5.addCell(cellTabBas9);

            PdfPCell cellTabBas10 = new PdfPCell();
            Paragraph paragraphTabBas10 = new Paragraph(" ");
            cellTabBas10.addElement(paragraphTabBas10);
            cellTabBas10.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas10.setVerticalAlignment(Element.ALIGN_LEFT);
            cellTabBas10.setBorder(Rectangle.NO_BORDER);
            cellTabBas10.setColspan(3);
            table5.addCell(cellTabBas10);

            PdfPCell cellTabBas11 = new PdfPCell();
            Paragraph paragraphTabBas11 = new Paragraph("AVANCEMENT", boldFontTitleTable);
            cellTabBas11.addElement(paragraphTabBas11);
            cellTabBas11.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas11.setVerticalAlignment(Element.ALIGN_LEFT);
            cellTabBas11.setBorder(Rectangle.BOX);
            cellTabBas11.setColspan(2);
            table5.addCell(cellTabBas11);

            PdfPCell cellTabBas12;
            cellTabBas12 = new PdfPCell(new Phrase(avancementHt));
            cellTabBas12.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellTabBas12.setVerticalAlignment(Element.ALIGN_RIGHT);
            cellTabBas12.setBorder(Rectangle.BOX);
            table5.addCell(cellTabBas12);

            PdfPCell cellTabBas13 = new PdfPCell();
            Paragraph paragraphTabBas13 = new Paragraph(" ");
            cellTabBas13.addElement(paragraphTabBas13);
            cellTabBas13.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas13.setVerticalAlignment(Element.ALIGN_LEFT);
            cellTabBas13.setBorder(Rectangle.NO_BORDER);
            cellTabBas13.setColspan(3);
            table5.addCell(cellTabBas13);

            PdfPCell cellTabBas14 = new PdfPCell();
            Paragraph paragraphTabBas14 = new Paragraph("TVA AVANCEMENT  " + valeurXPourcent + "%", boldFontTitleTable);
            cellTabBas14.addElement(paragraphTabBas14);
            cellTabBas14.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas14.setVerticalAlignment(Element.ALIGN_LEFT);
            cellTabBas14.setBorder(Rectangle.BOX);
            cellTabBas14.setColspan(2);
            table5.addCell(cellTabBas14);

            PdfPCell cellTabBas15;
            cellTabBas15 = new PdfPCell(new Phrase(avancementTVA));
            cellTabBas15.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellTabBas15.setVerticalAlignment(Element.ALIGN_RIGHT);
            cellTabBas15.setBorder(Rectangle.BOX);
            table5.addCell(cellTabBas15);

            PdfPCell cellTabBas16 = new PdfPCell();
            Paragraph paragraphTabBas16 = new Paragraph(" ");
            cellTabBas16.addElement(paragraphTabBas16);
            cellTabBas16.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas16.setVerticalAlignment(Element.ALIGN_LEFT);
            cellTabBas16.setBorder(Rectangle.NO_BORDER);
            cellTabBas16.setColspan(3);
            table5.addCell(cellTabBas16);

            PdfPCell cellTabBas17 = new PdfPCell();
            Paragraph paragraphTabBas17 = new Paragraph("AVANCEMENT  " + valeurXPourcent + "% TTC ", boldFontTitleTable);
            cellTabBas17.addElement(paragraphTabBas17);
            cellTabBas17.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas17.setVerticalAlignment(Element.ALIGN_LEFT);
            cellTabBas17.setBorder(Rectangle.BOX);
            cellTabBas17.setColspan(2);
            table5.addCell(cellTabBas17);

            PdfPCell cellTabBas18;
            cellTabBas18 = new PdfPCell(new Phrase(avancementTTC));
            cellTabBas18.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellTabBas18.setVerticalAlignment(Element.ALIGN_RIGHT);
            cellTabBas18.setBorder(Rectangle.BOX);
            table5.addCell(cellTabBas18);

            PdfPCell cellTabBas19 = new PdfPCell();
            Paragraph paragraphTabBas19 = new Paragraph(" ");
            cellTabBas19.addElement(paragraphTabBas19);
            cellTabBas19.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas19.setVerticalAlignment(Element.ALIGN_LEFT);
            cellTabBas19.setBorder(Rectangle.NO_BORDER);
            cellTabBas19.setColspan(3);
            table5.addCell(cellTabBas19);

            PdfPCell cellTabBas20 = new PdfPCell();
            Paragraph paragraphTabBas20 = new Paragraph("AVANCE RECUE", boldFontTitleTable);
            cellTabBas20.addElement(paragraphTabBas20);
            cellTabBas20.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas20.setVerticalAlignment(Element.ALIGN_LEFT);
            cellTabBas20.setBorder(Rectangle.BOX);
            cellTabBas20.setColspan(2);
            table5.addCell(cellTabBas20);

            PdfPCell cellTabBas21;
            cellTabBas21 = new PdfPCell(new Phrase(avanceRecu));
            cellTabBas21.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellTabBas21.setVerticalAlignment(Element.ALIGN_RIGHT);
            cellTabBas21.setBorder(Rectangle.BOX);
            table5.addCell(cellTabBas21);

            PdfPCell cellTabBas22 = new PdfPCell();
            Paragraph paragraphTabBas22 = new Paragraph(" ");
            cellTabBas22.addElement(paragraphTabBas22);
            cellTabBas22.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas22.setVerticalAlignment(Element.ALIGN_LEFT);
            cellTabBas22.setBorder(Rectangle.NO_BORDER);
            cellTabBas22.setColspan(3);
            table5.addCell(cellTabBas22);

            PdfPCell cellTabBas23 = new PdfPCell();
            Paragraph paragraphTabBas23 = new Paragraph("RELIQUAT DEMANDE", boldFontTitleTable);
            cellTabBas23.addElement(paragraphTabBas23);
            cellTabBas23.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas23.setVerticalAlignment(Element.ALIGN_LEFT);
            cellTabBas23.setBorder(Rectangle.BOX);
            cellTabBas23.setColspan(2);
            table5.addCell(cellTabBas23);

            PdfPCell cellTabBas24;
            cellTabBas24 = new PdfPCell(new Phrase(reliquantDemande));
            cellTabBas24.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellTabBas24.setVerticalAlignment(Element.ALIGN_RIGHT);
            cellTabBas24.setBorder(Rectangle.BOX);
            table5.addCell(cellTabBas24);

            document.add(table5);

            //Espace
            document.add(new Paragraph("     "));

            //Espace
            document.add(new Paragraph("FACTURE ARRETEE A LA SOMME DE : ", normalFontEmail));
            //Espace
            document.add(new Paragraph(decomptep.getMontantlettre(), normalFontEmail));

            //Espace
            document.add(new Paragraph("     "));

            // A table with 3 columns
            PdfPTable tableBas3 = new PdfPTable(4);
            tableBas3.setSpacingBefore(10f);
            tableBas3.setWidthPercentage(100);

            // the cell object
            PdfPCell cell37;
            Chunk underline = new Chunk("Directeur général", normalFont);
            underline.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            cell37 = new PdfPCell(new Phrase(underline));
            cell37.setBorder(0);
            cell37.setColspan(4);
            cell37.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell37.setVerticalAlignment(Element.ALIGN_RIGHT);
            tableBas3.addCell(cell37);
            document.add(tableBas3);

            //Espace
            document.add(new Paragraph("  "));
            //Espace
            document.add(new Paragraph("  "));

            // POUR POUVOIR GENERER LE DOCUMENT
            document.close();
            file.close();
            System.out.println("PDF Created!");

            // setting some response headers
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control",
                    "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            // setting the content type
            response.setContentType("application/pdf");
            // the contentlength
            response.setContentLength(baos.size());
            // write ByteArrayOutputStream to the ServletOutputStream
            OutputStream os = response.getOutputStream();
            baos.writeTo(os);
            os.flush();
            os.close();

            context.responseComplete();

        } catch (Exception e) {

        }

    }

    public void GenererExpression(List<FactureUtil> allFactureUtils, Expressionbesoin expressionbesoin, String totalHT, String montantTVA, String totalTTC) throws DocumentException {

        File directory = new File("./");
        String urlAbsolut = directory.getAbsolutePath();
        String url = "";

        try {
            String recuname = "Expression_besoin.pdf";
            url = urlAbsolut + recuname;

            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "inline=filename=file.pdf");

            Document document = new Document();
            document.setPageSize(PageSize.A4);
            OutputStream file = new FileOutputStream(new File(url));
            // step 2
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            PdfWriter.getInstance(document, file);

            // Add Header and Footer
//            PdfWriter writer = PdfWriter.getInstance(document, baos);
//            HeaderFooterPageEvent event = new HeaderFooterPageEvent();
//            writer.setPageEvent(event);
            // step 3
            document.open();

            Font boldFontTitle = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
            Font boldFontTitleTable = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
            Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD);
            Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
            Font normalFontEmail = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLUE);
            Font boldFontTitleExp1 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.RED);
            Font boldFontTitleExp2 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
            Font myFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLUE);

            String img = entetePath.getUrlAccel();

            Image logoProjet = Image.getInstance(img);
            logoProjet.scaleAbsolute(60f, 40f);
            logoProjet.setAlignment(Image.ALIGN_LEFT);
            document.add(logoProjet);

            Paragraph ph = new Paragraph(new Phrase(" ", myFont));
            PdfPCell cell = new PdfPCell(ph);
            cell.setBorder(Rectangle.BOTTOM);
            cell.setBorderColor(new BaseColor(44, 67, 144));
            cell.setBorderWidth(2f);

            PdfPTable table = new PdfPTable(1);
            table.addCell(cell);
            table.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.setWidthPercentage(100);
            document.add(table);

            //Espace
            // Creating text object       
            /*Text text1 = new Text("ACCEL");
             Text text2 = new Text("ACCEL TECHNOLOGIES");
            
             Font boldFontTitleSpecial1 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
             boldFontTitleSpecial1.setColor(BaseColor.RED);*/
            // A table1 with 4 columns
            PdfPTable table1 = new PdfPTable(4);
            table1.setSpacingBefore(2f);
            table1.setWidthPercentage(100);
            PdfPCell cell1 = new PdfPCell();

            Chunk c1 = new Chunk("ACCEL ", boldFontTitleExp1);
            Chunk c2 = new Chunk("TECHNOLOGIES", boldFontTitleExp2);
            Phrase p1 = new Phrase();
            p1.add(c1);
            p1.add(c2);
            Paragraph p = new Paragraph();
            p.add(p1);
//            document.add(p);
            //Espace
//            document.add(new Paragraph("", normalFont));
//            //Espace
//            document.add(new Paragraph("Dakar, Sénégal", normalFont));

            Paragraph paragraphSp1 = new Paragraph("4 Route de l’aéroport", normalFont);
            Paragraph paragraphSp2 = new Paragraph("Dakar, Sénégal", normalFont);
            Paragraph paragraph1 = new Paragraph("Tél : 33 820 83 83", normalFont);

            Chunk underlineEmail = new Chunk("Email : info@accel-tech.net", normalFontEmail);
            underlineEmail.setUnderline(0.1f, -2f);
            Paragraph paragraph2 = new Paragraph(underlineEmail);

            cell1.addElement(p);
            cell1.addElement(paragraphSp1);
            cell1.addElement(paragraphSp2);
            cell1.addElement(paragraph1);
            cell1.addElement(paragraph2);
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell1.setVerticalAlignment(Element.ALIGN_LEFT);
            cell1.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell1);

            PdfPCell cell2;
            cell2 = new PdfPCell(new Phrase(" "));
            cell2.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell2);

            PdfPCell cell3;
            cell3 = new PdfPCell(new Phrase(" "));
            cell3.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell3);

            // the cell object
            PdfPCell cell4 = new PdfPCell();
            Paragraph paragraph4 = new Paragraph("FOURNISSEUR", boldFont);
            Paragraph paragraph5 = new Paragraph(" ", boldFont);
            cell4.addElement(paragraph4);
            cell4.addElement(paragraph5);
            cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell4.setVerticalAlignment(Element.ALIGN_LEFT);
            cell4.setBorder(Rectangle.BOX);
            table1.addCell(cell4);

            // Defiles the relative width of the columns
            float[] columnWidths = new float[]{20f, 10f, 20f, 30f};
            table1.setWidths(columnWidths);
            document.add(table1);

            //Espace
            document.add(new Paragraph("  "));

            // A table1 with 4 columns
            PdfPTable table2 = new PdfPTable(4);
            table2.setSpacingBefore(10f);
            table2.setWidthPercentage(100);
            // the cell object
            PdfPCell cell5;
            // we add a cell with colspan 4
            cell5 = new PdfPCell(new Phrase("EXPRESSION DE BESOIN", boldFontTitle));
            cell5.setColspan(4);

            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
            //border
            cell5.setBorder(0);
            table2.addCell(cell5);
            document.add(table2);

            // A table1 with 4 columns
            PdfPTable table3 = new PdfPTable(4);
            table3.setSpacingBefore(10f);
            table3.setWidthPercentage(100);
            PdfPCell cell6 = new PdfPCell();
            Chunk underline1 = new Chunk("DATE : " + expressionbesoin.getDateExpression(), normalFont);
            underline1.setUnderline(0.1f, -2f);
            cell6.addElement(underline1);
            cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell6.setVerticalAlignment(Element.ALIGN_LEFT);
            cell6.setBorder(Rectangle.NO_BORDER);
            table3.addCell(cell6);

            PdfPCell cell7;
            cell7 = new PdfPCell(new Phrase(" "));
            cell7.setBorder(Rectangle.NO_BORDER);
            table3.addCell(cell7);

            PdfPCell cell8;
            cell8 = new PdfPCell(new Phrase(" "));
            cell8.setBorder(Rectangle.NO_BORDER);
            table3.addCell(cell8);

            // the cell object
            PdfPCell cell9 = new PdfPCell();
            Chunk underline2 = new Chunk("EXPRESSION DE BESOIN NUMERO", boldFont);
            underline2.setUnderline(0.1f, -2f);
            Paragraph paragraph8 = new Paragraph("N°" + expressionbesoin.getNumeroExpression(), boldFont);
            cell9.addElement(underline2);
            cell9.addElement(paragraph8);
            cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell9.setVerticalAlignment(Element.ALIGN_LEFT);
            cell9.setBorder(Rectangle.NO_BORDER);
            table3.addCell(cell9);

            // Defiles the relative width of the columns
            float[] columnWidthss = new float[]{20f, 10f, 20f, 30f};
            table3.setWidths(columnWidthss);
            document.add(table3);

            //Espace
            document.add(new Paragraph("     "));

            // A table1 with 4 columns
            PdfPTable tableDevis = new PdfPTable(4);
            tableDevis.setSpacingBefore(10f);
            tableDevis.setWidthPercentage(100);
            PdfPCell cell10 = new PdfPCell();
            Chunk underline3 = new Chunk("DEMANDEUR", boldFont);
            underline3.setUnderline(0.1f, -2f);
            Paragraph paragraphExp = new Paragraph(expressionbesoin.getIdUtilisateur().getNomUtilisateur() + " " + expressionbesoin.getIdUtilisateur().getPrenomUtilisateur(), boldFont);
            cell10.addElement(underline3);
            cell10.addElement(paragraphExp);
            cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell10.setVerticalAlignment(Element.ALIGN_LEFT);
            cell10.setBorder(Rectangle.NO_BORDER);
            cell10.setColspan(2);
            tableDevis.addCell(cell10);

            PdfPCell cell11;
            cell11 = new PdfPCell(new Phrase("CHANTIER: " + expressionbesoin.getIdChantier().getSiteChantier(), boldFont));
            cell11.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell11.setVerticalAlignment(Element.ALIGN_RIGHT);
            cell11.setBorder(Rectangle.NO_BORDER);
            cell11.setColspan(2);
            tableDevis.addCell(cell11);

            document.add(tableDevis);

            //Espace
            document.add(new Paragraph("     "));

            PdfPTable table4 = new PdfPTable(4);
            table4.setSpacingBefore(10f);
            table4.setWidthPercentage(100);

            table4.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);

            PdfPCell cellT1 = new PdfPCell();
            cellT1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellT1.setVerticalAlignment(Element.ALIGN_LEFT);
            Paragraph paragraph9 = new Paragraph("Désignation", boldFontTitleTable);
            cellT1.addElement(paragraph9);
            table4.addCell(cellT1);

            PdfPCell cellT2 = new PdfPCell();
            cellT2.setHorizontalAlignment(Element.ALIGN_CENTER);
            Paragraph paragraph10 = new Paragraph("Ref", boldFontTitleTable);
            cellT2.addElement(paragraph10);
            table4.addCell(cellT2);

            PdfPCell cellT3 = new PdfPCell();
            cellT3.setHorizontalAlignment(Element.ALIGN_CENTER);
            Paragraph paragraph11 = new Paragraph("U.mesure", boldFontTitleTable);
            cellT3.addElement(paragraph11);
            table4.addCell(cellT3);

            PdfPCell cellT4 = new PdfPCell();
            cellT4.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellT4.setVerticalAlignment(Element.ALIGN_MIDDLE);
            Paragraph paragraph12 = new Paragraph("Qte", boldFontTitleTable);
            cellT4.addElement(paragraph12);
            table4.addCell(cellT4);

            table4.setHeaderRows(1);
            PdfPCell[] cells = table4.getRow(0).getCells();
            for (int j = 0; j < cells.length; j++) {
                cells[j].setBackgroundColor(BaseColor.WHITE);
            }

            for (FactureUtil f : allFactureUtils) {

                table4.addCell(f.getDesignation());
                table4.addCell(f.getRef());
                table4.addCell(f.getUnite());
                String qt = String.valueOf(f.getQuantite());
                table4.addCell(qt);
            }

            document.add(table4);

            //Espace
            document.add(new Paragraph("     "));

            //Espace
            document.add(new Paragraph("     "));

            // A table with 3 columns
            PdfPTable tableBas3 = new PdfPTable(4);
            tableBas3.setSpacingBefore(10f);
            tableBas3.setWidthPercentage(100);

            // the cell object
            PdfPCell cell37;
            Chunk underline = new Chunk("Directeur général", normalFont);
            underline.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            cell37 = new PdfPCell(new Phrase(underline));
            cell37.setBorder(0);
            cell37.setColspan(4);
            cell37.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell37.setVerticalAlignment(Element.ALIGN_RIGHT);
            tableBas3.addCell(cell37);
            document.add(tableBas3);

            //Espace
            document.add(new Paragraph("  "));
            //Espace
            document.add(new Paragraph("  "));

            // POUR POUVOIR GENERER LE DOCUMENT
            document.close();
            file.close();
            System.out.println("PDF Created!");

            // setting some response headers
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control",
                    "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            // setting the content type
            response.setContentType("application/pdf");
            // the contentlength
            response.setContentLength(baos.size());
            // write ByteArrayOutputStream to the ServletOutputStream
            OutputStream os = response.getOutputStream();
            baos.writeTo(os);
            os.flush();
            os.close();

            context.responseComplete();

        } catch (Exception e) {

        }

    }

    public void GenererBonCommande(List<FactureUtil> allFactureUtils, Commande commande, String totalHT, String montantTVA, String totalTTC) throws DocumentException {

        File directory = new File("./");
        String urlAbsolut = directory.getAbsolutePath();
        String url = "";

        try {
            String recuname = "Bon_de_commande.pdf";
            url = urlAbsolut + recuname;

            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "inline=filename=file.pdf");

            Document document = new Document();
            document.setPageSize(PageSize.A4);
            OutputStream file = new FileOutputStream(new File(url));
            // step 2
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            PdfWriter.getInstance(document, file);

            // Add Header and Footer
//            PdfWriter writer = PdfWriter.getInstance(document, baos);
//            HeaderFooterPageEvent event = new HeaderFooterPageEvent();
//            writer.setPageEvent(event);
            // step 3
            document.open();

            Font boldFontTitle = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
            Font boldFontTitleTable = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
            Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD);
            Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
            Font normalFontEmail = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLUE);
            Font boldFontTitleExp1 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.RED);
            Font boldFontTitleExp2 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
            Font myFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLUE);

            String img = entetePath.getUrlAccel();

            Image logoProjet = Image.getInstance(img);
            logoProjet.scaleAbsolute(60f, 40f);
            logoProjet.setAlignment(Image.ALIGN_LEFT);
            document.add(logoProjet);

            Paragraph ph = new Paragraph(new Phrase(" ", myFont));
            PdfPCell cell = new PdfPCell(ph);
            cell.setBorder(Rectangle.BOTTOM);
            cell.setBorderColor(new BaseColor(44, 67, 144));
            cell.setBorderWidth(2f);

            PdfPTable table = new PdfPTable(1);
            table.addCell(cell);
            table.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.setWidthPercentage(100);
            document.add(table);

            //Espace
            // Creating text object       
            /*Text text1 = new Text("ACCEL");
             Text text2 = new Text("ACCEL TECHNOLOGIES");
            
             Font boldFontTitleSpecial1 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
             boldFontTitleSpecial1.setColor(BaseColor.RED);*/
            // A table1 with 4 columns
            PdfPTable table1 = new PdfPTable(4);
            table1.setSpacingBefore(2f);
            table1.setWidthPercentage(100);
            PdfPCell cell1 = new PdfPCell();

            Chunk c1 = new Chunk("ACCEL ", boldFontTitleExp1);
            Chunk c2 = new Chunk("TECHNOLOGIES", boldFontTitleExp2);
            Phrase p1 = new Phrase();
            p1.add(c1);
            p1.add(c2);
            Paragraph p = new Paragraph();
            p.add(p1);
//            document.add(p);
            //Espace
//            document.add(new Paragraph("", normalFont));
//            //Espace
//            document.add(new Paragraph("Dakar, Sénégal", normalFont));

            Paragraph paragraphSp1 = new Paragraph("4 Route de l’aéroport", normalFont);
            Paragraph paragraphSp2 = new Paragraph("Dakar, Sénégal", normalFont);
            Paragraph paragraph1 = new Paragraph("Tél : 33 820 83 83", normalFont);

            Chunk underlineEmail = new Chunk("Email : info@accel-tech.net", normalFontEmail);
            underlineEmail.setUnderline(0.1f, -2f);
            Paragraph paragraph2 = new Paragraph(underlineEmail);

            cell1.addElement(p);
            cell1.addElement(paragraphSp1);
            cell1.addElement(paragraphSp2);
            cell1.addElement(paragraph1);
            cell1.addElement(paragraph2);
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell1.setVerticalAlignment(Element.ALIGN_LEFT);
            cell1.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell1);

            PdfPCell cell2;
            cell2 = new PdfPCell(new Phrase(" "));
            cell2.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell2);

            PdfPCell cell3;
            cell3 = new PdfPCell(new Phrase(" "));
            cell3.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell3);

            // the cell object
            PdfPCell cell4 = new PdfPCell();
            Paragraph paragraph4 = new Paragraph("FOURNISSEUR", boldFont);
            Paragraph paragraph5 = new Paragraph(commande.getIdFournisseur().getNomFournisseur(), boldFont);
            Paragraph paragraphBs1 = new Paragraph(commande.getIdFournisseur().getAdresseFournisseur(), boldFont);
            Paragraph paragraphBs2 = new Paragraph(commande.getIdFournisseur().getTelephoneFournisseur(), boldFont);
            Paragraph paragraphBs3 = new Paragraph(commande.getIdFournisseur().getMailFournisseur(), boldFont);
            cell4.addElement(paragraph4);
            cell4.addElement(paragraph5);
            cell4.addElement(paragraphBs1);
            cell4.addElement(paragraphBs2);
            cell4.addElement(paragraphBs3);
            cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell4.setVerticalAlignment(Element.ALIGN_LEFT);
            cell4.setBorder(Rectangle.BOX);
            table1.addCell(cell4);

            // Defiles the relative width of the columns
            float[] columnWidths = new float[]{20f, 10f, 20f, 30f};
            table1.setWidths(columnWidths);
            document.add(table1);

            //Espace
            document.add(new Paragraph("  "));

            // A table1 with 4 columns
            PdfPTable table2 = new PdfPTable(4);
            table2.setSpacingBefore(10f);
            table2.setWidthPercentage(100);
            // the cell object
            PdfPCell cell5;
            // we add a cell with colspan 4
            cell5 = new PdfPCell(new Phrase("BON DE COMMANDE", boldFontTitle));
            cell5.setColspan(4);

            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
            //border
            cell5.setBorder(0);
            table2.addCell(cell5);
            document.add(table2);

            // A table1 with 4 columns
            PdfPTable table3 = new PdfPTable(4);
            table3.setSpacingBefore(10f);
            table3.setWidthPercentage(100);
            PdfPCell cell6 = new PdfPCell();
            Chunk underline1 = new Chunk("DATE : " + commande.getDateechance(), normalFont);
            underline1.setUnderline(0.1f, -2f);
            cell6.addElement(underline1);
            cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell6.setVerticalAlignment(Element.ALIGN_LEFT);
            cell6.setBorder(Rectangle.NO_BORDER);
            table3.addCell(cell6);

            PdfPCell cell7;
            cell7 = new PdfPCell(new Phrase(" "));
            cell7.setBorder(Rectangle.NO_BORDER);
            table3.addCell(cell7);

            PdfPCell cell8;
            cell8 = new PdfPCell(new Phrase(" "));
            cell8.setBorder(Rectangle.NO_BORDER);
            table3.addCell(cell8);

            // the cell object
            PdfPCell cell9 = new PdfPCell();
            Chunk underline2 = new Chunk("BON DE COMMANDE NUMERO", boldFont);
            underline2.setUnderline(0.1f, -2f);
            Paragraph paragraph8 = new Paragraph(commande.getCode(), boldFont);
            cell9.addElement(underline2);
            cell9.addElement(paragraph8);
            cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell9.setVerticalAlignment(Element.ALIGN_LEFT);
            cell9.setBorder(Rectangle.NO_BORDER);
            table3.addCell(cell9);

            // Defiles the relative width of the columns
            float[] columnWidthss = new float[]{20f, 10f, 20f, 30f};
            table3.setWidths(columnWidthss);
            document.add(table3);

            //Espace
            document.add(new Paragraph("     "));

            if (commande.getTypecommande().equals("Exp besoin")) {
                // A table1 with 4 columns
                PdfPTable tableDevis = new PdfPTable(4);
                tableDevis.setSpacingBefore(10f);
                tableDevis.setWidthPercentage(100);
                PdfPCell cell10 = new PdfPCell();
                Chunk underline3 = new Chunk("PROJET/CHANTIER", boldFont);
                underline3.setUnderline(0.1f, -2f);
                Paragraph paragraphExp = new Paragraph(commande.getIdMarche().getNomMarche() + "/" + commande.getIdChantier().getSiteChantier(), boldFont);
                cell10.addElement(underline3);
                cell10.addElement(paragraphExp);
                cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell10.setVerticalAlignment(Element.ALIGN_LEFT);
                cell10.setBorder(Rectangle.NO_BORDER);
                cell10.setColspan(2);
                tableDevis.addCell(cell10);

                PdfPCell cell11;
                cell11 = new PdfPCell(new Phrase(commande.getIdMarche().getIdDevise().getSymbole(), boldFont));
                cell11.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell11.setVerticalAlignment(Element.ALIGN_RIGHT);
                cell11.setBorder(Rectangle.NO_BORDER);
                cell11.setColspan(2);
                tableDevis.addCell(cell11);

                document.add(tableDevis);
            }

//            if (commande.getTypecommande().equals("Appro stock")) {
//                // A table1 with 4 columns
//                PdfPTable tableDevis = new PdfPTable(4);
//                tableDevis.setSpacingBefore(10f);
//                tableDevis.setWidthPercentage(100);
//                PdfPCell cell10 = new PdfPCell();
//                Chunk underline3 = new Chunk(" ", boldFont);
//                underline3.setUnderline(0.1f, -2f);
//                Paragraph paragraphExp = new Paragraph(" ", boldFont);
//                cell10.addElement(underline3);
//                cell10.addElement(paragraphExp);
//                cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
//                cell10.setVerticalAlignment(Element.ALIGN_LEFT);
//                cell10.setBorder(Rectangle.NO_BORDER);
//                cell10.setColspan(2);
//                tableDevis.addCell(cell10);
//
//                PdfPCell cell11;
//                cell11 = new PdfPCell(new Phrase(commande.getIdMarche().getIdDevise().getSymbole(), boldFont));
//                cell11.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                cell11.setVerticalAlignment(Element.ALIGN_RIGHT);
//                cell11.setBorder(Rectangle.NO_BORDER);
//                cell11.setColspan(2);
//                tableDevis.addCell(cell11);
//
//                document.add(tableDevis);
//            }
            //Espace
            document.add(new Paragraph("     "));

            PdfPTable table4 = new PdfPTable(6);
            table4.setSpacingBefore(10f);
            table4.setWidthPercentage(100);

            table4.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);

            PdfPCell cellT1 = new PdfPCell();
            cellT1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellT1.setVerticalAlignment(Element.ALIGN_LEFT);
            Paragraph paragraph9 = new Paragraph("Désignation", boldFontTitleTable);
            cellT1.addElement(paragraph9);
            table4.addCell(cellT1);

            PdfPCell cellT2 = new PdfPCell();
            cellT2.setHorizontalAlignment(Element.ALIGN_CENTER);
            Paragraph paragraph10 = new Paragraph("Ref", boldFontTitleTable);
            cellT2.addElement(paragraph10);
            table4.addCell(cellT2);

            PdfPCell cellT3 = new PdfPCell();
            cellT3.setHorizontalAlignment(Element.ALIGN_CENTER);
            Paragraph paragraph11 = new Paragraph("U.mesure", boldFontTitleTable);
            cellT3.addElement(paragraph11);
            table4.addCell(cellT3);

            PdfPCell cellT4 = new PdfPCell();
            cellT4.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellT4.setVerticalAlignment(Element.ALIGN_MIDDLE);
            Paragraph paragraph12 = new Paragraph("Qte", boldFontTitleTable);
            cellT4.addElement(paragraph12);
            table4.addCell(cellT4);

            PdfPCell cellT5 = new PdfPCell();
            cellT5.setHorizontalAlignment(Element.ALIGN_CENTER);
            Paragraph paragraph13 = new Paragraph("P.U", boldFontTitleTable);
            cellT5.addElement(paragraph13);
            table4.addCell(cellT5);

            PdfPCell cellT6 = new PdfPCell();
            cellT6.setHorizontalAlignment(Element.ALIGN_CENTER);
            Paragraph paragraph14 = new Paragraph("P.Total", boldFontTitleTable);
            cellT6.addElement(paragraph14);
            table4.addCell(cellT6);

            table4.setHeaderRows(1);
            PdfPCell[] cells = table4.getRow(0).getCells();
            for (int j = 0; j < cells.length; j++) {
                cells[j].setBackgroundColor(BaseColor.WHITE);
            }

            for (FactureUtil f : allFactureUtils) {

                table4.addCell(f.getDesignation());
                table4.addCell(f.getRef());
                table4.addCell(f.getUnite());
                String qt = String.valueOf(f.getQuantite());
                table4.addCell(qt);
                table4.addCell(f.getPu());
                table4.addCell(f.getPrixtotal());
            }

            document.add(table4);

            //Espace
            document.add(new Paragraph("     "));

            PdfPTable table5 = new PdfPTable(6);
            table5.setSpacingBefore(10f);
            table5.setWidthPercentage(100);

            PdfPCell cellTabBas1 = new PdfPCell();
            Paragraph paragraphTabBas1 = new Paragraph(" ");
            cellTabBas1.addElement(paragraphTabBas1);
            cellTabBas1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas1.setVerticalAlignment(Element.ALIGN_LEFT);
            cellTabBas1.setBorder(Rectangle.NO_BORDER);
            cellTabBas1.setColspan(3);
            table5.addCell(cellTabBas1);

            PdfPCell cellTabBas2 = new PdfPCell();
            Paragraph paragraphTabBas2 = new Paragraph("TOTAL HT", boldFontTitleTable);
            cellTabBas2.addElement(paragraphTabBas2);
            cellTabBas2.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas2.setVerticalAlignment(Element.ALIGN_LEFT);
            cellTabBas2.setBorder(Rectangle.BOX);
            cellTabBas2.setColspan(2);
            table5.addCell(cellTabBas2);

            PdfPCell cellTabBas3;
            cellTabBas3 = new PdfPCell(new Phrase(totalHT));
            cellTabBas3.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellTabBas3.setVerticalAlignment(Element.ALIGN_RIGHT);
            cellTabBas3.setBorder(Rectangle.BOX);
            table5.addCell(cellTabBas3);

            PdfPCell cellTabBas4 = new PdfPCell();
            Paragraph paragraphTabBas4 = new Paragraph(" ");
            cellTabBas4.addElement(paragraphTabBas4);
            cellTabBas4.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas4.setVerticalAlignment(Element.ALIGN_LEFT);
            cellTabBas4.setBorder(Rectangle.NO_BORDER);
            cellTabBas4.setColspan(3);
            table5.addCell(cellTabBas4);

            PdfPCell cellTabBas5 = new PdfPCell();
            Paragraph paragraphTabBas5 = new Paragraph("TVA 18%", boldFontTitleTable);
            cellTabBas5.addElement(paragraphTabBas5);
            cellTabBas5.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas5.setVerticalAlignment(Element.ALIGN_LEFT);
            cellTabBas5.setBorder(Rectangle.BOX);
            cellTabBas5.setColspan(2);
            table5.addCell(cellTabBas5);

            PdfPCell cellTabBas6;
            cellTabBas6 = new PdfPCell(new Phrase(montantTVA));
            cellTabBas6.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellTabBas6.setVerticalAlignment(Element.ALIGN_RIGHT);
            cellTabBas6.setBorder(Rectangle.BOX);
            table5.addCell(cellTabBas6);

            PdfPCell cellTabBas7 = new PdfPCell();
            Paragraph paragraphTabBas7 = new Paragraph(" ");
            cellTabBas7.addElement(paragraphTabBas7);
            cellTabBas7.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas7.setVerticalAlignment(Element.ALIGN_LEFT);
            cellTabBas7.setBorder(Rectangle.NO_BORDER);
            cellTabBas7.setColspan(3);
            table5.addCell(cellTabBas7);

            PdfPCell cellTabBas8 = new PdfPCell();
            Paragraph paragraphTabBas8 = new Paragraph("TOTAL TTC", boldFontTitleTable);
            cellTabBas8.addElement(paragraphTabBas8);
            cellTabBas8.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas8.setVerticalAlignment(Element.ALIGN_LEFT);
            cellTabBas8.setBorder(Rectangle.BOX);
            cellTabBas8.setColspan(2);
            table5.addCell(cellTabBas8);

            PdfPCell cellTabBas9;
            cellTabBas9 = new PdfPCell(new Phrase(totalTTC));
            cellTabBas9.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellTabBas9.setVerticalAlignment(Element.ALIGN_RIGHT);
            cellTabBas9.setBorder(Rectangle.BOX);
            table5.addCell(cellTabBas9);

            document.add(table5);

            //Espace
            document.add(new Paragraph("     "));

            // A table with 3 columns
            PdfPTable tableBas3 = new PdfPTable(4);
            tableBas3.setSpacingBefore(10f);
            tableBas3.setWidthPercentage(100);

            // the cell object
            PdfPCell cell37;
            Chunk underline = new Chunk("Directeur général", normalFont);
            underline.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            cell37 = new PdfPCell(new Phrase(underline));
            cell37.setBorder(0);
            cell37.setColspan(4);
            cell37.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell37.setVerticalAlignment(Element.ALIGN_RIGHT);
            tableBas3.addCell(cell37);
            document.add(tableBas3);

            //Espace
            document.add(new Paragraph("  "));
            //Espace
            document.add(new Paragraph("  "));

            // POUR POUVOIR GENERER LE DOCUMENT
            document.close();
            file.close();
            System.out.println("PDF Created!");

            // setting some response headers
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control",
                    "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            // setting the content type
            response.setContentType("application/pdf");
            // the contentlength
            response.setContentLength(baos.size());
            // write ByteArrayOutputStream to the ServletOutputStream
            OutputStream os = response.getOutputStream();
            baos.writeTo(os);
            os.flush();
            os.close();

            context.responseComplete();

        } catch (Exception e) {

        }

    }

    public void GenererMarcher(Marche marche, String soldeMarche, List<Chantier> allChantiers, String totalApproDirect, String totalApproStock, String totalDecompte, String totalDepenses, String totalMoHt, String totalMoTtc, String reliquat, String pourcentage) throws DocumentException {

        File directory = new File("./");
        String urlAbsolut = directory.getAbsolutePath();
        String url = "";

        try {
            String recuname = "Marche.pdf";
            url = urlAbsolut + recuname;

            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "inline=filename=file.pdf");

            Document document = new Document();
            document.setPageSize(PageSize.A4);
            OutputStream file = new FileOutputStream(new File(url));
            // step 2
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            PdfWriter.getInstance(document, file);
            // step 3
            document.open();

            Font boldFontTitle = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
            Font boldFontTitleTable = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
            Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD);
            Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
            Font normalFontEmail = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLUE);
            Font boldFontTitleExp1 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.RED);
            Font boldFontTitleExp2 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);

            String img = entetePath.getUrlAccel();

            Image logoProjet = Image.getInstance(img);
            logoProjet.scaleAbsolute(60f, 40f);
            logoProjet.setAlignment(Image.ALIGN_LEFT);
            document.add(logoProjet);

            //Espace
            // Creating text object       
            /*Text text1 = new Text("ACCEL");
             Text text2 = new Text("ACCEL TECHNOLOGIES");
            
             Font boldFontTitleSpecial1 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
             boldFontTitleSpecial1.setColor(BaseColor.RED);*/
            Chunk c1 = new Chunk("ACCEL ", boldFontTitleExp1);
            Chunk c2 = new Chunk("TECHNOLOGIES", boldFontTitleExp2);
            Phrase p1 = new Phrase();
            p1.add(c1);
            p1.add(c2);
            Paragraph p = new Paragraph();
            p.add(p1);
            document.add(p);
            //Espace
            document.add(new Paragraph("4 Route de l’aéroport", normalFont));
            //Espace
            document.add(new Paragraph("Dakar, Sénégal", normalFont));

            // A table1 with 4 columns
            PdfPTable table1 = new PdfPTable(4);
            table1.setSpacingBefore(2f);
            table1.setWidthPercentage(100);
            PdfPCell cell1 = new PdfPCell();
            Paragraph paragraph1 = new Paragraph("Tél : 33 820 83 83", normalFont);

            Chunk underlineEmail = new Chunk("Email : info@accel-tech.net", normalFontEmail);
            underlineEmail.setUnderline(0.1f, -2f);
            Paragraph paragraph2 = new Paragraph(underlineEmail);

            cell1.addElement(paragraph1);
            cell1.addElement(paragraph2);
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell1.setVerticalAlignment(Element.ALIGN_LEFT);
            cell1.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell1);

            PdfPCell cell2;
            cell2 = new PdfPCell(new Phrase(" "));
            cell2.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell2);

            PdfPCell cell3;
            cell3 = new PdfPCell(new Phrase(" "));
            cell3.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell3);

            // the cell object
            PdfPCell cell4 = new PdfPCell();
            Paragraph paragraph4 = new Paragraph("MARCHE", boldFont);
            Paragraph paragraph5 = new Paragraph(marche.getNomMarche(), boldFont);
            cell4.addElement(paragraph4);
            cell4.addElement(paragraph5);
            cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell4.setVerticalAlignment(Element.ALIGN_LEFT);
            cell4.setBorder(Rectangle.BOX);
            table1.addCell(cell4);

            // Defiles the relative width of the columns
            float[] columnWidths = new float[]{20f, 10f, 20f, 30f};
            table1.setWidths(columnWidths);
            document.add(table1);

            //Espace
            document.add(new Paragraph("  "));

            // A table1 with 4 columns
            PdfPTable table2 = new PdfPTable(4);
            table2.setSpacingBefore(10f);
            table2.setWidthPercentage(100);
            // the cell object
            PdfPCell cell5;
            // we add a cell with colspan 4
            cell5 = new PdfPCell(new Phrase("", boldFontTitle));
            cell5.setColspan(4);

            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
            //border
            cell5.setBorder(0);
            table2.addCell(cell5);
            document.add(table2);

            //Espace
            document.add(new Paragraph("     "));
            document.add(new Paragraph("     "));

            // A table with 4 columns
            PdfPTable tableInfo = new PdfPTable(4);
            tableInfo.setSpacingBefore(10f);
            tableInfo.setWidthPercentage(100);
            PdfPCell cellInfo;
            cellInfo = new PdfPCell(new Phrase("INFORMATION DU PROJET"));
            cellInfo.setColspan(4);
            cellInfo.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellInfo.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellInfo.setBorder(0);
            tableInfo.addCell(cellInfo);
            document.add(tableInfo);

            //Espace
            document.add(new Paragraph("     "));
            document.add(new Paragraph("     "));

            // A table1 with 3 columns
            PdfPTable table1P = new PdfPTable(3);
            table1P.setSpacingBefore(10f);
            table1P.setWidthPercentage(100);

            PdfPCell cell2P;
            cell2P = new PdfPCell(new Phrase("Client: " + marche.getIdDevis().getIdClient().getNomClient()));
            cell2P.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell2P.setVerticalAlignment(Element.ALIGN_LEFT);
            cell2P.setBorder(0);
            table1P.addCell(cell2P);

            PdfPCell cell3P;
            cell3P = new PdfPCell(new Phrase("Durée: " + marche.getDureeContrat()));
            cell3P.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell3P.setVerticalAlignment(Element.ALIGN_LEFT);
            cell3P.setBorder(0);
            table1P.addCell(cell3P);

            PdfPCell cell4P;
            cell4P = new PdfPCell(new Phrase("Montant de démarrage: " + marche.getMontantDemarrage()));
            cell4P.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell4P.setVerticalAlignment(Element.ALIGN_LEFT);
            cell4P.setBorder(0);
            table1P.addCell(cell4P);

            document.add(table1P);

            // A table1 with 3 columns
            PdfPTable table2P = new PdfPTable(3);
            table2P.setSpacingBefore(10f);
            table2P.setWidthPercentage(100);

            PdfPCell cell5P;
            cell5P = new PdfPCell(new Phrase("Date de début: " + marche.getDateDebut()));
            cell5P.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell5P.setVerticalAlignment(Element.ALIGN_LEFT);
            cell5P.setBorder(0);
            table2P.addCell(cell5P);

            PdfPCell cell6;
            cell6 = new PdfPCell(new Phrase("Objet du marché: " + marche.getObjetMarche()));
            cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell6.setVerticalAlignment(Element.ALIGN_LEFT);
            cell6.setBorder(0);
            table2P.addCell(cell6);

            PdfPCell cell7;
            cell7 = new PdfPCell(new Phrase("Date de notification: " + marche.getNotification()));
            cell7.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell7.setVerticalAlignment(Element.ALIGN_LEFT);
            cell7.setBorder(0);
            table2P.addCell(cell7);

            document.add(table2P);

            // A table1 with 3 columns
            PdfPTable table3 = new PdfPTable(3);
            table3.setSpacingBefore(10f);
            table3.setWidthPercentage(100);

            PdfPCell cell8;
            cell8 = new PdfPCell(new Phrase("Date de fin: " + marche.getDateFin()));
            cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell8.setVerticalAlignment(Element.ALIGN_LEFT);
            cell8.setBorder(0);
            table3.addCell(cell8);

            PdfPCell cell9;
            cell9 = new PdfPCell(new Phrase("Montant TTC: " + marche.getMontantMarche()));
            cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell9.setVerticalAlignment(Element.ALIGN_LEFT);
            cell9.setBorder(0);
            table3.addCell(cell9);

            PdfPCell cell10;
            cell10 = new PdfPCell(new Phrase("Solde: " + soldeMarche));
            cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell10.setVerticalAlignment(Element.ALIGN_LEFT);
            cell10.setBorder(0);
            table3.addCell(cell10);

            document.add(table3);

            document.add(new Paragraph("     "));
            document.add(new Paragraph("     "));

            // A table with 4 columns
            PdfPTable tableChantier = new PdfPTable(4);
            tableChantier.setSpacingBefore(10f);
            tableChantier.setWidthPercentage(100);
            PdfPCell cellChantier;
            cellChantier = new PdfPCell(new Phrase("CHANTIER DU PROJET"));
            cellChantier.setColspan(4);
            cellChantier.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellChantier.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellChantier.setBorder(0);
            tableChantier.addCell(cellChantier);
            document.add(tableChantier);

            //Espace
            document.add(new Paragraph("     "));
            document.add(new Paragraph("     "));

            PdfPTable tableInfoChantier = new PdfPTable(3);
            tableInfoChantier.setSpacingBefore(10f);
            tableInfoChantier.setWidthPercentage(100);
            Font blanc = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);

            tableInfoChantier.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            tableInfoChantier.addCell("Site");
            tableInfoChantier.addCell("Date de création");
            tableInfoChantier.addCell("Etat");
            tableInfoChantier.setHeaderRows(1);
            PdfPCell[] cells = tableInfoChantier.getRow(0).getCells();
            for (int j = 0; j < cells.length; j++) {
                cells[j].setBackgroundColor(BaseColor.GRAY);
            }

            for (Chantier ch : allChantiers) {

                tableInfoChantier.addCell(ch.getSiteChantier());
                tableInfoChantier.addCell(ch.getDateCreation());
                tableInfoChantier.addCell(ch.getEtatchantier());
            }

            document.add(tableInfoChantier);

            document.add(new Paragraph("     "));
            document.add(new Paragraph("     "));

            // A table with 4 columns
            PdfPTable tableAvencement = new PdfPTable(4);
            tableAvencement.setSpacingBefore(10f);
            tableAvencement.setWidthPercentage(100);
            PdfPCell cellAvancement;
            cellAvancement = new PdfPCell(new Phrase("DEPENSES ET AVANCEMENT DU PROJET (Devise : " + marche.getIdDevise().getSymbole() + ")"));
            cellAvancement.setColspan(4);
            cellAvancement.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellAvancement.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellAvancement.setBorder(0);
            tableAvencement.addCell(cellAvancement);
            document.add(tableAvencement);

            //Espace
            document.add(new Paragraph("     "));
            document.add(new Paragraph("     "));

            // A table1 with 3 columns
            PdfPTable table01 = new PdfPTable(2);
            table01.setSpacingBefore(10f);
            table01.setWidthPercentage(100);

            PdfPCell cell02;
            cell02 = new PdfPCell(new Phrase("Total Appro direct: " + totalApproDirect));
            cell02.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell02.setVerticalAlignment(Element.ALIGN_LEFT);
            cell02.setBorder(0);
            table01.addCell(cell02);

            PdfPCell cell03;
            cell03 = new PdfPCell(new Phrase("Total Appro stock: " + totalApproStock));
            cell03.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell03.setVerticalAlignment(Element.ALIGN_RIGHT);
            cell03.setBorder(0);
            table01.addCell(cell03);

            document.add(table01);

            // A table1 with 3 columns
            PdfPTable table02 = new PdfPTable(2);
            table02.setSpacingBefore(10f);
            table02.setWidthPercentage(100);

            PdfPCell cell05;
            cell05 = new PdfPCell(new Phrase("Total main d'oeuvre HT: " + totalMoHt));
            cell05.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell05.setVerticalAlignment(Element.ALIGN_LEFT);
            cell05.setBorder(0);
            table02.addCell(cell05);

            PdfPCell cell06;
            cell06 = new PdfPCell(new Phrase("Total main d'oeuvre TTC :" + totalMoTtc));
            cell06.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell06.setVerticalAlignment(Element.ALIGN_RIGHT);
            cell06.setBorder(0);
            table02.addCell(cell06);

            document.add(table02);

            // A table1 with 3 columns
            PdfPTable table03 = new PdfPTable(2);
            table03.setSpacingBefore(10f);
            table03.setWidthPercentage(100);

            PdfPCell cell07;
            cell07 = new PdfPCell(new Phrase("Total des depenses : " + totalDepenses));
            cell07.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell07.setVerticalAlignment(Element.ALIGN_LEFT);
            cell07.setBorder(0);
            table03.addCell(cell07);

            PdfPCell cell08;
            cell08 = new PdfPCell(new Phrase("Montant de démarrage :" + marche.getMontantDemarrage()));
            cell08.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell08.setVerticalAlignment(Element.ALIGN_RIGHT);
            cell08.setBorder(0);
            table03.addCell(cell08);

            document.add(table03);

            // A table1 with 3 columns
            PdfPTable table04 = new PdfPTable(2);
            table04.setSpacingBefore(10f);
            table04.setWidthPercentage(100);

            PdfPCell cell09;
            cell09 = new PdfPCell(new Phrase("Total des décomptes : " + totalDecompte));
            cell09.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell09.setVerticalAlignment(Element.ALIGN_LEFT);
            cell09.setBorder(0);
            table04.addCell(cell09);

            PdfPCell cell010;
            cell010 = new PdfPCell(new Phrase("Reliquat :" + reliquat));
            cell010.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell010.setVerticalAlignment(Element.ALIGN_RIGHT);
            cell010.setBorder(0);
            table04.addCell(cell010);

            document.add(table03);

            //Espace
            document.add(new Paragraph("     "));

            // A table with 3 columns
            PdfPTable tableBas3 = new PdfPTable(4);
            tableBas3.setSpacingBefore(10f);
            tableBas3.setWidthPercentage(100);

            // the cell object
            PdfPCell cell37;
            Chunk underline = new Chunk("Directeur général", normalFont);
            underline.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            cell37 = new PdfPCell(new Phrase(underline));
            cell37.setBorder(0);
            cell37.setColspan(4);
            cell37.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell37.setVerticalAlignment(Element.ALIGN_RIGHT);
            tableBas3.addCell(cell37);
            document.add(tableBas3);

            //Espace
            document.add(new Paragraph("  "));
            //Espace
            document.add(new Paragraph("  "));

            // A table1 with 4 columns
            PdfPTable table6 = new PdfPTable(4);
            table6.setSpacingBefore(10f);
            table6.setWidthPercentage(100);
            PdfPCell cellBottom;
            cellBottom = new PdfPCell(new Phrase("ACCEL TECHNOLOGIE SARL \n RCCM : SN DKR 2014 B 21849 NINEA : 005310652 2Y2", normalFont));
            cellBottom.setColspan(4);
            cellBottom.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellBottom.setBorder(0);
            table6.addCell(cellBottom);
            document.add(table6);

            // POUR POUVOIR GENERER LE DOCUMENT
            document.close();
            file.close();

            // setting some response headers
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control",
                    "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            // setting the content type
            response.setContentType("application/pdf");
            // the contentlength
            response.setContentLength(baos.size());
            // write ByteArrayOutputStream to the ServletOutputStream
            OutputStream os = response.getOutputStream();
            baos.writeTo(os);
            os.flush();
            os.close();

            context.responseComplete();

        } catch (Exception e) {

        }

    }

    public void GenererChantier(Chantier chantierFromEdit, List<Detaillechantier> listPersonnelChantier, String totalAccorps, String totalAcompteHT, String totalAcompteTCC, List<Livraison> listLivraisons, String coutTotalApproDirect, List<Sortiestock> listSortieStock, String coutTotalApproStock) throws DocumentException {

        File directory = new File("./");
        String urlAbsolut = directory.getAbsolutePath();
        String url = "";

        try {
            String recuname = "Chantier.pdf";
            url = urlAbsolut + recuname;

            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "inline=filename=file.pdf");

            Document document = new Document();
            document.setPageSize(PageSize.A4);
            OutputStream file = new FileOutputStream(new File(url));
            // step 2
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            PdfWriter.getInstance(document, file);
            // step 3
            document.open();

            Font boldFontTitle = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
            Font boldFontTitleTable = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
            Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD);
            Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
            Font normalFontEmail = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLUE);
            Font boldFontTitleExp1 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.RED);
            Font boldFontTitleExp2 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);

            String img = entetePath.getUrlAccel();

            Image logoProjet = Image.getInstance(img);
            logoProjet.scaleAbsolute(60f, 40f);
            logoProjet.setAlignment(Image.ALIGN_LEFT);
            document.add(logoProjet);

            //Espace
            // Creating text object       
            /*Text text1 = new Text("ACCEL");
             Text text2 = new Text("ACCEL TECHNOLOGIES");
            
             Font boldFontTitleSpecial1 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
             boldFontTitleSpecial1.setColor(BaseColor.RED);*/
            Chunk c1 = new Chunk("ACCEL ", boldFontTitleExp1);
            Chunk c2 = new Chunk("TECHNOLOGIES", boldFontTitleExp2);
            Phrase p1 = new Phrase();
            p1.add(c1);
            p1.add(c2);
            Paragraph p = new Paragraph();
            p.add(p1);
            document.add(p);
            //Espace
            document.add(new Paragraph("4 Route de l’aéroport", normalFont));
            //Espace
            document.add(new Paragraph("Dakar, Sénégal", normalFont));

            // A table1 with 4 columns
            PdfPTable table1 = new PdfPTable(4);
            table1.setSpacingBefore(2f);
            table1.setWidthPercentage(100);
            PdfPCell cell1 = new PdfPCell();
            Paragraph paragraph1 = new Paragraph("Tél : 33 820 83 83", normalFont);

            Chunk underlineEmail = new Chunk("Email : info@accel-tech.net", normalFontEmail);
            underlineEmail.setUnderline(0.1f, -2f);
            Paragraph paragraph2 = new Paragraph(underlineEmail);

            cell1.addElement(paragraph1);
            cell1.addElement(paragraph2);
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell1.setVerticalAlignment(Element.ALIGN_LEFT);
            cell1.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell1);

            PdfPCell cell2;
            cell2 = new PdfPCell(new Phrase(" "));
            cell2.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell2);

            PdfPCell cell3;
            cell3 = new PdfPCell(new Phrase(" "));
            cell3.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell3);

            // the cell object
            PdfPCell cell4 = new PdfPCell();
            Paragraph paragraph4 = new Paragraph("CHANTIER", boldFont);
            Paragraph paragraph5 = new Paragraph(chantierFromEdit.getSiteChantier(), boldFont);
            cell4.addElement(paragraph4);
            cell4.addElement(paragraph5);
            cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell4.setVerticalAlignment(Element.ALIGN_LEFT);
            cell4.setBorder(Rectangle.BOX);
            table1.addCell(cell4);

            // Defiles the relative width of the columns
            float[] columnWidths = new float[]{20f, 10f, 20f, 30f};
            table1.setWidths(columnWidths);
            document.add(table1);

            //Espace
            document.add(new Paragraph("  "));

            // A table1 with 4 columns
            PdfPTable table2 = new PdfPTable(4);
            table2.setSpacingBefore(10f);
            table2.setWidthPercentage(100);
            // the cell object
            PdfPCell cell5;
            // we add a cell with colspan 4
            cell5 = new PdfPCell(new Phrase("", boldFontTitle));
            cell5.setColspan(4);

            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
            //border
            cell5.setBorder(0);
            table2.addCell(cell5);
            document.add(table2);

            //Espace
            document.add(new Paragraph("     "));
            document.add(new Paragraph("     "));

            // A table with 4 columns
            PdfPTable tableInfo = new PdfPTable(4);
            tableInfo.setSpacingBefore(10f);
            tableInfo.setWidthPercentage(100);
            PdfPCell cellInfo;
            cellInfo = new PdfPCell(new Phrase("INFORMATION DU CHANTIER"));
            cellInfo.setColspan(4);
            cellInfo.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellInfo.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellInfo.setBorder(0);
            tableInfo.addCell(cellInfo);
            document.add(tableInfo);

            //Espace
            document.add(new Paragraph("     "));
            document.add(new Paragraph("     "));

            // A table1 with 3 columns
            PdfPTable table1P = new PdfPTable(3);
            table1P.setSpacingBefore(10f);
            table1P.setWidthPercentage(100);

            PdfPCell cell2P;
            cell2P = new PdfPCell(new Phrase("Projet : " + chantierFromEdit.getIdMarche().getNomMarche()));
            cell2P.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell2P.setVerticalAlignment(Element.ALIGN_LEFT);
            cell2P.setBorder(0);
            table1P.addCell(cell2P);

            PdfPCell cell3P;
            cell3P = new PdfPCell(new Phrase("Chef de chantier : " + chantierFromEdit.getIdUtilisateur().getNomUtilisateur() + " " + chantierFromEdit.getIdUtilisateur().getPrenomUtilisateur()));
            cell3P.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell3P.setVerticalAlignment(Element.ALIGN_LEFT);
            cell3P.setBorder(0);
            table1P.addCell(cell3P);

            PdfPCell cell4P;
            cell4P = new PdfPCell(new Phrase("Etat du chantier : " + chantierFromEdit.getEtatchantier()));
            cell4P.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell4P.setVerticalAlignment(Element.ALIGN_LEFT);
            cell4P.setBorder(0);
            table1P.addCell(cell4P);

            document.add(table1P);

            document.add(new Paragraph("     "));
            document.add(new Paragraph("     "));

            // A table with 4 columns
            PdfPTable tableChantier = new PdfPTable(4);
            tableChantier.setSpacingBefore(10f);
            tableChantier.setWidthPercentage(100);
            PdfPCell cellChantier;
            cellChantier = new PdfPCell(new Phrase("PRESTATAIRES AFFECTEES SUR LE CHANTIER"));
            cellChantier.setColspan(4);
            cellChantier.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellChantier.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellChantier.setBorder(0);
            tableChantier.addCell(cellChantier);
            document.add(tableChantier);

            //Espace
            document.add(new Paragraph("     "));
            document.add(new Paragraph("     "));

            PdfPTable tableInfoChantier = new PdfPTable(3);
            tableInfoChantier.setSpacingBefore(10f);
            tableInfoChantier.setWidthPercentage(100);
            Font blanc = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);

            tableInfoChantier.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            tableInfoChantier.addCell("Prestataire");
            tableInfoChantier.addCell("Corps d'etat");
            tableInfoChantier.addCell("Date d'affectation");
            tableInfoChantier.setHeaderRows(1);
            PdfPCell[] cells = tableInfoChantier.getRow(0).getCells();
            for (int j = 0; j < cells.length; j++) {
                cells[j].setBackgroundColor(BaseColor.GRAY);
            }

            for (Detaillechantier dch : listPersonnelChantier) {

                tableInfoChantier.addCell(dch.getIdPrestataire().getNomcomplet());
                tableInfoChantier.addCell(dch.getIdPrestataire().getCorps());
                tableInfoChantier.addCell(dch.getDateAlocation());
            }

            document.add(tableInfoChantier);

            // A table1 with 3 columns
            PdfPTable table1PP = new PdfPTable(3);
            table1PP.setSpacingBefore(10f);
            table1PP.setWidthPercentage(100);

            PdfPCell cell2PP;
            cell2PP = new PdfPCell(new Phrase("Total accords: " + totalAccorps));
            cell2PP.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell2PP.setVerticalAlignment(Element.ALIGN_LEFT);
            cell2PP.setBorder(0);
            table1PP.addCell(cell2PP);

            PdfPCell cell3PP;
            cell3PP = new PdfPCell(new Phrase("Total accomptes HT: " + totalAcompteHT));
            cell3PP.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell3PP.setVerticalAlignment(Element.ALIGN_LEFT);
            cell3PP.setBorder(0);
            table1PP.addCell(cell3PP);

            PdfPCell cell4PP;
            cell4PP = new PdfPCell(new Phrase("Total accomptes TTC: " + totalAcompteTCC));
            cell4PP.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell4PP.setVerticalAlignment(Element.ALIGN_LEFT);
            cell4PP.setBorder(0);
            table1PP.addCell(cell4PP);

            document.add(table1PP);

            document.add(new Paragraph("     "));
            document.add(new Paragraph("     "));

            // A table with 4 columns
            PdfPTable tableAvencement = new PdfPTable(4);
            tableAvencement.setSpacingBefore(10f);
            tableAvencement.setWidthPercentage(100);
            PdfPCell cellAvancement;
            cellAvancement = new PdfPCell(new Phrase("APPROVISIONNEMENT DU CHANTIER"));
            cellAvancement.setColspan(4);
            cellAvancement.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellAvancement.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellAvancement.setBorder(0);
            tableAvencement.addCell(cellAvancement);
            document.add(tableAvencement);

            //Espace
            document.add(new Paragraph("     "));
            document.add(new Paragraph("     "));

            // A table with 4 columns
            PdfPTable tableCDDPP = new PdfPTable(4);
            tableCDDPP.setSpacingBefore(10f);
            tableCDDPP.setWidthPercentage(100);

            PdfPCell cell1PP;
            cell1PP = new PdfPCell(new Phrase("Approvisionnement direct"));
            cell1PP.setColspan(2);
            cell1PP.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell1PP.setVerticalAlignment(Element.ALIGN_LEFT);
            cell1PP.setBorder(0);
            tableCDDPP.addCell(cell1PP);

            PdfPCell cell1PPP;
            cell1PPP = new PdfPCell(new Phrase("Total Appro direct = " + coutTotalApproDirect));
            cell1PPP.setColspan(2);
            cell1PPP.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell1PPP.setVerticalAlignment(Element.ALIGN_RIGHT);
            cell1PPP.setBorder(0);
            tableCDDPP.addCell(cell1PPP);

            document.add(tableCDDPP);

            //Espace
            document.add(new Paragraph("     "));
            document.add(new Paragraph("     "));

            PdfPTable tableApproDirect = new PdfPTable(4);
            tableApproDirect.setSpacingBefore(10f);
            tableApproDirect.setWidthPercentage(100);
            Font blanc1 = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);

            tableApproDirect.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            tableApproDirect.addCell("Désignation");
            tableApproDirect.addCell("Quantité");
            tableApproDirect.addCell("P.U");
            tableApproDirect.addCell("Date");
            tableApproDirect.setHeaderRows(1);
            PdfPCell[] cells1 = tableApproDirect.getRow(0).getCells();
            for (int j = 0; j < cells.length; j++) {
                cells1[j].setBackgroundColor(BaseColor.GRAY);
            }

            for (Livraison liv : listLivraisons) {

                tableApproDirect.addCell(liv.getDesignation());
                String qtL = String.valueOf(liv.getQtLivre());
                tableApproDirect.addCell(qtL);
                tableApproDirect.addCell(liv.getPu());
                tableApproDirect.addCell(liv.getDateLivraison());
            }

            document.add(tableApproDirect);

            document.add(new Paragraph("     "));
            document.add(new Paragraph("     "));

            // A table with 4 columns
            PdfPTable tableCDDPPF = new PdfPTable(4);
            tableCDDPPF.setSpacingBefore(10f);
            tableCDDPPF.setWidthPercentage(100);

            PdfPCell cell1PPF;
            cell1PPF = new PdfPCell(new Phrase("Approvisionnement via le stock"));
            cell1PPF.setColspan(2);
            cell1PPF.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell1PPF.setVerticalAlignment(Element.ALIGN_LEFT);
            cell1PPF.setBorder(0);
            tableCDDPPF.addCell(cell1PPF);

            PdfPCell cell1PPPF;
            cell1PPPF = new PdfPCell(new Phrase("Total Appro via stock = " + coutTotalApproStock));
            cell1PPPF.setColspan(2);
            cell1PPPF.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell1PPPF.setVerticalAlignment(Element.ALIGN_RIGHT);
            cell1PPPF.setBorder(0);
            tableCDDPPF.addCell(cell1PPPF);

            document.add(tableCDDPPF);

            //Espace
            document.add(new Paragraph("     "));
            document.add(new Paragraph("     "));

            PdfPTable tableApproStock = new PdfPTable(5);
            tableApproStock.setSpacingBefore(10f);
            tableApproStock.setWidthPercentage(100);
            Font blanc2 = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);

            tableApproStock.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            tableApproStock.addCell("Demandeur");
            tableApproStock.addCell("Materiel");
            tableApproStock.addCell("Qté");
            tableApproStock.addCell("P.U");
            tableApproStock.addCell("Date");
            tableApproStock.setHeaderRows(1);
            PdfPCell[] cells2 = tableApproStock.getRow(0).getCells();
            for (int j = 0; j < cells.length; j++) {
                cells2[j].setBackgroundColor(BaseColor.GRAY);
            }

            for (Sortiestock stk : listSortieStock) {

                tableApproStock.addCell(stk.getIdUtilisateur().getNomUtilisateur() + " " + stk.getIdUtilisateur().getPrenomUtilisateur());
                String qtS = String.valueOf(stk.getQuantite());
                tableApproStock.addCell(stk.getIdStock().getDesignation());
                tableApproStock.addCell(qtS);
                tableApproStock.addCell(stk.getPu());
                tableApproStock.addCell(stk.getDateSortiestock());
            }

            document.add(tableApproStock);

            //Espace
            document.add(new Paragraph("     "));

            // A table with 3 columns
            PdfPTable tableBas3 = new PdfPTable(4);
            tableBas3.setSpacingBefore(10f);
            tableBas3.setWidthPercentage(100);

            // the cell object
            PdfPCell cell37;
            Chunk underline = new Chunk("Directeur général", normalFont);
            underline.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            cell37 = new PdfPCell(new Phrase(underline));
            cell37.setBorder(0);
            cell37.setColspan(4);
            cell37.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell37.setVerticalAlignment(Element.ALIGN_RIGHT);
            tableBas3.addCell(cell37);
            document.add(tableBas3);

            //Espace
            document.add(new Paragraph("  "));
            //Espace
            document.add(new Paragraph("  "));

            // A table1 with 4 columns
            PdfPTable table6 = new PdfPTable(4);
            table6.setSpacingBefore(10f);
            table6.setWidthPercentage(100);
            PdfPCell cellBottom;
            cellBottom = new PdfPCell(new Phrase("ACCEL TECHNOLOGIE SARL \n RCCM : SN DKR 2014 B 21849 NINEA : 005310652 2Y2", normalFont));
            cellBottom.setColspan(4);
            cellBottom.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellBottom.setBorder(0);
            table6.addCell(cellBottom);
            document.add(table6);

            // POUR POUVOIR GENERER LE DOCUMENT
            document.close();
            file.close();

            // setting some response headers
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control",
                    "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            // setting the content type
            response.setContentType("application/pdf");
            // the contentlength
            response.setContentLength(baos.size());
            // write ByteArrayOutputStream to the ServletOutputStream
            OutputStream os = response.getOutputStream();
            baos.writeTo(os);
            os.flush();
            os.close();

            context.responseComplete();

        } catch (Exception e) {

        }

    }

    public void generateCDD(Contrat contrat) {

        File directory = new File("./");
        String urlAbsolut = directory.getAbsolutePath();
        String url = "";

        try {

            String recuname = "Contrat_CDD_" + contrat.getIdUtilisateur().getNomUtilisateur() + "_" + contrat.getIdUtilisateur().getPrenomUtilisateur() + ".pdf";
            url = urlAbsolut + recuname;

            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "inline=filename=file.pdf");

            Document document = new Document();
            document.setPageSize(PageSize.A4);
            OutputStream file = new FileOutputStream(new File(url));
            // step 2
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            PdfWriter.getInstance(document, file);
            // step 3
            document.open();

            Font boldFontTitle = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
            Font boldFontTitleTable = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
            Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD);
            Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
            Font normalFontEmail = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLUE);
            Font boldFontTitleExp1 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.RED);
            Font boldFontTitleExp2 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);

            String img = entetePath.getUrlAccel();

            Image logoProjet = Image.getInstance(img);
            logoProjet.scaleAbsolute(60f, 40f);
            logoProjet.setAlignment(Image.ALIGN_LEFT);
            document.add(logoProjet);

            //Espace
            // Creating text object       
            /*Text text1 = new Text("ACCEL");
             Text text2 = new Text("ACCEL TECHNOLOGIES");
            
             Font boldFontTitleSpecial1 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
             boldFontTitleSpecial1.setColor(BaseColor.RED);*/
            Chunk c1 = new Chunk("ACCEL ", boldFontTitleExp1);
            Chunk c2 = new Chunk("TECHNOLOGIES", boldFontTitleExp2);
            Phrase p1 = new Phrase();
            p1.add(c1);
            p1.add(c2);
            Paragraph p = new Paragraph();
            p.add(p1);
            document.add(p);
            //Espace
            document.add(new Paragraph("4 Route de l’aéroport", normalFont));
            //Espace
            document.add(new Paragraph("Dakar, Sénégal", normalFont));

            // A table1 with 4 columns
            PdfPTable table1 = new PdfPTable(4);
            table1.setSpacingBefore(2f);
            table1.setWidthPercentage(100);
            PdfPCell cell1 = new PdfPCell();
            Paragraph paragraph1 = new Paragraph("Tél : 33 820 83 83", normalFont);

            Chunk underlineEmail = new Chunk("Email : info@accel-tech.net", normalFontEmail);
            underlineEmail.setUnderline(0.1f, -2f);
            Paragraph paragraph2 = new Paragraph(underlineEmail);

            cell1.addElement(paragraph1);
            cell1.addElement(paragraph2);
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell1.setVerticalAlignment(Element.ALIGN_LEFT);
            cell1.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell1);

            PdfPCell cell22;
            cell22 = new PdfPCell(new Phrase(" "));
            cell22.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell22);

            PdfPCell cell33;
            cell33 = new PdfPCell(new Phrase(" "));
            cell33.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell33);

            // the cell object
            PdfPCell cell4 = new PdfPCell();
            Paragraph paragraph4 = new Paragraph("", boldFont);
            Paragraph paragraph5 = new Paragraph("", boldFont);
            cell4.addElement(paragraph4);
            cell4.addElement(paragraph5);
            cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell4.setVerticalAlignment(Element.ALIGN_LEFT);
            cell4.setBorder(Rectangle.BOX);
            table1.addCell(cell4);

            // Defiles the relative width of the columns
            float[] columnWidths = new float[]{20f, 10f, 20f, 30f};
            table1.setWidths(columnWidths);
            document.add(table1);

            //Espace
            document.add(new Paragraph("  "));

            // A table1 with 4 columns
            PdfPTable table22 = new PdfPTable(4);
            table22.setSpacingBefore(10f);
            table22.setWidthPercentage(100);
            // the cell object
            PdfPCell cell5;
            // we add a cell with colspan 4
            cell5 = new PdfPCell(new Phrase("", boldFontTitle));
            cell5.setColspan(4);

            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
            //border
            cell5.setBorder(0);
            table22.addCell(cell5);
            document.add(table22);

            // A table with 4 columns
            PdfPTable tableCDD = new PdfPTable(4);
            tableCDD.setSpacingBefore(10f);
            tableCDD.setWidthPercentage(100);
            // the cell object
            PdfPCell cell1B;
            // we add a cell with colspan 4
            cell1B = new PdfPCell(new Phrase("CONTRAT DE TRAVAIL A DUREE DETERMINEE"));
            cell1B.setColspan(4);
            // background color
            cell1B.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell1B.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1B.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            cell1B.setBorder(1);
            tableCDD.addCell(cell1B);
            document.add(tableCDD);

            //Espace
            document.add(new Paragraph("     "));

            //Espace
            document.add(new Paragraph("     "));

            //document.add(new Paragraph("ENTRE LES SOUSSIGNES:"));
            Chunk titreText = new Chunk("ENTRE:");
            titreText.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            document.add(titreText);

            //Espace
            document.add(new Paragraph("     "));

            document.add(new Paragraph("ACCEL TECHNOLOGIES,  Société A Responsabilité Limitée  au Capital Social de 1.000.000 (Un million) FCFA ayant son siège social au 4, route de  l’Aéroport Dakar –Sénégal Tel : 33 820 83 83, immatriculée au registre du commerce sous le n° SN DKR 2014 B 21849, Ninea : 05310652 2Y2, représentée par Monsieur Mor SARRE, agissant en qualité de Président Directeur Général.\n Ci-après dénommée « L’Employeur »"));

            // A table with 4 columns
            PdfPTable tableInter1 = new PdfPTable(4);
            tableInter1.setSpacingBefore(10f);
            tableInter1.setWidthPercentage(100);
            // the cell object
            PdfPCell cell2;
            // we add a cell with colspan 4
            cell2 = new PdfPCell(new Phrase("D’une part,"));
            cell2.setColspan(4);
            cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            cell2.setBorder(0);
            tableInter1.addCell(cell2);
            document.add(tableInter1);

            // A table with 4 columns
            PdfPTable tableInter2 = new PdfPTable(4);
            tableInter2.setSpacingBefore(10f);
            tableInter2.setWidthPercentage(100);
            // the cell object
            PdfPCell cell3;
            // we add a cell with colspan 4
            cell3 = new PdfPCell(new Phrase("ET:"));
            cell3.setColspan(4);
            cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            cell3.setBorder(0);
            tableInter2.addCell(cell3);
            document.add(tableInter2);

            // A table with 3 columns
            PdfPTable tableInfo1 = new PdfPTable(3);
            tableInfo1.setSpacingBefore(10f);
            tableInfo1.setWidthPercentage(100);

            // the cell object
            PdfPCell cell44;
            cell44 = new PdfPCell(new Phrase("Prénom et nom :"));
            cell44.setBorder(0);
            tableInfo1.addCell(cell44);
            // the cell object
            PdfPCell cell55;
            cell55 = new PdfPCell(new Phrase(contrat.getIdUtilisateur().getNomUtilisateur() + " " + contrat.getIdUtilisateur().getPrenomUtilisateur()));
            cell55.setBorder(0);
            tableInfo1.addCell(cell55);
            // the cell object
            PdfPCell cell6;
            cell6 = new PdfPCell(new Phrase("         "));
            cell6.setBorder(0);
            tableInfo1.addCell(cell6);

            // the cell object
            PdfPCell cell7;
            cell7 = new PdfPCell(new Phrase("Date et lieu de naissance :"));
            cell7.setBorder(0);
            tableInfo1.addCell(cell7);
            // the cell object
            PdfPCell cell8;
            cell8 = new PdfPCell(new Phrase(contrat.getIdUtilisateur().getDateNaissanceUtilisateur() + " à " + contrat.getIdUtilisateur().getLieuNaissanceUtilisateur()));
            cell8.setBorder(0);
            tableInfo1.addCell(cell8);
            // the cell object
            PdfPCell cell9;
            cell9 = new PdfPCell(new Phrase("         "));
            cell9.setBorder(0);
            tableInfo1.addCell(cell9);

            // the cell object
            PdfPCell cell13;
            cell13 = new PdfPCell(new Phrase("Situation de famille :"));
            cell13.setBorder(0);
            tableInfo1.addCell(cell13);
            // the cell object
            PdfPCell cell14;
            cell14 = new PdfPCell(new Phrase(contrat.getIdUtilisateur().getSituationfamille()));
            cell14.setBorder(0);
            tableInfo1.addCell(cell14);
            // the cell object
            PdfPCell cell15;
            cell15 = new PdfPCell(new Phrase("         "));
            cell15.setBorder(0);
            tableInfo1.addCell(cell15);

            // the cell object
            PdfPCell cell16;
            cell16 = new PdfPCell(new Phrase("Adresse :"));
            cell16.setBorder(0);
            tableInfo1.addCell(cell16);
            // the cell object
            PdfPCell cell17;
            cell17 = new PdfPCell(new Phrase(contrat.getIdUtilisateur().getAdresseUtilisateur()));
            cell17.setBorder(0);
            tableInfo1.addCell(cell17);
            // the cell object
            PdfPCell cell18;
            cell18 = new PdfPCell(new Phrase("         "));
            cell18.setBorder(0);
            tableInfo1.addCell(cell18);

            // the cell object
            PdfPCell cell10;
            cell10 = new PdfPCell(new Phrase("Nationalité  :"));
            cell10.setBorder(0);
            tableInfo1.addCell(cell10);
            // the cell object
            PdfPCell cell11;
            cell11 = new PdfPCell(new Phrase("Sénégalaise"));
            cell11.setBorder(0);
            tableInfo1.addCell(cell11);
            // the cell object
            PdfPCell cell12;
            cell12 = new PdfPCell(new Phrase("         "));
            cell12.setBorder(0);
            tableInfo1.addCell(cell12);

            // the cell object
            PdfPCell cell19;
            cell19 = new PdfPCell(new Phrase("Lieu d’exécution du contrat :"));
            cell19.setBorder(0);
            tableInfo1.addCell(cell19);
            // the cell object
            PdfPCell cell20;
            cell20 = new PdfPCell(new Phrase("Sénégal"));
            cell20.setBorder(0);
            tableInfo1.addCell(cell20);
            // the cell object
            PdfPCell cell21;
            cell21 = new PdfPCell(new Phrase("         "));
            cell21.setBorder(0);
            tableInfo1.addCell(cell21);

            // the cell object
            PdfPCell cell222;
            cell222 = new PdfPCell(new Phrase("Date de l’engagement :"));
            cell222.setBorder(0);
            tableInfo1.addCell(cell222);
            // the cell object
            PdfPCell cell23;
            cell23 = new PdfPCell(new Phrase(contrat.getDateEngagement()));
            cell23.setBorder(0);
            tableInfo1.addCell(cell23);
            // the cell object
            PdfPCell cell24;
            cell24 = new PdfPCell(new Phrase("         "));
            cell24.setBorder(0);
            tableInfo1.addCell(cell24);

            document.add(tableInfo1);

            // A table with 4 columns
            PdfPTable tableAvantArticle1 = new PdfPTable(4);
            tableAvantArticle1.setSpacingBefore(10f);
            tableAvantArticle1.setWidthPercentage(100);
            // the cell object
            PdfPCell cell31;
            // we add a cell with colspan 4
            cell31 = new PdfPCell(new Phrase("Ci-après dénommée « L’employé »,"));
            cell31.setColspan(4);
            cell31.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell31.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            cell31.setBorder(0);
            tableAvantArticle1.addCell(cell31);
            document.add(tableAvantArticle1);

            // A table with 4 columns
            PdfPTable tableAvantArticle2 = new PdfPTable(4);
            tableAvantArticle2.setSpacingBefore(10f);
            tableAvantArticle2.setWidthPercentage(100);
            // the cell object
            PdfPCell cell32;
            // we add a cell with colspan 4
            cell32 = new PdfPCell(new Phrase("D’autre part,"));
            cell32.setColspan(4);
            cell32.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell32.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            cell32.setBorder(0);
            tableAvantArticle2.addCell(cell32);
            document.add(tableAvantArticle2);

            document.add(new Paragraph("IL  EST CONVENU CE QUI SUIT :"));

            document.add(new Paragraph("L’employé titulaire du présent contrat est engagé aux conditions ci-après. Cet engagement est régi par les textes suivants :\n"
                    + "-	Loi n° 97-17 du 1er décembre 1997 portant Code du Travail au Sénégal, ensemble des règlements d’application de cette loi ;\n"
                    + "-	Convention Collective Nationale Interprofessionnelle du Sénégal du 27 mai 1982 ; \n"
                    + "-	Convention collective du commerce ;\n"
                    + "-	ensemble des avenants et décisions qui viendraient à la compléter ou à la modifier ;\n"
                    + "-	Règlement intérieur de l’entreprise."));

            //Espace
            document.add(new Paragraph("     "));
            //document.add(new Paragraph("ENTRE LES SOUSSIGNES:"));
            Chunk article1 = new Chunk("ARTICLE 1 : OBJET");
            article1.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            document.add(article1);
            document.add(new Paragraph(contrat.getArticle1()));

            //Espace
            document.add(new Paragraph("     "));
            //document.add(new Paragraph("ENTRE LES SOUSSIGNES:"));
            Chunk article2 = new Chunk("ARTICLE 2 : DUREE");
            article2.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            document.add(article2);
            document.add(new Paragraph(contrat.getArticle2()));

            //Espace
            document.add(new Paragraph("     "));
            //document.add(new Paragraph("ENTRE LES SOUSSIGNES:"));
            Chunk article3 = new Chunk("ARTICLE 3 : FONCTIONS ET CLASSIFICATIONS");
            article3.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            document.add(article3);
            document.add(new Paragraph(contrat.getArticle3()));

            //Espace
            document.add(new Paragraph("     "));
            //document.add(new Paragraph("ENTRE LES SOUSSIGNES:"));
            Chunk article4 = new Chunk("ARTICLE  4 : OBLIGATIONS");
            article4.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            document.add(article4);
            document.add(new Paragraph(contrat.getArticle4()));

            //Espace
            document.add(new Paragraph("     "));
            //document.add(new Paragraph("ENTRE LES SOUSSIGNES:"));
            Chunk article5 = new Chunk("ARTICLE  5: MALADIE");
            article5.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            document.add(article5);
            document.add(new Paragraph(contrat.getArticle5()));

            //Espace
            document.add(new Paragraph("     "));
            //document.add(new Paragraph("ENTRE LES SOUSSIGNES:"));
            Chunk article6 = new Chunk("ARTICLE  6: LIEU DE TRAVAIL");
            article6.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            document.add(article6);
            document.add(new Paragraph(contrat.getArticle6()));

            //Espace
            document.add(new Paragraph("     "));
            //document.add(new Paragraph("ENTRE LES SOUSSIGNES:"));
            Chunk article7 = new Chunk("ARTICLE 7: TEMPS DE TRAVAIL");
            article7.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            document.add(article7);
            document.add(new Paragraph(contrat.getArticle8()));

            //Espace
            document.add(new Paragraph("     "));
            //document.add(new Paragraph("ENTRE LES SOUSSIGNES:"));
            Chunk article8 = new Chunk("ARTICLE 8 : RENUMERATION");
            article8.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            document.add(article8);
            document.add(new Paragraph(contrat.getArticle9()));

            //Espace
            document.add(new Paragraph("     "));
            //document.add(new Paragraph("ENTRE LES SOUSSIGNES:"));
            Chunk article9 = new Chunk("ARTICLE 9 : CONGES PAYES – INDEMNITE DE CONGES");
            article9.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            document.add(article9);
            document.add(new Paragraph(contrat.getArticle10()));

            //Espace
            document.add(new Paragraph("     "));
            //document.add(new Paragraph("ENTRE LES SOUSSIGNES:"));
            Chunk article10 = new Chunk("ARTCLE 10: FRAIS PROFESSIONNELS");
            article10.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            document.add(article10);
            document.add(new Paragraph(contrat.getArticle11()));

            //Espace
            document.add(new Paragraph("     "));
            //document.add(new Paragraph("ENTRE LES SOUSSIGNES:"));
            Chunk article11 = new Chunk("ARTICLE 11: PREVOYANCE RETRAITE /SECURITE SOCIALE");
            article11.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            document.add(article11);
            document.add(new Paragraph(contrat.getArticle12()));

            //Espace
            document.add(new Paragraph("     "));
            //document.add(new Paragraph("ENTRE LES SOUSSIGNES:"));
            Chunk article12 = new Chunk("ARTICLE 12 : SECRET PROFESSIONNEL");
            article12.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            document.add(article12);
            document.add(new Paragraph(contrat.getArticle13()));

            //Espace
            document.add(new Paragraph("     "));
            //document.add(new Paragraph("ENTRE LES SOUSSIGNES:"));
            Chunk article13 = new Chunk("ARTICLE 13 : CLAUSE DE NON CONCURRENCE");
            article13.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            document.add(article13);
            document.add(new Paragraph(contrat.getArticle14()));

            //Espace
            document.add(new Paragraph("     "));
            //document.add(new Paragraph("ENTRE LES SOUSSIGNES:"));
            Chunk article14 = new Chunk("ARTICLE 14 : MODIFICATION");
            article14.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            document.add(article14);
            document.add(new Paragraph(contrat.getArticle15()));

            //Espace
            document.add(new Paragraph("     "));
            //document.add(new Paragraph("ENTRE LES SOUSSIGNES:"));
            Chunk article15 = new Chunk("ARTICLE 15 : ARRIVEE DU TERME, RUPTURE");
            article15.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            document.add(article15);
            document.add(new Paragraph(contrat.getArticle16()));

            //Espace
            document.add(new Paragraph("     "));
            //document.add(new Paragraph("ENTRE LES SOUSSIGNES:"));
            Chunk v1 = new Chunk("En quatre (4) exemplaires");
            v1.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            document.add(v1);

            //Espace
            document.add(new Paragraph("     "));
            document.add(new Paragraph("Fait à Dakar, le"));

            // A table with 3 columns
            PdfPTable tableBas3 = new PdfPTable(3);
            tableBas3.setSpacingBefore(10f);
            tableBas3.setWidthPercentage(100);

            // the cell object
            //Espace
            document.add(new Paragraph("     "));
            //Espace
            document.add(new Paragraph("     "));
            PdfPCell cell35;
            Chunk vv = new Chunk("Le Travailleur");
            vv.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            cell35 = new PdfPCell(new Phrase(vv));
            cell35.setBorder(0);
            tableBas3.addCell(cell35);
            // the cell object
            PdfPCell cell36;
            cell36 = new PdfPCell(new Phrase(" "));
            cell36.setBorder(0);
            tableBas3.addCell(cell36);
            // the cell object
            PdfPCell cell37;
            Chunk vvv = new Chunk("L’Employeur");
            vvv.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            cell37 = new PdfPCell(new Phrase(vvv));
            cell37.setBorder(0);
            tableBas3.addCell(cell37);
            document.add(tableBas3);

            //Espace
            //Espace
            document.add(new Paragraph("     "));
            document.add(new Paragraph("     "));
            document.add(new Paragraph("(Précéder de la mention lu et approuvé)"));

            document.close();
            file.close();
            System.out.println("PDF Created!");

            // setting some response headers
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control",
                    "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            // setting the content type
            response.setContentType("application/pdf");
            // the contentlength
            response.setContentLength(baos.size());
            // write ByteArrayOutputStream to the ServletOutputStream
            OutputStream os = response.getOutputStream();
            baos.writeTo(os);
            os.flush();
            os.close();

            context.responseComplete();

        } catch (Exception e) {
        }
    }

    public void generateBulletinSalaire(Bulletin bulletin) {

        File directory = new File("./");
        String urlAbsolut = directory.getAbsolutePath();
        String url = "";

        try {

            String recuname = "Bulletin_de_salaire.pdf";
            url = urlAbsolut + recuname;

            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "inline=filename=file.pdf");

            Document document = new Document();
            document.setPageSize(PageSize.A4);
            OutputStream file = new FileOutputStream(new File(url));
            // step 2
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            PdfWriter.getInstance(document, file);
            // step 3
            document.open();

            Font boldFontTitle = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
            Font boldFontTitleTable = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
            Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD);
            Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
            Font normalFontEmail = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLUE);
            Font boldFontTitleExp1 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.RED);
            Font boldFontTitleExp2 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);

            String img = entetePath.getUrlAccel();

            Image logoProjet = Image.getInstance(img);
            logoProjet.scaleAbsolute(60f, 40f);
            logoProjet.setAlignment(Image.ALIGN_LEFT);
            document.add(logoProjet);

            //Espace
            // Creating text object       
            /*Text text1 = new Text("ACCEL");
             Text text2 = new Text("ACCEL TECHNOLOGIES");
            
             Font boldFontTitleSpecial1 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
             boldFontTitleSpecial1.setColor(BaseColor.RED);*/
            Chunk c1 = new Chunk("ACCEL ", boldFontTitleExp1);
            Chunk c2 = new Chunk("TECHNOLOGIES", boldFontTitleExp2);
            Phrase p1 = new Phrase();
            p1.add(c1);
            p1.add(c2);
            Paragraph p = new Paragraph();
            p.add(p1);
            document.add(p);
            //Espace
            document.add(new Paragraph("4 Route de l’aéroport", normalFont));
            //Espace
            document.add(new Paragraph("Dakar, Sénégal", normalFont));

            // A table1 with 4 columns
            PdfPTable table1 = new PdfPTable(4);
            table1.setSpacingBefore(2f);
            table1.setWidthPercentage(100);
            PdfPCell cell1 = new PdfPCell();
            Paragraph paragraph1 = new Paragraph("Tél : 33 820 83 83", normalFont);

            Chunk underlineEmail = new Chunk("Email : info@accel-tech.net", normalFontEmail);
            underlineEmail.setUnderline(0.1f, -2f);
            Paragraph paragraph2 = new Paragraph(underlineEmail);

            cell1.addElement(paragraph1);
            cell1.addElement(paragraph2);
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell1.setVerticalAlignment(Element.ALIGN_LEFT);
            cell1.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell1);

            PdfPCell cell22;
            cell22 = new PdfPCell(new Phrase(" "));
            cell22.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell22);

            PdfPCell cell33;
            cell33 = new PdfPCell(new Phrase(" "));
            cell33.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell33);

            // the cell object
            PdfPCell cell4 = new PdfPCell();
            Paragraph paragraph4 = new Paragraph("", boldFont);
            Paragraph paragraph5 = new Paragraph("", boldFont);
            cell4.addElement(paragraph4);
            cell4.addElement(paragraph5);
            cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell4.setVerticalAlignment(Element.ALIGN_LEFT);
            cell4.setBorder(Rectangle.BOX);
            table1.addCell(cell4);

            // Defiles the relative width of the columns
            float[] columnWidths = new float[]{20f, 10f, 20f, 30f};
            table1.setWidths(columnWidths);
            document.add(table1);

            //Espace
            document.add(new Paragraph("  "));

            // A table1 with 4 columns
            PdfPTable table22 = new PdfPTable(4);
            table22.setSpacingBefore(10f);
            table22.setWidthPercentage(100);
            // the cell object
            PdfPCell cell5;
            // we add a cell with colspan 4
            cell5 = new PdfPCell(new Phrase("", boldFontTitle));
            cell5.setColspan(4);

            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
            //border
            cell5.setBorder(0);
            table22.addCell(cell5);
            document.add(table22);

            // A table with 4 columns
            PdfPTable tableCDD = new PdfPTable(4);
            tableCDD.setSpacingBefore(10f);
            tableCDD.setWidthPercentage(100);
            // the cell object
            PdfPCell cell1B;
            // we add a cell with colspan 4
            cell1B = new PdfPCell(new Phrase("BULLETIN DE PAIE"));
            cell1B.setColspan(4);
            // background color
            cell1B.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell1B.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1B.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            cell1B.setBorder(1);
            tableCDD.addCell(cell1B);
            document.add(tableCDD);

            //Espace
            document.add(new Paragraph("     "));

            //Espace
            document.add(new Paragraph("     "));

            //Espace
            document.add(new Paragraph("     "));

            // A table1 with 4 columns
            PdfPTable table2 = new PdfPTable(4);
            table2.setSpacingBefore(10f);
            table2.setWidthPercentage(100);
            // the cell object
            PdfPCell cell2;
            // we add a cell with colspan 4
            cell2 = new PdfPCell(new Phrase("Période : " + bulletin.getPeriode()));
            cell2.setColspan(4);

            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            cell2.setBorder(0);
            table2.addCell(cell2);
            document.add(table2);

            //Espace
            document.add(new Paragraph("     "));

            // A table1 with 4 columns
            PdfPTable table4 = new PdfPTable(4);
            table4.setSpacingBefore(10f);
            table4.setWidthPercentage(100);
            // the cell object
            PdfPCell cell4Salaire;
            // we add a cell with colspan 4
            cell4Salaire = new PdfPCell(new Phrase(" Nom: " + bulletin.getNom() + "\n\n Situation matrimoniale: " + bulletin.getSituation() + " \n\n  Nombre d'enfant \n\n Catégorie \n\n PARTS IR: " + bulletin.getPartir() + " \n"));
            cell4Salaire.setColspan(4);

            //cell4.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell4Salaire.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell4Salaire.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            //cell4.setBorder(0);
            table4.addCell(cell4Salaire);
            document.add(table4);

            //Espace
            document.add(new Paragraph("     "));

            // A table1 with 9 columns
            PdfPTable table5 = new PdfPTable(9);
            table5.setSpacingBefore(10f);
            table5.setWidthPercentage(100);

            PdfPCell cell5Salaire;
            cell5Salaire = new PdfPCell(new Phrase("Désignation"));
            cell5Salaire.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell5Salaire.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell5Salaire);

            PdfPCell cell6;
            cell6 = new PdfPCell(new Phrase("Nombre"));
            cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell6);

            PdfPCell cell7;
            cell7 = new PdfPCell(new Phrase("Base"));
            cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell7);

            PdfPCell cell8;
            cell8 = new PdfPCell(new Phrase("Part salariale"));
            cell8.setColspan(3);
            cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell8.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell8);

            PdfPCell cell9;
            cell9 = new PdfPCell(new Phrase("Part patronnale"));
            cell9.setColspan(3);
            cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell9.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell9);

            PdfPCell cell10;
            cell10 = new PdfPCell(new Phrase(" "));
            cell10.setColspan(3);
            cell10.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell10.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell10);

            PdfPCell cell11;
            cell11 = new PdfPCell(new Phrase("Taux"));
            cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell11.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell11);

            PdfPCell cell12;
            cell12 = new PdfPCell(new Phrase("Gain"));
            cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell12.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell12);

            PdfPCell cell13;
            cell13 = new PdfPCell(new Phrase("Retenue"));
            cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell13.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell13);

            PdfPCell cell14;
            cell14 = new PdfPCell(new Phrase("Taux"));
            cell14.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell14.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell14);

            PdfPCell cell15;
            cell15 = new PdfPCell(new Phrase("Retenue"));
            cell15.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell15.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell15);

            PdfPCell cell16;
            cell16 = new PdfPCell(new Phrase(" "));
            cell16.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell16.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell16);

            /*Ligne 1*/
            PdfPCell cell17;
            cell17 = new PdfPCell(new Phrase("Salaire de base categoriel"));
            cell17.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell17.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell17);

            PdfPCell cell18;
            cell18 = new PdfPCell(new Phrase("30"));
            cell18.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell18.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell18);

            PdfPCell cell19;
            cell19 = new PdfPCell(new Phrase(bulletin.getSalbase()));
            cell19.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell19.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell19);

            PdfPCell cell20;
            cell20 = new PdfPCell(new Phrase(" "));
            cell20.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell20.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell20);

            PdfPCell cell21;
            cell21 = new PdfPCell(new Phrase(" "));
            cell21.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell21.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell21);

            PdfPCell cell22Salaire;
            cell22Salaire = new PdfPCell(new Phrase(" "));
            cell22Salaire.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell22Salaire.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell22Salaire);

            PdfPCell cell23;
            cell23 = new PdfPCell(new Phrase(" "));
            cell23.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell23.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell23);

            PdfPCell cell24;
            cell24 = new PdfPCell(new Phrase(" "));
            cell24.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell24.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell24);

            PdfPCell cell25;
            cell25 = new PdfPCell(new Phrase(" "));
            cell25.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell25.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell25);
            /*Fin ligne 1*/

            /*Ligne 2*/
            PdfPCell cell26;
            cell26 = new PdfPCell(new Phrase("Sursalaire"));
            cell26.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell26.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell26);

            PdfPCell cell27;
            cell27 = new PdfPCell(new Phrase("1"));
            cell27.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell27.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell27);

            PdfPCell cell28;
            cell28 = new PdfPCell(new Phrase(bulletin.getSursalaire()));
            cell28.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell28.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell28);

            PdfPCell cell29;
            cell29 = new PdfPCell(new Phrase(" "));
            cell29.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell29.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell29);

            PdfPCell cell30;
            cell30 = new PdfPCell(new Phrase(" "));
            cell30.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell30.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell30);

            PdfPCell cell31;
            cell31 = new PdfPCell(new Phrase(" "));
            cell31.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell31.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell31);

            PdfPCell cell32;
            cell32 = new PdfPCell(new Phrase(" "));
            cell32.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell32.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell32);

            PdfPCell cell33Salaire;
            cell33Salaire = new PdfPCell(new Phrase(" "));
            cell33Salaire.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell33Salaire.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell33Salaire);

            PdfPCell cell34;
            cell34 = new PdfPCell(new Phrase(" "));
            cell34.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell34.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell34);
            /*Fin ligne 2*/

            /*Ligne 3*/
            PdfPCell cell35;
            cell35 = new PdfPCell(new Phrase("Ancienneté"));
            cell35.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell35.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell35);

            PdfPCell cell36;
            cell36 = new PdfPCell(new Phrase(" "));
            cell36.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell36.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell36);

            PdfPCell cell37;
            cell37 = new PdfPCell(new Phrase(" "));
            cell37.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell37.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell37);

            PdfPCell cell38;
            cell38 = new PdfPCell(new Phrase(" "));
            cell38.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell38.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell38);

            PdfPCell cell39;
            cell39 = new PdfPCell(new Phrase(" "));
            cell39.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell39.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell39);

            PdfPCell cell40;
            cell40 = new PdfPCell(new Phrase(" "));
            cell40.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell40.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell40);

            PdfPCell cell41;
            cell41 = new PdfPCell(new Phrase(" "));
            cell41.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell41.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell41);

            PdfPCell cell42;
            cell42 = new PdfPCell(new Phrase(" "));
            cell42.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell42.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell42);

            PdfPCell cell43;
            cell43 = new PdfPCell(new Phrase(" "));
            cell43.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell43.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell43);
            /*Fin ligne 3*/

            /*Ligne 4*/
            PdfPCell cell44;
            cell44 = new PdfPCell(new Phrase("Total brut"));
            cell44.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell44.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell44);

            PdfPCell cell45;
            cell45 = new PdfPCell(new Phrase(" "));
            cell45.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell45.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell45);

            PdfPCell cell46;
            cell46 = new PdfPCell(new Phrase(bulletin.getTotalbrut()));
            cell46.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell46.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell46);

            PdfPCell cell47;
            cell47 = new PdfPCell(new Phrase(" "));
            cell47.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell47.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell47);

            PdfPCell cell48;
            cell48 = new PdfPCell(new Phrase(" "));
            cell48.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell48.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell48);

            PdfPCell cell49;
            cell49 = new PdfPCell(new Phrase(" "));
            cell49.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell49.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell49);

            PdfPCell cell50;
            cell50 = new PdfPCell(new Phrase(" "));
            cell50.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell50.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell50);

            PdfPCell cell51;
            cell51 = new PdfPCell(new Phrase(" "));
            cell51.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell51.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell51);

            PdfPCell cell52;
            cell52 = new PdfPCell(new Phrase(" "));
            cell52.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell52.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell52);
            /*Fin ligne 4*/

            /*Ligne 5*/
            PdfPCell cell53;
            cell53 = new PdfPCell(new Phrase("IPRES RG"));
            cell53.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell53.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell53);

            PdfPCell cell54;
            cell54 = new PdfPCell(new Phrase(" "));
            cell54.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell54.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell54);

            PdfPCell cell55;
            cell55 = new PdfPCell(new Phrase("360 000"));
            cell55.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell55.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell55);

            PdfPCell cell56;
            cell56 = new PdfPCell(new Phrase("5,6"));
            cell56.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell56.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell56);

            PdfPCell cell57;
            cell57 = new PdfPCell(new Phrase(" "));
            cell57.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell57.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell57);

            PdfPCell cell58;
            cell58 = new PdfPCell(new Phrase("20 160"));
            cell58.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell58.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell58);

            PdfPCell cell59;
            cell59 = new PdfPCell(new Phrase("8,4"));
            cell59.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell59.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell59);

            PdfPCell cell60;
            cell60 = new PdfPCell(new Phrase("30 240"));
            cell60.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell60.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell60);

            PdfPCell cell61;
            cell61 = new PdfPCell(new Phrase(" "));
            cell61.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell61.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell61);
            /*Fin ligne 5*/

            /* Mileu*/
            /*Ligne 6*/
            PdfPCell cell62;
            cell62 = new PdfPCell(new Phrase("IPRES RC"));
            cell62.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell62.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell62);

            PdfPCell cell63;
            cell63 = new PdfPCell(new Phrase(" "));
            cell63.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell63.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell63);

            PdfPCell cell64;
            cell64 = new PdfPCell(new Phrase(bulletin.getTotalbrut()));
            cell64.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell64.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell64);

            PdfPCell cell65;
            cell65 = new PdfPCell(new Phrase("2,4"));
            cell65.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell65.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell65);

            PdfPCell cell66;
            cell66 = new PdfPCell(new Phrase(" "));
            cell66.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell66.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell66);

            PdfPCell cell67;
            cell67 = new PdfPCell(new Phrase(bulletin.getIpresrc()));
            cell67.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell67.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell67);

            PdfPCell cell68;
            cell68 = new PdfPCell(new Phrase("3,6"));
            cell68.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell68.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell68);

            PdfPCell cell69;
            cell69 = new PdfPCell(new Phrase(bulletin.getIpresrcc()));
            cell69.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell69.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell69);

            PdfPCell cell70;
            cell70 = new PdfPCell(new Phrase(""));
            cell70.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell70.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell70);
            /*Fin ligne 6*/

            /*Ligne 7*/
            PdfPCell cell71;
            cell71 = new PdfPCell(new Phrase("CSS Allocation familiales"));
            cell71.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell71.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell71);

            PdfPCell cell72;
            cell72 = new PdfPCell(new Phrase("  "));
            cell72.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell72.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell72);

            PdfPCell cell73;
            cell73 = new PdfPCell(new Phrase("63 000"));
            cell73.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell73.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell73);

            PdfPCell cell74;
            cell74 = new PdfPCell(new Phrase(" "));
            cell74.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell74.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell74);

            PdfPCell cell75;
            cell75 = new PdfPCell(new Phrase(" "));
            cell75.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell75.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell75);

            PdfPCell cell76;
            cell76 = new PdfPCell(new Phrase(" "));
            cell76.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell76.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell76);

            PdfPCell cell77;
            cell77 = new PdfPCell(new Phrase("7"));
            cell77.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell77.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell77);

            PdfPCell cell78;
            cell78 = new PdfPCell(new Phrase("4 410"));
            cell78.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell78.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell78);

            PdfPCell cell79;
            cell79 = new PdfPCell(new Phrase(" "));
            cell79.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell79.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell79);
            /*Fin ligne 7*/

            /*Ligne 8*/
            PdfPCell cell80;
            cell80 = new PdfPCell(new Phrase("CSS Accident de travail"));
            cell80.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell80.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell80);

            PdfPCell cell81;
            cell81 = new PdfPCell(new Phrase(" "));
            cell81.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell81.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell81);

            PdfPCell cell82;
            cell82 = new PdfPCell(new Phrase("63 000"));
            cell82.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell82.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell82);

            PdfPCell cell83;
            cell83 = new PdfPCell(new Phrase(" "));
            cell83.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell83.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell83);

            PdfPCell cell84;
            cell84 = new PdfPCell(new Phrase(" "));
            cell84.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell84.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell84);

            PdfPCell cell85;
            cell85 = new PdfPCell(new Phrase(" "));
            cell85.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell85.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell85);

            PdfPCell cell86;
            cell86 = new PdfPCell(new Phrase("1"));
            cell86.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell86.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell86);

            PdfPCell cell87;
            cell87 = new PdfPCell(new Phrase("630"));
            cell87.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell87.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell87);

            PdfPCell cell88;
            cell88 = new PdfPCell(new Phrase(" "));
            cell88.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell88.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell88);
            /*Fin ligne 8*/

            /*Ligne 9*/
            PdfPCell cell89;
            cell89 = new PdfPCell(new Phrase("CFCE"));
            cell89.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell89.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell89);

            PdfPCell cell90;
            cell90 = new PdfPCell(new Phrase(" "));
            cell90.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell90.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell90);

            PdfPCell cell91;
            cell91 = new PdfPCell(new Phrase(bulletin.getTotalbrut()));
            cell91.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell91.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell91);

            PdfPCell cell92;
            cell92 = new PdfPCell(new Phrase(" "));
            cell92.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell92.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell92);

            PdfPCell cell93;
            cell93 = new PdfPCell(new Phrase(" "));
            cell93.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell93.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell93);

            PdfPCell cell94;
            cell94 = new PdfPCell(new Phrase(" "));
            cell94.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell94.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell94);

            PdfPCell cell95;
            cell95 = new PdfPCell(new Phrase("3"));
            cell95.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell95.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell95);

            PdfPCell cell96;
            cell96 = new PdfPCell(new Phrase(bulletin.getCfcee()));
            cell96.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell96.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell96);

            PdfPCell cell97;
            cell97 = new PdfPCell(new Phrase("  "));
            cell97.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell97.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell97);
            /*Fin ligne 9*/

            /*Ligne 10*/
            PdfPCell cell98;
            cell98 = new PdfPCell(new Phrase("Assurance maladie"));
            cell98.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell98.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell98);

            PdfPCell cell99;
            cell99 = new PdfPCell(new Phrase(" "));
            cell99.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell99.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell99);

            PdfPCell cell100;
            cell100 = new PdfPCell(new Phrase(" "));
            cell100.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell100.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell100);

            PdfPCell cell101;
            cell101 = new PdfPCell(new Phrase(" "));
            cell101.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell101.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell101);

            PdfPCell cell102;
            cell102 = new PdfPCell(new Phrase(" "));
            cell102.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell102.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell102);

            PdfPCell cell103;
            cell103 = new PdfPCell(new Phrase(" "));
            cell103.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell103.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell103);

            PdfPCell cell104;
            cell104 = new PdfPCell(new Phrase(" "));
            cell104.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell104.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell104);

            PdfPCell cell105;
            cell105 = new PdfPCell(new Phrase(" "));
            cell105.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell105.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell105);

            PdfPCell cell106;
            cell106 = new PdfPCell(new Phrase(" "));
            cell106.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell106.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell106);
            /*Fin ligne 10*/

            /*Ligne 11*/
            PdfPCell cell107;
            cell107 = new PdfPCell(new Phrase("Retenue IR"));
            cell107.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell107.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell107);

            PdfPCell cell108;
            cell108 = new PdfPCell(new Phrase(" "));
            cell108.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell108.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell108);

            PdfPCell cell109;
            cell109 = new PdfPCell(new Phrase(" "));
            cell109.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell109.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell109);

            PdfPCell cell110;
            cell110 = new PdfPCell(new Phrase(" "));
            cell110.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell110.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell110);

            PdfPCell cell111;
            cell111 = new PdfPCell(new Phrase(" "));
            cell111.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell111.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell111);

            PdfPCell cell112;
            cell112 = new PdfPCell(new Phrase("169 462"));
            cell112.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell112.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell112);

            PdfPCell cell113;
            cell113 = new PdfPCell(new Phrase(" "));
            cell113.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell113.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell113);

            PdfPCell cell114;
            cell114 = new PdfPCell(new Phrase(" "));
            cell114.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell114.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell114);

            PdfPCell cell115;
            cell115 = new PdfPCell(new Phrase(" "));
            cell115.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell115.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell115);
            /*Fin ligne 11*/

            /*Ligne 12*/
            PdfPCell cell116;
            cell116 = new PdfPCell(new Phrase("Retenue TRIMF"));
            cell116.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell116.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell116);

            PdfPCell cell117;
            cell117 = new PdfPCell(new Phrase(" "));
            cell117.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell117.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell117);

            PdfPCell cell118;
            cell118 = new PdfPCell(new Phrase(" "));
            cell118.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell118.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell118);

            PdfPCell cell119;
            cell119 = new PdfPCell(new Phrase(" "));
            cell119.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell119.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell119);

            PdfPCell cell120;
            cell120 = new PdfPCell(new Phrase(" "));
            cell120.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell120.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell120);

            PdfPCell cell121;
            cell121 = new PdfPCell(new Phrase("3 000"));
            cell121.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell121.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell121);

            PdfPCell cell122;
            cell122 = new PdfPCell(new Phrase(" "));
            cell122.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell122.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell122);

            PdfPCell cell123;
            cell123 = new PdfPCell(new Phrase(" "));
            cell123.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell123.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell123);

            PdfPCell cell124;
            cell124 = new PdfPCell(new Phrase(" "));
            cell124.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell124.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell124);
            /*Fin ligne 12*/

            /*Ligne 13*/
            PdfPCell cell125;
            cell125 = new PdfPCell(new Phrase("Prime de transport"));
            cell125.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell125.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell125);

            PdfPCell cell126;
            cell126 = new PdfPCell(new Phrase(" "));
            cell126.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell126.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell126);

            PdfPCell cell127;
            cell127 = new PdfPCell(new Phrase(" "));
            cell127.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell127.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell127);

            PdfPCell cell128;
            cell128 = new PdfPCell(new Phrase(" "));
            cell128.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell128.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell128);

            PdfPCell cell129;
            cell129 = new PdfPCell(new Phrase("20 800"));
            cell129.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell129.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell129);

            PdfPCell cell130;
            cell130 = new PdfPCell(new Phrase(" "));
            cell130.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell130.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell130);

            PdfPCell cell140;
            cell140 = new PdfPCell(new Phrase(" "));
            cell140.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell140.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell140);

            PdfPCell cell141;
            cell141 = new PdfPCell(new Phrase(" "));
            cell141.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell141.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell141);

            PdfPCell cell142;
            cell142 = new PdfPCell(new Phrase(" "));
            cell142.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell142.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell142);
            /*Fin ligne 13*/

            if (bulletin.getPret() == true) {
                /*Ligne 13*/
                PdfPCell cellA;
                cellA = new PdfPCell(new Phrase("Pret"));
                cellA.setHorizontalAlignment(Element.ALIGN_LEFT);
                cellA.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table5.addCell(cellA);

                PdfPCell cellB;
                cellB = new PdfPCell(new Phrase(" "));
                cellB.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellB.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table5.addCell(cellB);

                PdfPCell cellC;
                cellC = new PdfPCell(new Phrase(" "));
                cellC.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellC.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table5.addCell(cellC);

                PdfPCell cellD;
                cellD = new PdfPCell(new Phrase(" "));
                cellD.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellD.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table5.addCell(cellD);

                PdfPCell cellE;
                cellE = new PdfPCell(new Phrase(" "));
                cellE.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellE.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table5.addCell(cellE);

                PdfPCell cellF;
                cellF = new PdfPCell(new Phrase(bulletin.getMtPret()));
                cellF.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellF.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table5.addCell(cellF);

                PdfPCell cellG;
                cellG = new PdfPCell(new Phrase(" "));
                cellG.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellG.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table5.addCell(cellG);

                PdfPCell cellH;
                cellH = new PdfPCell(new Phrase(" "));
                cellH.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellH.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table5.addCell(cellH);

                PdfPCell cellI;
                cellI = new PdfPCell(new Phrase(" "));
                cellI.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellI.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table5.addCell(cellI);
                /*Fin ligne 13*/
            }

            document.add(table5);

            // A table1 with 9 columns
            PdfPTable table6 = new PdfPTable(9);
            table6.setSpacingBefore(10f);
            table6.setWidthPercentage(100);

            /*Ligne 14*/
            PdfPCell cell143;
            cell143 = new PdfPCell(new Phrase("RECAPITULATIF"));
            cell143.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell143.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table6.addCell(cell143);

            PdfPCell cell144;
            cell144 = new PdfPCell(new Phrase("SALAIRE BRUT SOCIAL"));
            cell144.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell144.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table6.addCell(cell144);

            PdfPCell cell145;
            cell145 = new PdfPCell(new Phrase("AVANTAGE EN NATURE"));
            cell145.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell145.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table6.addCell(cell145);

            PdfPCell cell146;
            cell146 = new PdfPCell(new Phrase("SALAIRE BRUT FISCAL"));
            cell146.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell146.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table6.addCell(cell146);

            PdfPCell cell147;
            cell147 = new PdfPCell(new Phrase("IR"));
            cell147.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell147.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table6.addCell(cell147);

            PdfPCell cell148;
            cell148 = new PdfPCell(new Phrase("TRIMF"));
            cell148.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell148.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table6.addCell(cell148);

            PdfPCell cell149;
            cell149 = new PdfPCell(new Phrase("IPRES RG"));
            cell149.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell149.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table6.addCell(cell149);

            PdfPCell cell150;
            cell150 = new PdfPCell(new Phrase("IPRES CADRE"));
            cell150.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell150.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table6.addCell(cell150);

            PdfPCell cell151;
            cell151 = new PdfPCell(new Phrase("NET A PAYER"));
            cell151.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell151.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table6.addCell(cell151);
            /*Fin ligne 14*/

            /*Ligne 15*/
            PdfPCell cell152;
            cell152 = new PdfPCell(new Phrase("PERIODE"));
            cell152.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell152.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table6.addCell(cell152);

            PdfPCell cell153;
            cell153 = new PdfPCell(new Phrase(bulletin.getTotalbrut()));
            cell153.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell153.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table6.addCell(cell153);

            PdfPCell cell154;
            cell154 = new PdfPCell(new Phrase("-"));
            cell154.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell154.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table6.addCell(cell154);

            PdfPCell cell155;
            cell155 = new PdfPCell(new Phrase(bulletin.getTotalbrut()));
            cell155.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell155.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table6.addCell(cell155);

            PdfPCell cell156;
            cell156 = new PdfPCell(new Phrase("169 462"));
            cell156.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell156.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table6.addCell(cell156);

            PdfPCell cell157;
            cell157 = new PdfPCell(new Phrase("3 000"));
            cell157.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell157.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table6.addCell(cell157);

            PdfPCell cell158;
            cell158 = new PdfPCell(new Phrase("20 160"));
            cell158.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell158.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table6.addCell(cell158);

            PdfPCell cell159;
            cell159 = new PdfPCell(new Phrase(bulletin.getIpresrc()));
            cell159.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell159.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table6.addCell(cell159);

            PdfPCell cell160;
            cell160 = new PdfPCell(new Phrase(bulletin.getNet()));
            cell160.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell160.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table6.addCell(cell160);
            /*Fin ligne 15*/

            document.add(table6);

            // A table with 3 columns
            PdfPTable tableBas3 = new PdfPTable(3);
            tableBas3.setSpacingBefore(10f);
            tableBas3.setWidthPercentage(100);

            // the cell object
            PdfPCell cell161;
            cell161 = new PdfPCell(new Phrase("Signature employée"));
            cell161.setBorder(0);
            tableBas3.addCell(cell161);
            // the cell object
            PdfPCell cell162;
            cell162 = new PdfPCell(new Phrase(""));
            cell162.setBorder(0);
            tableBas3.addCell(cell162);
            // the cell object
            PdfPCell cell163;
            cell163 = new PdfPCell(new Phrase("Signture Employeur"));
            cell163.setBorder(0);
            tableBas3.addCell(cell163);

            document.add(tableBas3);

            // POUR POUVOIR GENERER LE DOCUMENT
            document.close();
            file.close();
            System.out.println("PDF Created!");

            // setting some response headers
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control",
                    "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            // setting the content type
            response.setContentType("application/pdf");
            // the contentlength
            response.setContentLength(baos.size());
            // write ByteArrayOutputStream to the ServletOutputStream
            OutputStream os = response.getOutputStream();
            baos.writeTo(os);
            os.flush();
            os.close();

            context.responseComplete();

        } catch (Exception e) {
        }

    }

}
