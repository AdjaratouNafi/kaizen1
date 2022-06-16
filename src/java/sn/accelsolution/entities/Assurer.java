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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DV7
 */
@Entity
@Table(name = "assurer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Assurer.findAll", query = "SELECT a FROM Assurer a")
    , @NamedQuery(name = "Assurer.findByIdAssurer", query = "SELECT a FROM Assurer a WHERE a.idAssurer = :idAssurer")})
public class Assurer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAssurer")
    private Integer idAssurer;
    @JoinColumn(name = "idAssurance", referencedColumnName = "idAssurance")
    @ManyToOne
    private Assurance idAssurance;
    @JoinColumn(name = "idAssureur", referencedColumnName = "idAssureur")
    @ManyToOne
    private Assureur idAssureur;
    @JoinColumn(name = "idUtilisateur", referencedColumnName = "idUtilisateur")
    @ManyToOne
    private Utilisateur idUtilisateur;

    public Assurer() {
    }

    public Assurer(Integer idAssurer) {
        this.idAssurer = idAssurer;
    }

    public Integer getIdAssurer() {
        return idAssurer;
    }

    public void setIdAssurer(Integer idAssurer) {
        this.idAssurer = idAssurer;
    }

    public Assurance getIdAssurance() {
        return idAssurance;
    }

    public void setIdAssurance(Assurance idAssurance) {
        this.idAssurance = idAssurance;
    }

    public Assureur getIdAssureur() {
        return idAssureur;
    }

    public void setIdAssureur(Assureur idAssureur) {
        this.idAssureur = idAssureur;
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
        hash += (idAssurer != null ? idAssurer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Assurer)) {
            return false;
        }
        Assurer other = (Assurer) object;
        if ((this.idAssurer == null && other.idAssurer != null) || (this.idAssurer != null && !this.idAssurer.equals(other.idAssurer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Assurer[ idAssurer=" + idAssurer + " ]";
    }
    
}
