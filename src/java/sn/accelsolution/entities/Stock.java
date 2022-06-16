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
@Table(name = "stock")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stock.findAll", query = "SELECT s FROM Stock s")
    , @NamedQuery(name = "Stock.findByIdSotk", query = "SELECT s FROM Stock s WHERE s.idSotk = :idSotk")
    , @NamedQuery(name = "Stock.findByDesignation", query = "SELECT s FROM Stock s WHERE s.designation = :designation")
    , @NamedQuery(name = "Stock.findByDateEntreStock", query = "SELECT s FROM Stock s WHERE s.dateEntreStock = :dateEntreStock")
    , @NamedQuery(name = "Stock.findByQtStock", query = "SELECT s FROM Stock s WHERE s.qtStock = :qtStock")
    , @NamedQuery(name = "Stock.findByPuStock", query = "SELECT s FROM Stock s WHERE s.puStock = :puStock")
    , @NamedQuery(name = "Stock.findByQtSeuille", query = "SELECT s FROM Stock s WHERE s.qtSeuille = :qtSeuille")
    , @NamedQuery(name = "Stock.findByDateLastAppro", query = "SELECT s FROM Stock s WHERE s.dateLastAppro = :dateLastAppro")
    , @NamedQuery(name = "Stock.findByQt", query = "SELECT s FROM Stock s WHERE s.qt = :qt")
    , @NamedQuery(name = "Stock.findByPu", query = "SELECT s FROM Stock s WHERE s.pu = :pu")})
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSotk")
    private Integer idSotk;
    @Size(max = 255)
    @Column(name = "designation")
    private String designation;
    @Size(max = 50)
    @Column(name = "dateEntreStock")
    private String dateEntreStock;
    @Column(name = "qtStock")
    private Integer qtStock;
    @Size(max = 50)
    @Column(name = "puStock")
    private String puStock;
    @Column(name = "qtSeuille")
    private Integer qtSeuille;
    @Size(max = 50)
    @Column(name = "dateLastAppro")
    private String dateLastAppro;
    @Column(name = "qt")
    private Integer qt;
    @Size(max = 50)
    @Column(name = "pu")
    private String pu;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "commentaire")
    private String commentaire;
    @JoinColumn(name = "idCommande", referencedColumnName = "idCommande")
    @ManyToOne
    private Commande idCommande;
    @JoinColumn(name = "idEntrepot", referencedColumnName = "idEntrepot")
    @ManyToOne
    private Entrepot idEntrepot;
    @JoinColumn(name = "idFournisseur", referencedColumnName = "idFournisseur")
    @ManyToOne
    private Fournisseur idFournisseur;
    @JoinColumn(name = "idMarchandise", referencedColumnName = "idMarchandise")
    @ManyToOne
    private Marchandise idMarchandise;
    @JoinColumn(name = "idUnitemesure", referencedColumnName = "idUnitemesure")
    @ManyToOne
    private Unitemesure idUnitemesure;
    @JoinColumn(name = "idUtilisateur", referencedColumnName = "idUtilisateur")
    @ManyToOne
    private Utilisateur idUtilisateur;
    @OneToMany(mappedBy = "idStock")
    private List<Sortiestock> sortiestockList;

    public Stock() {
    }

    public Stock(Integer idSotk) {
        this.idSotk = idSotk;
    }

    public Integer getIdSotk() {
        return idSotk;
    }

    public void setIdSotk(Integer idSotk) {
        this.idSotk = idSotk;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDateEntreStock() {
        return dateEntreStock;
    }

    public void setDateEntreStock(String dateEntreStock) {
        this.dateEntreStock = dateEntreStock;
    }

    public Integer getQtStock() {
        return qtStock;
    }

    public void setQtStock(Integer qtStock) {
        this.qtStock = qtStock;
    }

    public String getPuStock() {
        return puStock;
    }

    public void setPuStock(String puStock) {
        this.puStock = puStock;
    }

    public Integer getQtSeuille() {
        return qtSeuille;
    }

    public void setQtSeuille(Integer qtSeuille) {
        this.qtSeuille = qtSeuille;
    }

    public String getDateLastAppro() {
        return dateLastAppro;
    }

    public void setDateLastAppro(String dateLastAppro) {
        this.dateLastAppro = dateLastAppro;
    }

    public Integer getQt() {
        return qt;
    }

    public void setQt(Integer qt) {
        this.qt = qt;
    }

    public String getPu() {
        return pu;
    }

    public void setPu(String pu) {
        this.pu = pu;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
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

    public Marchandise getIdMarchandise() {
        return idMarchandise;
    }

    public void setIdMarchandise(Marchandise idMarchandise) {
        this.idMarchandise = idMarchandise;
    }

    public Unitemesure getIdUnitemesure() {
        return idUnitemesure;
    }

    public void setIdUnitemesure(Unitemesure idUnitemesure) {
        this.idUnitemesure = idUnitemesure;
    }

    public Utilisateur getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Utilisateur idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
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
        hash += (idSotk != null ? idSotk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stock)) {
            return false;
        }
        Stock other = (Stock) object;
        if ((this.idSotk == null && other.idSotk != null) || (this.idSotk != null && !this.idSotk.equals(other.idSotk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Stock[ idSotk=" + idSotk + " ]";
    }
    
}
