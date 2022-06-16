/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;
import sn.accelsolution.dao.DeviseFacade;
import sn.accelsolution.entities.Actionmenu;
import sn.accelsolution.entities.Devise;
import sn.accelsolution.util.UtilControleMenu;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DELL
 */
@ManagedBean     
@RequestScoped
public class DeviseBean implements Serializable {

    @EJB
    DeviseFacade ejbDevise;

    private DataModel item;
    private List listDevises;
    private Devise devise;
    private Devise deviseFromEdit;
    private List<Actionmenu> myllActionmenus;
    private String creerDevise;
    private String modifDevise;
    private String supprimerDevise;
    private String consulterDevise;
    private String imprimerDevise;
    private List<Devise> filteredListDevise;
    

    /**
     * Creates a new instance of UtilisateurBean
     */
    @PostConstruct
    public void init() {

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UtilControleMenu utilControleMenu = new UtilControleMenu();

        myllActionmenus = (List<Actionmenu>) session.getAttribute("allActionmenusFromTraitementDevises");

        this.setCreerDevise(utilControleMenu.creerDevise(myllActionmenus));    
        this.setModifDevise(utilControleMenu.modifierDevise(myllActionmenus));
        this.setSupprimerDevise(utilControleMenu.supprimerDevise(myllActionmenus));
        this.setConsulterDevise(utilControleMenu.consulterDevise(myllActionmenus));

        item = new ListDataModel();
        this.setListDevises(ejbDevise.findAll());
        item.setWrappedData(this.getListDevises());
        
        
    }

    public DeviseBean() {
        devise = new Devise();
        deviseFromEdit = new Devise();
    } 

    public List<Devise> getFilteredListDevise() {
        return filteredListDevise;
    }

    public void setFilteredListDevise(List<Devise> filteredListDevise) {
        this.filteredListDevise = filteredListDevise;
    }
    

    public List<Actionmenu> getMyllActionmenus() {
        return myllActionmenus;
    }

    public void setMyllActionmenus(List<Actionmenu> myllActionmenus) {
        this.myllActionmenus = myllActionmenus;
    }

    public String getCreerDevise() {
        return creerDevise;
    }

    public void setCreerDevise(String creerDevise) {
        this.creerDevise = creerDevise;
    }

    public String getModifDevise() {
        return modifDevise;
    }

    public void setModifDevise(String modifDevise) {
        this.modifDevise = modifDevise;
    }

    public String getSupprimerDevise() {
        return supprimerDevise;
    }

    public void setSupprimerDevise(String supprimerDevise) {
        this.supprimerDevise = supprimerDevise;
    }

    public String getConsulterDevise() {
        return consulterDevise;
    }

    public void setConsulterDevise(String consulterDevise) {
        this.consulterDevise = consulterDevise;
    }

    public String getImprimerDevise() {
        return imprimerDevise;
    }

    public void setImprimerDevise(String imprimerDevise) {
        this.imprimerDevise = imprimerDevise;
    }

    public DataModel getItem() {
        return item;
    }

    public void setItem(DataModel item) {
        this.item = item;
    }

    public List getListDevises() {
        listDevises = ejbDevise.findAll();
        return listDevises;
    }

    public void setListDevises(List listDevises) {
        this.listDevises = listDevises;
    }

    public Devise getDevise() {
        return devise;
    }

    public void setDevise(Devise devise) {
        this.devise = devise;
    }

    public Devise getDeviseFromEdit() {
        return deviseFromEdit;
    }

    public void setDeviseFromEdit(Devise deviseFromEdit) {
        this.deviseFromEdit = deviseFromEdit;
    }

    public String save() {
        try {
            ejbDevise.insertDevise(this.devise);
            return "devises";
        } catch (Exception e) {
            return "new_devise";
        }
    }

    public void reset() {
        this.devise = new Devise();
    }

    public String editer() {
        try {
            deviseFromEdit = (Devise) item.getRowData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "edit_devise";
    }

    public String update() {
        try { 
            UtilUtfconvert utfconvert = new UtilUtfconvert();
            deviseFromEdit.setLibelle(utfconvert.convertFromUTF8(deviseFromEdit.getLibelle()));
            ejbDevise.edit(deviseFromEdit);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "devises";
    }

    public String supprimer() {
        try {
            deviseFromEdit = (Devise) item.getRowData();
            ejbDevise.remove(deviseFromEdit);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "devises";
    }

}
