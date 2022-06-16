/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author DV7
 */
@Entity
@Table(name = "assureur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Assureur.findAll", query = "SELECT a FROM Assureur a")
    , @NamedQuery(name = "Assureur.findByIdAssureur", query = "SELECT a FROM Assureur a WHERE a.idAssureur = :idAssureur")
    , @NamedQuery(name = "Assureur.findByNomAssureur", query = "SELECT a FROM Assureur a WHERE a.nomAssureur = :nomAssureur")
    , @NamedQuery(name = "Assureur.findByTelephoneAssureur", query = "SELECT a FROM Assureur a WHERE a.telephoneAssureur = :telephoneAssureur")
    , @NamedQuery(name = "Assureur.findByAdresseAssureur", query = "SELECT a FROM Assureur a WHERE a.adresseAssureur = :adresseAssureur")
    , @NamedQuery(name = "Assureur.findByBpAssureur", query = "SELECT a FROM Assureur a WHERE a.bpAssureur = :bpAssureur")
    , @NamedQuery(name = "Assureur.findByMailAssureur", query = "SELECT a FROM Assureur a WHERE a.mailAssureur = :mailAssureur")})
public class Assureur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAssureur")
    private Integer idAssureur;
    @Size(max = 50)
    @Column(name = "nomAssureur")
    private String nomAssureur;
    @Size(max = 50)
    @Column(name = "telephoneAssureur")
    private String telephoneAssureur;
    @Size(max = 50)
    @Column(name = "adresseAssureur")
    private String adresseAssureur;
    @Size(max = 50)
    @Column(name = "bpAssureur")
    private String bpAssureur;
    @Size(max = 50)
    @Column(name = "mailAssureur")
    private String mailAssureur;
    @OneToMany(mappedBy = "idAssureur")
    private List<Assurer> assurerList;
    @OneToMany(mappedBy = "idAssureur")
    private List<DetailleAssurance> detailleAssuranceList;

    public Assureur() {
    }

    public Assureur(Integer idAssureur) {
        this.idAssureur = idAssureur;
    }

    public Integer getIdAssureur() {
        return idAssureur;
    }

    public void setIdAssureur(Integer idAssureur) {
        this.idAssureur = idAssureur;
    }

    public String getNomAssureur() {
        return nomAssureur;
    }

    public void setNomAssureur(String nomAssureur) {
        this.nomAssureur = nomAssureur;
    }

    public String getTelephoneAssureur() {
        return telephoneAssureur;
    }

    public void setTelephoneAssureur(String telephoneAssureur) {
        this.telephoneAssureur = telephoneAssureur;
    }

    public String getAdresseAssureur() {
        return adresseAssureur;
    }

    public void setAdresseAssureur(String adresseAssureur) {
        this.adresseAssureur = adresseAssureur;
    }

    public String getBpAssureur() {
        return bpAssureur;
    }

    public void setBpAssureur(String bpAssureur) {
        this.bpAssureur = bpAssureur;
    }

    public String getMailAssureur() {
        return mailAssureur;
    }

    public void setMailAssureur(String mailAssureur) {
        this.mailAssureur = mailAssureur;
    }

    @XmlTransient
    @JsonIgnore
    public List<Assurer> getAssurerList() {
        return assurerList;
    }

    public void setAssurerList(List<Assurer> assurerList) {
        this.assurerList = assurerList;
    }

    @XmlTransient
    @JsonIgnore
    public List<DetailleAssurance> getDetailleAssuranceList() {
        return detailleAssuranceList;
    }

    public void setDetailleAssuranceList(List<DetailleAssurance> detailleAssuranceList) {
        this.detailleAssuranceList = detailleAssuranceList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAssureur != null ? idAssureur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Assureur)) {
            return false;
        }
        Assureur other = (Assureur) object;
        if ((this.idAssureur == null && other.idAssureur != null) || (this.idAssureur != null && !this.idAssureur.equals(other.idAssureur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Assureur[ idAssureur=" + idAssureur + " ]";
    }
    
}
