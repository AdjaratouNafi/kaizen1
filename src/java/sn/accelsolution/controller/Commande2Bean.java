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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import sn.accelsolution.dao.ClientFacade;
import sn.accelsolution.dao.CommandeFacade;
import sn.accelsolution.dao.DetailleCommandeFacade;
import sn.accelsolution.dao.DetailledevisFacade;
import sn.accelsolution.dao.DevisFacade;
import sn.accelsolution.dao.EntrepotFacade;
import sn.accelsolution.dao.FournisseurFacade;
import sn.accelsolution.dao.MarchandiseFacade;
import sn.accelsolution.dao.NotificationFacade;
import sn.accelsolution.dao.PrixFacade;
import sn.accelsolution.dao.UtilisateurFacade;
import sn.accelsolution.entities.Actionmenu;
import sn.accelsolution.entities.Commande;
import sn.accelsolution.entities.DetailleCommande;
import sn.accelsolution.entities.Detailledevis;
import sn.accelsolution.entities.Devis;
import sn.accelsolution.entities.Entrepot;
import sn.accelsolution.entities.Expressionbesoin;
import sn.accelsolution.entities.Fournisseur;
import sn.accelsolution.entities.Marchandise;
import sn.accelsolution.entities.Notification;
import sn.accelsolution.entities.Prix;
import sn.accelsolution.entities.Utilisateur;
import sn.accelsolution.util.FactureUtil;
import sn.accelsolution.util.GenerationCodePdf;
import sn.accelsolution.util.MontantConverter;
import sn.accelsolution.util.UtilControleMenu;
import sn.accelsolution.util.UtilPrix;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DELL
 */
@ManagedBean
@ViewScoped
public class Commande2Bean implements Serializable {

    /**
     * Creates a new instance of ContratRecrutBean
     */
    @EJB
    UtilisateurFacade ejbUser;
    @EJB
    CommandeFacade ejbCommande;
    @EJB
    DetailleCommandeFacade ejbDetailleCommande;
    @EJB
    PrixFacade ejbPrix;
    @EJB
    MarchandiseFacade ejbMarchandise;
    @EJB
    FournisseurFacade ejbFournisseur;
    @EJB
    NotificationFacade ejbNotification;

    private Commande commande;
    private DetailleCommande detailleCommande;
    private Commande commandeFromEdit;
    private DetailleCommande detailleCommandeFromEdit;

    private List<Commande> allCommande;
    private List<DetailleCommande> allDetailleCommande;
    private List<FactureUtil> listCommandes;
    private List<FactureUtil> allFactureUtils;
    private List<Commande> listExpressions;
    private List<Commande> listCommandeTraitements;
    private List<Commande> listCommandeTraitements2;
    private String mttc;
    private Utilisateur user;
    private String totalHT;
    private String totalTTC;
    private String montantTVA;
    private String contactFournisseur;

    private List<Actionmenu> myllActionmenus;
    private String creerCommande;
    private String modifierCommande;
    private String supprimerCommande;
    private String consulterCommande;
    private String imprimerCommande;
    private String validerCommande;

    private List<Commande> commandeUtil1;
    private List<Commande> commandeUtil2;
    private int nbCommandeNonValide;
    private String controleNbCommande;
    private List<Commande> filteredListCommande;

    @PostConstruct
    public void init() {

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UtilControleMenu utilControleMenu = new UtilControleMenu();

        myllActionmenus = (List<Actionmenu>) session.getAttribute("allActionmenusTraitement");

        this.setCreerCommande(utilControleMenu.creerCommande(myllActionmenus));
        this.setModifierCommande(utilControleMenu.modifierCommande(myllActionmenus));
        this.setSupprimerCommande(utilControleMenu.supprimerCommande(myllActionmenus));
        this.setConsulterCommande(utilControleMenu.consulterCommande(myllActionmenus));
        this.setImprimerCommande(utilControleMenu.imprimerCommande(myllActionmenus));
        this.setValiderCommande(utilControleMenu.validerCommande(myllActionmenus));

        GenerationCodePdf gcpdf = new GenerationCodePdf();
        int maxCmd = ejbCommande.maxCommande();
        Commande mymaxCmd = ejbCommande.find(maxCmd);
        String ancienCode = mymaxCmd.getCode();
        String codeCommande = gcpdf.genererCodeCommande(ancienCode);
        String numCmd = codeCommande;
        this.commande.setCode(numCmd);

        FactureUtil fu = new FactureUtil();
        fu.setDesignation("");
        fu.setUnite(" ");
        fu.setPu("");
        fu.setQuantite(0);
        fu.setContact("");
        listCommandes.add(fu);

        user = new Utilisateur();
        user = (Utilisateur) session.getAttribute("user");
        this.commande.setIdUtilisateur(user);

        System.out.println("User :" + user.getNomUtilisateur());

        List<Commande> listCommandeattente = ejbCommande.listOfCommandeAttente();
        this.setNbCommandeNonValide(listCommandeattente.size());

        if (this.getNbCommandeNonValide() == 0) {
            this.setControleNbCommande("KO");
        } else {
            this.setControleNbCommande("OK");
        }

        allCommande = new ArrayList<>();
        allCommande.clear();
        allCommande = ejbCommande.findAll();

    }

    public Commande2Bean() {
        listCommandes = new ArrayList<>();
        commande = new Commande();
        commandeFromEdit = new Commande();
        detailleCommande = new DetailleCommande();
        detailleCommandeFromEdit = new DetailleCommande();
        allFactureUtils = new ArrayList<>();

    }

    public List<Commande> getFilteredListCommande() {
        return filteredListCommande;
    }

    public void setFilteredListCommande(List<Commande> filteredListCommande) {
        this.filteredListCommande = filteredListCommande;
    }

    public String getControleNbCommande() {
        return controleNbCommande;
    }

    public void setControleNbCommande(String controleNbCommande) {
        this.controleNbCommande = controleNbCommande;
    }

    public int getNbCommandeNonValide() {
        return nbCommandeNonValide;
    }

    public void setNbCommandeNonValide(int nbCommandeNonValide) {
        this.nbCommandeNonValide = nbCommandeNonValide;
    }

    public List<Commande> getCommandeUtil1() {
        return commandeUtil1;
    }

    public void setCommandeUtil1(List<Commande> commandeUtil1) {
        this.commandeUtil1 = commandeUtil1;
    }

    public List<Commande> getCommandeUtil2() {
        return commandeUtil2;
    }

    public void setCommandeUtil2(List<Commande> commandeUtil2) {
        this.commandeUtil2 = commandeUtil2;
    }

    public List<Actionmenu> getMyllActionmenus() {
        return myllActionmenus;
    }

    public void setMyllActionmenus(List<Actionmenu> myllActionmenus) {
        this.myllActionmenus = myllActionmenus;
    }

    public String getCreerCommande() {
        return creerCommande;
    }

    public void setCreerCommande(String creerCommande) {
        this.creerCommande = creerCommande;
    }

    public String getModifierCommande() {
        return modifierCommande;
    }

    public void setModifierCommande(String modifierCommande) {
        this.modifierCommande = modifierCommande;
    }

    public String getSupprimerCommande() {
        return supprimerCommande;
    }

    public void setSupprimerCommande(String supprimerCommande) {
        this.supprimerCommande = supprimerCommande;
    }

    public String getConsulterCommande() {
        return consulterCommande;
    }

    public void setConsulterCommande(String consulterCommande) {
        this.consulterCommande = consulterCommande;
    }

    public String getImprimerCommande() {
        return imprimerCommande;
    }

    public void setImprimerCommande(String imprimerCommande) {
        this.imprimerCommande = imprimerCommande;
    }

    public String getValiderCommande() {
        return validerCommande;
    }

    public void setValiderCommande(String validerCommande) {
        this.validerCommande = validerCommande;
    }

    public String getContactFournisseur() {
        return contactFournisseur;
    }

    public void setContactFournisseur(String contactFournisseur) {
        this.contactFournisseur = contactFournisseur;
    }

    public List<Commande> getListCommandeTraitements() {
        listCommandeTraitements = ejbCommande.listOfCommandeAttente();
        return listCommandeTraitements;
    }

    public void setListCommandeTraitements(List<Commande> listCommandeTraitements) {
        this.listCommandeTraitements = listCommandeTraitements;
    }

    public List<Commande> getListCommandeTraitements2() {
        listCommandeTraitements2 = ejbCommande.listOfCommandeNonLivrees2();
        return listCommandeTraitements2;
    }

    public void setListCommandeTraitements2(List<Commande> listCommandeTraitements2) {
        this.listCommandeTraitements2 = listCommandeTraitements2;
    }

    public String getTotalHT() {
        return totalHT;
    }

    public void setTotalHT(String totalHT) {
        this.totalHT = totalHT;
    }

    public String getTotalTTC() {
        return totalTTC;
    }

    public void setTotalTTC(String totalTTC) {
        this.totalTTC = totalTTC;
    }

    public String getMontantTVA() {
        return montantTVA;
    }

    public void setMontantTVA(String montantTVA) {
        this.montantTVA = montantTVA;
    }

    public List<FactureUtil> getAllFactureUtils() {
        return allFactureUtils;
    }

    public void setAllFactureUtils(List<FactureUtil> allFactureUtils) {
        this.allFactureUtils = allFactureUtils;
    }

    public List<Commande> getListExpressions() {
        listExpressions = ejbCommande.listOfExpression();
        return listExpressions;
    }

    public void setListExpressions(List<Commande> listExpressions) {
        this.listExpressions = listExpressions;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public DetailleCommande getDetailleCommande() {
        return detailleCommande;
    }

    public void setDetailleCommande(DetailleCommande detailleCommande) {
        this.detailleCommande = detailleCommande;
    }

    public Commande getCommandeFromEdit() {
        return commandeFromEdit;
    }

    public void setCommandeFromEdit(Commande commandeFromEdit) {
        this.commandeFromEdit = commandeFromEdit;
    }

    public DetailleCommande getDetailleCommandeFromEdit() {
        return detailleCommandeFromEdit;
    }

    public void setDetailleCommandeFromEdit(DetailleCommande detailleCommandeFromEdit) {
        this.detailleCommandeFromEdit = detailleCommandeFromEdit;
    }

    public List<DetailleCommande> getAllDetailleCommande() {
        return allDetailleCommande;
    }

    public void setAllDetailleCommande(List<DetailleCommande> allDetailleCommande) {
        this.allDetailleCommande = allDetailleCommande;
    }

    public List<FactureUtil> getListCommandes() {
        return listCommandes;
    }

    public void setListCommandes(List<FactureUtil> listCommandes) {
        this.listCommandes = listCommandes;
    }

    public List<Commande> getAllCommande() {
        return allCommande;
    }

    public void setAllCommande(List<Commande> allCommande) {
        this.allCommande = allCommande;
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
        fu.setPu("");
        fu.setQuantite(0);
        fu.setContact("");
        listCommandes.add(fu);
    }

    public void removeInputLigne(int index) {
        listCommandes.remove(index);
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
            for (int x = 0; x < this.listCommandes.size(); x++) {

                FactureUtil f1 = this.listCommandes.get(x);
                if (f1.getDesignation().equalsIgnoreCase(f.getDesignation())) {
                    index = x;
                }
            }

            Double cv = mtc.StringToDouble(f.getPu());
            String newv = mtc.DoubleToString(cv);
            f.setPu(newv);
            this.listCommandes.set(index, f);

            Double mtht = 0.0;
            for (FactureUtil df : this.listCommandes) {

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

    public void searchPrix(FactureUtil f) {
        try {
            List<UtilPrix> listOfPrix = new ArrayList<>();
            listOfPrix = ejbPrix.prixByMarchandise(f.getDesignation());

            int index = 0;
            for (int x = 0; x < this.listCommandes.size(); x++) {

                FactureUtil f1 = this.listCommandes.get(x);
                if (f1.getDesignation().equalsIgnoreCase(f.getDesignation())) {
                    index = x;
                }
            }

            f.setListPrix(listOfPrix);
            this.listCommandes.set(index, f);

        } catch (Exception e) {
        }
    }

    public String save() {

//        try {
//            this.commande.setEtat("En attente");
//            this.commande.setPositionLivraison(1);
//            this.commande.setTypecommande("Appro stock");
//            ejbCommande.insertCommande(this.commande);
//            int idCmd = ejbCommande.maxCommande();
//            Commande cmd = ejbCommande.find(idCmd);
//
//            for (FactureUtil f : listCommandes) {
//                this.detailleCommande.setDesignation(f.getDesignation());
//                this.detailleCommande.setReference(f.getRef());
//                this.detailleCommande.setUnite(f.getUnite());
//                this.detailleCommande.setQuantite(f.getQuantite());
//                this.detailleCommande.setPu(f.getPu());
//                this.detailleCommande.setIdCommande(cmd);
//                this.detailleCommande.setQtlivree(0);
//                this.detailleCommande.setQtrestante(f.getQuantite());
//                ejbDetailleCommande.insertDetailleCommandePrim(this.detailleCommande);
//            }
//
//            return "commandes2";
//        } catch (Exception e) {
//            return "new_commande2";
//        } 
        try {

            List<Fournisseur> listControlTraitement = new ArrayList<>();

            for (FactureUtil f : this.listCommandes) {
                Boolean trouver = false;
                FactureUtil up = new FactureUtil();
                List<FactureUtil> secondListCommandes = new ArrayList<>();
                secondListCommandes = up.SearchCommandeByFournisseur(this.listCommandes, f.getFournisseur());

                for (Fournisseur fsr : listControlTraitement) {
                    if (fsr.getIdFournisseur() == f.getFournisseur().getIdFournisseur()) {
                        trouver = true;
                    } else {
                        trouver = false;
                    }
                }

                if ((!secondListCommandes.isEmpty()) && (trouver == false)) {

                    /*Creation de la commande du fournisseur f*/
                    Commande newCmd = new Commande();
//                    int maxCmd = ejbCommande.maxCommande();
////                    String numCmd = "ESTCMD" + maxCmd;
////                    newCmd.setCode(numCmd);
                    newCmd.setCode(this.commande.getCode());
                    newCmd.setDateechance(this.commande.getDateechance());
                    newCmd.setIdFournisseur(f.getFournisseur());
                    newCmd.setEtat("En attente");
                    newCmd.setPositionLivraison(1);
                    newCmd.setIdEntrepot(this.commande.getIdEntrepot());
                    newCmd.setTypecommande("Appro stock");
                    ejbCommande.insertCommande(newCmd);

                    int maxSCmd1 = ejbCommande.maxCommande();
                    Commande scmd1 = ejbCommande.find(maxSCmd1);

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
                    notification.setIdUtilisateur(this.user);
                    notification.setIdCommande(scmd1);

                    ejbNotification.insertNotification6(notification);

                    /*Creation du detaille commande en fonction de la liste des commande du fournisseur f*/
                    for (FactureUtil secondValeur : secondListCommandes) {
                        Marchandise mch = ejbMarchandise.findMarchandiseByLibelle(secondValeur.getDesignation());
                        Prix p = ejbPrix.prixByProdAndFsse(mch, secondValeur.getFournisseur());
                        DetailleCommande dcmd = new DetailleCommande();
                        dcmd.setDesignation(secondValeur.getDesignation());
                        dcmd.setReference(secondValeur.getRef());
                        dcmd.setUnite(secondValeur.getUnite());
                        dcmd.setQuantite(secondValeur.getQuantite());
                        dcmd.setPu(p.getPrix());
                        int maxSCmd = ejbCommande.maxCommande();
                        Commande scmd = ejbCommande.find(maxSCmd);
                        dcmd.setIdCommande(scmd);
                        dcmd.setQtlivree(0);
                        dcmd.setQtrestante(secondValeur.getQuantite());
                        ejbDetailleCommande.insertDetailleCommandePrim(dcmd);

                    }

                } else {
                    System.out.println("Traitement terminer !!!");
                }

                /*Ajout dans la liste de controle*/
                listControlTraitement.add(f.getFournisseur());

            }

            return "commandes2";
        } catch (Exception e) {
            return "commande2";
        }
    }

    public String reset() {
        this.commande = new Commande();
        return "commandes2";
    }

    public String supprimer(Commande c) {
        try {

            ejbDetailleCommande.deleteByCommande(c.getIdCommande());
            ejbCommande.remove(c);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "commandes2";
    }

    public String editer(Commande c) {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "commandes2";
    }

    public void renderInfo2() {

        try {
            this.setCommandeFromEdit(this.getCommandeFromEdit());

            this.setAllDetailleCommande(ejbDetailleCommande.listOfDetailleCommandeByCommande(this.getCommandeFromEdit()));
            MontantConverter mtc = new MontantConverter();
            Double mtht = 0.0;

            for (DetailleCommande df : this.allDetailleCommande) {
                FactureUtil fut = new FactureUtil();
                List<UtilPrix> listOfPrix = new ArrayList<>();
                listOfPrix = ejbPrix.prixByMarchandise(df.getDesignation());

                fut.setIdDetailleCommande(df.getIdDetailleCommande());
                fut.setDesignation(df.getDesignation());
                fut.setQuantite(df.getQuantite());
                fut.setPu(df.getPu());
                fut.setRef(df.getReference());
                fut.setUnite(df.getUnite());
                Double puc = mtc.StringToDouble(df.getPu());
                Double ctotal = puc * df.getQuantite();
                mtht = mtht + ctotal;
                String rctotal = mtc.DoubleToString(ctotal);
                fut.setPrixtotal(rctotal);
                fut.setListPrix(listOfPrix);
                this.allFactureUtils.add(fut);
            }

            /*Montant HT*/
            String rmtht = mtc.DoubleToString(mtht);
            this.setTotalHT(rmtht);
            /*Montant TVA*/
            Double mttva = mtht * 0.18;
            String rmtva = mtc.DoubleToString(mttva);
            this.setMontantTVA(rmtva);
            /*Montant TTC*/
            Double mttc = mtht + mttva;
            String rmttc = mtc.DoubleToString(mttc);
            this.setTotalTTC(rmttc);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void renderInfoCmdTraitement() {

        try {
            this.setCommandeFromEdit(this.getCommandeFromEdit());

            this.setAllDetailleCommande(ejbDetailleCommande.listOfDetailleCommandeByCommande(this.getCommandeFromEdit()));
            MontantConverter mtc = new MontantConverter();
            Double mtht = 0.0;

            for (DetailleCommande df : this.allDetailleCommande) {
                FactureUtil fut = new FactureUtil();
                fut.setIdDetailleCommande(df.getIdDetailleCommande());
                fut.setDesignation(df.getDesignation());
                fut.setQuantite(df.getQuantite());
                fut.setPu(df.getPu());
                fut.setRef(df.getReference());
                fut.setUnite(df.getUnite());
                Double puc = mtc.StringToDouble(df.getPu());
                Double ctotal = puc * df.getQuantite();
                mtht = mtht + ctotal;
                String rctotal = mtc.DoubleToString(ctotal);
                fut.setPrixtotal(rctotal);
                this.allFactureUtils.add(fut);
            }

            /*Montant HT*/
            String rmtht = mtc.DoubleToString(mtht);
            this.setTotalHT(rmtht);
            /*Montant TVA*/
            Double mttva = mtht * 0.18;
            String rmtva = mtc.DoubleToString(mttva);
            this.setMontantTVA(rmtva);
            /*Montant TTC*/
            Double mttc = mtht + mttva;
            String rmttc = mtc.DoubleToString(mttc);
            this.setTotalTTC(rmttc);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void renderInfo3(FactureUtil f) {

        try {
            int index = 0;
            for (int x = 0; x < this.allFactureUtils.size(); x++) {

                FactureUtil f1 = this.allFactureUtils.get(x);
                if (f1.getDesignation().equalsIgnoreCase(f.getDesignation())) {
                    index = x;
                }
            }

            f.setContact(f.getFournisseur().getTelephoneFournisseur());
            this.allFactureUtils.set(index, f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String saveTraitement() {

        try {

            /*Delete old detaille commande*/
            Commande cmd = ejbCommande.find(this.commandeFromEdit.getIdCommande());
            cmd.setEtat(this.commandeFromEdit.getEtat());
            Commande archiveCmd = cmd;
            ejbDetailleCommande.deleteByCommande(cmd.getIdCommande());
            ejbCommande.deleteCommande(cmd.getIdCommande());

            List<Fournisseur> listControlTraitement = new ArrayList<>();

            for (FactureUtil f : this.allFactureUtils) {
                Boolean trouver = false;
                FactureUtil up = new FactureUtil();
                List<FactureUtil> secondListCommandes = new ArrayList<>();
                secondListCommandes = up.SearchCommandeByFournisseur(this.allFactureUtils, f.getFournisseur());

                for (Fournisseur fsr : listControlTraitement) {
                    if (fsr.getIdFournisseur() == f.getFournisseur().getIdFournisseur()) {
                        trouver = true;
                    } else {
                        trouver = false;
                    }
                }

                if ((!secondListCommandes.isEmpty()) && (trouver == false)) {

                    /*Creation de la commande du fournisseur f*/
                    Commande newCmd = new Commande();
                    GenerationCodePdf gcpdf = new GenerationCodePdf();
                    int maxCmd = ejbCommande.maxCommande();
                    Commande mymaxCmd = ejbCommande.find(maxCmd);
                    String ancienCode = mymaxCmd.getCode();
                    String codeCommande = gcpdf.genererCodeCommande(ancienCode);
                    String numCmd = codeCommande;
                    newCmd.setCode(numCmd);
                    String datecmd = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
                    newCmd.setDateechance(datecmd);
                    newCmd.setIdMarche(archiveCmd.getIdChantier().getIdMarche());
                    newCmd.setIdChantier(archiveCmd.getIdChantier());
                    newCmd.setIdFournisseur(f.getFournisseur());
                    newCmd.setEtat(archiveCmd.getEtat());
                    newCmd.setLivree("non");
                    newCmd.setTypecommande("Exp besoin");
                    newCmd.setIdExpression(archiveCmd.getIdExpression());
                    ejbCommande.insertSecondCommandeExp(newCmd);

                    /*Creation du detaille commande en fonction de la liste des commande du fournisseur f*/
                    for (FactureUtil secondValeur : secondListCommandes) {
                        Marchandise mch = ejbMarchandise.findMarchandiseByLibelle(secondValeur.getDesignation());
                        Prix p = ejbPrix.prixByProdAndFsse(mch, secondValeur.getFournisseur());
                        DetailleCommande dcmd = new DetailleCommande();
                        dcmd.setDesignation(secondValeur.getDesignation());
                        dcmd.setReference(secondValeur.getRef());
                        dcmd.setUnite(secondValeur.getUnite());
                        dcmd.setQuantite(secondValeur.getQuantite());
                        dcmd.setPu(p.getPrix());
                        int maxSCmd = ejbCommande.maxCommande();
                        Commande scmd = ejbCommande.find(maxSCmd);
                        dcmd.setIdCommande(scmd);
                        dcmd.setQtlivree(0);
                        dcmd.setQtrestante(secondValeur.getQuantite());
                        ejbDetailleCommande.insertDetailleCommandePrim(dcmd);

                    }

                } else {
                    System.out.println("Traitement terminer !!!");
                }

                /*Ajout dans la liste de controle*/
                listControlTraitement.add(f.getFournisseur());

            }

            return "commandes2";
        } catch (Exception e) {
            return "commande2";
        }
    }

    public String saveTraitement2() {
        
         UtilUtfconvert utfconvert = new UtilUtfconvert();

        try {

            Commande cmd = ejbCommande.find(this.commandeFromEdit.getIdCommande());
            cmd.setDateechance(this.commandeFromEdit.getDateechance());
            cmd.setIdChantier(this.commandeFromEdit.getIdChantier());
            cmd.setIdFournisseur(this.commandeFromEdit.getIdFournisseur());
            cmd.setEtat(this.commandeFromEdit.getEtat());
            cmd.setLivree("non");
            ejbCommande.edit(cmd);

            for (FactureUtil f : this.allFactureUtils) {
                DetailleCommande dcmd = new DetailleCommande();
                dcmd.setIdDetailleCommande(f.getIdDetailleCommande());
                dcmd.setDesignation( utfconvert.convertFromUTF8(f.getDesignation()));
                dcmd.setReference(f.getRef());
                dcmd.setUnite(f.getUnite());
                dcmd.setQuantite(f.getQuantite());
                dcmd.setPu(f.getPu());
                dcmd.setIdCommande(cmd);
                dcmd.setQtlivree(0);
                dcmd.setQtrestante(f.getQuantite());
                ejbDetailleCommande.edit(dcmd);
            }

            return "commandes2";
        } catch (Exception e) {
            return "commande2";
        }
    }

    public void renderInfo4(FactureUtil f) {
        try {
            MontantConverter mtc = new MontantConverter();
            Marchandise march = ejbMarchandise.findMarchandiseByLibelle(f.getDesignation());
            Prix p = ejbPrix.prixByProdAndFsse(march, f.getFournisseur());

            int index = 0;
            for (int x = 0; x < this.listCommandes.size(); x++) {

                FactureUtil f1 = this.listCommandes.get(x);
                if (f1.getDesignation().equalsIgnoreCase(f.getDesignation())) {
                    index = x;
                }
            }

            f.setPu(p.getPrix());
            f.setContact(f.getFournisseur().getTelephoneFournisseur());
            this.listCommandes.set(index, f);

            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Successful", "Contact fournisseur: " + f.getFournisseur().getContact()));

        } catch (Exception e) {
        }
    }

    public String traiterExpression(Commande cmd) {

        System.out.println("Valeur choisie: " + cmd.getTypecommande());
        if (cmd.getTypecommande().equals("Exp besoin")) {
            Notification notifcat = ejbNotification.getNotificationByCommande(cmd);
            if (notifcat != null) {
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                session.setAttribute("myCommandeExpression", notifcat);
                return "traitement_expressionTest";

            }

        }

        if (cmd.getTypecommande().equals("Appro stock")) {
            Notification notifcat = ejbNotification.getNotificationByCommande(cmd);
            if (notifcat != null) {
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                session.setAttribute("myCommandeCommande", notifcat);
                return "traitement_commandeTest";
            }

        }

        return null;

    }

}
