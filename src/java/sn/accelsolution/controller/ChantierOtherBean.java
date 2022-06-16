/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import sn.accelsolution.dao.AcompteFacade;
import sn.accelsolution.dao.ChantierFacade;
import sn.accelsolution.dao.CommandeFacade;
import sn.accelsolution.dao.DetaillechantierFacade;
import sn.accelsolution.dao.DetailleLivraisonFacade;
import sn.accelsolution.dao.LivraisonFacade;
import sn.accelsolution.dao.MarcheFacade;
import sn.accelsolution.dao.PrestataireFacade;
import sn.accelsolution.dao.RessourceFacade;
import sn.accelsolution.dao.SortiestockFacade;
import sn.accelsolution.dao.StockFacade;
import sn.accelsolution.entities.Acompte;
import sn.accelsolution.entities.Chantier;
import sn.accelsolution.entities.Commande;
import sn.accelsolution.entities.Detaillechantier;
import sn.accelsolution.entities.DetailleLivraison;
import sn.accelsolution.entities.Livraison;
import sn.accelsolution.entities.Marchandise;
import sn.accelsolution.entities.Prestataire;
import sn.accelsolution.entities.Ressource;
import sn.accelsolution.entities.Sortiestock;
import sn.accelsolution.entities.Stock;
import sn.accelsolution.util.MontantConverter;

/**
 *
 * @author DELL
 */
@ManagedBean
@ViewScoped
public class ChantierOtherBean implements Serializable {

    @EJB
    MarcheFacade ejbmarche;

    @EJB
    ChantierFacade ejbchantier;

    @EJB
    DetaillechantierFacade ejbdetaillechantier;

    @EJB
    SortiestockFacade ejbSortieStock;

    @EJB
    RessourceFacade ejbressource;
    @EJB
    LivraisonFacade ejbLivraison;
    @EJB
    DetailleLivraisonFacade ejbDetailleLivraison;
    @EJB
    StockFacade ejbstock;
    @EJB
    PrestataireFacade ejbPrestataire;
    @EJB
    CommandeFacade ejbCommande;
    @EJB
    AcompteFacade ejbAcompte;

    private DataModel item;
    private List listChantiers;
    private Chantier chantier;
    private Chantier chantierFromEdit;
    private Detaillechantier detailleChantier;
    private List<Detaillechantier> listDetailleChantier;
    private List<Livraison> listLivraisons;
    private List<Detaillechantier> listPersonnelChantier;
    private List<Sortiestock> listSortieStock;
    private List<Commande> listCommandeChantiers;
    private List<Stock> listStockByCommandes;
    private Stock stock;
    private List<Stock> listStocks;
    private Livraison livraison;
    private DetailleLivraison detailleLivraison;
    private String dateAppro;
    private int quantite;
    private List listMarches;
    private String coutTotalApproDirect;
    private String coutTotalApproStock;
    private String totalAccorps;
    private String totalAcompteHT;
    private String totalAcompteTCC;

    /**
     * Creates a new instance of UtilisateurBean
     */
    public ChantierOtherBean() {
        chantier = new Chantier();
        chantierFromEdit = new Chantier();
        detailleChantier = new Detaillechantier();
        stock = new Stock();
        livraison = new Livraison();
        detailleLivraison = new DetailleLivraison();
    }

    public String getTotalAccorps() {
        return totalAccorps;
    }

    public void setTotalAccorps(String totalAccorps) {
        this.totalAccorps = totalAccorps;
    }

    public String getTotalAcompteHT() {
        return totalAcompteHT;
    }

    public void setTotalAcompteHT(String totalAcompteHT) {
        this.totalAcompteHT = totalAcompteHT;
    }

    public String getTotalAcompteTCC() {
        return totalAcompteTCC;
    }

    public void setTotalAcompteTCC(String totalAcompteTCC) {
        this.totalAcompteTCC = totalAcompteTCC;
    }

    public String getCoutTotalApproDirect() {
        return coutTotalApproDirect;
    }

    public void setCoutTotalApproDirect(String coutTotalApproDirect) {
        this.coutTotalApproDirect = coutTotalApproDirect;
    }

    public String getCoutTotalApproStock() {
        return coutTotalApproStock;
    }

    public void setCoutTotalApproStock(String coutTotalApproStock) {
        this.coutTotalApproStock = coutTotalApproStock;
    }

    public List<Stock> getListStockByCommandes() {
        return listStockByCommandes;
    }

    public void setListStockByCommandes(List<Stock> listStockByCommandes) {
        this.listStockByCommandes = listStockByCommandes;
    }

    public List<Commande> getListCommandeChantiers() {
        return listCommandeChantiers;
    }

    public void setListCommandeChantiers(List<Commande> listCommandeChantiers) {
        this.listCommandeChantiers = listCommandeChantiers;
    }

    public List<Livraison> getListLivraisons() {
        return listLivraisons;
    }

    public void setListLivraisons(List<Livraison> listLivraisons) {
        this.listLivraisons = listLivraisons;
    }

    public String getDateAppro() {
        return dateAppro;
    }

    public void setDateAppro(String dateAppro) {
        this.dateAppro = dateAppro;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public List<Stock> getListStocks() {
        listStocks = ejbstock.findAll();
        return listStocks;
    }

    public void setListStocks(List<Stock> listStocks) {
        this.listStocks = listStocks;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Livraison getLivraison() {
        return livraison;
    }

    public void setLivraison(Livraison livraison) {
        this.livraison = livraison;
    }

    public DetailleLivraison getDetailleLivraison() {
        return detailleLivraison;
    }

    public void setDetailleLivraison(DetailleLivraison detailleLivraison) {
        this.detailleLivraison = detailleLivraison;
    }

    public List<Detaillechantier> getListPersonnelChantier() {
        return listPersonnelChantier;
    }

    public void setListPersonnelChantier(List<Detaillechantier> listPersonnelChantier) {
        this.listPersonnelChantier = listPersonnelChantier;
    }

    public List<Detaillechantier> getListDetailleChantier() {
        return listDetailleChantier;
    }

    public void setListDetailleChantier(List<Detaillechantier> listDetailleChantier) {
        this.listDetailleChantier = listDetailleChantier;
    }

    public DataModel getItem() {
        item = new ListDataModel();
        this.setListChantiers(ejbchantier.findAll());
        item.setWrappedData(this.getListChantiers());
        return item;
    }

    public void setItem(DataModel item) {
        this.item = item;
    }

    public List getListChantiers() {
        listChantiers = ejbchantier.findAll();
        return listChantiers;
    }

    public void setListChantiers(List listChantiers) {
        this.listChantiers = listChantiers;
    }

    public List getListMarches() {
        listMarches = ejbmarche.findAll();
        return listMarches;
    }

    public void setListMarches(List listMarches) {
        this.listMarches = listMarches;
    }

    public Chantier getChantier() {
        return chantier;
    }

    public void setChantier(Chantier chantier) {
        this.chantier = chantier;
    }

    public Chantier getChantierFromEdit() {
        return chantierFromEdit;
    }

    public void setChantierFromEdit(Chantier chantierFromEdit) {
        this.chantierFromEdit = chantierFromEdit;
    }

    public Detaillechantier getDetailleChantier() {
        return detailleChantier;
    }

    public void setDetailleChantier(Detaillechantier detailleChantier) {
        this.detailleChantier = detailleChantier;
    }

    public List<Sortiestock> getListSortieStock() {
        return listSortieStock;
    }

    public void setListSortieStock(List<Sortiestock> listSortieStock) {
        this.listSortieStock = listSortieStock;
    }

    public String save() {
        try {
            chantier.setEtatchantier("En cours");
            ejbchantier.insertChantier(this.chantier);
            return "chantiers";
        } catch (Exception e) {
            return "new_chantier";
        }
    }

    public String saveDetaille() {
        try {
            this.detailleChantier.setIdChantier(this.chantierFromEdit);
            ejbdetaillechantier.insertChantierAffectation(this.detailleChantier);

            Prestataire prestataire = this.detailleChantier.getIdPrestataire();
            prestataire.setIdChantier(this.chantierFromEdit);
            ejbPrestataire.edit(prestataire);

            return "chantiers";
        } catch (Exception e) {
            return "new_affectation_personnel";
        }
    }

    public String saveDetaillee() {
//        try {
//            this.detailleChantier.setIdChantier(this.chantierFromEdit);
//            ejbdetaillechantier.insertChantierr(this.detailleChantier);
//
//            Ressource ressource = ejbressource.find(this.detailleChantier.getIdRessource().getIdRessource());
//            int ancienqt = ressource.getQtStock();
//            int newQt = ancienqt - this.detailleChantier.getQt();
//            ressource.setQtStock(newQt);
//            ejbressource.edit(ressource);
//
//            return "chantiers";
//        } catch (Exception e) {
//            return "new_affectation_ressource";
//        }
        return "";
    }

    public void reset() {
        this.chantier = new Chantier();
        this.chantierFromEdit = new Chantier();
    }

    public String resetModif() {
        return "chantiers";
    }

    public String editer() {
        try {
            chantierFromEdit = (Chantier) item.getRowData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "edit_chantier";

    }

    public String affecterPersonnel() {
        try {
            chantierFromEdit = (Chantier) item.getRowData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "new_affectation_personnel";
    }

    public String affecterRessource() {
        try {
            chantierFromEdit = (Chantier) item.getRowData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "new_affectation_ressource";
    }

    public String update() {
        try {
            ejbchantier.edit(chantierFromEdit);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "chantiers";
    }

    public String consulter() {
        try {

            chantierFromEdit = (Chantier) item.getRowData();

            this.listLivraisons = ejbLivraison.listOfLivraisonByChantier(chantierFromEdit);
            this.setListLivraisons(this.listLivraisons);

            this.listPersonnelChantier = ejbdetaillechantier.listOfPrestataitreByChantier(chantierFromEdit.getIdChantier());
            this.setListPersonnelChantier(this.listPersonnelChantier);

            this.listSortieStock = ejbSortieStock.listOfSortieStockByChantier(chantierFromEdit);
            this.setListSortieStock(this.listSortieStock);

            MontantConverter mtc = new MontantConverter();

            Double mtcalcule0 = 0.0;

            for (Livraison lvs : this.listLivraisons) {
                Double pu1 = mtc.StringToDouble(lvs.getPu());
                Double cal1P = lvs.getQtLivre() * pu1;
                mtcalcule0 = mtcalcule0 + cal1P;
            }

            String mtcalculeConvert0 = mtc.DoubleToString(mtcalcule0);
            this.setCoutTotalApproDirect(mtcalculeConvert0);

            Double mtcalcule = 0.0;

            for (Sortiestock sst : this.listSortieStock) {
                Double pu = mtc.StringToDouble(sst.getPu());
                Double cal1 = sst.getQuantite() * pu;
                mtcalcule = mtcalcule + cal1;
            }

            String mtcalculeConvert = mtc.DoubleToString(mtcalcule);
            this.setCoutTotalApproStock(mtcalculeConvert);
            
            
            
            /*Calcule de total*/
            List<Prestataire> allPrestatairesForCalcules = ejbPrestataire.listOfPrestataireByChantier(chantierFromEdit);
            Double valueAccordd = 0.0;
            Double valueAcompteeHT = 0.0;
            Double valueAcompteeTTC = 0.0;

            for (Prestataire p : allPrestatairesForCalcules) {
                Double number1 = mtc.StringToDouble(p.getAccord());
                valueAccordd = valueAccordd + number1;
            }
            String r1 = mtc.DoubleToString(valueAccordd);
            this.setTotalAccorps(r1);

            for (Prestataire p : allPrestatairesForCalcules) {
                List<Acompte> allAcomptesForCalcules = new ArrayList<>();
                
                allAcomptesForCalcules = ejbAcompte.listOfAcompteByPrestataire(p);
                
                for (Acompte a : allAcomptesForCalcules) {
                   
                    if(a.getChoixtva().equals("non")){
                        Double nb1 = mtc.StringToDouble(a.getMontantAcompte());
                        valueAcompteeHT = valueAcompteeHT + nb1;
                    }
                    
                    if(a.getChoixtva().equals("oui")){
                        Double nb2 = mtc.StringToDouble(a.getMontantAcompte());
                        valueAcompteeTTC = valueAcompteeTTC + nb2;
                    }
                    
                }
            }
            
            
            String RvalueAcompteeHT = mtc.DoubleToString(valueAcompteeHT);
            String RvalueAcompteeTTC =  mtc.DoubleToString(valueAcompteeTTC);
            this.setTotalAcompteHT(RvalueAcompteeHT);
            this.setTotalAcompteTCC(RvalueAcompteeTTC);
            

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "consulte_chantier";
    }

    public String supprimer() {
        try {
            chantierFromEdit = (Chantier) item.getRowData();
            ejbchantier.remove(chantierFromEdit);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "chantiers";
    }

    public String saveLivraison() {

        try {

            this.livraison.setDesignation(this.getStock().getIdMarchandise().getLibelle());
            this.livraison.setQtLivre(this.getQuantite());
            this.livraison.setDateLivraison(this.getDateAppro());
            this.livraison.setIdFournisseur(this.getStock().getIdFournisseur());
            this.livraison.setIdCommande(this.getStock().getIdCommande());
            this.livraison.setIdChantier(this.chantierFromEdit);
            ejbLivraison.insertLivraison(this.livraison);

            int idL = ejbLivraison.maxLivraison();
            Livraison Lv = ejbLivraison.find(idL);
            this.detailleLivraison.setIdLivraison(Lv);
            this.detailleLivraison.setDesignation(Lv.getDesignation());
            this.setQuantite(this.getQuantite());

            ejbDetailleLivraison.insertDetailleCommande(this.detailleLivraison);

            int newQ = this.getStock().getQtStock() - this.quantite;
            this.stock.setQtStock(newQ);
            ejbstock.edit(this.getStock());

            return "chantiers";
        } catch (Exception e) {
            return "new_affectation_ressource";
        }
    }
    
    public String detail(Prestataire myPrestataire) {
        try {
            System.out.println("Prestataire: "+myPrestataire.getIdPrestataire());
            
        } catch (Exception e) {
        }
        
        return "";
    } 
    
    public String consulterImprimer(Chantier chantier) {
        System.out.println("Chantier: "+chantier.getSiteChantier());
        return null;
    }

}
