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
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import sn.accelsolution.dao.OperationFacade;
import sn.accelsolution.entities.Contrat;
import sn.accelsolution.entities.Operation;
import sn.accelsolution.entities.Salaire;

/**
 *
 * @author DELL
 */
public class ReportContrat implements Serializable {

    private String societe = "SARRE TECHNOBAT SARL";
    private String dg = "Monsieur  Mor SARRE";
    private EntetePath entetePath;

    public ReportContrat() {
        entetePath = new EntetePath();
    }

    public EntetePath getEntetePath() {
        return entetePath;
    }

    public void setEntetePath(EntetePath entetePath) {
        this.entetePath = entetePath;
    }

    public String getSociete() {
        return societe;
    }

    public void setSociete(String societe) {
        this.societe = societe;
    }

    public String getDg() {
        return dg;
    }

    public void setDg(String dg) {
        this.dg = dg;
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

            String img = entetePath.getUrl();

            Image logoProjet = Image.getInstance(img);
            logoProjet.scaleAbsolute(500f, 150f);
            logoProjet.setAlignment(Image.ALIGN_MIDDLE);
            document.add(logoProjet);

            // A table with 4 columns
            PdfPTable tableCDD = new PdfPTable(4);
            tableCDD.setSpacingBefore(10f);
            tableCDD.setWidthPercentage(100);
            // the cell object
            PdfPCell cell1;
            // we add a cell with colspan 4
            cell1 = new PdfPCell(new Phrase("CONTRAT DE TRAVAIL A DUREE DETERMINEE"));
            cell1.setColspan(4);
            // background color
            cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            cell1.setBorder(1);
            tableCDD.addCell(cell1);
            document.add(tableCDD);

            //Espace
            document.add(new Paragraph("     "));

            //document.add(new Paragraph("ENTRE LES SOUSSIGNES:"));
            Chunk titreText = new Chunk("ENTRE:");
            titreText.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            document.add(titreText);

            //Espace
            document.add(new Paragraph("     "));

            document.add(new Paragraph("SARRE TECHNOBAT,  Société A Responsabilité Limitée  au Capital Social de 1.000.000 (Un million) FCFA ayant son siège social au 4, route de  l’Aéroport Dakar –Sénégal Tel : 33 820 83 83, immatriculée au registre du commerce sous le n° SN DKR 2014 B 21849, Ninea : 05310652 2Y2, représentée par Monsieur Mor SARRE, agissant en qualité de Président Directeur Général.\n Ci-après dénommée « L’Employeur »"));

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
            PdfPCell cell4;
            cell4 = new PdfPCell(new Phrase("Prénom et nom :"));
            cell4.setBorder(0);
            tableInfo1.addCell(cell4);
            // the cell object
            PdfPCell cell5;
            cell5 = new PdfPCell(new Phrase(contrat.getIdUtilisateur().getNomUtilisateur() + " " + contrat.getIdUtilisateur().getPrenomUtilisateur()));
            cell5.setBorder(0);
            tableInfo1.addCell(cell5);
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
            PdfPCell cell22;
            cell22 = new PdfPCell(new Phrase("Date de l’engagement :"));
            cell22.setBorder(0);
            tableInfo1.addCell(cell22);
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

    public void generateCDI(Contrat contrat) {

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

            //String img = "C:\\Users\\DELL\\Documents\\NetBeansProjects\\Estprod\\src\\java\\sn\\accelsolution\\util\\tete.png";
            String img = "http://144.217.254.91/tete.png";

            Image logoProjet = Image.getInstance(img);
            logoProjet.scaleAbsolute(500f, 150f);
            logoProjet.setAlignment(Image.ALIGN_MIDDLE);
            document.add(logoProjet);

            // A table with 4 columns
            PdfPTable tableCDD = new PdfPTable(4);
            tableCDD.setSpacingBefore(10f);
            tableCDD.setWidthPercentage(100);
            // the cell object
            PdfPCell cell1;
            // we add a cell with colspan 4
            cell1 = new PdfPCell(new Phrase("CONTRAT DE TRAVAIL A DUREE INDETERMINEE"));
            cell1.setColspan(4);
            // background color
            cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            cell1.setBorder(1);
            tableCDD.addCell(cell1);
            document.add(tableCDD);

            //Espace
            document.add(new Paragraph("     "));

            //document.add(new Paragraph("ENTRE LES SOUSSIGNES:"));
            Chunk titreText = new Chunk("ENTRE LES SOUSSIGNES:");
            titreText.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            document.add(titreText);

            //Espace
            document.add(new Paragraph("     "));

            document.add(new Paragraph("La Société " + this.getSociete() + ",  ayant son siège au 102 Avenue Blaise DIAGNE – Dakar, représentée par  son Directeur Général, " + this.getDg() + ", ci-après dénommé « l’Employeur »,"));

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
            cell3 = new PdfPCell(new Phrase("Et;"));
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
            PdfPCell cell4;
            cell4 = new PdfPCell(new Phrase("Nom et prénoms du travailleur :"));
            cell4.setBorder(0);
            tableInfo1.addCell(cell4);
            // the cell object
            PdfPCell cell5;
            cell5 = new PdfPCell(new Phrase(contrat.getIdUtilisateur().getNomUtilisateur() + " " + contrat.getIdUtilisateur().getPrenomUtilisateur()));
            cell5.setBorder(0);
            tableInfo1.addCell(cell5);
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
            cell16 = new PdfPCell(new Phrase("Adresse complète :"));
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
            PdfPCell cell19;
            cell19 = new PdfPCell(new Phrase("Fonction :"));
            cell19.setBorder(0);
            tableInfo1.addCell(cell19);
            // the cell object
            PdfPCell cell20;
            cell20 = new PdfPCell(new Phrase(contrat.getIdUtilisateur().getFonction()));
            cell20.setBorder(0);
            tableInfo1.addCell(cell20);
            // the cell object
            PdfPCell cell21;
            cell21 = new PdfPCell(new Phrase("         "));
            cell21.setBorder(0);
            tableInfo1.addCell(cell21);

            // the cell object
            PdfPCell cell22;
            cell22 = new PdfPCell(new Phrase("Date de l’engagement :"));
            cell22.setBorder(0);
            tableInfo1.addCell(cell22);
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

            // the cell object
            PdfPCell cell25;
            cell25 = new PdfPCell(new Phrase("Classification professionnell :"));
            cell25.setBorder(0);
            tableInfo1.addCell(cell25);
            // the cell object
            PdfPCell cell26;
            cell26 = new PdfPCell(new Phrase(contrat.getClassificationProfe()));
            cell26.setBorder(0);
            tableInfo1.addCell(cell26);
            // the cell object
            PdfPCell cell27;
            cell27 = new PdfPCell(new Phrase("         "));
            cell27.setBorder(0);
            tableInfo1.addCell(cell27);

            // the cell object
            PdfPCell cell28;
            cell28 = new PdfPCell(new Phrase("Durée de travail :"));
            cell28.setBorder(0);
            tableInfo1.addCell(cell28);
            // the cell object
            PdfPCell cell29;
            cell29 = new PdfPCell(new Phrase(contrat.getDureeTravail()));
            cell29.setBorder(0);
            tableInfo1.addCell(cell29);
            // the cell object
            PdfPCell cell30;
            cell30 = new PdfPCell(new Phrase("         "));
            cell30.setBorder(0);
            tableInfo1.addCell(cell30);

            document.add(tableInfo1);

            // A table with 4 columns
            PdfPTable tableAvantArticle1 = new PdfPTable(4);
            tableAvantArticle1.setSpacingBefore(10f);
            tableAvantArticle1.setWidthPercentage(100);
            // the cell object
            PdfPCell cell31;
            // we add a cell with colspan 4
            cell31 = new PdfPCell(new Phrase("Ci-après, dénommé (e) « Le travailleur »"));
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

            document.add(new Paragraph("Il a été convenu et arrêté ce qui suit :"));

            //Espace
            document.add(new Paragraph("     "));
            //document.add(new Paragraph("ENTRE LES SOUSSIGNES:"));
            Chunk article1 = new Chunk("Article 1er : DISPOSITIONS REGISSANT LE CONTRAT");
            article1.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            document.add(article1);
            document.add(new Paragraph(contrat.getArticle1()));

            //Espace
            document.add(new Paragraph("     "));
            //document.add(new Paragraph("ENTRE LES SOUSSIGNES:"));
            Chunk article2 = new Chunk("Article 2 : DUREE DU CONTRAT");
            article2.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            document.add(article2);
            document.add(new Paragraph(contrat.getArticle2()));

            //Espace
            document.add(new Paragraph("     "));
            //document.add(new Paragraph("ENTRE LES SOUSSIGNES:"));
            Chunk article3 = new Chunk("Article 3 : DESCRIPTION DU POSTE ET LIEU D’EMPLOI");
            article3.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            document.add(article3);
            document.add(new Paragraph(contrat.getArticle3()));

            //Espace
            document.add(new Paragraph("     "));
            //document.add(new Paragraph("ENTRE LES SOUSSIGNES:"));
            Chunk article4 = new Chunk("Article 4 : CONDITIONS DE SERVICE");
            article4.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            document.add(article4);
            document.add(new Paragraph(contrat.getArticle4()));

            //Espace
            document.add(new Paragraph("     "));
            //document.add(new Paragraph("ENTRE LES SOUSSIGNES:"));
            Chunk article5 = new Chunk("Article 5 : CONGE ANNUEL");
            article5.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            document.add(article5);
            document.add(new Paragraph(contrat.getArticle5()));

            //Espace
            document.add(new Paragraph("     "));
            //document.add(new Paragraph("ENTRE LES SOUSSIGNES:"));
            Chunk article6 = new Chunk("Article 6 : COUVERTURE SOCIALE");
            article6.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            document.add(article6);
            document.add(new Paragraph(contrat.getArticle6()));

            //Espace
            document.add(new Paragraph("     "));
            //document.add(new Paragraph("ENTRE LES SOUSSIGNES:"));
            Chunk article7 = new Chunk("Article 7 : REMUNERATION");
            article7.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            document.add(article7);
            document.add(new Paragraph("Le salaire  mensuel est ainsi décomposé :"));
            document.add(new Paragraph(" - Salaire  de base :          " + contrat.getSalairebase() + " FCFA"));
            document.add(new Paragraph(" - Sursalaire :                " + contrat.getSurSalaire() + " FCFA"));
            document.add(new Paragraph(" - Salaire brut fiscal :       " + contrat.getSalaireBrutFiscal() + " FCFA"));

            //Espace
            document.add(new Paragraph("     "));
            //document.add(new Paragraph("ENTRE LES SOUSSIGNES:"));
            Chunk article8 = new Chunk("Article 8 : LITIGES – CONTESTATIONS");
            article8.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            document.add(article8);
            document.add(new Paragraph(contrat.getArticle8()));

            // A table with 4 columns
            PdfPTable tableBas1 = new PdfPTable(4);
            tableBas1.setSpacingBefore(10f);
            tableBas1.setWidthPercentage(100);
            // the cell object
            PdfPCell cell33;
            // we add a cell with colspan 4
            cell33 = new PdfPCell(new Phrase("Fait à Dakar, le " + contrat.getDateEngagement() + " en quatre exemplaires."));
            cell33.setColspan(4);
            cell33.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell33.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            cell33.setBorder(0);
            tableBas1.addCell(cell33);
            document.add(tableBas1);

            // A table with 4 columns
            PdfPTable tableBas2 = new PdfPTable(4);
            tableBas2.setSpacingBefore(10f);
            tableBas2.setWidthPercentage(100);
            // the cell object
            PdfPCell cell34;
            // we add a cell with colspan 4
            cell34 = new PdfPCell(new Phrase("(Signature précédée de la\n"
                    + "     Mention manuscrite) \n"
                    + "     « Lu et approuvé »"));
            cell34.setColspan(4);
            cell34.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell34.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            cell34.setBorder(0);
            tableBas2.addCell(cell34);
            document.add(tableBas2);

            // A table with 3 columns
            PdfPTable tableBas3 = new PdfPTable(3);
            tableBas3.setSpacingBefore(10f);
            tableBas3.setWidthPercentage(100);

            // the cell object
            PdfPCell cell35;
            cell35 = new PdfPCell(new Phrase("Le Travailleur"));
            cell35.setBorder(0);
            tableBas3.addCell(cell35);
            // the cell object
            PdfPCell cell36;
            cell36 = new PdfPCell(new Phrase("L’Inspecteur du travail"));
            cell36.setBorder(0);
            tableBas3.addCell(cell36);
            // the cell object
            PdfPCell cell37;
            cell37 = new PdfPCell(new Phrase("L’Employeur"));
            cell37.setBorder(0);
            tableBas3.addCell(cell37);
            document.add(tableBas3);

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

    public void generateStage(Contrat contrat) {

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

            //String img = "C:\\Users\\DELL\\Documents\\NetBeansProjects\\Estprod\\src\\java\\sn\\accelsolution\\util\\tete.png";
            String img = "http://144.217.254.91/tete.png";

            Image logoProjet = Image.getInstance(img);
            logoProjet.scaleAbsolute(500f, 150f);
            logoProjet.setAlignment(Image.ALIGN_MIDDLE);
            document.add(logoProjet);

            // A table with 4 columns
            PdfPTable tableCDD = new PdfPTable(4);
            tableCDD.setSpacingBefore(10f);
            tableCDD.setWidthPercentage(100);
            // the cell object
            PdfPCell cell1;
            // we add a cell with colspan 4
            cell1 = new PdfPCell(new Phrase("CONTRAT DE STAGE"));
            cell1.setColspan(4);
            // background color
            cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            cell1.setBorder(1);
            tableCDD.addCell(cell1);
            document.add(tableCDD);

            //Espace
            document.add(new Paragraph("     "));

            //document.add(new Paragraph("ENTRE LES SOUSSIGNES:"));
            Chunk titreText = new Chunk("ENTRE LES SOUSSIGNES:");
            titreText.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            document.add(titreText);

            //Espace
            document.add(new Paragraph("     "));

            document.add(new Paragraph("La Société " + this.getSociete() + ",  ayant son siège au 102 Avenue Blaise DIAGNE – Dakar, représentée par  son Directeur Général, " + this.getDg() + ", ci-après dénommé « l’Employeur »,"));

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
            cell3 = new PdfPCell(new Phrase("Et;"));
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
            PdfPCell cell4;
            cell4 = new PdfPCell(new Phrase("Nom et prénoms du travailleur :"));
            cell4.setBorder(0);
            tableInfo1.addCell(cell4);
            // the cell object
            PdfPCell cell5;
            cell5 = new PdfPCell(new Phrase(contrat.getIdUtilisateur().getNomUtilisateur() + " " + contrat.getIdUtilisateur().getPrenomUtilisateur()));
            cell5.setBorder(0);
            tableInfo1.addCell(cell5);
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
            cell16 = new PdfPCell(new Phrase("Adresse complète :"));
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
            PdfPCell cell19;
            cell19 = new PdfPCell(new Phrase("Fonction :"));
            cell19.setBorder(0);
            tableInfo1.addCell(cell19);
            // the cell object
            PdfPCell cell20;
            cell20 = new PdfPCell(new Phrase(contrat.getIdUtilisateur().getFonction()));
            cell20.setBorder(0);
            tableInfo1.addCell(cell20);
            // the cell object
            PdfPCell cell21;
            cell21 = new PdfPCell(new Phrase("         "));
            cell21.setBorder(0);
            tableInfo1.addCell(cell21);

            // the cell object
            PdfPCell cell22;
            cell22 = new PdfPCell(new Phrase("Date de l’engagement :"));
            cell22.setBorder(0);
            tableInfo1.addCell(cell22);
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

            // the cell object
            PdfPCell cell25;
            cell25 = new PdfPCell(new Phrase("Classification professionnell :"));
            cell25.setBorder(0);
            tableInfo1.addCell(cell25);
            // the cell object
            PdfPCell cell26;
            cell26 = new PdfPCell(new Phrase(contrat.getClassificationProfe()));
            cell26.setBorder(0);
            tableInfo1.addCell(cell26);
            // the cell object
            PdfPCell cell27;
            cell27 = new PdfPCell(new Phrase("         "));
            cell27.setBorder(0);
            tableInfo1.addCell(cell27);

            // the cell object
            PdfPCell cell28;
            cell28 = new PdfPCell(new Phrase("Durée de travail :"));
            cell28.setBorder(0);
            tableInfo1.addCell(cell28);
            // the cell object
            PdfPCell cell29;
            cell29 = new PdfPCell(new Phrase(contrat.getDureeTravail()));
            cell29.setBorder(0);
            tableInfo1.addCell(cell29);
            // the cell object
            PdfPCell cell30;
            cell30 = new PdfPCell(new Phrase("         "));
            cell30.setBorder(0);
            tableInfo1.addCell(cell30);

            document.add(tableInfo1);

            // A table with 4 columns
            PdfPTable tableAvantArticle1 = new PdfPTable(4);
            tableAvantArticle1.setSpacingBefore(10f);
            tableAvantArticle1.setWidthPercentage(100);
            // the cell object
            PdfPCell cell31;
            // we add a cell with colspan 4
            cell31 = new PdfPCell(new Phrase("Ci-après, dénommé (e) « Le travailleur »"));
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

            document.add(new Paragraph("Il a été convenu et arrêté ce qui suit :"));

            //Espace
            document.add(new Paragraph("     "));
            //document.add(new Paragraph("ENTRE LES SOUSSIGNES:"));
            Chunk article1 = new Chunk("Article 1er : DISPOSITIONS REGISSANT LE CONTRAT");
            article1.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            document.add(article1);
            document.add(new Paragraph(contrat.getArticle1()));

            //Espace
            document.add(new Paragraph("     "));
            //document.add(new Paragraph("ENTRE LES SOUSSIGNES:"));
            Chunk article2 = new Chunk("Article 2 : DUREE DU CONTRAT");
            article2.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            document.add(article2);
            document.add(new Paragraph(contrat.getArticle2()));

            //Espace
            document.add(new Paragraph("     "));
            //document.add(new Paragraph("ENTRE LES SOUSSIGNES:"));
            Chunk article3 = new Chunk("Article 3 : DESCRIPTION DU POSTE ET LIEU D’EMPLOI");
            article3.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            document.add(article3);
            document.add(new Paragraph(contrat.getArticle3()));

            //Espace
            document.add(new Paragraph("     "));
            //document.add(new Paragraph("ENTRE LES SOUSSIGNES:"));
            Chunk article4 = new Chunk("Article 4 : CONDITIONS DE SERVICE");
            article4.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            document.add(article4);
            document.add(new Paragraph(contrat.getArticle4()));

            //Espace
            document.add(new Paragraph("     "));
            //document.add(new Paragraph("ENTRE LES SOUSSIGNES:"));
            Chunk article5 = new Chunk("Article 5 : CONGE ANNUEL");
            article5.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            document.add(article5);
            document.add(new Paragraph(contrat.getArticle5()));

            //Espace
            document.add(new Paragraph("     "));
            //document.add(new Paragraph("ENTRE LES SOUSSIGNES:"));
            Chunk article6 = new Chunk("Article 6 : COUVERTURE SOCIALE");
            article6.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            document.add(article6);
            document.add(new Paragraph(contrat.getArticle6()));

            //Espace
            document.add(new Paragraph("     "));
            //document.add(new Paragraph("ENTRE LES SOUSSIGNES:"));
            Chunk article7 = new Chunk("Article 7 : REMUNERATION");
            article7.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            document.add(article7);
            document.add(new Paragraph("Le salaire  mensuel est ainsi décomposé :"));
            document.add(new Paragraph(" - Salaire  de base :          " + contrat.getSalairebase() + " FCFA"));
            document.add(new Paragraph(" - Sursalaire :                " + contrat.getSurSalaire() + " FCFA"));
            document.add(new Paragraph(" - Salaire brut fiscal :       " + contrat.getSalaireBrutFiscal() + " FCFA"));

            //Espace
            document.add(new Paragraph("     "));
            //document.add(new Paragraph("ENTRE LES SOUSSIGNES:"));
            Chunk article8 = new Chunk("Article 8 : LITIGES – CONTESTATIONS");
            article8.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            document.add(article8);
            document.add(new Paragraph(contrat.getArticle8()));

            // A table with 4 columns
            PdfPTable tableBas1 = new PdfPTable(4);
            tableBas1.setSpacingBefore(10f);
            tableBas1.setWidthPercentage(100);
            // the cell object
            PdfPCell cell33;
            // we add a cell with colspan 4
            cell33 = new PdfPCell(new Phrase("Fait à Dakar, le " + contrat.getDateEngagement() + " en quatre exemplaires."));
            cell33.setColspan(4);
            cell33.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell33.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            cell33.setBorder(0);
            tableBas1.addCell(cell33);
            document.add(tableBas1);

            // A table with 4 columns
            PdfPTable tableBas2 = new PdfPTable(4);
            tableBas2.setSpacingBefore(10f);
            tableBas2.setWidthPercentage(100);
            // the cell object
            PdfPCell cell34;
            // we add a cell with colspan 4
            cell34 = new PdfPCell(new Phrase("(Signature précédée de la\n"
                    + "     Mention manuscrite) \n"
                    + "     « Lu et approuvé »"));
            cell34.setColspan(4);
            cell34.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell34.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            cell34.setBorder(0);
            tableBas2.addCell(cell34);
            document.add(tableBas2);

            // A table with 3 columns
            PdfPTable tableBas3 = new PdfPTable(3);
            tableBas3.setSpacingBefore(10f);
            tableBas3.setWidthPercentage(100);

            // the cell object
            PdfPCell cell35;
            cell35 = new PdfPCell(new Phrase("Le Travailleur"));
            cell35.setBorder(0);
            tableBas3.addCell(cell35);
            // the cell object
            PdfPCell cell36;
            cell36 = new PdfPCell(new Phrase("L’Inspecteur du travail"));
            cell36.setBorder(0);
            tableBas3.addCell(cell36);
            // the cell object
            PdfPCell cell37;
            cell37 = new PdfPCell(new Phrase("L’Employeur"));
            cell37.setBorder(0);
            tableBas3.addCell(cell37);
            document.add(tableBas3);

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

    public void generateSalaire(Salaire salaire, Contrat contrat) {

        File directory = new File("./");
        String urlAbsolut = directory.getAbsolutePath();
        String url = "";

        try {

            String recuname = "Bulletin_PAIE_" + salaire.getIdUtilisateur().getNomUtilisateur() + "_" + salaire.getIdUtilisateur().getPrenomUtilisateur() + ".pdf";
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

            // A table with 4 columns
            PdfPTable tableSalaire = new PdfPTable(4);
            tableSalaire.setSpacingBefore(10f);
            tableSalaire.setWidthPercentage(100);
            // the cell object
            PdfPCell cell38 = null;
            // we add a cell with colspan 4
            if (salaire.getTypeSalarie().equals("Fiche de paie")) {
                cell38 = new PdfPCell(new Phrase("BULLETIN DE PAIE"));
            }
            if (salaire.getTypeSalarie().equals("Fiche de rémunération stagee")) {
                cell38 = new PdfPCell(new Phrase("FICHE DE REMUNERATION"));
            }

            cell38.setColspan(4);
            // background color
            cell38.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell38.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell38.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            cell38.setBorder(1);
            tableSalaire.addCell(cell38);
            document.add(tableSalaire);

            //Espace
            document.add(new Paragraph("     "));

            // A table with 4 columns
            PdfPTable tableSalaireH1 = new PdfPTable(4);
            tableSalaireH1.setSpacingBefore(10f);
            tableSalaireH1.setWidthPercentage(100);
            // the cell object
            PdfPCell cellH1 = null;
            // we add a cell with colspan 4
            cellH1 = new PdfPCell(new Phrase(this.societe));
            cellH1.setColspan(4);
            cellH1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellH1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            cellH1.setBorder(0);
            tableSalaireH1.addCell(cellH1);
            document.add(tableSalaireH1);
            //Espace
            document.add(new Paragraph("     "));

            // A table with 4 columns
            PdfPTable tableSalaireH2 = new PdfPTable(4);
            tableSalaireH2.setSpacingBefore(10f);
            tableSalaireH2.setWidthPercentage(100);
            // the cell object
            PdfPCell cellH2 = null;
            // we add a cell with colspan 4
            cellH2 = new PdfPCell(new Phrase("PERIODE DU: " + salaire.getPeriodeDebut() + " AU: " + salaire.getPeriodeFin()));
            cellH2.setColspan(4);
            cellH2.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellH2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellH2.setBorder(0);
            tableSalaireH2.addCell(cellH2);
            document.add(tableSalaireH2);
            //Espace
            document.add(new Paragraph("     "));

            // A table with 4 columns
            PdfPTable tableSalaireH3 = new PdfPTable(4);
            tableSalaireH3.setSpacingBefore(10f);
            tableSalaireH3.setWidthPercentage(100);
            // the cell object
            PdfPCell cellH3 = null;
            // we add a cell with colspan 4
            cellH3 = new PdfPCell(new Phrase("Nom et prénom: " + salaire.getIdUtilisateur().getNomUtilisateur() + " " + salaire.getIdUtilisateur().getPrenomUtilisateur()));
            cellH3.setColspan(4);
            cellH3.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellH3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellH3.setBorder(0);
            tableSalaireH3.addCell(cellH3);
            document.add(tableSalaireH3);
            //Espace
            document.add(new Paragraph("     "));
            //Espace
            document.add(new Paragraph("     "));
            //Espace
            document.add(new Paragraph("     "));
            //Espace
            document.add(new Paragraph("     "));

            // A table with 3 columns
            PdfPTable tableSalaire2 = new PdfPTable(3);
            tableSalaire2.setSpacingBefore(10f);
            tableSalaire2.setWidthPercentage(100);

            // the cell object
            PdfPCell cell42 = null;
            // we add a cell with colspan 4
            cell42 = new PdfPCell(new Phrase("SALAIRE DE BASE"));
            // background color
            cell42.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell42.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell42.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            cell42.setBorder(1);
            tableSalaire2.addCell(cell42);

            // the cell object
            PdfPCell cell43 = null;
            // we add a cell with colspan 4
            cell43 = new PdfPCell(new Phrase("SURSALAIRE"));
            // background color
            cell43.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell43.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell43.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            cell43.setBorder(1);
            tableSalaire2.addCell(cell43);

            // the cell object
            PdfPCell cell44 = null;
            // we add a cell with colspan 4
            cell44 = new PdfPCell(new Phrase("SALAIRE BRUT FISCAL"));
            // background color
            cell44.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell44.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell44.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            cell44.setBorder(1);
            tableSalaire2.addCell(cell44);

            // the cell object
            PdfPCell cell45 = null;
            // we add a cell with colspan 4
            cell45 = new PdfPCell(new Phrase(contrat.getSalairebase()));
            cell45.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell45.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            //cell45.setBorder(1);
            tableSalaire2.addCell(cell45);

            // the cell object
            PdfPCell cell46 = null;
            // we add a cell with colspan 4
            cell46 = new PdfPCell(new Phrase(contrat.getSurSalaire()));
            cell46.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell46.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            //cell46.setBorder(1);
            tableSalaire2.addCell(cell46);

            // the cell object
            PdfPCell cell47 = null;
            // we add a cell with colspan 4
            cell47 = new PdfPCell(new Phrase(contrat.getSalaireBrutFiscal()));
            cell47.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell47.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            //cell47.setBorder(1);
            tableSalaire2.addCell(cell47);

            document.add(tableSalaire2);

            // A table with 4 columns
            PdfPTable tableSalaireNet = new PdfPTable(4);
            tableSalaireNet.setSpacingBefore(10f);
            tableSalaireNet.setWidthPercentage(100);
            // the cell object
            PdfPCell cellNet = null;
            // we add a cell with colspan 4
            cellNet = new PdfPCell(new Phrase("NET A PAYER: " + contrat.getSalaireBrutFiscal() + " FCFA"));

            cellNet.setColspan(4);
            cellNet.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellNet.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tableSalaireNet.addCell(cellNet);
            document.add(tableSalaireNet);

            //Espace
            document.add(new Paragraph("     "));

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

    public void generateCaisseJour(List<Operation> listOperations, String totalentrees, String soldcaisse) {
//
//        File directory = new File("./");
//        String urlAbsolut = directory.getAbsolutePath();
//        String url = "";
//
//        try {
//
//            String recuname = "Etat_Entree_Caisse_" + operation.getDateoperation() + ".pdf";
//            url = urlAbsolut + recuname;
//
//            FacesContext context = FacesContext.getCurrentInstance();
//            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
//            response.setContentType("application/pdf");
//            response.setHeader("Content-disposition", "inline=filename=file.pdf");
//
//            Document document = new Document();
//            document.setPageSize(PageSize.A4);
//            OutputStream file = new FileOutputStream(new File(url));
//            // step 2
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            PdfWriter.getInstance(document, baos);
//            PdfWriter.getInstance(document, file);
//            // step 3
//            document.open();
//
//            // A table with 4 columns
//            PdfPTable tableCDD = new PdfPTable(4);
//            tableCDD.setSpacingBefore(10f);
//            tableCDD.setWidthPercentage(100);
//            // the cell object
//            PdfPCell cell1;
//            // we add a cell with colspan 4
//            cell1 = new PdfPCell(new Phrase("ETAT DES ENTREES EN CAISSE"));
//            cell1.setColspan(4);
//            // background color
//            cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
//            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            // border
//            cell1.setBorder(1);
//            tableCDD.addCell(cell1);
//            document.add(tableCDD);
//
//            //Espace
//            document.add(new Paragraph("     "));
//
//            // A table with 3 columns
//            PdfPTable tableSalaire2 = new PdfPTable(4);
//            tableSalaire2.setSpacingBefore(10f);
//            tableSalaire2.setWidthPercentage(100);
//
//            // the cell object
//            PdfPCell cell42 = null;
//            // we add a cell with colspan 4
//            cell42 = new PdfPCell(new Phrase("AMENER PAR"));
//            // background color
//            cell42.setBackgroundColor(BaseColor.LIGHT_GRAY);
//            cell42.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell42.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            // border
//            cell42.setBorder(1);
//            tableSalaire2.addCell(cell42);
//
//            // the cell object
//            PdfPCell cell43 = null;
//            // we add a cell with colspan 4
//            cell43 = new PdfPCell(new Phrase("OBJET"));
//            // background color
//            cell43.setBackgroundColor(BaseColor.LIGHT_GRAY);
//            cell43.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell43.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            // border
//            cell43.setBorder(1);
//            tableSalaire2.addCell(cell43);
//
//            // the cell object
//            PdfPCell cell44 = null;
//            // we add a cell with colspan 4
//            cell44 = new PdfPCell(new Phrase("DEBIT"));
//            // background color
//            cell44.setBackgroundColor(BaseColor.LIGHT_GRAY);
//            cell44.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell44.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            // border
//            cell44.setBorder(1);
//            tableSalaire2.addCell(cell44);
//
//            // the cell object
//            PdfPCell cell46 = null;
//            // we add a cell with colspan 4
//            cell46 = new PdfPCell(new Phrase("Date"));
//            cell46.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell46.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            // border
//            //cell46.setBorder(1);
//            tableSalaire2.addCell(cell46);
//
//            // the cell object
//            PdfPCell cell47 = null;
//            // we add a cell with colspan 4
//            cell47 = new PdfPCell(new Phrase(operation.getIdClient().getNomClient()));
//            cell47.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell47.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            // border
//            //cell47.setBorder(1);
//            tableSalaire2.addCell(cell47);
//
//            // the cell object
//            PdfPCell cell48 = null;
//            // we add a cell with colspan 4
//            cell48 = new PdfPCell(new Phrase(operation.getObjet()));
//            cell48.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell48.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            // border
//            //cell47.setBorder(1);
//            tableSalaire2.addCell(cell48);
//            
//            
//            // the cell object
//            PdfPCell cell49 = null;
//            // we add a cell with colspan 4
//            cell49 = new PdfPCell(new Phrase(operation.getDebit()));
//            cell49.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell49.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            // border
//            //cell47.setBorder(1);
//            tableSalaire2.addCell(cell49);
//            
//            // the cell object
//            PdfPCell cell50 = null;
//            // we add a cell with colspan 4
//            cell50 = new PdfPCell(new Phrase(operation.getDateoperation()));
//            cell50.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell50.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            // border
//            //cell47.setBorder(1);
//            tableSalaire2.addCell(cell50);
//
//            document.add(tableSalaire2);
//
//            // A table with 4 columns
//            PdfPTable tableSalaireNet = new PdfPTable(4);
//            tableSalaireNet.setSpacingBefore(10f);
//            tableSalaireNet.setWidthPercentage(100);
//            // the cell object
//            PdfPCell cellNet = null;
//            // we add a cell with colspan 4
//            cellNet = new PdfPCell(new Phrase("TOTAL DEBIT: " + operation.getIdCaisse().getSolde() + " FCFA"));
//
//            cellNet.setColspan(4);
//            cellNet.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            cellNet.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            tableSalaireNet.addCell(cellNet);
//            document.add(tableSalaireNet);
//
//            //Espace
//            document.add(new Paragraph("     "));
//
//            document.close();
//            file.close();
//            System.out.println("PDF Created!");
//
//            // setting some response headers
//            response.setHeader("Expires", "0");
//            response.setHeader("Cache-Control",
//                    "must-revalidate, post-check=0, pre-check=0");
//            response.setHeader("Pragma", "public");
//            // setting the content type
//            response.setContentType("application/pdf");
//            // the contentlength
//            response.setContentLength(baos.size());
//            // write ByteArrayOutputStream to the ServletOutputStream
//            OutputStream os = response.getOutputStream();
//            baos.writeTo(os);
//            os.flush();
//            os.close();
//
//            context.responseComplete();
//
//        } catch (Exception e) {
//        }

    }

    public void generateSortieJour(Operation operation) {

        File directory = new File("./");
        String urlAbsolut = directory.getAbsolutePath();
        String url = "";

        try {

            String recuname = "Etat_Sortie_Caisse_" + operation.getDateoperation() + ".pdf";
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

            // A table with 4 columns
            PdfPTable tableCDD = new PdfPTable(4);
            tableCDD.setSpacingBefore(10f);
            tableCDD.setWidthPercentage(100);
            // the cell object
            PdfPCell cell1;
            // we add a cell with colspan 4
            cell1 = new PdfPCell(new Phrase("SORTIE DE CAISSE DU : " + operation.getDateoperation()));
            cell1.setColspan(4);
            // background color
            cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            cell1.setBorder(1);
            tableCDD.addCell(cell1);
            document.add(tableCDD);

            //Espace
            document.add(new Paragraph("     "));

            // A table with 3 columns
            PdfPTable tableSalaire2 = new PdfPTable(3);
            tableSalaire2.setSpacingBefore(10f);
            tableSalaire2.setWidthPercentage(100);

            // the cell object
            PdfPCell cell42 = null;
            // we add a cell with colspan 4
            cell42 = new PdfPCell(new Phrase("AMENER PAR"));
            // background color
            cell42.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell42.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell42.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            cell42.setBorder(1);
            tableSalaire2.addCell(cell42);

            // the cell object
            PdfPCell cell43 = null;
            // we add a cell with colspan 4
            cell43 = new PdfPCell(new Phrase("OBJET"));
            // background color
            cell43.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell43.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell43.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            cell43.setBorder(1);
            tableSalaire2.addCell(cell43);

            // the cell object
            PdfPCell cell44 = null;
            // we add a cell with colspan 4
            cell44 = new PdfPCell(new Phrase("CREDIT"));
            // background color
            cell44.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell44.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell44.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            cell44.setBorder(1);
            tableSalaire2.addCell(cell44);

            // the cell object
            PdfPCell cell46 = null;
            // we add a cell with colspan 4
            cell46 = new PdfPCell(new Phrase("-----------------"));
            cell46.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell46.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            //cell46.setBorder(1);
            tableSalaire2.addCell(cell46);

            // the cell object
            PdfPCell cell47 = null;
            // we add a cell with colspan 4
            cell47 = new PdfPCell(new Phrase(operation.getObjet()));
            cell47.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell47.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            //cell47.setBorder(1);
            tableSalaire2.addCell(cell47);

            // the cell object
            PdfPCell cell48 = null;
            // we add a cell with colspan 4
            cell48 = new PdfPCell(new Phrase(operation.getValuecredit()));
            cell48.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell48.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            //cell47.setBorder(1);
            tableSalaire2.addCell(cell48);

            document.add(tableSalaire2);

            // A table with 4 columns
            PdfPTable tableSalaireNet = new PdfPTable(4);
            tableSalaireNet.setSpacingBefore(10f);
            tableSalaireNet.setWidthPercentage(100);
            // the cell object
            PdfPCell cellNet = null;
            // we add a cell with colspan 4
            cellNet = new PdfPCell(new Phrase("TOTAL CREDIT: " + operation.getIdCaisse().getSolde() + " FCFA"));

            cellNet.setColspan(4);
            cellNet.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellNet.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tableSalaireNet.addCell(cellNet);
            document.add(tableSalaireNet);

            //Espace
            document.add(new Paragraph("     "));

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

    public void generateBanqueJour(Operation operation) {

        File directory = new File("./");
        String urlAbsolut = directory.getAbsolutePath();
        String url = "";

        try {

            String recuname = "Etat_Entree_Banque_" + operation.getDateoperation() + ".pdf";
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

            // A table with 4 columns
            PdfPTable tableCDD = new PdfPTable(4);
            tableCDD.setSpacingBefore(10f);
            tableCDD.setWidthPercentage(100);
            // the cell object
            PdfPCell cell1;
            // we add a cell with colspan 4
            cell1 = new PdfPCell(new Phrase("ENTREE EN BANQUE DU : " + operation.getDateoperation()));
            cell1.setColspan(4);
            // background color
            cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            cell1.setBorder(1);
            tableCDD.addCell(cell1);
            document.add(tableCDD);

            //Espace
            document.add(new Paragraph("     "));

            // A table with 3 columns
            PdfPTable tableSalaire2 = new PdfPTable(3);
            tableSalaire2.setSpacingBefore(10f);
            tableSalaire2.setWidthPercentage(100);

            // the cell object
            PdfPCell cell42 = null;
            // we add a cell with colspan 4
            cell42 = new PdfPCell(new Phrase("BENEFICIAIRE"));
            // background color
            cell42.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell42.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell42.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            cell42.setBorder(1);
            tableSalaire2.addCell(cell42);

            // the cell object
            PdfPCell cell43 = null;
            // we add a cell with colspan 4
            cell43 = new PdfPCell(new Phrase("OBJET"));
            // background color
            cell43.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell43.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell43.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            cell43.setBorder(1);
            tableSalaire2.addCell(cell43);

            // the cell object
            PdfPCell cell44 = null;
            // we add a cell with colspan 4
            cell44 = new PdfPCell(new Phrase("DEBIT"));
            // background color
            cell44.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell44.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell44.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            cell44.setBorder(1);
            tableSalaire2.addCell(cell44);

            // the cell object
            PdfPCell cell46 = null;
            // we add a cell with colspan 4
            cell46 = new PdfPCell(new Phrase("-----------------"));
            cell46.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell46.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            //cell46.setBorder(1);
            tableSalaire2.addCell(cell46);

            // the cell object
            PdfPCell cell47 = null;
            // we add a cell with colspan 4
            cell47 = new PdfPCell(new Phrase(operation.getObjet()));
            cell47.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell47.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            //cell47.setBorder(1);
            tableSalaire2.addCell(cell47);

            // the cell object
            PdfPCell cell48 = null;
            // we add a cell with colspan 4
            cell48 = new PdfPCell(new Phrase(operation.getDebit()));
            cell48.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell48.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            //cell47.setBorder(1);
            tableSalaire2.addCell(cell48);

            document.add(tableSalaire2);

            // A table with 4 columns
            PdfPTable tableSalaireNet = new PdfPTable(4);
            tableSalaireNet.setSpacingBefore(10f);
            tableSalaireNet.setWidthPercentage(100);
            // the cell object
            PdfPCell cellNet = null;
            // we add a cell with colspan 4
            cellNet = new PdfPCell(new Phrase("TOTAL DEBIT: " + operation.getIdCaisse().getSolde() + " FCFA"));

            cellNet.setColspan(4);
            cellNet.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellNet.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tableSalaireNet.addCell(cellNet);
            document.add(tableSalaireNet);

            //Espace
            document.add(new Paragraph("     "));

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

    public void generateSortieBanqueJour(Operation operation) {

        File directory = new File("./");
        String urlAbsolut = directory.getAbsolutePath();
        String url = "";

        try {

            String recuname = "Etat_Sortie_Banque_" + operation.getDateoperation() + ".pdf";
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

            // A table with 4 columns
            PdfPTable tableCDD = new PdfPTable(4);
            tableCDD.setSpacingBefore(10f);
            tableCDD.setWidthPercentage(100);
            // the cell object
            PdfPCell cell1;
            // we add a cell with colspan 4
            cell1 = new PdfPCell(new Phrase("SORTIE DE BANQUE DU : " + operation.getDateoperation()));
            cell1.setColspan(4);
            // background color
            cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            cell1.setBorder(1);
            tableCDD.addCell(cell1);
            document.add(tableCDD);

            //Espace
            document.add(new Paragraph("     "));

            // A table with 3 columns
            PdfPTable tableSalaire2 = new PdfPTable(3);
            tableSalaire2.setSpacingBefore(10f);
            tableSalaire2.setWidthPercentage(100);

            // the cell object
            PdfPCell cell42 = null;
            // we add a cell with colspan 4
            cell42 = new PdfPCell(new Phrase("BENEFICIAIRE"));
            // background color
            cell42.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell42.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell42.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            cell42.setBorder(1);
            tableSalaire2.addCell(cell42);

            // the cell object
            PdfPCell cell43 = null;
            // we add a cell with colspan 4
            cell43 = new PdfPCell(new Phrase("OBJET"));
            // background color
            cell43.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell43.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell43.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            cell43.setBorder(1);
            tableSalaire2.addCell(cell43);

            // the cell object
            PdfPCell cell44 = null;
            // we add a cell with colspan 4
            cell44 = new PdfPCell(new Phrase("CREDIT"));
            // background color
            cell44.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell44.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell44.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            cell44.setBorder(1);
            tableSalaire2.addCell(cell44);

            // the cell object
            PdfPCell cell46 = null;
            // we add a cell with colspan 4
            cell46 = new PdfPCell(new Phrase("-----------------"));
            cell46.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell46.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            //cell46.setBorder(1);
            tableSalaire2.addCell(cell46);

            // the cell object
            PdfPCell cell47 = null;
            // we add a cell with colspan 4
            cell47 = new PdfPCell(new Phrase(operation.getObjet()));
            cell47.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell47.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            //cell47.setBorder(1);
            tableSalaire2.addCell(cell47);

            // the cell object
            PdfPCell cell48 = null;
            // we add a cell with colspan 4
            cell48 = new PdfPCell(new Phrase(operation.getValuecredit()));
            cell48.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell48.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            //cell47.setBorder(1);
            tableSalaire2.addCell(cell48);

            document.add(tableSalaire2);

            // A table with 4 columns
            PdfPTable tableSalaireNet = new PdfPTable(4);
            tableSalaireNet.setSpacingBefore(10f);
            tableSalaireNet.setWidthPercentage(100);
            // the cell object
            PdfPCell cellNet = null;
            // we add a cell with colspan 4
            cellNet = new PdfPCell(new Phrase("TOTAL CREDIT: " + operation.getIdCaisse().getSolde() + " FCFA"));

            cellNet.setColspan(4);
            cellNet.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellNet.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tableSalaireNet.addCell(cellNet);
            document.add(tableSalaireNet);

            //Espace
            document.add(new Paragraph("     "));

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
