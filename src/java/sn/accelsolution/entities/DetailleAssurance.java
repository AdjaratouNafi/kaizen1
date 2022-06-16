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
@Table(name = "detaille_assurance")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetailleAssurance.findAll", query = "SELECT d FROM DetailleAssurance d")
    , @NamedQuery(name = "DetailleAssurance.findByIdDetailleAssurance", query = "SELECT d FROM DetailleAssurance d WHERE d.idDetailleAssurance = :idDetailleAssurance")})
public class DetailleAssurance implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDetailleAssurance")
    private Integer idDetailleAssurance;
    @JoinColumn(name = "idAssurance", referencedColumnName = "idAssurance")
    @ManyToOne
    private Assurance idAssurance;
    @JoinColumn(name = "idAssureur", referencedColumnName = "idAssureur")
    @ManyToOne
    private Assureur idAssureur;

    public DetailleAssurance() {
    }

    public DetailleAssurance(Integer idDetailleAssurance) {
        this.idDetailleAssurance = idDetailleAssurance;
    }

    public Integer getIdDetailleAssurance() {
        return idDetailleAssurance;
    }

    public void setIdDetailleAssurance(Integer idDetailleAssurance) {
        this.idDetailleAssurance = idDetailleAssurance;
    }

    public Assurance getIdAssurance() {
        return idAssurance;
    }

    public void setIdAssurance(Assurance idAssurance) {
        this.idAssurance = idAssurance;
    }

    public Assureur getIdAssureur() {
        return idAssureur;
    }

    public void setIdAssureur(Assureur idAssureur) {
        this.idAssureur = idAssureur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetailleAssurance != null ? idDetailleAssurance.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetailleAssurance)) {
            return false;
        }
        DetailleAssurance other = (DetailleAssurance) object;
        if ((this.idDetailleAssurance == null && other.idDetailleAssurance != null) || (this.idDetailleAssurance != null && !this.idDetailleAssurance.equals(other.idDetailleAssurance))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.DetailleAssurance[ idDetailleAssurance=" + idDetailleAssurance + " ]";
    }
    
}
