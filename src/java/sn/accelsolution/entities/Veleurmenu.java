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
@Table(name = "veleurmenu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Veleurmenu.findAll", query = "SELECT v FROM Veleurmenu v")
    , @NamedQuery(name = "Veleurmenu.findByIdValeurMenu", query = "SELECT v FROM Veleurmenu v WHERE v.idValeurMenu = :idValeurMenu")
    , @NamedQuery(name = "Veleurmenu.findByLibelleValeurMenu", query = "SELECT v FROM Veleurmenu v WHERE v.libelleValeurMenu = :libelleValeurMenu")})
public class Veleurmenu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idValeurMenu")
    private Integer idValeurMenu;
    @Size(max = 250)
    @Column(name = "libelleValeurMenu")
    private String libelleValeurMenu;

    public Veleurmenu() {
    }

    public Veleurmenu(Integer idValeurMenu) {
        this.idValeurMenu = idValeurMenu;
    }

    public Integer getIdValeurMenu() {
        return idValeurMenu;
    }

    public void setIdValeurMenu(Integer idValeurMenu) {
        this.idValeurMenu = idValeurMenu;
    }

    public String getLibelleValeurMenu() {
        return libelleValeurMenu;
    }

    public void setLibelleValeurMenu(String libelleValeurMenu) {
        this.libelleValeurMenu = libelleValeurMenu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idValeurMenu != null ? idValeurMenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Veleurmenu)) {
            return false;
        }
        Veleurmenu other = (Veleurmenu) object;
        if ((this.idValeurMenu == null && other.idValeurMenu != null) || (this.idValeurMenu != null && !this.idValeurMenu.equals(other.idValeurMenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Veleurmenu[ idValeurMenu=" + idValeurMenu + " ]";
    }
    
}
