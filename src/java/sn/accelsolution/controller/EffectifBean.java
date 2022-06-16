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
import org.primefaces.model.chart.PieChartModel;
import sn.accelsolution.dao.ContratFacade;
import sn.accelsolution.dao.MarcheFacade;
import sn.accelsolution.entities.Contrat;
import sn.accelsolution.entities.Marche;

/**
 *
 * @author DELL
 */
@ManagedBean
@RequestScoped
public class EffectifBean implements Serializable {

    /**
     * Creates a new instance of EffectifBean
     */
    @EJB
    ContratFacade ejbcontrat;
    @EJB
    MarcheFacade ejbmarche;

    private PieChartModel pieModel1;
    private PieChartModel pieModel2;
    private int recruter;
    private int licencier;
    private int affecter;
    private int stagaire;
    private int demissionner;
    private int projetencours;
    private int projettermine;
    private List<Contrat> listContrats;
    private List<Marche> listMarcches;
    //private List<DetailleC> listContrats;

    @PostConstruct
    public void init() {
        createPieModels();
    }

    public EffectifBean() {
        listContrats = new ArrayList<Contrat>();
    }

    public int getProjettermine() {
        return projettermine;
    }

    public void setProjettermine(int projettermine) {
        this.projettermine = projettermine;
    }

    public List<Marche> getListMarcches() {
        return listMarcches;
    }

    public void setListMarcches(List<Marche> listMarcches) {
        this.listMarcches = listMarcches;
    }

    public int getProjetencours() {
        return projetencours;
    }

    public void setProjetencours(int projetencours) {
        this.projetencours = projetencours;
    }

    public int getRecruter() {
        return recruter;
    }

    public void setRecruter(int recruter) {
        this.recruter = recruter;
    }

    public int getLicencier() {
        return licencier;
    }

    public void setLicencier(int licencier) {
        this.licencier = licencier;
    }

    public int getAffecter() {
        return affecter;
    }

    public void setAffecter(int affecter) {
        this.affecter = affecter;
    }

    public int getStagaire() {
        return stagaire;
    }

    public void setStagaire(int stagaire) {
        this.stagaire = stagaire;
    }

    public PieChartModel getPieModel1() {
        return pieModel1;
    }

    public int getDemissionner() {
        return demissionner;
    }

    public void setDemissionner(int demissionner) {
        this.demissionner = demissionner;
    }

    public void setPieModel1(PieChartModel pieModel1) {
        this.pieModel1 = pieModel1;
    }

    public PieChartModel getPieModel2() {
        return pieModel2;
    }

    public void setPieModel2(PieChartModel pieModel2) {
        this.pieModel2 = pieModel2;
    }

    public List getListContrats() {
        return listContrats;
    }

    public void setListContrats(List listContrats) {
        this.listContrats = listContrats;
    }

    private void createPieModels() {
        createPieModel1();
        createPieModel2();
    }

    private void createPieModel1() {
        pieModel1 = new PieChartModel();

        this.setRecruter(0);
        this.setLicencier(0);
        this.setAffecter(0);
        this.setStagaire(0);
        this.setDemissionner(0);

        this.listContrats = ejbcontrat.findAll();

        for (Contrat contrat : this.listContrats) {
            if (contrat.getLocalTypeContrat().equals("Recrutement")) {
                this.recruter = this.recruter + 1;
            }

            if (contrat.getLocalTypeContrat().equals("Stage")) {
                this.stagaire = this.stagaire + 1;
            }

            if (contrat.getLocalTypeContrat().equals("Licenciement")) {
                this.licencier = this.licencier + 1;
            }

            if (contrat.getLocalTypeContrat().equals("Démission")) {
                this.demissionner = this.demissionner + 1;
            }

        }

        pieModel1.set(" Recrutés", this.recruter);
        pieModel1.set(" Stagaires", this.stagaire);
        pieModel1.set(" Affectés (chantier)", this.affecter);

        pieModel1.setTitle("Effectif du personnel");
        pieModel1.setLegendPosition("w");
    }

    private void createPieModel2() {
        pieModel2 = new PieChartModel();

        this.setProjetencours(0);
        this.setProjettermine(0);

        this.listMarcches = ejbmarche.findAll();

        for (Marche marche : this.listMarcches) {

            this.projetencours = this.projetencours + 1;
        }

        pieModel2.set(" Projets en cours", this.projetencours);
        pieModel2.set(" Projets terminés", this.projettermine);

        pieModel2.setTitle("Effectif des projets");
        pieModel2.setLegendPosition("w");
    }

}
