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
@Table(name = "actionactif")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actionactif.findAll", query = "SELECT a FROM Actionactif a")
    , @NamedQuery(name = "Actionactif.findByIdActionactif", query = "SELECT a FROM Actionactif a WHERE a.idActionactif = :idActionactif")
    , @NamedQuery(name = "Actionactif.findByLibelle", query = "SELECT a FROM Actionactif a WHERE a.libelle = :libelle")})
public class Actionactif implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idActionactif")
    private Integer idActionactif;
    @Size(max = 50)
    @Column(name = "libelle")
    private String libelle;

    public Actionactif() {
    }

    public Actionactif(Integer idActionactif) {
        this.idActionactif = idActionactif;
    }

    public Integer getIdActionactif() {
        return idActionactif;
    }

    public void setIdActionactif(Integer idActionactif) {
        this.idActionactif = idActionactif;
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
        hash += (idActionactif != null ? idActionactif.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actionactif)) {
            return false;
        }
        Actionactif other = (Actionactif) object;
        if ((this.idActionactif == null && other.idActionactif != null) || (this.idActionactif != null && !this.idActionactif.equals(other.idActionactif))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Actionactif[ idActionactif=" + idActionactif + " ]";
    }
    
}
