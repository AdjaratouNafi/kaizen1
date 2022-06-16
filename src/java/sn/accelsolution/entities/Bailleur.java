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
@Table(name = "bailleur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bailleur.findAll", query = "SELECT b FROM Bailleur b")
    , @NamedQuery(name = "Bailleur.findByIdBailleur", query = "SELECT b FROM Bailleur b WHERE b.idBailleur = :idBailleur")
    , @NamedQuery(name = "Bailleur.findByNomBailleur", query = "SELECT b FROM Bailleur b WHERE b.nomBailleur = :nomBailleur")
    , @NamedQuery(name = "Bailleur.findByAdresseBailleur", query = "SELECT b FROM Bailleur b WHERE b.adresseBailleur = :adresseBailleur")
    , @NamedQuery(name = "Bailleur.findByTelBailleur", query = "SELECT b FROM Bailleur b WHERE b.telBailleur = :telBailleur")
    , @NamedQuery(name = "Bailleur.findByBpBailleur", query = "SELECT b FROM Bailleur b WHERE b.bpBailleur = :bpBailleur")
    , @NamedQuery(name = "Bailleur.findByMailBailleur", query = "SELECT b FROM Bailleur b WHERE b.mailBailleur = :mailBailleur")})
public class Bailleur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idBailleur")
    private Integer idBailleur;
    @Size(max = 50)
    @Column(name = "nomBailleur")
    private String nomBailleur;
    @Size(max = 50)
    @Column(name = "adresseBailleur")
    private String adresseBailleur;
    @Size(max = 50)
    @Column(name = "telBailleur")
    private String telBailleur;
    @Size(max = 50)
    @Column(name = "bpBailleur")
    private String bpBailleur;
    @Size(max = 50)
    @Column(name = "mailBailleur")
    private String mailBailleur;
    @OneToMany(mappedBy = "idBailleur")
    private List<Decompte> decompteList;
    @OneToMany(mappedBy = "idBailleur")
    private List<Marche> marcheList;

    public Bailleur() {
    }

    public Bailleur(Integer idBailleur) {
        this.idBailleur = idBailleur;
    }

    public Integer getIdBailleur() {
        return idBailleur;
    }

    public void setIdBailleur(Integer idBailleur) {
        this.idBailleur = idBailleur;
    }

    public String getNomBailleur() {
        return nomBailleur;
    }

    public void setNomBailleur(String nomBailleur) {
        this.nomBailleur = nomBailleur;
    }

    public String getAdresseBailleur() {
        return adresseBailleur;
    }

    public void setAdresseBailleur(String adresseBailleur) {
        this.adresseBailleur = adresseBailleur;
    }

    public String getTelBailleur() {
        return telBailleur;
    }

    public void setTelBailleur(String telBailleur) {
        this.telBailleur = telBailleur;
    }

    public String getBpBailleur() {
        return bpBailleur;
    }

    public void setBpBailleur(String bpBailleur) {
        this.bpBailleur = bpBailleur;
    }

    public String getMailBailleur() {
        return mailBailleur;
    }

    public void setMailBailleur(String mailBailleur) {
        this.mailBailleur = mailBailleur;
    }

    @XmlTransient
    @JsonIgnore
    public List<Decompte> getDecompteList() {
        return decompteList;
    }

    public void setDecompteList(List<Decompte> decompteList) {
        this.decompteList = decompteList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Marche> getMarcheList() {
        return marcheList;
    }

    public void setMarcheList(List<Marche> marcheList) {
        this.marcheList = marcheList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBailleur != null ? idBailleur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bailleur)) {
            return false;
        }
        Bailleur other = (Bailleur) object;
        if ((this.idBailleur == null && other.idBailleur != null) || (this.idBailleur != null && !this.idBailleur.equals(other.idBailleur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Bailleur[ idBailleur=" + idBailleur + " ]";
    }
    
}
