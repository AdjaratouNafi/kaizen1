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
@Table(name = "actionmenu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actionmenu.findAll", query = "SELECT a FROM Actionmenu a")
    , @NamedQuery(name = "Actionmenu.findByIdActionmenu", query = "SELECT a FROM Actionmenu a WHERE a.idActionmenu = :idActionmenu")
    , @NamedQuery(name = "Actionmenu.findByLibelleaction", query = "SELECT a FROM Actionmenu a WHERE a.libelleaction = :libelleaction")})
public class Actionmenu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idActionmenu")
    private Integer idActionmenu;
    @Size(max = 250)
    @Column(name = "libelleaction")
    private String libelleaction;
    @JoinColumn(name = "idMenu", referencedColumnName = "idMenu")
    @ManyToOne
    private Menu idMenu;

    public Actionmenu() {
    }

    public Actionmenu(Integer idActionmenu) {
        this.idActionmenu = idActionmenu;
    }

    public Integer getIdActionmenu() {
        return idActionmenu;
    }

    public void setIdActionmenu(Integer idActionmenu) {
        this.idActionmenu = idActionmenu;
    }

    public String getLibelleaction() {
        return libelleaction;
    }

    public void setLibelleaction(String libelleaction) {
        this.libelleaction = libelleaction;
    }

    public Menu getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Menu idMenu) {
        this.idMenu = idMenu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActionmenu != null ? idActionmenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actionmenu)) {
            return false;
        }
        Actionmenu other = (Actionmenu) object;
        if ((this.idActionmenu == null && other.idActionmenu != null) || (this.idActionmenu != null && !this.idActionmenu.equals(other.idActionmenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Actionmenu[ idActionmenu=" + idActionmenu + " ]";
    }
    
}
