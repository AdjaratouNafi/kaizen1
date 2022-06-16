/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.controller;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import sn.accelsolution.dao.AcompteFacade;
import sn.accelsolution.dao.ActionmenuFacade;
import sn.accelsolution.dao.ChantierFacade;
import sn.accelsolution.dao.ClientFacade;
import sn.accelsolution.dao.DecomptepFacade;
import sn.accelsolution.dao.DetaillechantierFacade;
import sn.accelsolution.dao.DetailleCommandeFacade;
import sn.accelsolution.dao.DetailledecomptepFacade;
import sn.accelsolution.dao.DetailledevisFacade;
import sn.accelsolution.dao.DetaillenewfactureFacade;
import sn.accelsolution.dao.DevisFacade;
import sn.accelsolution.dao.DroitaccesFacade;
import sn.accelsolution.dao.LivraisonFacade;
import sn.accelsolution.dao.ManagementFacade;
import sn.accelsolution.dao.MarchandiseFacade;
import sn.accelsolution.dao.MarcheFacade;
import sn.accelsolution.dao.MenuFacade;
import sn.accelsolution.dao.NewfactureFacade;
import sn.accelsolution.dao.OperationFacade;
import sn.accelsolution.dao.PrestataireFacade;
import sn.accelsolution.dao.PrixFacade;
import sn.accelsolution.dao.RessourceFacade;
import sn.accelsolution.dao.SortiestockFacade;
import sn.accelsolution.dao.StockFacade;
import sn.accelsolution.dao.UtilisateurFacade;
import sn.accelsolution.entities.Acompte;
import sn.accelsolution.entities.Actionmenu;
import sn.accelsolution.entities.Chantier;
import sn.accelsolution.entities.Decomptep;
import sn.accelsolution.entities.Detaillechantier;
import sn.accelsolution.entities.Detailledecomptep;
import sn.accelsolution.entities.Detailledevis;
import sn.accelsolution.entities.Detaillenewfacture;
import sn.accelsolution.entities.Droitacces;
import sn.accelsolution.entities.Livraison;
import sn.accelsolution.entities.Management;
import sn.accelsolution.entities.Marchandise;
import sn.accelsolution.entities.Marche;
import sn.accelsolution.entities.Menu;
import sn.accelsolution.entities.Newfacture;
import sn.accelsolution.entities.Notification;
import sn.accelsolution.entities.Operation;
import sn.accelsolution.entities.Prestataire;
import sn.accelsolution.entities.Sortiestock;
import sn.accelsolution.entities.Stock;
import sn.accelsolution.entities.Utilisateur;
import sn.accelsolution.util.MontantConverter;
import sn.accelsolution.util.PasswordGenerate;
import sn.accelsolution.util.ReportMarches;
import sn.accelsolution.util.SendEmail;

/**
 *
 * @author DELL
 */
@ManagedBean
@ViewScoped
public class DeleteUserBean implements Serializable {

    @EJB
    DroitaccesFacade ejbdroitacces;

    @EJB
    MenuFacade ejbmenu;

    @EJB
    ActionmenuFacade ejbactionmenu;

    @EJB
    UtilisateurFacade ejbUtilisateur;

    private Utilisateur utilisateurFromEdit;

    /**
     * Creates a new instance of UtilisateurBean
     */
    public DeleteUserBean() {
        utilisateurFromEdit = new Utilisateur();
    }

    public Utilisateur getUtilisateurFromEdit() {
        return utilisateurFromEdit;
    }

    public void setUtilisateurFromEdit(Utilisateur utilisateurFromEdit) {
        this.utilisateurFromEdit = utilisateurFromEdit;
    }

    public String deleteUser(Utilisateur utilisateur) {
        System.out.println("Utilisateur: " + utilisateur.getAdresseUtilisateur());
        try {
            List<Droitacces> usersDroitacces = ejbdroitacces.listOfDroitByUtilisateur(utilisateur);

//            if(!usersDroitacces.isEmpty()){
//                
//            }else{
//                
//            } 
            for (Droitacces droit : usersDroitacces) {

                List<Menu> usersMenus = ejbmenu.listOfMenuByModule(droit);

                for (Menu menu : usersMenus) {

                    List<Actionmenu> usersActionmenus = ejbactionmenu.listOfActionByMenu(menu);

                    for (Actionmenu actionmenu : usersActionmenus) {
                        ejbactionmenu.remove(actionmenu);
                    }

                    ejbmenu.remove(menu);
                }
                ejbdroitacces.remove(droit);
            }

            ejbUtilisateur.remove(utilisateur);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "users";
    }

    public String resetPassword(Utilisateur utilisateurPassword) {
        System.out.println("Utilisateur: " + utilisateurPassword.getAdresseUtilisateur());
        try {

            SendEmail sendEml = new SendEmail();
            PasswordGenerate pwg = new PasswordGenerate();
            String gpwd = pwg.generatePassword2();
            LocalDateTime now = LocalDateTime.now();
            int hour = now.getHour();
            int minute = now.getMinute();
            int second = now.getSecond();

            utilisateurPassword.setPasswordUtisateur(gpwd);
            utilisateurPassword.setHcreationpwd(hour);
            utilisateurPassword.setMcreationpwd(minute);
            utilisateurPassword.setScreationpwd(second);
            utilisateurPassword.setEtatpwd("Invalide");
            utilisateurPassword.setEtatcompte("Invalide");
            utilisateurPassword.setFirstconnection("Non");

            ejbUtilisateur.edit(utilisateurPassword);

            boolean result = sendEml.prepareEmail(utilisateurPassword.getMailUtilisateur(), utilisateurPassword.getNomUtilisateur(), utilisateurPassword.getPrenomUtilisateur(), gpwd);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "users";
    }

    public String modifierUser(Utilisateur utilisateur) {
        System.out.println("Utilisateur: " + utilisateur.getAdresseUtilisateur());
        this.setUtilisateurFromEdit(utilisateurFromEdit);
        HttpSession session1 = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        session1.setAttribute("myUtilisateur", utilisateur);
        return "edit_users";
    }

}
