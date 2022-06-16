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
import javax.persistence.Lob;
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
@Table(name = "ressource")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ressource.findAll", query = "SELECT r FROM Ressource r")
    , @NamedQuery(name = "Ressource.findByIdRessource", query = "SELECT r FROM Ressource r WHERE r.idRessource = :idRessource")
    , @NamedQuery(name = "Ressource.findByNomRessource", query = "SELECT r FROM Ressource r WHERE r.nomRessource = :nomRessource")
    , @NamedQuery(name = "Ressource.findByDateCreation", query = "SELECT r FROM Ressource r WHERE r.dateCreation = :dateCreation")
    , @NamedQuery(name = "Ressource.findByTyperessource", query = "SELECT r FROM Ressource r WHERE r.typeressource = :typeressource")
    , @NamedQuery(name = "Ressource.findByEtatressource", query = "SELECT r FROM Ressource r WHERE r.etatressource = :etatressource")
    , @NamedQuery(name = "Ressource.findByQtStock", query = "SELECT r FROM Ressource r WHERE r.qtStock = :qtStock")
    , @NamedQuery(name = "Ressource.findByQtSeuill", query = "SELECT r FROM Ressource r WHERE r.qtSeuill = :qtSeuill")
    , @NamedQuery(name = "Ressource.findByMesrure", query = "SELECT r FROM Ressource r WHERE r.mesrure = :mesrure")
    , @NamedQuery(name = "Ressource.findByPu", query = "SELECT r FROM Ressource r WHERE r.pu = :pu")})
public class Ressource implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRessource")
    private Integer idRessource;
    @Size(max = 50)
    @Column(name = "nomRessource")
    private String nomRessource;
    @Size(max = 50)
    @Column(name = "dateCreation")
    private String dateCreation;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "descriptionResssource")
    private String descriptionResssource;
    @Size(max = 50)
    @Column(name = "typeressource")
    private String typeressource;
    @Size(max = 10)
    @Column(name = "etatressource")
    private String etatressource;
    @Column(name = "qtStock")
    private Integer qtStock;
    @Column(name = "qtSeuill")
    private Integer qtSeuill;
    @Size(max = 50)
    @Column(name = "mesrure")
    private String mesrure;
    @Size(max = 50)
    @Column(name = "pu")
    private String pu;
    @OneToMany(mappedBy = "idRessource")
    private List<Detaillechantier> detaillechantierList;
    @OneToMany(mappedBy = "idRessource")
    private List<DetailleCommande> detailleCommandeList;
    @JoinColumn(name = "idFournisseur", referencedColumnName = "idFournisseur")
    @ManyToOne
    private Fournisseur idFournisseur;
    @JoinColumn(name = "idTypeRessource", referencedColumnName = "idTypeRessource")
    @ManyToOne
    private TypeRessource idTypeRessource;

    public Ressource() {
    }

    public Ressource(Integer idRessource) {
        this.idRessource = idRessource;
    }

    public Integer getIdRessource() {
        return idRessource;
    }

    public void setIdRessource(Integer idRessource) {
        this.idRessource = idRessource;
    }

    public String getNomRessource() {
        return nomRessource;
    }

    public void setNomRessource(String nomRessource) {
        this.nomRessource = nomRessource;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getDescriptionResssource() {
        return descriptionResssource;
    }

    public void setDescriptionResssource(String descriptionResssource) {
        this.descriptionResssource = descriptionResssource;
    }

    public String getTyperessource() {
        return typeressource;
    }

    public void setTyperessource(String typeressource) {
        this.typeressource = typeressource;
    }

    public String getEtatressource() {
        return etatressource;
    }

    public void setEtatressource(String etatressource) {
        this.etatressource = etatressource;
    }

    public Integer getQtStock() {
        return qtStock;
    }

    public void setQtStock(Integer qtStock) {
        this.qtStock = qtStock;
    }

    public Integer getQtSeuill() {
        return qtSeuill;
    }

    public void setQtSeuill(Integer qtSeuill) {
        this.qtSeuill = qtSeuill;
    }

    public String getMesrure() {
        return mesrure;
    }

    public void setMesrure(String mesrure) {
        this.mesrure = mesrure;
    }

    public String getPu() {
        return pu;
    }

    public void setPu(String pu) {
        this.pu = pu;
    }

    @XmlTransient
    @JsonIgnore
    public List<Detaillechantier> getDetaillechantierList() {
        return detaillechantierList;
    }

    public void setDetaillechantierList(List<Detaillechantier> detaillechantierList) {
        this.detaillechantierList = detaillechantierList;
    }

    @XmlTransient
    @JsonIgnore
    public List<DetailleCommande> getDetailleCommandeList() {
        return detailleCommandeList;
    }

    public void setDetailleCommandeList(List<DetailleCommande> detailleCommandeList) {
        this.detailleCommandeList = detailleCommandeList;
    }

    public Fournisseur getIdFournisseur() {
        return idFournisseur;
    }

    public void setIdFournisseur(Fournisseur idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    public TypeRessource getIdTypeRessource() {
        return idTypeRessource;
    }

    public void setIdTypeRessource(TypeRessource idTypeRessource) {
        this.idTypeRessource = idTypeRessource;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRessource != null ? idRessource.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ressource)) {
            return false;
        }
        Ressource other = (Ressource) object;
        if ((this.idRessource == null && other.idRessource != null) || (this.idRessource != null && !this.idRessource.equals(other.idRessource))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Ressource[ idRessource=" + idRessource + " ]";
    }
    
}
