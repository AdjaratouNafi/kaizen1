/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.controller;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;       
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import sn.accelsolution.dao.AppelOffreFacade;
import sn.accelsolution.dao.BailleurFacade;
import sn.accelsolution.dao.DetailleappeloffreFacade;
import sn.accelsolution.dao.RessourceFacade;
import sn.accelsolution.entities.AppelOffre;
import sn.accelsolution.entities.Bailleur;
import sn.accelsolution.entities.Detailleappeloffre;
import sn.accelsolution.entities.Ressource;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DELL
 */
@ManagedBean
@RequestScoped
public class AppelOffreBean implements Serializable {

    @EJB
    AppelOffreFacade ejbappelOffre;
    @EJB
    DetailleappeloffreFacade ejbdetailleappelOffre;

    private DataModel item;
    private DataModel itemDetailAppelOffres;
    private List listAppelOffres;
    private List listDetailAppelOffres;
    private AppelOffre appelOffre;
    private AppelOffre appelOffreFromEdit;
    private Detailleappeloffre detailleAppelOffre;

    /**
     * Creates a new instance of UtilisateurBean
     */
    public AppelOffreBean() {
        appelOffre = new AppelOffre();
        appelOffreFromEdit = new AppelOffre();
        detailleAppelOffre = new Detailleappeloffre();
    }

    public DataModel getItem() {
        item = new ListDataModel();
        this.setListAppelOffres(ejbappelOffre.findAll());
        item.setWrappedData(this.getListAppelOffres());
        return item;
    }

    public void setItem(DataModel item) {
        this.item = item;
    }

    public DataModel getItemDetailAppelOffres() {
        return itemDetailAppelOffres;
    }

    public void setItemDetailAppelOffres(DataModel itemDetailAppelOffres) {
        this.itemDetailAppelOffres = itemDetailAppelOffres;
    }
    

    public List getListAppelOffres() {
        listAppelOffres = ejbappelOffre.findAll();
        return listAppelOffres;
    }

    public void setListAppelOffres(List listAppelOffres) {
        this.listAppelOffres = listAppelOffres;
    }

    public List getListDetailAppelOffres() {
        return listDetailAppelOffres;
    }

    public void setListDetailAppelOffres(List listDetailAppelOffres) {
        this.listDetailAppelOffres = listDetailAppelOffres;
    }
    

    public AppelOffre getAppelOffre() {
        return appelOffre;
    }

    public void setAppelOffre(AppelOffre appelOffre) {
        this.appelOffre = appelOffre;
    }

    public AppelOffre getAppelOffreFromEdit() {
        return appelOffreFromEdit;
    }

    public void setAppelOffreFromEdit(AppelOffre appelOffreFromEdit) {
        this.appelOffreFromEdit = appelOffreFromEdit;
    }

    public Detailleappeloffre getDetailleAppelOffre() {
        return detailleAppelOffre;
    }

    public void setDetailleAppelOffre(Detailleappeloffre detailleAppelOffre) {
        this.detailleAppelOffre = detailleAppelOffre;
    }

    public String save() {
        try {
            this.appelOffre.setEtatApel("Planifié");
            ejbappelOffre.insertAppelOffre(this.appelOffre);
            return "appel_offre";
        } catch (Exception e) {
            return "new_appel_offre";
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
        return "appel_offre";
    }

    public String editer() {
        try {
            appelOffreFromEdit = (AppelOffre) item.getRowData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "edit_appel_offre";
    }

    public String tache() {
        try {
            appelOffreFromEdit = (AppelOffre) item.getRowData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "new_tache";
    }

    public String saveDetaille() {
        try {
            this.detailleAppelOffre.setIdAppel(appelOffreFromEdit);
            ejbdetailleappelOffre.insertDetailAppelOffre(detailleAppelOffre);
            return "appel_offre";
        } catch (Exception e) {
            return "new_tache";
        }
    }

    public String consulter() {
        try {

            appelOffreFromEdit = (AppelOffre) item.getRowData();

            /* List des detailles appel offres */
            itemDetailAppelOffres = new ListDataModel();
            this.setListDetailAppelOffres(ejbdetailleappelOffre.listOfDetailApelOfreByAppel(appelOffreFromEdit));
            itemDetailAppelOffres.setWrappedData(this.getListDetailAppelOffres());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "consulte_appelOffre";
    }

    public String update() {
        try {
            UtilUtfconvert utfconvert = new UtilUtfconvert();
            appelOffreFromEdit.setTypeAppel(utfconvert.convertFromUTF8(appelOffreFromEdit.getTypeAppel())); 
            ejbappelOffre.edit(appelOffreFromEdit);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "appel_offre";
    }

    public void soumettre() {
        try {

            this.appelOffreFromEdit = (AppelOffre) item.getRowData();
            this.appelOffreFromEdit.setEtatApel("Soumis");

            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date today = Calendar.getInstance().getTime();
            String reportDate = df.format(today);
            this.appelOffreFromEdit.setDateSoumission(reportDate);

            ejbappelOffre.edit(this.appelOffreFromEdit);

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public String cloturer() {
        try {
            appelOffreFromEdit = (AppelOffre) item.getRowData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "cloture_appel_offre";
    }

    public String validerCloture() {
        try {
            this.appelOffreFromEdit.setEtatApel("Cloturé");
            AppelOffre appel = ejbappelOffre.find(this.appelOffreFromEdit.getIdAppel());
            this.appelOffreFromEdit.setNumAppel(appel.getNumAppel());
            this.appelOffreFromEdit.setTypeAppel(appel.getTypeAppel());
            this.appelOffreFromEdit.setDateAppel(appel.getDateAppel());
            this.appelOffreFromEdit.setIdUtilisateur(appel.getIdUtilisateur());
            this.appelOffreFromEdit.setDateSoumission(appel.getDateSoumission());
            ejbappelOffre.edit(this.appelOffreFromEdit);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "appel_offre";
    }

    public String supprimer() {
        try {
            appelOffreFromEdit = (AppelOffre) item.getRowData();
            ejbappelOffre.remove(appelOffreFromEdit);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "appel_offre";
    }

}
