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
@Table(name = "detaillenewfacture")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detaillenewfacture.findAll", query = "SELECT d FROM Detaillenewfacture d")
    , @NamedQuery(name = "Detaillenewfacture.findByIdDetailleNewfacture", query = "SELECT d FROM Detaillenewfacture d WHERE d.idDetailleNewfacture = :idDetailleNewfacture")
    , @NamedQuery(name = "Detaillenewfacture.findByReference", query = "SELECT d FROM Detaillenewfacture d WHERE d.reference = :reference")
    , @NamedQuery(name = "Detaillenewfacture.findByUnite", query = "SELECT d FROM Detaillenewfacture d WHERE d.unite = :unite")
    , @NamedQuery(name = "Detaillenewfacture.findByDesignation", query = "SELECT d FROM Detaillenewfacture d WHERE d.designation = :designation")
    , @NamedQuery(name = "Detaillenewfacture.findByTypeitem", query = "SELECT d FROM Detaillenewfacture d WHERE d.typeitem = :typeitem")
    , @NamedQuery(name = "Detaillenewfacture.findByQuantite", query = "SELECT d FROM Detaillenewfacture d WHERE d.quantite = :quantite")
    , @NamedQuery(name = "Detaillenewfacture.findByPu", query = "SELECT d FROM Detaillenewfacture d WHERE d.pu = :pu")})
public class Detaillenewfacture implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDetailleNewfacture")
    private Integer idDetailleNewfacture;
    @Size(max = 255)
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
    @JoinColumn(name = "idNewfacture", referencedColumnName = "idNewfacture")
    @ManyToOne
    private Newfacture idNewfacture;
    @JoinColumn(name = "idUnitemesure", referencedColumnName = "idUnitemesure")
    @ManyToOne
    private Unitemesure idUnitemesure;

    public Detaillenewfacture() {
    }

    public Detaillenewfacture(Integer idDetailleNewfacture) {
        this.idDetailleNewfacture = idDetailleNewfacture;
    }

    public Integer getIdDetailleNewfacture() {
        return idDetailleNewfacture;
    }

    public void setIdDetailleNewfacture(Integer idDetailleNewfacture) {
        this.idDetailleNewfacture = idDetailleNewfacture;
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

    public Newfacture getIdNewfacture() {
        return idNewfacture;
    }

    public void setIdNewfacture(Newfacture idNewfacture) {
        this.idNewfacture = idNewfacture;
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
        hash += (idDetailleNewfacture != null ? idDetailleNewfacture.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detaillenewfacture)) {
            return false;
        }
        Detaillenewfacture other = (Detaillenewfacture) object;
        if ((this.idDetailleNewfacture == null && other.idDetailleNewfacture != null) || (this.idDetailleNewfacture != null && !this.idDetailleNewfacture.equals(other.idDetailleNewfacture))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Detaillenewfacture[ idDetailleNewfacture=" + idDetailleNewfacture + " ]";
    }
    
}
