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
@Table(name = "prospect_facture")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProspectFacture.findAll", query = "SELECT p FROM ProspectFacture p")
    , @NamedQuery(name = "ProspectFacture.findByIdProspectFacture", query = "SELECT p FROM ProspectFacture p WHERE p.idProspectFacture = :idProspectFacture")
    , @NamedQuery(name = "ProspectFacture.findByDateProspectFacture", query = "SELECT p FROM ProspectFacture p WHERE p.dateProspectFacture = :dateProspectFacture")
    , @NamedQuery(name = "ProspectFacture.findByRemise", query = "SELECT p FROM ProspectFacture p WHERE p.remise = :remise")
    , @NamedQuery(name = "ProspectFacture.findByMontant", query = "SELECT p FROM ProspectFacture p WHERE p.montant = :montant")
    , @NamedQuery(name = "ProspectFacture.findByDateEchance", query = "SELECT p FROM ProspectFacture p WHERE p.dateEchance = :dateEchance")
    , @NamedQuery(name = "ProspectFacture.findByDesignation", query = "SELECT p FROM ProspectFacture p WHERE p.designation = :designation")
    , @NamedQuery(name = "ProspectFacture.findByObservation", query = "SELECT p FROM ProspectFacture p WHERE p.observation = :observation")
    , @NamedQuery(name = "ProspectFacture.findByPu", query = "SELECT p FROM ProspectFacture p WHERE p.pu = :pu")
    , @NamedQuery(name = "ProspectFacture.findByQuantite", query = "SELECT p FROM ProspectFacture p WHERE p.quantite = :quantite")
    , @NamedQuery(name = "ProspectFacture.findByPuhortaxe", query = "SELECT p FROM ProspectFacture p WHERE p.puhortaxe = :puhortaxe")
    , @NamedQuery(name = "ProspectFacture.findByMontanthortaxe", query = "SELECT p FROM ProspectFacture p WHERE p.montanthortaxe = :montanthortaxe")
    , @NamedQuery(name = "ProspectFacture.findByTtc", query = "SELECT p FROM ProspectFacture p WHERE p.ttc = :ttc")})
public class ProspectFacture implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProspectFacture")
    private Integer idProspectFacture;
    @Size(max = 50)
    @Column(name = "dateProspectFacture")
    private String dateProspectFacture;
    @Size(max = 50)
    @Column(name = "remise")
    private String remise;
    @Size(max = 50)
    @Column(name = "montant")
    private String montant;
    @Size(max = 50)
    @Column(name = "dateEchance")
    private String dateEchance;
    @Size(max = 50)
    @Column(name = "designation")
    private String designation;
    @Size(max = 50)
    @Column(name = "observation")
    private String observation;
    @Size(max = 50)
    @Column(name = "pu")
    private String pu;
    @Column(name = "quantite")
    private Integer quantite;
    @Size(max = 50)
    @Column(name = "puhortaxe")
    private String puhortaxe;
    @Size(max = 50)
    @Column(name = "montanthortaxe")
    private String montanthortaxe;
    @Size(max = 50)
    @Column(name = "ttc")
    private String ttc;
    @JoinColumn(name = "idFacture", referencedColumnName = "idFacture")
    @ManyToOne
    private Facture idFacture;
    @JoinColumn(name = "idProspect", referencedColumnName = "idProspect")
    @ManyToOne
    private Prospection idProspect;

    public ProspectFacture() {
    }

    public ProspectFacture(Integer idProspectFacture) {
        this.idProspectFacture = idProspectFacture;
    }

    public Integer getIdProspectFacture() {
        return idProspectFacture;
    }

    public void setIdProspectFacture(Integer idProspectFacture) {
        this.idProspectFacture = idProspectFacture;
    }

    public String getDateProspectFacture() {
        return dateProspectFacture;
    }

    public void setDateProspectFacture(String dateProspectFacture) {
        this.dateProspectFacture = dateProspectFacture;
    }

    public String getRemise() {
        return remise;
    }

    public void setRemise(String remise) {
        this.remise = remise;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }

    public String getDateEchance() {
        return dateEchance;
    }

    public void setDateEchance(String dateEchance) {
        this.dateEchance = dateEchance;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getPu() {
        return pu;
    }

    public void setPu(String pu) {
        this.pu = pu;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public String getPuhortaxe() {
        return puhortaxe;
    }

    public void setPuhortaxe(String puhortaxe) {
        this.puhortaxe = puhortaxe;
    }

    public String getMontanthortaxe() {
        return montanthortaxe;
    }

    public void setMontanthortaxe(String montanthortaxe) {
        this.montanthortaxe = montanthortaxe;
    }

    public String getTtc() {
        return ttc;
    }

    public void setTtc(String ttc) {
        this.ttc = ttc;
    }

    public Facture getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(Facture idFacture) {
        this.idFacture = idFacture;
    }

    public Prospection getIdProspect() {
        return idProspect;
    }

    public void setIdProspect(Prospection idProspect) {
        this.idProspect = idProspect;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProspectFacture != null ? idProspectFacture.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProspectFacture)) {
            return false;
        }
        ProspectFacture other = (ProspectFacture) object;
        if ((this.idProspectFacture == null && other.idProspectFacture != null) || (this.idProspectFacture != null && !this.idProspectFacture.equals(other.idProspectFacture))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.ProspectFacture[ idProspectFacture=" + idProspectFacture + " ]";
    }
    
}
