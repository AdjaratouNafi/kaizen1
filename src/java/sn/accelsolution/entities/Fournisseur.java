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
@Table(name = "fournisseur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fournisseur.findAll", query = "SELECT f FROM Fournisseur f")
    , @NamedQuery(name = "Fournisseur.findByIdFournisseur", query = "SELECT f FROM Fournisseur f WHERE f.idFournisseur = :idFournisseur")
    , @NamedQuery(name = "Fournisseur.findByNomFournisseur", query = "SELECT f FROM Fournisseur f WHERE f.nomFournisseur = :nomFournisseur")
    , @NamedQuery(name = "Fournisseur.findByNinea", query = "SELECT f FROM Fournisseur f WHERE f.ninea = :ninea")
    , @NamedQuery(name = "Fournisseur.findByAdresseFournisseur", query = "SELECT f FROM Fournisseur f WHERE f.adresseFournisseur = :adresseFournisseur")
    , @NamedQuery(name = "Fournisseur.findByTelephoneFournisseur", query = "SELECT f FROM Fournisseur f WHERE f.telephoneFournisseur = :telephoneFournisseur")
    , @NamedQuery(name = "Fournisseur.findByMailFournisseur", query = "SELECT f FROM Fournisseur f WHERE f.mailFournisseur = :mailFournisseur")
    , @NamedQuery(name = "Fournisseur.findByProduit", query = "SELECT f FROM Fournisseur f WHERE f.produit = :produit")
    , @NamedQuery(name = "Fournisseur.findByPrixProdtui", query = "SELECT f FROM Fournisseur f WHERE f.prixProdtui = :prixProdtui")
    , @NamedQuery(name = "Fournisseur.findByContact", query = "SELECT f FROM Fournisseur f WHERE f.contact = :contact")
    , @NamedQuery(name = "Fournisseur.findByFonctionContact", query = "SELECT f FROM Fournisseur f WHERE f.fonctionContact = :fonctionContact")})
public class Fournisseur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idFournisseur")
    private Integer idFournisseur;
    @Size(max = 50)
    @Column(name = "nomFournisseur")
    private String nomFournisseur;
    @Size(max = 250)
    @Column(name = "ninea")
    private String ninea;
    @Size(max = 50)
    @Column(name = "adresseFournisseur")
    private String adresseFournisseur;
    @Size(max = 50)
    @Column(name = "telephoneFournisseur")
    private String telephoneFournisseur;
    @Size(max = 50)
    @Column(name = "mailFournisseur")
    private String mailFournisseur;
    @Size(max = 50)
    @Column(name = "produit")
    private String produit;
    @Size(max = 50)
    @Column(name = "prixProdtui")
    private String prixProdtui;
    @Size(max = 50)
    @Column(name = "contact")
    private String contact;
    @Size(max = 50)
    @Column(name = "fonctionContact")
    private String fonctionContact;
    @Lob
    @Column(name = "faxe")
    private byte[] faxe;
    @OneToMany(mappedBy = "idFournisseur")
    private List<Prix> prixList;
    @JoinColumn(name = "idPays", referencedColumnName = "idPays")
    @ManyToOne
    private Pays idPays;
    @JoinColumn(name = "idVille", referencedColumnName = "idVille")
    @ManyToOne
    private Ville idVille;
    @OneToMany(mappedBy = "idFournisseur")
    private List<Historiquelivraison> historiquelivraisonList;
    @OneToMany(mappedBy = "idFournisseur")
    private List<Stock> stockList;
    @OneToMany(mappedBy = "idFournisseur")
    private List<Reffournisseur> reffournisseurList;
    @OneToMany(mappedBy = "idFournisseur")
    private List<Acomptefournisseur> acomptefournisseurList;
    @OneToMany(mappedBy = "idFournisseur")
    private List<Commande> commandeList;
    @OneToMany(mappedBy = "idFournisseur")
    private List<Ressource> ressourceList;
    @OneToMany(mappedBy = "idFournisseur")
    private List<Livraison> livraisonList;
    @OneToMany(mappedBy = "idFournisseur")
    private List<Vente> venteList;

    public Fournisseur() {
    }

    public Fournisseur(Integer idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    public Integer getIdFournisseur() {
        return idFournisseur;
    }

    public void setIdFournisseur(Integer idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    public String getNomFournisseur() {
        return nomFournisseur;
    }

    public void setNomFournisseur(String nomFournisseur) {
        this.nomFournisseur = nomFournisseur;
    }

    public String getNinea() {
        return ninea;
    }

    public void setNinea(String ninea) {
        this.ninea = ninea;
    }

    public String getAdresseFournisseur() {
        return adresseFournisseur;
    }

    public void setAdresseFournisseur(String adresseFournisseur) {
        this.adresseFournisseur = adresseFournisseur;
    }

    public String getTelephoneFournisseur() {
        return telephoneFournisseur;
    }

    public void setTelephoneFournisseur(String telephoneFournisseur) {
        this.telephoneFournisseur = telephoneFournisseur;
    }

    public String getMailFournisseur() {
        return mailFournisseur;
    }

    public void setMailFournisseur(String mailFournisseur) {
        this.mailFournisseur = mailFournisseur;
    }

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public String getPrixProdtui() {
        return prixProdtui;
    }

    public void setPrixProdtui(String prixProdtui) {
        this.prixProdtui = prixProdtui;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getFonctionContact() {
        return fonctionContact;
    }

    public void setFonctionContact(String fonctionContact) {
        this.fonctionContact = fonctionContact;
    }

    public byte[] getFaxe() {
        return faxe;
    }

    public void setFaxe(byte[] faxe) {
        this.faxe = faxe;
    }

    @XmlTransient
    @JsonIgnore
    public List<Prix> getPrixList() {
        return prixList;
    }

    public void setPrixList(List<Prix> prixList) {
        this.prixList = prixList;
    }

    public Pays getIdPays() {
        return idPays;
    }

    public void setIdPays(Pays idPays) {
        this.idPays = idPays;
    }

    public Ville getIdVille() {
        return idVille;
    }

    public void setIdVille(Ville idVille) {
        this.idVille = idVille;
    }

    @XmlTransient
    @JsonIgnore
    public List<Historiquelivraison> getHistoriquelivraisonList() {
        return historiquelivraisonList;
    }

    public void setHistoriquelivraisonList(List<Historiquelivraison> historiquelivraisonList) {
        this.historiquelivraisonList = historiquelivraisonList;
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
    public List<Reffournisseur> getReffournisseurList() {
        return reffournisseurList;
    }

    public void setReffournisseurList(List<Reffournisseur> reffournisseurList) {
        this.reffournisseurList = reffournisseurList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Acomptefournisseur> getAcomptefournisseurList() {
        return acomptefournisseurList;
    }

    public void setAcomptefournisseurList(List<Acomptefournisseur> acomptefournisseurList) {
        this.acomptefournisseurList = acomptefournisseurList;
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
    public List<Ressource> getRessourceList() {
        return ressourceList;
    }

    public void setRessourceList(List<Ressource> ressourceList) {
        this.ressourceList = ressourceList;
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
    public List<Vente> getVenteList() {
        return venteList;
    }

    public void setVenteList(List<Vente> venteList) {
        this.venteList = venteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFournisseur != null ? idFournisseur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fournisseur)) {
            return false;
        }
        Fournisseur other = (Fournisseur) object;
        if ((this.idFournisseur == null && other.idFournisseur != null) || (this.idFournisseur != null && !this.idFournisseur.equals(other.idFournisseur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Fournisseur[ idFournisseur=" + idFournisseur + " ]";
    }
    
}
