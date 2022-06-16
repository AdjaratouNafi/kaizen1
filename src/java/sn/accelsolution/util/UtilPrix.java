/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.util;

import java.util.ArrayList;
import java.util.List;
import sn.accelsolution.entities.Fournisseur;
import sn.accelsolution.entities.Marchandise;

/**
 *
 * @author DV7
 */
public class UtilPrix {

    private String prix;
    private int quantite;
    private String remise;
    private String montant;
    private String montanthortaxe;
    private String libelleMarchandise;
    private String ttc;

    private int idPrix;
    private Marchandise idMarchandise;
    private Fournisseur idFournisseur;
    private Double doublePrix;

    public UtilPrix() {

    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getRemise() {
        return remise;
    }

    public void setRemise(String remise) {
        this.remise = remise;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }

    public String getMontanthortaxe() {
        return montanthortaxe;
    }

    public void setMontanthortaxe(String montanthortaxe) {
        this.montanthortaxe = montanthortaxe;
    }

    public String getLibelleMarchandise() {
        return libelleMarchandise;
    }

    public void setLibelleMarchandise(String libelleMarchandise) {
        this.libelleMarchandise = libelleMarchandise;
    }

    public String getTtc() {
        return ttc;
    }

    public void setTtc(String ttc) {
        this.ttc = ttc;
    }

    public int getIdPrix() {
        return idPrix;
    }

    public void setIdPrix(int idPrix) {
        this.idPrix = idPrix;
    }

    public Marchandise getIdMarchandise() {
        return idMarchandise;
    }

    public void setIdMarchandise(Marchandise idMarchandise) {
        this.idMarchandise = idMarchandise;
    }

    public Fournisseur getIdFournisseur() {
        return idFournisseur;
    }

    public void setIdFournisseur(Fournisseur idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    public Double getDoublePrix() {
        return doublePrix;
    }

    public void setDoublePrix(Double doublePrix) {
        this.doublePrix = doublePrix;
    }

    public List<UtilPrix> SearchPrixByFournisseur(List<UtilPrix> listPrix, Fournisseur fournisseur) {

        List<UtilPrix> listOfCommandeFournisseur = new ArrayList<>();

        for (int x = 0; x < listPrix.size(); x++) {
            UtilPrix up = new UtilPrix();
            up = listPrix.get(x);
            if (up.getIdFournisseur().getIdFournisseur() == fournisseur.getIdFournisseur()) {
                listOfCommandeFournisseur.add(up);
            }
        }

        return listOfCommandeFournisseur;
    }

    public List<UtilPrix> RemovePrixByFournisseur(List<UtilPrix> listPrix, Fournisseur fournisseur) {

        for (int x = 0; x < listPrix.size(); x++) {
            UtilPrix up = new UtilPrix();
            up = listPrix.get(x);
            if (up.getIdFournisseur().getIdFournisseur() == fournisseur.getIdFournisseur()) {
                listPrix.remove(x);
            }
        }

        return listPrix;
    }

}
