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
import javax.persistence.Lob;
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
@Table(name = "management")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Management.findAll", query = "SELECT m FROM Management m")
    , @NamedQuery(name = "Management.findByIdManagement", query = "SELECT m FROM Management m WHERE m.idManagement = :idManagement")
    , @NamedQuery(name = "Management.findByTache", query = "SELECT m FROM Management m WHERE m.tache = :tache")
    , @NamedQuery(name = "Management.findByDuree", query = "SELECT m FROM Management m WHERE m.duree = :duree")
    , @NamedQuery(name = "Management.findByDatedebut", query = "SELECT m FROM Management m WHERE m.datedebut = :datedebut")
    , @NamedQuery(name = "Management.findByDatefin", query = "SELECT m FROM Management m WHERE m.datefin = :datefin")
    , @NamedQuery(name = "Management.findByPredecesseur", query = "SELECT m FROM Management m WHERE m.predecesseur = :predecesseur")
    , @NamedQuery(name = "Management.findByTerminer", query = "SELECT m FROM Management m WHERE m.terminer = :terminer")
    , @NamedQuery(name = "Management.findByEtat", query = "SELECT m FROM Management m WHERE m.etat = :etat")
    , @NamedQuery(name = "Management.findByTypeTache", query = "SELECT m FROM Management m WHERE m.typeTache = :typeTache")
    , @NamedQuery(name = "Management.findByCouleur", query = "SELECT m FROM Management m WHERE m.couleur = :couleur")})
public class Management implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idManagement")
    private Integer idManagement;
    @Size(max = 50)
    @Column(name = "tache")
    private String tache;
    @Size(max = 50)
    @Column(name = "duree")
    private String duree;
    @Size(max = 50)
    @Column(name = "datedebut")
    private String datedebut;
    @Size(max = 50)
    @Column(name = "datefin")
    private String datefin;
    @Column(name = "predecesseur")
    private Integer predecesseur;
    @Column(name = "terminer")
    private Integer terminer;
    @Size(max = 250)
    @Column(name = "etat")
    private String etat;
    @Size(max = 250)
    @Column(name = "typeTache")
    private String typeTache;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "commentaire")
    private String commentaire;
    @Size(max = 50)
    @Column(name = "couleur")
    private String couleur;
    @JoinColumn(name = "idChantier", referencedColumnName = "idChantier")
    @ManyToOne
    private Chantier idChantier;
    @JoinColumn(name = "idMarche", referencedColumnName = "idMarche")
    @ManyToOne
    private Marche idMarche;
    @JoinColumn(name = "idPrestataire", referencedColumnName = "idPrestataire")
    @ManyToOne
    private Prestataire idPrestataire;
    @JoinColumn(name = "idprestatairePrim", referencedColumnName = "idprestatairePrim")
    @ManyToOne
    private Prestataireprim idprestatairePrim;
    @JoinColumn(name = "idUtilisateur", referencedColumnName = "idUtilisateur")
    @ManyToOne
    private Utilisateur idUtilisateur;

    public Management() {
    }

    public Management(Integer idManagement) {
        this.idManagement = idManagement;
    }

    public Integer getIdManagement() {
        return idManagement;
    }

    public void setIdManagement(Integer idManagement) {
        this.idManagement = idManagement;
    }

    public String getTache() {
        return tache;
    }

    public void setTache(String tache) {
        this.tache = tache;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(String datedebut) {
        this.datedebut = datedebut;
    }

    public String getDatefin() {
        return datefin;
    }

    public void setDatefin(String datefin) {
        this.datefin = datefin;
    }

    public Integer getPredecesseur() {
        return predecesseur;
    }

    public void setPredecesseur(Integer predecesseur) {
        this.predecesseur = predecesseur;
    }

    public Integer getTerminer() {
        return terminer;
    }

    public void setTerminer(Integer terminer) {
        this.terminer = terminer;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getTypeTache() {
        return typeTache;
    }

    public void setTypeTache(String typeTache) {
        this.typeTache = typeTache;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public Chantier getIdChantier() {
        return idChantier;
    }

    public void setIdChantier(Chantier idChantier) {
        this.idChantier = idChantier;
    }

    public Marche getIdMarche() {
        return idMarche;
    }

    public void setIdMarche(Marche idMarche) {
        this.idMarche = idMarche;
    }

    public Prestataire getIdPrestataire() {
        return idPrestataire;
    }

    public void setIdPrestataire(Prestataire idPrestataire) {
        this.idPrestataire = idPrestataire;
    }

    public Prestataireprim getIdprestatairePrim() {
        return idprestatairePrim;
    }

    public void setIdprestatairePrim(Prestataireprim idprestatairePrim) {
        this.idprestatairePrim = idprestatairePrim;
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
        hash += (idManagement != null ? idManagement.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Management)) {
            return false;
        }
        Management other = (Management) object;
        if ((this.idManagement == null && other.idManagement != null) || (this.idManagement != null && !this.idManagement.equals(other.idManagement))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Management[ idManagement=" + idManagement + " ]";
    }
    
}
