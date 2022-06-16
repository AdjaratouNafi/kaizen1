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
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;
import org.primefaces.event.FlowEvent;
import sn.accelsolution.dao.IndemniteFacade;
import sn.accelsolution.dao.PretFacade;
import sn.accelsolution.dao.UtilisateurFacade;
import sn.accelsolution.entities.Actionmenu;
import sn.accelsolution.entities.Indemnite;
import sn.accelsolution.entities.Pret;
import sn.accelsolution.entities.Utilisateur;
import sn.accelsolution.util.UtilControleMenu;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DELL
 */
@ManagedBean
@RequestScoped
public class indemniteBean implements Serializable {

    /**
     * Creates a new instance of ContratRecrutBean
     */
    @EJB
    UtilisateurFacade ejbUser;
    @EJB
    IndemniteFacade ejbIndemnite;

    private Utilisateur user;
    private Indemnite indemnite;
    private Indemnite indemniteFromEdit;
    private Indemnite indemniteFromDelete;

    private boolean skip;
    private DataModel itemIndemnites;
    private List listIndemnites;
    private List listUsers;
    private List<Actionmenu> myllActionmenus;
    private String creerIndemnite;
    private String modifierIndemnite;
    private String supprimerIndemnite;
    private String consulterIndemnite;
    private String imprimerIndemnite;
    private String validerIndemnite;

    @PostConstruct
    public void init() {

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UtilControleMenu utilControleMenu = new UtilControleMenu();

        myllActionmenus = (List<Actionmenu>) session.getAttribute("allActionmenusFromTraitementIndemnites");

        this.setCreerIndemnite(utilControleMenu.creerIndemnite(myllActionmenus));
        this.setModifierIndemnite(utilControleMenu.modifierIndemnite(myllActionmenus));
        this.setSupprimerIndemnite(utilControleMenu.supprimerIndemnite(myllActionmenus));
        this.setConsulterIndemnite(utilControleMenu.consulterIndemnite(myllActionmenus));
        this.setImprimerIndemnite(utilControleMenu.imprimerIndemnite(myllActionmenus));
        this.setValiderIndemnite(utilControleMenu.validerIndemnite(myllActionmenus));
    }

    public indemniteBean() {

        user = new Utilisateur();
        indemnite = new Indemnite();
        indemniteFromEdit = new Indemnite();
        indemniteFromDelete = new Indemnite();

    }

    public List<Actionmenu> getMyllActionmenus() {
        return myllActionmenus;
    }

    public void setMyllActionmenus(List<Actionmenu> myllActionmenus) {
        this.myllActionmenus = myllActionmenus;
    }

    public String getCreerIndemnite() {
        return creerIndemnite;
    }

    public void setCreerIndemnite(String creerIndemnite) {
        this.creerIndemnite = creerIndemnite;
    }

    public String getModifierIndemnite() {
        return modifierIndemnite;
    }

    public void setModifierIndemnite(String modifierIndemnite) {
        this.modifierIndemnite = modifierIndemnite;
    }

    public String getSupprimerIndemnite() {
        return supprimerIndemnite;
    }

    public void setSupprimerIndemnite(String supprimerIndemnite) {
        this.supprimerIndemnite = supprimerIndemnite;
    }

    public String getConsulterIndemnite() {
        return consulterIndemnite;
    }

    public void setConsulterIndemnite(String consulterIndemnite) {
        this.consulterIndemnite = consulterIndemnite;
    }

    public String getImprimerIndemnite() {
        return imprimerIndemnite;
    }

    public void setImprimerIndemnite(String imprimerIndemnite) {
        this.imprimerIndemnite = imprimerIndemnite;
    }

    public String getValiderIndemnite() {
        return validerIndemnite;
    }

    public void setValiderIndemnite(String validerIndemnite) {
        this.validerIndemnite = validerIndemnite;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public List getListUsers() {
        return listUsers;
    }

    public void setListUsers(List listUsers) {
        this.listUsers = listUsers;
    }

    public Indemnite getIndemnite() {
        return indemnite;
    }

    public void setIndemnite(Indemnite indemnite) {
        this.indemnite = indemnite;
    }

    public Indemnite getIndemniteFromEdit() {
        return indemniteFromEdit;
    }

    public void setIndemniteFromEdit(Indemnite indemniteFromEdit) {
        this.indemniteFromEdit = indemniteFromEdit;
    }

    public Indemnite getIndemniteFromDelete() {
        return indemniteFromDelete;
    }

    public void setIndemniteFromDelete(Indemnite indemniteFromDelete) {
        this.indemniteFromDelete = indemniteFromDelete;
    }

    public DataModel getItemIndemnites() {
        itemIndemnites = new ListDataModel();
        this.setListIndemnites(ejbIndemnite.findAll());
        itemIndemnites.setWrappedData(this.getListIndemnites());
        return itemIndemnites;
    }

    public void setItemIndemnites(DataModel itemIndemnites) {
        this.itemIndemnites = itemIndemnites;
    }

    public List getListIndemnites() {
        return listIndemnites;
    }

    public void setListIndemnites(List listIndemnites) {
        this.listIndemnites = listIndemnites;
    }

    public void renderInfoUser() {

        this.user = this.indemnite.getIdUtilisateur();

    }

    public void renderInfoUserr() {

        this.user = this.indemniteFromEdit.getIdUtilisateur();

    }

    public String saveIndemnite() {
        try {
            this.indemnite.setEtatIndemnite("Invalide");
            ejbIndemnite.insertIndemnite(this.indemnite);
            return "indemnites";
        } catch (Exception e) {
            return "new_indemnite";
        }
    }

    public String editer() {
        try {
            this.indemniteFromEdit = (Indemnite) itemIndemnites.getRowData();
            this.user = this.indemniteFromEdit.getIdUtilisateur();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "edit_indemnite";
    }

    public String update() {
        try {
            UtilUtfconvert utfconvert = new UtilUtfconvert();
            this.indemniteFromEdit.setLibelleIndemnite(utfconvert.convertFromUTF8(indemnite.getLibelleIndemnite()));
            this.indemniteFromEdit.setEtatIndemnite(utfconvert.convertFromUTF8(indemnite.getEtatIndemnite()));
            ejbIndemnite.edit(this.indemniteFromEdit);
            return "indemnites";
        } catch (Exception e) {

            e.printStackTrace();
            return "edit_indemnite";
        }

    }

    public void valider() {
        try {

            this.indemniteFromEdit = (Indemnite) itemIndemnites.getRowData();
            this.indemniteFromEdit.setEtatIndemnite("Valide");
            ejbIndemnite.edit(this.indemniteFromEdit);

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public void invalider() {
        try {

            this.indemniteFromEdit = (Indemnite) itemIndemnites.getRowData();
            this.indemniteFromEdit.setEtatIndemnite("Invalide");
            ejbIndemnite.edit(this.indemniteFromEdit);

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public String supprimer() {
        try {
            indemniteFromDelete = (Indemnite) itemIndemnites.getRowData();
            ejbIndemnite.remove(indemniteFromDelete);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "indemnites";
    }

}
