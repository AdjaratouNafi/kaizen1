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
@Table(name = "facture")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Facture.findAll", query = "SELECT f FROM Facture f")
    , @NamedQuery(name = "Facture.findByIdFacture", query = "SELECT f FROM Facture f WHERE f.idFacture = :idFacture")
    , @NamedQuery(name = "Facture.findByNumeroFacture", query = "SELECT f FROM Facture f WHERE f.numeroFacture = :numeroFacture")
    , @NamedQuery(name = "Facture.findByDateechance", query = "SELECT f FROM Facture f WHERE f.dateechance = :dateechance")
    , @NamedQuery(name = "Facture.findByModepaiment", query = "SELECT f FROM Facture f WHERE f.modepaiment = :modepaiment")
    , @NamedQuery(name = "Facture.findByObservation", query = "SELECT f FROM Facture f WHERE f.observation = :observation")})
public class Facture implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idFacture")
    private Integer idFacture;
    @Size(max = 50)
    @Column(name = "numeroFacture")
    private String numeroFacture;
    @Size(max = 50)
    @Column(name = "dateechance")
    private String dateechance;
    @Size(max = 50)
    @Column(name = "modepaiment")
    private String modepaiment;
    @Size(max = 50)
    @Column(name = "observation")
    private String observation;
    @JoinColumn(name = "idClient", referencedColumnName = "idClient")
    @ManyToOne
    private Client idClient;
    @OneToMany(mappedBy = "idFacture")
    private List<ProspectFacture> prospectFactureList;

    public Facture() {
    }

    public Facture(Integer idFacture) {
        this.idFacture = idFacture;
    }

    public Integer getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(Integer idFacture) {
        this.idFacture = idFacture;
    }

    public String getNumeroFacture() {
        return numeroFacture;
    }

    public void setNumeroFacture(String numeroFacture) {
        this.numeroFacture = numeroFacture;
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

    public Client getIdClient() {
        return idClient;
    }

    public void setIdClient(Client idClient) {
        this.idClient = idClient;
    }

    @XmlTransient
    @JsonIgnore
    public List<ProspectFacture> getProspectFactureList() {
        return prospectFactureList;
    }

    public void setProspectFactureList(List<ProspectFacture> prospectFactureList) {
        this.prospectFactureList = prospectFactureList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFacture != null ? idFacture.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facture)) {
            return false;
        }
        Facture other = (Facture) object;
        if ((this.idFacture == null && other.idFacture != null) || (this.idFacture != null && !this.idFacture.equals(other.idFacture))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Facture[ idFacture=" + idFacture + " ]";
    }
    
}
