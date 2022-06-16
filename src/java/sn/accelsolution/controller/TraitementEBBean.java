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
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import sn.accelsolution.dao.CommandeFacade;
import sn.accelsolution.dao.DeboursserFacade;
import sn.accelsolution.dao.DetailleCommandeFacade;
import sn.accelsolution.dao.DetailleexpressionbesoinFacade;
import sn.accelsolution.dao.ExpressionbesoinFacade;
import sn.accelsolution.dao.FournisseurFacade;
import sn.accelsolution.dao.MarchandiseFacade;
import sn.accelsolution.dao.MouvementdebourseFacade;
import sn.accelsolution.dao.NotificationFacade;
import sn.accelsolution.dao.PrixFacade;
import sn.accelsolution.dao.StockFacade;
import sn.accelsolution.dao.UtilisateurFacade;
import sn.accelsolution.entities.Commande;
import sn.accelsolution.entities.Deboursser;
import sn.accelsolution.entities.DetailleCommande;
import sn.accelsolution.entities.Detailleexpressionbesoin;
import sn.accelsolution.entities.Expressionbesoin;
import sn.accelsolution.entities.Fournisseur;
import sn.accelsolution.entities.Marchandise;
import sn.accelsolution.entities.Mouvementdebourse;
import sn.accelsolution.entities.Notification;
import sn.accelsolution.entities.Prix;
import sn.accelsolution.entities.Stock;
import sn.accelsolution.entities.Utilisateur;
import sn.accelsolution.util.FactureUtil;
import sn.accelsolution.util.GenerationCodePdf;

/**
 *
 * @author DELL
 */
@ManagedBean
@ViewScoped
public class TraitementEBBean implements Serializable {

    /**
     * Creates a new instance of ContratRecrutBean
     */
    @EJB
    UtilisateurFacade ejbUser;
    @EJB
    DetailleexpressionbesoinFacade ejbDetailleexpressionbesoin;
    @EJB
    MarchandiseFacade ejbMarchandise;
    @EJB
    StockFacade ejbStock;
    @EJB
    CommandeFacade ejbCommande;
    @EJB
    DetailleCommandeFacade ejbDetailleCommande;
    @EJB
    PrixFacade ejbPrix;
    @EJB
    FournisseurFacade ejbFournisseur;
    @EJB
    NotificationFacade ejbNotification;
    @EJB
    ExpressionbesoinFacade ejbExpressionbesoin;
    @EJB
    DeboursserFacade ejbDeboursser;
    @EJB
    MouvementdebourseFacade ejbMouvementdebourse;

    private Expressionbesoin expressionbesoin;
    private List<Detailleexpressionbesoin> listDetailleexpressionbesoin;
    private List<FactureUtil> listEb;
    private String texttest;
    private Expressionbesoin expressionbesoinFromTraitement;
    private Utilisateur userNotif;
    private String emtyVakue;
    private Notification notif;

    @PostConstruct
    public void init() {

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

        userNotif = new Utilisateur();
        notif = new Notification();
        this.expressionbesoin = new Expressionbesoin();
        expressionbesoinFromTraitement = new Expressionbesoin();

        this.emtyVakue = "";
        userNotif = (Utilisateur) session.getAttribute("user");
        this.expressionbesoin = (Expressionbesoin) session.getAttribute("myExpression");

        this.listDetailleexpressionbesoin = ejbDetailleexpressionbesoin.listOfDetailleExpressionbesoinByExpression(this.expressionbesoin);

        for (Detailleexpressionbesoin deb : this.listDetailleexpressionbesoin) {
            String disponibilite = "";
            System.out.println("EB detail: " + deb.getDesignation());
            FactureUtil fut = new FactureUtil();
            Marchandise mchd = ejbMarchandise.findMarchandiseByLibelle(deb.getDesignation());
            List<Stock> listStock = ejbStock.listOfStockByMarchandise(mchd);
            List<Deboursser> listDeboursser = ejbDeboursser.listOfDeboursserByMarchandise(mchd);
            int nbEmtSup = 0;
            /*for (Stock stk : listStock) {
                if (stk.getQtStock() > stk.getQtSeuille()) {
                    nbEmtSup++;
                }
            }*/

            //int dif = 0;
            int cptQt = 0;
            for (Stock stk : listStock) {

                if (stk.getQtStock() > stk.getQtSeuille()) {
                    if (stk.getQtStock() < deb.getQuantite()) {
                        int dif = deb.getQuantite() - stk.getQtStock();
                        cptQt = cptQt + dif;
                    }
                    fut.setIdDetailleCommande(deb.getIdDetailleExpression());
                    fut.setDesignation(deb.getDesignation());
                    fut.setQtEbDemande(deb.getQuantite());
                    fut.setQtDispoStock(stk.getQtStock());
                    fut.setEntrepot(stk.getIdEntrepot());
                    fut.setUnite(deb.getUnite());
                    fut.setRef(deb.getReference());
                    fut.setNomEntrepot(stk.getIdEntrepot().getNom());
                    fut.setStock(stk);
                    fut.setStockOuDs("stock");

                    System.err.println("Detail: " + fut);

                    this.listEb.add(fut);
                }
            }
            /*Rechercher ds deboursser sec si stock pas suffisant ou absent*/
            /*if (cptQt < deb.getQuantite()) {
                for (Deboursser debour : listDeboursser) {
                    if (deb.getDesignation().equals(debour.getIdMateriel().getLibelle())) {
                        int qtDeb = Integer.parseInt(debour.getQuantite());
                        if (qtDeb > 0) {
                            fut.setIdDetailleCommande(deb.getIdDetailleExpression());
                            fut.setDesignation(deb.getDesignation());
                            fut.setQtEbDemande(deb.getQuantite());
                            fut.setQtDispoStock(qtDeb);
                            fut.setDeboursser(debour);
                            fut.setUnite(deb.getUnite());
                            fut.setRef(deb.getReference());
                            fut.setNomEntrepot("");
                            fut.setStockOuDs("deboursser");

                            System.err.println("Detail: " + fut);

                            this.listEb.add(fut);
                        } else {
                            fut.setIdDetailleCommande(deb.getIdDetailleExpression());
                            fut.setDesignation(deb.getDesignation());
                            fut.setQtEbDemande(deb.getQuantite());
                            fut.setQtDispoStock(0);
                            fut.setUnite(deb.getUnite());
                            fut.setRef(deb.getReference());
                            fut.setNomEntrepot("");
                            fut.setStockOuDs("deboursser");

                            System.err.println("Detail: " + fut);

                            this.listEb.add(fut);
                        }
                    }
                }

            }*/

        }

        this.setExpressionbesoinFromTraitement(
                this.expressionbesoin);

    }

    public TraitementEBBean() {
        this.listEb = new ArrayList<>();
        expressionbesoinFromTraitement = new Expressionbesoin();
    }

    public Notification getNotif() {
        return notif;
    }

    public void setNotif(Notification notif) {
        this.notif = notif;
    }

    public String getEmtyVakue() {
        return emtyVakue;
    }

    public void setEmtyVakue(String emtyVakue) {
        this.emtyVakue = emtyVakue;
    }

    public Utilisateur getUserNotif() {
        return userNotif;
    }

    public void setUserNotif(Utilisateur userNotif) {
        this.userNotif = userNotif;
    }

    public Expressionbesoin getExpressionbesoinFromTraitement() {
        return expressionbesoinFromTraitement;
    }

    public void setExpressionbesoinFromTraitement(Expressionbesoin expressionbesoinFromTraitement) {
        this.expressionbesoinFromTraitement = expressionbesoinFromTraitement;
    }

    public String getTexttest() {
        return texttest;
    }

    public void setTexttest(String texttest) {
        this.texttest = texttest;
    }

    public List<FactureUtil> getListEb() {
        return listEb;
    }

    public void setListEb(List<FactureUtil> listEb) {
        this.listEb = listEb;
    }

    public Expressionbesoin getExpressionbesoin() {
        return expressionbesoin;
    }

    public void setExpressionbesoin(Expressionbesoin expressionbesoin) {
        this.expressionbesoin = expressionbesoin;
    }

    public List<Detailleexpressionbesoin> getListDetailleexpressionbesoin() {
        return listDetailleexpressionbesoin;
    }

    public void setListDetailleexpressionbesoin(List<Detailleexpressionbesoin> listDetailleexpressionbesoin) {
        this.listDetailleexpressionbesoin = listDetailleexpressionbesoin;
    }

    public String save2() {

        try {

            System.out.println("Valeur: " + this.expressionbesoinFromTraitement.getIdExpression());

            this.expressionbesoinFromTraitement.setEtat("Validé");
            ejbExpressionbesoin.edit(this.expressionbesoinFromTraitement);

            Notification notif = ejbNotification.getNotificationByExpression(this.expressionbesoinFromTraitement);
            if (notif != null) {
                notif.setTraitement("Fait");
                ejbNotification.edit(notif);
            }

            Expressionbesoin expb = ejbExpressionbesoin.find(this.expressionbesoinFromTraitement.getIdExpression());
            List<Detailleexpressionbesoin> listDesDetaillesExpressionsBesoins = ejbDetailleexpressionbesoin.listOfDetailleExpressionbesoinByExpression(expb);

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

            for (Detailleexpressionbesoin debp : listDesDetaillesExpressionsBesoins) {

                int qtTt = 0;
                int gestrionEntrepo = 0;
                int qtStockTrouve = 0;
                int qtDeboursserTrouve = 0;
                int qtStockTrouveValide = 0;
                int qtDeboursserTrouveValide = 0;
                int qtStockTrouveDiff = 0;

                /*Totaliser la qte trouvée en stock et la qt validée pour un produit donné*/
                for (FactureUtil f : this.listEb) {
                    if (f.getStockOuDs().equals("stock") && debp.getIdDetailleExpression() == f.getIdDetailleCommande()) {
                        qtStockTrouve = qtStockTrouve + f.getQtDispoStock();
                        qtStockTrouveValide = qtStockTrouveValide + f.getQtEbValide();
                    }

                    /*if (f.getStockOuDs().equals("deboursser") && debp.getIdDetailleExpression() == f.getIdDetailleCommande()) {
                        qtDeboursserTrouve = qtDeboursserTrouve + f.getQtDispoStock();
                        qtDeboursserTrouveValide = qtDeboursserTrouveValide + f.getQtEbValide();
                    }*/
                }

                /*calcule de la qt et modif du stock si qt trouvée est > 0*/
                if (qtStockTrouve > 0) {
                    for (FactureUtil f : this.listEb) {
                        int newQt = 0;
                        if (f.getStockOuDs().equals("stock") && qtStockTrouveDiff <= qtStockTrouveValide) {
                            if (debp.getIdDetailleExpression() == f.getIdDetailleCommande()) {
                                //calcule de la qt et modif du stock
                                qtStockTrouveDiff = qtStockTrouveDiff + f.getQtDispoStock();
                                if (f.getQtDispoStock() > 0) {
                                    gestrionEntrepo++;
                                    qtTt = qtTt + f.getQtEbValide();
                                    Stock stk = ejbStock.find(f.getStock().getIdSotk());
                                    newQt = stk.getQtStock() - f.getQtDispoStock();
                                    stk.setQtStock(newQt);
                                    ejbStock.edit(stk);
                                }
                            }
                        }
                    }
                }

                /*calcule de la qt et modif du ds si qt trouvée est qtStockTrouveDiff < qtStockTrouveValide */
                /*if (qtStockTrouve > 0 && qtStockTrouveDiff < qtStockTrouveValide) {
                    for (FactureUtil f : this.listEb) {
                        int newQt = 0;
                        if (f.getStockOuDs().equals("deboursser") && qtStockTrouveDiff < qtStockTrouveValide) {
                            if (debp.getIdDetailleExpression() == f.getIdDetailleCommande()) {
                                //calcule de la qt et modif du stock
                                qtStockTrouveDiff = qtStockTrouveDiff + f.getQtDispoStock();
                                if (f.getQtDispoStock() > 0) {
                                    gestrionEntrepo++;
                                    qtTt = qtTt + f.getQtEbValide();
                                    Deboursser ds = ejbDeboursser.find(f.getDeboursser().getIdDeboursser());
                                    Mouvementdebourse mouvementdebourse = new Mouvementdebourse();
                                    mouvementdebourse.setDatesortiedeb(reportDate);
                                    mouvementdebourse.setIdChantier(expb.getIdChantier());
                                    mouvementdebourse.setIdDeboursse(f.getDeboursser());
                                    mouvementdebourse.setQuantite(f.getQtDispoStock());
                                    ejbMouvementdebourse.create(mouvementdebourse);
                                }
                            }
                        }
                    }
                }*/

                /*if (qtStockTrouve <= 0 && qtDeboursserTrouve > 0) {
                    for (FactureUtil f : this.listEb) {
                        int newQt = 0;
                        if (f.getStockOuDs().equals("deboursser") && qtStockTrouveDiff <= qtDeboursserTrouveValide) {
                            if (debp.getIdDetailleExpression() == f.getIdDetailleCommande()) {
                                //calcule de la qt et modif du stock
                                qtStockTrouveDiff = qtStockTrouveDiff + f.getQtDispoStock();
                                if (f.getQtDispoStock() > 0) {
                                    gestrionEntrepo++;
                                    qtTt = qtTt + f.getQtEbValide();
                                    Deboursser ds = ejbDeboursser.find(f.getDeboursser().getIdDeboursser());
                                    Mouvementdebourse mouvementdebourse = new Mouvementdebourse();
                                    mouvementdebourse.setDatesortiedeb(reportDate);
                                    mouvementdebourse.setIdChantier(expb.getIdChantier());
                                    mouvementdebourse.setIdDeboursse(f.getDeboursser());
                                    mouvementdebourse.setQuantite(f.getQtDispoStock());
                                    ejbMouvementdebourse.create(mouvementdebourse);
                                }
                            }
                        }
                    }
                }*/

                int qtCmd = 0;
                if (qtStockTrouve > 0 && qtStockTrouveValide > qtStockTrouveDiff) {
                    qtCmd = qtStockTrouveValide - qtStockTrouveDiff;
                }

                if (qtStockTrouve > 0 && qtStockTrouveValide < qtStockTrouveDiff) {
                    qtCmd = qtStockTrouveDiff - qtStockTrouveValide;
                }
                
                /*if (qtDeboursserTrouve > 0 && qtDeboursserTrouveValide > qtStockTrouveDiff) {
                    qtCmd = qtDeboursserTrouveValide - qtStockTrouveDiff;
                }
                
                if (qtDeboursserTrouve > 0 && qtDeboursserTrouveValide < qtStockTrouveDiff) {
                    qtCmd = qtStockTrouveDiff - qtDeboursserTrouveValide;
                }*/

                if (qtStockTrouve == 0 && qtDeboursserTrouve == 0) {
                    for (FactureUtil f : this.listEb) {
                        if (debp.getIdDetailleExpression() == f.getIdDetailleCommande()) {
                            qtCmd = qtCmd + f.getQtEbValide();
                        }
                    }
                }

                cmddetaille.setDesignation(debp.getDesignation());
                cmddetaille.setReference(debp.getReference());
                cmddetaille.setUnite(debp.getUnite());
                /*if ((gestrionEntrepo > 0) && (debp.getQuantite() > qtTt)) {
                    qtTt = debp.getQuantite() - qtTt;
                } else {
                    System.out.println("Aucun traitement !!!");
                }*/
                cmddetaille.setQuantite(qtCmd);
                cmddetaille.setPu("0.0");
                cmddetaille.setIdCommande(cmdFd);
                ejbDetailleCommande.insertDetailleCommandePrim(cmddetaille);

            }

            return "expressionbesoins";
        } catch (Exception e) {

            return "expressionbesoins";
        }
    }

    public String reset() {
        return "expressionbesoins";
    }

}
