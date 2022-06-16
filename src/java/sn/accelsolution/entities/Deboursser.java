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
@Table(name = "deboursser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Deboursser.findAll", query = "SELECT d FROM Deboursser d")
    , @NamedQuery(name = "Deboursser.findByIdDeboursser", query = "SELECT d FROM Deboursser d WHERE d.idDeboursser = :idDeboursser")
    , @NamedQuery(name = "Deboursser.findByQuantite", query = "SELECT d FROM Deboursser d WHERE d.quantite = :quantite")
    , @NamedQuery(name = "Deboursser.findByUnite", query = "SELECT d FROM Deboursser d WHERE d.unite = :unite")})
public class Deboursser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDeboursser")
    private Integer idDeboursser;
    @Size(max = 255)
    @Column(name = "quantite")
    private String quantite;
    @Size(max = 255)
    @Column(name = "unite")
    private String unite;
    @OneToMany(mappedBy = "idDeboursse")
    private List<Mouvementdebourse> mouvementdebourseList;
    @JoinColumn(name = "idNivodeboursser", referencedColumnName = "idNivodeboursser")
    @ManyToOne
    private Nivodeboursser idNivodeboursser;
    @JoinColumn(name = "idMateriel", referencedColumnName = "idMarchandise")
    @ManyToOne
    private Marchandise idMateriel;

    public Deboursser() {
    }

    public Deboursser(Integer idDeboursser) {
        this.idDeboursser = idDeboursser;
    }

    public Integer getIdDeboursser() {
        return idDeboursser;
    }

    public void setIdDeboursser(Integer idDeboursser) {
        this.idDeboursser = idDeboursser;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    @XmlTransient
    @JsonIgnore
    public List<Mouvementdebourse> getMouvementdebourseList() {
        return mouvementdebourseList;
    }

    public void setMouvementdebourseList(List<Mouvementdebourse> mouvementdebourseList) {
        this.mouvementdebourseList = mouvementdebourseList;
    }

    public Nivodeboursser getIdNivodeboursser() {
        return idNivodeboursser;
    }

    public void setIdNivodeboursser(Nivodeboursser idNivodeboursser) {
        this.idNivodeboursser = idNivodeboursser;
    }

    public Marchandise getIdMateriel() {
        return idMateriel;
    }

    public void setIdMateriel(Marchandise idMateriel) {
        this.idMateriel = idMateriel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDeboursser != null ? idDeboursser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Deboursser)) {
            return false;
        }
        Deboursser other = (Deboursser) object;
        if ((this.idDeboursser == null && other.idDeboursser != null) || (this.idDeboursser != null && !this.idDeboursser.equals(other.idDeboursser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Deboursser[ idDeboursser=" + idDeboursser + " ]";
    }
    
}
