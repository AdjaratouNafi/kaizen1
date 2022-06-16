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
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import sn.accelsolution.entities.Commande;
import sn.accelsolution.entities.Decomptep;
import sn.accelsolution.entities.DetailleCommande;
import sn.accelsolution.entities.Devis;
import sn.accelsolution.entities.Expressionbesoin;
import sn.accelsolution.entities.Newfacture;

/**
 *
 * @author DELL
 */
public class ReportFacture implements Serializable {

    private EntetePath entetePath;

    public ReportFacture() {
        entetePath = new EntetePath();
    }

    public EntetePath getEntetePath() {
        return entetePath;
    }

    public void setEntetePath(EntetePath entetePath) {
        this.entetePath = entetePath;
    }

    public void GenererCMB(List<FactureUtil> allFactureUtils, Devis devis, String totalHT, String montantTVA, String totalTTC) throws DocumentException {

        File directory = new File("./");
        String urlAbsolut = directory.getAbsolutePath();
        String url = "";

        try {
            String recuname = "Devis.pdf";
            url = urlAbsolut + recuname;

            System.out.println("Devis Num :" + devis.getNumeroDevis());

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

            String img = entetePath.getUrl();

            Image logoProjet = Image.getInstance(img);
            logoProjet.scaleAbsolute(500f, 140f);
            logoProjet.setAlignment(Image.ALIGN_MIDDLE);
            document.add(logoProjet);

            // A table1 with 4 columns
            PdfPTable table1 = new PdfPTable(3);
            table1.setSpacingBefore(10f);
            table1.setWidthPercentage(100);

            // the cell object
            PdfPCell cell1;
            cell1 = new PdfPCell(new Phrase(""));
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell1.setBackgroundColor(BaseColor.GRAY);
            cell1.setBorder(0);
            table1.addCell(cell1);

            // the cell object
            PdfPCell cell2;
            cell2 = new PdfPCell(new Phrase("DEVIS"));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell2.setBackgroundColor(BaseColor.GRAY);
            cell2.setBorder(0);
            table1.addCell(cell2);

            // the cell object
            PdfPCell cell3;
            cell3 = new PdfPCell(new Phrase(""));
            cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell3.setBackgroundColor(BaseColor.GRAY);
            cell3.setBorder(0);
            table1.addCell(cell3);

            document.add(table1);

            //Espace
            document.add(new Paragraph("     "));

            // A table1 with 4 columns
            PdfPTable tableNA = new PdfPTable(3);
            tableNA.setSpacingBefore(10f);
            tableNA.setWidthPercentage(100);

            // the cell object
            PdfPCell cellNA;
            cellNA = new PdfPCell(new Phrase("Client :  " + devis.getIdClient().getNomClient()));
            cellNA.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellNA.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellNA.setBorder(0);
            tableNA.addCell(cellNA);

            // the cell object
            PdfPCell cellNB;
            cellNB = new PdfPCell(new Phrase(""));
            cellNB.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellNB.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellNB.setBorder(0);
            tableNA.addCell(cellNB);

            // the cell object
            PdfPCell cellNC;
            cellNC = new PdfPCell(new Phrase("Devis numéro :  " + devis.getNumeroDevis()));
            cellNC.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellNC.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellNC.setBorder(0);
            tableNA.addCell(cellNC);

            document.add(tableNA);

            //Espace
            document.add(new Paragraph("     "));

            // A table1 with 4 columns
            PdfPTable tableNNA = new PdfPTable(3);
            tableNNA.setSpacingBefore(10f);
            tableNNA.setWidthPercentage(100);

            // the cell object
            PdfPCell cellNNA;
            cellNNA = new PdfPCell(new Phrase("Adresse :  " + devis.getIdClient().getAdresse()));
            cellNNA.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellNNA.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellNNA.setBorder(0);
            tableNNA.addCell(cellNNA);

            // the cell object
            PdfPCell cellNNB;
            cellNNB = new PdfPCell(new Phrase(""));
            cellNNB.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellNNB.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellNNB.setBorder(0);
            tableNNA.addCell(cellNNB);

            // the cell object
            PdfPCell cellNNC;
            cellNNC = new PdfPCell(new Phrase("Date :  " + devis.getDateDevis()));
            cellNNC.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellNNC.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellNNC.setBorder(0);
            tableNNA.addCell(cellNNC);

            document.add(tableNNA);

            //Espace
            document.add(new Paragraph("     "));

            //Espace
            document.add(new Paragraph("Projet : " + devis.getNommarche()));

            //Espace
            document.add(new Paragraph("     "));

            // A table1 with 4 columns
            PdfPTable tableNNAPab = new PdfPTable(4);
            tableNNAPab.setSpacingBefore(10f);
            tableNNAPab.setWidthPercentage(100);

            // the cell object
            PdfPCell cellNNAPab;
            cellNNAPab = new PdfPCell(new Phrase(devis.getIdDevise().getSymbole()));
            cellNNAPab.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellNNAPab.setVerticalAlignment(Element.ALIGN_RIGHT);
            cellNNAPab.setBorder(0);
            cellNNAPab.setColspan(4);
            tableNNAPab.addCell(cellNNAPab);

            document.add(tableNNAPab);

            //Espace
            document.add(new Paragraph("     "));

            PdfPTable table2 = new PdfPTable(6);
            table2.setSpacingBefore(10f);
            table2.setWidthPercentage(100);

            table2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            table2.addCell("Désignation");
            table2.addCell("Ref");
            table2.addCell("U.mesure");
            table2.addCell("Qte");
            table2.addCell("P.U");
            table2.addCell("P.Total");
            table2.setHeaderRows(1);
            PdfPCell[] cells = table2.getRow(0).getCells();
            for (int j = 0; j < cells.length; j++) {
                cells[j].setBackgroundColor(BaseColor.GRAY);
            }

            for (FactureUtil f : allFactureUtils) {

                table2.addCell(f.getDesignation());
                table2.addCell(f.getRef());
                table2.addCell(f.getUnite());
                String qt = String.valueOf(f.getQuantite());
                table2.addCell(qt);
                table2.addCell(f.getPu());
                table2.addCell(f.getPrixtotal());
            }

            document.add(table2);
//            
            // A table1 with 4 columns
            PdfPTable table3 = new PdfPTable(3);
            table3.setSpacingBefore(10f);
            table3.setWidthPercentage(100);
            // the cell object
            PdfPCell cell4;
            cell4 = new PdfPCell(new Phrase("TOTAL HT: " + totalHT));
            cell4.setColspan(3);
            cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell4.setBorder(0);
            table3.addCell(cell4);
            document.add(table3);

            // A table1 with 4 columns
            PdfPTable table4 = new PdfPTable(3);
            table4.setSpacingBefore(10f);
            table4.setWidthPercentage(100);
            // the cell object
            PdfPCell cell5;
            cell5 = new PdfPCell(new Phrase("TVA 18%: " + montantTVA));
            cell5.setColspan(3);
            cell5.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell5.setBorder(0);
            table4.addCell(cell5);
            document.add(table4);

            // A table1 with 4 columns
            PdfPTable table5 = new PdfPTable(3);
            table5.setSpacingBefore(10f);
            table5.setWidthPercentage(100);
            // the cell object
            PdfPCell cell6;
            cell6 = new PdfPCell(new Phrase("TOTAL TTC: " + totalTTC));
            cell6.setColspan(3);
            cell6.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell6.setBorder(0);
            table5.addCell(cell6);
            document.add(table5);

            //Espace
            document.add(new Paragraph("     "));

            // A table1 with 4 columns
            PdfPTable table7 = new PdfPTable(3);
            table7.setSpacingBefore(10f);
            table7.setWidthPercentage(100);
            // the cell object
            PdfPCell cell8;
            cell8 = new PdfPCell(new Phrase("Proforma arrêté à la somme de: " + devis.getMontantlettre()));
            cell8.setColspan(3);
            cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell8.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell8.setBorder(0);
            table7.addCell(cell8);
            document.add(table7);

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
            Chunk underline = new Chunk("Directeur général");
            underline.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            //document.add(underline);
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

    public void GenererCMBB(List<FactureUtil> allFactureUtils, Newfacture newfacture, String totalHT, String montantTVA, String totalTTC) throws DocumentException {

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
            // step 3
            document.open();

            String img = entetePath.getUrl();

            Image logoProjet = Image.getInstance(img);
            logoProjet.scaleAbsolute(500f, 150f);
            logoProjet.setAlignment(Image.ALIGN_MIDDLE);
            document.add(logoProjet);

            // A table1 with 4 columns
            PdfPTable table1 = new PdfPTable(3);
            table1.setSpacingBefore(10f);
            table1.setWidthPercentage(100);

            // the cell object
            PdfPCell cell1;
            cell1 = new PdfPCell(new Phrase(""));
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell1.setBackgroundColor(BaseColor.GRAY);
            cell1.setBorder(0);
            table1.addCell(cell1);

            // the cell object
            PdfPCell cell2;
            cell2 = new PdfPCell(new Phrase("FACTURE"));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell2.setBackgroundColor(BaseColor.GRAY);
            cell2.setBorder(0);
            table1.addCell(cell2);

            // the cell object
            PdfPCell cell3;
            cell3 = new PdfPCell(new Phrase(""));
            cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell3.setBackgroundColor(BaseColor.GRAY);
            cell3.setBorder(0);
            table1.addCell(cell3);

            document.add(table1);

            //Espace
            document.add(new Paragraph("     "));

            // A table1 with 4 columns
            PdfPTable tableNA = new PdfPTable(3);
            tableNA.setSpacingBefore(10f);
            tableNA.setWidthPercentage(100);

            // the cell object
            PdfPCell cellNA;
            cellNA = new PdfPCell(new Phrase("Client :  " + newfacture.getIdDevis().getIdClient().getNomClient()));
            cellNA.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellNA.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellNA.setBorder(0);
            tableNA.addCell(cellNA);

            // the cell object
            PdfPCell cellNB;
            cellNB = new PdfPCell(new Phrase("Date :  " + newfacture.getDateNewfacture()));
            cellNB.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellNB.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellNB.setBorder(0);
            tableNA.addCell(cellNB);

            // the cell object
            PdfPCell cellNC;
            cellNC = new PdfPCell(new Phrase("Devis numéro :  " + newfacture.getIdDevis().getNumeroDevis()));
            cellNC.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellNC.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellNC.setBorder(0);
            tableNA.addCell(cellNC);

            document.add(tableNA);

            //Espace
            document.add(new Paragraph("     "));

            // A table1 with 4 columns
            PdfPTable tableNNA = new PdfPTable(3);
            tableNNA.setSpacingBefore(10f);
            tableNNA.setWidthPercentage(100);

            // the cell object
            PdfPCell cellNNA;
            cellNNA = new PdfPCell(new Phrase("Adresse :  " + newfacture.getIdDevis().getIdClient().getAdresse()));
            cellNNA.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellNNA.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellNNA.setBorder(0);
            tableNNA.addCell(cellNNA);

            // the cell object
            PdfPCell cellNNB;
            cellNNB = new PdfPCell(new Phrase("Facture numéro :  " + newfacture.getNumeroNewfacture()));
            cellNNB.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellNNB.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellNNB.setBorder(0);
            tableNNA.addCell(cellNNB);

            // the cell object
            PdfPCell cellNNC;
            cellNNC = new PdfPCell(new Phrase("Projet : " + newfacture.getIdDevis().getNommarche()));
            cellNNC.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellNNC.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellNNC.setBorder(0);
            tableNNA.addCell(cellNNC);

            document.add(tableNNA);

            //Espace
            document.add(new Paragraph("     "));

            // A table1 with 4 columns
            PdfPTable tableNNAH = new PdfPTable(4);
            tableNNAH.setSpacingBefore(10f);
            tableNNAH.setWidthPercentage(100);

            // the cell object
            PdfPCell cellNNAH;
            cellNNAH = new PdfPCell(new Phrase("Devise: "+newfacture.getIdMarche().getIdDevise().getSymbole()));
            cellNNAH.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellNNAH.setVerticalAlignment(Element.ALIGN_RIGHT);
            cellNNAH.setBorder(0);
            cellNNAH.setColspan(4);
            tableNNAH.addCell(cellNNAH);

            document.add(tableNNAH);

            //Espace
            document.add(new Paragraph("     "));

            PdfPTable table2 = new PdfPTable(6);
            table2.setSpacingBefore(10f);
            table2.setWidthPercentage(100);

            table2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            table2.addCell("Désignation");
            table2.addCell("Ref");
            table2.addCell("U.mesure");
            table2.addCell("Qte");
            table2.addCell("P.U");
            table2.addCell("P.Total");
            table2.setHeaderRows(1);
            PdfPCell[] cells = table2.getRow(0).getCells();
            for (int j = 0; j < cells.length; j++) {
                cells[j].setBackgroundColor(BaseColor.GRAY);
            }

            for (FactureUtil f : allFactureUtils) {

                table2.addCell(f.getDesignation());
                table2.addCell(f.getRef());
                table2.addCell(f.getUnite());
                String qt = String.valueOf(f.getQuantite());
                table2.addCell(qt);
                table2.addCell(f.getPu());
                table2.addCell(f.getPrixtotal());
            }

            document.add(table2);
//            
            // A table1 with 4 columns
            PdfPTable table3 = new PdfPTable(3);
            table3.setSpacingBefore(10f);
            table3.setWidthPercentage(100);
            // the cell object
            PdfPCell cell4;
            cell4 = new PdfPCell(new Phrase("TOTAL HT: " + totalHT));
            cell4.setColspan(3);
            cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell4.setBorder(0);
            table3.addCell(cell4);
            document.add(table3);

            // A table1 with 4 columns
            PdfPTable table4 = new PdfPTable(3);
            table4.setSpacingBefore(10f);
            table4.setWidthPercentage(100);
            // the cell object
            PdfPCell cell5;
            cell5 = new PdfPCell(new Phrase("TVA 18%: " + montantTVA));
            cell5.setColspan(3);
            cell5.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell5.setBorder(0);
            table4.addCell(cell5);
            document.add(table4);

            // A table1 with 4 columns
            PdfPTable table5 = new PdfPTable(3);
            table5.setSpacingBefore(10f);
            table5.setWidthPercentage(100);
            // the cell object
            PdfPCell cell6;
            cell6 = new PdfPCell(new Phrase("TOTAL TTC: " + totalTTC));
            cell6.setColspan(3);
            cell6.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell6.setBorder(0);
            table5.addCell(cell6);
            document.add(table5);

            //Espace
            document.add(new Paragraph("     "));

            // A table1 with 4 columns
            PdfPTable table7 = new PdfPTable(3);
            table7.setSpacingBefore(10f);
            table7.setWidthPercentage(100);
            // the cell object
            PdfPCell cell8;
            cell8 = new PdfPCell(new Phrase("Arrêtée la facture à la somme de: " + newfacture.getMintantlettreNewfacture()));
            cell8.setColspan(3);
            cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell8.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell8.setBorder(0);
            table7.addCell(cell8);
            document.add(table7);

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
            Chunk underline = new Chunk("Directeur général");
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

    public void GenererCMBBB(List<FactureUtil> allFactureUtils, Decomptep decomptep, String totalHT, String montantTVA, String totalTTC, String valeurXPourcent, String avancementHt, String avancementTVA, String avancementTTC, String avanceRecu, String reliquantDemande) throws DocumentException {

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
            // step 3
            document.open();

            String img = entetePath.getUrl();

            Image logoProjet = Image.getInstance(img);
            logoProjet.scaleAbsolute(500f, 150f);
            logoProjet.setAlignment(Image.ALIGN_MIDDLE);
            document.add(logoProjet);

            // A table1 with 4 columns
            PdfPTable table1 = new PdfPTable(3);
            table1.setSpacingBefore(10f);
            table1.setWidthPercentage(100);

            // the cell object
            PdfPCell cell1;
            cell1 = new PdfPCell(new Phrase(""));
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell1.setBackgroundColor(BaseColor.GRAY);
            cell1.setBorder(0);
            table1.addCell(cell1);

            // the cell object
            PdfPCell cell2;
            cell2 = new PdfPCell(new Phrase("DECOMPTE"));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell2.setBackgroundColor(BaseColor.GRAY);
            cell2.setBorder(0);
            table1.addCell(cell2);

            // the cell object
            PdfPCell cell3;
            cell3 = new PdfPCell(new Phrase(""));
            cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell3.setBackgroundColor(BaseColor.GRAY);
            cell3.setBorder(0);
            table1.addCell(cell3);

            document.add(table1);

            //Espace
            document.add(new Paragraph("     "));

            // A table1 with 4 columns
            PdfPTable tableNA = new PdfPTable(3);
            tableNA.setSpacingBefore(10f);
            tableNA.setWidthPercentage(100);

            // the cell object
            PdfPCell cellNA;
            cellNA = new PdfPCell(new Phrase("Client :  " + decomptep.getIdNewfacture().getIdDevis().getIdClient().getNomClient()));
            cellNA.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellNA.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellNA.setBorder(0);
            tableNA.addCell(cellNA);

            // the cell object
            PdfPCell cellNB;
            cellNB = new PdfPCell(new Phrase("Date :  " + decomptep.getDateDecomptep()));
            cellNB.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellNB.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellNB.setBorder(0);
            tableNA.addCell(cellNB);

            // the cell object
            PdfPCell cellNC;
            cellNC = new PdfPCell(new Phrase("Décompte N :  " + decomptep.getNumeroDecomptep()));
            cellNC.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellNC.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellNC.setBorder(0);
            tableNA.addCell(cellNC);

            document.add(tableNA);

            //Espace
            document.add(new Paragraph("     "));

            // A table1 with 4 columns
            PdfPTable tableNNA = new PdfPTable(3);
            tableNNA.setSpacingBefore(10f);
            tableNNA.setWidthPercentage(100);

            // the cell object
            PdfPCell cellNNA;
            cellNNA = new PdfPCell(new Phrase("Adresse :  " + decomptep.getIdNewfacture().getIdDevis().getIdClient().getAdresse()));
            cellNNA.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellNNA.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellNNA.setBorder(0);
            tableNNA.addCell(cellNNA);

            // the cell object
            PdfPCell cellNNB;
            cellNNB = new PdfPCell(new Phrase("Facture numéro :  " + decomptep.getIdNewfacture().getNumeroNewfacture()));
            cellNNB.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellNNB.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellNNB.setBorder(0);
            tableNNA.addCell(cellNNB);

            // the cell object
            PdfPCell cellNNC;
            cellNNC = new PdfPCell(new Phrase("Projet : " + decomptep.getIdNewfacture().getIdDevis().getNommarche()));
            cellNNC.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellNNC.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellNNC.setBorder(0);
            tableNNA.addCell(cellNNC);

            document.add(tableNNA);

            //Espace
            document.add(new Paragraph("     "));

            // A table1 with 4 columns
            PdfPTable tableNNAP = new PdfPTable(3);
            tableNNAP.setSpacingBefore(10f);
            tableNNAP.setWidthPercentage(100);

            // the cell object
            PdfPCell cellNNAP;
            cellNNAP = new PdfPCell(new Phrase(decomptep.getIdNewfacture().getIdMarche().getIdDevise().getSymbole()));
            cellNNAP.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellNNAP.setVerticalAlignment(Element.ALIGN_RIGHT);
            cellNNAP.setColspan(4);
            cellNNAP.setBorder(0);
            tableNNAP.addCell(cellNNAP);

            document.add(tableNNAP);

            //Espace
            document.add(new Paragraph("     "));

            PdfPTable table2 = new PdfPTable(7);
            table2.setSpacingBefore(10f);
            table2.setWidthPercentage(100);

            table2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            table2.addCell("Désignation");
            table2.addCell("Ref");
            table2.addCell("U.mesure");
            table2.addCell("Qte");
            table2.addCell("Prix unitaire");
            table2.addCell("P.Total");
            table2.addCell("% Execution");
            table2.setHeaderRows(1);
            PdfPCell[] cells = table2.getRow(0).getCells();
            for (int j = 0; j < cells.length; j++) {
                cells[j].setBackgroundColor(BaseColor.GRAY);
            }

            for (FactureUtil f : allFactureUtils) {

                table2.addCell(f.getDesignation());
                table2.addCell(f.getRef());
                table2.addCell(f.getUnite());
                String qt = String.valueOf(f.getQuantite());
                table2.addCell(qt);
                table2.addCell(f.getPu());
                table2.addCell(f.getPrixtotal());
                String pct = String.valueOf(f.getExecution());
                table2.addCell(pct);
            }

            document.add(table2);
//            
            // A table1 with 4 columns
            PdfPTable table3 = new PdfPTable(3);
            table3.setSpacingBefore(10f);
            table3.setWidthPercentage(100);
            // the cell object
            PdfPCell cell4;
            cell4 = new PdfPCell(new Phrase("TOTAL HT: " + totalHT));
            cell4.setColspan(3);
            cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell4.setBorder(0);
            table3.addCell(cell4);
            document.add(table3);

            // A table1 with 4 columns
            PdfPTable table4 = new PdfPTable(3);
            table4.setSpacingBefore(10f);
            table4.setWidthPercentage(100);
            // the cell object
            PdfPCell cell5;
            cell5 = new PdfPCell(new Phrase("TVA 18%: " + montantTVA));
            cell5.setColspan(3);
            cell5.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell5.setBorder(0);
            table4.addCell(cell5);
            document.add(table4);

            // A table1 with 4 columns
            PdfPTable table5 = new PdfPTable(3);
            table5.setSpacingBefore(10f);
            table5.setWidthPercentage(100);
            // the cell object
            PdfPCell cell6;
            cell6 = new PdfPCell(new Phrase("TOTAL TTC: " + totalTTC));
            cell6.setColspan(3);
            cell6.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell6.setBorder(0);
            table5.addCell(cell6);
            document.add(table5);

            // A table1 with 4 columns
            PdfPTable tableA = new PdfPTable(3);
            tableA.setSpacingBefore(10f);
            tableA.setWidthPercentage(100);
            // the cell object
            PdfPCell cellA;
            cellA = new PdfPCell(new Phrase("AVANCEMENT " + valeurXPourcent + "% HT: " + avancementHt));
            cellA.setColspan(3);
            cellA.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellA.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellA.setBorder(0);
            tableA.addCell(cellA);
            document.add(tableA);

            // A table1 with 4 columns
            PdfPTable tableB = new PdfPTable(3);
            tableB.setSpacingBefore(10f);
            tableB.setWidthPercentage(100);
            // the cell object
            PdfPCell cellB;
            cellB = new PdfPCell(new Phrase("TVA AVANCEMENT " + valeurXPourcent + "% : " + avancementTVA));
            cellB.setColspan(3);
            cellB.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellB.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellB.setBorder(0);
            tableB.addCell(cellB);
            document.add(tableB);

            // A table1 with 4 columns
            PdfPTable tableC = new PdfPTable(3);
            tableC.setSpacingBefore(10f);
            tableC.setWidthPercentage(100);
            // the cell object
            PdfPCell cellC;
            cellC = new PdfPCell(new Phrase("AVANCEMENT " + valeurXPourcent + "% TTC : " + avancementTVA));
            cellC.setColspan(3);
            cellC.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellC.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellC.setBorder(0);
            tableC.addCell(cellC);
            document.add(tableC);

            // A table1 with 4 columns
            PdfPTable tableD = new PdfPTable(3);
            tableD.setSpacingBefore(10f);
            tableD.setWidthPercentage(100);
            // the cell object
            PdfPCell cellD;
            cellD = new PdfPCell(new Phrase("AVANCE RECUE : " + avanceRecu));
            cellD.setColspan(3);
            cellD.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellD.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellD.setBorder(0);
            tableD.addCell(cellD);
            document.add(tableD);

            // A table1 with 4 columns
            PdfPTable tableE = new PdfPTable(3);
            tableE.setSpacingBefore(10f);
            tableE.setWidthPercentage(100);
            // the cell object
            PdfPCell cellE;
            cellE = new PdfPCell(new Phrase("RELIQUAT DEMANDE : " + reliquantDemande));
            cellE.setColspan(3);
            cellE.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellE.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellE.setBorder(0);
            tableE.addCell(cellE);
            document.add(tableE);

            // A table1 with 4 columns
            PdfPTable tableF = new PdfPTable(3);
            tableF.setSpacingBefore(10f);
            tableF.setWidthPercentage(100);
            // the cell object
            PdfPCell cellF;
            cellF = new PdfPCell(new Phrase("Arrêtée la facture à la somme de: " + decomptep.getMontantlettre()));
            cellF.setColspan(3);
            cellF.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellF.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellF.setBorder(0);
            tableF.addCell(cellF);
            document.add(tableF);

            //Espace
            document.add(new Paragraph("     "));

            // A table with 3 columns
            PdfPTable tableBas3 = new PdfPTable(4);
            tableBas3.setSpacingBefore(10f);
            tableBas3.setWidthPercentage(100);

            // the cell object
            PdfPCell cell37;
            Chunk underline = new Chunk("Directeur général");
            underline.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            cell37 = new PdfPCell(new Phrase(underline));
            cell37.setBorder(0);
            cell37.setColspan(4);
            cell37.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell37.setVerticalAlignment(Element.ALIGN_RIGHT);
            tableBas3.addCell(cell37);
            document.add(tableBas3);

            //Espace
            document.add(new Paragraph("     "));

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

    public void GenererCMBBPPP(List<FactureUtil> allFactureUtils, Newfacture newfacture, String totalHT, String montantTVA, String totalTTC) throws DocumentException {

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
            // step 3
            document.open();

            String img = entetePath.getUrl();

            Image logoProjet = Image.getInstance(img);
            logoProjet.scaleAbsolute(500f, 150f);
            logoProjet.setAlignment(Image.ALIGN_MIDDLE);
            document.add(logoProjet);

            // A table1 with 4 columns
            PdfPTable table1 = new PdfPTable(3);
            table1.setSpacingBefore(10f);
            table1.setWidthPercentage(100);

            // the cell object
            PdfPCell cell1;
            cell1 = new PdfPCell(new Phrase(""));
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell1.setBackgroundColor(BaseColor.GRAY);
            cell1.setBorder(0);
            table1.addCell(cell1);

            // the cell object
            PdfPCell cell2;
            cell2 = new PdfPCell(new Phrase("FACTURE"));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell2.setBackgroundColor(BaseColor.GRAY);
            cell2.setBorder(0);
            table1.addCell(cell2);

            // the cell object
            PdfPCell cell3;
            cell3 = new PdfPCell(new Phrase(""));
            cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell3.setBackgroundColor(BaseColor.GRAY);
            cell3.setBorder(0);
            table1.addCell(cell3);

            document.add(table1);

            //Espace
            document.add(new Paragraph("     "));

            // A table1 with 4 columns
            PdfPTable tableNA = new PdfPTable(3);
            tableNA.setSpacingBefore(10f);
            tableNA.setWidthPercentage(100);

            // the cell object
            PdfPCell cellNA;
            cellNA = new PdfPCell(new Phrase("Client :  " + newfacture.getIdDevis().getIdClient().getNomClient()));
            cellNA.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellNA.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellNA.setBorder(0);
            tableNA.addCell(cellNA);

            // the cell object
            PdfPCell cellNB;
            cellNB = new PdfPCell(new Phrase("Date :  " + newfacture.getDateNewfacture()));
            cellNB.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellNB.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellNB.setBorder(0);
            tableNA.addCell(cellNB);

            // the cell object
            PdfPCell cellNC;
            cellNC = new PdfPCell(new Phrase("Devis numéro :  " + newfacture.getIdDevis().getNumeroDevis()));
            cellNC.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellNC.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellNC.setBorder(0);
            tableNA.addCell(cellNC);

            document.add(tableNA);

            //Espace
            document.add(new Paragraph("     "));

            // A table1 with 4 columns
            PdfPTable tableNNA = new PdfPTable(3);
            tableNNA.setSpacingBefore(10f);
            tableNNA.setWidthPercentage(100);

            // the cell object
            PdfPCell cellNNA;
            cellNNA = new PdfPCell(new Phrase("Adresse :  " + newfacture.getIdDevis().getIdClient().getAdresse()));
            cellNNA.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellNNA.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellNNA.setBorder(0);
            tableNNA.addCell(cellNNA);

            // the cell object
            PdfPCell cellNNB;
            cellNNB = new PdfPCell(new Phrase("Facture numéro :  " + newfacture.getNumeroNewfacture()));
            cellNNB.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellNNB.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellNNB.setBorder(0);
            tableNNA.addCell(cellNNB);

            // the cell object
            PdfPCell cellNNC;
            cellNNC = new PdfPCell(new Phrase("Projet : " + newfacture.getIdDevis().getNommarche()));
            cellNNC.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellNNC.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellNNC.setBorder(0);
            tableNNA.addCell(cellNNC);

            document.add(tableNNA);

            //Espace
            document.add(new Paragraph("     "));

            PdfPTable table2 = new PdfPTable(6);
            table2.setSpacingBefore(10f);
            table2.setWidthPercentage(100);

            table2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            table2.addCell("Désignation");
            table2.addCell("Ref");
            table2.addCell("U.mesure");
            table2.addCell("Qte");
            table2.addCell("Qte livrée");
            table2.addCell("% Execution");
            table2.setHeaderRows(1);
            PdfPCell[] cells = table2.getRow(0).getCells();
            for (int j = 0; j < cells.length; j++) {
                cells[j].setBackgroundColor(BaseColor.GRAY);
            }

            for (FactureUtil f : allFactureUtils) {

                table2.addCell(f.getDesignation());
                table2.addCell(f.getRef());
                table2.addCell(f.getUnite());
                String qt = String.valueOf(f.getQuantite());
                table2.addCell(qt);
                table2.addCell("");
                table2.addCell("");
            }

            document.add(table2);

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
            Chunk underline = new Chunk("Chef de projet");
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

    public void GenererExpression(List<FactureUtil> allFactureUtils, Expressionbesoin expressionbesoin, String totalHT, String montantTVA, String totalTTC) throws DocumentException {

        File directory = new File("./");
        String urlAbsolut = directory.getAbsolutePath();
        String url = "";

        try {
            String recuname = "Expression_besoin.pdf";
            url = urlAbsolut + recuname;

            System.out.println("Expression de besoins Num :" + expressionbesoin.getNumeroExpression());

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

            String img = entetePath.getUrl();

            Image logoProjet = Image.getInstance(img);
            logoProjet.scaleAbsolute(500f, 140f);
            logoProjet.setAlignment(Image.ALIGN_MIDDLE);
            document.add(logoProjet);

            // A table1 with 4 columns
            PdfPTable table1 = new PdfPTable(3);
            table1.setSpacingBefore(10f);
            table1.setWidthPercentage(100);

            // the cell object
            PdfPCell cell1;
            cell1 = new PdfPCell(new Phrase(""));
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell1.setBackgroundColor(BaseColor.GRAY);
            cell1.setBorder(0);
            table1.addCell(cell1);

            // the cell object
            PdfPCell cell2;
            cell2 = new PdfPCell(new Phrase("EXPRESSION DE BESOIN"));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell2.setBackgroundColor(BaseColor.GRAY);
            cell2.setBorder(0);
            table1.addCell(cell2);

            // the cell object
            PdfPCell cell3;
            cell3 = new PdfPCell(new Phrase(""));
            cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell3.setBackgroundColor(BaseColor.GRAY);
            cell3.setBorder(0);
            table1.addCell(cell3);

            document.add(table1);

            //Espace
            document.add(new Paragraph("     "));

            //Espace
            document.add(new Paragraph("Demandeur :  " + expressionbesoin.getIdUtilisateur().getNomUtilisateur() + " " + expressionbesoin.getIdUtilisateur().getPrenomUtilisateur()));

            //Espace
            document.add(new Paragraph("     "));

            //Espace
            document.add(new Paragraph("Chantier :  " + expressionbesoin.getIdChantier().getSiteChantier()));

            //Espace
            document.add(new Paragraph("     "));

            //Espace
            document.add(new Paragraph("Date :  " + expressionbesoin.getDateExpression()));

            //Espace
            document.add(new Paragraph("     "));

            //Espace
            document.add(new Paragraph("Expression de besoin numéro :  " + expressionbesoin.getNumeroExpression()));

            //Espace
            document.add(new Paragraph("     "));

            PdfPTable table2 = new PdfPTable(4);
            table2.setSpacingBefore(10f);
            table2.setWidthPercentage(100);

            table2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            table2.addCell("Désignation");
            table2.addCell("Ref");
            table2.addCell("U.mesure");
            table2.addCell("Qte");
            table2.setHeaderRows(1);
            PdfPCell[] cells = table2.getRow(0).getCells();
            for (int j = 0; j < cells.length; j++) {
                cells[j].setBackgroundColor(BaseColor.GRAY);
            }

            for (FactureUtil f : allFactureUtils) {

                table2.addCell(f.getDesignation());
                table2.addCell(f.getRef());
                table2.addCell(f.getUnite());
                String qt = String.valueOf(f.getQuantite());
                table2.addCell(qt);
            }

            document.add(table2);

//            //Espace
//            document.add(new Paragraph("     "));
//
//            // A table1 with 4 columns
//            PdfPTable table7 = new PdfPTable(3);
//            table7.setSpacingBefore(10f);
//            table7.setWidthPercentage(100);
//            // the cell object
//            PdfPCell cell8;
//            cell8 = new PdfPCell(new Phrase("Proforma arrêté à la somme de: " + devis.getMontantlettre() + " F CFA TTC"));
//            cell8.setColspan(3);
//            cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
//            cell8.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell8.setBorder(0);
//            table7.addCell(cell8);
//            document.add(table7);
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
            Chunk underline = new Chunk("Directeur général");
            underline.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            //document.add(underline);
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

    public void GenererBonCommande(List<FactureUtil> allFactureUtils, Commande commande, String totalHT, String montantTVA, String totalTTC) throws DocumentException {

        File directory = new File("./");
        String urlAbsolut = directory.getAbsolutePath();
        String url = "";

        try {
            String recuname = "Bon_de_commande.pdf";
            url = urlAbsolut + recuname;

            System.out.println("Bon de commande numéro :" + commande.getCode());

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

            String img = entetePath.getUrl();

            Image logoProjet = Image.getInstance(img);
            logoProjet.scaleAbsolute(500f, 140f);
            logoProjet.setAlignment(Image.ALIGN_MIDDLE);
            document.add(logoProjet);

            // A table1 with 4 columns
            PdfPTable table1 = new PdfPTable(3);
            table1.setSpacingBefore(10f);
            table1.setWidthPercentage(100);

            // the cell object
            PdfPCell cell1;
            cell1 = new PdfPCell(new Phrase(""));
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell1.setBackgroundColor(BaseColor.GRAY);
            cell1.setBorder(0);
            table1.addCell(cell1);

            // the cell object
            PdfPCell cell2;
            cell2 = new PdfPCell(new Phrase("BON DE COMMANDE"));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell2.setBackgroundColor(BaseColor.GRAY);
            cell2.setBorder(0);
            table1.addCell(cell2);

            // the cell object
            PdfPCell cell3;
            cell3 = new PdfPCell(new Phrase(""));
            cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell3.setBackgroundColor(BaseColor.GRAY);
            cell3.setBorder(0);
            table1.addCell(cell3);

            document.add(table1);

            //Espace
            document.add(new Paragraph("     "));

            // A table1 with 4 columns
            PdfPTable tableNA = new PdfPTable(3);
            tableNA.setSpacingBefore(10f);
            tableNA.setWidthPercentage(100);

            // the cell object
            PdfPCell cellNA;
            cellNA = new PdfPCell(new Phrase("Fournisseur :  " + commande.getIdFournisseur().getNomFournisseur()));
            cellNA.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellNA.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellNA.setBorder(0);
            tableNA.addCell(cellNA);

            // the cell object
            PdfPCell cellNB;
            cellNB = new PdfPCell(new Phrase("Bon de commande numéro :  " + commande.getCode()));
            cellNB.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellNB.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellNB.setBorder(0);
            tableNA.addCell(cellNB);

            // the cell object
            PdfPCell cellNC;
            cellNC = new PdfPCell(new Phrase("Date :  " + commande.getDateechance()));
            cellNC.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellNC.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellNC.setBorder(0);
            tableNA.addCell(cellNC);

            document.add(tableNA);

            //Espace
            document.add(new Paragraph("     "));

            // A table1 with 4 columns
            PdfPTable tableNNA = new PdfPTable(3);
            tableNNA.setSpacingBefore(10f);
            tableNNA.setWidthPercentage(100);

            // the cell object
            PdfPCell cellNNA;
            cellNNA = new PdfPCell(new Phrase("Adresse :  " + commande.getIdFournisseur().getAdresseFournisseur()));
            cellNNA.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellNNA.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellNNA.setBorder(0);
            tableNNA.addCell(cellNNA);

            if (commande.getTypecommande().equals("Exp besoin")) {

                // the cell object
                PdfPCell cellNNB;
                cellNNB = new PdfPCell(new Phrase("Marché : "+commande.getIdMarche().getNomMarche()));
                cellNNB.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellNNB.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cellNNB.setBorder(0);
                tableNNA.addCell(cellNNB);

                // the cell object
                PdfPCell cellNNC;
                cellNNC = new PdfPCell(new Phrase("Chantier : " + commande.getIdChantier().getSiteChantier()));
                cellNNC.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cellNNC.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cellNNC.setBorder(0);
                tableNNA.addCell(cellNNC);

                document.add(tableNNA);
            }

            //Espace
            document.add(new Paragraph("     "));

            if (commande.getTypecommande().equals("Appro stock")) {

                // A table1 with 4 columns
                PdfPTable tableNNAP1 = new PdfPTable(4);
                tableNNAP1.setSpacingBefore(10f);
                tableNNAP1.setWidthPercentage(100);

                // the cell object
                PdfPCell cellNNAP1;
                cellNNAP1 = new PdfPCell(new Phrase(commande.getIdMarche().getIdDevise().getSymbole()));
                cellNNAP1.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cellNNAP1.setVerticalAlignment(Element.ALIGN_RIGHT);
                cellNNAP1.setBorder(0);
                cellNNAP1.setColspan(4);
                tableNNAP1.addCell(cellNNAP1);

                document.add(tableNNAP1);

            }

            if (commande.getTypecommande().equals("Exp besoin")) {

                // A table1 with 4 columns
                PdfPTable tableNNAP2 = new PdfPTable(3);
                tableNNAP2.setSpacingBefore(10f);
                tableNNAP2.setWidthPercentage(100);

                // the cell object
                PdfPCell cellNNAP2;
                cellNNAP2 = new PdfPCell(new Phrase(commande.getIdChantier().getIdMarche().getIdDevise().getSymbole()));
                cellNNAP2.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cellNNAP2.setVerticalAlignment(Element.ALIGN_RIGHT);
                cellNNAP2.setBorder(0);
                cellNNAP2.setColspan(4);
                tableNNAP2.addCell(cellNNAP2);

                document.add(tableNNAP2);

            }

            //Espace
            document.add(new Paragraph("     "));

            PdfPTable table2 = new PdfPTable(6);
            table2.setSpacingBefore(10f);
            table2.setWidthPercentage(100);

            table2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            table2.addCell("Désignation");
            table2.addCell("Ref");
            table2.addCell("U.mesure");
            table2.addCell("Qte");
            table2.addCell("P.U");
            table2.addCell("P.Total");
            table2.setHeaderRows(1);
            PdfPCell[] cells = table2.getRow(0).getCells();
            for (int j = 0; j < cells.length; j++) {
                cells[j].setBackgroundColor(BaseColor.GRAY);
            }

            for (FactureUtil f : allFactureUtils) {

                table2.addCell(f.getDesignation());
                table2.addCell(f.getRef());
                table2.addCell(f.getUnite());
                String qt = String.valueOf(f.getQuantite());
                table2.addCell(qt);
                table2.addCell(f.getPu());
                table2.addCell(f.getPrixtotal());
            }

            document.add(table2);
//            
            // A table1 with 4 columns
            PdfPTable table3 = new PdfPTable(3);
            table3.setSpacingBefore(10f);
            table3.setWidthPercentage(100);
            // the cell object
            PdfPCell cell4;
            cell4 = new PdfPCell(new Phrase("TOTAL HT: " + totalHT));
            cell4.setColspan(3);
            cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell4.setBorder(0);
            table3.addCell(cell4);
            document.add(table3);

            // A table1 with 4 columns
            PdfPTable table4 = new PdfPTable(3);
            table4.setSpacingBefore(10f);
            table4.setWidthPercentage(100);
            // the cell object
            PdfPCell cell5;
            cell5 = new PdfPCell(new Phrase("TVA 18%: " + montantTVA));
            cell5.setColspan(3);
            cell5.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell5.setBorder(0);
            table4.addCell(cell5);
            document.add(table4);

            // A table1 with 4 columns
            PdfPTable table5 = new PdfPTable(3);
            table5.setSpacingBefore(10f);
            table5.setWidthPercentage(100);
            // the cell object
            PdfPCell cell6;
            cell6 = new PdfPCell(new Phrase("TOTAL TTC: " + totalTTC));
            cell6.setColspan(3);
            cell6.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell6.setBorder(0);
            table5.addCell(cell6);
            document.add(table5);

//            //Espace
//            document.add(new Paragraph("     "));
//
//            // A table1 with 4 columns
//            PdfPTable table7 = new PdfPTable(3);
//            table7.setSpacingBefore(10f);
//            table7.setWidthPercentage(100);
//            // the cell object
//            PdfPCell cell8;
//            cell8 = new PdfPCell(new Phrase("Proforma arrêté à la somme de: " + devis.getMontantlettre() + " F CFA TTC"));
//            cell8.setColspan(3);
//            cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
//            cell8.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell8.setBorder(0);
//            table7.addCell(cell8);
//            document.add(table7);
            //Espace
            document.add(new Paragraph("     "));
            //Espace
            document.add(new Paragraph("     "));

            // A table with 3 columns
            PdfPTable tableBas3 = new PdfPTable(3);
            tableBas3.setSpacingBefore(10f);
            tableBas3.setWidthPercentage(100);

            // the cell object
            PdfPCell cell35;
            cell35 = new PdfPCell(new Phrase(""));
            cell35.setBorder(0);
            tableBas3.addCell(cell35);
            // the cell object
            PdfPCell cell36;
            cell36 = new PdfPCell(new Phrase(""));
            cell36.setBorder(0);
            tableBas3.addCell(cell36);
            // the cell object
            PdfPCell cell37;
            Chunk underline = new Chunk("Directeur général");
            underline.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            //document.add(underline);
            cell37 = new PdfPCell(new Phrase(underline));
            cell37.setBorder(0);
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

}
