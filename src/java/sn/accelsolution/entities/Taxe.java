/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author DV7
 */
@Entity
@Table(name = "taxe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Taxe.findAll", query = "SELECT t FROM Taxe t")
    , @NamedQuery(name = "Taxe.findByIdTaxe", query = "SELECT t FROM Taxe t WHERE t.idTaxe = :idTaxe")
    , @NamedQuery(name = "Taxe.findByNatureTaxe", query = "SELECT t FROM Taxe t WHERE t.natureTaxe = :natureTaxe")
    , @NamedQuery(name = "Taxe.findByMontantTaxe", query = "SELECT t FROM Taxe t WHERE t.montantTaxe = :montantTaxe")})
public class Taxe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTaxe")
    private Integer idTaxe;
    @Size(max = 50)
    @Column(name = "natureTaxe")
    private String natureTaxe;
    @Column(name = "montantTaxe")
    private BigInteger montantTaxe;
    @OneToMany(mappedBy = "idTaxe")
    private List<Commande> commandeList;

    public Taxe() {
    }

    public Taxe(Integer idTaxe) {
        this.idTaxe = idTaxe;
    }

    public Integer getIdTaxe() {
        return idTaxe;
    }

    public void setIdTaxe(Integer idTaxe) {
        this.idTaxe = idTaxe;
    }

    public String getNatureTaxe() {
        return natureTaxe;
    }

    public void setNatureTaxe(String natureTaxe) {
        this.natureTaxe = natureTaxe;
    }

    public BigInteger getMontantTaxe() {
        return montantTaxe;
    }

    public void setMontantTaxe(BigInteger montantTaxe) {
        this.montantTaxe = montantTaxe;
    }

    @XmlTransient
    @JsonIgnore
    public List<Commande> getCommandeList() {
        return commandeList;
    }

    public void setCommandeList(List<Commande> commandeList) {
        this.commandeList = commandeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTaxe != null ? idTaxe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Taxe)) {
            return false;
        }
        Taxe other = (Taxe) object;
        if ((this.idTaxe == null && other.idTaxe != null) || (this.idTaxe != null && !this.idTaxe.equals(other.idTaxe))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Taxe[ idTaxe=" + idTaxe + " ]";
    }
    
}
