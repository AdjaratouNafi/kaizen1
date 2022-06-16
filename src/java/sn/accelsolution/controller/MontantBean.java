/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import sn.accelsolution.dao.ChantierFacade;
import sn.accelsolution.dao.MarcheFacade;
import sn.accelsolution.entities.Chantier;
import sn.accelsolution.entities.Marche;
import sn.accelsolution.util.MontantConverter;

/**
 *
 * @author DELL
 */
@ManagedBean
@ViewScoped
public class MontantBean implements Serializable {

    /**
     * Creates a new instance of ContratRecrutBean
     */
    @EJB
    MarcheFacade ejbmarche;
    @EJB
    ChantierFacade ejbchantier;

    private String stringNumber;
    private Double myNumber;

    private List<Marche> listMarches;
    private List<Chantier> listChantiers;
    private Marche marche;
    private Chantier chantier;

    public MontantBean() {
        marche = new Marche();
        chantier = new Chantier();
        listMarches = new ArrayList<>();
        listChantiers = new ArrayList<>();
    }

    public Marche getMarche() {
        return marche;
    }

    public void setMarche(Marche marche) {
        this.marche = marche;
    }

    public Chantier getChantier() {
        return chantier;
    }

    public void setChantier(Chantier chantier) {
        this.chantier = chantier;
    }

    public List<Marche> getListMarches() {
        listMarches = ejbmarche.findAll();
        return listMarches;
    }

    public void setListMarches(List<Marche> listMarches) {
        this.listMarches = listMarches;
    }

    public List<Chantier> getListChantiers() {
        return listChantiers;
    }

    public void setListChantiers(List<Chantier> listChantiers) {
        this.listChantiers = listChantiers;
    }

    public Double getMyNumber() {
        return myNumber;
    }

    public void setMyNumber(Double myNumber) {
        this.myNumber = myNumber;
    }

    public String getStringNumber() {
        return stringNumber;
    }

    public void setStringNumber(String stringNumber) {
        this.stringNumber = stringNumber;
    }

    public String save() {
//        System.out.println("Valeur string saisie:"+this.getStringNumber());
//        MontantConverter mtc = new MontantConverter();
//        Double rstd = mtc.StringToDouble(this.getStringNumber());
//        System.out.println("Resultat en double: "+rstd);
//        String rsts = mtc.DoubleToString(rstd);
//        System.out.println("Resultat en string: "+rsts);
        System.out.println("Montant saisie:" + this.getMyNumber());
        MontantConverter mtc = new MontantConverter();
        String rsts = mtc.DoubleToString(this.getMyNumber());
        System.out.println("Resultat en string: " + rsts);
        return "new_converter";
    }
    
    
    public void renderinfo() {
        
        try {
            System.out.println("Je suis là !!!");
            this.setListChantiers(ejbchantier.findAll());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String savee() {

        return "new_converter";
    }

    public String reset() {

        return "new_converter";
    }

}
