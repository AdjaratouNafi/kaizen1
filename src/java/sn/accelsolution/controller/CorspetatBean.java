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
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.primefaces.event.RowEditEvent;
import sn.accelsolution.dao.CorpsetatFacade;
import sn.accelsolution.entities.Corpsetat;

/**
 *
 * @author DELL
 */
@ManagedBean
@RequestScoped
public class CorspetatBean implements Serializable {

    /**
     * Creates a new instance of ContratRecrutBean
     */
    @EJB
    CorpsetatFacade ejbCorpsetat;

    private Corpsetat corpsetat;
    private Corpsetat corpsetatFromEdit;

    private List<Corpsetat> allCorpsetats = new ArrayList<>();
    private DataModel item;

    @PostConstruct
    public void init() {
        //this.allCorpsetats = ejbCorpsetat.findAll();
    }

    public CorspetatBean() {

        corpsetat = new Corpsetat();
        corpsetatFromEdit = new Corpsetat();
    }

    public DataModel getItem() {
        item = new ListDataModel();
        this.setAllCorpsetats(ejbCorpsetat.findAll());
        item.setWrappedData(this.getAllCorpsetats());
        return item;
    }

    public void setItem(DataModel item) {
        this.item = item;
    }

    public Corpsetat getCorpsetat() {
        return corpsetat;
    }

    public void setCorpsetat(Corpsetat corpsetat) {
        this.corpsetat = corpsetat;
    }

    public Corpsetat getCorpsetatFromEdit() {
        return corpsetatFromEdit;
    }

    public void setCorpsetatFromEdit(Corpsetat corpsetatFromEdit) {
        this.corpsetatFromEdit = corpsetatFromEdit;
    }

    public List<Corpsetat> getAllCorpsetats() {
        allCorpsetats = ejbCorpsetat.findAll();
        return allCorpsetats;
    }

    public void setAllCorpsetats(List<Corpsetat> allCorpsetats) {
        this.allCorpsetats = allCorpsetats;
    }

    public String save() {
        try {
            ejbCorpsetat.insertCorpsetat(this.corpsetat);
            return "corpsetat";
        } catch (Exception e) {
            return "new_corpsetat";
        }
    }

    public String reset() {
        this.corpsetat = new Corpsetat();
        return "corpsetat";
    }

    public CorpsetatFacade getEjbCorpsetat() {
        return ejbCorpsetat;
    }

    public void setEjbCorpsetat(CorpsetatFacade ejbCorpsetat) {
        this.ejbCorpsetat = ejbCorpsetat;
    }

    public void rowEdit(RowEditEvent event) {
        Corpsetat corpsetatEdit = (Corpsetat) event.getObject();
        ejbCorpsetat.edit(corpsetatEdit);
    }

    public void rowEditCancel() {

    }

    public String editer() {
        try {
            corpsetatFromEdit = (Corpsetat) item.getRowData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "edit_corpsetat";
    }

    public String update() {
        try {
            ejbCorpsetat.edit(corpsetatFromEdit);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "corpsetat";
    }

    public String supprimer() {
        try {
            corpsetatFromEdit = (Corpsetat) item.getRowData();
            ejbCorpsetat.remove(corpsetatFromEdit);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "corpsetat";
    }
}
