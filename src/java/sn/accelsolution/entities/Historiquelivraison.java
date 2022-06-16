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
@Table(name = "historiquelivraison")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Historiquelivraison.findAll", query = "SELECT h FROM Historiquelivraison h")
    , @NamedQuery(name = "Historiquelivraison.findByIdHistoLivraison", query = "SELECT h FROM Historiquelivraison h WHERE h.idHistoLivraison = :idHistoLivraison")
    , @NamedQuery(name = "Historiquelivraison.findByDesignation", query = "SELECT h FROM Historiquelivraison h WHERE h.designation = :designation")
    , @NamedQuery(name = "Historiquelivraison.findByQtLivree", query = "SELECT h FROM Historiquelivraison h WHERE h.qtLivree = :qtLivree")
    , @NamedQuery(name = "Historiquelivraison.findByQtRestante", query = "SELECT h FROM Historiquelivraison h WHERE h.qtRestante = :qtRestante")
    , @NamedQuery(name = "Historiquelivraison.findByPu", query = "SELECT h FROM Historiquelivraison h WHERE h.pu = :pu")
    , @NamedQuery(name = "Historiquelivraison.findByDateLivraison", query = "SELECT h FROM Historiquelivraison h WHERE h.dateLivraison = :dateLivraison")})
public class Historiquelivraison implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idHistoLivraison")
    private Integer idHistoLivraison;
    @Size(max = 250)
    @Column(name = "designation")
    private String designation;
    @Column(name = "qtLivree")
    private Integer qtLivree;
    @Column(name = "qtRestante")
    private Integer qtRestante;
    @Size(max = 250)
    @Column(name = "pu")
    private String pu;
    @Size(max = 250)
    @Column(name = "dateLivraison")
    private String dateLivraison;
    @JoinColumn(name = "idCommande", referencedColumnName = "idCommande")
    @ManyToOne
    private Commande idCommande;
    @JoinColumn(name = "idEntrepot", referencedColumnName = "idEntrepot")
    @ManyToOne
    private Entrepot idEntrepot;
    @JoinColumn(name = "idFournisseur", referencedColumnName = "idFournisseur")
    @ManyToOne
    private Fournisseur idFournisseur;

    public Historiquelivraison() {
    }

    public Historiquelivraison(Integer idHistoLivraison) {
        this.idHistoLivraison = idHistoLivraison;
    }

    public Integer getIdHistoLivraison() {
        return idHistoLivraison;
    }

    public void setIdHistoLivraison(Integer idHistoLivraison) {
        this.idHistoLivraison = idHistoLivraison;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Integer getQtLivree() {
        return qtLivree;
    }

    public void setQtLivree(Integer qtLivree) {
        this.qtLivree = qtLivree;
    }

    public Integer getQtRestante() {
        return qtRestante;
    }

    public void setQtRestante(Integer qtRestante) {
        this.qtRestante = qtRestante;
    }

    public String getPu() {
        return pu;
    }

    public void setPu(String pu) {
        this.pu = pu;
    }

    public String getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(String dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public Commande getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Commande idCommande) {
        this.idCommande = idCommande;
    }

    public Entrepot getIdEntrepot() {
        return idEntrepot;
    }

    public void setIdEntrepot(Entrepot idEntrepot) {
        this.idEntrepot = idEntrepot;
    }

    public Fournisseur getIdFournisseur() {
        return idFournisseur;
    }

    public void setIdFournisseur(Fournisseur idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHistoLivraison != null ? idHistoLivraison.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Historiquelivraison)) {
            return false;
        }
        Historiquelivraison other = (Historiquelivraison) object;
        if ((this.idHistoLivraison == null && other.idHistoLivraison != null) || (this.idHistoLivraison != null && !this.idHistoLivraison.equals(other.idHistoLivraison))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Historiquelivraison[ idHistoLivraison=" + idHistoLivraison + " ]";
    }
    
}
