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
@Table(name = "devise")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Devise.findAll", query = "SELECT d FROM Devise d")
    , @NamedQuery(name = "Devise.findByIdDevise", query = "SELECT d FROM Devise d WHERE d.idDevise = :idDevise")
    , @NamedQuery(name = "Devise.findByLibelle", query = "SELECT d FROM Devise d WHERE d.libelle = :libelle")
    , @NamedQuery(name = "Devise.findBySymbole", query = "SELECT d FROM Devise d WHERE d.symbole = :symbole")})
public class Devise implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDevise")
    private Integer idDevise;
    @Size(max = 250)
    @Column(name = "libelle")
    private String libelle;
    @Size(max = 250)
    @Column(name = "symbole")
    private String symbole;
    @OneToMany(mappedBy = "idDevise")
    private List<Prix> prixList;
    @OneToMany(mappedBy = "idDevise")
    private List<Marche> marcheList;
    @OneToMany(mappedBy = "idDevise")
    private List<Devis> devisList;

    public Devise() {
    }

    public Devise(Integer idDevise) {
        this.idDevise = idDevise;
    }

    public Integer getIdDevise() {
        return idDevise;
    }

    public void setIdDevise(Integer idDevise) {
        this.idDevise = idDevise;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getSymbole() {
        return symbole;
    }

    public void setSymbole(String symbole) {
        this.symbole = symbole;
    }

    @XmlTransient
    @JsonIgnore
    public List<Prix> getPrixList() {
        return prixList;
    }

    public void setPrixList(List<Prix> prixList) {
        this.prixList = prixList;
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
    public List<Devis> getDevisList() {
        return devisList;
    }

    public void setDevisList(List<Devis> devisList) {
        this.devisList = devisList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDevise != null ? idDevise.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Devise)) {
            return false;
        }
        Devise other = (Devise) object;
        if ((this.idDevise == null && other.idDevise != null) || (this.idDevise != null && !this.idDevise.equals(other.idDevise))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Devise[ idDevise=" + idDevise + " ]";
    }
    
}
