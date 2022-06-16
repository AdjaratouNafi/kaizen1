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
@Table(name = "decomptep")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Decomptep.findAll", query = "SELECT d FROM Decomptep d")
    , @NamedQuery(name = "Decomptep.findByIdDecomptep", query = "SELECT d FROM Decomptep d WHERE d.idDecomptep = :idDecomptep")
    , @NamedQuery(name = "Decomptep.findByNumeroDecomptep", query = "SELECT d FROM Decomptep d WHERE d.numeroDecomptep = :numeroDecomptep")
    , @NamedQuery(name = "Decomptep.findByDateDecomptep", query = "SELECT d FROM Decomptep d WHERE d.dateDecomptep = :dateDecomptep")
    , @NamedQuery(name = "Decomptep.findByMontantlettre", query = "SELECT d FROM Decomptep d WHERE d.montantlettre = :montantlettre")
    , @NamedQuery(name = "Decomptep.findByEtat", query = "SELECT d FROM Decomptep d WHERE d.etat = :etat")
    , @NamedQuery(name = "Decomptep.findByMotif", query = "SELECT d FROM Decomptep d WHERE d.motif = :motif")})
public class Decomptep implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDecomptep")
    private Integer idDecomptep;
    @Size(max = 50)
    @Column(name = "numeroDecomptep")
    private String numeroDecomptep;
    @Size(max = 255)
    @Column(name = "dateDecomptep")
    private String dateDecomptep;
    @Size(max = 255)
    @Column(name = "montantlettre")
    private String montantlettre;
    @Size(max = 250)
    @Column(name = "etat")
    private String etat;
    @Size(max = 250)
    @Column(name = "motif")
    private String motif;
    @OneToMany(mappedBy = "idDecomptep")
    private List<Notification> notificationList;
    @JoinColumn(name = "idNewfacture", referencedColumnName = "idNewfacture")
    @ManyToOne
    private Newfacture idNewfacture;
    @JoinColumn(name = "idUtilisateur", referencedColumnName = "idUtilisateur")
    @ManyToOne
    private Utilisateur idUtilisateur;
    @OneToMany(mappedBy = "idDecomptep")
    private List<Detailledecomptep> detailledecomptepList;

    public Decomptep() {
    }

    public Decomptep(Integer idDecomptep) {
        this.idDecomptep = idDecomptep;
    }

    public Integer getIdDecomptep() {
        return idDecomptep;
    }

    public void setIdDecomptep(Integer idDecomptep) {
        this.idDecomptep = idDecomptep;
    }

    public String getNumeroDecomptep() {
        return numeroDecomptep;
    }

    public void setNumeroDecomptep(String numeroDecomptep) {
        this.numeroDecomptep = numeroDecomptep;
    }

    public String getDateDecomptep() {
        return dateDecomptep;
    }

    public void setDateDecomptep(String dateDecomptep) {
        this.dateDecomptep = dateDecomptep;
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

    @XmlTransient
    @JsonIgnore
    public List<Notification> getNotificationList() {
        return notificationList;
    }

    public void setNotificationList(List<Notification> notificationList) {
        this.notificationList = notificationList;
    }

    public Newfacture getIdNewfacture() {
        return idNewfacture;
    }

    public void setIdNewfacture(Newfacture idNewfacture) {
        this.idNewfacture = idNewfacture;
    }

    public Utilisateur getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Utilisateur idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    @XmlTransient
    @JsonIgnore
    public List<Detailledecomptep> getDetailledecomptepList() {
        return detailledecomptepList;
    }

    public void setDetailledecomptepList(List<Detailledecomptep> detailledecomptepList) {
        this.detailledecomptepList = detailledecomptepList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDecomptep != null ? idDecomptep.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Decomptep)) {
            return false;
        }
        Decomptep other = (Decomptep) object;
        if ((this.idDecomptep == null && other.idDecomptep != null) || (this.idDecomptep != null && !this.idDecomptep.equals(other.idDecomptep))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Decomptep[ idDecomptep=" + idDecomptep + " ]";
    }
    
}
