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
@Table(name = "assurance")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Assurance.findAll", query = "SELECT a FROM Assurance a")
    , @NamedQuery(name = "Assurance.findByIdAssurance", query = "SELECT a FROM Assurance a WHERE a.idAssurance = :idAssurance")
    , @NamedQuery(name = "Assurance.findByLibelleAssurance", query = "SELECT a FROM Assurance a WHERE a.libelleAssurance = :libelleAssurance")})
public class Assurance implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAssurance")
    private Integer idAssurance;
    @Size(max = 50)
    @Column(name = "libelleAssurance")
    private String libelleAssurance;
    @OneToMany(mappedBy = "idAssurance")
    private List<Assurer> assurerList;
    @OneToMany(mappedBy = "idAssurance")
    private List<DetailleAssurance> detailleAssuranceList;

    public Assurance() {
    }

    public Assurance(Integer idAssurance) {
        this.idAssurance = idAssurance;
    }

    public Integer getIdAssurance() {
        return idAssurance;
    }

    public void setIdAssurance(Integer idAssurance) {
        this.idAssurance = idAssurance;
    }

    public String getLibelleAssurance() {
        return libelleAssurance;
    }

    public void setLibelleAssurance(String libelleAssurance) {
        this.libelleAssurance = libelleAssurance;
    }

    @XmlTransient
    @JsonIgnore
    public List<Assurer> getAssurerList() {
        return assurerList;
    }

    public void setAssurerList(List<Assurer> assurerList) {
        this.assurerList = assurerList;
    }

    @XmlTransient
    @JsonIgnore
    public List<DetailleAssurance> getDetailleAssuranceList() {
        return detailleAssuranceList;
    }

    public void setDetailleAssuranceList(List<DetailleAssurance> detailleAssuranceList) {
        this.detailleAssuranceList = detailleAssuranceList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAssurance != null ? idAssurance.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Assurance)) {
            return false;
        }
        Assurance other = (Assurance) object;
        if ((this.idAssurance == null && other.idAssurance != null) || (this.idAssurance != null && !this.idAssurance.equals(other.idAssurance))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Assurance[ idAssurance=" + idAssurance + " ]";
    }
    
}
