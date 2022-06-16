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
@Table(name = "profil")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profil.findAll", query = "SELECT p FROM Profil p")
    , @NamedQuery(name = "Profil.findByIdProfil", query = "SELECT p FROM Profil p WHERE p.idProfil = :idProfil")
    , @NamedQuery(name = "Profil.findByLevel", query = "SELECT p FROM Profil p WHERE p.level = :level")
    , @NamedQuery(name = "Profil.findByLibelle", query = "SELECT p FROM Profil p WHERE p.libelle = :libelle")})
public class Profil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProfil")
    private Integer idProfil;
    @Size(max = 255)
    @Column(name = "level")
    private String level;
    @Size(max = 255)
    @Column(name = "libelle")
    private String libelle;

    public Profil() {
    }

    public Profil(Integer idProfil) {
        this.idProfil = idProfil;
    }

    public Integer getIdProfil() {
        return idProfil;
    }

    public void setIdProfil(Integer idProfil) {
        this.idProfil = idProfil;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProfil != null ? idProfil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profil)) {
            return false;
        }
        Profil other = (Profil) object;
        if ((this.idProfil == null && other.idProfil != null) || (this.idProfil != null && !this.idProfil.equals(other.idProfil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Profil[ idProfil=" + idProfil + " ]";
    }
    
}
