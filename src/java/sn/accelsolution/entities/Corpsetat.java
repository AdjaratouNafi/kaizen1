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
@Table(name = "corpsetat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Corpsetat.findAll", query = "SELECT c FROM Corpsetat c")
    , @NamedQuery(name = "Corpsetat.findByIdCorpsetat", query = "SELECT c FROM Corpsetat c WHERE c.idCorpsetat = :idCorpsetat")
    , @NamedQuery(name = "Corpsetat.findByLibellecoprsetat", query = "SELECT c FROM Corpsetat c WHERE c.libellecoprsetat = :libellecoprsetat")})
public class Corpsetat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCorpsetat")
    private Integer idCorpsetat;
    @Size(max = 255)
    @Column(name = "libellecoprsetat")
    private String libellecoprsetat;
    @OneToMany(mappedBy = "idCorpsetat")
    private List<Marchandise> marchandiseList;
    @JoinColumn(name = "idLottehcnique", referencedColumnName = "idLottehcnique")
    @ManyToOne
    private Lottechnique idLottehcnique;

    public Corpsetat() {
    }

    public Corpsetat(Integer idCorpsetat) {
        this.idCorpsetat = idCorpsetat;
    }

    public Integer getIdCorpsetat() {
        return idCorpsetat;
    }

    public void setIdCorpsetat(Integer idCorpsetat) {
        this.idCorpsetat = idCorpsetat;
    }

    public String getLibellecoprsetat() {
        return libellecoprsetat;
    }

    public void setLibellecoprsetat(String libellecoprsetat) {
        this.libellecoprsetat = libellecoprsetat;
    }

    @XmlTransient
    @JsonIgnore
    public List<Marchandise> getMarchandiseList() {
        return marchandiseList;
    }

    public void setMarchandiseList(List<Marchandise> marchandiseList) {
        this.marchandiseList = marchandiseList;
    }

    public Lottechnique getIdLottehcnique() {
        return idLottehcnique;
    }

    public void setIdLottehcnique(Lottechnique idLottehcnique) {
        this.idLottehcnique = idLottehcnique;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCorpsetat != null ? idCorpsetat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Corpsetat)) {
            return false;
        }
        Corpsetat other = (Corpsetat) object;
        if ((this.idCorpsetat == null && other.idCorpsetat != null) || (this.idCorpsetat != null && !this.idCorpsetat.equals(other.idCorpsetat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Corpsetat[ idCorpsetat=" + idCorpsetat + " ]";
    }
    
}
