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
@Table(name = "operation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Operation.findAll", query = "SELECT o FROM Operation o")
    , @NamedQuery(name = "Operation.findByIdOperation", query = "SELECT o FROM Operation o WHERE o.idOperation = :idOperation")
    , @NamedQuery(name = "Operation.findByDateoperation", query = "SELECT o FROM Operation o WHERE o.dateoperation = :dateoperation")
    , @NamedQuery(name = "Operation.findByObjet", query = "SELECT o FROM Operation o WHERE o.objet = :objet")
    , @NamedQuery(name = "Operation.findByDebit", query = "SELECT o FROM Operation o WHERE o.debit = :debit")
    , @NamedQuery(name = "Operation.findByMontantlettre", query = "SELECT o FROM Operation o WHERE o.montantlettre = :montantlettre")
    , @NamedQuery(name = "Operation.findByNature", query = "SELECT o FROM Operation o WHERE o.nature = :nature")
    , @NamedQuery(name = "Operation.findByAnciensolde", query = "SELECT o FROM Operation o WHERE o.anciensolde = :anciensolde")
    , @NamedQuery(name = "Operation.findByBeneficiaire", query = "SELECT o FROM Operation o WHERE o.beneficiaire = :beneficiaire")
    , @NamedQuery(name = "Operation.findByNouveausolde", query = "SELECT o FROM Operation o WHERE o.nouveausolde = :nouveausolde")
    , @NamedQuery(name = "Operation.findByTypeoperation", query = "SELECT o FROM Operation o WHERE o.typeoperation = :typeoperation")
    , @NamedQuery(name = "Operation.findByTypebeneficiaire", query = "SELECT o FROM Operation o WHERE o.typebeneficiaire = :typebeneficiaire")
    , @NamedQuery(name = "Operation.findByValuecredit", query = "SELECT o FROM Operation o WHERE o.valuecredit = :valuecredit")
    , @NamedQuery(name = "Operation.findByNombanque", query = "SELECT o FROM Operation o WHERE o.nombanque = :nombanque")
    , @NamedQuery(name = "Operation.findByValidateur", query = "SELECT o FROM Operation o WHERE o.validateur = :validateur")
    , @NamedQuery(name = "Operation.findByObservation", query = "SELECT o FROM Operation o WHERE o.observation = :observation")})
public class Operation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idOperation")
    private Integer idOperation;
    @Size(max = 50)
    @Column(name = "dateoperation")
    private String dateoperation;
    @Size(max = 50)
    @Column(name = "objet")
    private String objet;
    @Size(max = 50)
    @Column(name = "debit")
    private String debit;
    @Size(max = 50)
    @Column(name = "montantlettre")
    private String montantlettre;
    @Size(max = 50)
    @Column(name = "nature")
    private String nature;
    @Size(max = 50)
    @Column(name = "anciensolde")
    private String anciensolde;
    @Size(max = 50)
    @Column(name = "beneficiaire")
    private String beneficiaire;
    @Size(max = 50)
    @Column(name = "nouveausolde")
    private String nouveausolde;
    @Size(max = 50)
    @Column(name = "typeoperation")
    private String typeoperation;
    @Size(max = 50)
    @Column(name = "typebeneficiaire")
    private String typebeneficiaire;
    @Size(max = 50)
    @Column(name = "valuecredit")
    private String valuecredit;
    @Size(max = 50)
    @Column(name = "nombanque")
    private String nombanque;
    @Size(max = 50)
    @Column(name = "validateur")
    private String validateur;
    @Size(max = 50)
    @Column(name = "observation")
    private String observation;
    @JoinColumn(name = "idBanque", referencedColumnName = "numeroBanque")
    @ManyToOne
    private Banque idBanque;
    @JoinColumn(name = "idCaisse", referencedColumnName = "numeroCaisse")
    @ManyToOne
    private Caisse idCaisse;
    @JoinColumn(name = "idChantier", referencedColumnName = "idChantier")
    @ManyToOne
    private Chantier idChantier;
    @JoinColumn(name = "idClient", referencedColumnName = "idClient")
    @ManyToOne
    private Client idClient;
    @JoinColumn(name = "idMarche", referencedColumnName = "idMarche")
    @ManyToOne
    private Marche idMarche;
    @JoinColumn(name = "idApprobateur", referencedColumnName = "idUtilisateur")
    @ManyToOne
    private Utilisateur idApprobateur;

    public Operation() {
    }

    public Operation(Integer idOperation) {
        this.idOperation = idOperation;
    }

    public Integer getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(Integer idOperation) {
        this.idOperation = idOperation;
    }

    public String getDateoperation() {
        return dateoperation;
    }

    public void setDateoperation(String dateoperation) {
        this.dateoperation = dateoperation;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getDebit() {
        return debit;
    }

    public void setDebit(String debit) {
        this.debit = debit;
    }

    public String getMontantlettre() {
        return montantlettre;
    }

    public void setMontantlettre(String montantlettre) {
        this.montantlettre = montantlettre;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getAnciensolde() {
        return anciensolde;
    }

    public void setAnciensolde(String anciensolde) {
        this.anciensolde = anciensolde;
    }

    public String getBeneficiaire() {
        return beneficiaire;
    }

    public void setBeneficiaire(String beneficiaire) {
        this.beneficiaire = beneficiaire;
    }

    public String getNouveausolde() {
        return nouveausolde;
    }

    public void setNouveausolde(String nouveausolde) {
        this.nouveausolde = nouveausolde;
    }

    public String getTypeoperation() {
        return typeoperation;
    }

    public void setTypeoperation(String typeoperation) {
        this.typeoperation = typeoperation;
    }

    public String getTypebeneficiaire() {
        return typebeneficiaire;
    }

    public void setTypebeneficiaire(String typebeneficiaire) {
        this.typebeneficiaire = typebeneficiaire;
    }

    public String getValuecredit() {
        return valuecredit;
    }

    public void setValuecredit(String valuecredit) {
        this.valuecredit = valuecredit;
    }

    public String getNombanque() {
        return nombanque;
    }

    public void setNombanque(String nombanque) {
        this.nombanque = nombanque;
    }

    public String getValidateur() {
        return validateur;
    }

    public void setValidateur(String validateur) {
        this.validateur = validateur;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Banque getIdBanque() {
        return idBanque;
    }

    public void setIdBanque(Banque idBanque) {
        this.idBanque = idBanque;
    }

    public Caisse getIdCaisse() {
        return idCaisse;
    }

    public void setIdCaisse(Caisse idCaisse) {
        this.idCaisse = idCaisse;
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

    public Marche getIdMarche() {
        return idMarche;
    }

    public void setIdMarche(Marche idMarche) {
        this.idMarche = idMarche;
    }

    public Utilisateur getIdApprobateur() {
        return idApprobateur;
    }

    public void setIdApprobateur(Utilisateur idApprobateur) {
        this.idApprobateur = idApprobateur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOperation != null ? idOperation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Operation)) {
            return false;
        }
        Operation other = (Operation) object;
        if ((this.idOperation == null && other.idOperation != null) || (this.idOperation != null && !this.idOperation.equals(other.idOperation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Operation[ idOperation=" + idOperation + " ]";
    }
    
}
