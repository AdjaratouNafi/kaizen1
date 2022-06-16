/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.controller;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import sn.accelsolution.dao.AcompteFacade;
import sn.accelsolution.dao.ActionmenuFacade;
import sn.accelsolution.dao.CommandeFacade;
import sn.accelsolution.dao.DetailleCommandeFacade;
import sn.accelsolution.dao.DroitaccesFacade;
import sn.accelsolution.dao.MenuFacade;
import sn.accelsolution.dao.UtilisateurFacade;
import sn.accelsolution.entities.Acompte;
import sn.accelsolution.entities.Actionmenu;
import sn.accelsolution.entities.Commande;
import sn.accelsolution.entities.DetailleCommande;
import sn.accelsolution.entities.Droitacces;
import sn.accelsolution.entities.Menu;
import sn.accelsolution.entities.Utilisateur;
import sn.accelsolution.util.CalculeHeure;
import sn.accelsolution.util.UtilCalculeDepenses;
import sn.accelsolution.util.UtilDepenses;
import sn.accelsolution.util.UtilTime;

/**
 *
 * @author DELL
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    /**
     * Creates a new instance of LoginBean
     */
    @EJB
    UtilisateurFacade ejbutilisateur;

    @EJB
    DroitaccesFacade ejbDroitacces;
    @EJB
    MenuFacade ejbMenu;
    @EJB
    ActionmenuFacade ejbActionmenu;
    @EJB
    AcompteFacade ejbAcompte;
    @EJB
    CommandeFacade ejbCommande;
    @EJB
    DetailleCommandeFacade ejbDetailleCommande;

    private String login;
    private String password;
    private String password1;
    private String password2;
    private Utilisateur utilisateur;
    private String echecconnexion;
    private List<Droitacces> allAcces;
    private List<Menu> allMenus;
    private List<Actionmenu> allActionmenus;
    private List<Actionmenu> allActionmenusFromTraitement;
    private List<Actionmenu> allActionmenusFromTraitementUser;
    private List<Actionmenu> allActionmenusFromTraitementRecrutement;
    private List<Actionmenu> allActionmenusFromTraitementStage;
    private List<Actionmenu> allActionmenusFromTraitementSalaire;
    private List<Actionmenu> allActionmenusFromTraitementPrets;
    private List<Actionmenu> allActionmenusFromTraitementIndemnites;
    private List<Actionmenu> allActionmenusFromTraitementClients;
    private List<Actionmenu> allActionmenusFromTraitementDevis;
    private List<Actionmenu> allActionmenusFromTraitementMarches;
    private List<Actionmenu> allActionmenusFromTraitementFactures;
    private List<Actionmenu> allActionmenusFromTraitementDecomptes;
    private List<Actionmenu> allActionmenusFromTraitementChantiers;
    private List<Actionmenu> allActionmenusFromTraitementPrestataires;
    private List<Actionmenu> allActionmenusFromTraitementMainOeuvres;
    private List<Actionmenu> allActionmenusFromTraitementAcomptes;
    private List<Actionmenu> allActionmenusFromTraitementProjetctManagements;
    private List<Actionmenu> allActionmenusFromTraitementDevises;
    private List<Actionmenu> allActionmenusFromTraitementUniteMesure;
    private List<Actionmenu> allActionmenusFromTraitementLotTechnique;
    private List<Actionmenu> allActionmenusFromTraitementCorpEtat;
    private List<Actionmenu> allActionmenusFromTraitementMateriels;
    private List<Actionmenu> allActionmenusFromTraitementFournisseurs;
    private List<Actionmenu> allActionmenusFromTraitementReferencielPrix;
    private List<Actionmenu> allActionmenusFromTraitementExpressionBesoins;
    private List<Actionmenu> allActionmenusFromTraitementBonCommande;
    private List<Actionmenu> allActionmenusFromTraitementLivraison;
    private List<Actionmenu> allActionmenusFromTraitementApproChantier;
    private List<Actionmenu> allActionmenusFromTraitementEntrepots;
    private List<Actionmenu> allActionmenusFromTraitementInventaire;
    private List<Actionmenu> allActionmenusFromTraitementMjs;
    private List<Actionmenu> allActionmenusFromTraitementSortieStock;
    private List<Actionmenu> allActionmenusFromTraitementHistoLivraison;
    private List<Actionmenu> allActionmenusFromTraitementOpportunites;
    private String consulterRole;
    private String modifierRole;
    private String supprimerRole;
    private String creerRole;
    private List<Acompte> listAcomptes;
    private List<Acompte> listAcomptesTest;
    private List<Commande> listCommande;
    private List<DetailleCommande> listDetailleCommande;

    @PostConstruct
    public void init() {

        this.listAcomptes = new ArrayList<>();
        this.listAcomptes.clear();
        this.listDetailleCommande = new ArrayList<>();
        this.listDetailleCommande.clear();
        this.listAcomptes = ejbAcompte.findAll();
        this.listDetailleCommande = ejbDetailleCommande.findAll();
        UtilCalculeDepenses ucd = new UtilCalculeDepenses();

        UtilDepenses utilDepenses1 = new UtilDepenses();
        utilDepenses1 = ucd.calculeOfAcompte(this.listAcomptes);
        System.out.println("Montant du mois de janvier: " + utilDepenses1.getMontantAcomptesJanvier());
        System.out.println("Montant du mois de février: " + utilDepenses1.getMontantAcomptesFevrier());

        UtilDepenses utilDepenses2 = new UtilDepenses();
        utilDepenses2 = ucd.calculeOfCommande(this.listDetailleCommande);
        System.out.println("Commande Montant du mois de janvier: " + utilDepenses2.getMontantBonCommandeJanvier());
        System.out.println("Commande Montant du mois de février: " + utilDepenses2.getMontantBonCommandeFevrier());
        System.out.println("Commande Montant du mois de Mars: " + utilDepenses2.getMontantBonCommandeMars());
    }

    public LoginBean() {
        utilisateur = new Utilisateur();
    }

    public List<DetailleCommande> getListDetailleCommande() {
        return listDetailleCommande;
    }

    public void setListDetailleCommande(List<DetailleCommande> listDetailleCommande) {
        this.listDetailleCommande = listDetailleCommande;
    }

    public List<Acompte> getListAcomptes() {
        return listAcomptes;
    }

    public List<Acompte> getListAcomptesTest() {
        return listAcomptesTest;
    }

    public void setListAcomptesTest(List<Acompte> listAcomptesTest) {
        this.listAcomptesTest = listAcomptesTest;
    }

    public void setListAcomptes(List<Acompte> listAcomptes) {
        this.listAcomptes = listAcomptes;
    }

    public List<Commande> getListCommande() {
        return listCommande;
    }

    public void setListCommande(List<Commande> listCommande) {
        this.listCommande = listCommande;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public List<Actionmenu> getAllActionmenusFromTraitement() {
        return allActionmenusFromTraitement;
    }

    public void setAllActionmenusFromTraitement(List<Actionmenu> allActionmenusFromTraitement) {
        this.allActionmenusFromTraitement = allActionmenusFromTraitement;
    }

    public String getConsulterRole() {
        return consulterRole;
    }

    public void setConsulterRole(String consulterRole) {
        this.consulterRole = consulterRole;
    }

    public String getModifierRole() {
        return modifierRole;
    }

    public void setModifierRole(String modifierRole) {
        this.modifierRole = modifierRole;
    }

    public String getSupprimerRole() {
        return supprimerRole;
    }

    public void setSupprimerRole(String supprimerRole) {
        this.supprimerRole = supprimerRole;
    }

    public String getCreerRole() {
        return creerRole;
    }

    public void setCreerRole(String creerRole) {
        this.creerRole = creerRole;
    }

    public List<Droitacces> getAllAcces() {
        return allAcces;
    }

    public void setAllAcces(List<Droitacces> allAcces) {
        this.allAcces = allAcces;
    }

    public List<Menu> getAllMenus() {
        return allMenus;
    }

    public void setAllMenus(List<Menu> allMenus) {
        this.allMenus = allMenus;
    }

    public List<Actionmenu> getAllActionmenus() {
        return allActionmenus;
    }

    public void setAllActionmenus(List<Actionmenu> allActionmenus) {
        this.allActionmenus = allActionmenus;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UtilisateurFacade getEjbutilisateur() {
        return ejbutilisateur;
    }

    public void setEjbutilisateur(UtilisateurFacade ejbutilisateur) {
        this.ejbutilisateur = ejbutilisateur;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getEchecconnexion() {
        return echecconnexion;
    }

    public void setEchecconnexion(String echecconnexion) {
        this.echecconnexion = echecconnexion;
    }

    public List<Actionmenu> getAllActionmenusFromTraitementUser() {
        return allActionmenusFromTraitementUser;
    }

    public void setAllActionmenusFromTraitementUser(List<Actionmenu> allActionmenusFromTraitementUser) {
        this.allActionmenusFromTraitementUser = allActionmenusFromTraitementUser;
    }

    public List<Actionmenu> getAllActionmenusFromTraitementRecrutement() {
        return allActionmenusFromTraitementRecrutement;
    }

    public void setAllActionmenusFromTraitementRecrutement(List<Actionmenu> allActionmenusFromTraitementRecrutement) {
        this.allActionmenusFromTraitementRecrutement = allActionmenusFromTraitementRecrutement;
    }

    public List<Actionmenu> getAllActionmenusFromTraitementStage() {
        return allActionmenusFromTraitementStage;
    }

    public void setAllActionmenusFromTraitementStage(List<Actionmenu> allActionmenusFromTraitementStage) {
        this.allActionmenusFromTraitementStage = allActionmenusFromTraitementStage;
    }

    public List<Actionmenu> getAllActionmenusFromTraitementSalaire() {
        return allActionmenusFromTraitementSalaire;
    }

    public void setAllActionmenusFromTraitementSalaire(List<Actionmenu> allActionmenusFromTraitementSalaire) {
        this.allActionmenusFromTraitementSalaire = allActionmenusFromTraitementSalaire;
    }

    public List<Actionmenu> getAllActionmenusFromTraitementPrets() {
        return allActionmenusFromTraitementPrets;
    }

    public void setAllActionmenusFromTraitementPrets(List<Actionmenu> allActionmenusFromTraitementPrets) {
        this.allActionmenusFromTraitementPrets = allActionmenusFromTraitementPrets;
    }

    public List<Actionmenu> getAllActionmenusFromTraitementIndemnites() {
        return allActionmenusFromTraitementIndemnites;
    }

    public void setAllActionmenusFromTraitementIndemnites(List<Actionmenu> allActionmenusFromTraitementIndemnites) {
        this.allActionmenusFromTraitementIndemnites = allActionmenusFromTraitementIndemnites;
    }

    public List<Actionmenu> getAllActionmenusFromTraitementClients() {
        return allActionmenusFromTraitementClients;
    }

    public void setAllActionmenusFromTraitementClients(List<Actionmenu> allActionmenusFromTraitementClients) {
        this.allActionmenusFromTraitementClients = allActionmenusFromTraitementClients;
    }

    public List<Actionmenu> getAllActionmenusFromTraitementDevis() {
        return allActionmenusFromTraitementDevis;
    }

    public void setAllActionmenusFromTraitementDevis(List<Actionmenu> allActionmenusFromTraitementDevis) {
        this.allActionmenusFromTraitementDevis = allActionmenusFromTraitementDevis;
    }

    public List<Actionmenu> getAllActionmenusFromTraitementMarches() {
        return allActionmenusFromTraitementMarches;
    }

    public void setAllActionmenusFromTraitementMarches(List<Actionmenu> allActionmenusFromTraitementMarches) {
        this.allActionmenusFromTraitementMarches = allActionmenusFromTraitementMarches;
    }

    public List<Actionmenu> getAllActionmenusFromTraitementFactures() {
        return allActionmenusFromTraitementFactures;
    }

    public void setAllActionmenusFromTraitementFactures(List<Actionmenu> allActionmenusFromTraitementFactures) {
        this.allActionmenusFromTraitementFactures = allActionmenusFromTraitementFactures;
    }

    public List<Actionmenu> getAllActionmenusFromTraitementDecomptes() {
        return allActionmenusFromTraitementDecomptes;
    }

    public void setAllActionmenusFromTraitementDecomptes(List<Actionmenu> allActionmenusFromTraitementDecomptes) {
        this.allActionmenusFromTraitementDecomptes = allActionmenusFromTraitementDecomptes;
    }

    public List<Actionmenu> getAllActionmenusFromTraitementChantiers() {
        return allActionmenusFromTraitementChantiers;
    }

    public void setAllActionmenusFromTraitementChantiers(List<Actionmenu> allActionmenusFromTraitementChantiers) {
        this.allActionmenusFromTraitementChantiers = allActionmenusFromTraitementChantiers;
    }

    public List<Actionmenu> getAllActionmenusFromTraitementPrestataires() {
        return allActionmenusFromTraitementPrestataires;
    }

    public void setAllActionmenusFromTraitementPrestataires(List<Actionmenu> allActionmenusFromTraitementPrestataires) {
        this.allActionmenusFromTraitementPrestataires = allActionmenusFromTraitementPrestataires;
    }

    public List<Actionmenu> getAllActionmenusFromTraitementMainOeuvres() {
        return allActionmenusFromTraitementMainOeuvres;
    }

    public void setAllActionmenusFromTraitementMainOeuvres(List<Actionmenu> allActionmenusFromTraitementMainOeuvres) {
        this.allActionmenusFromTraitementMainOeuvres = allActionmenusFromTraitementMainOeuvres;
    }

    public List<Actionmenu> getAllActionmenusFromTraitementAcomptes() {
        return allActionmenusFromTraitementAcomptes;
    }

    public void setAllActionmenusFromTraitementAcomptes(List<Actionmenu> allActionmenusFromTraitementAcomptes) {
        this.allActionmenusFromTraitementAcomptes = allActionmenusFromTraitementAcomptes;
    }

    public List<Actionmenu> getAllActionmenusFromTraitementProjetctManagements() {
        return allActionmenusFromTraitementProjetctManagements;
    }

    public void setAllActionmenusFromTraitementProjetctManagements(List<Actionmenu> allActionmenusFromTraitementProjetctManagements) {
        this.allActionmenusFromTraitementProjetctManagements = allActionmenusFromTraitementProjetctManagements;
    }

    public List<Actionmenu> getAllActionmenusFromTraitementDevises() {
        return allActionmenusFromTraitementDevises;
    }

    public void setAllActionmenusFromTraitementDevises(List<Actionmenu> allActionmenusFromTraitementDevises) {
        this.allActionmenusFromTraitementDevises = allActionmenusFromTraitementDevises;
    }

    public List<Actionmenu> getAllActionmenusFromTraitementUniteMesure() {
        return allActionmenusFromTraitementUniteMesure;
    }

    public void setAllActionmenusFromTraitementUniteMesure(List<Actionmenu> allActionmenusFromTraitementUniteMesure) {
        this.allActionmenusFromTraitementUniteMesure = allActionmenusFromTraitementUniteMesure;
    }

    public List<Actionmenu> getAllActionmenusFromTraitementLotTechnique() {
        return allActionmenusFromTraitementLotTechnique;
    }

    public void setAllActionmenusFromTraitementLotTechnique(List<Actionmenu> allActionmenusFromTraitementLotTechnique) {
        this.allActionmenusFromTraitementLotTechnique = allActionmenusFromTraitementLotTechnique;
    }

    public List<Actionmenu> getAllActionmenusFromTraitementCorpEtat() {
        return allActionmenusFromTraitementCorpEtat;
    }

    public void setAllActionmenusFromTraitementCorpEtat(List<Actionmenu> allActionmenusFromTraitementCorpEtat) {
        this.allActionmenusFromTraitementCorpEtat = allActionmenusFromTraitementCorpEtat;
    }

    public List<Actionmenu> getAllActionmenusFromTraitementMateriels() {
        return allActionmenusFromTraitementMateriels;
    }

    public void setAllActionmenusFromTraitementMateriels(List<Actionmenu> allActionmenusFromTraitementMateriels) {
        this.allActionmenusFromTraitementMateriels = allActionmenusFromTraitementMateriels;
    }

    public List<Actionmenu> getAllActionmenusFromTraitementFournisseurs() {
        return allActionmenusFromTraitementFournisseurs;
    }

    public void setAllActionmenusFromTraitementFournisseurs(List<Actionmenu> allActionmenusFromTraitementFournisseurs) {
        this.allActionmenusFromTraitementFournisseurs = allActionmenusFromTraitementFournisseurs;
    }

    public List<Actionmenu> getAllActionmenusFromTraitementReferencielPrix() {
        return allActionmenusFromTraitementReferencielPrix;
    }

    public void setAllActionmenusFromTraitementReferencielPrix(List<Actionmenu> allActionmenusFromTraitementReferencielPrix) {
        this.allActionmenusFromTraitementReferencielPrix = allActionmenusFromTraitementReferencielPrix;
    }

    public List<Actionmenu> getAllActionmenusFromTraitementExpressionBesoins() {
        return allActionmenusFromTraitementExpressionBesoins;
    }

    public void setAllActionmenusFromTraitementExpressionBesoins(List<Actionmenu> allActionmenusFromTraitementExpressionBesoins) {
        this.allActionmenusFromTraitementExpressionBesoins = allActionmenusFromTraitementExpressionBesoins;
    }

    public List<Actionmenu> getAllActionmenusFromTraitementBonCommande() {
        return allActionmenusFromTraitementBonCommande;
    }

    public void setAllActionmenusFromTraitementBonCommande(List<Actionmenu> allActionmenusFromTraitementBonCommande) {
        this.allActionmenusFromTraitementBonCommande = allActionmenusFromTraitementBonCommande;
    }

    public List<Actionmenu> getAllActionmenusFromTraitementLivraison() {
        return allActionmenusFromTraitementLivraison;
    }

    public void setAllActionmenusFromTraitementLivraison(List<Actionmenu> allActionmenusFromTraitementLivraison) {
        this.allActionmenusFromTraitementLivraison = allActionmenusFromTraitementLivraison;
    }

    public List<Actionmenu> getAllActionmenusFromTraitementApproChantier() {
        return allActionmenusFromTraitementApproChantier;
    }

    public void setAllActionmenusFromTraitementApproChantier(List<Actionmenu> allActionmenusFromTraitementApproChantier) {
        this.allActionmenusFromTraitementApproChantier = allActionmenusFromTraitementApproChantier;
    }

    public List<Actionmenu> getAllActionmenusFromTraitementEntrepots() {
        return allActionmenusFromTraitementEntrepots;
    }

    public void setAllActionmenusFromTraitementEntrepots(List<Actionmenu> allActionmenusFromTraitementEntrepots) {
        this.allActionmenusFromTraitementEntrepots = allActionmenusFromTraitementEntrepots;
    }

    public List<Actionmenu> getAllActionmenusFromTraitementInventaire() {
        return allActionmenusFromTraitementInventaire;
    }

    public void setAllActionmenusFromTraitementInventaire(List<Actionmenu> allActionmenusFromTraitementInventaire) {
        this.allActionmenusFromTraitementInventaire = allActionmenusFromTraitementInventaire;
    }

    public List<Actionmenu> getAllActionmenusFromTraitementMjs() {
        return allActionmenusFromTraitementMjs;
    }

    public void setAllActionmenusFromTraitementMjs(List<Actionmenu> allActionmenusFromTraitementMjs) {
        this.allActionmenusFromTraitementMjs = allActionmenusFromTraitementMjs;
    }

    public List<Actionmenu> getAllActionmenusFromTraitementSortieStock() {
        return allActionmenusFromTraitementSortieStock;
    }

    public void setAllActionmenusFromTraitementSortieStock(List<Actionmenu> allActionmenusFromTraitementSortieStock) {
        this.allActionmenusFromTraitementSortieStock = allActionmenusFromTraitementSortieStock;
    }

    public List<Actionmenu> getAllActionmenusFromTraitementHistoLivraison() {
        return allActionmenusFromTraitementHistoLivraison;
    }

    public void setAllActionmenusFromTraitementHistoLivraison(List<Actionmenu> allActionmenusFromTraitementHistoLivraison) {
        this.allActionmenusFromTraitementHistoLivraison = allActionmenusFromTraitementHistoLivraison;
    }

    public List<Actionmenu> getAllActionmenusFromTraitementOpportunites() {
        return allActionmenusFromTraitementOpportunites;
    }

    public void setAllActionmenusFromTraitementOpportunites(List<Actionmenu> allActionmenusFromTraitementOpportunites) {
        this.allActionmenusFromTraitementOpportunites = allActionmenusFromTraitementOpportunites;
    }

    public String connexion() {

        try {

            this.utilisateur = ejbutilisateur.connexion(this.login, this.password);

            if ((this.utilisateur != null) && (this.utilisateur.getFirstconnection().equals("Oui"))) {
                utilisateur = this.getUtilisateur();
                this.allAcces = new ArrayList<>();
                this.allMenus = new ArrayList<>();
                this.allActionmenus = new ArrayList<>();
                this.allActionmenusFromTraitement = new ArrayList<>();
                this.allActionmenusFromTraitementUser = new ArrayList<>();
                this.allActionmenusFromTraitementRecrutement = new ArrayList<>();
                this.allActionmenusFromTraitementStage = new ArrayList<>();
                this.allActionmenusFromTraitementSalaire = new ArrayList<>();
                this.allActionmenusFromTraitementPrets = new ArrayList<>();
                this.allActionmenusFromTraitementIndemnites = new ArrayList<>();
                this.allActionmenusFromTraitementClients = new ArrayList<>();
                this.allActionmenusFromTraitementDevis = new ArrayList<>();
                this.allActionmenusFromTraitementMarches = new ArrayList<>();
                this.allActionmenusFromTraitementFactures = new ArrayList<>();
                this.allActionmenusFromTraitementDecomptes = new ArrayList<>();
                this.allActionmenusFromTraitementChantiers = new ArrayList<>();
                this.allActionmenusFromTraitementPrestataires = new ArrayList<>();
                this.allActionmenusFromTraitementMainOeuvres = new ArrayList<>();
                this.allActionmenusFromTraitementAcomptes = new ArrayList<>();
                this.allActionmenusFromTraitementProjetctManagements = new ArrayList<>();
                this.allActionmenusFromTraitementDevises = new ArrayList<>();
                this.allActionmenusFromTraitementUniteMesure = new ArrayList<>();
                this.allActionmenusFromTraitementLotTechnique = new ArrayList<>();
                this.allActionmenusFromTraitementCorpEtat = new ArrayList<>();
                this.allActionmenusFromTraitementMateriels = new ArrayList<>();
                this.allActionmenusFromTraitementFournisseurs = new ArrayList<>();
                this.allActionmenusFromTraitementReferencielPrix = new ArrayList<>();
                this.allActionmenusFromTraitementExpressionBesoins = new ArrayList<>();
                this.allActionmenusFromTraitementBonCommande = new ArrayList<>();
                this.allActionmenusFromTraitementLivraison = new ArrayList<>();
                this.allActionmenusFromTraitementApproChantier = new ArrayList<>();

                this.allActionmenusFromTraitementEntrepots = new ArrayList<>();
                this.allActionmenusFromTraitementInventaire = new ArrayList<>();
                this.allActionmenusFromTraitementMjs = new ArrayList<>();
                this.allActionmenusFromTraitementSortieStock = new ArrayList<>();
                this.allActionmenusFromTraitementHistoLivraison = new ArrayList<>();
                this.allActionmenusFromTraitementOpportunites = new ArrayList<>();

                this.allAcces = ejbDroitacces.listOfDroitByUser(utilisateur);

                this.allMenus = ejbMenu.findAll();

                this.allActionmenus = ejbActionmenu.findAll();
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

                /*Controle roles*/
                for (Droitacces myDroit1 : this.allAcces) {
                    System.out.println("Droit: " + myDroit1.getModule());

                    for (Menu myMenu1 : this.allMenus) {
                        if (myDroit1.getIdPrivilege() == myMenu1.getIdPrivilege().getIdPrivilege()) {
                            System.out.println("Menu: " + myMenu1.getLibelemenu());
                            if (myMenu1.getLibelemenu().equals("Gestion des rôles")) {
                                for (Actionmenu myActionmenu1 : this.allActionmenus) {
                                    if (myMenu1.getIdMenu() == myActionmenu1.getIdMenu().getIdMenu()) {
                                        this.allActionmenusFromTraitement.add(myActionmenu1);
                                    }

                                }
                            }

                        }

                    }
                }
                /*Fin Controle roles*/

                /*Controle users*/
                for (Droitacces myDroit2 : this.allAcces) {
                    System.out.println("Droit: " + myDroit2.getModule());

                    for (Menu myMenu2 : this.allMenus) {
                        if (myDroit2.getIdPrivilege() == myMenu2.getIdPrivilege().getIdPrivilege()) {
                            System.out.println("Menu: " + myMenu2.getLibelemenu());
                            if (myMenu2.getLibelemenu().equals("Gestion des utilisateurs")) {
                                for (Actionmenu myActionmenu2 : this.allActionmenus) {
                                    if (myMenu2.getIdMenu() == myActionmenu2.getIdMenu().getIdMenu()) {
                                        this.allActionmenusFromTraitementUser.add(myActionmenu2);
                                    }

                                }
                            }

                        }

                    }
                }
                /*Fin Controle users*/

                /*Controle Recrutement*/
                for (Droitacces myDroit3 : this.allAcces) {
                    System.out.println("Droit: " + myDroit3.getModule());

                    for (Menu myMenu3 : this.allMenus) {
                        if (myDroit3.getIdPrivilege() == myMenu3.getIdPrivilege().getIdPrivilege()) {
                            System.out.println("Menu: " + myMenu3.getLibelemenu());
                            if (myMenu3.getLibelemenu().equals("Recrutement")) {
                                for (Actionmenu myActionmenu3 : this.allActionmenus) {
                                    if (myMenu3.getIdMenu() == myActionmenu3.getIdMenu().getIdMenu()) {
                                        this.allActionmenusFromTraitementRecrutement.add(myActionmenu3);
                                    }

                                }
                            }

                        }

                    }
                }
                /*Fin Controle Recrutement*/

                /*Controle Stage*/
                for (Droitacces myDroit4 : this.allAcces) {
                    System.out.println("Droit: " + myDroit4.getModule());

                    for (Menu myMenu4 : this.allMenus) {
                        if (myDroit4.getIdPrivilege() == myMenu4.getIdPrivilege().getIdPrivilege()) {
                            System.out.println("Menu: " + myMenu4.getLibelemenu());
                            if (myMenu4.getLibelemenu().equals("Stage")) {
                                for (Actionmenu myActionmenu4 : this.allActionmenus) {
                                    if (myMenu4.getIdMenu() == myActionmenu4.getIdMenu().getIdMenu()) {
                                        this.allActionmenusFromTraitementStage.add(myActionmenu4);
                                    }

                                }
                            }

                        }

                    }
                }
                /*Fin Controle Stage*/

                /*Controle Salaire*/
                for (Droitacces myDroit5 : this.allAcces) {
                    System.out.println("Droit: " + myDroit5.getModule());

                    for (Menu myMenu5 : this.allMenus) {
                        if (myDroit5.getIdPrivilege() == myMenu5.getIdPrivilege().getIdPrivilege()) {
                            System.out.println("Menu: " + myMenu5.getLibelemenu());
                            if (myMenu5.getLibelemenu().equals("Salaire")) {
                                for (Actionmenu myActionmenu5 : this.allActionmenus) {
                                    if (myMenu5.getIdMenu() == myActionmenu5.getIdMenu().getIdMenu()) {
                                        this.allActionmenusFromTraitementSalaire.add(myActionmenu5);
                                    }

                                }
                            }

                        }

                    }
                }
                /*Fin Controle Salaire*/

                /*Controle Prêts*/
                for (Droitacces myDroit6 : this.allAcces) {
                    System.out.println("Droit: " + myDroit6.getModule());

                    for (Menu myMenu6 : this.allMenus) {
                        if (myDroit6.getIdPrivilege() == myMenu6.getIdPrivilege().getIdPrivilege()) {
                            System.out.println("Menu: " + myMenu6.getLibelemenu());
                            if (myMenu6.getLibelemenu().equals("Prêts")) {
                                for (Actionmenu myActionmenu6 : this.allActionmenus) {
                                    if (myMenu6.getIdMenu() == myActionmenu6.getIdMenu().getIdMenu()) {
                                        this.allActionmenusFromTraitementPrets.add(myActionmenu6);
                                    }

                                }
                            }

                        }

                    }
                }
                /*Fin Controle Prêts*/

                /*Controle Indemnités*/
                for (Droitacces myDroit7 : this.allAcces) {
                    System.out.println("Droit: " + myDroit7.getModule());

                    for (Menu myMenu7 : this.allMenus) {
                        if (myDroit7.getIdPrivilege() == myMenu7.getIdPrivilege().getIdPrivilege()) {
                            System.out.println("Menu: " + myMenu7.getLibelemenu());
                            if (myMenu7.getLibelemenu().equals("Indemnités")) {
                                for (Actionmenu myActionmenu7 : this.allActionmenus) {
                                    if (myMenu7.getIdMenu() == myActionmenu7.getIdMenu().getIdMenu()) {
                                        this.allActionmenusFromTraitementIndemnites.add(myActionmenu7);
                                    }

                                }
                            }

                        }

                    }
                }
                /*Fin Controle Indemnités*/
                /*Controle Clients*/
                for (Droitacces myDroit8 : this.allAcces) {
                    System.out.println("Droit: " + myDroit8.getModule());

                    for (Menu myMenu8 : this.allMenus) {
                        if (myDroit8.getIdPrivilege() == myMenu8.getIdPrivilege().getIdPrivilege()) {
                            System.out.println("Menu: " + myMenu8.getLibelemenu());
                            if (myMenu8.getLibelemenu().equals("Clients")) {
                                for (Actionmenu myActionmenu8 : this.allActionmenus) {
                                    if (myMenu8.getIdMenu() == myActionmenu8.getIdMenu().getIdMenu()) {
                                        this.allActionmenusFromTraitementClients.add(myActionmenu8);
                                    }

                                }
                            }

                        }

                    }
                }
                /*Fin Controle Clients*/
                /*Controle devis*/
                for (Droitacces myDroit9 : this.allAcces) {
                    System.out.println("Droit: " + myDroit9.getModule());

                    for (Menu myMenu9 : this.allMenus) {
                        if (myDroit9.getIdPrivilege() == myMenu9.getIdPrivilege().getIdPrivilege()) {
                            System.out.println("Menu: " + myMenu9.getLibelemenu());
                            if (myMenu9.getLibelemenu().equals("Devis")) {
                                for (Actionmenu myActionmenu9 : this.allActionmenus) {
                                    if (myMenu9.getIdMenu() == myActionmenu9.getIdMenu().getIdMenu()) {
                                        this.allActionmenusFromTraitementDevis.add(myActionmenu9);
                                    }

                                }
                            }

                        }

                    }
                }
                /*Fin Controle Devis*/
                /*Controle Marches*/
                for (Droitacces myDroit10 : this.allAcces) {
                    System.out.println("Droit: " + myDroit10.getModule());

                    for (Menu myMenu10 : this.allMenus) {
                        if (myDroit10.getIdPrivilege() == myMenu10.getIdPrivilege().getIdPrivilege()) {
                            System.out.println("Menu: " + myMenu10.getLibelemenu());
                            if (myMenu10.getLibelemenu().equals("Marchés")) {
                                for (Actionmenu myActionmenu10 : this.allActionmenus) {
                                    if (myMenu10.getIdMenu() == myActionmenu10.getIdMenu().getIdMenu()) {
                                        this.allActionmenusFromTraitementMarches.add(myActionmenu10);
                                    }

                                }
                            }

                        }

                    }
                }
                /*Fin Controle Marches*/
                /*Controle Factures*/
                for (Droitacces myDroit11 : this.allAcces) {
                    System.out.println("Droit: " + myDroit11.getModule());

                    for (Menu myMenu11 : this.allMenus) {
                        if (myDroit11.getIdPrivilege() == myMenu11.getIdPrivilege().getIdPrivilege()) {
                            System.out.println("Menu: " + myMenu11.getLibelemenu());
                            if (myMenu11.getLibelemenu().equals("Factures")) {
                                for (Actionmenu myActionmenu11 : this.allActionmenus) {
                                    if (myMenu11.getIdMenu() == myActionmenu11.getIdMenu().getIdMenu()) {
                                        this.allActionmenusFromTraitementFactures.add(myActionmenu11);
                                    }

                                }
                            }

                        }

                    }
                }
                /*Fin Controle Factures*/
                /*Controle Décomptes*/
                for (Droitacces myDroit12 : this.allAcces) {
                    System.out.println("Droit: " + myDroit12.getModule());

                    for (Menu myMenu12 : this.allMenus) {
                        if (myDroit12.getIdPrivilege() == myMenu12.getIdPrivilege().getIdPrivilege()) {
                            System.out.println("Menu: " + myMenu12.getLibelemenu());
                            if (myMenu12.getLibelemenu().equals("Décomptes")) {
                                for (Actionmenu myActionmenu12 : this.allActionmenus) {
                                    if (myMenu12.getIdMenu() == myActionmenu12.getIdMenu().getIdMenu()) {
                                        this.allActionmenusFromTraitementDecomptes.add(myActionmenu12);
                                    }

                                }
                            }

                        }

                    }
                }
                /*Fin Controle Décomptes*/
                /*Controle Chantiers*/
                for (Droitacces myDroit13 : this.allAcces) {
                    System.out.println("Droit: " + myDroit13.getModule());

                    for (Menu myMenu13 : this.allMenus) {
                        if (myDroit13.getIdPrivilege() == myMenu13.getIdPrivilege().getIdPrivilege()) {
                            System.out.println("Menu: " + myMenu13.getLibelemenu());
                            if (myMenu13.getLibelemenu().equals("Chantiers")) {
                                for (Actionmenu myActionmenu13 : this.allActionmenus) {
                                    if (myMenu13.getIdMenu() == myActionmenu13.getIdMenu().getIdMenu()) {
                                        this.allActionmenusFromTraitementChantiers.add(myActionmenu13);
                                    }

                                }
                            }

                        }

                    }
                }
                /*Fin Controle Chantiers*/
                /*Controle Liste des prestataires*/
                for (Droitacces myDroit14 : this.allAcces) {
                    System.out.println("Droit: " + myDroit14.getModule());

                    for (Menu myMenu14 : this.allMenus) {
                        if (myDroit14.getIdPrivilege() == myMenu14.getIdPrivilege().getIdPrivilege()) {
                            System.out.println("Menu: " + myMenu14.getLibelemenu());
                            if (myMenu14.getLibelemenu().equals("Liste des prestataires")) {
                                for (Actionmenu myActionmenu14 : this.allActionmenus) {
                                    if (myMenu14.getIdMenu() == myActionmenu14.getIdMenu().getIdMenu()) {
                                        this.allActionmenusFromTraitementPrestataires.add(myActionmenu14);
                                    }

                                }
                            }

                        }

                    }
                }
                /*Fin Controle Liste des prestataires*/
                /*Controle Liste des Referenciel Main-d'oeuvre*/
                for (Droitacces myDroit15 : this.allAcces) {
                    System.out.println("Droit: " + myDroit15.getModule());

                    for (Menu myMenu15 : this.allMenus) {
                        if (myDroit15.getIdPrivilege() == myMenu15.getIdPrivilege().getIdPrivilege()) {
                            System.out.println("Menu: " + myMenu15.getLibelemenu());
                            if (myMenu15.getLibelemenu().equals("Referenciel Main-d'oeuvre")) {
                                for (Actionmenu myActionmenu15 : this.allActionmenus) {
                                    if (myMenu15.getIdMenu() == myActionmenu15.getIdMenu().getIdMenu()) {
                                        this.allActionmenusFromTraitementMainOeuvres.add(myActionmenu15);
                                    }

                                }
                            }

                        }

                    }
                }
                /*Fin Controle Liste des Referenciel Main-d'oeuvre*/
                /*Controle Liste des Acomptes*/
                for (Droitacces myDroit16 : this.allAcces) {
                    System.out.println("Droit: " + myDroit16.getModule());

                    for (Menu myMenu16 : this.allMenus) {
                        if (myDroit16.getIdPrivilege() == myMenu16.getIdPrivilege().getIdPrivilege()) {
                            System.out.println("Menu: " + myMenu16.getLibelemenu());
                            if (myMenu16.getLibelemenu().equals("Acomptes")) {
                                for (Actionmenu myActionmenu16 : this.allActionmenus) {
                                    if (myMenu16.getIdMenu() == myActionmenu16.getIdMenu().getIdMenu()) {
                                        this.allActionmenusFromTraitementAcomptes.add(myActionmenu16);
                                    }

                                }
                            }

                        }

                    }
                }
                /*Fin Controle Liste des Acomptes*/
                /*Controle Liste des Project Management*/
                for (Droitacces myDroit17 : this.allAcces) {
                    System.out.println("Droit: " + myDroit17.getModule());

                    for (Menu myMenu17 : this.allMenus) {
                        if (myDroit17.getIdPrivilege() == myMenu17.getIdPrivilege().getIdPrivilege()) {
                            System.out.println("Menu: " + myMenu17.getLibelemenu());
                            if (myMenu17.getLibelemenu().equals("Project Management")) {
                                for (Actionmenu myActionmenu17 : this.allActionmenus) {
                                    if (myMenu17.getIdMenu() == myActionmenu17.getIdMenu().getIdMenu()) {
                                        this.allActionmenusFromTraitementAcomptes.add(myActionmenu17);
                                    }

                                }
                            }

                        }

                    }
                }
                /*Fin Controle Liste des Project Management*/
                /*Controle Devises*/
                for (Droitacces myDroit18 : this.allAcces) {
                    System.out.println("Droit: " + myDroit18.getModule());

                    for (Menu myMenu18 : this.allMenus) {
                        if (myDroit18.getIdPrivilege() == myMenu18.getIdPrivilege().getIdPrivilege()) {
                            System.out.println("Menu: " + myMenu18.getLibelemenu());
                            if (myMenu18.getLibelemenu().equals("Devises")) {
                                for (Actionmenu myActionmenu18 : this.allActionmenus) {
                                    if (myMenu18.getIdMenu() == myActionmenu18.getIdMenu().getIdMenu()) {
                                        this.allActionmenusFromTraitementDevises.add(myActionmenu18);
                                    }

                                }
                            }

                        }

                    }
                }
                /*Fin Controle Devises*/
                /*Controle Unite de mesure*/
                for (Droitacces myDroit19 : this.allAcces) {
                    System.out.println("Droit: " + myDroit19.getModule());

                    for (Menu myMenu19 : this.allMenus) {
                        if (myDroit19.getIdPrivilege() == myMenu19.getIdPrivilege().getIdPrivilege()) {
                            System.out.println("Menu: " + myMenu19.getLibelemenu());
                            if (myMenu19.getLibelemenu().equals("Unite de mesure")) {
                                for (Actionmenu myActionmenu19 : this.allActionmenus) {
                                    if (myMenu19.getIdMenu() == myActionmenu19.getIdMenu().getIdMenu()) {
                                        this.allActionmenusFromTraitementUniteMesure.add(myActionmenu19);
                                    }

                                }
                            }

                        }

                    }
                }
                /*Fin Controle Unite de mesure*/
                /*Controle Lot technique*/
                for (Droitacces myDroit20 : this.allAcces) {
                    System.out.println("Droit: " + myDroit20.getModule());

                    for (Menu myMenu20 : this.allMenus) {
                        if (myDroit20.getIdPrivilege() == myMenu20.getIdPrivilege().getIdPrivilege()) {
                            System.out.println("Menu: " + myMenu20.getLibelemenu());
                            if (myMenu20.getLibelemenu().equals("Lot technique")) {
                                for (Actionmenu myActionmenu20 : this.allActionmenus) {
                                    if (myMenu20.getIdMenu() == myActionmenu20.getIdMenu().getIdMenu()) {
                                        this.allActionmenusFromTraitementLotTechnique.add(myActionmenu20);
                                    }

                                }
                            }

                        }

                    }
                }
                /*Fin Controle Lot technique*/
                /*Controle Corps d'etat*/
                for (Droitacces myDroit21 : this.allAcces) {
                    System.out.println("Droit: " + myDroit21.getModule());

                    for (Menu myMenu21 : this.allMenus) {
                        if (myDroit21.getIdPrivilege() == myMenu21.getIdPrivilege().getIdPrivilege()) {
                            System.out.println("Menu: " + myMenu21.getLibelemenu());
                            if (myMenu21.getLibelemenu().equals("Corps d'etat")) {
                                for (Actionmenu myActionmenu21 : this.allActionmenus) {
                                    if (myMenu21.getIdMenu() == myActionmenu21.getIdMenu().getIdMenu()) {
                                        this.allActionmenusFromTraitementCorpEtat.add(myActionmenu21);
                                    }

                                }
                            }

                        }

                    }
                }
                /*Fin Controle Corps d'etat*/
                /*Controle Materiels*/
                for (Droitacces myDroit22 : this.allAcces) {
                    System.out.println("Droit: " + myDroit22.getModule());

                    for (Menu myMenu22 : this.allMenus) {
                        if (myDroit22.getIdPrivilege() == myMenu22.getIdPrivilege().getIdPrivilege()) {
                            System.out.println("Menu: " + myMenu22.getLibelemenu());
                            if (myMenu22.getLibelemenu().equals("Materiels")) {
                                for (Actionmenu myActionmenu22 : this.allActionmenus) {
                                    if (myMenu22.getIdMenu() == myActionmenu22.getIdMenu().getIdMenu()) {
                                        this.allActionmenusFromTraitementMateriels.add(myActionmenu22);
                                    }

                                }
                            }

                        }

                    }
                }
                /*Fin Controle Materiels*/
                /*Controle Fournisseurs*/
                for (Droitacces myDroit23 : this.allAcces) {
                    System.out.println("Droit: " + myDroit23.getModule());

                    for (Menu myMenu23 : this.allMenus) {
                        if (myDroit23.getIdPrivilege() == myMenu23.getIdPrivilege().getIdPrivilege()) {
                            System.out.println("Menu: " + myMenu23.getLibelemenu());
                            if (myMenu23.getLibelemenu().equals("Fournisseurs")) {
                                for (Actionmenu myActionmenu23 : this.allActionmenus) {
                                    if (myMenu23.getIdMenu() == myActionmenu23.getIdMenu().getIdMenu()) {
                                        this.allActionmenusFromTraitementFournisseurs.add(myActionmenu23);
                                    }

                                }
                            }

                        }

                    }
                }
                /*Fin Controle Fournisseurs*/
                /*Controle Referenciel de prix*/
                for (Droitacces myDroit24 : this.allAcces) {
                    System.out.println("Droit: " + myDroit24.getModule());

                    for (Menu myMenu24 : this.allMenus) {
                        if (myDroit24.getIdPrivilege() == myMenu24.getIdPrivilege().getIdPrivilege()) {
                            System.out.println("Menu: " + myMenu24.getLibelemenu());
                            if (myMenu24.getLibelemenu().equals("Referenciel de prix")) {
                                for (Actionmenu myActionmenu24 : this.allActionmenus) {
                                    if (myMenu24.getIdMenu() == myActionmenu24.getIdMenu().getIdMenu()) {
                                        this.allActionmenusFromTraitementReferencielPrix.add(myActionmenu24);
                                    }

                                }
                            }

                        }

                    }
                }
                /*Fin Controle Referenciel de prix*/
                /*Controle Expression des besoins*/
                for (Droitacces myDroit25 : this.allAcces) {
                    System.out.println("Droit: " + myDroit25.getModule());

                    for (Menu myMenu25 : this.allMenus) {
                        if (myDroit25.getIdPrivilege() == myMenu25.getIdPrivilege().getIdPrivilege()) {
                            System.out.println("Menu: " + myMenu25.getLibelemenu());
                            if (myMenu25.getLibelemenu().equals("Expression des besoins")) {
                                for (Actionmenu myActionmenu25 : this.allActionmenus) {
                                    if (myMenu25.getIdMenu() == myActionmenu25.getIdMenu().getIdMenu()) {
                                        this.allActionmenusFromTraitementExpressionBesoins.add(myActionmenu25);
                                    }

                                }
                            }

                        }

                    }
                }
                /*Fin Controle Expression des besoins*/
                /*Controle Bon de commande*/
                for (Droitacces myDroit26 : this.allAcces) {
                    System.out.println("Droit: " + myDroit26.getModule());

                    for (Menu myMenu26 : this.allMenus) {
                        if (myDroit26.getIdPrivilege() == myMenu26.getIdPrivilege().getIdPrivilege()) {
                            System.out.println("Menu: " + myMenu26.getLibelemenu());
                            if (myMenu26.getLibelemenu().equals("Bon de commande")) {
                                for (Actionmenu myActionmenu26 : this.allActionmenus) {
                                    if (myMenu26.getIdMenu() == myActionmenu26.getIdMenu().getIdMenu()) {
                                        this.allActionmenusFromTraitementBonCommande.add(myActionmenu26);
                                    }

                                }
                            }

                        }

                    }
                }
                /*Fin Controle Bon de commande*/
                /*Controle Livraison*/
                for (Droitacces myDroit27 : this.allAcces) {
                    System.out.println("Droit: " + myDroit27.getModule());

                    for (Menu myMenu27 : this.allMenus) {
                        if (myDroit27.getIdPrivilege() == myMenu27.getIdPrivilege().getIdPrivilege()) {
                            System.out.println("Menu: " + myMenu27.getLibelemenu());
                            if (myMenu27.getLibelemenu().equals("Livraison")) {
                                for (Actionmenu myActionmenu27 : this.allActionmenus) {
                                    if (myMenu27.getIdMenu() == myActionmenu27.getIdMenu().getIdMenu()) {
                                        this.allActionmenusFromTraitementLivraison.add(myActionmenu27);
                                    }

                                }
                            }

                        }

                    }
                }
                /*Fin Controle Livraison*/
                /*Controle Approvisionnement chantier*/
                for (Droitacces myDroit28 : this.allAcces) {
                    System.out.println("Droit: " + myDroit28.getModule());

                    for (Menu myMenu28 : this.allMenus) {
                        if (myDroit28.getIdPrivilege() == myMenu28.getIdPrivilege().getIdPrivilege()) {
                            System.out.println("Menu: " + myMenu28.getLibelemenu());
                            if (myMenu28.getLibelemenu().equals("Approvisionnement chantier")) {
                                for (Actionmenu myActionmenu28 : this.allActionmenus) {
                                    if (myMenu28.getIdMenu() == myActionmenu28.getIdMenu().getIdMenu()) {
                                        this.allActionmenusFromTraitementApproChantier.add(myActionmenu28);
                                    }

                                }
                            }

                        }

                    }
                }
                /*Fin Controle Approvisionnement chantier*/
                /*Controle Approvisionnement Entrepots*/
                for (Droitacces myDroit29 : this.allAcces) {
                    System.out.println("Droit: " + myDroit29.getModule());

                    for (Menu myMenu29 : this.allMenus) {
                        if (myDroit29.getIdPrivilege() == myMenu29.getIdPrivilege().getIdPrivilege()) {
                            System.out.println("Menu: " + myMenu29.getLibelemenu());
                            if (myMenu29.getLibelemenu().equals("Entrepots")) {
                                for (Actionmenu myActionmenu29 : this.allActionmenus) {
                                    if (myMenu29.getIdMenu() == myActionmenu29.getIdMenu().getIdMenu()) {
                                        this.allActionmenusFromTraitementEntrepots.add(myActionmenu29);
                                    }

                                }
                            }

                        }

                    }
                }
                /*Fin Controle Approvisionnement Entrepots*/

                /*Controle Approvisionnement Inventaire*/
                for (Droitacces myDroit30 : this.allAcces) {
                    System.out.println("Droit: " + myDroit30.getModule());

                    for (Menu myMenu30 : this.allMenus) {
                        if (myDroit30.getIdPrivilege() == myMenu30.getIdPrivilege().getIdPrivilege()) {
                            System.out.println("Menu: " + myMenu30.getLibelemenu());
                            if (myMenu30.getLibelemenu().equals("Inventaire")) {
                                for (Actionmenu myActionmenu30 : this.allActionmenus) {
                                    if (myMenu30.getIdMenu() == myActionmenu30.getIdMenu().getIdMenu()) {
                                        this.allActionmenusFromTraitementInventaire.add(myActionmenu30);
                                    }

                                }
                            }

                        }

                    }
                }
                /*Fin Controle Approvisionnement Inventaire*/

                /*Controle Approvisionnement Mise à jour stock*/
                for (Droitacces myDroit31 : this.allAcces) {
                    System.out.println("Droit: " + myDroit31.getModule());

                    for (Menu myMenu31 : this.allMenus) {
                        if (myDroit31.getIdPrivilege() == myMenu31.getIdPrivilege().getIdPrivilege()) {
                            System.out.println("Menu: " + myMenu31.getLibelemenu());
                            if (myMenu31.getLibelemenu().equals("Mise à jour stock")) {
                                for (Actionmenu myActionmenu31 : this.allActionmenus) {
                                    if (myMenu31.getIdMenu() == myActionmenu31.getIdMenu().getIdMenu()) {
                                        this.allActionmenusFromTraitementMjs.add(myActionmenu31);
                                    }

                                }
                            }

                        }

                    }
                }
                /*Fin Controle Approvisionnement Mise à jour stock*/

                /*Controle Approvisionnement Sortie de stock*/
                for (Droitacces myDroit32 : this.allAcces) {
                    System.out.println("Droit: " + myDroit32.getModule());

                    for (Menu myMenu32 : this.allMenus) {
                        if (myDroit32.getIdPrivilege() == myMenu32.getIdPrivilege().getIdPrivilege()) {
                            System.out.println("Menu: " + myMenu32.getLibelemenu());
                            if (myMenu32.getLibelemenu().equals("Sortie de stock")) {
                                for (Actionmenu myActionmenu32 : this.allActionmenus) {
                                    if (myMenu32.getIdMenu() == myActionmenu32.getIdMenu().getIdMenu()) {
                                        this.allActionmenusFromTraitementSortieStock.add(myActionmenu32);
                                    }

                                }
                            }

                        }

                    }
                }
                /*Fin Controle Approvisionnement Sortie de stock*/

                /*Controle Approvisionnement Historique livraison*/
                for (Droitacces myDroit33 : this.allAcces) {
                    System.out.println("Droit: " + myDroit33.getModule());

                    for (Menu myMenu33 : this.allMenus) {
                        if (myDroit33.getIdPrivilege() == myMenu33.getIdPrivilege().getIdPrivilege()) {
                            System.out.println("Menu: " + myMenu33.getLibelemenu());
                            if (myMenu33.getLibelemenu().equals("Historique livraison")) {
                                for (Actionmenu myActionmenu33 : this.allActionmenus) {
                                    if (myMenu33.getIdMenu() == myActionmenu33.getIdMenu().getIdMenu()) {
                                        this.allActionmenusFromTraitementHistoLivraison.add(myActionmenu33);
                                    }

                                }
                            }

                        }

                    }
                }
                /*Fin Controle Approvisionnement Historique livraison*/

                /*Controle Approvisionnement Opportunités */
                for (Droitacces myDroit34 : this.allAcces) {
                    System.out.println("Droit: " + myDroit34.getModule());

                    for (Menu myMenu34 : this.allMenus) {
                        if (myDroit34.getIdPrivilege() == myMenu34.getIdPrivilege().getIdPrivilege()) {
                            System.out.println("Menu: " + myMenu34.getLibelemenu());
                            if (myMenu34.getLibelemenu().equals("Opportunités")) {
                                for (Actionmenu myActionmenu34 : this.allActionmenus) {
                                    if (myMenu34.getIdMenu() == myActionmenu34.getIdMenu().getIdMenu()) {
                                        this.allActionmenusFromTraitementOpportunites.add(myActionmenu34);
                                    }

                                }
                            }

                        }

                    }
                }
                /*Fin Controle Approvisionnement Opportunités */

                /**/
                /*this.listAcomptes = new ArrayList<>();
                this.listCommande = new ArrayList<>();
                this.listAcomptes.clear();
                this.listCommande.clear();
                this.listAcomptes = ejbAcompte.findAll();
                UtilCalculeDepenses ucd = new UtilCalculeDepenses();
                UtilDepenses utilDepenses = ucd.calculeOfAcompte(listAcomptes);
                System.out.println("Montant du mois de janvier: " + utilDepenses.getMontantAcomptesJanvier());
                System.out.println("Montant du mois de février: " + utilDepenses.getMontantAcomptesFevrier());*/

                this.listAcomptes = new ArrayList<>();
                this.listAcomptes.clear();
                this.listDetailleCommande = new ArrayList<>();
                this.listDetailleCommande.clear();
                this.listAcomptes = ejbAcompte.findAll();
                this.listDetailleCommande = ejbDetailleCommande.findAll();
                UtilCalculeDepenses ucd = new UtilCalculeDepenses();

                UtilDepenses utilDepenses1 = new UtilDepenses();
                utilDepenses1 = ucd.calculeOfAcompte(this.listAcomptes);
                System.out.println("Montant du mois de janvier: " + utilDepenses1.getMontantAcomptesJanvier());
                System.out.println("Montant du mois de février: " + utilDepenses1.getMontantAcomptesFevrier());

                UtilDepenses utilDepenses2 = new UtilDepenses();
                utilDepenses2 = ucd.calculeOfCommande(this.listDetailleCommande);
                System.out.println("Commande Montant du mois de janvier: " + utilDepenses2.getMontantBonCommandeJanvier());
                System.out.println("Commande Montant du mois de février: " + utilDepenses2.getMontantBonCommandeFevrier());
                System.out.println("Commande Montant du mois de Mars: " + utilDepenses2.getMontantBonCommandeMars());

                /**/
                session.setAttribute("allActionmenusFromTraitementUser", this.allActionmenusFromTraitementUser);
                session.setAttribute("allActionmenusTraitement", this.allActionmenusFromTraitement);
                session.setAttribute("allActionmenusFromTraitementRecrutement", this.allActionmenusFromTraitementRecrutement);
                session.setAttribute("allActionmenusFromTraitementStage", this.allActionmenusFromTraitementStage);
                session.setAttribute("allActionmenusFromTraitementSalaire", this.allActionmenusFromTraitementSalaire);
                session.setAttribute("allActionmenusFromTraitementPrets", this.allActionmenusFromTraitementPrets);
                session.setAttribute("allActionmenusFromTraitementIndemnites", this.allActionmenusFromTraitementIndemnites);

                session.setAttribute("allActionmenusFromTraitementClients", this.allActionmenusFromTraitementClients);
                session.setAttribute("allActionmenusFromTraitementDevis", this.allActionmenusFromTraitementDevis);
                session.setAttribute("allActionmenusFromTraitementMarches", this.allActionmenusFromTraitementMarches);
                session.setAttribute("allActionmenusFromTraitementFactures", this.allActionmenusFromTraitementFactures);
                session.setAttribute("allActionmenusFromTraitementDecomptes", this.allActionmenusFromTraitementDecomptes);
                session.setAttribute("allActionmenusFromTraitementChantiers", this.allActionmenusFromTraitementChantiers);
                session.setAttribute("allActionmenusFromTraitementPrestataires", this.allActionmenusFromTraitementPrestataires);
                session.setAttribute("allActionmenusFromTraitementMainOeuvres", this.allActionmenusFromTraitementMainOeuvres);
                session.setAttribute("allActionmenusFromTraitementAcomptes", this.allActionmenusFromTraitementAcomptes);
                session.setAttribute("allActionmenusFromTraitementProjetctManagements", this.allActionmenusFromTraitementProjetctManagements);

                session.setAttribute("allActionmenusFromTraitementDevises", this.allActionmenusFromTraitementDevises);
                session.setAttribute("allActionmenusFromTraitementUniteMesure", this.allActionmenusFromTraitementUniteMesure);
                session.setAttribute("allActionmenusFromTraitementLotTechnique", this.allActionmenusFromTraitementLotTechnique);
                session.setAttribute("allActionmenusFromTraitementCorpEtat", this.allActionmenusFromTraitementCorpEtat);
                session.setAttribute("allActionmenusFromTraitementMateriels", this.allActionmenusFromTraitementMateriels);
                session.setAttribute("allActionmenusFromTraitementFournisseurs", this.allActionmenusFromTraitementFournisseurs);
                session.setAttribute("allActionmenusFromTraitementReferencielPrix", this.allActionmenusFromTraitementReferencielPrix);
                session.setAttribute("allActionmenusFromTraitementExpressionBesoins", this.allActionmenusFromTraitementExpressionBesoins);
                session.setAttribute("allActionmenusFromTraitementBonCommande", this.allActionmenusFromTraitementBonCommande);
                session.setAttribute("allActionmenusFromTraitementLivraison", this.allActionmenusFromTraitementLivraison);
                session.setAttribute("allActionmenusFromTraitementApproChantier", this.allActionmenusFromTraitementApproChantier);

                session.setAttribute("allActionmenusFromTraitementEntrepots", this.allActionmenusFromTraitementEntrepots);
                session.setAttribute("allActionmenusFromTraitementInventaire", this.allActionmenusFromTraitementInventaire);
                session.setAttribute("allActionmenusFromTraitementMjs", this.allActionmenusFromTraitementMjs);
                session.setAttribute("allActionmenusFromTraitementSortieStock", this.allActionmenusFromTraitementSortieStock);
                session.setAttribute("allActionmenusFromTraitementHistoLivraison", this.allActionmenusFromTraitementHistoLivraison);
                session.setAttribute("allActionmenusFromTraitementOpportunites", this.allActionmenusFromTraitementOpportunites);
                session.setAttribute("utilDepensesAcompteFromDashbord", utilDepenses1);
                session.setAttribute("utilDepensesCommandeFromDashbord", utilDepenses2);

                session.setAttribute("user", this.utilisateur);

                return "succes";

            }

            if ((this.utilisateur != null) && (this.utilisateur.getFirstconnection().equals("Non"))) {

                LocalDateTime now = LocalDateTime.now();
                int hour = now.getHour();
                int minute = now.getMinute();
                int second = now.getSecond();

                UtilTime startT = new UtilTime(hour, minute, second);
                UtilTime stopT = new UtilTime(this.utilisateur.getHcreationpwd(), this.utilisateur.getMcreationpwd(), this.utilisateur.getScreationpwd());
                UtilTime diffT;
                CalculeHeure calHeur = new CalculeHeure();
                diffT = calHeur.difference(startT, stopT);

                if (diffT.getHours() >= 24) {
                    this.setEchecconnexion("Votre mot de passe a expiré !!!");
                    return "index";
                }

                if (diffT.getHours() < 24) {
                    this.setEchecconnexion("Le mot de passe a expiré, veuillez reinitialiser votre mot de passe !!!");
                    this.setLogin("");
                    this.setPassword("");
                    this.setPassword1("");
                    this.setPassword2("");
                    return "initPassword";
                }

            }

        } catch (Exception ex) {

        }

        this.setEchecconnexion("Utilisateur ou mot de passe incorrecte !!!");

        return "echec";

    }

    public void logoutUtilisateur() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

        utilisateur = (Utilisateur) session.getAttribute("user");

        String id = utilisateur.getIdUtilisateur().toString();
        session.removeAttribute(id);
        session.invalidate();

        Cookie[] cookies = ((HttpServletRequest) ec.getRequest()).getCookies();

        ec.redirect(ec.getRequestContextPath() + "/index.xhtml?faces-redirect=true");
    }

    public String connexion2() {

        try {
            this.utilisateur = new Utilisateur();
            this.utilisateur = ejbutilisateur.connexion(this.login, this.password);

            if ((this.utilisateur != null) && (this.utilisateur.getFirstconnection().equals("Non"))) {

                if (this.password1.equals(this.password2)) {

                    this.utilisateur.setFirstconnection("Oui");
                    this.utilisateur.setEtatcompte("Valide");
                    this.utilisateur.setEtatpwd("Valide");
                    this.utilisateur.setPasswordUtisateur(this.getPassword1());
                    ejbutilisateur.edit(this.utilisateur);

                    utilisateur = this.getUtilisateur();
                    this.allAcces = new ArrayList<>();
                    this.allMenus = new ArrayList<>();
                    this.allActionmenus = new ArrayList<>();
                    this.allActionmenusFromTraitement = new ArrayList<>();
                    this.allActionmenusFromTraitementUser = new ArrayList<>();
                    this.allActionmenusFromTraitementRecrutement = new ArrayList<>();
                    this.allActionmenusFromTraitementStage = new ArrayList<>();
                    this.allActionmenusFromTraitementSalaire = new ArrayList<>();
                    this.allActionmenusFromTraitementPrets = new ArrayList<>();
                    this.allActionmenusFromTraitementIndemnites = new ArrayList<>();
                    this.allActionmenusFromTraitementClients = new ArrayList<>();
                    this.allActionmenusFromTraitementDevis = new ArrayList<>();
                    this.allActionmenusFromTraitementMarches = new ArrayList<>();
                    this.allActionmenusFromTraitementFactures = new ArrayList<>();
                    this.allActionmenusFromTraitementDecomptes = new ArrayList<>();
                    this.allActionmenusFromTraitementChantiers = new ArrayList<>();
                    this.allActionmenusFromTraitementPrestataires = new ArrayList<>();
                    this.allActionmenusFromTraitementMainOeuvres = new ArrayList<>();
                    this.allActionmenusFromTraitementAcomptes = new ArrayList<>();
                    this.allActionmenusFromTraitementProjetctManagements = new ArrayList<>();
                    this.allActionmenusFromTraitementDevises = new ArrayList<>();
                    this.allActionmenusFromTraitementUniteMesure = new ArrayList<>();
                    this.allActionmenusFromTraitementLotTechnique = new ArrayList<>();
                    this.allActionmenusFromTraitementCorpEtat = new ArrayList<>();
                    this.allActionmenusFromTraitementMateriels = new ArrayList<>();
                    this.allActionmenusFromTraitementFournisseurs = new ArrayList<>();
                    this.allActionmenusFromTraitementReferencielPrix = new ArrayList<>();
                    this.allActionmenusFromTraitementExpressionBesoins = new ArrayList<>();
                    this.allActionmenusFromTraitementBonCommande = new ArrayList<>();
                    this.allActionmenusFromTraitementLivraison = new ArrayList<>();
                    this.allActionmenusFromTraitementApproChantier = new ArrayList<>();

                    this.allActionmenusFromTraitementEntrepots = new ArrayList<>();
                    this.allActionmenusFromTraitementInventaire = new ArrayList<>();
                    this.allActionmenusFromTraitementMjs = new ArrayList<>();
                    this.allActionmenusFromTraitementSortieStock = new ArrayList<>();
                    this.allActionmenusFromTraitementHistoLivraison = new ArrayList<>();
                    this.allActionmenusFromTraitementOpportunites = new ArrayList<>();

                    this.allAcces = ejbDroitacces.listOfDroitByUser(utilisateur);

                    this.allMenus = ejbMenu.findAll();

                    this.allActionmenus = ejbActionmenu.findAll();
                    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

                    /*Controle roles*/
                    for (Droitacces myDroit1 : this.allAcces) {
                        System.out.println("Droit: " + myDroit1.getModule());

                        for (Menu myMenu1 : this.allMenus) {
                            if (myDroit1.getIdPrivilege() == myMenu1.getIdPrivilege().getIdPrivilege()) {
                                System.out.println("Menu: " + myMenu1.getLibelemenu());
                                if (myMenu1.getLibelemenu().equals("Gestion des rôles")) {
                                    for (Actionmenu myActionmenu1 : this.allActionmenus) {
                                        if (myMenu1.getIdMenu() == myActionmenu1.getIdMenu().getIdMenu()) {
                                            this.allActionmenusFromTraitement.add(myActionmenu1);
                                        }

                                    }
                                }

                            }

                        }
                    }
                    /*Fin Controle roles*/

                    /*Controle users*/
                    for (Droitacces myDroit2 : this.allAcces) {
                        System.out.println("Droit: " + myDroit2.getModule());

                        for (Menu myMenu2 : this.allMenus) {
                            if (myDroit2.getIdPrivilege() == myMenu2.getIdPrivilege().getIdPrivilege()) {
                                System.out.println("Menu: " + myMenu2.getLibelemenu());
                                if (myMenu2.getLibelemenu().equals("Gestion des utilisateurs")) {
                                    for (Actionmenu myActionmenu2 : this.allActionmenus) {
                                        if (myMenu2.getIdMenu() == myActionmenu2.getIdMenu().getIdMenu()) {
                                            this.allActionmenusFromTraitementUser.add(myActionmenu2);
                                        }

                                    }
                                }

                            }

                        }
                    }
                    /*Fin Controle users*/

                    /*Controle Recrutement*/
                    for (Droitacces myDroit3 : this.allAcces) {
                        System.out.println("Droit: " + myDroit3.getModule());

                        for (Menu myMenu3 : this.allMenus) {
                            if (myDroit3.getIdPrivilege() == myMenu3.getIdPrivilege().getIdPrivilege()) {
                                System.out.println("Menu: " + myMenu3.getLibelemenu());
                                if (myMenu3.getLibelemenu().equals("Recrutement")) {
                                    for (Actionmenu myActionmenu3 : this.allActionmenus) {
                                        if (myMenu3.getIdMenu() == myActionmenu3.getIdMenu().getIdMenu()) {
                                            this.allActionmenusFromTraitementRecrutement.add(myActionmenu3);
                                        }

                                    }
                                }

                            }

                        }
                    }
                    /*Fin Controle Recrutement*/

                    /*Controle Stage*/
                    for (Droitacces myDroit4 : this.allAcces) {
                        System.out.println("Droit: " + myDroit4.getModule());

                        for (Menu myMenu4 : this.allMenus) {
                            if (myDroit4.getIdPrivilege() == myMenu4.getIdPrivilege().getIdPrivilege()) {
                                System.out.println("Menu: " + myMenu4.getLibelemenu());
                                if (myMenu4.getLibelemenu().equals("Stage")) {
                                    for (Actionmenu myActionmenu4 : this.allActionmenus) {
                                        if (myMenu4.getIdMenu() == myActionmenu4.getIdMenu().getIdMenu()) {
                                            this.allActionmenusFromTraitementStage.add(myActionmenu4);
                                        }

                                    }
                                }

                            }

                        }
                    }
                    /*Fin Controle Stage*/

                    /*Controle Salaire*/
                    for (Droitacces myDroit5 : this.allAcces) {
                        System.out.println("Droit: " + myDroit5.getModule());

                        for (Menu myMenu5 : this.allMenus) {
                            if (myDroit5.getIdPrivilege() == myMenu5.getIdPrivilege().getIdPrivilege()) {
                                System.out.println("Menu: " + myMenu5.getLibelemenu());
                                if (myMenu5.getLibelemenu().equals("Salaire")) {
                                    for (Actionmenu myActionmenu5 : this.allActionmenus) {
                                        if (myMenu5.getIdMenu() == myActionmenu5.getIdMenu().getIdMenu()) {
                                            this.allActionmenusFromTraitementSalaire.add(myActionmenu5);
                                        }

                                    }
                                }

                            }

                        }
                    }
                    /*Fin Controle Salaire*/

                    /*Controle Prêts*/
                    for (Droitacces myDroit6 : this.allAcces) {
                        System.out.println("Droit: " + myDroit6.getModule());

                        for (Menu myMenu6 : this.allMenus) {
                            if (myDroit6.getIdPrivilege() == myMenu6.getIdPrivilege().getIdPrivilege()) {
                                System.out.println("Menu: " + myMenu6.getLibelemenu());
                                if (myMenu6.getLibelemenu().equals("Prêts")) {
                                    for (Actionmenu myActionmenu6 : this.allActionmenus) {
                                        if (myMenu6.getIdMenu() == myActionmenu6.getIdMenu().getIdMenu()) {
                                            this.allActionmenusFromTraitementPrets.add(myActionmenu6);
                                        }

                                    }
                                }

                            }

                        }
                    }
                    /*Fin Controle Prêts*/

                    /*Controle Indemnités*/
                    for (Droitacces myDroit7 : this.allAcces) {
                        System.out.println("Droit: " + myDroit7.getModule());

                        for (Menu myMenu7 : this.allMenus) {
                            if (myDroit7.getIdPrivilege() == myMenu7.getIdPrivilege().getIdPrivilege()) {
                                System.out.println("Menu: " + myMenu7.getLibelemenu());
                                if (myMenu7.getLibelemenu().equals("Indemnités")) {
                                    for (Actionmenu myActionmenu7 : this.allActionmenus) {
                                        if (myMenu7.getIdMenu() == myActionmenu7.getIdMenu().getIdMenu()) {
                                            this.allActionmenusFromTraitementIndemnites.add(myActionmenu7);
                                        }

                                    }
                                }

                            }

                        }
                    }
                    /*Fin Controle Indemnités*/
                    /*Controle Clients*/
                    for (Droitacces myDroit8 : this.allAcces) {
                        System.out.println("Droit: " + myDroit8.getModule());

                        for (Menu myMenu8 : this.allMenus) {
                            if (myDroit8.getIdPrivilege() == myMenu8.getIdPrivilege().getIdPrivilege()) {
                                System.out.println("Menu: " + myMenu8.getLibelemenu());
                                if (myMenu8.getLibelemenu().equals("Clients")) {
                                    for (Actionmenu myActionmenu8 : this.allActionmenus) {
                                        if (myMenu8.getIdMenu() == myActionmenu8.getIdMenu().getIdMenu()) {
                                            this.allActionmenusFromTraitementClients.add(myActionmenu8);
                                        }

                                    }
                                }

                            }

                        }
                    }
                    /*Fin Controle Clients*/
                    /*Controle devis*/
                    for (Droitacces myDroit9 : this.allAcces) {
                        System.out.println("Droit: " + myDroit9.getModule());

                        for (Menu myMenu9 : this.allMenus) {
                            if (myDroit9.getIdPrivilege() == myMenu9.getIdPrivilege().getIdPrivilege()) {
                                System.out.println("Menu: " + myMenu9.getLibelemenu());
                                if (myMenu9.getLibelemenu().equals("Devis")) {
                                    for (Actionmenu myActionmenu9 : this.allActionmenus) {
                                        if (myMenu9.getIdMenu() == myActionmenu9.getIdMenu().getIdMenu()) {
                                            this.allActionmenusFromTraitementDevis.add(myActionmenu9);
                                        }

                                    }
                                }

                            }

                        }
                    }
                    /*Fin Controle Devis*/
                    /*Controle Marches*/
                    for (Droitacces myDroit10 : this.allAcces) {
                        System.out.println("Droit: " + myDroit10.getModule());

                        for (Menu myMenu10 : this.allMenus) {
                            if (myDroit10.getIdPrivilege() == myMenu10.getIdPrivilege().getIdPrivilege()) {
                                System.out.println("Menu: " + myMenu10.getLibelemenu());
                                if (myMenu10.getLibelemenu().equals("Marchés")) {
                                    for (Actionmenu myActionmenu10 : this.allActionmenus) {
                                        if (myMenu10.getIdMenu() == myActionmenu10.getIdMenu().getIdMenu()) {
                                            this.allActionmenusFromTraitementMarches.add(myActionmenu10);
                                        }

                                    }
                                }

                            }

                        }
                    }
                    /*Fin Controle Marches*/
                    /*Controle Factures*/
                    for (Droitacces myDroit11 : this.allAcces) {
                        System.out.println("Droit: " + myDroit11.getModule());

                        for (Menu myMenu11 : this.allMenus) {
                            if (myDroit11.getIdPrivilege() == myMenu11.getIdPrivilege().getIdPrivilege()) {
                                System.out.println("Menu: " + myMenu11.getLibelemenu());
                                if (myMenu11.getLibelemenu().equals("Factures")) {
                                    for (Actionmenu myActionmenu11 : this.allActionmenus) {
                                        if (myMenu11.getIdMenu() == myActionmenu11.getIdMenu().getIdMenu()) {
                                            this.allActionmenusFromTraitementFactures.add(myActionmenu11);
                                        }

                                    }
                                }

                            }

                        }
                    }
                    /*Fin Controle Factures*/
                    /*Controle Décomptes*/
                    for (Droitacces myDroit12 : this.allAcces) {
                        System.out.println("Droit: " + myDroit12.getModule());

                        for (Menu myMenu12 : this.allMenus) {
                            if (myDroit12.getIdPrivilege() == myMenu12.getIdPrivilege().getIdPrivilege()) {
                                System.out.println("Menu: " + myMenu12.getLibelemenu());
                                if (myMenu12.getLibelemenu().equals("Décomptes")) {
                                    for (Actionmenu myActionmenu12 : this.allActionmenus) {
                                        if (myMenu12.getIdMenu() == myActionmenu12.getIdMenu().getIdMenu()) {
                                            this.allActionmenusFromTraitementDecomptes.add(myActionmenu12);
                                        }

                                    }
                                }

                            }

                        }
                    }
                    /*Fin Controle Décomptes*/
                    /*Controle Chantiers*/
                    for (Droitacces myDroit13 : this.allAcces) {
                        System.out.println("Droit: " + myDroit13.getModule());

                        for (Menu myMenu13 : this.allMenus) {
                            if (myDroit13.getIdPrivilege() == myMenu13.getIdPrivilege().getIdPrivilege()) {
                                System.out.println("Menu: " + myMenu13.getLibelemenu());
                                if (myMenu13.getLibelemenu().equals("Chantiers")) {
                                    for (Actionmenu myActionmenu13 : this.allActionmenus) {
                                        if (myMenu13.getIdMenu() == myActionmenu13.getIdMenu().getIdMenu()) {
                                            this.allActionmenusFromTraitementChantiers.add(myActionmenu13);
                                        }

                                    }
                                }

                            }

                        }
                    }
                    /*Fin Controle Chantiers*/
                    /*Controle Liste des prestataires*/
                    for (Droitacces myDroit14 : this.allAcces) {
                        System.out.println("Droit: " + myDroit14.getModule());

                        for (Menu myMenu14 : this.allMenus) {
                            if (myDroit14.getIdPrivilege() == myMenu14.getIdPrivilege().getIdPrivilege()) {
                                System.out.println("Menu: " + myMenu14.getLibelemenu());
                                if (myMenu14.getLibelemenu().equals("Liste des prestataires")) {
                                    for (Actionmenu myActionmenu14 : this.allActionmenus) {
                                        if (myMenu14.getIdMenu() == myActionmenu14.getIdMenu().getIdMenu()) {
                                            this.allActionmenusFromTraitementPrestataires.add(myActionmenu14);
                                        }

                                    }
                                }

                            }

                        }
                    }
                    /*Fin Controle Liste des prestataires*/
                    /*Controle Liste des Referenciel Main-d'oeuvre*/
                    for (Droitacces myDroit15 : this.allAcces) {
                        System.out.println("Droit: " + myDroit15.getModule());

                        for (Menu myMenu15 : this.allMenus) {
                            if (myDroit15.getIdPrivilege() == myMenu15.getIdPrivilege().getIdPrivilege()) {
                                System.out.println("Menu: " + myMenu15.getLibelemenu());
                                if (myMenu15.getLibelemenu().equals("Referenciel Main-d'oeuvre")) {
                                    for (Actionmenu myActionmenu15 : this.allActionmenus) {
                                        if (myMenu15.getIdMenu() == myActionmenu15.getIdMenu().getIdMenu()) {
                                            this.allActionmenusFromTraitementMainOeuvres.add(myActionmenu15);
                                        }

                                    }
                                }

                            }

                        }
                    }
                    /*Fin Controle Liste des Referenciel Main-d'oeuvre*/
                    /*Controle Liste des Acomptes*/
                    for (Droitacces myDroit16 : this.allAcces) {
                        System.out.println("Droit: " + myDroit16.getModule());

                        for (Menu myMenu16 : this.allMenus) {
                            if (myDroit16.getIdPrivilege() == myMenu16.getIdPrivilege().getIdPrivilege()) {
                                System.out.println("Menu: " + myMenu16.getLibelemenu());
                                if (myMenu16.getLibelemenu().equals("Acomptes")) {
                                    for (Actionmenu myActionmenu16 : this.allActionmenus) {
                                        if (myMenu16.getIdMenu() == myActionmenu16.getIdMenu().getIdMenu()) {
                                            this.allActionmenusFromTraitementAcomptes.add(myActionmenu16);
                                        }

                                    }
                                }

                            }

                        }
                    }
                    /*Fin Controle Liste des Acomptes*/
                    /*Controle Liste des Project Management*/
                    for (Droitacces myDroit17 : this.allAcces) {
                        System.out.println("Droit: " + myDroit17.getModule());

                        for (Menu myMenu17 : this.allMenus) {
                            if (myDroit17.getIdPrivilege() == myMenu17.getIdPrivilege().getIdPrivilege()) {
                                System.out.println("Menu: " + myMenu17.getLibelemenu());
                                if (myMenu17.getLibelemenu().equals("Project Management")) {
                                    for (Actionmenu myActionmenu17 : this.allActionmenus) {
                                        if (myMenu17.getIdMenu() == myActionmenu17.getIdMenu().getIdMenu()) {
                                            this.allActionmenusFromTraitementAcomptes.add(myActionmenu17);
                                        }

                                    }
                                }

                            }

                        }
                    }
                    /*Fin Controle Liste des Project Management*/
                    /*Controle Devises*/
                    for (Droitacces myDroit18 : this.allAcces) {
                        System.out.println("Droit: " + myDroit18.getModule());

                        for (Menu myMenu18 : this.allMenus) {
                            if (myDroit18.getIdPrivilege() == myMenu18.getIdPrivilege().getIdPrivilege()) {
                                System.out.println("Menu: " + myMenu18.getLibelemenu());
                                if (myMenu18.getLibelemenu().equals("Devises")) {
                                    for (Actionmenu myActionmenu18 : this.allActionmenus) {
                                        if (myMenu18.getIdMenu() == myActionmenu18.getIdMenu().getIdMenu()) {
                                            this.allActionmenusFromTraitementDevises.add(myActionmenu18);
                                        }

                                    }
                                }

                            }

                        }
                    }
                    /*Fin Controle Devises*/
                    /*Controle Unite de mesure*/
                    for (Droitacces myDroit19 : this.allAcces) {
                        System.out.println("Droit: " + myDroit19.getModule());

                        for (Menu myMenu19 : this.allMenus) {
                            if (myDroit19.getIdPrivilege() == myMenu19.getIdPrivilege().getIdPrivilege()) {
                                System.out.println("Menu: " + myMenu19.getLibelemenu());
                                if (myMenu19.getLibelemenu().equals("Unite de mesure")) {
                                    for (Actionmenu myActionmenu19 : this.allActionmenus) {
                                        if (myMenu19.getIdMenu() == myActionmenu19.getIdMenu().getIdMenu()) {
                                            this.allActionmenusFromTraitementUniteMesure.add(myActionmenu19);
                                        }

                                    }
                                }

                            }

                        }
                    }
                    /*Fin Controle Unite de mesure*/
                    /*Controle Lot technique*/
                    for (Droitacces myDroit20 : this.allAcces) {
                        System.out.println("Droit: " + myDroit20.getModule());

                        for (Menu myMenu20 : this.allMenus) {
                            if (myDroit20.getIdPrivilege() == myMenu20.getIdPrivilege().getIdPrivilege()) {
                                System.out.println("Menu: " + myMenu20.getLibelemenu());
                                if (myMenu20.getLibelemenu().equals("Lot technique")) {
                                    for (Actionmenu myActionmenu20 : this.allActionmenus) {
                                        if (myMenu20.getIdMenu() == myActionmenu20.getIdMenu().getIdMenu()) {
                                            this.allActionmenusFromTraitementLotTechnique.add(myActionmenu20);
                                        }

                                    }
                                }

                            }

                        }
                    }
                    /*Fin Controle Lot technique*/
                    /*Controle Corps d'etat*/
                    for (Droitacces myDroit21 : this.allAcces) {
                        System.out.println("Droit: " + myDroit21.getModule());

                        for (Menu myMenu21 : this.allMenus) {
                            if (myDroit21.getIdPrivilege() == myMenu21.getIdPrivilege().getIdPrivilege()) {
                                System.out.println("Menu: " + myMenu21.getLibelemenu());
                                if (myMenu21.getLibelemenu().equals("Corps d'etat")) {
                                    for (Actionmenu myActionmenu21 : this.allActionmenus) {
                                        if (myMenu21.getIdMenu() == myActionmenu21.getIdMenu().getIdMenu()) {
                                            this.allActionmenusFromTraitementCorpEtat.add(myActionmenu21);
                                        }

                                    }
                                }

                            }

                        }
                    }
                    /*Fin Controle Corps d'etat*/
                    /*Controle Materiels*/
                    for (Droitacces myDroit22 : this.allAcces) {
                        System.out.println("Droit: " + myDroit22.getModule());

                        for (Menu myMenu22 : this.allMenus) {
                            if (myDroit22.getIdPrivilege() == myMenu22.getIdPrivilege().getIdPrivilege()) {
                                System.out.println("Menu: " + myMenu22.getLibelemenu());
                                if (myMenu22.getLibelemenu().equals("Materiels")) {
                                    for (Actionmenu myActionmenu22 : this.allActionmenus) {
                                        if (myMenu22.getIdMenu() == myActionmenu22.getIdMenu().getIdMenu()) {
                                            this.allActionmenusFromTraitementMateriels.add(myActionmenu22);
                                        }

                                    }
                                }

                            }

                        }
                    }
                    /*Fin Controle Materiels*/
                    /*Controle Fournisseurs*/
                    for (Droitacces myDroit23 : this.allAcces) {
                        System.out.println("Droit: " + myDroit23.getModule());

                        for (Menu myMenu23 : this.allMenus) {
                            if (myDroit23.getIdPrivilege() == myMenu23.getIdPrivilege().getIdPrivilege()) {
                                System.out.println("Menu: " + myMenu23.getLibelemenu());
                                if (myMenu23.getLibelemenu().equals("Fournisseurs")) {
                                    for (Actionmenu myActionmenu23 : this.allActionmenus) {
                                        if (myMenu23.getIdMenu() == myActionmenu23.getIdMenu().getIdMenu()) {
                                            this.allActionmenusFromTraitementFournisseurs.add(myActionmenu23);
                                        }

                                    }
                                }

                            }

                        }
                    }
                    /*Fin Controle Fournisseurs*/
                    /*Controle Referenciel de prix*/
                    for (Droitacces myDroit24 : this.allAcces) {
                        System.out.println("Droit: " + myDroit24.getModule());

                        for (Menu myMenu24 : this.allMenus) {
                            if (myDroit24.getIdPrivilege() == myMenu24.getIdPrivilege().getIdPrivilege()) {
                                System.out.println("Menu: " + myMenu24.getLibelemenu());
                                if (myMenu24.getLibelemenu().equals("Referenciel de prix")) {
                                    for (Actionmenu myActionmenu24 : this.allActionmenus) {
                                        if (myMenu24.getIdMenu() == myActionmenu24.getIdMenu().getIdMenu()) {
                                            this.allActionmenusFromTraitementReferencielPrix.add(myActionmenu24);
                                        }

                                    }
                                }

                            }

                        }
                    }
                    /*Fin Controle Referenciel de prix*/
                    /*Controle Expression des besoins*/
                    for (Droitacces myDroit25 : this.allAcces) {
                        System.out.println("Droit: " + myDroit25.getModule());

                        for (Menu myMenu25 : this.allMenus) {
                            if (myDroit25.getIdPrivilege() == myMenu25.getIdPrivilege().getIdPrivilege()) {
                                System.out.println("Menu: " + myMenu25.getLibelemenu());
                                if (myMenu25.getLibelemenu().equals("Expression des besoins")) {
                                    for (Actionmenu myActionmenu25 : this.allActionmenus) {
                                        if (myMenu25.getIdMenu() == myActionmenu25.getIdMenu().getIdMenu()) {
                                            this.allActionmenusFromTraitementExpressionBesoins.add(myActionmenu25);
                                        }

                                    }
                                }

                            }

                        }
                    }
                    /*Fin Controle Expression des besoins*/
                    /*Controle Bon de commande*/
                    for (Droitacces myDroit26 : this.allAcces) {
                        System.out.println("Droit: " + myDroit26.getModule());

                        for (Menu myMenu26 : this.allMenus) {
                            if (myDroit26.getIdPrivilege() == myMenu26.getIdPrivilege().getIdPrivilege()) {
                                System.out.println("Menu: " + myMenu26.getLibelemenu());
                                if (myMenu26.getLibelemenu().equals("Bon de commande")) {
                                    for (Actionmenu myActionmenu26 : this.allActionmenus) {
                                        if (myMenu26.getIdMenu() == myActionmenu26.getIdMenu().getIdMenu()) {
                                            this.allActionmenusFromTraitementBonCommande.add(myActionmenu26);
                                        }

                                    }
                                }

                            }

                        }
                    }
                    /*Fin Controle Bon de commande*/
                    /*Controle Livraison*/
                    for (Droitacces myDroit27 : this.allAcces) {
                        System.out.println("Droit: " + myDroit27.getModule());

                        for (Menu myMenu27 : this.allMenus) {
                            if (myDroit27.getIdPrivilege() == myMenu27.getIdPrivilege().getIdPrivilege()) {
                                System.out.println("Menu: " + myMenu27.getLibelemenu());
                                if (myMenu27.getLibelemenu().equals("Livraison")) {
                                    for (Actionmenu myActionmenu27 : this.allActionmenus) {
                                        if (myMenu27.getIdMenu() == myActionmenu27.getIdMenu().getIdMenu()) {
                                            this.allActionmenusFromTraitementLivraison.add(myActionmenu27);
                                        }

                                    }
                                }

                            }

                        }
                    }
                    /*Fin Controle Livraison*/
                    /*Controle Approvisionnement chantier*/
                    for (Droitacces myDroit28 : this.allAcces) {
                        System.out.println("Droit: " + myDroit28.getModule());

                        for (Menu myMenu28 : this.allMenus) {
                            if (myDroit28.getIdPrivilege() == myMenu28.getIdPrivilege().getIdPrivilege()) {
                                System.out.println("Menu: " + myMenu28.getLibelemenu());
                                if (myMenu28.getLibelemenu().equals("Approvisionnement chantier")) {
                                    for (Actionmenu myActionmenu28 : this.allActionmenus) {
                                        if (myMenu28.getIdMenu() == myActionmenu28.getIdMenu().getIdMenu()) {
                                            this.allActionmenusFromTraitementApproChantier.add(myActionmenu28);
                                        }

                                    }
                                }

                            }

                        }
                    }
                    /*Fin Controle Approvisionnement chantier*/
                    /*Controle Approvisionnement Entrepots*/
                    for (Droitacces myDroit29 : this.allAcces) {
                        System.out.println("Droit: " + myDroit29.getModule());

                        for (Menu myMenu29 : this.allMenus) {
                            if (myDroit29.getIdPrivilege() == myMenu29.getIdPrivilege().getIdPrivilege()) {
                                System.out.println("Menu: " + myMenu29.getLibelemenu());
                                if (myMenu29.getLibelemenu().equals("Entrepots")) {
                                    for (Actionmenu myActionmenu29 : this.allActionmenus) {
                                        if (myMenu29.getIdMenu() == myActionmenu29.getIdMenu().getIdMenu()) {
                                            this.allActionmenusFromTraitementEntrepots.add(myActionmenu29);
                                        }

                                    }
                                }

                            }

                        }
                    }
                    /*Fin Controle Approvisionnement Entrepots*/

                    /*Controle Approvisionnement Inventaire*/
                    for (Droitacces myDroit30 : this.allAcces) {
                        System.out.println("Droit: " + myDroit30.getModule());

                        for (Menu myMenu30 : this.allMenus) {
                            if (myDroit30.getIdPrivilege() == myMenu30.getIdPrivilege().getIdPrivilege()) {
                                System.out.println("Menu: " + myMenu30.getLibelemenu());
                                if (myMenu30.getLibelemenu().equals("Inventaire")) {
                                    for (Actionmenu myActionmenu30 : this.allActionmenus) {
                                        if (myMenu30.getIdMenu() == myActionmenu30.getIdMenu().getIdMenu()) {
                                            this.allActionmenusFromTraitementInventaire.add(myActionmenu30);
                                        }

                                    }
                                }

                            }

                        }
                    }
                    /*Fin Controle Approvisionnement Inventaire*/

                    /*Controle Approvisionnement Mise à jour stock*/
                    for (Droitacces myDroit31 : this.allAcces) {
                        System.out.println("Droit: " + myDroit31.getModule());

                        for (Menu myMenu31 : this.allMenus) {
                            if (myDroit31.getIdPrivilege() == myMenu31.getIdPrivilege().getIdPrivilege()) {
                                System.out.println("Menu: " + myMenu31.getLibelemenu());
                                if (myMenu31.getLibelemenu().equals("Mise à jour stock")) {
                                    for (Actionmenu myActionmenu31 : this.allActionmenus) {
                                        if (myMenu31.getIdMenu() == myActionmenu31.getIdMenu().getIdMenu()) {
                                            this.allActionmenusFromTraitementMjs.add(myActionmenu31);
                                        }

                                    }
                                }

                            }

                        }
                    }
                    /*Fin Controle Approvisionnement Mise à jour stock*/

                    /*Controle Approvisionnement Sortie de stock*/
                    for (Droitacces myDroit32 : this.allAcces) {
                        System.out.println("Droit: " + myDroit32.getModule());

                        for (Menu myMenu32 : this.allMenus) {
                            if (myDroit32.getIdPrivilege() == myMenu32.getIdPrivilege().getIdPrivilege()) {
                                System.out.println("Menu: " + myMenu32.getLibelemenu());
                                if (myMenu32.getLibelemenu().equals("Sortie de stock")) {
                                    for (Actionmenu myActionmenu32 : this.allActionmenus) {
                                        if (myMenu32.getIdMenu() == myActionmenu32.getIdMenu().getIdMenu()) {
                                            this.allActionmenusFromTraitementSortieStock.add(myActionmenu32);
                                        }

                                    }
                                }

                            }

                        }
                    }
                    /*Fin Controle Approvisionnement Sortie de stock*/

                    /*Controle Approvisionnement Historique livraison*/
                    for (Droitacces myDroit33 : this.allAcces) {
                        System.out.println("Droit: " + myDroit33.getModule());

                        for (Menu myMenu33 : this.allMenus) {
                            if (myDroit33.getIdPrivilege() == myMenu33.getIdPrivilege().getIdPrivilege()) {
                                System.out.println("Menu: " + myMenu33.getLibelemenu());
                                if (myMenu33.getLibelemenu().equals("Historique livraison")) {
                                    for (Actionmenu myActionmenu33 : this.allActionmenus) {
                                        if (myMenu33.getIdMenu() == myActionmenu33.getIdMenu().getIdMenu()) {
                                            this.allActionmenusFromTraitementHistoLivraison.add(myActionmenu33);
                                        }

                                    }
                                }

                            }

                        }
                    }
                    /*Fin Controle Approvisionnement Historique livraison*/

                    /*Controle Approvisionnement Opportunités */
                    for (Droitacces myDroit34 : this.allAcces) {
                        System.out.println("Droit: " + myDroit34.getModule());

                        for (Menu myMenu34 : this.allMenus) {
                            if (myDroit34.getIdPrivilege() == myMenu34.getIdPrivilege().getIdPrivilege()) {
                                System.out.println("Menu: " + myMenu34.getLibelemenu());
                                if (myMenu34.getLibelemenu().equals("Opportunités")) {
                                    for (Actionmenu myActionmenu34 : this.allActionmenus) {
                                        if (myMenu34.getIdMenu() == myActionmenu34.getIdMenu().getIdMenu()) {
                                            this.allActionmenusFromTraitementOpportunites.add(myActionmenu34);
                                        }

                                    }
                                }

                            }

                        }
                    }
                    /*Fin Controle Approvisionnement Opportunités */

                    session.setAttribute("allActionmenusFromTraitementUser", this.allActionmenusFromTraitementUser);
                    session.setAttribute("allActionmenusTraitement", this.allActionmenusFromTraitement);
                    session.setAttribute("allActionmenusFromTraitementRecrutement", this.allActionmenusFromTraitementRecrutement);
                    session.setAttribute("allActionmenusFromTraitementStage", this.allActionmenusFromTraitementStage);
                    session.setAttribute("allActionmenusFromTraitementSalaire", this.allActionmenusFromTraitementSalaire);
                    session.setAttribute("allActionmenusFromTraitementPrets", this.allActionmenusFromTraitementPrets);
                    session.setAttribute("allActionmenusFromTraitementIndemnites", this.allActionmenusFromTraitementIndemnites);

                    session.setAttribute("allActionmenusFromTraitementClients", this.allActionmenusFromTraitementClients);
                    session.setAttribute("allActionmenusFromTraitementDevis", this.allActionmenusFromTraitementDevis);
                    session.setAttribute("allActionmenusFromTraitementMarches", this.allActionmenusFromTraitementMarches);
                    session.setAttribute("allActionmenusFromTraitementFactures", this.allActionmenusFromTraitementFactures);
                    session.setAttribute("allActionmenusFromTraitementDecomptes", this.allActionmenusFromTraitementDecomptes);
                    session.setAttribute("allActionmenusFromTraitementChantiers", this.allActionmenusFromTraitementChantiers);
                    session.setAttribute("allActionmenusFromTraitementPrestataires", this.allActionmenusFromTraitementPrestataires);
                    session.setAttribute("allActionmenusFromTraitementMainOeuvres", this.allActionmenusFromTraitementMainOeuvres);
                    session.setAttribute("allActionmenusFromTraitementAcomptes", this.allActionmenusFromTraitementAcomptes);
                    session.setAttribute("allActionmenusFromTraitementProjetctManagements", this.allActionmenusFromTraitementProjetctManagements);

                    session.setAttribute("allActionmenusFromTraitementDevises", this.allActionmenusFromTraitementDevises);
                    session.setAttribute("allActionmenusFromTraitementUniteMesure", this.allActionmenusFromTraitementUniteMesure);
                    session.setAttribute("allActionmenusFromTraitementLotTechnique", this.allActionmenusFromTraitementLotTechnique);
                    session.setAttribute("allActionmenusFromTraitementCorpEtat", this.allActionmenusFromTraitementCorpEtat);
                    session.setAttribute("allActionmenusFromTraitementMateriels", this.allActionmenusFromTraitementMateriels);
                    session.setAttribute("allActionmenusFromTraitementFournisseurs", this.allActionmenusFromTraitementFournisseurs);
                    session.setAttribute("allActionmenusFromTraitementReferencielPrix", this.allActionmenusFromTraitementReferencielPrix);
                    session.setAttribute("allActionmenusFromTraitementExpressionBesoins", this.allActionmenusFromTraitementExpressionBesoins);
                    session.setAttribute("allActionmenusFromTraitementBonCommande", this.allActionmenusFromTraitementBonCommande);
                    session.setAttribute("allActionmenusFromTraitementLivraison", this.allActionmenusFromTraitementLivraison);
                    session.setAttribute("allActionmenusFromTraitementApproChantier", this.allActionmenusFromTraitementApproChantier);

                    session.setAttribute("allActionmenusFromTraitementEntrepots", this.allActionmenusFromTraitementEntrepots);
                    session.setAttribute("allActionmenusFromTraitementInventaire", this.allActionmenusFromTraitementInventaire);
                    session.setAttribute("allActionmenusFromTraitementMjs", this.allActionmenusFromTraitementMjs);
                    session.setAttribute("allActionmenusFromTraitementSortieStock", this.allActionmenusFromTraitementSortieStock);
                    session.setAttribute("allActionmenusFromTraitementHistoLivraison", this.allActionmenusFromTraitementHistoLivraison);
                    session.setAttribute("allActionmenusFromTraitementOpportunites", this.allActionmenusFromTraitementOpportunites);

                    session.setAttribute("user", this.utilisateur);

                    return "home";

                } else {
                    this.setEchecconnexion("Les mot de passe ne sont pas identiques, veuillez vonfirmer le nouveau mot de passe !!!");
                    this.setPassword("");
                    this.setPassword1("");
                    this.setPassword2("");
                    return "initPassword";
                }

            } else {
                this.setEchecconnexion("Utilisateur ou mot de passe incorrecte !!!");
                return "initPassword";
            }

        } catch (Exception ex) {

        }

        this.setEchecconnexion("Utilisateur ou mot de passe incorrecte !!!");
        return "echec";
    }

}
