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
@Table(name = "banque")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Banque.findAll", query = "SELECT b FROM Banque b")
    , @NamedQuery(name = "Banque.findByNumeroBanque", query = "SELECT b FROM Banque b WHERE b.numeroBanque = :numeroBanque")
    , @NamedQuery(name = "Banque.findByDateCreation", query = "SELECT b FROM Banque b WHERE b.dateCreation = :dateCreation")
    , @NamedQuery(name = "Banque.findByMontantInitiale", query = "SELECT b FROM Banque b WHERE b.montantInitiale = :montantInitiale")
    , @NamedQuery(name = "Banque.findBySolde", query = "SELECT b FROM Banque b WHERE b.solde = :solde")
    , @NamedQuery(name = "Banque.findBySoldesortie", query = "SELECT b FROM Banque b WHERE b.soldesortie = :soldesortie")})
public class Banque implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numeroBanque")
    private Integer numeroBanque;
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
    @OneToMany(mappedBy = "idBanque")
    private List<Operation> operationList;

    public Banque() {
    }

    public Banque(Integer numeroBanque) {
        this.numeroBanque = numeroBanque;
    }

    public Integer getNumeroBanque() {
        return numeroBanque;
    }

    public void setNumeroBanque(Integer numeroBanque) {
        this.numeroBanque = numeroBanque;
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
        hash += (numeroBanque != null ? numeroBanque.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Banque)) {
            return false;
        }
        Banque other = (Banque) object;
        if ((this.numeroBanque == null && other.numeroBanque != null) || (this.numeroBanque != null && !this.numeroBanque.equals(other.numeroBanque))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Banque[ numeroBanque=" + numeroBanque + " ]";
    }
    
}
