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
@Table(name = "prestataire")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prestataire.findAll", query = "SELECT p FROM Prestataire p")
    , @NamedQuery(name = "Prestataire.findByIdPrestataire", query = "SELECT p FROM Prestataire p WHERE p.idPrestataire = :idPrestataire")
    , @NamedQuery(name = "Prestataire.findByNinea", query = "SELECT p FROM Prestataire p WHERE p.ninea = :ninea")
    , @NamedQuery(name = "Prestataire.findByNomcomplet", query = "SELECT p FROM Prestataire p WHERE p.nomcomplet = :nomcomplet")
    , @NamedQuery(name = "Prestataire.findByCorps", query = "SELECT p FROM Prestataire p WHERE p.corps = :corps")
    , @NamedQuery(name = "Prestataire.findByTelephone", query = "SELECT p FROM Prestataire p WHERE p.telephone = :telephone")
    , @NamedQuery(name = "Prestataire.findByAccord", query = "SELECT p FROM Prestataire p WHERE p.accord = :accord")
    , @NamedQuery(name = "Prestataire.findByNature", query = "SELECT p FROM Prestataire p WHERE p.nature = :nature")
    , @NamedQuery(name = "Prestataire.findByAccompte", query = "SELECT p FROM Prestataire p WHERE p.accompte = :accompte")
    , @NamedQuery(name = "Prestataire.findByReliquant", query = "SELECT p FROM Prestataire p WHERE p.reliquant = :reliquant")
    , @NamedQuery(name = "Prestataire.findByVoyant", query = "SELECT p FROM Prestataire p WHERE p.voyant = :voyant")
    , @NamedQuery(name = "Prestataire.findByEchenance", query = "SELECT p FROM Prestataire p WHERE p.echenance = :echenance")})
public class Prestataire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPrestataire")
    private Integer idPrestataire;
    @Size(max = 255)
    @Column(name = "ninea")
    private String ninea;
    @Size(max = 255)
    @Column(name = "nomcomplet")
    private String nomcomplet;
    @Size(max = 50)
    @Column(name = "corps")
    private String corps;
    @Size(max = 50)
    @Column(name = "telephone")
    private String telephone;
    @Size(max = 50)
    @Column(name = "accord")
    private String accord;
    @Size(max = 255)
    @Column(name = "nature")
    private String nature;
    @Size(max = 50)
    @Column(name = "accompte")
    private String accompte;
    @Size(max = 50)
    @Column(name = "reliquant")
    private String reliquant;
    @Size(max = 50)
    @Column(name = "voyant")
    private String voyant;
    @Size(max = 50)
    @Column(name = "echenance")
    private String echenance;
    @JoinColumn(name = "idChantier", referencedColumnName = "idChantier")
    @ManyToOne
    private Chantier idChantier;
    @JoinColumn(name = "idprestatairePrim", referencedColumnName = "idprestatairePrim")
    @ManyToOne
    private Prestataireprim idprestatairePrim;
    @OneToMany(mappedBy = "idPrestataire")
    private List<Acompte> acompteList;
    @OneToMany(mappedBy = "idPrestataire")
    private List<Chantier> chantierList;
    @OneToMany(mappedBy = "idPrestataire")
    private List<Detaillechantier> detaillechantierList;
    @OneToMany(mappedBy = "idPrestataire")
    private List<Management> managementList;

    public Prestataire() {
    }

    public Prestataire(Integer idPrestataire) {
        this.idPrestataire = idPrestataire;
    }

    public Integer getIdPrestataire() {
        return idPrestataire;
    }

    public void setIdPrestataire(Integer idPrestataire) {
        this.idPrestataire = idPrestataire;
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

    public String getAccord() {
        return accord;
    }

    public void setAccord(String accord) {
        this.accord = accord;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getAccompte() {
        return accompte;
    }

    public void setAccompte(String accompte) {
        this.accompte = accompte;
    }

    public String getReliquant() {
        return reliquant;
    }

    public void setReliquant(String reliquant) {
        this.reliquant = reliquant;
    }

    public String getVoyant() {
        return voyant;
    }

    public void setVoyant(String voyant) {
        this.voyant = voyant;
    }

    public String getEchenance() {
        return echenance;
    }

    public void setEchenance(String echenance) {
        this.echenance = echenance;
    }

    public Chantier getIdChantier() {
        return idChantier;
    }

    public void setIdChantier(Chantier idChantier) {
        this.idChantier = idChantier;
    }

    public Prestataireprim getIdprestatairePrim() {
        return idprestatairePrim;
    }

    public void setIdprestatairePrim(Prestataireprim idprestatairePrim) {
        this.idprestatairePrim = idprestatairePrim;
    }

    @XmlTransient
    @JsonIgnore
    public List<Acompte> getAcompteList() {
        return acompteList;
    }

    public void setAcompteList(List<Acompte> acompteList) {
        this.acompteList = acompteList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Chantier> getChantierList() {
        return chantierList;
    }

    public void setChantierList(List<Chantier> chantierList) {
        this.chantierList = chantierList;
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
        hash += (idPrestataire != null ? idPrestataire.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prestataire)) {
            return false;
        }
        Prestataire other = (Prestataire) object;
        if ((this.idPrestataire == null && other.idPrestataire != null) || (this.idPrestataire != null && !this.idPrestataire.equals(other.idPrestataire))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Prestataire[ idPrestataire=" + idPrestataire + " ]";
    }
    
}
