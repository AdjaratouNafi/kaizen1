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
@Table(name = "tonpomcoef")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tonpomcoef.findAll", query = "SELECT t FROM Tonpomcoef t")
    , @NamedQuery(name = "Tonpomcoef.findByIdTonpomcoef", query = "SELECT t FROM Tonpomcoef t WHERE t.idTonpomcoef = :idTonpomcoef")
    , @NamedQuery(name = "Tonpomcoef.findByNumerodevis", query = "SELECT t FROM Tonpomcoef t WHERE t.numerodevis = :numerodevis")
    , @NamedQuery(name = "Tonpomcoef.findByDesignation", query = "SELECT t FROM Tonpomcoef t WHERE t.designation = :designation")
    , @NamedQuery(name = "Tonpomcoef.findByTypeitem", query = "SELECT t FROM Tonpomcoef t WHERE t.typeitem = :typeitem")
    , @NamedQuery(name = "Tonpomcoef.findByRef", query = "SELECT t FROM Tonpomcoef t WHERE t.ref = :ref")
    , @NamedQuery(name = "Tonpomcoef.findByUnite", query = "SELECT t FROM Tonpomcoef t WHERE t.unite = :unite")
    , @NamedQuery(name = "Tonpomcoef.findByQuantite", query = "SELECT t FROM Tonpomcoef t WHERE t.quantite = :quantite")
    , @NamedQuery(name = "Tonpomcoef.findByPu", query = "SELECT t FROM Tonpomcoef t WHERE t.pu = :pu")
    , @NamedQuery(name = "Tonpomcoef.findByPassage", query = "SELECT t FROM Tonpomcoef t WHERE t.passage = :passage")
    , @NamedQuery(name = "Tonpomcoef.findByTypepassage", query = "SELECT t FROM Tonpomcoef t WHERE t.typepassage = :typepassage")})
public class Tonpomcoef implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTonpomcoef")
    private Integer idTonpomcoef;
    @Size(max = 250)
    @Column(name = "numerodevis")
    private String numerodevis;
    @Size(max = 250)
    @Column(name = "designation")
    private String designation;
    @Size(max = 255)
    @Column(name = "typeitem")
    private String typeitem;
    @Size(max = 250)
    @Column(name = "ref")
    private String ref;
    @Size(max = 250)
    @Column(name = "unite")
    private String unite;
    @Column(name = "quantite")
    private Integer quantite;
    @Size(max = 250)
    @Column(name = "pu")
    private String pu;
    @Column(name = "passage")
    private Integer passage;
    @Size(max = 250)
    @Column(name = "typepassage")
    private String typepassage;

    public Tonpomcoef() {
    }

    public Tonpomcoef(Integer idTonpomcoef) {
        this.idTonpomcoef = idTonpomcoef;
    }

    public Integer getIdTonpomcoef() {
        return idTonpomcoef;
    }

    public void setIdTonpomcoef(Integer idTonpomcoef) {
        this.idTonpomcoef = idTonpomcoef;
    }

    public String getNumerodevis() {
        return numerodevis;
    }

    public void setNumerodevis(String numerodevis) {
        this.numerodevis = numerodevis;
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

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
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

    public Integer getPassage() {
        return passage;
    }

    public void setPassage(Integer passage) {
        this.passage = passage;
    }

    public String getTypepassage() {
        return typepassage;
    }

    public void setTypepassage(String typepassage) {
        this.typepassage = typepassage;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTonpomcoef != null ? idTonpomcoef.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tonpomcoef)) {
            return false;
        }
        Tonpomcoef other = (Tonpomcoef) object;
        if ((this.idTonpomcoef == null && other.idTonpomcoef != null) || (this.idTonpomcoef != null && !this.idTonpomcoef.equals(other.idTonpomcoef))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Tonpomcoef[ idTonpomcoef=" + idTonpomcoef + " ]";
    }
    
}
