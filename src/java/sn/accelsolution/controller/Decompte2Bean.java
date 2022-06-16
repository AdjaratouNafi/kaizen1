/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import sn.accelsolution.dao.Decompte2Facade;
import sn.accelsolution.dao.Detailledecompte2Facade;
import sn.accelsolution.dao.UtilisateurFacade;
import sn.accelsolution.entities.Decompte2;
import sn.accelsolution.entities.Detailledecompte2;

/**
 *
 * @author DELL
 */
@ManagedBean
@RequestScoped
public class Decompte2Bean implements Serializable {

    /**
     * Creates a new instance of ContratRecrutBean
     */
    @EJB
    UtilisateurFacade ejbUser;
    @EJB
    Decompte2Facade ejbDecompte2;
    @EJB
    Detailledecompte2Facade ejbDetailleDecompte2;

    private Decompte2 decompte2;
    private Decompte2 decompte2FromEdit;
    private Detailledecompte2 detailleDecompte2;
    private Detailledecompte2 detailleDecompte2FromEdit;

    private DataModel itemDecomptes2;
    private DataModel itemDetailleDecomptes2;
    private List listDecomptes;
    private List listDecomptesFromForm;
    private List listDetailleDecomptes;
    private String tva;

    @PostConstruct
    public void init() {
        this.setTva("18%");
    }

    public Decompte2Bean() {

        decompte2 = new Decompte2();
        decompte2FromEdit = new Decompte2();
        detailleDecompte2 = new Detailledecompte2();
        detailleDecompte2FromEdit = new Detailledecompte2();
    }

    public Decompte2 getDecompte2() {
        return decompte2;
    }

    public void setDecompte2(Decompte2 decompte2) {
        this.decompte2 = decompte2;
    }

    public Decompte2 getDecompte2FromEdit() {
        return decompte2FromEdit;
    }

    public void setDecompte2FromEdit(Decompte2 decompte2FromEdit) {
        this.decompte2FromEdit = decompte2FromEdit;
    }

    public Detailledecompte2 getDetailleDecompte2() {
        return detailleDecompte2;
    }

    public void setDetailleDecompte2(Detailledecompte2 detailleDecompte2) {
        this.detailleDecompte2 = detailleDecompte2;
    }

    public Detailledecompte2 getDetailleDecompte2FromEdit() {
        return detailleDecompte2FromEdit;
    }

    public void setDetailleDecompte2FromEdit(Detailledecompte2 detailleDecompte2FromEdit) {
        this.detailleDecompte2FromEdit = detailleDecompte2FromEdit;
    }

    public DataModel getItemDecomptes2() {
        itemDecomptes2 = new ListDataModel();
        this.setListDecomptes(ejbDecompte2.findAll());
        itemDecomptes2.setWrappedData(this.getListDecomptes());
        return itemDecomptes2;
    }

    public void setItemDecomptes2(DataModel itemDecomptes2) {
        this.itemDecomptes2 = itemDecomptes2;
    }

    public DataModel getItemDetailleDecomptes2() {
        return itemDetailleDecomptes2;
    }

    public void setItemDetailleDecomptes2(DataModel itemDetailleDecomptes2) {
        this.itemDetailleDecomptes2 = itemDetailleDecomptes2;
    }

    public List getListDecomptes() {
        return listDecomptes;
    }

    public void setListDecomptes(List listDecomptes) {
        this.listDecomptes = listDecomptes;
    }

    public List getListDecomptesFromForm() {
        listDecomptesFromForm = ejbDecompte2.findAll();
        return listDecomptesFromForm;
    }

    public void setListDecomptesFromForm(List listDecomptesFromForm) {
        this.listDecomptesFromForm = listDecomptesFromForm;
    }

    public List getListDetailleDecomptes() {
        return listDetailleDecomptes;
    }

    public void setListDetailleDecomptes(List listDetailleDecomptes) {
        this.listDetailleDecomptes = listDetailleDecomptes;
    }

    public String getTva() {
        return tva;
    }

    public void setTva(String tva) {
        this.tva = tva;
    }

    public void renderInfoCmd() {

        this.setDecompte2(this.getDetailleDecompte2().getIdDecompte2());

    }

    public String save() {

        try {

            ejbDecompte2.insertDecompte(this.decompte2);
            int idDectp = ejbDecompte2.maxCommande();
            Decompte2 dcpt2 = ejbDecompte2.find(idDectp);
            this.detailleDecompte2.setIdDecompte2(dcpt2);
            ejbDetailleDecompte2.insertDetailleDecompte2(this.detailleDecompte2);

            return "decomptes2";
        } catch (Exception e) {
            return "new_decompte2";
        }
    }

    public String reset() {
        this.decompte2 = new Decompte2();
        return "decomptes2";
    }

    public String saveMaj() {
        try {
            ejbDetailleDecompte2.insertDetailleDecompte2(this.detailleDecompte2);
            return "decomptes2";
        } catch (Exception e) {
            return "maj_decompte";
        }
    }

    public String supprimer() {
        try {
            decompte2FromEdit = (Decompte2) itemDecomptes2.getRowData();
            ejbDetailleDecompte2.deleteByDecompte(decompte2FromEdit.getIdDecompte2());
            ejbDecompte2.remove(decompte2FromEdit);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "decomptes2";
    }

    public String imprimer() {
        try {
//            commandeFromEdit = (Commande) itemCommandes.getRowData();
//            ReportBonCommande rBCmd = new ReportBonCommande();
//            List<DetailleCommande> lstDetailCommandes = ejbDetailleCommande.listOfDetailleByCommande(commandeFromEdit);
//            rBCmd.GenererCMB(commandeFromEdit, lstDetailCommandes);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "decomptes2";
    }

    public String detail() {
        try {

            decompte2FromEdit = (Decompte2) itemDecomptes2.getRowData();

            /* List des detailles commandes */
            itemDetailleDecomptes2 = new ListDataModel();
            this.setListDetailleDecomptes(ejbDetailleDecompte2.listOfDetailleByDecompte(decompte2FromEdit));
            itemDetailleDecomptes2.setWrappedData(this.getListDetailleDecomptes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "consulte_decomptes2";
    }

}
