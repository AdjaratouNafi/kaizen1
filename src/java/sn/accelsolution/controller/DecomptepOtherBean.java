/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import sn.accelsolution.dao.DecomptepFacade;
import sn.accelsolution.dao.DetailledecomptepFacade;
import sn.accelsolution.dao.DetaillenewfactureFacade;
import sn.accelsolution.dao.MarcheFacade;
import sn.accelsolution.dao.NewfactureFacade;
import sn.accelsolution.dao.NotificationFacade;
import sn.accelsolution.dao.UtilisateurFacade;
import sn.accelsolution.entities.Decomptep;
import sn.accelsolution.entities.Detailledecomptep;
import sn.accelsolution.entities.Detaillenewfacture;
import sn.accelsolution.entities.Marche;
import sn.accelsolution.entities.Newfacture;
import sn.accelsolution.entities.Notification;
import sn.accelsolution.util.FactureUtil;
import sn.accelsolution.util.MontantConverter;
import sn.accelsolution.util.ReportAccelModel;
import sn.accelsolution.util.ReportFacture;
import sn.accelsolution.util.ReportModel;
import sn.accelsolution.util.UtilListItemDataDecompte;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DELL
 */
@ManagedBean
@RequestScoped
public class DecomptepOtherBean implements Serializable {

    /**
     * Creates a new instance of ContratRecrutBean
     */
    @EJB
    UtilisateurFacade ejbUser;
    @EJB
    DecomptepFacade ejbDecomptep;
    @EJB
    DetailledecomptepFacade ejbDetailleDecomptep;
    @EJB
    MarcheFacade ejbMarche;
    @EJB
    NotificationFacade ejbNotification;
    
    private Decomptep decomptepFromEdit;
    private Detailledecomptep detailleDecomptepFromEdit;
    private List<Detailledecomptep> allDetailleDecompteps;
    private List<FactureUtil> allFactureUtils;
    private String totalHT;
    private String montantTVA;
    private String totalTTC;
    private String valeurXPourcent;
    private String avancementHt;
    private String avancementTVA;
    private String avancementTTC;
    private String avanceRecu;
    private String reliquantDemande;
    private String emtyvalues;
    
    @PostConstruct
    public void init() {
        /*FactureUtil fu = new FactureUtil();
         fu.setDesignation("");
         fu.setPu("");
         fu.setQuantite(0);
         listDevis.add(fu);*/
    }
    
    public DecomptepOtherBean() {
        allDetailleDecompteps = new ArrayList<>();
        decomptepFromEdit = new Decomptep();
        detailleDecomptepFromEdit = new Detailledecomptep();
        allFactureUtils = new ArrayList<>();
        this.emtyvalues = "-";
    }
    
    public String getEmtyvalues() {
        return emtyvalues;
    }
    
    public void setEmtyvalues(String emtyvalues) {
        this.emtyvalues = emtyvalues;
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
    
    public List<Detailledecomptep> getAllDetailleDecompteps() {
        return allDetailleDecompteps;
    }
    
    public void setAllDetailleDecompteps(List<Detailledecomptep> allDetailleDecompteps) {
        this.allDetailleDecompteps = allDetailleDecompteps;
    }
    
    public String getAvancementHt() {
        return avancementHt;
    }
    
    public void setAvancementHt(String avancementHt) {
        this.avancementHt = avancementHt;
    }
    
    public String getAvancementTVA() {
        return avancementTVA;
    }
    
    public void setAvancementTVA(String avancementTVA) {
        this.avancementTVA = avancementTVA;
    }
    
    public String getAvancementTTC() {
        return avancementTTC;
    }
    
    public void setAvancementTTC(String avancementTTC) {
        this.avancementTTC = avancementTTC;
    }
    
    public String getAvanceRecu() {
        return avanceRecu;
    }
    
    public void setAvanceRecu(String avanceRecu) {
        this.avanceRecu = avanceRecu;
    }
    
    public String getReliquantDemande() {
        return reliquantDemande;
    }
    
    public void setReliquantDemande(String reliquantDemande) {
        this.reliquantDemande = reliquantDemande;
    }
    
    public List<FactureUtil> getAllFactureUtils() {
        return allFactureUtils;
    }
    
    public void setAllFactureUtils(List<FactureUtil> allFactureUtils) {
        this.allFactureUtils = allFactureUtils;
    }
    
    public String getTotalHT() {
        return totalHT;
    }
    
    public void setTotalHT(String totalHT) {
        this.totalHT = totalHT;
    }
    
    public String getMontantTVA() {
        return montantTVA;
    }
    
    public void setMontantTVA(String montantTVA) {
        this.montantTVA = montantTVA;
    }
    
    public String getTotalTTC() {
        return totalTTC;
    }
    
    public void setTotalTTC(String totalTTC) {
        this.totalTTC = totalTTC;
    }
    
    public String getValeurXPourcent() {
        return valeurXPourcent;
    }
    
    public void setValeurXPourcent(String valeurXPourcent) {
        this.valeurXPourcent = valeurXPourcent;
    }
    
    public String detail(Decomptep d) {
        try {
            
            this.setDecomptepFromEdit(d);
            /* List des detailles devis */
            this.setAllDetailleDecompteps(ejbDetailleDecomptep.listOfDetailleDecompetByDecompte(this.getDecomptepFromEdit()));
            MontantConverter mtc = new MontantConverter();
            Double mtht = 0.0;
            int totalPourcentage = 0;
            int nbElement = 0;
            
            for (Detailledecomptep df : this.allDetailleDecompteps) {
                FactureUtil fut = new FactureUtil();
                fut.setDesignation(df.getDesignation());
                fut.setTypeitem(df.getTypeitem());
                fut.setQuantite(df.getQuantite());
                fut.setPu(df.getPu());
                fut.setRef(df.getReference());
                fut.setUnite(df.getUnite());
                
                if (df.getTypeitem().equals("Item")) {
                    Double puc = mtc.StringToDouble(df.getPu());
                    Double ctotal = puc * df.getQuantite();
                    mtht = mtht + ctotal;
                    String rctotal = mtc.DoubleToString(ctotal);
                    fut.setPrixtotal(rctotal);
                } else {
                    if (!df.getPu().equals("0")) {
                        Double puc = mtc.StringToDouble(df.getPu());
                        Double ctotal = puc * df.getQuantite();
                        String rctotal = mtc.DoubleToString(ctotal);
                        fut.setPrixtotal(rctotal);
                    } else {
                        fut.setPrixtotal("0");
                    }
                    
                }
                
                fut.setExecution(df.getExecution());
                allFactureUtils.add(fut);
                totalPourcentage = totalPourcentage + fut.getExecution();
                nbElement = nbElement + 1;
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
            /*Moyenne des pourcentages*/
            int moyennePourcentage = totalPourcentage / nbElement;
            /*Avencement x% HT*/
            Double v1 = (double) moyennePourcentage / (double) 100;
            Double xPourcentHT = mtht * v1;
            String ravancementHt = mtc.DoubleToString(xPourcentHT);
            this.setAvancementHt(ravancementHt);
            /*Montant TVA x% HT*/
            Double mttvaxHT = xPourcentHT * 0.18;
            String rmttvaxHT = mtc.DoubleToString(mttvaxHT);
            this.setAvancementTVA(rmttvaxHT);
            /*Montant x% TTC*/
            Double mxttc = xPourcentHT + mttvaxHT;
            String rmxttc = mtc.DoubleToString(mxttc);
            this.setAvancementTTC(rmxttc);
            /*Valeur du pourcentage*/
            this.setValeurXPourcent(String.valueOf(moyennePourcentage));

            /*Calcule avance perçu*/
            Marche marchefromcalcule = ejbMarche.marcheByDevis(d.getIdNewfacture().getIdDevis());
            String montantdemarrage = marchefromcalcule.getMontantDemarrage();
            System.out.println("Mt demarrage: " + montantdemarrage);
            String montantprojet = marchefromcalcule.getMontantMarche();
            Double mtdemar = mtc.StringToDouble(montantdemarrage);
            Double montantprojetConvert = mtc.StringToDouble(montantprojet);
            Double rmontantavanceprojet = mtdemar;
            System.out.println("Avance + demarrage: " + rmontantavanceprojet);
            Double rreliquat = montantprojetConvert - rmontantavanceprojet;
            
            List<Decomptep> listDecompteByIdFacture = ejbDecomptep.listOfDecompetByfacture(d);
            int nbElOfList = listDecompteByIdFacture.size();
            System.out.println("Nb element de la liste: " + nbElOfList);
            
            if (nbElOfList == 1) {
                String rmontantavanceprojetConvert = mtc.DoubleToString(rmontantavanceprojet);
                String rreliquatConvert = mtc.DoubleToString(rreliquat);
                this.setAvanceRecu(rmontantavanceprojetConvert);
                this.setReliquantDemande(rreliquatConvert);
            }
            
            Double allTTC1 = 0.0;
            
            if (nbElOfList > 1) {
                for (Decomptep dcp : listDecompteByIdFacture) {
                    Double totelMHTX = 0.0;
                    Double montantTVAX = 0.0;
                    Double newMTTX = 0.0;
                    if (!dcp.getDateDecomptep().equals(d.getDateDecomptep())) {
                        List<Detailledecomptep> listDetailleDecompteBDecompte = ejbDetailleDecomptep.listOfDetailleDecompetByDecompte(dcp);
                        for (Detailledecomptep ddcp : listDetailleDecompteBDecompte) {
                            Double prixx = mtc.StringToDouble(ddcp.getPu());
                            Double ctotalX = prixx * ddcp.getQuantite();
                            totelMHTX = totelMHTX + ctotalX;
                        }
                    }
                    
                    montantTVAX = totelMHTX * 0.18;
                    newMTTX = totelMHTX + montantTVAX;
                    allTTC1 = allTTC1 + newMTTX;
                }
                
                Double searchmontantfinale = allTTC1 + rmontantavanceprojet;
                Double rreliquatd = montantprojetConvert - searchmontantfinale;
                String rrreliquatd = mtc.DoubleToString(rreliquatd);
                this.setReliquantDemande(rrreliquatd);
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "consulte_decomptep";
    }
    
    public String imprimer(Decomptep d) throws IOException {
        
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        
        try {
            
            this.setDecomptepFromEdit(d);
            /* List des detailles devis */
            this.setAllDetailleDecompteps(ejbDetailleDecomptep.listOfDetailleDecompetByDecompte(this.getDecomptepFromEdit()));
            MontantConverter mtc = new MontantConverter();
            Double mtht = 0.0;
            int totalPourcentage = 0;
            int nbElement = 0;
            
            List<UtilListItemDataDecompte> listItems = new ArrayList<UtilListItemDataDecompte>();
            
            for (Detailledecomptep df : this.allDetailleDecompteps) {
                FactureUtil fut = new FactureUtil();
                UtilListItemDataDecompte itemData = new UtilListItemDataDecompte();
                
                fut.setDesignation(df.getDesignation());
                fut.setQuantite(df.getQuantite());
                fut.setPu(df.getPu());
                fut.setRef(df.getReference());
                fut.setUnite(df.getUnite());
                
                String rctotal = "";
                if (df.getTypeitem().equals("Item")) {
                    Double puc = mtc.StringToDouble(df.getPu());
                    Double ctotal = puc * df.getQuantite();
                    mtht = mtht + ctotal;
                    rctotal = mtc.DoubleToString(ctotal);
                    fut.setPrixtotal(rctotal);
                } else {
                    if (!df.getPu().equals("0")) {
                        System.out.println("1 Je suis là pour le total!!!");
                        Double puc = mtc.StringToDouble(df.getPu());
                        Double ctotal = puc * df.getQuantite();
                        rctotal = mtc.DoubleToString(ctotal);
                        fut.setPrixtotal(rctotal);
                    } else {
                        fut.setPrixtotal("0");
                    }
                }
                
                fut.setExecution(df.getExecution());
                allFactureUtils.add(fut);
                totalPourcentage = totalPourcentage + fut.getExecution();
                nbElement = nbElement + 1;
                
                itemData.setDesignation(df.getDesignation());
                itemData.setRef(df.getReference());
                itemData.setUnite(df.getUnite());
                //String qte = String.valueOf(df.getQuantite());
                // itemData.setQuantite(qte);
                //  itemData.setPu(df.getPu());
                //itemData.setPrixtotal(rctotal);

                itemData.setTypedata(df.getTypeitem());
                
                String qte;
                if (df.getQuantite() == 0) {
                    qte = "-";
                } else {
                    qte = String.valueOf(df.getQuantite());
                }
                itemData.setQuantite(qte);
                
                if (df.getPu().equals("0")) {
                    itemData.setPu("-");
                } else {
                    itemData.setPu(df.getPu());
                }
                
                System.out.println("Valeur de total en cas erreur: " + rctotal);
                
                if (rctotal.equals("0") || rctotal.equals("") || rctotal.isEmpty()) {
                    System.out.println("2 Je suis là pour le total!!!");
                    itemData.setPrixtotal("-");
                } else {
                    itemData.setPrixtotal(rctotal);
                }
                
                itemData.setExecution(df.getExecution());
                listItems.add(itemData);
                
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
            /*Moyenne des pourcentages*/
            int moyennePourcentage = totalPourcentage / nbElement;
            /*Avencement x% HT*/
            Double v1 = (double) moyennePourcentage / (double) 100;
            Double xPourcentHT = mtht * v1;
            String ravancementHt = mtc.DoubleToString(xPourcentHT);
            this.setAvancementHt(ravancementHt);
            /*Montant TVA x% HT*/
            Double mttvaxHT = xPourcentHT * 0.18;
            String rmttvaxHT = mtc.DoubleToString(mttvaxHT);
            this.setAvancementTVA(rmttvaxHT);
            /*Montant x% TTC*/
            Double mxttc = xPourcentHT + mttvaxHT;
            String rmxttc = mtc.DoubleToString(mxttc);
            this.setAvancementTTC(rmxttc);
            /*Valeur du pourcentage*/
            this.setValeurXPourcent(String.valueOf(moyennePourcentage));

            /*Calcule avance perçu*/
            Marche marchefromcalcule = ejbMarche.marcheByDevis(d.getIdNewfacture().getIdDevis());
            String montantdemarrage = marchefromcalcule.getMontantDemarrage();
            String montantprojet = marchefromcalcule.getMontantMarche();
            Double mtdemar = mtc.StringToDouble(montantdemarrage);
            Double montantprojetConvert = mtc.StringToDouble(montantprojet);
            Double rmontantavanceprojet = mtdemar;
            Double rreliquat = montantprojetConvert - rmontantavanceprojet;
            
            List<Decomptep> listDecompteByIdFacture = ejbDecomptep.listOfDecompetByfacture(d);
            int nbElOfList = listDecompteByIdFacture.size();
            
            if (nbElOfList == 1) {
                String rmontantavanceprojetConvert = mtc.DoubleToString(rmontantavanceprojet);
                String rreliquatConvert = mtc.DoubleToString(rreliquat);
                this.setAvanceRecu(rmontantavanceprojetConvert);
                this.setReliquantDemande(rreliquatConvert);
            }
            
            Double allTTC1 = 0.0;
            
            if (nbElOfList > 1) {
                for (Decomptep dcp : listDecompteByIdFacture) {
                    Double totelMHTX = 0.0;
                    Double montantTVAX = 0.0;
                    Double newMTTX = 0.0;
                    if (!dcp.getDateDecomptep().equals(d.getDateDecomptep())) {
                        List<Detailledecomptep> listDetailleDecompteBDecompte = ejbDetailleDecomptep.listOfDetailleDecompetByDecompte(dcp);
                        for (Detailledecomptep ddcp : listDetailleDecompteBDecompte) {
                            Double prixx = mtc.StringToDouble(ddcp.getPu());
                            Double ctotalX = prixx * ddcp.getQuantite();
                            totelMHTX = totelMHTX + ctotalX;
                        }
                    }
                    
                    montantTVAX = totelMHTX * 0.18;
                    newMTTX = totelMHTX + montantTVAX;
                    allTTC1 = allTTC1 + newMTTX;
                }
                
                Double searchmontantfinale = allTTC1 + rmontantavanceprojet;
                Double rreliquatd = montantprojetConvert - searchmontantfinale;
                String rrreliquatd = mtc.DoubleToString(rreliquatd);
                this.setReliquantDemande(rrreliquatd);
                
            }

//            //Imprimer pour ESTPROD
//            ReportFacture rfacture = new ReportFacture();
//            rfacture.GenererCMBBB(this.allFactureUtils, d, totalHT, montantTVA, totalTTC, valeurXPourcent, avancementHt, avancementTVA, avancementTTC, avanceRecu, reliquantDemande);
            //Imprimer pour ACCEL
//            ReportAccelModel rfacture = new ReportAccelModel();
//            rfacture.GenererDecompte(this.allFactureUtils, d, totalHT, montantTVA, totalTTC, valeurXPourcent, avancementHt, avancementTVA, avancementTTC, avanceRecu, reliquantDemande);

            /*Methode2*/
//            ServletOutputStream servletOutputStream = response.getOutputStream();
//            String sourceFileName = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/rapport/decompte.jasper");
//            File reportFile = new File(sourceFileName);
//            byte[] bytes = null;
//
//            JRBeanCollectionDataSource itemJRBean = new JRBeanCollectionDataSource(listItems);
//            Map<String, Object> parameters = new HashMap<String, Object>();
//
//            parameters.put("logoReport", FacesContext.getCurrentInstance().getExternalContext().getRealPath("/rapport"));
//            //String nomCltUtil = utfconvert.convertFromUTF8(d.getIdNewfacture().getIdDevis().getIdClient().getNomClient());
//            parameters.put("nomClient", d.getIdNewfacture().getIdDevis().getIdClient().getNomClient());
//            //String adrCltUtil = utfconvert.convertFromUTF8(d.getIdNewfacture().getIdDevis().getIdClient().getAdresse());
//            parameters.put("adresseClient", d.getIdNewfacture().getIdDevis().getIdClient().getAdresse());
//            parameters.put("telClient", d.getIdNewfacture().getIdDevis().getIdClient().getTelephoneClient());
//            parameters.put("mailClient", d.getIdNewfacture().getIdDevis().getIdClient().getMailClient());
//            parameters.put("dateDevis", d.getDateDecomptep());
//            parameters.put("numeroDevis", d.getNumeroDecomptep());
//            // String nomMarcheUtil = utfconvert.convertFromUTF8(d.getIdNewfacture().getIdDevis().getNommarche());
//            parameters.put("reference", d.getIdNewfacture().getIdDevis().getNommarche());
//            parameters.put("devise", d.getIdNewfacture().getIdMarche().getIdDevise().getSymbole());
//            parameters.put("montantht", totalHT);
//            parameters.put("montanttva", montantTVA);
//            parameters.put("montantttc", totalTTC);
//            parameters.put("prtg", valeurXPourcent);
//            parameters.put("avancementHt", avancementHt);
//            parameters.put("avancementTVA", avancementTVA);
//            parameters.put("avancementTTC", avancementTTC);
//            parameters.put("avanceRecu", avanceRecu);
//            parameters.put("reliquantDemande", reliquantDemande);
            String mtlUtil = "";
            if (d.getMontantlettre() != null) {
                mtlUtil = d.getMontantlettre();
            }
//            parameters.put("montantlettre", mtlUtil);
//            parameters.put("ItemDataSource", itemJRBean);
//
//            bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameters, new JREmptyDataSource());
//
//            response.setContentType("application/pdf");
//            response.setContentLength(bytes.length);
//
//            servletOutputStream.write(bytes, 0, bytes.length);
//            servletOutputStream.flush();
//            servletOutputStream.close();
            /* End Methode2*/
            ReportModel reportModel = new ReportModel();
            reportModel.GenererModelDecompte(d, listItems, totalHT, montantTVA, totalTTC, mtlUtil, valeurXPourcent, avancementHt, avancementTVA, avancementTTC, avanceRecu, reliquantDemande);
        } catch (Exception e) {
            // display stack trace in the browser
//            StringWriter stringWriter = new StringWriter();
//            PrintWriter printWriter = new PrintWriter(stringWriter);
//            e.printStackTrace(printWriter);
//            response.setContentType("text/plain");
//            response.getOutputStream().print(stringWriter.toString());
        }
        return "decomptep";
    }
    
    public String traiterDecompte(Decomptep d) {
        Notification notification = ejbNotification.getNotificationByDecompte(d);
        HttpSession session1 = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        session1.setAttribute("myNotificationDecompte", notification);
        return "traitement_notificationDecompte";
    }
    
}
