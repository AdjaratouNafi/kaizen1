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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DV7
 */
@Entity
@Table(name = "acomptefournisseur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Acomptefournisseur.findAll", query = "SELECT a FROM Acomptefournisseur a")
    , @NamedQuery(name = "Acomptefournisseur.findByIdAcomptefournisseur", query = "SELECT a FROM Acomptefournisseur a WHERE a.idAcomptefournisseur = :idAcomptefournisseur")
    , @NamedQuery(name = "Acomptefournisseur.findByEcheanceAcompte", query = "SELECT a FROM Acomptefournisseur a WHERE a.echeanceAcompte = :echeanceAcompte")
    , @NamedQuery(name = "Acomptefournisseur.findByMontantAcompte", query = "SELECT a FROM Acomptefournisseur a WHERE a.montantAcompte = :montantAcompte")
    , @NamedQuery(name = "Acomptefournisseur.findByDateAcompte", query = "SELECT a FROM Acomptefournisseur a WHERE a.dateAcompte = :dateAcompte")
    , @NamedQuery(name = "Acomptefournisseur.findByNumerocheque", query = "SELECT a FROM Acomptefournisseur a WHERE a.numerocheque = :numerocheque")
    , @NamedQuery(name = "Acomptefournisseur.findByChoixtva", query = "SELECT a FROM Acomptefournisseur a WHERE a.choixtva = :choixtva")
    , @NamedQuery(name = "Acomptefournisseur.findByNumeroacomptef", query = "SELECT a FROM Acomptefournisseur a WHERE a.numeroacomptef = :numeroacomptef")})
public class Acomptefournisseur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAcomptefournisseur")
    private Integer idAcomptefournisseur;
    @Size(max = 250)
    @Column(name = "echeanceAcompte")
    private String echeanceAcompte;
    @Size(max = 250)
    @Column(name = "montantAcompte")
    private String montantAcompte;
    @Size(max = 250)
    @Column(name = "dateAcompte")
    private String dateAcompte;
    @Size(max = 250)
    @Column(name = "numerocheque")
    private String numerocheque;
    @Size(max = 50)
    @Column(name = "choixtva")
    private String choixtva;
    @Size(max = 250)
    @Column(name = "numeroacomptef")
    private String numeroacomptef;
    @JoinColumn(name = "idCommande", referencedColumnName = "idCommande")
    @ManyToOne
    private Commande idCommande;
    @JoinColumn(name = "idFournisseur", referencedColumnName = "idFournisseur")
    @ManyToOne
    private Fournisseur idFournisseur;
    @JoinColumn(name = "idReffournisseur", referencedColumnName = "idReffournisseur")
    @ManyToOne
    private Reffournisseur idReffournisseur;

    public Acomptefournisseur() {
    }

    public Acomptefournisseur(Integer idAcomptefournisseur) {
        this.idAcomptefournisseur = idAcomptefournisseur;
    }

    public Integer getIdAcomptefournisseur() {
        return idAcomptefournisseur;
    }

    public void setIdAcomptefournisseur(Integer idAcomptefournisseur) {
        this.idAcomptefournisseur = idAcomptefournisseur;
    }

    public String getEcheanceAcompte() {
        return echeanceAcompte;
    }

    public void setEcheanceAcompte(String echeanceAcompte) {
        this.echeanceAcompte = echeanceAcompte;
    }

    public String getMontantAcompte() {
        return montantAcompte;
    }

    public void setMontantAcompte(String montantAcompte) {
        this.montantAcompte = montantAcompte;
    }

    public String getDateAcompte() {
        return dateAcompte;
    }

    public void setDateAcompte(String dateAcompte) {
        this.dateAcompte = dateAcompte;
    }

    public String getNumerocheque() {
        return numerocheque;
    }

    public void setNumerocheque(String numerocheque) {
        this.numerocheque = numerocheque;
    }

    public String getChoixtva() {
        return choixtva;
    }

    public void setChoixtva(String choixtva) {
        this.choixtva = choixtva;
    }

    public String getNumeroacomptef() {
        return numeroacomptef;
    }

    public void setNumeroacomptef(String numeroacomptef) {
        this.numeroacomptef = numeroacomptef;
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

    public Reffournisseur getIdReffournisseur() {
        return idReffournisseur;
    }

    public void setIdReffournisseur(Reffournisseur idReffournisseur) {
        this.idReffournisseur = idReffournisseur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAcomptefournisseur != null ? idAcomptefournisseur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Acomptefournisseur)) {
            return false;
        }
        Acomptefournisseur other = (Acomptefournisseur) object;
        if ((this.idAcomptefournisseur == null && other.idAcomptefournisseur != null) || (this.idAcomptefournisseur != null && !this.idAcomptefournisseur.equals(other.idAcomptefournisseur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Acomptefournisseur[ idAcomptefournisseur=" + idAcomptefournisseur + " ]";
    }
    
}
