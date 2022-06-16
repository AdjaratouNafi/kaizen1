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
@Table(name = "type_operation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeOperation.findAll", query = "SELECT t FROM TypeOperation t")
    , @NamedQuery(name = "TypeOperation.findByIdTypeOperation", query = "SELECT t FROM TypeOperation t WHERE t.idTypeOperation = :idTypeOperation")
    , @NamedQuery(name = "TypeOperation.findByLibelleTypeOperation", query = "SELECT t FROM TypeOperation t WHERE t.libelleTypeOperation = :libelleTypeOperation")})
public class TypeOperation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTypeOperation")
    private Integer idTypeOperation;
    @Size(max = 50)
    @Column(name = "libelleTypeOperation")
    private String libelleTypeOperation;

    public TypeOperation() {
    }

    public TypeOperation(Integer idTypeOperation) {
        this.idTypeOperation = idTypeOperation;
    }

    public Integer getIdTypeOperation() {
        return idTypeOperation;
    }

    public void setIdTypeOperation(Integer idTypeOperation) {
        this.idTypeOperation = idTypeOperation;
    }

    public String getLibelleTypeOperation() {
        return libelleTypeOperation;
    }

    public void setLibelleTypeOperation(String libelleTypeOperation) {
        this.libelleTypeOperation = libelleTypeOperation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTypeOperation != null ? idTypeOperation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeOperation)) {
            return false;
        }
        TypeOperation other = (TypeOperation) object;
        if ((this.idTypeOperation == null && other.idTypeOperation != null) || (this.idTypeOperation != null && !this.idTypeOperation.equals(other.idTypeOperation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.TypeOperation[ idTypeOperation=" + idTypeOperation + " ]";
    }
    
}
