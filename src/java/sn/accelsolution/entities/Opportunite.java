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
@Table(name = "opportunite")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Opportunite.findAll", query = "SELECT o FROM Opportunite o")
    , @NamedQuery(name = "Opportunite.findByIdOpportunite", query = "SELECT o FROM Opportunite o WHERE o.idOpportunite = :idOpportunite")
    , @NamedQuery(name = "Opportunite.findByLibelle", query = "SELECT o FROM Opportunite o WHERE o.libelle = :libelle")
    , @NamedQuery(name = "Opportunite.findByPriorite", query = "SELECT o FROM Opportunite o WHERE o.priorite = :priorite")
    , @NamedQuery(name = "Opportunite.findByStadevente", query = "SELECT o FROM Opportunite o WHERE o.stadevente = :stadevente")
    , @NamedQuery(name = "Opportunite.findByValeur", query = "SELECT o FROM Opportunite o WHERE o.valeur = :valeur")
    , @NamedQuery(name = "Opportunite.findByDateCloture", query = "SELECT o FROM Opportunite o WHERE o.dateCloture = :dateCloture")
    , @NamedQuery(name = "Opportunite.findByProbabilte", query = "SELECT o FROM Opportunite o WHERE o.probabilte = :probabilte")
    , @NamedQuery(name = "Opportunite.findByProchainetap", query = "SELECT o FROM Opportunite o WHERE o.prochainetap = :prochainetap")
    , @NamedQuery(name = "Opportunite.findByProduit", query = "SELECT o FROM Opportunite o WHERE o.produit = :produit")
    , @NamedQuery(name = "Opportunite.findByDerniercontact", query = "SELECT o FROM Opportunite o WHERE o.derniercontact = :derniercontact")
    , @NamedQuery(name = "Opportunite.findByNomcontact", query = "SELECT o FROM Opportunite o WHERE o.nomcontact = :nomcontact")
    , @NamedQuery(name = "Opportunite.findByEmail", query = "SELECT o FROM Opportunite o WHERE o.email = :email")
    , @NamedQuery(name = "Opportunite.findByTelephone", query = "SELECT o FROM Opportunite o WHERE o.telephone = :telephone")
    , @NamedQuery(name = "Opportunite.findByRouge", query = "SELECT o FROM Opportunite o WHERE o.rouge = :rouge")
    , @NamedQuery(name = "Opportunite.findByJaune", query = "SELECT o FROM Opportunite o WHERE o.jaune = :jaune")
    , @NamedQuery(name = "Opportunite.findByVert", query = "SELECT o FROM Opportunite o WHERE o.vert = :vert")})
public class Opportunite implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idOpportunite")
    private Integer idOpportunite;
    @Size(max = 255)
    @Column(name = "libelle")
    private String libelle;
    @Size(max = 50)
    @Column(name = "priorite")
    private String priorite;
    @Size(max = 50)
    @Column(name = "stadevente")
    private String stadevente;
    @Size(max = 50)
    @Column(name = "valeur")
    private String valeur;
    @Size(max = 50)
    @Column(name = "dateCloture")
    private String dateCloture;
    @Size(max = 50)
    @Column(name = "probabilte")
    private String probabilte;
    @Size(max = 255)
    @Column(name = "prochainetap")
    private String prochainetap;
    @Size(max = 255)
    @Column(name = "produit")
    private String produit;
    @Size(max = 255)
    @Column(name = "derniercontact")
    private String derniercontact;
    @Size(max = 50)
    @Column(name = "nomcontact")
    private String nomcontact;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "email")
    private String email;
    @Size(max = 50)
    @Column(name = "telephone")
    private String telephone;
    @Size(max = 50)
    @Column(name = "rouge")
    private String rouge;
    @Size(max = 50)
    @Column(name = "jaune")
    private String jaune;
    @Size(max = 50)
    @Column(name = "vert")
    private String vert;
    @JoinColumn(name = "idUtilisateur", referencedColumnName = "idUtilisateur")
    @ManyToOne
    private Utilisateur idUtilisateur;

    public Opportunite() {
    }

    public Opportunite(Integer idOpportunite) {
        this.idOpportunite = idOpportunite;
    }

    public Integer getIdOpportunite() {
        return idOpportunite;
    }

    public void setIdOpportunite(Integer idOpportunite) {
        this.idOpportunite = idOpportunite;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getPriorite() {
        return priorite;
    }

    public void setPriorite(String priorite) {
        this.priorite = priorite;
    }

    public String getStadevente() {
        return stadevente;
    }

    public void setStadevente(String stadevente) {
        this.stadevente = stadevente;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public String getDateCloture() {
        return dateCloture;
    }

    public void setDateCloture(String dateCloture) {
        this.dateCloture = dateCloture;
    }

    public String getProbabilte() {
        return probabilte;
    }

    public void setProbabilte(String probabilte) {
        this.probabilte = probabilte;
    }

    public String getProchainetap() {
        return prochainetap;
    }

    public void setProchainetap(String prochainetap) {
        this.prochainetap = prochainetap;
    }

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public String getDerniercontact() {
        return derniercontact;
    }

    public void setDerniercontact(String derniercontact) {
        this.derniercontact = derniercontact;
    }

    public String getNomcontact() {
        return nomcontact;
    }

    public void setNomcontact(String nomcontact) {
        this.nomcontact = nomcontact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRouge() {
        return rouge;
    }

    public void setRouge(String rouge) {
        this.rouge = rouge;
    }

    public String getJaune() {
        return jaune;
    }

    public void setJaune(String jaune) {
        this.jaune = jaune;
    }

    public String getVert() {
        return vert;
    }

    public void setVert(String vert) {
        this.vert = vert;
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
        hash += (idOpportunite != null ? idOpportunite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Opportunite)) {
            return false;
        }
        Opportunite other = (Opportunite) object;
        if ((this.idOpportunite == null && other.idOpportunite != null) || (this.idOpportunite != null && !this.idOpportunite.equals(other.idOpportunite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Opportunite[ idOpportunite=" + idOpportunite + " ]";
    }
    
}
