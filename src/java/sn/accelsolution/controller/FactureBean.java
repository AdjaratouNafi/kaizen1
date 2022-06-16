/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.FlowEvent;
import sn.accelsolution.dao.CommandeFacade;
import sn.accelsolution.dao.ContratFacade;
import sn.accelsolution.dao.DetailleCommandeFacade;
import sn.accelsolution.dao.PretFacade;
import sn.accelsolution.dao.RembourssementFacade;
import sn.accelsolution.dao.RessourceFacade;
import sn.accelsolution.dao.SalaireFacade;
import sn.accelsolution.dao.UtilisateurFacade;
import sn.accelsolution.entities.Commande;
import sn.accelsolution.entities.Contrat;
import sn.accelsolution.entities.DetailleCommande;
import sn.accelsolution.entities.Pret;
import sn.accelsolution.entities.Rembourssement;
import sn.accelsolution.entities.Salaire;
import sn.accelsolution.entities.Utilisateur;
import sn.accelsolution.util.Bulletin;
import sn.accelsolution.util.NumberManager;
import sn.accelsolution.util.ReportBonCommande;
import sn.accelsolution.util.ReportContrat;
import sn.accelsolution.util.ReportSalaire;

/**
 *
 * @author DELL
 */
@ManagedBean
@RequestScoped
public class FactureBean implements Serializable {

    /**
     * Creates a new instance of ContratRecrutBean
     */
    @EJB
    UtilisateurFacade ejbUser;
    @EJB
    CommandeFacade ejbCommande;
    @EJB
    DetailleCommandeFacade ejbDetailleCommande;
    @EJB
    RessourceFacade ejbRessource;

    private Commande commande;
    private Commande commandeFromEdit;
    private DetailleCommande detailleCommande;
    private DetailleCommande detailleCommandeFromEdit;

    private DataModel itemCommandes;
    private List listCommandes;
    private DataModel itemDetailleCommandes;
    private List listDetaillesCommandes;
    private Date dateEcheance;
    private String puht;
    private String tva;
    private String pht;
    private String ttc;
    private String taxe;

    @PostConstruct
    public void init() {
        this.setTva("18%");
    }

    public FactureBean() {

        commande = new Commande();
        commandeFromEdit = new Commande();
        detailleCommande = new DetailleCommande();
        detailleCommandeFromEdit = new DetailleCommande();
    }

    public String getTaxe() {
        return taxe;
    }

    public void setTaxe(String taxe) {
        this.taxe = taxe;
    }

    public String getPuht() {
        return puht;
    }

    public void setPuht(String puht) {
        this.puht = puht;
    }

    public String getTva() {
        return tva;
    }

    public void setTva(String tva) {
        this.tva = tva;
    }

    public String getPht() {
        return pht;
    }

    public void setPht(String pht) {
        this.pht = pht;
    }

    public String getTtc() {
        return ttc;
    }

    public void setTtc(String ttc) {
        this.ttc = ttc;
    }

    public Date getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(Date dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Commande getCommandeFromEdit() {
        return commandeFromEdit;
    }

    public void setCommandeFromEdit(Commande commandeFromEdit) {
        this.commandeFromEdit = commandeFromEdit;
    }

    public DetailleCommande getDetailleCommande() {
        return detailleCommande;
    }

    public void setDetailleCommande(DetailleCommande detailleCommande) {
        this.detailleCommande = detailleCommande;
    }

    public DetailleCommande getDetailleCommandeFromEdit() {
        return detailleCommandeFromEdit;
    }

    public void setDetailleCommandeFromEdit(DetailleCommande detailleCommandeFromEdit) {
        this.detailleCommandeFromEdit = detailleCommandeFromEdit;
    }

    public DataModel getItemCommandes() {
        itemCommandes = new ListDataModel();
        this.setListCommandes(ejbCommande.findAll());
        itemCommandes.setWrappedData(this.getListCommandes());
        return itemCommandes;
    }

    public void setItemCommandes(DataModel itemCommandes) {
        this.itemCommandes = itemCommandes;
    }

    public List getListCommandes() {
        listCommandes = ejbCommande.findAll();
        return listCommandes;
    }

    public void setListCommandes(List listCommandes) {
        this.listCommandes = listCommandes;
    }

    public DataModel getItemDetailleCommandes() {
        return itemDetailleCommandes;
    }

    public void setItemDetailleCommandes(DataModel itemDetailleCommandes) {
        this.itemDetailleCommandes = itemDetailleCommandes;
    }

    public List getListDetaillesCommandes() {
        listDetaillesCommandes = ejbDetailleCommande.findAll();
        return listDetaillesCommandes;
    }

    public void setListDetaillesCommandes(List listDetaillesCommandes) {
        this.listDetaillesCommandes = listDetaillesCommandes;
    }

    public void renderInfoSurSalaire0() {

        this.detailleCommande.setIdRessource(this.detailleCommande.getIdRessource());
        System.out.println("PU 1 :" + this.detailleCommande.getIdRessource().getPu());

    }

    public String save() {
        try {

            String vpu = this.detailleCommande.getIdRessource().getPu();
            this.setPuht(vpu);

            Double localtva = 0.18;
            Double pud = Double.parseDouble(vpu);
            int qtp = this.detailleCommande.getQuantite();

            Double phtcal = pud * qtp;
            //String rs1 = String.format("%.3f", phtcal);
            String rs1 = phtcal.toString();
            String newR = rs1.replace(",", ".");

            Double taxecal = phtcal * localtva;
            //String rs2 = String.format("%.3f", taxecal);
            String rs2 = taxecal.toString();
            String newR2 = rs2.replace(",", ".");

            Double ttccal = phtcal + taxecal;
            //String rs3 = String.format("%.3f", ttccal);
            String rs3 = ttccal.toString();
            String newR3 = rs3.replace(",", ".");

            Double mttcrm = 0.0;

            if (this.detailleCommande.getRemise().equals(" ")) {

                if (this.getDateEcheance() != null) {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    String strDate = formatter.format(this.dateEcheance);
                    this.commande.setDateechance(strDate);
                } else {
                    System.out.println("Date null");
                }

                System.out.println("Je suis OK");
                String getremise = this.detailleCommande.getRemise();
                Double rm = Double.parseDouble(getremise);
                Double mtremise = ttccal * rm;
                mttcrm = ttccal - mtremise;
                String rs5 = mttcrm.toString();
                String newR5 = rs5.replace(",", ".");
                System.out.println("MTTC :" + newR5);

                ejbCommande.insertCommande(commande);

                int idCmd = ejbCommande.maxCommande();
                Commande cmd = ejbCommande.find(idCmd);

                /*Monotant taxe*/
                this.detailleCommande.setMontant(newR2);
                this.detailleCommande.setPuhortaxe(newR);
                this.detailleCommande.setMontanthortaxe(newR);
                this.detailleCommande.setTtc(newR5);
                this.detailleCommande.setIdCommande(cmd);

                ejbDetailleCommande.insertDetailleCommande(detailleCommande);

            }

            if (!this.detailleCommande.getRemise().equals(" ")) {

                if (this.getDateEcheance() != null) {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    String strDate = formatter.format(this.dateEcheance);
                    this.commande.setDateechance(strDate);
                } else {
                    System.out.println("Date null");
                }

                System.out.println("Je suis KO");
                ejbCommande.insertCommande(commande);

                int idCmd = ejbCommande.maxCommande();
                Commande cmd = ejbCommande.find(idCmd);

                /*Monotant taxe*/
                this.detailleCommande.setRemise("0.0");
                this.detailleCommande.setMontant(newR2);
                this.detailleCommande.setPuhortaxe(newR);
                this.detailleCommande.setMontanthortaxe(newR);
                this.detailleCommande.setTtc(newR3);
                this.detailleCommande.setIdCommande(cmd);

                ejbDetailleCommande.insertDetailleCommande(detailleCommande);
            }

            return "commandes";
        } catch (Exception e) {
            return "new_commande";
        }
    }

    public String saveMaj() {
        try {

            String vpu = this.detailleCommande.getIdRessource().getPu();
            this.setPuht(vpu);

            Double localtva = 0.18;
            Double pud = Double.parseDouble(vpu);
            int qtp = this.detailleCommande.getQuantite();

            Double phtcal = pud * qtp;
            //String rs1 = String.format("%.3f", phtcal);
            String rs1 = phtcal.toString();
            String newR = rs1.replace(",", ".");
            System.out.println("PHT " + newR);
            this.setPht(newR);

            Double taxecal = phtcal * localtva;
            //String rs2 = String.format("%.3f", taxecal);
            String rs2 = taxecal.toString();
            String newR2 = rs2.replace(",", ".");
            System.out.println("Taxe " + newR2);
            this.setTaxe(newR2);

            Double ttccal = phtcal + taxecal;
            //String rs3 = String.format("%.3f", ttccal);
            String rs3 = ttccal.toString();
            String newR3 = rs3.replace(",", ".");
            System.out.println("Taxe " + newR3);
            this.setTtc(newR3);

            Double mttcrm = 0.0;

            if (this.detailleCommande.getRemise().equals(" ")) {

                if (this.getDateEcheance() != null) {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    String strDate = formatter.format(this.dateEcheance);
                    this.commande.setDateechance(strDate);
                } else {
                    System.out.println("Date null");
                }

                System.out.println("Je suis OK");
                String getremise = this.detailleCommande.getRemise();
                Double rm = Double.parseDouble(getremise);
                Double mtremise = ttccal * rm;
                mttcrm = ttccal - mtremise;
                String rs5 = mttcrm.toString();
                String newR5 = rs5.replace(",", ".");
                System.out.println("MTTC :" + newR5);

                /*Monotant taxe*/
                this.detailleCommande.setMontant(newR2);
                this.detailleCommande.setPuhortaxe(newR);
                this.detailleCommande.setMontanthortaxe(newR);
                this.detailleCommande.setTtc(newR5);

                ejbDetailleCommande.insertDetailleCommande(detailleCommande);

            }

            if (!this.detailleCommande.getRemise().equals(" ")) {

                if (this.getDateEcheance() != null) {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    String strDate = formatter.format(this.dateEcheance);
                    this.commande.setDateechance(strDate);
                } else {
                    System.out.println("Date null");
                }

                System.out.println("Je suis KO");
                /*Monotant taxe*/
                this.detailleCommande.setRemise(null);
                this.detailleCommande.setMontant(newR2);
                this.detailleCommande.setPuhortaxe(newR);
                this.detailleCommande.setMontanthortaxe(newR);
                this.detailleCommande.setTtc(newR3);

                ejbDetailleCommande.insertDetailleCommande(detailleCommande);
            }

            return "commandes";
        } catch (Exception e) {
            return "maj_commande";
        }
    }

    public String supprimer() {
        try {
            commandeFromEdit = (Commande) itemCommandes.getRowData();
            ejbCommande.remove(commandeFromEdit);
            ejbDetailleCommande.deleteByCommande(commandeFromEdit.getIdCommande());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "commandes";
    }

    public String imprimer() {
        try {
            commandeFromEdit = (Commande) itemCommandes.getRowData();
            ReportBonCommande rBCmd = new ReportBonCommande();
            List<DetailleCommande> lstDetailCommandes = ejbDetailleCommande.listOfDetailleByCommande(commandeFromEdit);
            //rBCmd.GenererCMB(commandeFromEdit, lstDetailCommandes);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "commandes";
    }

    public void renderInfoSurSalaire() {

//        String vpu = this.detailleCommande.getIdRessource().getPu();
//        this.setPuht(vpu);
//        
//        Double localtva = 0.18;
//        Double pud = Double.parseDouble(vpu);
//        int qtp = this.detailleCommande.getQuantite();
//        
//        Double phtcal = pud * qtp;
//        String rs1 = String.format("%.3f", phtcal);
//        String newR = rs1.replace(",", ".");
//        this.setPht(newR);
//        
//        Double taxecal = phtcal * localtva;
//        String rs2 = String.format("%.3f", taxecal);
//        String newR2 = rs2.replace(",", ".");
//        this.setTaxe(newR2);
//        
//        Double ttccal = phtcal + taxecal;
//        String rs3 = String.format("%.3f", ttccal);
//        String newR3 = rs3.replace(",", ".");
//        this.setTtc(newR3);
    }
}
