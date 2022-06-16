/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.util;

import sn.accelsolution.entities.Commande;
import sn.accelsolution.entities.Fournisseur;
import sn.accelsolution.entities.Reffournisseur;

/**
 *
 * @author DV7
 */
public class FournisseurUtil {

    private Integer idRefFournisseur;
    private String ninea;
    private String nomcomplet;
    private String telephone;
    private Double accord;
    private String accordAffichage;
    private String nature;
    private Double accompte;
    private String accompteAffichage;
    private Double reliquant;
    private String reliquantAffichage;
    private String voyant;
    private String echenance;
    private Commande idCommande;
    private Fournisseur Fournisseur;

    public FournisseurUtil() {

    }

    public Integer getIdRefFournisseur() {
        return idRefFournisseur;
    }

    public void setIdRefFournisseur(Integer idRefFournisseur) {
        this.idRefFournisseur = idRefFournisseur;
    }

    public Fournisseur getFournisseur() { 
        return Fournisseur;
    }

    public void setFournisseur(Fournisseur Fournisseur) {
        this.Fournisseur = Fournisseur;
    }

    public String getNinea() {
        return ninea;
    }

    public void setNinea(String ninea) {
        this.ninea = ninea;
    }

    public String getNomcomplet() {
        return nomcomplet;
    }

    public void setNomcomplet(String nomcomplet) {
        this.nomcomplet = nomcomplet;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Double getAccord() {
        return accord;
    }

    public void setAccord(Double accord) {
        this.accord = accord;
    }

    public String getAccordAffichage() {
        return accordAffichage;
    }

    public void setAccordAffichage(String accordAffichage) {
        this.accordAffichage = accordAffichage;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public Double getAccompte() {
        return accompte;
    }

    public void setAccompte(Double accompte) {
        this.accompte = accompte;
    }

    public String getAccompteAffichage() {
        return accompteAffichage;
    }

    public void setAccompteAffichage(String accompteAffichage) {
        this.accompteAffichage = accompteAffichage;
    }

    public Double getReliquant() {
        return reliquant;
    }

    public void setReliquant(Double reliquant) {
        this.reliquant = reliquant;
    }

    public String getReliquantAffichage() {
        return reliquantAffichage;
    }

    public void setReliquantAffichage(String reliquantAffichage) {
        this.reliquantAffichage = reliquantAffichage;
    }

    public String getVoyant() {
        return voyant;
    }

    public void setVoyant(String voyant) {
        this.voyant = voyant;
    }

    public String getEchenance() {
        return echenance;
    }

    public void setEchenance(String echenance) {
        this.echenance = echenance;
    }

    public Commande getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Commande idCommande) {
        this.idCommande = idCommande;
    }
    

}
