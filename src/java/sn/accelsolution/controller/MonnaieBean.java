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
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import sn.accelsolution.dao.CaisseFacade;
import sn.accelsolution.dao.MonnaieFacade;
import sn.accelsolution.dao.OperationFacade;
import sn.accelsolution.dao.TypeOperationFacade;
import sn.accelsolution.entities.Caisse;
import sn.accelsolution.entities.Monnaie;
import sn.accelsolution.entities.Operation;
import sn.accelsolution.entities.TypeOperation;
import sn.accelsolution.util.ReportContrat;

/**
 *
 * @author DELL
 */
@ManagedBean
@RequestScoped
public class MonnaieBean implements Serializable {

    /**
     * Creates a new instance of CaisseBean
     */
    @EJB
    MonnaieFacade ejbmonanaie;

    private Monnaie monnaie;
    private Monnaie monnaieFromEdit;
    private DataModel item;
    private List listMonnaies;
    private String totalMonnaie;

    @PostConstruct
    public void init() {
        List<Monnaie> listOfMonnaies = new ArrayList<Monnaie>();
        listOfMonnaies = ejbmonanaie.findAll();
        Double ttl = 0.0;
        for (Monnaie mn : listOfMonnaies) {
             ttl = ttl + Double.parseDouble(mn.getMonnnaie());
        }

        this.totalMonnaie = ttl.toString();
    }

    public MonnaieBean() {
        monnaie = new Monnaie();
        monnaieFromEdit = new Monnaie();
    }

    public String getTotalMonnaie() {
        return totalMonnaie;
    }

    public void setTotalMonnaie(String totalMonnaie) {
        this.totalMonnaie = totalMonnaie;
    }

    public DataModel getItem() {
        item = new ListDataModel();
        this.setListMonnaies(ejbmonanaie.findAll());
        item.setWrappedData(this.getListMonnaies());
        return item;
    }

    public void setItem(DataModel item) {
        this.item = item;
    }

    public Monnaie getMonnaie() {
        return monnaie;
    }

    public void setMonnaie(Monnaie monnaie) {
        this.monnaie = monnaie;
    }

    public Monnaie getMonnaieFromEdit() {
        return monnaieFromEdit;
    }

    public void setMonnaieFromEdit(Monnaie monnaieFromEdit) {
        this.monnaieFromEdit = monnaieFromEdit;
    }

    public List getListMonnaies() {
        listMonnaies = ejbmonanaie.findAll();
        return listMonnaies;
    }

    public void setListMonnaies(List listMonnaies) {
        this.listMonnaies = listMonnaies;
    }

    public String save() {
        System.out.println("Bonjour Willi !!!");

        try {

            ejbmonanaie.insertMonnaie(monnaie);

            return "monnaies";
        } catch (Exception e) {
            return "new_monnaie";
        }

    }
    
    
    public String supprimer() {
        try {
            monnaieFromEdit = (Monnaie) item.getRowData();
            ejbmonanaie.remove(monnaieFromEdit);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "monnaies";
    }

}
