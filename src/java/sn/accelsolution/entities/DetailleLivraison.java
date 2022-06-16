/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.entities;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "detaille_livraison")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetailleLivraison.findAll", query = "SELECT d FROM DetailleLivraison d")
    , @NamedQuery(name = "DetailleLivraison.findByIdDetaileLivraison", query = "SELECT d FROM DetailleLivraison d WHERE d.idDetaileLivraison = :idDetaileLivraison")
    , @NamedQuery(name = "DetailleLivraison.findByQtLivre", query = "SELECT d FROM DetailleLivraison d WHERE d.qtLivre = :qtLivre")
    , @NamedQuery(name = "DetailleLivraison.findByDesignation", query = "SELECT d FROM DetailleLivraison d WHERE d.designation = :designation")
    , @NamedQuery(name = "DetailleLivraison.findByPu", query = "SELECT d FROM DetailleLivraison d WHERE d.pu = :pu")
    , @NamedQuery(name = "DetailleLivraison.findByPuhortaxe", query = "SELECT d FROM DetailleLivraison d WHERE d.puhortaxe = :puhortaxe")
    , @NamedQuery(name = "DetailleLivraison.findByTtc", query = "SELECT d FROM DetailleLivraison d WHERE d.ttc = :ttc")})
public class DetailleLivraison implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDetaileLivraison")
    private Integer idDetaileLivraison;
    @Column(name = "qtLivre")
    private Integer qtLivre;
    @Size(max = 50)
    @Column(name = "designation")
    private String designation;
    @Column(name = "pu")
    private BigInteger pu;
    @Column(name = "puhortaxe")
    private BigInteger puhortaxe;
    @Column(name = "ttc")
    private BigInteger ttc;
    @JoinColumn(name = "idLivraison", referencedColumnName = "idLivraison")
    @ManyToOne
    private Livraison idLivraison;

    public DetailleLivraison() {
    }

    public DetailleLivraison(Integer idDetaileLivraison) {
        this.idDetaileLivraison = idDetaileLivraison;
    }

    public Integer getIdDetaileLivraison() {
        return idDetaileLivraison;
    }

    public void setIdDetaileLivraison(Integer idDetaileLivraison) {
        this.idDetaileLivraison = idDetaileLivraison;
    }

    public Integer getQtLivre() {
        return qtLivre;
    }

    public void setQtLivre(Integer qtLivre) {
        this.qtLivre = qtLivre;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public BigInteger getPu() {
        return pu;
    }

    public void setPu(BigInteger pu) {
        this.pu = pu;
    }

    public BigInteger getPuhortaxe() {
        return puhortaxe;
    }

    public void setPuhortaxe(BigInteger puhortaxe) {
        this.puhortaxe = puhortaxe;
    }

    public BigInteger getTtc() {
        return ttc;
    }

    public void setTtc(BigInteger ttc) {
        this.ttc = ttc;
    }

    public Livraison getIdLivraison() {
        return idLivraison;
    }

    public void setIdLivraison(Livraison idLivraison) {
        this.idLivraison = idLivraison;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetaileLivraison != null ? idDetaileLivraison.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetailleLivraison)) {
            return false;
        }
        DetailleLivraison other = (DetailleLivraison) object;
        if ((this.idDetaileLivraison == null && other.idDetaileLivraison != null) || (this.idDetaileLivraison != null && !this.idDetaileLivraison.equals(other.idDetaileLivraison))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.DetailleLivraison[ idDetaileLivraison=" + idDetaileLivraison + " ]";
    }
    
}
