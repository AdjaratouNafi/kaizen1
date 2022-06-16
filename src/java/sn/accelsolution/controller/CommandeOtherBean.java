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
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import sn.accelsolution.dao.CommandeFacade;
import sn.accelsolution.dao.DetailleCommandeFacade;
import sn.accelsolution.dao.UtilisateurFacade;
import sn.accelsolution.entities.Commande;
import sn.accelsolution.entities.DetailleCommande;
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
public class CommandeOtherBean implements Serializable {

    /**
     * Creates a new instance of ContratRecrutBean
     */
    @EJB
    UtilisateurFacade ejbUser;
    @EJB
    CommandeFacade ejbCommande;
    @EJB
    DetailleCommandeFacade ejbDetailleCommande;

    private Commande commandeFromEdit;
    private DetailleCommande detailleCommandeFromEdit;
    private List<DetailleCommande> allDetailleCommande;
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

    public CommandeOtherBean() {
        allDetailleCommande = new ArrayList<>();
        commandeFromEdit = new Commande();
        detailleCommandeFromEdit = new DetailleCommande();
        allFactureUtils = new ArrayList<>();
    }

    public Commande getCommandeFromEdit() {
        return commandeFromEdit;
    }

    public void setCommandeFromEdit(Commande commandeFromEdit) {
        this.commandeFromEdit = commandeFromEdit;
    }

    public DetailleCommande getDetailleCommandeFromEdit() {
        return detailleCommandeFromEdit;
    }

    public void setDetailleCommandeFromEdit(DetailleCommande detailleCommandeFromEdit) {
        this.detailleCommandeFromEdit = detailleCommandeFromEdit;
    }

    public List<DetailleCommande> getAllDetailleCommande() {
        return allDetailleCommande;
    }

    public void setAllDetailleCommande(List<DetailleCommande> allDetailleCommande) {
        this.allDetailleCommande = allDetailleCommande;
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

    public String detail(Commande c) {
        try {

            this.setCommandeFromEdit(c);
//            System.out.println("Devis :" + this.devisFromEdit.getNumeroDevis());
            /* List des detailles devis */
            this.setAllDetailleCommande(ejbDetailleCommande.listOfDetailleCommandeByCommande(this.getCommandeFromEdit()));
            MontantConverter mtc = new MontantConverter();
            Double mtht = 0.0;
            //UtilUtfconvert utfconvert = new UtilUtfconvert();

            for (DetailleCommande df : this.allDetailleCommande) {
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
        return "consulte_commande2";
    }

    public String imprimer(Commande c) throws IOException {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {

            this.setCommandeFromEdit(c);
            //System.out.println("Devis :" + this.devisFromEdit.getNumeroDevis());
            /* List des detailles devis */
            this.setAllDetailleCommande(ejbDetailleCommande.listOfDetailleCommandeByCommande(this.getCommandeFromEdit()));
            MontantConverter mtc = new MontantConverter();
            Double mtht = 0.0;

            List<UtilListItemData> listItems = new ArrayList<UtilListItemData>();
            //UtilUtfconvert utfconvert = new UtilUtfconvert();

            for (DetailleCommande df : this.allDetailleCommande) {
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

            // Imprimer pour ESTPROD
//            ReportFacture rfacture = new ReportFacture();
//            rfacture.GenererBonCommande(this.allFactureUtils, c, totalHT, montantTVA, totalTTC);
            // Imprimer pour Accel
//            ReportAccelModel rfacture = new ReportAccelModel();
//            rfacture.GenererBonCommande(this.allFactureUtils, c, totalHT, montantTVA, totalTTC);
           /*Methode2*/
            ServletOutputStream servletOutputStream = response.getOutputStream();
            String sourceFileName = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/rapport/commande.jasper");
            File reportFile = new File(sourceFileName);
            byte[] bytes = null;

            JRBeanCollectionDataSource itemJRBean = new JRBeanCollectionDataSource(listItems);
            Map<String, Object> parameters = new HashMap<String, Object>();

            parameters.put("logoReport", FacesContext.getCurrentInstance().getExternalContext().getRealPath("/rapport"));
            parameters.put("nomClient", c.getIdFournisseur().getNomFournisseur());
            String adr = " ";
            if(c.getIdFournisseur().getAdresseFournisseur() != null){
                adr = c.getIdFournisseur().getAdresseFournisseur();
            }
            parameters.put("adresseClient", adr);
            parameters.put("telClient", c.getIdFournisseur().getTelephoneFournisseur());
            parameters.put("mailClient", c.getIdFournisseur().getMailFournisseur());
            parameters.put("dateDevis", c.getDateechance());
            parameters.put("numeroDevis", c.getCode());
            parameters.put("reference", "");
            parameters.put("devise", " ");
            parameters.put("montantht", totalHT);
            parameters.put("montanttva", montantTVA);
            parameters.put("montantttc", totalTTC);
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
        return "commandes2";
    }

}
