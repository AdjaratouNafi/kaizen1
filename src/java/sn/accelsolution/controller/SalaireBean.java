/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.controller;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.event.FlowEvent;
import sn.accelsolution.dao.ContratFacade;
import sn.accelsolution.dao.NotificationFacade;
import sn.accelsolution.dao.PretFacade;
import sn.accelsolution.dao.RembourssementFacade;
import sn.accelsolution.dao.SalaireFacade;
import sn.accelsolution.dao.UtilisateurFacade;
import sn.accelsolution.entities.Actionmenu;
import sn.accelsolution.entities.Contrat;
import sn.accelsolution.entities.Notification;
import sn.accelsolution.entities.Pret;
import sn.accelsolution.entities.Rembourssement;
import sn.accelsolution.entities.Salaire;
import sn.accelsolution.entities.Utilisateur;
import sn.accelsolution.util.Bulletin;
import sn.accelsolution.util.ReportAccelModel;
import sn.accelsolution.util.ReportContrat;
import sn.accelsolution.util.ReportSalaire;
import sn.accelsolution.util.UtilControleMenu;  

/**
 *
 * @author DELL
 */
@ManagedBean
@RequestScoped
public class SalaireBean implements Serializable {

    /**
     * Creates a new instance of ContratRecrutBean
     */
    @EJB
    UtilisateurFacade ejbUser;
    @EJB
    ContratFacade ejbContrat;
    @EJB
    SalaireFacade ejbSalaire;
    @EJB
    PretFacade ejbPret;
    @EJB
    RembourssementFacade ejbRembourssement;
    @EJB
    NotificationFacade ejbNotification;

    private Utilisateur user;
    private Contrat contrat;
    private Salaire salaire;
    private Salaire salaireFromRead;
    private Salaire salaireFromEdit;
    private Salaire salaireFromDelete;
    private ReportContrat reportContrat;

    private boolean skip;
    private DataModel itemSalaires;
    private List listSalaires;
    private List listUsers;
    private List listUsersWithoutAdmin;
    private List listSalaireWithoutImprimer;
    private List listContrats;
    private String typeSalaire;
    private Utilisateur userFromSalaire;
    private Salaire salaireFromReport;
    private Pret pret;
    private List<Actionmenu> myllActionmenus;
    private String creerSalaire;
    private String modifierSalaire;
    private String supprimerSalaire;
    private String consulterSalaire;
    private String imprimerSalaire;
    private String validerSalaire;
    private Utilisateur userNotif;

    @PostConstruct
    public void init() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UtilControleMenu utilControleMenu = new UtilControleMenu();

        userNotif = new Utilisateur();
        userNotif = (Utilisateur) session.getAttribute("user");

        myllActionmenus = (List<Actionmenu>) session.getAttribute("allActionmenusFromTraitementSalaire");

        this.setCreerSalaire(utilControleMenu.creerSalaire(myllActionmenus));
        this.setModifierSalaire(utilControleMenu.modifierSalaire(myllActionmenus));
        this.setSupprimerSalaire(utilControleMenu.supprimerSalaire(myllActionmenus));
        this.setConsulterSalaire(utilControleMenu.consulterSalaire(myllActionmenus));
        this.setImprimerSalaire(utilControleMenu.imprimerSalaire(myllActionmenus));
        this.setValiderSalaire(utilControleMenu.validerSalaire(myllActionmenus));
    }

    public SalaireBean() {
        user = new Utilisateur();
        salaire = new Salaire();
        reportContrat = new ReportContrat();
        contrat = new Contrat();
        salaireFromRead = new Salaire();
        salaireFromEdit = new Salaire();
        salaireFromDelete = new Salaire();
        userFromSalaire = new Utilisateur();
        salaireFromReport = new Salaire();
        pret = new Pret();

    }

    public Utilisateur getUserNotif() {
        return userNotif;
    }

    public void setUserNotif(Utilisateur userNotif) {
        this.userNotif = userNotif;
    }

    public String getValiderSalaire() {
        return validerSalaire;
    }

    public void setValiderSalaire(String validerSalaire) {
        this.validerSalaire = validerSalaire;
    }

    public List<Actionmenu> getMyllActionmenus() {
        return myllActionmenus;
    }

    public void setMyllActionmenus(List<Actionmenu> myllActionmenus) {
        this.myllActionmenus = myllActionmenus;
    }

    public String getCreerSalaire() {
        return creerSalaire;
    }

    public void setCreerSalaire(String creerSalaire) {
        this.creerSalaire = creerSalaire;
    }

    public String getModifierSalaire() {
        return modifierSalaire;
    }

    public void setModifierSalaire(String modifierSalaire) {
        this.modifierSalaire = modifierSalaire;
    }

    public String getSupprimerSalaire() {
        return supprimerSalaire;
    }

    public void setSupprimerSalaire(String supprimerSalaire) {
        this.supprimerSalaire = supprimerSalaire;
    }

    public String getConsulterSalaire() {
        return consulterSalaire;
    }

    public void setConsulterSalaire(String consulterSalaire) {
        this.consulterSalaire = consulterSalaire;
    }

    public String getImprimerSalaire() {
        return imprimerSalaire;
    }

    public void setImprimerSalaire(String imprimerSalaire) {
        this.imprimerSalaire = imprimerSalaire;
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

    public Salaire getSalaire() {
        return salaire;
    }

    public void setSalaire(Salaire salaire) {
        this.salaire = salaire;
    }

    public List getListUsers() {
        return listUsers;
    }

    public void setListUsers(List listUsers) {
        this.listUsers = listUsers;
    }

    public List getListUsersWithoutAdmin() {
        listUsersWithoutAdmin = ejbUser.listOfUserWithoutAdmin();
        return listUsersWithoutAdmin;
    }

    public void setListUsersWithoutAdmin(List listUsersWithoutAdmin) {
        this.listUsersWithoutAdmin = listUsersWithoutAdmin;
    }

    public List getListSalaireWithoutImprimer() {
        listSalaireWithoutImprimer = ejbSalaire.listOfSalaireWithoutImpayer();
        return listSalaireWithoutImprimer;
    }

    public void setListSalaireWithoutImprimer(List listSalaireWithoutImprimer) {
        this.listSalaireWithoutImprimer = listSalaireWithoutImprimer;
    }

    public String getTypeSalaire() {
        return typeSalaire;
    }

    public void setTypeSalaire(String typeSalaire) {
        this.typeSalaire = typeSalaire;
    }

    public Contrat getContrat() {
        return contrat;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }

    public Pret getPret() {
        return pret;
    }

    public void setPret(Pret pret) {
        this.pret = pret;
    }

    public void renderInfoUser() {

        this.user = this.salaire.getIdUtilisateur();
        this.contrat = ejbContrat.findContratByUser(user);

    }

    public DataModel getItemSalaires() {
        itemSalaires = new ListDataModel();
        this.setListSalaires(ejbSalaire.findAll());
        itemSalaires.setWrappedData(this.getListSalaires());
        return itemSalaires;
    }

    public void setItemSalaires(DataModel itemSalaires) {
        this.itemSalaires = itemSalaires;
    }

    public List getListSalaires() {
        listSalaires = ejbSalaire.findAll();
        return listSalaires;
    }

    public void setListSalaires(List listSalaires) {
        this.listSalaires = listSalaires;
    }

    public Salaire getSalaireFromRead() {
        return salaireFromRead;
    }

    public void setSalaireFromRead(Salaire salaireFromRead) {
        this.salaireFromRead = salaireFromRead;
    }

    public Salaire getSalaireFromDelete() {
        return salaireFromDelete;
    }

    public void setSalaireFromDelete(Salaire salaireFromDelete) {
        this.salaireFromDelete = salaireFromDelete;
    }

    public Salaire getSalaireFromEdit() {
        return salaireFromEdit;
    }

    public void setSalaireFromEdit(Salaire salaireFromEdit) {
        this.salaireFromEdit = salaireFromEdit;
    }

    public Utilisateur getUserFromSalaire() {
        return userFromSalaire;
    }

    public void setUserFromSalaire(Utilisateur userFromSalaire) {
        this.userFromSalaire = userFromSalaire;
    }

    public Salaire getSalaireFromReport() {
        return salaireFromReport;
    }

    public void setSalaireFromReport(Salaire salaireFromReport) {
        this.salaireFromReport = salaireFromReport;
    }

    public String save() {
        try {
            this.salaire.setEtat("Impayer");
            ejbSalaire.insertSalaire2(this.salaire);

            int idS = ejbSalaire.maxSalaire();
            Salaire salr = ejbSalaire.find(idS);

            Notification notification = new Notification();

            /*Creation de la notification*/
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

            Date today = Calendar.getInstance().getTime();

            String reportDate = df.format(today);

            LocalDateTime now = LocalDateTime.now();
            int hour = now.getHour();
            String heures = String.valueOf(hour);
            int minute = now.getMinute();
            String minutes = String.valueOf(minute);
            int second = now.getSecond();
            String seconds = String.valueOf(second);

            notification.setMessage("Plannification de salaire à valider");
            notification.setDateNotification(reportDate);
            notification.setHeure(heures);
            notification.setMinute(minutes);
            notification.setSeconde(seconds);
            notification.setTraitement("Non fait");
            notification.setTypeNotification("Salaire");
            notification.setEtatNotification("Non lu");
            notification.setIdUtilisateur(this.userNotif);
            notification.setIdSalaire(salr);

            ejbNotification.insertNotification2(notification);

            List<Pret> lesprets = ejbPret.listOfPretNonPayer();
            Salaire sal = ejbSalaire.findSalaireByPeriode(this.salaire);

            for (Pret p : lesprets) {

                this.pret = p;

                if (this.pret.getPremierpayement().equals("Oui")) {

                    Double mr = Double.parseDouble(this.pret.getMontantR());
                    Double map = Double.parseDouble(this.pret.getMontanAp());
                    Double mR1 = mr - map;
                    String mR2 = String.format("%.3f", mR1);
                    String newMR = mR2.replace(",", ".");
                    int newpr = this.pret.getPerioderestante() - 1;

                    this.pret.setMontantR(newMR);
                    this.pret.setPerioderestante(newpr);
                    this.pret.setPremierpayement("Non");

                    ejbPret.edit(this.pret);

                } else {

                    Double mr = Double.parseDouble(this.pret.getMontantR());
                    Double map = Double.parseDouble(this.pret.getMontanAp());
                    Double mR1 = mr - map;
                    String mR2 = String.format("%.3f", mR1);
                    String newMR = mR2.replace(",", ".");
                    int newpr = this.pret.getPerioderestante() - 1;

                    this.pret.setMontantR(newMR);
                    this.pret.setPerioderestante(newpr);

                    if (newpr == 0) {
                        this.pret.setCloture("Oui");
                        this.pret.setEtatPret("Remboursé");
                    }

                    ejbPret.edit(this.pret);

                }

                Rembourssement rbs = new Rembourssement();
                rbs.setIdPret(this.pret);
                rbs.setIdSalaire(sal);

                ejbRembourssement.insertRembourssement(rbs);

            }

            /*Repport*/
            /*this.contrat = ejbContrat.findContratByUser(this.salaire.getIdUtilisateur());
             System.out.println("Contrat trouvé :" + this.contrat);
             reportContrat.generateSalaire(this.salaire, this.contrat);*/
            return "salaires";
        } catch (Exception e) {
            return "new_salaire";
        }
    }

    public void Bulletin() {
//        ReportSalaire rp = new ReportSalaire();
//        rp.generateCDD();

    }

    public String consulter() {
        try {
            this.salaireFromRead = (Salaire) itemSalaires.getRowData();
            this.contrat = ejbContrat.findContratByUser(this.salaireFromRead.getIdUtilisateur());
            /*ReportSalaire rp = new ReportSalaire();
             rp.generateCDD();*/

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "consulter_salaire";
    }

    public String generateBulletin() {
        try {
            /*this.salaireFromRead = ejbSalaire.find(this.salaireFromRead.getIdSalaire());
             this.contrat = ejbContrat.findContratByUser(this.salaireFromRead.getIdUtilisateur());
             this.reportContrat.generateSalaire(this.salaireFromRead, this.contrat);*/

            //ReportSalaire rps = new ReportSalaire();
            ReportAccelModel rps = new ReportAccelModel();
            Bulletin bulletin = new Bulletin();
            Contrat contratFromBulletin = ejbContrat.findContratByUser(this.userFromSalaire);

            bulletin.setPeriode(this.salaireFromReport.getPeriode());
            bulletin.setNom(this.userFromSalaire.getPrenomUtilisateur() + " " + this.userFromSalaire.getNomUtilisateur());
            bulletin.setSituation(this.userFromSalaire.getSituationfamille());
            bulletin.setPartir("2,5");
            bulletin.setSalbase(contratFromBulletin.getSalairebase());
            bulletin.setSursalaire(contratFromBulletin.getSurSalaire());

            Double val1 = Double.parseDouble(contratFromBulletin.getSalairebase());
            Double val2 = Double.parseDouble(contratFromBulletin.getSurSalaire());
            Double rs1 = val1 + val2;
            String rsf1 = String.format("%.3f", rs1);
            bulletin.setTotalbrut(rsf1);

            Double val3 = 2.4 * rs1;
            Double val3P = val3 / 100;
            String rsf2 = String.format("%.3f", val3P);
            bulletin.setIpresrc(rsf2);

            Double val4 = 3.6 * rs1;
            Double val4P = val4 / 100;
            String rsf3 = String.format("%.3f", val4P);
            bulletin.setIpresrcc(rsf3);

            Double val5 = 3 * rs1;
            Double val5P = val5 / 100;
            String rsf4 = String.format("%.3f", val5P);
            bulletin.setCfcee(rsf4);

            Double net1 = 169.462 + 3.000 + 20.160 + val3P;
            Double net2 = rs1 - net1;

            /*Retret du pret*/
            List<Rembourssement> rbsstList = ejbRembourssement.listOfRembourssementBySalaire(this.salaireFromReport, this.userFromSalaire);
            Boolean pret = false;
            Double mtPret = 0.0;
            for (Rembourssement rbt : rbsstList) {
                if (rbt.getIdPret().getCloture().equals("Non")) {
                    pret = true;
                    mtPret = Double.parseDouble(rbt.getIdPret().getMontanAp());
                }
            }
            bulletin.setPret(pret);
            Double calculenet = 0.0;
            if (bulletin.getPret() == true) {
                calculenet = net2 - mtPret;
            } else {
                calculenet = net2;
            }
            String newMtPret = String.format("%.3f", mtPret);
            bulletin.setMtPret(newMtPret);

            String rsf5 = String.format("%.3f", calculenet);
            bulletin.setNet(rsf5);

            rps.generateBulletinSalaire(bulletin);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "salaires";
    }

    public String valider() {
        try {
            salaireFromEdit = (Salaire) itemSalaires.getRowData();
            salaireFromEdit.setEtat("Payer");
            ejbSalaire.edit(salaireFromEdit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "salaires";
    }

    public String precedent() {

        /*try {
         ReportSalaire rps = new ReportSalaire();
         rps.TestSauvegarde();

         } catch (Exception e) {
         e.printStackTrace();
         }*/
        return "salaires";
    }

    public String supprimer() {
        try {
            salaireFromDelete = (Salaire) itemSalaires.getRowData();
            ejbSalaire.remove(salaireFromDelete);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "salaires";
    }

    public String traiterSalaire() {
        salaireFromDelete = (Salaire) itemSalaires.getRowData();
        Notification notification = ejbNotification.getNotificationBySalaire(salaireFromDelete);
        HttpSession session1 = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        session1.setAttribute("myNotificationSalaire", notification);
        return "traitement_notificationSalaire";
    }

}
