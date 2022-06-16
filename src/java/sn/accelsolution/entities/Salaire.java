/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "salaire")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Salaire.findAll", query = "SELECT s FROM Salaire s")
    , @NamedQuery(name = "Salaire.findByIdSalaire", query = "SELECT s FROM Salaire s WHERE s.idSalaire = :idSalaire")
    , @NamedQuery(name = "Salaire.findByNumFicheDepaye", query = "SELECT s FROM Salaire s WHERE s.numFicheDepaye = :numFicheDepaye")
    , @NamedQuery(name = "Salaire.findByNomEmployeur", query = "SELECT s FROM Salaire s WHERE s.nomEmployeur = :nomEmployeur")
    , @NamedQuery(name = "Salaire.findByAdreseEmployeur", query = "SELECT s FROM Salaire s WHERE s.adreseEmployeur = :adreseEmployeur")
    , @NamedQuery(name = "Salaire.findByPeriodeDebut", query = "SELECT s FROM Salaire s WHERE s.periodeDebut = :periodeDebut")
    , @NamedQuery(name = "Salaire.findByPeriodeFin", query = "SELECT s FROM Salaire s WHERE s.periodeFin = :periodeFin")
    , @NamedQuery(name = "Salaire.findByMontantBrut", query = "SELECT s FROM Salaire s WHERE s.montantBrut = :montantBrut")
    , @NamedQuery(name = "Salaire.findByTypeSalarie", query = "SELECT s FROM Salaire s WHERE s.typeSalarie = :typeSalarie")
    , @NamedQuery(name = "Salaire.findByRetenu", query = "SELECT s FROM Salaire s WHERE s.retenu = :retenu")
    , @NamedQuery(name = "Salaire.findByBase", query = "SELECT s FROM Salaire s WHERE s.base = :base")
    , @NamedQuery(name = "Salaire.findByTauxEmploye", query = "SELECT s FROM Salaire s WHERE s.tauxEmploye = :tauxEmploye")
    , @NamedQuery(name = "Salaire.findByCotisation", query = "SELECT s FROM Salaire s WHERE s.cotisation = :cotisation")
    , @NamedQuery(name = "Salaire.findByTauxSalarie", query = "SELECT s FROM Salaire s WHERE s.tauxSalarie = :tauxSalarie")
    , @NamedQuery(name = "Salaire.findByValeurRetenu", query = "SELECT s FROM Salaire s WHERE s.valeurRetenu = :valeurRetenu")
    , @NamedQuery(name = "Salaire.findByTotalRetenu", query = "SELECT s FROM Salaire s WHERE s.totalRetenu = :totalRetenu")
    , @NamedQuery(name = "Salaire.findByNet", query = "SELECT s FROM Salaire s WHERE s.net = :net")
    , @NamedQuery(name = "Salaire.findByNetImposable", query = "SELECT s FROM Salaire s WHERE s.netImposable = :netImposable")
    , @NamedQuery(name = "Salaire.findByAutreAvantage", query = "SELECT s FROM Salaire s WHERE s.autreAvantage = :autreAvantage")
    , @NamedQuery(name = "Salaire.findByNetAPayer", query = "SELECT s FROM Salaire s WHERE s.netAPayer = :netAPayer")
    , @NamedQuery(name = "Salaire.findByPeriode", query = "SELECT s FROM Salaire s WHERE s.periode = :periode")
    , @NamedQuery(name = "Salaire.findByDatepaiement", query = "SELECT s FROM Salaire s WHERE s.datepaiement = :datepaiement")
    , @NamedQuery(name = "Salaire.findByTypesalaire", query = "SELECT s FROM Salaire s WHERE s.typesalaire = :typesalaire")
    , @NamedQuery(name = "Salaire.findByEtat", query = "SELECT s FROM Salaire s WHERE s.etat = :etat")
    , @NamedQuery(name = "Salaire.findByMotif", query = "SELECT s FROM Salaire s WHERE s.motif = :motif")})
public class Salaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSalaire")
    private Integer idSalaire;
    @Size(max = 50)
    @Column(name = "numFicheDepaye")
    private String numFicheDepaye;
    @Size(max = 50)
    @Column(name = "nomEmployeur")
    private String nomEmployeur;
    @Size(max = 50)
    @Column(name = "adreseEmployeur")
    private String adreseEmployeur;
    @Size(max = 50)
    @Column(name = "periodeDebut")
    private String periodeDebut;
    @Size(max = 50)
    @Column(name = "periodeFin")
    private String periodeFin;
    @Column(name = "montantBrut")
    private BigInteger montantBrut;
    @Size(max = 50)
    @Column(name = "typeSalarie")
    private String typeSalarie;
    @Column(name = "retenu")
    private BigInteger retenu;
    @Column(name = "base")
    private BigInteger base;
    @Column(name = "tauxEmploye")
    private BigInteger tauxEmploye;
    @Column(name = "cotisation")
    private BigInteger cotisation;
    @Column(name = "tauxSalarie")
    private BigInteger tauxSalarie;
    @Column(name = "valeurRetenu")
    private BigInteger valeurRetenu;
    @Column(name = "totalRetenu")
    private BigInteger totalRetenu;
    @Column(name = "net")
    private BigInteger net;
    @Column(name = "netImposable")
    private BigInteger netImposable;
    @Column(name = "autreAvantage")
    private BigInteger autreAvantage;
    @Size(max = 50)
    @Column(name = "netAPayer")
    private String netAPayer;
    @Size(max = 50)
    @Column(name = "periode")
    private String periode;
    @Size(max = 50)
    @Column(name = "datepaiement")
    private String datepaiement;
    @Size(max = 50)
    @Column(name = "typesalaire")
    private String typesalaire;
    @Size(max = 50)
    @Column(name = "etat")
    private String etat;
    @Size(max = 250)
    @Column(name = "motif")
    private String motif;
    @OneToMany(mappedBy = "idSalaire")
    private List<Notification> notificationList;
    @JoinColumn(name = "idUtilisateur", referencedColumnName = "idUtilisateur")
    @ManyToOne
    private Utilisateur idUtilisateur;
    @OneToMany(mappedBy = "idSalaire")
    private List<Rembourssement> rembourssementList;

    public Salaire() {
    }

    public Salaire(Integer idSalaire) {
        this.idSalaire = idSalaire;
    }

    public Integer getIdSalaire() {
        return idSalaire;
    }

    public void setIdSalaire(Integer idSalaire) {
        this.idSalaire = idSalaire;
    }

    public String getNumFicheDepaye() {
        return numFicheDepaye;
    }

    public void setNumFicheDepaye(String numFicheDepaye) {
        this.numFicheDepaye = numFicheDepaye;
    }

    public String getNomEmployeur() {
        return nomEmployeur;
    }

    public void setNomEmployeur(String nomEmployeur) {
        this.nomEmployeur = nomEmployeur;
    }

    public String getAdreseEmployeur() {
        return adreseEmployeur;
    }

    public void setAdreseEmployeur(String adreseEmployeur) {
        this.adreseEmployeur = adreseEmployeur;
    }

    public String getPeriodeDebut() {
        return periodeDebut;
    }

    public void setPeriodeDebut(String periodeDebut) {
        this.periodeDebut = periodeDebut;
    }

    public String getPeriodeFin() {
        return periodeFin;
    }

    public void setPeriodeFin(String periodeFin) {
        this.periodeFin = periodeFin;
    }

    public BigInteger getMontantBrut() {
        return montantBrut;
    }

    public void setMontantBrut(BigInteger montantBrut) {
        this.montantBrut = montantBrut;
    }

    public String getTypeSalarie() {
        return typeSalarie;
    }

    public void setTypeSalarie(String typeSalarie) {
        this.typeSalarie = typeSalarie;
    }

    public BigInteger getRetenu() {
        return retenu;
    }

    public void setRetenu(BigInteger retenu) {
        this.retenu = retenu;
    }

    public BigInteger getBase() {
        return base;
    }

    public void setBase(BigInteger base) {
        this.base = base;
    }

    public BigInteger getTauxEmploye() {
        return tauxEmploye;
    }

    public void setTauxEmploye(BigInteger tauxEmploye) {
        this.tauxEmploye = tauxEmploye;
    }

    public BigInteger getCotisation() {
        return cotisation;
    }

    public void setCotisation(BigInteger cotisation) {
        this.cotisation = cotisation;
    }

    public BigInteger getTauxSalarie() {
        return tauxSalarie;
    }

    public void setTauxSalarie(BigInteger tauxSalarie) {
        this.tauxSalarie = tauxSalarie;
    }

    public BigInteger getValeurRetenu() {
        return valeurRetenu;
    }

    public void setValeurRetenu(BigInteger valeurRetenu) {
        this.valeurRetenu = valeurRetenu;
    }

    public BigInteger getTotalRetenu() {
        return totalRetenu;
    }

    public void setTotalRetenu(BigInteger totalRetenu) {
        this.totalRetenu = totalRetenu;
    }

    public BigInteger getNet() {
        return net;
    }

    public void setNet(BigInteger net) {
        this.net = net;
    }

    public BigInteger getNetImposable() {
        return netImposable;
    }

    public void setNetImposable(BigInteger netImposable) {
        this.netImposable = netImposable;
    }

    public BigInteger getAutreAvantage() {
        return autreAvantage;
    }

    public void setAutreAvantage(BigInteger autreAvantage) {
        this.autreAvantage = autreAvantage;
    }

    public String getNetAPayer() {
        return netAPayer;
    }

    public void setNetAPayer(String netAPayer) {
        this.netAPayer = netAPayer;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public String getDatepaiement() {
        return datepaiement;
    }

    public void setDatepaiement(String datepaiement) {
        this.datepaiement = datepaiement;
    }

    public String getTypesalaire() {
        return typesalaire;
    }

    public void setTypesalaire(String typesalaire) {
        this.typesalaire = typesalaire;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    @XmlTransient
    @JsonIgnore
    public List<Notification> getNotificationList() {
        return notificationList;
    }

    public void setNotificationList(List<Notification> notificationList) {
        this.notificationList = notificationList;
    }

    public Utilisateur getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Utilisateur idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    @XmlTransient
    @JsonIgnore
    public List<Rembourssement> getRembourssementList() {
        return rembourssementList;
    }

    public void setRembourssementList(List<Rembourssement> rembourssementList) {
        this.rembourssementList = rembourssementList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSalaire != null ? idSalaire.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Salaire)) {
            return false;
        }
        Salaire other = (Salaire) object;
        if ((this.idSalaire == null && other.idSalaire != null) || (this.idSalaire != null && !this.idSalaire.equals(other.idSalaire))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Salaire[ idSalaire=" + idSalaire + " ]";
    }
    
}
