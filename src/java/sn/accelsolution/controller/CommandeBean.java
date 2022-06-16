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
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;
import sn.accelsolution.dao.CommandeFacade;
import sn.accelsolution.dao.DetailleCommandeFacade;
import sn.accelsolution.dao.MarchandiseFacade;
import sn.accelsolution.dao.PrixFacade;
import sn.accelsolution.dao.RessourceFacade;
import sn.accelsolution.dao.UtilisateurFacade;
import sn.accelsolution.entities.Commande;
import sn.accelsolution.entities.DetailleCommande;
import sn.accelsolution.entities.Prix;
import sn.accelsolution.entities.Utilisateur;
import sn.accelsolution.util.MontantConverter;
import sn.accelsolution.util.ReportBonCommande;
import sn.accelsolution.util.UtilPrix;

/**
 *
 * @author DELL
 */
@ManagedBean
@RequestScoped
public class CommandeBean implements Serializable {

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
    @EJB
    MarchandiseFacade ejbMarchandise;
    @EJB
    PrixFacade ejbprix;

    private Commande commande;
    private Prix prix;
    private Commande commandeFromEdit;
    private DetailleCommande detailleCommande;
    private DetailleCommande detailleCommandeFromEdit;

    private DataModel itemCommandes;
    private DataModel itemFactures;
    private DataModel item2DetailleCommande;
    private List listCommandes;
    private List list2DetaillesCommandes;
    private List listFactures;
    private DataModel itemDetailleCommandes;
    private List listDetaillesCommandes;
    private List listMarchandises;
    private Date dateEcheance;
    private String puht;
    private String tva;
    private String pht;
    private String ttc;
    private String taxe;
    private Utilisateur user;
    private MontantConverter montantConverter = new MontantConverter();
    private String dateEch;
    private List<UtilPrix> listUtilPrix;

    @PostConstruct
    public void init() {
        this.setTva("18%");
        int maxCmdd= ejbCommande.maxCommande();
        String numDmdd = "ESTCMD" + maxCmdd;
        this.commande.setCode(numDmdd);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        user = new Utilisateur();
        user = (Utilisateur) session.getAttribute("user");

        System.out.println("User :" + user.getNomUtilisateur());
    }

    public CommandeBean() {

        commande = new Commande();
        commandeFromEdit = new Commande();
        detailleCommande = new DetailleCommande();
        detailleCommandeFromEdit = new DetailleCommande();
        Double initD = 0.0;
        detailleCommande.setRemise(initD.toString());
    }

    public List<UtilPrix> getListUtilPrix() {
        return listUtilPrix;
    }

    public void setListUtilPrix(List<UtilPrix> listUtilPrix) {
        this.listUtilPrix = listUtilPrix;
    }

    public Prix getPrix() {
        return prix;
    }

    public void setPrix(Prix prix) {
        this.prix = prix;
    }

    public String getDateEch() {
        return dateEch;
    }

    public void setDateEch(String dateEch) {
        this.dateEch = dateEch;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public DataModel getItem2DetailleCommande() {
        return item2DetailleCommande;
    }

    public void setItem2DetailleCommande(DataModel item2DetailleCommande) {
        this.item2DetailleCommande = item2DetailleCommande;
    }

    public List getList2DetaillesCommandes() {
        return list2DetaillesCommandes;
    }

    public void setList2DetaillesCommandes(List list2DetaillesCommandes) {
        this.list2DetaillesCommandes = list2DetaillesCommandes;
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
        this.setListCommandes(ejbCommande.listOfCommande());
        itemCommandes.setWrappedData(this.getListCommandes());
        return itemCommandes;
    }

    public void setItemCommandes(DataModel itemCommandes) {
        this.itemCommandes = itemCommandes;
    }

    public List getListCommandes() {
        listCommandes = ejbCommande.listOfCommande();
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

    public DataModel getItemFactures() {
        itemFactures = new ListDataModel();
        this.setListFactures(ejbCommande.listOfFacture());
        itemFactures.setWrappedData(this.getListFactures());
        return itemFactures;

    }

    public void setItemFactures(DataModel itemFactures) {
        this.itemFactures = itemFactures;
    }

    public List getListFactures() {
        listFactures = ejbCommande.listOfFacture();
        return listFactures;
    }

    public void setListFactures(List listFactures) {
        this.listFactures = listFactures;
    }

    public List getListMarchandises() {
        listMarchandises = ejbMarchandise.findAll();
        return listMarchandises;
    }

    public void setListMarchandises(List listMarchandises) {
        this.listMarchandises = listMarchandises;
    }

    public void renderInfoSurSalaire0() {
        this.detailleCommande.setIdRessource(this.detailleCommande.getIdRessource());
        System.out.println("PU 1 :" + this.detailleCommande.getIdRessource().getPu());
    }

    public String save() {

        try {

            this.detailleCommande.setDateEchance(this.getDateEch());
            this.commande.setDateechance(this.getDateEch());
            this.prix = ejbprix.prixByProdAndFsse(this.detailleCommande.getIdMarchandise(), this.commande.getIdFournisseur());
            String vpu = this.prix.getPrix();
            this.setPuht(vpu);

            Double localtva = 0.18;
            //Double pud = Double.parseDouble(vpu);
            Double pud = montantConverter.StringToDouble(vpu);
            int qtp = this.detailleCommande.getQuantite();

            Double phtcal = pud * qtp;
            //String rs1 = String.format("%.3f", phtcal);
            //String rs1 = phtcal.toString();
            String newR = montantConverter.DoubleToString(phtcal);
            //String newR = rs1.replace(",", ".");

            Double taxecal = phtcal * localtva;
            //String rs2 = String.format("%.3f", taxecal);
            //String rs2 = taxecal.toString();
            String newR2 = montantConverter.DoubleToString(phtcal);
            //String newR2 = rs2.replace(",", ".");

            Double ttccal = phtcal + taxecal;
            //String rs3 = String.format("%.3f", ttccal);
            //String rs3 = ttccal.toString();
            String newR3 = montantConverter.DoubleToString(phtcal);
            //String newR3 = rs3.replace(",", ".");

            Double mttcrm = 0.0;

            if (this.detailleCommande.getRemise().equals("0.0")) {

                commande.setTypecommande("Commande");
                commande.setIdUtilisateur(this.getUser());
                ejbCommande.insertCommande(commande);

                int idCmd = ejbCommande.maxCommande();
                Commande cmd = ejbCommande.find(idCmd);

                /*Monotant taxe*/
                this.detailleCommande.setMontant(newR2);
                this.detailleCommande.setPuhortaxe(newR);
                this.detailleCommande.setMontanthortaxe(newR);
                this.detailleCommande.setTtc(newR3);
                this.detailleCommande.setIdCommande(cmd);
                ejbDetailleCommande.insertDetailleCommande(this.detailleCommande);

            } else {

                String getremise = this.detailleCommande.getRemise();
                Double rm = Double.parseDouble(getremise);
                Double mtremise = ttccal * rm;
                mttcrm = ttccal - mtremise;
                //String rs5 = mttcrm.toString();
                //String newR5 = rs5.replace(",", ".");
                String newR5 = montantConverter.DoubleToString(phtcal);
                commande.setTypecommande("Commande");
                commande.setIdUtilisateur(this.getUser());
                ejbCommande.insertCommande(commande);

                int idCmd = ejbCommande.maxCommande();
                Commande cmd = ejbCommande.find(idCmd);

                /*Monotant taxe*/
                this.detailleCommande.setMontant(newR2);
                this.detailleCommande.setPuhortaxe(newR);
                this.detailleCommande.setMontanthortaxe(newR);
                this.detailleCommande.setTtc(newR5);
                this.detailleCommande.setIdCommande(cmd);
                ejbDetailleCommande.insertDetailleCommande(this.detailleCommande);
            }

            return "commandes";
        } catch (Exception e) {
            return "new_commande";
        }
    }

    public String savee() {
        try {

            this.detailleCommande.setDateEchance(this.getDateEch());
            this.commande.setDateechance(this.getDateEch());
            this.prix = ejbprix.prixByProdAndFsse(this.detailleCommande.getIdMarchandise(), this.commande.getIdFournisseur());
            String vpu = this.prix.getPrix();
            this.setPuht(vpu);

            Double localtva = 0.18;
            Double pud = Double.parseDouble(vpu);
            int qtp = this.detailleCommande.getQuantite();

            Double phtcal = pud * qtp;
            //String rs1 = String.format("%.3f", phtcal);
            //String rs1 = phtcal.toString();
            //String newR = rs1.replace(",", ".");
            String newR = montantConverter.DoubleToString(phtcal);

            Double taxecal = phtcal * localtva;
            //String rs2 = String.format("%.3f", taxecal);
            //String rs2 = taxecal.toString();
            //String newR2 = rs2.replace(",", ".");
            String newR2 = montantConverter.DoubleToString(taxecal);

            Double ttccal = phtcal + taxecal;
            //String rs3 = String.format("%.3f", ttccal);
            //String rs3 = ttccal.toString();
            //String newR3 = rs3.replace(",", ".");
            String newR3 = montantConverter.DoubleToString(ttccal);

            Double mttcrm = 0.0;

            commande.setTypecommande("Facture");
            ejbCommande.insertCommande2P(commande);

            int idCmd = ejbCommande.maxCommande();
            Commande cmd = ejbCommande.find(idCmd);

            /*Monotant taxe*/
            this.detailleCommande.setMontant(newR2);
            this.detailleCommande.setPuhortaxe(this.detailleCommande.getPu());
            this.detailleCommande.setMontanthortaxe(newR);
            this.detailleCommande.setTtc(newR3);
            this.detailleCommande.setIdCommande(cmd);

            ejbDetailleCommande.insertDetailleCommande2P(detailleCommande);

            return "factures";
        } catch (Exception e) {
            return "new_facture";
        }
    }

    public String saveMaj() {
        try {

            this.prix = ejbprix.prixByProdAndFsse(this.detailleCommande.getIdMarchandise(), this.commande.getIdFournisseur());
            String vpu = this.prix.getPrix();
            this.setPuht(vpu);

            Double localtva = 0.18;
            Double pud = Double.parseDouble(vpu);
            int qtp = this.detailleCommande.getQuantite();

            Double phtcal = pud * qtp;
            //String rs1 = String.format("%.3f", phtcal);
            //String rs1 = phtcal.toString();
            //String newR = rs1.replace(",", ".");
            String newR = montantConverter.DoubleToString(phtcal);
            System.out.println("PHT " + newR);
            this.setPht(newR);

            Double taxecal = phtcal * localtva;
            //String rs2 = String.format("%.3f", taxecal);
            //String rs2 = taxecal.toString();
            //String newR2 = rs2.replace(",", ".");
            String newR2 = montantConverter.DoubleToString(taxecal);

            this.setTaxe(newR2);

            Double ttccal = phtcal + taxecal;
            //String rs3 = String.format("%.3f", ttccal);
            //String rs3 = ttccal.toString();
            //String newR3 = rs3.replace(",", ".");
            String newR3 = montantConverter.DoubleToString(ttccal);

            this.setTtc(newR3);

            Double mttcrm = 0.0;

            if (this.detailleCommande.getRemise().equals("0.0")) {

                /*Monotant taxe*/
                this.detailleCommande.setMontant(newR2);
                this.detailleCommande.setPuhortaxe(newR);
                this.detailleCommande.setMontanthortaxe(newR);
                this.detailleCommande.setTtc(newR3);

                ejbDetailleCommande.insertDetailleCommande(detailleCommande);

            } else {

                String getremise = this.detailleCommande.getRemise();
                Double rm = Double.parseDouble(getremise);
                Double mtremise = ttccal * rm;
                mttcrm = ttccal - mtremise;
                //String rs5 = mttcrm.toString();
                //String newR5 = rs5.replace(",", ".");
                String newR5 = montantConverter.DoubleToString(mttcrm);

                /*Monotant taxe*/
                this.detailleCommande.setMontant(newR2);
                this.detailleCommande.setPuhortaxe(newR);
                this.detailleCommande.setMontanthortaxe(newR);
                this.detailleCommande.setTtc(newR5);

                ejbDetailleCommande.insertDetailleCommande(detailleCommande);

            }

            return "commandes";
        } catch (Exception e) {
            return "maj_commande";
        }
    }

    public String saveMaj2() {
        try {

            this.prix = ejbprix.prixByProdAndFsse(this.detailleCommande.getIdMarchandise(), this.commande.getIdFournisseur());
            String vpu = this.prix.getPrix();
            this.setPuht(vpu);

            Double localtva = 0.18;
            Double pud = Double.parseDouble(vpu);
            int qtp = this.detailleCommande.getQuantite();

            Double phtcal = pud * qtp;
            //String rs1 = String.format("%.3f", phtcal);
            //String rs1 = phtcal.toString();
            //String newR = rs1.replace(",", ".");
            String newR = montantConverter.DoubleToString(phtcal);

            Double taxecal = phtcal * localtva;
            //String rs2 = String.format("%.3f", taxecal);
            // String rs2 = taxecal.toString();
            // String newR2 = rs2.replace(",", ".");
            String newR2 = montantConverter.DoubleToString(taxecal);

            Double ttccal = phtcal + taxecal;
            //String rs3 = String.format("%.3f", ttccal);
            //String rs3 = ttccal.toString();
            //String newR3 = rs3.replace(",", ".");
            String newR3 = montantConverter.DoubleToString(ttccal);

            Double mttcrm = 0.0;

            /*Monotant taxe*/
            this.detailleCommande.setMontant(newR2);
            this.detailleCommande.setPuhortaxe(this.detailleCommande.getPu());
            this.detailleCommande.setMontanthortaxe(newR);
            this.detailleCommande.setTtc(newR3);

            ejbDetailleCommande.insertDetailleCommande2P(detailleCommande);

            return "factures";
        } catch (Exception e) {
            return "new_facture";
        }
    }

    public String supprimer() {
        try {
            commandeFromEdit = (Commande) itemCommandes.getRowData();
            System.out.println("Valeur recuperer: " + commandeFromEdit.getIdCommande());
            ejbDetailleCommande.deleteByCommande(commandeFromEdit.getIdCommande());
            ejbCommande.deleteCommande(commandeFromEdit.getIdCommande());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "commandes";
    }

    public String supprimerr() {
        try {

            commandeFromEdit = (Commande) itemFactures.getRowData();
            System.out.println("Valeur recuperer: " + commandeFromEdit.getIdCommande());
            ejbDetailleCommande.deleteByCommande(commandeFromEdit.getIdCommande());
            ejbCommande.deleteCommande(commandeFromEdit.getIdCommande());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "factures";
    }

    public String imprimer() {
        try {
            commandeFromEdit = (Commande) itemCommandes.getRowData();
            ReportBonCommande rBCmd = new ReportBonCommande(); 
            List<DetailleCommande> lstDetailCommandes = ejbDetailleCommande.listOfDetailleByCommande(commandeFromEdit);

//            for (DetailleCommande d : lstDetailCommandes) {
//                UtilPrix utilprix = new UtilPrix();
//                this.prix = ejbprix.prixByProdAndFsse(d.getIdMarchandise(), commandeFromEdit.getIdFournisseur());
//                String vpu = prix.getPrix();
//                utilprix.setLibelleMarchandise(d.getIdMarchandise().getLibelle());
//                utilprix.setMontant(d.getMontant());
//                utilprix.setMontanthortaxe(d.getMontanthortaxe());
//                utilprix.setPrix(vpu);
//                utilprix.setQuantite(d.getQuantite());
//                utilprix.setRemise(d.getRemise());
//                utilprix.setTtc(d.getTtc());
//                this.listUtilPrix.add(utilprix);
//            }

            rBCmd.GenererCMB(commandeFromEdit, lstDetailCommandes);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "commandes";
    }

    public String imprimerr() {
        try {
            commandeFromEdit = (Commande) itemFactures.getRowData();
            ReportBonCommande rBCmd = new ReportBonCommande();
            List<DetailleCommande> lstDetailCommandes = ejbDetailleCommande.listOfDetailleByCommande(commandeFromEdit);
            rBCmd.GenererNewCMBB(commandeFromEdit, lstDetailCommandes);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "factures";
    }

    public String detail() {
        try {

            commandeFromEdit = (Commande) itemCommandes.getRowData();

            /* List des detailles commandes */
            item2DetailleCommande = new ListDataModel();
            this.setList2DetaillesCommandes(ejbDetailleCommande.listOfDetailleByCommande(commandeFromEdit));
            item2DetailleCommande.setWrappedData(this.getList2DetaillesCommandes());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "consulte_commande";
    }

    public String detail2() {
        try {

            commandeFromEdit = (Commande) itemFactures.getRowData();

            /* List des detailles commandes */
            item2DetailleCommande = new ListDataModel();
            this.setList2DetaillesCommandes(ejbDetailleCommande.listOfDetailleByCommande(commandeFromEdit));
            item2DetailleCommande.setWrappedData(this.getList2DetaillesCommandes());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "consulte_facture";
    }

    /*public String editDetail() {
     try {
     System.out.println("Values");
     detailleCommandeFromEdit = (DetailleCommande) item2DetailleCommande.getRowData();
     System.out.println("Commande: " + detailleCommandeFromEdit.getDateEchance());

     } catch (Exception e) {
     e.printStackTrace();
     }
     return "commandes";
     }*/
}
