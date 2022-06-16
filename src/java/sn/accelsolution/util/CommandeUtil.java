/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.util;

import sn.accelsolution.entities.DetailleCommande;
import sn.accelsolution.entities.Marchandise;

/**
 *
 * @author DV7
 */
public class CommandeUtil {
    private String idCommande;
    private DetailleCommande detailleCommande;
    private Marchandise marchandise;
    private String unite;
    private int quantite;
    
    public CommandeUtil(){
        
    }

    public String getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(String idCommande) {
        this.idCommande = idCommande;
    }

    public DetailleCommande getDetailleCommande() {
        return detailleCommande;
    }

    public void setDetailleCommande(DetailleCommande detailleCommande) {
        this.detailleCommande = detailleCommande;
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

    public Marchandise getMarchandise() {
        return marchandise;
    }

    public void setMarchandise(Marchandise marchandise) {
        this.marchandise = marchandise;
    }
    
}
