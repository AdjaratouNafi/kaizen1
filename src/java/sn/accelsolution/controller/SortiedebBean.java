/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.controller;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import sn.accelsolution.dao.ClientFacade;
import sn.accelsolution.dao.DetailledevisFacade;
import sn.accelsolution.dao.DevisFacade;
import sn.accelsolution.dao.DeviseFacade;
import sn.accelsolution.dao.MouvementdebourseFacade;
import sn.accelsolution.dao.NotificationFacade;
import sn.accelsolution.dao.TonpomcoefFacade;
import sn.accelsolution.dao.UtilisateurFacade;
import sn.accelsolution.entities.Actionmenu;
import sn.accelsolution.entities.Client;
import sn.accelsolution.entities.Detailledevis;
import sn.accelsolution.entities.Devis;
import sn.accelsolution.entities.Devise;
import sn.accelsolution.entities.Mouvementdebourse;
import sn.accelsolution.entities.Notification;
import sn.accelsolution.entities.Tonpomcoef;
import sn.accelsolution.entities.Utilisateur;
import sn.accelsolution.util.FactureUtil;
import sn.accelsolution.util.GenerationCodePdf;
import sn.accelsolution.util.MontantConverter;
import sn.accelsolution.util.UtilControleMenu;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DELL
 */
@ManagedBean
@ViewScoped
public class SortiedebBean implements Serializable {

    /**
     * Creates a new instance of ContratRecrutBean
     */
    @EJB
    UtilisateurFacade ejbUser;
    @EJB
    ClientFacade ejbClient;
    @EJB
    DevisFacade ejbDevis;
    @EJB
    DetailledevisFacade ejbDetailleDevis;
    @EJB
    NotificationFacade ejbNotification;
    @EJB
    TonpomcoefFacade ejbTonpomcoef;
    @EJB
    DeviseFacade ejbDevise;
    @EJB
    MouvementdebourseFacade ejbMouvementdebourse;

    private Mouvementdebourse mouvementdebourse;

    @PostConstruct
    public void init() {

//        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
//        UtilControleMenu utilControleMenu = new UtilControleMenu();
//
//        userNotif = new Utilisateur();
//        userNotif = (Utilisateur) session.getAttribute("user");
//
//        myllActionmenus = (List<Actionmenu>) session.getAttribute("allActionmenusFromTraitementDevis");
//
//        this.setCreerDevis(utilControleMenu.creerDevis(myllActionmenus));
//        this.setModifierDevis(utilControleMenu.modifierDevis(myllActionmenus));
//        this.setSupprimerDevis(utilControleMenu.supprimerDevis(myllActionmenus));
//        this.setConsulterDevis(utilControleMenu.consulterDevis(myllActionmenus));
//        this.setImprimerDevis(utilControleMenu.imprimerFacture(myllActionmenus));

    }

    public SortiedebBean() {
       mouvementdebourse = new Mouvementdebourse();
    }

    public Mouvementdebourse getMouvementdebourse() {
        return mouvementdebourse;
    }

    public void setMouvementdebourse(Mouvementdebourse mouvementdebourse) {
        this.mouvementdebourse = mouvementdebourse;
    }

    public String save() {            
        try {     
            ejbMouvementdebourse.create(this.mouvementdebourse);
            return "new_sortideb";
        } catch (Exception e) {
            return "new_sortideb";
        }

    }

    public String reset() {
       this.mouvementdebourse = new Mouvementdebourse();
        return "new_sortideb";
    }



}
