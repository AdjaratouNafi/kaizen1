/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DV7
 */
@Entity
@Table(name = "paiement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paiement.findAll", query = "SELECT p FROM Paiement p")
    , @NamedQuery(name = "Paiement.findByIdPaiement", query = "SELECT p FROM Paiement p WHERE p.idPaiement = :idPaiement")
    , @NamedQuery(name = "Paiement.findByDatePaiement", query = "SELECT p FROM Paiement p WHERE p.datePaiement = :datePaiement")
    , @NamedQuery(name = "Paiement.findByMontantRegle", query = "SELECT p FROM Paiement p WHERE p.montantRegle = :montantRegle")
    , @NamedQuery(name = "Paiement.findByMontantFacture", query = "SELECT p FROM Paiement p WHERE p.montantFacture = :montantFacture")
    , @NamedQuery(name = "Paiement.findByModePaiement", query = "SELECT p FROM Paiement p WHERE p.modePaiement = :modePaiement")
    , @NamedQuery(name = "Paiement.findByNumeroCheque", query = "SELECT p FROM Paiement p WHERE p.numeroCheque = :numeroCheque")})
public class Paiement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPaiement")
    private Integer idPaiement;
    @Column(name = "datePaiement")
    @Temporal(TemporalType.DATE)
    private Date datePaiement;
    @Column(name = "montantRegle")
    private BigInteger montantRegle;
    @Column(name = "montantFacture")
    private BigInteger montantFacture;
    @Size(max = 50)
    @Column(name = "modePaiement")
    private String modePaiement;
    @Column(name = "numeroCheque")
    private BigInteger numeroCheque;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "observation")
    private String observation;
    @JoinColumn(name = "idCommande", referencedColumnName = "idCommande")
    @ManyToOne
    private Commande idCommande;
    @JoinColumn(name = "idMarche", referencedColumnName = "idMarche")
    @ManyToOne
    private Marche idMarche;

    public Paiement() {
    }

    public Paiement(Integer idPaiement) {
        this.idPaiement = idPaiement;
    }

    public Integer getIdPaiement() {
        return idPaiement;
    }

    public void setIdPaiement(Integer idPaiement) {
        this.idPaiement = idPaiement;
    }

    public Date getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(Date datePaiement) {
        this.datePaiement = datePaiement;
    }

    public BigInteger getMontantRegle() {
        return montantRegle;
    }

    public void setMontantRegle(BigInteger montantRegle) {
        this.montantRegle = montantRegle;
    }

    public BigInteger getMontantFacture() {
        return montantFacture;
    }

    public void setMontantFacture(BigInteger montantFacture) {
        this.montantFacture = montantFacture;
    }

    public String getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(String modePaiement) {
        this.modePaiement = modePaiement;
    }

    public BigInteger getNumeroCheque() {
        return numeroCheque;
    }

    public void setNumeroCheque(BigInteger numeroCheque) {
        this.numeroCheque = numeroCheque;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Commande getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Commande idCommande) {
        this.idCommande = idCommande;
    }

    public Marche getIdMarche() {
        return idMarche;
    }

    public void setIdMarche(Marche idMarche) {
        this.idMarche = idMarche;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPaiement != null ? idPaiement.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paiement)) {
            return false;
        }
        Paiement other = (Paiement) object;
        if ((this.idPaiement == null && other.idPaiement != null) || (this.idPaiement != null && !this.idPaiement.equals(other.idPaiement))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Paiement[ idPaiement=" + idPaiement + " ]";
    }
    
}
