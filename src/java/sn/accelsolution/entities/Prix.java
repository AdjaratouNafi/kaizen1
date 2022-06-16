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
@Table(name = "prix")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prix.findAll", query = "SELECT p FROM Prix p")
    , @NamedQuery(name = "Prix.findByIdPrix", query = "SELECT p FROM Prix p WHERE p.idPrix = :idPrix")
    , @NamedQuery(name = "Prix.findByPrix", query = "SELECT p FROM Prix p WHERE p.prix = :prix")})
public class Prix implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPrix")
    private Integer idPrix;
    @Size(max = 100)
    @Column(name = "prix")
    private String prix;
    @JoinColumn(name = "idDevise", referencedColumnName = "idDevise")
    @ManyToOne
    private Devise idDevise;
    @JoinColumn(name = "idFournisseur", referencedColumnName = "idFournisseur")
    @ManyToOne
    private Fournisseur idFournisseur;
    @JoinColumn(name = "idMarchandise", referencedColumnName = "idMarchandise")
    @ManyToOne
    private Marchandise idMarchandise;
    @JoinColumn(name = "idUnitemesure", referencedColumnName = "idUnitemesure")
    @ManyToOne
    private Unitemesure idUnitemesure;

    public Prix() {
    }

    public Prix(Integer idPrix) {
        this.idPrix = idPrix;
    }

    public Integer getIdPrix() {
        return idPrix;
    }

    public void setIdPrix(Integer idPrix) {
        this.idPrix = idPrix;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public Devise getIdDevise() {
        return idDevise;
    }

    public void setIdDevise(Devise idDevise) {
        this.idDevise = idDevise;
    }

    public Fournisseur getIdFournisseur() {
        return idFournisseur;
    }

    public void setIdFournisseur(Fournisseur idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    public Marchandise getIdMarchandise() {
        return idMarchandise;
    }

    public void setIdMarchandise(Marchandise idMarchandise) {
        this.idMarchandise = idMarchandise;
    }

    public Unitemesure getIdUnitemesure() {
        return idUnitemesure;
    }

    public void setIdUnitemesure(Unitemesure idUnitemesure) {
        this.idUnitemesure = idUnitemesure;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPrix != null ? idPrix.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prix)) {
            return false;
        }
        Prix other = (Prix) object;
        if ((this.idPrix == null && other.idPrix != null) || (this.idPrix != null && !this.idPrix.equals(other.idPrix))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Prix[ idPrix=" + idPrix + " ]";
    }
    
}
