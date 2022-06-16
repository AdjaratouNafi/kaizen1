/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import sn.accelsolution.dao.CommandeFacade;
import sn.accelsolution.dao.DetailleCommandeFacade;
import sn.accelsolution.dao.FournisseurFacade;
import sn.accelsolution.dao.ManagementFacade;
import sn.accelsolution.dao.MarchandiseFacade;
import sn.accelsolution.dao.PrixFacade;
import sn.accelsolution.dao.UtilisateurFacade;
import sn.accelsolution.entities.Management;

/**
 *
 * @author DELL
 */
@ManagedBean
@ViewScoped
public class TraitementManagementBean implements Serializable {

    /**
     * Creates a new instance of ContratRecrutBean
     */
    @EJB
    ManagementFacade ejbManagement;

    private Management management;

    @PostConstruct
    public void init() {

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

        management = new Management();
        management = (Management) session.getAttribute("myManagement");

    }

    public TraitementManagementBean() {
    }

    public Management getManagement() {  
        return management;
    }

    public void setManagement(Management management) {
        this.management = management;  
    }

    public String save() {       
        if (this.management.getTerminer() < 80) {
            this.management.setCouleur("rouge");
        }
        if (this.management.getTerminer() >= 80 && this.management.getTerminer() < 100) {
            this.management.setCouleur("orange");
        }
        if (this.management.getTerminer() == 100) {
            this.management.setCouleur("vert");
        }

        ejbManagement.edit(this.management);

        return "management";
    }

}
