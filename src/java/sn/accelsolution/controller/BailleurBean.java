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
import sn.accelsolution.entities.Bailleur;   

/**
 *
 * @author DELL
 */
@ManagedBean
@RequestScoped
public class BailleurBean implements Serializable {   

    @EJB
    BailleurFacade ejbbailleur;

    private DataModel item;
    private List listBailleurs;
    private Bailleur bailleur;
    private Bailleur bailleurFromEdit;
    

    /**
     * Creates a new instance of UtilisateurBean
     */
    public BailleurBean() {
        bailleur = new Bailleur();
        bailleurFromEdit = new Bailleur();
    }
    
    
    public DataModel getItem() {
        item = new ListDataModel();
        this.setListBailleurs(ejbbailleur.findAll());
        item.setWrappedData(this.getListBailleurs());
        return item;
    }

    public void setItem(DataModel item) {
        this.item = item;
    }

    public BailleurFacade getEjbbailleur() {
        return ejbbailleur;
    }

    public void setEjbbailleur(BailleurFacade ejbbailleur) {
        this.ejbbailleur = ejbbailleur;
    }

    public List getListBailleurs() {
        listBailleurs = ejbbailleur.findAll();
        return listBailleurs;
    }

    public void setListBailleurs(List listBailleurs) {
        this.listBailleurs = listBailleurs;
    }

    public Bailleur getBailleur() {
        return bailleur;
    }

    public void setBailleur(Bailleur bailleur) {
        this.bailleur = bailleur;
    }

    public Bailleur getBailleurFromEdit() {
        return bailleurFromEdit;
    }

    public void setBailleurFromEdit(Bailleur bailleurFromEdit) {
        this.bailleurFromEdit = bailleurFromEdit;
    }

    
    public String save() {
        try {
            ejbbailleur.insertBailleur(this.bailleur);
            return "bailleurs";
        } catch (Exception e) {
            return "new_bailleur";
        }
    }

    public void reset() {
        this.bailleur.setNomBailleur("");
        this.bailleur.setAdresseBailleur("");
        this.bailleur.setBpBailleur("");
        this.bailleur.setMailBailleur("");
        this.bailleur.setTelBailleur("");     
    }

    public String resetModif() {                        
        return "bailleurs";   
    }

    public String editer() {
        try {
            bailleurFromEdit = (Bailleur) item.getRowData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "edit_bailleur";
    }

    public String update() {
        try {
            ejbbailleur.edit(bailleurFromEdit);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "bailleurs";
    }

    public String supprimer() {
        try {
            bailleurFromEdit = (Bailleur) item.getRowData();
            ejbbailleur.remove(bailleurFromEdit);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "bailleurs";
    }

}
