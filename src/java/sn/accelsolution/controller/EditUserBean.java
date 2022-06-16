/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.model.TreeNode;
import sn.accelsolution.dao.ActionactifFacade;
import sn.accelsolution.dao.ActionmenuFacade;
import sn.accelsolution.dao.CommandeFacade;
import sn.accelsolution.dao.DecomptepFacade;
import sn.accelsolution.dao.DetailleCommandeFacade;
import sn.accelsolution.dao.DetailleexpressionbesoinFacade;
import sn.accelsolution.dao.DevisFacade;
import sn.accelsolution.dao.DroitaccesFacade;
import sn.accelsolution.dao.ExpressionbesoinFacade;
import sn.accelsolution.dao.MenuFacade;
import sn.accelsolution.dao.NewfactureFacade;
import sn.accelsolution.dao.NotificationFacade;
import sn.accelsolution.dao.SalaireFacade;
import sn.accelsolution.dao.UtilisateurFacade;
import sn.accelsolution.entities.Actionmenu;
import sn.accelsolution.entities.Commande;
import sn.accelsolution.entities.Decomptep;
import sn.accelsolution.entities.DetailleCommande;
import sn.accelsolution.entities.Detailleexpressionbesoin;
import sn.accelsolution.entities.Devis;
import sn.accelsolution.entities.Droitacces;
import sn.accelsolution.entities.Expressionbesoin;
import sn.accelsolution.entities.Menu;
import sn.accelsolution.entities.Newfacture;
import sn.accelsolution.entities.Notification;
import sn.accelsolution.entities.Salaire;
import sn.accelsolution.entities.Utilisateur;
import sn.accelsolution.services.PrivillegeService;
import sn.accelsolution.util.FactureUtil;
import sn.accelsolution.util.MontantConverter;
import sn.accelsolution.util.PasswordGenerate;
import sn.accelsolution.util.SendEmail;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DELL
 */
@ManagedBean
@ViewScoped
public class EditUserBean implements Serializable {

    /**
     * Creates a new instance of ContratRecrutBean
     */
    @EJB
    ExpressionbesoinFacade ejbExpressionbesoin;
    @EJB
    UtilisateurFacade ejbUser;
    @EJB
    DevisFacade ejbDevis;
    @EJB
    DetailleexpressionbesoinFacade ejbDetailleExpressionbesoin;
    @EJB
    CommandeFacade ejbCommande;
    @EJB
    DetailleCommandeFacade ejbDetailleCommande;
    @EJB
    NotificationFacade ejbNotification;
    @EJB
    DecomptepFacade ejbDecomptep;
    @EJB
    DroitaccesFacade ejbDroitacces;
    @EJB
    MenuFacade ejbMenu;
    @EJB
    ActionmenuFacade ejbActionmenu;
    @EJB
    ActionactifFacade ejbActionactif;
    @EJB
    UtilisateurFacade ejbUtilisateur;
    
    private TreeNode root;
    private TreeNode[] selectedNodes;
    @ManagedProperty("#{privillegeService}")
    private PrivillegeService service;
    
    private Decomptep decomptep;
    private Notification notification;
    private String etatValidation;
    private String etatDecompte;
    private Utilisateur utilisateur;
    private List<Droitacces> allAcces;
    private List<Menu> allMenus;
    private List<Actionmenu> allActionmenus;
    
    @PostConstruct
    public void init() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        utilisateur = new Utilisateur();
        utilisateur = (Utilisateur) session.getAttribute("myUtilisateur");
        
        this.allAcces = new ArrayList<>();
        this.allMenus = new ArrayList<>();
        this.allActionmenus = new ArrayList<>();
        
        this.allAcces.clear();
        this.allAcces = ejbDroitacces.listOfDroitByUser(utilisateur);
        
        this.allMenus.clear();
        for (Droitacces dacces : this.allAcces) {
            List<Menu> givenListMenu = ejbMenu.listOfMenuByDroitAcces(dacces);
            for (Menu myMenu1 : givenListMenu) {
                this.allMenus.add(myMenu1);
            }
        }
        
        this.allActionmenus.clear();
        for (Menu myMenu2 : this.allMenus) {
            List<Actionmenu> givenListActionMenu = ejbActionmenu.listOfActionByMenu(myMenu2);
            for (Actionmenu myActionmenu : givenListActionMenu) {
                this.allActionmenus.add(myActionmenu);
            }
        }

        //root = service.createCheckboxDocuments();
        root = service.getCheckboxDocuments(this.allAcces, this.allMenus, this.allActionmenus);
        
    }
    
    public EditUserBean() {
        
    }
    
    public TreeNode getRoot() {
        return root;
    }
    
    public void setRoot(TreeNode root) {
        this.root = root;
    }
    
    public TreeNode[] getSelectedNodes() {
        return selectedNodes;
    }
    
    public void setSelectedNodes(TreeNode[] selectedNodes) {
        this.selectedNodes = selectedNodes;
    }
    
    public PrivillegeService getService() {
        return service;
    }
    
    public void setService(PrivillegeService service) {
        this.service = service;
    }
    
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
    
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    
    public Decomptep getDecomptep() {
        return decomptep;
    }
    
    public void setDecomptep(Decomptep decomptep) {
        this.decomptep = decomptep;
    }
    
    public String getEtatDecompte() {
        return etatDecompte;
    }
    
    public void setEtatDecompte(String etatDecompte) {
        this.etatDecompte = etatDecompte;
    }
    
    public String getEtatValidation() {
        return etatValidation;
    }
    
    public void setEtatValidation(String etatValidation) {
        this.etatValidation = etatValidation;
    }
    
    public Notification getNotification() {
        return notification;
    }
    
    public void setNotification(Notification notification) {
        this.notification = notification;
    }
    
    public void renderInfo() {
        
        try {
            
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    
    public String save() {
        try {
            
            UtilUtfconvert utfconvert = new UtilUtfconvert();
            this.utilisateur.setNomUtilisateur(utfconvert.convertFromUTF8(utilisateur.getNomUtilisateur()));
            this.utilisateur.setPrenomUtilisateur(utfconvert.convertFromUTF8(utilisateur.getPrenomUtilisateur()));
            this.utilisateur.setAdresseUtilisateur(utfconvert.convertFromUTF8(utilisateur.getAdresseUtilisateur()));
            this.utilisateur.setLieuNaissanceUtilisateur(utfconvert.convertFromUTF8(utilisateur.getLieuNaissanceUtilisateur()));
            this.utilisateur.setFonction(utfconvert.convertFromUTF8(utilisateur.getFonction()));
            this.utilisateur.setEtatpwd(utfconvert.convertFromUTF8(utilisateur.getEtatpwd()));
            this.utilisateur.setEtatcompte(utfconvert.convertFromUTF8(utilisateur.getEtatcompte()));
            ejbUtilisateur.edit(this.utilisateur);
            Utilisateur user = ejbUtilisateur.find(this.utilisateur.getIdUtilisateur());
            
            List<Droitacces> usersDroitacces = new ArrayList<>();
            List<Menu> usersMenus = new ArrayList<>();
            List<Actionmenu> usersActionmenus = new ArrayList<>();
            
            usersDroitacces.clear();
            usersDroitacces = ejbDroitacces.listOfDroitByUser(utilisateur);
            for (Droitacces droit : usersDroitacces) {
                
                usersMenus.clear();
                usersMenus = ejbMenu.listOfMenuByDroitAcces(droit);
                
                for (Menu menu : usersMenus) {
                    
                    usersActionmenus.clear();
                    usersActionmenus = ejbActionmenu.listOfActionByMenu(menu);
                    
                    for (Actionmenu actionmenu : usersActionmenus) {
                        ejbActionmenu.remove(actionmenu);
                    }
                    
                    ejbMenu.remove(menu);
                }
                ejbDroitacces.remove(droit);
            }
            
            int creationModuleConfiguration = 0;
            int creationMenurole = 0;
            int creationActionrole = 0;
            
            int creationModulePersonnel = 0;
            int creationMenupersonnel = 0;
            int creationActionpersonnel = 0;
            
            int creationModuleProjet = 0;
            int creationMenuProjet = 0;
            int creationActionProjet = 0;
            
            int creationModuleAppro = 0;
            int creationMenuAppro = 0;
            int creationActionAppro = 0;
            
            int creationModuleStock = 0;
            int creationMenuStock = 0;
            int creationActionStock = 0;
            
            int creationModuleOpportunite = 0;
            int creationMenuOpportunite = 0;
            int creationActionOpportunite = 0;
            
            int creationActionuser = 0;
            int creationActionstage = 0;
            int creationActionsalaire = 0;
            int creationActionpret = 0;
            int creationActionindemnites = 0;
            int creationActionDevis = 0;
            int creationActionMarches = 0;
            int creationActionFactures = 0;
            int creationActionDecomptes = 0;
            int creationActionChantiers = 0;
            int creationActionListprestataire = 0;
            int creationActionReferentielmod = 0;
            int creationActionAcomptes = 0;
            int creationActionTache = 0;
            int creationActionManagement = 0;
            int creationActionUnite = 0;
            int creationActionLottechnique = 0;
            int creationActionCorpsetat = 0;
            int creationActionMateriels = 0;
            int creationActionListfournisseur = 0;
            int creationActionReferentielfournisseur = 0;
            int creationActionAcomptefournisseur = 0;
            int creationActionPrix = 0;
            int creationActionExpression = 0;
            int creationActionBoncommande = 0;
            int creationActionApprochantier = 0;
            int creationActionInventaire = 0;
            int creationActionMajours = 0;
            int creationActionSortiestock = 0;
            int creationActionHistorique = 0;
            
            for (TreeNode nodeaffichage : this.selectedNodes) {
                String node = "";
                String nodeParent = "";
                node = nodeaffichage.getData().toString();
                nodeParent = nodeaffichage.getParent().getData().toString();

                //Gestion des roles
                if (node.equals("Gestion des rôles")) {
                    System.out.println("Premier cas");
                    if (creationModuleConfiguration == 0) {
                        //Creation module config

                        Droitacces droitacces0 = new Droitacces();
                        droitacces0.setModule("Accueil");
                        droitacces0.setIdUtilisateur(user);
                        droitacces0.setIdRole(user.getIdRole());
                        ejbDroitacces.insertDroit(droitacces0);
                        
                        Droitacces droitacces1 = new Droitacces();
                        droitacces1.setModule("Configuration");
                        droitacces1.setIdUtilisateur(user);
                        droitacces1.setIdRole(user.getIdRole());
                        ejbDroitacces.insertDroit(droitacces1);
                        System.out.println("Creation du module accueil et config");
                        creationModuleConfiguration++;
                    }
                    
                    if (creationMenurole == 0) {
                        //Creation menu role
                        Menu menu = new Menu();
                        int idP = ejbDroitacces.maxDroit();
                        Droitacces daac = ejbDroitacces.find(idP);
                        menu.setLibelemenu("Gestion des rôles");
                        menu.setIdPrivilege(daac);
                        ejbMenu.insertMenu(menu);
                        System.out.println("Creation du menu Gestion des rôles");
                        creationMenurole++;
                    }
                    
                    if (creationActionrole == 0) {
                        //Creation action role
                        List<TreeNode> listEnfants = nodeaffichage.getChildren();
                        for (TreeNode enfantNode : listEnfants) {
                            String actionRole = "";
                            actionRole = enfantNode.getData().toString();
                            Actionmenu actionmenu = new Actionmenu();
                            int idM = ejbMenu.maxMenu();
                            Menu mn = ejbMenu.find(idM);
                            actionmenu.setLibelleaction(actionRole);
                            actionmenu.setIdMenu(mn);
                            ejbActionmenu.insertAction(actionmenu);
                            System.out.println("Creation de l'action: " + actionRole);
                        }
                        creationActionrole++;
                    }
                    
                } else {
                    
                    if (nodeParent.equals("Gestion des rôles")) {
                        System.out.println("Second cas");
                        if (creationModuleConfiguration == 0) {
                            //Creation module config

                            Droitacces droitacces0 = new Droitacces();
                            droitacces0.setModule("Accueil");
                            droitacces0.setIdUtilisateur(user);
                            droitacces0.setIdRole(user.getIdRole());
                            ejbDroitacces.insertDroit(droitacces0);
                            
                            Droitacces droitacces1 = new Droitacces();
                            droitacces1.setModule("Configuration");
                            droitacces1.setIdUtilisateur(user);
                            droitacces1.setIdRole(user.getIdRole());
                            ejbDroitacces.insertDroit(droitacces1);
                            System.out.println("Creation du module accueil et config");
                            creationModuleConfiguration++;
                        }
                        
                        if (creationMenurole == 0) {
                            //Creation menu role
                            Menu menu = new Menu();
                            int idP = ejbDroitacces.maxDroit();
                            Droitacces daac = ejbDroitacces.find(idP);
                            menu.setLibelemenu("Gestion des rôles");
                            menu.setIdPrivilege(daac);
                            ejbMenu.insertMenu(menu);
                            System.out.println("Creation du menu Gestion des rôles");
                            creationMenurole++;
                        }
                        
                        if (creationActionrole == 0) {
                            //Creation action role

                            for (TreeNode enfantNode : this.selectedNodes) {
                                if (enfantNode.getParent().getData().toString().equalsIgnoreCase("Gestion des rôles")) {
                                    String actionRole = "";
                                    actionRole = enfantNode.getData().toString();
                                    Actionmenu actionmenu = new Actionmenu();
                                    int idM = ejbMenu.maxMenu();
                                    Menu mn = ejbMenu.find(idM);
                                    actionmenu.setLibelleaction(actionRole);
                                    actionmenu.setIdMenu(mn);
                                    ejbActionmenu.insertAction(actionmenu);
                                    System.out.println("Creation de l'action: " + actionRole);
                                }
                                
                            }
                            creationActionrole++;
                        }
                    }
                    
                }

                //Gestion des utilisateurs
                if (node.equals("Gestion des utilisateurs")) {
                    System.out.println("Parent: Gestion des utilisateurs");
                    if (creationModuleConfiguration == 0) {
                        //Creation module config

                        Droitacces droitacces0 = new Droitacces();
                        droitacces0.setModule("Accueil");
                        droitacces0.setIdUtilisateur(user);
                        droitacces0.setIdRole(user.getIdRole());
                        ejbDroitacces.insertDroit(droitacces0);
                        
                        Droitacces droitacces1 = new Droitacces();
                        droitacces1.setModule("Configuration");
                        droitacces1.setIdUtilisateur(user);
                        droitacces1.setIdRole(user.getIdRole());
                        ejbDroitacces.insertDroit(droitacces1);
                        System.out.println("Creation du module accueil et config");
                        creationModuleConfiguration++;
                    }
                    
                    if (creationActionuser == 0) {
                        //Creation menu utilisateur
                        Menu menu = new Menu();
                        int idP = ejbDroitacces.maxDroit();
                        Droitacces daac = ejbDroitacces.find(idP);
                        menu.setLibelemenu("Gestion des utilisateurs");
                        menu.setIdPrivilege(daac);
                        ejbMenu.insertMenu(menu);
                        //Creation action role
                        List<TreeNode> listEnfants = nodeaffichage.getChildren();
                        for (TreeNode enfantNode : listEnfants) {
                            String actionUser = "";
                            actionUser = enfantNode.getData().toString();
                            Actionmenu actionmenu = new Actionmenu();
                            int idM = ejbMenu.maxMenu();
                            Menu mn = ejbMenu.find(idM);
                            actionmenu.setLibelleaction(actionUser);
                            actionmenu.setIdMenu(mn);
                            ejbActionmenu.insertAction(actionmenu);
                            System.out.println("Enfant user: " + actionUser);
                        }
                        creationActionuser++;
                    }
                    
                } else {
                    
                    if (nodeParent.equals("Gestion des utilisateurs")) {
                        System.out.println("Second cas");
                        if (creationModuleConfiguration == 0) {
                            //Creation module config

                            Droitacces droitacces0 = new Droitacces();
                            droitacces0.setModule("Accueil");
                            droitacces0.setIdUtilisateur(user);
                            droitacces0.setIdRole(user.getIdRole());
                            ejbDroitacces.insertDroit(droitacces0);
                            
                            Droitacces droitacces1 = new Droitacces();
                            droitacces1.setModule("Configuration");
                            droitacces1.setIdUtilisateur(user);
                            droitacces1.setIdRole(user.getIdRole());
                            ejbDroitacces.insertDroit(droitacces1);
                            System.out.println("Creation du module accueil et config");
                            creationModuleConfiguration++;
                        }
                        if (creationActionuser == 0) {
                            //Creation menu utilisateur
                            Menu menu = new Menu();
                            int idP = ejbDroitacces.maxDroit();
                            Droitacces daac = ejbDroitacces.find(idP);
                            menu.setLibelemenu("Gestion des utilisateurs");
                            menu.setIdPrivilege(daac);
                            ejbMenu.insertMenu(menu);
                            //Creation action role
                            for (TreeNode enfantNode : this.selectedNodes) {
                                if (enfantNode.getParent().getData().toString().equalsIgnoreCase("Gestion des utilisateurs")) {
                                    String actionUser = "";
                                    actionUser = enfantNode.getData().toString();
                                    Actionmenu actionmenu = new Actionmenu();
                                    int idM = ejbMenu.maxMenu();
                                    Menu mn = ejbMenu.find(idM);
                                    actionmenu.setLibelleaction(actionUser);
                                    actionmenu.setIdMenu(mn);
                                    ejbActionmenu.insertAction(actionmenu);
                                    System.out.println("Enfant user: " + actionUser);
                                }
                                
                            }
                            creationActionuser++;
                        }
                        
                    }
                    
                }

//                    Gestion des recrutement
                if (node.equals("Recrutement")) {
                    System.out.println("Parent: Recrutement");
                    if (creationModulePersonnel == 0) {
                        //Creation module personnel
                        Droitacces droitacces1 = new Droitacces();
                        droitacces1.setModule("Personnel");
                        droitacces1.setIdUtilisateur(user);
                        droitacces1.setIdRole(user.getIdRole());
                        ejbDroitacces.insertDroit(droitacces1);
                        creationModulePersonnel++;
                    }
                    
                    if (creationMenupersonnel == 0) {
                        //Creation menu personnel
                        Menu menu = new Menu();
                        int idP = ejbDroitacces.maxDroit();
                        Droitacces daac = ejbDroitacces.find(idP);
                        menu.setLibelemenu("Recrutement");
                        menu.setIdPrivilege(daac);
                        ejbMenu.insertMenu(menu);
                        creationMenupersonnel++;
                    }
                    
                    if (creationActionpersonnel == 0) {
                        //Creation action personnel
                        List<TreeNode> listEnfants = nodeaffichage.getChildren();
                        for (TreeNode enfantNode : listEnfants) {
                            String actionRecrutement = "";
                            actionRecrutement = enfantNode.getData().toString();
                            Actionmenu actionmenu = new Actionmenu();
                            int idM = ejbMenu.maxMenu();
                            Menu mn = ejbMenu.find(idM);
                            actionmenu.setLibelleaction(actionRecrutement);
                            actionmenu.setIdMenu(mn);
                            ejbActionmenu.insertAction(actionmenu);
                            System.out.println("Enfant Recrutement: " + actionRecrutement);
                        }
                        creationActionpersonnel++;
                    }
                    
                } else {
                    
                    if (nodeParent.equals("Recrutement")) {
                        System.out.println("Second cas");
                        
                        if (creationModulePersonnel == 0) {
                            //Creation module personnel
                            Droitacces droitacces1 = new Droitacces();
                            droitacces1.setModule("Personnel");
                            droitacces1.setIdUtilisateur(user);
                            droitacces1.setIdRole(user.getIdRole());
                            ejbDroitacces.insertDroit(droitacces1);
                            creationModulePersonnel++;
                        }
                        
                        if (creationMenupersonnel == 0) {
                            //Creation menu personnel
                            Menu menu = new Menu();
                            int idP = ejbDroitacces.maxDroit();
                            Droitacces daac = ejbDroitacces.find(idP);
                            menu.setLibelemenu("Recrutement");
                            menu.setIdPrivilege(daac);
                            ejbMenu.insertMenu(menu);
                            creationMenupersonnel++;
                        }
                        
                        if (creationActionpersonnel == 0) {
                            //Creation action personnel
                            for (TreeNode enfantNode : this.selectedNodes) {
                                if (enfantNode.getParent().getData().toString().equalsIgnoreCase("Recrutement")) {
                                    String actionRecrutement = "";
                                    actionRecrutement = enfantNode.getData().toString();
                                    Actionmenu actionmenu = new Actionmenu();
                                    int idM = ejbMenu.maxMenu();
                                    Menu mn = ejbMenu.find(idM);
                                    actionmenu.setLibelleaction(actionRecrutement);
                                    actionmenu.setIdMenu(mn);
                                    ejbActionmenu.insertAction(actionmenu);
                                    System.out.println("Enfant Recrutement: " + actionRecrutement);
                                }
                                
                            }
                            creationActionpersonnel++;
                        }
                        
                    }
                    
                }

                //Gestion des stage
                if (node.equals("Stage")) {
                    System.out.println("Parent: Stage");
                    if (creationModulePersonnel == 0) {
                        //Creation module personnel
                        Droitacces droitacces1 = new Droitacces();
                        droitacces1.setModule("Personnel");
                        droitacces1.setIdUtilisateur(user);
                        droitacces1.setIdRole(user.getIdRole());
                        ejbDroitacces.insertDroit(droitacces1);
                        creationModulePersonnel++;
                    }
                    if (creationActionstage == 0) {
                        //Creation menu utilisateur
                        Menu menu = new Menu();
                        int idP = ejbDroitacces.maxDroit();
                        Droitacces daac = ejbDroitacces.find(idP);
                        menu.setLibelemenu("Stage");
                        menu.setIdPrivilege(daac);
                        ejbMenu.insertMenu(menu);
                        //Creation action stage
                        List<TreeNode> listEnfants = nodeaffichage.getChildren();
                        for (TreeNode enfantNode : listEnfants) {
                            String actionStage = "";
                            actionStage = enfantNode.getData().toString();
                            Actionmenu actionmenu = new Actionmenu();
                            int idM = ejbMenu.maxMenu();
                            Menu mn = ejbMenu.find(idM);
                            actionmenu.setLibelleaction(actionStage);
                            actionmenu.setIdMenu(mn);
                            ejbActionmenu.insertAction(actionmenu);
                            System.out.println("Enfant Stage: " + actionStage);
                        }
                        creationActionstage++;
                    }
                    
                } else {
                    
                    if (nodeParent.equals("Stage")) {
                        System.out.println("Second cas");
                        if (creationModulePersonnel == 0) {
                            //Creation module personnel
                            Droitacces droitacces1 = new Droitacces();
                            droitacces1.setModule("Personnel");
                            droitacces1.setIdUtilisateur(user);
                            droitacces1.setIdRole(user.getIdRole());
                            ejbDroitacces.insertDroit(droitacces1);
                            creationModulePersonnel++;
                        }
                        if (creationActionstage == 0) {
                            //Creation menu utilisateur
                            Menu menu = new Menu();
                            int idP = ejbDroitacces.maxDroit();
                            Droitacces daac = ejbDroitacces.find(idP);
                            menu.setLibelemenu("Stage");
                            menu.setIdPrivilege(daac);
                            ejbMenu.insertMenu(menu);
                            //Creation action stage
                            for (TreeNode enfantNode : this.selectedNodes) {
                                if (enfantNode.getParent().getData().toString().equalsIgnoreCase("Stage")) {
                                    String actionStage = "";
                                    actionStage = enfantNode.getData().toString();
                                    Actionmenu actionmenu = new Actionmenu();
                                    int idM = ejbMenu.maxMenu();
                                    Menu mn = ejbMenu.find(idM);
                                    actionmenu.setLibelleaction(actionStage);
                                    actionmenu.setIdMenu(mn);
                                    ejbActionmenu.insertAction(actionmenu);
                                    System.out.println("Enfant Stage: " + actionStage);
                                }
                                
                            }
                            creationActionstage++;
                        }
                        
                    }
                    
                }

                //Gestion des salaire
                if (node.equals("Salaire")) {
                    System.out.println("Parent: Salaire");
                    if (creationModulePersonnel == 0) {
                        //Creation module personnel
                        Droitacces droitacces1 = new Droitacces();
                        droitacces1.setModule("Personnel");
                        droitacces1.setIdUtilisateur(user);
                        droitacces1.setIdRole(user.getIdRole());
                        ejbDroitacces.insertDroit(droitacces1);
                        creationModulePersonnel++;
                    }
                    if (creationActionsalaire == 0) {
                        //Creation menu utilisateur
                        Menu menu = new Menu();
                        int idP = ejbDroitacces.maxDroit();
                        Droitacces daac = ejbDroitacces.find(idP);
                        menu.setLibelemenu("Salaire");
                        menu.setIdPrivilege(daac);
                        ejbMenu.insertMenu(menu);
                        //Creation action stage
                        List<TreeNode> listEnfants = nodeaffichage.getChildren();
                        for (TreeNode enfantNode : listEnfants) {
                            String actionSalaire = "";
                            actionSalaire = enfantNode.getData().toString();
                            Actionmenu actionmenu = new Actionmenu();
                            int idM = ejbMenu.maxMenu();
                            Menu mn = ejbMenu.find(idM);
                            actionmenu.setLibelleaction(actionSalaire);
                            actionmenu.setIdMenu(mn);
                            ejbActionmenu.insertAction(actionmenu);
                            System.out.println("Enfant Salaire: " + actionSalaire);
                        }
                        creationActionsalaire++;
                    }
                    
                } else {
                    
                    if (nodeParent.equals("Salaire")) {
                        System.out.println("Second cas");
                        if (creationModulePersonnel == 0) {
                            //Creation module personnel
                            Droitacces droitacces1 = new Droitacces();
                            droitacces1.setModule("Personnel");
                            droitacces1.setIdUtilisateur(user);
                            droitacces1.setIdRole(user.getIdRole());
                            ejbDroitacces.insertDroit(droitacces1);
                            creationModulePersonnel++;
                        }
                        if (creationActionsalaire == 0) {
                            //Creation menu utilisateur
                            Menu menu = new Menu();
                            int idP = ejbDroitacces.maxDroit();
                            Droitacces daac = ejbDroitacces.find(idP);
                            menu.setLibelemenu("Salaire");
                            menu.setIdPrivilege(daac);
                            ejbMenu.insertMenu(menu);
                            //Creation action stage
                            for (TreeNode enfantNode : this.selectedNodes) {
                                if (enfantNode.getParent().getData().toString().equalsIgnoreCase("Salaire")) {
                                    String actionSalaire = "";
                                    actionSalaire = enfantNode.getData().toString();
                                    Actionmenu actionmenu = new Actionmenu();
                                    int idM = ejbMenu.maxMenu();
                                    Menu mn = ejbMenu.find(idM);
                                    actionmenu.setLibelleaction(actionSalaire);
                                    actionmenu.setIdMenu(mn);
                                    ejbActionmenu.insertAction(actionmenu);
                                    System.out.println("Enfant Salaire: " + actionSalaire);
                                }
                                
                            }
                            creationActionsalaire++;
                        }
                        
                    }
                    
                }

                //Gestion des pret
                if (node.equals("Prêt")) {
                    System.out.println("Parent: Prêt");
                    if (creationModulePersonnel == 0) {
                        //Creation module personnel
                        Droitacces droitacces1 = new Droitacces();
                        droitacces1.setModule("Personnel");
                        droitacces1.setIdUtilisateur(user);
                        droitacces1.setIdRole(user.getIdRole());
                        ejbDroitacces.insertDroit(droitacces1);
                        creationModulePersonnel++;
                    }
                    if (creationActionpret == 0) {
                        //Creation menu utilisateur
                        Menu menu = new Menu();
                        int idP = ejbDroitacces.maxDroit();
                        Droitacces daac = ejbDroitacces.find(idP);
                        menu.setLibelemenu("Prêt");
                        menu.setIdPrivilege(daac);
                        ejbMenu.insertMenu(menu);
                        //Creation action stage
                        List<TreeNode> listEnfants = nodeaffichage.getChildren();
                        for (TreeNode enfantNode : listEnfants) {
                            String actionPret = "";
                            actionPret = enfantNode.getData().toString();
                            Actionmenu actionmenu = new Actionmenu();
                            int idM = ejbMenu.maxMenu();
                            Menu mn = ejbMenu.find(idM);
                            actionmenu.setLibelleaction(actionPret);
                            actionmenu.setIdMenu(mn);
                            ejbActionmenu.insertAction(actionmenu);
                            System.out.println("Enfant Prêt: " + actionPret);
                        }
                        creationActionpret++;
                    }
                    
                } else {
                    
                    if (nodeParent.equals("Prêt")) {
                        System.out.println("Second cas");
                        if (creationModulePersonnel == 0) {
                            //Creation module personnel
                            Droitacces droitacces1 = new Droitacces();
                            droitacces1.setModule("Personnel");
                            droitacces1.setIdUtilisateur(user);
                            droitacces1.setIdRole(user.getIdRole());
                            ejbDroitacces.insertDroit(droitacces1);
                            creationModulePersonnel++;
                        }
                        if (creationActionpret == 0) {
                            //Creation menu utilisateur
                            Menu menu = new Menu();
                            int idP = ejbDroitacces.maxDroit();
                            Droitacces daac = ejbDroitacces.find(idP);
                            menu.setLibelemenu("Prêt");
                            menu.setIdPrivilege(daac);
                            ejbMenu.insertMenu(menu);
                            //Creation action stage
                            for (TreeNode enfantNode : this.selectedNodes) {
                                if (enfantNode.getParent().getData().toString().equalsIgnoreCase("Prêt")) {
                                    String actionPret = "";
                                    actionPret = enfantNode.getData().toString();
                                    Actionmenu actionmenu = new Actionmenu();
                                    int idM = ejbMenu.maxMenu();
                                    Menu mn = ejbMenu.find(idM);
                                    actionmenu.setLibelleaction(actionPret);
                                    actionmenu.setIdMenu(mn);
                                    ejbActionmenu.insertAction(actionmenu);
                                    System.out.println("Enfant Prêt: " + actionPret);
                                }
                                
                            }
                            creationActionpret++;
                        }
                        
                    }
                    
                }

                //Gestion des indemnites
                if (node.equals("Indemnités")) {
                    System.out.println("Parent: Indemnités");
                    if (creationModulePersonnel == 0) {
                        //Creation module personnel
                        Droitacces droitacces1 = new Droitacces();
                        droitacces1.setModule("Personnel");
                        droitacces1.setIdUtilisateur(user);
                        droitacces1.setIdRole(user.getIdRole());
                        ejbDroitacces.insertDroit(droitacces1);
                        creationModulePersonnel++;
                    }
                    if (creationActionindemnites == 0) {
                        //Creation menu utilisateur
                        Menu menu = new Menu();
                        int idP = ejbDroitacces.maxDroit();
                        Droitacces daac = ejbDroitacces.find(idP);
                        menu.setLibelemenu("Indemnités");
                        menu.setIdPrivilege(daac);
                        ejbMenu.insertMenu(menu);
                        //Creation action stage
                        List<TreeNode> listEnfants = nodeaffichage.getChildren();
                        for (TreeNode enfantNode : listEnfants) {
                            String actionIndemnites = "";
                            actionIndemnites = enfantNode.getData().toString();
                            Actionmenu actionmenu = new Actionmenu();
                            int idM = ejbMenu.maxMenu();
                            Menu mn = ejbMenu.find(idM);
                            actionmenu.setLibelleaction(actionIndemnites);
                            actionmenu.setIdMenu(mn);
                            ejbActionmenu.insertAction(actionmenu);
                            System.out.println("Enfant Indemnités: " + actionIndemnites);
                        }
                        creationActionindemnites++;
                    }
                    
                } else {
                    
                    if (nodeParent.equals("Indemnités")) {
                        System.out.println("Second cas");
                        if (creationModulePersonnel == 0) {
                            //Creation module personnel
                            Droitacces droitacces1 = new Droitacces();
                            droitacces1.setModule("Personnel");
                            droitacces1.setIdUtilisateur(user);
                            droitacces1.setIdRole(user.getIdRole());
                            ejbDroitacces.insertDroit(droitacces1);
                            creationModulePersonnel++;
                        }
                        if (creationActionindemnites == 0) {
                            //Creation menu utilisateur
                            Menu menu = new Menu();
                            int idP = ejbDroitacces.maxDroit();
                            Droitacces daac = ejbDroitacces.find(idP);
                            menu.setLibelemenu("Indemnités");
                            menu.setIdPrivilege(daac);
                            ejbMenu.insertMenu(menu);
                            //Creation action stage
                            for (TreeNode enfantNode : this.selectedNodes) {
                                if (enfantNode.getParent().getData().toString().equalsIgnoreCase("Indemnités")) {
                                    String actionIndemnites = "";
                                    actionIndemnites = enfantNode.getData().toString();
                                    Actionmenu actionmenu = new Actionmenu();
                                    int idM = ejbMenu.maxMenu();
                                    Menu mn = ejbMenu.find(idM);
                                    actionmenu.setLibelleaction(actionIndemnites);
                                    actionmenu.setIdMenu(mn);
                                    ejbActionmenu.insertAction(actionmenu);
                                    System.out.println("Enfant Indemnités: " + actionIndemnites);
                                }
                                
                            }
                            creationActionindemnites++;
                        }
                        
                    }
                    
                }

                //Gestion des client
                if (node.equals("Clients")) {
                    System.out.println("Parent: Client");
                    if (creationModuleProjet == 0) {
                        //Creation module personnel
                        Droitacces droitacces1 = new Droitacces();
                        droitacces1.setModule("Projets");
                        droitacces1.setIdUtilisateur(user);
                        droitacces1.setIdRole(user.getIdRole());
                        ejbDroitacces.insertDroit(droitacces1);
                        creationModuleProjet++;
                    }
                    
                    if (creationMenuProjet == 0) {
                        //Creation menu personnel
                        Menu menu = new Menu();
                        int idP = ejbDroitacces.maxDroit();
                        Droitacces daac = ejbDroitacces.find(idP);
                        menu.setLibelemenu("Clients");
                        menu.setIdPrivilege(daac);
                        ejbMenu.insertMenu(menu);
                        creationMenuProjet++;
                    }
                    
                    if (creationActionProjet == 0) {
                        //Creation action personnel
                        List<TreeNode> listEnfants = nodeaffichage.getChildren();
                        for (TreeNode enfantNode : listEnfants) {
                            String actionClient = "";
                            actionClient = enfantNode.getData().toString();
                            Actionmenu actionmenu = new Actionmenu();
                            int idM = ejbMenu.maxMenu();
                            Menu mn = ejbMenu.find(idM);
                            actionmenu.setLibelleaction(actionClient);
                            actionmenu.setIdMenu(mn);
                            ejbActionmenu.insertAction(actionmenu);
                            System.out.println("Enfant Client: " + actionClient);
                        }
                        creationActionProjet++;
                    }
                    
                } else {
                    
                    if (nodeParent.equals("Clients")) {
                        System.out.println("Second cas");
                        if (creationModuleProjet == 0) {
                            //Creation module personnel
                            Droitacces droitacces1 = new Droitacces();
                            droitacces1.setModule("Projets");
                            droitacces1.setIdUtilisateur(user);
                            droitacces1.setIdRole(user.getIdRole());
                            ejbDroitacces.insertDroit(droitacces1);
                            creationModuleProjet++;
                        }
                        if (creationModuleProjet == 0) {
                            //Creation module personnel
                            Droitacces droitacces1 = new Droitacces();
                            droitacces1.setModule("Projets");
                            droitacces1.setIdUtilisateur(user);
                            droitacces1.setIdRole(user.getIdRole());
                            ejbDroitacces.insertDroit(droitacces1);
                            creationModuleProjet++;
                        }
                        
                        if (creationMenuProjet == 0) {
                            //Creation menu personnel
                            Menu menu = new Menu();
                            int idP = ejbDroitacces.maxDroit();
                            Droitacces daac = ejbDroitacces.find(idP);
                            menu.setLibelemenu("Clients");
                            menu.setIdPrivilege(daac);
                            ejbMenu.insertMenu(menu);
                            creationMenuProjet++;
                        }
                        
                        if (creationActionProjet == 0) {
                            //Creation action personnel
                            for (TreeNode enfantNode : this.selectedNodes) {
                                if (enfantNode.getParent().getData().toString().equalsIgnoreCase("Clients")) {
                                    String actionClient = "";
                                    actionClient = enfantNode.getData().toString();
                                    Actionmenu actionmenu = new Actionmenu();
                                    int idM = ejbMenu.maxMenu();
                                    Menu mn = ejbMenu.find(idM);
                                    actionmenu.setLibelleaction(actionClient);
                                    actionmenu.setIdMenu(mn);
                                    ejbActionmenu.insertAction(actionmenu);
                                    System.out.println("Enfant Client: " + actionClient);
                                }
                                
                            }
                            creationActionProjet++;
                        }
                        
                    }
                    
                }

                //Gestion des Devis
                if (node.equals("Devis")) {
                    System.out.println("Parent: Devis");
                    if (creationModuleProjet == 0) {
                        //Creation module personnel
                        Droitacces droitacces1 = new Droitacces();
                        droitacces1.setModule("Projets");
                        droitacces1.setIdUtilisateur(user);
                        droitacces1.setIdRole(user.getIdRole());
                        ejbDroitacces.insertDroit(droitacces1);
                        creationModuleProjet++;
                    }
                    if (creationActionDevis == 0) {
                        //Creation menu utilisateur
                        Menu menu = new Menu();
                        int idP = ejbDroitacces.maxDroit();
                        Droitacces daac = ejbDroitacces.find(idP);
                        menu.setLibelemenu("Devis");
                        menu.setIdPrivilege(daac);
                        ejbMenu.insertMenu(menu);
                        //Creation action stage
                        List<TreeNode> listEnfants = nodeaffichage.getChildren();
                        for (TreeNode enfantNode : listEnfants) {
                            String actionDevis = "";
                            actionDevis = enfantNode.getData().toString();
                            Actionmenu actionmenu = new Actionmenu();
                            int idM = ejbMenu.maxMenu();
                            Menu mn = ejbMenu.find(idM);
                            actionmenu.setLibelleaction(actionDevis);
                            actionmenu.setIdMenu(mn);
                            ejbActionmenu.insertAction(actionmenu);
                            System.out.println("Enfant Devis: " + actionDevis);
                        }
                        creationActionDevis++;
                    }
                    
                } else {
                    
                    if (nodeParent.equals("Devis")) {
                        System.out.println("Second cas");
                        if (creationModuleProjet == 0) {
                            //Creation module personnel
                            Droitacces droitacces1 = new Droitacces();
                            droitacces1.setModule("Projets");
                            droitacces1.setIdUtilisateur(user);
                            droitacces1.setIdRole(user.getIdRole());
                            ejbDroitacces.insertDroit(droitacces1);
                            creationModuleProjet++;
                        }
                        if (creationActionDevis == 0) {
                            //Creation menu utilisateur
                            Menu menu = new Menu();
                            int idP = ejbDroitacces.maxDroit();
                            Droitacces daac = ejbDroitacces.find(idP);
                            menu.setLibelemenu("Devis");
                            menu.setIdPrivilege(daac);
                            ejbMenu.insertMenu(menu);
                            //Creation action stage
                            for (TreeNode enfantNode : this.selectedNodes) {
                                if (enfantNode.getParent().getData().toString().equalsIgnoreCase("Devis")) {
                                    String actionDevis = "";
                                    actionDevis = enfantNode.getData().toString();
                                    Actionmenu actionmenu = new Actionmenu();
                                    int idM = ejbMenu.maxMenu();
                                    Menu mn = ejbMenu.find(idM);
                                    actionmenu.setLibelleaction(actionDevis);
                                    actionmenu.setIdMenu(mn);
                                    ejbActionmenu.insertAction(actionmenu);
                                    System.out.println("Enfant Devis: " + actionDevis);
                                }
                                
                            }
                            creationActionDevis++;
                        }
                        
                    }
                    
                }

                //Gestion des Marchés
                if (node.equals("Marchés")) {
                    System.out.println("Parent: Marchés");
                    if (creationModuleProjet == 0) {
                        //Creation module personnel
                        Droitacces droitacces1 = new Droitacces();
                        droitacces1.setModule("Projets");
                        droitacces1.setIdUtilisateur(user);
                        droitacces1.setIdRole(user.getIdRole());
                        ejbDroitacces.insertDroit(droitacces1);
                        creationModuleProjet++;
                    }
                    if (creationActionMarches == 0) {
                        //Creation menu utilisateur
                        Menu menu = new Menu();
                        int idP = ejbDroitacces.maxDroit();
                        Droitacces daac = ejbDroitacces.find(idP);
                        menu.setLibelemenu("Marchés");
                        menu.setIdPrivilege(daac);
                        ejbMenu.insertMenu(menu);
                        //Creation action stage
                        List<TreeNode> listEnfants = nodeaffichage.getChildren();
                        for (TreeNode enfantNode : listEnfants) {
                            String actionMarches = "";
                            actionMarches = enfantNode.getData().toString();
                            Actionmenu actionmenu = new Actionmenu();
                            int idM = ejbMenu.maxMenu();
                            Menu mn = ejbMenu.find(idM);
                            actionmenu.setLibelleaction(actionMarches);
                            actionmenu.setIdMenu(mn);
                            ejbActionmenu.insertAction(actionmenu);
                            System.out.println("Enfant Marchés: " + actionMarches);
                        }
                        creationActionMarches++;
                    }
                    
                } else {
                    
                    if (nodeParent.equals("Marchés")) {
                        System.out.println("Second cas");
                        if (creationModuleProjet == 0) {
                            //Creation module personnel
                            Droitacces droitacces1 = new Droitacces();
                            droitacces1.setModule("Projets");
                            droitacces1.setIdUtilisateur(user);
                            droitacces1.setIdRole(user.getIdRole());
                            ejbDroitacces.insertDroit(droitacces1);
                            creationModuleProjet++;
                        }
                        if (creationActionMarches == 0) {
                            //Creation menu utilisateur
                            Menu menu = new Menu();
                            int idP = ejbDroitacces.maxDroit();
                            Droitacces daac = ejbDroitacces.find(idP);
                            menu.setLibelemenu("Marchés");
                            menu.setIdPrivilege(daac);
                            ejbMenu.insertMenu(menu);
                            //Creation action stage
                            for (TreeNode enfantNode : this.selectedNodes) {
                                if (enfantNode.getParent().getData().toString().equalsIgnoreCase("Marchés")) {
                                    String actionMarches = "";
                                    actionMarches = enfantNode.getData().toString();
                                    Actionmenu actionmenu = new Actionmenu();
                                    int idM = ejbMenu.maxMenu();
                                    Menu mn = ejbMenu.find(idM);
                                    actionmenu.setLibelleaction(actionMarches);
                                    actionmenu.setIdMenu(mn);
                                    ejbActionmenu.insertAction(actionmenu);
                                    System.out.println("Enfant Marchés: " + actionMarches);
                                }
                                
                            }
                            creationActionMarches++;
                        }
                        
                    }
                    
                }

                //Gestion des facture
                if (node.equals("Factures")) {
                    System.out.println("Parent: Factures");
                    if (creationModuleProjet == 0) {
                        //Creation module personnel
                        Droitacces droitacces1 = new Droitacces();
                        droitacces1.setModule("Projets");
                        droitacces1.setIdUtilisateur(user);
                        droitacces1.setIdRole(user.getIdRole());
                        ejbDroitacces.insertDroit(droitacces1);
                        creationModuleProjet++;
                    }
                    if (creationActionFactures == 0) {
                        //Creation menu utilisateur
                        Menu menu = new Menu();
                        int idP = ejbDroitacces.maxDroit();
                        Droitacces daac = ejbDroitacces.find(idP);
                        menu.setLibelemenu("Factures");
                        menu.setIdPrivilege(daac);
                        ejbMenu.insertMenu(menu);
                        //Creation action stage
                        List<TreeNode> listEnfants = nodeaffichage.getChildren();
                        for (TreeNode enfantNode : listEnfants) {
                            String actionFactures = "";
                            actionFactures = enfantNode.getData().toString();
                            Actionmenu actionmenu = new Actionmenu();
                            int idM = ejbMenu.maxMenu();
                            Menu mn = ejbMenu.find(idM);
                            actionmenu.setLibelleaction(actionFactures);
                            actionmenu.setIdMenu(mn);
                            ejbActionmenu.insertAction(actionmenu);
                            System.out.println("Enfant Factures: " + actionFactures);
                        }
                        creationActionFactures++;
                    }
                    
                } else {
                    
                    if (nodeParent.equals("Factures")) {
                        System.out.println("Second cas");
                        if (creationModuleProjet == 0) {
                            //Creation module personnel
                            Droitacces droitacces1 = new Droitacces();
                            droitacces1.setModule("Projets");
                            droitacces1.setIdUtilisateur(user);
                            droitacces1.setIdRole(user.getIdRole());
                            ejbDroitacces.insertDroit(droitacces1);
                            creationModuleProjet++;
                        }
                        if (creationActionFactures == 0) {
                            //Creation menu utilisateur
                            Menu menu = new Menu();
                            int idP = ejbDroitacces.maxDroit();
                            Droitacces daac = ejbDroitacces.find(idP);
                            menu.setLibelemenu("Factures");
                            menu.setIdPrivilege(daac);
                            ejbMenu.insertMenu(menu);
                            //Creation action stage
                            for (TreeNode enfantNode : this.selectedNodes) {
                                if (enfantNode.getParent().getData().toString().equalsIgnoreCase("Factures")) {
                                    String actionFactures = "";
                                    actionFactures = enfantNode.getData().toString();
                                    Actionmenu actionmenu = new Actionmenu();
                                    int idM = ejbMenu.maxMenu();
                                    Menu mn = ejbMenu.find(idM);
                                    actionmenu.setLibelleaction(actionFactures);
                                    actionmenu.setIdMenu(mn);
                                    ejbActionmenu.insertAction(actionmenu);
                                    System.out.println("Enfant Factures: " + actionFactures);
                                }
                                
                            }
                            creationActionFactures++;
                        }
                        
                    }
                    
                }

                //Gestion des Decomptes
                if (node.equals("Decomptes")) {
                    System.out.println("Parent: Decomptes");
                    if (creationModuleProjet == 0) {
                        //Creation module personnel
                        Droitacces droitacces1 = new Droitacces();
                        droitacces1.setModule("Projets");
                        droitacces1.setIdUtilisateur(user);
                        droitacces1.setIdRole(user.getIdRole());
                        ejbDroitacces.insertDroit(droitacces1);
                        creationModuleProjet++;
                    }
                    if (creationActionDecomptes == 0) {
                        //Creation menu utilisateur
                        Menu menu = new Menu();
                        int idP = ejbDroitacces.maxDroit();
                        Droitacces daac = ejbDroitacces.find(idP);
                        menu.setLibelemenu("Decomptes");
                        menu.setIdPrivilege(daac);
                        ejbMenu.insertMenu(menu);
                        //Creation action stage
                        List<TreeNode> listEnfants = nodeaffichage.getChildren();
                        for (TreeNode enfantNode : listEnfants) {
                            String actionDecomptes = "";
                            actionDecomptes = enfantNode.getData().toString();
                            Actionmenu actionmenu = new Actionmenu();
                            int idM = ejbMenu.maxMenu();
                            Menu mn = ejbMenu.find(idM);
                            actionmenu.setLibelleaction(actionDecomptes);
                            actionmenu.setIdMenu(mn);
                            ejbActionmenu.insertAction(actionmenu);
                            System.out.println("Enfant Decomptes: " + actionDecomptes);
                        }
                        creationActionDecomptes++;
                    }
                    
                } else {
                    
                    if (nodeParent.equals("Decomptes")) {
                        System.out.println("Second cas");
                        if (creationModuleProjet == 0) {
                            //Creation module personnel
                            Droitacces droitacces1 = new Droitacces();
                            droitacces1.setModule("Projets");
                            droitacces1.setIdUtilisateur(user);
                            droitacces1.setIdRole(user.getIdRole());
                            ejbDroitacces.insertDroit(droitacces1);
                            creationModuleProjet++;
                        }
                        if (creationActionDecomptes == 0) {
                            //Creation menu utilisateur
                            Menu menu = new Menu();
                            int idP = ejbDroitacces.maxDroit();
                            Droitacces daac = ejbDroitacces.find(idP);
                            menu.setLibelemenu("Decomptes");
                            menu.setIdPrivilege(daac);
                            ejbMenu.insertMenu(menu);
                            //Creation action stage
                            for (TreeNode enfantNode : this.selectedNodes) {
                                if (enfantNode.getParent().getData().toString().equalsIgnoreCase("Decomptes")) {
                                    String actionDecomptes = "";
                                    actionDecomptes = enfantNode.getData().toString();
                                    Actionmenu actionmenu = new Actionmenu();
                                    int idM = ejbMenu.maxMenu();
                                    Menu mn = ejbMenu.find(idM);
                                    actionmenu.setLibelleaction(actionDecomptes);
                                    actionmenu.setIdMenu(mn);
                                    ejbActionmenu.insertAction(actionmenu);
                                    System.out.println("Enfant Decomptes: " + actionDecomptes);
                                }
                                
                            }
                            creationActionDecomptes++;
                        }
                        
                    }
                    
                }

                //Gestion des Chantiers
                if (node.equals("Chantiers")) {
                    System.out.println("Parent: Chantiers");
                    if (creationModuleProjet == 0) {
                        //Creation module personnel
                        Droitacces droitacces1 = new Droitacces();
                        droitacces1.setModule("Projets");
                        droitacces1.setIdUtilisateur(user);
                        droitacces1.setIdRole(user.getIdRole());
                        ejbDroitacces.insertDroit(droitacces1);
                        creationModuleProjet++;
                    }
                    if (creationActionChantiers == 0) {
                        //Creation menu utilisateur
                        Menu menu = new Menu();
                        int idP = ejbDroitacces.maxDroit();
                        Droitacces daac = ejbDroitacces.find(idP);
                        menu.setLibelemenu("Chantiers");
                        menu.setIdPrivilege(daac);
                        ejbMenu.insertMenu(menu);
                        //Creation action stage
                        List<TreeNode> listEnfants = nodeaffichage.getChildren();
                        for (TreeNode enfantNode : listEnfants) {
                            String actionChantiers = "";
                            actionChantiers = enfantNode.getData().toString();
                            Actionmenu actionmenu = new Actionmenu();
                            int idM = ejbMenu.maxMenu();
                            Menu mn = ejbMenu.find(idM);
                            actionmenu.setLibelleaction(actionChantiers);
                            actionmenu.setIdMenu(mn);
                            ejbActionmenu.insertAction(actionmenu);
                            System.out.println("Enfant Chantiers: " + actionChantiers);
                        }
                        creationActionChantiers++;
                    }
                    
                } else {
                    
                    if (nodeParent.equals("Chantiers")) {
                        System.out.println("Second cas");
                        if (creationModuleProjet == 0) {
                            //Creation module personnel
                            Droitacces droitacces1 = new Droitacces();
                            droitacces1.setModule("Projets");
                            droitacces1.setIdUtilisateur(user);
                            droitacces1.setIdRole(user.getIdRole());
                            ejbDroitacces.insertDroit(droitacces1);
                            creationModuleProjet++;
                        }
                        if (creationActionChantiers == 0) {
                            //Creation menu utilisateur
                            Menu menu = new Menu();
                            int idP = ejbDroitacces.maxDroit();
                            Droitacces daac = ejbDroitacces.find(idP);
                            menu.setLibelemenu("Chantiers");
                            menu.setIdPrivilege(daac);
                            ejbMenu.insertMenu(menu);
                            //Creation action stage
                            for (TreeNode enfantNode : this.selectedNodes) {
                                if (enfantNode.getParent().getData().toString().equalsIgnoreCase("Chantiers")) {
                                    String actionChantiers = "";
                                    actionChantiers = enfantNode.getData().toString();
                                    Actionmenu actionmenu = new Actionmenu();
                                    int idM = ejbMenu.maxMenu();
                                    Menu mn = ejbMenu.find(idM);
                                    actionmenu.setLibelleaction(actionChantiers);
                                    actionmenu.setIdMenu(mn);
                                    ejbActionmenu.insertAction(actionmenu);
                                    System.out.println("Enfant Chantiers: " + actionChantiers);
                                }
                                
                            }
                            creationActionChantiers++;
                        }
                        
                    }
                    
                }

                //Gestion des Listprestataire
                if (node.equals("Liste prestataires")) {
                    System.out.println("Parent: Liste prestataires");
                    if (creationModuleProjet == 0) {
                        //Creation module personnel
                        Droitacces droitacces1 = new Droitacces();
                        droitacces1.setModule("Projets");
                        droitacces1.setIdUtilisateur(user);
                        droitacces1.setIdRole(user.getIdRole());
                        ejbDroitacces.insertDroit(droitacces1);
                        creationModuleProjet++;
                    }
                    if (creationActionListprestataire == 0) {
                        //Creation menu utilisateur
                        Menu menu = new Menu();
                        int idP = ejbDroitacces.maxDroit();
                        Droitacces daac = ejbDroitacces.find(idP);
                        menu.setLibelemenu("Liste prestataires");
                        menu.setIdPrivilege(daac);
                        ejbMenu.insertMenu(menu);
                        //Creation action stage
                        List<TreeNode> listEnfants = nodeaffichage.getChildren();
                        for (TreeNode enfantNode : listEnfants) {
                            String actionListprestataire = "";
                            actionListprestataire = enfantNode.getData().toString();
                            Actionmenu actionmenu = new Actionmenu();
                            int idM = ejbMenu.maxMenu();
                            Menu mn = ejbMenu.find(idM);
                            actionmenu.setLibelleaction(actionListprestataire);
                            actionmenu.setIdMenu(mn);
                            ejbActionmenu.insertAction(actionmenu);
                            System.out.println("Enfant Liste prestataires: " + actionListprestataire);
                        }
                        creationActionListprestataire++;
                    }
                    
                } else {
                    
                    if (nodeParent.equals("Liste prestataires")) {
                        System.out.println("Second cas");
                        if (creationModuleProjet == 0) {
                            //Creation module personnel
                            Droitacces droitacces1 = new Droitacces();
                            droitacces1.setModule("Projets");
                            droitacces1.setIdUtilisateur(user);
                            droitacces1.setIdRole(user.getIdRole());
                            ejbDroitacces.insertDroit(droitacces1);
                            creationModuleProjet++;
                        }
                        if (creationActionListprestataire == 0) {
                            //Creation menu utilisateur
                            Menu menu = new Menu();
                            int idP = ejbDroitacces.maxDroit();
                            Droitacces daac = ejbDroitacces.find(idP);
                            menu.setLibelemenu("Liste prestataires");
                            menu.setIdPrivilege(daac);
                            ejbMenu.insertMenu(menu);
                            //Creation action stage
                            for (TreeNode enfantNode : this.selectedNodes) {
                                if (enfantNode.getParent().getData().toString().equalsIgnoreCase("Liste prestataires")) {
                                    String actionListprestataire = "";
                                    actionListprestataire = enfantNode.getData().toString();
                                    Actionmenu actionmenu = new Actionmenu();
                                    int idM = ejbMenu.maxMenu();
                                    Menu mn = ejbMenu.find(idM);
                                    actionmenu.setLibelleaction(actionListprestataire);
                                    actionmenu.setIdMenu(mn);
                                    ejbActionmenu.insertAction(actionmenu);
                                    System.out.println("Enfant Liste prestataires: " + actionListprestataire);
                                }
                                
                            }
                            creationActionListprestataire++;
                        }
                        
                    }
                    
                }

                //Gestion des Referentielmod
                if (node.equals("Referenciel Main-d'oeuvre")) {
                    System.out.println("Parent: Referenciel Main-d'oeuvre");
                    if (creationModuleProjet == 0) {
                        //Creation module personnel
                        Droitacces droitacces1 = new Droitacces();
                        droitacces1.setModule("Projets");
                        droitacces1.setIdUtilisateur(user);
                        droitacces1.setIdRole(user.getIdRole());
                        ejbDroitacces.insertDroit(droitacces1);
                        creationModuleProjet++;
                    }
                    if (creationActionReferentielmod == 0) {
                        //Creation menu utilisateur
                        Menu menu = new Menu();
                        int idP = ejbDroitacces.maxDroit();
                        Droitacces daac = ejbDroitacces.find(idP);
                        menu.setLibelemenu("Referenciel Main-d'oeuvre");
                        menu.setIdPrivilege(daac);
                        ejbMenu.insertMenu(menu);
                        //Creation action stage
                        List<TreeNode> listEnfants = nodeaffichage.getChildren();
                        for (TreeNode enfantNode : listEnfants) {
                            String actionReferentielmod = "";
                            actionReferentielmod = enfantNode.getData().toString();
                            Actionmenu actionmenu = new Actionmenu();
                            int idM = ejbMenu.maxMenu();
                            Menu mn = ejbMenu.find(idM);
                            actionmenu.setLibelleaction(actionReferentielmod);
                            actionmenu.setIdMenu(mn);
                            ejbActionmenu.insertAction(actionmenu);
                            System.out.println("Enfant Referenciel Main-d'oeuvre: " + actionReferentielmod);
                        }
                        creationActionReferentielmod++;
                    }
                    
                } else {
                    
                    if (nodeParent.equals("Referenciel Main-d'oeuvre")) {
                        System.out.println("Second cas");
                        if (creationModuleProjet == 0) {
                            //Creation module personnel
                            Droitacces droitacces1 = new Droitacces();
                            droitacces1.setModule("Projets");
                            droitacces1.setIdUtilisateur(user);
                            droitacces1.setIdRole(user.getIdRole());
                            ejbDroitacces.insertDroit(droitacces1);
                            creationModuleProjet++;
                        }
                        if (creationActionReferentielmod == 0) {
                            //Creation menu utilisateur
                            Menu menu = new Menu();
                            int idP = ejbDroitacces.maxDroit();
                            Droitacces daac = ejbDroitacces.find(idP);
                            menu.setLibelemenu("Referenciel Main-d'oeuvre");
                            menu.setIdPrivilege(daac);
                            ejbMenu.insertMenu(menu);
                            //Creation action stage
                            for (TreeNode enfantNode : this.selectedNodes) {
                                if (enfantNode.getParent().getData().toString().equalsIgnoreCase("Referenciel Main-d'oeuvre")) {
                                    String actionReferentielmod = "";
                                    actionReferentielmod = enfantNode.getData().toString();
                                    Actionmenu actionmenu = new Actionmenu();
                                    int idM = ejbMenu.maxMenu();
                                    Menu mn = ejbMenu.find(idM);
                                    actionmenu.setLibelleaction(actionReferentielmod);
                                    actionmenu.setIdMenu(mn);
                                    ejbActionmenu.insertAction(actionmenu);
                                    System.out.println("Enfant Referenciel Main-d'oeuvre: " + actionReferentielmod);
                                }
                                
                            }
                            creationActionReferentielmod++;
                        }
                        
                    }
                    
                }

                //Gestion des Acomptes
                if (node.equals("Acomptes")) {
                    System.out.println("Parent: Acomptes");
                    if (creationModuleProjet == 0) {
                        //Creation module personnel
                        Droitacces droitacces1 = new Droitacces();
                        droitacces1.setModule("Projets");
                        droitacces1.setIdUtilisateur(user);
                        droitacces1.setIdRole(user.getIdRole());
                        ejbDroitacces.insertDroit(droitacces1);
                        creationModuleProjet++;
                    }
                    if (creationActionAcomptes == 0) {
                        //Creation menu utilisateur
                        Menu menu = new Menu();
                        int idP = ejbDroitacces.maxDroit();
                        Droitacces daac = ejbDroitacces.find(idP);
                        menu.setLibelemenu("Acomptes");
                        menu.setIdPrivilege(daac);
                        ejbMenu.insertMenu(menu);
                        //Creation action stage
                        List<TreeNode> listEnfants = nodeaffichage.getChildren();
                        for (TreeNode enfantNode : listEnfants) {
                            String actionAcomptes = "";
                            actionAcomptes = enfantNode.getData().toString();
                            Actionmenu actionmenu = new Actionmenu();
                            int idM = ejbMenu.maxMenu();
                            Menu mn = ejbMenu.find(idM);
                            actionmenu.setLibelleaction(actionAcomptes);
                            actionmenu.setIdMenu(mn);
                            ejbActionmenu.insertAction(actionmenu);
                            System.out.println("Enfant Acomptes: " + actionAcomptes);
                        }
                        creationActionAcomptes++;
                    }
                    
                } else {
                    
                    if (nodeParent.equals("Acomptes")) {
                        System.out.println("Second cas");
                        if (creationModuleProjet == 0) {
                            //Creation module personnel
                            Droitacces droitacces1 = new Droitacces();
                            droitacces1.setModule("Projets");
                            droitacces1.setIdUtilisateur(user);
                            droitacces1.setIdRole(user.getIdRole());
                            ejbDroitacces.insertDroit(droitacces1);
                            creationModuleProjet++;
                        }
                        if (creationActionAcomptes == 0) {
                            //Creation menu utilisateur
                            Menu menu = new Menu();
                            int idP = ejbDroitacces.maxDroit();
                            Droitacces daac = ejbDroitacces.find(idP);
                            menu.setLibelemenu("Acomptes");
                            menu.setIdPrivilege(daac);
                            ejbMenu.insertMenu(menu);
                            //Creation action stage
                            for (TreeNode enfantNode : this.selectedNodes) {
                                if (enfantNode.getParent().getData().toString().equalsIgnoreCase("Acomptes")) {
                                    String actionAcomptes = "";
                                    actionAcomptes = enfantNode.getData().toString();
                                    Actionmenu actionmenu = new Actionmenu();
                                    int idM = ejbMenu.maxMenu();
                                    Menu mn = ejbMenu.find(idM);
                                    actionmenu.setLibelleaction(actionAcomptes);
                                    actionmenu.setIdMenu(mn);
                                    ejbActionmenu.insertAction(actionmenu);
                                    System.out.println("Enfant Acomptes: " + actionAcomptes);
                                }
                                
                            }
                            creationActionAcomptes++;
                        }
                        
                    }
                    
                }

                //Gestion des tache
                if (node.equals("Nouvelle tâche")) {
                    System.out.println("Parent: Nouvelle tâche");
                    if (creationModuleProjet == 0) {
                        //Creation module personnel
                        Droitacces droitacces1 = new Droitacces();
                        droitacces1.setModule("Projets");
                        droitacces1.setIdUtilisateur(user);
                        droitacces1.setIdRole(user.getIdRole());
                        ejbDroitacces.insertDroit(droitacces1);
                        creationModuleProjet++;
                    }
                    if (creationActionTache == 0) {
                        //Creation menu utilisateur
                        Menu menu = new Menu();
                        int idP = ejbDroitacces.maxDroit();
                        Droitacces daac = ejbDroitacces.find(idP);
                        menu.setLibelemenu("Nouvelle tâche");
                        menu.setIdPrivilege(daac);
                        ejbMenu.insertMenu(menu);
                        //Creation action stage
                        List<TreeNode> listEnfants = nodeaffichage.getChildren();
                        for (TreeNode enfantNode : listEnfants) {
                            String actionTache = "";
                            actionTache = enfantNode.getData().toString();
                            Actionmenu actionmenu = new Actionmenu();
                            int idM = ejbMenu.maxMenu();
                            Menu mn = ejbMenu.find(idM);
                            actionmenu.setLibelleaction(actionTache);
                            actionmenu.setIdMenu(mn);
                            ejbActionmenu.insertAction(actionmenu);
                            System.out.println("Enfant Nouvelle tâche: " + actionTache);
                        }
                        creationActionTache++;
                    }
                    
                } else {
                    
                    if (nodeParent.equals("Nouvelle tâche")) {
                        System.out.println("Second cas");
                        if (creationModuleProjet == 0) {
                            //Creation module personnel
                            Droitacces droitacces1 = new Droitacces();
                            droitacces1.setModule("Projets");
                            droitacces1.setIdUtilisateur(user);
                            droitacces1.setIdRole(user.getIdRole());
                            ejbDroitacces.insertDroit(droitacces1);
                            creationModuleProjet++;
                        }
                        if (creationActionTache == 0) {
                            //Creation menu utilisateur
                            Menu menu = new Menu();
                            int idP = ejbDroitacces.maxDroit();
                            Droitacces daac = ejbDroitacces.find(idP);
                            menu.setLibelemenu("Nouvelle tâche");
                            menu.setIdPrivilege(daac);
                            ejbMenu.insertMenu(menu);
                            //Creation action stage
                            for (TreeNode enfantNode : this.selectedNodes) {
                                if (enfantNode.getParent().getData().toString().equalsIgnoreCase("Nouvelle tâche")) {
                                    String actionTache = "";
                                    actionTache = enfantNode.getData().toString();
                                    Actionmenu actionmenu = new Actionmenu();
                                    int idM = ejbMenu.maxMenu();
                                    Menu mn = ejbMenu.find(idM);
                                    actionmenu.setLibelleaction(actionTache);
                                    actionmenu.setIdMenu(mn);
                                    ejbActionmenu.insertAction(actionmenu);
                                    System.out.println("Enfant Nouvelle tâche: " + actionTache);
                                }
                                
                            }
                            creationActionTache++;
                        }
                        
                    }
                    
                }

                //Gestion des management
                if (node.equals("Management")) {
                    System.out.println("Parent: Management");
                    if (creationModuleProjet == 0) {
                        //Creation module personnel
                        Droitacces droitacces1 = new Droitacces();
                        droitacces1.setModule("Projets");
                        droitacces1.setIdUtilisateur(user);
                        droitacces1.setIdRole(user.getIdRole());
                        ejbDroitacces.insertDroit(droitacces1);
                        creationModuleProjet++;
                    }
                    if (creationActionManagement == 0) {
                        //Creation menu utilisateur
                        Menu menu = new Menu();
                        int idP = ejbDroitacces.maxDroit();
                        Droitacces daac = ejbDroitacces.find(idP);
                        menu.setLibelemenu("Management");
                        menu.setIdPrivilege(daac);
                        ejbMenu.insertMenu(menu);
                        //Creation action stage
                        List<TreeNode> listEnfants = nodeaffichage.getChildren();
                        for (TreeNode enfantNode : listEnfants) {
                            String actionManagement = "";
                            actionManagement = enfantNode.getData().toString();
                            Actionmenu actionmenu = new Actionmenu();
                            int idM = ejbMenu.maxMenu();
                            Menu mn = ejbMenu.find(idM);
                            actionmenu.setLibelleaction(actionManagement);
                            actionmenu.setIdMenu(mn);
                            ejbActionmenu.insertAction(actionmenu);
                            System.out.println("Enfant Management: " + actionManagement);
                        }
                        creationActionManagement++;
                    }
                    
                } else {
                    
                    if (nodeParent.equals("Management")) {
                        System.out.println("Second cas");
                        if (creationModuleProjet == 0) {
                            //Creation module personnel
                            Droitacces droitacces1 = new Droitacces();
                            droitacces1.setModule("Projets");
                            droitacces1.setIdUtilisateur(user);
                            droitacces1.setIdRole(user.getIdRole());
                            ejbDroitacces.insertDroit(droitacces1);
                            creationModuleProjet++;
                        }
                        if (creationActionManagement == 0) {
                            //Creation menu utilisateur
                            Menu menu = new Menu();
                            int idP = ejbDroitacces.maxDroit();
                            Droitacces daac = ejbDroitacces.find(idP);
                            menu.setLibelemenu("Management");
                            menu.setIdPrivilege(daac);
                            ejbMenu.insertMenu(menu);
                            //Creation action stage
                            for (TreeNode enfantNode : this.selectedNodes) {
                                if (enfantNode.getParent().getData().toString().equalsIgnoreCase("Management")) {
                                    String actionManagement = "";
                                    actionManagement = enfantNode.getData().toString();
                                    Actionmenu actionmenu = new Actionmenu();
                                    int idM = ejbMenu.maxMenu();
                                    Menu mn = ejbMenu.find(idM);
                                    actionmenu.setLibelleaction(actionManagement);
                                    actionmenu.setIdMenu(mn);
                                    ejbActionmenu.insertAction(actionmenu);
                                    System.out.println("Enfant Management: " + actionManagement);
                                }
                                
                            }
                            creationActionManagement++;
                        }
                    }
                    
                }

                //Gestion des devises
                if (node.equals("Devises")) {
                    System.out.println("Parent: Devises");
                    if (creationModuleAppro == 0) {
                        //Creation module config
                        Droitacces droitacces1 = new Droitacces();
                        droitacces1.setModule("Approvisionnement");
                        droitacces1.setIdUtilisateur(user);
                        droitacces1.setIdRole(user.getIdRole());
                        ejbDroitacces.insertDroit(droitacces1);
                        creationModuleAppro++;
                    }
                    
                    if (creationMenuAppro == 0) {
                        //Creation menu role
                        Menu menu = new Menu();
                        int idP = ejbDroitacces.maxDroit();
                        Droitacces daac = ejbDroitacces.find(idP);
                        menu.setLibelemenu("Devises");
                        menu.setIdPrivilege(daac);
                        ejbMenu.insertMenu(menu);
                        creationMenuAppro++;
                    }
                    
                    if (creationActionAppro == 0) {
                        //Creation action role
                        List<TreeNode> listEnfants = nodeaffichage.getChildren();
                        for (TreeNode enfantNode : listEnfants) {
                            String actionDevises = "";
                            actionDevises = enfantNode.getData().toString();
                            Actionmenu actionmenu = new Actionmenu();
                            int idM = ejbMenu.maxMenu();
                            Menu mn = ejbMenu.find(idM);
                            actionmenu.setLibelleaction(actionDevises);
                            actionmenu.setIdMenu(mn);
                            ejbActionmenu.insertAction(actionmenu);
                            System.out.println("Enfant Devises: " + actionDevises);
                        }
                        creationActionAppro++;
                    }
                    
                } else {
                    
                    if (nodeParent.equals("Devises")) {
                        System.out.println("Second cas");
                        if (creationModuleAppro == 0) {
                            //Creation module config
                            Droitacces droitacces1 = new Droitacces();
                            droitacces1.setModule("Approvisionnement");
                            droitacces1.setIdUtilisateur(user);
                            droitacces1.setIdRole(user.getIdRole());
                            ejbDroitacces.insertDroit(droitacces1);
                            creationModuleAppro++;
                        }
                        
                        if (creationMenuAppro == 0) {
                            //Creation menu role
                            Menu menu = new Menu();
                            int idP = ejbDroitacces.maxDroit();
                            Droitacces daac = ejbDroitacces.find(idP);
                            menu.setLibelemenu("Devises");
                            menu.setIdPrivilege(daac);
                            ejbMenu.insertMenu(menu);
                            creationMenuAppro++;
                        }
                        
                        if (creationActionAppro == 0) {
                            //Creation action role
                            for (TreeNode enfantNode : this.selectedNodes) {
                                if (enfantNode.getParent().getData().toString().equalsIgnoreCase("Devises")) {
                                    String actionDevises = "";
                                    actionDevises = enfantNode.getData().toString();
                                    Actionmenu actionmenu = new Actionmenu();
                                    int idM = ejbMenu.maxMenu();
                                    Menu mn = ejbMenu.find(idM);
                                    actionmenu.setLibelleaction(actionDevises);
                                    actionmenu.setIdMenu(mn);
                                    ejbActionmenu.insertAction(actionmenu);
                                    System.out.println("Enfant Devises: " + actionDevises);
                                }
                                
                            }
                            creationActionAppro++;
                        }
                    }
                    
                }

                //Gestion des unite
                if (node.equals("Unite de mesure")) {
                    System.out.println("Parent: Unite de mesure");
                    if (creationModuleAppro == 0) {
                        //Creation module config
                        Droitacces droitacces1 = new Droitacces();
                        droitacces1.setModule("Approvisionnement");
                        droitacces1.setIdUtilisateur(user);
                        droitacces1.setIdRole(user.getIdRole());
                        ejbDroitacces.insertDroit(droitacces1);
                        creationModuleAppro++;
                    }
                    if (creationActionUnite == 0) {
                        //Creation menu utilisateur
                        Menu menu = new Menu();
                        int idP = ejbDroitacces.maxDroit();
                        Droitacces daac = ejbDroitacces.find(idP);
                        menu.setLibelemenu("Unite de mesure");
                        menu.setIdPrivilege(daac);
                        ejbMenu.insertMenu(menu);
                        //Creation action stage
                        List<TreeNode> listEnfants = nodeaffichage.getChildren();
                        for (TreeNode enfantNode : listEnfants) {
                            String actionUnite = "";
                            actionUnite = enfantNode.getData().toString();
                            Actionmenu actionmenu = new Actionmenu();
                            int idM = ejbMenu.maxMenu();
                            Menu mn = ejbMenu.find(idM);
                            actionmenu.setLibelleaction(actionUnite);
                            actionmenu.setIdMenu(mn);
                            ejbActionmenu.insertAction(actionmenu);
                            System.out.println("Enfant Unite de mesure: " + actionUnite);
                        }
                        creationActionUnite++;
                    }
                    
                } else {
                    
                    if (nodeParent.equals("Unite de mesure")) {
                        System.out.println("Second cas");
                        if (creationModuleAppro == 0) {
                            //Creation module config
                            Droitacces droitacces1 = new Droitacces();
                            droitacces1.setModule("Approvisionnement");
                            droitacces1.setIdUtilisateur(user);
                            droitacces1.setIdRole(user.getIdRole());
                            ejbDroitacces.insertDroit(droitacces1);
                            creationModuleAppro++;
                        }
                        if (creationActionUnite == 0) {
                            //Creation menu utilisateur
                            Menu menu = new Menu();
                            int idP = ejbDroitacces.maxDroit();
                            Droitacces daac = ejbDroitacces.find(idP);
                            menu.setLibelemenu("Unite de mesure");
                            menu.setIdPrivilege(daac);
                            ejbMenu.insertMenu(menu);
                            //Creation action stage
                            for (TreeNode enfantNode : this.selectedNodes) {
                                if (enfantNode.getParent().getData().toString().equalsIgnoreCase("Unite de mesure")) {
                                    String actionUnite = "";
                                    actionUnite = enfantNode.getData().toString();
                                    Actionmenu actionmenu = new Actionmenu();
                                    int idM = ejbMenu.maxMenu();
                                    Menu mn = ejbMenu.find(idM);
                                    actionmenu.setLibelleaction(actionUnite);
                                    actionmenu.setIdMenu(mn);
                                    ejbActionmenu.insertAction(actionmenu);
                                    System.out.println("Enfant Unite de mesure: " + actionUnite);
                                }
                                
                            }
                            creationActionUnite++;
                        }
                        
                    }
                    
                }

                //Gestion des lottechnique
                if (node.equals("Lot technique")) {
                    System.out.println("Parent: Lot technique");
                    if (creationModuleAppro == 0) {
                        //Creation module config
                        Droitacces droitacces1 = new Droitacces();
                        droitacces1.setModule("Approvisionnement");
                        droitacces1.setIdUtilisateur(user);
                        droitacces1.setIdRole(user.getIdRole());
                        ejbDroitacces.insertDroit(droitacces1);
                        creationModuleAppro++;
                    }
                    if (creationActionLottechnique == 0) {
                        //Creation menu utilisateur
                        Menu menu = new Menu();
                        int idP = ejbDroitacces.maxDroit();
                        Droitacces daac = ejbDroitacces.find(idP);
                        menu.setLibelemenu("Lot technique");
                        menu.setIdPrivilege(daac);
                        ejbMenu.insertMenu(menu);
                        //Creation action stage
                        List<TreeNode> listEnfants = nodeaffichage.getChildren();
                        for (TreeNode enfantNode : listEnfants) {
                            String actionLottechnique = "";
                            actionLottechnique = enfantNode.getData().toString();
                            Actionmenu actionmenu = new Actionmenu();
                            int idM = ejbMenu.maxMenu();
                            Menu mn = ejbMenu.find(idM);
                            actionmenu.setLibelleaction(actionLottechnique);
                            actionmenu.setIdMenu(mn);
                            ejbActionmenu.insertAction(actionmenu);
                            System.out.println("Enfant Lottechnique: " + actionLottechnique);
                        }
                        creationActionLottechnique++;
                    }
                    
                } else {
                    
                    if (nodeParent.equals("Lot technique")) {
                        System.out.println("Second cas");
                        if (creationModuleAppro == 0) {
                            //Creation module config
                            Droitacces droitacces1 = new Droitacces();
                            droitacces1.setModule("Approvisionnement");
                            droitacces1.setIdUtilisateur(user);
                            droitacces1.setIdRole(user.getIdRole());
                            ejbDroitacces.insertDroit(droitacces1);
                            creationModuleAppro++;
                        }
                        if (creationActionLottechnique == 0) {
                            //Creation menu utilisateur
                            Menu menu = new Menu();
                            int idP = ejbDroitacces.maxDroit();
                            Droitacces daac = ejbDroitacces.find(idP);
                            menu.setLibelemenu("Lot technique");
                            menu.setIdPrivilege(daac);
                            ejbMenu.insertMenu(menu);
                            //Creation action stage
                            for (TreeNode enfantNode : this.selectedNodes) {
                                if (enfantNode.getParent().getData().toString().equalsIgnoreCase("Lot technique")) {
                                    String actionLottechnique = "";
                                    actionLottechnique = enfantNode.getData().toString();
                                    Actionmenu actionmenu = new Actionmenu();
                                    int idM = ejbMenu.maxMenu();
                                    Menu mn = ejbMenu.find(idM);
                                    actionmenu.setLibelleaction(actionLottechnique);
                                    actionmenu.setIdMenu(mn);
                                    ejbActionmenu.insertAction(actionmenu);
                                    System.out.println("Enfant Lottechnique: " + actionLottechnique);
                                }
                                
                            }
                            creationActionLottechnique++;
                        }
                    }
                    
                }

                //Gestion des corpsetat
                if (node.equals("Corps d'etat")) {
                    System.out.println("Parent: Corps d'etat");
                    if (creationModuleAppro == 0) {
                        //Creation module config
                        Droitacces droitacces1 = new Droitacces();
                        droitacces1.setModule("Approvisionnement");
                        droitacces1.setIdUtilisateur(user);
                        droitacces1.setIdRole(user.getIdRole());
                        ejbDroitacces.insertDroit(droitacces1);
                        creationModuleAppro++;
                    }
                    if (creationActionCorpsetat == 0) {
                        //Creation menu utilisateur
                        Menu menu = new Menu();
                        int idP = ejbDroitacces.maxDroit();
                        Droitacces daac = ejbDroitacces.find(idP);
                        menu.setLibelemenu("Corps d'etat");
                        menu.setIdPrivilege(daac);
                        ejbMenu.insertMenu(menu);
                        //Creation action stage
                        List<TreeNode> listEnfants = nodeaffichage.getChildren();
                        for (TreeNode enfantNode : listEnfants) {
                            String actionCorpsetat = "";
                            actionCorpsetat = enfantNode.getData().toString();
                            Actionmenu actionmenu = new Actionmenu();
                            int idM = ejbMenu.maxMenu();
                            Menu mn = ejbMenu.find(idM);
                            actionmenu.setLibelleaction(actionCorpsetat);
                            actionmenu.setIdMenu(mn);
                            ejbActionmenu.insertAction(actionmenu);
                            System.out.println("Enfant Corpsetat: " + actionCorpsetat);
                        }
                        creationActionCorpsetat++;
                    }
                    
                } else {
                    
                    if (nodeParent.equals("Corps d'etat")) {
                        System.out.println("Second cas");
                        if (creationModuleAppro == 0) {
                            //Creation module config
                            Droitacces droitacces1 = new Droitacces();
                            droitacces1.setModule("Approvisionnement");
                            droitacces1.setIdUtilisateur(user);
                            droitacces1.setIdRole(user.getIdRole());
                            ejbDroitacces.insertDroit(droitacces1);
                            creationModuleAppro++;
                        }
                        if (creationActionCorpsetat == 0) {
                            //Creation menu utilisateur
                            Menu menu = new Menu();
                            int idP = ejbDroitacces.maxDroit();
                            Droitacces daac = ejbDroitacces.find(idP);
                            menu.setLibelemenu("Corps d'etat");
                            menu.setIdPrivilege(daac);
                            ejbMenu.insertMenu(menu);
                            //Creation action stage
                            for (TreeNode enfantNode : this.selectedNodes) {
                                if (enfantNode.getParent().getData().toString().equalsIgnoreCase("Corps d'etat")) {
                                    String actionCorpsetat = "";
                                    actionCorpsetat = enfantNode.getData().toString();
                                    Actionmenu actionmenu = new Actionmenu();
                                    int idM = ejbMenu.maxMenu();
                                    Menu mn = ejbMenu.find(idM);
                                    actionmenu.setLibelleaction(actionCorpsetat);
                                    actionmenu.setIdMenu(mn);
                                    ejbActionmenu.insertAction(actionmenu);
                                    System.out.println("Enfant Corpsetat: " + actionCorpsetat);
                                }
                                
                            }
                            creationActionCorpsetat++;
                        }
                        
                    }
                    
                }

                //Gestion des Materiels
                if (node.equals("Materiels")) {
                    System.out.println("Parent:Materiels");
                    if (creationModuleAppro == 0) {
                        //Creation module config
                        Droitacces droitacces1 = new Droitacces();
                        droitacces1.setModule("Approvisionnement");
                        droitacces1.setIdUtilisateur(user);
                        droitacces1.setIdRole(user.getIdRole());
                        ejbDroitacces.insertDroit(droitacces1);
                        creationModuleAppro++;
                    }
                    if (creationActionMateriels == 0) {
                        //Creation menu utilisateur
                        Menu menu = new Menu();
                        int idP = ejbDroitacces.maxDroit();
                        Droitacces daac = ejbDroitacces.find(idP);
                        menu.setLibelemenu("Materiels");
                        menu.setIdPrivilege(daac);
                        ejbMenu.insertMenu(menu);
                        //Creation action stage
                        List<TreeNode> listEnfants = nodeaffichage.getChildren();
                        for (TreeNode enfantNode : listEnfants) {
                            String actionMateriels = "";
                            actionMateriels = enfantNode.getData().toString();
                            Actionmenu actionmenu = new Actionmenu();
                            int idM = ejbMenu.maxMenu();
                            Menu mn = ejbMenu.find(idM);
                            actionmenu.setLibelleaction(actionMateriels);
                            actionmenu.setIdMenu(mn);
                            ejbActionmenu.insertAction(actionmenu);
                            System.out.println("Enfant Materiels: " + actionMateriels);
                        }
                        creationActionMateriels++;
                    }
                    
                } else {
                    
                    if (nodeParent.equals("Materiels")) {
                        System.out.println("Second cas");
                        if (creationModuleAppro == 0) {
                            //Creation module config
                            Droitacces droitacces1 = new Droitacces();
                            droitacces1.setModule("Approvisionnement");
                            droitacces1.setIdUtilisateur(user);
                            droitacces1.setIdRole(user.getIdRole());
                            ejbDroitacces.insertDroit(droitacces1);
                            creationModuleAppro++;
                        }
                        if (creationActionMateriels == 0) {
                            //Creation menu utilisateur
                            Menu menu = new Menu();
                            int idP = ejbDroitacces.maxDroit();
                            Droitacces daac = ejbDroitacces.find(idP);
                            menu.setLibelemenu("Materiels");
                            menu.setIdPrivilege(daac);
                            ejbMenu.insertMenu(menu);
                            //Creation action stage
                            for (TreeNode enfantNode : this.selectedNodes) {
                                if (enfantNode.getParent().getData().toString().equalsIgnoreCase("Materiels")) {
                                    String actionMateriels = "";
                                    actionMateriels = enfantNode.getData().toString();
                                    Actionmenu actionmenu = new Actionmenu();
                                    int idM = ejbMenu.maxMenu();
                                    Menu mn = ejbMenu.find(idM);
                                    actionmenu.setLibelleaction(actionMateriels);
                                    actionmenu.setIdMenu(mn);
                                    ejbActionmenu.insertAction(actionmenu);
                                    System.out.println("Enfant Materiels: " + actionMateriels);
                                }
                                
                            }
                            creationActionMateriels++;
                        }
                    }
                    
                }

                //Gestion des Listfournisseur
                if (node.equals("Liste fournisseurs")) {
                    System.out.println("Parent: Liste fournisseurs");
                    if (creationModuleAppro == 0) {
                        //Creation module config
                        Droitacces droitacces1 = new Droitacces();
                        droitacces1.setModule("Approvisionnement");
                        droitacces1.setIdUtilisateur(user);
                        droitacces1.setIdRole(user.getIdRole());
                        ejbDroitacces.insertDroit(droitacces1);
                        creationModuleAppro++;
                    }
                    if (creationActionListfournisseur == 0) {
                        //Creation menu utilisateur
                        Menu menu = new Menu();
                        int idP = ejbDroitacces.maxDroit();
                        Droitacces daac = ejbDroitacces.find(idP);
                        menu.setLibelemenu("Liste fournisseurs");
                        menu.setIdPrivilege(daac);
                        ejbMenu.insertMenu(menu);
                        //Creation action stage
                        List<TreeNode> listEnfants = nodeaffichage.getChildren();
                        for (TreeNode enfantNode : listEnfants) {
                            String actionListfournisseur = "";
                            actionListfournisseur = enfantNode.getData().toString();
                            Actionmenu actionmenu = new Actionmenu();
                            int idM = ejbMenu.maxMenu();
                            Menu mn = ejbMenu.find(idM);
                            actionmenu.setLibelleaction(actionListfournisseur);
                            actionmenu.setIdMenu(mn);
                            ejbActionmenu.insertAction(actionmenu);
                            System.out.println("Enfant Listfournisseur: " + actionListfournisseur);
                        }
                        creationActionListfournisseur++;
                    }
                    
                } else {
                    
                    if (nodeParent.equals("Liste fournisseurs")) {
                        System.out.println("Second cas");
                        if (creationModuleAppro == 0) {
                            //Creation module config
                            Droitacces droitacces1 = new Droitacces();
                            droitacces1.setModule("Approvisionnement");
                            droitacces1.setIdUtilisateur(user);
                            droitacces1.setIdRole(user.getIdRole());
                            ejbDroitacces.insertDroit(droitacces1);
                            creationModuleAppro++;
                        }
                        if (creationActionListfournisseur == 0) {
                            //Creation menu utilisateur
                            Menu menu = new Menu();
                            int idP = ejbDroitacces.maxDroit();
                            Droitacces daac = ejbDroitacces.find(idP);
                            menu.setLibelemenu("Liste fournisseurs");
                            menu.setIdPrivilege(daac);
                            ejbMenu.insertMenu(menu);
                            //Creation action stage
                            for (TreeNode enfantNode : this.selectedNodes) {
                                if (enfantNode.getParent().getData().toString().equalsIgnoreCase("Liste fournisseurs")) {
                                    String actionListfournisseur = "";
                                    actionListfournisseur = enfantNode.getData().toString();
                                    Actionmenu actionmenu = new Actionmenu();
                                    int idM = ejbMenu.maxMenu();
                                    Menu mn = ejbMenu.find(idM);
                                    actionmenu.setLibelleaction(actionListfournisseur);
                                    actionmenu.setIdMenu(mn);
                                    ejbActionmenu.insertAction(actionmenu);
                                    System.out.println("Enfant Listfournisseur: " + actionListfournisseur);
                                }
                                
                            }
                            creationActionListfournisseur++;
                        }
                        
                    }
                    
                }

                //Gestion des Referentielfournisseur
                if (node.equals("Referenciel fournisseurs")) {
                    System.out.println("Parent: Referenciel fournisseurs");
                    if (creationModuleAppro == 0) {
                        //Creation module config
                        Droitacces droitacces1 = new Droitacces();
                        droitacces1.setModule("Approvisionnement");
                        droitacces1.setIdUtilisateur(user);
                        droitacces1.setIdRole(user.getIdRole());
                        ejbDroitacces.insertDroit(droitacces1);
                        creationModuleAppro++;
                    }
                    if (creationActionReferentielfournisseur == 0) {
                        //Creation menu utilisateur
                        Menu menu = new Menu();
                        int idP = ejbDroitacces.maxDroit();
                        Droitacces daac = ejbDroitacces.find(idP);
                        menu.setLibelemenu("Referenciel fournisseurs");
                        menu.setIdPrivilege(daac);
                        ejbMenu.insertMenu(menu);
                        //Creation action stage
                        List<TreeNode> listEnfants = nodeaffichage.getChildren();
                        for (TreeNode enfantNode : listEnfants) {
                            String actionReferentielfournisseur = "";
                            actionReferentielfournisseur = enfantNode.getData().toString();
                            Actionmenu actionmenu = new Actionmenu();
                            int idM = ejbMenu.maxMenu();
                            Menu mn = ejbMenu.find(idM);
                            actionmenu.setLibelleaction(actionReferentielfournisseur);
                            actionmenu.setIdMenu(mn);
                            ejbActionmenu.insertAction(actionmenu);
                            System.out.println("Enfant Referenciel fournisseurs: " + actionReferentielfournisseur);
                        }
                        creationActionReferentielfournisseur++;
                    }
                    
                } else {
                    
                    if (nodeParent.equals("Referenciel fournisseurs")) {
                        System.out.println("Second cas");
                        if (creationModuleAppro == 0) {
                            //Creation module config
                            Droitacces droitacces1 = new Droitacces();
                            droitacces1.setModule("Approvisionnement");
                            droitacces1.setIdUtilisateur(user);
                            droitacces1.setIdRole(user.getIdRole());
                            ejbDroitacces.insertDroit(droitacces1);
                            creationModuleAppro++;
                        }
                        if (creationActionReferentielfournisseur == 0) {
                            //Creation menu utilisateur
                            Menu menu = new Menu();
                            int idP = ejbDroitacces.maxDroit();
                            Droitacces daac = ejbDroitacces.find(idP);
                            menu.setLibelemenu("Referenciel fournisseurs");
                            menu.setIdPrivilege(daac);
                            ejbMenu.insertMenu(menu);
                            //Creation action stage
                            for (TreeNode enfantNode : this.selectedNodes) {
                                if (enfantNode.getParent().getData().toString().equalsIgnoreCase("Referenciel fournisseurs")) {
                                    String actionReferentielfournisseur = "";
                                    actionReferentielfournisseur = enfantNode.getData().toString();
                                    Actionmenu actionmenu = new Actionmenu();
                                    int idM = ejbMenu.maxMenu();
                                    Menu mn = ejbMenu.find(idM);
                                    actionmenu.setLibelleaction(actionReferentielfournisseur);
                                    actionmenu.setIdMenu(mn);
                                    ejbActionmenu.insertAction(actionmenu);
                                    System.out.println("Enfant Referenciel fournisseurs: " + actionReferentielfournisseur);
                                }
                                
                            }
                            creationActionReferentielfournisseur++;
                        }
                    }
                    
                }

                //Gestion des Acomptefournisseur
                if (node.equals("Acomptes fournisseur")) {
                    System.out.println("Parent: Acomptes fournisseur");
                    if (creationModuleAppro == 0) {
                        //Creation module config
                        Droitacces droitacces1 = new Droitacces();
                        droitacces1.setModule("Approvisionnement");
                        droitacces1.setIdUtilisateur(user);
                        droitacces1.setIdRole(user.getIdRole());
                        ejbDroitacces.insertDroit(droitacces1);
                        creationModuleAppro++;
                    }
                    if (creationActionAcomptefournisseur == 0) {
                        //Creation menu utilisateur
                        Menu menu = new Menu();
                        int idP = ejbDroitacces.maxDroit();
                        Droitacces daac = ejbDroitacces.find(idP);
                        menu.setLibelemenu("Acomptes fournisseur");
                        menu.setIdPrivilege(daac);
                        ejbMenu.insertMenu(menu);
                        //Creation action stage
                        List<TreeNode> listEnfants = nodeaffichage.getChildren();
                        for (TreeNode enfantNode : listEnfants) {
                            String actionAcomptefournisseur = "";
                            actionAcomptefournisseur = enfantNode.getData().toString();
                            Actionmenu actionmenu = new Actionmenu();
                            int idM = ejbMenu.maxMenu();
                            Menu mn = ejbMenu.find(idM);
                            actionmenu.setLibelleaction(actionAcomptefournisseur);
                            actionmenu.setIdMenu(mn);
                            ejbActionmenu.insertAction(actionmenu);
                            System.out.println("Enfant Acomptes fournisseur: " + actionAcomptefournisseur);
                        }
                        creationActionAcomptefournisseur++;
                    }
                    
                } else {
                    
                    if (nodeParent.equals("Acomptes fournisseur")) {
                        System.out.println("Second cas");
                        if (creationModuleAppro == 0) {
                            //Creation module config
                            Droitacces droitacces1 = new Droitacces();
                            droitacces1.setModule("Approvisionnement");
                            droitacces1.setIdUtilisateur(user);
                            droitacces1.setIdRole(user.getIdRole());
                            ejbDroitacces.insertDroit(droitacces1);
                            creationModuleAppro++;
                        }
                        if (creationActionAcomptefournisseur == 0) {
                            //Creation menu utilisateur
                            Menu menu = new Menu();
                            int idP = ejbDroitacces.maxDroit();
                            Droitacces daac = ejbDroitacces.find(idP);
                            menu.setLibelemenu("Acomptes fournisseur");
                            menu.setIdPrivilege(daac);
                            ejbMenu.insertMenu(menu);
                            //Creation action stage
                            for (TreeNode enfantNode : this.selectedNodes) {
                                if (enfantNode.getParent().getData().toString().equalsIgnoreCase("Acomptes fournisseur")) {
                                    String actionAcomptefournisseur = "";
                                    actionAcomptefournisseur = enfantNode.getData().toString();
                                    Actionmenu actionmenu = new Actionmenu();
                                    int idM = ejbMenu.maxMenu();
                                    Menu mn = ejbMenu.find(idM);
                                    actionmenu.setLibelleaction(actionAcomptefournisseur);
                                    actionmenu.setIdMenu(mn);
                                    ejbActionmenu.insertAction(actionmenu);
                                    System.out.println("Enfant Acomptes fournisseur: " + actionAcomptefournisseur);
                                }
                                
                            }
                            creationActionAcomptefournisseur++;
                        }
                        
                    }
                    
                }

                //Gestion des Prix
                if (node.equals("Referenciel de prix")) {
                    System.out.println("Parent: Referenciel de prix");
                    if (creationModuleAppro == 0) {
                        //Creation module config
                        Droitacces droitacces1 = new Droitacces();
                        droitacces1.setModule("Approvisionnement");
                        droitacces1.setIdUtilisateur(user);
                        droitacces1.setIdRole(user.getIdRole());
                        ejbDroitacces.insertDroit(droitacces1);
                        creationModuleAppro++;
                    }
                    if (creationActionPrix == 0) {
                        //Creation menu utilisateur
                        Menu menu = new Menu();
                        int idP = ejbDroitacces.maxDroit();
                        Droitacces daac = ejbDroitacces.find(idP);
                        menu.setLibelemenu("Referenciel de prix");
                        menu.setIdPrivilege(daac);
                        ejbMenu.insertMenu(menu);
                        //Creation action stage
                        List<TreeNode> listEnfants = nodeaffichage.getChildren();
                        for (TreeNode enfantNode : listEnfants) {
                            String actionPrix = "";
                            actionPrix = enfantNode.getData().toString();
                            Actionmenu actionmenu = new Actionmenu();
                            int idM = ejbMenu.maxMenu();
                            Menu mn = ejbMenu.find(idM);
                            actionmenu.setLibelleaction(actionPrix);
                            actionmenu.setIdMenu(mn);
                            ejbActionmenu.insertAction(actionmenu);
                            System.out.println("Enfant Referenciel de prix: " + actionPrix);
                        }
                        creationActionPrix++;
                    }
                    
                } else {
                    
                    if (nodeParent.equals("Referenciel de prix")) {
                        System.out.println("Second cas");
                        if (creationModuleAppro == 0) {
                            //Creation module config
                            Droitacces droitacces1 = new Droitacces();
                            droitacces1.setModule("Approvisionnement");
                            droitacces1.setIdUtilisateur(user);
                            droitacces1.setIdRole(user.getIdRole());
                            ejbDroitacces.insertDroit(droitacces1);
                            creationModuleAppro++;
                        }
                        if (creationActionPrix == 0) {
                            //Creation menu utilisateur
                            Menu menu = new Menu();
                            int idP = ejbDroitacces.maxDroit();
                            Droitacces daac = ejbDroitacces.find(idP);
                            menu.setLibelemenu("Referenciel de prix");
                            menu.setIdPrivilege(daac);
                            ejbMenu.insertMenu(menu);
                            //Creation action stage
                            for (TreeNode enfantNode : this.selectedNodes) {
                                if (enfantNode.getParent().getData().toString().equalsIgnoreCase("Referenciel de prix")) {
                                    String actionPrix = "";
                                    actionPrix = enfantNode.getData().toString();
                                    Actionmenu actionmenu = new Actionmenu();
                                    int idM = ejbMenu.maxMenu();
                                    Menu mn = ejbMenu.find(idM);
                                    actionmenu.setLibelleaction(actionPrix);
                                    actionmenu.setIdMenu(mn);
                                    ejbActionmenu.insertAction(actionmenu);
                                    System.out.println("Enfant Referenciel de prix: " + actionPrix);
                                }
                                
                            }
                            creationActionPrix++;
                        }
                        
                    }
                    
                }

                //Gestion des Expression
                if (node.equals("Expressions des besoins")) {
                    System.out.println("Parent: Expressions des besoins");
                    if (creationModuleAppro == 0) {
                        //Creation module config
                        Droitacces droitacces1 = new Droitacces();
                        droitacces1.setModule("Approvisionnement");
                        droitacces1.setIdUtilisateur(user);
                        droitacces1.setIdRole(user.getIdRole());
                        ejbDroitacces.insertDroit(droitacces1);
                        creationModuleAppro++;
                    }
                    if (creationActionExpression == 0) {
                        //Creation menu utilisateur
                        Menu menu = new Menu();
                        int idP = ejbDroitacces.maxDroit();
                        Droitacces daac = ejbDroitacces.find(idP);
                        menu.setLibelemenu("Expressions des besoins");
                        menu.setIdPrivilege(daac);
                        ejbMenu.insertMenu(menu);
                        //Creation action stage
                        List<TreeNode> listEnfants = nodeaffichage.getChildren();
                        for (TreeNode enfantNode : listEnfants) {
                            String actionExpression = "";
                            actionExpression = enfantNode.getData().toString();
                            Actionmenu actionmenu = new Actionmenu();
                            int idM = ejbMenu.maxMenu();
                            Menu mn = ejbMenu.find(idM);
                            actionmenu.setLibelleaction(actionExpression);
                            actionmenu.setIdMenu(mn);
                            ejbActionmenu.insertAction(actionmenu);
                            System.out.println("Enfant Expressions des besoins: " + actionExpression);
                        }
                        creationActionExpression++;
                    }
                    
                } else {
                    
                    if (nodeParent.equals("Expressions des besoins")) {
                        System.out.println("Second cas");
                        if (creationModuleAppro == 0) {
                            //Creation module config
                            Droitacces droitacces1 = new Droitacces();
                            droitacces1.setModule("Approvisionnement");
                            droitacces1.setIdUtilisateur(user);
                            droitacces1.setIdRole(user.getIdRole());
                            ejbDroitacces.insertDroit(droitacces1);
                            creationModuleAppro++;
                        }
                        if (creationActionExpression == 0) {
                            //Creation menu utilisateur
                            Menu menu = new Menu();
                            int idP = ejbDroitacces.maxDroit();
                            Droitacces daac = ejbDroitacces.find(idP);
                            menu.setLibelemenu("Expressions des besoins");
                            menu.setIdPrivilege(daac);
                            ejbMenu.insertMenu(menu);
                            //Creation action stage
                            for (TreeNode enfantNode : this.selectedNodes) {
                                if (enfantNode.getParent().getData().toString().equalsIgnoreCase("Expressions des besoins")) {
                                    String actionExpression = "";
                                    actionExpression = enfantNode.getData().toString();
                                    Actionmenu actionmenu = new Actionmenu();
                                    int idM = ejbMenu.maxMenu();
                                    Menu mn = ejbMenu.find(idM);
                                    actionmenu.setLibelleaction(actionExpression);
                                    actionmenu.setIdMenu(mn);
                                    ejbActionmenu.insertAction(actionmenu);
                                    System.out.println("Enfant Expressions des besoins: " + actionExpression);
                                }
                                
                            }
                            creationActionExpression++;
                        }
                        
                    }
                    
                }

                //Gestion des Boncommande
                if (node.equals("Bon de commande")) {
                    System.out.println("Parent: Bon de commande");
                    if (creationModuleAppro == 0) {
                        //Creation module config
                        Droitacces droitacces1 = new Droitacces();
                        droitacces1.setModule("Approvisionnement");
                        droitacces1.setIdUtilisateur(user);
                        droitacces1.setIdRole(user.getIdRole());
                        ejbDroitacces.insertDroit(droitacces1);
                        creationModuleAppro++;
                    }
                    if (creationActionBoncommande == 0) {
                        //Creation menu utilisateur
                        Menu menu = new Menu();
                        int idP = ejbDroitacces.maxDroit();
                        Droitacces daac = ejbDroitacces.find(idP);
                        menu.setLibelemenu("Bon de commande");
                        menu.setIdPrivilege(daac);
                        ejbMenu.insertMenu(menu);
                        //Creation action stage
                        List<TreeNode> listEnfants = nodeaffichage.getChildren();
                        for (TreeNode enfantNode : listEnfants) {
                            String actionBoncommande = "";
                            actionBoncommande = enfantNode.getData().toString();
                            Actionmenu actionmenu = new Actionmenu();
                            int idM = ejbMenu.maxMenu();
                            Menu mn = ejbMenu.find(idM);
                            actionmenu.setLibelleaction(actionBoncommande);
                            actionmenu.setIdMenu(mn);
                            ejbActionmenu.insertAction(actionmenu);
                            System.out.println("Enfant Bon de commande: " + actionBoncommande);
                        }
                        creationActionBoncommande++;
                    }
                    
                } else {
                    
                    if (nodeParent.equals("Bon de commande")) {
                        System.out.println("Second cas");
                        if (creationModuleAppro == 0) {
                            //Creation module config
                            Droitacces droitacces1 = new Droitacces();
                            droitacces1.setModule("Approvisionnement");
                            droitacces1.setIdUtilisateur(user);
                            droitacces1.setIdRole(user.getIdRole());
                            ejbDroitacces.insertDroit(droitacces1);
                            creationModuleAppro++;
                        }
                        if (creationActionBoncommande == 0) {
                            //Creation menu utilisateur
                            Menu menu = new Menu();
                            int idP = ejbDroitacces.maxDroit();
                            Droitacces daac = ejbDroitacces.find(idP);
                            menu.setLibelemenu("Bon de commande");
                            menu.setIdPrivilege(daac);
                            ejbMenu.insertMenu(menu);
                            //Creation action stage
                            for (TreeNode enfantNode : this.selectedNodes) {
                                if (enfantNode.getParent().getData().toString().equalsIgnoreCase("Bon de commande")) {
                                    String actionBoncommande = "";
                                    actionBoncommande = enfantNode.getData().toString();
                                    Actionmenu actionmenu = new Actionmenu();
                                    int idM = ejbMenu.maxMenu();
                                    Menu mn = ejbMenu.find(idM);
                                    actionmenu.setLibelleaction(actionBoncommande);
                                    actionmenu.setIdMenu(mn);
                                    ejbActionmenu.insertAction(actionmenu);
                                    System.out.println("Enfant Bon de commande: " + actionBoncommande);
                                }
                                
                            }
                            creationActionBoncommande++;
                        }
                        
                    }
                    
                }

                //Gestion des Approchantier
                if (node.equals("Approvisionnement chantier")) {
                    System.out.println("Parent: Approvisionnement chantier");
                    if (creationModuleAppro == 0) {
                        //Creation module config
                        Droitacces droitacces1 = new Droitacces();
                        droitacces1.setModule("Approvisionnement");
                        droitacces1.setIdUtilisateur(user);
                        droitacces1.setIdRole(user.getIdRole());
                        ejbDroitacces.insertDroit(droitacces1);
                        creationModuleAppro++;
                    }
                    if (creationActionApprochantier == 0) {
                        //Creation menu utilisateur
                        Menu menu = new Menu();
                        int idP = ejbDroitacces.maxDroit();
                        Droitacces daac = ejbDroitacces.find(idP);
                        menu.setLibelemenu("Approvisionnement chantier");
                        menu.setIdPrivilege(daac);
                        ejbMenu.insertMenu(menu);
                        //Creation action stage
                        List<TreeNode> listEnfants = nodeaffichage.getChildren();
                        for (TreeNode enfantNode : listEnfants) {
                            String actionApprochantier = "";
                            actionApprochantier = enfantNode.getData().toString();
                            Actionmenu actionmenu = new Actionmenu();
                            int idM = ejbMenu.maxMenu();
                            Menu mn = ejbMenu.find(idM);
                            actionmenu.setLibelleaction(actionApprochantier);
                            actionmenu.setIdMenu(mn);
                            ejbActionmenu.insertAction(actionmenu);
                            System.out.println("Enfant Approvisionnement chantier: " + actionApprochantier);
                        }
                        creationActionApprochantier++;
                    }
                    
                } else {
                    
                    if (nodeParent.equals("Approvisionnement chantier")) {
                        System.out.println("Second cas");
                        if (creationModuleAppro == 0) {
                            //Creation module config
                            Droitacces droitacces1 = new Droitacces();
                            droitacces1.setModule("Approvisionnement");
                            droitacces1.setIdUtilisateur(user);
                            droitacces1.setIdRole(user.getIdRole());
                            ejbDroitacces.insertDroit(droitacces1);
                            creationModuleAppro++;
                        }
                        if (creationActionApprochantier == 0) {
                            //Creation menu utilisateur
                            Menu menu = new Menu();
                            int idP = ejbDroitacces.maxDroit();
                            Droitacces daac = ejbDroitacces.find(idP);
                            menu.setLibelemenu("Approvisionnement chantier");
                            menu.setIdPrivilege(daac);
                            ejbMenu.insertMenu(menu);
                            //Creation action stage
                            for (TreeNode enfantNode : this.selectedNodes) {
                                if (enfantNode.getParent().getData().toString().equalsIgnoreCase("Approvisionnement chantier")) {
                                    String actionApprochantier = "";
                                    actionApprochantier = enfantNode.getData().toString();
                                    Actionmenu actionmenu = new Actionmenu();
                                    int idM = ejbMenu.maxMenu();
                                    Menu mn = ejbMenu.find(idM);
                                    actionmenu.setLibelleaction(actionApprochantier);
                                    actionmenu.setIdMenu(mn);
                                    ejbActionmenu.insertAction(actionmenu);
                                    System.out.println("Enfant Approvisionnement chantier: " + actionApprochantier);
                                }
                                
                            }
                            creationActionApprochantier++;
                        }
                        
                    }
                    
                }

                //Gestion des Entrepots
                if (node.equals("Entrepots")) {
                    System.out.println("Parent: Entrepots");
                    if (creationModuleStock == 0) {
                        //Creation module config
                        Droitacces droitacces1 = new Droitacces();
                        droitacces1.setModule("Stock");
                        droitacces1.setIdUtilisateur(user);
                        droitacces1.setIdRole(user.getIdRole());
                        ejbDroitacces.insertDroit(droitacces1);
                        creationModuleStock++;
                    }
                    
                    if (creationMenuStock == 0) {
                        //Creation menu role
                        Menu menu = new Menu();
                        int idP = ejbDroitacces.maxDroit();
                        Droitacces daac = ejbDroitacces.find(idP);
                        menu.setLibelemenu("Entrepots");
                        menu.setIdPrivilege(daac);
                        ejbMenu.insertMenu(menu);
                        creationMenuStock++;
                    }
                    
                    if (creationActionStock == 0) {
                        //Creation action role
                        List<TreeNode> listEnfants = nodeaffichage.getChildren();
                        for (TreeNode enfantNode : listEnfants) {
                            String actionEntrepots = "";
                            actionEntrepots = enfantNode.getData().toString();
                            Actionmenu actionmenu = new Actionmenu();
                            int idM = ejbMenu.maxMenu();
                            Menu mn = ejbMenu.find(idM);
                            actionmenu.setLibelleaction(actionEntrepots);
                            actionmenu.setIdMenu(mn);
                            ejbActionmenu.insertAction(actionmenu);
                            System.out.println("Enfant Entrepots: " + actionEntrepots);
                        }
                        creationActionStock++;
                    }
                    
                } else {
                    
                    if (nodeParent.equals("Entrepots")) {
                        System.out.println("Second cas");
                        if (creationModuleStock == 0) {
                            //Creation module config
                            Droitacces droitacces1 = new Droitacces();
                            droitacces1.setModule("Stock");
                            droitacces1.setIdUtilisateur(user);
                            droitacces1.setIdRole(user.getIdRole());
                            ejbDroitacces.insertDroit(droitacces1);
                            creationModuleStock++;
                        }
                        
                        if (creationMenuStock == 0) {
                            //Creation menu role
                            Menu menu = new Menu();
                            int idP = ejbDroitacces.maxDroit();
                            Droitacces daac = ejbDroitacces.find(idP);
                            menu.setLibelemenu("Entrepots");
                            menu.setIdPrivilege(daac);
                            ejbMenu.insertMenu(menu);
                            creationMenuStock++;
                        }
                        
                        if (creationActionStock == 0) {
                            //Creation action role
                            for (TreeNode enfantNode : this.selectedNodes) {
                                if (enfantNode.getParent().getData().toString().equalsIgnoreCase("Entrepots")) {
                                    String actionEntrepots = "";
                                    actionEntrepots = enfantNode.getData().toString();
                                    Actionmenu actionmenu = new Actionmenu();
                                    int idM = ejbMenu.maxMenu();
                                    Menu mn = ejbMenu.find(idM);
                                    actionmenu.setLibelleaction(actionEntrepots);
                                    actionmenu.setIdMenu(mn);
                                    ejbActionmenu.insertAction(actionmenu);
                                    System.out.println("Enfant Entrepots: " + actionEntrepots);
                                }
                                
                            }
                            creationActionStock++;
                        }
                    }
                    
                }

                //Gestion des Inventaire
                if (node.equals("Inventaire")) {
                    System.out.println("Parent: Inventaire");
                    if (creationModuleStock == 0) {
                        //Creation module config
                        Droitacces droitacces1 = new Droitacces();
                        droitacces1.setModule("Stock");
                        droitacces1.setIdUtilisateur(user);
                        droitacces1.setIdRole(user.getIdRole());
                        ejbDroitacces.insertDroit(droitacces1);
                        creationModuleStock++;
                    }
                    if (creationActionInventaire == 0) {
                        //Creation menu utilisateur
                        Menu menu = new Menu();
                        int idP = ejbDroitacces.maxDroit();
                        Droitacces daac = ejbDroitacces.find(idP);
                        menu.setLibelemenu("Inventaire");
                        menu.setIdPrivilege(daac);
                        ejbMenu.insertMenu(menu);
                        //Creation action stage
                        List<TreeNode> listEnfants = nodeaffichage.getChildren();
                        for (TreeNode enfantNode : listEnfants) {
                            String actionInventaire = "";
                            actionInventaire = enfantNode.getData().toString();
                            Actionmenu actionmenu = new Actionmenu();
                            int idM = ejbMenu.maxMenu();
                            Menu mn = ejbMenu.find(idM);
                            actionmenu.setLibelleaction(actionInventaire);
                            actionmenu.setIdMenu(mn);
                            ejbActionmenu.insertAction(actionmenu);
                            System.out.println("Enfant Approvisionnement Inventaire: " + actionInventaire);
                        }
                        creationActionInventaire++;
                    }
                    
                } else {
                    
                    if (nodeParent.equals("Inventaire")) {
                        System.out.println("Second cas");
                        if (creationModuleStock == 0) {
                            //Creation module config
                            Droitacces droitacces1 = new Droitacces();
                            droitacces1.setModule("Stock");
                            droitacces1.setIdUtilisateur(user);
                            droitacces1.setIdRole(user.getIdRole());
                            ejbDroitacces.insertDroit(droitacces1);
                            creationModuleStock++;
                        }
                        if (creationActionInventaire == 0) {
                            //Creation menu utilisateur
                            Menu menu = new Menu();
                            int idP = ejbDroitacces.maxDroit();
                            Droitacces daac = ejbDroitacces.find(idP);
                            menu.setLibelemenu("Inventaire");
                            menu.setIdPrivilege(daac);
                            ejbMenu.insertMenu(menu);
                            //Creation action stage
                            for (TreeNode enfantNode : this.selectedNodes) {
                                if (enfantNode.getParent().getData().toString().equalsIgnoreCase("Inventaire")) {
                                    String actionInventaire = "";
                                    actionInventaire = enfantNode.getData().toString();
                                    Actionmenu actionmenu = new Actionmenu();
                                    int idM = ejbMenu.maxMenu();
                                    Menu mn = ejbMenu.find(idM);
                                    actionmenu.setLibelleaction(actionInventaire);
                                    actionmenu.setIdMenu(mn);
                                    ejbActionmenu.insertAction(actionmenu);
                                    System.out.println("Enfant Approvisionnement Inventaire: " + actionInventaire);
                                }
                                
                            }
                            creationActionInventaire++;
                        }
                        
                    }
                    
                }

                //Gestion des Majours
                if (node.equals("Mise à jour")) {
                    System.out.println("Parent: Mise à jour");
                    if (creationModuleStock == 0) {
                        //Creation module config
                        Droitacces droitacces1 = new Droitacces();
                        droitacces1.setModule("Stock");
                        droitacces1.setIdUtilisateur(user);
                        droitacces1.setIdRole(user.getIdRole());
                        ejbDroitacces.insertDroit(droitacces1);
                        creationModuleStock++;
                    }
                    if (creationActionMajours == 0) {
                        //Creation menu utilisateur
                        Menu menu = new Menu();
                        int idP = ejbDroitacces.maxDroit();
                        Droitacces daac = ejbDroitacces.find(idP);
                        menu.setLibelemenu("Mise à jour");
                        menu.setIdPrivilege(daac);
                        ejbMenu.insertMenu(menu);
                        //Creation action stage
                        List<TreeNode> listEnfants = nodeaffichage.getChildren();
                        for (TreeNode enfantNode : listEnfants) {
                            String actionMajours = "";
                            actionMajours = enfantNode.getData().toString();
                            Actionmenu actionmenu = new Actionmenu();
                            int idM = ejbMenu.maxMenu();
                            Menu mn = ejbMenu.find(idM);
                            actionmenu.setLibelleaction(actionMajours);
                            actionmenu.setIdMenu(mn);
                            ejbActionmenu.insertAction(actionmenu);
                            System.out.println("Enfant Approvisionnement Mise à jour: " + actionMajours);
                        }
                        creationActionMajours++;
                    }
                    
                } else {
                    
                    if (nodeParent.equals("Mise à jour")) {
                        System.out.println("Second cas");
                        if (creationModuleStock == 0) {
                            //Creation module config
                            Droitacces droitacces1 = new Droitacces();
                            droitacces1.setModule("Stock");
                            droitacces1.setIdUtilisateur(user);
                            droitacces1.setIdRole(user.getIdRole());
                            ejbDroitacces.insertDroit(droitacces1);
                            creationModuleStock++;
                        }
                        if (creationActionMajours == 0) {
                            //Creation menu utilisateur
                            Menu menu = new Menu();
                            int idP = ejbDroitacces.maxDroit();
                            Droitacces daac = ejbDroitacces.find(idP);
                            menu.setLibelemenu("Mise à jour");
                            menu.setIdPrivilege(daac);
                            ejbMenu.insertMenu(menu);
                            //Creation action stage
                            for (TreeNode enfantNode : this.selectedNodes) {
                                if (enfantNode.getParent().getData().toString().equalsIgnoreCase("Mise à jour")) {
                                    String actionMajours = "";
                                    actionMajours = enfantNode.getData().toString();
                                    Actionmenu actionmenu = new Actionmenu();
                                    int idM = ejbMenu.maxMenu();
                                    Menu mn = ejbMenu.find(idM);
                                    actionmenu.setLibelleaction(actionMajours);
                                    actionmenu.setIdMenu(mn);
                                    ejbActionmenu.insertAction(actionmenu);
                                    System.out.println("Enfant Approvisionnement Mise à jour: " + actionMajours);
                                }
                                
                            }
                            creationActionMajours++;
                        }
                        
                    }
                    
                }

                //Gestion des Sortiestock
                if (node.equals("Sortie de stock")) {
                    System.out.println("Parent: Sortie de stock");
                    if (creationModuleStock == 0) {
                        //Creation module config
                        Droitacces droitacces1 = new Droitacces();
                        droitacces1.setModule("Stock");
                        droitacces1.setIdUtilisateur(user);
                        droitacces1.setIdRole(user.getIdRole());
                        ejbDroitacces.insertDroit(droitacces1);
                        creationModuleStock++;
                    }
                    if (creationActionSortiestock == 0) {
                        //Creation menu utilisateur
                        Menu menu = new Menu();
                        int idP = ejbDroitacces.maxDroit();
                        Droitacces daac = ejbDroitacces.find(idP);
                        menu.setLibelemenu("Sortie de stock");
                        menu.setIdPrivilege(daac);
                        ejbMenu.insertMenu(menu);
                        //Creation action stage
                        List<TreeNode> listEnfants = nodeaffichage.getChildren();
                        for (TreeNode enfantNode : listEnfants) {
                            String actionSortiestock = "";
                            actionSortiestock = enfantNode.getData().toString();
                            Actionmenu actionmenu = new Actionmenu();
                            int idM = ejbMenu.maxMenu();
                            Menu mn = ejbMenu.find(idM);
                            actionmenu.setLibelleaction(actionSortiestock);
                            actionmenu.setIdMenu(mn);
                            ejbActionmenu.insertAction(actionmenu);
                            System.out.println("Enfant Approvisionnement Sortie de stock: " + actionSortiestock);
                        }
                        creationActionSortiestock++;
                    }
                    
                } else {
                    
                    if (nodeParent.equals("Sortie de stock")) {
                        System.out.println("Second cas");
                        if (creationModuleStock == 0) {
                            //Creation module config
                            Droitacces droitacces1 = new Droitacces();
                            droitacces1.setModule("Stock");
                            droitacces1.setIdUtilisateur(user);
                            droitacces1.setIdRole(user.getIdRole());
                            ejbDroitacces.insertDroit(droitacces1);
                            creationModuleStock++;
                        }
                        if (creationActionSortiestock == 0) {
                            //Creation menu utilisateur
                            Menu menu = new Menu();
                            int idP = ejbDroitacces.maxDroit();
                            Droitacces daac = ejbDroitacces.find(idP);
                            menu.setLibelemenu("Sortie de stock");
                            menu.setIdPrivilege(daac);
                            ejbMenu.insertMenu(menu);
                            //Creation action stage
                            for (TreeNode enfantNode : this.selectedNodes) {
                                if (enfantNode.getParent().getData().toString().equalsIgnoreCase("Sortie de stock")) {
                                    String actionSortiestock = "";
                                    actionSortiestock = enfantNode.getData().toString();
                                    Actionmenu actionmenu = new Actionmenu();
                                    int idM = ejbMenu.maxMenu();
                                    Menu mn = ejbMenu.find(idM);
                                    actionmenu.setLibelleaction(actionSortiestock);
                                    actionmenu.setIdMenu(mn);
                                    ejbActionmenu.insertAction(actionmenu);
                                    System.out.println("Enfant Approvisionnement Sortie de stock: " + actionSortiestock);
                                }
                                
                            }
                            creationActionSortiestock++;
                        }
                        
                    }
                    
                }

                //Gestion des Historique
                if (node.equals("Historique livraison")) {
                    System.out.println("Parent: Historique livraison");
                    if (creationModuleStock == 0) {
                        //Creation module config
                        Droitacces droitacces1 = new Droitacces();
                        droitacces1.setModule("Stock");
                        droitacces1.setIdUtilisateur(user);
                        droitacces1.setIdRole(user.getIdRole());
                        ejbDroitacces.insertDroit(droitacces1);
                        creationModuleStock++;
                    }
                    if (creationActionHistorique == 0) {
                        //Creation menu utilisateur
                        Menu menu = new Menu();
                        int idP = ejbDroitacces.maxDroit();
                        Droitacces daac = ejbDroitacces.find(idP);
                        menu.setLibelemenu("Historique livraison");
                        menu.setIdPrivilege(daac);
                        ejbMenu.insertMenu(menu);
                        //Creation action stage
                        List<TreeNode> listEnfants = nodeaffichage.getChildren();
                        for (TreeNode enfantNode : listEnfants) {
                            String actionHistorique = "";
                            actionHistorique = enfantNode.getData().toString();
                            Actionmenu actionmenu = new Actionmenu();
                            int idM = ejbMenu.maxMenu();
                            Menu mn = ejbMenu.find(idM);
                            actionmenu.setLibelleaction(actionHistorique);
                            actionmenu.setIdMenu(mn);
                            ejbActionmenu.insertAction(actionmenu);
                            System.out.println("Enfant Approvisionnement Historique livraison: " + actionHistorique);
                        }
                        creationActionHistorique++;
                    }
                    
                } else {
                    
                    if (nodeParent.equals("Historique livraison")) {
                        System.out.println("Second cas");
                        if (creationModuleStock == 0) {
                            //Creation module config
                            Droitacces droitacces1 = new Droitacces();
                            droitacces1.setModule("Stock");
                            droitacces1.setIdUtilisateur(user);
                            droitacces1.setIdRole(user.getIdRole());
                            ejbDroitacces.insertDroit(droitacces1);
                            creationModuleStock++;
                        }
                        if (creationActionHistorique == 0) {
                            //Creation menu utilisateur
                            Menu menu = new Menu();
                            int idP = ejbDroitacces.maxDroit();
                            Droitacces daac = ejbDroitacces.find(idP);
                            menu.setLibelemenu("Historique livraison");
                            menu.setIdPrivilege(daac);
                            ejbMenu.insertMenu(menu);
                            //Creation action stage
                            for (TreeNode enfantNode : this.selectedNodes) {
                                if (enfantNode.getParent().getData().toString().equalsIgnoreCase("Historique livraison")) {
                                    String actionHistorique = "";
                                    actionHistorique = enfantNode.getData().toString();
                                    Actionmenu actionmenu = new Actionmenu();
                                    int idM = ejbMenu.maxMenu();
                                    Menu mn = ejbMenu.find(idM);
                                    actionmenu.setLibelleaction(actionHistorique);
                                    actionmenu.setIdMenu(mn);
                                    ejbActionmenu.insertAction(actionmenu);
                                    System.out.println("Enfant Approvisionnement Historique livraison: " + actionHistorique);
                                }
                                
                            }
                            creationActionHistorique++;
                        }
                    }
                    
                }

                //Gestion des Opportunite
                if (node.equals("Opportunités")) {
                    System.out.println("Parent: Opportunités");
                    if (creationModuleOpportunite == 0) {
                        //Creation module config
                        Droitacces droitacces1 = new Droitacces();
                        droitacces1.setModule("Opportunités");
                        droitacces1.setIdUtilisateur(user);
                        droitacces1.setIdRole(user.getIdRole());
                        ejbDroitacces.insertDroit(droitacces1);
                        creationModuleOpportunite++;
                    }
                    
                    if (creationMenuOpportunite == 0) {
                        //Creation menu role
                        Menu menu = new Menu();
                        int idP = ejbDroitacces.maxDroit();
                        Droitacces daac = ejbDroitacces.find(idP);
                        menu.setLibelemenu("Opportunités");
                        menu.setIdPrivilege(daac);
                        ejbMenu.insertMenu(menu);
                        creationMenuOpportunite++;
                    }
                    
                    if (creationActionOpportunite == 0) {
                        //Creation action role
                        List<TreeNode> listEnfants = nodeaffichage.getChildren();
                        for (TreeNode enfantNode : listEnfants) {
                            String actionOpportunite = "";
                            actionOpportunite = enfantNode.getData().toString();
                            Actionmenu actionmenu = new Actionmenu();
                            int idM = ejbMenu.maxMenu();
                            Menu mn = ejbMenu.find(idM);
                            actionmenu.setLibelleaction(actionOpportunite);
                            actionmenu.setIdMenu(mn);
                            ejbActionmenu.insertAction(actionmenu);
                            System.out.println("Enfant Opportunités: " + actionOpportunite);
                        }
                        creationActionOpportunite++;
                    }
                    
                } else {
                    
                    if (nodeParent.equals("Opportunités")) {
                        System.out.println("Second cas");
                        if (creationModuleOpportunite == 0) {
                            //Creation module config
                            Droitacces droitacces1 = new Droitacces();
                            droitacces1.setModule("Opportunités");
                            droitacces1.setIdUtilisateur(user);
                            droitacces1.setIdRole(user.getIdRole());
                            ejbDroitacces.insertDroit(droitacces1);
                            creationModuleOpportunite++;
                        }
                        
                        if (creationMenuOpportunite == 0) {
                            //Creation menu role
                            Menu menu = new Menu();
                            int idP = ejbDroitacces.maxDroit();
                            Droitacces daac = ejbDroitacces.find(idP);
                            menu.setLibelemenu("Opportunités");
                            menu.setIdPrivilege(daac);
                            ejbMenu.insertMenu(menu);
                            creationMenuOpportunite++;
                        }
                        
                        if (creationActionOpportunite == 0) {
                            //Creation action role
                            for (TreeNode enfantNode : this.selectedNodes) {
                                if (enfantNode.getParent().getData().toString().equalsIgnoreCase("Opportunités")) {
                                    String actionOpportunite = "";
                                    actionOpportunite = enfantNode.getData().toString();
                                    Actionmenu actionmenu = new Actionmenu();
                                    int idM = ejbMenu.maxMenu();
                                    Menu mn = ejbMenu.find(idM);
                                    actionmenu.setLibelleaction(actionOpportunite);
                                    actionmenu.setIdMenu(mn);
                                    ejbActionmenu.insertAction(actionmenu);
                                    System.out.println("Enfant Opportunités: " + actionOpportunite);
                                }
                                
                            }
                            creationActionOpportunite++;
                        }
                        
                    }
                    
                }
                
            }
            
            return "users";
            
        } catch (Exception e) {
            return "edit_users";
        }
        
    }
    
}
