/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.controller;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import sn.accelsolution.dao.ClientFacade;
import sn.accelsolution.dao.ProspectFacade;
import sn.accelsolution.entities.Client;
import sn.accelsolution.entities.Prospect;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DELL
 */
@ManagedBean
@RequestScoped
public class ProspectBean implements Serializable {
    
    @EJB
    ProspectFacade ejbprospect;
    @EJB
    ClientFacade ejbclient;
    
    private DataModel item;
    private List listProspects;
    private Prospect prospect;
    private Prospect prospectFromEdit;

    /**
     * Creates a new instance of UtilisateurBean
     */
    public ProspectBean() {
        prospect = new Prospect();
        prospectFromEdit = new Prospect();
    }
    
    public DataModel getItem() {
        item = new ListDataModel();
        this.setListProspects(ejbprospect.findAll());
        item.setWrappedData(this.getListProspects());
        return item;
    }
    
    public void setItem(DataModel item) {
        this.item = item;
    }
    
    public List getListProspects() {
        listProspects = ejbprospect.findAll();
        return listProspects;
    }
    
    public void setListProspects(List listProspects) {
        this.listProspects = listProspects;
    }
    
    public Prospect getProspect() {
        return prospect;
    }
    
    public void setProspect(Prospect prospect) {
        this.prospect = prospect;
    }
    
    public Prospect getProspectFromEdit() {
        return prospectFromEdit;
    }
    
    public void setProspectFromEdit(Prospect prospectFromEdit) {
        this.prospectFromEdit = prospectFromEdit;
    }
    
    public String save() {
        try {
            prospect.setClient("non");
            ejbprospect.insertProspect(prospect);
            return "prospects";
        } catch (Exception e) {
            return "new_prospect";
        }
    }
    
    public String transformer() {
        try {
            prospectFromEdit = (Prospect) item.getRowData();
            
            Client client = new Client();
            client.setNomClient(prospectFromEdit.getNomProspect());
            client.setAdresse(prospectFromEdit.getAdresse());
            client.setBpClient(prospectFromEdit.getBpProspect());
            client.setMailClient(prospectFromEdit.getMailProspect());
            client.setTelephoneClient(prospectFromEdit.getTelephoneProspect());
            ejbclient.insertClient(client);
            
            prospectFromEdit.setClient("oui");
            
            ejbprospect.edit(prospectFromEdit);
            
            return "clients";
        } catch (Exception e) {
            return "prospects";
        }
    }
    
    public void reset() {
//        this.prospect.setAdresse("");
//        this.client.setBpClient("");
//        this.client.setMailClient("");
//        this.client.setNomClient("");
//        this.client.setTelephoneClient("");
    }
    
    public String resetModif() {
        return "prospects";
    }
    
    public String editer() {
        try {
            prospectFromEdit = (Prospect) item.getRowData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "edit_prospect";
    }
    
    public String update() {
        try { 
            UtilUtfconvert utfconvert = new UtilUtfconvert();
            prospectFromEdit.setNomProspect(utfconvert.convertFromUTF8(prospect.getNomProspect()));
            prospectFromEdit.setAdresse(utfconvert.convertFromUTF8(prospect.getAdresse()));
            ejbprospect.edit(prospectFromEdit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return "prospects";
    }
    
    public String supprimer() {
        try {
            prospectFromEdit = (Prospect) item.getRowData();
            ejbprospect.remove(prospectFromEdit);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "prospects";
    }
    
}
