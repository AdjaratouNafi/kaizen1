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
@Table(name = "detaille_commande")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetailleCommande.findAll", query = "SELECT d FROM DetailleCommande d")
    , @NamedQuery(name = "DetailleCommande.findByIdDetailleCommande", query = "SELECT d FROM DetailleCommande d WHERE d.idDetailleCommande = :idDetailleCommande")
    , @NamedQuery(name = "DetailleCommande.findByReference", query = "SELECT d FROM DetailleCommande d WHERE d.reference = :reference")
    , @NamedQuery(name = "DetailleCommande.findByRemise", query = "SELECT d FROM DetailleCommande d WHERE d.remise = :remise")
    , @NamedQuery(name = "DetailleCommande.findByMontant", query = "SELECT d FROM DetailleCommande d WHERE d.montant = :montant")
    , @NamedQuery(name = "DetailleCommande.findByDateEchance", query = "SELECT d FROM DetailleCommande d WHERE d.dateEchance = :dateEchance")
    , @NamedQuery(name = "DetailleCommande.findByDesignation", query = "SELECT d FROM DetailleCommande d WHERE d.designation = :designation")
    , @NamedQuery(name = "DetailleCommande.findByQuantite", query = "SELECT d FROM DetailleCommande d WHERE d.quantite = :quantite")
    , @NamedQuery(name = "DetailleCommande.findByPuhortaxe", query = "SELECT d FROM DetailleCommande d WHERE d.puhortaxe = :puhortaxe")
    , @NamedQuery(name = "DetailleCommande.findByMontanthortaxe", query = "SELECT d FROM DetailleCommande d WHERE d.montanthortaxe = :montanthortaxe")
    , @NamedQuery(name = "DetailleCommande.findByTtc", query = "SELECT d FROM DetailleCommande d WHERE d.ttc = :ttc")
    , @NamedQuery(name = "DetailleCommande.findByPu", query = "SELECT d FROM DetailleCommande d WHERE d.pu = :pu")
    , @NamedQuery(name = "DetailleCommande.findByUnite", query = "SELECT d FROM DetailleCommande d WHERE d.unite = :unite")
    , @NamedQuery(name = "DetailleCommande.findByTraiter", query = "SELECT d FROM DetailleCommande d WHERE d.traiter = :traiter")
    , @NamedQuery(name = "DetailleCommande.findByQtlivree", query = "SELECT d FROM DetailleCommande d WHERE d.qtlivree = :qtlivree")
    , @NamedQuery(name = "DetailleCommande.findByQtrestante", query = "SELECT d FROM DetailleCommande d WHERE d.qtrestante = :qtrestante")})
public class DetailleCommande implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDetailleCommande")
    private Integer idDetailleCommande;
    @Size(max = 255)
    @Column(name = "reference")
    private String reference;
    @Size(max = 50)
    @Column(name = "remise")
    private String remise;
    @Size(max = 50)
    @Column(name = "montant")
    private String montant;
    @Size(max = 50)
    @Column(name = "dateEchance")
    private String dateEchance;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "observation")
    private String observation;
    @Size(max = 50)
    @Column(name = "designation")
    private String designation;
    @Column(name = "quantite")
    private Integer quantite;
    @Size(max = 50)
    @Column(name = "puhortaxe")
    private String puhortaxe;
    @Size(max = 50)
    @Column(name = "montanthortaxe")
    private String montanthortaxe;
    @Size(max = 50)
    @Column(name = "ttc")
    private String ttc;
    @Size(max = 50)
    @Column(name = "pu")
    private String pu;
    @Size(max = 50)
    @Column(name = "unite")
    private String unite;
    @Size(max = 50)
    @Column(name = "traiter")
    private String traiter;
    @Column(name = "qtlivree")
    private Integer qtlivree;
    @Column(name = "qtrestante")
    private Integer qtrestante;
    @JoinColumn(name = "idCommande", referencedColumnName = "idCommande")
    @ManyToOne
    private Commande idCommande;
    @JoinColumn(name = "idMarchandise", referencedColumnName = "idMarchandise")
    @ManyToOne
    private Marchandise idMarchandise;
    @JoinColumn(name = "idRessource", referencedColumnName = "idRessource")
    @ManyToOne
    private Ressource idRessource;

    public DetailleCommande() {
    }

    public DetailleCommande(Integer idDetailleCommande) {
        this.idDetailleCommande = idDetailleCommande;
    }

    public Integer getIdDetailleCommande() {
        return idDetailleCommande;
    }

    public void setIdDetailleCommande(Integer idDetailleCommande) {
        this.idDetailleCommande = idDetailleCommande;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getRemise() {
        return remise;
    }

    public void setRemise(String remise) {
        this.remise = remise;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }

    public String getDateEchance() {
        return dateEchance;
    }

    public void setDateEchance(String dateEchance) {
        this.dateEchance = dateEchance;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public String getPuhortaxe() {
        return puhortaxe;
    }

    public void setPuhortaxe(String puhortaxe) {
        this.puhortaxe = puhortaxe;
    }

    public String getMontanthortaxe() {
        return montanthortaxe;
    }

    public void setMontanthortaxe(String montanthortaxe) {
        this.montanthortaxe = montanthortaxe;
    }

    public String getTtc() {
        return ttc;
    }

    public void setTtc(String ttc) {
        this.ttc = ttc;
    }

    public String getPu() {
        return pu;
    }

    public void setPu(String pu) {
        this.pu = pu;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public String getTraiter() {
        return traiter;
    }

    public void setTraiter(String traiter) {
        this.traiter = traiter;
    }

    public Integer getQtlivree() {
        return qtlivree;
    }

    public void setQtlivree(Integer qtlivree) {
        this.qtlivree = qtlivree;
    }

    public Integer getQtrestante() {
        return qtrestante;
    }

    public void setQtrestante(Integer qtrestante) {
        this.qtrestante = qtrestante;
    }

    public Commande getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Commande idCommande) {
        this.idCommande = idCommande;
    }

    public Marchandise getIdMarchandise() {
        return idMarchandise;
    }

    public void setIdMarchandise(Marchandise idMarchandise) {
        this.idMarchandise = idMarchandise;
    }

    public Ressource getIdRessource() {
        return idRessource;
    }

    public void setIdRessource(Ressource idRessource) {
        this.idRessource = idRessource;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetailleCommande != null ? idDetailleCommande.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetailleCommande)) {
            return false;
        }
        DetailleCommande other = (DetailleCommande) object;
        if ((this.idDetailleCommande == null && other.idDetailleCommande != null) || (this.idDetailleCommande != null && !this.idDetailleCommande.equals(other.idDetailleCommande))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.DetailleCommande[ idDetailleCommande=" + idDetailleCommande + " ]";
    }
    
}
