/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.util;

import java.util.ArrayList;
import java.util.List;
import sn.accelsolution.entities.Deboursser;
import sn.accelsolution.entities.Entrepot;
import sn.accelsolution.entities.Fournisseur;
import sn.accelsolution.entities.Prix;
import sn.accelsolution.entities.Stock;

/**
 *
 * @author DV7
 */
public class FactureUtil {
    private int idLigne;
    private String designation;
    private String typeitem;
    private String ref;
    private String unite;
    private int quantite;
    private String puSansCoef;
    private String pu;
    private Double  puMontant;
    private String prixtotal;
    private int execution;
    private int qtLivree;
    private int qtRestante;
    private int qtSeuill;
    private int idDetailleCommande;
    private int idEntrepot;
    private int ancienQtRestant;
    private List<UtilPrix> listPrix;
    private Fournisseur fournisseur;
    private String contact;
    private int qtEbDemande;
    private String dispoEbStock;
    private int qtDispoStock;
    private int qtEbValide;
    private Entrepot entrepot;
    private String nomEntrepot;
    private Stock stock;
    private Deboursser deboursser;
    private String stockOuDs;
    
    public FactureUtil(){
        
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
    

    public String getPuSansCoef() {
        return puSansCoef;
    }

    public void setPuSansCoef(String puSansCoef) {
        this.puSansCoef = puSansCoef;
    }

    public int getIdDetailleCommande() {
        return idDetailleCommande;
    }

    public void setIdDetailleCommande(int idDetailleCommande) {
        this.idDetailleCommande = idDetailleCommande;
    }

    public int getQtLivree() {
        return qtLivree;
    }

    public void setQtLivree(int qtLivree) {
        this.qtLivree = qtLivree;
    }

    public int getIdLigne() {
        return idLigne;
    }

    public void setIdLigne(int idLigne) {
        this.idLigne = idLigne;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    } 

    public String getTypeitem() {
        return typeitem;
    }

    public void setTypeitem(String typeitem) {
        this.typeitem = typeitem;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }
    

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getPu() {
        return pu;
    }

    public void setPu(String pu) {
        this.pu = pu;
    }

    public String getPrixtotal() {
        return prixtotal;
    }

    public void setPrixtotal(String prixtotal) {
        this.prixtotal = prixtotal;
    }

    public int getExecution() {
        return execution;
    }

    public void setExecution(int execution) {
        this.execution = execution;
    }

    public Double getPuMontant() {
        return puMontant;
    }

    public void setPuMontant(Double puMontant) {
        this.puMontant = puMontant;
    }

    public int getQtSeuill() {
        return qtSeuill;
    }

    public void setQtSeuill(int qtSeuill) {
        this.qtSeuill = qtSeuill;
    }

    public int getQtRestante() {
        return qtRestante;
    }

    public void setQtRestante(int qtRestante) {
        this.qtRestante = qtRestante;
    }

    public int getIdEntrepot() {
        return idEntrepot;
    }

    public void setIdEntrepot(int idEntrepot) {
        this.idEntrepot = idEntrepot;
    }

    public int getAncienQtRestant() {
        return ancienQtRestant;
    }

    public void setAncienQtRestant(int ancienQtRestant) {
        this.ancienQtRestant = ancienQtRestant;
    } 

    public List<UtilPrix> getListPrix() {
        return listPrix;
    }

    public void setListPrix(List<UtilPrix> listPrix) {
        this.listPrix = listPrix;
    }
    
    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    } 
    
    public List<FactureUtil> SearchCommandeByFournisseur(List<FactureUtil> listCommandes, Fournisseur fournisseur) {

        List<FactureUtil> listOfCommandeFournisseur = new ArrayList<>();

        for (int x = 0; x < listCommandes.size(); x++) {
            FactureUtil up = new FactureUtil();
            up = listCommandes.get(x);
            if (up.getFournisseur().getIdFournisseur() == fournisseur.getIdFournisseur()) {
                listOfCommandeFournisseur.add(up);
            }
        }

        return listOfCommandeFournisseur;
    } 

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    } 

    public int getQtEbDemande() {
        return qtEbDemande;
    }

    public void setQtEbDemande(int qtEbDemande) {
        this.qtEbDemande = qtEbDemande;
    }

    public int getQtEbValide() {
        return qtEbValide;
    }

    public void setQtEbValide(int qtEbValide) {
        this.qtEbValide = qtEbValide;
    }

    public String getDispoEbStock() {
        return dispoEbStock;
    }

    public void setDispoEbStock(String dispoEbStock) {
        this.dispoEbStock = dispoEbStock;
    }

    public int getQtDispoStock() {
        return qtDispoStock;
    }

    public void setQtDispoStock(int qtDispoStock) {
        this.qtDispoStock = qtDispoStock;
    }

    public Entrepot getEntrepot() {
        return entrepot;
    }

    public void setEntrepot(Entrepot entrepot) {
        this.entrepot = entrepot;
    }

    public String getNomEntrepot() {
        return nomEntrepot;
    }

    public void setNomEntrepot(String nomEntrepot) {
        this.nomEntrepot = nomEntrepot;
    }

    public String getStockOuDs() {
        return stockOuDs;
    }

    public void setStockOuDs(String stockOuDs) {
        this.stockOuDs = stockOuDs;
    } 

    public Deboursser getDeboursser() {
        return deboursser;
    }

    public void setDeboursser(Deboursser deboursser) {
        this.deboursser = deboursser;
    }

    @Override
    public String toString() {
        return "FactureUtil{" + "idLigne=" + idLigne + ", designation=" + designation + ", ref=" + ref + ", unite=" + unite + ", quantite=" + quantite + ", puSansCoef=" + puSansCoef + ", pu=" + pu + ", puMontant=" + puMontant + ", prixtotal=" + prixtotal + ", execution=" + execution + ", qtLivree=" + qtLivree + ", qtRestante=" + qtRestante + ", qtSeuill=" + qtSeuill + ", idDetailleCommande=" + idDetailleCommande + ", idEntrepot=" + idEntrepot + ", ancienQtRestant=" + ancienQtRestant + ", listPrix=" + listPrix + ", fournisseur=" + fournisseur + ", contact=" + contact + ", qtEbDemande=" + qtEbDemande + ", dispoEbStock=" + dispoEbStock + ", qtDispoStock=" + qtDispoStock + ", qtEbValide=" + qtEbValide + ", entrepot=" + entrepot + ", nomEntrepot=" + nomEntrepot + ", stock=" + stock + '}';
    }
    
}
