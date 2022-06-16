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
@Table(name = "appel_offre")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AppelOffre.findAll", query = "SELECT a FROM AppelOffre a")
    , @NamedQuery(name = "AppelOffre.findByIdAppel", query = "SELECT a FROM AppelOffre a WHERE a.idAppel = :idAppel")
    , @NamedQuery(name = "AppelOffre.findByDateAppel", query = "SELECT a FROM AppelOffre a WHERE a.dateAppel = :dateAppel")
    , @NamedQuery(name = "AppelOffre.findByNumAppel", query = "SELECT a FROM AppelOffre a WHERE a.numAppel = :numAppel")
    , @NamedQuery(name = "AppelOffre.findByTypeAppel", query = "SELECT a FROM AppelOffre a WHERE a.typeAppel = :typeAppel")
    , @NamedQuery(name = "AppelOffre.findByDateSoumission", query = "SELECT a FROM AppelOffre a WHERE a.dateSoumission = :dateSoumission")
    , @NamedQuery(name = "AppelOffre.findByEtatApel", query = "SELECT a FROM AppelOffre a WHERE a.etatApel = :etatApel")
    , @NamedQuery(name = "AppelOffre.findByResultat", query = "SELECT a FROM AppelOffre a WHERE a.resultat = :resultat")
    , @NamedQuery(name = "AppelOffre.findByMotif", query = "SELECT a FROM AppelOffre a WHERE a.motif = :motif")})
public class AppelOffre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAppel")
    private Integer idAppel;
    @Size(max = 50)
    @Column(name = "dateAppel")
    private String dateAppel;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "description")
    private String description;
    @Size(max = 50)
    @Column(name = "numAppel")
    private String numAppel;
    @Size(max = 50)
    @Column(name = "typeAppel")
    private String typeAppel;
    @Size(max = 50)
    @Column(name = "dateSoumission")
    private String dateSoumission;
    @Size(max = 50)
    @Column(name = "etatApel")
    private String etatApel;
    @Size(max = 50)
    @Column(name = "resultat")
    private String resultat;
    @Size(max = 50)
    @Column(name = "motif")
    private String motif;
    @OneToMany(mappedBy = "idAppel")
    private List<Detailleappeloffre> detailleappeloffreList;
    @JoinColumn(name = "idUtilisateur", referencedColumnName = "idUtilisateur")
    @ManyToOne
    private Utilisateur idUtilisateur;

    public AppelOffre() {
    }

    public AppelOffre(Integer idAppel) {
        this.idAppel = idAppel;
    }

    public Integer getIdAppel() {
        return idAppel;
    }

    public void setIdAppel(Integer idAppel) {
        this.idAppel = idAppel;
    }

    public String getDateAppel() {
        return dateAppel;
    }

    public void setDateAppel(String dateAppel) {
        this.dateAppel = dateAppel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNumAppel() {
        return numAppel;
    }

    public void setNumAppel(String numAppel) {
        this.numAppel = numAppel;
    }

    public String getTypeAppel() {
        return typeAppel;
    }

    public void setTypeAppel(String typeAppel) {
        this.typeAppel = typeAppel;
    }

    public String getDateSoumission() {
        return dateSoumission;
    }

    public void setDateSoumission(String dateSoumission) {
        this.dateSoumission = dateSoumission;
    }

    public String getEtatApel() {
        return etatApel;
    }

    public void setEtatApel(String etatApel) {
        this.etatApel = etatApel;
    }

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    @XmlTransient
    @JsonIgnore
    public List<Detailleappeloffre> getDetailleappeloffreList() {
        return detailleappeloffreList;
    }

    public void setDetailleappeloffreList(List<Detailleappeloffre> detailleappeloffreList) {
        this.detailleappeloffreList = detailleappeloffreList;
    }

    public Utilisateur getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Utilisateur idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAppel != null ? idAppel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AppelOffre)) {
            return false;
        }
        AppelOffre other = (AppelOffre) object;
        if ((this.idAppel == null && other.idAppel != null) || (this.idAppel != null && !this.idAppel.equals(other.idAppel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.AppelOffre[ idAppel=" + idAppel + " ]";
    }
    
}
