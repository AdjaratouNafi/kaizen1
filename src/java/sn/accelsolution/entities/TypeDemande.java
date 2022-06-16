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
@Table(name = "type_demande")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeDemande.findAll", query = "SELECT t FROM TypeDemande t")
    , @NamedQuery(name = "TypeDemande.findByIdTypeDemande", query = "SELECT t FROM TypeDemande t WHERE t.idTypeDemande = :idTypeDemande")
    , @NamedQuery(name = "TypeDemande.findByLibelleTypeDemande", query = "SELECT t FROM TypeDemande t WHERE t.libelleTypeDemande = :libelleTypeDemande")})
public class TypeDemande implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTypeDemande")
    private Integer idTypeDemande;
    @Size(max = 50)
    @Column(name = "libelleTypeDemande")
    private String libelleTypeDemande;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTypeDemande")
    private List<Demande> demandeList;

    public TypeDemande() {
    }

    public TypeDemande(Integer idTypeDemande) {
        this.idTypeDemande = idTypeDemande;
    }

    public Integer getIdTypeDemande() {
        return idTypeDemande;
    }

    public void setIdTypeDemande(Integer idTypeDemande) {
        this.idTypeDemande = idTypeDemande;
    }

    public String getLibelleTypeDemande() {
        return libelleTypeDemande;
    }

    public void setLibelleTypeDemande(String libelleTypeDemande) {
        this.libelleTypeDemande = libelleTypeDemande;
    }

    @XmlTransient
    @JsonIgnore
    public List<Demande> getDemandeList() {
        return demandeList;
    }

    public void setDemandeList(List<Demande> demandeList) {
        this.demandeList = demandeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTypeDemande != null ? idTypeDemande.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeDemande)) {
            return false;
        }
        TypeDemande other = (TypeDemande) object;
        if ((this.idTypeDemande == null && other.idTypeDemande != null) || (this.idTypeDemande != null && !this.idTypeDemande.equals(other.idTypeDemande))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.TypeDemande[ idTypeDemande=" + idTypeDemande + " ]";
    }
    
}
