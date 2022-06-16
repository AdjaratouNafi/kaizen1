/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.controller;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import sn.accelsolution.dao.DetailledevisFacade;
import sn.accelsolution.dao.DevisFacade;
import sn.accelsolution.dao.NotificationFacade;
import sn.accelsolution.dao.TonpomcoefFacade;
import sn.accelsolution.dao.UtilisateurFacade;
import sn.accelsolution.entities.Detailledevis;
import sn.accelsolution.entities.Devis;
import sn.accelsolution.entities.Notification;
import sn.accelsolution.entities.Tonpomcoef;
import sn.accelsolution.entities.Utilisateur;
import sn.accelsolution.util.FactureUtil;
import sn.accelsolution.util.GenerationCodePdf;
import sn.accelsolution.util.MontantConverter;
import sn.accelsolution.util.ReportAccelModel;
import sn.accelsolution.util.ReportFacture;
import sn.accelsolution.util.ReportModel;
import sn.accelsolution.util.UtilControleMenu;
import sn.accelsolution.util.UtilListItemData;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DELL
 */
@ManagedBean
@SessionScoped
public class DevisOtherBean implements Serializable {

    /**
     * Creates a new instance of ContratRecrutBean
     */
    @EJB
    UtilisateurFacade ejbUser;
    @EJB
    DevisFacade ejbDevis;
    @EJB
    DetailledevisFacade ejbDetailleDevis;
    @EJB
    NotificationFacade ejbNotification;
    @EJB
    TonpomcoefFacade ejbTonpomcoef;
    @EJB
    UtilisateurFacade ejbUtilisateur;

    private Devis devisFromEdit;
    private Detailledevis detailleDevisFromEdit;
    private List<Detailledevis> allDetailleDevis;
    private List<FactureUtil> allFactureUtils;
    private List<FactureUtil> allFactureUtilsSauvegarde;
    private String totalHT;
    private String montantTVA;
    private String totalTTC;
    private String mttc;
    private int firstPassage;
    private List<FactureUtil> listDevisSauvegarde;
    private Utilisateur userNotif;
    private Detailledevis detailleDevis;
    private String ancienCoef;
    private String myCoef;
    private String myCoefIntermediaire;
    private String emtyvalues;

    @PostConstruct
    public void init() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

        userNotif = new Utilisateur();
        userNotif = (Utilisateur) session.getAttribute("user");
        /*FactureUtil fu = new FactureUtil();
         fu.setDesignation("");
         fu.setPu("");
         fu.setQuantite(0);
         listDevis.add(fu);*/
    }

    public DevisOtherBean() {
        allDetailleDevis = new ArrayList<>();
        devisFromEdit = new Devis();
        detailleDevisFromEdit = new Detailledevis();
        allFactureUtils = new ArrayList<>();
        allFactureUtilsSauvegarde = new ArrayList<>();
        listDevisSauvegarde = new ArrayList<>();
        detailleDevis = new Detailledevis();
        this.firstPassage = 0;
        this.emtyvalues = "-";
    }

    public String getEmtyvalues() {
        return emtyvalues;
    }

    public void setEmtyvalues(String emtyvalues) {
        this.emtyvalues = emtyvalues;
    }

    public String getMyCoefIntermediaire() {
        return myCoefIntermediaire;
    }

    public void setMyCoefIntermediaire(String myCoefIntermediaire) {
        this.myCoefIntermediaire = myCoefIntermediaire;
    }

    public List<FactureUtil> getAllFactureUtilsSauvegarde() {
        return allFactureUtilsSauvegarde;
    }

    public void setAllFactureUtilsSauvegarde(List<FactureUtil> allFactureUtilsSauvegarde) {
        this.allFactureUtilsSauvegarde = allFactureUtilsSauvegarde;
    }

    public String getMyCoef() {
        return myCoef;
    }

    public void setMyCoef(String myCoef) {
        this.myCoef = myCoef;
    }

    public String getAncienCoef() {
        return ancienCoef;
    }

    public void setAncienCoef(String ancienCoef) {
        this.ancienCoef = ancienCoef;
    }

    public Detailledevis getDetailleDevis() {
        return detailleDevis;
    }

    public void setDetailleDevis(Detailledevis detailleDevis) {
        this.detailleDevis = detailleDevis;
    }

    public Utilisateur getUserNotif() {
        return userNotif;
    }

    public void setUserNotif(Utilisateur userNotif) {
        this.userNotif = userNotif;
    }

    public List<FactureUtil> getListDevisSauvegarde() {
        return listDevisSauvegarde;
    }

    public void setListDevisSauvegarde(List<FactureUtil> listDevisSauvegarde) {
        this.listDevisSauvegarde = listDevisSauvegarde;
    }

    public int getFirstPassage() {
        return firstPassage;
    }

    public void setFirstPassage(int firstPassage) {
        this.firstPassage = firstPassage;
    }

    public String getMttc() {
        return mttc;
    }

    public void setMttc(String mttc) {
        this.mttc = mttc;
    }

    public Devis getDevisFromEdit() {
        return devisFromEdit;
    }

    public void setDevisFromEdit(Devis devisFromEdit) {
        this.devisFromEdit = devisFromEdit;
    }

    public Detailledevis getDetailleDevisFromEdit() {
        return detailleDevisFromEdit;
    }

    public void setDetailleDevisFromEdit(Detailledevis detailleDevisFromEdit) {
        this.detailleDevisFromEdit = detailleDevisFromEdit;
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

    public String detail(Devis d) {
        try {

            System.out.println("Adresse: " + d.getIdClient().getAdresse());
            // UtilUtfconvert utfconvert = new UtilUtfconvert();

//            String nomCltUtil = utfconvert.convertFromUTF8(d.getIdClient().getNomClient());
//            d.getIdClient().setNomClient(nomCltUtil);
//            String adrCltUtil = utfconvert.convertFromUTF8(d.getIdClient().getAdresse());
//            d.getIdClient().setAdresse(adrCltUtil);
            this.setDevisFromEdit(d);

            System.out.println("Devis :" + this.devisFromEdit.getNumeroDevis());
            /* List des detailles devis */
            this.allDetailleDevis.clear();
            this.setAllDetailleDevis(ejbDetailleDevis.listOfDetailleDevisByDevis(this.getDevisFromEdit()));
            MontantConverter mtc = new MontantConverter();
            Double mtht = 0.0;
            allFactureUtils.clear();

            for (Detailledevis df : this.allDetailleDevis) {
                FactureUtil fut = new FactureUtil();
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
            }

            /*Montant HT*/
            String rmtht = mtc.DoubleToString(mtht);
            this.setTotalHT(rmtht);
            /*Montant TVA*/
            Double mttva = mtht * 0.18;
            String rmtva = mtc.DoubleToString(mttva);
            this.setMontantTVA(rmtva);
            /*Montant TTC*/
            Double mttc = mtht + mttva;
            String rmttc = mtc.DoubleToString(mttc);
            this.setTotalTTC(rmttc);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "consulte_devis";
    }

    public String traiterDevis(Devis d) {

        Notification notification = ejbNotification.getNotificationByDevis(d);
        HttpSession session1 = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        session1.setAttribute("myNotificationDevis", notification);
        return "traitement_notificationDevis";
    }

    public String imprimer(Devis d) throws IOException, DocumentException {

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        this.setDevisFromEdit(d); // display stack trace in the browser

        System.out.println("Devis :" + this.devisFromEdit.getNumeroDevis());
        /* List des detailles devis */
        this.setAllDetailleDevis(ejbDetailleDevis.listOfDetailleDevisByDevis(this.getDevisFromEdit()));
        MontantConverter mtc = new MontantConverter();
        Double mtht = 0.0;
        allFactureUtils.clear();
        List<UtilListItemData> listItems = new ArrayList<UtilListItemData>();

        for (Detailledevis df : this.allDetailleDevis) {
            FactureUtil fut = new FactureUtil();
            UtilListItemData itemData = new UtilListItemData();

            fut.setDesignation(df.getDesignation());
            fut.setQuantite(df.getQuantite());
            fut.setPu(df.getPu());
            fut.setRef(df.getReference());
            fut.setUnite(df.getUnite());
            String rctotal = "";
            if (df.getTypeitem().equals("Item")) {
                Double puc = mtc.StringToDouble(df.getPu());
                Double ctotal = puc * df.getQuantite();
                mtht = mtht + ctotal;
                rctotal = mtc.DoubleToString(ctotal);
                fut.setPrixtotal(rctotal);
            } else {
                if (!df.getPu().equals("0")) {
                    System.out.println("1 Je suis là pour le total!!!");
                    Double puc = mtc.StringToDouble(df.getPu());
                    Double ctotal = puc * df.getQuantite();
                    rctotal = mtc.DoubleToString(ctotal);
                    fut.setPrixtotal(rctotal);
                } else {
                    fut.setPrixtotal("0");
                }
            }
            allFactureUtils.add(fut);

            itemData.setDesignation(df.getDesignation());
            itemData.setRef(df.getReference());
            itemData.setUnite(df.getUnite());
            itemData.setTypedata(df.getTypeitem());

            String qte;
            if (df.getQuantite() == 0) {
                qte = "-";
            } else {
                qte = String.valueOf(df.getQuantite());
            }
            itemData.setQuantite(qte);

            if (df.getPu().equals("0")) {
                itemData.setPu("-");
            } else {
                itemData.setPu(df.getPu());
            }

            System.out.println("Valeur de total en cas erreur: " + rctotal);

            if (rctotal.equals("0") || rctotal.equals("") || rctotal.isEmpty()) {
                System.out.println("2 Je suis là pour le total!!!");
                itemData.setPrixtotal("-");
            } else {
                itemData.setPrixtotal(rctotal);
            }

            listItems.add(itemData);
        }
        /*Montant HT*/
        String rmtht = mtc.DoubleToString(mtht);
        this.setTotalHT(rmtht);
        /*Montant TVA*/
        Double mttva = mtht * 0.18;
        String rmtva = mtc.DoubleToString(mttva);
        this.setMontantTVA(rmtva);
        /*Montant TTC*/
        Double mttc = mtht + mttva;
        String rmttc = mtc.DoubleToString(mttc);
        this.setTotalTTC(rmttc);

        String mtlUtil = "";
        if (d.getMontantlettre() != null) {
            mtlUtil = d.getMontantlettre();
        }

        String termUtil = "";
        if (d.getTerme() != null) {
            termUtil = d.getTerme();
        }
        String nbJours = "";
        if (d.getNbjours() != null) {
            nbJours = d.getNbjours();
        }

        GenerationCodePdf acronim = new GenerationCodePdf();
        String acrovalue = "";
        if (d.getIdUtilisateur() != null) {
            acrovalue = acronim.genererInitiales(d.getIdUtilisateur().getNomUtilisateur(), d.getIdUtilisateur().getPrenomUtilisateur());
        }

        String nbjours = "";
        if (d.getNbjours() != null) {
            nbjours = d.getNbjours();
        }

        ReportModel reportModel = new ReportModel();
        reportModel.GenererModelDevis(d, listItems, totalHT, montantTVA, totalTTC, mtlUtil, termUtil, nbjours, acrovalue);
        return "devis";
    }

    public String modifier(Devis d) {
        try {

            this.setDevisFromEdit(d);

            this.setMyCoefIntermediaire(d.getCoef());
            this.setAncienCoef(this.devisFromEdit.getCoef());
            System.out.println("Devis :" + this.devisFromEdit.getNumeroDevis());
            /* List des detailles devis */
            this.setAllDetailleDevis(ejbDetailleDevis.listOfDetailleDevisByDevis(this.getDevisFromEdit()));
            MontantConverter mtc = new MontantConverter();
            Double mtht = 0.0;
            allFactureUtils.clear();
            allFactureUtilsSauvegarde.clear();

            for (Detailledevis df : this.allDetailleDevis) {
                FactureUtil fut = new FactureUtil();
                /*Recuperation du detaille au niveau de tonpon*/
                Tonpomcoef tpcoef = ejbTonpomcoef.getTonpomCreationByNumAndDesignation(this.devisFromEdit.getNumeroDevis(), df.getDesignation());
                if (tpcoef == null) {
                    fut.setPuSansCoef(df.getPu());
                } else {
                    fut.setPuSansCoef(tpcoef.getPu());
                }

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

            for (int k = 0; k < this.allFactureUtils.size(); k++) {
                Tonpomcoef tpc = new Tonpomcoef();
                tpc = ejbTonpomcoef.getTonpomModificationFromDelete(this.devisFromEdit.getNumeroDevis(), 1);
                if (tpc != null) {
                    ejbTonpomcoef.deleteExistingTinponByNumeDevis(this.devisFromEdit.getNumeroDevis(), 1);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "edit_devis";
    }

    public void renderInfo() {

        try {
            this.devisFromEdit.setIdClient(this.devisFromEdit.getIdClient());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void calculateTTC(FactureUtil f) {

        try {

            MontantConverter mtc = new MontantConverter();

            if ((!f.getDesignation().equals("")) && (!f.getPuSansCoef().equals(""))) {

                /*Verifier le nouveau coef methode 1*/
                for (FactureUtil df : this.allFactureUtils) {
                    if (df.getDesignation().equals(f.getDesignation())) {
                        /*Convert ancien coef et calcule prix*/
                        Double vanciencoef = Double.parseDouble(this.devisFromEdit.getCoef());
                        Double puconvert = mtc.StringToDouble(f.getPuSansCoef());
                        Double vwithcoef = puconvert * vanciencoef;
                        System.out.println("Montant avec coef: " + vwithcoef);
                        Double newMontantWithCoef = puconvert + vwithcoef;
                        String newv = mtc.DoubleToString(newMontantWithCoef);
                        f.setPu(newv);
                        /*Recuperation position element*/
                        int index = 0;
                        for (int x = 0; x < this.allFactureUtils.size(); x++) {
                            FactureUtil f1 = this.allFactureUtils.get(x);
                            if (f1.getDesignation().equalsIgnoreCase(f.getDesignation())) {
                                index = x;
                            }
                        }
                        /*Modification du prix*/
                        String newvForUpdate = mtc.DoubleToString(puconvert);
                        f.setPuSansCoef(newvForUpdate);
                        this.allFactureUtils.set(index, f);
                    }
                }

            }

            /*Mise à jour du TTC*/
            this.setMttc("");
            Double mthT = 0.0;
            for (FactureUtil dfttc : this.allFactureUtils) {
                if (dfttc.getPu().equals("")) {

                } else {
                    if (dfttc.getTypeitem().equals("Item")) {
                        Double puc = mtc.StringToDouble(dfttc.getPu());
                        Double calcule1 = puc * dfttc.getQuantite();
                        mthT = mthT + calcule1;
                    }

                }

            }

            System.out.println("Montant total: " + mthT);

            /*TVA*/
            Double mthTTVA = mthT * 0.18;
            Double mtTTC = mthT + mthTTVA;
            String rctotal = mtc.DoubleToString(mtTTC);
            this.setMttc(rctotal);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void calculateCoef() {

        try {

            MontantConverter mtc = new MontantConverter();
            if (this.myCoef.equals("")) {
                this.setMyCoef(this.devisFromEdit.getCoef());
            } else {
                this.setMyCoefIntermediaire(this.myCoef);
                System.out.println("Le coef n'est pas vide !");
            }

            if (this.firstPassage == 0) {

                for (int k = 0; k < this.allFactureUtils.size(); k++) {
                    Tonpomcoef tpc = new Tonpomcoef();
                    tpc = ejbTonpomcoef.getTonpomModificationFromDelete(this.devisFromEdit.getNumeroDevis(), 1);
                    if (tpc != null) {
                        ejbTonpomcoef.deleteExistingTinponByNumeDevis(this.devisFromEdit.getNumeroDevis(), 1);
                    }
                }

                for (int j = 0; j < this.allFactureUtils.size(); j++) {
                    Tonpomcoef tpc = new Tonpomcoef();
                    FactureUtil f1P = new FactureUtil();
                    int index = 0;
                    f1P = this.allFactureUtils.get(j);
                    index = j;

                    tpc.setDesignation(f1P.getDesignation());
                    tpc.setTypeitem(f1P.getTypeitem());
                    tpc.setRef(f1P.getRef());
                    tpc.setUnite(f1P.getUnite());
                    tpc.setQuantite(f1P.getQuantite());
                    tpc.setPu(f1P.getPuSansCoef());
                    tpc.setNumerodevis(this.devisFromEdit.getNumeroDevis());
                    int ordrepassage = this.firstPassage + 1;
                    tpc.setPassage(ordrepassage);
                    tpc.setTypepassage("Modification");

                    boolean rst = ejbTonpomcoef.verifierTonpom2(this.devisFromEdit.getNumeroDevis(), this.firstPassage);
                    if ((rst == true) && (j == 0)) {
                        ejbTonpomcoef.deleteByNumeDevisModif(this.devisFromEdit.getNumeroDevis());
                        ejbTonpomcoef.insertTonpom(tpc);
                    }

                    if ((rst == true) && (j != 0)) {
                        ejbTonpomcoef.insertTonpom(tpc);
                    }

                    if (rst != true) {
                        ejbTonpomcoef.insertTonpom(tpc);
                    }

                }
                this.methodeCoef1(this.allFactureUtils);
            } else {

                this.methodeCoef2(this.allFactureUtils);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void methodeCoef1(List<FactureUtil> listD) {
        System.out.println("Valeur coef1: " + this.firstPassage);

        MontantConverter mtc = new MontantConverter();
        int index = 0;

        for (int x = 0; x < this.allFactureUtils.size(); x++) {
            FactureUtil f1 = new FactureUtil();
            f1 = this.allFactureUtils.get(x);
            if (f1.getDesignation().equals("")) {

            } else {
                if (f1.getTypeitem().equals("Item")) {
                    index = x;
                    Double opu = mtc.StringToDouble(f1.getPuSansCoef());
                    Double vcoef = Double.parseDouble(this.getMyCoef());
                    Double newPu = opu * vcoef;
                    Double vvaleur = opu + newPu;
                    String newv = mtc.DoubleToString(vvaleur);
                    f1.setPu(newv);
                    this.allFactureUtils.set(index, f1);
                }
            }

        }

        /*Mise à jour du TTC*/
        this.setMttc("");
        Double mthT = 0.0;
        for (FactureUtil dfttc : this.allFactureUtils) {
            if (dfttc.getPu().equals("")) {

            } else {
                if (dfttc.getTypeitem().equals("Item")) {
                    Double puc = mtc.StringToDouble(dfttc.getPu());
                    Double calcule1 = puc * dfttc.getQuantite();
                    mthT = mthT + calcule1;
                }
            }

        }

        System.out.println("Montant total: " + mthT);

        /*TVA*/
        Double mthTTVA = mthT * 0.18;
        Double mtTTC = mthT + mthTTVA;
        String rctotal = mtc.DoubleToString(mtTTC);
        this.setMttc(rctotal);
        int ordp = this.firstPassage + 1;
        this.setFirstPassage(ordp);

    }

    public void methodeCoef2(List<FactureUtil> listD) {
        System.out.println("Valeur coef2: " + this.firstPassage);

        MontantConverter mtc = new MontantConverter();
        int index = 0;
        for (int x = 0; x < this.allFactureUtils.size(); x++) {
            FactureUtil f1 = new FactureUtil();
            f1 = this.allFactureUtils.get(x);
            index = x;
            if (f1.getDesignation().equals("")) {

            } else {
                if (f1.getTypeitem().equals("Item")) {
                    Double opu = mtc.StringToDouble(f1.getPuSansCoef());
                    Double vcoef = Double.parseDouble(this.getMyCoef());
                    Double newPu = opu * vcoef;
                    Double vraivaleur = opu + newPu;
                    String newv = mtc.DoubleToString(vraivaleur);
                    f1.setPu(newv);
                    this.allFactureUtils.set(index, f1);
                }
            }
        }

        /*Mise à jour du TTC*/
        this.setMttc("");
        Double mthT = 0.0;
        for (FactureUtil dfttc : this.allFactureUtils) {
            if (dfttc.getPu().equals("")) {

            } else {
                if (dfttc.getTypeitem().equals("Item")) {
                    Double puc = mtc.StringToDouble(dfttc.getPu());
                    Double calcule1 = puc * dfttc.getQuantite();
                    mthT = mthT + calcule1;
                }
            }

        }

        System.out.println("Montant total: " + mthT);

        /*TVA*/
        Double mthTTVA = mthT * 0.18;
        Double mtTTC = mthT + mthTTVA;
        String rctotal = mtc.DoubleToString(mtTTC);
        this.setMttc(rctotal);
        int ordp = this.firstPassage + 1;
        this.setFirstPassage(ordp);
    }

    public void addNewInputLigne(int index) {
        FactureUtil fu = new FactureUtil();
        fu.setIdDetailleCommande(0);
        fu.setDesignation("");
        fu.setTypeitem("");
        fu.setRef("");
        fu.setUnite(" ");
        fu.setPu("");
        fu.setQuantite(0);
        int nindex = index + 1;
        allFactureUtils.add(nindex, fu);
    }

    public void removeInputLigne(int givenIndex) {
        for (int x = 0; x < this.allFactureUtils.size(); x++) {
            if (givenIndex == x) {
                this.allFactureUtils.remove(x);
            }
        }

        try {

            MontantConverter mtc = new MontantConverter();

            /*Verifier le nouveau coef methode 1*/
            for (FactureUtil df : this.allFactureUtils) {

                /*Convert ancien coef et calcule prix*/
                Double vanciencoef = Double.parseDouble(this.devisFromEdit.getCoef());
                Double puconvert = mtc.StringToDouble(df.getPuSansCoef());
                Double vwithcoef = puconvert * vanciencoef;
                System.out.println("Montant avec coef: " + vwithcoef);
                Double newMontantWithCoef = puconvert + vwithcoef;
                String newv = mtc.DoubleToString(newMontantWithCoef);
                df.setPu(newv);
                /*Recuperation position element*/
                int index = 0;
                for (int x = 0; x < this.allFactureUtils.size(); x++) {
                    FactureUtil f1 = this.allFactureUtils.get(x);
                    if (f1.getDesignation().equalsIgnoreCase(df.getDesignation())) {
                        index = x;
                    }
                }
                /*Modification du prix*/
                String newvForUpdate = mtc.DoubleToString(puconvert);
                df.setPuSansCoef(newvForUpdate);
                this.allFactureUtils.set(index, df);

            }

            /*Mise à jour du TTC*/
            this.setMttc("");
            Double mthT = 0.0;
            for (FactureUtil dfttc : this.allFactureUtils) {
                if (dfttc.getPu().equals("")) {

                } else {
                    if (dfttc.getTypeitem().equals("Item")) {
                        Double puc = mtc.StringToDouble(dfttc.getPu());
                        Double calcule1 = puc * dfttc.getQuantite();
                        mthT = mthT + calcule1;
                    }

                }

            }

            System.out.println("Montant total: " + mthT);

            /*TVA*/
            Double mthTTVA = mthT * 0.18;
            Double mtTTC = mthT + mthTTVA;
            String rctotal = mtc.DoubleToString(mtTTC);
            this.setMttc(rctotal);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String save() {

        try {

            this.devisFromEdit.setEtat("En attente de validation");
            //this.devisFromEdit.setEtat("Validé");
            this.devisFromEdit.setCoef(this.getMyCoef());
            //this.devisFromEdit.setIdUtilisateur(this.userNotif);
            ejbDevis.edit(this.devisFromEdit);

            for (FactureUtil f : allFactureUtils) {
                Detailledevis dtdv = new Detailledevis();
                Tonpomcoef tpc = new Tonpomcoef();
                if (f.getIdDetailleCommande() == 0) {
                    //dtdv = ejbDetailleDevis.find(f.getIdDetailleCommande());

                    if (!f.getDesignation().equals("")) {
                        dtdv.setDesignation(f.getDesignation());
                        dtdv.setTypeitem(f.getTypeitem());
                        dtdv.setReference(f.getRef());
                        dtdv.setUnite(f.getUnite());
                        dtdv.setQuantite(f.getQuantite());
                        dtdv.setPu(f.getPu());
                        dtdv.setIdDevis(this.devisFromEdit);
                        ejbDetailleDevis.insertDetailleDevis(dtdv);

                        tpc.setNumerodevis(this.devisFromEdit.getNumeroDevis());
                        tpc.setRef("");
                        tpc.setDesignation(f.getDesignation());
                        tpc.setTypeitem(f.getTypeitem());
                        tpc.setUnite(f.getUnite());
                        tpc.setQuantite(f.getQuantite());
                        tpc.setPu(f.getPuSansCoef());
                        tpc.setPassage(1);
                        tpc.setTypepassage("Creation");
                        ejbTonpomcoef.insertTonpom(tpc);
                    }

                } else {

                    if (!f.getDesignation().equals("")) {
                        UtilUtfconvert utfconvert = new UtilUtfconvert();
                        dtdv = ejbDetailleDevis.find(f.getIdDetailleCommande());
                        dtdv.setDesignation(f.getDesignation());
                        dtdv.setTypeitem(f.getTypeitem());
                        dtdv.setReference(f.getRef());
                        dtdv.setUnite(f.getUnite());
                        dtdv.setQuantite(f.getQuantite());
                        dtdv.setPu(f.getPu());
                        dtdv.setIdDevis(this.devisFromEdit);
                        ejbDetailleDevis.edit(dtdv);
                    }

                }

            }

            return "devis";
        } catch (Exception e) {
            return "edit_devis";
        }
    }

}
