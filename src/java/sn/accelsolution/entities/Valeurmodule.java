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
@Table(name = "valeurmodule")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Valeurmodule.findAll", query = "SELECT v FROM Valeurmodule v")
    , @NamedQuery(name = "Valeurmodule.findByIdValeurModule", query = "SELECT v FROM Valeurmodule v WHERE v.idValeurModule = :idValeurModule")
    , @NamedQuery(name = "Valeurmodule.findByLibelleValeurModule", query = "SELECT v FROM Valeurmodule v WHERE v.libelleValeurModule = :libelleValeurModule")})
public class Valeurmodule implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idValeurModule")
    private Integer idValeurModule;
    @Size(max = 250)
    @Column(name = "libelleValeurModule")
    private String libelleValeurModule;

    public Valeurmodule() {
    }

    public Valeurmodule(Integer idValeurModule) {
        this.idValeurModule = idValeurModule;
    }

    public Integer getIdValeurModule() {
        return idValeurModule;
    }

    public void setIdValeurModule(Integer idValeurModule) {
        this.idValeurModule = idValeurModule;
    }

    public String getLibelleValeurModule() {
        return libelleValeurModule;
    }

    public void setLibelleValeurModule(String libelleValeurModule) {
        this.libelleValeurModule = libelleValeurModule;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idValeurModule != null ? idValeurModule.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Valeurmodule)) {
            return false;
        }
        Valeurmodule other = (Valeurmodule) object;
        if ((this.idValeurModule == null && other.idValeurModule != null) || (this.idValeurModule != null && !this.idValeurModule.equals(other.idValeurModule))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Valeurmodule[ idValeurModule=" + idValeurModule + " ]";
    }
    
}
