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
@Table(name = "commande")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Commande.findAll", query = "SELECT c FROM Commande c")
    , @NamedQuery(name = "Commande.findByIdCommande", query = "SELECT c FROM Commande c WHERE c.idCommande = :idCommande")
    , @NamedQuery(name = "Commande.findByCode", query = "SELECT c FROM Commande c WHERE c.code = :code")
    , @NamedQuery(name = "Commande.findByDateechance", query = "SELECT c FROM Commande c WHERE c.dateechance = :dateechance")
    , @NamedQuery(name = "Commande.findByModepaiment", query = "SELECT c FROM Commande c WHERE c.modepaiment = :modepaiment")
    , @NamedQuery(name = "Commande.findByObservation", query = "SELECT c FROM Commande c WHERE c.observation = :observation")
    , @NamedQuery(name = "Commande.findByEtat", query = "SELECT c FROM Commande c WHERE c.etat = :etat")
    , @NamedQuery(name = "Commande.findByMotif", query = "SELECT c FROM Commande c WHERE c.motif = :motif")
    , @NamedQuery(name = "Commande.findByTypecommande", query = "SELECT c FROM Commande c WHERE c.typecommande = :typecommande")
    , @NamedQuery(name = "Commande.findByLivree", query = "SELECT c FROM Commande c WHERE c.livree = :livree")
    , @NamedQuery(name = "Commande.findByPositionLivraison", query = "SELECT c FROM Commande c WHERE c.positionLivraison = :positionLivraison")})
public class Commande implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCommande")
    private Integer idCommande;
    @Size(max = 50)
    @Column(name = "code")
    private String code;
    @Size(max = 50)
    @Column(name = "dateechance")
    private String dateechance;
    @Size(max = 50)
    @Column(name = "modepaiment")
    private String modepaiment;
    @Size(max = 50)
    @Column(name = "observation")
    private String observation;
    @Size(max = 255)
    @Column(name = "etat")
    private String etat;
    @Size(max = 250)
    @Column(name = "motif")
    private String motif;
    @Size(max = 255)
    @Column(name = "typecommande")
    private String typecommande;
    @Size(max = 50)
    @Column(name = "livree")
    private String livree;
    @Column(name = "positionLivraison")
    private Integer positionLivraison;
    @OneToMany(mappedBy = "idCommande")
    private List<Paiement> paiementList;
    @OneToMany(mappedBy = "idCommande")
    private List<Notification> notificationList;
    @OneToMany(mappedBy = "idCommande")
    private List<Historiquelivraison> historiquelivraisonList;
    @OneToMany(mappedBy = "idCommande")
    private List<Stock> stockList;
    @OneToMany(mappedBy = "idCommande")
    private List<Reffournisseur> reffournisseurList;
    @OneToMany(mappedBy = "idCommande")
    private List<Acomptefournisseur> acomptefournisseurList;
    @JoinColumn(name = "idChantier", referencedColumnName = "idChantier")
    @ManyToOne
    private Chantier idChantier;
    @JoinColumn(name = "idClient", referencedColumnName = "idClient")
    @ManyToOne
    private Client idClient;
    @JoinColumn(name = "idEntrepot", referencedColumnName = "idEntrepot")
    @ManyToOne
    private Entrepot idEntrepot;
    @JoinColumn(name = "idExpression", referencedColumnName = "idExpression")
    @ManyToOne
    private Expressionbesoin idExpression;
    @JoinColumn(name = "idFournisseur", referencedColumnName = "idFournisseur")
    @ManyToOne
    private Fournisseur idFournisseur;
    @JoinColumn(name = "idMarche", referencedColumnName = "idMarche")
    @ManyToOne
    private Marche idMarche;
    @JoinColumn(name = "idTaxe", referencedColumnName = "idTaxe")
    @ManyToOne
    private Taxe idTaxe;
    @JoinColumn(name = "idUtilisateur", referencedColumnName = "idUtilisateur")
    @ManyToOne
    private Utilisateur idUtilisateur;
    @OneToMany(mappedBy = "idCommande")
    private List<DetailleCommande> detailleCommandeList;
    @OneToMany(mappedBy = "idCommande")
    private List<Livraison> livraisonList;

    public Commande() {
    }

    public Commande(Integer idCommande) {
        this.idCommande = idCommande;
    }

    public Integer getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Integer idCommande) {
        this.idCommande = idCommande;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDateechance() {
        return dateechance;
    }

    public void setDateechance(String dateechance) {
        this.dateechance = dateechance;
    }

    public String getModepaiment() {
        return modepaiment;
    }

    public void setModepaiment(String modepaiment) {
        this.modepaiment = modepaiment;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getTypecommande() {
        return typecommande;
    }

    public void setTypecommande(String typecommande) {
        this.typecommande = typecommande;
    }

    public String getLivree() {
        return livree;
    }

    public void setLivree(String livree) {
        this.livree = livree;
    }

    public Integer getPositionLivraison() {
        return positionLivraison;
    }

    public void setPositionLivraison(Integer positionLivraison) {
        this.positionLivraison = positionLivraison;
    }

    @XmlTransient
    @JsonIgnore
    public List<Paiement> getPaiementList() {
        return paiementList;
    }

    public void setPaiementList(List<Paiement> paiementList) {
        this.paiementList = paiementList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Notification> getNotificationList() {
        return notificationList;
    }

    public void setNotificationList(List<Notification> notificationList) {
        this.notificationList = notificationList;
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

    public Chantier getIdChantier() {
        return idChantier;
    }

    public void setIdChantier(Chantier idChantier) {
        this.idChantier = idChantier;
    }

    public Client getIdClient() {
        return idClient;
    }

    public void setIdClient(Client idClient) {
        this.idClient = idClient;
    }

    public Entrepot getIdEntrepot() {
        return idEntrepot;
    }

    public void setIdEntrepot(Entrepot idEntrepot) {
        this.idEntrepot = idEntrepot;
    }

    public Expressionbesoin getIdExpression() {
        return idExpression;
    }

    public void setIdExpression(Expressionbesoin idExpression) {
        this.idExpression = idExpression;
    }

    public Fournisseur getIdFournisseur() {
        return idFournisseur;
    }

    public void setIdFournisseur(Fournisseur idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    public Marche getIdMarche() {
        return idMarche;
    }

    public void setIdMarche(Marche idMarche) {
        this.idMarche = idMarche;
    }

    public Taxe getIdTaxe() {
        return idTaxe;
    }

    public void setIdTaxe(Taxe idTaxe) {
        this.idTaxe = idTaxe;
    }

    public Utilisateur getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Utilisateur idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    @XmlTransient
    @JsonIgnore
    public List<DetailleCommande> getDetailleCommandeList() {
        return detailleCommandeList;
    }

    public void setDetailleCommandeList(List<DetailleCommande> detailleCommandeList) {
        this.detailleCommandeList = detailleCommandeList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Livraison> getLivraisonList() {
        return livraisonList;
    }

    public void setLivraisonList(List<Livraison> livraisonList) {
        this.livraisonList = livraisonList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCommande != null ? idCommande.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Commande)) {
            return false;
        }
        Commande other = (Commande) object;
        if ((this.idCommande == null && other.idCommande != null) || (this.idCommande != null && !this.idCommande.equals(other.idCommande))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Commande[ idCommande=" + idCommande + " ]";
    }
    
}
