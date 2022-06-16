/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.controller;                                        

import java.io.File;            
import java.io.Serializable;        
import java.net.MalformedURLException;   
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;  
import javax.servlet.http.HttpServletRequest;      
import javax.servlet.http.HttpSession;        
import org.primefaces.context.RequestContext;
import sn.accelsolution.dao.NotificationFacade;
import sn.accelsolution.entities.Actionmenu;
import sn.accelsolution.entities.Notification;    
import sn.accelsolution.util.UtilControleMenu;    

/**
 *
 * @author DV7
 */
@ManagedBean
@ViewScoped
public class NotificationBean implements Serializable {

    @EJB
    NotificationFacade ejbNotification;
    /**
     * Creates a new instance of NotificationBean
     */
    private String bip;
    private int number;
    private String notificationExpression;
    private String validationExpression;
    private List<Actionmenu> myllActionmenus;
    private List<Notification> myllNotificationExpressions;
    private List<Notification> myllNotificationSalaire;
    private List<Notification> myllNotificationDevis;
    private List<Notification> myllNotificationfacture;
    private List<Notification> myllNotificationDecompte;
    private List<Notification> myllNotificationCommandeExp;
    private List<Notification> myllNotificationCommandeCmd;
    private Notification notification;

    @PostConstruct
    public void init() {
        //Localhost
        //this.setBip("file:///C:/Users/DV7/Documents/NetBeansProjects/Kaisen/web/resources/Son/BipSonor.mp3");
        //Online
        String sourceFileName = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Son/BipSonor.mp3");
        this.setBip(sourceFileName);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UtilControleMenu utilControleMenu = new UtilControleMenu();

        myllActionmenus = (List<Actionmenu>) session.getAttribute("allActionmenusFromTraitementExpressionBesoins"); 

        this.setNotificationExpression(utilControleMenu.notifierExpression(myllActionmenus));
        this.setValidationExpression(utilControleMenu.validerExpression(myllActionmenus));
    }

    public NotificationBean() {

    }

    public List<Notification> getMyllNotificationCommandeCmd() {
        return myllNotificationCommandeCmd;
    }

    public void setMyllNotificationCommandeCmd(List<Notification> myllNotificationCommandeCmd) {
        this.myllNotificationCommandeCmd = myllNotificationCommandeCmd;
    }

    
    public List<Notification> getMyllNotificationCommandeExp() {
        return myllNotificationCommandeExp;
    }

    public void setMyllNotificationCommandeExp(List<Notification> myllNotificationCommandeExp) {
        this.myllNotificationCommandeExp = myllNotificationCommandeExp;
    }

    public List<Notification> getMyllNotificationDecompte() {
        return myllNotificationDecompte;
    }

    public void setMyllNotificationDecompte(List<Notification> myllNotificationDecompte) {
        this.myllNotificationDecompte = myllNotificationDecompte;
    }

    public List<Notification> getMyllNotificationfacture() {
        return myllNotificationfacture;
    }

    public void setMyllNotificationfacture(List<Notification> myllNotificationfacture) {
        this.myllNotificationfacture = myllNotificationfacture;
    }

    public List<Notification> getMyllNotificationSalaire() {
        return myllNotificationSalaire;
    }

    public void setMyllNotificationSalaire(List<Notification> myllNotificationSalaire) {
        this.myllNotificationSalaire = myllNotificationSalaire;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public String getBip() {
        return bip;
    }

    public void setBip(String bip) {
        this.bip = bip;
    }

    public String getValidationExpression() {
        return validationExpression;
    }

    public void setValidationExpression(String validationExpression) {
        this.validationExpression = validationExpression;
    }

    public List<Notification> getMyllNotificationExpressions() {
        return myllNotificationExpressions;
    }

    public void setMyllNotificationExpressions(List<Notification> myllNotificationExpressions) {
        this.myllNotificationExpressions = myllNotificationExpressions;
    }

    public String getNotificationExpression() {
        return notificationExpression;
    }

    public void setNotificationExpression(String notificationExpression) {
        this.notificationExpression = notificationExpression;
    }

    public List<Actionmenu> getMyllActionmenus() {
        return myllActionmenus;
    }

    public void setMyllActionmenus(List<Actionmenu> myllActionmenus) {
        this.myllActionmenus = myllActionmenus;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Notification> getMyllNotificationDevis() {
        return myllNotificationDevis;
    }

    public void setMyllNotificationDevis(List<Notification> myllNotificationDevis) {
        this.myllNotificationDevis = myllNotificationDevis;
    }

    public void increment() {
        int nbElementExp = 0;
        this.number = 0;
        this.myllNotificationExpressions = new ArrayList<>();
        this.myllNotificationSalaire = new ArrayList<>();
        this.myllNotificationDevis = new ArrayList<>();
        this.myllNotificationfacture = new ArrayList<>();
        this.myllNotificationDecompte = new ArrayList<>();
        this.myllNotificationCommandeExp = new ArrayList<>();
        this.myllNotificationCommandeCmd = new ArrayList<>();

       // if (this.notificationExpression.equals("yes")) {

            this.myllNotificationExpressions = ejbNotification.getListOfNotificationExpressions();
            this.myllNotificationSalaire = ejbNotification.getListOfNotificationSalaire();
            this.myllNotificationDevis = ejbNotification.getListOfNotificationDevis();
            this.myllNotificationfacture = ejbNotification.getListOfNotificationfacture();
            this.myllNotificationDecompte = ejbNotification.getListOfNotificationDecompte();
            this.myllNotificationCommandeExp = ejbNotification.getListOfNotificationExpressions();
            this.myllNotificationCommandeCmd = ejbNotification.getListOfNotificationCommande();

            if (this.myllNotificationExpressions != null) {  
//                System.out.println("La liste n'est pas vide");
                for (Notification n : this.myllNotificationExpressions) {
                    nbElementExp++;
                    if (n.getEtatNotification().equals("Non lu")) {
//                       
                        try {
                            /*BasicPlayer player = new BasicPlayer();
                            player.open(new URL(this.bip));
                            player.play();*/
                            RequestContext.getCurrentInstance().execute("playSound();");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        //Modifier l'etat
                        n.setEtatNotification("Lu");
                        ejbNotification.edit(n);
                    }
                }

                this.number = this.number + nbElementExp;
            }

            if (this.myllNotificationSalaire != null) {
//                System.out.println("La liste n'est pas vide");
                for (Notification n : this.myllNotificationSalaire) {
                    nbElementExp++;
                    if (n.getEtatNotification().equals("Non lu")) {
//                       
                        try {
                            RequestContext.getCurrentInstance().execute("playSound();");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        //Modifier l'etat
                        n.setEtatNotification("Lu");
                        ejbNotification.edit(n);
                    }
                }

                this.number = this.number + nbElementExp;
            }

            if (this.myllNotificationDevis != null) {
//                System.out.println("La liste n'est pas vide");
                for (Notification n : this.myllNotificationDevis) {
                    nbElementExp++;
                    if (n.getEtatNotification().equals("Non lu")) {
//                       
                        try {
                            RequestContext.getCurrentInstance().execute("playSound();");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        //Modifier l'etat
                        n.setEtatNotification("Lu");
                        ejbNotification.edit(n);
                    }
                }

                this.number = this.number + nbElementExp;
            }

            if (this.myllNotificationfacture != null) {
//                System.out.println("La liste n'est pas vide");
                for (Notification n : this.myllNotificationfacture) {
                    nbElementExp++;
                    if (n.getEtatNotification().equals("Non lu")) {
//                       
                        try {
                            RequestContext.getCurrentInstance().execute("playSound();");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        //Modifier l'etat
                        n.setEtatNotification("Lu");
                        ejbNotification.edit(n);
                    }
                }

                this.number = this.number + nbElementExp;
            }

            if (this.myllNotificationDecompte != null) {
//                System.out.println("La liste n'est pas vide");
                for (Notification n : this.myllNotificationDecompte) {
                    nbElementExp++;
                    if (n.getEtatNotification().equals("Non lu")) {
//                       
                        try {
                            RequestContext.getCurrentInstance().execute("playSound();");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        //Modifier l'etat
                        n.setEtatNotification("Lu");
                        ejbNotification.edit(n);  
                    }
                }

                this.number = this.number + nbElementExp;   
            }

//            if (this.myllNotificationCommandeExp != null) {
////                System.out.println("La liste n'est pas vide");
//                for (Notification n : this.myllNotificationCommandeExp) {
//                    nbElementExp++;
//                    if (n.getEtatNotification().equals("Non lu")) {
////                       
//                        try {
//                            BasicPlayer player = new BasicPlayer();
//                            player.open(new URL(this.bip));
//                            player.play();
//                        } catch (BasicPlayerException | MalformedURLException e) {
//                            e.printStackTrace();
//                        }
//
//                        //Modifier l'etat
//                        n.setEtatNotification("Lu");
//                        ejbNotification.edit(n);
//                    }
//                }
//
//                this.number = this.number + nbElementExp;
//            } 
            
            
            if (this.myllNotificationCommandeCmd != null) {
//                System.out.println("La liste n'est pas vide");
                for (Notification n : this.myllNotificationCommandeCmd) {
                    nbElementExp++;
                    if (n.getEtatNotification().equals("Non lu")) {
//                       
                        try {
                            RequestContext.getCurrentInstance().execute("playSound();");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        //Modifier l'etat
                        n.setEtatNotification("Lu");
                        ejbNotification.edit(n);
                    }
                }

                this.number = this.number + nbElementExp;
            }

        //}
    }

    public String traiterExpression() {
        System.out.println("Notification: " + notification.getIdNotification());
        HttpSession session1 = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        session1.setAttribute("myNotificationExpression", notification);
        //return "traitement_notificationExpression";
        return "traitementEBN";
    }

    public String traiterSalaire() {
        System.out.println("Notification: " + notification.getIdNotification());
        HttpSession session1 = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        session1.setAttribute("myNotificationSalaire", notification);
        return "traitement_notificationSalaire";
    }

    public String traiterDevis() {
        System.out.println("Notification: " + notification.getIdNotification());
        HttpSession session1 = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        session1.setAttribute("myNotificationDevis", notification);
        return "traitement_notificationDevis";
    }

    public String traiterFacture() {
        System.out.println("Notification: " + notification.getIdNotification());
        HttpSession session1 = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        session1.setAttribute("myNotificationFacture", notification);
        return "traitement_notificationFacture";
    }

    public String traiterDecompte() {
        System.out.println("Notification: " + notification.getIdNotification());
        HttpSession session1 = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        session1.setAttribute("myNotificationDecompte", notification);
        return "traitement_notificationDecompte";
    }

    public String traiterCommandeExp() {
        System.out.println("Notification: " + notification.getIdNotification());
        HttpSession session1 = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        session1.setAttribute("myCommandeExpression", notification);
        return "traitement_expressionTest";
    } 
    
    
    public String traiterCommandeCmd() {
        System.out.println("Notification: " + notification.getIdNotification());
        HttpSession session1 = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        session1.setAttribute("myCommandeCommande", notification);
        return "traitement_commandeTest";
    }

}
