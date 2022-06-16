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
import javax.faces.bean.RequestScoped;
import sn.accelsolution.dao.CommandeFacade;
import sn.accelsolution.dao.LivraisonFacade;
import sn.accelsolution.dao.UtilisateurFacade;
import sn.accelsolution.entities.Livraison;

/**
 *
 * @author DELL
 */
@ManagedBean
@RequestScoped
public class LivraisonOtherBean implements Serializable {

    /**
     * Creates a new instance of ContratRecrutBean
     */
    @EJB
    UtilisateurFacade ejbUser;
    @EJB
    CommandeFacade ejbCommande;
    @EJB
    LivraisonFacade ejbLivraison;
    
    
    private List<Livraison> allDetailleLivraison;
    private String dateLiv;
    private String fournisseurLiv;
    private String idCommandeLiv;

    @PostConstruct
    public void init() {
      
    }

    public LivraisonOtherBean() {
        allDetailleLivraison = new ArrayList<>();
    }

    public String getDateLiv() {
        return dateLiv;
    }

    public void setDateLiv(String dateLiv) {
        this.dateLiv = dateLiv;
    }

    public String getFournisseurLiv() {
        return fournisseurLiv;
    }

    public void setFournisseurLiv(String fournisseurLiv) {
        this.fournisseurLiv = fournisseurLiv;
    }

    public String getIdCommandeLiv() {
        return idCommandeLiv;
    }

    public void setIdCommandeLiv(String idCommandeLiv) {
        this.idCommandeLiv = idCommandeLiv;
    }
    

    public List<Livraison> getAllDetailleLivraison() {
        return allDetailleLivraison;
    }

    public void setAllDetailleLivraison(List<Livraison> allDetailleLivraison) {
        this.allDetailleLivraison = allDetailleLivraison;
    }
    

    public String detail(Livraison l) {
        try {

            /* List des detailles devis */
            this.setDateLiv(l.getDateLivraison());
            this.setFournisseurLiv(l.getIdFournisseur().getNomFournisseur());
            this.setIdCommandeLiv(l.getIdCommande().getCode());
            this.setAllDetailleLivraison(ejbLivraison.listOfLivraisonByCommande(l.getIdCommande()));
           

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "detail_livraison";
    }

    

}
