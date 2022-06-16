/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "indemnite")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Indemnite.findAll", query = "SELECT i FROM Indemnite i")
    , @NamedQuery(name = "Indemnite.findByIdIndemnite", query = "SELECT i FROM Indemnite i WHERE i.idIndemnite = :idIndemnite")
    , @NamedQuery(name = "Indemnite.findByLibelleIndemnite", query = "SELECT i FROM Indemnite i WHERE i.libelleIndemnite = :libelleIndemnite")
    , @NamedQuery(name = "Indemnite.findByDateCreationIndemnite", query = "SELECT i FROM Indemnite i WHERE i.dateCreationIndemnite = :dateCreationIndemnite")
    , @NamedQuery(name = "Indemnite.findByFrais", query = "SELECT i FROM Indemnite i WHERE i.frais = :frais")
    , @NamedQuery(name = "Indemnite.findByEffet", query = "SELECT i FROM Indemnite i WHERE i.effet = :effet")
    , @NamedQuery(name = "Indemnite.findByEtatIndemnite", query = "SELECT i FROM Indemnite i WHERE i.etatIndemnite = :etatIndemnite")})
public class Indemnite implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idIndemnite")
    private Integer idIndemnite;
    @Size(max = 50)
    @Column(name = "libelleIndemnite")
    private String libelleIndemnite;
    @Size(max = 50)
    @Column(name = "dateCreationIndemnite")
    private String dateCreationIndemnite;
    @Size(max = 50)
    @Column(name = "frais")
    private String frais;
    @Size(max = 50)
    @Column(name = "effet")
    private String effet;
    @Size(max = 50)
    @Column(name = "etatIndemnite")
    private String etatIndemnite;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idIndemnite")
    private List<UserIndemnite> userIndemniteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idIndemnite")
    private List<Demande> demandeList;
    @JoinColumn(name = "idUtilisateur", referencedColumnName = "idUtilisateur")
    @ManyToOne
    private Utilisateur idUtilisateur;

    public Indemnite() {
    }

    public Indemnite(Integer idIndemnite) {
        this.idIndemnite = idIndemnite;
    }

    public Integer getIdIndemnite() {
        return idIndemnite;
    }

    public void setIdIndemnite(Integer idIndemnite) {
        this.idIndemnite = idIndemnite;
    }

    public String getLibelleIndemnite() {
        return libelleIndemnite;
    }

    public void setLibelleIndemnite(String libelleIndemnite) {
        this.libelleIndemnite = libelleIndemnite;
    }

    public String getDateCreationIndemnite() {
        return dateCreationIndemnite;
    }

    public void setDateCreationIndemnite(String dateCreationIndemnite) {
        this.dateCreationIndemnite = dateCreationIndemnite;
    }

    public String getFrais() {
        return frais;
    }

    public void setFrais(String frais) {
        this.frais = frais;
    }

    public String getEffet() {
        return effet;
    }

    public void setEffet(String effet) {
        this.effet = effet;
    }

    public String getEtatIndemnite() {
        return etatIndemnite;
    }

    public void setEtatIndemnite(String etatIndemnite) {
        this.etatIndemnite = etatIndemnite;
    }

    @XmlTransient
    @JsonIgnore
    public List<UserIndemnite> getUserIndemniteList() {
        return userIndemniteList;
    }

    public void setUserIndemniteList(List<UserIndemnite> userIndemniteList) {
        this.userIndemniteList = userIndemniteList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Demande> getDemandeList() {
        return demandeList;
    }

    public void setDemandeList(List<Demande> demandeList) {
        this.demandeList = demandeList;
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
        hash += (idIndemnite != null ? idIndemnite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Indemnite)) {
            return false;
        }
        Indemnite other = (Indemnite) object;
        if ((this.idIndemnite == null && other.idIndemnite != null) || (this.idIndemnite != null && !this.idIndemnite.equals(other.idIndemnite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Indemnite[ idIndemnite=" + idIndemnite + " ]";
    }
    
}
