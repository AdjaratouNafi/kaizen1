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
@Table(name = "reffournisseur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reffournisseur.findAll", query = "SELECT r FROM Reffournisseur r")
    , @NamedQuery(name = "Reffournisseur.findByIdReffournisseur", query = "SELECT r FROM Reffournisseur r WHERE r.idReffournisseur = :idReffournisseur")
    , @NamedQuery(name = "Reffournisseur.findByNomcomplet", query = "SELECT r FROM Reffournisseur r WHERE r.nomcomplet = :nomcomplet")
    , @NamedQuery(name = "Reffournisseur.findByTelephone", query = "SELECT r FROM Reffournisseur r WHERE r.telephone = :telephone")
    , @NamedQuery(name = "Reffournisseur.findByAccord", query = "SELECT r FROM Reffournisseur r WHERE r.accord = :accord")
    , @NamedQuery(name = "Reffournisseur.findByNature", query = "SELECT r FROM Reffournisseur r WHERE r.nature = :nature")
    , @NamedQuery(name = "Reffournisseur.findByAccompte", query = "SELECT r FROM Reffournisseur r WHERE r.accompte = :accompte")
    , @NamedQuery(name = "Reffournisseur.findByReliquant", query = "SELECT r FROM Reffournisseur r WHERE r.reliquant = :reliquant")
    , @NamedQuery(name = "Reffournisseur.findByVoyant", query = "SELECT r FROM Reffournisseur r WHERE r.voyant = :voyant")
    , @NamedQuery(name = "Reffournisseur.findByEcheance", query = "SELECT r FROM Reffournisseur r WHERE r.echeance = :echeance")})
public class Reffournisseur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idReffournisseur")
    private Integer idReffournisseur;
    @Size(max = 250)
    @Column(name = "nomcomplet")
    private String nomcomplet;
    @Size(max = 50)
    @Column(name = "telephone")
    private String telephone;
    @Size(max = 250)
    @Column(name = "accord")
    private String accord;
    @Size(max = 250)
    @Column(name = "nature")
    private String nature;
    @Size(max = 250)
    @Column(name = "accompte")
    private String accompte;
    @Size(max = 250)
    @Column(name = "reliquant")
    private String reliquant;
    @Size(max = 50)
    @Column(name = "voyant")
    private String voyant;
    @Size(max = 50)
    @Column(name = "echeance")
    private String echeance;
    @JoinColumn(name = "idCommande", referencedColumnName = "idCommande")
    @ManyToOne
    private Commande idCommande;
    @JoinColumn(name = "idFournisseur", referencedColumnName = "idFournisseur")
    @ManyToOne
    private Fournisseur idFournisseur;
    @OneToMany(mappedBy = "idReffournisseur")
    private List<Acomptefournisseur> acomptefournisseurList;

    public Reffournisseur() {
    }

    public Reffournisseur(Integer idReffournisseur) {
        this.idReffournisseur = idReffournisseur;
    }

    public Integer getIdReffournisseur() {
        return idReffournisseur;
    }

    public void setIdReffournisseur(Integer idReffournisseur) {
        this.idReffournisseur = idReffournisseur;
    }

    public String getNomcomplet() {
        return nomcomplet;
    }

    public void setNomcomplet(String nomcomplet) {
        this.nomcomplet = nomcomplet;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAccord() {
        return accord;
    }

    public void setAccord(String accord) {
        this.accord = accord;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getAccompte() {
        return accompte;
    }

    public void setAccompte(String accompte) {
        this.accompte = accompte;
    }

    public String getReliquant() {
        return reliquant;
    }

    public void setReliquant(String reliquant) {
        this.reliquant = reliquant;
    }

    public String getVoyant() {
        return voyant;
    }

    public void setVoyant(String voyant) {
        this.voyant = voyant;
    }

    public String getEcheance() {
        return echeance;
    }

    public void setEcheance(String echeance) {
        this.echeance = echeance;
    }

    public Commande getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Commande idCommande) {
        this.idCommande = idCommande;
    }

    public Fournisseur getIdFournisseur() {
        return idFournisseur;
    }

    public void setIdFournisseur(Fournisseur idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    @XmlTransient
    @JsonIgnore
    public List<Acomptefournisseur> getAcomptefournisseurList() {
        return acomptefournisseurList;
    }

    public void setAcomptefournisseurList(List<Acomptefournisseur> acomptefournisseurList) {
        this.acomptefournisseurList = acomptefournisseurList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReffournisseur != null ? idReffournisseur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reffournisseur)) {
            return false;
        }
        Reffournisseur other = (Reffournisseur) object;
        if ((this.idReffournisseur == null && other.idReffournisseur != null) || (this.idReffournisseur != null && !this.idReffournisseur.equals(other.idReffournisseur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Reffournisseur[ idReffournisseur=" + idReffournisseur + " ]";
    }
    
}
