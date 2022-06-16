/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import sn.accelsolution.dao.ClientFacade;
import sn.accelsolution.dao.DetailledevisFacade;
import sn.accelsolution.dao.DevisFacade;
import sn.accelsolution.dao.NotificationFacade;
import sn.accelsolution.dao.TonpomcoefFacade;
import sn.accelsolution.dao.UtilisateurFacade;
import sn.accelsolution.entities.Client;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DELL
 */
@ManagedBean
@SessionScoped
public class ClientOtherBean implements Serializable {

    /**
     * Creates a new instance of ContratRecrutBean
     */
    @EJB
    UtilisateurFacade ejbUser;
    @EJB
    DevisFacade ejbDevis;
    @EJB
    DetailledevisFacade ejbDetailleDevis;
    @EJB
    NotificationFacade ejbNotification;
    @EJB
    TonpomcoefFacade ejbTonpomcoef;
    @EJB
    UtilisateurFacade ejbUtilisateur;
    @EJB
    ClientFacade ejbClient;

    private Client clientFromEdit;

    @PostConstruct
    public void init() {
       
    }

    public ClientOtherBean() {
        clientFromEdit = new Client();
    }

    public Client getClientFromEdit() {
        return clientFromEdit;
    }

    public void setClientFromEdit(Client clientFromEdit) {
        this.clientFromEdit = clientFromEdit;
    }
    


    public String modifier(Client clt) {
        try {

          this.setClientFromEdit(clt);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "edit_client";
    }


    public String save() {

        try {
            UtilUtfconvert utfconvert = new UtilUtfconvert();
            clientFromEdit.setNomClient(utfconvert.convertFromUTF8(clientFromEdit.getNomClient()));
            clientFromEdit.setAdresse(utfconvert.convertFromUTF8(clientFromEdit.getAdresse()));
            ejbClient.edit(clientFromEdit);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "clients";
    } 
    
    
    public String supprimer(Client clt) {
        try {
            //clientFromEdit = (Client) item.getRowData();
            ejbClient.remove(clt);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "clients";
    }

}
