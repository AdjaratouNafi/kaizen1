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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "marchandise")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Marchandise.findAll", query = "SELECT m FROM Marchandise m")
    , @NamedQuery(name = "Marchandise.findByIdMarchandise", query = "SELECT m FROM Marchandise m WHERE m.idMarchandise = :idMarchandise")
    , @NamedQuery(name = "Marchandise.findByLibelle", query = "SELECT m FROM Marchandise m WHERE m.libelle = :libelle")
    , @NamedQuery(name = "Marchandise.findByPrixunitaire", query = "SELECT m FROM Marchandise m WHERE m.prixunitaire = :prixunitaire")})
public class Marchandise implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMarchandise")
    private Integer idMarchandise;
    @Size(max = 50)
    @Column(name = "libelle")
    private String libelle;
    @Size(max = 50)
    @Column(name = "prixunitaire")
    private String prixunitaire;
    @OneToMany(mappedBy = "idMarchandise")
    private List<Prix> prixList;
    @OneToMany(mappedBy = "idMarchandise")
    private List<Stock> stockList;
    @JoinColumn(name = "idCorpsetat", referencedColumnName = "idCorpsetat")
    @ManyToOne
    private Corpsetat idCorpsetat;
    @JoinColumn(name = "idLottechnique", referencedColumnName = "idLottehcnique")
    @ManyToOne
    private Lottechnique idLottechnique;
    @OneToMany(mappedBy = "idMateriel")
    private List<Deboursser> deboursserList;
    @OneToMany(mappedBy = "idMarchandise")
    private List<DetailleCommande> detailleCommandeList;
    @OneToMany(mappedBy = "idMarchandise")
    private List<Vente> venteList;

    public Marchandise() {
    }

    public Marchandise(Integer idMarchandise) {
        this.idMarchandise = idMarchandise;
    }

    public Integer getIdMarchandise() {
        return idMarchandise;
    }

    public void setIdMarchandise(Integer idMarchandise) {
        this.idMarchandise = idMarchandise;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getPrixunitaire() {
        return prixunitaire;
    }

    public void setPrixunitaire(String prixunitaire) {
        this.prixunitaire = prixunitaire;
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

    public Corpsetat getIdCorpsetat() {
        return idCorpsetat;
    }

    public void setIdCorpsetat(Corpsetat idCorpsetat) {
        this.idCorpsetat = idCorpsetat;
    }

    public Lottechnique getIdLottechnique() {
        return idLottechnique;
    }

    public void setIdLottechnique(Lottechnique idLottechnique) {
        this.idLottechnique = idLottechnique;
    }

    @XmlTransient
    @JsonIgnore
    public List<Deboursser> getDeboursserList() {
        return deboursserList;
    }

    public void setDeboursserList(List<Deboursser> deboursserList) {
        this.deboursserList = deboursserList;
    }

    @XmlTransient
    @JsonIgnore
    public List<DetailleCommande> getDetailleCommandeList() {
        return detailleCommandeList;
    }

    public void setDetailleCommandeList(List<DetailleCommande> detailleCommandeList) {
        this.detailleCommandeList = detailleCommandeList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Vente> getVenteList() {
        return venteList;
    }

    public void setVenteList(List<Vente> venteList) {
        this.venteList = venteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMarchandise != null ? idMarchandise.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Marchandise)) {
            return false;
        }
        Marchandise other = (Marchandise) object;
        if ((this.idMarchandise == null && other.idMarchandise != null) || (this.idMarchandise != null && !this.idMarchandise.equals(other.idMarchandise))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Marchandise[ idMarchandise=" + idMarchandise + " ]";
    }
    
}
