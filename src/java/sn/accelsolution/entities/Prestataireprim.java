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
@Table(name = "prestataireprim")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prestataireprim.findAll", query = "SELECT p FROM Prestataireprim p")
    , @NamedQuery(name = "Prestataireprim.findByIdprestatairePrim", query = "SELECT p FROM Prestataireprim p WHERE p.idprestatairePrim = :idprestatairePrim")
    , @NamedQuery(name = "Prestataireprim.findByNinea", query = "SELECT p FROM Prestataireprim p WHERE p.ninea = :ninea")
    , @NamedQuery(name = "Prestataireprim.findByNomcomplet", query = "SELECT p FROM Prestataireprim p WHERE p.nomcomplet = :nomcomplet")
    , @NamedQuery(name = "Prestataireprim.findByCorps", query = "SELECT p FROM Prestataireprim p WHERE p.corps = :corps")
    , @NamedQuery(name = "Prestataireprim.findByTelephone", query = "SELECT p FROM Prestataireprim p WHERE p.telephone = :telephone")})
public class Prestataireprim implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprestatairePrim")
    private Integer idprestatairePrim;
    @Size(max = 255)
    @Column(name = "ninea")
    private String ninea;
    @Size(max = 250)
    @Column(name = "nomcomplet")
    private String nomcomplet;
    @Size(max = 250)
    @Column(name = "corps")
    private String corps;
    @Size(max = 50)
    @Column(name = "telephone")
    private String telephone;
    @OneToMany(mappedBy = "idprestatairePrim")
    private List<Prestataire> prestataireList;
    @OneToMany(mappedBy = "idprestatairePrim")
    private List<Detaillechantier> detaillechantierList;
    @OneToMany(mappedBy = "idprestatairePrim")
    private List<Management> managementList;

    public Prestataireprim() {
    }

    public Prestataireprim(Integer idprestatairePrim) {
        this.idprestatairePrim = idprestatairePrim;
    }

    public Integer getIdprestatairePrim() {
        return idprestatairePrim;
    }

    public void setIdprestatairePrim(Integer idprestatairePrim) {
        this.idprestatairePrim = idprestatairePrim;
    }

    public String getNinea() {
        return ninea;
    }

    public void setNinea(String ninea) {
        this.ninea = ninea;
    }

    public String getNomcomplet() {
        return nomcomplet;
    }

    public void setNomcomplet(String nomcomplet) {
        this.nomcomplet = nomcomplet;
    }

    public String getCorps() {
        return corps;
    }

    public void setCorps(String corps) {
        this.corps = corps;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @XmlTransient
    @JsonIgnore
    public List<Prestataire> getPrestataireList() {
        return prestataireList;
    }

    public void setPrestataireList(List<Prestataire> prestataireList) {
        this.prestataireList = prestataireList;
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
    public List<Management> getManagementList() {
        return managementList;
    }

    public void setManagementList(List<Management> managementList) {
        this.managementList = managementList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprestatairePrim != null ? idprestatairePrim.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prestataireprim)) {
            return false;
        }
        Prestataireprim other = (Prestataireprim) object;
        if ((this.idprestatairePrim == null && other.idprestatairePrim != null) || (this.idprestatairePrim != null && !this.idprestatairePrim.equals(other.idprestatairePrim))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Prestataireprim[ idprestatairePrim=" + idprestatairePrim + " ]";
    }
    
}
