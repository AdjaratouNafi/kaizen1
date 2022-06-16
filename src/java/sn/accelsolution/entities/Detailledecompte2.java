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
@Table(name = "detailledecompte2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detailledecompte2.findAll", query = "SELECT d FROM Detailledecompte2 d")
    , @NamedQuery(name = "Detailledecompte2.findByIdDetailleDecompte2", query = "SELECT d FROM Detailledecompte2 d WHERE d.idDetailleDecompte2 = :idDetailleDecompte2")
    , @NamedQuery(name = "Detailledecompte2.findByDesignation", query = "SELECT d FROM Detailledecompte2 d WHERE d.designation = :designation")
    , @NamedQuery(name = "Detailledecompte2.findByUnite", query = "SELECT d FROM Detailledecompte2 d WHERE d.unite = :unite")
    , @NamedQuery(name = "Detailledecompte2.findByQuantite", query = "SELECT d FROM Detailledecompte2 d WHERE d.quantite = :quantite")
    , @NamedQuery(name = "Detailledecompte2.findByPu", query = "SELECT d FROM Detailledecompte2 d WHERE d.pu = :pu")
    , @NamedQuery(name = "Detailledecompte2.findByExecution", query = "SELECT d FROM Detailledecompte2 d WHERE d.execution = :execution")})
public class Detailledecompte2 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDetailleDecompte2")
    private Integer idDetailleDecompte2;
    @Size(max = 255)
    @Column(name = "designation")
    private String designation;
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
    @JoinColumn(name = "idDecompte2", referencedColumnName = "idDecompte2")
    @ManyToOne
    private Decompte2 idDecompte2;

    public Detailledecompte2() {
    }

    public Detailledecompte2(Integer idDetailleDecompte2) {
        this.idDetailleDecompte2 = idDetailleDecompte2;
    }

    public Integer getIdDetailleDecompte2() {
        return idDetailleDecompte2;
    }

    public void setIdDetailleDecompte2(Integer idDetailleDecompte2) {
        this.idDetailleDecompte2 = idDetailleDecompte2;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
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

    public Decompte2 getIdDecompte2() {
        return idDecompte2;
    }

    public void setIdDecompte2(Decompte2 idDecompte2) {
        this.idDecompte2 = idDecompte2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetailleDecompte2 != null ? idDetailleDecompte2.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detailledecompte2)) {
            return false;
        }
        Detailledecompte2 other = (Detailledecompte2) object;
        if ((this.idDetailleDecompte2 == null && other.idDetailleDecompte2 != null) || (this.idDetailleDecompte2 != null && !this.idDetailleDecompte2.equals(other.idDetailleDecompte2))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Detailledecompte2[ idDetailleDecompte2=" + idDetailleDecompte2 + " ]";
    }
    
}
