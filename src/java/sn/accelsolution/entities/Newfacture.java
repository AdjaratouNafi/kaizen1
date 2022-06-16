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
@Table(name = "newfacture")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Newfacture.findAll", query = "SELECT n FROM Newfacture n")
    , @NamedQuery(name = "Newfacture.findByIdNewfacture", query = "SELECT n FROM Newfacture n WHERE n.idNewfacture = :idNewfacture")
    , @NamedQuery(name = "Newfacture.findByNumeroNewfacture", query = "SELECT n FROM Newfacture n WHERE n.numeroNewfacture = :numeroNewfacture")
    , @NamedQuery(name = "Newfacture.findByDateNewfacture", query = "SELECT n FROM Newfacture n WHERE n.dateNewfacture = :dateNewfacture")
    , @NamedQuery(name = "Newfacture.findByMintantlettreNewfacture", query = "SELECT n FROM Newfacture n WHERE n.mintantlettreNewfacture = :mintantlettreNewfacture")
    , @NamedQuery(name = "Newfacture.findByEtat", query = "SELECT n FROM Newfacture n WHERE n.etat = :etat")
    , @NamedQuery(name = "Newfacture.findByMotif", query = "SELECT n FROM Newfacture n WHERE n.motif = :motif")})
public class Newfacture implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idNewfacture")
    private Integer idNewfacture;
    @Size(max = 50)
    @Column(name = "numeroNewfacture")
    private String numeroNewfacture;
    @Size(max = 255)
    @Column(name = "dateNewfacture")
    private String dateNewfacture;
    @Size(max = 255)
    @Column(name = "mintantlettreNewfacture")
    private String mintantlettreNewfacture;
    @Size(max = 250)
    @Column(name = "etat")
    private String etat;
    @Size(max = 250)
    @Column(name = "motif")
    private String motif;
    @OneToMany(mappedBy = "idNewfacture")
    private List<Marche> marcheList;
    @OneToMany(mappedBy = "idNewfacture")
    private List<Notification> notificationList;
    @OneToMany(mappedBy = "idNewfacture")
    private List<Detaillenewfacture> detaillenewfactureList;
    @OneToMany(mappedBy = "idNewfacture")
    private List<Decomptep> decomptepList;
    @JoinColumn(name = "idDevis", referencedColumnName = "idDevis")
    @ManyToOne
    private Devis idDevis;
    @JoinColumn(name = "idMarche", referencedColumnName = "idMarche")
    @ManyToOne
    private Marche idMarche;
    @JoinColumn(name = "idUtilisateur", referencedColumnName = "idUtilisateur")
    @ManyToOne
    private Utilisateur idUtilisateur;

    public Newfacture() {
    }

    public Newfacture(Integer idNewfacture) {
        this.idNewfacture = idNewfacture;
    }

    public Integer getIdNewfacture() {
        return idNewfacture;
    }

    public void setIdNewfacture(Integer idNewfacture) {
        this.idNewfacture = idNewfacture;
    }

    public String getNumeroNewfacture() {
        return numeroNewfacture;
    }

    public void setNumeroNewfacture(String numeroNewfacture) {
        this.numeroNewfacture = numeroNewfacture;
    }

    public String getDateNewfacture() {
        return dateNewfacture;
    }

    public void setDateNewfacture(String dateNewfacture) {
        this.dateNewfacture = dateNewfacture;
    }

    public String getMintantlettreNewfacture() {
        return mintantlettreNewfacture;
    }

    public void setMintantlettreNewfacture(String mintantlettreNewfacture) {
        this.mintantlettreNewfacture = mintantlettreNewfacture;
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
    public List<Detaillenewfacture> getDetaillenewfactureList() {
        return detaillenewfactureList;
    }

    public void setDetaillenewfactureList(List<Detaillenewfacture> detaillenewfactureList) {
        this.detaillenewfactureList = detaillenewfactureList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Decomptep> getDecomptepList() {
        return decomptepList;
    }

    public void setDecomptepList(List<Decomptep> decomptepList) {
        this.decomptepList = decomptepList;
    }

    public Devis getIdDevis() {
        return idDevis;
    }

    public void setIdDevis(Devis idDevis) {
        this.idDevis = idDevis;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNewfacture != null ? idNewfacture.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Newfacture)) {
            return false;
        }
        Newfacture other = (Newfacture) object;
        if ((this.idNewfacture == null && other.idNewfacture != null) || (this.idNewfacture != null && !this.idNewfacture.equals(other.idNewfacture))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Newfacture[ idNewfacture=" + idNewfacture + " ]";
    }
    
}
