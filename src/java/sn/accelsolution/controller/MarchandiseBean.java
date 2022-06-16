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
import sn.accelsolution.dao.CorpsetatFacade;
import sn.accelsolution.dao.MarchandiseFacade;
import sn.accelsolution.dao.PrixFacade;
import sn.accelsolution.entities.Actionmenu;
import sn.accelsolution.entities.Corpsetat;
import sn.accelsolution.entities.Marchandise;
import sn.accelsolution.entities.Prix;
import sn.accelsolution.util.MontantConverter;
import sn.accelsolution.util.UtilControleMenu;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DELL
 */
@ManagedBean
@RequestScoped
public class MarchandiseBean implements Serializable {

    @EJB
    MarchandiseFacade ejbmarchandise;
    @EJB
    PrixFacade ejbprix;
    @EJB
    CorpsetatFacade ejbCorpsetat;

    private DataModel item;
    private List listMarchandise;
    private List listCorpsEtat;
    private Marchandise marchandise;
    private Marchandise marchandiseFromEdit;
    private LottechniqueBean lottechnique;
    private Prix prix;
    private Marchandise marchandiseFromSearch;
    private List<Prix> allPrixs;
    private String nomCorps;
    private Double prixDouble;
    private List<Actionmenu> myllActionmenus;
    private String creerMarchandise;
    private String modifierMarchandise;
    private String supprimerMarchandise;
    private String consulterMarchandise;
    private String imprimerMarchandise;

    private List<Actionmenu> myllActionmenusPrix;
    private String creerPrix;
    private String modifierPrix;
    private String supprimerPrix;
    private String consulterPrix;
    private String imprimerPrix;
    private List<Marchandise> filteredListMarchandise;

    /**
     * Creates a new instance of UtilisateurBean
     */
    @PostConstruct
    public void init() {

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UtilControleMenu utilControleMenu = new UtilControleMenu();

        myllActionmenus = (List<Actionmenu>) session.getAttribute("allActionmenusFromTraitementMateriels");

        this.setCreerMarchandise(utilControleMenu.creerMarchandise(myllActionmenus));
        this.setModifierMarchandise(utilControleMenu.modifierMarchandise(myllActionmenus));
        this.setSupprimerMarchandise(utilControleMenu.supprimerMarchandise(myllActionmenus));
        this.setConsulterMarchandise(utilControleMenu.consulterMarchandise(myllActionmenus));

        myllActionmenusPrix = (List<Actionmenu>) session.getAttribute("allActionmenusFromTraitementReferencielPrix");

        this.setCreerPrix(utilControleMenu.creerPrix(myllActionmenus));
        this.setModifierPrix(utilControleMenu.modifierPrix(myllActionmenus));
        this.setSupprimerPrix(utilControleMenu.supprimerPrix(myllActionmenus));
        this.setConsulterPrix(utilControleMenu.consulterPrix(myllActionmenus));

        item = new ListDataModel();

        List<Marchandise> listAllMarchand = ejbmarchandise.findAll();
        List<Marchandise> listAllMarchandToAdd = new ArrayList<>();
        listAllMarchandToAdd.clear();
        //UtilUtfconvert utfconvert = new UtilUtfconvert();
        for (Marchandise march : listAllMarchand) {
            listAllMarchandToAdd.add(march);
        }
        this.setListMarchandise(listAllMarchandToAdd);
        item.setWrappedData(this.getListMarchandise());

    }

    public MarchandiseBean() {
        lottechnique = new LottechniqueBean();
        marchandise = new Marchandise();
        marchandiseFromEdit = new Marchandise();
        prix = new Prix();
        marchandiseFromSearch = new Marchandise();
    } 

    public List<Marchandise> getFilteredListMarchandise() {
        return filteredListMarchandise;
    }

    public void setFilteredListMarchandise(List<Marchandise> filteredListMarchandise) {
        this.filteredListMarchandise = filteredListMarchandise;
    }
    
    

    public List<Actionmenu> getMyllActionmenusPrix() {
        return myllActionmenusPrix;
    }

    public void setMyllActionmenusPrix(List<Actionmenu> myllActionmenusPrix) {
        this.myllActionmenusPrix = myllActionmenusPrix;
    }

    public String getCreerPrix() {
        return creerPrix;
    }

    public void setCreerPrix(String creerPrix) {
        this.creerPrix = creerPrix;
    }

    public String getModifierPrix() {
        return modifierPrix;
    }

    public void setModifierPrix(String modifierPrix) {
        this.modifierPrix = modifierPrix;
    }

    public String getSupprimerPrix() {
        return supprimerPrix;
    }

    public void setSupprimerPrix(String supprimerPrix) {
        this.supprimerPrix = supprimerPrix;
    }

    public String getConsulterPrix() {
        return consulterPrix;
    }

    public void setConsulterPrix(String consulterPrix) {
        this.consulterPrix = consulterPrix;
    }

    public String getImprimerPrix() {
        return imprimerPrix;
    }

    public void setImprimerPrix(String imprimerPrix) {
        this.imprimerPrix = imprimerPrix;
    }

    public List<Actionmenu> getMyllActionmenus() {
        return myllActionmenus;
    }

    public void setMyllActionmenus(List<Actionmenu> myllActionmenus) {
        this.myllActionmenus = myllActionmenus;
    }

    public String getCreerMarchandise() {
        return creerMarchandise;
    }

    public void setCreerMarchandise(String creerMarchandise) {
        this.creerMarchandise = creerMarchandise;
    }

    public String getModifierMarchandise() {
        return modifierMarchandise;
    }

    public void setModifierMarchandise(String modifierMarchandise) {
        this.modifierMarchandise = modifierMarchandise;
    }

    public String getSupprimerMarchandise() {
        return supprimerMarchandise;
    }

    public void setSupprimerMarchandise(String supprimerMarchandise) {
        this.supprimerMarchandise = supprimerMarchandise;
    }

    public String getConsulterMarchandise() {
        return consulterMarchandise;
    }

    public void setConsulterMarchandise(String consulterMarchandise) {
        this.consulterMarchandise = consulterMarchandise;
    }

    public String getImprimerMarchandise() {
        return imprimerMarchandise;
    }

    public void setImprimerMarchandise(String imprimerMarchandise) {
        this.imprimerMarchandise = imprimerMarchandise;
    }

    public Double getPrixDouble() {
        return prixDouble;
    }

    public void setPrixDouble(Double prixDouble) {
        this.prixDouble = prixDouble;
    }

    public List getListCorpsEtat() {
        listCorpsEtat = ejbCorpsetat.findAll();
        return listCorpsEtat;
    }

    public void setListCorpsEtat(List listCorpsEtat) {
        this.listCorpsEtat = listCorpsEtat;
    }

    public String getNomCorps() {
        return nomCorps;
    }

    public void setNomCorps(String nomCorps) {
        this.nomCorps = nomCorps;
    }

    public List<Prix> getAllPrixs() {
        return allPrixs;
    }

    public void setAllPrixs(List<Prix> allPrixs) {
        this.allPrixs = allPrixs;
    }

    public Marchandise getMarchandiseFromSearch() {
        return marchandiseFromSearch;
    }

    public void setMarchandiseFromSearch(Marchandise marchandiseFromSearch) {
        this.marchandiseFromSearch = marchandiseFromSearch;
    }

    public Prix getPrix() {
        return prix;
    }

    public void setPrix(Prix prix) {
        this.prix = prix;
    }

    public LottechniqueBean getLottechnique() {
        return lottechnique;
    }

    public void setLottechnique(LottechniqueBean lottechnique) {
        this.lottechnique = lottechnique;
    }

    public DataModel getItem() {
        return item;
    }

    public void setItem(DataModel item) {
        this.item = item;
    }

    public List getListMarchandise() {
        return listMarchandise;
    }

    public void setListMarchandise(List listMarchandise) {
        this.listMarchandise = listMarchandise;
    }

    public Marchandise getMarchandise() {
        return marchandise;
    }

    public void setMarchandise(Marchandise marchandise) {
        this.marchandise = marchandise;
    }

    public Marchandise getMarchandiseFromEdit() {
        return marchandiseFromEdit;
    }

    public void setMarchandiseFromEdit(Marchandise marchandiseFromEdit) {
        this.marchandiseFromEdit = marchandiseFromEdit;
    }

    public String save() {
        try {
            ejbmarchandise.insertMarchandise(marchandise);
            return "marchandises";
        } catch (Exception e) {
            return "new_marchandise";
        }

    }

    public String savePrix() {
        try {
            MontantConverter mtc = new MontantConverter();
            String mtprix = mtc.DoubleToString(this.prixDouble);
            this.prix.setPrix(mtprix);
            ejbprix.insertPrix(this.prix);
            return "prix";
        } catch (Exception e) {
            return "new_prix";
        }

    }

    public void reset() {

    }

    public String resetModif() {
        return "marchandises";
    }

    public String editer() {
        try {
            marchandiseFromEdit = (Marchandise) item.getRowData();
            System.out.println("Lot technique :" + marchandiseFromEdit.getLibelle());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "edit_marchandise";
    }

    public String update() {
        try { 
            UtilUtfconvert utfconvert = new UtilUtfconvert(); 
            this.marchandiseFromEdit.setLibelle(utfconvert.convertFromUTF8(this.marchandiseFromEdit.getLibelle()));
            ejbmarchandise.edit(this.marchandiseFromEdit);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "marchandises";
    }

    public String supprimer() {
        try {
            marchandiseFromEdit = (Marchandise) item.getRowData();
            ejbmarchandise.remove(marchandiseFromEdit);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "marchandises";
    }

    public void search() {

        try {

            this.setNomCorps(this.marchandiseFromSearch.getLibelle());
            this.allPrixs = ejbprix.listOfPrixByMarchandise(this.marchandiseFromSearch);

        } catch (Exception e) {
        }
    }

//    public void renderInfo() {
//
//        try {
//            List<Corpsetat> lescorpsEtat = ejbCorpsetat.listOfCorpsByLot(this.marchandise.getIdLottechnique());
//            //this.setListCorpsEtat(lescorpsEtat);
//            
//            for(Corpsetat c: lescorpsEtat){
//                System.out.println("Corps d'etat: "+c.getLibellecoprsetat());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
