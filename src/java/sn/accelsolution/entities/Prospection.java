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
@Table(name = "prospection")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prospection.findAll", query = "SELECT p FROM Prospection p")
    , @NamedQuery(name = "Prospection.findByIdProspect", query = "SELECT p FROM Prospection p WHERE p.idProspect = :idProspect")
    , @NamedQuery(name = "Prospection.findByDateProspection", query = "SELECT p FROM Prospection p WHERE p.dateProspection = :dateProspection")})
public class Prospection implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProspect")
    private Integer idProspect;
    @Size(max = 50)
    @Column(name = "dateProspection")
    private String dateProspection;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "descriptionProspection")
    private String descriptionProspection;
    @OneToMany(mappedBy = "idProspect")
    private List<ProspectFacture> prospectFactureList;
    @JoinColumn(name = "idClient", referencedColumnName = "idClient")
    @ManyToOne
    private Client idClient;
    @JoinColumn(name = "idProspectClient", referencedColumnName = "idProspectClient")
    @ManyToOne
    private Prospect idProspectClient;

    public Prospection() {
    }

    public Prospection(Integer idProspect) {
        this.idProspect = idProspect;
    }

    public Integer getIdProspect() {
        return idProspect;
    }

    public void setIdProspect(Integer idProspect) {
        this.idProspect = idProspect;
    }

    public String getDateProspection() {
        return dateProspection;
    }

    public void setDateProspection(String dateProspection) {
        this.dateProspection = dateProspection;
    }

    public String getDescriptionProspection() {
        return descriptionProspection;
    }

    public void setDescriptionProspection(String descriptionProspection) {
        this.descriptionProspection = descriptionProspection;
    }

    @XmlTransient
    @JsonIgnore
    public List<ProspectFacture> getProspectFactureList() {
        return prospectFactureList;
    }

    public void setProspectFactureList(List<ProspectFacture> prospectFactureList) {
        this.prospectFactureList = prospectFactureList;
    }

    public Client getIdClient() {
        return idClient;
    }

    public void setIdClient(Client idClient) {
        this.idClient = idClient;
    }

    public Prospect getIdProspectClient() {
        return idProspectClient;
    }

    public void setIdProspectClient(Prospect idProspectClient) {
        this.idProspectClient = idProspectClient;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProspect != null ? idProspect.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prospection)) {
            return false;
        }
        Prospection other = (Prospection) object;
        if ((this.idProspect == null && other.idProspect != null) || (this.idProspect != null && !this.idProspect.equals(other.idProspect))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Prospection[ idProspect=" + idProspect + " ]";
    }
    
}
