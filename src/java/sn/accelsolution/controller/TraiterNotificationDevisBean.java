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
import sn.accelsolution.dao.DetailledevisFacade;
import sn.accelsolution.dao.DetailleexpressionbesoinFacade;
import sn.accelsolution.dao.DevisFacade;
import sn.accelsolution.dao.ExpressionbesoinFacade;
import sn.accelsolution.dao.NotificationFacade;
import sn.accelsolution.dao.SalaireFacade;
import sn.accelsolution.dao.UtilisateurFacade;
import sn.accelsolution.entities.Commande;
import sn.accelsolution.entities.DetailleCommande;
import sn.accelsolution.entities.Detailledevis;
import sn.accelsolution.entities.Detailleexpressionbesoin;
import sn.accelsolution.entities.Devis;
import sn.accelsolution.entities.Expressionbesoin;
import sn.accelsolution.entities.Notification;
import sn.accelsolution.entities.Salaire;
import sn.accelsolution.entities.Tonpomcoef;
import sn.accelsolution.util.FactureUtil;
import sn.accelsolution.util.MontantConverter;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DELL
 */
@ManagedBean
@ViewScoped
public class TraiterNotificationDevisBean implements Serializable {

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
    DetailledevisFacade ejbDetailleDevis;

    private Devis devis;
    private Notification notification;
    private String etatValidation;
    private String etatDevis;
    private String myCoefIntermediaire;
    private List<Detailledevis> allDetailleDevis;
    private List<FactureUtil> allFactureUtils;
    private List<FactureUtil> allFactureUtilsSauvegarde;
    private String totalHT;
    private String montantTVA;
    private String totalTTC;
    private String mttc;

    @PostConstruct
    public void init() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        notification = new Notification();
        devis = new Devis();
        notification = (Notification) session.getAttribute("myNotificationDevis");
        devis = ejbDevis.find(notification.getIdDevis().getIdDevis());


        /* List des detailles devis */
        this.setAllDetailleDevis(ejbDetailleDevis.listOfDetailleDevisByDevis(this.devis));
        MontantConverter mtc = new MontantConverter();
        Double mtht = 0.0;
        allFactureUtils.clear();
        allFactureUtilsSauvegarde.clear();

        for (Detailledevis df : this.allDetailleDevis) {
            FactureUtil fut = new FactureUtil();

            fut.setPuSansCoef(df.getPu());
            fut.setIdDetailleCommande(df.getIdDetailDevis());
            //String designUtil = utfconvert.convertFromUTF8(df.getDesignation());
            fut.setDesignation(df.getDesignation());
            fut.setTypeitem(df.getTypeitem());
            fut.setQuantite(df.getQuantite());
            fut.setPu(df.getPu());
            fut.setRef(df.getReference());
            fut.setUnite(df.getUnite());
            if (df.getTypeitem().equals("Item")) {
                Double puc = mtc.StringToDouble(df.getPu());
                Double ctotal = puc * df.getQuantite();
                mtht = mtht + ctotal;
                String rctotal = mtc.DoubleToString(ctotal);
                fut.setPrixtotal(rctotal);
            } else {
                if (!df.getPu().equals("0")) {
                    Double puc = mtc.StringToDouble(df.getPu());
                    Double ctotal = puc * df.getQuantite();
                    String rctotal = mtc.DoubleToString(ctotal);
                    fut.setPrixtotal(rctotal);
                } else {
                    fut.setPrixtotal("0");
                }
            }
            allFactureUtils.add(fut);
            allFactureUtilsSauvegarde.add(fut);
        }

        this.setMttc("");
        Double mthT = 0.0;
        for (Detailledevis df : this.allDetailleDevis) {
            if (df.getPu().equals("")) {

            } else {
                if (df.getTypeitem().equals("Item")) {
                    Double puc = mtc.StringToDouble(df.getPu());
                    Double ctotal = puc * df.getQuantite();
                    mthT = mthT + ctotal;
                }      

            }

        }

        /*TVA*/
        Double mthTTVA = mthT * 0.18;
        Double mtTTC = mthT + mthTTVA;
        String rctotal = mtc.DoubleToString(mtTTC);
        this.setMttc(rctotal);

    }

    public TraiterNotificationDevisBean() {
        allDetailleDevis = new ArrayList<>();
        // devis = new Devis();
        allFactureUtils = new ArrayList<>();
        allFactureUtilsSauvegarde = new ArrayList<>();
    }

    public List<Detailledevis> getAllDetailleDevis() {
        return allDetailleDevis;
    }

    public void setAllDetailleDevis(List<Detailledevis> allDetailleDevis) {
        this.allDetailleDevis = allDetailleDevis;
    }

    public List<FactureUtil> getAllFactureUtils() {
        return allFactureUtils;
    }

    public void setAllFactureUtils(List<FactureUtil> allFactureUtils) {
        this.allFactureUtils = allFactureUtils;
    }

    public List<FactureUtil> getAllFactureUtilsSauvegarde() {
        return allFactureUtilsSauvegarde;
    }

    public void setAllFactureUtilsSauvegarde(List<FactureUtil> allFactureUtilsSauvegarde) {
        this.allFactureUtilsSauvegarde = allFactureUtilsSauvegarde;
    }

    public String getTotalHT() {
        return totalHT;
    }

    public void setTotalHT(String totalHT) {
        this.totalHT = totalHT;
    }

    public String getMontantTVA() {
        return montantTVA;
    }

    public void setMontantTVA(String montantTVA) {
        this.montantTVA = montantTVA;
    }

    public String getTotalTTC() {
        return totalTTC;
    }

    public void setTotalTTC(String totalTTC) {
        this.totalTTC = totalTTC;
    }

    public String getMttc() {
        return mttc;
    }

    public void setMttc(String mttc) {
        this.mttc = mttc;
    }

    public String getMyCoefIntermediaire() {
        return myCoefIntermediaire;
    }

    public void setMyCoefIntermediaire(String myCoefIntermediaire) {
        this.myCoefIntermediaire = myCoefIntermediaire;
    }

    public Devis getDevis() {
        return devis;
    }

    public void setDevis(Devis devis) {
        this.devis = devis;
    }

    public String getEtatDevis() {
        return etatDevis;
    }

    public void setEtatDevis(String etatDevis) {
        this.etatDevis = etatDevis;
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

    public void renderInfoExpTraitement2() {
        try {

//            UtilUtfconvert utfconvert = new UtilUtfconvert();
//            this.devis.setEtat(utfconvert.convertFromUTF8(this.devis.getEtat()));
            if (this.etatDevis.equals("Rejeter")) {
                this.setEtatValidation("Rejeter");
            }
        } catch (Exception e) {
        }
    }

    public String save() {

        try {

            System.out.println("Valeur: " + this.devis.getIdDevis());

            /*UtilUtfconvert utfconvert = new UtilUtfconvert();
            this.setEtatDevis(utfconvert.convertFromUTF8(this.getEtatDevis()));
            this.devis.setMotif(utfconvert.convertFromUTF8(this.devis.getMotif()));*/
            
            if (etatDevis.equals("Rejeter")) {

                this.devis.setEtat("Rejeter");
                ejbDevis.edit(devis);

                this.notification.setTraitement("Fait");
                ejbNotification.edit(this.notification);

            } else {

                devis.setEtat("Valider");
                ejbDevis.edit(devis);

                this.notification.setTraitement("Fait");
                ejbNotification.edit(this.notification);

            }

            return "devis";
        } catch (Exception e) {

            return "traitement_notificationDevis";
        }
    }

}
