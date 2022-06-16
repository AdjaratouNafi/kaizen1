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
@Table(name = "detailledecomptep")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detailledecomptep.findAll", query = "SELECT d FROM Detailledecomptep d")
    , @NamedQuery(name = "Detailledecomptep.findByIdDetailleDecomptep", query = "SELECT d FROM Detailledecomptep d WHERE d.idDetailleDecomptep = :idDetailleDecomptep")
    , @NamedQuery(name = "Detailledecomptep.findByDesignation", query = "SELECT d FROM Detailledecomptep d WHERE d.designation = :designation")
    , @NamedQuery(name = "Detailledecomptep.findByTypeitem", query = "SELECT d FROM Detailledecomptep d WHERE d.typeitem = :typeitem")
    , @NamedQuery(name = "Detailledecomptep.findByReference", query = "SELECT d FROM Detailledecomptep d WHERE d.reference = :reference")
    , @NamedQuery(name = "Detailledecomptep.findByUnite", query = "SELECT d FROM Detailledecomptep d WHERE d.unite = :unite")
    , @NamedQuery(name = "Detailledecomptep.findByQuantite", query = "SELECT d FROM Detailledecomptep d WHERE d.quantite = :quantite")
    , @NamedQuery(name = "Detailledecomptep.findByPu", query = "SELECT d FROM Detailledecomptep d WHERE d.pu = :pu")
    , @NamedQuery(name = "Detailledecomptep.findByExecution", query = "SELECT d FROM Detailledecomptep d WHERE d.execution = :execution")})
public class Detailledecomptep implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDetailleDecomptep")
    private Integer idDetailleDecomptep;
    @Size(max = 255)
    @Column(name = "designation")
    private String designation;
    @Size(max = 255)
    @Column(name = "typeitem")
    private String typeitem;
    @Size(max = 50)
    @Column(name = "reference")
    private String reference;
    @Size(max = 50)
    @Column(name = "unite")
    private String unite;
    @Column(name = "quantite")
    private Integer quantite;
    @Size(max = 255)
    @Column(name = "pu")
    private String pu;
    @Column(name = "execution")
    private Integer execution;
    @JoinColumn(name = "idDecomptep", referencedColumnName = "idDecomptep")
    @ManyToOne
    private Decomptep idDecomptep;
    @JoinColumn(name = "idUnitemesure", referencedColumnName = "idUnitemesure")
    @ManyToOne
    private Unitemesure idUnitemesure;

    public Detailledecomptep() {
    }

    public Detailledecomptep(Integer idDetailleDecomptep) {
        this.idDetailleDecomptep = idDetailleDecomptep;
    }

    public Integer getIdDetailleDecomptep() {
        return idDetailleDecomptep;
    }

    public void setIdDetailleDecomptep(Integer idDetailleDecomptep) {
        this.idDetailleDecomptep = idDetailleDecomptep;
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

    public Integer getExecution() {
        return execution;
    }

    public void setExecution(Integer execution) {
        this.execution = execution;
    }

    public Decomptep getIdDecomptep() {
        return idDecomptep;
    }

    public void setIdDecomptep(Decomptep idDecomptep) {
        this.idDecomptep = idDecomptep;
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
        hash += (idDetailleDecomptep != null ? idDetailleDecomptep.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detailledecomptep)) {
            return false;
        }
        Detailledecomptep other = (Detailledecomptep) object;
        if ((this.idDetailleDecomptep == null && other.idDetailleDecomptep != null) || (this.idDetailleDecomptep != null && !this.idDetailleDecomptep.equals(other.idDetailleDecomptep))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Detailledecomptep[ idDetailleDecomptep=" + idDetailleDecomptep + " ]";
    }
    
}
