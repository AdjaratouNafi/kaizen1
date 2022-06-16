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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;
import org.primefaces.event.RowEditEvent;
import sn.accelsolution.dao.PrestataireprimFacade;
import sn.accelsolution.entities.Acompte;
import sn.accelsolution.entities.Actionmenu;
import sn.accelsolution.entities.Chantier;
import sn.accelsolution.entities.Prestataire;
import sn.accelsolution.entities.Prestataireprim;
import sn.accelsolution.util.MontantConverter;
import sn.accelsolution.util.PrestataireUtil;
import sn.accelsolution.util.Total;
import sn.accelsolution.util.UtilControleMenu;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DELL
 */
@ManagedBean
@RequestScoped
public class PrestatairePrimBean implements Serializable {  

    /**
     * Creates a new instance of ContratRecrutBean
     */
    @EJB
    PrestataireprimFacade ejbPrestatairePrim;

    private DataModel item;
    private Prestataireprim prestatairePrim;
    private Prestataireprim prestatairePrimFromEdit;
    private List<Prestataireprim> listprestataires;
    private List<Actionmenu> myllActionmenus;
    private String creerPrestataire;
    private String modifierPrestataire;
    private String supprimerPrestataire;
    private String consulterPrestataire;
     private List<Prestataireprim> filteredListPrestatairePrim;
     
    @PostConstruct
    public void init() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UtilControleMenu utilControleMenu = new UtilControleMenu();

        myllActionmenus = (List<Actionmenu>) session.getAttribute("allActionmenusFromTraitementPrestataires");

        //this.setCreerPrestataire(utilControleMenu.creerPrestataire(myllActionmenus));
        this.setCreerPrestataire("yes");
        //this.setModifierPrestataire(utilControleMenu.modifierPrestataire(myllActionmenus));
        this.setModifierPrestataire("yes");
        //this.setSupprimerPrestataire(utilControleMenu.supprimerPrestataire(myllActionmenus));
        this.setSupprimerPrestataire("yes");
        //this.setConsulterPrestataire(utilControleMenu.consulterPrestataire(myllActionmenus));
        this.setConsulterPrestataire("yes");
        
        
        item = new ListDataModel();
        this.setListprestataires(ejbPrestatairePrim.findAll());
        item.setWrappedData(this.getListprestataires());
        
    }

    public PrestatairePrimBean() {

        prestatairePrim = new Prestataireprim();
        prestatairePrimFromEdit = new Prestataireprim();
    }  

    public PrestataireprimFacade getEjbPrestatairePrim() {
        return ejbPrestatairePrim;
    }

    public void setEjbPrestatairePrim(PrestataireprimFacade ejbPrestatairePrim) {
        this.ejbPrestatairePrim = ejbPrestatairePrim;
    }

    public List<Prestataireprim> getFilteredListPrestatairePrim() {
        return filteredListPrestatairePrim;
    }

    public void setFilteredListPrestatairePrim(List<Prestataireprim> filteredListPrestatairePrim) {
        this.filteredListPrestatairePrim = filteredListPrestatairePrim;
    }

    public List<Actionmenu> getMyllActionmenus() {
        return myllActionmenus;
    }

    public void setMyllActionmenus(List<Actionmenu> myllActionmenus) {
        this.myllActionmenus = myllActionmenus;
    }

    public String getCreerPrestataire() {
        return creerPrestataire;
    }

    public void setCreerPrestataire(String creerPrestataire) {
        this.creerPrestataire = creerPrestataire;
    }

    public String getModifierPrestataire() {
        return modifierPrestataire;
    }

    public void setModifierPrestataire(String modifierPrestataire) {
        this.modifierPrestataire = modifierPrestataire;
    }

    public String getSupprimerPrestataire() {
        return supprimerPrestataire;
    }

    public void setSupprimerPrestataire(String supprimerPrestataire) {
        this.supprimerPrestataire = supprimerPrestataire;
    }

    public String getConsulterPrestataire() {
        return consulterPrestataire;
    }

    public void setConsulterPrestataire(String consulterPrestataire) {
        this.consulterPrestataire = consulterPrestataire;
    }

    public DataModel getItem() {
        
        return item;
    }

    public void setItem(DataModel item) {
        this.item = item;
    }

    public Prestataireprim getPrestatairePrim() {
        return prestatairePrim;
    }

    public void setPrestatairePrim(Prestataireprim prestatairePrim) {
        this.prestatairePrim = prestatairePrim;
    }

    public Prestataireprim getPrestatairePrimFromEdit() {
        return prestatairePrimFromEdit;
    }

    public void setPrestatairePrimFromEdit(Prestataireprim prestatairePrimFromEdit) {
        this.prestatairePrimFromEdit = prestatairePrimFromEdit;
    }

    public List<Prestataireprim> getListprestataires() {
        return listprestataires;
    }

    public void setListprestataires(List<Prestataireprim> listprestataires) {
        this.listprestataires = listprestataires;
    }

    public String save() {
        try {
            ejbPrestatairePrim.insertPrestataire(this.prestatairePrim);
            return "prestataires_prims";
        } catch (Exception e) {
            return "new_prestataire_prim";
        }
    }

    public String reset() {
        this.prestatairePrim = new Prestataireprim();
        return "new_prestataire_prim";
    }

    public String resetModif() {
        return "prestataires_prims";
    }

    public String editer() {
        try {
            prestatairePrimFromEdit = (Prestataireprim) item.getRowData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "edit_prestataire_prim";
    }

    public String update() {
        try { 
            UtilUtfconvert utfconvert = new UtilUtfconvert();
            this.prestatairePrimFromEdit.setNomcomplet(utfconvert.convertFromUTF8(prestatairePrimFromEdit.getNomcomplet()));
            this.prestatairePrimFromEdit.setCorps(utfconvert.convertFromUTF8(prestatairePrimFromEdit.getCorps()));
            ejbPrestatairePrim.edit(this.prestatairePrimFromEdit);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "prestataires_prims";
    }

    public String supprimer() {
        try {
            this.prestatairePrimFromEdit = (Prestataireprim) item.getRowData();
            ejbPrestatairePrim.remove(this.prestatairePrimFromEdit);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "prestataires_prims";
    }

}
