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
@Table(name = "monnaie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Monnaie.findAll", query = "SELECT m FROM Monnaie m")
    , @NamedQuery(name = "Monnaie.findByIdMonnaie", query = "SELECT m FROM Monnaie m WHERE m.idMonnaie = :idMonnaie")
    , @NamedQuery(name = "Monnaie.findByDateMonnaie", query = "SELECT m FROM Monnaie m WHERE m.dateMonnaie = :dateMonnaie")
    , @NamedQuery(name = "Monnaie.findByMonnnaie", query = "SELECT m FROM Monnaie m WHERE m.monnnaie = :monnnaie")
    , @NamedQuery(name = "Monnaie.findByRendrepar", query = "SELECT m FROM Monnaie m WHERE m.rendrepar = :rendrepar")
    , @NamedQuery(name = "Monnaie.findByPiece", query = "SELECT m FROM Monnaie m WHERE m.piece = :piece")
    , @NamedQuery(name = "Monnaie.findByNumeropiece", query = "SELECT m FROM Monnaie m WHERE m.numeropiece = :numeropiece")
    , @NamedQuery(name = "Monnaie.findByMontantlettre", query = "SELECT m FROM Monnaie m WHERE m.montantlettre = :montantlettre")
    , @NamedQuery(name = "Monnaie.findByNature", query = "SELECT m FROM Monnaie m WHERE m.nature = :nature")
    , @NamedQuery(name = "Monnaie.findByObservation", query = "SELECT m FROM Monnaie m WHERE m.observation = :observation")
    , @NamedQuery(name = "Monnaie.findByTotalemonnaie", query = "SELECT m FROM Monnaie m WHERE m.totalemonnaie = :totalemonnaie")
    , @NamedQuery(name = "Monnaie.findByDevise", query = "SELECT m FROM Monnaie m WHERE m.devise = :devise")})
public class Monnaie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMonnaie")
    private Integer idMonnaie;
    @Size(max = 50)
    @Column(name = "dateMonnaie")
    private String dateMonnaie;
    @Size(max = 50)
    @Column(name = "monnnaie")
    private String monnnaie;
    @Size(max = 50)
    @Column(name = "rendrepar")
    private String rendrepar;
    @Size(max = 50)
    @Column(name = "piece")
    private String piece;
    @Size(max = 50)
    @Column(name = "numeropiece")
    private String numeropiece;
    @Size(max = 50)
    @Column(name = "montantlettre")
    private String montantlettre;
    @Size(max = 50)
    @Column(name = "nature")
    private String nature;
    @Size(max = 50)
    @Column(name = "observation")
    private String observation;
    @Size(max = 50)
    @Column(name = "totalemonnaie")
    private String totalemonnaie;
    @Size(max = 50)
    @Column(name = "devise")
    private String devise;
    @OneToMany(mappedBy = "idMonnaie")
    private List<Brouillard> brouillardList;

    public Monnaie() {
    }

    public Monnaie(Integer idMonnaie) {
        this.idMonnaie = idMonnaie;
    }

    public Integer getIdMonnaie() {
        return idMonnaie;
    }

    public void setIdMonnaie(Integer idMonnaie) {
        this.idMonnaie = idMonnaie;
    }

    public String getDateMonnaie() {
        return dateMonnaie;
    }

    public void setDateMonnaie(String dateMonnaie) {
        this.dateMonnaie = dateMonnaie;
    }

    public String getMonnnaie() {
        return monnnaie;
    }

    public void setMonnnaie(String monnnaie) {
        this.monnnaie = monnnaie;
    }

    public String getRendrepar() {
        return rendrepar;
    }

    public void setRendrepar(String rendrepar) {
        this.rendrepar = rendrepar;
    }

    public String getPiece() {
        return piece;
    }

    public void setPiece(String piece) {
        this.piece = piece;
    }

    public String getNumeropiece() {
        return numeropiece;
    }

    public void setNumeropiece(String numeropiece) {
        this.numeropiece = numeropiece;
    }

    public String getMontantlettre() {
        return montantlettre;
    }

    public void setMontantlettre(String montantlettre) {
        this.montantlettre = montantlettre;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getTotalemonnaie() {
        return totalemonnaie;
    }

    public void setTotalemonnaie(String totalemonnaie) {
        this.totalemonnaie = totalemonnaie;
    }

    public String getDevise() {
        return devise;
    }

    public void setDevise(String devise) {
        this.devise = devise;
    }

    @XmlTransient
    @JsonIgnore
    public List<Brouillard> getBrouillardList() {
        return brouillardList;
    }

    public void setBrouillardList(List<Brouillard> brouillardList) {
        this.brouillardList = brouillardList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMonnaie != null ? idMonnaie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Monnaie)) {
            return false;
        }
        Monnaie other = (Monnaie) object;
        if ((this.idMonnaie == null && other.idMonnaie != null) || (this.idMonnaie != null && !this.idMonnaie.equals(other.idMonnaie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Monnaie[ idMonnaie=" + idMonnaie + " ]";
    }
    
}
