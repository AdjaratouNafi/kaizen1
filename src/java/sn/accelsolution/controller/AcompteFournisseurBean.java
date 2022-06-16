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
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import sn.accelsolution.dao.AcomptefournisseurFacade;
import sn.accelsolution.dao.CommandeFacade;
import sn.accelsolution.dao.ReffournisseurFacade;
import sn.accelsolution.entities.Acompte;
import sn.accelsolution.entities.Acomptefournisseur;
import sn.accelsolution.entities.Reffournisseur;
import sn.accelsolution.util.GenerationCodePdf;
import sn.accelsolution.util.MontantConverter;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DELL
 */
@ManagedBean
@ViewScoped
public class AcompteFournisseurBean implements Serializable {

    /**
     * Creates a new instance of ContratRecrutBean
     */
    @EJB
    ReffournisseurFacade ejbRefFournisseur;
    @EJB
    CommandeFacade ejbCommande;
    @EJB
    AcomptefournisseurFacade ejbAcompteFournisseur;
    
    private Acomptefournisseur acompteFournisseur;
    private Acomptefournisseur acompteFournisseurFromEdit;
    private Reffournisseur refFournisseur;
    
    private List allAcomptes;
    private List listFournisseurs;
    private List allFournisseurs;
    private DataModel item;
    private String ancienMtAcompteFromEdit;
    private Double montantDouble;
    private Double montantFromEditDouble;
    private String choixTva;
    private String controle;
    private List<Acomptefournisseur> listAcomptes;
    private List<Acomptefournisseur> filteredListAcomptes;
    private List<Reffournisseur> listRefFournisseurs;
    private List<Reffournisseur> allRefFournisseurs;
    
    @PostConstruct
    public void init() {
        List<Acomptefournisseur> listAllAcompte = ejbAcompteFournisseur.findAll();
        this.listAcomptes = new ArrayList<>();
        this.listAcomptes.clear();
        //UtilUtfconvert utfconvert = new UtilUtfconvert();
        for (Acomptefournisseur acpt : listAllAcompte) {
            this.listAcomptes.add(acpt);
        }
        
        
    }
    
    public AcompteFournisseurBean() {
        acompteFournisseur = new Acomptefournisseur();
        acompteFournisseurFromEdit = new Acomptefournisseur();
        refFournisseur = new Reffournisseur();
    }
    
    public Reffournisseur getRefFournisseur() {
        return refFournisseur;
    }
    
    public void setRefFournisseur(Reffournisseur refFournisseur) {
        this.refFournisseur = refFournisseur;
    }
    
    public Acomptefournisseur getAcompteFournisseur() {
        return acompteFournisseur;
    }
    
    public void setAcompteFournisseur(Acomptefournisseur acompteFournisseur) {
        this.acompteFournisseur = acompteFournisseur;
    }
    
    public Acomptefournisseur getAcompteFournisseurFromEdit() {
        return acompteFournisseurFromEdit;
    }
    
    public void setAcompteFournisseurFromEdit(Acomptefournisseur acompteFournisseurFromEdit) {
        this.acompteFournisseurFromEdit = acompteFournisseurFromEdit;
    }
    
    public List getAllAcomptes() {
        return allAcomptes;
    }
    
    public void setAllAcomptes(List allAcomptes) {
        this.allAcomptes = allAcomptes;
    }
    
    public List getListFournisseurs() {
        return listFournisseurs;
    }
    
    public void setListFournisseurs(List listFournisseurs) {
        this.listFournisseurs = listFournisseurs;
    }
    
    public List getAllFournisseurs() {
        return allFournisseurs;
    }
    
    public void setAllFournisseurs(List allFournisseurs) {
        this.allFournisseurs = allFournisseurs;
    }
    
    public String getAncienMtAcompteFromEdit() {
        return ancienMtAcompteFromEdit;
    }
    
    public void setAncienMtAcompteFromEdit(String ancienMtAcompteFromEdit) {
        this.ancienMtAcompteFromEdit = ancienMtAcompteFromEdit;
    }
    
    public Double getMontantDouble() {
        return montantDouble;
    }
    
    public void setMontantDouble(Double montantDouble) {
        this.montantDouble = montantDouble;
    }
    
    public Double getMontantFromEditDouble() {
        return montantFromEditDouble;
    }
    
    public void setMontantFromEditDouble(Double montantFromEditDouble) {
        this.montantFromEditDouble = montantFromEditDouble;
    }
    
    public String getChoixTva() {
        return choixTva;
    }
    
    public void setChoixTva(String choixTva) {
        this.choixTva = choixTva;
    }
    
    public String getControle() {
        return controle;
    }
    
    public void setControle(String controle) {
        this.controle = controle;
    }
    
    public List<Acomptefournisseur> getListAcomptes() {
        return listAcomptes;
    }
    
    public void setListAcomptes(List<Acomptefournisseur> listAcomptes) {
        this.listAcomptes = listAcomptes;
    }
    
    public List<Acomptefournisseur> getFilteredListAcomptes() {
        return filteredListAcomptes;
    }
    
    public void setFilteredListAcomptes(List<Acomptefournisseur> filteredListAcomptes) {
        this.filteredListAcomptes = filteredListAcomptes;
    }
    
    public List<Reffournisseur> getListRefFournisseurs() {
        return listRefFournisseurs;
    }
    
    public void setListRefFournisseurs(List<Reffournisseur> listRefFournisseurs) {
        this.listRefFournisseurs = listRefFournisseurs;
    }
    
    public List<Reffournisseur> getAllRefFournisseurs() {
        return allRefFournisseurs;
    }
    
    public void setAllRefFournisseurs(List<Reffournisseur> allRefFournisseurs) {
        this.allRefFournisseurs = allRefFournisseurs;
    }
    
    public void renderInfo() {
        
        try {
            
            this.setListRefFournisseurs(ejbRefFournisseur.listOfRefFournisseurByFssr(this.getAcompteFournisseur().getIdFournisseur()));
            GenerationCodePdf gcpdf = new GenerationCodePdf();
            int maxAcpt = ejbAcompteFournisseur.maxAcompteByFournisseur(this.getAcompteFournisseur().getIdFournisseur());
            Acomptefournisseur mymaxAcptF = ejbAcompteFournisseur.find(maxAcpt);
            String ancienCode = mymaxAcptF.getNumeroacomptef();
            String codeAcompte = gcpdf.genererCodeAcompteFournisseur(ancienCode);
            String numAcp = codeAcompte;
            this.acompteFournisseur.setNumeroacomptef(numAcp);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void renderInfoo() {
        
        try {
//
//            this.acompte.setIdPrestataire(this.acompte.getIdPrestataire());
//            if (this.acompte.getIdPrestataire().getNinea() != null) {
//                this.setControle("oui");
//            }

            this.setRefFournisseur(this.getRefFournisseur());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public DataModel getItem() {
        item = new ListDataModel();
        this.setAllAcomptes(ejbAcompteFournisseur.findAll());
        item.setWrappedData(this.getAllAcomptes());
        return item;
    }
    
    public void setItem(DataModel item) {
        this.item = item;
    }
    
    public void renderInfoChoix() {
        this.acompteFournisseur.setChoixtva(this.getChoixTva());
        System.out.println("Valeur 1: " + this.acompteFournisseur.getChoixtva());
    }
    
    public String save() {
        try {
            
            if (this.acompteFournisseur.getChoixtva().equals("oui")) {
                
                MontantConverter mtc = new MontantConverter();
                Double macp = this.montantDouble;
                Double mttva = macp * 0.18;
                Double allmacp = macp + mttva;
                String mtacpconver = mtc.DoubleToString(allmacp);
                this.acompteFournisseur.setMontantAcompte(mtacpconver);
                Double accord = mtc.StringToDouble(this.refFournisseur.getAccord());
                Double accp = mtc.StringToDouble(this.refFournisseur.getAccompte());
                Double calculeinter = accp + allmacp;
                String rcalculeinter = mtc.DoubleToString(calculeinter);
                Double rewreliquat = accord - calculeinter;
                String rrewreliquat = mtc.DoubleToString(rewreliquat);

                /*String mts = mtc.DoubleToString(mttva);
                 this.acompte.setMontantAcompte(mts);*/
                this.acompteFournisseur.setIdCommande(this.refFournisseur.getIdCommande());
                this.acompteFournisseur.setIdReffournisseur(this.refFournisseur);
                System.out.println("Accompte TVA: " + this.acompteFournisseur.getChoixtva());
                
                ejbAcompteFournisseur.insertAcompte(this.acompteFournisseur);
                
                this.refFournisseur.setAccompte(rcalculeinter);
                this.refFournisseur.setReliquant(rrewreliquat);
                this.refFournisseur.setEcheance(this.acompteFournisseur.getEcheanceAcompte());
                
                if (accord < rewreliquat) {
                    this.refFournisseur.setVoyant("oui");
                } else {
                    this.refFournisseur.setVoyant("non");
                }
                ejbRefFournisseur.edit(this.refFournisseur);
                
            } else {
                
                MontantConverter mtc = new MontantConverter();
                Double macp = this.montantDouble;
                String mtacpconver = mtc.DoubleToString(macp);
                this.acompteFournisseur.setMontantAcompte(mtacpconver);
                Double accord = mtc.StringToDouble(this.refFournisseur.getAccord());
                Double accp = mtc.StringToDouble(this.refFournisseur.getAccompte());
                Double calculeinter = accp + macp;
                String rcalculeinter = mtc.DoubleToString(calculeinter);
                Double rewreliquat = accord - calculeinter;
                String rrewreliquat = mtc.DoubleToString(rewreliquat);

                /*String mts = mtc.DoubleToString(this.montantDouble);
                 this.acompte.setMontantAcompte(mts);*/
                System.out.println("Accompte TVA: " + this.acompteFournisseur.getChoixtva());
                
                this.acompteFournisseur.setIdCommande(this.refFournisseur.getIdCommande());
                this.acompteFournisseur.setIdReffournisseur(this.refFournisseur);
                ejbAcompteFournisseur.insertAcompte(this.acompteFournisseur);
                
                this.refFournisseur.setAccompte(rcalculeinter);
                this.refFournisseur.setReliquant(rrewreliquat);
                this.refFournisseur.setEcheance(this.acompteFournisseur.getEcheanceAcompte());
                
                if (accord < rewreliquat) {
                    this.refFournisseur.setVoyant("oui");
                } else {
                    this.refFournisseur.setVoyant("non");
                }
                
                ejbRefFournisseur.edit(this.refFournisseur);
                
            }
            
            return "acomptes_fournisseurs";
            
        } catch (Exception e) {
            return "new_acompte_fournisseur";
        }
    }
    
    public String reset() {
//        this.acompte = new Acompte();
        return "new_acompte";
    }
    
    public String editer() {
        try {
//            acompteFromEdit = (Acompte) item.getRowData();
//            MontantConverter mtc = new MontantConverter();
//            Double mtcc = mtc.StringToDouble(acompteFromEdit.getMontantAcompte());
//            this.setMontantFromEditDouble(mtcc);
//            this.setAncienMtAcompteFromEdit(acompteFromEdit.getMontantAcompte());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "edit_acompte";
    }
    
    public String update() {
        try {

//            if (this.acompteFromEdit.getChoixtva().equals("oui")) {
//                MontantConverter mtc = new MontantConverter();
//                Double macp = this.montantFromEditDouble;
//                Double mttva = macp * 0.18;
//                Double allmacp = macp + mttva;
//                String mtacpconver = mtc.DoubleToString(allmacp);
//                this.acompteFromEdit.setMontantAcompte(mtacpconver);
//                Double accord = mtc.StringToDouble(this.acompteFromEdit.getIdPrestataire().getAccord());
//                Double accp = mtc.StringToDouble(this.acompteFromEdit.getIdPrestataire().getAccompte());
//                Double ancienmt = mtc.StringToDouble(this.getAncienMtAcompteFromEdit());
//                Double initaccp = accp - ancienmt;
//                Double calculeinter = initaccp + allmacp;
//                String rcalculeinter = mtc.DoubleToString(calculeinter);
//                Double rewreliquat = accord - calculeinter;
//                String rrewreliquat = mtc.DoubleToString(rewreliquat);
//                ejbAcompte.edit(this.acompteFromEdit);
//
//                this.acompteFromEdit.getIdPrestataire().setAccompte(rcalculeinter);
//                this.acompteFromEdit.getIdPrestataire().setReliquant(rrewreliquat);
//                this.acompteFromEdit.getIdPrestataire().setEchenance(this.acompteFromEdit.getEcheanceAcompte());
//                if (accord < rewreliquat) {
//                    this.acompteFromEdit.getIdPrestataire().setVoyant("oui");
//                } else {
//                    this.acompteFromEdit.getIdPrestataire().setVoyant("non");
//                }
//                ejbPrestataire.edit(this.acompteFromEdit.getIdPrestataire());
//            } else {
//                MontantConverter mtc = new MontantConverter();
//                Double macp = this.montantFromEditDouble;
//                String mtacpconver = mtc.DoubleToString(macp);
//                this.acompteFromEdit.setMontantAcompte(mtacpconver);
//                Double accord = mtc.StringToDouble(this.acompteFromEdit.getIdPrestataire().getAccord());
//                Double accp = mtc.StringToDouble(this.acompteFromEdit.getIdPrestataire().getAccompte());
//                Double ancienmt = mtc.StringToDouble(this.getAncienMtAcompteFromEdit());
//                Double initaccp = accp - ancienmt;
//                Double calculeinter = initaccp + macp;
//                String rcalculeinter = mtc.DoubleToString(calculeinter);
//                Double rewreliquat = accord - calculeinter;
//                String rrewreliquat = mtc.DoubleToString(rewreliquat);
//                ejbAcompte.edit(this.acompteFromEdit);
//
//                this.acompteFromEdit.getIdPrestataire().setAccompte(rcalculeinter);
//                this.acompteFromEdit.getIdPrestataire().setReliquant(rrewreliquat);
//                this.acompteFromEdit.getIdPrestataire().setEchenance(this.acompteFromEdit.getEcheanceAcompte());
//                if (accord < rewreliquat) {
//                    this.acompteFromEdit.getIdPrestataire().setVoyant("oui");
//                } else {
//                    this.acompteFromEdit.getIdPrestataire().setVoyant("non");
//                }
//                ejbPrestataire.edit(this.acompteFromEdit.getIdPrestataire());
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return "acomptes";
    }
    
    public String supprimer() {
        try {
//            acompteFromEdit = (Acompte) item.getRowData();
//            ejbAcompte.remove(acompteFromEdit);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "acomptes";
    }
    
}
