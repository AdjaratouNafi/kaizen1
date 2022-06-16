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
@Table(name = "lottechnique")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lottechnique.findAll", query = "SELECT l FROM Lottechnique l")
    , @NamedQuery(name = "Lottechnique.findByIdLottehcnique", query = "SELECT l FROM Lottechnique l WHERE l.idLottehcnique = :idLottehcnique")
    , @NamedQuery(name = "Lottechnique.findByLibellelottechnique", query = "SELECT l FROM Lottechnique l WHERE l.libellelottechnique = :libellelottechnique")})
public class Lottechnique implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idLottehcnique")
    private Integer idLottehcnique;
    @Size(max = 255)
    @Column(name = "libellelottechnique")
    private String libellelottechnique;
    @OneToMany(mappedBy = "idLottechnique")
    private List<Marchandise> marchandiseList;
    @OneToMany(mappedBy = "idLottehcnique")
    private List<Corpsetat> corpsetatList;

    public Lottechnique() {
    }

    public Lottechnique(Integer idLottehcnique) {
        this.idLottehcnique = idLottehcnique;
    }

    public Integer getIdLottehcnique() {
        return idLottehcnique;
    }

    public void setIdLottehcnique(Integer idLottehcnique) {
        this.idLottehcnique = idLottehcnique;
    }

    public String getLibellelottechnique() {
        return libellelottechnique;
    }

    public void setLibellelottechnique(String libellelottechnique) {
        this.libellelottechnique = libellelottechnique;
    }

    @XmlTransient
    @JsonIgnore
    public List<Marchandise> getMarchandiseList() {
        return marchandiseList;
    }

    public void setMarchandiseList(List<Marchandise> marchandiseList) {
        this.marchandiseList = marchandiseList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Corpsetat> getCorpsetatList() {
        return corpsetatList;
    }

    public void setCorpsetatList(List<Corpsetat> corpsetatList) {
        this.corpsetatList = corpsetatList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLottehcnique != null ? idLottehcnique.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lottechnique)) {
            return false;
        }
        Lottechnique other = (Lottechnique) object;
        if ((this.idLottehcnique == null && other.idLottehcnique != null) || (this.idLottehcnique != null && !this.idLottehcnique.equals(other.idLottehcnique))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Lottechnique[ idLottehcnique=" + idLottehcnique + " ]";
    }
    
}
