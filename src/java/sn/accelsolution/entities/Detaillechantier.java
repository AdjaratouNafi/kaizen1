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
@Table(name = "detaillechantier")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detaillechantier.findAll", query = "SELECT d FROM Detaillechantier d")
    , @NamedQuery(name = "Detaillechantier.findByIdDetailleChantier", query = "SELECT d FROM Detaillechantier d WHERE d.idDetailleChantier = :idDetailleChantier")
    , @NamedQuery(name = "Detaillechantier.findByDateAlocation", query = "SELECT d FROM Detaillechantier d WHERE d.dateAlocation = :dateAlocation")
    , @NamedQuery(name = "Detaillechantier.findByQt", query = "SELECT d FROM Detaillechantier d WHERE d.qt = :qt")})
public class Detaillechantier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDetailleChantier")
    private Integer idDetailleChantier;
    @Size(max = 50)
    @Column(name = "dateAlocation")
    private String dateAlocation;
    @Column(name = "qt")
    private Integer qt;
    @JoinColumn(name = "idPrestataire", referencedColumnName = "idPrestataire")
    @ManyToOne
    private Prestataire idPrestataire;
    @JoinColumn(name = "idprestatairePrim", referencedColumnName = "idprestatairePrim")
    @ManyToOne
    private Prestataireprim idprestatairePrim;
    @JoinColumn(name = "idChantier", referencedColumnName = "idChantier")
    @ManyToOne
    private Chantier idChantier;
    @JoinColumn(name = "idRessource", referencedColumnName = "idRessource")
    @ManyToOne
    private Ressource idRessource;
    @JoinColumn(name = "idUtilisateur", referencedColumnName = "idUtilisateur")
    @ManyToOne
    private Utilisateur idUtilisateur;

    public Detaillechantier() {
    }

    public Detaillechantier(Integer idDetailleChantier) {
        this.idDetailleChantier = idDetailleChantier;
    }

    public Integer getIdDetailleChantier() {
        return idDetailleChantier;
    }

    public void setIdDetailleChantier(Integer idDetailleChantier) {
        this.idDetailleChantier = idDetailleChantier;
    }

    public String getDateAlocation() {
        return dateAlocation;
    }

    public void setDateAlocation(String dateAlocation) {
        this.dateAlocation = dateAlocation;
    }

    public Integer getQt() {
        return qt;
    }

    public void setQt(Integer qt) {
        this.qt = qt;
    }

    public Prestataire getIdPrestataire() {
        return idPrestataire;
    }

    public void setIdPrestataire(Prestataire idPrestataire) {
        this.idPrestataire = idPrestataire;
    }

    public Prestataireprim getIdprestatairePrim() {
        return idprestatairePrim;
    }

    public void setIdprestatairePrim(Prestataireprim idprestatairePrim) {
        this.idprestatairePrim = idprestatairePrim;
    }

    public Chantier getIdChantier() {
        return idChantier;
    }

    public void setIdChantier(Chantier idChantier) {
        this.idChantier = idChantier;
    }

    public Ressource getIdRessource() {
        return idRessource;
    }

    public void setIdRessource(Ressource idRessource) {
        this.idRessource = idRessource;
    }

    public Utilisateur getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Utilisateur idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetailleChantier != null ? idDetailleChantier.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detaillechantier)) {
            return false;
        }
        Detaillechantier other = (Detaillechantier) object;
        if ((this.idDetailleChantier == null && other.idDetailleChantier != null) || (this.idDetailleChantier != null && !this.idDetailleChantier.equals(other.idDetailleChantier))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Detaillechantier[ idDetailleChantier=" + idDetailleChantier + " ]";
    }
    
}
