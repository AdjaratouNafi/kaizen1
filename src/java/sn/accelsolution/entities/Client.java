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
@Table(name = "client")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c")
    , @NamedQuery(name = "Client.findByIdClient", query = "SELECT c FROM Client c WHERE c.idClient = :idClient")
    , @NamedQuery(name = "Client.findByNomClient", query = "SELECT c FROM Client c WHERE c.nomClient = :nomClient")
    , @NamedQuery(name = "Client.findByAdresse", query = "SELECT c FROM Client c WHERE c.adresse = :adresse")
    , @NamedQuery(name = "Client.findByTelephoneClient", query = "SELECT c FROM Client c WHERE c.telephoneClient = :telephoneClient")
    , @NamedQuery(name = "Client.findByBpClient", query = "SELECT c FROM Client c WHERE c.bpClient = :bpClient")
    , @NamedQuery(name = "Client.findByMailClient", query = "SELECT c FROM Client c WHERE c.mailClient = :mailClient")})
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idClient")
    private Integer idClient;
    @Size(max = 50)
    @Column(name = "nomClient")
    private String nomClient;
    @Size(max = 255)
    @Column(name = "adresse")
    private String adresse;
    @Size(max = 50)
    @Column(name = "telephoneClient")
    private String telephoneClient;
    @Size(max = 50)
    @Column(name = "bpClient")
    private String bpClient;
    @Size(max = 50)
    @Column(name = "mailClient")
    private String mailClient;
    @OneToMany(mappedBy = "idClient")
    private List<Decompte> decompteList;
    @OneToMany(mappedBy = "idClient")
    private List<Marche> marcheList;
    @OneToMany(mappedBy = "idClient")
    private List<Facture> factureList;
    @OneToMany(mappedBy = "idClient")
    private List<Prospection> prospectionList;
    @OneToMany(mappedBy = "idClient")
    private List<Devis> devisList;
    @OneToMany(mappedBy = "idClient")
    private List<Commande> commandeList;
    @OneToMany(mappedBy = "idClient")
    private List<Operation> operationList;

    public Client() {
    }

    public Client(Integer idClient) {
        this.idClient = idClient;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephoneClient() {
        return telephoneClient;
    }

    public void setTelephoneClient(String telephoneClient) {
        this.telephoneClient = telephoneClient;
    }

    public String getBpClient() {
        return bpClient;
    }

    public void setBpClient(String bpClient) {
        this.bpClient = bpClient;
    }

    public String getMailClient() {
        return mailClient;
    }

    public void setMailClient(String mailClient) {
        this.mailClient = mailClient;
    }

    @XmlTransient
    @JsonIgnore
    public List<Decompte> getDecompteList() {
        return decompteList;
    }

    public void setDecompteList(List<Decompte> decompteList) {
        this.decompteList = decompteList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Marche> getMarcheList() {
        return marcheList;
    }

    public void setMarcheList(List<Marche> marcheList) {
        this.marcheList = marcheList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Facture> getFactureList() {
        return factureList;
    }

    public void setFactureList(List<Facture> factureList) {
        this.factureList = factureList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Prospection> getProspectionList() {
        return prospectionList;
    }

    public void setProspectionList(List<Prospection> prospectionList) {
        this.prospectionList = prospectionList;
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
    public List<Commande> getCommandeList() {
        return commandeList;
    }

    public void setCommandeList(List<Commande> commandeList) {
        this.commandeList = commandeList;
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
        hash += (idClient != null ? idClient.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.idClient == null && other.idClient != null) || (this.idClient != null && !this.idClient.equals(other.idClient))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Client[ idClient=" + idClient + " ]";
    }
    
}
