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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DV7
 */
@Entity
@Table(name = "user_indemnite")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserIndemnite.findAll", query = "SELECT u FROM UserIndemnite u")
    , @NamedQuery(name = "UserIndemnite.findByIdUserIndemnite", query = "SELECT u FROM UserIndemnite u WHERE u.idUserIndemnite = :idUserIndemnite")
    , @NamedQuery(name = "UserIndemnite.findByDateCreation", query = "SELECT u FROM UserIndemnite u WHERE u.dateCreation = :dateCreation")})
public class UserIndemnite implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUserIndemnite")
    private Integer idUserIndemnite;
    @Column(name = "dateCreation")
    @Temporal(TemporalType.DATE)
    private Date dateCreation;
    @JoinColumn(name = "idIndemnite", referencedColumnName = "idIndemnite")
    @ManyToOne(optional = false)
    private Indemnite idIndemnite;
    @JoinColumn(name = "idUtilisateur", referencedColumnName = "idUtilisateur")
    @ManyToOne(optional = false)
    private Utilisateur idUtilisateur;

    public UserIndemnite() {
    }

    public UserIndemnite(Integer idUserIndemnite) {
        this.idUserIndemnite = idUserIndemnite;
    }

    public Integer getIdUserIndemnite() {
        return idUserIndemnite;
    }

    public void setIdUserIndemnite(Integer idUserIndemnite) {
        this.idUserIndemnite = idUserIndemnite;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Indemnite getIdIndemnite() {
        return idIndemnite;
    }

    public void setIdIndemnite(Indemnite idIndemnite) {
        this.idIndemnite = idIndemnite;
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
        hash += (idUserIndemnite != null ? idUserIndemnite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserIndemnite)) {
            return false;
        }
        UserIndemnite other = (UserIndemnite) object;
        if ((this.idUserIndemnite == null && other.idUserIndemnite != null) || (this.idUserIndemnite != null && !this.idUserIndemnite.equals(other.idUserIndemnite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.UserIndemnite[ idUserIndemnite=" + idUserIndemnite + " ]";
    }
    
}
