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
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import sn.accelsolution.dao.ClientFacade;
import sn.accelsolution.dao.DetailledevisFacade;
import sn.accelsolution.dao.DevisFacade;
import sn.accelsolution.dao.DeviseFacade;
import sn.accelsolution.dao.NotificationFacade;
import sn.accelsolution.dao.TonpomcoefFacade;
import sn.accelsolution.dao.UtilisateurFacade;
import sn.accelsolution.entities.Actionmenu;
import sn.accelsolution.entities.Client;
import sn.accelsolution.entities.Detailledevis;
import sn.accelsolution.entities.Devis;
import sn.accelsolution.entities.Devise;
import sn.accelsolution.entities.Notification;
import sn.accelsolution.entities.Tonpomcoef;
import sn.accelsolution.entities.Utilisateur;
import sn.accelsolution.util.FactureUtil;
import sn.accelsolution.util.GenerationCodePdf;
import sn.accelsolution.util.MontantConverter;
import sn.accelsolution.util.UtilControleMenu;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DELL
 */
@ManagedBean
@ViewScoped
public class DevisBean implements Serializable {

    /**
     * Creates a new instance of ContratRecrutBean
     */
    @EJB
    UtilisateurFacade ejbUser;
    @EJB
    ClientFacade ejbClient;
    @EJB
    DevisFacade ejbDevis;
    @EJB
    DetailledevisFacade ejbDetailleDevis;
    @EJB
    NotificationFacade ejbNotification;
    @EJB
    TonpomcoefFacade ejbTonpomcoef;
    @EJB
    DeviseFacade ejbDevise;
    
    private Devis devis;
    private Detailledevis detailleDevis;
    private Devis devisFromEdit;
    private Detailledevis detailleDevisFromEdit;
    
    private List<Devis> allDevis;
    private List<Devis> allDevisByUser;
    private List<Detailledevis> allDetailleDevis;
    private List<FactureUtil> listDevis;
    private List<FactureUtil> listDevisSauvegarde;
    private String mtHTAAfficher;
    private String mtTVAAAfficher;
    private String mttc;
    private String testNumber;
    private List<Actionmenu> myllActionmenus;
    private String creerDevis;
    private String modifierDevis;
    private String supprimerDevis;
    private String consulterDevis;
    private String imprimerDevis;
    private Utilisateur userNotif;
    private Double coef;
    private int firstPassage;
    private List<Devis> devisUtil1;
    private List<Devis> devisUtil2;
    private List<Devis> devisUtil3;
    private List<Devis> devisUtil4;
    private List<Devis> filteredListDevis1;
    private List<Devis> filteredListDevis2;
    
    @PostConstruct
    public void init() {
        
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UtilControleMenu utilControleMenu = new UtilControleMenu();
        
        userNotif = new Utilisateur();
        userNotif = (Utilisateur) session.getAttribute("user");
        
        myllActionmenus = (List<Actionmenu>) session.getAttribute("allActionmenusFromTraitementDevis");
        
        this.setCreerDevis(utilControleMenu.creerDevis(myllActionmenus));
        this.setModifierDevis(utilControleMenu.modifierDevis(myllActionmenus));
        this.setSupprimerDevis(utilControleMenu.supprimerDevis(myllActionmenus));
        this.setConsulterDevis(utilControleMenu.consulterDevis(myllActionmenus));
        this.setImprimerDevis(utilControleMenu.imprimerFacture(myllActionmenus));       
        
        GenerationCodePdf gcpdf = new GenerationCodePdf();
        int maxDev = ejbDevis.maxDevis();
        Devis mymaxDev = ejbDevis.find(maxDev);
        String ancienCode = mymaxDev.getNumeroDevis();
        String codeDevis = gcpdf.genererCodeDevis(ancienCode);
        String numDev = codeDevis;
        this.devis.setNumeroDevis(numDev);
        FactureUtil fu = new FactureUtil();
        fu.setDesignation("");
        fu.setTypeitem("");
        fu.setRef("");
        fu.setUnite(" ");
        fu.setPu("");
        fu.setQuantite(0);
        listDevis.add(fu);
        this.setTestNumber("");
        
        allDevis = new ArrayList<>();
        allDevisByUser = new ArrayList<>();
        
        this.allDevis = ejbDevis.findAll();
        
        this.allDevisByUser = ejbDevis.listOfDevisByUser(this.userNotif);
        
    }
    
    public DevisBean() {    
        listDevis = new ArrayList<>();
        listDevisSauvegarde = new ArrayList<>();
        allDetailleDevis = new ArrayList<>();
        devis = new Devis();
        devisFromEdit = new Devis();
        detailleDevis = new Detailledevis();
        detailleDevisFromEdit = new Detailledevis();    
        this.firstPassage = 0;
        
    }
    
    public List<Devis> getFilteredListDevis1() {
        return filteredListDevis1;
    }
    
    public void setFilteredListDevis1(List<Devis> filteredListDevis1) {
        this.filteredListDevis1 = filteredListDevis1;
    }
    
    public List<Devis> getFilteredListDevis2() {
        return filteredListDevis2;
    }
    
    public void setFilteredListDevis2(List<Devis> filteredListDevis2) {
        this.filteredListDevis2 = filteredListDevis2;
    }
    
    public List<Devis> getAllDevisByUser() {
        return allDevisByUser;
    }
    
    public void setAllDevisByUser(List<Devis> allDevisByUser) {
        this.allDevisByUser = allDevisByUser;
    }
    
    public List<Devis> getDevisUtil1() {
        return devisUtil1;
    }
    
    public void setDevisUtil1(List<Devis> devisUtil1) {
        this.devisUtil1 = devisUtil1;
    }
    
    public List<Devis> getDevisUtil2() {
        return devisUtil2;
    }
    
    public void setDevisUtil2(List<Devis> devisUtil2) {
        this.devisUtil2 = devisUtil2;
    }
    
    public List<Devis> getDevisUtil3() {
        return devisUtil3;
    }
    
    public void setDevisUtil3(List<Devis> devisUtil3) {
        this.devisUtil3 = devisUtil3;
    }
    
    public List<Devis> getDevisUtil4() {
        return devisUtil4;
    }
    
    public void setDevisUtil4(List<Devis> devisUtil4) {
        this.devisUtil4 = devisUtil4;
    }
    
    public String getMtHTAAfficher() {
        return mtHTAAfficher;
    }
    
    public void setMtHTAAfficher(String mtHTAAfficher) {
        this.mtHTAAfficher = mtHTAAfficher;
    }
    
    public String getMtTVAAAfficher() {
        return mtTVAAAfficher;
    }
    
    public void setMtTVAAAfficher(String mtTVAAAfficher) {
        this.mtTVAAAfficher = mtTVAAAfficher;
    }
    
    public int getFirstPassage() {
        return firstPassage;
    }
    
    public void setFirstPassage(int firstPassage) {
        this.firstPassage = firstPassage;
    }
    
    public List<FactureUtil> getListDevisSauvegarde() {
        return listDevisSauvegarde;
    }
    
    public void setListDevisSauvegarde(List<FactureUtil> listDevisSauvegarde) {
        this.listDevisSauvegarde = listDevisSauvegarde;
    }
    
    public Double getCoef() {
        return coef;
    }
    
    public void setCoef(Double coef) {
        this.coef = coef;
    }
    
    public Utilisateur getUserNotif() {
        return userNotif;
    }
    
    public void setUserNotif(Utilisateur userNotif) {
        this.userNotif = userNotif;
    }
    
    public List<Actionmenu> getMyllActionmenus() {
        return myllActionmenus;
    }
    
    public void setMyllActionmenus(List<Actionmenu> myllActionmenus) {
        this.myllActionmenus = myllActionmenus;
    }
    
    public String getCreerDevis() {
        return creerDevis;
    }
    
    public void setCreerDevis(String creerDevis) {
        this.creerDevis = creerDevis;
    }
    
    public String getModifierDevis() {
        return modifierDevis;
    }
    
    public void setModifierDevis(String modifierDevis) {
        this.modifierDevis = modifierDevis;
    }
    
    public String getSupprimerDevis() {
        return supprimerDevis;
    }
    
    public void setSupprimerDevis(String supprimerDevis) {
        this.supprimerDevis = supprimerDevis;
    }
    
    public String getConsulterDevis() {
        return consulterDevis;
    }
    
    public void setConsulterDevis(String consulterDevis) {
        this.consulterDevis = consulterDevis;
    }
    
    public String getImprimerDevis() {
        return imprimerDevis;
    }
    
    public void setImprimerDevis(String imprimerDevis) {
        this.imprimerDevis = imprimerDevis;
    }
    
    public String getTestNumber() {
        return testNumber;
    }
    
    public void setTestNumber(String testNumber) {
        this.testNumber = testNumber;
    }
    
    public Devis getDevis() {
        return devis;
    }
    
    public void setDevis(Devis devis) {
        this.devis = devis;
    }
    
    public Detailledevis getDetailleDevis() {
        return detailleDevis;
    }
    
    public void setDetailleDevis(Detailledevis detailleDevis) {
        this.detailleDevis = detailleDevis;
    }
    
    public Devis getDevisFromEdit() {
        return devisFromEdit;
    }
    
    public void setDevisFromEdit(Devis devisFromEdit) {
        this.devisFromEdit = devisFromEdit;
    }
    
    public Detailledevis getDetailleDevisFromEdit() {
        return detailleDevisFromEdit;
    }
    
    public void setDetailleDevisFromEdit(Detailledevis detailleDevisFromEdit) {
        this.detailleDevisFromEdit = detailleDevisFromEdit;
    }
    
    public List<Devis> getAllDevis() {
        return allDevis;
    }
    
    public void setAllDevis(List<Devis> allDevis) {
        this.allDevis = allDevis;
    }
    
    public List<Detailledevis> getAllDetailleDevis() {
        return allDetailleDevis;
    }
    
    public void setAllDetailleDevis(List<Detailledevis> allDetailleDevis) {
        this.allDetailleDevis = allDetailleDevis;
    }
    
    public List<FactureUtil> getListDevis() {
        return listDevis;
    }
    
    public void setListDevis(List<FactureUtil> listDevis) {
        this.listDevis = listDevis;
    }
    
    public void renderInfo() {
        
        try {
            this.devis.setIdClient(this.devis.getIdClient());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void addNewInputLigne() {
        FactureUtil fu = new FactureUtil();
        fu.setDesignation("");
        fu.setRef("");
        fu.setUnite(" ");
        fu.setPu("");
        fu.setQuantite(0);
        listDevis.add(fu);
    }
    
    public void removeInputLigne(int index) {
        listDevis.remove(index);
    }
    
    public String getMttc() {
        return mttc;
    }
    
    public void setMttc(String mttc) {
        this.mttc = mttc;
    }
    
    public void montantFormat(String mtt) {
        
    }
    
    public void formatConverter(FactureUtil f) {
        
        try {
            
            int index = 0;
            for (int x = 0; x < this.listDevis.size(); x++) {
                
                FactureUtil f1 = this.listDevis.get(x);
                if (f1.getDesignation().equalsIgnoreCase(f.getDesignation())) {
                    index = x;
                }
            }
            
            MontantConverter mtc = new MontantConverter();
            Double cv = mtc.StringToDouble(f.getPu());
            String newv = mtc.DoubleToString(cv);
            f.setPu(newv);
            this.listDevis.set(index, f);
        } catch (NumberFormatException ex) { // handle your exception

        }
        
    }
    
    public void calculateTTC(FactureUtil f) {
        
        try {
            
            if (this.devis.getCoef().equals("")) {
                this.devis.setCoef("0.0");
            } else {
                System.out.println("Le coef n'est pas vide !");
            }
            
            MontantConverter mtc = new MontantConverter();
            int index = 0;
            for (int x = 0; x < this.listDevis.size(); x++) {
                
                FactureUtil f1 = this.listDevis.get(x);
                if (f1.getDesignation().equalsIgnoreCase(f.getDesignation())) {
                    index = x;
                }
            }
            
            if (f.getPu() == null || f.getPu().isEmpty()) {
                f.setPu("0");
                this.listDevis.set(index, f);
            } else {
                Double cv = mtc.StringToDouble(f.getPu());
                String newv = mtc.DoubleToString(cv);
                f.setPu(newv);
                this.listDevis.set(index, f);
            }


            /*Mise à jour du TTC*/
            this.setMttc("");
            Double mthT = 0.0;
            for (FactureUtil dfttc : this.listDevis) {
                if (dfttc.getPu().equals("")) {
                    
                } else {
                    if (dfttc.getTypeitem().equals("Item")) {
                        Double puc = mtc.StringToDouble(dfttc.getPu());
                        Double calcule1 = dfttc.getQuantite() * puc;
                        mthT = mthT + calcule1;
                    }
                    
                }
                
            }
            
            this.setMtHTAAfficher("");
            System.out.println("Montant total: " + mthT);
//            String mthtv = mtc.DoubleToString(mthT);
//            this.setMtHTAAfficher(mthtv);

            /*TVA*/
            Double mthTTVA = mthT * 0.18;
//            String mttvav = mtc.DoubleToString(mthTTVA);
//            this.setMtTVAAAfficher(mttvav);
            /*Mt TTC*/
            Double mtTTC = mthT + mthTTVA;
            String rctotal = mtc.DoubleToString(mtTTC);
            this.setMttc(rctotal);
            
            int nbElementVide = 0;
            for (FactureUtil df : this.listDevis) {
                
                if (df.getPu().equals("")) {
                    nbElementVide++;
                }
                
            }
            
            if (nbElementVide == 0) {
                FactureUtil fu = new FactureUtil();
                fu.setDesignation("");
                fu.setRef("");
                fu.setUnite(" ");
                fu.setPu("");
                fu.setQuantite(0);
                listDevis.add(fu);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void calculateCoef() {
        
        try {
            
            if (this.devis.getCoef().equals("")) {
                this.devis.setCoef("0.0");
            } else {
                System.out.println("Le coef n'est pas vide !");
            }
            
            if (this.firstPassage == 0) {
                
                for (int k = 0; k < this.listDevis.size(); k++) {
                    Tonpomcoef tpc = new Tonpomcoef();
                    tpc = ejbTonpomcoef.getTonpomCreationFromDelete(this.devis.getNumeroDevis(), 1);
                    if (tpc != null) {
                        ejbTonpomcoef.deleteExistingTinponByNumeDevis2(this.devis.getNumeroDevis(), 1);
                    }
                }
                
                for (int j = 0; j < this.listDevis.size(); j++) {
                    Tonpomcoef tpc = new Tonpomcoef();
                    FactureUtil f1P = new FactureUtil();
                    f1P = this.listDevis.get(j);
                    tpc.setDesignation(f1P.getDesignation());
                    tpc.setTypeitem(f1P.getTypeitem());
                    tpc.setRef(f1P.getRef());
                    tpc.setUnite(f1P.getUnite());
                    tpc.setQuantite(f1P.getQuantite());
                    tpc.setPu(f1P.getPu());
                    tpc.setNumerodevis(this.devis.getNumeroDevis());
                    int ordrepassage = this.firstPassage + 1;
                    tpc.setPassage(ordrepassage);
                    tpc.setTypepassage("Creation");
                    
                    boolean rst = ejbTonpomcoef.verifierTonpom(this.devis.getNumeroDevis(), this.firstPassage);
                    if ((rst == true) && (j == 0)) {
                        ejbTonpomcoef.deleteByNumeDevis(this.devis.getNumeroDevis());
                        ejbTonpomcoef.insertTonpom(tpc);
                    }
                    
                    if ((rst == true) && (j != 0)) {
                        ejbTonpomcoef.insertTonpom(tpc);
                    }
                    
                    if (rst != true) {
                        ejbTonpomcoef.insertTonpom(tpc);
                    }
                    
                }
                this.methodeCoef1(this.listDevis);
            } else {
                this.methodeCoef2(this.listDevis);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void methodeCoef1(List<FactureUtil> listD) {
        System.out.println("Valeur coef1: " + this.firstPassage);
        
        MontantConverter mtc = new MontantConverter();
        int index = 0;
        
        for (int x = 0; x < this.listDevis.size(); x++) {
            FactureUtil f1 = new FactureUtil();
            f1 = this.listDevis.get(x);
            if (f1.getDesignation().equals("")) {
                
            } else {
                if (f1.getTypeitem().equals("Item")) {
                    index = x;
                    Double opu = mtc.StringToDouble(f1.getPu());
                    Double vcoef = Double.parseDouble(this.devis.getCoef());
                    Double newPu = opu * vcoef;
                    Double vvaleur = opu + newPu;
                    String newv = mtc.DoubleToString(vvaleur);
                    f1.setPu(newv);
                    this.listDevis.set(index, f1);
                }
                
            }
            
        }

        /*Mise à jour du TTC*/
        this.setMttc("");
        Double mthT = 0.0;
        for (FactureUtil dfttc : this.listDevis) {
            if (dfttc.getPu().equals("")) {
                
            } else {
                if (dfttc.getTypeitem().equals("Item")) {
                    Double puc = mtc.StringToDouble(dfttc.getPu());
                    Double calcule1 = dfttc.getQuantite() * puc;
                    mthT = mthT + calcule1;
                }
                
            }
            
        }
        
        System.out.println("Montant total: " + mthT);

        /*TVA*/
        Double mthTTVA = mthT * 0.18;
        Double mtTTC = mthT + mthTTVA;
        String rctotal = mtc.DoubleToString(mtTTC);
        this.setMttc(rctotal);
        System.out.println("Montant TTC: " + this.getMttc());
        int ordp = this.firstPassage + 1;
        this.setFirstPassage(ordp);
        
    }
    
    public void methodeCoef2(List<FactureUtil> listD) {
        System.out.println("Valeur coef2: " + this.firstPassage);
        List<Tonpomcoef> listOfTonpom = ejbTonpomcoef.listTonpom(this.devis.getNumeroDevis());
        this.listDevis.clear();
        if (listOfTonpom != null) {
            for (Tonpomcoef tp : listOfTonpom) {
                if (tp.getDesignation().equals("")) {
                    
                } else {
                    FactureUtil f1P = new FactureUtil();
                    f1P.setDesignation(tp.getDesignation());
                    f1P.setTypeitem(tp.getTypeitem());
                    f1P.setRef(tp.getRef());
                    f1P.setUnite(tp.getUnite());
                    f1P.setQuantite(tp.getQuantite());
                    f1P.setPu(tp.getPu());
                    this.listDevis.add(f1P);
                }
                
            }
        }
        
        MontantConverter mtc = new MontantConverter();
        int index = 0;
        for (int x = 0; x < this.listDevis.size(); x++) {
            FactureUtil f1 = new FactureUtil();
            f1 = this.listDevis.get(x);
            
            if (f1.getDesignation().equals("")) {
                
            } else {
                if (f1.getTypeitem().equals("Item")) {
                    index = x;
                    Double opu = mtc.StringToDouble(f1.getPu());
                    Double vcoef = Double.parseDouble(this.devis.getCoef());
                    Double newPu = opu * vcoef;
                    Double vraivaleur = opu + newPu;
                    String newv = mtc.DoubleToString(vraivaleur);
                    f1.setPu(newv);
                    this.listDevis.set(index, f1);
                }
            }
            
        }

        /*Mise à jour du TTC*/
        this.setMttc("");
        Double mthT = 0.0;
        for (FactureUtil dfttc : this.listDevis) {
            if (dfttc.getPu().equals("")) {
                
            } else {
                if (dfttc.getTypeitem().equals("Item")) {
                    Double puc = mtc.StringToDouble(dfttc.getPu());
                    Double calcule1 = dfttc.getQuantite() * puc;
                    mthT = mthT + calcule1;
                }
                
            }
            
        }
        
        System.out.println("Montant total: " + mthT);

        /*TVA*/
        Double mthTTVA = mthT * 0.18;
        Double mtTTC = mthT + mthTTVA;
        String rctotal = mtc.DoubleToString(mtTTC);
        this.setMttc(rctotal);
    }
    
    public String save() {
        
        try {
            
            if (this.devis.getCoef() == null || this.devis.getCoef().isEmpty()) {
                this.devis.setCoef("0.0");
            }
            
            this.devis.setEtat("En attente de validation");
            this.devis.setIdUtilisateur(this.userNotif);
            ejbDevis.insertDevis(this.devis);
            
            int idDev = ejbDevis.maxDevis();
            Devis dv = ejbDevis.find(idDev);
            System.out.println("Devis");
            
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
            
            notification.setMessage("Devis à valider");
            notification.setDateNotification(reportDate);
            notification.setHeure(heures);
            notification.setMinute(minutes);
            notification.setSeconde(seconds);
            notification.setTraitement("Non fait");
            notification.setTypeNotification("Devis");
            notification.setEtatNotification("Non lu");
            notification.setIdUtilisateur(this.userNotif);
            notification.setIdDevis(dv);
            
            ejbNotification.insertNotification3(notification);
            for (FactureUtil f : listDevis) {
                if (f.getDesignation().equals("")) {
                } else {
                    this.detailleDevis.setDesignation(f.getDesignation());
                    this.detailleDevis.setTypeitem(f.getTypeitem());
                    this.detailleDevis.setReference(f.getRef());
                    this.detailleDevis.setUnite(f.getUnite());
                    this.detailleDevis.setQuantite(f.getQuantite());
                    this.detailleDevis.setPu(f.getPu());

//                    if (f.getRef() == null || f.getRef().isEmpty()) {
//                        System.out.println("Pas de reference");
//                    } else {
//                        this.detailleDevis.setReference(f.getRef());
//                    }
//
//                    if (f.getUnite() == null || f.getUnite().isEmpty()) {
//                        System.out.println("Pas d'unite");
//                    } else {
//                        this.detailleDevis.setUnite(f.getUnite());
//                    }
//
//                    if (f.getQuantite() == 0) {
//                        System.out.println("Pas de qt");
//                    } else {
//                        this.detailleDevis.setQuantite(f.getQuantite());
//                    }
//
//                    if (f.getPu() == null || f.getPu().equals("0") || f.getPu().isEmpty()) {
//                        System.out.println("Pas de PU");
//                    } else {
//                        this.detailleDevis.setPu(f.getPu());
//                    }
                    this.detailleDevis.setIdDevis(dv);

                    //System.out.println("Detail devis: "+this.detailleDevis);
                    ejbDetailleDevis.insertDetailleDevis(this.detailleDevis);
                }
                
            }
            
            return "devis";
            
        } catch (Exception e) {
            return "new_devis";
        }
    }
    
    public String reset() {
        this.devis = new Devis();
        return "devis";
    }
    
    public String supprimer(Devis d) {
        try {
            
            System.out.println("devis :" + d.getNumeroDevis());
            
            List<Detailledevis> allDetaildevisToDelete = ejbDetailleDevis.listOfDetailleDevisByDevis(d);
            for (Detailledevis dtd : allDetaildevisToDelete) {
                ejbDetailleDevis.remove(dtd);
            }
            
            List<Notification> allNotificationToDelete = ejbNotification.listOfNotificationByDevis(d);
            for (Notification notif : allNotificationToDelete) {
                ejbNotification.remove(notif);
            }
            
            ejbDevis.remove(d);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "devis";
    }
    
}
