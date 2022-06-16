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
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;
import sn.accelsolution.dao.BailleurFacade;
import sn.accelsolution.dao.FournisseurFacade;
import sn.accelsolution.dao.VenteFacade;
import sn.accelsolution.entities.Actionmenu;
import sn.accelsolution.entities.Bailleur;
import sn.accelsolution.entities.Fournisseur;
import sn.accelsolution.entities.Vente;
import sn.accelsolution.util.UtilControleMenu;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DELL
 */
@ManagedBean
@RequestScoped
public class FournisseurBean implements Serializable {

    @EJB
    FournisseurFacade ejbfournisseur;
    @EJB
    VenteFacade ejbvente;

    private DataModel item;
    private DataModel itemVentes;
    private List listFournisseur;
    private List listVente;
    private Fournisseur fournisseur;
    private Fournisseur fournisseurFromEdit;
    private Vente vente;
    private List<Actionmenu> myllActionmenus;
    private String creerFournisseur;
    private String modifierFournisseur;
    private String supprimerFournisseur;
    private String consulterFournisseur;
    private String imprimerFournisseur;
    private List<Fournisseur> filteredListFournisseur;

    /**
     * Creates a new instance of UtilisateurBean
     */
    @PostConstruct
    public void init() {

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UtilControleMenu utilControleMenu = new UtilControleMenu();

        myllActionmenus = (List<Actionmenu>) session.getAttribute("allActionmenusFromTraitementFournisseurs");

        //this.setCreerFournisseur(utilControleMenu.creerFournisseur(myllActionmenus));
        this.setCreerFournisseur("yes");
        //this.setModifierFournisseur(utilControleMenu.modifierFournisseur(myllActionmenus));
        this.setModifierFournisseur("yes");
        //this.setSupprimerFournisseur(utilControleMenu.supprimerFournisseur(myllActionmenus));
        this.setSupprimerFournisseur("yes");
        //this.setConsulterFournisseur(utilControleMenu.consulterFournisseur(myllActionmenus));
        this.setConsulterFournisseur("yes");

        item = new ListDataModel();
        List<Fournisseur> listAllFour = ejbfournisseur.findAll();
        List<Fournisseur> listAllFourToAdd = new ArrayList<>();
        listAllFourToAdd.clear();
        //UtilUtfconvert utfconvert = new UtilUtfconvert();
        for (Fournisseur fssr : listAllFour) {
            listAllFourToAdd.add(fssr);
        }
        this.setListFournisseur(listAllFourToAdd);
        item.setWrappedData(this.getListFournisseur());

    }

    public FournisseurBean() {
        fournisseur = new Fournisseur();
        fournisseurFromEdit = new Fournisseur();
        vente = new Vente();
    }

    public List<Fournisseur> getFilteredListFournisseur() {
        return filteredListFournisseur;
    }

    public void setFilteredListFournisseur(List<Fournisseur> filteredListFournisseur) {
        this.filteredListFournisseur = filteredListFournisseur;
    }

    public List<Actionmenu> getMyllActionmenus() {
        return myllActionmenus;
    }

    public void setMyllActionmenus(List<Actionmenu> myllActionmenus) {
        this.myllActionmenus = myllActionmenus;
    }

    public String getCreerFournisseur() {
        return creerFournisseur;
    }

    public void setCreerFournisseur(String creerFournisseur) {
        this.creerFournisseur = creerFournisseur;
    }

    public String getModifierFournisseur() {
        return modifierFournisseur;
    }

    public void setModifierFournisseur(String modifierFournisseur) {
        this.modifierFournisseur = modifierFournisseur;
    }

    public String getSupprimerFournisseur() {
        return supprimerFournisseur;
    }

    public void setSupprimerFournisseur(String supprimerFournisseur) {
        this.supprimerFournisseur = supprimerFournisseur;
    }

    public String getConsulterFournisseur() {
        return consulterFournisseur;
    }

    public void setConsulterFournisseur(String consulterFournisseur) {
        this.consulterFournisseur = consulterFournisseur;
    }

    public String getImprimerFournisseur() {
        return imprimerFournisseur;
    }

    public void setImprimerFournisseur(String imprimerFournisseur) {
        this.imprimerFournisseur = imprimerFournisseur;
    }

    public DataModel getItem() {
        return item;
    }

    public void setItem(DataModel item) {
        this.item = item;
    }

    public DataModel getItemVentes() {
        return itemVentes;
    }

    public void setItemVentes(DataModel itemVentes) {
        this.itemVentes = itemVentes;
    }

    public List getListFournisseur() {
        listFournisseur = ejbfournisseur.findAll();
        return listFournisseur;
    }

    public void setListFournisseur(List listFournisseur) {
        this.listFournisseur = listFournisseur;
    }

    public List getListVente() {
        return listVente;
    }

    public void setListVente(List listVente) {
        this.listVente = listVente;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public Fournisseur getFournisseurFromEdit() {
        return fournisseurFromEdit;
    }

    public void setFournisseurFromEdit(Fournisseur fournisseurFromEdit) {
        this.fournisseurFromEdit = fournisseurFromEdit;
    }

    public Vente getVente() {
        return vente;
    }

    public void setVente(Vente vente) {
        this.vente = vente;
    }

    public String save() {
        try {
            ejbfournisseur.insertFournisseur(this.fournisseur);
            return "fournisseurs";
        } catch (Exception e) {
            return "new_fournisseur";
        }

    }

    public String saveVente() {
        try {
            ejbvente.insertVente(vente);
            return "fournisseurs";
        } catch (Exception e) {
            return "new_affectation_marchandise";
        }

    }

    public void reset() {
//        this.bailleur.setNomBailleur("");
//        this.bailleur.setAdresseBailleur("");
//        this.bailleur.setBpBailleur("");
//        this.bailleur.setMailBailleur("");
//        this.bailleur.setTelBailleur("");
    }

    public String resetModif() {
        return "fournisseurs";
    }

    public String editer() {
        try {
            fournisseurFromEdit = (Fournisseur) item.getRowData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "edit_fournisseur";
    }

    public String update() {
        try { 
            UtilUtfconvert utfconvert = new UtilUtfconvert();
            this.fournisseurFromEdit.setNomFournisseur(utfconvert.convertFromUTF8(fournisseurFromEdit.getNomFournisseur()));
            this.fournisseurFromEdit.setAdresseFournisseur(utfconvert.convertFromUTF8(fournisseurFromEdit.getAdresseFournisseur()));
            ejbfournisseur.edit(this.fournisseurFromEdit);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "fournisseurs";
    }

    public String supprimer() {
        try {
            fournisseurFromEdit = (Fournisseur) item.getRowData();
            ejbfournisseur.remove(fournisseurFromEdit);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "fournisseurs";
    }

    public String consulter() {
        try {

            fournisseurFromEdit = (Fournisseur) item.getRowData();

            /* List des ventes */
            itemVentes = new ListDataModel();
            this.setListVente(ejbvente.listOfVenteByFssr(fournisseurFromEdit));
            itemVentes.setWrappedData(this.getListVente());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "consulte_fournisseur";
    }

}
