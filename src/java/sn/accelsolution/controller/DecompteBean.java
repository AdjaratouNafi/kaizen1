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
import sn.accelsolution.dao.ChantierFacade;
import sn.accelsolution.dao.ClientFacade;
import sn.accelsolution.dao.DecompteFacade;
import sn.accelsolution.dao.DetaillechantierFacade;
import sn.accelsolution.dao.MarcheFacade;
import sn.accelsolution.dao.OperationFacade;
import sn.accelsolution.dao.RessourceFacade;
import sn.accelsolution.entities.Chantier;
import sn.accelsolution.entities.Decompte;
import sn.accelsolution.entities.Detaillechantier;
import sn.accelsolution.entities.Marche;
import sn.accelsolution.entities.Operation;

/**
 *
 * @author DELL
 */
@ManagedBean
@RequestScoped
public class DecompteBean implements Serializable {

    @EJB
    ClientFacade ejbclient;

    @EJB
    MarcheFacade ejbmarche;

    @EJB
    ChantierFacade ejbchantier;

    @EJB
    DecompteFacade ejbdecompte;

    

    private DataModel item;
    private List listDecomptes;

    
    private Decompte decompte;
    private Decompte decompteFromEdit;

    /**
     * Creates a new instance of UtilisateurBean
     */
    public DecompteBean() {
        decompte = new Decompte();
        decompteFromEdit = new Decompte();
    }

    

    public DataModel getItem() {
        item = new ListDataModel();
        this.setListDecomptes(ejbdecompte.findAll());
        item.setWrappedData(this.getListDecomptes());
        return item;
    }
    
    public void setItem(DataModel item) {
        this.item = item;
    }

    public List getListDecomptes() {
        listDecomptes = ejbdecompte.findAll();
        return listDecomptes;
    }

    public void setListDecomptes(List listDecomptes) {
        this.listDecomptes = listDecomptes;
    }

    public Decompte getDecompte() {
        return decompte;
    }

    public void setDecompte(Decompte decompte) {
        this.decompte = decompte;
    }

    public Decompte getDecompteFromEdit() {
        return decompteFromEdit;
    }

    public void setDecompteFromEdit(Decompte decompteFromEdit) {
        this.decompteFromEdit = decompteFromEdit;
    }

    

    public String save() {
        try {
            ejbdecompte.insertDecompte(decompte);
            return "decomptes";
        } catch (Exception e) {
            return "new_decompte";
        }
    }

    

    public void reset() {

    }

    public String resetModif() {
        return "decomptes";
    }

//    public String editer() {
//        try {
//            decompteFromEdit = (Decompte) item.getRowData();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "edit_marches";
//    }

    

//    public String update() {
//        try {
//            int mte = Integer.parseInt(this.marcheFromEdit.getMontantExecution());
//            Double mtetva = mte * 0.18;
//            Double mt = mte + mtetva;
//            this.marcheFromEdit.setMontantMarche(mt.toString());
//            ejbmarche.edit(marcheFromEdit);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return "marches";
//    }

    public String supprimer() {
        try {
            decompteFromEdit = (Decompte) item.getRowData();
            ejbdecompte.remove(decompteFromEdit);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "decomptes";
    }

}
