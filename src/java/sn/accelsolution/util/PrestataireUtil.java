/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.util;

import sn.accelsolution.entities.Chantier;
import sn.accelsolution.entities.Prestataireprim;

/**
 *
 * @author DV7
 */
public class PrestataireUtil {
    private Integer idPrestataire;
    private String ninea;
    private String nomcomplet;
    private String corps;
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
    private Chantier idChantier;
    private Prestataireprim prestatairePrim;
    
    public PrestataireUtil(){
        
    }

    public String getNinea() {
        return ninea;
    }

    public void setNinea(String ninea) {
        this.ninea = ninea;
    }

    public String getAccordAffichage() {
        return accordAffichage;
    }

    public void setAccordAffichage(String accordAffichage) {
        this.accordAffichage = accordAffichage;
    }

    public String getAccompteAffichage() {
        return accompteAffichage;
    }

    public void setAccompteAffichage(String accompteAffichage) {
        this.accompteAffichage = accompteAffichage;
    }

    public String getReliquantAffichage() {
        return reliquantAffichage;
    }

    public void setReliquantAffichage(String reliquantAffichage) {
        this.reliquantAffichage = reliquantAffichage;
    }
    

    public Integer getIdPrestataire() {
        return idPrestataire;
    }

    public void setIdPrestataire(Integer idPrestataire) {
        this.idPrestataire = idPrestataire;
    }

    public String getNomcomplet() {
        return nomcomplet;
    }

    public void setNomcomplet(String nomcomplet) {
        this.nomcomplet = nomcomplet;
    }

    public String getCorps() {
        return corps;
    }

    public void setCorps(String corps) {
        this.corps = corps;
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

    public Double getReliquant() {
        return reliquant;
    }

    public void setReliquant(Double reliquant) {
        this.reliquant = reliquant;
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

    public Chantier getIdChantier() {
        return idChantier;
    }

    public void setIdChantier(Chantier idChantier) {
        this.idChantier = idChantier;
    }

    public Prestataireprim getPrestatairePrim() {
        return prestatairePrim;
    }

    public void setPrestatairePrim(Prestataireprim prestatairePrim) {
        this.prestatairePrim = prestatairePrim;
    }
    
}
