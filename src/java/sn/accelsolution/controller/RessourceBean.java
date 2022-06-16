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
import sn.accelsolution.dao.RessourceFacade;
import sn.accelsolution.entities.Bailleur;
import sn.accelsolution.entities.Ressource;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DELL
 */
@ManagedBean
@RequestScoped
public class RessourceBean implements Serializable {

    @EJB
    RessourceFacade ejbressource;

    private DataModel item;
    private List listRessources;
    private Ressource ressource;
    private Ressource ressourceFromEdit;
    private Ressource ressourceMaj1;
    private Ressource ressourceMaj2;

    /**
     * Creates a new instance of UtilisateurBean
     */
    public RessourceBean() {
        ressource = new Ressource();
        ressourceFromEdit = new Ressource();
        ressourceMaj1 = new Ressource();
        ressourceMaj2 = new Ressource();
    }

    public DataModel getItem() {
        item = new ListDataModel();
        this.setListRessources(ejbressource.findAll());
        item.setWrappedData(this.getListRessources());
        return item;
    }

    public void setItem(DataModel item) {
        this.item = item;
    }

    public List getListRessources() {
        listRessources = ejbressource.findAll();
        return listRessources;
    }

    public void setListRessources(List listRessources) {
        this.listRessources = listRessources;
    }

    public Ressource getRessource() {
        return ressource;
    }

    public void setRessource(Ressource ressource) {
        this.ressource = ressource;
    }

    public Ressource getRessourceFromEdit() {
        return ressourceFromEdit;
    }

    public void setRessourceFromEdit(Ressource ressourceFromEdit) {
        this.ressourceFromEdit = ressourceFromEdit;
    }

    public Ressource getRessourceMaj1() {
        return ressourceMaj1;
    }

    public void setRessourceMaj1(Ressource ressourceMaj1) {
        this.ressourceMaj1 = ressourceMaj1;
    }

    public Ressource getRessourceMaj2() {
        return ressourceMaj2;
    }

    public void setRessourceMaj2(Ressource ressourceMaj2) {
        this.ressourceMaj2 = ressourceMaj2;
    }

    public String save() {
        try {
            this.ressource.setEtatressource("Disponible");
            ejbressource.insertRessource(this.ressource);
            return "ressources";
        } catch (Exception e) {
            return "new_ressource";
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
        return "ressources";
    }

    public String editer() {
        try {
            ressourceFromEdit = (Ressource) item.getRowData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "edit_ressource";
    }

    public String miseAjour() {
        try {
            ressourceFromEdit = ejbressource.find(this.ressourceMaj2.getIdRessource());
            int newqts = ressourceFromEdit.getQtStock() + this.ressourceMaj1.getQtStock();
            ressourceFromEdit.setQtStock(newqts);
            ejbressource.edit(ressourceFromEdit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ressources";
    }

    public String update() {
        try {
            UtilUtfconvert utfconvert = new UtilUtfconvert();
            ressourceFromEdit.setNomRessource(utfconvert.convertFromUTF8(ressourceFromEdit.getNomRessource()));
            ressourceFromEdit.setDescriptionResssource(utfconvert.convertFromUTF8(ressourceFromEdit.getDescriptionResssource()));
            ressourceFromEdit.setTyperessource(utfconvert.convertFromUTF8(ressourceFromEdit.getTyperessource()));
            ressourceFromEdit.setEtatressource(utfconvert.convertFromUTF8(ressourceFromEdit.getEtatressource()));
            ejbressource.edit(ressourceFromEdit);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "ressources";
    }

    public String supprimer() {
        try {
            ressourceFromEdit = (Ressource) item.getRowData();
            ejbressource.remove(ressourceFromEdit);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ressources";
    }

}
