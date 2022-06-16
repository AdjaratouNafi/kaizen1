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
@Table(name = "detailledevis")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detailledevis.findAll", query = "SELECT d FROM Detailledevis d")
    , @NamedQuery(name = "Detailledevis.findByIdDetailDevis", query = "SELECT d FROM Detailledevis d WHERE d.idDetailDevis = :idDetailDevis")
    , @NamedQuery(name = "Detailledevis.findByReference", query = "SELECT d FROM Detailledevis d WHERE d.reference = :reference")
    , @NamedQuery(name = "Detailledevis.findByUnite", query = "SELECT d FROM Detailledevis d WHERE d.unite = :unite")
    , @NamedQuery(name = "Detailledevis.findByDesignation", query = "SELECT d FROM Detailledevis d WHERE d.designation = :designation")
    , @NamedQuery(name = "Detailledevis.findByTypeitem", query = "SELECT d FROM Detailledevis d WHERE d.typeitem = :typeitem")
    , @NamedQuery(name = "Detailledevis.findByQuantite", query = "SELECT d FROM Detailledevis d WHERE d.quantite = :quantite")
    , @NamedQuery(name = "Detailledevis.findByPu", query = "SELECT d FROM Detailledevis d WHERE d.pu = :pu")})
public class Detailledevis implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDetailDevis")
    private Integer idDetailDevis;
    @Size(max = 250)
    @Column(name = "reference")
    private String reference;
    @Size(max = 50)
    @Column(name = "unite")
    private String unite;
    @Size(max = 255)
    @Column(name = "designation")
    private String designation;
    @Size(max = 255)
    @Column(name = "typeitem")
    private String typeitem;
    @Column(name = "quantite")
    private Integer quantite;
    @Size(max = 255)
    @Column(name = "pu")
    private String pu;
    @JoinColumn(name = "idDevis", referencedColumnName = "idDevis")
    @ManyToOne
    private Devis idDevis;
    @JoinColumn(name = "idUnitemesure", referencedColumnName = "idUnitemesure")
    @ManyToOne
    private Unitemesure idUnitemesure;

    public Detailledevis() {
    }

    public Detailledevis(Integer idDetailDevis) {
        this.idDetailDevis = idDetailDevis;
    }

    public Integer getIdDetailDevis() {
        return idDetailDevis;
    }

    public void setIdDetailDevis(Integer idDetailDevis) {
        this.idDetailDevis = idDetailDevis;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getTypeitem() {
        return typeitem;
    }

    public void setTypeitem(String typeitem) {
        this.typeitem = typeitem;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public String getPu() {
        return pu;
    }

    public void setPu(String pu) {
        this.pu = pu;
    }

    public Devis getIdDevis() {
        return idDevis;
    }

    public void setIdDevis(Devis idDevis) {
        this.idDevis = idDevis;
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
        hash += (idDetailDevis != null ? idDetailDevis.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detailledevis)) {
            return false;
        }
        Detailledevis other = (Detailledevis) object;
        if ((this.idDetailDevis == null && other.idDetailDevis != null) || (this.idDetailDevis != null && !this.idDetailDevis.equals(other.idDetailDevis))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Detailledevis[ idDetailDevis=" + idDetailDevis + " ]";
    }
    
}
