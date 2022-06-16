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
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import sn.accelsolution.dao.PrixFacade;
import sn.accelsolution.entities.Commande;
import sn.accelsolution.entities.DetailleCommande;
import sn.accelsolution.entities.Prix;
import sn.accelsolution.entities.Salaire;
import sn.accelsolution.entities.Utilisateur;

/**
 *
 * @author DELL
 */
public class ReportBonCommande implements Serializable {

    
    @EJB
    PrixFacade ejbprix;
    
    private EntetePath entetePath;
    private Prix prix;
    

    public ReportBonCommande() {
        entetePath = new EntetePath();
        prix = new Prix();
    }

    public EntetePath getEntetePath() {
        return entetePath;
    }

    public void setEntetePath(EntetePath entetePath) {
        this.entetePath = entetePath;
    }

    public Prix getPrix() {
        return prix;
    }

    public void setPrix(Prix prix) {
        this.prix = prix;
    }
    

    public void GenererCMB(Commande commande, List<DetailleCommande> detailleCommandes) throws DocumentException {

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
            cell1 = new PdfPCell(new Phrase("BON DE COMMANDE"));
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell1.setBackgroundColor(BaseColor.GRAY);
            cell1.setBorder(0);
            table1.addCell(cell1);

            // the cell object
            PdfPCell cell2;
            cell2 = new PdfPCell(new Phrase("FOURNISSEURS / SOUS TRAITANT / PRESTATAIRES"));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell2.setBackgroundColor(BaseColor.GRAY);
            cell2.setBorder(0);
            table1.addCell(cell2);

            // the cell object
            PdfPCell cell3;
            cell3 = new PdfPCell(new Phrase("N :" + 123));
            cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell3.setBackgroundColor(BaseColor.GRAY);
            cell3.setBorder(0);
            table1.addCell(cell3);

            document.add(table1);

            //Espace
            document.add(new Paragraph("     "));

            //Espace
            document.add(new Paragraph("Fournisseur : " + commande.getIdFournisseur().getIdFournisseur() + "  " + commande.getIdFournisseur().getNomFournisseur() + "  " + commande.getIdFournisseur().getAdresseFournisseur() + " " + commande.getIdFournisseur().getTelephoneFournisseur()));

            //Espace
            document.add(new Paragraph("     "));

            //Espace
            document.add(new Paragraph("Marche : " + commande.getIdMarche().getIdMarche() + "  " + commande.getIdMarche().getNomMarche()));

            //Espace
            document.add(new Paragraph("     "));

            //Espace
            document.add(new Paragraph("Chantier : " + commande.getIdChantier().getIdChantier() + "  " + commande.getIdChantier().getSiteChantier()));

            //Espace
            document.add(new Paragraph("     "));

            PdfPTable table2 = new PdfPTable(7);
            table2.setSpacingBefore(10f);
            table2.setWidthPercentage(100);

            table2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table2.addCell("Désignation");
            table2.addCell("Qté");
            table2.addCell("PU.HT");
            table2.addCell("TVA");
            table2.addCell("Taxes");
            table2.addCell("Hors taxes");
            table2.addCell("Total TTC");
            table2.setHeaderRows(1);
            PdfPCell[] cells = table2.getRow(0).getCells();
            for (int j = 0; j < cells.length; j++) {
                cells[j].setBackgroundColor(BaseColor.GRAY);
            }

            int qt = 0;
            Double remise = 0.0;
            Double ttremise = 0.0;
            Double prixTaxe = 0.0;
            Double prixtaxe = 0.0;
            Double mht = 0.0;
            Double mtht = 0.0;
            Double mtttc = 0.0;
            Double total = 0.0;
            MontantConverter montantMc = new MontantConverter();

            for (DetailleCommande d : detailleCommandes) {
                qt = qt + d.getQuantite();
               
                remise = montantMc.StringToDouble(d.getRemise());
                ttremise = ttremise + remise;

                prixTaxe = montantMc.StringToDouble(d.getMontant());
                prixtaxe = prixtaxe + prixTaxe;

                mht = montantMc.StringToDouble(d.getMontanthortaxe());
                mtht = mtht + mht;

                Double ttc = montantMc.StringToDouble(d.getMontanthortaxe());
                mtttc = mtttc + ttc;

                table2.addCell(d.getIdMarchandise().getLibelle());
                table2.addCell(d.getQuantite().toString());
                table2.addCell("" + " CFA");
                table2.addCell("18%");
                table2.addCell(d.getMontant() + " CFA");
                table2.addCell(d.getMontanthortaxe() + " CFA");
                table2.addCell(d.getTtc() + " CFA");
            }

            document.add(table2);

            total = mtht + prixTaxe - remise;

            // A table1 with 4 columns
            PdfPTable table3 = new PdfPTable(3);
            table3.setSpacingBefore(10f);
            table3.setWidthPercentage(100);
            // the cell object
            PdfPCell cell4;
            cell4 = new PdfPCell(new Phrase("REMISE: ===> " + ttremise.toString() + " FCFA"));
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
            cell5 = new PdfPCell(new Phrase("TAXES: ===> " + prixtaxe.toString() + " FCFA"));
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
            cell6 = new PdfPCell(new Phrase("H. TAXES: ===> " + mtht.toString() + " FCFA"));
            cell6.setColspan(3);
            cell6.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell6.setBorder(0);
            table5.addCell(cell6);
            document.add(table5);

            // A table1 with 4 columns
            PdfPTable table6 = new PdfPTable(3);
            table6.setSpacingBefore(10f);
            table6.setWidthPercentage(100);
            // the cell object
            PdfPCell cell7;
            cell7 = new PdfPCell(new Phrase("MONTANT TTC: ===> " + total.toString() + " FCFA"));
            cell7.setColspan(3);
            cell7.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell7.setBorder(0);
            table6.addCell(cell7);
            document.add(table6);

            //Espace
            document.add(new Paragraph("     "));

            // A table1 with 4 columns
            PdfPTable table7 = new PdfPTable(3);
            table7.setSpacingBefore(10f);
            table7.setWidthPercentage(100);
            // the cell object
            PdfPCell cell8;
            cell8 = new PdfPCell(new Phrase("Mode de paiement: " + commande.getModepaiment()));
            cell8.setColspan(3);
            cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell8.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell8.setBorder(0);
            table7.addCell(cell8);
            document.add(table7);

            // A table1 with 4 columns
            PdfPTable table8 = new PdfPTable(3);
            table8.setSpacingBefore(10f);
            table8.setWidthPercentage(100);
            // the cell object
            PdfPCell cell9;
            cell9 = new PdfPCell(new Phrase("Date Echeance: " + commande.getDateechance()));
            cell9.setColspan(3);
            cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell9.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell9.setBorder(0);
            table8.addCell(cell9);
            document.add(table8);

            // A table1 with 4 columns
            PdfPTable table9 = new PdfPTable(3);
            table9.setSpacingBefore(10f);
            table9.setWidthPercentage(100);
            // the cell object
            PdfPCell cell10;
            cell10 = new PdfPCell(new Phrase("Observations: " + commande.getObservation()));
            cell10.setColspan(3);
            cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell10.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell10.setBorder(0);
            table9.addCell(cell10);
            document.add(table9);

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

    public void GenererCMBB(Commande commande, List<DetailleCommande> detailleCommandes) throws DocumentException {

        File directory = new File("./");
        String urlAbsolut = directory.getAbsolutePath();
        String url = "";

        try {
            String recuname = "Facture_proformat.pdf";
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

            //String img = "C:\\Users\\DELL\\Documents\\NetBeansProjects\\Estprod\\src\\java\\sn\\accelsolution\\util\\tete.png";
            String img = "http://144.217.254.91/tete.png";

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
            cell1 = new PdfPCell(new Phrase("FACTURE PROFORMAT"));
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell1.setBackgroundColor(BaseColor.GRAY);
            cell1.setBorder(0);
            table1.addCell(cell1);

            // the cell object
            PdfPCell cell2;
            cell2 = new PdfPCell(new Phrase(" "));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell2.setBackgroundColor(BaseColor.GRAY);
            cell2.setBorder(0);
            table1.addCell(cell2);

            // the cell object
            PdfPCell cell3;
            cell3 = new PdfPCell(new Phrase("N :" + commande.getCode()));
            cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell3.setBackgroundColor(BaseColor.GRAY);
            cell3.setBorder(0);
            table1.addCell(cell3);

            document.add(table1);

            //Espace
            document.add(new Paragraph("     "));

            //Espace
            document.add(new Paragraph("Client : " + commande.getIdClient().getIdClient() + "  " + commande.getIdClient().getNomClient() + "  " + commande.getIdClient().getAdresse() + " " + commande.getIdClient().getTelephoneClient()));

            //Espace
            document.add(new Paragraph("     "));

            PdfPTable table2 = new PdfPTable(7);
            table2.setSpacingBefore(10f);
            table2.setWidthPercentage(100);

            table2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table2.addCell("Désignation");
            table2.addCell("Qté");
            table2.addCell("PU.HT");
            table2.addCell("TVA");
            table2.addCell("Taxes");
            table2.addCell("Hors taxes");
            table2.addCell("Total TTC");
            table2.setHeaderRows(1);
            PdfPCell[] cells = table2.getRow(0).getCells();
            for (int j = 0; j < cells.length; j++) {
                cells[j].setBackgroundColor(BaseColor.GRAY);
            }

            int qt = 0;
            Double remise = 0.0;
            Double ttremise = 0.0;
            Double prixTaxe = 0.0;
            Double prixtaxe = 0.0;
            Double mht = 0.0;
            Double mtht = 0.0;
            Double mtttc = 0.0;
            Double total = 0.0;

            for (DetailleCommande d : detailleCommandes) {
                qt = qt + d.getQuantite();

                remise = Double.parseDouble(d.getRemise());
                ttremise = ttremise + remise;

                prixTaxe = Double.parseDouble(d.getMontant());
                prixtaxe = prixtaxe + prixTaxe;

                mht = Double.parseDouble(d.getMontanthortaxe());
                mtht = mtht + mht;

                Double ttc = Double.parseDouble(d.getMontanthortaxe());
                mtttc = mtttc + ttc;

                table2.addCell(d.getDesignation());
                table2.addCell(d.getQuantite().toString());
                table2.addCell(d.getPu() + " CFA");
                table2.addCell("18%");
                table2.addCell(d.getMontant() + " CFA");
                table2.addCell(d.getMontanthortaxe() + " CFA");
                table2.addCell(d.getTtc() + " CFA");
            }

            document.add(table2);

            total = mtht + prixTaxe - remise;

            // A table1 with 4 columns
            PdfPTable table3 = new PdfPTable(3);
            table3.setSpacingBefore(10f);
            table3.setWidthPercentage(100);
            // the cell object
            PdfPCell cell4;
            cell4 = new PdfPCell(new Phrase("REMISE: ===> " + ttremise.toString() + " FCFA"));
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
            cell5 = new PdfPCell(new Phrase("TAXES: ===> " + prixtaxe.toString() + " FCFA"));
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
            cell6 = new PdfPCell(new Phrase("H. TAXES: ===> " + mtht.toString() + " FCFA"));
            cell6.setColspan(3);
            cell6.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell6.setBorder(0);
            table5.addCell(cell6);
            document.add(table5);

            // A table1 with 4 columns
            PdfPTable table6 = new PdfPTable(3);
            table6.setSpacingBefore(10f);
            table6.setWidthPercentage(100);
            // the cell object
            PdfPCell cell7;
            cell7 = new PdfPCell(new Phrase("MONTANT TTC: ===> " + total.toString() + " FCFA"));
            cell7.setColspan(3);
            cell7.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell7.setBorder(0);
            table6.addCell(cell7);
            document.add(table6);

            //Espace
            document.add(new Paragraph("     "));

            // A table1 with 4 columns
            PdfPTable table7 = new PdfPTable(3);
            table7.setSpacingBefore(10f);
            table7.setWidthPercentage(100);
            // the cell object
            PdfPCell cell8;
            cell8 = new PdfPCell(new Phrase("Mode de paiement: " + commande.getModepaiment()));
            cell8.setColspan(3);
            cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell8.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell8.setBorder(0);
            table7.addCell(cell8);
            document.add(table7);

            // A table1 with 4 columns
            PdfPTable table8 = new PdfPTable(3);
            table8.setSpacingBefore(10f);
            table8.setWidthPercentage(100);
            // the cell object
            PdfPCell cell9;
            cell9 = new PdfPCell(new Phrase("Date Echeance: " + commande.getDateechance()));
            cell9.setColspan(3);
            cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell9.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell9.setBorder(0);
            table8.addCell(cell9);
            document.add(table8);

            // A table1 with 4 columns
            PdfPTable table9 = new PdfPTable(3);
            table9.setSpacingBefore(10f);
            table9.setWidthPercentage(100);
            // the cell object
            PdfPCell cell10;
            cell10 = new PdfPCell(new Phrase("Observations: " + commande.getObservation()));
            cell10.setColspan(3);
            cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell10.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell10.setBorder(0);
            table9.addCell(cell10);
            document.add(table9);

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

    public void GenererNewCMBB(Commande commande, List<DetailleCommande> detailleCommandes) throws DocumentException {

        File directory = new File("./");
        String urlAbsolut = directory.getAbsolutePath();
        String url = "";

        try {
            String recuname = "Facture_proforma.pdf";
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

            //String img = "C:\\Users\\DELL\\Documents\\NetBeansProjects\\Estprod\\src\\java\\sn\\accelsolution\\util\\tete.png";
            String img = "http://144.217.254.91/tete.png";

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
            cell1 = new PdfPCell(new Phrase("FACTURE PROFORMA"));
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            //cell1.setBackgroundColor(BaseColor.GRAY);
            cell1.setBorder(0);
            table1.addCell(cell1);

            // the cell object
            PdfPCell cell2;
            cell2 = new PdfPCell(new Phrase(" "));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            //cell2.setBackgroundColor(BaseColor.GRAY);
            cell2.setBorder(0);
            table1.addCell(cell2);

            // the cell object
            PdfPCell cell3;
            cell3 = new PdfPCell(new Phrase("Date: " + commande.getDateechance()));
            cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            //cell3.setBackgroundColor(BaseColor.GRAY);
            cell3.setBorder(0);
            table1.addCell(cell3);

            document.add(table1);

            //Espace
            document.add(new Paragraph("     "));

            //Espace
            document.add(new Paragraph("Projet : " + commande.getObservation()));

            //Espace
            document.add(new Paragraph("     "));

            PdfPTable table2 = new PdfPTable(5);
            table2.setSpacingBefore(10f);
            table2.setWidthPercentage(100);

            table2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table2.addCell("DESIGNATION");
            table2.addCell("UNITE");
            table2.addCell("QUANTITE");
            table2.addCell("PRIX UNITAIRE");
            table2.addCell("PRIX TOTAL");
            table2.setHeaderRows(1);
            PdfPCell[] cells = table2.getRow(0).getCells();
            for (int j = 0; j < cells.length; j++) {
                cells[j].setBackgroundColor(BaseColor.GRAY);
            }

            int qt = 0;
            Double remise = 0.0;
            Double ttremise = 0.0;
            Double prixTaxe = 0.0;
            Double prixtaxe = 0.0;
            Double mht = 0.0;
            Double mtht = 0.0;
            Double mtttc = 0.0;
            Double total = 0.0;

            for (DetailleCommande d : detailleCommandes) {
                qt = qt + d.getQuantite();

                /*remise = Double.parseDouble(d.getRemise());
                 ttremise = ttremise + remise;*/
                prixTaxe = Double.parseDouble(d.getMontant());
                prixtaxe = prixtaxe + prixTaxe;

                mht = Double.parseDouble(d.getMontanthortaxe());
                mtht = mtht + mht;

                Double ttc = Double.parseDouble(d.getMontanthortaxe());
                mtttc = mtttc + ttc;

                table2.addCell(d.getDesignation());
                table2.addCell(d.getUnite());
                table2.addCell(d.getQuantite().toString());
                table2.addCell(d.getPu() + " CFA");
                table2.addCell(d.getMontanthortaxe() + " CFA");
            }

            document.add(table2);

            total = mtht + prixTaxe;

            // A table1 with 4 columns
            PdfPTable table5 = new PdfPTable(3);
            table5.setSpacingBefore(10f);
            table5.setWidthPercentage(100);
            // the cell object
            PdfPCell cell6;
            cell6 = new PdfPCell(new Phrase("TOTAL HTVA: " + mtht.toString() + " FCFA"));
            cell6.setColspan(3);
            cell6.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell6.setBorder(0);
            table5.addCell(cell6);
            document.add(table5);

            // A table1 with 4 columns
            PdfPTable table4 = new PdfPTable(3);
            table4.setSpacingBefore(10f);
            table4.setWidthPercentage(100);
            // the cell object
            PdfPCell cell5;
            cell5 = new PdfPCell(new Phrase("TVA 18%: " + prixtaxe.toString() + " FCFA"));
            cell5.setColspan(3);
            cell5.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell5.setBorder(0);
            table4.addCell(cell5);
            document.add(table4);

            // A table1 with 4 columns
            PdfPTable table6 = new PdfPTable(3);
            table6.setSpacingBefore(10f);
            table6.setWidthPercentage(100);
            // the cell object
            PdfPCell cell7;
            cell7 = new PdfPCell(new Phrase("MONTANT TOTAL TTC: " + total.toString() + " FCFA"));
            cell7.setColspan(3);
            cell7.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell7.setBorder(0);
            table6.addCell(cell7);
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

}
