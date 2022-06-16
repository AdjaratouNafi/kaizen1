/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.controller;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import sn.accelsolution.dao.BailleurFacade;
import sn.accelsolution.dao.ProspectionFacade;
import sn.accelsolution.entities.Bailleur;
import sn.accelsolution.entities.Prospection;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DELL
 */
@ManagedBean
@RequestScoped
public class ProspectionBean implements Serializable {

    @EJB
    ProspectionFacade ejbprospection;

    private DataModel item;
    private List listProspections;
    private Prospection prospection;
    private Prospection prospectionFromEdit;
    

    /**
     * Creates a new instance of UtilisateurBean
     */
    public ProspectionBean() {
        prospection = new Prospection();
        prospectionFromEdit = new Prospection();
    }
    
    
    public DataModel getItem() {
        item = new ListDataModel();
        this.setListProspections(ejbprospection.findAll());
        item.setWrappedData(this.getListProspections());
        return item;
    }

    public void setItem(DataModel item) {
        this.item = item;
    }

    public List getListProspections() {
        listProspections = ejbprospection.findAll();
        return listProspections;
    }

    public void setListProspections(List listProspections) {
        this.listProspections = listProspections;
    }

    public Prospection getProspection() {
        return prospection;
    }

    public void setProspection(Prospection prospection) {
        this.prospection = prospection;
    }

    public Prospection getProspectionFromEdit() {
        return prospectionFromEdit;
    }

    public void setProspectionFromEdit(Prospection prospectionFromEdit) {
        this.prospectionFromEdit = prospectionFromEdit;
    }

    

    
    public String save() {
        try {
            ejbprospection.insertProspection(this.prospection);
            return "prospections";
        } catch (Exception e) {
            return "new_prospection";
        }
    }

    public void reset() {
//        this.bailleur.setNomBailleur("");
//        this.bailleur.setAdresseBailleur("");
//        this.bailleur.setBpBailleur("");
//        this.bailleur.setMailBailleur("");
//        this.bailleur.setTelBailleur("");
    }

    public String resetModif() {
        return "prospections";
    }

    public String editer() {
        try {
            prospectionFromEdit = (Prospection) item.getRowData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "edit_prospection";
    }

    public String update() {
        try { 
            UtilUtfconvert utfconvert = new UtilUtfconvert();
            prospectionFromEdit.setDescriptionProspection(utfconvert.convertFromUTF8(prospectionFromEdit.getDescriptionProspection()));
            ejbprospection.edit(prospectionFromEdit);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "prospections";
    }

    public String supprimer() {
        try {
            prospectionFromEdit = (Prospection) item.getRowData();
            ejbprospection.remove(prospectionFromEdit);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "prospections";
    }

}
