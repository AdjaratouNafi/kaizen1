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
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;
import sn.accelsolution.dao.ChantierFacade;
import sn.accelsolution.dao.SortiestockFacade;
import sn.accelsolution.dao.StockFacade;
import sn.accelsolution.dao.UtilisateurFacade;
import sn.accelsolution.entities.Actionmenu;
import sn.accelsolution.entities.Marchandise;
import sn.accelsolution.entities.Sortiestock;
import sn.accelsolution.entities.Stock;
import sn.accelsolution.util.UtilControleMenu;

/**
 *
 * @author DELL
 */
@ManagedBean
@ViewScoped
public class SortieStockBean implements Serializable {

    @EJB
    SortiestockFacade ejbSortieStock;
    @EJB
    StockFacade ejbStock;
    @EJB
    UtilisateurFacade ejbUtilisateur;
    @EJB
    ChantierFacade ejbChantier;

    private DataModel item;
    private List listUtilisateurs;
    private List listStocks;
    private List listChantiers;
    private List listSortieStocks;
    private List<Stock> listEntrepots;
    private Sortiestock sortieStock;
    private Sortiestock sortieStockFromEdit;
    private int ancienQuantite;
    private Marchandise marchandise;
    private String textMessage;

    private List<Actionmenu> myllActionmenus;
    private String creerSortieStock;
    private String modifierSortieStock;
    private String supprimerSortieStock;
    private String consulterSortieStock;
    private String imprimerSortieStock;
    private List<Sortiestock> filteredListSortiestock;

    /**
     * Creates a new instance of UtilisateurBean
     */
     @PostConstruct
    public void init() {

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UtilControleMenu utilControleMenu = new UtilControleMenu();
        
        
        myllActionmenus = (List<Actionmenu>) session.getAttribute("allActionmenusFromTraitementSortieStock"); 
        
        this.setCreerSortieStock(utilControleMenu.creerSortieStock(myllActionmenus));
        this.setModifierSortieStock(utilControleMenu.modifierSortieStock(myllActionmenus));
        this.setSupprimerSortieStock(utilControleMenu.supprimerSortieStock(myllActionmenus));
        this.setConsulterSortieStock(utilControleMenu.consulterSortieStock(myllActionmenus));
        
        
        item = new ListDataModel();
        this.setListSortieStocks(ejbSortieStock.findAll());
        item.setWrappedData(this.getListSortieStocks());

    }
    
    public SortieStockBean() {
        sortieStock = new Sortiestock();
        sortieStockFromEdit = new Sortiestock();
        marchandise = new Marchandise();
    }  

    public List<Sortiestock> getFilteredListSortiestock() {
        return filteredListSortiestock;
    }

    public void setFilteredListSortiestock(List<Sortiestock> filteredListSortiestock) {
        this.filteredListSortiestock = filteredListSortiestock;
    }

    public List<Actionmenu> getMyllActionmenus() {
        return myllActionmenus;
    }

    public void setMyllActionmenus(List<Actionmenu> myllActionmenus) {
        this.myllActionmenus = myllActionmenus;
    }

    public String getCreerSortieStock() {
        return creerSortieStock;
    }

    public void setCreerSortieStock(String creerSortieStock) {
        this.creerSortieStock = creerSortieStock;
    }

    public String getModifierSortieStock() {
        return modifierSortieStock;
    }

    public void setModifierSortieStock(String modifierSortieStock) {
        this.modifierSortieStock = modifierSortieStock;
    }

    public String getSupprimerSortieStock() {
        return supprimerSortieStock;
    }

    public void setSupprimerSortieStock(String supprimerSortieStock) {
        this.supprimerSortieStock = supprimerSortieStock;
    }

    public String getConsulterSortieStock() {
        return consulterSortieStock;
    }

    public void setConsulterSortieStock(String consulterSortieStock) {
        this.consulterSortieStock = consulterSortieStock;
    }

    public String getImprimerSortieStock() {
        return imprimerSortieStock;
    }

    public void setImprimerSortieStock(String imprimerSortieStock) {
        this.imprimerSortieStock = imprimerSortieStock;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public List<Stock> getListEntrepots() {
        return listEntrepots;
    }

    public void setListEntrepots(List<Stock> listEntrepots) {
        this.listEntrepots = listEntrepots;
    }

    public Marchandise getMarchandise() {
        return marchandise;
    }

    public void setMarchandise(Marchandise marchandise) {
        this.marchandise = marchandise;
    }

    public List getListChantiers() {
        listChantiers = ejbChantier.findAll();
        return listChantiers;
    }

    public void setListChantiers(List listChantiers) {
        this.listChantiers = listChantiers;
    }

    public int getAncienQuantite() {
        return ancienQuantite;
    }

    public void setAncienQuantite(int ancienQuantite) {
        this.ancienQuantite = ancienQuantite;
    }

    public DataModel getItem() {
        
        return item;
    }

    public void setItem(DataModel item) {
        this.item = item;
    }

    public List getListUtilisateurs() {
        listUtilisateurs = ejbUtilisateur.findAll();
        return listUtilisateurs;
    }

    public void setListUtilisateurs(List listUtilisateurs) {
        this.listUtilisateurs = listUtilisateurs;
    }

    public List getListStocks() {
        listStocks = ejbStock.findAll();
        return listStocks;
    }

    public void setListStocks(List listStocks) {
        this.listStocks = listStocks;
    }

    public Sortiestock getSortieStock() {
        return sortieStock;
    }

    public void setSortieStock(Sortiestock sortieStock) {
        this.sortieStock = sortieStock;
    }

    public Sortiestock getSortieStockFromEdit() {
        return sortieStockFromEdit;
    }

    public void setSortieStockFromEdit(Sortiestock sortieStockFromEdit) {
        this.sortieStockFromEdit = sortieStockFromEdit;
    }

    public List getListSortieStocks() {
        listSortieStocks = ejbSortieStock.findAll();
        return listSortieStocks;
    }

    public void setListSortieStocks(List listSortieStocks) {
        this.listSortieStocks = listSortieStocks;
    }

    public String save() {
        try {

            if (this.sortieStock.getQuantite() > this.sortieStock.getIdStock().getQtStock()) {

                this.setTextMessage("La quantité entrée est superieure à la quantité disponible");
                return "new_sortieStock";

            } else {

                String pu = this.sortieStock.getIdStock().getPuStock();
                this.sortieStock.setPu(pu);
                this.sortieStock.setIdEntrepot(this.sortieStock.getIdStock().getIdEntrepot());
                ejbSortieStock.insertSortie(this.sortieStock);
                int ancienqt = this.sortieStock.getIdStock().getQtStock();
                int newQtStock = ancienqt - this.sortieStock.getQuantite();
                this.sortieStock.getIdStock().setQtStock(newQtStock);
                ejbStock.edit(this.sortieStock.getIdStock());
                return "sortieStocks";
            }

        } catch (Exception e) {
            return "new_sortieStock";
        }
    }

    public String reset() {
        this.sortieStock = new Sortiestock();
        return "new_sortieStock";
    }

    public String resetModif() {
        return "sortieStocks";
    }

    public String editer() {
        try {
            this.sortieStockFromEdit = (Sortiestock) item.getRowData();
            this.ancienQuantite = this.sortieStockFromEdit.getQuantite();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "edit_sortieStock";
    }

    public String update() {
        try {
            int ancienqtActu = this.sortieStockFromEdit.getIdStock().getQtStock();
            int ancienqt = this.ancienQuantite;
            int valAncienQt = ancienqtActu + ancienqt;
            int newQtStock = valAncienQt - this.sortieStockFromEdit.getQuantite();
            String pu = this.sortieStockFromEdit.getIdStock().getPuStock();
            this.sortieStockFromEdit.setPu(pu);
            ejbSortieStock.edit(this.sortieStockFromEdit);

            this.sortieStockFromEdit.getIdStock().setQtStock(newQtStock);
            ejbStock.edit(this.sortieStockFromEdit.getIdStock());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "sortieStocks";
    }

    public String supprimer() {
        try {

            this.sortieStockFromEdit = (Sortiestock) item.getRowData();
            int ancienqtActu = this.sortieStockFromEdit.getIdStock().getQtStock();
            int newQtStock = ancienqtActu + this.sortieStockFromEdit.getQuantite();
            this.sortieStockFromEdit.getIdStock().setQtStock(newQtStock);

            ejbSortieStock.remove(this.sortieStockFromEdit);

            ejbStock.edit(this.sortieStockFromEdit.getIdStock());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "sortieStocks";
    }

    public void renderInfo() {
        try {
            this.listEntrepots = ejbStock.listOfStockByMarchandise(this.marchandise);
        } catch (Exception e) {
        }
    }

    public void renderInfoo() {
        try {
            this.sortieStockFromEdit.setIdStock(this.sortieStockFromEdit.getIdStock());
            this.sortieStockFromEdit.setPu(this.sortieStockFromEdit.getIdStock().getPuStock());
        } catch (Exception e) {
        }
    }

    public void renderInfo2() {
        try {

            if (this.sortieStock.getQuantite() > this.sortieStock.getIdStock().getQtStock()) {
                this.setTextMessage("La quantité entrée est superieure à la quantité disponible");

            } else {
                this.setTextMessage("");
            }
        } catch (Exception e) {
        }
    }

}
