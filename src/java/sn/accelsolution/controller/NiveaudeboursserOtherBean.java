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
import sn.accelsolution.dao.NivodeboursserFacade;
import sn.accelsolution.dao.NotificationFacade;
import sn.accelsolution.dao.TonpomcoefFacade;
import sn.accelsolution.dao.UtilisateurFacade;     
import sn.accelsolution.entities.Client;
import sn.accelsolution.entities.Nivodeboursser;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DELL
 */
@ManagedBean
@SessionScoped
public class NiveaudeboursserOtherBean implements Serializable {        

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
    NivodeboursserFacade ejbNivodeboursser;

    private Nivodeboursser nivodeboursserFromEdit;

    @PostConstruct
    public void init() {

    }

    public NiveaudeboursserOtherBean() {
        nivodeboursserFromEdit = new Nivodeboursser();
    }

    public Nivodeboursser getNivodeboursserFromEdit() {
        return nivodeboursserFromEdit;
    }

    public void setNivodeboursserFromEdit(Nivodeboursser nivodeboursserFromEdit) {
        this.nivodeboursserFromEdit = nivodeboursserFromEdit;
    }

    public String modifier(Nivodeboursser niveau) {
        try {

            this.setNivodeboursserFromEdit(niveau);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "edit_niveaudeboursser";
    }

    public String save() {

        try {
            
            ejbNivodeboursser.edit(nivodeboursserFromEdit);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "niveaudeboursser";
    }

    public String supprimer(Nivodeboursser niveau) {
        try {
            //clientFromEdit = (Client) item.getRowData();
            ejbNivodeboursser.remove(niveau);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "niveaudeboursser";
    }

}
