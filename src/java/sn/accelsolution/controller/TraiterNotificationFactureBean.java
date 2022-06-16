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
import sn.accelsolution.dao.DetaillenewfactureFacade;
import sn.accelsolution.dao.DevisFacade;
import sn.accelsolution.dao.ExpressionbesoinFacade;
import sn.accelsolution.dao.NewfactureFacade;
import sn.accelsolution.dao.NotificationFacade;
import sn.accelsolution.dao.SalaireFacade;
import sn.accelsolution.dao.UtilisateurFacade;
import sn.accelsolution.entities.Commande;
import sn.accelsolution.entities.DetailleCommande;
import sn.accelsolution.entities.Detailleexpressionbesoin;
import sn.accelsolution.entities.Detaillenewfacture;
import sn.accelsolution.entities.Devis;
import sn.accelsolution.entities.Expressionbesoin;
import sn.accelsolution.entities.Newfacture;
import sn.accelsolution.entities.Notification;
import sn.accelsolution.entities.Salaire;
import sn.accelsolution.util.FactureUtil;
import sn.accelsolution.util.MontantConverter;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DELL
 */
@ManagedBean
@ViewScoped
public class TraiterNotificationFactureBean implements Serializable {

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
    NewfactureFacade ejbNewFacture;
    @EJB
    DetaillenewfactureFacade ejbDetaillenewfactureFacade;

    private Newfacture newfacture;
    private Notification notification;
    private String etatValidation;
    private String etatFacture;
    private List<Detaillenewfacture> allDetailleFactures;

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
        newfacture = new Newfacture();
        notification = (Notification) session.getAttribute("myNotificationFacture");
        newfacture = ejbNewFacture.find(notification.getIdNewfacture().getIdNewfacture());

        /* List des detailles facture */
        this.setAllDetailleFactures(ejbDetaillenewfactureFacade.listOfDetailleFactureByfacture(newfacture));
        MontantConverter mtc = new MontantConverter();
        Double mtht = 0.0;
        allFactureUtils.clear();
        allFactureUtilsSauvegarde.clear();

        for (Detaillenewfacture df : this.allDetailleFactures) {
            FactureUtil fut = new FactureUtil();

            fut.setPuSansCoef(df.getPu());
            fut.setIdDetailleCommande(df.getIdDetailleNewfacture());
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
        for (Detaillenewfacture df : this.allDetailleFactures) {
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

    public TraiterNotificationFactureBean() {
        allDetailleFactures = new ArrayList<>();
        allFactureUtils = new ArrayList<>();
        allFactureUtilsSauvegarde = new ArrayList<>();
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

    public List<Detaillenewfacture> getAllDetailleFactures() {
        return allDetailleFactures;
    }

    public void setAllDetailleFactures(List<Detaillenewfacture> allDetailleFactures) {
        this.allDetailleFactures = allDetailleFactures;
    }

    public Newfacture getNewfacture() {
        return newfacture;
    }

    public void setNewfacture(Newfacture newfacture) {
        this.newfacture = newfacture;
    }

    public String getEtatFacture() {
        return etatFacture;
    }

    public void setEtatFacture(String etatFacture) {
        this.etatFacture = etatFacture;
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
//            this.newfacture.setEtat(utfconvert.convertFromUTF8(newfacture.getEtat()));
            if (this.etatFacture.equals("Rejeter")) {
                this.setEtatValidation("Rejeter");
            }
        } catch (Exception e) {
        }
    }

    public String save() {

        try {

            System.out.println("Valeur: " + this.newfacture.getIdDevis());
            /*UtilUtfconvert utfconvert = new UtilUtfconvert();
            this.setEtatFacture(utfconvert.convertFromUTF8(this.getEtatFacture()));
            this.newfacture.setMotif(utfconvert.convertFromUTF8(newfacture.getMotif()));*/
            if (etatFacture.equals("Rejeter")) {

                this.newfacture.setEtat("Rejeter");
                ejbNewFacture.edit(newfacture);

                this.notification.setTraitement("Fait");
                ejbNotification.edit(this.notification);

            } else {

                newfacture.setEtat("Valider");
                ejbNewFacture.edit(newfacture);

                this.notification.setTraitement("Fait");
                ejbNotification.edit(this.notification);

            }

            return "facture2";
        } catch (Exception e) {

            return "traitement_notificationFacture";
        }
    }

}
