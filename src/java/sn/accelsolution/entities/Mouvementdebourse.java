/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.entities;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DV7
 */
@Entity
@Table(name = "mouvementdebourse")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mouvementdebourse.findAll", query = "SELECT m FROM Mouvementdebourse m")
    , @NamedQuery(name = "Mouvementdebourse.findByIdSortiedeb", query = "SELECT m FROM Mouvementdebourse m WHERE m.idSortiedeb = :idSortiedeb")
    , @NamedQuery(name = "Mouvementdebourse.findByDatesortiedeb", query = "SELECT m FROM Mouvementdebourse m WHERE m.datesortiedeb = :datesortiedeb")
    , @NamedQuery(name = "Mouvementdebourse.findByQuantite", query = "SELECT m FROM Mouvementdebourse m WHERE m.quantite = :quantite")})
public class Mouvementdebourse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSortiedeb")
    private Integer idSortiedeb;
    @Size(max = 255)
    @Column(name = "datesortiedeb")
    private String datesortiedeb;
    @Column(name = "quantite")
    private Integer quantite;
    @JoinColumn(name = "idDeboursse", referencedColumnName = "idDeboursser")
    @ManyToOne
    private Deboursser idDeboursse;
    @JoinColumn(name = "idChantier", referencedColumnName = "idChantier")
    @ManyToOne
    private Chantier idChantier;

    public Mouvementdebourse() {
    }

    public Mouvementdebourse(Integer idSortiedeb) {
        this.idSortiedeb = idSortiedeb;
    }

    public Integer getIdSortiedeb() {
        return idSortiedeb;
    }

    public void setIdSortiedeb(Integer idSortiedeb) {
        this.idSortiedeb = idSortiedeb;
    }

    public String getDatesortiedeb() {
        return datesortiedeb;
    }

    public void setDatesortiedeb(String datesortiedeb) {
        this.datesortiedeb = datesortiedeb;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Deboursser getIdDeboursse() {
        return idDeboursse;
    }

    public void setIdDeboursse(Deboursser idDeboursse) {
        this.idDeboursse = idDeboursse;
    }

    public Chantier getIdChantier() {
        return idChantier;
    }

    public void setIdChantier(Chantier idChantier) {
        this.idChantier = idChantier;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSortiedeb != null ? idSortiedeb.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mouvementdebourse)) {
            return false;
        }
        Mouvementdebourse other = (Mouvementdebourse) object;
        if ((this.idSortiedeb == null && other.idSortiedeb != null) || (this.idSortiedeb != null && !this.idSortiedeb.equals(other.idSortiedeb))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Mouvementdebourse[ idSortiedeb=" + idSortiedeb + " ]";
    }
    
}
