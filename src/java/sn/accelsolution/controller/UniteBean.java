/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.controller;

import java.io.Serializable;
import java.util.ArrayList;
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
import sn.accelsolution.dao.UnitemesureFacade;
import sn.accelsolution.entities.Actionmenu;
import sn.accelsolution.entities.Unitemesure;
import sn.accelsolution.util.UtilControleMenu;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DELL
 */
@ManagedBean
@RequestScoped
public class UniteBean implements Serializable {

    @EJB
    UnitemesureFacade ejbunitemesure;

    private DataModel item;
    private List listUnites;
    private Unitemesure unitemesure;
    private Unitemesure unitemesureFromEdit;
    private List<Actionmenu> myllActionmenus;
    private String creerUnitemesure;
    private String modifierUnitemesure;
    private String supprimerUnitemesure;
    private String consulterUnitemesure;
    private String imprimerUnitemesure;
    private List<Unitemesure> filteredListUnite;

    /**
     * Creates a new instance of UtilisateurBean
     */
    @PostConstruct
    public void init() {

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UtilControleMenu utilControleMenu = new UtilControleMenu();
        
        
        myllActionmenus = (List<Actionmenu>) session.getAttribute("allActionmenusFromTraitementUniteMesure"); 
        
        this.setCreerUnitemesure(utilControleMenu.creerUnitemesure(myllActionmenus));
        this.setModifierUnitemesure(utilControleMenu.modifierUnitemesure(myllActionmenus));
        this.setSupprimerUnitemesure(utilControleMenu.supprimerUnitemesure(myllActionmenus));
        this.setConsulterUnitemesure(utilControleMenu.consulterUnitemesure(myllActionmenus));
        
        item = new ListDataModel();
        List<Unitemesure> allListMesure = ejbunitemesure.findAll();
        List<Unitemesure> allListMesureToAdd = new ArrayList<>();
        allListMesureToAdd.clear();    
        //UtilUtfconvert utfconvert = new UtilUtfconvert();
        for(Unitemesure unitem: allListMesure){
            allListMesureToAdd.add(unitem);
        }
        
        this.setListUnites(allListMesureToAdd);
        item.setWrappedData(this.getListUnites());

    }
    
    public UniteBean() {
        unitemesure = new Unitemesure();
        unitemesureFromEdit = new Unitemesure();
    } 

    public List<Unitemesure> getFilteredListUnite() {
        return filteredListUnite;
    }

    public void setFilteredListUnite(List<Unitemesure> filteredListUnite) {
        this.filteredListUnite = filteredListUnite;
    }

    public List<Actionmenu> getMyllActionmenus() {
        return myllActionmenus;
    }

    public void setMyllActionmenus(List<Actionmenu> myllActionmenus) {
        this.myllActionmenus = myllActionmenus;
    }

    public String getCreerUnitemesure() {
        return creerUnitemesure;
    }

    public void setCreerUnitemesure(String creerUnitemesure) {
        this.creerUnitemesure = creerUnitemesure;
    }

    public String getModifierUnitemesure() {
        return modifierUnitemesure;
    }

    public void setModifierUnitemesure(String modifierUnitemesure) {
        this.modifierUnitemesure = modifierUnitemesure;
    }

    public String getSupprimerUnitemesure() {
        return supprimerUnitemesure;
    }

    public void setSupprimerUnitemesure(String supprimerUnitemesure) {
        this.supprimerUnitemesure = supprimerUnitemesure;
    }

    public String getConsulterUnitemesure() {
        return consulterUnitemesure;
    }

    public void setConsulterUnitemesure(String consulterUnitemesure) {
        this.consulterUnitemesure = consulterUnitemesure;
    }

    public String getImprimerUnitemesure() {
        return imprimerUnitemesure;
    }

    public void setImprimerUnitemesure(String imprimerUnitemesure) {
        this.imprimerUnitemesure = imprimerUnitemesure;
    }
    

    public DataModel getItem() {
        
        return item;
    }

    public void setItem(DataModel item) {
        this.item = item;
    }

    public List getListUnites() {  
        return listUnites;
    }

    public void setListUnites(List listUnites) {
        this.listUnites = listUnites;
    }

    public Unitemesure getUnitemesure() {
        return unitemesure;
    }

    public void setUnitemesure(Unitemesure unitemesure) {
        this.unitemesure = unitemesure;
    }

    public Unitemesure getUnitemesureFromEdit() {
        return unitemesureFromEdit;
    }

    public void setUnitemesureFromEdit(Unitemesure unitemesureFromEdit) {
        this.unitemesureFromEdit = unitemesureFromEdit;
    }

    public String save() {
        try {
            ejbunitemesure.insertUnite(this.unitemesure);
            return "unites";
        } catch (Exception e) {
            return "new_unite";
        }
    }

    public void reset() {
        this.unitemesure = new Unitemesure();
    }

    public String editer() {
        try {
            unitemesureFromEdit = (Unitemesure) item.getRowData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "edit_unite";
    }

    public String update() {
        try { 
             UtilUtfconvert utfconvert = new UtilUtfconvert();
             unitemesureFromEdit.setLibelle(utfconvert.convertFromUTF8(unitemesureFromEdit.getLibelle()));
            ejbunitemesure.edit(unitemesureFromEdit);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "unites";
    }

    public String supprimer() {
        try {
            unitemesureFromEdit = (Unitemesure) item.getRowData();
            ejbunitemesure.remove(unitemesureFromEdit);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "unites";
    }

}
