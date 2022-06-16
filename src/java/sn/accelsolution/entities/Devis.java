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
@Table(name = "devis")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Devis.findAll", query = "SELECT d FROM Devis d")
    , @NamedQuery(name = "Devis.findByIdDevis", query = "SELECT d FROM Devis d WHERE d.idDevis = :idDevis")
    , @NamedQuery(name = "Devis.findByNumeroDevis", query = "SELECT d FROM Devis d WHERE d.numeroDevis = :numeroDevis")
    , @NamedQuery(name = "Devis.findByDateDevis", query = "SELECT d FROM Devis d WHERE d.dateDevis = :dateDevis")
    , @NamedQuery(name = "Devis.findByNommarche", query = "SELECT d FROM Devis d WHERE d.nommarche = :nommarche")
    , @NamedQuery(name = "Devis.findByMontantlettre", query = "SELECT d FROM Devis d WHERE d.montantlettre = :montantlettre")
    , @NamedQuery(name = "Devis.findByEtat", query = "SELECT d FROM Devis d WHERE d.etat = :etat")
    , @NamedQuery(name = "Devis.findByMotif", query = "SELECT d FROM Devis d WHERE d.motif = :motif")
    , @NamedQuery(name = "Devis.findByTerme", query = "SELECT d FROM Devis d WHERE d.terme = :terme")
    , @NamedQuery(name = "Devis.findByNbjours", query = "SELECT d FROM Devis d WHERE d.nbjours = :nbjours")
    , @NamedQuery(name = "Devis.findByCoef", query = "SELECT d FROM Devis d WHERE d.coef = :coef")})
public class Devis implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDevis")
    private Integer idDevis;
    @Size(max = 50)
    @Column(name = "numeroDevis")
    private String numeroDevis;
    @Size(max = 50)
    @Column(name = "dateDevis")
    private String dateDevis;
    @Size(max = 250)
    @Column(name = "nommarche")
    private String nommarche;
    @Size(max = 255)
    @Column(name = "montantlettre")
    private String montantlettre;
    @Size(max = 255)
    @Column(name = "etat")
    private String etat;
    @Size(max = 250)
    @Column(name = "motif")
    private String motif;
    @Size(max = 250)
    @Column(name = "terme")
    private String terme;
    @Size(max = 250)
    @Column(name = "nbjours")
    private String nbjours;
    @Size(max = 50)
    @Column(name = "coef")
    private String coef;
    @OneToMany(mappedBy = "idDevis")
    private List<Marche> marcheList;
    @OneToMany(mappedBy = "idDevis")
    private List<Notification> notificationList;
    @OneToMany(mappedBy = "idDevis")
    private List<Detailledevis> detailledevisList;
    @JoinColumn(name = "idClient", referencedColumnName = "idClient")
    @ManyToOne
    private Client idClient;
    @JoinColumn(name = "idDevise", referencedColumnName = "idDevise")
    @ManyToOne
    private Devise idDevise;
    @JoinColumn(name = "idMarche", referencedColumnName = "idMarche")
    @ManyToOne
    private Marche idMarche;
    @JoinColumn(name = "idUtilisateur", referencedColumnName = "idUtilisateur")
    @ManyToOne
    private Utilisateur idUtilisateur;
    @OneToMany(mappedBy = "idDevis")
    private List<Newfacture> newfactureList;

    public Devis() {
    }

    public Devis(Integer idDevis) {
        this.idDevis = idDevis;
    }

    public Integer getIdDevis() {
        return idDevis;
    }

    public void setIdDevis(Integer idDevis) {
        this.idDevis = idDevis;
    }

    public String getNumeroDevis() {
        return numeroDevis;
    }

    public void setNumeroDevis(String numeroDevis) {
        this.numeroDevis = numeroDevis;
    }

    public String getDateDevis() {
        return dateDevis;
    }

    public void setDateDevis(String dateDevis) {
        this.dateDevis = dateDevis;
    }

    public String getNommarche() {
        return nommarche;
    }

    public void setNommarche(String nommarche) {
        this.nommarche = nommarche;
    }

    public String getMontantlettre() {
        return montantlettre;
    }

    public void setMontantlettre(String montantlettre) {
        this.montantlettre = montantlettre;
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

    public String getTerme() {
        return terme;
    }

    public void setTerme(String terme) {
        this.terme = terme;
    }

    public String getNbjours() {
        return nbjours;
    }

    public void setNbjours(String nbjours) {
        this.nbjours = nbjours;
    }

    public String getCoef() {
        return coef;
    }

    public void setCoef(String coef) {
        this.coef = coef;
    }

    @XmlTransient
    @JsonIgnore
    public List<Marche> getMarcheList() {
        return marcheList;
    }

    public void setMarcheList(List<Marche> marcheList) {
        this.marcheList = marcheList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Notification> getNotificationList() {
        return notificationList;
    }

    public void setNotificationList(List<Notification> notificationList) {
        this.notificationList = notificationList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Detailledevis> getDetailledevisList() {
        return detailledevisList;
    }

    public void setDetailledevisList(List<Detailledevis> detailledevisList) {
        this.detailledevisList = detailledevisList;
    }

    public Client getIdClient() {
        return idClient;
    }

    public void setIdClient(Client idClient) {
        this.idClient = idClient;
    }

    public Devise getIdDevise() {
        return idDevise;
    }

    public void setIdDevise(Devise idDevise) {
        this.idDevise = idDevise;
    }

    public Marche getIdMarche() {
        return idMarche;
    }

    public void setIdMarche(Marche idMarche) {
        this.idMarche = idMarche;
    }

    public Utilisateur getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Utilisateur idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    @XmlTransient
    @JsonIgnore
    public List<Newfacture> getNewfactureList() {
        return newfactureList;
    }

    public void setNewfactureList(List<Newfacture> newfactureList) {
        this.newfactureList = newfactureList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDevis != null ? idDevis.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Devis)) {
            return false;
        }
        Devis other = (Devis) object;
        if ((this.idDevis == null && other.idDevis != null) || (this.idDevis != null && !this.idDevis.equals(other.idDevis))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Devis[ idDevis=" + idDevis + " ]";
    }
    
}
