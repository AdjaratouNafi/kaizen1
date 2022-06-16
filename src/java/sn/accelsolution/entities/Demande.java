/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DV7
 */
@Entity
@Table(name = "demande")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Demande.findAll", query = "SELECT d FROM Demande d")
    , @NamedQuery(name = "Demande.findByIdDemande", query = "SELECT d FROM Demande d WHERE d.idDemande = :idDemande")
    , @NamedQuery(name = "Demande.findByDateCreationDemande", query = "SELECT d FROM Demande d WHERE d.dateCreationDemande = :dateCreationDemande")
    , @NamedQuery(name = "Demande.findByDestinataire", query = "SELECT d FROM Demande d WHERE d.destinataire = :destinataire")
    , @NamedQuery(name = "Demande.findByObjet", query = "SELECT d FROM Demande d WHERE d.objet = :objet")
    , @NamedQuery(name = "Demande.findByIdSalaire", query = "SELECT d FROM Demande d WHERE d.idSalaire = :idSalaire")})
public class Demande implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDemande")
    private Integer idDemande;
    @Column(name = "dateCreationDemande")
    @Temporal(TemporalType.DATE)
    private Date dateCreationDemande;
    @Size(max = 50)
    @Column(name = "destinataire")
    private String destinataire;
    @Size(max = 50)
    @Column(name = "objet")
    private String objet;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idSalaire")
    private int idSalaire;
    @JoinColumn(name = "idIndemnite", referencedColumnName = "idIndemnite")
    @ManyToOne(optional = false)
    private Indemnite idIndemnite;
    @JoinColumn(name = "idPret", referencedColumnName = "idPret")
    @ManyToOne(optional = false)
    private Pret idPret;
    @JoinColumn(name = "idTypeDemande", referencedColumnName = "idTypeDemande")
    @ManyToOne(optional = false)
    private TypeDemande idTypeDemande;
    @JoinColumn(name = "idUtilisateur", referencedColumnName = "idUtilisateur")
    @ManyToOne(optional = false)
    private Utilisateur idUtilisateur;

    public Demande() {
    }

    public Demande(Integer idDemande) {
        this.idDemande = idDemande;
    }

    public Demande(Integer idDemande, int idSalaire) {
        this.idDemande = idDemande;
        this.idSalaire = idSalaire;
    }

    public Integer getIdDemande() {
        return idDemande;
    }

    public void setIdDemande(Integer idDemande) {
        this.idDemande = idDemande;
    }

    public Date getDateCreationDemande() {
        return dateCreationDemande;
    }

    public void setDateCreationDemande(Date dateCreationDemande) {
        this.dateCreationDemande = dateCreationDemande;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdSalaire() {
        return idSalaire;
    }

    public void setIdSalaire(int idSalaire) {
        this.idSalaire = idSalaire;
    }

    public Indemnite getIdIndemnite() {
        return idIndemnite;
    }

    public void setIdIndemnite(Indemnite idIndemnite) {
        this.idIndemnite = idIndemnite;
    }

    public Pret getIdPret() {
        return idPret;
    }

    public void setIdPret(Pret idPret) {
        this.idPret = idPret;
    }

    public TypeDemande getIdTypeDemande() {
        return idTypeDemande;
    }

    public void setIdTypeDemande(TypeDemande idTypeDemande) {
        this.idTypeDemande = idTypeDemande;
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
        hash += (idDemande != null ? idDemande.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Demande)) {
            return false;
        }
        Demande other = (Demande) object;
        if ((this.idDemande == null && other.idDemande != null) || (this.idDemande != null && !this.idDemande.equals(other.idDemande))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Demande[ idDemande=" + idDemande + " ]";
    }
    
}
