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
@Table(name = "valeuraction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Valeuraction.findAll", query = "SELECT v FROM Valeuraction v")
    , @NamedQuery(name = "Valeuraction.findByIdValeurAction", query = "SELECT v FROM Valeuraction v WHERE v.idValeurAction = :idValeurAction")
    , @NamedQuery(name = "Valeuraction.findByLibelleValeurAction", query = "SELECT v FROM Valeuraction v WHERE v.libelleValeurAction = :libelleValeurAction")})
public class Valeuraction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idValeurAction")
    private Integer idValeurAction;
    @Size(max = 250)
    @Column(name = "libelleValeurAction")
    private String libelleValeurAction;

    public Valeuraction() {
    }

    public Valeuraction(Integer idValeurAction) {
        this.idValeurAction = idValeurAction;
    }

    public Integer getIdValeurAction() {
        return idValeurAction;
    }

    public void setIdValeurAction(Integer idValeurAction) {
        this.idValeurAction = idValeurAction;
    }

    public String getLibelleValeurAction() {
        return libelleValeurAction;
    }

    public void setLibelleValeurAction(String libelleValeurAction) {
        this.libelleValeurAction = libelleValeurAction;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idValeurAction != null ? idValeurAction.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Valeuraction)) {
            return false;
        }
        Valeuraction other = (Valeuraction) object;
        if ((this.idValeurAction == null && other.idValeurAction != null) || (this.idValeurAction != null && !this.idValeurAction.equals(other.idValeurAction))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Valeuraction[ idValeurAction=" + idValeurAction + " ]";
    }
    
}
