/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.entities;

import java.io.Serializable;
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
@Table(name = "caisse")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Caisse.findAll", query = "SELECT c FROM Caisse c")
    , @NamedQuery(name = "Caisse.findByNumeroCaisse", query = "SELECT c FROM Caisse c WHERE c.numeroCaisse = :numeroCaisse")
    , @NamedQuery(name = "Caisse.findByDateCreation", query = "SELECT c FROM Caisse c WHERE c.dateCreation = :dateCreation")
    , @NamedQuery(name = "Caisse.findByMontantInitiale", query = "SELECT c FROM Caisse c WHERE c.montantInitiale = :montantInitiale")
    , @NamedQuery(name = "Caisse.findBySolde", query = "SELECT c FROM Caisse c WHERE c.solde = :solde")
    , @NamedQuery(name = "Caisse.findBySoldesortie", query = "SELECT c FROM Caisse c WHERE c.soldesortie = :soldesortie")})
public class Caisse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numeroCaisse")
    private Integer numeroCaisse;
    @Size(max = 50)
    @Column(name = "dateCreation")
    private String dateCreation;
    @Size(max = 50)
    @Column(name = "montantInitiale")
    private String montantInitiale;
    @Size(max = 50)
    @Column(name = "solde")
    private String solde;
    @Size(max = 50)
    @Column(name = "soldesortie")
    private String soldesortie;
    @OneToMany(mappedBy = "idCaisse")
    private List<Operation> operationList;

    public Caisse() {
    }

    public Caisse(Integer numeroCaisse) {
        this.numeroCaisse = numeroCaisse;
    }

    public Integer getNumeroCaisse() {
        return numeroCaisse;
    }

    public void setNumeroCaisse(Integer numeroCaisse) {
        this.numeroCaisse = numeroCaisse;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getMontantInitiale() {
        return montantInitiale;
    }

    public void setMontantInitiale(String montantInitiale) {
        this.montantInitiale = montantInitiale;
    }

    public String getSolde() {
        return solde;
    }

    public void setSolde(String solde) {
        this.solde = solde;
    }

    public String getSoldesortie() {
        return soldesortie;
    }

    public void setSoldesortie(String soldesortie) {
        this.soldesortie = soldesortie;
    }

    @XmlTransient
    @JsonIgnore
    public List<Operation> getOperationList() {
        return operationList;
    }

    public void setOperationList(List<Operation> operationList) {
        this.operationList = operationList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroCaisse != null ? numeroCaisse.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Caisse)) {
            return false;
        }
        Caisse other = (Caisse) object;
        if ((this.numeroCaisse == null && other.numeroCaisse != null) || (this.numeroCaisse != null && !this.numeroCaisse.equals(other.numeroCaisse))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Caisse[ numeroCaisse=" + numeroCaisse + " ]";
    }
    
}
