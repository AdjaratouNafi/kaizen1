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
import org.primefaces.event.RowEditEvent;
import sn.accelsolution.dao.LottechniqueFacade;
import sn.accelsolution.entities.Actionmenu;
import sn.accelsolution.entities.Lottechnique;
import sn.accelsolution.util.UtilControleMenu;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DELL
 */
@ManagedBean
@RequestScoped
public class LottechniqueBean implements Serializable {

    /**
     * Creates a new instance of ContratRecrutBean
     */
    @EJB
    LottechniqueFacade ejbLottechnique;

    private Lottechnique lottechnique;
    private Lottechnique lottechniqueFromEdit;

    private List<Lottechnique> allLottechniques = new ArrayList<>();
    private DataModel item;

    private List<Actionmenu> myllActionmenus;
    private String creerLottechnique;
    private String modifierLottechnique;
    private String supprimerLottechnique;
    private String consulterLottechnique;
    private String imprimerLottechnique;
    private List<Lottechnique> filteredListLottechnique;

    @PostConstruct
    public void init() {

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UtilControleMenu utilControleMenu = new UtilControleMenu();

        myllActionmenus = (List<Actionmenu>) session.getAttribute("allActionmenusFromTraitementLotTechnique");

        this.setCreerLottechnique(utilControleMenu.creerLottechnique(myllActionmenus));
        this.setModifierLottechnique(utilControleMenu.modifierLottechnique(myllActionmenus));
        this.setSupprimerLottechnique(utilControleMenu.supprimerLottechnique(myllActionmenus));
        this.setConsulterLottechnique(utilControleMenu.consulterLottechnique(myllActionmenus));

        // this.allLottechniques = ejbLottechnique.findAll();
        
        item = new ListDataModel();
        List<Lottechnique> listAllLotehnique = ejbLottechnique.findAll();
         List<Lottechnique> listAllLotehniqueToAdd = new ArrayList<>();
         listAllLotehniqueToAdd.clear();
         //UtilUtfconvert utfconvert = new UtilUtfconvert();
         for(Lottechnique lotech: listAllLotehnique){
             listAllLotehniqueToAdd.add(lotech);
         }
        this.setAllLottechniques(listAllLotehniqueToAdd);
        item.setWrappedData(this.getAllLottechniques());
        
    }

    public LottechniqueBean() {
        lottechnique = new Lottechnique();
        lottechniqueFromEdit = new Lottechnique();
    } 

    public List<Lottechnique> getFilteredListLottechnique() {
        return filteredListLottechnique;
    }

    public void setFilteredListLottechnique(List<Lottechnique> filteredListLottechnique) {
        this.filteredListLottechnique = filteredListLottechnique;
    }

    public List<Actionmenu> getMyllActionmenus() {
        return myllActionmenus;
    }

    public void setMyllActionmenus(List<Actionmenu> myllActionmenus) {
        this.myllActionmenus = myllActionmenus;
    }

    public String getCreerLottechnique() {
        return creerLottechnique;
    }

    public void setCreerLottechnique(String creerLottechnique) {
        this.creerLottechnique = creerLottechnique;
    }

    public String getModifierLottechnique() {
        return modifierLottechnique;
    }

    public void setModifierLottechnique(String modifierLottechnique) {
        this.modifierLottechnique = modifierLottechnique;
    }

    public String getSupprimerLottechnique() {
        return supprimerLottechnique;
    }

    public void setSupprimerLottechnique(String supprimerLottechnique) {
        this.supprimerLottechnique = supprimerLottechnique;
    }

    public String getConsulterLottechnique() {
        return consulterLottechnique;
    }

    public void setConsulterLottechnique(String consulterLottechnique) {
        this.consulterLottechnique = consulterLottechnique;
    }

    public String getImprimerLottechnique() {
        return imprimerLottechnique;
    }

    public void setImprimerLottechnique(String imprimerLottechnique) {
        this.imprimerLottechnique = imprimerLottechnique;
    }

    public DataModel getItem() {
        
        return item;
    }

    public void setItem(DataModel item) {
        this.item = item;
    }

    public Lottechnique getLottechnique() {
        return lottechnique;
    }

    public void setLottechnique(Lottechnique lottechnique) {
        this.lottechnique = lottechnique;
    }

    public Lottechnique getLottechniqueFromEdit() {
        return lottechniqueFromEdit;
    }

    public void setLottechniqueFromEdit(Lottechnique lottechniqueFromEdit) {
        this.lottechniqueFromEdit = lottechniqueFromEdit;
    }

    public List<Lottechnique> getAllLottechniques() {
        return allLottechniques;
    }

    public void setAllLottechniques(List<Lottechnique> allLottechniques) {

        this.allLottechniques = allLottechniques;
    }

    public String save() {
        try {
            ejbLottechnique.insertLottechnique(this.lottechnique);
            return "lottechnique";
        } catch (Exception e) {
            return "new_lottechnique";
        }
    }

    public String reset() {
        this.lottechnique = new Lottechnique();
        return "lottechnique";
    }

    public void rowEdit(RowEditEvent event) {
        Lottechnique lottechniqueEdit = (Lottechnique) event.getObject();
        ejbLottechnique.edit(lottechniqueEdit);
    }

    public void rowEditCancel() {

    }

    public String editer() {
        try {
            lottechniqueFromEdit = (Lottechnique) item.getRowData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "edit_lottechnique";
    }

    public String update() {
        try { 
            UtilUtfconvert utfconvert = new UtilUtfconvert(); 
            lottechniqueFromEdit.setLibellelottechnique(utfconvert.convertFromUTF8(lottechniqueFromEdit.getLibellelottechnique()));
            ejbLottechnique.edit(lottechniqueFromEdit);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "lottechnique";
    }

    public String supprimer() {
        try {
            lottechniqueFromEdit = (Lottechnique) item.getRowData();
            ejbLottechnique.remove(lottechniqueFromEdit);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "lottechnique";
    }

}
