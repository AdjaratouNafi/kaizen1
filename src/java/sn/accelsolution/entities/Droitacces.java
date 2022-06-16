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
@Table(name = "droitacces")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Droitacces.findAll", query = "SELECT d FROM Droitacces d")
    , @NamedQuery(name = "Droitacces.findByIdPrivilege", query = "SELECT d FROM Droitacces d WHERE d.idPrivilege = :idPrivilege")
    , @NamedQuery(name = "Droitacces.findByModule", query = "SELECT d FROM Droitacces d WHERE d.module = :module")
    , @NamedQuery(name = "Droitacces.findByNiveau", query = "SELECT d FROM Droitacces d WHERE d.niveau = :niveau")})
public class Droitacces implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPrivilege")
    private Integer idPrivilege;
    @Size(max = 250)
    @Column(name = "module")
    private String module;
    @Column(name = "niveau")
    private Integer niveau;
    @JoinColumn(name = "idRole", referencedColumnName = "idRole")
    @ManyToOne
    private Role idRole;
    @JoinColumn(name = "idUtilisateur", referencedColumnName = "idUtilisateur")
    @ManyToOne
    private Utilisateur idUtilisateur;
    @OneToMany(mappedBy = "idPrivilege")
    private List<Menu> menuList;

    public Droitacces() {
    }

    public Droitacces(Integer idPrivilege) {
        this.idPrivilege = idPrivilege;
    }

    public Integer getIdPrivilege() {
        return idPrivilege;
    }

    public void setIdPrivilege(Integer idPrivilege) {
        this.idPrivilege = idPrivilege;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public Integer getNiveau() {
        return niveau;
    }

    public void setNiveau(Integer niveau) {
        this.niveau = niveau;
    }

    public Role getIdRole() {
        return idRole;
    }

    public void setIdRole(Role idRole) {
        this.idRole = idRole;
    }

    public Utilisateur getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Utilisateur idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    @XmlTransient
    @JsonIgnore
    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPrivilege != null ? idPrivilege.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Droitacces)) {
            return false;
        }
        Droitacces other = (Droitacces) object;
        if ((this.idPrivilege == null && other.idPrivilege != null) || (this.idPrivilege != null && !this.idPrivilege.equals(other.idPrivilege))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Droitacces[ idPrivilege=" + idPrivilege + " ]";
    }
    
}
