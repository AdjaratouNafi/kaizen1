/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "utilisateur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Utilisateur.findAll", query = "SELECT u FROM Utilisateur u")
    , @NamedQuery(name = "Utilisateur.findByIdUtilisateur", query = "SELECT u FROM Utilisateur u WHERE u.idUtilisateur = :idUtilisateur")
    , @NamedQuery(name = "Utilisateur.findByCni", query = "SELECT u FROM Utilisateur u WHERE u.cni = :cni")
    , @NamedQuery(name = "Utilisateur.findByNomUtilisateur", query = "SELECT u FROM Utilisateur u WHERE u.nomUtilisateur = :nomUtilisateur")
    , @NamedQuery(name = "Utilisateur.findByPrenomUtilisateur", query = "SELECT u FROM Utilisateur u WHERE u.prenomUtilisateur = :prenomUtilisateur")
    , @NamedQuery(name = "Utilisateur.findByAdresseUtilisateur", query = "SELECT u FROM Utilisateur u WHERE u.adresseUtilisateur = :adresseUtilisateur")
    , @NamedQuery(name = "Utilisateur.findByTelUtilisateur", query = "SELECT u FROM Utilisateur u WHERE u.telUtilisateur = :telUtilisateur")
    , @NamedQuery(name = "Utilisateur.findByMailUtilisateur", query = "SELECT u FROM Utilisateur u WHERE u.mailUtilisateur = :mailUtilisateur")
    , @NamedQuery(name = "Utilisateur.findByPasswordUtisateur", query = "SELECT u FROM Utilisateur u WHERE u.passwordUtisateur = :passwordUtisateur")
    , @NamedQuery(name = "Utilisateur.findByNumeroSecuriteSociale", query = "SELECT u FROM Utilisateur u WHERE u.numeroSecuriteSociale = :numeroSecuriteSociale")
    , @NamedQuery(name = "Utilisateur.findByDateNaissanceUtilisateur", query = "SELECT u FROM Utilisateur u WHERE u.dateNaissanceUtilisateur = :dateNaissanceUtilisateur")
    , @NamedQuery(name = "Utilisateur.findByLieuNaissanceUtilisateur", query = "SELECT u FROM Utilisateur u WHERE u.lieuNaissanceUtilisateur = :lieuNaissanceUtilisateur")
    , @NamedQuery(name = "Utilisateur.findBySituationfamille", query = "SELECT u FROM Utilisateur u WHERE u.situationfamille = :situationfamille")
    , @NamedQuery(name = "Utilisateur.findByFonction", query = "SELECT u FROM Utilisateur u WHERE u.fonction = :fonction")
    , @NamedQuery(name = "Utilisateur.findByHcreationpwd", query = "SELECT u FROM Utilisateur u WHERE u.hcreationpwd = :hcreationpwd")
    , @NamedQuery(name = "Utilisateur.findByMcreationpwd", query = "SELECT u FROM Utilisateur u WHERE u.mcreationpwd = :mcreationpwd")
    , @NamedQuery(name = "Utilisateur.findByScreationpwd", query = "SELECT u FROM Utilisateur u WHERE u.screationpwd = :screationpwd")
    , @NamedQuery(name = "Utilisateur.findByEtatpwd", query = "SELECT u FROM Utilisateur u WHERE u.etatpwd = :etatpwd")
    , @NamedQuery(name = "Utilisateur.findByEtatcompte", query = "SELECT u FROM Utilisateur u WHERE u.etatcompte = :etatcompte")
    , @NamedQuery(name = "Utilisateur.findByFirstconnection", query = "SELECT u FROM Utilisateur u WHERE u.firstconnection = :firstconnection")})
public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUtilisateur")
    private Integer idUtilisateur;
    @Size(max = 50)
    @Column(name = "cni")
    private String cni;
    @Size(max = 50)
    @Column(name = "nomUtilisateur")
    private String nomUtilisateur;
    @Size(max = 50)
    @Column(name = "prenomUtilisateur")
    private String prenomUtilisateur;
    @Size(max = 50)
    @Column(name = "adresseUtilisateur")
    private String adresseUtilisateur;
    @Size(max = 50)
    @Column(name = "telUtilisateur")
    private String telUtilisateur;
    @Size(max = 50)
    @Column(name = "mailUtilisateur")
    private String mailUtilisateur;
    @Size(max = 50)
    @Column(name = "passwordUtisateur")
    private String passwordUtisateur;
    @Size(max = 50)
    @Column(name = "numeroSecuriteSociale")
    private String numeroSecuriteSociale;
    @Size(max = 50)
    @Column(name = "dateNaissanceUtilisateur")
    private String dateNaissanceUtilisateur;
    @Size(max = 50)
    @Column(name = "lieuNaissanceUtilisateur")
    private String lieuNaissanceUtilisateur;
    @Size(max = 50)
    @Column(name = "situationfamille")
    private String situationfamille;
    @Size(max = 50)
    @Column(name = "fonction")
    private String fonction;
    @Column(name = "hcreationpwd")
    private Integer hcreationpwd;
    @Column(name = "mcreationpwd")
    private Integer mcreationpwd;
    @Column(name = "screationpwd")
    private Integer screationpwd;
    @Size(max = 250)
    @Column(name = "etatpwd")
    private String etatpwd;
    @Size(max = 250)
    @Column(name = "etatcompte")
    private String etatcompte;
    @Size(max = 50)
    @Column(name = "firstconnection")
    private String firstconnection;
    @OneToMany(mappedBy = "idUtilisateur")
    private List<Brouillard> brouillardList;
    @OneToMany(mappedBy = "idUtilisateur")
    private List<Contrat> contratList;
    @OneToMany(mappedBy = "idUtilisateur")
    private List<Pret> pretList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUtilisateur")
    private List<UserIndemnite> userIndemniteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUtilisateur")
    private List<Demande> demandeList;
    @OneToMany(mappedBy = "idUtilisateur")
    private List<Assurer> assurerList;
    @OneToMany(mappedBy = "idUtilisateur")
    private List<Notification> notificationList;
    @JoinColumn(name = "idRole", referencedColumnName = "idRole")
    @ManyToOne
    private Role idRole;
    @OneToMany(mappedBy = "idUtilisateur")
    private List<Entrepot> entrepotList;
    @OneToMany(mappedBy = "idUtilisateur")
    private List<Stock> stockList;
    @OneToMany(mappedBy = "idUtilisateur")
    private List<Salaire> salaireList;
    @OneToMany(mappedBy = "idUtilisateur")
    private List<Decomptep> decomptepList;
    @OneToMany(mappedBy = "idUtilisateur")
    private List<Detailleappeloffre> detailleappeloffreList;
    @OneToMany(mappedBy = "idUtilisateur")
    private List<Opportunite> opportuniteList;
    @OneToMany(mappedBy = "idUtilisateur")
    private List<Conge> congeList;
    @OneToMany(mappedBy = "idUtilisateur")
    private List<Devis> devisList;
    @OneToMany(mappedBy = "idUtilisateur")
    private List<Chantier> chantierList;
    @OneToMany(mappedBy = "idUtilisateur")
    private List<Droitacces> droitaccesList;
    @OneToMany(mappedBy = "idUtilisateur")
    private List<Detaillechantier> detaillechantierList;
    @OneToMany(mappedBy = "idUtilisateur")
    private List<Expressionbesoin> expressionbesoinList;
    @OneToMany(mappedBy = "idUtilisateur")
    private List<Commande> commandeList;
    @OneToMany(mappedBy = "idUtilisateur")
    private List<AppelOffre> appelOffreList;
    @OneToMany(mappedBy = "idUtilisateur")
    private List<Sortiestock> sortiestockList;
    @OneToMany(mappedBy = "idUtilisateur")
    private List<Management> managementList;
    @OneToMany(mappedBy = "idUtilisateur")
    private List<Newfacture> newfactureList;
    @OneToMany(mappedBy = "idUtilisateur")
    private List<Indemnite> indemniteList;
    @OneToMany(mappedBy = "idApprobateur")
    private List<Operation> operationList;

    public Utilisateur() {
    }

    public Utilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getCni() {
        return cni;
    }

    public void setCni(String cni) {
        this.cni = cni;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getPrenomUtilisateur() {
        return prenomUtilisateur;
    }

    public void setPrenomUtilisateur(String prenomUtilisateur) {
        this.prenomUtilisateur = prenomUtilisateur;
    }

    public String getAdresseUtilisateur() {
        return adresseUtilisateur;
    }

    public void setAdresseUtilisateur(String adresseUtilisateur) {
        this.adresseUtilisateur = adresseUtilisateur;
    }

    public String getTelUtilisateur() {
        return telUtilisateur;
    }

    public void setTelUtilisateur(String telUtilisateur) {
        this.telUtilisateur = telUtilisateur;
    }

    public String getMailUtilisateur() {
        return mailUtilisateur;
    }

    public void setMailUtilisateur(String mailUtilisateur) {
        this.mailUtilisateur = mailUtilisateur;
    }

    public String getPasswordUtisateur() {
        return passwordUtisateur;
    }

    public void setPasswordUtisateur(String passwordUtisateur) {
        this.passwordUtisateur = passwordUtisateur;
    }

    public String getNumeroSecuriteSociale() {
        return numeroSecuriteSociale;
    }

    public void setNumeroSecuriteSociale(String numeroSecuriteSociale) {
        this.numeroSecuriteSociale = numeroSecuriteSociale;
    }

    public String getDateNaissanceUtilisateur() {
        return dateNaissanceUtilisateur;
    }

    public void setDateNaissanceUtilisateur(String dateNaissanceUtilisateur) {
        this.dateNaissanceUtilisateur = dateNaissanceUtilisateur;
    }

    public String getLieuNaissanceUtilisateur() {
        return lieuNaissanceUtilisateur;
    }

    public void setLieuNaissanceUtilisateur(String lieuNaissanceUtilisateur) {
        this.lieuNaissanceUtilisateur = lieuNaissanceUtilisateur;
    }

    public String getSituationfamille() {
        return situationfamille;
    }

    public void setSituationfamille(String situationfamille) {
        this.situationfamille = situationfamille;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public Integer getHcreationpwd() {
        return hcreationpwd;
    }

    public void setHcreationpwd(Integer hcreationpwd) {
        this.hcreationpwd = hcreationpwd;
    }

    public Integer getMcreationpwd() {
        return mcreationpwd;
    }

    public void setMcreationpwd(Integer mcreationpwd) {
        this.mcreationpwd = mcreationpwd;
    }

    public Integer getScreationpwd() {
        return screationpwd;
    }

    public void setScreationpwd(Integer screationpwd) {
        this.screationpwd = screationpwd;
    }

    public String getEtatpwd() {
        return etatpwd;
    }

    public void setEtatpwd(String etatpwd) {
        this.etatpwd = etatpwd;
    }

    public String getEtatcompte() {
        return etatcompte;
    }

    public void setEtatcompte(String etatcompte) {
        this.etatcompte = etatcompte;
    }

    public String getFirstconnection() {
        return firstconnection;
    }

    public void setFirstconnection(String firstconnection) {
        this.firstconnection = firstconnection;
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
    public List<Contrat> getContratList() {
        return contratList;
    }

    public void setContratList(List<Contrat> contratList) {
        this.contratList = contratList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Pret> getPretList() {
        return pretList;
    }

    public void setPretList(List<Pret> pretList) {
        this.pretList = pretList;
    }

    @XmlTransient
    @JsonIgnore
    public List<UserIndemnite> getUserIndemniteList() {
        return userIndemniteList;
    }

    public void setUserIndemniteList(List<UserIndemnite> userIndemniteList) {
        this.userIndemniteList = userIndemniteList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Demande> getDemandeList() {
        return demandeList;
    }

    public void setDemandeList(List<Demande> demandeList) {
        this.demandeList = demandeList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Assurer> getAssurerList() {
        return assurerList;
    }

    public void setAssurerList(List<Assurer> assurerList) {
        this.assurerList = assurerList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Notification> getNotificationList() {
        return notificationList;
    }

    public void setNotificationList(List<Notification> notificationList) {
        this.notificationList = notificationList;
    }

    public Role getIdRole() {
        return idRole;
    }

    public void setIdRole(Role idRole) {
        this.idRole = idRole;
    }

    @XmlTransient
    @JsonIgnore
    public List<Entrepot> getEntrepotList() {
        return entrepotList;
    }

    public void setEntrepotList(List<Entrepot> entrepotList) {
        this.entrepotList = entrepotList;
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
    public List<Salaire> getSalaireList() {
        return salaireList;
    }

    public void setSalaireList(List<Salaire> salaireList) {
        this.salaireList = salaireList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Decomptep> getDecomptepList() {
        return decomptepList;
    }

    public void setDecomptepList(List<Decomptep> decomptepList) {
        this.decomptepList = decomptepList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Detailleappeloffre> getDetailleappeloffreList() {
        return detailleappeloffreList;
    }

    public void setDetailleappeloffreList(List<Detailleappeloffre> detailleappeloffreList) {
        this.detailleappeloffreList = detailleappeloffreList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Opportunite> getOpportuniteList() {
        return opportuniteList;
    }

    public void setOpportuniteList(List<Opportunite> opportuniteList) {
        this.opportuniteList = opportuniteList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Conge> getCongeList() {
        return congeList;
    }

    public void setCongeList(List<Conge> congeList) {
        this.congeList = congeList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Devis> getDevisList() {
        return devisList;
    }

    public void setDevisList(List<Devis> devisList) {
        this.devisList = devisList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Chantier> getChantierList() {
        return chantierList;
    }

    public void setChantierList(List<Chantier> chantierList) {
        this.chantierList = chantierList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Droitacces> getDroitaccesList() {
        return droitaccesList;
    }

    public void setDroitaccesList(List<Droitacces> droitaccesList) {
        this.droitaccesList = droitaccesList;
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
    public List<AppelOffre> getAppelOffreList() {
        return appelOffreList;
    }

    public void setAppelOffreList(List<AppelOffre> appelOffreList) {
        this.appelOffreList = appelOffreList;
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
    public List<Newfacture> getNewfactureList() {
        return newfactureList;
    }

    public void setNewfactureList(List<Newfacture> newfactureList) {
        this.newfactureList = newfactureList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Indemnite> getIndemniteList() {
        return indemniteList;
    }

    public void setIndemniteList(List<Indemnite> indemniteList) {
        this.indemniteList = indemniteList;
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
        hash += (idUtilisateur != null ? idUtilisateur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utilisateur)) {
            return false;
        }
        Utilisateur other = (Utilisateur) object;
        if ((this.idUtilisateur == null && other.idUtilisateur != null) || (this.idUtilisateur != null && !this.idUtilisateur.equals(other.idUtilisateur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Utilisateur[ idUtilisateur=" + idUtilisateur + " ]";
    }
    
}
