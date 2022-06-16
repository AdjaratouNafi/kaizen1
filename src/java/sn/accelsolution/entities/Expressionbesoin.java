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
@Table(name = "expressionbesoin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Expressionbesoin.findAll", query = "SELECT e FROM Expressionbesoin e")
    , @NamedQuery(name = "Expressionbesoin.findByIdExpression", query = "SELECT e FROM Expressionbesoin e WHERE e.idExpression = :idExpression")
    , @NamedQuery(name = "Expressionbesoin.findByNumeroExpression", query = "SELECT e FROM Expressionbesoin e WHERE e.numeroExpression = :numeroExpression")
    , @NamedQuery(name = "Expressionbesoin.findByDateExpression", query = "SELECT e FROM Expressionbesoin e WHERE e.dateExpression = :dateExpression")
    , @NamedQuery(name = "Expressionbesoin.findByMontantlettre", query = "SELECT e FROM Expressionbesoin e WHERE e.montantlettre = :montantlettre")
    , @NamedQuery(name = "Expressionbesoin.findByNivovalidation", query = "SELECT e FROM Expressionbesoin e WHERE e.nivovalidation = :nivovalidation")
    , @NamedQuery(name = "Expressionbesoin.findByEtat", query = "SELECT e FROM Expressionbesoin e WHERE e.etat = :etat")})
public class Expressionbesoin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idExpression")
    private Integer idExpression;
    @Size(max = 255)
    @Column(name = "numeroExpression")
    private String numeroExpression;
    @Size(max = 255)
    @Column(name = "dateExpression")
    private String dateExpression;
    @Size(max = 255)
    @Column(name = "montantlettre")
    private String montantlettre;
    @Size(max = 50)
    @Column(name = "nivovalidation")
    private String nivovalidation;
    @Size(max = 50)
    @Column(name = "etat")
    private String etat;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "motif")
    private String motif;
    @OneToMany(mappedBy = "idExpression")
    private List<Notification> notificationList;
    @OneToMany(mappedBy = "idExpression")
    private List<Detailleexpressionbesoin> detailleexpressionbesoinList;
    @JoinColumn(name = "idChantier", referencedColumnName = "idChantier")
    @ManyToOne
    private Chantier idChantier;
    @JoinColumn(name = "idUtilisateur", referencedColumnName = "idUtilisateur")
    @ManyToOne
    private Utilisateur idUtilisateur;
    @OneToMany(mappedBy = "idExpression")
    private List<Commande> commandeList;

    public Expressionbesoin() {
    }

    public Expressionbesoin(Integer idExpression) {
        this.idExpression = idExpression;
    }

    public Integer getIdExpression() {
        return idExpression;
    }

    public void setIdExpression(Integer idExpression) {
        this.idExpression = idExpression;
    }

    public String getNumeroExpression() {
        return numeroExpression;
    }

    public void setNumeroExpression(String numeroExpression) {
        this.numeroExpression = numeroExpression;
    }

    public String getDateExpression() {
        return dateExpression;
    }

    public void setDateExpression(String dateExpression) {
        this.dateExpression = dateExpression;
    }

    public String getMontantlettre() {
        return montantlettre;
    }

    public void setMontantlettre(String montantlettre) {
        this.montantlettre = montantlettre;
    }

    public String getNivovalidation() {
        return nivovalidation;
    }

    public void setNivovalidation(String nivovalidation) {
        this.nivovalidation = nivovalidation;
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
    public List<Detailleexpressionbesoin> getDetailleexpressionbesoinList() {
        return detailleexpressionbesoinList;
    }

    public void setDetailleexpressionbesoinList(List<Detailleexpressionbesoin> detailleexpressionbesoinList) {
        this.detailleexpressionbesoinList = detailleexpressionbesoinList;
    }

    public Chantier getIdChantier() {
        return idChantier;
    }

    public void setIdChantier(Chantier idChantier) {
        this.idChantier = idChantier;
    }

    public Utilisateur getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Utilisateur idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    @XmlTransient
    @JsonIgnore
    public List<Commande> getCommandeList() {
        return commandeList;
    }

    public void setCommandeList(List<Commande> commandeList) {
        this.commandeList = commandeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExpression != null ? idExpression.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Expressionbesoin)) {
            return false;
        }
        Expressionbesoin other = (Expressionbesoin) object;
        if ((this.idExpression == null && other.idExpression != null) || (this.idExpression != null && !this.idExpression.equals(other.idExpression))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Expressionbesoin[ idExpression=" + idExpression + " ]";
    }
    
}
