/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.util;

import sn.accelsolution.entities.Marche;

/**
 *
 * @author DV7
 */
public class UtilCommandePrim {
    private int idCommande;
    private String code;
    private String dateecheance;
    private Marche idMarcher;
    
    public UtilCommandePrim(){
        
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDateecheance() {
        return dateecheance;
    }

    public void setDateecheance(String dateecheance) {
        this.dateecheance = dateecheance;
    }

    public Marche getIdMarcher() {
        return idMarcher;
    }

    public void setIdMarcher(Marche idMarcher) {
        this.idMarcher = idMarcher;
    }
    
    
}
