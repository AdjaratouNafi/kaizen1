/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DV7
 */
@Entity
@Table(name = "conge")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conge.findAll", query = "SELECT c FROM Conge c")
    , @NamedQuery(name = "Conge.findByIdConge", query = "SELECT c FROM Conge c WHERE c.idConge = :idConge")
    , @NamedQuery(name = "Conge.findByDateDebut", query = "SELECT c FROM Conge c WHERE c.dateDebut = :dateDebut")
    , @NamedQuery(name = "Conge.findByDateFin", query = "SELECT c FROM Conge c WHERE c.dateFin = :dateFin")
    , @NamedQuery(name = "Conge.findByMotifConge", query = "SELECT c FROM Conge c WHERE c.motifConge = :motifConge")})
public class Conge implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idConge")
    private Integer idConge;
    @Column(name = "dateDebut")
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @Column(name = "dateFin")
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    @Size(max = 50)
    @Column(name = "motifConge")
    private String motifConge;
    @JoinColumn(name = "idUtilisateur", referencedColumnName = "idUtilisateur")
    @ManyToOne
    private Utilisateur idUtilisateur;

    public Conge() {
    }

    public Conge(Integer idConge) {
        this.idConge = idConge;
    }

    public Integer getIdConge() {
        return idConge;
    }

    public void setIdConge(Integer idConge) {
        this.idConge = idConge;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getMotifConge() {
        return motifConge;
    }

    public void setMotifConge(String motifConge) {
        this.motifConge = motifConge;
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
        hash += (idConge != null ? idConge.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conge)) {
            return false;
        }
        Conge other = (Conge) object;
        if ((this.idConge == null && other.idConge != null) || (this.idConge != null && !this.idConge.equals(other.idConge))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Conge[ idConge=" + idConge + " ]";
    }
    
}
