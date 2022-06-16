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
@Table(name = "detailleexpressionbesoin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detailleexpressionbesoin.findAll", query = "SELECT d FROM Detailleexpressionbesoin d")
    , @NamedQuery(name = "Detailleexpressionbesoin.findByIdDetailleExpression", query = "SELECT d FROM Detailleexpressionbesoin d WHERE d.idDetailleExpression = :idDetailleExpression")
    , @NamedQuery(name = "Detailleexpressionbesoin.findByReference", query = "SELECT d FROM Detailleexpressionbesoin d WHERE d.reference = :reference")
    , @NamedQuery(name = "Detailleexpressionbesoin.findByUnite", query = "SELECT d FROM Detailleexpressionbesoin d WHERE d.unite = :unite")
    , @NamedQuery(name = "Detailleexpressionbesoin.findByDesignation", query = "SELECT d FROM Detailleexpressionbesoin d WHERE d.designation = :designation")
    , @NamedQuery(name = "Detailleexpressionbesoin.findByQuantite", query = "SELECT d FROM Detailleexpressionbesoin d WHERE d.quantite = :quantite")
    , @NamedQuery(name = "Detailleexpressionbesoin.findByPu", query = "SELECT d FROM Detailleexpressionbesoin d WHERE d.pu = :pu")})
public class Detailleexpressionbesoin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDetailleExpression")
    private Integer idDetailleExpression;
    @Size(max = 250)
    @Column(name = "reference")
    private String reference;
    @Size(max = 250)
    @Column(name = "unite")
    private String unite;
    @Size(max = 250)
    @Column(name = "designation")
    private String designation;
    @Column(name = "quantite")
    private Integer quantite;
    @Size(max = 250)
    @Column(name = "pu")
    private String pu;
    @JoinColumn(name = "idExpression", referencedColumnName = "idExpression")
    @ManyToOne
    private Expressionbesoin idExpression;

    public Detailleexpressionbesoin() {
    }

    public Detailleexpressionbesoin(Integer idDetailleExpression) {
        this.idDetailleExpression = idDetailleExpression;
    }

    public Integer getIdDetailleExpression() {
        return idDetailleExpression;
    }

    public void setIdDetailleExpression(Integer idDetailleExpression) {
        this.idDetailleExpression = idDetailleExpression;
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

    public Expressionbesoin getIdExpression() {
        return idExpression;
    }

    public void setIdExpression(Expressionbesoin idExpression) {
        this.idExpression = idExpression;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetailleExpression != null ? idDetailleExpression.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detailleexpressionbesoin)) {
            return false;
        }
        Detailleexpressionbesoin other = (Detailleexpressionbesoin) object;
        if ((this.idDetailleExpression == null && other.idDetailleExpression != null) || (this.idDetailleExpression != null && !this.idDetailleExpression.equals(other.idDetailleExpression))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Detailleexpressionbesoin[ idDetailleExpression=" + idDetailleExpression + " ]";
    }
    
}
