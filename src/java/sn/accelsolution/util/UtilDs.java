/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.util;

import sn.accelsolution.entities.Nivodeboursser;

/**
 *
 * @author DV7
 */
public class UtilDs {
     private int idMouvementDeboursserSec;
     private String nivodeboursser;
     private String designation;
     private int qt;
     private String dateMouvDeboursser;
     private String chantier;
     private String unite;
     private String conso;
     private String taux;
     
     public UtilDs(){
         
     }

    public int getIdMouvementDeboursserSec() {
        return idMouvementDeboursserSec;
    }

    public void setIdMouvementDeboursserSec(int idMouvementDeboursserSec) {
        this.idMouvementDeboursserSec = idMouvementDeboursserSec;
    }

    public String getNivodeboursser() {
        return nivodeboursser;
    }

    public void setNivodeboursser(String nivodeboursser) {
        this.nivodeboursser = nivodeboursser;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getQt() {
        return qt;
    }

    public void setQt(int qt) {
        this.qt = qt;
    }

    public String getDateMouvDeboursser() {
        return dateMouvDeboursser;
    }

    public void setDateMouvDeboursser(String dateMouvDeboursser) {
        this.dateMouvDeboursser = dateMouvDeboursser;
    }

    public String getChantier() {
        return chantier;
    }

    public void setChantier(String chantier) {
        this.chantier = chantier;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public String getConso() {
        return conso;
    }

    public void setConso(String conso) {
        this.conso = conso;
    }

    public String getTaux() {
        return taux;
    }

    public void setTaux(String taux) {
        this.taux = taux;
    }

    @Override
    public String toString() {
        return "UtilDs{" + "idMouvementDeboursserSec=" + idMouvementDeboursserSec + ", nivodeboursser=" + nivodeboursser + ", designation=" + designation + ", qt=" + qt + ", dateMouvDeboursser=" + dateMouvDeboursser + ", chantier=" + chantier + ", unite=" + unite + ", conso=" + conso + ", taux=" + taux + '}';
    }
    
    
}
