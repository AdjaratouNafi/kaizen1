/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.controller;

import com.itextpdf.text.DocumentException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.FlowEvent;
import sn.accelsolution.dao.ContratFacade;
import sn.accelsolution.dao.UtilisateurFacade;
import sn.accelsolution.entities.Actionmenu;
import sn.accelsolution.entities.Contrat;
import sn.accelsolution.entities.Utilisateur;
import sn.accelsolution.util.ContratRecrutemenForm;
import sn.accelsolution.util.MontantConverter;
import sn.accelsolution.util.NumberManager;
import sn.accelsolution.util.ReportAccelModel;
import sn.accelsolution.util.ReportContrat;
import sn.accelsolution.util.ReportSalaire;
import sn.accelsolution.util.UtilControleMenu;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DELL
 */
@ManagedBean
@SessionScoped
public class ContratRecrutBean implements Serializable {

    /**
     * Creates a new instance of ContratRecrutBean
     */
    @EJB
    UtilisateurFacade ejbUser;
    @EJB
    ContratFacade ejbContrat;

    private ContratRecrutemenForm contratRecrutemenForm;
    private Utilisateur user;
    private Contrat contrat;
    private Contrat contratFromRead;
    private Contrat contratFromEdit;
    private Contrat contratFromDelete;
    private ReportContrat reportContrat;

    private boolean skip;
    private DataModel item;
    private List listUsers;
    private DataModel itemPrim;
    private List listContrats;
    private DataModel itemStages;
    private List listContratStages;
    private String getSalaireBase;
    private Double salBase;
    private Date dateEngagement;
    private Double mttsalb;
    private Double mttsals;
    private Double mttsalp;
    private String creerRecrutement;
    private String modifierRecrutement;
    private String supprimerRecrutement;
    private String consulterRecrutement;
    private String imprimerRecrutement;
    private String creerStage;
    private String modifierStage;
    private String supprimerStage;
    private String consulterStage;
    private String imprimerStage;
    private List<Actionmenu> myllActionmenus;
    private List<Actionmenu> myllActionmenusStages;

    @PostConstruct
    public void init() {

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UtilControleMenu utilControleMenu = new UtilControleMenu();

        myllActionmenus = (List<Actionmenu>) session.getAttribute("allActionmenusFromTraitementRecrutement");
        myllActionmenusStages = (List<Actionmenu>) session.getAttribute("allActionmenusFromTraitementStage");

        this.setCreerRecrutement(utilControleMenu.creerRecrutement(myllActionmenus));
        this.setModifierRecrutement(utilControleMenu.modifierRecrutement(myllActionmenus));
        this.setSupprimerRecrutement(utilControleMenu.supprimerRecrutement(myllActionmenus));
        this.setConsulterRecrutement(utilControleMenu.consulterRecrutement(myllActionmenus));
        this.setImprimerRecrutement(utilControleMenu.imprimerRecrutement(myllActionmenus));

        this.setCreerStage(utilControleMenu.creerStage(myllActionmenusStages));
        this.setModifierStage(utilControleMenu.modifierStage(myllActionmenusStages));
        this.setSupprimerStage(utilControleMenu.supprimerStage(myllActionmenusStages));
        this.setConsulterStage(utilControleMenu.consulterStage(myllActionmenusStages));
        this.setImprimerStage(utilControleMenu.imprimerStage(myllActionmenusStages));

        contratRecrutemenForm = new ContratRecrutemenForm();

        contratRecrutemenForm.setDurreeTravail("40 h / semaine");

        String art1 = "Le présent contrat a pour objet de définir les conditions de l’engagement entre l’Entreprise SARRE TECHNOBAT et Monsieur qui se déclare libre de tout engagement professionnel.";
        contratRecrutemenForm.setArticle1(art1);

        String art2 = "Le présent contrat est conclu pour une durée déterminée de (2) ans à compter duxx xx201.";
        contratRecrutemenForm.setArticle2(art2);

        String art3 = "L’Entreprise SARRE TECHNOBAT engage Monsieur xxxxx, en qualité d’Ingénieur en Réseaux et Systèmes. Il sera classé à la 9émeAcatégorie de la convention collective du Commerce.";
        contratRecrutemenForm.setArticle3(art3);

        String art4 = "Monsieur xxxxxxx consacrera l’exclusivité de son activité professionnelle à l’entreprise. Il devra respecter le règlement intérieur de l’entreprise, toutes consignes verbales ou écrites ainsi que les notes de services afférentes à son activité.\n"
                + "Il s’oblige à respecter les objectifs qui lui auront été fixés et qu’il aura acceptés.";
        contratRecrutemenForm.setArticle4(art4);

        String art5 = "Pour ne pas nuire à la bonne organisation du travail et pour permettre à l’employeur de prendre les dispositions nécessaires, toute indisponibilité du fait de maladie ou d’accident, doit être signalée à l’employeur par l’employé dans un délai de six (06) jours suivant l’accident ou la maladie. \n"
                + "L’employé doit également dans un délai de sept jours (07) jours suivant la date de l’information, produire le certificat médical.\n"
                + "L’employeur sera immédiatement avisé en cas de prolongation.";
        contratRecrutemenForm.setArticle5(art5);

        String art6 = "xxxxxx est engagé pour servir en tous lieux dans le Sénégal  où l’Entreprise SARRE TECHNOBAT aurait besoin de ses services. Son principal lieu de travail est à Dakar.";
        contratRecrutemenForm.setArticle6(art6);

        String art8 = "Conformément aux articles L.135 du Code du Travail, la durée de travail est de quarante (40) heures par semaine. Cette durée de travail sera fonction de l’horaire établi par l’employeur.";
        contratRecrutemenForm.setArticle8(art8);

        String art9 = "En contrepartie du temps de travail effectué tel que mentionné à l’article ci-dessus, le salarié percevra une rémunération mensuelle nette xxxxxx. Ce montant, se décompose comme suit :\n"
                + "Salaire de base :				\n"
                + "Sursalaire :				\n"
                + "Prime de transport :        \n"
                + "						 ---------------------\n"
                + "Total :					\n"
                + "\n"
                + "Cette rémunération nette lui sera payée par virement bancaire à l’expiration de chaque période d’un (1) mois, les cotisations sociales et charges prévues par les lois et règlements en vigueur seront pris en compte dans le bulletin de salaire.";
        contratRecrutemenForm.setArticle9(art9);

        String art10 = "Monsieur xxxxxxxx bénéficiera d’un congé annuel conformément aux articles L.148 à L.155 du Code du Travail. La période de congé sera déterminée compte tenu des nécessités du service, d’un commun accord entre les parties.\n"
                + "Toutefois, si le travailleur n’a pas acquis le droit de jouissance, il bénéficiera d’une indemnité de congé calculée sur la base des droits acquis, calculée conformément aux articles L.148 à L.155 du Code du Travail.";
        contratRecrutemenForm.setArticle10(art10);

        String art11 = "Les frais professionnels du travailleur lui seront remboursés sur présentation des justificatifs correspondants dans les limites en vigueur au sein de la société.";
        contratRecrutemenForm.setArticle11(art11);

        String art12 = "Par les soins de l’employeur, l’employé sera affilié aux régimes légaux de retraite (au niveau de l’Institut de Prévoyance Retraite du Sénégal), d’allocations familiales et d’accidents de travail (au niveau de la Caisse de sécurité sociale).";
        contratRecrutemenForm.setArticle12(art12);

        String art13 = "Compte tenu de la nature des activités de L’Entreprise SARRE TECHNOBAT, Monsieur xxxxxx s’engage au secret professionnel le plus absolu, tant pendant le temps du présent contrat que postérieurement. Toute violation de cette obligation de secret professionnel sera susceptible d’occasionner à l’encontre de l’employé des poursuites pénales.";
        contratRecrutemenForm.setArticle13(art13);

        String art14 = "Conformément à l’article L.35 du Code du Travail, en cas de rupture du présent contrat du fait de l’employé ou pour faute lourde, celui-ci s’interdit de collaborer pendant le délai d’un an, sous quelque forme que ce soit, directement ou indirectement par personne physique ou morale interposée, avec une entreprise ayant des activités concurrentes à celui de L’Entreprise SARRE TECHNOBAT dans un rayon de cinquante (50) kilomètres autour du lieu de travail.\n"
                + "De manière générale, l’employé est tenue de respecter les termes du contrat, en particulier ceux relatifs à la clause de non concurrence, la clause de confidentialité et à l’autorité hiérarchique.";
        contratRecrutemenForm.setArticle14(art14);

        String art15 = "Toute modification du présent contrat ne devra être faite qu'avec le consentement des deux parties, exprimé par écrit sous la forme d'avenant.";
        contratRecrutemenForm.setArticle15(art15);

        String art16 = "Lorsque les relations contractuelles de travail ne se poursuivent pas à l’issu de la période ci-dessus fixée, l’employé aura droit à une indemnité de fin de contrat égale à 7% du montant de la rémunération totale brute qui lui est due pendant la durée du contrat.\n"
                + "\n"
                + "Il ne peut être mis fin avant terme à ce contrat à durée déterminée qu’en cas de faute lourde, d’accord des parties constaté par écrit ou de force majeure.";
        contratRecrutemenForm.setArticle16(art16);

        contratRecrutemenForm.setDiffTypeContrat("CDD");
    }

    public ContratRecrutBean() {

        user = new Utilisateur();
        contrat = new Contrat();
        contratFromEdit = new Contrat();
        reportContrat = new ReportContrat();
        contratFromRead = new Contrat();
        contratFromDelete = new Contrat();

    }

    public Contrat getContratFromDelete() {
        return contratFromDelete;
    }

    public void setContratFromDelete(Contrat contratFromDelete) {
        this.contratFromDelete = contratFromDelete;
    }

    public String getCreerStage() {
        return creerStage;
    }

    public void setCreerStage(String creerStage) {
        this.creerStage = creerStage;
    }

    public String getModifierStage() {
        return modifierStage;
    }

    public void setModifierStage(String modifierStage) {
        this.modifierStage = modifierStage;
    }

    public String getSupprimerStage() {
        return supprimerStage;
    }

    public void setSupprimerStage(String supprimerStage) {
        this.supprimerStage = supprimerStage;
    }

    public String getConsulterStage() {
        return consulterStage;
    }

    public void setConsulterStage(String consulterStage) {
        this.consulterStage = consulterStage;
    }

    public String getImprimerStage() {
        return imprimerStage;
    }

    public void setImprimerStage(String imprimerStage) {
        this.imprimerStage = imprimerStage;
    }

    public List<Actionmenu> getMyllActionmenusStages() {
        return myllActionmenusStages;
    }

    public void setMyllActionmenusStages(List<Actionmenu> myllActionmenusStages) {
        this.myllActionmenusStages = myllActionmenusStages;
    }

    public List<Actionmenu> getMyllActionmenus() {
        return myllActionmenus;
    }

    public void setMyllActionmenus(List<Actionmenu> myllActionmenus) {
        this.myllActionmenus = myllActionmenus;
    }

    public ReportContrat getReportContrat() {
        return reportContrat;
    }

    public void setReportContrat(ReportContrat reportContrat) {
        this.reportContrat = reportContrat;
    }

    public String getCreerRecrutement() {
        return creerRecrutement;
    }

    public void setCreerRecrutement(String creerRecrutement) {
        this.creerRecrutement = creerRecrutement;
    }

    public String getModifierRecrutement() {
        return modifierRecrutement;
    }

    public void setModifierRecrutement(String modifierRecrutement) {
        this.modifierRecrutement = modifierRecrutement;
    }

    public String getSupprimerRecrutement() {
        return supprimerRecrutement;
    }

    public void setSupprimerRecrutement(String supprimerRecrutement) {
        this.supprimerRecrutement = supprimerRecrutement;
    }

    public String getConsulterRecrutement() {
        return consulterRecrutement;
    }

    public void setConsulterRecrutement(String consulterRecrutement) {
        this.consulterRecrutement = consulterRecrutement;
    }

    public String getImprimerRecrutement() {
        return imprimerRecrutement;
    }

    public void setImprimerRecrutement(String imprimerRecrutement) {
        this.imprimerRecrutement = imprimerRecrutement;
    }

    public Double getMttsalb() {
        return mttsalb;
    }

    public void setMttsalb(Double mttsalb) {
        this.mttsalb = mttsalb;
    }

    public Double getMttsals() {
        return mttsals;
    }

    public void setMttsals(Double mttsals) {
        this.mttsals = mttsals;
    }

    public Double getMttsalp() {
        return mttsalp;
    }

    public void setMttsalp(Double mttsalp) {
        this.mttsalp = mttsalp;
    }

    public Date getDateEngagement() {
        return dateEngagement;
    }

    public void setDateEngagement(Date dateEngagement) {
        this.dateEngagement = dateEngagement;
    }

    public Double getSalBase() {
        return salBase;
    }

    public void setSalBase(Double salBase) {
        this.salBase = salBase;
    }

    public String getGetSalaireBase() {
        return getSalaireBase;
    }

    public void setGetSalaireBase(String getSalaireBase) {
        this.getSalaireBase = getSalaireBase;
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

    public DataModel getItem() {
        item = new ListDataModel();
        this.setListUsers(ejbUser.listOfUserWithoutAdmin());
        item.setWrappedData(this.getListUsers());
        return item;
    }

    public void setItem(DataModel item) {
        this.item = item;
    }

    public List getListUsers() {
        listUsers = ejbUser.listOfUserWithoutAdmin();
        return listUsers;
    }

    public void setListUsers(List listUsers) {
        this.listUsers = listUsers;
    }

    public Contrat getContrat() {
        return contrat;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }

    public Contrat getContratFromEdit() {
        return contratFromEdit;
    }

    public void setContratFromEdit(Contrat contratFromEdit) {
        this.contratFromEdit = contratFromEdit;
    }

    public DataModel getItemPrim() {
        itemPrim = new ListDataModel();
        this.setListContrats(ejbContrat.listOfContratRecru());
        itemPrim.setWrappedData(this.getListContrats());
        return itemPrim;
    }

    public void setItemPrim(DataModel itemPrim) {
        this.itemPrim = itemPrim;
    }

    public List getListContrats() {
        listContrats = ejbContrat.listOfContratRecru();
        return listContrats;
    }

    public void setListContrats(List listContrats) {
        this.listContrats = listContrats;
    }

    public DataModel getItemStages() {
        itemStages = new ListDataModel();
        this.setListContratStages(ejbContrat.listOfContratStage());
        itemStages.setWrappedData(this.getListContratStages());
        return itemStages;
    }

    public void setItemStages(DataModel itemStages) {
        this.itemStages = itemStages;
    }

    public List getListContratStages() {
        listContratStages = ejbContrat.listOfContratStage();
        return listContratStages;
    }

    public void setListContratStages(List listContratStages) {
        this.listContratStages = listContratStages;
    }

    public ContratRecrutemenForm getContratRecrutemenForm() {
        return contratRecrutemenForm;
    }

    public void setContratRecrutemenForm(ContratRecrutemenForm contratRecrutemenForm) {
        this.contratRecrutemenForm = contratRecrutemenForm;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public Contrat getContratFromRead() {
        return contratFromRead;
    }

    public void setContratFromRead(Contrat contratFromRead) {
        this.contratFromRead = contratFromRead;
    }

    public void renderInfoUser() {

        this.user = this.contratRecrutemenForm.getUtilisateur();

    }

    public void renderInfoUserr() {

        this.user = this.contratFromEdit.getIdUtilisateur();

    }

    public void renderInfoSalaireBase() {
        //this.setGetSalaireBase(this.contratRecrutemenForm.getArticle7SalaireBase());
        System.out.println("Salaire :" + this.getSalBase());

    }

    public void renderInfoSurSalaire() {
        MontantConverter mtc = new MontantConverter();
        Double rs1 = this.getMttsalb() + this.getMttsals() + this.getMttsalp();
        String chaineResult = mtc.DoubleToString(rs1);
        this.contratRecrutemenForm.setArticle7SalaireBrutFiscal(chaineResult);
    }

    public String saveCdd() {

        try {

            MontantConverter mtc = new MontantConverter();
            this.contratRecrutemenForm.setArticle7SalaireBrutFiscal(this.contratRecrutemenForm.getArticle7SalaireBrutFiscal());
            String val1 = mtc.DoubleToString(this.mttsalb);
            this.contratRecrutemenForm.setArticle7SalaireBase(val1);
            String val2 = mtc.DoubleToString(this.mttsals);
            this.contratRecrutemenForm.setArticle7SurSalaire(val2);
            String val3 = mtc.DoubleToString(this.mttsalp);
            this.contratRecrutemenForm.setArticle7SalaireTransport(val3);
            /* Insertion*/

            contrat.setDureeTravail(this.contratRecrutemenForm.getArticle7SalaireTransport());
            contrat.setSalairebase(this.contratRecrutemenForm.getArticle7SalaireBase());
            contrat.setSurSalaire(this.contratRecrutemenForm.getArticle7SurSalaire());
            contrat.setSalaireBrutFiscal(this.contratRecrutemenForm.getArticle7SalaireBrutFiscal());
            contrat.setLocalTypeContrat("Recrutement");
            contrat.setDiffTypeContrat("CDD");
            contrat.setArticle1(this.contratRecrutemenForm.getArticle1());
            contrat.setArticle2(this.contratRecrutemenForm.getArticle2());
            contrat.setArticle3(this.contratRecrutemenForm.getArticle3());
            contrat.setArticle4(this.contratRecrutemenForm.getArticle4());
            contrat.setArticle5(this.contratRecrutemenForm.getArticle5());
            contrat.setArticle6(this.contratRecrutemenForm.getArticle6());
            contrat.setArticle8(this.contratRecrutemenForm.getArticle8());
            contrat.setArticle9(this.contratRecrutemenForm.getArticle9());
            contrat.setArticle10(this.contratRecrutemenForm.getArticle10());
            contrat.setArticle11(this.contratRecrutemenForm.getArticle11());
            contrat.setArticle12(this.contratRecrutemenForm.getArticle12());
            contrat.setArticle13(this.contratRecrutemenForm.getArticle13());
            contrat.setArticle14(this.contratRecrutemenForm.getArticle14());
            contrat.setArticle15(this.contratRecrutemenForm.getArticle15());
            contrat.setArticle16(this.contratRecrutemenForm.getArticle16());
            contrat.setIdUtilisateur(this.contratRecrutemenForm.getUtilisateur());
            System.out.println("Contrat :" + this.contrat);
            ejbContrat.insertContratCDD(contrat);

            /*Repport*/
            reportContrat.generateCDD(contrat);
            return "recrues";
        } catch (Exception e) {
            return "new_recrue1";
        }

    }

    public String saveCdi() {

        try {

            MontantConverter mtc = new MontantConverter();
            this.contratRecrutemenForm.setArticle7SalaireBrutFiscal(this.contratRecrutemenForm.getArticle7SalaireBrutFiscal());
            String val1 = mtc.DoubleToString(this.mttsalb);
            this.contratRecrutemenForm.setArticle7SalaireBase(val1);
            String val2 = mtc.DoubleToString(this.mttsals);
            this.contratRecrutemenForm.setArticle7SurSalaire(val2);
            String val3 = mtc.DoubleToString(this.mttsalp);
            this.contratRecrutemenForm.setArticle7SalaireTransport(val3);
            /* Insertion*/

            contrat.setDureeTravail(this.contratRecrutemenForm.getArticle7SalaireTransport());
            contrat.setSalairebase(this.contratRecrutemenForm.getArticle7SalaireBase());
            contrat.setSurSalaire(this.contratRecrutemenForm.getArticle7SurSalaire());
            contrat.setSalaireBrutFiscal(this.contratRecrutemenForm.getArticle7SalaireBrutFiscal());
            contrat.setLocalTypeContrat("Recrutement");
            contrat.setDiffTypeContrat("CDI");
            contrat.setArticle1(this.contratRecrutemenForm.getArticle1());
            contrat.setArticle2(this.contratRecrutemenForm.getArticle2());
            contrat.setArticle3(this.contratRecrutemenForm.getArticle3());
            contrat.setArticle4(this.contratRecrutemenForm.getArticle4());
            contrat.setArticle5(this.contratRecrutemenForm.getArticle5());
            contrat.setArticle6(this.contratRecrutemenForm.getArticle6());
            contrat.setArticle8(this.contratRecrutemenForm.getArticle8());
            contrat.setArticle9(this.contratRecrutemenForm.getArticle9());
            contrat.setArticle10(this.contratRecrutemenForm.getArticle10());
            contrat.setArticle11(this.contratRecrutemenForm.getArticle11());
            contrat.setArticle12(this.contratRecrutemenForm.getArticle12());
            contrat.setArticle13(this.contratRecrutemenForm.getArticle13());
            contrat.setArticle14(this.contratRecrutemenForm.getArticle14());
            contrat.setArticle15(this.contratRecrutemenForm.getArticle15());
            contrat.setArticle16(this.contratRecrutemenForm.getArticle16());
            contrat.setIdUtilisateur(this.contratRecrutemenForm.getUtilisateur());
            System.out.println("Contrat :" + this.contrat);
            ejbContrat.insertContratCDD(contrat);

            /*Repport*/
            reportContrat.generateCDI(contrat);

            return "recrues";
        } catch (Exception e) {
            return "new_recrue2";
        }

    }

    public String saveContratStage() {

        try {

            MontantConverter mtc = new MontantConverter();
            this.contratRecrutemenForm.setArticle7SalaireBrutFiscal(this.contratRecrutemenForm.getArticle7SalaireBrutFiscal());
            String val1 = mtc.DoubleToString(this.mttsalb);
            this.contratRecrutemenForm.setArticle7SalaireBase(val1);
            String val2 = mtc.DoubleToString(this.mttsals);
            this.contratRecrutemenForm.setArticle7SurSalaire(val2);
            String val3 = mtc.DoubleToString(this.mttsalp);
            this.contratRecrutemenForm.setArticle7SalaireTransport(val3);
            /* Insertion*/

            contrat.setDureeTravail(this.contratRecrutemenForm.getArticle7SalaireTransport());
            contrat.setSalairebase(this.contratRecrutemenForm.getArticle7SalaireBase());
            contrat.setSurSalaire(this.contratRecrutemenForm.getArticle7SurSalaire());
            contrat.setSalaireBrutFiscal(this.contratRecrutemenForm.getArticle7SalaireBrutFiscal());
            contrat.setLocalTypeContrat("Stage");
            contrat.setDiffTypeContrat("Stage");
            contrat.setArticle1(this.contratRecrutemenForm.getArticle1());
            contrat.setArticle2(this.contratRecrutemenForm.getArticle2());
            contrat.setArticle3(this.contratRecrutemenForm.getArticle3());
            contrat.setArticle4(this.contratRecrutemenForm.getArticle4());
            contrat.setArticle5(this.contratRecrutemenForm.getArticle5());
            contrat.setArticle6(this.contratRecrutemenForm.getArticle6());
            contrat.setArticle8(this.contratRecrutemenForm.getArticle8());
            contrat.setArticle9(this.contratRecrutemenForm.getArticle9());
            contrat.setArticle10(this.contratRecrutemenForm.getArticle10());
            contrat.setArticle11(this.contratRecrutemenForm.getArticle11());
            contrat.setArticle12(this.contratRecrutemenForm.getArticle12());
            contrat.setArticle13(this.contratRecrutemenForm.getArticle13());
            contrat.setArticle14(this.contratRecrutemenForm.getArticle14());
            contrat.setArticle15(this.contratRecrutemenForm.getArticle15());
            contrat.setArticle16(this.contratRecrutemenForm.getArticle16());
            contrat.setIdUtilisateur(this.contratRecrutemenForm.getUtilisateur());
            System.out.println("Contrat :" + this.contrat);
            ejbContrat.insertContratCDD(contrat);

            /*Repport*/
            reportContrat.generateStage(contrat);

            return "stagaires";
        } catch (Exception e) {
            return "new_stagaire";
        }

    }

    public void reset() throws DocumentException {

        System.out.println("Je suis là ");
        ReportSalaire rps = new ReportSalaire();
        rps.TestList();
    }

    public String resetModif() {
        return "users";
    }

    public String consulter() {
        try {
            this.contratFromRead = (Contrat) itemPrim.getRowData();
            System.out.println("Valeur :" + this.contratFromRead);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "consulter_contrat";
    }

    public String consulterStageM() {
        try {
            this.contratFromRead = (Contrat) itemStages.getRowData();
            System.out.println("Valeur :" + this.contratFromRead);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "consulter_contrat_1";
    }

    public String precedent() {
        return "recrues";
    }

    public String precedentt() {
        return "stagaires";
    }

    public String generateContrat() {
        try {

            this.contratFromRead = ejbContrat.find(this.contratFromRead.getIdContrat());
            if (this.contratFromRead.getDiffTypeContrat().equals("CDD")) {
                // Generer pour ESTPROD
//                this.reportContrat.generateCDD(this.contratFromRead);
                // Generer pour Accel
                ReportAccelModel racm = new ReportAccelModel();
                racm.generateCDD(this.contratFromRead);
            } else {
                this.reportContrat.generateCDI(this.contratFromRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "recrues";
    }

    public String generateContratt() {
        try {
            this.contratFromRead = ejbContrat.find(this.contratFromRead.getIdContrat());
            this.reportContrat.generateStage(this.contratFromRead);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "stagaires";
    }

    public String editer() {
        try {
            this.contratFromEdit = (Contrat) itemPrim.getRowData();
            this.user = this.contratFromEdit.getIdUtilisateur();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "edit_recrue";
    }

    public String update() {
        try {

            UtilUtfconvert utfconvert = new UtilUtfconvert();

            this.contratFromEdit.setArticle1(utfconvert.convertFromUTF8(contrat.getArticle1()));
            this.contratFromEdit.setArticle2(utfconvert.convertFromUTF8(contrat.getArticle2()));
            this.contratFromEdit.setArticle3(utfconvert.convertFromUTF8(contrat.getArticle3()));
            this.contratFromEdit.setArticle4(utfconvert.convertFromUTF8(contrat.getArticle4()));
            this.contratFromEdit.setArticle5(utfconvert.convertFromUTF8(contrat.getArticle5()));
            this.contratFromEdit.setArticle6(utfconvert.convertFromUTF8(contrat.getArticle6()));
            this.contratFromEdit.setArticle8(utfconvert.convertFromUTF8(contrat.getArticle8()));
            this.contratFromEdit.setArticle9(utfconvert.convertFromUTF8(contrat.getArticle9()));
            this.contratFromEdit.setArticle10(utfconvert.convertFromUTF8(contrat.getArticle10()));
            this.contratFromEdit.setArticle11(utfconvert.convertFromUTF8(contrat.getArticle11()));
            this.contratFromEdit.setArticle12(utfconvert.convertFromUTF8(contrat.getArticle12()));
            this.contratFromEdit.setArticle13(utfconvert.convertFromUTF8(contrat.getArticle13()));
            this.contratFromEdit.setArticle14(utfconvert.convertFromUTF8(contrat.getArticle14()));
            this.contratFromEdit.setArticle15(utfconvert.convertFromUTF8(contrat.getArticle15()));
            this.contratFromEdit.setArticle16(utfconvert.convertFromUTF8(contrat.getArticle16()));

            ejbContrat.edit(this.contratFromEdit);

            if (this.contratFromEdit.getDiffTypeContrat().equals("CDD")) {
                this.reportContrat.generateCDD(this.contratFromEdit);
            } else {
                this.reportContrat.generateCDI(this.contratFromEdit);
            }

            return "recrues";
        } catch (Exception e) {

            e.printStackTrace();
            return "edit_recrue";
        }

    }

    public String supprimer() {
        try {
            contratFromDelete = (Contrat) itemPrim.getRowData();
            ejbContrat.remove(contratFromDelete);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "recrues";
    }

    public String editerStage() {
        try {
            this.contratFromEdit = (Contrat) itemStages.getRowData();
            this.user = this.contratFromEdit.getIdUtilisateur();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "edit_stagaire";
    }

    public String updateStage() {
        try {

            UtilUtfconvert utfconvert = new UtilUtfconvert();

            this.contratFromEdit.setArticle1(utfconvert.convertFromUTF8(contrat.getArticle1()));
            this.contratFromEdit.setArticle2(utfconvert.convertFromUTF8(contrat.getArticle2()));
            this.contratFromEdit.setArticle3(utfconvert.convertFromUTF8(contrat.getArticle3()));
            this.contratFromEdit.setArticle4(utfconvert.convertFromUTF8(contrat.getArticle4()));
            this.contratFromEdit.setArticle5(utfconvert.convertFromUTF8(contrat.getArticle5()));
            this.contratFromEdit.setArticle6(utfconvert.convertFromUTF8(contrat.getArticle6()));
            this.contratFromEdit.setArticle8(utfconvert.convertFromUTF8(contrat.getArticle8()));
            this.contratFromEdit.setArticle9(utfconvert.convertFromUTF8(contrat.getArticle9()));
            this.contratFromEdit.setArticle10(utfconvert.convertFromUTF8(contrat.getArticle10()));
            this.contratFromEdit.setArticle11(utfconvert.convertFromUTF8(contrat.getArticle11()));
            this.contratFromEdit.setArticle12(utfconvert.convertFromUTF8(contrat.getArticle12()));
            this.contratFromEdit.setArticle13(utfconvert.convertFromUTF8(contrat.getArticle13()));
            this.contratFromEdit.setArticle14(utfconvert.convertFromUTF8(contrat.getArticle14()));
            this.contratFromEdit.setArticle15(utfconvert.convertFromUTF8(contrat.getArticle15()));
            this.contratFromEdit.setArticle16(utfconvert.convertFromUTF8(contrat.getArticle16()));

            ejbContrat.edit(this.contratFromEdit);

            this.reportContrat.generateStage(this.contratFromEdit);

            return "stagaires";
        } catch (Exception e) {

            e.printStackTrace();
            return "edit_stagaire";
        }

    }

    public String supprimerStage() {
        try {
            contratFromDelete = (Contrat) itemStages.getRowData();
            ejbContrat.remove(contratFromDelete);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "stagaires";
    }

}
