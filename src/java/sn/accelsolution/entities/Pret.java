/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "pret")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pret.findAll", query = "SELECT p FROM Pret p")
    , @NamedQuery(name = "Pret.findByIdPret", query = "SELECT p FROM Pret p WHERE p.idPret = :idPret")
    , @NamedQuery(name = "Pret.findByDatePret", query = "SELECT p FROM Pret p WHERE p.datePret = :datePret")
    , @NamedQuery(name = "Pret.findByMontantPret", query = "SELECT p FROM Pret p WHERE p.montantPret = :montantPret")
    , @NamedQuery(name = "Pret.findByMontanAp", query = "SELECT p FROM Pret p WHERE p.montanAp = :montanAp")
    , @NamedQuery(name = "Pret.findByMontantR", query = "SELECT p FROM Pret p WHERE p.montantR = :montantR")
    , @NamedQuery(name = "Pret.findBySuivi", query = "SELECT p FROM Pret p WHERE p.suivi = :suivi")
    , @NamedQuery(name = "Pret.findByPerioderestante", query = "SELECT p FROM Pret p WHERE p.perioderestante = :perioderestante")
    , @NamedQuery(name = "Pret.findByEtatPret", query = "SELECT p FROM Pret p WHERE p.etatPret = :etatPret")
    , @NamedQuery(name = "Pret.findByCloture", query = "SELECT p FROM Pret p WHERE p.cloture = :cloture")
    , @NamedQuery(name = "Pret.findByPremierpayement", query = "SELECT p FROM Pret p WHERE p.premierpayement = :premierpayement")})
public class Pret implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPret")
    private Integer idPret;
    @Size(max = 50)
    @Column(name = "datePret")
    private String datePret;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "motifPret")
    private String motifPret;
    @Size(max = 50)
    @Column(name = "montantPret")
    private String montantPret;
    @Size(max = 50)
    @Column(name = "montanAp")
    private String montanAp;
    @Size(max = 50)
    @Column(name = "montantR")
    private String montantR;
    @Column(name = "suivi")
    private Integer suivi;
    @Column(name = "perioderestante")
    private Integer perioderestante;
    @Size(max = 50)
    @Column(name = "etatPret")
    private String etatPret;
    @Size(max = 50)
    @Column(name = "cloture")
    private String cloture;
    @Size(max = 50)
    @Column(name = "premierpayement")
    private String premierpayement;
    @JoinColumn(name = "idUtilisateur", referencedColumnName = "idUtilisateur")
    @ManyToOne
    private Utilisateur idUtilisateur;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPret")
    private List<Demande> demandeList;
    @OneToMany(mappedBy = "idPret")
    private List<Rembourssement> rembourssementList;

    public Pret() {
    }

    public Pret(Integer idPret) {
        this.idPret = idPret;
    }

    public Integer getIdPret() {
        return idPret;
    }

    public void setIdPret(Integer idPret) {
        this.idPret = idPret;
    }

    public String getDatePret() {
        return datePret;
    }

    public void setDatePret(String datePret) {
        this.datePret = datePret;
    }

    public String getMotifPret() {
        return motifPret;
    }

    public void setMotifPret(String motifPret) {
        this.motifPret = motifPret;
    }

    public String getMontantPret() {
        return montantPret;
    }

    public void setMontantPret(String montantPret) {
        this.montantPret = montantPret;
    }

    public String getMontanAp() {
        return montanAp;
    }

    public void setMontanAp(String montanAp) {
        this.montanAp = montanAp;
    }

    public String getMontantR() {
        return montantR;
    }

    public void setMontantR(String montantR) {
        this.montantR = montantR;
    }

    public Integer getSuivi() {
        return suivi;
    }

    public void setSuivi(Integer suivi) {
        this.suivi = suivi;
    }

    public Integer getPerioderestante() {
        return perioderestante;
    }

    public void setPerioderestante(Integer perioderestante) {
        this.perioderestante = perioderestante;
    }

    public String getEtatPret() {
        return etatPret;
    }

    public void setEtatPret(String etatPret) {
        this.etatPret = etatPret;
    }

    public String getCloture() {
        return cloture;
    }

    public void setCloture(String cloture) {
        this.cloture = cloture;
    }

    public String getPremierpayement() {
        return premierpayement;
    }

    public void setPremierpayement(String premierpayement) {
        this.premierpayement = premierpayement;
    }

    public Utilisateur getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Utilisateur idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    @XmlTransient
    @JsonIgnore
    public List<Demande> getDemandeList() {
        return demandeList;
    }

    public void setDemandeList(List<Demande> demandeList) {
        this.demandeList = demandeList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Rembourssement> getRembourssementList() {
        return rembourssementList;
    }

    public void setRembourssementList(List<Rembourssement> rembourssementList) {
        this.rembourssementList = rembourssementList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPret != null ? idPret.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pret)) {
            return false;
        }
        Pret other = (Pret) object;
        if ((this.idPret == null && other.idPret != null) || (this.idPret != null && !this.idPret.equals(other.idPret))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Pret[ idPret=" + idPret + " ]";
    }
    
}
