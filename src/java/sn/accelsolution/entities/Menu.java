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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "menu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Menu.findAll", query = "SELECT m FROM Menu m")
    , @NamedQuery(name = "Menu.findByIdMenu", query = "SELECT m FROM Menu m WHERE m.idMenu = :idMenu")
    , @NamedQuery(name = "Menu.findByLibelemenu", query = "SELECT m FROM Menu m WHERE m.libelemenu = :libelemenu")})
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMenu")
    private Integer idMenu;
    @Size(max = 250)
    @Column(name = "libelemenu")
    private String libelemenu;
    @JoinColumn(name = "idPrivilege", referencedColumnName = "idPrivilege")
    @ManyToOne
    private Droitacces idPrivilege;
    @OneToMany(mappedBy = "idMenu")
    private List<Actionmenu> actionmenuList;

    public Menu() {
    }

    public Menu(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public Integer getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public String getLibelemenu() {
        return libelemenu;
    }

    public void setLibelemenu(String libelemenu) {
        this.libelemenu = libelemenu;
    }

    public Droitacces getIdPrivilege() {
        return idPrivilege;
    }

    public void setIdPrivilege(Droitacces idPrivilege) {
        this.idPrivilege = idPrivilege;
    }

    @XmlTransient
    @JsonIgnore
    public List<Actionmenu> getActionmenuList() {
        return actionmenuList;
    }

    public void setActionmenuList(List<Actionmenu> actionmenuList) {
        this.actionmenuList = actionmenuList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMenu != null ? idMenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Menu)) {
            return false;
        }
        Menu other = (Menu) object;
        if ((this.idMenu == null && other.idMenu != null) || (this.idMenu != null && !this.idMenu.equals(other.idMenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Menu[ idMenu=" + idMenu + " ]";
    }
    
}
