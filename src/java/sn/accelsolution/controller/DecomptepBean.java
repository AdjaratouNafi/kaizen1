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
import sn.accelsolution.dao.DecomptepFacade;
import sn.accelsolution.dao.DetailledecomptepFacade;
import sn.accelsolution.dao.DetaillenewfactureFacade;
import sn.accelsolution.dao.NewfactureFacade;
import sn.accelsolution.dao.NotificationFacade;
import sn.accelsolution.dao.UtilisateurFacade;
import sn.accelsolution.entities.Actionmenu;
import sn.accelsolution.entities.Client;
import sn.accelsolution.entities.Decomptep;
import sn.accelsolution.entities.Detailledecomptep;
import sn.accelsolution.entities.Detaillenewfacture;
import sn.accelsolution.entities.Devis;
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
public class DecomptepBean implements Serializable {

    /**
     * Creates a new instance of ContratRecrutBean
     */
    @EJB
    UtilisateurFacade ejbUser;
    @EJB
    NewfactureFacade ejbNewfacture;
    @EJB
    DetaillenewfactureFacade ejbDetailleNewfacture;
    @EJB
    DecomptepFacade ejbDecomptep;
    @EJB
    DetailledecomptepFacade ejbDetailleDecomptep;
    @EJB
    NotificationFacade ejbNotification;

    private Decomptep decomptep;
    private Detailledecomptep detailleDecomptep;
    private Decomptep decomptepFromEdit;
    private Detailledecomptep detailleDecomptepFromEdit;

    private List<Decomptep> allDecompteps;
    private List<Detailledecomptep> allDetailleDecompteps;
    private List<FactureUtil> listDecompteps;
    private List<Newfacture> listfactures;
    private FactureUtil factureUtil;
    private String mttc;
    private List<Actionmenu> myllActionmenus;
    private String creerDecompte;
    private String modifierDecompte;
    private String supprimerDecompte;
    private String consulterDecompte;
    private String imprimerDecompte;
    private Utilisateur userNotif;
    private List<Decomptep> decomptepUtil1;
    private List<Decomptep> decomptepUtil2;
    private List<Newfacture> filteredListDecompte;

    @PostConstruct
    public void init() {

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UtilControleMenu utilControleMenu = new UtilControleMenu();

        userNotif = new Utilisateur();
        userNotif = (Utilisateur) session.getAttribute("user");

        myllActionmenus = (List<Actionmenu>) session.getAttribute("allActionmenusFromTraitementDecomptes");

        //this.setCreerDecompte(utilControleMenu.creerDecompte(myllActionmenus));
        this.setCreerDecompte("yes");
        // this.setModifierDecompte(utilControleMenu.modifierDecompte(myllActionmenus));
        this.setModifierDecompte("yes");
        //this.setSupprimerDecompte(utilControleMenu.supprimerDecompte(myllActionmenus));
        this.setSupprimerDecompte("yes");
        //this.setConsulterDecompte(utilControleMenu.consulterDecompte(myllActionmenus));
        this.setConsulterDecompte("yes");
        //this.setImprimerDecompte(utilControleMenu.imprimerDecompte(myllActionmenus));
        this.setImprimerDecompte("yes");

        GenerationCodePdf gcpdf = new GenerationCodePdf();
        int maxDecpt = ejbDecomptep.maxDecompte();
        Decomptep mymaxDecpt = ejbDecomptep.find(maxDecpt);
        String ancienCode = mymaxDecpt.getNumeroDecomptep();
        String codeDecompte = gcpdf.genererCodeDecompte(ancienCode);
        String numDecpt = codeDecompte;
        this.decomptep.setNumeroDecomptep(numDecpt);
        /* FactureUtil fu = new FactureUtil();
         fu.setDesignation("");
         fu.setPu("");
         fu.setQuantite(0);
         fu.setExecution(0);
         listDecompteps.add(fu); */

        System.out.println("Valeur de consulter: " + this.getConsulterDecompte());

        allDecompteps = new ArrayList<>();

        allDecompteps = ejbDecomptep.findAll();

    }

    public DecomptepBean() {

        listDecompteps = new ArrayList<>();
        allDetailleDecompteps = new ArrayList<>();
        decomptep = new Decomptep();
        decomptepFromEdit = new Decomptep();
        detailleDecomptep = new Detailledecomptep();
        detailleDecomptepFromEdit = new Detailledecomptep();

    }

    public List<Newfacture> getFilteredListDecompte() {
        return filteredListDecompte;
    }

    public void setFilteredListDecompte(List<Newfacture> filteredListDecompte) {
        this.filteredListDecompte = filteredListDecompte;
    }

    public List<Decomptep> getDecomptepUtil1() {
        return decomptepUtil1;
    }

    public void setDecomptepUtil1(List<Decomptep> decomptepUtil1) {
        this.decomptepUtil1 = decomptepUtil1;
    }

    public List<Decomptep> getDecomptepUtil2() {
        return decomptepUtil2;
    }

    public void setDecomptepUtil2(List<Decomptep> decomptepUtil2) {
        this.decomptepUtil2 = decomptepUtil2;
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

    public String getCreerDecompte() {
        return creerDecompte;
    }

    public void setCreerDecompte(String creerDecompte) {
        this.creerDecompte = creerDecompte;
    }

    public String getModifierDecompte() {
        return modifierDecompte;
    }

    public void setModifierDecompte(String modifierDecompte) {
        this.modifierDecompte = modifierDecompte;
    }

    public String getSupprimerDecompte() {
        return supprimerDecompte;
    }

    public void setSupprimerDecompte(String supprimerDecompte) {
        this.supprimerDecompte = supprimerDecompte;
    }

    public String getConsulterDecompte() {
        return consulterDecompte;
    }

    public void setConsulterDecompte(String consulterDecompte) {
        this.consulterDecompte = consulterDecompte;
    }

    public String getImprimerDecompte() {
        return imprimerDecompte;
    }

    public void setImprimerDecompte(String imprimerDecompte) {
        this.imprimerDecompte = imprimerDecompte;
    }

    public FactureUtil getFactureUtil() {
        return factureUtil;
    }

    public void setFactureUtil(FactureUtil factureUtil) {
        this.factureUtil = factureUtil;
    }

    public Decomptep getDecomptep() {
        return decomptep;
    }

    public void setDecomptep(Decomptep decomptep) {
        this.decomptep = decomptep;
    }

    public Detailledecomptep getDetailleDecomptep() {
        return detailleDecomptep;
    }

    public void setDetailleDecomptep(Detailledecomptep detailleDecomptep) {
        this.detailleDecomptep = detailleDecomptep;
    }

    public Decomptep getDecomptepFromEdit() {
        return decomptepFromEdit;
    }

    public void setDecomptepFromEdit(Decomptep decomptepFromEdit) {
        this.decomptepFromEdit = decomptepFromEdit;
    }

    public Detailledecomptep getDetailleDecomptepFromEdit() {
        return detailleDecomptepFromEdit;
    }

    public void setDetailleDecomptepFromEdit(Detailledecomptep detailleDecomptepFromEdit) {
        this.detailleDecomptepFromEdit = detailleDecomptepFromEdit;
    }

    public List<Decomptep> getAllDecompteps() {
        return allDecompteps;
    }

    public void setAllDecompteps(List<Decomptep> allDecompteps) {
        this.allDecompteps = allDecompteps;
    }

    public List<Detailledecomptep> getAllDetailleDecompteps() {
        return allDetailleDecompteps;
    }

    public void setAllDetailleDecompteps(List<Detailledecomptep> allDetailleDecompteps) {
        this.allDetailleDecompteps = allDetailleDecompteps;
    }

    public List<FactureUtil> getListDecompteps() {
        return listDecompteps;
    }

    public void setListDecompteps(List<FactureUtil> listDecompteps) {
        this.listDecompteps = listDecompteps;
    }

    public List<Newfacture> getListfactures() {
        listfactures = ejbNewfacture.findAll();
        return listfactures;
    }

    public void setListfactures(List<Newfacture> listfactures) {
        this.listfactures = listfactures;
    }

    public void addNewInputLigne() {
        FactureUtil fu = new FactureUtil();
        fu.setDesignation("");
        fu.setPu("");
        fu.setQuantite(0);
        fu.setExecution(0);
        listDecompteps.add(fu);
    }

    public void removeInputLigne(int index) {
        listDecompteps.remove(index);
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
            for (FactureUtil df : this.listDecompteps) {

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
            this.decomptep.setIdNewfacture(this.decomptep.getIdNewfacture());
            List<Detaillenewfacture> listDetailfactureByfacture = ejbDetailleNewfacture.listOfDetailleFactureByfacture(this.decomptep.getIdNewfacture());
            for (Detaillenewfacture f : listDetailfactureByfacture) {
                FactureUtil fu = new FactureUtil();
                fu.setDesignation(f.getDesignation());
                fu.setTypeitem(f.getTypeitem());
                fu.setRef(f.getReference());
                fu.setUnite(f.getUnite());
                fu.setQuantite(f.getQuantite());
                fu.setPu(f.getPu());
                fu.setExecution(0);
                this.listDecompteps.add(fu);
            }

            MontantConverter mtc = new MontantConverter();
            Double mtht = 0.0;
            for (FactureUtil df : this.listDecompteps) {

                Double puc = mtc.StringToDouble(df.getPu());
                Double ctotal = puc * df.getQuantite();
                Double mttvasub = ctotal * 0.18;
                Double mttcsub = ctotal + mttvasub;
                mtht = mtht + mttcsub;
            }
            String rctotal = mtc.DoubleToString(mtht);
            this.setMttc(rctotal);

            this.decomptep.setMontantlettre(this.decomptep.getIdNewfacture().getMintantlettreNewfacture());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String save() {

        try {

            this.decomptep.setEtat("En attente de validation");
            this.decomptep.setIdUtilisateur(this.userNotif);
            ejbDecomptep.insertDecompte(this.decomptep);
            int idDec = ejbDecomptep.maxDecompte();
            Decomptep decp = ejbDecomptep.find(idDec);

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

            notification.setMessage("Decompte à valider");
            notification.setDateNotification(reportDate);
            notification.setHeure(heures);
            notification.setMinute(minutes);
            notification.setSeconde(seconds);
            notification.setTraitement("Non fait");
            notification.setTypeNotification("Decompte");
            notification.setEtatNotification("Non lu");
            notification.setIdUtilisateur(this.userNotif);
            notification.setIdDecomptep(decp);

            ejbNotification.insertNotification5(notification);

            for (FactureUtil f : listDecompteps) {
                this.detailleDecomptep.setDesignation(f.getDesignation());
                this.detailleDecomptep.setTypeitem(f.getTypeitem()); 
                this.detailleDecomptep.setQuantite(f.getQuantite());
                this.detailleDecomptep.setPu(f.getPu());
                this.detailleDecomptep.setReference(f.getRef());
                this.detailleDecomptep.setUnite(f.getUnite());
                this.detailleDecomptep.setExecution(f.getExecution());
                this.detailleDecomptep.setIdDecomptep(decp);
                ejbDetailleDecomptep.insertDetailleDecompte(this.detailleDecomptep);
            }

            return "decomptep";
        } catch (Exception e) {
            return "new_decomptep";
        }
    }

    public String reset() {
        this.decomptep = new Decomptep();
        return "decomptep";
    }

    public String supprimer(Decomptep d) {
        try {

            ejbDetailleDecomptep.deleteByDecompte(d.getIdDecomptep());
            ejbDecomptep.remove(d);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "decomptep";
    }

}
