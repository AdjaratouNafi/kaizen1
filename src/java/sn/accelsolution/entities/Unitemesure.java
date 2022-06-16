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
@Table(name = "unitemesure")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Unitemesure.findAll", query = "SELECT u FROM Unitemesure u")
    , @NamedQuery(name = "Unitemesure.findByIdUnitemesure", query = "SELECT u FROM Unitemesure u WHERE u.idUnitemesure = :idUnitemesure")
    , @NamedQuery(name = "Unitemesure.findByLibelle", query = "SELECT u FROM Unitemesure u WHERE u.libelle = :libelle")
    , @NamedQuery(name = "Unitemesure.findByAbreviation", query = "SELECT u FROM Unitemesure u WHERE u.abreviation = :abreviation")})
public class Unitemesure implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUnitemesure")
    private Integer idUnitemesure;
    @Size(max = 255)
    @Column(name = "libelle")
    private String libelle;
    @Size(max = 50)
    @Column(name = "abreviation")
    private String abreviation;
    @OneToMany(mappedBy = "idUnitemesure")
    private List<Prix> prixList;
    @OneToMany(mappedBy = "idUnitemesure")
    private List<Stock> stockList;
    @OneToMany(mappedBy = "idUnitemesure")
    private List<Detaillenewfacture> detaillenewfactureList;
    @OneToMany(mappedBy = "idUnitemesure")
    private List<Detailledevis> detailledevisList;
    @OneToMany(mappedBy = "idUnitemesure")
    private List<Detailledecomptep> detailledecomptepList;

    public Unitemesure() {
    }

    public Unitemesure(Integer idUnitemesure) {
        this.idUnitemesure = idUnitemesure;
    }

    public Integer getIdUnitemesure() {
        return idUnitemesure;
    }

    public void setIdUnitemesure(Integer idUnitemesure) {
        this.idUnitemesure = idUnitemesure;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getAbreviation() {
        return abreviation;
    }

    public void setAbreviation(String abreviation) {
        this.abreviation = abreviation;
    }

    @XmlTransient
    @JsonIgnore
    public List<Prix> getPrixList() {
        return prixList;
    }

    public void setPrixList(List<Prix> prixList) {
        this.prixList = prixList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Stock> getStockList() {
        return stockList;
    }

    public void setStockList(List<Stock> stockList) {
        this.stockList = stockList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Detaillenewfacture> getDetaillenewfactureList() {
        return detaillenewfactureList;
    }

    public void setDetaillenewfactureList(List<Detaillenewfacture> detaillenewfactureList) {
        this.detaillenewfactureList = detaillenewfactureList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Detailledevis> getDetailledevisList() {
        return detailledevisList;
    }

    public void setDetailledevisList(List<Detailledevis> detailledevisList) {
        this.detailledevisList = detailledevisList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Detailledecomptep> getDetailledecomptepList() {
        return detailledecomptepList;
    }

    public void setDetailledecomptepList(List<Detailledecomptep> detailledecomptepList) {
        this.detailledecomptepList = detailledecomptepList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUnitemesure != null ? idUnitemesure.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Unitemesure)) {
            return false;
        }
        Unitemesure other = (Unitemesure) object;
        if ((this.idUnitemesure == null && other.idUnitemesure != null) || (this.idUnitemesure != null && !this.idUnitemesure.equals(other.idUnitemesure))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Unitemesure[ idUnitemesure=" + idUnitemesure + " ]";
    }
    
}
