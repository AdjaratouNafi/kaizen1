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
@Table(name = "prospect")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prospect.findAll", query = "SELECT p FROM Prospect p")
    , @NamedQuery(name = "Prospect.findByIdProspectClient", query = "SELECT p FROM Prospect p WHERE p.idProspectClient = :idProspectClient")
    , @NamedQuery(name = "Prospect.findByNomProspect", query = "SELECT p FROM Prospect p WHERE p.nomProspect = :nomProspect")
    , @NamedQuery(name = "Prospect.findByAdresse", query = "SELECT p FROM Prospect p WHERE p.adresse = :adresse")
    , @NamedQuery(name = "Prospect.findByTelephoneProspect", query = "SELECT p FROM Prospect p WHERE p.telephoneProspect = :telephoneProspect")
    , @NamedQuery(name = "Prospect.findByBpProspect", query = "SELECT p FROM Prospect p WHERE p.bpProspect = :bpProspect")
    , @NamedQuery(name = "Prospect.findByMailProspect", query = "SELECT p FROM Prospect p WHERE p.mailProspect = :mailProspect")
    , @NamedQuery(name = "Prospect.findByClient", query = "SELECT p FROM Prospect p WHERE p.client = :client")})
public class Prospect implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProspectClient")
    private Integer idProspectClient;
    @Size(max = 50)
    @Column(name = "nomProspect")
    private String nomProspect;
    @Size(max = 50)
    @Column(name = "adresse")
    private String adresse;
    @Size(max = 50)
    @Column(name = "telephoneProspect")
    private String telephoneProspect;
    @Size(max = 50)
    @Column(name = "bpProspect")
    private String bpProspect;
    @Size(max = 50)
    @Column(name = "mailProspect")
    private String mailProspect;
    @Size(max = 50)
    @Column(name = "client")
    private String client;
    @OneToMany(mappedBy = "idProspectClient")
    private List<Prospection> prospectionList;

    public Prospect() {
    }

    public Prospect(Integer idProspectClient) {
        this.idProspectClient = idProspectClient;
    }

    public Integer getIdProspectClient() {
        return idProspectClient;
    }

    public void setIdProspectClient(Integer idProspectClient) {
        this.idProspectClient = idProspectClient;
    }

    public String getNomProspect() {
        return nomProspect;
    }

    public void setNomProspect(String nomProspect) {
        this.nomProspect = nomProspect;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephoneProspect() {
        return telephoneProspect;
    }

    public void setTelephoneProspect(String telephoneProspect) {
        this.telephoneProspect = telephoneProspect;
    }

    public String getBpProspect() {
        return bpProspect;
    }

    public void setBpProspect(String bpProspect) {
        this.bpProspect = bpProspect;
    }

    public String getMailProspect() {
        return mailProspect;
    }

    public void setMailProspect(String mailProspect) {
        this.mailProspect = mailProspect;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    @XmlTransient
    @JsonIgnore
    public List<Prospection> getProspectionList() {
        return prospectionList;
    }

    public void setProspectionList(List<Prospection> prospectionList) {
        this.prospectionList = prospectionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProspectClient != null ? idProspectClient.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prospect)) {
            return false;
        }
        Prospect other = (Prospect) object;
        if ((this.idProspectClient == null && other.idProspectClient != null) || (this.idProspectClient != null && !this.idProspectClient.equals(other.idProspectClient))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Prospect[ idProspectClient=" + idProspectClient + " ]";
    }
    
}
