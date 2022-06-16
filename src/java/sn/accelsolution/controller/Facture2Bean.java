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
import sn.accelsolution.dao.DetailledevisFacade;
import sn.accelsolution.dao.DetaillenewfactureFacade;
import sn.accelsolution.dao.DevisFacade;
import sn.accelsolution.dao.MarcheFacade;
import sn.accelsolution.dao.NewfactureFacade;
import sn.accelsolution.dao.NotificationFacade;
import sn.accelsolution.dao.UtilisateurFacade;
import sn.accelsolution.entities.Actionmenu;
import sn.accelsolution.entities.Client;
import sn.accelsolution.entities.Detailledevis;
import sn.accelsolution.entities.Detaillenewfacture;
import sn.accelsolution.entities.Devis;
import sn.accelsolution.entities.Marche;
import sn.accelsolution.entities.Newfacture;
import sn.accelsolution.entities.Notification;
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
public class Facture2Bean implements Serializable {

    /**
     * Creates a new instance of ContratRecrutBean
     */
    @EJB
    UtilisateurFacade ejbUser;
    @EJB
    DevisFacade ejbDevis;
    @EJB
    NewfactureFacade ejbNewfacture;
    @EJB
    DetaillenewfactureFacade ejbDetailleNewfacture;
    @EJB
    DetailledevisFacade ejbDetailledevis;
    @EJB
    MarcheFacade ejbMarche;
    @EJB
    NotificationFacade ejbNotification;
    
    private Newfacture newfacture;
    private Detaillenewfacture detailleNewfacture;
    private Newfacture newfactureFromEdit;
    private Detaillenewfacture detailleNewfactureFromEdit;
    
    private List<Newfacture> allNewfactures;
    private List<Detaillenewfacture> allDetailleNewfactures;
    private List<FactureUtil> listNewfactures;
    private List<Devis> listDevis;
    private String mttc;
    private List<Actionmenu> myllActionmenus;
    private String creerFacture;
    private String modifierFacture;
    private String supprimerFacture;
    private String consulterFacture;
    private String imprimerFacture;
    private Utilisateur userNotif;
    private List<Newfacture> newfactureUtil1;
    private List<Newfacture> newfactureUtil2;
    private List<Newfacture> filteredListFacture;
    
    @PostConstruct
    public void init() {
        
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UtilControleMenu utilControleMenu = new UtilControleMenu();
        
        userNotif = new Utilisateur();
        userNotif = (Utilisateur) session.getAttribute("user");
        
        myllActionmenus = (List<Actionmenu>) session.getAttribute("allActionmenusFromTraitementFactures");
        
        this.setCreerFacture(utilControleMenu.creerFacture(myllActionmenus));
        this.setModifierFacture(utilControleMenu.modifierFacture(myllActionmenus));
        this.setSupprimerFacture(utilControleMenu.supprimerFacture(myllActionmenus));
        this.setConsulterFacture(utilControleMenu.consulterFacture(myllActionmenus));
        this.setImprimerFacture(utilControleMenu.imprimerFacture(myllActionmenus));
        
        GenerationCodePdf gcpdf = new GenerationCodePdf();
        int maxfac = ejbNewfacture.maxDevis();
        Newfacture mymaxfac = ejbNewfacture.find(maxfac);
        String ancienCode = mymaxfac.getNumeroNewfacture();
        String codeFacture = gcpdf.genererCodeFacture(ancienCode);
        String numFact = codeFacture;
        this.newfacture.setNumeroNewfacture(numFact);

        /*FactureUtil fu = new FactureUtil();
         fu.setDesignation("");
         fu.setPu("");
         fu.setQuantite(0);
         listNewfactures.add(fu);*/
        
        allNewfactures = ejbNewfacture.findAll();
        
    }
    
    public Facture2Bean() {
        
        listNewfactures = new ArrayList<>();
        
        allDetailleNewfactures = new ArrayList<>();
        newfacture = new Newfacture();
        newfactureFromEdit = new Newfacture();
        detailleNewfacture = new Detaillenewfacture();
        detailleNewfactureFromEdit = new Detaillenewfacture();
        
    }
    
    public List<Newfacture> getFilteredListFacture() {
        return filteredListFacture;
    }
    
    public void setFilteredListFacture(List<Newfacture> filteredListFacture) {
        this.filteredListFacture = filteredListFacture;
    }
    
    public List<Newfacture> getNewfactureUtil1() {
        return newfactureUtil1;
    }
    
    public void setNewfactureUtil1(List<Newfacture> newfactureUtil1) {
        this.newfactureUtil1 = newfactureUtil1;
    }
    
    public List<Newfacture> getNewfactureUtil2() {
        return newfactureUtil2;
    }
    
    public void setNewfactureUtil2(List<Newfacture> newfactureUtil2) {
        this.newfactureUtil2 = newfactureUtil2;
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
    
    public String getCreerFacture() {
        return creerFacture;
    }
    
    public void setCreerFacture(String creerFacture) {
        this.creerFacture = creerFacture;
    }
    
    public String getModifierFacture() {
        return modifierFacture;
    }
    
    public void setModifierFacture(String modifierFacture) {
        this.modifierFacture = modifierFacture;
    }
    
    public String getSupprimerFacture() {
        return supprimerFacture;
    }
    
    public void setSupprimerFacture(String supprimerFacture) {
        this.supprimerFacture = supprimerFacture;
    }
    
    public String getConsulterFacture() {
        return consulterFacture;
    }
    
    public void setConsulterFacture(String consulterFacture) {
        this.consulterFacture = consulterFacture;
    }
    
    public String getImprimerFacture() {
        return imprimerFacture;
    }
    
    public void setImprimerFacture(String imprimerFacture) {
        this.imprimerFacture = imprimerFacture;
    }
    
    public Newfacture getNewfacture() {
        return newfacture;
    }
    
    public void setNewfacture(Newfacture newfacture) {
        this.newfacture = newfacture;
    }
    
    public Newfacture getNewfactureFromEdit() {
        return newfactureFromEdit;
    }
    
    public void setNewfactureFromEdit(Newfacture newfactureFromEdit) {
        this.newfactureFromEdit = newfactureFromEdit;
    }
    
    public Detaillenewfacture getDetailleNewfactureFromEdit() {
        return detailleNewfactureFromEdit;
    }
    
    public void setDetailleNewfactureFromEdit(Detaillenewfacture detailleNewfactureFromEdit) {
        this.detailleNewfactureFromEdit = detailleNewfactureFromEdit;
    }
    
    public List<Newfacture> getAllNewfactures() {
        return allNewfactures;
    }
    
    public void setAllNewfactures(List<Newfacture> allNewfactures) {
        this.allNewfactures = allNewfactures;
    }
    
    public List<Detaillenewfacture> getAllDetailleNewfactures() {
        return allDetailleNewfactures;
    }
    
    public void setAllDetailleNewfactures(List<Detaillenewfacture> allDetailleNewfactures) {
        this.allDetailleNewfactures = allDetailleNewfactures;
    }
    
    public List<FactureUtil> getListNewfactures() {
        return listNewfactures;
    }
    
    public void setListNewfactures(List<FactureUtil> listNewfactures) {
        this.listNewfactures = listNewfactures;
    }
    
    public List<Devis> getListDevis() {
        listDevis = ejbDevis.findAll();
        return listDevis;
    }
    
    public void setListDevis(List<Devis> listDevis) {
        this.listDevis = listDevis;
    }
    
    public Detaillenewfacture getDetailleNewfacture() {
        return detailleNewfacture;
    }
    
    public void setDetailleNewfacture(Detaillenewfacture detailleNewfacture) {
        this.detailleNewfacture = detailleNewfacture;
    }
    
    public void addNewInputLigne() {
        FactureUtil fu = new FactureUtil();
        fu.setDesignation("");
        fu.setPu("");
        fu.setQuantite(0);
        listNewfactures.add(fu);
    }
    
    public void removeInputLigne(int index) {
        listNewfactures.remove(index);
    }
    
    public String getMttc() {
        return mttc;
    }
    
    public void setMttc(String mttc) {
        this.mttc = mttc;
    }
    
    public void calculateTTC() {
        
        try {
            
            MontantConverter mtc = new MontantConverter();
            Double mtht = 0.0;
            for (FactureUtil df : this.listNewfactures) {
                
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
    
    public void renderInfo() {
        
        try {
            this.newfacture.setIdDevis(this.newfacture.getIdDevis());
            List<Detailledevis> listDetaildevisByDevis = ejbDetailledevis.listOfDetailleDevisByDevis(this.newfacture.getIdDevis());
            Marche marche = ejbMarche.marcheByDevis(this.newfacture.getIdDevis());
            this.newfacture.setIdMarche(marche);
            for (Detailledevis dd : listDetaildevisByDevis) {
                FactureUtil fu = new FactureUtil();
                fu.setDesignation(dd.getDesignation()); 
                fu.setTypeitem(dd.getTypeitem());
                fu.setRef(dd.getReference());
                fu.setUnite(dd.getUnite());
                fu.setPu(dd.getPu());
                fu.setQuantite(dd.getQuantite());
                this.listNewfactures.add(fu);
            }
            
            MontantConverter mtc = new MontantConverter();
            Double mtht = 0.0;
            for (FactureUtil df : this.listNewfactures) {
                
                Double puc = mtc.StringToDouble(df.getPu());
                Double ctotal = puc * df.getQuantite();
                Double mttvasub = ctotal * 0.18;
                Double mttcsub = ctotal + mttvasub;
                mtht = mtht + mttcsub;
            }
            String rctotal = mtc.DoubleToString(mtht);
            this.setMttc(rctotal);
            this.newfacture.setMintantlettreNewfacture(this.newfacture.getIdDevis().getMontantlettre());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String save() {
        
        try {
            
            this.newfacture.setEtat("En attente de validation");
            ejbNewfacture.insertfacture(this.newfacture);
            int idFact = ejbNewfacture.maxDevis();
            Newfacture fact = ejbNewfacture.find(idFact);
            
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
            
            notification.setMessage("Facture à valider");
            notification.setDateNotification(reportDate);
            notification.setHeure(heures);
            notification.setMinute(minutes);
            notification.setSeconde(seconds);
            notification.setTraitement("Non fait");
            notification.setTypeNotification("Facture");
            notification.setEtatNotification("Non lu");
            notification.setIdUtilisateur(this.userNotif);
            notification.setIdNewfacture(fact);
            
            ejbNotification.insertNotification4(notification);
            
            for (FactureUtil f : listNewfactures) {
                this.detailleNewfacture.setDesignation(f.getDesignation());
                this.detailleNewfacture.setTypeitem(f.getTypeitem());
                this.detailleNewfacture.setReference(f.getRef());
                this.detailleNewfacture.setUnite(f.getUnite());
                this.detailleNewfacture.setQuantite(f.getQuantite());
                this.detailleNewfacture.setPu(f.getPu());
                this.detailleNewfacture.setIdNewfacture(fact);
                ejbDetailleNewfacture.insertDetailleDevis(this.detailleNewfacture);
            }
            
            return "facture2";
        } catch (Exception e) {
            return "new_facture2";
        }
    }
    
    public String reset() {
        this.newfacture = new Newfacture();
        return "facture2";
    }
    
    public String supprimer(Newfacture f) {
        try {
            ejbDetailleNewfacture.deleteByDevis(f.getIdNewfacture());
            ejbNewfacture.remove(f);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "facture2";
    }
    
}
