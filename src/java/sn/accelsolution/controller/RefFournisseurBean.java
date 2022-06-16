/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.controller;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.event.RowEditEvent;
import sn.accelsolution.dao.AcompteFacade;
import sn.accelsolution.dao.AcomptefournisseurFacade;
import sn.accelsolution.dao.ChantierFacade;
import sn.accelsolution.dao.CommandeFacade;
import sn.accelsolution.dao.CorpsetatFacade;
import sn.accelsolution.dao.DetaillechantierFacade;
import sn.accelsolution.dao.DetailleCommandeFacade;
import sn.accelsolution.dao.FournisseurFacade;
import sn.accelsolution.dao.MarcheFacade;
import sn.accelsolution.dao.PrestataireFacade;
import sn.accelsolution.dao.PrestataireprimFacade;
import sn.accelsolution.dao.ReffournisseurFacade;
import sn.accelsolution.entities.Acompte;
import sn.accelsolution.entities.Acomptefournisseur;
import sn.accelsolution.entities.Actionmenu;
import sn.accelsolution.entities.Chantier;
import sn.accelsolution.entities.Commande;
import sn.accelsolution.entities.Detaillechantier;
import sn.accelsolution.entities.Marche;
import sn.accelsolution.entities.Prestataire;
import sn.accelsolution.entities.Prestataireprim;
import sn.accelsolution.entities.Reffournisseur;
import sn.accelsolution.util.FournisseurUtil;
import sn.accelsolution.util.MontantConverter;
import sn.accelsolution.util.PrestataireUtil;
import sn.accelsolution.util.Total;
import sn.accelsolution.util.UtilControleMenu;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DELL
 */
@ManagedBean
@ViewScoped
public class RefFournisseurBean implements Serializable {

    /**
     * Creates a new instance of ContratRecrutBean
     */
    @EJB
    PrestataireFacade ejbPrestataire;
    @EJB
    ChantierFacade ejbChantier;
    @EJB
    MarcheFacade ejbMarche;
    @EJB
    CorpsetatFacade ejbCorpsetat;
    @EJB
    AcompteFacade ejbAcompte;
    @EJB
    DetaillechantierFacade ejbdetaillechantier;
    @EJB
    PrestataireprimFacade ejbPrestatairePrim;

    @EJB
    CommandeFacade ejbCommande;
    @EJB
    FournisseurFacade ejbFournisseur;
    @EJB
    AcomptefournisseurFacade ejbAcompteFournisseur;
    @EJB
    ReffournisseurFacade ejbRefFournisseur;
    @EJB
    DetailleCommandeFacade ejbDetailleCommande;

    private Prestataire prestataire;
    private Prestataire prestataireFromCreation;
    private Prestataire prestataireFromEdit;
    private Prestataireprim prestatairePrim;
    private Chantier chantier;
    private Marche Marche;
    private Detaillechantier detaillechantier;

    private Reffournisseur refFournisseur;
    private Reffournisseur refFournisseurFromEdit;
    private FournisseurUtil fournisseurUtil;
    private List<FournisseurUtil> allFournisseurs = new ArrayList<>();
    private List<Commande> allCommandes = new ArrayList<>();

    private List<PrestataireUtil> allPrestataires = new ArrayList<>();
    private List<PrestataireUtil> allPrestatairesFiltereds = new ArrayList<>();
    private List<Prestataire> lesPrestataires = new ArrayList<>();
    private List<Prestataire> allPrestatairesForCalcules = new ArrayList<>();
    private List<Prestataire> listDesPrestataires;
    private List listChantier;
    private Total total = new Total();
    private List<Total> listTotal = new ArrayList<>();
    private String totalAcompteHT;
    private String totalAcompteTCC;
    private String totalAccorps;
    private List listCorpsEtats;
    private String txtVide;
    private PrestataireUtil prestataireUtil;
    private String choixCreation;

    private List<Acomptefournisseur> listDesAcomptes;
    private String totalDesAcomptesHT;
    private String totalDesAcomptesTTC;
    private String dateAffectation;
    private List<Actionmenu> myllActionmenus;
    private String creerFirstPrestataire;
    private String modifierFirstPrestataire;
    private String supprimerFirstPrestataire;
    private String imprimerFirstPrestataire;
    private String consulterFirstPrestataire;

    @PostConstruct
    public void init() {

//        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
//        UtilControleMenu utilControleMenu = new UtilControleMenu();
//
//        myllActionmenus = (List<Actionmenu>) session.getAttribute("allActionmenusFromTraitementMainOeuvres");
//
//        this.setCreerFirstPrestataire(utilControleMenu.creerFirstPrestataire(myllActionmenus));
//        this.setModifierFirstPrestataire(utilControleMenu.modifierFirstPrestataire(myllActionmenus));
//        this.setSupprimerFirstPrestataire(utilControleMenu.supprimerFirstPrestataire(myllActionmenus));
//        this.setConsulterFirstPrestataire(utilControleMenu.consulterFirstPrestataire(myllActionmenus));
        this.setTxtVide("TTT");
        MontantConverter mtct = new MontantConverter();
        this.listDesAcomptes = ejbAcompteFournisseur.findAll();
        List<Reffournisseur> lesFours = ejbRefFournisseur.listOfFournisseurs();
       // UtilUtfconvert utfconvert = new UtilUtfconvert();
        for (Reffournisseur rffs : lesFours) {
            FournisseurUtil prpadd = new FournisseurUtil();
            prpadd.setIdRefFournisseur(rffs.getIdReffournisseur());
            prpadd.setNinea(rffs.getIdFournisseur().getNinea());
            prpadd.setNomcomplet(rffs.getNomcomplet());
            prpadd.setTelephone(rffs.getTelephone());
            prpadd.setEchenance(rffs.getEcheance());
            Double vc1 = mtct.StringToDouble(rffs.getAccord());
            prpadd.setAccord(vc1);
            prpadd.setAccordAffichage(rffs.getAccord());
            prpadd.setNature(rffs.getNature());
            Double vc2 = mtct.StringToDouble(rffs.getAccompte());
            prpadd.setAccompte(vc2);
            prpadd.setAccompteAffichage(rffs.getAccompte());
            Double vc3 = mtct.StringToDouble(rffs.getReliquant());
            prpadd.setReliquant(vc3);
            prpadd.setReliquantAffichage(rffs.getReliquant());
            prpadd.setVoyant(rffs.getVoyant());
            prpadd.setIdCommande(rffs.getIdCommande());
            prpadd.setFournisseur(rffs.getIdFournisseur());
            this.allFournisseurs.add(prpadd);
        }

        //Add element in total list
        int v1 = 0;
        Double valueAccord = (double) v1;
        int v2 = 0;
        Double valueAcompte = (double) v2;

        for (FournisseurUtil p : allFournisseurs) {
            valueAccord = valueAccord + p.getAccord();
            valueAcompte = valueAcompte + p.getAccompte();
        }
        String v11 = mtct.DoubleToString(valueAccord);
        String v12 = mtct.DoubleToString(valueAcompte);
        total.setAccord(v11);
        total.setAcompte(v12);
        listTotal.add(this.total);

        /*Calcule des totalaux*/
        Double tacompteht = 0.0;
        Double tacompteTTC = 0.0;
        for (Acomptefournisseur acompteFournisseur : this.listDesAcomptes) {

            if (acompteFournisseur.getChoixtva().equals("non")) {
                Double mttacp2 = mtct.StringToDouble(acompteFournisseur.getMontantAcompte());
                tacompteht = tacompteht + mttacp2;
            }

            if (acompteFournisseur.getChoixtva().equals("oui")) {
                Double mttacp1 = mtct.StringToDouble(acompteFournisseur.getMontantAcompte());
                tacompteTTC = tacompteTTC + mttacp1;
            }
        }

        String Rtacompteht = mtct.DoubleToString(tacompteht);
        String RtacompteTTC = mtct.DoubleToString(tacompteTTC);
        this.setTotalDesAcomptesHT(Rtacompteht);
        this.setTotalDesAcomptesTTC(RtacompteTTC);
    }

    public RefFournisseurBean() {

        refFournisseur = new Reffournisseur();
        refFournisseurFromEdit = new Reffournisseur();
//        Double initAccompte = 0.0;
//        prestataire.setAccompte(initAccompte.toString());
//        prestataireUtil = new PrestataireUtil();
//        prestatairePrim = new PrestatairePrim();
//        Marche = new Marche();
//        detaillechantier = new DetailleChantier();
    }

    public FournisseurUtil getFournisseurUtil() {
        return fournisseurUtil;
    }

    public void setFournisseurUtil(FournisseurUtil fournisseurUtil) {
        this.fournisseurUtil = fournisseurUtil;
    }

    public Reffournisseur getRefFournisseur() {
        return refFournisseur;
    }

    public void setRefFournisseur(Reffournisseur refFournisseur) {
        this.refFournisseur = refFournisseur;
    }

    public List<FournisseurUtil> getAllFournisseurs() {
        return allFournisseurs;
    }

    public void setAllFournisseurs(List<FournisseurUtil> allFournisseurs) {
        this.allFournisseurs = allFournisseurs;
    }

    public String getConsulterFirstPrestataire() {
        return consulterFirstPrestataire;
    }

    public void setConsulterFirstPrestataire(String consulterFirstPrestataire) {
        this.consulterFirstPrestataire = consulterFirstPrestataire;
    }

    public List<Actionmenu> getMyllActionmenus() {
        return myllActionmenus;
    }

    public void setMyllActionmenus(List<Actionmenu> myllActionmenus) {
        this.myllActionmenus = myllActionmenus;
    }

    public String getCreerFirstPrestataire() {
        return creerFirstPrestataire;
    }

    public void setCreerFirstPrestataire(String creerFirstPrestataire) {
        this.creerFirstPrestataire = creerFirstPrestataire;
    }

    public String getModifierFirstPrestataire() {
        return modifierFirstPrestataire;
    }

    public void setModifierFirstPrestataire(String modifierFirstPrestataire) {
        this.modifierFirstPrestataire = modifierFirstPrestataire;
    }

    public String getSupprimerFirstPrestataire() {
        return supprimerFirstPrestataire;
    }

    public void setSupprimerFirstPrestataire(String supprimerFirstPrestataire) {
        this.supprimerFirstPrestataire = supprimerFirstPrestataire;
    }

    public String getImprimerFirstPrestataire() {
        return imprimerFirstPrestataire;
    }

    public void setImprimerFirstPrestataire(String imprimerFirstPrestataire) {
        this.imprimerFirstPrestataire = imprimerFirstPrestataire;
    }

    public Detaillechantier getDetaillechantier() {
        return detaillechantier;
    }

    public void setDetaillechantier(Detaillechantier detaillechantier) {
        this.detaillechantier = detaillechantier;
    }

    public String getDateAffectation() {
        return dateAffectation;
    }

    public void setDateAffectation(String dateAffectation) {
        this.dateAffectation = dateAffectation;
    }

    public Marche getMarche() {
        return Marche;
    }

    public void setMarche(Marche Marche) {
        this.Marche = Marche;
    }

    public Prestataireprim getPrestatairePrim() {
        return prestatairePrim;
    }

    public void setPrestatairePrim(Prestataireprim prestatairePrim) {
        this.prestatairePrim = prestatairePrim;
    }

    public List<Acomptefournisseur> getListDesAcomptes() {
        return listDesAcomptes;
    }

    public void setListDesAcomptes(List<Acomptefournisseur> listDesAcomptes) {
        this.listDesAcomptes = listDesAcomptes;
    }

    public String getTotalDesAcomptesHT() {
        return totalDesAcomptesHT;
    }

    public void setTotalDesAcomptesHT(String totalDesAcomptesHT) {
        this.totalDesAcomptesHT = totalDesAcomptesHT;
    }

    public String getTotalDesAcomptesTTC() {
        return totalDesAcomptesTTC;
    }

    public void setTotalDesAcomptesTTC(String totalDesAcomptesTTC) {
        this.totalDesAcomptesTTC = totalDesAcomptesTTC;
    }

    public List<Prestataire> getListDesPrestataires() {
        listDesPrestataires = ejbPrestataire.findAll();
        return listDesPrestataires;
    }

    public void setListDesPrestataires(List<Prestataire> listDesPrestataires) {
        this.listDesPrestataires = listDesPrestataires;
    }

    public Prestataire getPrestataireFromCreation() {
        return prestataireFromCreation;
    }

    public void setPrestataireFromCreation(Prestataire prestataireFromCreation) {
        this.prestataireFromCreation = prestataireFromCreation;
    }

    public String getChoixCreation() {
        return choixCreation;
    }

    public void setChoixCreation(String choixCreation) {
        this.choixCreation = choixCreation;
    }

    public List<Prestataire> getLesPrestataires() {
        lesPrestataires = ejbPrestataire.findAll();
        return lesPrestataires;
    }

    public void setLesPrestataires(List<Prestataire> lesPrestataires) {
        this.lesPrestataires = lesPrestataires;
    }

    public PrestataireUtil getPrestataireUtil() {
        return prestataireUtil;
    }

    public void setPrestataireUtil(PrestataireUtil prestataireUtil) {
        this.prestataireUtil = prestataireUtil;
    }

    public String getTxtVide() {
        return txtVide;
    }

    public void setTxtVide(String txtVide) {
        this.txtVide = txtVide;
    }

    public List getListCorpsEtats() {
        listCorpsEtats = ejbCorpsetat.findAll();
        return listCorpsEtats;
    }

    public void setListCorpsEtats(List listCorpsEtats) {
        this.listCorpsEtats = listCorpsEtats;
    }

    public List<Prestataire> getAllPrestatairesForCalcules() {
        return allPrestatairesForCalcules;
    }

    public void setAllPrestatairesForCalcules(List<Prestataire> allPrestatairesForCalcules) {
        this.allPrestatairesForCalcules = allPrestatairesForCalcules;
    }

    public String getTotalAcompteHT() {
        return totalAcompteHT;
    }

    public void setTotalAcompteHT(String totalAcompteHT) {
        this.totalAcompteHT = totalAcompteHT;
    }

    public String getTotalAcompteTCC() {
        return totalAcompteTCC;
    }

    public void setTotalAcompteTCC(String totalAcompteTCC) {
        this.totalAcompteTCC = totalAcompteTCC;
    }

    public String getTotalAccorps() {
        return totalAccorps;
    }

    public void setTotalAccorps(String totalAccorps) {
        this.totalAccorps = totalAccorps;
    }

    public Chantier getChantier() {
        return chantier;
    }

    public void setChantier(Chantier chantier) {
        this.chantier = chantier;
    }

    public Prestataire getPrestataire() {
        return prestataire;
    }

    public void setPrestataire(Prestataire prestataire) {
        this.prestataire = prestataire;
    }

    public Prestataire getPrestataireFromEdit() {
        return prestataireFromEdit;
    }

    public void setPrestataireFromEdit(Prestataire prestataireFromEdit) {
        this.prestataireFromEdit = prestataireFromEdit;
    }

    public List<PrestataireUtil> getAllPrestataires() {
        return allPrestataires;
    }

    public void setAllPrestataires(List<PrestataireUtil> allPrestataires) {
        this.allPrestataires = allPrestataires;
    }

    public List<PrestataireUtil> getAllPrestatairesFiltereds() {
        return allPrestatairesFiltereds;
    }

    public void setAllPrestatairesFiltereds(List<PrestataireUtil> allPrestatairesFiltereds) {
        this.allPrestatairesFiltereds = allPrestatairesFiltereds;
    }

    public Total getTotal() {
        return total;
    }

    public void setTotal(Total total) {
        this.total = total;
    }

    public List<Total> getListTotal() {
        return listTotal;
    }

    public void setListTotal(List<Total> listTotal) {
        this.listTotal = listTotal;
    }

    public List getListChantier() {
        //listChantier = ejbChantier.findAll();
        return listChantier;
    }

    public void setListChantier(List listChantier) {
        this.listChantier = listChantier;
    }

    public List<Commande> getAllCommandes() {
        return allCommandes;
    }

    public void setAllCommandes(List<Commande> allCommandes) {
        this.allCommandes = allCommandes;  
    }

    public String save() {
        try {

            Double initReliqat = 0.0;  
            refFournisseur.setNomcomplet(this.refFournisseur.getIdFournisseur().getNomFournisseur());   
            refFournisseur.setTelephone(this.refFournisseur.getIdFournisseur().getTelephoneFournisseur());  
            refFournisseur.setReliquant(initReliqat.toString());
            refFournisseur.setAccord(initReliqat.toString());
            refFournisseur.setAccompte(initReliqat.toString());
            refFournisseur.setIdCommande(this.refFournisseur.getIdCommande());
            refFournisseur.setVoyant("non");
            refFournisseur.setEcheance("Aucune");
            refFournisseur.setIdFournisseur(this.refFournisseur.getIdFournisseur()); 
            ejbRefFournisseur.insertRefFournisseur(this.refFournisseur);

            return "refFournisseurs.xhtml";

        } catch (Exception e) {
            return "new_refFournisseur";
        }
    }

    public String reset() {
        this.prestataire = new Prestataire();
        return "new_prestataire";
    }

    public void onEdit(RowEditEvent event) {

        MontantConverter mtc = new MontantConverter();

        FournisseurUtil prestaUtil = (FournisseurUtil) event.getObject();
        Reffournisseur prestaEdit = new Reffournisseur();
        prestaEdit.setIdReffournisseur(prestaUtil.getIdRefFournisseur());
        prestaEdit.setNomcomplet(prestaUtil.getNomcomplet());
        prestaEdit.setTelephone(prestaUtil.getTelephone());
        String a1 = mtc.DoubleToString(prestaUtil.getAccord());
        prestaEdit.setAccord(a1);
        prestaEdit.setNature(prestaUtil.getNature());
        String a2 = mtc.DoubleToString(prestaUtil.getAccompte());
        prestaEdit.setAccompte(a2);
        String a3 = mtc.DoubleToString(prestaUtil.getReliquant());
        prestaEdit.setReliquant(a3);
        prestaEdit.setVoyant(prestaUtil.getVoyant());
        prestaEdit.setIdCommande(prestaUtil.getIdCommande());
        prestaEdit.setIdFournisseur(prestaUtil.getFournisseur());

        Double number1 = mtc.StringToDouble(prestaEdit.getAccord());

        //Double temp2 = Double.valueOf(prestaEdit.getAccompte());
        Double number2 = mtc.StringToDouble(prestaEdit.getAccompte());

        //Double temp3 = Double.valueOf();
        Double number3 = mtc.StringToDouble(prestaEdit.getReliquant());

        Double result = number1 - number2;

        String rstt = mtc.DoubleToString(result);

        prestaEdit.setReliquant(rstt);

        if (number3 > number1) {
            prestaEdit.setVoyant("oui");
        } else {
            prestaEdit.setVoyant("non");
        }
        String vaccord = mtc.DoubleToString(number1);
        String vaccompte = mtc.DoubleToString(number2);
        prestaEdit.setAccord(vaccord);
        prestaEdit.setAccompte(vaccompte);
        ejbRefFournisseur.edit(prestaEdit);

    }

    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Modification annulée !");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void searchCalcule() {

        try {

            MontantConverter mtcc = new MontantConverter();
            this.allPrestatairesForCalcules = ejbPrestataire.listOfPrestataireByChantier(this.chantier);

            //Add element in total list
            Double valueAccordd = 0.0;
            Double valueAcompteeHT = 0.0;
            Double valueAcompteeTTC = 0.0;

            for (Prestataire p : allPrestatairesForCalcules) {
                Double number1 = mtcc.StringToDouble(p.getAccord());
                valueAccordd = valueAccordd + number1;
            }
            String r1 = mtcc.DoubleToString(valueAccordd);
            this.setTotalAccorps(r1);

            for (Prestataire p : allPrestatairesForCalcules) {
                List<Acompte> allAcomptesForCalcules = new ArrayList<>();

                allAcomptesForCalcules = ejbAcompte.listOfAcompteByPrestataire(p);

                for (Acompte a : allAcomptesForCalcules) {

                    if (a.getChoixtva().equals("non")) {
                        Double nb1 = mtcc.StringToDouble(a.getMontantAcompte());
                        valueAcompteeHT = valueAcompteeHT + nb1;
                    }

                    if (a.getChoixtva().equals("oui")) {
                        Double nb2 = mtcc.StringToDouble(a.getMontantAcompte());
                        valueAcompteeTTC = valueAcompteeTTC + nb2;
                    }

                }
            }

            String RvalueAcompteeHT = mtcc.DoubleToString(valueAcompteeHT);
            String RvalueAcompteeTTC = mtcc.DoubleToString(valueAcompteeTTC);
            this.setTotalAcompteHT(RvalueAcompteeHT);
            this.setTotalAcompteTCC(RvalueAcompteeTTC);

        } catch (Exception e) {
        }
    }

    public void renderInfo() {
        this.setChoixCreation(this.getChoixCreation());
        this.getPrestataire().setNinea("");
        this.getPrestataire().setCorps("");
        this.getPrestataire().setTelephone("");
        this.listChantier = new ArrayList<>();
    }

    public void renderInfoo() {
        this.getRefFournisseur().setIdFournisseur(this.getRefFournisseur().getIdFournisseur()); 
        this.getRefFournisseur().getIdFournisseur().setNinea(this.getRefFournisseur().getIdFournisseur().getNinea());
        allCommandes = ejbCommande.listOfCommandeByFournisseur(this.getRefFournisseur().getIdFournisseur());
        this.setAllCommandes(allCommandes);
    }

    public void renderInfooo() {
        this.listChantier = ejbChantier.listOfChantierByMarche(this.getMarche().getIdMarche());
    }

}
