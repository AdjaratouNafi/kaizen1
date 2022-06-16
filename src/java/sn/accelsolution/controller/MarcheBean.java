/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.controller;

import java.io.Serializable;
import java.util.ArrayList;
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
import sn.accelsolution.dao.AcompteFacade;
import sn.accelsolution.dao.ChantierFacade;
import sn.accelsolution.dao.ClientFacade;
import sn.accelsolution.dao.DecomptepFacade;
import sn.accelsolution.dao.DetaillechantierFacade;
import sn.accelsolution.dao.DetailleCommandeFacade;
import sn.accelsolution.dao.DetailledecomptepFacade;
import sn.accelsolution.dao.DetailledevisFacade;
import sn.accelsolution.dao.DetaillenewfactureFacade;
import sn.accelsolution.dao.DevisFacade;
import sn.accelsolution.dao.LivraisonFacade;
import sn.accelsolution.dao.ManagementFacade;
import sn.accelsolution.dao.MarchandiseFacade;
import sn.accelsolution.dao.MarcheFacade;
import sn.accelsolution.dao.NewfactureFacade;
import sn.accelsolution.dao.OperationFacade;
import sn.accelsolution.dao.PrestataireFacade;
import sn.accelsolution.dao.PrixFacade;
import sn.accelsolution.dao.RessourceFacade;
import sn.accelsolution.dao.SortiestockFacade;
import sn.accelsolution.dao.StockFacade;
import sn.accelsolution.entities.Acompte;
import sn.accelsolution.entities.Actionmenu;
import sn.accelsolution.entities.Chantier;
import sn.accelsolution.entities.Client;
import sn.accelsolution.entities.Decomptep;
import sn.accelsolution.entities.Detaillechantier;
import sn.accelsolution.entities.Detailledecomptep;
import sn.accelsolution.entities.Detailledevis;
import sn.accelsolution.entities.Detaillenewfacture;
import sn.accelsolution.entities.Devis;
import sn.accelsolution.entities.Livraison;
import sn.accelsolution.entities.Management;
import sn.accelsolution.entities.Marchandise;
import sn.accelsolution.entities.Marche;
import sn.accelsolution.entities.Newfacture;
import sn.accelsolution.entities.Operation;
import sn.accelsolution.entities.Prestataire;
import sn.accelsolution.entities.Sortiestock;
import sn.accelsolution.entities.Stock;
import sn.accelsolution.entities.Utilisateur;
import sn.accelsolution.util.MontantConverter;
import sn.accelsolution.util.ReportAccelModel;
import sn.accelsolution.util.ReportMarches;
import sn.accelsolution.util.UtilControleMenu;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DELL
 */
@ManagedBean
@ViewScoped
public class MarcheBean implements Serializable {

    @EJB
    ClientFacade ejbclient;

    @EJB
    MarcheFacade ejbmarche;

    @EJB
    ChantierFacade ejbchantier;

    @EJB
    DetaillechantierFacade ejbdetaillechantier;

    @EJB
    RessourceFacade ejbressource;

    @EJB
    OperationFacade ejboperation;
    @EJB
    LivraisonFacade ejblivraison;
    @EJB
    DetailleCommandeFacade ejbdetaillecommande;
    @EJB
    PrixFacade ejbprix;
    @EJB
    MarchandiseFacade ejbmarchandise;
    @EJB
    StockFacade ejbstock;
    @EJB
    NewfactureFacade ejbNewfacture;
    @EJB
    DetaillenewfactureFacade ejbDetailleNewfacture;
    @EJB
    DevisFacade ejbDevis;
    @EJB
    DetailledevisFacade ejbDetailleDevis;
    @EJB
    SortiestockFacade ejbSortieStock;
    @EJB
    PrestataireFacade ejbPrestataire;
    @EJB
    AcompteFacade ejbAcompte;
    @EJB
    DecomptepFacade ejbDecompte;
    @EJB
    DetailledecomptepFacade ejbDetailleDecompte;
    @EJB
    ManagementFacade ejbManagement;

    private DataModel item;
    //private DataModel itemChantiers;
    private List<Chantier> itemChantiers;
    private List listMarches;
    private Marche marche;
    private Marche marcheFromEdit;

    private List listClients;
    private String soldeMarche;
    private String totalAppro;
    private String totalApproDirect;
    private String totalApproStock;
    private String totalMoHt;
    private String totalMoTtc;
    private String reliquat;
    private String totalDepenses;
    private String totalDecompte;
    private List<Chantier> listChantier;
    private Chantier chantierFromEdit;
    private List<Detaillechantier> listDetailleChantier;
    private List<Detaillechantier> listPersonnelChantier;
    private List<Operation> listOperations;
    private List listFacture;
    private List listDetailleFacture;
    private List listDevis;
    private Double mtDemarageFromEdit;
    private Double mtCautionFromEdit;
    private Double mtPrimCautionFromEdit;
    private List<Actionmenu> myllActionmenus;
    private String creerMarcher;
    private String modifierMarcher;
    private String supprimerMarcher;
    private String consulterMarcher;
    private String imprimerMarcher;
    private String pourcentageExecution;
    private List<Marche> marcheUtil1;
    private List<Marche> marcheUtil2;
    private List<Marche> marcheUtil3;
    private List<Marche> marcheUtil4;
    private List<Marche> filteredListMarche;
    private List<Marche> allMarches;

    /**
     * Creates a new instance of UtilisateurBean
     */
    @PostConstruct
    public void init() {

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UtilControleMenu utilControleMenu = new UtilControleMenu();

        myllActionmenus = (List<Actionmenu>) session.getAttribute("allActionmenusFromTraitementMarches");

        this.setCreerMarcher(utilControleMenu.creerMarcher(myllActionmenus));
        this.setModifierMarcher(utilControleMenu.modifierMarcher(myllActionmenus));
        this.setSupprimerMarcher(utilControleMenu.supprimerMarcher(myllActionmenus));
        this.setConsulterMarcher(utilControleMenu.consulterMarcher(myllActionmenus));
        this.setImprimerMarcher(utilControleMenu.imprimerMarcher(myllActionmenus));

//        item = new ListDataModel();
//        this.setListMarches(ejbmarche.findAll());
//        item.setWrappedData(this.getListMarches());
        this.setListMarches(ejbmarche.findAll());
        this.allMarches = new ArrayList<>();
        this.allMarches = ejbmarche.findAll();

    }

    public MarcheBean() {
        marche = new Marche();
        marche.setTvaPrecompte("0.18");
        marcheFromEdit = new Marche();
        marcheFromEdit.setTvaPrecompte("0.18");
        chantierFromEdit = new Chantier();
        marcheUtil3 = new ArrayList<>();
        marcheUtil4 = new ArrayList<>();
    }

    public List<Marche> getAllMarches() {
        return allMarches;
    }

    public void setAllMarches(List<Marche> allMarches) {
        this.allMarches = allMarches;
    }

    public List<Marche> getFilteredListMarche() {
        return filteredListMarche;
    }

    public void setFilteredListMarche(List<Marche> filteredListMarche) {
        this.filteredListMarche = filteredListMarche;
    }

    public List<Marche> getMarcheUtil3() {
        return marcheUtil3;
    }

    public void setMarcheUtil3(List<Marche> marcheUtil3) {
        this.marcheUtil3 = marcheUtil3;
    }

    public List<Marche> getMarcheUtil4() {
        return marcheUtil4;
    }

    public void setMarcheUtil4(List<Marche> marcheUtil4) {
        this.marcheUtil4 = marcheUtil4;
    }

    public List<Marche> getMarcheUtil1() {
        return marcheUtil1;
    }

    public void setMarcheUtil1(List<Marche> marcheUtil1) {
        this.marcheUtil1 = marcheUtil1;
    }

    public List<Marche> getMarcheUtil2() {
        return marcheUtil2;
    }

    public void setMarcheUtil2(List<Marche> marcheUtil2) {
        this.marcheUtil2 = marcheUtil2;
    }

    public String getPourcentageExecution() {
        return pourcentageExecution;
    }

    public void setPourcentageExecution(String pourcentageExecution) {
        this.pourcentageExecution = pourcentageExecution;
    }

    public List<Actionmenu> getMyllActionmenus() {
        return myllActionmenus;
    }

    public void setMyllActionmenus(List<Actionmenu> myllActionmenus) {
        this.myllActionmenus = myllActionmenus;
    }

    public String getCreerMarcher() {
        return creerMarcher;
    }

    public void setCreerMarcher(String creerMarcher) {
        this.creerMarcher = creerMarcher;
    }

    public String getModifierMarcher() {
        return modifierMarcher;
    }

    public void setModifierMarcher(String modifierMarcher) {
        this.modifierMarcher = modifierMarcher;
    }

    public String getSupprimerMarcher() {
        return supprimerMarcher;
    }

    public void setSupprimerMarcher(String supprimerMarcher) {
        this.supprimerMarcher = supprimerMarcher;
    }

    public String getConsulterMarcher() {
        return consulterMarcher;
    }

    public void setConsulterMarcher(String consulterMarcher) {
        this.consulterMarcher = consulterMarcher;
    }

    public String getImprimerMarcher() {
        return imprimerMarcher;
    }

    public void setImprimerMarcher(String imprimerMarcher) {
        this.imprimerMarcher = imprimerMarcher;
    }

    public String getTotalDecompte() {
        return totalDecompte;
    }

    public void setTotalDecompte(String totalDecompte) {
        this.totalDecompte = totalDecompte;
    }

    public String getTotalDepenses() {
        return totalDepenses;
    }

    public void setTotalDepenses(String totalDepenses) {
        this.totalDepenses = totalDepenses;
    }

    public Double getMtPrimCautionFromEdit() {
        return mtPrimCautionFromEdit;
    }

    public void setMtPrimCautionFromEdit(Double mtPrimCautionFromEdit) {
        this.mtPrimCautionFromEdit = mtPrimCautionFromEdit;
    }

    public String getTotalMoTtc() {
        return totalMoTtc;
    }

    public void setTotalMoTtc(String totalMoTtc) {
        this.totalMoTtc = totalMoTtc;
    }

    public String getTotalMoHt() {
        return totalMoHt;
    }

    public void setTotalMoHt(String totalMoHt) {
        this.totalMoHt = totalMoHt;
    }

    public String getTotalApproDirect() {
        return totalApproDirect;
    }

    public void setTotalApproDirect(String totalApproDirect) {
        this.totalApproDirect = totalApproDirect;
    }

    public String getTotalApproStock() {
        return totalApproStock;
    }

    public void setTotalApproStock(String totalApproStock) {
        this.totalApproStock = totalApproStock;
    }

    public Double getMtDemarageFromEdit() {
        return mtDemarageFromEdit;
    }

    public void setMtDemarageFromEdit(Double mtDemarageFromEdit) {
        this.mtDemarageFromEdit = mtDemarageFromEdit;
    }

    public Double getMtCautionFromEdit() {
        return mtCautionFromEdit;
    }

    public void setMtCautionFromEdit(Double mtCautionFromEdit) {
        this.mtCautionFromEdit = mtCautionFromEdit;
    }

    public List getListDevis() {
        listDevis = ejbDevis.findAll();
        return listDevis;
    }

    public void setListDevis(List listDevis) {
        this.listDevis = listDevis;
    }

    public List getListFacture() {
        listFacture = ejbNewfacture.findAll();
        return listFacture;
    }

    public void setListFacture(List listFacture) {
        this.listFacture = listFacture;
    }

    public List getListDetailleFacture() {
        return listDetailleFacture;
    }

    public void setListDetailleFacture(List listDetailleFacture) {
        this.listDetailleFacture = listDetailleFacture;
    }

    public String getTotalAppro() {
        return totalAppro;
    }

    public void setTotalAppro(String totalAppro) {
        this.totalAppro = totalAppro;
    }

    public String getReliquat() {
        return reliquat;
    }

    public void setReliquat(String reliquat) {
        this.reliquat = reliquat;
    }

    public List<Operation> getListOperations() {
        return listOperations;
    }

    public void setListOperations(List<Operation> listOperations) {
        this.listOperations = listOperations;
    }

    public Chantier getChantierFromEdit() {
        return chantierFromEdit;
    }

    public void setChantierFromEdit(Chantier chantierFromEdit) {
        this.chantierFromEdit = chantierFromEdit;
    }

    public List<Chantier> getListChantier() {
        return listChantier;
    }

    public void setListChantier(List<Chantier> listChantier) {
        this.listChantier = listChantier;
    }

    public String getSoldeMarche() {
        return soldeMarche;
    }

    public void setSoldeMarche(String soldeMarche) {
        this.soldeMarche = soldeMarche;
    }

    public DataModel getItem() {
//        item = new ListDataModel();
//        this.setListMarches(marcheUtil2);
//        item.setWrappedData(this.getListMarches());
        return item;
    }

    public List<Chantier> getItemChantiers() {
        return itemChantiers;
    }

    public void setItemChantiers(List<Chantier> itemChantiers) {
        this.itemChantiers = itemChantiers;
    }

    public void setItem(DataModel item) {
        this.item = item;
    }

    public List getListClients() {
        listClients = ejbclient.findAll();
        return listClients;
    }

    public void setListClients(List listClients) {
        this.listClients = listClients;
    }

    public List getListMarches() {
        return listMarches;
    }

    public void setListMarches(List listMarches) {
        this.listMarches = listMarches;
    }

    public Marche getMarche() {
        return marche;
    }

    public void setMarche(Marche marche) {
        this.marche = marche;
    }

    public Marche getMarcheFromEdit() {
        return marcheFromEdit;
    }

    public void setMarcheFromEdit(Marche marcheFromEdit) {
        this.marcheFromEdit = marcheFromEdit;
    }

    public List<Detaillechantier> getListDetailleChantier() {
        return listDetailleChantier;
    }

    public void setListDetailleChantier(List<Detaillechantier> listDetailleChantier) {
        this.listDetailleChantier = listDetailleChantier;
    }

    public List<Detaillechantier> getListPersonnelChantier() {
        return listPersonnelChantier;
    }

    public void setListPersonnelChantier(List<Detaillechantier> listPersonnelChantier) {
        this.listPersonnelChantier = listPersonnelChantier;
    }

    public void renderInfo() {

        try {

            this.marcheFromEdit.setIdDevis(this.marcheFromEdit.getIdDevis());
            List<Detailledevis> listDetaildevisByDevis = ejbDetailleDevis.listOfDetailleDevisByDevis(this.marcheFromEdit.getIdDevis());

            MontantConverter mtc = new MontantConverter();
            Double mtht = 0.0;
            Double mttc = 0.0;
            for (Detailledevis df : listDetaildevisByDevis) {

                Double puc = mtc.StringToDouble(df.getPu());
                Double ctotal = puc * df.getQuantite();
                mtht = mtht + ctotal;
                Double mttvasub = mtht * 0.18;
                mttc = mtht + mttvasub;
            }
            String rht = mtc.DoubleToString(mtht);
            String rttc = mtc.DoubleToString(mttc);
            this.marcheFromEdit.setMontantExecution(rht);
            this.marcheFromEdit.setMontantMarche(rttc);
            this.marcheFromEdit.setNomMarche(this.marcheFromEdit.getIdDevis().getNommarche());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String save() {
        try {
            System.out.println("Nom marché: " + this.marche.getNomMarche());
            System.out.println("montant exe: " + this.marche.getMontantExecution());
            System.out.println("montant mm: " + this.marche.getMontantMarche());
            //ejbmarche.insertMarche(this.marche);
            return "marches";
        } catch (Exception e) {
            return "new_marche";
        }
    }

    public void renderInfoTVA() {
        int mte = Integer.parseInt(this.marche.getMontantExecution());
        Double mtetva = mte * 0.18;
        Double mt = mte + mtetva;
        MontantConverter mtcr = new MontantConverter();
        String rst = mtcr.DoubleToString(mt);
        this.marche.setMontantMarche(rst);
    }

    public void renderInfoTVA2() {
        int mte = Integer.parseInt(this.marcheFromEdit.getMontantExecution());
        Double mtetva = mte * 0.18;
        Double mt = mte + mtetva;
        MontantConverter mtcr = new MontantConverter();
        String rst = mtcr.DoubleToString(mt);
        this.marcheFromEdit.setMontantMarche(rst);
    }

    public void reset() {
        this.marche = new Marche();
        this.marcheFromEdit = new Marche();
    }

    public String resetModif() {
        return "marches";
    }

    public String editer() {
        try {
            MontantConverter montantC = new MontantConverter();
            marcheFromEdit = (Marche) item.getRowData();
            Double mtd = montantC.StringToDouble(marcheFromEdit.getMontantDemarrage());
            Double mtc = montantC.StringToDouble(marcheFromEdit.getMontantcotion());
            Double mtp = montantC.StringToDouble(marcheFromEdit.getPrimcaution());
            this.setMtDemarageFromEdit(mtd);
            this.setMtCautionFromEdit(mtc);
            this.setMtPrimCautionFromEdit(mtp);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "edit_marches";
    }

    public String consulter() {
        try {

            MontantConverter montantC = new MontantConverter();
            marcheFromEdit = (Marche) item.getRowData();

            /*First reliquat*/
            this.setSoldeMarche("");
            Double val1 = montantC.StringToDouble(marcheFromEdit.getMontantMarche());
            Double val3 = montantC.StringToDouble(marcheFromEdit.getMontantDemarrage());
            //Double val4 = val3;
            Double rs1 = val1 - val3;
            String rrs1 = montantC.DoubleToString(rs1);
            System.out.println("resultat calcule: " + rrs1);
            this.setSoldeMarche(rrs1);

            /* List des chantiers */
            System.out.println("ID marche: " + this.marcheFromEdit.getIdMarche());
            this.setListChantier(ejbchantier.listOfChantierByMarche(this.marcheFromEdit.getIdMarche()));

            /*Calcule depenses*/
            MontantConverter mtcct = new MontantConverter();

            /*Depense appro direct*/
            Double ttapD = 0.0;
            this.setTotalApproDirect("");
            for (Chantier ch : this.getListChantier()) {
                List<Livraison> lesLivs = new ArrayList<>();
                lesLivs = ejblivraison.listOfLivraisonByChantier(ch);

                for (Livraison l : lesLivs) {
                    Double prixFC = mtcct.StringToDouble(l.getPu());
                    Double cal1 = l.getQtLivre() * prixFC;
                    ttapD = ttapD + cal1;
                }
            }
            String rttap = mtcct.DoubleToString(ttapD);
            this.setTotalApproDirect(rttap);

            /*Depense appro stock*/
            Double ttapS = 0.0;
            this.setTotalApproStock("");
            for (Chantier ch : this.getListChantier()) {
                List<Sortiestock> lesSortieS = new ArrayList<>();
                lesSortieS = ejbSortieStock.listOfSortieStockByChantier(ch);

                for (Sortiestock s : lesSortieS) {
                    Double prixFC = mtcct.StringToDouble(s.getPu());
                    Double cal2 = s.getQuantite() * prixFC;
                    ttapS = ttapS + cal2;
                }
            }
            String rttapS = mtcct.DoubleToString(ttapS);
            this.setTotalApproStock(rttapS);

            /*Depense Main oeuvre*/
            Double valueAcompteeHT = 0.0;
            Double valueAcompteeTTC = 0.0;
            this.setTotalMoHt("");
            this.setTotalMoTtc("");
            this.setReliquat("");
            for (Chantier ch : this.getListChantier()) {
                List<Prestataire> lesPrestats = new ArrayList<>();
                lesPrestats = ejbPrestataire.listOfPrestataireByChantier(ch);

                for (Prestataire p : lesPrestats) {
                    List<Acompte> allAcomptesForCalcules = new ArrayList<>();

                    allAcomptesForCalcules = ejbAcompte.listOfAcompteByPrestataire(p);

                    for (Acompte a : allAcomptesForCalcules) {
                        System.out.println("Les acomptes : " + a.getChoixtva());
                        if (a.getChoixtva().equals("non")) {
                            Double nb1 = mtcct.StringToDouble(a.getMontantAcompte());
                            valueAcompteeHT = valueAcompteeHT + nb1;
                        }

                        if (a.getChoixtva().equals("oui")) {
                            Double nb2 = mtcct.StringToDouble(a.getMontantAcompte());
                            valueAcompteeTTC = valueAcompteeTTC + nb2;

                        }

                    }
                }
            }
            String RvalueAcompteeHT = mtcct.DoubleToString(valueAcompteeHT);
            String RvalueAcompteeTTC = mtcct.DoubleToString(valueAcompteeTTC);
            this.setTotalMoHt(RvalueAcompteeHT);
            this.setTotalMoTtc(RvalueAcompteeTTC);

            Double calculereliquat1 = valueAcompteeHT + valueAcompteeTTC + ttapD + ttapS;
            Double creliquat = rs1 - calculereliquat1;
            String rrqt = mtcct.DoubleToString(creliquat);
            this.setReliquat(rrqt);

            /*Total des depenses*/
            this.setTotalDepenses("");
            Double totalDep = ttapD + ttapS + valueAcompteeHT + valueAcompteeTTC;
            String RtotalDep = mtcct.DoubleToString(totalDep);
            this.setTotalDepenses(RtotalDep);

            /*Total decomptes*/
            Newfacture nft = ejbNewfacture.getFactureByMarcher(marcheFromEdit);
            //Decomptep decpt = ejbDecompte.getDecompteByFacture(nft);
            List<Decomptep> listDecpts = new ArrayList<>();
            listDecpts = ejbDecompte.listOfDecompteByIdNewFacture(nft);
            List<Detailledecomptep> listDetailDecomptes = new ArrayList<>();
            Double mtdcp = 0.0;
            int totalPourcentage = 0;
            int nbElement = 0;
            this.setTotalDecompte("");
            for (Decomptep decpt : listDecpts) {
                listDetailDecomptes = new ArrayList<>();
                listDetailDecomptes = ejbDetailleDecompte.listOfDetailleDecompetByDecompte(decpt);
                for (Detailledecomptep dcp : listDetailDecomptes) {
                    Double pu = mtcct.StringToDouble(dcp.getPu());
                    Double fmtdcp = dcp.getQuantite() * pu;
                    mtdcp = mtdcp + fmtdcp;
                    totalPourcentage = totalPourcentage + dcp.getExecution();
                    nbElement = nbElement + 1;
                }
            }

            /*Montant TVA*/
            Double mtdcptva = mtdcp * 0.18;
            /*Montant TTC*/
            Double mttcRmtdcp = mtdcp + mtdcptva;
            String Rmtdcp = mtcct.DoubleToString(mttcRmtdcp);
            this.setTotalDecompte(Rmtdcp);

            /*Reliquat*/
 /*Moyenne des pourcentages*/
            int moyennePourcentage = totalPourcentage / nbElement;
            /*Avencement x% HT*/
            Double v1 = (double) moyennePourcentage / (double) 100;
            Double xPourcentHT = mtdcp * v1;
            /*Montant TVA x% HT*/
            Double mttvaxHT = xPourcentHT * 0.18;
            /*Montant x% TTC*/
            Double mxttc = xPourcentHT + mttvaxHT;
            Double mtm = mtcct.StringToDouble(marcheFromEdit.getMontantMarche());
            Double mtd = mtcct.StringToDouble(marcheFromEdit.getMontantDemarrage());
            Double rlq1 = mtdcp + mtd;
            Double rlq2 = mtm - rlq1;
            Double rlq2InterPourcentage = rlq2 - mxttc;
            String rlq3 = mtcct.DoubleToString(rlq2InterPourcentage);
            this.setReliquat(rlq3);

            /*Pourcentage execution*/
            int nbElementPourcent = 0;
            double sommeElementPourcent = 0.0;
            for (Detailledecomptep dcp2 : listDetailDecomptes) {
                sommeElementPourcent = sommeElementPourcent + dcp2.getExecution();
                nbElementPourcent++;
            }
            double moyenneDecompte = sommeElementPourcent / nbElementPourcent;

            List<Management> listDesManagements = ejbManagement.listOfManagementByProjet(marcheFromEdit);
            int nbElementPourcent1 = 0;
            double sommeElementPourcent1 = 0.0;
            for (Management manag : listDesManagements) {
                sommeElementPourcent1 = sommeElementPourcent1 + manag.getTerminer();
                nbElementPourcent1++;
            }
            double moyenneManagement = sommeElementPourcent1 / nbElementPourcent1;

            double sommeFinnal = moyenneDecompte + moyenneManagement;
            double moyenneFinale = sommeFinnal / 2;

            System.out.println("Pourcentage d'execution: " + moyenneFinale);

            String moyenneFinaleConvert = String.valueOf(moyenneFinale);

            if (moyenneFinaleConvert.equals("NaN")) {
                this.setPourcentageExecution("0");
            } else {
                this.setPourcentageExecution(moyenneFinaleConvert);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "consulte_marches";
    }

    public String consulterChantier() {
        try {
//            chantierFromEdit = (Chantier) itemChantiers.getRowData();
//
//            System.out.println("Chantier :" + this.chantierFromEdit.getSiteChantier());
//
//            this.listDetailleChantier = ejbdetaillechantier.listOfRessourceByChantier(chantierFromEdit.getIdChantier());
//            this.setListDetailleChantier(this.listDetailleChantier);
//
//            this.listPersonnelChantier = ejbdetaillechantier.listOfPersonnelByChantier(chantierFromEdit.getIdChantier());
//            this.setListPersonnelChantier(this.listPersonnelChantier);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "consulte_chantier_marche";
    }

    public String update() {
        try {
            MontantConverter mttc = new MontantConverter();
            String mtd = mttc.DoubleToString(this.mtDemarageFromEdit);
            String mtc = mttc.DoubleToString(this.mtCautionFromEdit);
            String mtp = mttc.DoubleToString(this.mtPrimCautionFromEdit);
            this.marcheFromEdit.setMontantDemarrage(mtd);
            this.marcheFromEdit.setMontantcotion(mtc);
            this.marcheFromEdit.setPrimcaution(mtp);

            UtilUtfconvert utfconvert = new UtilUtfconvert();
            this.marcheFromEdit.setNomMarche(utfconvert.convertFromUTF8(marcheFromEdit.getNomMarche()));
            this.marcheFromEdit.setObjetMarche(utfconvert.convertFromUTF8(marcheFromEdit.getObjetMarche()));
            this.marcheFromEdit.setObservation(utfconvert.convertFromUTF8(marcheFromEdit.getObservation()));

            ejbmarche.edit(marcheFromEdit);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "marches";
    }

    public String supprimer() {
        try {
            marcheFromEdit = (Marche) item.getRowData();
            ejbmarche.remove(marcheFromEdit);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "marches";
    }

    public String imprimer() {
        try {

            marcheFromEdit = (Marche) item.getRowData();

            MontantConverter mtc = new MontantConverter();

            Double val1 = mtc.StringToDouble(marcheFromEdit.getMontantMarche());
            Double val3 = mtc.StringToDouble(marcheFromEdit.getMontantDemarrage());
            Double val4 = val3;

            Double rs1 = val1 - val4;

            String rstc = mtc.DoubleToString(rs1);
            this.setSoldeMarche(rstc);

            /* List des chantiers */
            List<Chantier> allChantiers = ejbchantier.listOfChantierByMarche(this.marcheFromEdit.getIdMarche());

            /* List des chantiers */
            System.out.println("ID marche: " + this.marcheFromEdit.getIdMarche());
            this.setListChantier(ejbchantier.listOfChantierByMarche(this.marcheFromEdit.getIdMarche()));

            /*Calcule depenses*/
            MontantConverter mtcct = new MontantConverter();

            /*Depense appro direct*/
            Double ttapD = 0.0;
            for (Chantier ch : this.getListChantier()) {
                List<Livraison> lesLivs = ejblivraison.listOfLivraisonByChantier(ch);

                for (Livraison l : lesLivs) {
                    Double prixFC = mtcct.StringToDouble(l.getPu());
                    Double cal1 = l.getQtLivre() * prixFC;
                    ttapD = ttapD + cal1;
                }
            }
            String rttap = mtcct.DoubleToString(ttapD);
            this.setTotalApproDirect(rttap);

            /*Depense appro stock*/
            Double ttapS = 0.0;
            for (Chantier ch : this.getListChantier()) {
                List<Sortiestock> lesSortieS = ejbSortieStock.listOfSortieStockByChantier(ch);

                for (Sortiestock s : lesSortieS) {
                    Double prixFC = mtcct.StringToDouble(s.getPu());
                    Double cal2 = s.getQuantite() * prixFC;
                    ttapS = ttapS + cal2;
                }
            }
            String rttapS = mtcct.DoubleToString(ttapS);
            this.setTotalApproStock(rttapS);

            /*Depense Main oeuvre*/
            Double valueAcompteeHT = 0.0;
            Double valueAcompteeTTC = 0.0;

            for (Chantier ch : this.getListChantier()) {
                List<Prestataire> lesPrestats = ejbPrestataire.listOfPrestataireByChantier(ch);

                for (Prestataire p : lesPrestats) {
                    List<Acompte> allAcomptesForCalcules = new ArrayList<>();

                    allAcomptesForCalcules = ejbAcompte.listOfAcompteByPrestataire(p);

                    for (Acompte a : allAcomptesForCalcules) {
                        System.out.println("Les acomptes : " + a.getChoixtva());
                        if (a.getChoixtva().equals("non")) {
                            Double nb1 = mtcct.StringToDouble(a.getMontantAcompte());
                            valueAcompteeHT = valueAcompteeHT + nb1;
                        }

                        if (a.getChoixtva().equals("oui")) {
                            Double nb2 = mtcct.StringToDouble(a.getMontantAcompte());
                            valueAcompteeTTC = valueAcompteeTTC + nb2;

                        }

                    }
                }
            }
            String RvalueAcompteeHT = mtcct.DoubleToString(valueAcompteeHT);
            String RvalueAcompteeTTC = mtcct.DoubleToString(valueAcompteeTTC);
            this.setTotalMoHt(RvalueAcompteeHT);
            this.setTotalMoTtc(RvalueAcompteeTTC);

            Double calculereliquat1 = valueAcompteeHT + valueAcompteeTTC + ttapD + ttapS;
            Double creliquat = rs1 - calculereliquat1;
            String rrqt = mtcct.DoubleToString(creliquat);
            this.setReliquat(rrqt);

            /*Total des depenses*/
            Double totalDep = ttapD + ttapS + valueAcompteeHT + valueAcompteeTTC;
            String RtotalDep = mtcct.DoubleToString(totalDep);
            this.setTotalDepenses(RtotalDep);

            /*Total decomptes*/
            Newfacture nft = ejbNewfacture.getFactureByMarcher(marcheFromEdit);
            Decomptep decpt = ejbDecompte.getDecompteByFacture(nft);
            List<Detailledecomptep> listDetailDecomptes = ejbDetailleDecompte.listOfDetailleDecompetByDecompte(decpt);
            Double mtdcp = 0.0;
            for (Detailledecomptep dcp : listDetailDecomptes) {
                Double pu = mtcct.StringToDouble(dcp.getPu());
                Double fmtdcp = dcp.getQuantite() * pu;
                mtdcp = mtdcp + fmtdcp;
            }

            String Rmtdcp = mtcct.DoubleToString(mtdcp);
            this.setTotalDecompte(Rmtdcp);

            /*Reliquat*/
            Double mtd = mtcct.StringToDouble(marcheFromEdit.getMontantDemarrage());
            Double mtm = mtcct.StringToDouble(marcheFromEdit.getMontantMarche());
            Double rlq1 = mtdcp + mtd;
            Double rlq2 = mtm - rlq1;
            String rlq3 = mtcct.DoubleToString(rlq2);
            this.setReliquat(rlq3);

            /*Pourcentage execution*/
            int nbElementPourcent = 0;
            double sommeElementPourcent = 0.0;
            for (Detailledecomptep dcp2 : listDetailDecomptes) {
                sommeElementPourcent = sommeElementPourcent + dcp2.getExecution();
                nbElementPourcent++;
            }
            double moyenneDecompte = sommeElementPourcent / nbElementPourcent;

            List<Management> listDesManagements = ejbManagement.listOfManagementByProjet(marcheFromEdit);
            int nbElementPourcent1 = 0;
            double sommeElementPourcent1 = 0.0;
            for (Management manag : listDesManagements) {
                sommeElementPourcent1 = sommeElementPourcent1 + manag.getTerminer();
                nbElementPourcent1++;
            }
            double moyenneManagement = sommeElementPourcent1 / nbElementPourcent1;

            double sommeFinnal = moyenneDecompte + moyenneManagement;
            double moyenneFinale = sommeFinnal / 2;

            String moyenneFinaleConvert = String.valueOf(moyenneFinale);

            this.setPourcentageExecution(moyenneFinaleConvert);

//            /*Imprimer pour ESTPROD*/
//            ReportMarches rpM = new ReportMarches();
//            rpM.Generer(this.marcheFromEdit, this.getSoldeMarche(), allChantiers, this.totalApproDirect, this.totalApproStock, this.totalDecompte, this.totalDepenses, this.totalMoHt, this.totalMoTtc, this.reliquat, this.pourcentageExecution);
//            
            /*Imprimer pour ESTPROD*/
            ReportAccelModel rpM = new ReportAccelModel();
            rpM.GenererMarcher(this.marcheFromEdit, this.getSoldeMarche(), allChantiers, this.totalApproDirect, this.totalApproStock, this.totalDecompte, this.totalDepenses, this.totalMoHt, this.totalMoTtc, this.reliquat, this.pourcentageExecution);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "consulte_marches";
    }

    public String imprimer2() {
        try {

            marcheFromEdit = ejbmarche.find(this.chantierFromEdit.getIdMarche());

            Double val1 = Double.parseDouble(marcheFromEdit.getMontantMarche());
            Double val2 = Double.parseDouble(marcheFromEdit.getAvanceSurAppro());
            Double val3 = Double.parseDouble(marcheFromEdit.getMontantDemarrage());
            Double val4 = val2 + val3;
            //this.setListMarches(ejboperation.listOfOpEntreeByMarche(this.marcheFromEdit.getIdMarche()));
            List<Operation> listOps = ejboperation.listOfOpEntreeByMarche(this.marcheFromEdit.getIdMarche());

            Double clalculeT = 0.0;

            for (Operation op : listOps) {
                Double sold = Double.parseDouble(op.getDebit());
                clalculeT = clalculeT + sold;
            }

            Double caltotal = clalculeT + val4;

            Double rs1 = val1 - caltotal;

            MontantConverter mtc = new MontantConverter();
            String rstc = mtc.DoubleToString(rs1);
            this.setSoldeMarche(rstc);

            /* List des chantiers */
            List<Chantier> allChantiers = ejbchantier.listOfChantierByMarche(this.marcheFromEdit.getIdMarche());

            /* List des chantiers */
            System.out.println("ID marche: " + this.marcheFromEdit.getIdMarche());
            this.setListChantier(ejbchantier.listOfChantierByMarche(this.marcheFromEdit.getIdMarche()));

            /*Calcule depenses*/
            MontantConverter mtcct = new MontantConverter();

            /*Depense appro direct*/
            Double ttapD = 0.0;
            for (Chantier ch : this.getListChantier()) {
                List<Livraison> lesLivs = ejblivraison.listOfLivraisonByChantier(ch);

                for (Livraison l : lesLivs) {
                    Double prixFC = mtcct.StringToDouble(l.getPu());
                    Double cal1 = l.getQtLivre() * prixFC;
                    ttapD = ttapD + cal1;
                }
            }
            String rttap = mtcct.DoubleToString(ttapD);
            this.setTotalApproDirect(rttap);

            /*Depense appro stock*/
            Double ttapS = 0.0;
            for (Chantier ch : this.getListChantier()) {
                List<Sortiestock> lesSortieS = ejbSortieStock.listOfSortieStockByChantier(ch);

                for (Sortiestock s : lesSortieS) {
                    Double prixFC = mtcct.StringToDouble(s.getPu());
                    Double cal2 = s.getQuantite() * prixFC;
                    ttapS = ttapS + cal2;
                }
            }
            String rttapS = mtcct.DoubleToString(ttapS);
            this.setTotalApproStock(rttapS);

            /*Depense Main oeuvre*/
            Double valueAcompteeHT = 0.0;
            Double valueAcompteeTTC = 0.0;

            for (Chantier ch : this.getListChantier()) {
                List<Prestataire> lesPrestats = ejbPrestataire.listOfPrestataireByChantier(ch);

                for (Prestataire p : lesPrestats) {
                    List<Acompte> allAcomptesForCalcules = new ArrayList<>();

                    allAcomptesForCalcules = ejbAcompte.listOfAcompteByPrestataire(p);

                    for (Acompte a : allAcomptesForCalcules) {
                        System.out.println("Les acomptes : " + a.getChoixtva());
                        if (a.getChoixtva().equals("non")) {
                            Double nb1 = mtcct.StringToDouble(a.getMontantAcompte());
                            valueAcompteeHT = valueAcompteeHT + nb1;
                        }

                        if (a.getChoixtva().equals("oui")) {
                            Double nb2 = mtcct.StringToDouble(a.getMontantAcompte());
                            valueAcompteeTTC = valueAcompteeTTC + nb2;

                        }

                    }
                }
            }
            String RvalueAcompteeHT = mtcct.DoubleToString(valueAcompteeHT);
            String RvalueAcompteeTTC = mtcct.DoubleToString(valueAcompteeTTC);
            this.setTotalMoHt(RvalueAcompteeHT);
            this.setTotalMoTtc(RvalueAcompteeTTC);

            Double calculereliquat1 = valueAcompteeHT + valueAcompteeTTC + ttapD + ttapS;
            Double creliquat = rs1 - calculereliquat1;
            String rrqt = mtcct.DoubleToString(creliquat);
            this.setReliquat(rrqt);

            /*Total des depenses*/
            Double totalDep = ttapD + ttapS + valueAcompteeHT + valueAcompteeTTC;
            String RtotalDep = mtcct.DoubleToString(totalDep);
            this.setTotalDepenses(RtotalDep);

            /*Total decomptes*/
            Newfacture nft = ejbNewfacture.getFactureByMarcher(marcheFromEdit);
            Decomptep decpt = ejbDecompte.getDecompteByFacture(nft);
            List<Detailledecomptep> listDetailDecomptes = ejbDetailleDecompte.listOfDetailleDecompetByDecompte(decpt);
            Double mtdcp = 0.0;
            for (Detailledecomptep dcp : listDetailDecomptes) {
                Double pu = mtcct.StringToDouble(dcp.getPu());
                Double fmtdcp = dcp.getQuantite() * pu;
                mtdcp = mtdcp + fmtdcp;
            }

            String Rmtdcp = mtcct.DoubleToString(mtdcp);
            this.setTotalDecompte(Rmtdcp);

            /*Reliquat*/
            Double mtd = mtcct.StringToDouble(marcheFromEdit.getMontantDemarrage());
            Double mtm = mtcct.StringToDouble(marcheFromEdit.getMontantMarche());
            Double rlq1 = mtdcp + mtd;
            Double rlq2 = mtm - rlq1;
            String rlq3 = mtcct.DoubleToString(rlq2);
            this.setReliquat(rlq3);

            ReportMarches rpM = new ReportMarches();
            rpM.Generer(this.marcheFromEdit, this.getSoldeMarche(), allChantiers, this.totalApproDirect, this.totalApproStock, this.totalDecompte, this.totalDepenses, this.totalMoHt, this.totalMoTtc, this.reliquat, this.pourcentageExecution);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "consulte_marches";
    }

}
