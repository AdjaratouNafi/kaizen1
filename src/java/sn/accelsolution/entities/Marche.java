/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author DV7
 */
@Entity
@Table(name = "marche")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Marche.findAll", query = "SELECT m FROM Marche m")
    , @NamedQuery(name = "Marche.findByIdMarche", query = "SELECT m FROM Marche m WHERE m.idMarche = :idMarche")
    , @NamedQuery(name = "Marche.findByNomMarche", query = "SELECT m FROM Marche m WHERE m.nomMarche = :nomMarche")
    , @NamedQuery(name = "Marche.findByAlias", query = "SELECT m FROM Marche m WHERE m.alias = :alias")
    , @NamedQuery(name = "Marche.findByMontantExecution", query = "SELECT m FROM Marche m WHERE m.montantExecution = :montantExecution")
    , @NamedQuery(name = "Marche.findByObjetMarche", query = "SELECT m FROM Marche m WHERE m.objetMarche = :objetMarche")
    , @NamedQuery(name = "Marche.findByNumeroFinancement", query = "SELECT m FROM Marche m WHERE m.numeroFinancement = :numeroFinancement")
    , @NamedQuery(name = "Marche.findByDateMarche", query = "SELECT m FROM Marche m WHERE m.dateMarche = :dateMarche")
    , @NamedQuery(name = "Marche.findByMontantDemarrage", query = "SELECT m FROM Marche m WHERE m.montantDemarrage = :montantDemarrage")
    , @NamedQuery(name = "Marche.findByMontantMarche", query = "SELECT m FROM Marche m WHERE m.montantMarche = :montantMarche")
    , @NamedQuery(name = "Marche.findByRegimeFiscale", query = "SELECT m FROM Marche m WHERE m.regimeFiscale = :regimeFiscale")
    , @NamedQuery(name = "Marche.findByTvaPrecompte", query = "SELECT m FROM Marche m WHERE m.tvaPrecompte = :tvaPrecompte")
    , @NamedQuery(name = "Marche.findByAvanceSurAppro", query = "SELECT m FROM Marche m WHERE m.avanceSurAppro = :avanceSurAppro")
    , @NamedQuery(name = "Marche.findByDateAvenant", query = "SELECT m FROM Marche m WHERE m.dateAvenant = :dateAvenant")
    , @NamedQuery(name = "Marche.findByAvenant", query = "SELECT m FROM Marche m WHERE m.avenant = :avenant")
    , @NamedQuery(name = "Marche.findBySite", query = "SELECT m FROM Marche m WHERE m.site = :site")
    , @NamedQuery(name = "Marche.findByDureeContrat", query = "SELECT m FROM Marche m WHERE m.dureeContrat = :dureeContrat")
    , @NamedQuery(name = "Marche.findByDateDebut", query = "SELECT m FROM Marche m WHERE m.dateDebut = :dateDebut")
    , @NamedQuery(name = "Marche.findByDateFin", query = "SELECT m FROM Marche m WHERE m.dateFin = :dateFin")
    , @NamedQuery(name = "Marche.findByEtatmarche", query = "SELECT m FROM Marche m WHERE m.etatmarche = :etatmarche")
    , @NamedQuery(name = "Marche.findByNotification", query = "SELECT m FROM Marche m WHERE m.notification = :notification")
    , @NamedQuery(name = "Marche.findByCotion", query = "SELECT m FROM Marche m WHERE m.cotion = :cotion")
    , @NamedQuery(name = "Marche.findByMontantcotion", query = "SELECT m FROM Marche m WHERE m.montantcotion = :montantcotion")
    , @NamedQuery(name = "Marche.findByPrimcaution", query = "SELECT m FROM Marche m WHERE m.primcaution = :primcaution")
    , @NamedQuery(name = "Marche.findByMainlevee", query = "SELECT m FROM Marche m WHERE m.mainlevee = :mainlevee")
    , @NamedQuery(name = "Marche.findByOrdreservice", query = "SELECT m FROM Marche m WHERE m.ordreservice = :ordreservice")})
public class Marche implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMarche")
    private Integer idMarche;
    @Size(max = 50)
    @Column(name = "nomMarche")
    private String nomMarche;
    @Size(max = 50)
    @Column(name = "alias")
    private String alias;
    @Size(max = 255)
    @Column(name = "montantExecution")
    private String montantExecution;
    @Size(max = 50)
    @Column(name = "objetMarche")
    private String objetMarche;
    @Size(max = 50)
    @Column(name = "numeroFinancement")
    private String numeroFinancement;
    @Size(max = 50)
    @Column(name = "dateMarche")
    private String dateMarche;
    @Size(max = 255)
    @Column(name = "montantDemarrage")
    private String montantDemarrage;
    @Size(max = 255)
    @Column(name = "montantMarche")
    private String montantMarche;
    @Size(max = 50)
    @Column(name = "regimeFiscale")
    private String regimeFiscale;
    @Size(max = 50)
    @Column(name = "tvaPrecompte")
    private String tvaPrecompte;
    @Size(max = 255)
    @Column(name = "avanceSurAppro")
    private String avanceSurAppro;
    @Size(max = 50)
    @Column(name = "dateAvenant")
    private String dateAvenant;
    @Size(max = 50)
    @Column(name = "avenant")
    private String avenant;
    @Size(max = 50)
    @Column(name = "site")
    private String site;
    @Size(max = 50)
    @Column(name = "dureeContrat")
    private String dureeContrat;
    @Size(max = 50)
    @Column(name = "dateDebut")
    private String dateDebut;
    @Size(max = 50)
    @Column(name = "dateFin")
    private String dateFin;
    @Size(max = 50)
    @Column(name = "etatmarche")
    private String etatmarche;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "observation")
    private String observation;
    @Size(max = 50)
    @Column(name = "notification")
    private String notification;
    @Size(max = 255)
    @Column(name = "cotion")
    private String cotion;
    @Size(max = 255)
    @Column(name = "montantcotion")
    private String montantcotion;
    @Size(max = 255)
    @Column(name = "primcaution")
    private String primcaution;
    @Size(max = 255)
    @Column(name = "mainlevee")
    private String mainlevee;
    @Size(max = 50)
    @Column(name = "ordreservice")
    private String ordreservice;
    @OneToMany(mappedBy = "idMarche")
    private List<Brouillard> brouillardList;
    @OneToMany(mappedBy = "idMarche")
    private List<Paiement> paiementList;
    @OneToMany(mappedBy = "idMarche")
    private List<Decompte> decompteList;
    @JoinColumn(name = "idBailleur", referencedColumnName = "idBailleur")
    @ManyToOne
    private Bailleur idBailleur;
    @JoinColumn(name = "idClient", referencedColumnName = "idClient")
    @ManyToOne
    private Client idClient;
    @JoinColumn(name = "idDevis", referencedColumnName = "idDevis")
    @ManyToOne
    private Devis idDevis;
    @JoinColumn(name = "idDevise", referencedColumnName = "idDevise")
    @ManyToOne
    private Devise idDevise;
    @JoinColumn(name = "idNewfacture", referencedColumnName = "idNewfacture")
    @ManyToOne
    private Newfacture idNewfacture;
    @OneToMany(mappedBy = "idMarche")
    private List<Devis> devisList;
    @OneToMany(mappedBy = "idMarche")
    private List<Chantier> chantierList;
    @OneToMany(mappedBy = "idMarche")
    private List<Decompte2> decompte2List;
    @OneToMany(mappedBy = "idMarche")
    private List<Commande> commandeList;
    @OneToMany(mappedBy = "idMarche")
    private List<Management> managementList;
    @OneToMany(mappedBy = "idMarche")
    private List<Newfacture> newfactureList;
    @OneToMany(mappedBy = "idMarche")
    private List<Operation> operationList;

    public Marche() {
    }

    public Marche(Integer idMarche) {
        this.idMarche = idMarche;
    }

    public Integer getIdMarche() {
        return idMarche;
    }

    public void setIdMarche(Integer idMarche) {
        this.idMarche = idMarche;
    }

    public String getNomMarche() {
        return nomMarche;
    }

    public void setNomMarche(String nomMarche) {
        this.nomMarche = nomMarche;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getMontantExecution() {
        return montantExecution;
    }

    public void setMontantExecution(String montantExecution) {
        this.montantExecution = montantExecution;
    }

    public String getObjetMarche() {
        return objetMarche;
    }

    public void setObjetMarche(String objetMarche) {
        this.objetMarche = objetMarche;
    }

    public String getNumeroFinancement() {
        return numeroFinancement;
    }

    public void setNumeroFinancement(String numeroFinancement) {
        this.numeroFinancement = numeroFinancement;
    }

    public String getDateMarche() {
        return dateMarche;
    }

    public void setDateMarche(String dateMarche) {
        this.dateMarche = dateMarche;
    }

    public String getMontantDemarrage() {
        return montantDemarrage;
    }

    public void setMontantDemarrage(String montantDemarrage) {
        this.montantDemarrage = montantDemarrage;
    }

    public String getMontantMarche() {
        return montantMarche;
    }

    public void setMontantMarche(String montantMarche) {
        this.montantMarche = montantMarche;
    }

    public String getRegimeFiscale() {
        return regimeFiscale;
    }

    public void setRegimeFiscale(String regimeFiscale) {
        this.regimeFiscale = regimeFiscale;
    }

    public String getTvaPrecompte() {
        return tvaPrecompte;
    }

    public void setTvaPrecompte(String tvaPrecompte) {
        this.tvaPrecompte = tvaPrecompte;
    }

    public String getAvanceSurAppro() {
        return avanceSurAppro;
    }

    public void setAvanceSurAppro(String avanceSurAppro) {
        this.avanceSurAppro = avanceSurAppro;
    }

    public String getDateAvenant() {
        return dateAvenant;
    }

    public void setDateAvenant(String dateAvenant) {
        this.dateAvenant = dateAvenant;
    }

    public String getAvenant() {
        return avenant;
    }

    public void setAvenant(String avenant) {
        this.avenant = avenant;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getDureeContrat() {
        return dureeContrat;
    }

    public void setDureeContrat(String dureeContrat) {
        this.dureeContrat = dureeContrat;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getEtatmarche() {
        return etatmarche;
    }

    public void setEtatmarche(String etatmarche) {
        this.etatmarche = etatmarche;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getCotion() {
        return cotion;
    }

    public void setCotion(String cotion) {
        this.cotion = cotion;
    }

    public String getMontantcotion() {
        return montantcotion;
    }

    public void setMontantcotion(String montantcotion) {
        this.montantcotion = montantcotion;
    }

    public String getPrimcaution() {
        return primcaution;
    }

    public void setPrimcaution(String primcaution) {
        this.primcaution = primcaution;
    }

    public String getMainlevee() {
        return mainlevee;
    }

    public void setMainlevee(String mainlevee) {
        this.mainlevee = mainlevee;
    }

    public String getOrdreservice() {
        return ordreservice;
    }

    public void setOrdreservice(String ordreservice) {
        this.ordreservice = ordreservice;
    }

    @XmlTransient
    @JsonIgnore
    public List<Brouillard> getBrouillardList() {
        return brouillardList;
    }

    public void setBrouillardList(List<Brouillard> brouillardList) {
        this.brouillardList = brouillardList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Paiement> getPaiementList() {
        return paiementList;
    }

    public void setPaiementList(List<Paiement> paiementList) {
        this.paiementList = paiementList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Decompte> getDecompteList() {
        return decompteList;
    }

    public void setDecompteList(List<Decompte> decompteList) {
        this.decompteList = decompteList;
    }

    public Bailleur getIdBailleur() {
        return idBailleur;
    }

    public void setIdBailleur(Bailleur idBailleur) {
        this.idBailleur = idBailleur;
    }

    public Client getIdClient() {
        return idClient;
    }

    public void setIdClient(Client idClient) {
        this.idClient = idClient;
    }

    public Devis getIdDevis() {
        return idDevis;
    }

    public void setIdDevis(Devis idDevis) {
        this.idDevis = idDevis;
    }

    public Devise getIdDevise() {
        return idDevise;
    }

    public void setIdDevise(Devise idDevise) {
        this.idDevise = idDevise;
    }

    public Newfacture getIdNewfacture() {
        return idNewfacture;
    }

    public void setIdNewfacture(Newfacture idNewfacture) {
        this.idNewfacture = idNewfacture;
    }

    @XmlTransient
    @JsonIgnore
    public List<Devis> getDevisList() {
        return devisList;
    }

    public void setDevisList(List<Devis> devisList) {
        this.devisList = devisList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Chantier> getChantierList() {
        return chantierList;
    }

    public void setChantierList(List<Chantier> chantierList) {
        this.chantierList = chantierList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Decompte2> getDecompte2List() {
        return decompte2List;
    }

    public void setDecompte2List(List<Decompte2> decompte2List) {
        this.decompte2List = decompte2List;
    }

    @XmlTransient
    @JsonIgnore
    public List<Commande> getCommandeList() {
        return commandeList;
    }

    public void setCommandeList(List<Commande> commandeList) {
        this.commandeList = commandeList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Management> getManagementList() {
        return managementList;
    }

    public void setManagementList(List<Management> managementList) {
        this.managementList = managementList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Newfacture> getNewfactureList() {
        return newfactureList;
    }

    public void setNewfactureList(List<Newfacture> newfactureList) {
        this.newfactureList = newfactureList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Operation> getOperationList() {
        return operationList;
    }

    public void setOperationList(List<Operation> operationList) {
        this.operationList = operationList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMarche != null ? idMarche.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Marche)) {
            return false;
        }
        Marche other = (Marche) object;
        if ((this.idMarche == null && other.idMarche != null) || (this.idMarche != null && !this.idMarche.equals(other.idMarche))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Marche[ idMarche=" + idMarche + " ]";
    }
    
}
