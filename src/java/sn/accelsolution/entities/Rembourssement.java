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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DV7
 */
@Entity
@Table(name = "rembourssement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rembourssement.findAll", query = "SELECT r FROM Rembourssement r")
    , @NamedQuery(name = "Rembourssement.findByIdRembourssement", query = "SELECT r FROM Rembourssement r WHERE r.idRembourssement = :idRembourssement")})
public class Rembourssement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRembourssement")
    private Integer idRembourssement;
    @JoinColumn(name = "idPret", referencedColumnName = "idPret")
    @ManyToOne
    private Pret idPret;
    @JoinColumn(name = "idSalaire", referencedColumnName = "idSalaire")
    @ManyToOne
    private Salaire idSalaire;

    public Rembourssement() {
    }

    public Rembourssement(Integer idRembourssement) {
        this.idRembourssement = idRembourssement;
    }

    public Integer getIdRembourssement() {
        return idRembourssement;
    }

    public void setIdRembourssement(Integer idRembourssement) {
        this.idRembourssement = idRembourssement;
    }

    public Pret getIdPret() {
        return idPret;
    }

    public void setIdPret(Pret idPret) {
        this.idPret = idPret;
    }

    public Salaire getIdSalaire() {
        return idSalaire;
    }

    public void setIdSalaire(Salaire idSalaire) {
        this.idSalaire = idSalaire;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRembourssement != null ? idRembourssement.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rembourssement)) {
            return false;
        }
        Rembourssement other = (Rembourssement) object;
        if ((this.idRembourssement == null && other.idRembourssement != null) || (this.idRembourssement != null && !this.idRembourssement.equals(other.idRembourssement))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Rembourssement[ idRembourssement=" + idRembourssement + " ]";
    }
    
}
