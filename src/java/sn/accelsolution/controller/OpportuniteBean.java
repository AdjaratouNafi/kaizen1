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
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.event.RowEditEvent;
import sn.accelsolution.dao.CommandeFacade;
import sn.accelsolution.dao.DetailleCommandeFacade;
import sn.accelsolution.dao.FournisseurFacade;
import sn.accelsolution.dao.MarchandiseFacade;
import sn.accelsolution.dao.OpportuniteFacade;
import sn.accelsolution.dao.StockFacade;
import sn.accelsolution.dao.UnitemesureFacade;
import sn.accelsolution.dao.UtilisateurFacade;
import sn.accelsolution.entities.Actionmenu;
import sn.accelsolution.entities.Commande;
import sn.accelsolution.entities.DetailleCommande;
import sn.accelsolution.entities.Fournisseur;
import sn.accelsolution.entities.Marchandise;
import sn.accelsolution.entities.Opportunite;
import sn.accelsolution.entities.Stock;  
import sn.accelsolution.entities.Unitemesure;   
import sn.accelsolution.entities.Utilisateur;
import sn.accelsolution.util.MontantConverter; 
import sn.accelsolution.util.UtilControleMenu;  
import sn.accelsolution.util.UtilStock;

/**
 *
 * @author DELL
 */
@ManagedBean
@ViewScoped
public class OpportuniteBean implements Serializable {

    /**
     * Creates a new instance of ContratRecrutBean
     */
    @EJB
    OpportuniteFacade ejbOpportunite;
    @EJB
    UtilisateurFacade ejbUtilisateur;

    private Opportunite opportunite;
    private Opportunite opportuniteFromEdit;

    private List<Opportunite> allOpportunites = new ArrayList<>();

    private List<Actionmenu> myllActionmenus;
    private String creerOpportunite;
    private String modifierOpportunite;
    private String supprimerOpportunite;
    private String consulterOpportunite;
    private String imprimerOpportunite;

    @PostConstruct
    public void init() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UtilControleMenu utilControleMenu = new UtilControleMenu();

        myllActionmenus = (List<Actionmenu>) session.getAttribute("allActionmenusFromTraitementOpportunites");

        this.setCreerOpportunite(utilControleMenu.creerOpportunite(myllActionmenus));
        this.setModifierOpportunite(utilControleMenu.modifierOpportunite(myllActionmenus));
        this.setSupprimerOpportunite(utilControleMenu.supprimerOpportunite(myllActionmenus));
        this.setConsulterOpportunite(utilControleMenu.consulterOpportunite(myllActionmenus));

        this.allOpportunites = ejbOpportunite.findAll();
    }

    public OpportuniteBean() {

        opportunite = new Opportunite();
        opportuniteFromEdit = new Opportunite();
    }

    public List<Actionmenu> getMyllActionmenus() {
        return myllActionmenus;
    }

    public void setMyllActionmenus(List<Actionmenu> myllActionmenus) {
        this.myllActionmenus = myllActionmenus;
    }

    public String getCreerOpportunite() {
        return creerOpportunite;
    }

    public void setCreerOpportunite(String creerOpportunite) {
        this.creerOpportunite = creerOpportunite;
    }

    public String getModifierOpportunite() {
        return modifierOpportunite;
    }

    public void setModifierOpportunite(String modifierOpportunite) {
        this.modifierOpportunite = modifierOpportunite;
    }

    public String getSupprimerOpportunite() {
        return supprimerOpportunite;
    }

    public void setSupprimerOpportunite(String supprimerOpportunite) {
        this.supprimerOpportunite = supprimerOpportunite;
    }

    public String getConsulterOpportunite() {
        return consulterOpportunite;
    }

    public void setConsulterOpportunite(String consulterOpportunite) {
        this.consulterOpportunite = consulterOpportunite;
    }

    public String getImprimerOpportunite() {
        return imprimerOpportunite;
    }

    public void setImprimerOpportunite(String imprimerOpportunite) {
        this.imprimerOpportunite = imprimerOpportunite;
    }

    public Opportunite getOpportunite() {
        return opportunite;
    }

    public void setOpportunite(Opportunite opportunite) {
        this.opportunite = opportunite;
    }

    public Opportunite getOpportuniteFromEdit() {
        return opportuniteFromEdit;
    }

    public void setOpportuniteFromEdit(Opportunite opportuniteFromEdit) {
        this.opportuniteFromEdit = opportuniteFromEdit;
    }

    public List<Opportunite> getAllOpportunites() {
        return allOpportunites;
    }

    public void setAllOpportunites(List<Opportunite> allOpportunites) {
        this.allOpportunites = allOpportunites;
    }

    public String save() {
        try {

            ejbOpportunite.insertOpportunite(this.opportunite);
            return "opportunites";
        } catch (Exception e) {
            return "new_opportunite";
        }

    }

    public String reset() {

        opportunite = new Opportunite();
        return "opportunites";
    }

    public void rowEdit(RowEditEvent event) {
        Opportunite opportuniteEdit = (Opportunite) event.getObject();
        ejbOpportunite.edit(opportuniteEdit);
    }

    public void rowEditCancel() {

    }
}
