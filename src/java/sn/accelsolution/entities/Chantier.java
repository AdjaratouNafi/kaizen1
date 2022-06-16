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
@Table(name = "chantier")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chantier.findAll", query = "SELECT c FROM Chantier c")
    , @NamedQuery(name = "Chantier.findByIdChantier", query = "SELECT c FROM Chantier c WHERE c.idChantier = :idChantier")
    , @NamedQuery(name = "Chantier.findByEtatchantier", query = "SELECT c FROM Chantier c WHERE c.etatchantier = :etatchantier")
    , @NamedQuery(name = "Chantier.findBySiteChantier", query = "SELECT c FROM Chantier c WHERE c.siteChantier = :siteChantier")
    , @NamedQuery(name = "Chantier.findByDateCreation", query = "SELECT c FROM Chantier c WHERE c.dateCreation = :dateCreation")})
public class Chantier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idChantier")
    private Integer idChantier;
    @Size(max = 50)
    @Column(name = "etatchantier")
    private String etatchantier;
    @Size(max = 50)
    @Column(name = "siteChantier")
    private String siteChantier;
    @Size(max = 50)
    @Column(name = "dateCreation")
    private String dateCreation;
    @OneToMany(mappedBy = "idChantier")
    private List<Prestataire> prestataireList;
    @OneToMany(mappedBy = "idChantier")
    private List<Brouillard> brouillardList;
    @OneToMany(mappedBy = "idChantier")
    private List<Mouvementdebourse> mouvementdebourseList;
    @OneToMany(mappedBy = "idChantier")
    private List<Acompte> acompteList;
    @JoinColumn(name = "idMarche", referencedColumnName = "idMarche")
    @ManyToOne
    private Marche idMarche;
    @JoinColumn(name = "idPrestataire", referencedColumnName = "idPrestataire")
    @ManyToOne
    private Prestataire idPrestataire;
    @JoinColumn(name = "idUtilisateur", referencedColumnName = "idUtilisateur")
    @ManyToOne
    private Utilisateur idUtilisateur;
    @OneToMany(mappedBy = "idChantier")
    private List<Detaillechantier> detaillechantierList;
    @OneToMany(mappedBy = "idChantier")
    private List<Expressionbesoin> expressionbesoinList;
    @OneToMany(mappedBy = "idChantier")
    private List<Commande> commandeList;
    @OneToMany(mappedBy = "idChantier")
    private List<Sortiestock> sortiestockList;
    @OneToMany(mappedBy = "idChantier")
    private List<Management> managementList;
    @OneToMany(mappedBy = "idChantier")
    private List<Livraison> livraisonList;
    @OneToMany(mappedBy = "idChantier")
    private List<Operation> operationList;

    public Chantier() {
    }

    public Chantier(Integer idChantier) {
        this.idChantier = idChantier;
    }

    public Integer getIdChantier() {
        return idChantier;
    }

    public void setIdChantier(Integer idChantier) {
        this.idChantier = idChantier;
    }

    public String getEtatchantier() {
        return etatchantier;
    }

    public void setEtatchantier(String etatchantier) {
        this.etatchantier = etatchantier;
    }

    public String getSiteChantier() {
        return siteChantier;
    }

    public void setSiteChantier(String siteChantier) {
        this.siteChantier = siteChantier;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    @XmlTransient
    @JsonIgnore
    public List<Prestataire> getPrestataireList() {
        return prestataireList;
    }

    public void setPrestataireList(List<Prestataire> prestataireList) {
        this.prestataireList = prestataireList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Brouillard> getBrouillardList() {
        return brouillardList;
    }

    public void setBrouillardList(List<Brouillard> brouillardList) {
        this.brouillardList = brouillardList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Mouvementdebourse> getMouvementdebourseList() {
        return mouvementdebourseList;
    }

    public void setMouvementdebourseList(List<Mouvementdebourse> mouvementdebourseList) {
        this.mouvementdebourseList = mouvementdebourseList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Acompte> getAcompteList() {
        return acompteList;
    }

    public void setAcompteList(List<Acompte> acompteList) {
        this.acompteList = acompteList;
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

    public Utilisateur getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Utilisateur idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
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
    public List<Expressionbesoin> getExpressionbesoinList() {
        return expressionbesoinList;
    }

    public void setExpressionbesoinList(List<Expressionbesoin> expressionbesoinList) {
        this.expressionbesoinList = expressionbesoinList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Commande> getCommandeList() {
        return commandeList;
    }

    public void setCommandeList(List<Commande> commandeList) {
        this.commandeList = commandeList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Sortiestock> getSortiestockList() {
        return sortiestockList;
    }

    public void setSortiestockList(List<Sortiestock> sortiestockList) {
        this.sortiestockList = sortiestockList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Management> getManagementList() {
        return managementList;
    }

    public void setManagementList(List<Management> managementList) {
        this.managementList = managementList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Livraison> getLivraisonList() {
        return livraisonList;
    }

    public void setLivraisonList(List<Livraison> livraisonList) {
        this.livraisonList = livraisonList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Operation> getOperationList() {
        return operationList;
    }

    public void setOperationList(List<Operation> operationList) {
        this.operationList = operationList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idChantier != null ? idChantier.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chantier)) {
            return false;
        }
        Chantier other = (Chantier) object;
        if ((this.idChantier == null && other.idChantier != null) || (this.idChantier != null && !this.idChantier.equals(other.idChantier))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Chantier[ idChantier=" + idChantier + " ]";
    }
    
}
