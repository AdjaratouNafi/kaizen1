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
@Table(name = "pays")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pays.findAll", query = "SELECT p FROM Pays p")
    , @NamedQuery(name = "Pays.findByIdPays", query = "SELECT p FROM Pays p WHERE p.idPays = :idPays")
    , @NamedQuery(name = "Pays.findByNomPays", query = "SELECT p FROM Pays p WHERE p.nomPays = :nomPays")})
public class Pays implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPays")
    private Integer idPays;
    @Size(max = 50)
    @Column(name = "nomPays")
    private String nomPays;
    @OneToMany(mappedBy = "idPays")
    private List<Fournisseur> fournisseurList;
    @OneToMany(mappedBy = "idPays")
    private List<Ville> villeList;

    public Pays() {
    }

    public Pays(Integer idPays) {
        this.idPays = idPays;
    }

    public Integer getIdPays() {
        return idPays;
    }

    public void setIdPays(Integer idPays) {
        this.idPays = idPays;
    }

    public String getNomPays() {
        return nomPays;
    }

    public void setNomPays(String nomPays) {
        this.nomPays = nomPays;
    }

    @XmlTransient
    @JsonIgnore
    public List<Fournisseur> getFournisseurList() {
        return fournisseurList;
    }

    public void setFournisseurList(List<Fournisseur> fournisseurList) {
        this.fournisseurList = fournisseurList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Ville> getVilleList() {
        return villeList;
    }

    public void setVilleList(List<Ville> villeList) {
        this.villeList = villeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPays != null ? idPays.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pays)) {
            return false;
        }
        Pays other = (Pays) object;
        if ((this.idPays == null && other.idPays != null) || (this.idPays != null && !this.idPays.equals(other.idPays))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Pays[ idPays=" + idPays + " ]";
    }
    
}
