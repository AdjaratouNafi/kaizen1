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
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import sn.accelsolution.dao.ChantierFacade;
import sn.accelsolution.dao.CommandeFacade;
import sn.accelsolution.dao.DetailleCommandeFacade;
import sn.accelsolution.dao.DetailleLivraisonFacade;
import sn.accelsolution.dao.LivraisonFacade;
import sn.accelsolution.dao.MarcheFacade;
import sn.accelsolution.entities.Actionmenu;
import sn.accelsolution.entities.Chantier;
import sn.accelsolution.entities.Commande;
import sn.accelsolution.entities.DetailleCommande;
import sn.accelsolution.entities.DetailleLivraison;
import sn.accelsolution.entities.Livraison;
import sn.accelsolution.entities.Marche;
import sn.accelsolution.util.FactureUtil;
import sn.accelsolution.util.UtilControleMenu;  

/**
 *
 * @author DELL
 */
@ManagedBean
@ViewScoped
public class LivraisonBean implements Serializable {

    /**
     * Creates a new instance of ContratRecrutBean
     */
    @EJB
    LivraisonFacade ejbLivraison;
    @EJB
    DetailleLivraisonFacade ejbDetailleLivraison;
    @EJB
    MarcheFacade ejbmarche;
    @EJB
    ChantierFacade ejbchantier;
    @EJB
    CommandeFacade ejbcommande;
    @EJB
    DetailleCommandeFacade ejbdetaillecmd;

    private Livraison livraison;
    private Marche marche;
    private Chantier chantier;
    private DetailleLivraison detailleLivraison;
    private List<Marche> listMarches;
    private List<Chantier> listChantier;
    private List<Livraison> listLivraisons;
    private List<Livraison> filteredListLivraisons;
    private String NomChantier;
    private int qtCmd;
    private int qtRst;
    private List<Commande> allCommandes;
    private List<Commande> listCommandes;
    private List<FactureUtil> listDesLivraisons;
    private List<Livraison> allDetailleLivraison;
    private String dateLiv;
    private String fournisseurLiv;
    private String idCommandeLiv;
    private int pourcentageExe;

    private List<Actionmenu> myllActionmenus;
    private String creerLivraison;
    private String modifierLivraison;
    private String supprimerLivraison;
    private String consulterLivraison;
    private String imprimerLivraison;

    @PostConstruct
    public void init() {

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UtilControleMenu utilControleMenu = new UtilControleMenu();

        myllActionmenus = (List<Actionmenu>) session.getAttribute("allActionmenusFromTraitementApproChantier");

        this.setCreerLivraison(utilControleMenu.creerLivraison(myllActionmenus));
        this.setModifierLivraison(utilControleMenu.modifierLivraison(myllActionmenus));
        this.setSupprimerLivraison(utilControleMenu.supprimerLivraison(myllActionmenus));
        this.setConsulterLivraison(utilControleMenu.consulterLivraison(myllActionmenus));

        //this.setTva("18%");
        //this.listLivraisons = ejbLivraison.listOfAllLivraison();
        pourcentageExe = 0;
    }

    public LivraisonBean() {
        livraison = new Livraison();
        detailleLivraison = new DetailleLivraison();
        listDesLivraisons = new ArrayList<>();
    }

    public List<Actionmenu> getMyllActionmenus() {
        return myllActionmenus;
    }

    public void setMyllActionmenus(List<Actionmenu> myllActionmenus) {
        this.myllActionmenus = myllActionmenus;
    }

    public String getCreerLivraison() {
        return creerLivraison;
    }

    public void setCreerLivraison(String creerLivraison) {
        this.creerLivraison = creerLivraison;
    }

    public String getModifierLivraison() {
        return modifierLivraison;
    }

    public void setModifierLivraison(String modifierLivraison) {
        this.modifierLivraison = modifierLivraison;
    }

    public String getSupprimerLivraison() {
        return supprimerLivraison;
    }

    public void setSupprimerLivraison(String supprimerLivraison) {
        this.supprimerLivraison = supprimerLivraison;
    }

    public String getConsulterLivraison() {
        return consulterLivraison;
    }

    public void setConsulterLivraison(String consulterLivraison) {
        this.consulterLivraison = consulterLivraison;
    }

    public String getImprimerLivraison() {
        return imprimerLivraison;
    }

    public void setImprimerLivraison(String imprimerLivraison) {
        this.imprimerLivraison = imprimerLivraison;
    }

    public int getPourcentageExe() {
        return pourcentageExe;
    }

    public void setPourcentageExe(int pourcentageExe) {
        this.pourcentageExe = pourcentageExe;
    }

    public List<Livraison> getAllDetailleLivraison() {
        return allDetailleLivraison;
    }

    public void setAllDetailleLivraison(List<Livraison> allDetailleLivraison) {
        this.allDetailleLivraison = allDetailleLivraison;
    }

    public String getDateLiv() {
        return dateLiv;
    }

    public void setDateLiv(String dateLiv) {
        this.dateLiv = dateLiv;
    }

    public String getFournisseurLiv() {
        return fournisseurLiv;
    }

    public void setFournisseurLiv(String fournisseurLiv) {
        this.fournisseurLiv = fournisseurLiv;
    }

    public String getIdCommandeLiv() {
        return idCommandeLiv;
    }

    public void setIdCommandeLiv(String idCommandeLiv) {
        this.idCommandeLiv = idCommandeLiv;
    }

    public List<Commande> getListCommandes() {
        listCommandes = ejbcommande.listOfExpressionNonLivrees();
        return listCommandes;
    }

    public void setListCommandes(List<Commande> listCommandes) {
        this.listCommandes = listCommandes;
    }

    public List<Livraison> getFilteredListLivraisons() {
        return filteredListLivraisons;
    }

    public void setFilteredListLivraisons(List<Livraison> filteredListLivraisons) {
        this.filteredListLivraisons = filteredListLivraisons;
    }

    public List<Commande> getAllCommandes() {
        allCommandes = ejbcommande.findAll();
        return allCommandes;
    }

    public void setAllCommandes(List<Commande> allCommandes) {
        this.allCommandes = allCommandes;
    }

    public int getQtCmd() {
        return qtCmd;
    }

    public void setQtCmd(int qtCmd) {
        this.qtCmd = qtCmd;
    }

    public int getQtRst() {
        return qtRst;
    }

    public void setQtRst(int qtRst) {
        this.qtRst = qtRst;
    }

    public List<FactureUtil> getListDesLivraisons() {
        return listDesLivraisons;
    }

    public void setListDesLivraisons(List<FactureUtil> listDesLivraisons) {
        this.listDesLivraisons = listDesLivraisons;
    }

    public String save() {

        try {

            for (FactureUtil f : this.listDesLivraisons) {
                this.livraison.setDesignation(f.getDesignation());
                this.livraison.setQtLivre(f.getQtLivree());
                this.livraison.setPu(f.getPu());
                this.livraison.setQtRestante(f.getQtRestante());
                ejbLivraison.insertLivraison(this.livraison);

                int idL = ejbLivraison.maxLivraison();
                Livraison Lv = ejbLivraison.find(idL);
                this.detailleLivraison.setIdLivraison(Lv);

                ejbDetailleLivraison.insertDetailleCommande(this.detailleLivraison);
            }

            int cpt1 = 0;
            int cpt2 = 0;
            for (FactureUtil f : this.listDesLivraisons) {
                cpt1 = cpt1 + 1;
                DetailleCommande dcmd = ejbdetaillecmd.find(f.getIdDetailleCommande());
                dcmd.setQtlivree(f.getQtLivree());
                dcmd.setQtrestante(f.getQtRestante());

                if (dcmd.getQtrestante() == 0) {
                    cpt2 = cpt2 + 1;
                }

                ejbdetaillecmd.edit(dcmd);
            }

            if (cpt1 == cpt2) {
                this.livraison.getIdCommande().setLivree("oui");
            } else {
                this.livraison.getIdCommande().setLivree("non");
            }

            ejbcommande.edit(this.livraison.getIdCommande());
            return "livraisons";
        } catch (Exception e) {
            return "new_livraison";
        }
    }

    public String getNomChantier() {
        return NomChantier;
    }

    public void setNomChantier(String NomChantier) {
        this.NomChantier = NomChantier;
    }

    public Livraison getLivraison() {
        return livraison;
    }

    public void setLivraison(Livraison livraison) {
        this.livraison = livraison;
    }

    public DetailleLivraison getDetailleLivraison() {
        return detailleLivraison;
    }

    public void setDetailleLivraison(DetailleLivraison detailleLivraison) {
        this.detailleLivraison = detailleLivraison;
    }

    public List<Livraison> getListLivraisons() {
        return listLivraisons;
    }

    public void setListLivraisons(List<Livraison> listLivraisons) {
        this.listLivraisons = listLivraisons;
    }

    public Marche getMarche() {
        return marche;
    }

    public void setMarche(Marche marche) {
        this.marche = marche;
    }

    public Chantier getChantier() {
        return chantier;
    }

    public void setChantier(Chantier chantier) {
        this.chantier = chantier;
    }

    public List<Marche> getListMarches() {
        listMarches = ejbmarche.findAll();
        return listMarches;
    }

    public void setListMarches(List<Marche> listMarches) {
        this.listMarches = listMarches;
    }

    public List<Chantier> getListChantier() {
        listChantier = ejbchantier.findAll();
        return listChantier;
    }

    public void setListChantier(List<Chantier> listChantier) {
        this.listChantier = listChantier;
    }

    public void search() {

        try {

            this.setNomChantier(this.chantier.getSiteChantier());
            this.listLivraisons = ejbLivraison.listOfLivraisonByChantier(this.chantier);

        } catch (Exception e) {
        }
    }

    public void renderInfoCmd() {
        List<DetailleCommande> listDCmd = ejbdetaillecmd.listOfDetailleByCommande(this.getLivraison().getIdCommande());
        for (DetailleCommande d : listDCmd) {
            this.detailleLivraison.setDesignation(d.getIdMarchandise().getLibelle());
            this.setQtCmd(d.getQuantite());
        }

        this.livraison.setIdChantier(this.getLivraison().getIdCommande().getIdChantier());
        this.livraison.setIdFournisseur(this.getLivraison().getIdCommande().getIdFournisseur());

    }

    public void renderCalcule() {

        int rst = this.getQtCmd() - this.detailleLivraison.getQtLivre();
        this.setQtRst(rst);

    }

    public void renderInfoCMD() {

        this.livraison.setIdFournisseur(this.livraison.getIdCommande().getIdFournisseur());
        this.livraison.setIdChantier(this.livraison.getIdCommande().getIdChantier());

        this.livraison.setIdCommande(this.livraison.getIdCommande());

        List<DetailleCommande> listDetailCommandeByCmd = ejbdetaillecmd.listOfDetailleCommandeByCommande(this.livraison.getIdCommande());

        for (DetailleCommande d : listDetailCommandeByCmd) {
            FactureUtil fu = new FactureUtil();
            fu.setIdDetailleCommande(d.getIdDetailleCommande());
            fu.setDesignation(d.getDesignation());
            fu.setRef(d.getReference());
            fu.setUnite(d.getUnite());
            fu.setQuantite(d.getQuantite());
            fu.setPu(d.getPu());
            fu.setQtLivree(0);
            fu.setQtRestante(d.getQtrestante());
            fu.setAncienQtRestant(d.getQtrestante());
            this.listDesLivraisons.add(fu);
        }

    }

    public void calculeMtRestant(FactureUtil f) {

        try {

            int index = 0;
            for (int x = 0; x < this.listDesLivraisons.size(); x++) {

                FactureUtil f1 = this.listDesLivraisons.get(x);
                if (f1.getDesignation().equalsIgnoreCase(f.getDesignation())) {
                    index = x;
                }
            }

            if (f.getAncienQtRestant() >= f.getQtLivree()) {
                int qtr = f.getAncienQtRestant() - f.getQtLivree();
                if (qtr > 0) {
                    f.setQtRestante(qtr);
                } else {
                    f.setQtRestante(0);
                }
            } else {
                f.setQtLivree(0);
                f.setQtRestante(f.getAncienQtRestant());
            }

            System.out.println("Qtr: " + f.getQtRestante());
            this.listDesLivraisons.set(index, f);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void renderInfo1() {
        System.out.println("Je suis là 1");
        this.setListChantier(ejbchantier.listOfChantierByMarche(this.getMarche().getIdMarche()));
    }

    public void renderInfo2() {
        System.out.println("Je suis là 2");
        this.setListLivraisons(ejbLivraison.listOfAllLivraisonByChantier(this.getChantier()));
    }

    public void detail() {
        try {

            /* List des detailles devis */
            this.setDateLiv(this.livraison.getDateLivraison());
            this.setFournisseurLiv(this.livraison.getIdFournisseur().getNomFournisseur());
            this.setIdCommandeLiv(this.livraison.getIdCommande().getCode());
            this.setAllDetailleLivraison(ejbLivraison.listOfLivraisonByCommande(this.livraison.getIdCommande()));

            int nbElement = 0;
            int calculeP = 0;
            for (Livraison livre : this.allDetailleLivraison) {
                nbElement = nbElement + 1;
                calculeP = calculeP + livre.getQtLivre();
            }

            pourcentageExe = calculeP / nbElement;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
