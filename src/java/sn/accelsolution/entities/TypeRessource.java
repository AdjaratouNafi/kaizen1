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
@Table(name = "type_ressource")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeRessource.findAll", query = "SELECT t FROM TypeRessource t")
    , @NamedQuery(name = "TypeRessource.findByIdTypeRessource", query = "SELECT t FROM TypeRessource t WHERE t.idTypeRessource = :idTypeRessource")
    , @NamedQuery(name = "TypeRessource.findByLibelleTypeRessource", query = "SELECT t FROM TypeRessource t WHERE t.libelleTypeRessource = :libelleTypeRessource")})
public class TypeRessource implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTypeRessource")
    private Integer idTypeRessource;
    @Size(max = 50)
    @Column(name = "libelleTypeRessource")
    private String libelleTypeRessource;
    @OneToMany(mappedBy = "idTypeRessource")
    private List<Ressource> ressourceList;

    public TypeRessource() {
    }

    public TypeRessource(Integer idTypeRessource) {
        this.idTypeRessource = idTypeRessource;
    }

    public Integer getIdTypeRessource() {
        return idTypeRessource;
    }

    public void setIdTypeRessource(Integer idTypeRessource) {
        this.idTypeRessource = idTypeRessource;
    }

    public String getLibelleTypeRessource() {
        return libelleTypeRessource;
    }

    public void setLibelleTypeRessource(String libelleTypeRessource) {
        this.libelleTypeRessource = libelleTypeRessource;
    }

    @XmlTransient
    @JsonIgnore
    public List<Ressource> getRessourceList() {
        return ressourceList;
    }

    public void setRessourceList(List<Ressource> ressourceList) {
        this.ressourceList = ressourceList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTypeRessource != null ? idTypeRessource.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeRessource)) {
            return false;
        }
        TypeRessource other = (TypeRessource) object;
        if ((this.idTypeRessource == null && other.idTypeRessource != null) || (this.idTypeRessource != null && !this.idTypeRessource.equals(other.idTypeRessource))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.TypeRessource[ idTypeRessource=" + idTypeRessource + " ]";
    }
    
}
