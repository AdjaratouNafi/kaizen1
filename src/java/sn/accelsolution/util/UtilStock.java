/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.util;

import sn.accelsolution.entities.Commande;
import sn.accelsolution.entities.Fournisseur;
import sn.accelsolution.entities.Marchandise;
import sn.accelsolution.entities.Unitemesure;
import sn.accelsolution.entities.Utilisateur;

/**
 *
 * @author DV7
 */

public class UtilStock {
    private int idSotk;
    private Marchandise marchandise;
    private Fournisseur fournisseur;
    private String designation;
    private String dateEntreStock;
    private int qtStock;
    private String puStock;
    private Double puStockDouble;
    private int qtSeuille;
    private String dateLastAppro;
    private String commentaire;
    private String valeurInventaire;
    private String enDessusDessous;
    private String commande;
    private String codeCommande;
    private Commande idCommande;
    private Unitemesure unitemesure;
    private Utilisateur utilisateur;
    private String nomUser;
    private String pu;
    private Double puDouble;
    private int qt;
    private String total;
    private String controlStock;
    
    public UtilStock(){
        
    }

    public String getControlStock() {
        return controlStock;
    }

    public void setControlStock(String controlStock) {
        this.controlStock = controlStock;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Unitemesure getUnitemesure() {
        return unitemesure;
    }

    public void setUnitemesure(Unitemesure unitemesure) {
        this.unitemesure = unitemesure;
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public Commande getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Commande idCommande) {
        this.idCommande = idCommande;
    }
    

    public int getIdSotk() {
        return idSotk;
    }

    public void setIdSotk(int idSotk) {
        this.idSotk = idSotk;
    }

    public String getDateEntreStock() {
        return dateEntreStock;
    }

    public void setDateEntreStock(String dateEntreStock) {
        this.dateEntreStock = dateEntreStock;
    }

    public int getQtStock() {
        return qtStock;
    }

    public void setQtStock(int qtStock) {
        this.qtStock = qtStock;
    }

    public String getPuStock() {
        return puStock;
    }

    public void setPuStock(String puStock) {
        this.puStock = puStock;
    }

    public int getQtSeuille() {
        return qtSeuille;
    }

    public void setQtSeuille(int qtSeuille) {
        this.qtSeuille = qtSeuille;
    }

    public String getDateLastAppro() {
        return dateLastAppro;
    }

    public void setDateLastAppro(String dateLastAppro) {
        this.dateLastAppro = dateLastAppro;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getValeurInventaire() {
        return valeurInventaire;
    }

    public void setValeurInventaire(String valeurInventaire) {
        this.valeurInventaire = valeurInventaire;
    }

    public String getEnDessusDessous() {
        return enDessusDessous;
    }

    public void setEnDessusDessous(String enDessusDessous) {
        this.enDessusDessous = enDessusDessous;
    }

    public String getCommande() {
        return commande;
    }

    public void setCommande(String commande) {
        this.commande = commande;
    }

    public String getCodeCommande() {
        return codeCommande;
    }

    public void setCodeCommande(String codeCommande) {
        this.codeCommande = codeCommande;
    }

    public String getPu() {
        return pu;
    }

    public void setPu(String pu) {
        this.pu = pu;
    }

    public int getQt() {
        return qt;
    }

    public void setQt(int qt) {
        this.qt = qt;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Marchandise getMarchandise() {
        return marchandise;
    }

    public void setMarchandise(Marchandise marchandise) {
        this.marchandise = marchandise;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Double getPuStockDouble() {
        return puStockDouble;
    }

    public void setPuStockDouble(Double puStockDouble) {
        this.puStockDouble = puStockDouble;
    }

    public Double getPuDouble() {
        return puDouble;
    }

    public void setPuDouble(Double puDouble) {
        this.puDouble = puDouble;
    }
    

    @Override
    public String toString() {
        return "UtilStock{" + "idSotk=" + idSotk + ", marchandise=" + marchandise + ", fournisseur=" + fournisseur + ", dateEntreStock=" + dateEntreStock + ", qtStock=" + qtStock + ", puStock=" + puStock + ", qtSeuille=" + qtSeuille + ", dateLastAppro=" + dateLastAppro + ", commentaire=" + commentaire + ", valeurInventaire=" + valeurInventaire + ", enDessusDessous=" + enDessusDessous + ", commande=" + commande + ", codeCommande=" + codeCommande + ", idCommande=" + idCommande + ", nomUser=" + nomUser + ", pu=" + pu + ", qt=" + qt + ", total=" + total + '}';
    }
    
    
}
