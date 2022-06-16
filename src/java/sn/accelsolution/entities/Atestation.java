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
@Table(name = "atestation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Atestation.findAll", query = "SELECT a FROM Atestation a")
    , @NamedQuery(name = "Atestation.findByIdAtestation", query = "SELECT a FROM Atestation a WHERE a.idAtestation = :idAtestation")
    , @NamedQuery(name = "Atestation.findByAssurance", query = "SELECT a FROM Atestation a WHERE a.assurance = :assurance")
    , @NamedQuery(name = "Atestation.findByBilletordre", query = "SELECT a FROM Atestation a WHERE a.billetordre = :billetordre")
    , @NamedQuery(name = "Atestation.findByCommission", query = "SELECT a FROM Atestation a WHERE a.commission = :commission")
    , @NamedQuery(name = "Atestation.findByDateAtestation", query = "SELECT a FROM Atestation a WHERE a.dateAtestation = :dateAtestation")
    , @NamedQuery(name = "Atestation.findByFraisdossier", query = "SELECT a FROM Atestation a WHERE a.fraisdossier = :fraisdossier")
    , @NamedQuery(name = "Atestation.findByIdClient", query = "SELECT a FROM Atestation a WHERE a.idClient = :idClient")
    , @NamedQuery(name = "Atestation.findByIdDemande", query = "SELECT a FROM Atestation a WHERE a.idDemande = :idDemande")
    , @NamedQuery(name = "Atestation.findByMtt", query = "SELECT a FROM Atestation a WHERE a.mtt = :mtt")
    , @NamedQuery(name = "Atestation.findByPrime", query = "SELECT a FROM Atestation a WHERE a.prime = :prime")
    , @NamedQuery(name = "Atestation.findByTaux", query = "SELECT a FROM Atestation a WHERE a.taux = :taux")
    , @NamedQuery(name = "Atestation.findByValidite", query = "SELECT a FROM Atestation a WHERE a.validite = :validite")})
public class Atestation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAtestation")
    private Integer idAtestation;
    @Size(max = 255)
    @Column(name = "assurance")
    private String assurance;
    @Size(max = 255)
    @Column(name = "billetordre")
    private String billetordre;
    @Size(max = 255)
    @Column(name = "commission")
    private String commission;
    @Size(max = 255)
    @Column(name = "dateAtestation")
    private String dateAtestation;
    @Size(max = 255)
    @Column(name = "fraisdossier")
    private String fraisdossier;
    @Column(name = "idClient")
    private Integer idClient;
    @Column(name = "idDemande")
    private Integer idDemande;
    @Size(max = 255)
    @Column(name = "mtt")
    private String mtt;
    @Size(max = 255)
    @Column(name = "prime")
    private String prime;
    @Size(max = 255)
    @Column(name = "taux")
    private String taux;
    @Size(max = 255)
    @Column(name = "validite")
    private String validite;

    public Atestation() {
    }

    public Atestation(Integer idAtestation) {
        this.idAtestation = idAtestation;
    }

    public Integer getIdAtestation() {
        return idAtestation;
    }

    public void setIdAtestation(Integer idAtestation) {
        this.idAtestation = idAtestation;
    }

    public String getAssurance() {
        return assurance;
    }

    public void setAssurance(String assurance) {
        this.assurance = assurance;
    }

    public String getBilletordre() {
        return billetordre;
    }

    public void setBilletordre(String billetordre) {
        this.billetordre = billetordre;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public String getDateAtestation() {
        return dateAtestation;
    }

    public void setDateAtestation(String dateAtestation) {
        this.dateAtestation = dateAtestation;
    }

    public String getFraisdossier() {
        return fraisdossier;
    }

    public void setFraisdossier(String fraisdossier) {
        this.fraisdossier = fraisdossier;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public Integer getIdDemande() {
        return idDemande;
    }

    public void setIdDemande(Integer idDemande) {
        this.idDemande = idDemande;
    }

    public String getMtt() {
        return mtt;
    }

    public void setMtt(String mtt) {
        this.mtt = mtt;
    }

    public String getPrime() {
        return prime;
    }

    public void setPrime(String prime) {
        this.prime = prime;
    }

    public String getTaux() {
        return taux;
    }

    public void setTaux(String taux) {
        this.taux = taux;
    }

    public String getValidite() {
        return validite;
    }

    public void setValidite(String validite) {
        this.validite = validite;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAtestation != null ? idAtestation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Atestation)) {
            return false;
        }
        Atestation other = (Atestation) object;
        if ((this.idAtestation == null && other.idAtestation != null) || (this.idAtestation != null && !this.idAtestation.equals(other.idAtestation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Atestation[ idAtestation=" + idAtestation + " ]";
    }
    
}
