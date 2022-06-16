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
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;
import sn.accelsolution.dao.BailleurFacade;
import sn.accelsolution.dao.DeboursserFacade;
import sn.accelsolution.dao.FournisseurFacade;
import sn.accelsolution.dao.MarchandiseFacade;
import sn.accelsolution.dao.NivodeboursserFacade;
import sn.accelsolution.dao.VenteFacade;
import sn.accelsolution.entities.Actionmenu;
import sn.accelsolution.entities.Bailleur;
import sn.accelsolution.entities.Deboursser;
import sn.accelsolution.entities.Fournisseur;
import sn.accelsolution.entities.Marchandise;
import sn.accelsolution.entities.Nivodeboursser;
import sn.accelsolution.entities.Vente;
import sn.accelsolution.util.UtilControleMenu;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DELL
 */
@ManagedBean
@ViewScoped
public class DeboursserBean implements Serializable {

    @EJB
    FournisseurFacade ejbfournisseur;
    @EJB
    VenteFacade ejbvente;
    @EJB
    DeboursserFacade ejbDeboursser;
    @EJB
    NivodeboursserFacade ejbNivodeboursser;
    @EJB
    MarchandiseFacade ejbMarchandise;

    private DataModel item;
    private List listDeboursser;
    private List listVente;
    private Deboursser deboursser;
    private Deboursser deboursserFromEdit;
    private List<Actionmenu> myllActionmenus;
    private List<Deboursser> filteredListDeboursser;
    private List<Nivodeboursser> allNiveauDeboursser = new ArrayList<>();
    private List<Marchandise> allMarchandise = new ArrayList<>();
    private List<Deboursser> allDeboursser;

    /**
     * Creates a new instance of UtilisateurBean
     */
    @PostConstruct
    public void init() {

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UtilControleMenu utilControleMenu = new UtilControleMenu();

        myllActionmenus = (List<Actionmenu>) session.getAttribute("allActionmenusFromTraitementFournisseurs");

//        //this.setCreerFournisseur(utilControleMenu.creerFournisseur(myllActionmenus));
//        this.setCreerFournisseur("yes");
//        //this.setModifierFournisseur(utilControleMenu.modifierFournisseur(myllActionmenus));
//        this.setModifierFournisseur("yes");
//        //this.setSupprimerFournisseur(utilControleMenu.supprimerFournisseur(myllActionmenus));
//        this.setSupprimerFournisseur("yes");
//        //this.setConsulterFournisseur(utilControleMenu.consulterFournisseur(myllActionmenus));
//        this.setConsulterFournisseur("yes");
//        item = new ListDataModel();
//        List<Deboursser> listAllDebour = ejbDeboursser.findAll();
//        List<Deboursser> listAllDebourToAdd = new ArrayList<>();
//        listAllDebourToAdd.clear();
//        //UtilUtfconvert utfconvert = new UtilUtfconvert();
//        for (Deboursser fssr : listAllDebour) {
//            listAllDebourToAdd.add(fssr);
//        }
//        this.setListDeboursser(listAllDebourToAdd);
//        item.setWrappedData(this.getListDeboursser());
        this.setListDeboursser(ejbDeboursser.findAll());
        allDeboursser = new ArrayList<>();
        allDeboursser = ejbDeboursser.findAll();

    }

    public DeboursserBean() {
        deboursser = new Deboursser();
        deboursserFromEdit = new Deboursser();
    }

    public List<Deboursser> getAllDeboursser() {
        return allDeboursser;
    }

    public void setAllDeboursser(List<Deboursser> allDeboursser) {
        this.allDeboursser = allDeboursser;
    }

    public List<Nivodeboursser> getAllNiveauDeboursser() {
        allNiveauDeboursser = ejbNivodeboursser.findAll();
        return allNiveauDeboursser;
    }

    public void setAllNiveauDeboursser(List<Nivodeboursser> allNiveauDeboursser) {
        this.allNiveauDeboursser = allNiveauDeboursser;
    }

    public List<Marchandise> getAllMarchandise() {
        allMarchandise = ejbMarchandise.findAll();
        return allMarchandise;
    }

    public void setAllMarchandise(List<Marchandise> allMarchandise) {
        this.allMarchandise = allMarchandise;
    }

    public List<Deboursser> getFilteredListDeboursser() {
        return filteredListDeboursser;
    }

    public void setFilteredListDeboursser(List<Deboursser> filteredListDeboursser) {
        this.filteredListDeboursser = filteredListDeboursser;
    }

    public List<Actionmenu> getMyllActionmenus() {
        return myllActionmenus;
    }

    public void setMyllActionmenus(List<Actionmenu> myllActionmenus) {
        this.myllActionmenus = myllActionmenus;
    }

    public DataModel getItem() {
        return item;
    }

    public void setItem(DataModel item) {
        this.item = item;
    }

    public List getListDeboursser() {
        listDeboursser = ejbDeboursser.findAll();
        return listDeboursser;
    }

    public void setListDeboursser(List listDeboursser) {
        this.listDeboursser = listDeboursser;
    }

    public Deboursser getDeboursser() {
        return deboursser;
    }

    public void setDeboursser(Deboursser deboursser) {
        this.deboursser = deboursser;
    }

    public Deboursser getDeboursserFromEdit() {
        return deboursserFromEdit;
    }

    public void setDeboursserFromEdit(Deboursser deboursserFromEdit) {
        this.deboursserFromEdit = deboursserFromEdit;
    }

    public String editer() {
        try {
            this.deboursserFromEdit = (Deboursser) item.getRowData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "edit_deboursser";
    }

    public String update() {
        try {
            ejbDeboursser.edit(this.deboursserFromEdit);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "deboursser";
    }

    public String supprimer() {
        try {
            this.deboursserFromEdit = (Deboursser) item.getRowData();
            ejbDeboursser.remove(this.deboursserFromEdit);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "deboursser";
    }

    public String save() {
        try {
            ejbDeboursser.create(this.deboursser);
            return "deboursser";
        } catch (Exception e) {
            return "new_deboursser";
        }

    }

    public String reset() {
        return "";

    }

}
