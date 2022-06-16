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
@Table(name = "notification")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notification.findAll", query = "SELECT n FROM Notification n")
    , @NamedQuery(name = "Notification.findByIdNotification", query = "SELECT n FROM Notification n WHERE n.idNotification = :idNotification")
    , @NamedQuery(name = "Notification.findByMessage", query = "SELECT n FROM Notification n WHERE n.message = :message")
    , @NamedQuery(name = "Notification.findByDateNotification", query = "SELECT n FROM Notification n WHERE n.dateNotification = :dateNotification")
    , @NamedQuery(name = "Notification.findByHeure", query = "SELECT n FROM Notification n WHERE n.heure = :heure")
    , @NamedQuery(name = "Notification.findByMinute", query = "SELECT n FROM Notification n WHERE n.minute = :minute")
    , @NamedQuery(name = "Notification.findBySeconde", query = "SELECT n FROM Notification n WHERE n.seconde = :seconde")
    , @NamedQuery(name = "Notification.findByTraitement", query = "SELECT n FROM Notification n WHERE n.traitement = :traitement")
    , @NamedQuery(name = "Notification.findByTypeNotification", query = "SELECT n FROM Notification n WHERE n.typeNotification = :typeNotification")
    , @NamedQuery(name = "Notification.findByEtatNotification", query = "SELECT n FROM Notification n WHERE n.etatNotification = :etatNotification")})
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idNotification")
    private Integer idNotification;
    @Size(max = 250)
    @Column(name = "message")
    private String message;
    @Size(max = 50)
    @Column(name = "dateNotification")
    private String dateNotification;
    @Size(max = 250)
    @Column(name = "heure")
    private String heure;
    @Size(max = 50)
    @Column(name = "minute")
    private String minute;
    @Size(max = 50)
    @Column(name = "seconde")
    private String seconde;
    @Size(max = 250)
    @Column(name = "traitement")
    private String traitement;
    @Size(max = 250)
    @Column(name = "typeNotification")
    private String typeNotification;
    @Size(max = 250)
    @Column(name = "etatNotification")
    private String etatNotification;
    @JoinColumn(name = "idCommande", referencedColumnName = "idCommande")
    @ManyToOne
    private Commande idCommande;
    @JoinColumn(name = "idDecomptep", referencedColumnName = "idDecomptep")
    @ManyToOne
    private Decomptep idDecomptep;
    @JoinColumn(name = "idDevis", referencedColumnName = "idDevis")
    @ManyToOne
    private Devis idDevis;
    @JoinColumn(name = "idExpression", referencedColumnName = "idExpression")
    @ManyToOne
    private Expressionbesoin idExpression;
    @JoinColumn(name = "idNewfacture", referencedColumnName = "idNewfacture")
    @ManyToOne
    private Newfacture idNewfacture;
    @JoinColumn(name = "idSalaire", referencedColumnName = "idSalaire")
    @ManyToOne
    private Salaire idSalaire;
    @JoinColumn(name = "idUtilisateur", referencedColumnName = "idUtilisateur")
    @ManyToOne
    private Utilisateur idUtilisateur;

    public Notification() {
    }

    public Notification(Integer idNotification) {
        this.idNotification = idNotification;
    }

    public Integer getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(Integer idNotification) {
        this.idNotification = idNotification;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDateNotification() {
        return dateNotification;
    }

    public void setDateNotification(String dateNotification) {
        this.dateNotification = dateNotification;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getSeconde() {
        return seconde;
    }

    public void setSeconde(String seconde) {
        this.seconde = seconde;
    }

    public String getTraitement() {
        return traitement;
    }

    public void setTraitement(String traitement) {
        this.traitement = traitement;
    }

    public String getTypeNotification() {
        return typeNotification;
    }

    public void setTypeNotification(String typeNotification) {
        this.typeNotification = typeNotification;
    }

    public String getEtatNotification() {
        return etatNotification;
    }

    public void setEtatNotification(String etatNotification) {
        this.etatNotification = etatNotification;
    }

    public Commande getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Commande idCommande) {
        this.idCommande = idCommande;
    }

    public Decomptep getIdDecomptep() {
        return idDecomptep;
    }

    public void setIdDecomptep(Decomptep idDecomptep) {
        this.idDecomptep = idDecomptep;
    }

    public Devis getIdDevis() {
        return idDevis;
    }

    public void setIdDevis(Devis idDevis) {
        this.idDevis = idDevis;
    }

    public Expressionbesoin getIdExpression() {
        return idExpression;
    }

    public void setIdExpression(Expressionbesoin idExpression) {
        this.idExpression = idExpression;
    }

    public Newfacture getIdNewfacture() {
        return idNewfacture;
    }

    public void setIdNewfacture(Newfacture idNewfacture) {
        this.idNewfacture = idNewfacture;
    }

    public Salaire getIdSalaire() {
        return idSalaire;
    }

    public void setIdSalaire(Salaire idSalaire) {
        this.idSalaire = idSalaire;
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
        hash += (idNotification != null ? idNotification.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notification)) {
            return false;
        }
        Notification other = (Notification) object;
        if ((this.idNotification == null && other.idNotification != null) || (this.idNotification != null && !this.idNotification.equals(other.idNotification))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Notification[ idNotification=" + idNotification + " ]";
    }
    
}
