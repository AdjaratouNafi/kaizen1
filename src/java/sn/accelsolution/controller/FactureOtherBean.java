/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import sn.accelsolution.dao.DetailledevisFacade;
import sn.accelsolution.dao.DetaillenewfactureFacade;
import sn.accelsolution.dao.DevisFacade;
import sn.accelsolution.dao.NewfactureFacade;
import sn.accelsolution.dao.NotificationFacade;
import sn.accelsolution.dao.UtilisateurFacade;
import sn.accelsolution.entities.Detailledevis;
import sn.accelsolution.entities.Detaillenewfacture;
import sn.accelsolution.entities.Devis;
import sn.accelsolution.entities.Newfacture;
import sn.accelsolution.entities.Notification;
import sn.accelsolution.util.FactureUtil;
import sn.accelsolution.util.GenerationCodePdf;
import sn.accelsolution.util.MontantConverter;
import sn.accelsolution.util.ReportAccelModel;
import sn.accelsolution.util.ReportFacture;
import sn.accelsolution.util.ReportModel;
import sn.accelsolution.util.UtilListItemChantierData;
import sn.accelsolution.util.UtilListItemData;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DELL
 */
@ManagedBean
@RequestScoped
public class FactureOtherBean implements Serializable {

    /**
     * Creates a new instance of ContratRecrutBean
     */
    @EJB
    UtilisateurFacade ejbUser;
    @EJB
    NewfactureFacade ejbNewfacture;
    @EJB
    DetaillenewfactureFacade ejbDetailleNewfacture;
    @EJB
    NotificationFacade ejbNotification;

    private Newfacture newfactureFromEdit;
    private Detaillenewfacture detailleNewfactureFromEdit;
    private List<Detaillenewfacture> allDetailleNewfactures;
    private List<FactureUtil> allFactureUtils;
    private String totalHT;
    private String montantTVA;
    private String totalTTC;
    private String emtyvalues;

    @PostConstruct
    public void init() {
        /*FactureUtil fu = new FactureUtil();
         fu.setDesignation("");
         fu.setPu("");
         fu.setQuantite(0);
         listDevis.add(fu);*/
    }

    public FactureOtherBean() {
        allDetailleNewfactures = new ArrayList<>();
        newfactureFromEdit = new Newfacture();
        detailleNewfactureFromEdit = new Detaillenewfacture();
        allFactureUtils = new ArrayList<>();
        this.emtyvalues = "-";
    }

    public String getEmtyvalues() {
        return emtyvalues;
    }

    public void setEmtyvalues(String emtyvalues) {
        this.emtyvalues = emtyvalues;
    }

    public Newfacture getNewfactureFromEdit() {
        return newfactureFromEdit;
    }

    public void setNewfactureFromEdit(Newfacture newfactureFromEdit) {
        this.newfactureFromEdit = newfactureFromEdit;
    }

    public Detaillenewfacture getDetailleNewfactureFromEdit() {
        return detailleNewfactureFromEdit;
    }

    public void setDetailleNewfactureFromEdit(Detaillenewfacture detailleNewfactureFromEdit) {
        this.detailleNewfactureFromEdit = detailleNewfactureFromEdit;
    }

    public List<Detaillenewfacture> getAllDetailleNewfactures() {
        return allDetailleNewfactures;
    }

    public void setAllDetailleNewfactures(List<Detaillenewfacture> allDetailleNewfactures) {
        this.allDetailleNewfactures = allDetailleNewfactures;
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

    public String detail(Newfacture f) {
        try {

            this.setNewfactureFromEdit(f);
            /* List des detailles devis */
            this.setAllDetailleNewfactures(ejbDetailleNewfacture.listOfDetailleFactureByfacture(this.getNewfactureFromEdit()));
            MontantConverter mtc = new MontantConverter();
            Double mtht = 0.0;
            //UtilUtfconvert utfconvert = new UtilUtfconvert();
            for (Detaillenewfacture df : this.allDetailleNewfactures) {
                FactureUtil fut = new FactureUtil();
                //String designutil = utfconvert.convertFromUTF8(df.getDesignation());
                fut.setDesignation(df.getDesignation());
                fut.setTypeitem(df.getTypeitem());
                fut.setRef(df.getReference());
                fut.setUnite(df.getUnite());
                fut.setQuantite(df.getQuantite());
                fut.setPu(df.getPu());
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

                /*Double puc = mtc.StringToDouble(df.getPu());
                Double ctotal = puc * df.getQuantite();
                mtht = mtht + ctotal;
                String rctotal = mtc.DoubleToString(ctotal);
                fut.setPrixtotal(rctotal);*/
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
        return "consulte_facture2";
    }

    public String imprimer(Newfacture f) throws IOException {

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        try {

            this.setNewfactureFromEdit(f);
            /* List des detailles devis */
            this.setAllDetailleNewfactures(ejbDetailleNewfacture.listOfDetailleFactureByfacture(this.getNewfactureFromEdit()));
            MontantConverter mtc = new MontantConverter();
            Double mtht = 0.0;
            List<UtilListItemData> listItems = new ArrayList<UtilListItemData>();
            //UtilUtfconvert utfconvert = new UtilUtfconvert();

            for (Detaillenewfacture df : this.allDetailleNewfactures) {
                FactureUtil fut = new FactureUtil();
                UtilListItemData itemData = new UtilListItemData();

                //String designutil = utfconvert.convertFromUTF8(df.getDesignation());
                fut.setDesignation(df.getDesignation());
                fut.setRef(df.getReference());
                fut.setUnite(df.getUnite());
                fut.setQuantite(df.getQuantite());
                fut.setPu(df.getPu());
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
                //String qte = String.valueOf(df.getQuantite());
                //itemData.setQuantite(qte);
                itemData.setPu(df.getPu());
                //itemData.setPrixtotal(rctotal);
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
            if (f.getMintantlettreNewfacture() != null) {
                mtlUtil = f.getMintantlettreNewfacture();
            }

            ReportModel  ReportModel = new ReportModel();
            ReportModel.GenererModelFacture(f, listItems, totalHT, montantTVA, totalTTC, mtlUtil, mtlUtil);

        } catch (Exception e) {
            
        }
        return "facture2";
    }

    public String imprimerr(Newfacture f) throws IOException {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {

            this.setNewfactureFromEdit(f);
            /* List des detailles devis */
            this.setAllDetailleNewfactures(ejbDetailleNewfacture.listOfDetailleFactureByfacture(this.getNewfactureFromEdit()));
            MontantConverter mtc = new MontantConverter();
            Double mtht = 0.0;
            List<UtilListItemChantierData> listItems = new ArrayList<UtilListItemChantierData>();
            //UtilUtfconvert utfconvert = new UtilUtfconvert();

            for (Detaillenewfacture df : this.allDetailleNewfactures) {
                FactureUtil fut = new FactureUtil();
                UtilListItemChantierData itemData = new UtilListItemChantierData();

                //String designutil = utfconvert.convertFromUTF8(df.getDesignation());
                fut.setDesignation(df.getDesignation());
                fut.setRef(df.getReference());
                fut.setUnite(df.getUnite());
                fut.setQuantite(df.getQuantite());
                fut.setPu(df.getPu());
                Double puc = mtc.StringToDouble(df.getPu());
                Double ctotal = puc * df.getQuantite();
                mtht = mtht + ctotal;
                String rctotal = mtc.DoubleToString(ctotal);
                fut.setPrixtotal(rctotal);
                allFactureUtils.add(fut);

                itemData.setDesignation(df.getDesignation());
                itemData.setRef(df.getReference());
                itemData.setUnite(df.getUnite());
                String qte = String.valueOf(df.getQuantite());
                itemData.setQuantite(qte);
                itemData.setQtLivree(" ");
                itemData.setExecution(" ");
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

            
            ReportModel  ReportModel = new ReportModel();
            ReportModel.GenererModelFactureChef(f, listItems);

        } catch (Exception e) {
            
        }
        return "facture2";
    }

    public String traiterFacture(Newfacture newfactureT) {

        Notification notification = ejbNotification.getNotificationByfacture(newfactureT);
        HttpSession session1 = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        session1.setAttribute("myNotificationFacture", notification);
        return "traitement_notificationFacture";
    }

}
