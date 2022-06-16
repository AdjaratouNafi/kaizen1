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
@Table(name = "conjoint")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conjoint.findAll", query = "SELECT c FROM Conjoint c")
    , @NamedQuery(name = "Conjoint.findByIdConjoint", query = "SELECT c FROM Conjoint c WHERE c.idConjoint = :idConjoint")
    , @NamedQuery(name = "Conjoint.findByAdresse", query = "SELECT c FROM Conjoint c WHERE c.adresse = :adresse")
    , @NamedQuery(name = "Conjoint.findByIdClient", query = "SELECT c FROM Conjoint c WHERE c.idClient = :idClient")
    , @NamedQuery(name = "Conjoint.findByNomConjoint", query = "SELECT c FROM Conjoint c WHERE c.nomConjoint = :nomConjoint")
    , @NamedQuery(name = "Conjoint.findByPrenomConjoint", query = "SELECT c FROM Conjoint c WHERE c.prenomConjoint = :prenomConjoint")
    , @NamedQuery(name = "Conjoint.findByProfession", query = "SELECT c FROM Conjoint c WHERE c.profession = :profession")
    , @NamedQuery(name = "Conjoint.findByTelephone", query = "SELECT c FROM Conjoint c WHERE c.telephone = :telephone")})
public class Conjoint implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idConjoint")
    private Integer idConjoint;
    @Size(max = 255)
    @Column(name = "adresse")
    private String adresse;
    @Column(name = "idClient")
    private Integer idClient;
    @Size(max = 255)
    @Column(name = "nomConjoint")
    private String nomConjoint;
    @Size(max = 255)
    @Column(name = "prenomConjoint")
    private String prenomConjoint;
    @Size(max = 255)
    @Column(name = "profession")
    private String profession;
    @Size(max = 255)
    @Column(name = "telephone")
    private String telephone;

    public Conjoint() {
    }

    public Conjoint(Integer idConjoint) {
        this.idConjoint = idConjoint;
    }

    public Integer getIdConjoint() {
        return idConjoint;
    }

    public void setIdConjoint(Integer idConjoint) {
        this.idConjoint = idConjoint;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public String getNomConjoint() {
        return nomConjoint;
    }

    public void setNomConjoint(String nomConjoint) {
        this.nomConjoint = nomConjoint;
    }

    public String getPrenomConjoint() {
        return prenomConjoint;
    }

    public void setPrenomConjoint(String prenomConjoint) {
        this.prenomConjoint = prenomConjoint;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConjoint != null ? idConjoint.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conjoint)) {
            return false;
        }
        Conjoint other = (Conjoint) object;
        if ((this.idConjoint == null && other.idConjoint != null) || (this.idConjoint != null && !this.idConjoint.equals(other.idConjoint))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Conjoint[ idConjoint=" + idConjoint + " ]";
    }
    
}
