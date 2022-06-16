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
@Table(name = "nivodeboursser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nivodeboursser.findAll", query = "SELECT n FROM Nivodeboursser n")
    , @NamedQuery(name = "Nivodeboursser.findByIdNivodeboursser", query = "SELECT n FROM Nivodeboursser n WHERE n.idNivodeboursser = :idNivodeboursser")
    , @NamedQuery(name = "Nivodeboursser.findByLibellenivodeboursser", query = "SELECT n FROM Nivodeboursser n WHERE n.libellenivodeboursser = :libellenivodeboursser")})
public class Nivodeboursser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idNivodeboursser")
    private Integer idNivodeboursser;
    @Size(max = 255)
    @Column(name = "libellenivodeboursser")
    private String libellenivodeboursser;
    @OneToMany(mappedBy = "idNivodeboursser")
    private List<Deboursser> deboursserList;

    public Nivodeboursser() {
    }

    public Nivodeboursser(Integer idNivodeboursser) {
        this.idNivodeboursser = idNivodeboursser;
    }

    public Integer getIdNivodeboursser() {
        return idNivodeboursser;
    }

    public void setIdNivodeboursser(Integer idNivodeboursser) {
        this.idNivodeboursser = idNivodeboursser;
    }

    public String getLibellenivodeboursser() {
        return libellenivodeboursser;
    }

    public void setLibellenivodeboursser(String libellenivodeboursser) {
        this.libellenivodeboursser = libellenivodeboursser;
    }

    @XmlTransient
    @JsonIgnore
    public List<Deboursser> getDeboursserList() {
        return deboursserList;
    }

    public void setDeboursserList(List<Deboursser> deboursserList) {
        this.deboursserList = deboursserList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNivodeboursser != null ? idNivodeboursser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nivodeboursser)) {
            return false;
        }
        Nivodeboursser other = (Nivodeboursser) object;
        if ((this.idNivodeboursser == null && other.idNivodeboursser != null) || (this.idNivodeboursser != null && !this.idNivodeboursser.equals(other.idNivodeboursser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Nivodeboursser[ idNivodeboursser=" + idNivodeboursser + " ]";
    }
    
}
