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
@Table(name = "acompte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Acompte.findAll", query = "SELECT a FROM Acompte a")
    , @NamedQuery(name = "Acompte.findByIdAcompte", query = "SELECT a FROM Acompte a WHERE a.idAcompte = :idAcompte")
    , @NamedQuery(name = "Acompte.findByEcheanceAcompte", query = "SELECT a FROM Acompte a WHERE a.echeanceAcompte = :echeanceAcompte")
    , @NamedQuery(name = "Acompte.findByMontantAcompte", query = "SELECT a FROM Acompte a WHERE a.montantAcompte = :montantAcompte")
    , @NamedQuery(name = "Acompte.findByDateAcompte", query = "SELECT a FROM Acompte a WHERE a.dateAcompte = :dateAcompte")
    , @NamedQuery(name = "Acompte.findByNumerocheque", query = "SELECT a FROM Acompte a WHERE a.numerocheque = :numerocheque")
    , @NamedQuery(name = "Acompte.findByChoixtva", query = "SELECT a FROM Acompte a WHERE a.choixtva = :choixtva")
    , @NamedQuery(name = "Acompte.findByNumeroacompte", query = "SELECT a FROM Acompte a WHERE a.numeroacompte = :numeroacompte")})
public class Acompte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAcompte")
    private Integer idAcompte;
    @Size(max = 250)
    @Column(name = "echeanceAcompte")
    private String echeanceAcompte;
    @Size(max = 250)
    @Column(name = "montantAcompte")
    private String montantAcompte;
    @Size(max = 250)
    @Column(name = "dateAcompte")
    private String dateAcompte;
    @Size(max = 255)
    @Column(name = "numerocheque")
    private String numerocheque;
    @Size(max = 50)
    @Column(name = "choixtva")
    private String choixtva;
    @Size(max = 250)
    @Column(name = "numeroacompte")
    private String numeroacompte;
    @JoinColumn(name = "idChantier", referencedColumnName = "idChantier")
    @ManyToOne
    private Chantier idChantier;
    @JoinColumn(name = "idPrestataire", referencedColumnName = "idPrestataire")
    @ManyToOne
    private Prestataire idPrestataire;

    public Acompte() {
    }

    public Acompte(Integer idAcompte) {
        this.idAcompte = idAcompte;
    }

    public Integer getIdAcompte() {
        return idAcompte;
    }

    public void setIdAcompte(Integer idAcompte) {
        this.idAcompte = idAcompte;
    }

    public String getEcheanceAcompte() {
        return echeanceAcompte;
    }

    public void setEcheanceAcompte(String echeanceAcompte) {
        this.echeanceAcompte = echeanceAcompte;
    }

    public String getMontantAcompte() {
        return montantAcompte;
    }

    public void setMontantAcompte(String montantAcompte) {
        this.montantAcompte = montantAcompte;
    }

    public String getDateAcompte() {
        return dateAcompte;
    }

    public void setDateAcompte(String dateAcompte) {
        this.dateAcompte = dateAcompte;
    }

    public String getNumerocheque() {
        return numerocheque;
    }

    public void setNumerocheque(String numerocheque) {
        this.numerocheque = numerocheque;
    }

    public String getChoixtva() {
        return choixtva;
    }

    public void setChoixtva(String choixtva) {
        this.choixtva = choixtva;
    }

    public String getNumeroacompte() {
        return numeroacompte;
    }

    public void setNumeroacompte(String numeroacompte) {
        this.numeroacompte = numeroacompte;
    }

    public Chantier getIdChantier() {
        return idChantier;
    }

    public void setIdChantier(Chantier idChantier) {
        this.idChantier = idChantier;
    }

    public Prestataire getIdPrestataire() {
        return idPrestataire;
    }

    public void setIdPrestataire(Prestataire idPrestataire) {
        this.idPrestataire = idPrestataire;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAcompte != null ? idAcompte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Acompte)) {
            return false;
        }
        Acompte other = (Acompte) object;
        if ((this.idAcompte == null && other.idAcompte != null) || (this.idAcompte != null && !this.idAcompte.equals(other.idAcompte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Acompte[ idAcompte=" + idAcompte + " ]";
    }
    
}
