/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.controller;

import java.io.FileInputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.commons.io.FilenameUtils;
import sn.accelsolution.util.FileManager;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sn.accelsolution.util.DevisImportUtil;
import sn.accelsolution.util.EbImportUtil;

/**
 *
 * @author DELL
 */
@ManagedBean
@ViewScoped
public class DevisimportBean implements Serializable {

    /**
     * Creates a new instance of ContratRecrutBean
     */
    private Part file;
    private List<DevisImportUtil> listDevisImportUtil;
    private Integer count;

    @PostConstruct
    public void init() {
    }

    public DevisimportBean() {
        listDevisImportUtil = new ArrayList<>();
    }

    public List<DevisImportUtil> getListDevisImportUtil() {
        return listDevisImportUtil;
    }

    public void setListDevisImportUtil(List<DevisImportUtil> listDevisImportUtil) {
        this.listDevisImportUtil = listDevisImportUtil;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public Integer getCount() {
        if (count == null) {
            count = 0;
        } else {
            count = count + (int) (Math.random() * 35);
            if (count > 100) {
                count = 100;
            }
        }

        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String save() {

        try {

            count = 0;

            Part uploadedFile = getFile();
            Workbook workbook = null;
            try {
                if (uploadedFile != null) {
                    // Create a Workbook from the file input stream
                    workbook = new XSSFWorkbook(uploadedFile.getInputStream());

                    // Get the First sheet from the wrokbook
                    Sheet firstSheet = workbook.getSheetAt(0);
                    Iterator<Row> iterator = firstSheet.iterator();

                    while (iterator.hasNext()) {
                        // Get the Row that we need to read
                        Row nextRow = iterator.next();

                        String designation;
                        if (nextRow.getCell(0) == null) {
                            designation = "null";
                        } else {
                            designation = nextRow.getCell(0).toString();
                        }

                        String ref;
                        if (nextRow.getCell(1) == null) {
                            ref = "null";
                        } else {
                            ref = nextRow.getCell(1).toString();
                        }

                        String um;
                        if (nextRow.getCell(2) == null) {
                            um = "null";
                        } else {
                            um = nextRow.getCell(2).toString();
                        }

                        String qt;
                        if (nextRow.getCell(3) == null) {
                            qt = "null";
                        } else {
                            qt = nextRow.getCell(3).toString();
                        }

                        DevisImportUtil devimp = new DevisImportUtil();
                        devimp.setDesignation(designation);
                        devimp.setReference(ref);
                        devimp.setUm(um);
                        devimp.setQt(qt);

                        this.listDevisImportUtil.add(devimp);

                    }

                    HttpSession session1 = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                    session1.setAttribute("MyDevisImported", this.listDevisImportUtil);
                    //return "traitement_notificationDevis";
                    return "traitement_devisImported";
                }

            } catch (Exception e) {
                e.getStackTrace();
            }

            return "new_devis_import";

        } catch (Exception e) {
        }
        return "new_expression_import";
    }

}
