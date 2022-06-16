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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import sn.accelsolution.dao.CommandeFacade;
import sn.accelsolution.dao.DetailleCommandeFacade;
import sn.accelsolution.dao.DetailleexpressionbesoinFacade;
import sn.accelsolution.dao.DevisFacade;
import sn.accelsolution.dao.ExpressionbesoinFacade;
import sn.accelsolution.dao.NotificationFacade;
import sn.accelsolution.dao.SalaireFacade;
import sn.accelsolution.dao.UtilisateurFacade;
import sn.accelsolution.entities.Commande;
import sn.accelsolution.entities.DetailleCommande;
import sn.accelsolution.entities.Detailleexpressionbesoin;
import sn.accelsolution.entities.Expressionbesoin;
import sn.accelsolution.entities.Notification;
import sn.accelsolution.entities.Salaire;
import sn.accelsolution.util.FactureUtil;
import sn.accelsolution.util.MontantConverter;

/**
 *
 * @author DELL
 */
@ManagedBean
@ViewScoped
public class TraiterNotificationSalaireBean implements Serializable {

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
    SalaireFacade ejbSalaire;
    @EJB
    NotificationFacade ejbNotification;

    private Salaire salaire;
    private Notification notification;
    private String etatValidation;
    private String etatSalaire;

    @PostConstruct
    public void init() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        notification = new Notification();
        salaire = new Salaire();
        notification = (Notification) session.getAttribute("myNotificationSalaire");
        salaire = ejbSalaire.find(notification.getIdSalaire().getIdSalaire());

    }

    public TraiterNotificationSalaireBean() {

    }

    public String getEtatSalaire() {
        return etatSalaire;
    }

    public void setEtatSalaire(String etatSalaire) {
        this.etatSalaire = etatSalaire;
    }

    public String getEtatValidation() {
        return etatValidation;
    }

    public void setEtatValidation(String etatValidation) {
        this.etatValidation = etatValidation;
    }

    public Salaire getSalaire() {
        return salaire;
    }

    public void setSalaire(Salaire salaire) {
        this.salaire = salaire;
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

    public void renderInfoExpTraitement2() {
        try {
            if (this.etatSalaire.equals("Rejetée")) {
                this.setEtatValidation("Rejeter");
            }
        } catch (Exception e) {
        }
    }

    public String save() {

        try {

            System.out.println("Valeur: " + this.salaire.getIdSalaire());
      
            if (etatSalaire.equals("Rejeté")) {

                this.salaire.setEtat("Rejeté");
                ejbSalaire.edit(salaire);

                this.notification.setTraitement("Fait");
                ejbNotification.edit(this.notification);

            } else {

                salaire.setEtat("Payer");
                ejbSalaire.edit(salaire);

                this.notification.setTraitement("Fait");
                ejbNotification.edit(this.notification);

            }

            return "salaires";
        } catch (Exception e) {

            return "traitement_notificationSalaire";
        }
    }

}
