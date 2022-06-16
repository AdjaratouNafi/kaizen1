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
@Table(name = "entrepot")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entrepot.findAll", query = "SELECT e FROM Entrepot e")
    , @NamedQuery(name = "Entrepot.findByIdEntrepot", query = "SELECT e FROM Entrepot e WHERE e.idEntrepot = :idEntrepot")
    , @NamedQuery(name = "Entrepot.findByNom", query = "SELECT e FROM Entrepot e WHERE e.nom = :nom")
    , @NamedQuery(name = "Entrepot.findByAdresse", query = "SELECT e FROM Entrepot e WHERE e.adresse = :adresse")
    , @NamedQuery(name = "Entrepot.findByTelephone", query = "SELECT e FROM Entrepot e WHERE e.telephone = :telephone")})
public class Entrepot implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEntrepot")
    private Integer idEntrepot;
    @Size(max = 255)
    @Column(name = "nom")
    private String nom;
    @Size(max = 255)
    @Column(name = "adresse")
    private String adresse;
    @Size(max = 50)
    @Column(name = "telephone")
    private String telephone;
    @OneToMany(mappedBy = "idEntrepot")
    private List<Historiquelivraison> historiquelivraisonList;
    @JoinColumn(name = "idUtilisateur", referencedColumnName = "idUtilisateur")
    @ManyToOne
    private Utilisateur idUtilisateur;
    @OneToMany(mappedBy = "idEntrepot")
    private List<Stock> stockList;
    @OneToMany(mappedBy = "idEntrepot")
    private List<Commande> commandeList;
    @OneToMany(mappedBy = "idEntrepot")
    private List<Sortiestock> sortiestockList;

    public Entrepot() {
    }

    public Entrepot(Integer idEntrepot) {
        this.idEntrepot = idEntrepot;
    }

    public Integer getIdEntrepot() {
        return idEntrepot;
    }

    public void setIdEntrepot(Integer idEntrepot) {
        this.idEntrepot = idEntrepot;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @XmlTransient
    @JsonIgnore
    public List<Historiquelivraison> getHistoriquelivraisonList() {
        return historiquelivraisonList;
    }

    public void setHistoriquelivraisonList(List<Historiquelivraison> historiquelivraisonList) {
        this.historiquelivraisonList = historiquelivraisonList;
    }

    public Utilisateur getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Utilisateur idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    @XmlTransient
    @JsonIgnore
    public List<Stock> getStockList() {
        return stockList;
    }

    public void setStockList(List<Stock> stockList) {
        this.stockList = stockList;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEntrepot != null ? idEntrepot.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entrepot)) {
            return false;
        }
        Entrepot other = (Entrepot) object;
        if ((this.idEntrepot == null && other.idEntrepot != null) || (this.idEntrepot != null && !this.idEntrepot.equals(other.idEntrepot))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Entrepot[ idEntrepot=" + idEntrepot + " ]";
    }
    
}
