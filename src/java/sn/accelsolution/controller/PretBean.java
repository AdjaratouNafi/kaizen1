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
import sn.accelsolution.dao.PretFacade;
import sn.accelsolution.dao.UtilisateurFacade;
import sn.accelsolution.entities.Actionmenu;
import sn.accelsolution.entities.Pret;
import sn.accelsolution.entities.Salaire;
import sn.accelsolution.entities.Utilisateur;
import sn.accelsolution.util.UtilControleMenu;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DELL
 */
@ManagedBean
@RequestScoped
public class PretBean implements Serializable {

    /**
     * Creates a new instance of ContratRecrutBean
     */
    @EJB
    UtilisateurFacade ejbUser;
    @EJB
    PretFacade ejbPret;

    private Utilisateur user;
    private Pret pret;
    private Pret preFromEdit;
    private Pret preFromDelete;

    private boolean skip;
    private DataModel itemPrets;
    private List listPrets;
    private List listUsers;
    private List<Actionmenu> myllActionmenus;
    private String creerPret;
    private String modifierPret;
    private String supprimerPret;
    private String consulterPret;
    private String imprimerPret;
    private String validerPret;

    @PostConstruct
    public void init() {

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UtilControleMenu utilControleMenu = new UtilControleMenu();

        myllActionmenus = (List<Actionmenu>) session.getAttribute("allActionmenusFromTraitementPrets");

        this.setCreerPret(utilControleMenu.creerPret(myllActionmenus));
        this.setModifierPret(utilControleMenu.modifierPret(myllActionmenus));
        this.setSupprimerPret(utilControleMenu.supprimerPret(myllActionmenus));
        this.setConsulterPret(utilControleMenu.consulterPret(myllActionmenus));
        this.setImprimerPret(utilControleMenu.imprimerPret(myllActionmenus));
        this.setValiderPret(utilControleMenu.validerPret(myllActionmenus));
    }

    public PretBean() {

        user = new Utilisateur();
        pret = new Pret();
        preFromEdit = new Pret();
        preFromDelete = new Pret();

    }

    public List<Actionmenu> getMyllActionmenus() {
        return myllActionmenus;
    }

    public void setMyllActionmenus(List<Actionmenu> myllActionmenus) {
        this.myllActionmenus = myllActionmenus;
    }

    public String getCreerPret() {
        return creerPret;
    }

    public void setCreerPret(String creerPret) {
        this.creerPret = creerPret;
    }

    public String getModifierPret() {
        return modifierPret;
    }

    public void setModifierPret(String modifierPret) {
        this.modifierPret = modifierPret;
    }

    public String getSupprimerPret() {
        return supprimerPret;
    }

    public void setSupprimerPret(String supprimerPret) {
        this.supprimerPret = supprimerPret;
    }

    public String getConsulterPret() {
        return consulterPret;
    }

    public void setConsulterPret(String consulterPret) {
        this.consulterPret = consulterPret;
    }

    public String getImprimerPret() {
        return imprimerPret;
    }

    public void setImprimerPret(String imprimerPret) {
        this.imprimerPret = imprimerPret;
    }

    public String getValiderPret() {
        return validerPret;
    }

    public void setValiderPret(String validerPret) {
        this.validerPret = validerPret;
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

    public Pret getPret() {
        return pret;
    }

    public void setPret(Pret pret) {
        this.pret = pret;
    }

    public Pret getPreFromEdit() {
        return preFromEdit;
    }

    public void setPreFromEdit(Pret preFromEdit) {
        this.preFromEdit = preFromEdit;
    }

    public Pret getPreFromDelete() {
        return preFromDelete;
    }

    public void setPreFromDelete(Pret preFromDelete) {
        this.preFromDelete = preFromDelete;
    }

    public DataModel getItemPrets() {
        itemPrets = new ListDataModel();
        this.setListPrets(ejbPret.findAll());
        itemPrets.setWrappedData(this.getListPrets());
        return itemPrets;
    }

    public void setItemPrets(DataModel itemPrets) {
        this.itemPrets = itemPrets;
    }

    public List getListPrets() {
        listPrets = ejbPret.findAll();
        return listPrets;
    }

    public void setListPrets(List listPrets) {
        this.listPrets = listPrets;
    }

    public void renderInfoUser() {

        this.user = this.pret.getIdUtilisateur();

    }

    public void renderInfoUserr() {

        this.user = this.preFromEdit.getIdUtilisateur();

    }

    public String savePret() {
        try {
            this.pret.setEtatPret("Demande");

            Double mp1 = Double.parseDouble(this.pret.getMontantPret());
            int nbm = this.pret.getSuivi();
            Double mp2 = mp1 / nbm;
            String mp = String.format("%.3f", mp2);
            String replace = mp.replace(",", ".");
            this.pret.setMontanAp(replace);

            this.pret.setMontantR(this.pret.getMontantPret());

            this.pret.setPerioderestante(this.pret.getSuivi());

            this.pret.setCloture("Non");
            this.pret.setPremierpayement("Oui");

            ejbPret.insertPret(pret);

            return "prets";
        } catch (Exception e) {
            return "new_pret";
        }
    }

    public String editer() {
        try {
            this.preFromEdit = (Pret) itemPrets.getRowData();
            this.user = this.preFromEdit.getIdUtilisateur();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "edit_pret";
    }

    public String update() {
        try {

            Double mp1 = Double.parseDouble(this.preFromEdit.getMontantPret());
            int nbm = this.preFromEdit.getSuivi();
            Double mp2 = mp1 / nbm;
            String mp = String.format("%.3f", mp2);
            String replace = mp.replace(",", ".");
            this.preFromEdit.setMontanAp(replace);

            this.preFromEdit.setMontantR(this.preFromEdit.getMontantPret());

            this.preFromEdit.setPerioderestante(this.preFromEdit.getSuivi());

            this.preFromEdit.setCloture("Non");
            this.preFromEdit.setPremierpayement("Oui");

            UtilUtfconvert utfconvert = new UtilUtfconvert();
            this.preFromEdit.setMotifPret(utfconvert.convertFromUTF8(preFromEdit.getMotifPret()));
            this.preFromEdit.setEtatPret(utfconvert.convertFromUTF8(preFromEdit.getEtatPret()));
            ejbPret.edit(this.preFromEdit);
            return "prets";
        } catch (Exception e) {

            e.printStackTrace();
            return "edit_pret";
        }

    }

    public String supprimer() {
        try {
            preFromDelete = (Pret) itemPrets.getRowData();
            ejbPret.remove(preFromDelete);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "prets";
    }

    public void valider() {
        try {

            this.preFromEdit = (Pret) itemPrets.getRowData();
            this.preFromEdit.setEtatPret("Validé");
            ejbPret.edit(this.preFromEdit);

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public void percu() {
        try {

            this.preFromEdit = (Pret) itemPrets.getRowData();
            this.preFromEdit.setEtatPret("Perçu");
            ejbPret.edit(this.preFromEdit);

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public void rembourser() {
        try {

            this.preFromEdit = (Pret) itemPrets.getRowData();
            this.preFromEdit.setEtatPret("Remboursé");
            ejbPret.edit(this.preFromEdit);

        } catch (Exception e) {

            e.printStackTrace();
        }

    }
}
