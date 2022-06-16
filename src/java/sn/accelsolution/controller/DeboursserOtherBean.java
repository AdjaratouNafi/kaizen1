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
import sn.accelsolution.dao.DeboursserFacade;
import sn.accelsolution.dao.DetailledevisFacade;
import sn.accelsolution.dao.DevisFacade;
import sn.accelsolution.dao.NotificationFacade;
import sn.accelsolution.dao.TonpomcoefFacade;
import sn.accelsolution.dao.UtilisateurFacade;
import sn.accelsolution.entities.Client;
import sn.accelsolution.entities.Deboursser;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DELL
 */
@ManagedBean
@SessionScoped
public class DeboursserOtherBean implements Serializable {

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
    @EJB
    DeboursserFacade ejbDeboursser;

    private Deboursser deboursserFromEdit;

    @PostConstruct
    public void init() {

    }

    public DeboursserOtherBean() {
        deboursserFromEdit = new Deboursser();
    }

    public Deboursser getDeboursserFromEdit() {
        return deboursserFromEdit;
    }

    public void setDeboursserFromEdit(Deboursser deboursserFromEdit) {
        this.deboursserFromEdit = deboursserFromEdit;
    }

    public String modifier(Deboursser deb) {
        try {

            this.setDeboursserFromEdit(deb);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "edit_deboursser";
    }

    public String save() {

        try {
            ejbDeboursser.edit(deboursserFromEdit);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "deboursser";
    }

    public String supprimer(Deboursser deb) {
        try {
            //clientFromEdit = (Client) item.getRowData();
            ejbDeboursser.remove(deb);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "deboursser";
    }

}
