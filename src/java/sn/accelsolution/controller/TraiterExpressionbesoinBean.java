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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import sn.accelsolution.dao.CommandeFacade;
import sn.accelsolution.dao.DetailleCommandeFacade;
import sn.accelsolution.dao.DetailleexpressionbesoinFacade;
import sn.accelsolution.dao.DevisFacade;
import sn.accelsolution.dao.ExpressionbesoinFacade;
import sn.accelsolution.dao.NotificationFacade;
import sn.accelsolution.dao.UtilisateurFacade;
import sn.accelsolution.entities.Commande;
import sn.accelsolution.entities.DetailleCommande;
import sn.accelsolution.entities.Detailleexpressionbesoin;
import sn.accelsolution.entities.Expressionbesoin;
import sn.accelsolution.entities.Notification;
import sn.accelsolution.entities.Utilisateur;
import sn.accelsolution.util.FactureUtil;
import sn.accelsolution.util.GenerationCodePdf;
import sn.accelsolution.util.MontantConverter;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DELL
 */
@ManagedBean
@ViewScoped
public class TraiterExpressionbesoinBean implements Serializable {

    /**
     * Creates a new instance of ContratRecrutBean
     */
    @EJB
    ExpressionbesoinFacade ejbExpressionbesoin;
    @EJB
    UtilisateurFacade ejbUser;
    @EJB
    DevisFacade ejbDevis;
    @EJB
    DetailleexpressionbesoinFacade ejbDetailleExpressionbesoin;
    @EJB
    CommandeFacade ejbCommande;
    @EJB
    DetailleCommandeFacade ejbDetailleCommande;
    @EJB
    NotificationFacade ejbNotification;

    private Expressionbesoin expressionbesoin;
    private Expressionbesoin expressionbesoinFromTraitement;
    private Detailleexpressionbesoin detailleExpressionbesoin;
    private Expressionbesoin expressionbesoinFromEdit;
    private Detailleexpressionbesoin detailleExpressionbesoinFromEdit;
    private List<Expressionbesoin> listExpressionbesoinsFromTraitement;
    private List<Expressionbesoin> allExpressionbesoins;
    private List<Detailleexpressionbesoin> allDetailleExpressionbesoins;
    private List<FactureUtil> listExpressionbesoins;
    private String mttc;
    private String etatValidation;
    private String etatExpression;
    private Expressionbesoin expressionbesoinFromTrmt;
    private Utilisateur userNotif;

    @PostConstruct
    public void init() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

        userNotif = new Utilisateur();
        userNotif = (Utilisateur) session.getAttribute("user");

        Expressionbesoin exp1 = new Expressionbesoin();
        expressionbesoin = new Expressionbesoin();
        exp1 = (Expressionbesoin) session.getAttribute("myExpression");
        System.out.println("Expression trouvée: " + exp1.getIdExpression());
        this.setExpressionbesoin(exp1);

        this.setExpressionbesoinFromTraitement(exp1);

        List<Detailleexpressionbesoin> lstEbFromt = ejbDetailleExpressionbesoin.listOfDetailleExpressionbesoinByExpression(exp1);

        FactureUtil fu = new FactureUtil();

        for (Detailleexpressionbesoin deb : lstEbFromt) {
            fu.setDesignation(deb.getDesignation());
            fu.setQtEbDemande(deb.getQuantite());
            fu.setDispoEbStock("");
            fu.setQtEbValide(0);
            listExpressionbesoins.add(fu);
        }

    }

    public TraiterExpressionbesoinBean() {

        listExpressionbesoins = new ArrayList<>();
        allExpressionbesoins = new ArrayList<>();
        allDetailleExpressionbesoins = new ArrayList<>();
        detailleExpressionbesoin = new Detailleexpressionbesoin();
        expressionbesoinFromEdit = new Expressionbesoin();
        detailleExpressionbesoinFromEdit = new Detailleexpressionbesoin();
        expressionbesoinFromTraitement = new Expressionbesoin();
        expressionbesoinFromTrmt = new Expressionbesoin();

    }

    public Utilisateur getUserNotif() {
        return userNotif;
    }

    public void setUserNotif(Utilisateur userNotif) {
        this.userNotif = userNotif;
    }

    public Expressionbesoin getExpressionbesoin() {
        return expressionbesoin;
    }

    public void setExpressionbesoin(Expressionbesoin expressionbesoin) {
        this.expressionbesoin = expressionbesoin;
    }

    public Expressionbesoin getExpressionbesoinFromTrmt() {
        return expressionbesoinFromTrmt;
    }

    public void setExpressionbesoinFromTrmt(Expressionbesoin expressionbesoinFromTrmt) {
        this.expressionbesoinFromTrmt = expressionbesoinFromTrmt;
    }

    public String getEtatExpression() {
        return etatExpression;
    }

    public void setEtatExpression(String etatExpression) {
        this.etatExpression = etatExpression;
    }

    public String getEtatValidation() {
        return etatValidation;
    }

    public void setEtatValidation(String etatValidation) {
        this.etatValidation = etatValidation;
    }

    public List<Expressionbesoin> getListExpressionbesoinsFromTraitement() {
        listExpressionbesoinsFromTraitement = ejbExpressionbesoin.listOfExpressionAttente();
        return listExpressionbesoinsFromTraitement;
    }

    public void setListExpressionbesoinsFromTraitement(List<Expressionbesoin> listExpressionbesoinsFromTraitement) {
        this.listExpressionbesoinsFromTraitement = listExpressionbesoinsFromTraitement;
    }

    public Expressionbesoin getExpressionbesoinFromTraitement() {
        return expressionbesoinFromTraitement;
    }

    public void setExpressionbesoinFromTraitement(Expressionbesoin expressionbesoinFromTraitement) {
        this.expressionbesoinFromTraitement = expressionbesoinFromTraitement;
    }

    public Detailleexpressionbesoin getDetailleExpressionbesoin() {
        return detailleExpressionbesoin;
    }

    public void setDetailleExpressionbesoin(Detailleexpressionbesoin detailleExpressionbesoin) {
        this.detailleExpressionbesoin = detailleExpressionbesoin;
    }

    public Expressionbesoin getExpressionbesoinFromEdit() {
        return expressionbesoinFromEdit;
    }

    public void setExpressionbesoinFromEdit(Expressionbesoin expressionbesoinFromEdit) {
        this.expressionbesoinFromEdit = expressionbesoinFromEdit;
    }

    public Detailleexpressionbesoin getDetailleExpressionbesoinFromEdit() {
        return detailleExpressionbesoinFromEdit;
    }

    public void setDetailleExpressionbesoinFromEdit(Detailleexpressionbesoin detailleExpressionbesoinFromEdit) {
        this.detailleExpressionbesoinFromEdit = detailleExpressionbesoinFromEdit;
    }

    public List<Expressionbesoin> getAllExpressionbesoins() {
        allExpressionbesoins = ejbExpressionbesoin.findAll();
        return allExpressionbesoins;
    }

    public void setAllExpressionbesoins(List<Expressionbesoin> allExpressionbesoins) {
        this.allExpressionbesoins = allExpressionbesoins;
    }

    public List<Detailleexpressionbesoin> getAllDetailleExpressionbesoins() {
        return allDetailleExpressionbesoins;
    }

    public void setAllDetailleExpressionbesoins(List<Detailleexpressionbesoin> allDetailleExpressionbesoins) {
        this.allDetailleExpressionbesoins = allDetailleExpressionbesoins;
    }

    public List<FactureUtil> getListExpressionbesoins() {
        return listExpressionbesoins;
    }

    public void setListExpressionbesoins(List<FactureUtil> listExpressionbesoins) {
        this.listExpressionbesoins = listExpressionbesoins;
    }

    public void renderInfo() {

        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addNewInputLigne() {
        FactureUtil fu = new FactureUtil();
        fu.setDesignation("");
        fu.setRef("");
        fu.setUnite("");
        fu.setPu("");
        fu.setQuantite(0);
        listExpressionbesoins.add(fu);
    }

    public void removeInputLigne(int index) {
        listExpressionbesoins.remove(index);
    }

    public String getMttc() {
        return mttc;
    }

    public void setMttc(String mttc) {
        this.mttc = mttc;
    }

    public void calculateTTC(FactureUtil f) {

        try {

            MontantConverter mtc = new MontantConverter();
            int index = 0;
            for (int x = 0; x < this.listExpressionbesoins.size(); x++) {

                FactureUtil f1 = this.listExpressionbesoins.get(x);
                if (f1.getDesignation().equalsIgnoreCase(f.getDesignation())) {
                    index = x;
                }
            }

            Double cv = mtc.StringToDouble(f.getPu());
            String newv = mtc.DoubleToString(cv);
            f.setPu(newv);
            this.listExpressionbesoins.set(index, f);

            Double mtht = 0.0;
            for (FactureUtil df : this.listExpressionbesoins) {

                Double puc = mtc.StringToDouble(df.getPu());
                Double ctotal = puc * df.getQuantite();
                Double mttvasub = ctotal * 0.18;
                Double mttcsub = ctotal + mttvasub;
                mtht = mtht + mttcsub;
            }
            String rctotal = mtc.DoubleToString(mtht);
            this.setMttc(rctotal);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String save() {

        try {

            this.expressionbesoin.setNivovalidation("nivo1");
            this.expressionbesoin.setEtat("En cours de validation");
            ejbExpressionbesoin.insertExpression(this.expressionbesoin);
            int idExp = ejbExpressionbesoin.maxExpression();
            Expressionbesoin expb = ejbExpressionbesoin.find(idExp);

            for (FactureUtil f : listExpressionbesoins) {
                this.detailleExpressionbesoin.setDesignation(f.getDesignation());
                this.detailleExpressionbesoin.setReference(f.getRef());
                this.detailleExpressionbesoin.setUnite(f.getUnite());
                this.detailleExpressionbesoin.setQuantite(f.getQuantite());
                this.detailleExpressionbesoin.setPu("0.0");
                this.detailleExpressionbesoin.setIdExpression(expb);
                ejbDetailleExpressionbesoin.insertDetailleExpression(this.detailleExpressionbesoin);
            }

            return "expressionbesoins";
        } catch (Exception e) {
            return "new_expression";
        }
    }

    public String reset() {
        this.expressionbesoin = new Expressionbesoin();
        return "expressionbesoins";
    }

    public String supprimer(Expressionbesoin expb) {
        try {

            ejbDetailleExpressionbesoin.deleteByExpression(expb.getIdExpression());
            ejbExpressionbesoin.remove(expb);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "expressionbesoins";
    }

    public void saveUpdateNivo2(Expressionbesoin Expb) {
        try {
            Expressionbesoin expb = ejbExpressionbesoin.find(Expb.getIdExpression());
            expb.setNivovalidation("nivo2");
            ejbExpressionbesoin.edit(expb);
        } catch (Exception e) {
        }
    }

    public void saveUpdateNivo3(Expressionbesoin Expb) {
        try {
            Expressionbesoin expb = ejbExpressionbesoin.find(Expb.getIdExpression());
            List<Detailleexpressionbesoin> listDesDetaillesExpressionsBesoins = ejbDetailleExpressionbesoin.listOfDetailleExpressionbesoinByExpression(expb);

            Commande cmd = new Commande();
            int maxCmd = ejbCommande.maxCommande();
            String numCmd = "ESTCMD" + maxCmd;
            cmd.setCode(numCmd);
            String datecmd = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
            cmd.setDateechance(datecmd);
            cmd.setIdMarche(expb.getIdChantier().getIdMarche());
            cmd.setIdChantier(expb.getIdChantier());
            cmd.setIdFournisseur(null);
            cmd.setTypecommande("Expression besoins");

            ejbCommande.insertCommandeExp(cmd);

            int idCmd = ejbCommande.maxCommande();
            Commande cmdFd = ejbCommande.find(idCmd);
            DetailleCommande cmddetaille = new DetailleCommande();

            for (Detailleexpressionbesoin f : listDesDetaillesExpressionsBesoins) {
                cmddetaille.setDesignation(f.getDesignation());
                cmddetaille.setReference(f.getReference());
                cmddetaille.setUnite(f.getUnite());
                cmddetaille.setQuantite(f.getQuantite());
                cmddetaille.setPu(f.getPu());
                cmddetaille.setIdCommande(cmdFd);
                ejbDetailleCommande.insertDetailleCommandePrim(cmddetaille);
            }

        } catch (Exception e) {
        }
    }

    public void renderInfoExpTraitement() {
        try {
            this.setExpressionbesoinFromTraitement(this.getExpressionbesoinFromTraitement());
        } catch (Exception e) {
        }
    }

    public void renderInfoExpTraitement2() {
        try {
            if (this.etatExpression.equals("Rejeté")) {
                this.setEtatValidation("Rejeter");
            }
        } catch (Exception e) {
        }
    }

    public String save2() {

        try {

            System.out.println("Valeur: " + this.expressionbesoinFromTraitement.getIdExpression());
//            UtilUtfconvert utfconvert = new UtilUtfconvert();
//            this.setEtatExpression(utfconvert.convertFromUTF8(this.getEtatExpression()));
//            this.expressionbesoinFromTraitement.setMotif(utfconvert.convertFromUTF8(this.expressionbesoinFromTraitement.getMotif()));
//            
            if (etatExpression.equals("Rejeté")) {

                this.expressionbesoinFromTraitement.setEtat("Rejeté");
                ejbExpressionbesoin.edit(this.expressionbesoinFromTraitement);

                Notification notif = ejbNotification.getNotificationByExpression(this.expressionbesoinFromTraitement);
                if (notif != null) {
                    notif.setTraitement("Fait");
                    ejbNotification.edit(notif);
                }

            } else {

                this.expressionbesoinFromTraitement.setEtat("Validé");
                ejbExpressionbesoin.edit(this.expressionbesoinFromTraitement);

                Notification notif = ejbNotification.getNotificationByExpression(this.expressionbesoinFromTraitement);
                if (notif != null) {
                    notif.setTraitement("Fait");
                    ejbNotification.edit(notif);
                }

                Expressionbesoin expb = ejbExpressionbesoin.find(this.expressionbesoinFromTraitement.getIdExpression());
                List<Detailleexpressionbesoin> listDesDetaillesExpressionsBesoins = ejbDetailleExpressionbesoin.listOfDetailleExpressionbesoinByExpression(expb);

                Commande cmd = new Commande();
                GenerationCodePdf gcpdf = new GenerationCodePdf();
                int maxCmd = ejbCommande.maxCommande();
                Commande mymaxCmd = ejbCommande.find(maxCmd);
                String ancienCode = mymaxCmd.getCode();
                String codeCommande = gcpdf.genererCodeCommande(ancienCode);
                String numCmd = codeCommande;
                cmd.setCode(numCmd);
                String datecmd = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
                cmd.setDateechance(datecmd);
                cmd.setIdMarche(expb.getIdChantier().getIdMarche());
                cmd.setIdChantier(expb.getIdChantier());
                cmd.setIdFournisseur(null);
                cmd.setEtat("En attente");
                cmd.setTypecommande("Exp besoin");
                cmd.setIdExpression(this.expressionbesoinFromTraitement);

                ejbCommande.insertCommandeExp(cmd);

                int idCmd = ejbCommande.maxCommande();
                Commande cmdFd = ejbCommande.find(idCmd);
                DetailleCommande cmddetaille = new DetailleCommande();

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

                notification.setMessage("Commande à valider");
                notification.setDateNotification(reportDate);
                notification.setHeure(heures);
                notification.setMinute(minutes);
                notification.setSeconde(seconds);
                notification.setTraitement("Non fait");
                notification.setTypeNotification("Commande");
                notification.setEtatNotification("Non lu");
                notification.setIdUtilisateur(this.userNotif);
                notification.setIdCommande(cmdFd);

                ejbNotification.insertNotification6(notification);

                for (Detailleexpressionbesoin f : listDesDetaillesExpressionsBesoins) {
                    cmddetaille.setDesignation(f.getDesignation());
                    cmddetaille.setReference(f.getReference());
                    cmddetaille.setUnite(f.getUnite());
                    cmddetaille.setQuantite(f.getQuantite());
                    cmddetaille.setPu("0.0");
                    cmddetaille.setIdCommande(cmdFd);
                    ejbDetailleCommande.insertDetailleCommandePrim(cmddetaille);
                }
            }

            return "expressionbesoins";
        } catch (Exception e) {

            return "first_traitement_expression";
        }
    }

}
