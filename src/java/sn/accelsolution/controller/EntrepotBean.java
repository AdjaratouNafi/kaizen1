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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;
import sn.accelsolution.dao.ClientFacade;
import sn.accelsolution.dao.EntrepotFacade;
import sn.accelsolution.entities.Actionmenu;
import sn.accelsolution.entities.Client;
import sn.accelsolution.entities.Entrepot;
import sn.accelsolution.util.UtilControleMenu;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DELL
 */
@ManagedBean
@RequestScoped
public class EntrepotBean implements Serializable {

    @EJB
    EntrepotFacade ejbentrepot;

    private DataModel item;
    private List listEntrepots;
    private Entrepot entrepot;
    private Entrepot entrepotFromEdit;
    private List<Actionmenu> myllActionmenus;
    private String creerEntrepot;
    private String modifierEntrepot;
    private String supprimerEntrepot;
    private String consulterEntrepot;
    private String imprimerEntrepot;
    private List<Entrepot> filteredListEntrepot;

    /**
     * Creates a new instance of UtilisateurBean
     */
    @PostConstruct
    public void init() {

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UtilControleMenu utilControleMenu = new UtilControleMenu();
        
        
        myllActionmenus = (List<Actionmenu>) session.getAttribute("allActionmenusFromTraitementEntrepots"); 
        
        this.setCreerEntrepot(utilControleMenu.creerEntrepot(myllActionmenus));
        this.setModifierEntrepot(utilControleMenu.modifierEntrepot(myllActionmenus));
        this.setSupprimerEntrepot(utilControleMenu.supprimerEntrepot(myllActionmenus));
        this.setConsulterEntrepot(utilControleMenu.consulterEntrepot(myllActionmenus));
        
        item = new ListDataModel();
        this.setListEntrepots(ejbentrepot.findAll());
        item.setWrappedData(this.getListEntrepots());

    } 
    
    public EntrepotBean() {
        entrepot = new Entrepot();
        entrepotFromEdit = new Entrepot();
    } 

    public List<Entrepot> getFilteredListEntrepot() {
        return filteredListEntrepot;
    }

    public void setFilteredListEntrepot(List<Entrepot> filteredListEntrepot) {
        this.filteredListEntrepot = filteredListEntrepot;
    }
    
    

    public DataModel getItem() {
        
        return item;
    } 

    public List<Actionmenu> getMyllActionmenus() {
        return myllActionmenus;
    }

    public void setMyllActionmenus(List<Actionmenu> myllActionmenus) {
        this.myllActionmenus = myllActionmenus;
    }

    public String getCreerEntrepot() {
        return creerEntrepot;
    }

    public void setCreerEntrepot(String creerEntrepot) {
        this.creerEntrepot = creerEntrepot;
    }

    public String getModifierEntrepot() {
        return modifierEntrepot;
    }

    public void setModifierEntrepot(String modifierEntrepot) {
        this.modifierEntrepot = modifierEntrepot;
    }

    public String getSupprimerEntrepot() {
        return supprimerEntrepot;
    }

    public void setSupprimerEntrepot(String supprimerEntrepot) {
        this.supprimerEntrepot = supprimerEntrepot;
    }

    public String getConsulterEntrepot() {
        return consulterEntrepot;
    }

    public void setConsulterEntrepot(String consulterEntrepot) {
        this.consulterEntrepot = consulterEntrepot;
    }

    public String getImprimerEntrepot() {
        return imprimerEntrepot;
    }

    public void setImprimerEntrepot(String imprimerEntrepot) {
        this.imprimerEntrepot = imprimerEntrepot;
    }

    public void setItem(DataModel item) {
        this.item = item;
    }

    public List getListEntrepots() {
        listEntrepots = ejbentrepot.findAll();
        return listEntrepots;
    }

    public void setListEntrepots(List listEntrepots) {
        this.listEntrepots = listEntrepots;
    }

    public Entrepot getEntrepot() {
        return entrepot;
    }

    public void setEntrepot(Entrepot entrepot) {
        this.entrepot = entrepot;
    }

    public Entrepot getEntrepotFromEdit() {
        return entrepotFromEdit;
    }

    public void setEntrepotFromEdit(Entrepot entrepotFromEdit) {
        this.entrepotFromEdit = entrepotFromEdit;
    }

    public String save() {
        try {
            ejbentrepot.insertEntrepot(this.entrepot);
            return "entrepots";
        } catch (Exception e) {
            return "new_entrepot";
        }
    }

    public String reset() {
        this.entrepot = new Entrepot();
        return "new_entrepot";
    }

    public String resetModif() {
        return "entrepots";
    }

    public String editer() {
        try {
            entrepotFromEdit = (Entrepot) item.getRowData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "edit_entrepot";
    }

    public String update() {
        try { 
            UtilUtfconvert utfconvert = new UtilUtfconvert();
            entrepotFromEdit.setNom(utfconvert.convertFromUTF8(entrepotFromEdit.getNom()));
            entrepotFromEdit.setAdresse(utfconvert.convertFromUTF8(entrepotFromEdit.getAdresse()));
            ejbentrepot.edit(entrepotFromEdit);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "entrepots";
    }

    public String supprimer() {
        try {
            entrepotFromEdit = (Entrepot) item.getRowData();
            ejbentrepot.remove(entrepotFromEdit);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "entrepots";
    }

}
