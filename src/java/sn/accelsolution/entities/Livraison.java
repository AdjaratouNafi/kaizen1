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
@Table(name = "livraison")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Livraison.findAll", query = "SELECT l FROM Livraison l")
    , @NamedQuery(name = "Livraison.findByIdLivraison", query = "SELECT l FROM Livraison l WHERE l.idLivraison = :idLivraison")
    , @NamedQuery(name = "Livraison.findByDesignation", query = "SELECT l FROM Livraison l WHERE l.designation = :designation")
    , @NamedQuery(name = "Livraison.findByQtLivre", query = "SELECT l FROM Livraison l WHERE l.qtLivre = :qtLivre")
    , @NamedQuery(name = "Livraison.findByQtRestante", query = "SELECT l FROM Livraison l WHERE l.qtRestante = :qtRestante")
    , @NamedQuery(name = "Livraison.findByPu", query = "SELECT l FROM Livraison l WHERE l.pu = :pu")
    , @NamedQuery(name = "Livraison.findByDateLivraison", query = "SELECT l FROM Livraison l WHERE l.dateLivraison = :dateLivraison")})
public class Livraison implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idLivraison")
    private Integer idLivraison;
    @Size(max = 50)
    @Column(name = "designation")
    private String designation;
    @Column(name = "qtLivre")
    private Integer qtLivre;
    @Column(name = "qtRestante")
    private Integer qtRestante;
    @Size(max = 250)
    @Column(name = "pu")
    private String pu;
    @Size(max = 50)
    @Column(name = "dateLivraison")
    private String dateLivraison;
    @OneToMany(mappedBy = "idLivraison")
    private List<DetailleLivraison> detailleLivraisonList;
    @JoinColumn(name = "idChantier", referencedColumnName = "idChantier")
    @ManyToOne
    private Chantier idChantier;
    @JoinColumn(name = "idCommande", referencedColumnName = "idCommande")
    @ManyToOne
    private Commande idCommande;
    @JoinColumn(name = "idFournisseur", referencedColumnName = "idFournisseur")
    @ManyToOne
    private Fournisseur idFournisseur;

    public Livraison() {
    }

    public Livraison(Integer idLivraison) {
        this.idLivraison = idLivraison;
    }

    public Integer getIdLivraison() {
        return idLivraison;
    }

    public void setIdLivraison(Integer idLivraison) {
        this.idLivraison = idLivraison;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Integer getQtLivre() {
        return qtLivre;
    }

    public void setQtLivre(Integer qtLivre) {
        this.qtLivre = qtLivre;
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

    @XmlTransient
    @JsonIgnore
    public List<DetailleLivraison> getDetailleLivraisonList() {
        return detailleLivraisonList;
    }

    public void setDetailleLivraisonList(List<DetailleLivraison> detailleLivraisonList) {
        this.detailleLivraisonList = detailleLivraisonList;
    }

    public Chantier getIdChantier() {
        return idChantier;
    }

    public void setIdChantier(Chantier idChantier) {
        this.idChantier = idChantier;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLivraison != null ? idLivraison.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Livraison)) {
            return false;
        }
        Livraison other = (Livraison) object;
        if ((this.idLivraison == null && other.idLivraison != null) || (this.idLivraison != null && !this.idLivraison.equals(other.idLivraison))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Livraison[ idLivraison=" + idLivraison + " ]";
    }
    
}
