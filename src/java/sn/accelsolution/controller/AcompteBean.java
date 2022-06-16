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
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;
import sn.accelsolution.dao.AcompteFacade;
import sn.accelsolution.dao.ChantierFacade;
import sn.accelsolution.dao.PrestataireFacade;
import sn.accelsolution.entities.Acompte;
import sn.accelsolution.entities.Actionmenu;
import sn.accelsolution.entities.Prestataireprim;
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
public class AcompteBean implements Serializable {

    /**
     * Creates a new instance of ContratRecrutBean
     */
    @EJB
    PrestataireFacade ejbPrestataire;
    @EJB
    ChantierFacade ejbChantier;
    @EJB
    AcompteFacade ejbAcompte;

    private Acompte acompte;
    private Acompte acompteFromEdit;

    private List allAcomptes;
    private List listPrestataires;
    private List allPrestataires;
    private DataModel item;
    private String ancienMtAcompteFromEdit;
    private Double montantDouble;
    private Double montantFromEditDouble;
    private String choixTva;
    private String controle;
    private Prestataireprim pretatairePrim;
    private List<Acompte> listAcomptes;
    private List<Acompte> filteredListAcomptes;
    private List<Actionmenu> myllActionmenus;
    private String creerAcompte;
    private String modifierAcompte;
    private String supprimerAcompte;
    private String consulterAcompte;
    private String imprimetrAcompte;

    @PostConstruct
    public void init() {

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UtilControleMenu utilControleMenu = new UtilControleMenu();

        myllActionmenus = (List<Actionmenu>) session.getAttribute("allActionmenusFromTraitementAcomptes");

        this.setCreerAcompte(utilControleMenu.creerAcompte(myllActionmenus));
        this.setModifierAcompte(utilControleMenu.modifierAcompte(myllActionmenus));
        this.setSupprimerAcompte(utilControleMenu.supprimerAcompte(myllActionmenus));
        this.setConsulterAcompte(utilControleMenu.consulterAcompte(myllActionmenus));

        this.setControle("non");
        List<Acompte> listAllAcompte = ejbAcompte.findAll();
        //UtilUtfconvert utfconvert = new UtilUtfconvert();
        this.listAcomptes = new ArrayList<>();
        this.listAcomptes.clear();
        for (Acompte acompteTA : listAllAcompte) {
            this.listAcomptes.add(acompteTA);
        }

        GenerationCodePdf gcpdf = new GenerationCodePdf();
        int maxCpt = ejbAcompte.maxAcompte();
        Acompte mymaxCpt = ejbAcompte.find(maxCpt);
        String ancienCode = mymaxCpt.getNumeroacompte();
        String codeCpt = gcpdf.genererCodeCommande(ancienCode);
        String numCpt = codeCpt;
        this.acompte.setNumeroacompte(numCpt);
    }

    public AcompteBean() {

        acompte = new Acompte();
        acompteFromEdit = new Acompte();
        pretatairePrim = new Prestataireprim();
    }

    public List<Actionmenu> getMyllActionmenus() {
        return myllActionmenus;
    }

    public void setMyllActionmenus(List<Actionmenu> myllActionmenus) {
        this.myllActionmenus = myllActionmenus;
    }

    public String getCreerAcompte() {
        return creerAcompte;
    }

    public void setCreerAcompte(String creerAcompte) {
        this.creerAcompte = creerAcompte;
    }

    public String getModifierAcompte() {
        return modifierAcompte;
    }

    public void setModifierAcompte(String modifierAcompte) {
        this.modifierAcompte = modifierAcompte;
    }

    public String getSupprimerAcompte() {
        return supprimerAcompte;
    }

    public void setSupprimerAcompte(String supprimerAcompte) {
        this.supprimerAcompte = supprimerAcompte;
    }

    public String getConsulterAcompte() {
        return consulterAcompte;
    }

    public void setConsulterAcompte(String consulterAcompte) {
        this.consulterAcompte = consulterAcompte;
    }

    public String getImprimetrAcompte() {
        return imprimetrAcompte;
    }

    public void setImprimetrAcompte(String imprimetrAcompte) {
        this.imprimetrAcompte = imprimetrAcompte;
    }

    public List<Acompte> getListAcomptes() {
        return listAcomptes;
    }

    public void setListAcomptes(List<Acompte> listAcomptes) {
        this.listAcomptes = listAcomptes;
    }

    public List<Acompte> getFilteredListAcomptes() {
        return filteredListAcomptes;
    }

    public void setFilteredListAcomptes(List<Acompte> filteredListAcomptes) {
        this.filteredListAcomptes = filteredListAcomptes;
    }

    public List getAllPrestataires() {
        return allPrestataires;
    }

    public void setAllPrestataires(List allPrestataires) {
        this.allPrestataires = allPrestataires;
    }

    public Prestataireprim getPretatairePrim() {
        return pretatairePrim;
    }

    public void setPretatairePrim(Prestataireprim pretatairePrim) {
        this.pretatairePrim = pretatairePrim;
    }

    public String getControle() {
        return controle;
    }

    public void setControle(String controle) {
        this.controle = controle;
    }

    public String getChoixTva() {
        return choixTva;
    }

    public void setChoixTva(String choixTva) {
        this.choixTva = choixTva;
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

    public Acompte getAcompte() {
        return acompte;
    }

    public void setAcompte(Acompte acompte) {
        this.acompte = acompte;
    }

    public Acompte getAcompteFromEdit() {
        return acompteFromEdit;
    }

    public void setAcompteFromEdit(Acompte acompteFromEdit) {
        this.acompteFromEdit = acompteFromEdit;
    }

    public List getAllAcomptes() {
        allAcomptes = ejbAcompte.findAll();
        return allAcomptes;
    }

    public void setAllAcomptes(List allAcomptes) {
        this.allAcomptes = allAcomptes;
    }

    public List getListPrestataires() {
        //listPrestataires = ejbPrestataire.findAll();
        return listPrestataires;
    }

    public void setListPrestataires(List listPrestataires) {
        this.listPrestataires = listPrestataires;
    }

    public String getAncienMtAcompteFromEdit() {
        return ancienMtAcompteFromEdit;
    }

    public void setAncienMtAcompteFromEdit(String ancienMtAcompteFromEdit) {
        this.ancienMtAcompteFromEdit = ancienMtAcompteFromEdit;
    }

    public void renderInfo() {
        try {

            //this.setListPrestataires(ejbPrestataire.listOfPrestataireByPrestatairePrim(this.getPretatairePrim()));
            System.out.println("Prestataire prime: " + this.getPretatairePrim().getIdprestatairePrim());
            this.allPrestataires = ejbPrestataire.listOfPrestataireByPrestatairePrim(this.getPretatairePrim());

//            for(Prestataire p: this.allPrestataires){
//                System.out.println("Chantier: "+p.getIdChantier().getSiteChantier());
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void renderInfoo() {

        try {

            this.acompte.setIdPrestataire(this.acompte.getIdPrestataire());
            if (this.acompte.getIdPrestataire().getNinea() != null) {
                this.setControle("oui");
            }

            GenerationCodePdf gcpdf = new GenerationCodePdf();
            int maxAcpt = ejbAcompte.maxAcompteByPrestataire(this.acompte.getIdPrestataire());
            Acompte mymaxAcpt = ejbAcompte.find(maxAcpt);
            String ancienCode = mymaxAcpt.getNumeroacompte();
            String codeAcompte = gcpdf.genererCodeAcompte(ancienCode);
            String numAcp = codeAcompte;
            this.acompte.setNumeroacompte(numAcp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DataModel getItem() {
        item = new ListDataModel();
        this.setAllAcomptes(ejbAcompte.findAll());
        item.setWrappedData(this.getAllAcomptes());
        return item;
    }

    public void setItem(DataModel item) {
        this.item = item;
    }

    public void renderInfoChoix() {
        this.acompte.setChoixtva(this.getChoixTva());
        System.out.println("Valeur 1: " + this.acompte.getChoixtva());
    }

    public String save() {
        try {

            if (this.acompte.getChoixtva().equals("oui")) {

                MontantConverter mtc = new MontantConverter();
                Double macp = this.montantDouble;
                Double mttva = macp * 0.18;
                Double allmacp = macp + mttva;
                String mtacpconver = mtc.DoubleToString(allmacp);
                this.acompte.setMontantAcompte(mtacpconver);
                Double accord = mtc.StringToDouble(this.acompte.getIdPrestataire().getAccord());
                Double accp = mtc.StringToDouble(this.acompte.getIdPrestataire().getAccompte());
                Double calculeinter = accp + allmacp;
                String rcalculeinter = mtc.DoubleToString(calculeinter);
                Double rewreliquat = accord - calculeinter;
                String rrewreliquat = mtc.DoubleToString(rewreliquat);

                /*String mts = mtc.DoubleToString(mttva);
                 this.acompte.setMontantAcompte(mts);*/
                System.out.println("Accompte TVA: " + this.acompte.getChoixtva());

                ejbAcompte.insertAcompte(this.acompte);

                this.acompte.getIdPrestataire().setAccompte(rcalculeinter);
                this.acompte.getIdPrestataire().setReliquant(rrewreliquat);
                this.acompte.getIdPrestataire().setEchenance(this.acompte.getEcheanceAcompte());
                if (accord < rewreliquat) {
                    this.acompte.getIdPrestataire().setVoyant("oui");
                } else {
                    this.acompte.getIdPrestataire().setVoyant("non");
                }
                ejbPrestataire.edit(this.acompte.getIdPrestataire());

            } else {

                MontantConverter mtc = new MontantConverter();
                Double macp = this.montantDouble;
                String mtacpconver = mtc.DoubleToString(macp);
                this.acompte.setMontantAcompte(mtacpconver);
                Double accord = mtc.StringToDouble(this.acompte.getIdPrestataire().getAccord());
                Double accp = mtc.StringToDouble(this.acompte.getIdPrestataire().getAccompte());
                Double calculeinter = accp + macp;
                String rcalculeinter = mtc.DoubleToString(calculeinter);
                Double rewreliquat = accord - calculeinter;
                String rrewreliquat = mtc.DoubleToString(rewreliquat);

                /*String mts = mtc.DoubleToString(this.montantDouble);
                 this.acompte.setMontantAcompte(mts);*/
                System.out.println("Accompte TVA: " + this.acompte.getChoixtva());

                ejbAcompte.insertAcompte(this.acompte);

                this.acompte.getIdPrestataire().setAccompte(rcalculeinter);
                this.acompte.getIdPrestataire().setReliquant(rrewreliquat);
                this.acompte.getIdPrestataire().setEchenance(this.acompte.getEcheanceAcompte());
                if (accord < rewreliquat) {
                    this.acompte.getIdPrestataire().setVoyant("oui");
                } else {
                    this.acompte.getIdPrestataire().setVoyant("non");
                }
                ejbPrestataire.edit(this.acompte.getIdPrestataire());

            }

            return "acomptes";

        } catch (Exception e) {
            return "new_acompte";
        }
    }

    public String reset() {
        this.acompte = new Acompte();
        return "new_acompte";
    }

    public String editer() {
        try {
            acompteFromEdit = (Acompte) item.getRowData();
            MontantConverter mtc = new MontantConverter();
            Double mtcc = mtc.StringToDouble(acompteFromEdit.getMontantAcompte());
            this.setMontantFromEditDouble(mtcc);
            this.setAncienMtAcompteFromEdit(acompteFromEdit.getMontantAcompte());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "edit_acompte";
    }

    public String update() {
        try {

            if (this.acompteFromEdit.getChoixtva().equals("oui")) {

                MontantConverter mtc = new MontantConverter();
                Double macp = this.montantFromEditDouble;
                Double mttva = macp * 0.18;
                Double allmacp = macp + mttva;
                String mtacpconver = mtc.DoubleToString(allmacp);
                this.acompteFromEdit.setMontantAcompte(mtacpconver);
                Double accord = mtc.StringToDouble(this.acompteFromEdit.getIdPrestataire().getAccord());
                Double accp = mtc.StringToDouble(this.acompteFromEdit.getIdPrestataire().getAccompte());
                Double ancienmt = mtc.StringToDouble(this.getAncienMtAcompteFromEdit());
                Double initaccp = accp - ancienmt;
                Double calculeinter = initaccp + allmacp;
                String rcalculeinter = mtc.DoubleToString(calculeinter);
                Double rewreliquat = accord - calculeinter;
                String rrewreliquat = mtc.DoubleToString(rewreliquat);
                ejbAcompte.edit(this.acompteFromEdit);

                this.acompteFromEdit.getIdPrestataire().setAccompte(rcalculeinter);
                this.acompteFromEdit.getIdPrestataire().setReliquant(rrewreliquat);
                this.acompteFromEdit.getIdPrestataire().setEchenance(this.acompteFromEdit.getEcheanceAcompte());
                if (accord < rewreliquat) {
                    this.acompteFromEdit.getIdPrestataire().setVoyant("oui");
                } else {
                    this.acompteFromEdit.getIdPrestataire().setVoyant("non");
                }
                ejbPrestataire.edit(this.acompteFromEdit.getIdPrestataire());
            } else {

                MontantConverter mtc = new MontantConverter();
                Double macp = this.montantFromEditDouble;
                String mtacpconver = mtc.DoubleToString(macp);
                this.acompteFromEdit.setMontantAcompte(mtacpconver);
                Double accord = mtc.StringToDouble(this.acompteFromEdit.getIdPrestataire().getAccord());
                Double accp = mtc.StringToDouble(this.acompteFromEdit.getIdPrestataire().getAccompte());
                Double ancienmt = mtc.StringToDouble(this.getAncienMtAcompteFromEdit());
                Double initaccp = accp - ancienmt;
                Double calculeinter = initaccp + macp;
                String rcalculeinter = mtc.DoubleToString(calculeinter);
                Double rewreliquat = accord - calculeinter;
                String rrewreliquat = mtc.DoubleToString(rewreliquat);
                ejbAcompte.edit(this.acompteFromEdit);

                this.acompteFromEdit.getIdPrestataire().setAccompte(rcalculeinter);
                this.acompteFromEdit.getIdPrestataire().setReliquant(rrewreliquat);
                this.acompteFromEdit.getIdPrestataire().setEchenance(this.acompteFromEdit.getEcheanceAcompte());
                if (accord < rewreliquat) {
                    this.acompteFromEdit.getIdPrestataire().setVoyant("oui");
                } else {
                    this.acompteFromEdit.getIdPrestataire().setVoyant("non");
                }
                ejbPrestataire.edit(this.acompteFromEdit.getIdPrestataire());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "acomptes";
    }

    public String supprimer() {
        try {
            acompteFromEdit = (Acompte) item.getRowData();
            ejbAcompte.remove(acompteFromEdit);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "acomptes";
    }

}
