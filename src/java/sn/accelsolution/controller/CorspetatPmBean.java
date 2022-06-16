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
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;
import org.primefaces.event.RowEditEvent;
import sn.accelsolution.dao.CorpsetatFacade;
import sn.accelsolution.entities.Actionmenu;
import sn.accelsolution.entities.Corpsetat;
import sn.accelsolution.util.UtilControleMenu;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DELL
 */
@ManagedBean
@RequestScoped
public class CorspetatPmBean implements Serializable {

    /**
     * Creates a new instance of ContratRecrutBean
     */
    @EJB
    CorpsetatFacade ejbCorpsetat;

    private Corpsetat corpsetat;
    private Corpsetat corpsetatFromEdit;

    private List<Corpsetat> allCorpsetats = new ArrayList<>();
    private DataModel item;
    private List<Actionmenu> myllActionmenus;
    private String creerCorspetat;
    private String modifierCorspetat;
    private String supprimerCorspetat;
    private String consulterCorspetat;
    private String imprimerCorspetat;
    private List<Corpsetat> filteredListCorpsetat;

    @PostConstruct
    public void init() {

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UtilControleMenu utilControleMenu = new UtilControleMenu();

        myllActionmenus = (List<Actionmenu>) session.getAttribute("allActionmenusFromTraitementCorpEtat");

        this.setCreerCorspetat(utilControleMenu.creerCorspetat(myllActionmenus));
        this.setModifierCorspetat(utilControleMenu.modifierCorspetat(myllActionmenus));
        this.setSupprimerCorspetat(utilControleMenu.supprimerCorspetat(myllActionmenus));
        this.setConsulterCorspetat(utilControleMenu.consulterCorspetat(myllActionmenus));

        //this.allCorpsetats = ejbCorpsetat.findAll();
        
        item = new ListDataModel();
        List<Corpsetat> lisAllCorps = ejbCorpsetat.findAll();
        List<Corpsetat> lisAllCorpsToAdd = new ArrayList<>();
        lisAllCorpsToAdd.clear();
        //UtilUtfconvert utfconvert = new UtilUtfconvert();
        for(Corpsetat corpett: lisAllCorps){
            lisAllCorpsToAdd.add(corpett);
        }
        this.setAllCorpsetats(lisAllCorpsToAdd);
        item.setWrappedData(this.getAllCorpsetats());
        
        
    }

    public CorspetatPmBean() {

        corpsetat = new Corpsetat();
        corpsetatFromEdit = new Corpsetat();
    } 

    public List<Corpsetat> getFilteredListCorpsetat() {
        return filteredListCorpsetat;
    }

    public void setFilteredListCorpsetat(List<Corpsetat> filteredListCorpsetat) {
        this.filteredListCorpsetat = filteredListCorpsetat;
    }

    public List<Actionmenu> getMyllActionmenus() {
        return myllActionmenus;
    }

    public void setMyllActionmenus(List<Actionmenu> myllActionmenus) {
        this.myllActionmenus = myllActionmenus;
    }

    public String getCreerCorspetat() {
        return creerCorspetat;
    }

    public void setCreerCorspetat(String creerCorspetat) {
        this.creerCorspetat = creerCorspetat;
    }

    public String getModifierCorspetat() {
        return modifierCorspetat;
    }

    public void setModifierCorspetat(String modifierCorspetat) {
        this.modifierCorspetat = modifierCorspetat;
    }

    public String getSupprimerCorspetat() {
        return supprimerCorspetat;
    }

    public void setSupprimerCorspetat(String supprimerCorspetat) {
        this.supprimerCorspetat = supprimerCorspetat;
    }

    public String getConsulterCorspetat() {
        return consulterCorspetat;
    }

    public void setConsulterCorspetat(String consulterCorspetat) {
        this.consulterCorspetat = consulterCorspetat;
    }

    public String getImprimerCorspetat() {
        return imprimerCorspetat;
    }

    public void setImprimerCorspetat(String imprimerCorspetat) {
        this.imprimerCorspetat = imprimerCorspetat;
    }

    public DataModel getItem() {
        return item;
    }

    public void setItem(DataModel item) {
        this.item = item;
    }

    public Corpsetat getCorpsetat() {
        return corpsetat;
    }

    public void setCorpsetat(Corpsetat corpsetat) {
        this.corpsetat = corpsetat;
    }

    public Corpsetat getCorpsetatFromEdit() {
        return corpsetatFromEdit;
    }

    public void setCorpsetatFromEdit(Corpsetat corpsetatFromEdit) {
        this.corpsetatFromEdit = corpsetatFromEdit;
    }

    public List<Corpsetat> getAllCorpsetats() {
        return allCorpsetats;
    }

    public void setAllCorpsetats(List<Corpsetat> allCorpsetats) {
        this.allCorpsetats = allCorpsetats;
    }

    public String save() {
        try {
            ejbCorpsetat.insertCorpsetat(this.corpsetat);
            return "corpsetats";
        } catch (Exception e) {
            return "new_corpsetat";
        }
    }

    public String reset() {
        this.corpsetat = new Corpsetat();
        return "corpsetats";
    }

    public CorpsetatFacade getEjbCorpsetat() {
        return ejbCorpsetat;
    }

    public void setEjbCorpsetat(CorpsetatFacade ejbCorpsetat) {
        this.ejbCorpsetat = ejbCorpsetat;
    }

    public void rowEdit(RowEditEvent event) {
        Corpsetat corpsetatEdit = (Corpsetat) event.getObject();
        ejbCorpsetat.edit(corpsetatEdit);
    }

    public void rowEditCancel() {

    }

    public String editer() {
        try {
            corpsetatFromEdit = (Corpsetat) item.getRowData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "edit_corpsetatpm";
    }

    public String update() {
        try { 
            UtilUtfconvert utfconvert = new UtilUtfconvert();
            corpsetatFromEdit.setLibellecoprsetat(utfconvert.convertFromUTF8(corpsetatFromEdit.getLibellecoprsetat()));
            ejbCorpsetat.edit(corpsetatFromEdit); 
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "corpsetats";
    }

    public String supprimer() {
        try {
            corpsetatFromEdit = (Corpsetat) item.getRowData();
            ejbCorpsetat.remove(corpsetatFromEdit);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "corpsetats";
    }
}
