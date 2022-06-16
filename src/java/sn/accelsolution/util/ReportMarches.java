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
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Image;
//import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import sn.accelsolution.entities.Chantier;
import sn.accelsolution.entities.Detaillechantier;
import sn.accelsolution.entities.Livraison;
import sn.accelsolution.entities.Marche;
import sn.accelsolution.entities.Sortiestock;

/**
 *
 * @author DELL
 */
public class ReportMarches implements Serializable {

    private EntetePath entetePath;

    public ReportMarches() {
        entetePath = new EntetePath();
    }

    public EntetePath getEntetePath() {
        return entetePath;
    }

    public void setEntetePath(EntetePath entetePath) {
        this.entetePath = entetePath;
    }

    public void Generer(Marche marche, String soldeMarche, List<Chantier> allChantiers, String totalApproDirect, String totalApproStock, String totalDecompte, String totalDepenses, String totalMoHt, String totalMoTtc, String reliquat, String pourcentage) throws DocumentException {

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

            String img = entetePath.getUrl();

            Image logoProjet = Image.getInstance(img);
            logoProjet.scaleAbsolute(500f, 150f);
            logoProjet.setAlignment(Image.ALIGN_MIDDLE);
            document.add(logoProjet);

            document.add(new Paragraph("     "));
            document.add(new Paragraph("     "));

            // A table with 4 columns
            PdfPTable tableCDD = new PdfPTable(4);
            tableCDD.setSpacingBefore(10f);
            tableCDD.setWidthPercentage(100);
            PdfPCell cell1;
            cell1 = new PdfPCell(new Phrase("MARCHE: " + marche.getNomMarche()));
            cell1.setColspan(4);
            cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell1.setBorder(0);
            tableCDD.addCell(cell1);
            document.add(tableCDD);

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
            PdfPTable table1 = new PdfPTable(3);
            table1.setSpacingBefore(10f);
            table1.setWidthPercentage(100);

            PdfPCell cell2;
            cell2 = new PdfPCell(new Phrase("Client: " + marche.getIdDevis().getIdClient().getNomClient()));
            cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell2.setVerticalAlignment(Element.ALIGN_LEFT);
            cell2.setBorder(0);
            table1.addCell(cell2);

            PdfPCell cell3;
            cell3 = new PdfPCell(new Phrase("Durée: " + marche.getDureeContrat()));
            cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell3.setVerticalAlignment(Element.ALIGN_LEFT);
            cell3.setBorder(0);
            table1.addCell(cell3);

            PdfPCell cell4;
            cell4 = new PdfPCell(new Phrase("Montant de démarrage: " + marche.getMontantDemarrage()));
            cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell4.setVerticalAlignment(Element.ALIGN_LEFT);
            cell4.setBorder(0);
            table1.addCell(cell4);

            document.add(table1);

            // A table1 with 3 columns
            PdfPTable table2 = new PdfPTable(3);
            table2.setSpacingBefore(10f);
            table2.setWidthPercentage(100);

            PdfPCell cell5;
            cell5 = new PdfPCell(new Phrase("Date de début: " + marche.getDateDebut()));
            cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell5.setVerticalAlignment(Element.ALIGN_LEFT);
            cell5.setBorder(0);
            table2.addCell(cell5);

            PdfPCell cell6;
            cell6 = new PdfPCell(new Phrase("Objet du marché: " + marche.getObjetMarche()));
            cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell6.setVerticalAlignment(Element.ALIGN_LEFT);
            cell6.setBorder(0);
            table2.addCell(cell6);

            PdfPCell cell7;
            cell7 = new PdfPCell(new Phrase("Date de notification: " + marche.getNotification()));
            cell7.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell7.setVerticalAlignment(Element.ALIGN_LEFT);
            cell7.setBorder(0);
            table2.addCell(cell7);

            document.add(table2);

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
            Font blanc = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);

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
            cellAvancement = new PdfPCell(new Phrase("DEPENSES ET AVANCEMENT DU PROJET (Devise : "+marche.getIdDevise().getSymbole()+")"));
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

            String img = entetePath.getUrl();

            Image logoProjet = Image.getInstance(img);
            logoProjet.scaleAbsolute(500f, 150f);
            logoProjet.setAlignment(Image.ALIGN_MIDDLE);
            document.add(logoProjet);

            document.add(new Paragraph("     "));
            document.add(new Paragraph("     "));

            // A table with 4 columns
            PdfPTable tableCDD = new PdfPTable(4);
            tableCDD.setSpacingBefore(10f);
            tableCDD.setWidthPercentage(100);

            PdfPCell cell1;
            cell1 = new PdfPCell(new Phrase("CHANTIER : " + chantierFromEdit.getSiteChantier()));
            cell1.setColspan(4);
            cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell1.setBorder(0);
            tableCDD.addCell(cell1);

            document.add(tableCDD);

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
            PdfPTable table1 = new PdfPTable(3);
            table1.setSpacingBefore(10f);
            table1.setWidthPercentage(100);

            PdfPCell cell2;
            cell2 = new PdfPCell(new Phrase("Projet : " + chantierFromEdit.getIdMarche().getNomMarche()));
            cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell2.setVerticalAlignment(Element.ALIGN_LEFT);
            cell2.setBorder(0);
            table1.addCell(cell2);

            PdfPCell cell3;
            cell3 = new PdfPCell(new Phrase("Chef de chantier : " + chantierFromEdit.getIdUtilisateur().getNomUtilisateur() + " " + chantierFromEdit.getIdUtilisateur().getPrenomUtilisateur()));
            cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell3.setVerticalAlignment(Element.ALIGN_LEFT);
            cell3.setBorder(0);
            table1.addCell(cell3);

            PdfPCell cell4;
            cell4 = new PdfPCell(new Phrase("Etat du chantier : " + chantierFromEdit.getEtatchantier()));
            cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell4.setVerticalAlignment(Element.ALIGN_LEFT);
            cell4.setBorder(0);
            table1.addCell(cell4);

            document.add(table1);

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
            Font blanc = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);

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
            PdfPTable table1P = new PdfPTable(3);
            table1P.setSpacingBefore(10f);
            table1P.setWidthPercentage(100);

            PdfPCell cell2P;
            cell2P = new PdfPCell(new Phrase("Total accords: " + totalAccorps));
            cell2P.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell2P.setVerticalAlignment(Element.ALIGN_LEFT);
            cell2P.setBorder(0);
            table1P.addCell(cell2P);

            PdfPCell cell3P;
            cell3P = new PdfPCell(new Phrase("Total accomptes HT: " + totalAcompteHT));
            cell3P.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell3P.setVerticalAlignment(Element.ALIGN_LEFT);
            cell3P.setBorder(0);
            table1P.addCell(cell3P);

            PdfPCell cell4P;
            cell4P = new PdfPCell(new Phrase("Total accomptes TTC: " + totalAcompteTCC));
            cell4P.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell4P.setVerticalAlignment(Element.ALIGN_LEFT);
            cell4P.setBorder(0);
            table1P.addCell(cell4P);

            document.add(table1P);

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
            Font blanc1 = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);

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
            Font blanc2 = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);

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

}
