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
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.event.RowEditEvent;
import sn.accelsolution.dao.ChantierFacade;
import sn.accelsolution.dao.ManagementFacade;
import sn.accelsolution.dao.MarcheFacade;
import sn.accelsolution.dao.PrestataireFacade;
import sn.accelsolution.dao.PrestataireprimFacade;
import sn.accelsolution.dao.UtilisateurFacade;
import sn.accelsolution.entities.Chantier;
import sn.accelsolution.entities.Management;
import sn.accelsolution.entities.Marche;
import sn.accelsolution.entities.Prestataireprim;    
import sn.accelsolution.entities.Utilisateur;   

/**
 *
 * @author DV7
 */
@ManagedBean
@ViewScoped
public class GestionprojetBean implements Serializable {   

    /**
     * Creates a new instance of ManagementBean
     */
    @EJB
    MarcheFacade ejbMarche;
    @EJB
    PrestataireFacade ejbPrestataire;
    @EJB
    ManagementFacade ejbManagement;
    @EJB
    ChantierFacade ejbChantier;
    @EJB
    PrestataireprimFacade ejbPrestatairePrim;
    @EJB
    UtilisateurFacade ejbUtilisateur;

    private Management management;
    private Marche marche;
    private Management managementFromEdit;
    private Management managementFromTraitement;

    private List<Management> allManagements = new ArrayList<>();
    private List<Marche> allMarches;
    private List<Chantier> allChantiers;
    private List<Utilisateur> allUsers;
    private List<Prestataireprim> allPrestataires;
    private String choixAffectation;

    @PostConstruct
    public void init() {
        //this.allManagements = ejbManagement.findAll();    
    }

    public GestionprojetBean() {
        management = new Management();
        managementFromEdit = new Management();
        allUsers = new ArrayList<>();
        allPrestataires = new ArrayList<>();
        marche = new Marche();
        managementFromTraitement = new Management();
    }

    public Management getManagementFromTraitement() {
        return managementFromTraitement;
    }

    public void setManagementFromTraitement(Management managementFromTraitement) {
        this.managementFromTraitement = managementFromTraitement;
    }

    public Marche getMarche() {
        return marche;
    }

    public void setMarche(Marche marche) {
        this.marche = marche;
    }

    public List<Utilisateur> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(List<Utilisateur> allUsers) {
        this.allUsers = allUsers;
    }

    public List<Prestataireprim> getAllPrestataires() {
        return allPrestataires;
    }

    public void setAllPrestataires(List<Prestataireprim> allPrestataires) {
        this.allPrestataires = allPrestataires;
    }

    public String getChoixAffectation() {
        return choixAffectation;
    }

    public void setChoixAffectation(String choixAffectation) {
        this.choixAffectation = choixAffectation;
    }

    public List<Chantier> getAllChantiers() {
        return allChantiers;
    }

    public void setAllChantiers(List<Chantier> allChantiers) {
        this.allChantiers = allChantiers;
    }

    public Management getManagement() {
        return management;
    }

    public void setManagement(Management management) {
        this.management = management;
    }

    public Management getManagementFromEdit() {
        return managementFromEdit;
    }

    public void setManagementFromEdit(Management managementFromEdit) {
        this.managementFromEdit = managementFromEdit;
    }

    public List<Management> getAllManagements() {
        return allManagements;
    }

    public void setAllManagements(List<Management> allManagements) {
        this.allManagements = allManagements;
    }

    public List<Marche> getAllMarches() {
        allMarches = ejbMarche.findAll();
        return allMarches;
    }

    public void setAllMarches(List<Marche> allMarches) {
        this.allMarches = allMarches;
    }

    public String save() {
        try {
            
            if (this.management.getTerminer() < 80) {
                this.management.setCouleur("rouge");
            }
            if (this.management.getTerminer() >= 80 && this.management.getTerminer() < 100) {
                this.management.setCouleur("orange");
            }
            if (this.management.getTerminer() == 100) {
                this.management.setCouleur("vert");
            }

            this.management.setEtat("En cours");
            
            ejbManagement.insertManagement(this.management, this.choixAffectation);

            return "management";
        } catch (Exception e) {
            return "new_management";
        }
    }

    public String rest() {
        this.management = new Management();
        return "management";
    }

    public void onEdit(RowEditEvent event) {

        Management managementEdit = (Management) event.getObject();
        if (managementEdit.getTerminer() < 80) {
            managementEdit.setCouleur("rouge");
        }
        if (managementEdit.getTerminer() >= 80 && managementEdit.getTerminer() < 100) {
            managementEdit.setCouleur("orange");
        }
        if (managementEdit.getTerminer() == 100) {
            managementEdit.setCouleur("vert");
        }
        ejbManagement.edit(managementEdit);
    }

    public void onCancel(RowEditEvent event) {

    }

    public void renderInfo() {
        this.allChantiers = ejbChantier.listOfChantierByMarche(this.management.getIdMarche().getIdMarche());
    }

    public void renderInfoo() {
        this.setChoixAffectation(this.getChoixAffectation());

        if (this.choixAffectation.equals("Prestataire")) {
            this.allUsers = new ArrayList<>();
            this.allPrestataires = ejbPrestatairePrim.findAll();
        }

        if (this.choixAffectation.equals("Personnel")) {
            this.allPrestataires = new ArrayList<>();
            this.allUsers = ejbUtilisateur.findAll();
        }

    }

    public void search() {  

        try {

            this.allManagements = ejbManagement.getManagementByMarche(this.marche);

        } catch (Exception e) {
        }
    }

    public String traiterManagement() { 
        
        HttpSession session1 = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        session1.setAttribute("myManagement", this.managementFromTraitement);
        return "traitement_management";
        
    }

}
