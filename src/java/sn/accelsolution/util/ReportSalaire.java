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
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Image;
//import java.awt.Image;
import java.awt.Toolkit;
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
import sn.accelsolution.entities.Salaire;
import sn.accelsolution.entities.Utilisateur;

/**
 *
 * @author DELL
 */
public class ReportSalaire implements Serializable {

    public ReportSalaire() {
        //setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("tete.png")));
    }

    public void generateBulletinSalaire(Bulletin bulletin) {
        //System.out.print("bonjour");

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

            //String img = "C:\\Users\\DELL\\Documents\\NetBeansProjects\\Estprod\\src\\java\\sn\\accelsolution\\util\\tete.png";
            String img = "http://144.217.254.91/tete.png";

            Image logoProjet = Image.getInstance(img);
            logoProjet.scaleAbsolute(500f, 150f);
            logoProjet.setAlignment(Image.ALIGN_MIDDLE);
            document.add(logoProjet);

            //Espace
            document.add(new Paragraph("     "));

            // A table1 with 4 columns
            PdfPTable table1 = new PdfPTable(4);
            table1.setSpacingBefore(10f);
            table1.setWidthPercentage(100);
            // the cell object
            PdfPCell cell1;
            // we add a cell with colspan 4
            cell1 = new PdfPCell(new Phrase("BULLETIN DE PAIE"));
            cell1.setColspan(4);

            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            //cell1.setBorder(1);
            table1.addCell(cell1);
            document.add(table1);

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
            PdfPCell cell4;
            // we add a cell with colspan 4
            cell4 = new PdfPCell(new Phrase(" Nom: " + bulletin.getNom() + "\n\n Situation matrimoniale: " + bulletin.getSituation() + " \n\n  Nombre d'enfant \n\n Catégorie \n\n PARTS IR: " + bulletin.getPartir() + " \n"));
            cell4.setColspan(4);

            //cell4.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            //cell4.setBorder(0);
            table4.addCell(cell4);
            document.add(table4);

            //Espace
            document.add(new Paragraph("     "));

            // A table1 with 9 columns
            PdfPTable table5 = new PdfPTable(9);
            table5.setSpacingBefore(10f);
            table5.setWidthPercentage(100);

            PdfPCell cell5;
            cell5 = new PdfPCell(new Phrase("Désignation"));
            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell5);

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

            PdfPCell cell22;
            cell22 = new PdfPCell(new Phrase(" "));
            cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell22.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell22);

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

            PdfPCell cell33;
            cell33 = new PdfPCell(new Phrase(" "));
            cell33.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell33.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cell33);

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

    public void TestSauvegarde() throws DocumentException {
        try {

            /*Cnstruction of path name*/
            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
            String strDate = dateFormat.format(date);
            GenerateurCode gnCode = new GenerateurCode();
            int code = gnCode.genererRandomInteger(100, 1);
            String construct_file_name = "Fiche_de_paie_" + code + "_" + strDate + ".pdf";
            String file_name = "C:\\Users\\DELL\\Documents\\DocPdfTest\\" + construct_file_name;

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file_name));

            document.open();

            document.add(new Paragraph("  Test de generation de document !!!   "));

            document.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReportSalaire.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void TestList() throws DocumentException {
        try {

            Document document = new Document();
            String file_name = "C:\\Users\\DELL\\Documents\\DocPdfTest\\sample3.pdf";
            PdfPTable table = new PdfPTable(7);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell("Désignation");
            table.addCell("Qté");
            table.addCell("PU.HT");
            table.addCell("TVA");
            table.addCell("Taxes");
            table.addCell("Hors taxes");
            table.addCell("Total TTC");
            table.setHeaderRows(1);
            PdfPCell[] cells = table.getRow(0).getCells();
            for (int j = 0; j < cells.length; j++) {
                cells[j].setBackgroundColor(BaseColor.GRAY);
            }
            for (int i = 1; i < 5; i++) {
                table.addCell("Name:" + i);
                table.addCell("Age:" + i);
                table.addCell("Location:" + i);
                table.addCell("Name:" + i);
                table.addCell("Age:" + i);
                table.addCell("Location:" + i);
                table.addCell("Location:" + i);
            }
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            document.open();
            document.add(table);
            document.close();
            System.out.println("Done");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReportSalaire.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ImgPdf() {
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

            // A table1 with 4 columns
            PdfPTable table1 = new PdfPTable(4);
            table1.setSpacingBefore(10f);
            table1.setWidthPercentage(100);
            // the cell object
            PdfPCell cell1;
            // we add a cell with colspan 4
            cell1 = new PdfPCell(new Phrase("BULLETIN DE PAIE"));
            cell1.setColspan(4);

            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // border
            //cell1.setBorder(1);
            table1.addCell(cell1);
            document.add(table1);

            //Espace
            document.add(new Paragraph("     "));

            //String img = "C:\\Users\\DELL\\Documents\\NetBeansProjects\\Estprod\\src\\java\\sn\\accelsolution\\util\\tete.png";
            String img = "http://144.217.254.91/tete.png";

            Image logoProjet = Image.getInstance(img);
            logoProjet.setAlignment(Image.ALIGN_MIDDLE);
            document.add(logoProjet);

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
