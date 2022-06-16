/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.Visibility;
import sn.accelsolution.dao.CommandeFacade;
import sn.accelsolution.dao.DetailleCommandeFacade;
import sn.accelsolution.dao.EntrepotFacade;
import sn.accelsolution.dao.FournisseurFacade;
import sn.accelsolution.dao.HistoriquelivraisonFacade;
import sn.accelsolution.dao.MarchandiseFacade;
import sn.accelsolution.dao.StockFacade;
import sn.accelsolution.dao.UnitemesureFacade;
import sn.accelsolution.dao.UtilisateurFacade;
import sn.accelsolution.entities.Actionmenu;
import sn.accelsolution.entities.Commande;
import sn.accelsolution.entities.DetailleCommande;
import sn.accelsolution.entities.Entrepot;
import sn.accelsolution.entities.Fournisseur;
import sn.accelsolution.entities.Historiquelivraison;
import sn.accelsolution.entities.Marchandise;
import sn.accelsolution.entities.Stock;
import sn.accelsolution.entities.Unitemesure;
import sn.accelsolution.entities.Utilisateur;
import sn.accelsolution.util.FactureUtil;
import sn.accelsolution.util.MontantConverter;
import sn.accelsolution.util.UtilControleMenu;
import sn.accelsolution.util.UtilStock;   

/**
 *
 * @author DELL
 */
@ManagedBean
@ViewScoped
public class StockBean implements Serializable {

    /**
     * Creates a new instance of ContratRecrutBean
     */
    @EJB
    StockFacade ejbStock;
    @EJB
    MarchandiseFacade ejbMarchandise;
    @EJB
    FournisseurFacade ejbFournisseur;
    @EJB
    UnitemesureFacade ejbUnitemesure;
    @EJB
    CommandeFacade ejbCommande;
    @EJB
    DetailleCommandeFacade ejbDetailCommande;
    @EJB
    UtilisateurFacade ejbUtilisateur;
    @EJB
    EntrepotFacade ejbEntrepot;
    @EJB
    HistoriquelivraisonFacade ejbHistoriqueLivraison;

    private Stock stock;
    private Stock stockFromEdit;
    private Entrepot entrepot;
    private Utilisateur user;
    private UtilStock utilStockFromEdit;
    private DetailleCommande detailleCommande;
    private UtilStock utilStock;
    private MontantConverter montantConverter = new MontantConverter();

    private List<UtilStock> allStocks = new ArrayList<>();
    private List<Marchandise> allMarchandises;
    private List<Fournisseur> allFournisseurs;
    private List<Unitemesure> allUnitemesures;
    private List<Commande> allCommandes;
    private List<DetailleCommande> allDEtailCommandes;
    private String myEmtyValues;
    private List<Commande> allCommande;
    private List<FactureUtil> listStocks;
    private Historiquelivraison historiqueLivraison;
    private List<Historiquelivraison> listLivraisons;
    private List<Historiquelivraison> allDetailleLivraison;
    private String idCommandeLiv;
    private String nomEntrepot;

    private List<Actionmenu> myllActionmenus;
    private String creerStock;
    private String modifierStock;
    private String supprimerStock;
    private String consulterStock;
    private String imprimerStock;

    private List<Actionmenu> myllActionmenusHisto;
    private String creerHistoLivraison;
    private String modifierHistoLivraison;
    private String supprimerHistoLivraison;
    private String consulterHistoLivraison;
    private String imprimerHistoLivraison;

    @PostConstruct
    public void init() {

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UtilControleMenu utilControleMenu = new UtilControleMenu();

        myllActionmenus = (List<Actionmenu>) session.getAttribute("allActionmenusFromTraitementInventaire");

        this.setCreerStock(utilControleMenu.creerStock(myllActionmenus));
        this.setModifierStock(utilControleMenu.modifierStock(myllActionmenus));
        this.setSupprimerStock(utilControleMenu.supprimerStock(myllActionmenus));
        this.setConsulterStock(utilControleMenu.consulterStock(myllActionmenus)); 

        myllActionmenusHisto = (List<Actionmenu>) session.getAttribute("allActionmenusFromTraitementHistoLivraison");

        this.setCreerHistoLivraison(utilControleMenu.creerHistoLivraison(myllActionmenus));
        this.setModifierHistoLivraison(utilControleMenu.modifierHistoLivraison(myllActionmenus));
        this.setSupprimerHistoLivraison(utilControleMenu.supprimerHistoLivraison(myllActionmenus));
        this.setConsulterHistoLivraison(utilControleMenu.consulterHistoLivraison(myllActionmenus));

        /*List<Stock> listStk = ejbStock.findAll();
         MontantConverter mtc = new MontantConverter();

         for (Stock s : listStk) {
         utilStock = new UtilStock();
         utilStock.setIdSotk(s.getIdSotk());
         utilStock.setDesignation(s.getDesignation());
         utilStock.setFournisseur(s.getIdFournisseur());
         utilStock.setDateEntreStock(s.getDateEntreStock());
         utilStock.setPuStock(s.getPuStock());
         Double mtc1 = mtc.StringToDouble(s.getPuStock());
         utilStock.setPuStockDouble(mtc1);
         utilStock.setQtStock(s.getQtStock());
         String vs = montantConverter.CalculeValeurInventaire(s.getPuStock(), s.getQtStock());
         utilStock.setValeurInventaire(vs);
         utilStock.setQtSeuille(s.getQtSeuille());
         utilStock.setDateLastAppro(s.getDateLastAppro());
         utilStock.setIdCommande(s.getIdCommande());
         utilStock.setUnitemesure(s.getIdUnitemesure());
         utilStock.setUtilisateur(s.getIdUtilisateur());
         utilStock.setNomUser(s.getIdUtilisateur().getNomUtilisateur() + " " + s.getIdUtilisateur().getPrenomUtilisateur());
         utilStock.setPu(s.getPuStock());
         Double mtc2 = mtc.StringToDouble(s.getPu());
         utilStock.setPuDouble(mtc2);
         utilStock.setCommentaire(s.getCommentaire());
         utilStock.setQt(s.getQt());
         utilStock.setPu(s.getPu());

         if (utilStock.getQtStock() <= utilStock.getQtSeuille()) {
         utilStock.setControlStock("oui");
         } else {
         utilStock.setControlStock("non");
         }

         System.out.println("Valeur controle:" + utilStock.getControlStock());
         String pt = montantConverter.CalculeValeurInventaire(s.getPu(), s.getQt());
         utilStock.setTotal(pt);
         this.allStocks.add(utilStock);
         } */
    }

    public StockBean() {

        stock = new Stock();
        stockFromEdit = new Stock();
        utilStock = new UtilStock();
        utilStockFromEdit = new UtilStock();
        listStocks = new ArrayList<>();
        entrepot = new Entrepot();
        historiqueLivraison = new Historiquelivraison();
    }

    public List<Actionmenu> getMyllActionmenusHisto() {
        return myllActionmenusHisto;
    }

    public void setMyllActionmenusHisto(List<Actionmenu> myllActionmenusHisto) {
        this.myllActionmenusHisto = myllActionmenusHisto;
    }

    public String getCreerHistoLivraison() {
        return creerHistoLivraison;
    }

    public void setCreerHistoLivraison(String creerHistoLivraison) {
        this.creerHistoLivraison = creerHistoLivraison;
    }

    public String getModifierHistoLivraison() {
        return modifierHistoLivraison;
    }

    public void setModifierHistoLivraison(String modifierHistoLivraison) {
        this.modifierHistoLivraison = modifierHistoLivraison;
    }

    public String getSupprimerHistoLivraison() {
        return supprimerHistoLivraison;
    }

    public void setSupprimerHistoLivraison(String supprimerHistoLivraison) {
        this.supprimerHistoLivraison = supprimerHistoLivraison;
    }

    public String getConsulterHistoLivraison() {
        return consulterHistoLivraison;
    }

    public void setConsulterHistoLivraison(String consulterHistoLivraison) {
        this.consulterHistoLivraison = consulterHistoLivraison;
    }

    public String getImprimerHistoLivraison() {
        return imprimerHistoLivraison;
    }

    public void setImprimerHistoLivraison(String imprimerHistoLivraison) {
        this.imprimerHistoLivraison = imprimerHistoLivraison;
    }

    public MontantConverter getMontantConverter() {
        return montantConverter;
    }

    public void setMontantConverter(MontantConverter montantConverter) {
        this.montantConverter = montantConverter;
    }

    public List<Actionmenu> getMyllActionmenus() {
        return myllActionmenus;
    }

    public void setMyllActionmenus(List<Actionmenu> myllActionmenus) {
        this.myllActionmenus = myllActionmenus;
    }

    public String getCreerStock() {
        return creerStock;
    }

    public void setCreerStock(String creerStock) {
        this.creerStock = creerStock;
    }

    public String getModifierStock() {
        return modifierStock;
    }

    public void setModifierStock(String modifierStock) {
        this.modifierStock = modifierStock;
    }

    public String getSupprimerStock() {
        return supprimerStock;
    }

    public void setSupprimerStock(String supprimerStock) {
        this.supprimerStock = supprimerStock;
    }

    public String getConsulterStock() {
        return consulterStock;
    }

    public void setConsulterStock(String consulterStock) {
        this.consulterStock = consulterStock;
    }

    public String getImprimerStock() {
        return imprimerStock;
    }

    public void setImprimerStock(String imprimerStock) {
        this.imprimerStock = imprimerStock;
    }

    public String getNomEntrepot() {
        return nomEntrepot;
    }

    public void setNomEntrepot(String nomEntrepot) {
        this.nomEntrepot = nomEntrepot;
    }

    public String getIdCommandeLiv() {
        return idCommandeLiv;
    }

    public void setIdCommandeLiv(String idCommandeLiv) {
        this.idCommandeLiv = idCommandeLiv;
    }

    public List<Historiquelivraison> getAllDetailleLivraison() {
        return allDetailleLivraison;
    }

    public void setAllDetailleLivraison(List<Historiquelivraison> allDetailleLivraison) {
        this.allDetailleLivraison = allDetailleLivraison;
    }

    public Historiquelivraison getHistoriqueLivraison() {
        return historiqueLivraison;
    }

    public void setHistoriqueLivraison(Historiquelivraison historiqueLivraison) {
        this.historiqueLivraison = historiqueLivraison;
    }

    public List<Historiquelivraison> getListLivraisons() {
        return listLivraisons;
    }

    public void setListLivraisons(List<Historiquelivraison> listLivraisons) {
        this.listLivraisons = listLivraisons;
    }

    public Entrepot getEntrepot() {
        return entrepot;
    }

    public void setEntrepot(Entrepot entrepot) {
        this.entrepot = entrepot;
    }

    public List<Commande> getAllCommande() {
        allCommande = ejbCommande.listOfCommandeNonLivrees();
        return allCommande;
    }

    public void setAllCommande(List<Commande> allCommande) {
        this.allCommande = allCommande;
    }

    public String getMyEmtyValues() {
        return myEmtyValues;
    }

    public void setMyEmtyValues(String myEmtyValues) {
        this.myEmtyValues = myEmtyValues;
    }

    public UtilStock getUtilStockFromEdit() {
        return utilStockFromEdit;
    }

    public void setUtilStockFromEdit(UtilStock utilStockFromEdit) {
        this.utilStockFromEdit = utilStockFromEdit;
    }

    public UtilStock getUtilStock() {
        return utilStock;
    }

    public void setUtilStock(UtilStock utilStock) {
        this.utilStock = utilStock;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Stock getStockFromEdit() {
        return stockFromEdit;
    }

    public void setStockFromEdit(Stock stockFromEdit) {
        this.stockFromEdit = stockFromEdit;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public List<UtilStock> getAllStocks() {
        return allStocks;
    }

    public void setAllStocks(List<UtilStock> allStocks) {
        this.allStocks = allStocks;
    }

    public List<Marchandise> getAllMarchandises() {
        allMarchandises = ejbMarchandise.findAll();
        return allMarchandises;
    }

    public void setAllMarchandises(List<Marchandise> allMarchandises) {
        this.allMarchandises = allMarchandises;
    }

    public List<Fournisseur> getAllFournisseurs() {
        return allFournisseurs;
    }

    public void setAllFournisseurs(List<Fournisseur> allFournisseurs) {
        this.allFournisseurs = allFournisseurs;
    }

    public List<Unitemesure> getAllUnitemesures() {
        allUnitemesures = ejbUnitemesure.findAll();
        return allUnitemesures;
    }

    public void setAllUnitemesures(List<Unitemesure> allUnitemesures) {
        this.allUnitemesures = allUnitemesures;
    }

    public List<Commande> getAllCommandes() {
        return allCommandes;
    }

    public void setAllCommandes(List<Commande> allCommandes) {
        this.allCommandes = allCommandes;
    }

    public List<DetailleCommande> getAllDEtailCommandes() {
        return allDEtailCommandes;
    }

    public void setAllDEtailCommandes(List<DetailleCommande> allDEtailCommandes) {
        this.allDEtailCommandes = allDEtailCommandes;
    }

    public DetailleCommande getDetailleCommande() {
        return detailleCommande;
    }

    public void setDetailleCommande(DetailleCommande detailleCommande) {
        this.detailleCommande = detailleCommande;
    }

    public void renderInfoCmd() {

        this.stock.setIdFournisseur(this.stock.getIdCommande().getIdFournisseur());

        this.stock.setIdCommande(this.stock.getIdCommande());

        List<DetailleCommande> listDetailCommandeByCmd = ejbDetailCommande.listOfDetailleCommandeByCommande(this.stock.getIdCommande());

        for (DetailleCommande d : listDetailCommandeByCmd) {
            FactureUtil fu = new FactureUtil();
            fu.setIdDetailleCommande(d.getIdDetailleCommande());
            fu.setDesignation(d.getDesignation());
            fu.setRef(d.getReference());
            fu.setUnite(d.getUnite());
            fu.setQuantite(d.getQuantite());
            fu.setPu(d.getPu());
            fu.setQtLivree(0);
            fu.setQtRestante(d.getQtrestante());
            fu.setAncienQtRestant(d.getQtrestante());
            fu.setExecution(0);
            this.listStocks.add(fu);
        }

    }

    public List<FactureUtil> getListStocks() {
        return listStocks;
    }

    public void setListStocks(List<FactureUtil> listStocks) {
        this.listStocks = listStocks;
    }

    public String save() {
        try {

            user = ejbUtilisateur.find(1);
            this.stock.setIdUtilisateur(user);
            this.stock.setDateLastAppro(this.stock.getDateEntreStock());

            for (FactureUtil f : this.listStocks) {
                Stock myStock = new Stock();
                myStock = ejbStock.findStockByMarchandiseAndStock(f.getDesignation(), this.stock.getIdCommande().getIdEntrepot());
                if (myStock == null) {

                    /*Creation Stock*/
                    this.stock.setQtStock(f.getQtLivree());
                    this.stock.setQtSeuille(f.getQtSeuill());
                    this.stock.setQt(f.getQtLivree());
                    this.stock.setPuStock(f.getPu());
                    this.stock.setPu(f.getPu());
                    this.stock.setDesignation(f.getDesignation());
                    this.stock.setIdEntrepot(this.stock.getIdCommande().getIdEntrepot());
                    ejbStock.insertStock(this.stock);

                    /*Creation historique livraison*/
                    this.historiqueLivraison.setDesignation(f.getDesignation());
                    this.historiqueLivraison.setQtLivree(f.getQtLivree());
                    this.historiqueLivraison.setPu(f.getPu());
                    this.historiqueLivraison.setQtRestante(f.getQtRestante());
                    this.historiqueLivraison.setDateLivraison(this.stock.getDateEntreStock());
                    this.historiqueLivraison.setIdFournisseur(this.stock.getIdFournisseur());
                    this.historiqueLivraison.setIdCommande(this.stock.getIdCommande());
                    this.historiqueLivraison.setIdEntrepot(this.stock.getIdCommande().getIdEntrepot());
                    ejbHistoriqueLivraison.insertLivraison(this.historiqueLivraison);

                } else {

                    /*Creation Stock*/
                    int ancienQt = myStock.getQtStock();
                    int newQt = ancienQt + f.getQtLivree();
                    myStock.setQtStock(newQt);
                    myStock.setDateLastAppro(this.stock.getDateLastAppro());
                    myStock.setPu(f.getPu());
                    ejbStock.edit(myStock);

                    /*Creation historique livraison*/
                    this.historiqueLivraison.setDesignation(f.getDesignation());
                    this.historiqueLivraison.setQtLivree(f.getQtLivree());
                    this.historiqueLivraison.setPu(f.getPu());
                    this.historiqueLivraison.setQtRestante(f.getQtRestante());
                    this.historiqueLivraison.setDateLivraison(this.stock.getDateEntreStock());
                    this.historiqueLivraison.setIdFournisseur(this.stock.getIdFournisseur());
                    this.historiqueLivraison.setIdCommande(this.stock.getIdCommande());
                    this.historiqueLivraison.setIdEntrepot(this.stock.getIdCommande().getIdEntrepot());
                    ejbHistoriqueLivraison.insertLivraison(this.historiqueLivraison);

                }

            }

            int cpt1 = 0;
            int cpt2 = 0;
            for (FactureUtil f : this.listStocks) {
                cpt1 = cpt1 + 1;
                DetailleCommande dcmd = ejbDetailCommande.find(f.getIdDetailleCommande());
                dcmd.setQtlivree(f.getQtLivree());
                dcmd.setQtrestante(f.getQtRestante());

                if (dcmd.getQtrestante() == 0) {
                    cpt2 = cpt2 + 1;
                }

                ejbDetailCommande.edit(dcmd);
            }

            if (cpt1 == cpt2) {
                this.stock.getIdCommande().setLivree("oui");
            } else {
                this.stock.getIdCommande().setLivree("non");
            }
            this.stock.getIdCommande().setPositionLivraison(2);
            ejbCommande.edit(this.stock.getIdCommande());

            return "stock";
        } catch (Exception e) {
            return "new_stock";
        }
    }

    public void rowEdit(RowEditEvent event) {
        MontantConverter mtc = new MontantConverter();
        UtilStock utilStockEdit = (UtilStock) event.getObject();
        Stock anCienStock = ejbStock.find(utilStockEdit.getIdSotk());
        Stock stk = new Stock();

        stk.setIdSotk(utilStockEdit.getIdSotk());
        stk.setDesignation(utilStockEdit.getDesignation());
        stk.setDateLastAppro(utilStockEdit.getDateLastAppro());
        int qts = 0;
        if (utilStockEdit.getQt() != anCienStock.getQt()) {
            qts = montantConverter.CalculeNewQtStock(utilStockEdit.getQtStock(), utilStockEdit.getQt());
            stk.setQtStock(qts);
        } else {
            stk.setQtStock(anCienStock.getQtStock());
        }

        String vpu1 = mtc.DoubleToString(utilStockEdit.getPuStockDouble());
        stk.setPuStock(vpu1);
        stk.setQtSeuille(utilStockEdit.getQtSeuille());
        stk.setDateLastAppro(utilStockEdit.getDateLastAppro());
        stk.setDateEntreStock(utilStockEdit.getDateEntreStock());
        stk.setQt(utilStockEdit.getQt());
        String vpu2 = mtc.DoubleToString(utilStockEdit.getPuDouble());
        stk.setPu(vpu2);
//        stk.setIdMarchandise(utilStockEdit.getMarchandise());
        stk.setIdFournisseur(anCienStock.getIdFournisseur());
//        stk.setIdUnitemesure(utilStockEdit.getUnitemesure());
        stk.setIdCommande(utilStockEdit.getIdCommande());
        stk.setIdUtilisateur(anCienStock.getIdUtilisateur());
        stk.setCommentaire(utilStockEdit.getCommentaire());
        stk.setIdEntrepot(anCienStock.getIdEntrepot());
        ejbStock.edit(stk);
    }

    public void rowEditCancel() {

    }

    public void calculeMtRestant(FactureUtil f) {

        try {

            int index = 0;
            for (int x = 0; x < this.listStocks.size(); x++) {

                FactureUtil f1 = this.listStocks.get(x);
                if (f1.getDesignation().equalsIgnoreCase(f.getDesignation())) {
                    index = x;
                }
            }

            /*int qtr = f.getQuantite() - f.getQtLivree();

             if (qtr > 0) {
             f.setQtRestante(qtr);
             } else {
             f.setQtRestante(0);
             }

             System.out.println("Qtr: " + f.getQtRestante());
             this.listStocks.set(index, f);*/
            if (f.getAncienQtRestant() >= f.getQtLivree()) {
                int qtr = f.getAncienQtRestant() - f.getQtLivree();
                if (qtr > 0) {
                    f.setQtRestante(qtr);
                } else {
                    f.setQtRestante(0);
                }
            } else {
                f.setQtLivree(0);
                f.setQtRestante(f.getAncienQtRestant());
            }

            System.out.println("Qtr: " + f.getQtRestante());
            this.listStocks.set(index, f);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void detail() {
        try {

            System.out.println("Entrepot : " + this.getEntrepot().getIdEntrepot());
            List<Stock> listStk = new ArrayList<>();
            allStocks = new ArrayList<>();
            this.nomEntrepot = "";
            listStk = ejbStock.listOfStockByEntrepot(this.getEntrepot());
            MontantConverter mtc = new MontantConverter();

            for (Stock s : listStk) {
                System.out.println("Valeur : " + s.getIdCommande());
                utilStock = new UtilStock();
                utilStock.setIdSotk(s.getIdSotk());
                utilStock.setDesignation(s.getDesignation());
                utilStock.setFournisseur(s.getIdFournisseur());
                utilStock.setDateEntreStock(s.getDateEntreStock());
                utilStock.setPuStock(s.getPuStock());
                Double mtc1 = mtc.StringToDouble(s.getPuStock());
                utilStock.setPuStockDouble(mtc1);
                utilStock.setQtStock(s.getQtStock());
                String vs = montantConverter.CalculeValeurInventaire(s.getPuStock(), s.getQtStock());
                utilStock.setValeurInventaire(vs);
                utilStock.setQtSeuille(s.getQtSeuille());
                utilStock.setDateLastAppro(s.getDateLastAppro());
                utilStock.setIdCommande(s.getIdCommande());
                utilStock.setUnitemesure(s.getIdUnitemesure());
                utilStock.setUtilisateur(s.getIdUtilisateur());
                utilStock.setNomUser(s.getIdUtilisateur().getNomUtilisateur() + " " + s.getIdUtilisateur().getPrenomUtilisateur());
                utilStock.setPu(s.getPuStock());
                Double mtc2 = mtc.StringToDouble(s.getPu());
                utilStock.setPuDouble(mtc2);
                utilStock.setCommentaire(s.getCommentaire());
                utilStock.setQt(s.getQt());
                utilStock.setPu(s.getPu());

                if (utilStock.getQtStock() <= utilStock.getQtSeuille()) {
                    utilStock.setControlStock("oui");
                } else {
                    utilStock.setControlStock("non");
                }

                System.out.println("Valeur controle:" + utilStock.getControlStock());
                String pt = montantConverter.CalculeValeurInventaire(s.getPu(), s.getQt());
                utilStock.setTotal(pt);
                this.allStocks.add(utilStock);
            }

            this.nomEntrepot = this.entrepot.getNom();
            this.entrepot = new Entrepot();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void renderInfo1() {
        System.out.println("Je suis là 2");
        this.setListLivraisons(ejbHistoriqueLivraison.listOfAllLivraisonByEntrepot(this.getEntrepot()));
    }

    public void detailLivraison() {
        try {

            /* List des detailles devis */
            this.setIdCommandeLiv(this.historiqueLivraison.getIdCommande().getCode());
            this.setAllDetailleLivraison(ejbHistoriqueLivraison.listOfLivraisonByCommande(this.historiqueLivraison.getIdCommande()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
