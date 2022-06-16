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
@Table(name = "detailleappeloffre")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detailleappeloffre.findAll", query = "SELECT d FROM Detailleappeloffre d")
    , @NamedQuery(name = "Detailleappeloffre.findByIdDetaille", query = "SELECT d FROM Detailleappeloffre d WHERE d.idDetaille = :idDetaille")
    , @NamedQuery(name = "Detailleappeloffre.findByDateaffectation", query = "SELECT d FROM Detailleappeloffre d WHERE d.dateaffectation = :dateaffectation")
    , @NamedQuery(name = "Detailleappeloffre.findByTache", query = "SELECT d FROM Detailleappeloffre d WHERE d.tache = :tache")})
public class Detailleappeloffre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDetaille")
    private Integer idDetaille;
    @Size(max = 50)
    @Column(name = "dateaffectation")
    private String dateaffectation;
    @Size(max = 50)
    @Column(name = "tache")
    private String tache;
    @JoinColumn(name = "idAppel", referencedColumnName = "idAppel")
    @ManyToOne
    private AppelOffre idAppel;
    @JoinColumn(name = "idUtilisateur", referencedColumnName = "idUtilisateur")
    @ManyToOne
    private Utilisateur idUtilisateur;

    public Detailleappeloffre() {
    }

    public Detailleappeloffre(Integer idDetaille) {
        this.idDetaille = idDetaille;
    }

    public Integer getIdDetaille() {
        return idDetaille;
    }

    public void setIdDetaille(Integer idDetaille) {
        this.idDetaille = idDetaille;
    }

    public String getDateaffectation() {
        return dateaffectation;
    }

    public void setDateaffectation(String dateaffectation) {
        this.dateaffectation = dateaffectation;
    }

    public String getTache() {
        return tache;
    }

    public void setTache(String tache) {
        this.tache = tache;
    }

    public AppelOffre getIdAppel() {
        return idAppel;
    }

    public void setIdAppel(AppelOffre idAppel) {
        this.idAppel = idAppel;
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
        hash += (idDetaille != null ? idDetaille.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detailleappeloffre)) {
            return false;
        }
        Detailleappeloffre other = (Detailleappeloffre) object;
        if ((this.idDetaille == null && other.idDetaille != null) || (this.idDetaille != null && !this.idDetaille.equals(other.idDetaille))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Detailleappeloffre[ idDetaille=" + idDetaille + " ]";
    }
    
}
