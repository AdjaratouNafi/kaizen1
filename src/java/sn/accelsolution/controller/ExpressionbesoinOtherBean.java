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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import sn.accelsolution.dao.CommandeFacade;
import sn.accelsolution.dao.DetailleCommandeFacade;
import sn.accelsolution.dao.DetailledevisFacade;
import sn.accelsolution.dao.DetailleexpressionbesoinFacade;
import sn.accelsolution.dao.DevisFacade;
import sn.accelsolution.dao.ExpressionbesoinFacade;
import sn.accelsolution.dao.UtilisateurFacade;
import sn.accelsolution.entities.Commande;
import sn.accelsolution.entities.DetailleCommande;
import sn.accelsolution.entities.Detailledevis;
import sn.accelsolution.entities.Detailleexpressionbesoin;
import sn.accelsolution.entities.Devis;
import sn.accelsolution.entities.Expressionbesoin;
import sn.accelsolution.util.FactureUtil;
import sn.accelsolution.util.MontantConverter;
import sn.accelsolution.util.ReportAccelModel;
import sn.accelsolution.util.ReportFacture;
import sn.accelsolution.util.UtilListItemData;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DELL
 */
@ManagedBean
@RequestScoped
public class ExpressionbesoinOtherBean implements Serializable {

    /**
     * Creates a new instance of ContratRecrutBean
     */
    @EJB
    UtilisateurFacade ejbUser;
    @EJB
    ExpressionbesoinFacade ejbExpressionbesoin;
    @EJB
    DetailleexpressionbesoinFacade ejbDetailleExpressionbesoin;
    @EJB
    CommandeFacade ejbCommande;
    @EJB
    DetailleCommandeFacade ejbDetailleCommande;

    private Expressionbesoin expressionbesoinFromEdit;
    private Detailleexpressionbesoin detailleExpressionbesoinFromEdit;
    private List<Detailleexpressionbesoin> allDetailleExpressionbesoins;
    private List<FactureUtil> allFactureUtils;
    private String totalHT;
    private String montantTVA;
    private String totalTTC;

    @PostConstruct
    public void init() {
        /*FactureUtil fu = new FactureUtil();
         fu.setDesignation("");
         fu.setPu("");
         fu.setQuantite(0);
         listDevis.add(fu);*/
    }

    public ExpressionbesoinOtherBean() {
        allDetailleExpressionbesoins = new ArrayList<>();
        expressionbesoinFromEdit = new Expressionbesoin();
        detailleExpressionbesoinFromEdit = new Detailleexpressionbesoin();
        allFactureUtils = new ArrayList<>();
    }

    public Expressionbesoin getExpressionbesoinFromEdit() {
        return expressionbesoinFromEdit;
    }

    public void setExpressionbesoinFromEdit(Expressionbesoin expressionbesoinFromEdit) {
        this.expressionbesoinFromEdit = expressionbesoinFromEdit;
    }

    public Detailleexpressionbesoin getDetailleExpressionbesoinFromEdit() {
        return detailleExpressionbesoinFromEdit;
    }

    public void setDetailleExpressionbesoinFromEdit(Detailleexpressionbesoin detailleExpressionbesoinFromEdit) {
        this.detailleExpressionbesoinFromEdit = detailleExpressionbesoinFromEdit;
    }

    public List<Detailleexpressionbesoin> getAllDetailleExpressionbesoins() {
        return allDetailleExpressionbesoins;
    }

    public void setAllDetailleExpressionbesoins(List<Detailleexpressionbesoin> allDetailleExpressionbesoins) {
        this.allDetailleExpressionbesoins = allDetailleExpressionbesoins;
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

    public String detail(Expressionbesoin exb) {
        try {

            this.setExpressionbesoinFromEdit(exb);
            //System.out.println("Devis :" + this.devisFromEdit.getNumeroDevis());
            /* List des detailles devis */
            this.setAllDetailleExpressionbesoins(ejbDetailleExpressionbesoin.listOfDetailleExpressionbesoinByExpression(this.getExpressionbesoinFromEdit()));
            MontantConverter mtc = new MontantConverter();
            Double mtht = 0.0;

            //UtilUtfconvert utfconvert = new UtilUtfconvert();

            for (Detailleexpressionbesoin df : this.allDetailleExpressionbesoins) {
                FactureUtil fut = new FactureUtil();
                fut.setDesignation(df.getDesignation());
                fut.setQuantite(df.getQuantite());
                fut.setPu(df.getPu());
                fut.setRef(df.getReference());
                fut.setUnite(df.getUnite());
                Double puc = mtc.StringToDouble(df.getPu());
                Double ctotal = puc * df.getQuantite();
                mtht = mtht + ctotal;
                String rctotal = mtc.DoubleToString(ctotal);
                fut.setPrixtotal(rctotal);
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
        return "consulte_expression";
    }

    public String imprimer(Expressionbesoin exb) throws IOException {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {

            this.setExpressionbesoinFromEdit(exb);
            //System.out.println("Devis :" + this.devisFromEdit.getNumeroDevis());
            /* List des detailles devis */
            this.setAllDetailleExpressionbesoins(ejbDetailleExpressionbesoin.listOfDetailleExpressionbesoinByExpression(this.getExpressionbesoinFromEdit()));
            MontantConverter mtc = new MontantConverter();
            Double mtht = 0.0;

            List<UtilListItemData> listItems = new ArrayList<UtilListItemData>();

            //UtilUtfconvert utfconvert = new UtilUtfconvert();

            for (Detailleexpressionbesoin df : this.allDetailleExpressionbesoins) {
                FactureUtil fut = new FactureUtil();
                UtilListItemData itemData = new UtilListItemData();
                fut.setDesignation(df.getDesignation());
                fut.setQuantite(df.getQuantite());
                fut.setPu(df.getPu());
                fut.setRef(df.getReference());
                fut.setUnite(df.getUnite());
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
                itemData.setPu(df.getPu());
                itemData.setPrixtotal(rctotal);
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

//            // Imprimre pour ESTPROD
//            ReportFacture rfacture = new ReportFacture();
//            rfacture.GenererExpression(this.allFactureUtils, exb, totalHT, montantTVA, totalTTC);
            // Imprimre pour ACCEL
//            ReportAccelModel rfacture = new ReportAccelModel();
//            rfacture.GenererExpression(this.allFactureUtils, exb, totalHT, montantTVA, totalTTC);

            /*Methode2*/
            ServletOutputStream servletOutputStream = response.getOutputStream();
            String sourceFileName = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/rapport/expression.jasper");
            File reportFile = new File(sourceFileName);
            byte[] bytes = null;

            JRBeanCollectionDataSource itemJRBean = new JRBeanCollectionDataSource(listItems);
            Map<String, Object> parameters = new HashMap<String, Object>();

            parameters.put("logoReport", FacesContext.getCurrentInstance().getExternalContext().getRealPath("/rapport"));
            parameters.put("nomClient", "");
            parameters.put("adresseClient", "");
            parameters.put("telClient", "");
            parameters.put("mailClient", "");
            parameters.put("dateDevis", exb.getDateExpression());
            parameters.put("numeroDevis", exb.getNumeroExpression());
            parameters.put("reference", "");
            parameters.put("demandeur", exb.getIdUtilisateur().getNomUtilisateur() + " " + exb.getIdUtilisateur().getPrenomUtilisateur());
            parameters.put("chantier", exb.getIdChantier().getSiteChantier());
            parameters.put("montanttva", "");
            parameters.put("montantttc", "");
            parameters.put("montantlettre", " ");
            parameters.put("ItemDataSource", itemJRBean);

            bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameters, new JREmptyDataSource());

            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);

            servletOutputStream.write(bytes, 0, bytes.length);
            servletOutputStream.flush();
            servletOutputStream.close();
            /* End Methode2*/

        } catch (Exception e) {
            // display stack trace in the browser
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            response.setContentType("text/plain");
            response.getOutputStream().print(stringWriter.toString());
        }
        return "expressionbesoins";
    }

    public void saveUpdateNivo2(Expressionbesoin Expb) {
        try {
            Expressionbesoin expb = ejbExpressionbesoin.find(Expb.getIdExpression());
            expb.setNivovalidation("nivo2");
            ejbExpressionbesoin.edit(expb);
        } catch (Exception e) {
        }
    }

    public void saveUpdateNivo3(Expressionbesoin Expb) {
        try {
            Expressionbesoin expb = ejbExpressionbesoin.find(Expb.getIdExpression());
            List<Detailleexpressionbesoin> listDesDetaillesExpressionsBesoins = ejbDetailleExpressionbesoin.listOfDetailleExpressionbesoinByExpression(expb);
            expb.setNivovalidation("nivo3");
            expb.setEtat("Validée");
            ejbExpressionbesoin.edit(expb);

            Commande cmd = new Commande();
            int maxCmd = ejbCommande.maxCommande();
            String numCmd = "ESTCMD" + maxCmd;
            cmd.setCode(numCmd);
            String datecmd = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
            cmd.setDateechance(datecmd);
            cmd.setIdMarche(expb.getIdChantier().getIdMarche());
            cmd.setIdChantier(expb.getIdChantier());
            cmd.setIdFournisseur(null);
            cmd.setEtat("En attente");
            cmd.setTypecommande("Expression besoins");

            ejbCommande.insertCommandeExp(cmd);

            int idCmd = ejbCommande.maxCommande();
            Commande cmdFd = ejbCommande.find(idCmd);
            DetailleCommande cmddetaille = new DetailleCommande();

            for (Detailleexpressionbesoin f : listDesDetaillesExpressionsBesoins) {
                cmddetaille.setDesignation(f.getDesignation());
                cmddetaille.setReference(f.getReference());
                cmddetaille.setUnite(f.getUnite());
                cmddetaille.setQuantite(f.getQuantite());
                cmddetaille.setPu(f.getPu());
                cmddetaille.setIdCommande(cmdFd);
                ejbDetailleCommande.insertDetailleCommandePrim(cmddetaille);
            }

        } catch (Exception e) {
        }
    }

}
