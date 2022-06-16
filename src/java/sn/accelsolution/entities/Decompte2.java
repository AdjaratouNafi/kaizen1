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
@Table(name = "decompte2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Decompte2.findAll", query = "SELECT d FROM Decompte2 d")
    , @NamedQuery(name = "Decompte2.findByIdDecompte2", query = "SELECT d FROM Decompte2 d WHERE d.idDecompte2 = :idDecompte2")
    , @NamedQuery(name = "Decompte2.findByDateDecompte2", query = "SELECT d FROM Decompte2 d WHERE d.dateDecompte2 = :dateDecompte2")
    , @NamedQuery(name = "Decompte2.findByNumeroDecompte2", query = "SELECT d FROM Decompte2 d WHERE d.numeroDecompte2 = :numeroDecompte2")})
public class Decompte2 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDecompte2")
    private Integer idDecompte2;
    @Size(max = 50)
    @Column(name = "dateDecompte2")
    private String dateDecompte2;
    @Size(max = 255)
    @Column(name = "numeroDecompte2")
    private String numeroDecompte2;
    @OneToMany(mappedBy = "idDecompte2")
    private List<Detailledecompte2> detailledecompte2List;
    @JoinColumn(name = "idMarche", referencedColumnName = "idMarche")
    @ManyToOne
    private Marche idMarche;

    public Decompte2() {
    }

    public Decompte2(Integer idDecompte2) {
        this.idDecompte2 = idDecompte2;
    }

    public Integer getIdDecompte2() {
        return idDecompte2;
    }

    public void setIdDecompte2(Integer idDecompte2) {
        this.idDecompte2 = idDecompte2;
    }

    public String getDateDecompte2() {
        return dateDecompte2;
    }

    public void setDateDecompte2(String dateDecompte2) {
        this.dateDecompte2 = dateDecompte2;
    }

    public String getNumeroDecompte2() {
        return numeroDecompte2;
    }

    public void setNumeroDecompte2(String numeroDecompte2) {
        this.numeroDecompte2 = numeroDecompte2;
    }

    @XmlTransient
    @JsonIgnore
    public List<Detailledecompte2> getDetailledecompte2List() {
        return detailledecompte2List;
    }

    public void setDetailledecompte2List(List<Detailledecompte2> detailledecompte2List) {
        this.detailledecompte2List = detailledecompte2List;
    }

    public Marche getIdMarche() {
        return idMarche;
    }

    public void setIdMarche(Marche idMarche) {
        this.idMarche = idMarche;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDecompte2 != null ? idDecompte2.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Decompte2)) {
            return false;
        }
        Decompte2 other = (Decompte2) object;
        if ((this.idDecompte2 == null && other.idDecompte2 != null) || (this.idDecompte2 != null && !this.idDecompte2.equals(other.idDecompte2))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Decompte2[ idDecompte2=" + idDecompte2 + " ]";
    }
    
}
