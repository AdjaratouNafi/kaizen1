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
@Table(name = "type_contrat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeContrat.findAll", query = "SELECT t FROM TypeContrat t")
    , @NamedQuery(name = "TypeContrat.findByIdTypeContrat", query = "SELECT t FROM TypeContrat t WHERE t.idTypeContrat = :idTypeContrat")
    , @NamedQuery(name = "TypeContrat.findByLibelleTypeContrat", query = "SELECT t FROM TypeContrat t WHERE t.libelleTypeContrat = :libelleTypeContrat")
    , @NamedQuery(name = "TypeContrat.findByCategorie", query = "SELECT t FROM TypeContrat t WHERE t.categorie = :categorie")})
public class TypeContrat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTypeContrat")
    private Integer idTypeContrat;
    @Size(max = 50)
    @Column(name = "libelleTypeContrat")
    private String libelleTypeContrat;
    @Size(max = 50)
    @Column(name = "categorie")
    private String categorie;
    @OneToMany(mappedBy = "idTypeContrat")
    private List<Contrat> contratList;

    public TypeContrat() {
    }

    public TypeContrat(Integer idTypeContrat) {
        this.idTypeContrat = idTypeContrat;
    }

    public Integer getIdTypeContrat() {
        return idTypeContrat;
    }

    public void setIdTypeContrat(Integer idTypeContrat) {
        this.idTypeContrat = idTypeContrat;
    }

    public String getLibelleTypeContrat() {
        return libelleTypeContrat;
    }

    public void setLibelleTypeContrat(String libelleTypeContrat) {
        this.libelleTypeContrat = libelleTypeContrat;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @XmlTransient
    @JsonIgnore
    public List<Contrat> getContratList() {
        return contratList;
    }

    public void setContratList(List<Contrat> contratList) {
        this.contratList = contratList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTypeContrat != null ? idTypeContrat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeContrat)) {
            return false;
        }
        TypeContrat other = (TypeContrat) object;
        if ((this.idTypeContrat == null && other.idTypeContrat != null) || (this.idTypeContrat != null && !this.idTypeContrat.equals(other.idTypeContrat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.TypeContrat[ idTypeContrat=" + idTypeContrat + " ]";
    }
    
}
