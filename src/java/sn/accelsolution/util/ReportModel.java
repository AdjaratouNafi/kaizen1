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
public class ReportModel implements Serializable {

    private EntetePath entetePath;

    public ReportModel() {
        entetePath = new EntetePath();
    }

    public EntetePath getEntetePath() {
        return entetePath;
    }

    public void setEntetePath(EntetePath entetePath) {
        this.entetePath = entetePath;
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
            float[] columnWidthsTab = new float[]{125f, 120f, 30f, 25f, 100f, 100f};
            table4.setWidths(columnWidthsTab);
            table4.setWidthPercentage(100);

            PdfPCell cellTab1 = new PdfPCell(new Phrase("Désignation", boldFontTitleTable));
            cellTab1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTab1.setPaddingBottom(5);
            table4.addCell(cellTab1);
            PdfPCell cellTab2 = new PdfPCell(new Phrase("Ref", boldFontTitleTable));
            cellTab2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTab2.setPaddingBottom(5);
            table4.addCell(cellTab2);
            PdfPCell cellTab3 = new PdfPCell(new Phrase("U.M", boldFontTitleTable));
            cellTab3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTab3.setPaddingBottom(5);
            table4.addCell(cellTab3);
            PdfPCell cellTab4 = new PdfPCell(new Phrase("Qté", boldFontTitleTable));
            cellTab4.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTab4.setPaddingBottom(5);
            table4.addCell(cellTab4);
            PdfPCell cellTab5 = new PdfPCell(new Phrase("P.U", boldFontTitleTable));
            cellTab5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTab5.setPaddingBottom(5);
            table4.addCell(cellTab5);
            PdfPCell cellTab6 = new PdfPCell(new Phrase("P.Total", boldFontTitleTable));
            cellTab6.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTab6.setPaddingBottom(5);
            table4.addCell(cellTab6);
            table4.completeRow();

            for (FactureUtil f : allFactureUtils) {

                PdfPCell cellInterTab1 = new PdfPCell(new Phrase(f.getDesignation(), normalFont));
                cellInterTab1.setPaddingBottom(5);
                cellInterTab1.setHorizontalAlignment(Element.ALIGN_LEFT);
                table4.addCell(cellInterTab1);

                PdfPCell cellInterTab2 = new PdfPCell(new Phrase(f.getRef(), normalFont));
                cellInterTab2.setHorizontalAlignment(Element.ALIGN_LEFT);
                table4.addCell(cellInterTab2);

                PdfPCell cellInterTab3 = new PdfPCell(new Phrase(f.getUnite(), normalFont));
                cellInterTab3.setHorizontalAlignment(Element.ALIGN_CENTER);
                table4.addCell(cellInterTab3);

                String qt = String.valueOf(f.getQuantite());

                PdfPCell cellInterTab4 = new PdfPCell(new Phrase(qt, normalFont));
                cellInterTab4.setHorizontalAlignment(Element.ALIGN_CENTER);
                table4.addCell(cellInterTab4);

                PdfPCell cellInterTab5 = new PdfPCell(new Phrase(f.getPu(), normalFont));
                cellInterTab5.setHorizontalAlignment(Element.ALIGN_CENTER);
                table4.addCell(cellInterTab5);

                PdfPCell cellInterTab6 = new PdfPCell(new Phrase(f.getPrixtotal(), normalFont));
                cellInterTab6.setHorizontalAlignment(Element.ALIGN_CENTER);
                table4.addCell(cellInterTab6);

            }

            table4.completeRow();

            document.add(table4);

            //Espace
            //document.add(new Paragraph("     "));
            PdfPTable table5 = new PdfPTable(6);
            table5.setSpacingBefore(10f);
            float[] columnWidthsTabP = new float[]{125f, 125f, 25f, 25f, 100f, 100f};
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
            cellTabBas3 = new PdfPCell(new Phrase(totalHT, normalFont));
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
            cellTabBas6 = new PdfPCell(new Phrase(montantTVA, normalFont));
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
            cellTabBas9 = new PdfPCell(new Phrase(totalTTC, normalFont));
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
                document.add(new Paragraph(devis.getMontantlettre() + " " + devis.getIdDevise().getSymbole(), normalFontCG));
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

    public void GenererModel() throws DocumentException {

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

            /*Document document = new Document();
            document.setPageSize(PageSize.A4);*/
            Document document = new Document(PageSize.A4, 50, 50, 90, 50);
            OutputStream file = new FileOutputStream(new File(url));
            Devis devis = new Devis();
            HeaderFooterPageEvent event = new HeaderFooterPageEvent(devis);
            // step 2
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos).setPageEvent(event);
            PdfWriter.getInstance(document, file).setPageEvent(event);

            //PdfWriter.getInstance(document, file).setPageEvent(event);
//            Document document = new Document(PageSize.A4, 50, 50, 90, 50);
//            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\wilfried\\Desktop\\ItextPdf\\HeaderFooter.pdf"));
//
//            // add header and footer
//            HeaderFooterPageEvent event = new HeaderFooterPageEvent();
//            writer.setPageEvent(event);
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
            Paragraph paragraph4 = new Paragraph(" CLIENT", boldFont);
            Paragraph paragraph5 = new Paragraph(" DP WORLD", boldFont);
            Paragraph paragraphBs1 = new Paragraph(" Zone Nord - Port Autonome de Dakar", boldFont);
            Paragraph paragraphBs2 = new Paragraph(" 33 889 0835", boldFont);
            Paragraph paragraphBs3 = new Paragraph(" Dkr.Purchasing@dpworld.com", boldFont);

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
            table1.addCell(cell4);

            // Defiles the relative width of the columns
            float[] columnWidths = new float[]{20f, 10f, 20f, 30f};
            table1.setWidths(columnWidths);
            document.add(table1);

            //Espace
            document.add(new Paragraph("  "));

            //Content PDF
            // A table1 with 4 columns
            PdfPTable table2 = new PdfPTable(4);
            table2.setSpacingBefore(10f);
            table2.setWidthPercentage(100);
            // the cell object
            PdfPCell cell5 = new PdfPCell(new Phrase("PROFORMA", boldFont));

            cell5.setColspan(4);
            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell5.setBorder(0);
            table2.addCell(cell5);
            document.add(table2);

            // A table1 with 4 columns
            PdfPTable table3 = new PdfPTable(4);
            table3.setSpacingBefore(10f);
            table3.setWidthPercentage(100);
            PdfPCell cell6 = new PdfPCell(new Phrase("DATE : 20/01/2020", boldFont));
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

            PdfPCell cell9 = new PdfPCell(new Phrase("DEVIS N°:  D25112019/215", boldFont));
            cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell9.setVerticalAlignment(Element.ALIGN_LEFT);
            cell9.setBorder(Rectangle.NO_BORDER);
            table3.addCell(cell9);

            // Defiles the relative width of the columns
            float[] columnWidthss = new float[]{20f, 10f, 20f, 30f};
            table3.setWidths(columnWidthss);
            document.add(table3);

            PdfPTable tableDevis = new PdfPTable(2);
            tableDevis.setSpacingBefore(10f);
            tableDevis.setWidthPercentage(100);
            PdfPCell cell10 = new PdfPCell(new Phrase("REFERENCE:  RADIO COMMUNICATION SOLUTION", boldFont));
            cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell10.setVerticalAlignment(Element.ALIGN_LEFT);
            cell10.setBorder(Rectangle.NO_BORDER);
            tableDevis.addCell(cell10);

            PdfPCell cell1P = new PdfPCell(new Phrase("Devise:  FCFA", boldFont));
            cell1P.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell1P.setVerticalAlignment(Element.ALIGN_RIGHT);
            cell1P.setBorder(Rectangle.NO_BORDER);
            tableDevis.addCell(cell1P);

            document.add(tableDevis);


            /*Debut tableau item*/
            PdfPTable table4 = new PdfPTable(6);
            table4.setSpacingBefore(10f);
            // Defiles the relative width of the columns
            float[] columnWidthsTab = new float[]{125f, 125f, 25f, 25f, 100f, 100f};
            table4.setWidths(columnWidthsTab);
            table4.setWidthPercentage(100);

            PdfPCell cellTab1 = new PdfPCell(new Phrase("Désignation", boldFontTitleTable));
            cellTab1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTab1.setPaddingBottom(5);
            table4.addCell(cellTab1);
            PdfPCell cellTab2 = new PdfPCell(new Phrase("Ref", boldFontTitleTable));
            cellTab2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTab2.setPaddingBottom(5);
            table4.addCell(cellTab2);
            PdfPCell cellTab3 = new PdfPCell(new Phrase("U.M", boldFontTitleTable));
            cellTab3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTab3.setPaddingBottom(5);
            table4.addCell(cellTab3);
            PdfPCell cellTab4 = new PdfPCell(new Phrase("Qte", boldFontTitleTable));
            cellTab4.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTab4.setPaddingBottom(5);
            table4.addCell(cellTab4);
            PdfPCell cellTab5 = new PdfPCell(new Phrase("P.U", boldFontTitleTable));
            cellTab5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTab5.setPaddingBottom(5);
            table4.addCell(cellTab5);
            PdfPCell cellTab6 = new PdfPCell(new Phrase("P.Total", boldFontTitleTable));
            cellTab6.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTab6.setPaddingBottom(5);
            table4.addCell(cellTab6);
            table4.completeRow();

            /*for (int i = 0; i < 2; i++) {
            
            PdfPCell cellInterTab1 = new PdfPCell(new Phrase("RADIO MOTOROLA DP4401E UHF 403-527 GNSBT 4WNK", normalFont));
            cellInterTab1.setPaddingBottom(5);
            cellInterTab1.setHorizontalAlignment(Element.ALIGN_LEFT);
            table4.addCell(cellInterTab1);
            
            PdfPCell cellInterTab2 = new PdfPCell(new Phrase("990487", normalFont));
            cellInterTab2.setHorizontalAlignment(Element.ALIGN_LEFT);
            table4.addCell(cellInterTab2);
            
            PdfPCell cellInterTab3 = new PdfPCell(new Phrase("u", normalFont));
            cellInterTab3.setHorizontalAlignment(Element.ALIGN_CENTER);
            table4.addCell(cellInterTab3);
            
            String qt = String.valueOf(35);
            
            PdfPCell cellInterTab4 = new PdfPCell(new Phrase(qt, normalFont));
            cellInterTab4.setHorizontalAlignment(Element.ALIGN_CENTER);
            table4.addCell(cellInterTab4);
            
            PdfPCell cellInterTab5 = new PdfPCell(new Phrase("368.600", normalFont));
            cellInterTab5.setHorizontalAlignment(Element.ALIGN_CENTER);
            table4.addCell(cellInterTab5);
            
            PdfPCell cellInterTab6 = new PdfPCell(new Phrase("12.901.000", normalFont));
            cellInterTab6.setHorizontalAlignment(Element.ALIGN_CENTER);
            table4.addCell(cellInterTab6);
            
        }*/
            // Item 
            PdfPCell cellInterTab1 = new PdfPCell(new Phrase("RADIO MOTOROLA DP4401E UHF 403-527 GNSBT 4WNK", normalFont));
            cellInterTab1.setPaddingBottom(5);
            cellInterTab1.setPaddingLeft(5);
            cellInterTab1.setHorizontalAlignment(Element.ALIGN_LEFT);
            table4.addCell(cellInterTab1);

            PdfPCell cellInterTab2 = new PdfPCell(new Phrase("990487", normalFont));
            cellInterTab2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellInterTab2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table4.addCell(cellInterTab2);

            PdfPCell cellInterTab3 = new PdfPCell(new Phrase("u", normalFont));
            cellInterTab3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellInterTab3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table4.addCell(cellInterTab3);

            String qt = String.valueOf(35);

            PdfPCell cellInterTab4 = new PdfPCell(new Phrase(qt, normalFont));
            cellInterTab4.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellInterTab4.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table4.addCell(cellInterTab4);

            PdfPCell cellInterTab5 = new PdfPCell(new Phrase("368.600", normalFont));
            cellInterTab5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellInterTab5.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table4.addCell(cellInterTab5);

            PdfPCell cellInterTab6 = new PdfPCell(new Phrase("12.901.000", normalFont));
            cellInterTab6.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellInterTab6.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table4.addCell(cellInterTab6);

            // Sous item
            PdfPCell cellInterTab12 = new PdfPCell(new Phrase("- RADIO MOTOROLA DP4401E UHF 403-527 GNSBT 4WNK", normalFont));
            cellInterTab12.setPaddingBottom(5);
            cellInterTab12.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellInterTab12.setPaddingLeft(15);
            table4.addCell(cellInterTab12);

            PdfPCell cellInterTab22 = new PdfPCell(new Phrase("990487", normalFont));
            cellInterTab22.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellInterTab22.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table4.addCell(cellInterTab22);

            PdfPCell cellInterTab33 = new PdfPCell(new Phrase("u", normalFont));
            cellInterTab33.setHorizontalAlignment(Element.ALIGN_CENTER);
            table4.addCell(cellInterTab33);

            String qtt = String.valueOf(35);

            PdfPCell cellInterTab44 = new PdfPCell(new Phrase(qtt, normalFont));
            cellInterTab44.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellInterTab44.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table4.addCell(cellInterTab44);

            PdfPCell cellInterTab55 = new PdfPCell(new Phrase("368.600", normalFont));
            cellInterTab55.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellInterTab55.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table4.addCell(cellInterTab55);

            PdfPCell cellInterTab66 = new PdfPCell(new Phrase("12.901.000", normalFont));
            cellInterTab66.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellInterTab66.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table4.addCell(cellInterTab66);

            // Sous item
            PdfPCell cellInterTab123 = new PdfPCell(new Phrase("- RADIO MOTOROLA DP4401E UHF 403-527 GNSBT 4WNK", normalFont));
            cellInterTab123.setPaddingBottom(5);
            cellInterTab123.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellInterTab123.setPaddingLeft(15);
            table4.addCell(cellInterTab123);

            PdfPCell cellInterTab223 = new PdfPCell(new Phrase("990487", normalFont));
            cellInterTab223.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellInterTab223.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table4.addCell(cellInterTab223);

            PdfPCell cellInterTab333 = new PdfPCell(new Phrase("u", normalFont));
            cellInterTab333.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellInterTab333.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table4.addCell(cellInterTab333);

            String qttt = String.valueOf(35);

            PdfPCell cellInterTab443 = new PdfPCell(new Phrase(qttt, normalFont));
            cellInterTab443.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellInterTab443.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table4.addCell(cellInterTab443);

            PdfPCell cellInterTab555 = new PdfPCell(new Phrase("368.600", normalFont));
            cellInterTab555.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellInterTab555.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table4.addCell(cellInterTab555);

            PdfPCell cellInterTab666 = new PdfPCell(new Phrase("12.901.000", normalFont));
            cellInterTab666.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellInterTab666.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table4.addCell(cellInterTab666);

            // Item 
            PdfPCell cellInterTab15 = new PdfPCell(new Phrase("RADIO MOTOROLA DP4401E UHF 403-527 GNSBT 4WNK", normalFont));
            cellInterTab15.setPaddingBottom(5);
            cellInterTab15.setPaddingLeft(5);
            cellInterTab15.setHorizontalAlignment(Element.ALIGN_LEFT);
            table4.addCell(cellInterTab15);

            PdfPCell cellInterTab25 = new PdfPCell(new Phrase("990487", normalFont));
            cellInterTab25.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellInterTab25.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table4.addCell(cellInterTab25);

            PdfPCell cellInterTab35 = new PdfPCell(new Phrase("u", normalFont));
            cellInterTab35.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellInterTab35.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table4.addCell(cellInterTab35);

            String qt5 = String.valueOf(35);

            PdfPCell cellInterTab45 = new PdfPCell(new Phrase(qt5, normalFont));
            cellInterTab45.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellInterTab45.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table4.addCell(cellInterTab45);

            PdfPCell cellInterTab5556 = new PdfPCell(new Phrase("368.600", normalFont));
            cellInterTab5556.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellInterTab5556.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table4.addCell(cellInterTab5556);

            PdfPCell cellInterTab667 = new PdfPCell(new Phrase("12.901.000", normalFont));
            cellInterTab667.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellInterTab667.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table4.addCell(cellInterTab667);

            //Item
            // Item 
            PdfPCell cellInterTab15T = new PdfPCell(new Phrase("RADIO MOTOROLA DP4401E UHF 403-527 GNSBT 4WNK", normalFont));
            cellInterTab15T.setPaddingBottom(5);
            cellInterTab15T.setPaddingLeft(5);
            cellInterTab15T.setHorizontalAlignment(Element.ALIGN_LEFT);
            table4.addCell(cellInterTab15T);

            PdfPCell cellInterTab25T = new PdfPCell(new Phrase("990487", normalFont));
            cellInterTab25T.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellInterTab25T.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table4.addCell(cellInterTab25T);

            PdfPCell cellInterTab35T = new PdfPCell(new Phrase("u", normalFont));
            cellInterTab35T.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellInterTab35T.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table4.addCell(cellInterTab35T);

            String qt5T = String.valueOf(35);

            PdfPCell cellInterTab45T = new PdfPCell(new Phrase(qt5T, normalFont));
            cellInterTab45T.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellInterTab45T.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table4.addCell(cellInterTab45T);

            PdfPCell cellInterTab5556T = new PdfPCell(new Phrase("368.600", normalFont));
            cellInterTab5556T.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellInterTab5556T.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table4.addCell(cellInterTab5556T);

            PdfPCell cellInterTab667T = new PdfPCell(new Phrase("12.901.000", normalFont));
            cellInterTab667T.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellInterTab667T.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table4.addCell(cellInterTab667T);
//        
//        
//        //Item
            // Item 
            PdfPCell cellInterT = new PdfPCell(new Phrase("RADIO MOTOROLA DP4401E UHF 403-527 GNSBT 4WNK", normalFont));
            cellInterT.setPaddingBottom(5);
            cellInterT.setPaddingLeft(5);
            cellInterT.setHorizontalAlignment(Element.ALIGN_LEFT);
            table4.addCell(cellInterT);

            PdfPCell cellInterS = new PdfPCell(new Phrase("990487", normalFont));
            cellInterS.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellInterS.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table4.addCell(cellInterS);

            PdfPCell cellInterW = new PdfPCell(new Phrase("u", normalFont));
            cellInterW.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellInterW.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table4.addCell(cellInterW);

            String qtW = String.valueOf(35);

            PdfPCell cellInterY = new PdfPCell(new Phrase(qtW, normalFont));
            cellInterY.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellInterY.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table4.addCell(cellInterY);

            PdfPCell cellInterZZ = new PdfPCell(new Phrase("368.600", normalFont));
            cellInterZZ.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellInterZZ.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table4.addCell(cellInterZZ);

            PdfPCell cellInterTYT = new PdfPCell(new Phrase("12.901.000", normalFont));
            cellInterTYT.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellInterTYT.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table4.addCell(cellInterTYT);

            //Item
            // Item 
            PdfPCell cellInterTabA = new PdfPCell(new Phrase("RADIO MOTOROLA DP4401E UHF 403-527 GNSBT 4WNK", normalFont));
            cellInterTabA.setPaddingBottom(5);
            cellInterTabA.setPaddingLeft(5);
            cellInterTabA.setHorizontalAlignment(Element.ALIGN_LEFT);
            table4.addCell(cellInterTabA);

            PdfPCell cellInterTabB = new PdfPCell(new Phrase("990487", normalFont));
            cellInterTabB.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellInterTabB.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table4.addCell(cellInterTabB);

            PdfPCell cellInterTabC = new PdfPCell(new Phrase("u", normalFont));
            cellInterTabC.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellInterTabC.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table4.addCell(cellInterTabC);

            String qtA = String.valueOf(35);

            PdfPCell cellInterTabD = new PdfPCell(new Phrase(qtA, normalFont));
            cellInterTabD.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellInterTabD.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table4.addCell(cellInterTabD);

            PdfPCell cellInterTabE = new PdfPCell(new Phrase("368.600", normalFont));
            cellInterTabE.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellInterTabE.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table4.addCell(cellInterTabE);

            PdfPCell cellInterTabF = new PdfPCell(new Phrase("12.901.000", normalFont));
            cellInterTabF.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellInterTabF.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table4.addCell(cellInterTabF);

            table4.completeRow();

            document.add(table4);

            //Espace
            //document.add(new Paragraph("     "));
            PdfPTable table5 = new PdfPTable(6);
            table5.setSpacingBefore(10f);
            float[] columnWidthsTabP = new float[]{125f, 125f, 25f, 25f, 100f, 100f};
            table5.setWidths(columnWidthsTabP);
            table5.setWidthPercentage(100);

            PdfPCell cellTabBas1 = new PdfPCell();
            Paragraph paragraphTabBas1 = new Paragraph(" ");
            cellTabBas1.addElement(paragraphTabBas1);
            cellTabBas1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas1.setBorder(Rectangle.NO_BORDER);
            //cellTabBas1.setColspan(3);
            cellTabBas1.setColspan(4);
            table5.addCell(cellTabBas1);

            PdfPCell cellTabBas2 = new PdfPCell();
            Paragraph paragraphTabBas2 = new Paragraph("TOTAL HT", boldFontTitleTable);
            cellTabBas2.addElement(paragraphTabBas2);
            cellTabBas2.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas2.setBorder(Rectangle.BOX);
            //cellTabBas2.setColspan(2);
            table5.addCell(cellTabBas2);

            PdfPCell cellTabBas3;
            cellTabBas3 = new PdfPCell(new Phrase("14.371.300", normalFont));
            cellTabBas3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTabBas3.setBorder(Rectangle.BOX);
            table5.addCell(cellTabBas3);

            PdfPCell cellTabBas4 = new PdfPCell();
            Paragraph paragraphTabBas4 = new Paragraph(" ");
            cellTabBas4.addElement(paragraphTabBas4);
            cellTabBas4.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas4.setBorder(Rectangle.NO_BORDER);
            //cellTabBas4.setColspan(3);
            cellTabBas4.setColspan(4);
            table5.addCell(cellTabBas4);

            PdfPCell cellTabBas5 = new PdfPCell();
            Paragraph paragraphTabBas5 = new Paragraph("TVA 18%", boldFontTitleTable);
            cellTabBas5.addElement(paragraphTabBas5);
            cellTabBas5.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas5.setBorder(Rectangle.BOX);
            //cellTabBas5.setColspan(2);
            table5.addCell(cellTabBas5);

            PdfPCell cellTabBas6;
            cellTabBas6 = new PdfPCell(new Phrase("2.586.834", normalFont));
            cellTabBas6.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTabBas6.setBorder(Rectangle.BOX);
            table5.addCell(cellTabBas6);

            PdfPCell cellTabBas7 = new PdfPCell();
            Paragraph paragraphTabBas7 = new Paragraph(" ");
            cellTabBas7.addElement(paragraphTabBas7);
            cellTabBas7.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas7.setBorder(Rectangle.NO_BORDER);
            //cellTabBas7.setColspan(3);
            cellTabBas7.setColspan(4);
            table5.addCell(cellTabBas7);

            PdfPCell cellTabBas8 = new PdfPCell();
            Paragraph paragraphTabBas8 = new Paragraph("TOTAL TTC", boldFontTitleTable);
            cellTabBas8.addElement(paragraphTabBas8);
            cellTabBas8.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas8.setBorder(Rectangle.BOX);
            //cellTabBas8.setColspan(2);
            table5.addCell(cellTabBas8);

            PdfPCell cellTabBas9;
            cellTabBas9 = new PdfPCell(new Phrase("16.958.134", normalFont));
            cellTabBas9.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTabBas9.setBorder(Rectangle.BOX);
            table5.addCell(cellTabBas9);

            document.add(table5);

            //Espace
            document.add(new Paragraph("     "));

            PdfPTable tableDirecteur = new PdfPTable(2);
            tableDirecteur.setSpacingBefore(10f);
            tableDirecteur.setWidthPercentage(100);
            PdfPCell cellDirect = new PdfPCell(new Phrase("PROFORMA ARRETE A LA SOMME DE : ", normalFont));
            cellDirect.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellDirect.setVerticalAlignment(Element.ALIGN_LEFT);
            cellDirect.setBorder(Rectangle.NO_BORDER);
            tableDirecteur.addCell(cellDirect);

            PdfPCell cell37;
            Chunk underline = new Chunk("Directeur général", boldFont);
            underline.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            cell37 = new PdfPCell(new Phrase(underline));
            cell37.setBorder(0);
            cell37.setColspan(4);
            cell37.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell37.setVerticalAlignment(Element.ALIGN_RIGHT);
            tableDirecteur.addCell(cell37);
            document.add(tableDirecteur);

            PdfPTable tableSommetext = new PdfPTable(2);
            tableSommetext.setSpacingBefore(10f);
            tableSommetext.setWidthPercentage(100);
            PdfPCell cellSommeText = new PdfPCell(new Phrase("Cinq millions" + " " + "FCFA", normalFont));
            cellSommeText.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellSommeText.setVerticalAlignment(Element.ALIGN_LEFT);
            cellSommeText.setBorder(Rectangle.NO_BORDER);
            tableSommetext.addCell(cellSommeText);

            PdfPCell cell1SommeText = new PdfPCell(new Phrase(""));
            cell1SommeText.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell1SommeText.setVerticalAlignment(Element.ALIGN_RIGHT);
            cell1SommeText.setBorder(Rectangle.NO_BORDER);
            tableSommetext.addCell(cell1SommeText);

            document.add(tableSommetext);

//        if (devis.getMontantlettre().equals("")) {
//
//        } else {
//            //Espace
//            document.add(new Paragraph(devis.getMontantlettre() + " " + devis.getIdDevise().getSymbole(), normalFontCG));
//        }
            //Espace
            document.add(new Paragraph("     "));

            Chunk CBas1 = new Chunk("CONDITIONS GÉNÉRALES", boldFont);
            CBas1.setUnderline(0.1f, -2f);
            Phrase pBas1 = new Phrase();
            pBas1.add(CBas1);
            Paragraph paragarphBas1 = new Paragraph();
            paragarphBas1.add(pBas1);
            document.add(paragarphBas1);

            //Espace
            document.add(new Paragraph("     "));

            //Espace
//        if (devis.getTerme().equals("")) {
//
//        } else {
//            document.add(new Paragraph("TERMES DU CONTRAT: " + devis.getTerme(), normalFontCG));
//        }
            document.add(new Paragraph("TERMES DU CONTRAT: " + "50 % à la commande", normalFont));

            //Espace
//        GenerationCodePdf acronim = new GenerationCodePdf();
//        String acrovalue = "";
//        if (devis.getIdUtilisateur() != null) {
//            acrovalue = acronim.genererInitiales(devis.getIdUtilisateur().getNomUtilisateur(), devis.getIdUtilisateur().getPrenomUtilisateur());
//        }
//
//        if (acrovalue.equals("")) {
//
//        } else {
//            document.add(new Paragraph("DOSSIER SUIVI PAR: " + acrovalue, normalFontCG));
//        }
            document.add(new Paragraph("DOSSIER SUIVI PAR: " + "WA", normalFont));

            //Espace
//        if (devis.getNbjours().equals("")) {
//
//        } else {
//            document.add(new Paragraph("DISPONIBILITE: " + devis.getNbjours(), normalFontCG));
//        }
            document.add(new Paragraph("DISPONIBILITE: " + "30 jours", normalFont));

            //Espace
            document.add(new Paragraph("     "));

            document.add(new Paragraph("1. Toutes marchandises livrées ou services rendus demeurent la propriété de", normalFont));
            document.add(new Paragraph("   ACCEL TECHNOLOGIES jusqu'au paiement intégral de la facture.", normalFont));
            document.add(new Paragraph("2. Veuillez mettre le chèque à l'ordre de: ACCEL TECHNOLOGIES", normalFont));

            //Espace
            document.add(new Paragraph("     "));

            // A table with 3 columns
            PdfPTable tableBas3 = new PdfPTable(4);
            tableBas3.setSpacingBefore(10f);
            tableBas3.setWidthPercentage(100);

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

    public void GenererModelDevis(Devis devis, List<UtilListItemData> listItems, String totalHT, String montantTVA, String totalTTC, String mtlUtil, String termUtil, String nbJours, String acrovalue) throws DocumentException {

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

            Document document = new Document(PageSize.A4, 50, 50, 200, 50);
            OutputStream file = new FileOutputStream(new File(url));
            HeaderFooterPageEvent event = new HeaderFooterPageEvent(devis);
            // step 2
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos).setPageEvent(event);
            PdfWriter.getInstance(document, file).setPageEvent(event);

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

            // A table1 with 4 columns
//            PdfPTable table1 = new PdfPTable(4);
//            table1.setSpacingBefore(2f);
//            table1.setWidthPercentage(100);
//            PdfPCell cell1 = new PdfPCell();
//
//            Chunk c1 = new Chunk("ACCEL ", boldFontTitleExp1);
//            Chunk c2 = new Chunk("TECHNOLOGIES", boldFontTitleExp2);
//            Phrase p1 = new Phrase();
//            p1.add(c1);
//            p1.add(c2);
//            Paragraph p = new Paragraph();
//            p.add(p1);
//
//            Paragraph paragraphSp1 = new Paragraph("4 Route de l’aéroport", normalFont);
//            Paragraph paragraphSp2 = new Paragraph("Dakar, Sénégal", normalFont);
//            Paragraph paragraph1 = new Paragraph("Tél : 33 820 83 83", normalFont);
//            Paragraph paragraphElementBas2 = new Paragraph("NINEA: 005310652 2Y2", normalFontP);
//
//            Chunk underlineEmail = new Chunk("Email : info@accel-tech.net", normalFontEmail);
//            underlineEmail.setUnderline(0.1f, -2f);
//            Paragraph paragraph2 = new Paragraph(underlineEmail);
//
//            cell1.addElement(p);
//            cell1.addElement(paragraphSp1);
//            cell1.addElement(paragraphSp2);
//            cell1.addElement(paragraph1);
//            cell1.addElement(paragraph2);
//            cell1.addElement(paragraphElementBas2);
//            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//            cell1.setVerticalAlignment(Element.ALIGN_LEFT);
//            cell1.setBorder(Rectangle.NO_BORDER);
//            table1.addCell(cell1);
//
//            PdfPCell cell2;
//            cell2 = new PdfPCell(new Phrase(" "));
//            cell2.setBorder(Rectangle.NO_BORDER);
//            table1.addCell(cell2);
//
//            PdfPCell cell3;
//            cell3 = new PdfPCell(new Phrase(" "));
//            cell3.setBorder(Rectangle.NO_BORDER);
//            table1.addCell(cell3);
//
//            // the cell object
//            PdfPCell cell4 = new PdfPCell();
//            Paragraph paragraph4 = new Paragraph(" CLIENT", boldFont);
//            Paragraph paragraph5 = new Paragraph(" " + devis.getIdClient().getNomClient(), boldFont);
//            Paragraph paragraphBs1 = new Paragraph(" " + devis.getIdClient().getAdresse(), boldFont);
//            Paragraph paragraphBs2 = new Paragraph(" " + devis.getIdClient().getTelephoneClient(), boldFont);
//            Paragraph paragraphBs3 = new Paragraph(" " + devis.getIdClient().getMailClient(), boldFont);
//
//            cell4.addElement(paragraph4);
//            cell4.addElement(paragraph5);
//            cell4.addElement(paragraphBs1);
//            cell4.addElement(paragraphBs2);
//            cell4.addElement(paragraphBs3);
//            cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
//            cell4.setVerticalAlignment(Element.ALIGN_LEFT);
//            //cell4.setBorder(Rectangle.BOX);
//            cell4.setBorder(Rectangle.NO_BORDER);
//            cell4.setCellEvent(new RoundRectangle());
//            table1.addCell(cell4);
//
//            // Defiles the relative width of the columns
//            float[] columnWidths = new float[]{20f, 10f, 20f, 30f};
//            table1.setWidths(columnWidths);
//            document.add(table1);
            //Espace
           // document.add(new Paragraph("  "));

            //Content PDF
            // A table1 with 4 columns
            PdfPTable table2 = new PdfPTable(4);
            table2.setSpacingBefore(10f);
            table2.setWidthPercentage(100);
            // the cell object
            PdfPCell cell5 = new PdfPCell(new Phrase("PROFORMA", boldFont));

            cell5.setColspan(4);
            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell5.setBorder(0);
            table2.addCell(cell5);
            document.add(table2);

            // A table1 with 4 columns
            PdfPTable table3 = new PdfPTable(4);
            table3.setSpacingBefore(10f);
            table3.setWidthPercentage(100);
            PdfPCell cell6 = new PdfPCell(new Phrase("DATE : " + devis.getDateDevis(), boldFont));
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

            PdfPCell cell9 = new PdfPCell(new Phrase("DEVIS N°:  " + devis.getNumeroDevis(), boldFont));
            cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell9.setVerticalAlignment(Element.ALIGN_LEFT);
            cell9.setBorder(Rectangle.NO_BORDER);
            table3.addCell(cell9);

            // Defiles the relative width of the columns
            float[] columnWidthss = new float[]{20f, 10f, 20f, 30f};
            table3.setWidths(columnWidthss);
            document.add(table3);

            PdfPTable tableDevis = new PdfPTable(2);
            tableDevis.setSpacingBefore(10f);
            tableDevis.setWidthPercentage(100);
            PdfPCell cell10 = new PdfPCell(new Phrase("REFERENCE:  " + devis.getNommarche(), boldFont));
            cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell10.setVerticalAlignment(Element.ALIGN_LEFT);
            cell10.setBorder(Rectangle.NO_BORDER);
            tableDevis.addCell(cell10);

            PdfPCell cell1P = new PdfPCell(new Phrase("Devise:  " + devis.getIdDevise().getSymbole(), boldFont));
            cell1P.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell1P.setVerticalAlignment(Element.ALIGN_RIGHT);
            cell1P.setBorder(Rectangle.NO_BORDER);
            tableDevis.addCell(cell1P);

            document.add(tableDevis);


            /*Debut tableau item*/
            PdfPTable table4 = new PdfPTable(6);
            table4.setSpacingBefore(10f);
            // Defiles the relative width of the columns
            float[] columnWidthsTab = new float[]{120f, 120f, 35f, 25f, 100f, 100f};
            table4.setWidths(columnWidthsTab);
            table4.setWidthPercentage(100);

            PdfPCell cellTab1 = new PdfPCell(new Phrase("Désignation", boldFontTitleTable));
            cellTab1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTab1.setPaddingBottom(5);
            //cellTab1.setBackgroundColor(new BaseColor(0, 102, 204));
            cellTab1.setBackgroundColor(new BaseColor(125, 150, 255));
            table4.addCell(cellTab1);
            PdfPCell cellTab2 = new PdfPCell(new Phrase("Ref", boldFontTitleTable));
            cellTab2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTab2.setPaddingBottom(5);
            cellTab2.setBackgroundColor(new BaseColor(120, 150, 255));
            table4.addCell(cellTab2);
            PdfPCell cellTab3 = new PdfPCell(new Phrase("U.M", boldFontTitleTable));
            cellTab3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTab3.setPaddingBottom(5);
            cellTab3.setBackgroundColor(new BaseColor(120, 150, 255));
            table4.addCell(cellTab3);
            PdfPCell cellTab4 = new PdfPCell(new Phrase("Qté", boldFontTitleTable));
            cellTab4.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTab4.setPaddingBottom(5);
            cellTab4.setBackgroundColor(new BaseColor(120, 150, 255));
            table4.addCell(cellTab4);
            PdfPCell cellTab5 = new PdfPCell(new Phrase("P.U", boldFontTitleTable));
            cellTab5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTab5.setPaddingBottom(5);
            cellTab5.setBackgroundColor(new BaseColor(120, 150, 255));
            table4.addCell(cellTab5);
            PdfPCell cellTab6 = new PdfPCell(new Phrase("P.Total", boldFontTitleTable));
            cellTab6.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTab6.setPaddingBottom(5);
            cellTab6.setBackgroundColor(new BaseColor(120, 150, 255));
            table4.addCell(cellTab6);
            table4.completeRow();

            for (UtilListItemData item : listItems) {
                if (item.getTypedata().equals("Item")) {
                    PdfPCell cellInterTab1 = new PdfPCell(new Phrase(item.getDesignation(), normalFont));
                    cellInterTab1.setPaddingBottom(5);
                    cellInterTab1.setPaddingLeft(5);
                    cellInterTab1.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table4.addCell(cellInterTab1);

                    PdfPCell cellInterTab2 = new PdfPCell(new Phrase(item.getRef(), normalFont));
                    cellInterTab2.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellInterTab2.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table4.addCell(cellInterTab2);

                    PdfPCell cellInterTab3 = new PdfPCell(new Phrase(item.getUnite(), normalFont));
                    cellInterTab3.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellInterTab3.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table4.addCell(cellInterTab3);

                    String qt = String.valueOf(item.getQuantite());

                    PdfPCell cellInterTab4 = new PdfPCell(new Phrase(qt, normalFont));
                    cellInterTab4.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellInterTab4.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table4.addCell(cellInterTab4);

                    PdfPCell cellInterTab5 = new PdfPCell(new Phrase(item.getPu(), normalFont));
                    cellInterTab5.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellInterTab5.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table4.addCell(cellInterTab5);

                    PdfPCell cellInterTab6 = new PdfPCell(new Phrase(item.getPrixtotal(), normalFont));
                    cellInterTab6.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellInterTab6.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table4.addCell(cellInterTab6);
                } else {
                    PdfPCell cellInterTab12 = new PdfPCell(new Phrase("- " + item.getDesignation(), normalFont));
                    cellInterTab12.setPaddingBottom(5);
                    cellInterTab12.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cellInterTab12.setPaddingLeft(15);
                    table4.addCell(cellInterTab12);

                    PdfPCell cellInterTab22 = new PdfPCell(new Phrase(item.getRef(), normalFont));
                    cellInterTab22.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellInterTab22.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table4.addCell(cellInterTab22);

                    PdfPCell cellInterTab33 = new PdfPCell(new Phrase(item.getUnite(), normalFont));
                    cellInterTab33.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table4.addCell(cellInterTab33);

                    String qtt = String.valueOf(item.getQuantite());

                    PdfPCell cellInterTab44 = new PdfPCell(new Phrase(qtt, normalFont));
                    cellInterTab44.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellInterTab44.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table4.addCell(cellInterTab44);

                    PdfPCell cellInterTab55 = new PdfPCell(new Phrase(item.getPu(), normalFont));
                    cellInterTab55.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellInterTab55.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table4.addCell(cellInterTab55);

                    PdfPCell cellInterTab66 = new PdfPCell(new Phrase(item.getPrixtotal(), normalFont));
                    cellInterTab66.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellInterTab66.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table4.addCell(cellInterTab66);
                }
            }

            table4.completeRow();

            document.add(table4);

            //Espace
            //document.add(new Paragraph("     "));
            PdfPTable table5 = new PdfPTable(6);
            table5.setSpacingBefore(10f);
            float[] columnWidthsTabP = new float[]{120f, 120f, 35f, 25f, 100f, 100f};
            table5.setWidths(columnWidthsTabP);
            table5.setWidthPercentage(100);

            PdfPCell cellTabBas1 = new PdfPCell();
            Paragraph paragraphTabBas1 = new Paragraph(" ");
            cellTabBas1.addElement(paragraphTabBas1);
            cellTabBas1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas1.setBorder(Rectangle.NO_BORDER);
            //cellTabBas1.setColspan(3);
            cellTabBas1.setColspan(4);
            table5.addCell(cellTabBas1);

            PdfPCell cellTabBas2 = new PdfPCell();
            Paragraph paragraphTabBas2 = new Paragraph("TOTAL HT", boldFontTitleTable);
            cellTabBas2.addElement(paragraphTabBas2);
            cellTabBas2.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas2.setBorder(Rectangle.BOX);
            cellTabBas2.setBackgroundColor(new BaseColor(120, 150, 255));
            //cellTabBas2.setColspan(2);
            table5.addCell(cellTabBas2);

            PdfPCell cellTabBas3;
            cellTabBas3 = new PdfPCell(new Phrase(totalHT, normalFont));
            cellTabBas3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTabBas3.setBorder(Rectangle.BOX);
            cellTabBas3.setBackgroundColor(new BaseColor(120, 150, 255));
            table5.addCell(cellTabBas3);

            PdfPCell cellTabBas4 = new PdfPCell();
            Paragraph paragraphTabBas4 = new Paragraph(" ");
            cellTabBas4.addElement(paragraphTabBas4);
            cellTabBas4.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas4.setBorder(Rectangle.NO_BORDER);
            //cellTabBas4.setColspan(3);
            cellTabBas4.setColspan(4);
            table5.addCell(cellTabBas4);

            PdfPCell cellTabBas5 = new PdfPCell();
            Paragraph paragraphTabBas5 = new Paragraph("TVA 18%", boldFontTitleTable);
            cellTabBas5.addElement(paragraphTabBas5);
            cellTabBas5.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas5.setBorder(Rectangle.BOX);
            cellTabBas5.setBackgroundColor(new BaseColor(120, 150, 255));
            //cellTabBas5.setColspan(2);
            table5.addCell(cellTabBas5);

            PdfPCell cellTabBas6;
            cellTabBas6 = new PdfPCell(new Phrase(montantTVA, normalFont));
            cellTabBas6.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTabBas6.setBorder(Rectangle.BOX);
            cellTabBas6.setBackgroundColor(new BaseColor(120, 150, 255));
            table5.addCell(cellTabBas6);

            PdfPCell cellTabBas7 = new PdfPCell();
            Paragraph paragraphTabBas7 = new Paragraph(" ");
            cellTabBas7.addElement(paragraphTabBas7);
            cellTabBas7.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas7.setBorder(Rectangle.NO_BORDER);
            //cellTabBas7.setColspan(3);
            cellTabBas7.setColspan(4);
            table5.addCell(cellTabBas7);

            PdfPCell cellTabBas8 = new PdfPCell();
            Paragraph paragraphTabBas8 = new Paragraph("TOTAL TTC", boldFontTitleTable);
            cellTabBas8.addElement(paragraphTabBas8);
            cellTabBas8.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas8.setBorder(Rectangle.BOX);
            cellTabBas8.setBackgroundColor(new BaseColor(120, 150, 255));
            //cellTabBas8.setColspan(2);
            table5.addCell(cellTabBas8);

            PdfPCell cellTabBas9;
            cellTabBas9 = new PdfPCell(new Phrase(totalTTC, normalFont));
            cellTabBas9.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTabBas9.setBorder(Rectangle.BOX);
            cellTabBas9.setBackgroundColor(new BaseColor(120, 150, 255));
            table5.addCell(cellTabBas9);

            document.add(table5);

            //Espace
            document.add(new Paragraph("     "));

            PdfPTable tableDirecteur = new PdfPTable(2);
            tableDirecteur.setSpacingBefore(10f);
            tableDirecteur.setWidthPercentage(100);
            PdfPCell cellDirect = new PdfPCell(new Phrase("PROFORMA ARRETE A LA SOMME DE : ", normalFont));
            cellDirect.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellDirect.setVerticalAlignment(Element.ALIGN_LEFT);
            cellDirect.setBorder(Rectangle.NO_BORDER);
            tableDirecteur.addCell(cellDirect);

            PdfPCell cell37;
            Chunk underline = new Chunk("Directeur général", boldFont);
            underline.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            cell37 = new PdfPCell(new Phrase(underline));
            cell37.setBorder(0);
            cell37.setColspan(4);
            cell37.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell37.setVerticalAlignment(Element.ALIGN_RIGHT);
            tableDirecteur.addCell(cell37);
            document.add(tableDirecteur);

            PdfPTable tableSommetext = new PdfPTable(2);
            tableSommetext.setSpacingBefore(10f);
            tableSommetext.setWidthPercentage(100);
            PdfPCell cellSommeText = new PdfPCell(new Phrase(mtlUtil, normalFont));
            cellSommeText.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellSommeText.setVerticalAlignment(Element.ALIGN_LEFT);
            cellSommeText.setBorder(Rectangle.NO_BORDER);
            tableSommetext.addCell(cellSommeText);

            PdfPCell cell1SommeText = new PdfPCell(new Phrase(""));
            cell1SommeText.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell1SommeText.setVerticalAlignment(Element.ALIGN_RIGHT);
            cell1SommeText.setBorder(Rectangle.NO_BORDER);
            tableSommetext.addCell(cell1SommeText);

            document.add(tableSommetext);

            document.add(new Paragraph("     "));

            Chunk CBas1 = new Chunk("CONDITIONS GÉNÉRALES", boldFont);
            CBas1.setUnderline(0.1f, -2f);
            Phrase pBas1 = new Phrase();
            pBas1.add(CBas1);
            Paragraph paragarphBas1 = new Paragraph();
            paragarphBas1.add(pBas1);
            document.add(paragarphBas1);

            //Espace
            document.add(new Paragraph("     "));

            document.add(new Paragraph(termUtil, normalFont));

            document.add(new Paragraph("DOSSIER SUIVI PAR: " + acrovalue, normalFont));

            document.add(new Paragraph(nbJours, normalFont));

            //Espace
            document.add(new Paragraph("     "));

            document.add(new Paragraph("1. Toutes marchandises livrées ou services rendus demeurent la propriété de", normalFont));
            document.add(new Paragraph("   ACCEL TECHNOLOGIES jusqu'au paiement intégral de la facture.", normalFont));
            document.add(new Paragraph("2. Veuillez mettre le chèque à l'ordre de: ACCEL TECHNOLOGIES", normalFont));

            //Espace
            document.add(new Paragraph("     "));

            // A table with 3 columns
            PdfPTable tableBas3 = new PdfPTable(4);
            tableBas3.setSpacingBefore(10f);
            tableBas3.setWidthPercentage(100);

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

    public void GenererModelFacture(Newfacture facture, List<UtilListItemData> listItems, String totalHT, String montantTVA, String totalTTC, String mtlUtil, String termUtil) throws DocumentException {

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

            Document document = new Document(PageSize.A4, 50, 50, 200, 50);
            OutputStream file = new FileOutputStream(new File(url));
            HeaderFooterPageEventFacture event = new HeaderFooterPageEventFacture(facture);
            // step 2
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos).setPageEvent(event);
            PdfWriter.getInstance(document, file).setPageEvent(event);

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

//            // A table1 with 4 columns
//            PdfPTable table1 = new PdfPTable(4);
//            table1.setSpacingBefore(2f);
//            table1.setWidthPercentage(100);
//            PdfPCell cell1 = new PdfPCell();
//
//            Chunk c1 = new Chunk("ACCEL ", boldFontTitleExp1);
//            Chunk c2 = new Chunk("TECHNOLOGIES", boldFontTitleExp2);
//            Phrase p1 = new Phrase();
//            p1.add(c1);
//            p1.add(c2);
//            Paragraph p = new Paragraph();
//            p.add(p1);
//
//            Paragraph paragraphSp1 = new Paragraph("4 Route de l’aéroport", normalFont);
//            Paragraph paragraphSp2 = new Paragraph("Dakar, Sénégal", normalFont);
//            Paragraph paragraph1 = new Paragraph("Tél : 33 820 83 83", normalFont);
//            Paragraph paragraphElementBas2 = new Paragraph("NINEA: 005310652 2Y2", normalFontP);
//
//            Chunk underlineEmail = new Chunk("Email : info@accel-tech.net", normalFontEmail);
//            underlineEmail.setUnderline(0.1f, -2f);
//            Paragraph paragraph2 = new Paragraph(underlineEmail);
//
//            cell1.addElement(p);
//            cell1.addElement(paragraphSp1);
//            cell1.addElement(paragraphSp2);
//            cell1.addElement(paragraph1);
//            cell1.addElement(paragraph2);
//            cell1.addElement(paragraphElementBas2);
//            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//            cell1.setVerticalAlignment(Element.ALIGN_LEFT);
//            cell1.setBorder(Rectangle.NO_BORDER);
//            table1.addCell(cell1);
//
//            PdfPCell cell2;
//            cell2 = new PdfPCell(new Phrase(" "));
//            cell2.setBorder(Rectangle.NO_BORDER);
//            table1.addCell(cell2);
//
//            PdfPCell cell3;
//            cell3 = new PdfPCell(new Phrase(" "));
//            cell3.setBorder(Rectangle.NO_BORDER);
//            table1.addCell(cell3);
//
//            // the cell object
//            PdfPCell cell4 = new PdfPCell();
//            Paragraph paragraph4 = new Paragraph(" CLIENT", boldFont);
//            Paragraph paragraph5 = new Paragraph(" " + facture.getIdDevis().getIdClient().getNomClient(), boldFont);
//            Paragraph paragraphBs1 = new Paragraph(" " + facture.getIdDevis().getIdClient().getAdresse(), boldFont);
//            Paragraph paragraphBs2 = new Paragraph(" " + facture.getIdDevis().getIdClient().getTelephoneClient(), boldFont);
//            Paragraph paragraphBs3 = new Paragraph(" " + facture.getIdDevis().getIdClient().getMailClient(), boldFont);
//
//            cell4.addElement(paragraph4);
//            cell4.addElement(paragraph5);
//            cell4.addElement(paragraphBs1);
//            cell4.addElement(paragraphBs2);
//            cell4.addElement(paragraphBs3);
//            cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
//            cell4.setVerticalAlignment(Element.ALIGN_LEFT);
//            //cell4.setBorder(Rectangle.BOX);
//            cell4.setBorder(Rectangle.NO_BORDER);
//            cell4.setCellEvent(new RoundRectangle());
//            table1.addCell(cell4);
//
//            // Defiles the relative width of the columns
//            float[] columnWidths = new float[]{20f, 10f, 20f, 30f};
//            table1.setWidths(columnWidths);
//            document.add(table1);
            //Espace
            //document.add(new Paragraph("  "));

            //Content PDF
            // A table1 with 4 columns
            PdfPTable table2 = new PdfPTable(4);
            table2.setSpacingBefore(10f);
            table2.setWidthPercentage(100);
            // the cell object
            PdfPCell cell5 = new PdfPCell(new Phrase("FACTURE", boldFont));

            cell5.setColspan(4);
            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell5.setBorder(0);
            table2.addCell(cell5);
            document.add(table2);

            // A table1 with 4 columns
            PdfPTable table3 = new PdfPTable(4);
            table3.setSpacingBefore(10f);
            table3.setWidthPercentage(100);
            PdfPCell cell6 = new PdfPCell(new Phrase("DATE : " + facture.getDateNewfacture(), boldFont));
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

            PdfPCell cell9 = new PdfPCell(new Phrase("FACTURE N°:  " + facture.getNumeroNewfacture(), boldFont));
            cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell9.setVerticalAlignment(Element.ALIGN_LEFT);
            cell9.setBorder(Rectangle.NO_BORDER);
            table3.addCell(cell9);

            // Defiles the relative width of the columns
            float[] columnWidthss = new float[]{20f, 10f, 20f, 30f};
            table3.setWidths(columnWidthss);
            document.add(table3);

            PdfPTable tableDevis = new PdfPTable(2);
            tableDevis.setSpacingBefore(10f);
            tableDevis.setWidthPercentage(100);
            PdfPCell cell10 = new PdfPCell(new Phrase("REFERENCE:  " + facture.getIdDevis().getNommarche(), boldFont));
            cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell10.setVerticalAlignment(Element.ALIGN_LEFT);
            cell10.setBorder(Rectangle.NO_BORDER);
            tableDevis.addCell(cell10);

            PdfPCell cell1P = new PdfPCell(new Phrase("Devise:  " + facture.getIdMarche().getIdDevise().getSymbole(), boldFont));
            cell1P.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell1P.setVerticalAlignment(Element.ALIGN_RIGHT);
            cell1P.setBorder(Rectangle.NO_BORDER);
            tableDevis.addCell(cell1P);

            document.add(tableDevis);


            /*Debut tableau item*/
            PdfPTable table4 = new PdfPTable(6);
            table4.setSpacingBefore(10f);
            // Defiles the relative width of the columns
            float[] columnWidthsTab = new float[]{120f, 120f, 35f, 25f, 100f, 100f};
            table4.setWidths(columnWidthsTab);
            table4.setWidthPercentage(100);

            PdfPCell cellTab1 = new PdfPCell(new Phrase("Désignation", boldFontTitleTable));
            cellTab1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTab1.setPaddingBottom(5);
            cellTab1.setBackgroundColor(new BaseColor(128, 159, 255));
            table4.addCell(cellTab1);
            PdfPCell cellTab2 = new PdfPCell(new Phrase("Ref", boldFontTitleTable));
            cellTab2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTab2.setPaddingBottom(5);
            cellTab2.setBackgroundColor(new BaseColor(128, 159, 255));
            table4.addCell(cellTab2);
            PdfPCell cellTab3 = new PdfPCell(new Phrase("U.M", boldFontTitleTable));
            cellTab3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTab3.setPaddingBottom(5);
            cellTab3.setBackgroundColor(new BaseColor(128, 159, 255));
            table4.addCell(cellTab3);
            PdfPCell cellTab4 = new PdfPCell(new Phrase("Qté", boldFontTitleTable));
            cellTab4.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTab4.setPaddingBottom(5);
            cellTab4.setBackgroundColor(new BaseColor(128, 159, 255));
            table4.addCell(cellTab4);
            PdfPCell cellTab5 = new PdfPCell(new Phrase("P.U", boldFontTitleTable));
            cellTab5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTab5.setPaddingBottom(5);
            cellTab5.setBackgroundColor(new BaseColor(128, 159, 255));
            table4.addCell(cellTab5);
            PdfPCell cellTab6 = new PdfPCell(new Phrase("P.Total", boldFontTitleTable));
            cellTab6.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTab6.setPaddingBottom(5);
            cellTab6.setBackgroundColor(new BaseColor(128, 159, 255));
            table4.addCell(cellTab6);
            table4.completeRow();

            for (UtilListItemData item : listItems) {
                if (item.getTypedata().equals("Item")) {
                    PdfPCell cellInterTab1 = new PdfPCell(new Phrase(item.getDesignation(), normalFont));
                    cellInterTab1.setPaddingBottom(5);
                    cellInterTab1.setPaddingLeft(5);
                    cellInterTab1.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table4.addCell(cellInterTab1);

                    PdfPCell cellInterTab2 = new PdfPCell(new Phrase(item.getRef(), normalFont));
                    cellInterTab2.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellInterTab2.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table4.addCell(cellInterTab2);

                    PdfPCell cellInterTab3 = new PdfPCell(new Phrase(item.getUnite(), normalFont));
                    cellInterTab3.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellInterTab3.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table4.addCell(cellInterTab3);

                    String qt = String.valueOf(item.getQuantite());

                    PdfPCell cellInterTab4 = new PdfPCell(new Phrase(qt, normalFont));
                    cellInterTab4.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellInterTab4.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table4.addCell(cellInterTab4);

                    PdfPCell cellInterTab5 = new PdfPCell(new Phrase(item.getPu(), normalFont));
                    cellInterTab5.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellInterTab5.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table4.addCell(cellInterTab5);

                    PdfPCell cellInterTab6 = new PdfPCell(new Phrase(item.getPrixtotal(), normalFont));
                    cellInterTab6.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellInterTab6.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table4.addCell(cellInterTab6);
                } else {
                    PdfPCell cellInterTab12 = new PdfPCell(new Phrase("- " + item.getDesignation(), normalFont));
                    cellInterTab12.setPaddingBottom(5);
                    cellInterTab12.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cellInterTab12.setPaddingLeft(15);
                    table4.addCell(cellInterTab12);

                    PdfPCell cellInterTab22 = new PdfPCell(new Phrase(item.getRef(), normalFont));
                    cellInterTab22.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellInterTab22.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table4.addCell(cellInterTab22);

                    PdfPCell cellInterTab33 = new PdfPCell(new Phrase(item.getUnite(), normalFont));
                    cellInterTab33.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table4.addCell(cellInterTab33);

                    String qtt = String.valueOf(item.getQuantite());

                    PdfPCell cellInterTab44 = new PdfPCell(new Phrase(qtt, normalFont));
                    cellInterTab44.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellInterTab44.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table4.addCell(cellInterTab44);

                    PdfPCell cellInterTab55 = new PdfPCell(new Phrase(item.getPu(), normalFont));
                    cellInterTab55.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellInterTab55.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table4.addCell(cellInterTab55);

                    PdfPCell cellInterTab66 = new PdfPCell(new Phrase(item.getPrixtotal(), normalFont));
                    cellInterTab66.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellInterTab66.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table4.addCell(cellInterTab66);
                }
            }

            table4.completeRow();

            document.add(table4);

            //Espace
            //document.add(new Paragraph("     "));
            PdfPTable table5 = new PdfPTable(6);
            table5.setSpacingBefore(10f);
            float[] columnWidthsTabP = new float[]{120f, 120f, 35f, 25f, 100f, 100f};
            table5.setWidths(columnWidthsTabP);
            table5.setWidthPercentage(100);

            PdfPCell cellTabBas1 = new PdfPCell();
            Paragraph paragraphTabBas1 = new Paragraph(" ");
            cellTabBas1.addElement(paragraphTabBas1);
            cellTabBas1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas1.setBorder(Rectangle.NO_BORDER);
            //cellTabBas1.setColspan(3);
            cellTabBas1.setColspan(4);
            table5.addCell(cellTabBas1);

            PdfPCell cellTabBas2 = new PdfPCell();
            Paragraph paragraphTabBas2 = new Paragraph("TOTAL HT", boldFontTitleTable);
            cellTabBas2.addElement(paragraphTabBas2);
            cellTabBas2.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas2.setBorder(Rectangle.BOX);
            cellTabBas2.setBackgroundColor(new BaseColor(128, 159, 255));
            //cellTabBas2.setColspan(2);
            table5.addCell(cellTabBas2);

            PdfPCell cellTabBas3;
            cellTabBas3 = new PdfPCell(new Phrase(totalHT, normalFont));
            cellTabBas3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTabBas3.setBorder(Rectangle.BOX);
            cellTabBas3.setBackgroundColor(new BaseColor(128, 159, 255));
            table5.addCell(cellTabBas3);

            PdfPCell cellTabBas4 = new PdfPCell();
            Paragraph paragraphTabBas4 = new Paragraph(" ");
            cellTabBas4.addElement(paragraphTabBas4);
            cellTabBas4.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas4.setBorder(Rectangle.NO_BORDER);
            //cellTabBas4.setColspan(3);
            cellTabBas4.setColspan(4);
            table5.addCell(cellTabBas4);

            PdfPCell cellTabBas5 = new PdfPCell();
            Paragraph paragraphTabBas5 = new Paragraph("TVA 18%", boldFontTitleTable);
            cellTabBas5.addElement(paragraphTabBas5);
            cellTabBas5.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas5.setBorder(Rectangle.BOX);
            cellTabBas5.setBackgroundColor(new BaseColor(128, 159, 255));
            //cellTabBas5.setColspan(2);
            table5.addCell(cellTabBas5);

            PdfPCell cellTabBas6;
            cellTabBas6 = new PdfPCell(new Phrase(montantTVA, normalFont));
            cellTabBas6.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTabBas6.setBorder(Rectangle.BOX);
            cellTabBas6.setBackgroundColor(new BaseColor(128, 159, 255));
            table5.addCell(cellTabBas6);

            PdfPCell cellTabBas7 = new PdfPCell();
            Paragraph paragraphTabBas7 = new Paragraph(" ");
            cellTabBas7.addElement(paragraphTabBas7);
            cellTabBas7.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas7.setBorder(Rectangle.NO_BORDER);
            //cellTabBas7.setColspan(3);
            cellTabBas7.setColspan(4);
            table5.addCell(cellTabBas7);

            PdfPCell cellTabBas8 = new PdfPCell();
            Paragraph paragraphTabBas8 = new Paragraph("TOTAL TTC", boldFontTitleTable);
            cellTabBas8.addElement(paragraphTabBas8);
            cellTabBas8.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas8.setBorder(Rectangle.BOX);
            cellTabBas8.setBackgroundColor(new BaseColor(128, 159, 255));
            //cellTabBas8.setColspan(2);
            table5.addCell(cellTabBas8);

            PdfPCell cellTabBas9;
            cellTabBas9 = new PdfPCell(new Phrase(totalTTC, normalFont));
            cellTabBas9.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTabBas9.setBorder(Rectangle.BOX);
            cellTabBas9.setBackgroundColor(new BaseColor(128, 159, 255));
            table5.addCell(cellTabBas9);

            document.add(table5);

            //Espace
            document.add(new Paragraph("     "));

            PdfPTable tableDirecteur = new PdfPTable(2);
            tableDirecteur.setSpacingBefore(10f);
            tableDirecteur.setWidthPercentage(100);
            PdfPCell cellDirect = new PdfPCell(new Phrase("FACTURE ARRETEE A LA SOMME DE : ", normalFont));
            cellDirect.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellDirect.setVerticalAlignment(Element.ALIGN_LEFT);
            cellDirect.setBorder(Rectangle.NO_BORDER);
            tableDirecteur.addCell(cellDirect);

            PdfPCell cell37;
            Chunk underline = new Chunk("Directeur général", boldFont);
            underline.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            cell37 = new PdfPCell(new Phrase(underline));
            cell37.setBorder(0);
            cell37.setColspan(4);
            cell37.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell37.setVerticalAlignment(Element.ALIGN_RIGHT);
            tableDirecteur.addCell(cell37);
            document.add(tableDirecteur);

            PdfPTable tableSommetext = new PdfPTable(2);
            tableSommetext.setSpacingBefore(10f);
            tableSommetext.setWidthPercentage(100);
            PdfPCell cellSommeText = new PdfPCell(new Phrase(mtlUtil, normalFont));
            cellSommeText.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellSommeText.setVerticalAlignment(Element.ALIGN_LEFT);
            cellSommeText.setBorder(Rectangle.NO_BORDER);
            tableSommetext.addCell(cellSommeText);

            PdfPCell cell1SommeText = new PdfPCell(new Phrase(""));
            cell1SommeText.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell1SommeText.setVerticalAlignment(Element.ALIGN_RIGHT);
            cell1SommeText.setBorder(Rectangle.NO_BORDER);
            tableSommetext.addCell(cell1SommeText);

            document.add(tableSommetext);

            document.add(new Paragraph("     "));

            Chunk CBas1 = new Chunk("CONDITIONS GÉNÉRALES", boldFont);
            CBas1.setUnderline(0.1f, -2f);
            Phrase pBas1 = new Phrase();
            pBas1.add(CBas1);
            Paragraph paragarphBas1 = new Paragraph();
            paragarphBas1.add(pBas1);
            document.add(paragarphBas1);

            //Espace
            document.add(new Paragraph("     "));

            document.add(new Paragraph(termUtil, normalFont));

//            document.add(new Paragraph("DOSSIER SUIVI PAR: " + acrovalue, normalFont));
//
//            document.add(new Paragraph(nbJours, normalFont));
            //Espace
            document.add(new Paragraph("     "));

            document.add(new Paragraph("1. Toutes marchandises livrées ou services rendus demeurent la propriété de", normalFont));
            document.add(new Paragraph("   ACCEL TECHNOLOGIES jusqu'au paiement intégral de la facture.", normalFont));
            document.add(new Paragraph("2. Veuillez mettre le chèque à l'ordre de: ACCEL TECHNOLOGIES", normalFont));

            //Espace
            document.add(new Paragraph("     "));

            // A table with 3 columns
            PdfPTable tableBas3 = new PdfPTable(4);
            tableBas3.setSpacingBefore(10f);
            tableBas3.setWidthPercentage(100);

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

    public void GenererModelFactureChef(Newfacture facture, List<UtilListItemChantierData> listItems) throws DocumentException {

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

            Document document = new Document(PageSize.A4, 50, 50, 200, 50);
            OutputStream file = new FileOutputStream(new File(url));
            HeaderFooterPageEventFacture event = new HeaderFooterPageEventFacture(facture);
            // step 2
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos).setPageEvent(event);
            PdfWriter.getInstance(document, file).setPageEvent(event);

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

            // A table1 with 4 columns
//            PdfPTable table1 = new PdfPTable(4);
//            table1.setSpacingBefore(2f);
//            table1.setWidthPercentage(100);
//            PdfPCell cell1 = new PdfPCell();
//
//            Chunk c1 = new Chunk("ACCEL ", boldFontTitleExp1);
//            Chunk c2 = new Chunk("TECHNOLOGIES", boldFontTitleExp2);
//            Phrase p1 = new Phrase();
//            p1.add(c1);
//            p1.add(c2);
//            Paragraph p = new Paragraph();
//            p.add(p1);
//
//            Paragraph paragraphSp1 = new Paragraph("4 Route de l’aéroport", normalFont);
//            Paragraph paragraphSp2 = new Paragraph("Dakar, Sénégal", normalFont);
//            Paragraph paragraph1 = new Paragraph("Tél : 33 820 83 83", normalFont);
//            Paragraph paragraphElementBas2 = new Paragraph("NINEA: 005310652 2Y2", normalFontP);
//
//            Chunk underlineEmail = new Chunk("Email : info@accel-tech.net", normalFontEmail);
//            underlineEmail.setUnderline(0.1f, -2f);
//            Paragraph paragraph2 = new Paragraph(underlineEmail);
//
//            cell1.addElement(p);
//            cell1.addElement(paragraphSp1);
//            cell1.addElement(paragraphSp2);
//            cell1.addElement(paragraph1);
//            cell1.addElement(paragraph2);
//            cell1.addElement(paragraphElementBas2);
//            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//            cell1.setVerticalAlignment(Element.ALIGN_LEFT);
//            cell1.setBorder(Rectangle.NO_BORDER);
//            table1.addCell(cell1);
//
//            PdfPCell cell2;
//            cell2 = new PdfPCell(new Phrase(" "));
//            cell2.setBorder(Rectangle.NO_BORDER);
//            table1.addCell(cell2);
//
//            PdfPCell cell3;
//            cell3 = new PdfPCell(new Phrase(" "));
//            cell3.setBorder(Rectangle.NO_BORDER);
//            table1.addCell(cell3);
//
//            // the cell object
//            PdfPCell cell4 = new PdfPCell();
//            Paragraph paragraph4 = new Paragraph(" CLIENT", boldFont);
//            Paragraph paragraph5 = new Paragraph(" " + facture.getIdDevis().getIdClient().getNomClient(), boldFont);
//            Paragraph paragraphBs1 = new Paragraph(" " + facture.getIdDevis().getIdClient().getAdresse(), boldFont);
//            Paragraph paragraphBs2 = new Paragraph(" " + facture.getIdDevis().getIdClient().getTelephoneClient(), boldFont);
//            Paragraph paragraphBs3 = new Paragraph(" " + facture.getIdDevis().getIdClient().getMailClient(), boldFont);
//
//            cell4.addElement(paragraph4);
//            cell4.addElement(paragraph5);
//            cell4.addElement(paragraphBs1);
//            cell4.addElement(paragraphBs2);
//            cell4.addElement(paragraphBs3);
//            cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
//            cell4.setVerticalAlignment(Element.ALIGN_LEFT);
//            //cell4.setBorder(Rectangle.BOX);
//            cell4.setBorder(Rectangle.NO_BORDER);
//            cell4.setCellEvent(new RoundRectangle());
//            table1.addCell(cell4);
//
//            // Defiles the relative width of the columns
//            float[] columnWidths = new float[]{20f, 10f, 20f, 30f};
//            table1.setWidths(columnWidths);
//            document.add(table1);
            //Espace
            //document.add(new Paragraph("  "));

            //Content PDF
            // A table1 with 4 columns
            PdfPTable table2 = new PdfPTable(4);
            table2.setSpacingBefore(10f);
            table2.setWidthPercentage(100);
            // the cell object
            PdfPCell cell5 = new PdfPCell(new Phrase("FACTURE", boldFont));

            cell5.setColspan(4);
            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell5.setBorder(0);
            table2.addCell(cell5);
            document.add(table2);

            // A table1 with 4 columns
            PdfPTable table3 = new PdfPTable(4);
            table3.setSpacingBefore(10f);
            table3.setWidthPercentage(100);
            PdfPCell cell6 = new PdfPCell(new Phrase("DATE : " + facture.getDateNewfacture(), boldFont));
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

            PdfPCell cell9 = new PdfPCell(new Phrase("FACTURE N°:  " + facture.getNumeroNewfacture(), boldFont));
            cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell9.setVerticalAlignment(Element.ALIGN_LEFT);
            cell9.setBorder(Rectangle.NO_BORDER);
            table3.addCell(cell9);

            // Defiles the relative width of the columns
            float[] columnWidthss = new float[]{20f, 10f, 20f, 30f};
            table3.setWidths(columnWidthss);
            document.add(table3);

            PdfPTable tableDevis = new PdfPTable(2);
            tableDevis.setSpacingBefore(10f);
            tableDevis.setWidthPercentage(100);
            PdfPCell cell10 = new PdfPCell(new Phrase("REFERENCE:  " + facture.getIdDevis().getNommarche(), boldFont));
            cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell10.setVerticalAlignment(Element.ALIGN_LEFT);
            cell10.setBorder(Rectangle.NO_BORDER);
            tableDevis.addCell(cell10);

            PdfPCell cell1P = new PdfPCell(new Phrase("Devise:  " + facture.getIdMarche().getIdDevise().getSymbole(), boldFont));
            cell1P.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell1P.setVerticalAlignment(Element.ALIGN_RIGHT);
            cell1P.setBorder(Rectangle.NO_BORDER);
            tableDevis.addCell(cell1P);

            document.add(tableDevis);


            /*Debut tableau item*/
            PdfPTable table4 = new PdfPTable(6);
            table4.setSpacingBefore(10f);
            // Defiles the relative width of the columns
            float[] columnWidthsTab = new float[]{120f, 120f, 35f, 25f, 100f, 100f};
            table4.setWidths(columnWidthsTab);
            table4.setWidthPercentage(100);

            PdfPCell cellTab1 = new PdfPCell(new Phrase("Désignation", boldFontTitleTable));
            cellTab1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTab1.setPaddingBottom(5);
            cellTab1.setBackgroundColor(new BaseColor(128, 159, 255));
            table4.addCell(cellTab1);
            PdfPCell cellTab2 = new PdfPCell(new Phrase("Ref", boldFontTitleTable));
            cellTab2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTab2.setPaddingBottom(5);
            cellTab2.setBackgroundColor(new BaseColor(128, 159, 255));
            table4.addCell(cellTab2);
            PdfPCell cellTab3 = new PdfPCell(new Phrase("U.M", boldFontTitleTable));
            cellTab3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTab3.setPaddingBottom(5);
            cellTab3.setBackgroundColor(new BaseColor(128, 159, 255));
            table4.addCell(cellTab3);
            PdfPCell cellTab4 = new PdfPCell(new Phrase("Qté", boldFontTitleTable));
            cellTab4.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTab4.setPaddingBottom(5);
            cellTab4.setBackgroundColor(new BaseColor(128, 159, 255));
            table4.addCell(cellTab4);
            PdfPCell cellTab5 = new PdfPCell(new Phrase("Qté.Livrée", boldFontTitleTable));
            cellTab5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTab5.setPaddingBottom(5);
            cellTab5.setBackgroundColor(new BaseColor(128, 159, 255));
            table4.addCell(cellTab5);
            PdfPCell cellTab6 = new PdfPCell(new Phrase("% Execution", boldFontTitleTable));
            cellTab6.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTab6.setPaddingBottom(5);
            cellTab6.setBackgroundColor(new BaseColor(128, 159, 255));
            table4.addCell(cellTab6);
            table4.completeRow();

            for (UtilListItemChantierData item : listItems) {

                PdfPCell cellInterTab1 = new PdfPCell(new Phrase(item.getDesignation(), normalFont));
                cellInterTab1.setPaddingBottom(5);
                cellInterTab1.setPaddingLeft(5);
                cellInterTab1.setHorizontalAlignment(Element.ALIGN_LEFT);
                table4.addCell(cellInterTab1);

                PdfPCell cellInterTab2 = new PdfPCell(new Phrase(item.getRef(), normalFont));
                cellInterTab2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellInterTab2.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table4.addCell(cellInterTab2);

                PdfPCell cellInterTab3 = new PdfPCell(new Phrase(item.getUnite(), normalFont));
                cellInterTab3.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellInterTab3.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table4.addCell(cellInterTab3);

                //String qt = String.valueOf(item.getQuantite());
                PdfPCell cellInterTab4 = new PdfPCell(new Phrase(item.getQuantite(), normalFont));
                cellInterTab4.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellInterTab4.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table4.addCell(cellInterTab4);

                PdfPCell cellInterTab5 = new PdfPCell(new Phrase(item.getQtLivree(), normalFont));
                cellInterTab5.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellInterTab5.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table4.addCell(cellInterTab5);

                PdfPCell cellInterTab6 = new PdfPCell(new Phrase(item.getExecution(), normalFont));
                cellInterTab6.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellInterTab6.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table4.addCell(cellInterTab6);
            }

            table4.completeRow();

            document.add(table4);

            //Espace
            document.add(new Paragraph("     "));

            PdfPTable tableDirecteur = new PdfPTable(2);
            tableDirecteur.setSpacingBefore(10f);
            tableDirecteur.setWidthPercentage(100);
            PdfPCell cellDirect = new PdfPCell(new Phrase(" ", normalFont));
            cellDirect.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellDirect.setVerticalAlignment(Element.ALIGN_LEFT);
            cellDirect.setBorder(Rectangle.NO_BORDER);
            tableDirecteur.addCell(cellDirect);

            PdfPCell cell37;
            Chunk underline = new Chunk("Chef de projet", boldFont);
            underline.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            cell37 = new PdfPCell(new Phrase(underline));
            cell37.setBorder(0);
            cell37.setColspan(4);
            cell37.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell37.setVerticalAlignment(Element.ALIGN_RIGHT);
            tableDirecteur.addCell(cell37);
            document.add(tableDirecteur);

            document.add(new Paragraph("     "));

            // A table with 3 columns
            PdfPTable tableBas3 = new PdfPTable(4);
            tableBas3.setSpacingBefore(10f);
            tableBas3.setWidthPercentage(100);

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

    public void GenererModelDecompte(Decomptep decompte, List<UtilListItemDataDecompte> listItems, String totalHT, String montantTVA, String totalTTC, String mtlUtil, String valeurXPourcent, String avancementHt, String avancementTVA, String avancementTTC, String avanceRecu, String reliquantDemande) throws DocumentException {

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

            Document document = new Document(PageSize.A4, 50, 50, 200, 50);
            OutputStream file = new FileOutputStream(new File(url));
            HeaderFooterPageEventDecompte event = new HeaderFooterPageEventDecompte(decompte);
            // step 2
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos).setPageEvent(event);
            PdfWriter.getInstance(document, file).setPageEvent(event);

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

            // A table1 with 4 columns
//            PdfPTable table1 = new PdfPTable(4);
//            table1.setSpacingBefore(2f);
//            table1.setWidthPercentage(100);
//            PdfPCell cell1 = new PdfPCell();
//
//            Chunk c1 = new Chunk("ACCEL ", boldFontTitleExp1);
//            Chunk c2 = new Chunk("TECHNOLOGIES", boldFontTitleExp2);
//            Phrase p1 = new Phrase();
//            p1.add(c1);
//            p1.add(c2);
//            Paragraph p = new Paragraph();
//            p.add(p1);
//
//            Paragraph paragraphSp1 = new Paragraph("4 Route de l’aéroport", normalFont);
//            Paragraph paragraphSp2 = new Paragraph("Dakar, Sénégal", normalFont);
//            Paragraph paragraph1 = new Paragraph("Tél : 33 820 83 83", normalFont);
//            Paragraph paragraphElementBas2 = new Paragraph("NINEA: 005310652 2Y2", normalFontP);
//
//            Chunk underlineEmail = new Chunk("Email : info@accel-tech.net", normalFontEmail);
//            underlineEmail.setUnderline(0.1f, -2f);
//            Paragraph paragraph2 = new Paragraph(underlineEmail);
//
//            cell1.addElement(p);
//            cell1.addElement(paragraphSp1);
//            cell1.addElement(paragraphSp2);
//            cell1.addElement(paragraph1);
//            cell1.addElement(paragraph2);
//            cell1.addElement(paragraphElementBas2);
//            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//            cell1.setVerticalAlignment(Element.ALIGN_LEFT);
//            cell1.setBorder(Rectangle.NO_BORDER);
//            table1.addCell(cell1);
//
//            PdfPCell cell2;
//            cell2 = new PdfPCell(new Phrase(" "));
//            cell2.setBorder(Rectangle.NO_BORDER);
//            table1.addCell(cell2);
//
//            PdfPCell cell3;
//            cell3 = new PdfPCell(new Phrase(" "));
//            cell3.setBorder(Rectangle.NO_BORDER);
//            table1.addCell(cell3);
//
//            // the cell object
//            PdfPCell cell4 = new PdfPCell();
//            Paragraph paragraph4 = new Paragraph(" CLIENT", boldFont);
//            Paragraph paragraph5 = new Paragraph(" " + decompte.getIdNewfacture().getIdDevis().getIdClient().getNomClient(), boldFont);
//            Paragraph paragraphBs1 = new Paragraph(" " + decompte.getIdNewfacture().getIdDevis().getIdClient().getAdresse(), boldFont);
//            Paragraph paragraphBs2 = new Paragraph(" " + decompte.getIdNewfacture().getIdDevis().getIdClient().getTelephoneClient(), boldFont);
//            Paragraph paragraphBs3 = new Paragraph(" " + decompte.getIdNewfacture().getIdDevis().getIdClient().getMailClient(), boldFont);
//
//            cell4.addElement(paragraph4);
//            cell4.addElement(paragraph5);
//            cell4.addElement(paragraphBs1);
//            cell4.addElement(paragraphBs2);
//            cell4.addElement(paragraphBs3);
//            cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
//            cell4.setVerticalAlignment(Element.ALIGN_LEFT);
//            //cell4.setBorder(Rectangle.BOX);
//            cell4.setBorder(Rectangle.NO_BORDER);
//            cell4.setCellEvent(new RoundRectangle());
//            table1.addCell(cell4);
//
//            // Defiles the relative width of the columns
//            float[] columnWidths = new float[]{20f, 10f, 20f, 30f};
//            table1.setWidths(columnWidths);
//            document.add(table1);
            //Espace
            //document.add(new Paragraph("  "));

            //Content PDF
            // A table1 with 4 columns
            PdfPTable table2 = new PdfPTable(4);
            table2.setSpacingBefore(10f);
            table2.setWidthPercentage(100);
            // the cell object
            PdfPCell cell5 = new PdfPCell(new Phrase("FACTURE", boldFont));

            cell5.setColspan(4);
            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell5.setBorder(0);
            table2.addCell(cell5);
            document.add(table2);

            // A table1 with 4 columns
            PdfPTable table3 = new PdfPTable(4);
            table3.setSpacingBefore(10f);
            table3.setWidthPercentage(100);
            PdfPCell cell6 = new PdfPCell(new Phrase("DATE : " + decompte.getDateDecomptep(), boldFont));
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

            PdfPCell cell9 = new PdfPCell(new Phrase("DECOMPTE N°:  " + decompte.getNumeroDecomptep(), boldFont));
            cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell9.setVerticalAlignment(Element.ALIGN_LEFT);
            cell9.setBorder(Rectangle.NO_BORDER);
            table3.addCell(cell9);

            // Defiles the relative width of the columns
            float[] columnWidthss = new float[]{20f, 10f, 20f, 30f};
            table3.setWidths(columnWidthss);
            document.add(table3);

            PdfPTable tableDevis = new PdfPTable(2);
            tableDevis.setSpacingBefore(10f);
            tableDevis.setWidthPercentage(100);
            PdfPCell cell10 = new PdfPCell(new Phrase("REFERENCE:  " + decompte.getIdNewfacture().getIdDevis().getNommarche(), boldFont));
            cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell10.setVerticalAlignment(Element.ALIGN_LEFT);
            cell10.setBorder(Rectangle.NO_BORDER);
            tableDevis.addCell(cell10);

            PdfPCell cell1P = new PdfPCell(new Phrase("Devise:  " + decompte.getIdNewfacture().getIdMarche().getIdDevise().getSymbole(), boldFont));
            cell1P.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell1P.setVerticalAlignment(Element.ALIGN_RIGHT);
            cell1P.setBorder(Rectangle.NO_BORDER);
            tableDevis.addCell(cell1P);

            document.add(tableDevis);


            /*Debut tableau item*/
            PdfPTable table4 = new PdfPTable(6);
            table4.setSpacingBefore(10f);
            // Defiles the relative width of the columns
            float[] columnWidthsTab = new float[]{120f, 120f, 35f, 25f, 100f, 100f};
            table4.setWidths(columnWidthsTab);
            table4.setWidthPercentage(100);

            PdfPCell cellTab1 = new PdfPCell(new Phrase("Désignation", boldFontTitleTable));
            cellTab1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTab1.setPaddingBottom(5);
            cellTab1.setBackgroundColor(new BaseColor(128, 159, 255));
            table4.addCell(cellTab1);
            PdfPCell cellTab2 = new PdfPCell(new Phrase("Ref", boldFontTitleTable));
            cellTab2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTab2.setPaddingBottom(5);
            cellTab2.setBackgroundColor(new BaseColor(128, 159, 255));
            table4.addCell(cellTab2);
            PdfPCell cellTab3 = new PdfPCell(new Phrase("U.M", boldFontTitleTable));
            cellTab3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTab3.setPaddingBottom(5);
            cellTab3.setBackgroundColor(new BaseColor(128, 159, 255));
            table4.addCell(cellTab3);
            PdfPCell cellTab4 = new PdfPCell(new Phrase("Qté", boldFontTitleTable));
            cellTab4.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTab4.setPaddingBottom(5);
            cellTab4.setBackgroundColor(new BaseColor(128, 159, 255));
            table4.addCell(cellTab4);
            PdfPCell cellTab5 = new PdfPCell(new Phrase("P.U", boldFontTitleTable));
            cellTab5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTab5.setPaddingBottom(5);
            cellTab5.setBackgroundColor(new BaseColor(128, 159, 255));
            table4.addCell(cellTab5);
            PdfPCell cellTab6 = new PdfPCell(new Phrase("P.Total", boldFontTitleTable));
            cellTab6.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTab6.setPaddingBottom(5);
            cellTab6.setBackgroundColor(new BaseColor(128, 159, 255));
            table4.addCell(cellTab6);
            table4.completeRow();
//            PdfPCell cellTab7 = new PdfPCell(new Phrase("% Execution", boldFontTitleTable));
//            cellTab7.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cellTab7.setPaddingBottom(5);
//            table4.addCell(cellTab7);
//            table4.completeRow();

            for (UtilListItemDataDecompte item : listItems) {
                if (item.getTypedata().equals("Item")) {
                    PdfPCell cellInterTab1 = new PdfPCell(new Phrase(item.getDesignation(), normalFont));
                    cellInterTab1.setPaddingBottom(5);
                    cellInterTab1.setPaddingLeft(5);
                    cellInterTab1.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table4.addCell(cellInterTab1);

                    PdfPCell cellInterTab2 = new PdfPCell(new Phrase(item.getRef(), normalFont));
                    cellInterTab2.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellInterTab2.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table4.addCell(cellInterTab2);

                    PdfPCell cellInterTab3 = new PdfPCell(new Phrase(item.getUnite(), normalFont));
                    cellInterTab3.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellInterTab3.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table4.addCell(cellInterTab3);

                    String qt = String.valueOf(item.getQuantite());

                    PdfPCell cellInterTab4 = new PdfPCell(new Phrase(qt, normalFont));
                    cellInterTab4.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellInterTab4.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table4.addCell(cellInterTab4);

                    PdfPCell cellInterTab5 = new PdfPCell(new Phrase(item.getPu(), normalFont));
                    cellInterTab5.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellInterTab5.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table4.addCell(cellInterTab5);

                    PdfPCell cellInterTab6 = new PdfPCell(new Phrase(item.getPrixtotal(), normalFont));
                    cellInterTab6.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellInterTab6.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table4.addCell(cellInterTab6);

//                    String execution = String.valueOf(item.getExecution());
//
//                    PdfPCell cellInterTab7 = new PdfPCell(new Phrase(execution, normalFont));
//                    cellInterTab7.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cellInterTab7.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                    table4.addCell(cellInterTab7);
                } else {
                    PdfPCell cellInterTab12 = new PdfPCell(new Phrase("- " + item.getDesignation(), normalFont));
                    cellInterTab12.setPaddingBottom(5);
                    cellInterTab12.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cellInterTab12.setPaddingLeft(15);
                    table4.addCell(cellInterTab12);

                    PdfPCell cellInterTab22 = new PdfPCell(new Phrase(item.getRef(), normalFont));
                    cellInterTab22.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellInterTab22.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table4.addCell(cellInterTab22);

                    PdfPCell cellInterTab33 = new PdfPCell(new Phrase(item.getUnite(), normalFont));
                    cellInterTab33.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table4.addCell(cellInterTab33);

                    String qtt = String.valueOf(item.getQuantite());

                    PdfPCell cellInterTab44 = new PdfPCell(new Phrase(qtt, normalFont));
                    cellInterTab44.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellInterTab44.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table4.addCell(cellInterTab44);

                    PdfPCell cellInterTab55 = new PdfPCell(new Phrase(item.getPu(), normalFont));
                    cellInterTab55.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellInterTab55.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table4.addCell(cellInterTab55);

                    PdfPCell cellInterTab66 = new PdfPCell(new Phrase(item.getPrixtotal(), normalFont));
                    cellInterTab66.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellInterTab66.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table4.addCell(cellInterTab66);

//                    String execution = String.valueOf(item.getExecution());
//
//                    PdfPCell cellInterTab77 = new PdfPCell(new Phrase(execution, normalFont));
//                    cellInterTab77.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cellInterTab77.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                    table4.addCell(cellInterTab77);
                }
            }

            table4.completeRow();

            document.add(table4);

            //Espace
            //document.add(new Paragraph("     "));
            PdfPTable table5 = new PdfPTable(6);
            table5.setSpacingBefore(10f);
            float[] columnWidthsTabP = new float[]{120f, 120f, 35f, 25f, 100f, 100f};
            table5.setWidths(columnWidthsTabP);
            table5.setWidthPercentage(100);

            PdfPCell cellTabBas1 = new PdfPCell();
            Paragraph paragraphTabBas1 = new Paragraph(" ");
            cellTabBas1.addElement(paragraphTabBas1);
            cellTabBas1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas1.setBorder(Rectangle.NO_BORDER);
            //cellTabBas1.setColspan(3);
            cellTabBas1.setColspan(4);
            table5.addCell(cellTabBas1);

            PdfPCell cellTabBas2 = new PdfPCell();
            Paragraph paragraphTabBas2 = new Paragraph("TOTAL HT", boldFontTitleTable);
            cellTabBas2.addElement(paragraphTabBas2);
            cellTabBas2.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas2.setBorder(Rectangle.BOX);
            cellTabBas2.setBackgroundColor(new BaseColor(128, 159, 255));
            //cellTabBas2.setColspan(2);
            table5.addCell(cellTabBas2);

            PdfPCell cellTabBas3;
            cellTabBas3 = new PdfPCell(new Phrase(totalHT, normalFont));
            cellTabBas3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTabBas3.setBorder(Rectangle.BOX);
            cellTabBas3.setBackgroundColor(new BaseColor(128, 159, 255));
            table5.addCell(cellTabBas3);

            PdfPCell cellTabBas4 = new PdfPCell();
            Paragraph paragraphTabBas4 = new Paragraph(" ");
            cellTabBas4.addElement(paragraphTabBas4);
            cellTabBas4.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas4.setBorder(Rectangle.NO_BORDER);
            //cellTabBas4.setColspan(3);
            cellTabBas4.setColspan(4);
            table5.addCell(cellTabBas4);

            PdfPCell cellTabBas5 = new PdfPCell();
            Paragraph paragraphTabBas5 = new Paragraph("TVA 18%", boldFontTitleTable);
            cellTabBas5.addElement(paragraphTabBas5);
            cellTabBas5.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas5.setBorder(Rectangle.BOX);
            cellTabBas5.setBackgroundColor(new BaseColor(128, 159, 255));
            //cellTabBas5.setColspan(2);
            table5.addCell(cellTabBas5);

            PdfPCell cellTabBas6;
            cellTabBas6 = new PdfPCell(new Phrase(montantTVA, normalFont));
            cellTabBas6.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTabBas6.setBorder(Rectangle.BOX);
            cellTabBas6.setBackgroundColor(new BaseColor(128, 159, 255));
            table5.addCell(cellTabBas6);

            PdfPCell cellTabBas7 = new PdfPCell();
            Paragraph paragraphTabBas7 = new Paragraph(" ");
            cellTabBas7.addElement(paragraphTabBas7);
            cellTabBas7.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas7.setBorder(Rectangle.NO_BORDER);
            //cellTabBas7.setColspan(3);
            cellTabBas7.setColspan(4);
            table5.addCell(cellTabBas7);

            PdfPCell cellTabBas8 = new PdfPCell();
            Paragraph paragraphTabBas8 = new Paragraph("TOTAL TTC", boldFontTitleTable);
            cellTabBas8.addElement(paragraphTabBas8);
            cellTabBas8.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas8.setBorder(Rectangle.BOX);
            cellTabBas8.setBackgroundColor(new BaseColor(128, 159, 255));
            //cellTabBas8.setColspan(2);
            table5.addCell(cellTabBas8);

            PdfPCell cellTabBas9;
            cellTabBas9 = new PdfPCell(new Phrase(totalTTC, normalFont));
            cellTabBas9.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTabBas9.setBorder(Rectangle.BOX);
            cellTabBas9.setBackgroundColor(new BaseColor(128, 159, 255));
            table5.addCell(cellTabBas9);

            PdfPCell cellTabBas10 = new PdfPCell();
            Paragraph paragraphTabBas10 = new Paragraph(" ");
            cellTabBas10.addElement(paragraphTabBas10);
            cellTabBas10.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas10.setBorder(Rectangle.NO_BORDER);
            //cellTabBas7.setColspan(3);
            cellTabBas10.setColspan(4);
            table5.addCell(cellTabBas10);

            PdfPCell cellTabBas11 = new PdfPCell();
            Paragraph paragraphTabBas11 = new Paragraph("Avancement", boldFontTitleTable);
            cellTabBas11.addElement(paragraphTabBas11);
            cellTabBas11.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas11.setBorder(Rectangle.BOX);
            cellTabBas11.setBackgroundColor(new BaseColor(128, 159, 255));
            //cellTabBas8.setColspan(2);
            table5.addCell(cellTabBas11);

            PdfPCell cellTabBas12;
            cellTabBas12 = new PdfPCell(new Phrase(avancementHt, normalFont));
            cellTabBas12.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTabBas12.setBorder(Rectangle.BOX);
            cellTabBas12.setBackgroundColor(new BaseColor(128, 159, 255));
            table5.addCell(cellTabBas12);

            PdfPCell cellTabBas13 = new PdfPCell();
            Paragraph paragraphTabBas13 = new Paragraph(" ");
            cellTabBas13.addElement(paragraphTabBas13);
            cellTabBas13.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas13.setBorder(Rectangle.NO_BORDER);
            //cellTabBas7.setColspan(3);
            cellTabBas13.setColspan(4);
            table5.addCell(cellTabBas13);

            PdfPCell cellTabBas14 = new PdfPCell();
            Paragraph paragraphTabBas14 = new Paragraph("TVA avancement " + valeurXPourcent + "%", boldFontTitleTable);
            cellTabBas14.addElement(paragraphTabBas14);
            cellTabBas14.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas14.setBorder(Rectangle.BOX);
            cellTabBas14.setBackgroundColor(new BaseColor(128, 159, 255));
            //cellTabBas8.setColspan(2);
            table5.addCell(cellTabBas14);

            PdfPCell cellTabBas15;
            cellTabBas15 = new PdfPCell(new Phrase(avancementTVA, normalFont));
            cellTabBas15.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTabBas15.setBorder(Rectangle.BOX);
            cellTabBas15.setBackgroundColor(new BaseColor(128, 159, 255));
            table5.addCell(cellTabBas15);

            PdfPCell cellTabBas16 = new PdfPCell();
            Paragraph paragraphTabBas16 = new Paragraph(" ");
            cellTabBas16.addElement(paragraphTabBas16);
            cellTabBas16.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas16.setBorder(Rectangle.NO_BORDER);
            //cellTabBas7.setColspan(3);
            cellTabBas16.setColspan(4);
            table5.addCell(cellTabBas16);

            PdfPCell cellTabBas17 = new PdfPCell();
            Paragraph paragraphTabBas17 = new Paragraph(" Avancement " + valeurXPourcent + " %", boldFontTitleTable);
            cellTabBas17.addElement(paragraphTabBas17);
            cellTabBas17.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas17.setBorder(Rectangle.BOX);
            cellTabBas17.setBackgroundColor(new BaseColor(128, 159, 255));
            //cellTabBas8.setColspan(2);
            table5.addCell(cellTabBas17);

            PdfPCell cellTabBas18;
            cellTabBas18 = new PdfPCell(new Phrase(avancementTTC, normalFont));
            cellTabBas18.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTabBas18.setBorder(Rectangle.BOX);
            cellTabBas18.setBackgroundColor(new BaseColor(128, 159, 255));
            table5.addCell(cellTabBas18);

            PdfPCell cellTabBas19 = new PdfPCell();
            Paragraph paragraphTabBas19 = new Paragraph(" ");
            cellTabBas19.addElement(paragraphTabBas19);
            cellTabBas19.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas19.setBorder(Rectangle.NO_BORDER);
            //cellTabBas7.setColspan(3);
            cellTabBas19.setColspan(4);
            table5.addCell(cellTabBas19);

            PdfPCell cellTabBas20 = new PdfPCell();
            Paragraph paragraphTabBas20 = new Paragraph("Avance reçu", boldFontTitleTable);
            cellTabBas20.addElement(paragraphTabBas20);
            cellTabBas20.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas20.setBorder(Rectangle.BOX);
            cellTabBas20.setBackgroundColor(new BaseColor(128, 159, 255));
            //cellTabBas8.setColspan(2);
            table5.addCell(cellTabBas20);

            PdfPCell cellTabBas21;
            cellTabBas21 = new PdfPCell(new Phrase(avanceRecu, normalFont));
            cellTabBas21.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTabBas21.setBorder(Rectangle.BOX);
            cellTabBas21.setBackgroundColor(new BaseColor(128, 159, 255));
            table5.addCell(cellTabBas21);

            PdfPCell cellTabBas22 = new PdfPCell();
            Paragraph paragraphTabBas22 = new Paragraph(" ");
            cellTabBas22.addElement(paragraphTabBas22);
            cellTabBas22.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas22.setBorder(Rectangle.NO_BORDER);
            //cellTabBas7.setColspan(3);
            cellTabBas22.setColspan(4);
            table5.addCell(cellTabBas22);

            PdfPCell cellTabBas23 = new PdfPCell();
            Paragraph paragraphTabBas23 = new Paragraph(" Réliquat demandé", boldFontTitleTable);
            cellTabBas23.addElement(paragraphTabBas23);
            cellTabBas23.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTabBas23.setBorder(Rectangle.BOX);
            cellTabBas23.setBackgroundColor(new BaseColor(128, 159, 255));
            //cellTabBas8.setColspan(2);
            table5.addCell(cellTabBas23);

            PdfPCell cellTabBas24;
            cellTabBas24 = new PdfPCell(new Phrase(reliquantDemande, normalFont));
            cellTabBas24.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTabBas24.setBorder(Rectangle.BOX);
            cellTabBas24.setBackgroundColor(new BaseColor(128, 159, 255));
            table5.addCell(cellTabBas24);

            document.add(table5);

            //Espace
            document.add(new Paragraph("     "));

            PdfPTable tableDirecteur = new PdfPTable(2);
            tableDirecteur.setSpacingBefore(10f);
            tableDirecteur.setWidthPercentage(100);
            PdfPCell cellDirect = new PdfPCell(new Phrase("FACTURE ARRETEE A LA SOMME DE : ", normalFont));
            cellDirect.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellDirect.setVerticalAlignment(Element.ALIGN_LEFT);
            cellDirect.setBorder(Rectangle.NO_BORDER);
            tableDirecteur.addCell(cellDirect);

            PdfPCell cell37;
            Chunk underline = new Chunk("Directeur général", boldFont);
            underline.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            cell37 = new PdfPCell(new Phrase(underline));
            cell37.setBorder(0);
            cell37.setColspan(4);
            cell37.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell37.setVerticalAlignment(Element.ALIGN_RIGHT);
            tableDirecteur.addCell(cell37);
            document.add(tableDirecteur);

            PdfPTable tableSommetext = new PdfPTable(2);
            tableSommetext.setSpacingBefore(10f);
            tableSommetext.setWidthPercentage(100);
            PdfPCell cellSommeText = new PdfPCell(new Phrase(mtlUtil, normalFont));
            cellSommeText.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellSommeText.setVerticalAlignment(Element.ALIGN_LEFT);
            cellSommeText.setBorder(Rectangle.NO_BORDER);
            tableSommetext.addCell(cellSommeText);

            PdfPCell cell1SommeText = new PdfPCell(new Phrase(""));
            cell1SommeText.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell1SommeText.setVerticalAlignment(Element.ALIGN_RIGHT);
            cell1SommeText.setBorder(Rectangle.NO_BORDER);
            tableSommetext.addCell(cell1SommeText);

            document.add(tableSommetext);

            document.add(new Paragraph("     "));

            Chunk CBas1 = new Chunk("CONDITIONS GÉNÉRALES", boldFont);
            CBas1.setUnderline(0.1f, -2f);
            Phrase pBas1 = new Phrase();
            pBas1.add(CBas1);
            Paragraph paragarphBas1 = new Paragraph();
            paragarphBas1.add(pBas1);
            document.add(paragarphBas1);

            //Espace
            document.add(new Paragraph("     "));

            document.add(new Paragraph("1. Toutes marchandises livrées ou services rendus demeurent la propriété de", normalFont));
            document.add(new Paragraph("   ACCEL TECHNOLOGIES jusqu'au paiement intégral de la facture.", normalFont));
            document.add(new Paragraph("2. Veuillez mettre le chèque à l'ordre de: ACCEL TECHNOLOGIES", normalFont));

            //Espace
            document.add(new Paragraph("     "));

            // A table with 3 columns
            PdfPTable tableBas3 = new PdfPTable(4);
            tableBas3.setSpacingBefore(10f);
            tableBas3.setWidthPercentage(100);

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
