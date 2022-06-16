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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "contrat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contrat.findAll", query = "SELECT c FROM Contrat c")
    , @NamedQuery(name = "Contrat.findByIdContrat", query = "SELECT c FROM Contrat c WHERE c.idContrat = :idContrat")
    , @NamedQuery(name = "Contrat.findByNomSociete", query = "SELECT c FROM Contrat c WHERE c.nomSociete = :nomSociete")
    , @NamedQuery(name = "Contrat.findByAdresseSociete", query = "SELECT c FROM Contrat c WHERE c.adresseSociete = :adresseSociete")
    , @NamedQuery(name = "Contrat.findByNomRepresantantSociete", query = "SELECT c FROM Contrat c WHERE c.nomRepresantantSociete = :nomRepresantantSociete")
    , @NamedQuery(name = "Contrat.findByFonctionRepresentant", query = "SELECT c FROM Contrat c WHERE c.fonctionRepresentant = :fonctionRepresentant")
    , @NamedQuery(name = "Contrat.findByNationaliteRepresentant", query = "SELECT c FROM Contrat c WHERE c.nationaliteRepresentant = :nationaliteRepresentant")
    , @NamedQuery(name = "Contrat.findByDateEngagement", query = "SELECT c FROM Contrat c WHERE c.dateEngagement = :dateEngagement")
    , @NamedQuery(name = "Contrat.findByClassificationProfe", query = "SELECT c FROM Contrat c WHERE c.classificationProfe = :classificationProfe")
    , @NamedQuery(name = "Contrat.findByDureeTravail", query = "SELECT c FROM Contrat c WHERE c.dureeTravail = :dureeTravail")
    , @NamedQuery(name = "Contrat.findBySalairebase", query = "SELECT c FROM Contrat c WHERE c.salairebase = :salairebase")
    , @NamedQuery(name = "Contrat.findBySurSalaire", query = "SELECT c FROM Contrat c WHERE c.surSalaire = :surSalaire")
    , @NamedQuery(name = "Contrat.findBySalaireBrutFiscal", query = "SELECT c FROM Contrat c WHERE c.salaireBrutFiscal = :salaireBrutFiscal")
    , @NamedQuery(name = "Contrat.findByLocalTypeContrat", query = "SELECT c FROM Contrat c WHERE c.localTypeContrat = :localTypeContrat")
    , @NamedQuery(name = "Contrat.findByDiffTypeContrat", query = "SELECT c FROM Contrat c WHERE c.diffTypeContrat = :diffTypeContrat")})
public class Contrat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idContrat")
    private Integer idContrat;
    @Size(max = 50)
    @Column(name = "nomSociete")
    private String nomSociete;
    @Size(max = 50)
    @Column(name = "adresseSociete")
    private String adresseSociete;
    @Size(max = 50)
    @Column(name = "nomRepresantantSociete")
    private String nomRepresantantSociete;
    @Size(max = 50)
    @Column(name = "fonctionRepresentant")
    private String fonctionRepresentant;
    @Size(max = 50)
    @Column(name = "nationaliteRepresentant")
    private String nationaliteRepresentant;
    @Size(max = 50)
    @Column(name = "dateEngagement")
    private String dateEngagement;
    @Size(max = 50)
    @Column(name = "classificationProfe")
    private String classificationProfe;
    @Size(max = 50)
    @Column(name = "dureeTravail")
    private String dureeTravail;
    @Size(max = 50)
    @Column(name = "salairebase")
    private String salairebase;
    @Size(max = 50)
    @Column(name = "surSalaire")
    private String surSalaire;
    @Size(max = 50)
    @Column(name = "salaireBrutFiscal")
    private String salaireBrutFiscal;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "article")
    private String article;
    @Size(max = 100)
    @Column(name = "localTypeContrat")
    private String localTypeContrat;
    @Size(max = 100)
    @Column(name = "diffTypeContrat")
    private String diffTypeContrat;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "article1")
    private String article1;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "article2")
    private String article2;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "article3")
    private String article3;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "article4")
    private String article4;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "article5")
    private String article5;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "article6")
    private String article6;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "article8")
    private String article8;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "article9")
    private String article9;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "article10")
    private String article10;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "article11")
    private String article11;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "article12")
    private String article12;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "article13")
    private String article13;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "article14")
    private String article14;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "article15")
    private String article15;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "article16")
    private String article16;
    @JoinColumn(name = "idTypeContrat", referencedColumnName = "idTypeContrat")
    @ManyToOne
    private TypeContrat idTypeContrat;
    @JoinColumn(name = "idUtilisateur", referencedColumnName = "idUtilisateur")
    @ManyToOne
    private Utilisateur idUtilisateur;

    public Contrat() {
    }

    public Contrat(Integer idContrat) {
        this.idContrat = idContrat;
    }

    public Integer getIdContrat() {
        return idContrat;
    }

    public void setIdContrat(Integer idContrat) {
        this.idContrat = idContrat;
    }

    public String getNomSociete() {
        return nomSociete;
    }

    public void setNomSociete(String nomSociete) {
        this.nomSociete = nomSociete;
    }

    public String getAdresseSociete() {
        return adresseSociete;
    }

    public void setAdresseSociete(String adresseSociete) {
        this.adresseSociete = adresseSociete;
    }

    public String getNomRepresantantSociete() {
        return nomRepresantantSociete;
    }

    public void setNomRepresantantSociete(String nomRepresantantSociete) {
        this.nomRepresantantSociete = nomRepresantantSociete;
    }

    public String getFonctionRepresentant() {
        return fonctionRepresentant;
    }

    public void setFonctionRepresentant(String fonctionRepresentant) {
        this.fonctionRepresentant = fonctionRepresentant;
    }

    public String getNationaliteRepresentant() {
        return nationaliteRepresentant;
    }

    public void setNationaliteRepresentant(String nationaliteRepresentant) {
        this.nationaliteRepresentant = nationaliteRepresentant;
    }

    public String getDateEngagement() {
        return dateEngagement;
    }

    public void setDateEngagement(String dateEngagement) {
        this.dateEngagement = dateEngagement;
    }

    public String getClassificationProfe() {
        return classificationProfe;
    }

    public void setClassificationProfe(String classificationProfe) {
        this.classificationProfe = classificationProfe;
    }

    public String getDureeTravail() {
        return dureeTravail;
    }

    public void setDureeTravail(String dureeTravail) {
        this.dureeTravail = dureeTravail;
    }

    public String getSalairebase() {
        return salairebase;
    }

    public void setSalairebase(String salairebase) {
        this.salairebase = salairebase;
    }

    public String getSurSalaire() {
        return surSalaire;
    }

    public void setSurSalaire(String surSalaire) {
        this.surSalaire = surSalaire;
    }

    public String getSalaireBrutFiscal() {
        return salaireBrutFiscal;
    }

    public void setSalaireBrutFiscal(String salaireBrutFiscal) {
        this.salaireBrutFiscal = salaireBrutFiscal;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getLocalTypeContrat() {
        return localTypeContrat;
    }

    public void setLocalTypeContrat(String localTypeContrat) {
        this.localTypeContrat = localTypeContrat;
    }

    public String getDiffTypeContrat() {
        return diffTypeContrat;
    }

    public void setDiffTypeContrat(String diffTypeContrat) {
        this.diffTypeContrat = diffTypeContrat;
    }

    public String getArticle1() {
        return article1;
    }

    public void setArticle1(String article1) {
        this.article1 = article1;
    }

    public String getArticle2() {
        return article2;
    }

    public void setArticle2(String article2) {
        this.article2 = article2;
    }

    public String getArticle3() {
        return article3;
    }

    public void setArticle3(String article3) {
        this.article3 = article3;
    }

    public String getArticle4() {
        return article4;
    }

    public void setArticle4(String article4) {
        this.article4 = article4;
    }

    public String getArticle5() {
        return article5;
    }

    public void setArticle5(String article5) {
        this.article5 = article5;
    }

    public String getArticle6() {
        return article6;
    }

    public void setArticle6(String article6) {
        this.article6 = article6;
    }

    public String getArticle8() {
        return article8;
    }

    public void setArticle8(String article8) {
        this.article8 = article8;
    }

    public String getArticle9() {
        return article9;
    }

    public void setArticle9(String article9) {
        this.article9 = article9;
    }

    public String getArticle10() {
        return article10;
    }

    public void setArticle10(String article10) {
        this.article10 = article10;
    }

    public String getArticle11() {
        return article11;
    }

    public void setArticle11(String article11) {
        this.article11 = article11;
    }

    public String getArticle12() {
        return article12;
    }

    public void setArticle12(String article12) {
        this.article12 = article12;
    }

    public String getArticle13() {
        return article13;
    }

    public void setArticle13(String article13) {
        this.article13 = article13;
    }

    public String getArticle14() {
        return article14;
    }

    public void setArticle14(String article14) {
        this.article14 = article14;
    }

    public String getArticle15() {
        return article15;
    }

    public void setArticle15(String article15) {
        this.article15 = article15;
    }

    public String getArticle16() {
        return article16;
    }

    public void setArticle16(String article16) {
        this.article16 = article16;
    }

    public TypeContrat getIdTypeContrat() {
        return idTypeContrat;
    }

    public void setIdTypeContrat(TypeContrat idTypeContrat) {
        this.idTypeContrat = idTypeContrat;
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
        hash += (idContrat != null ? idContrat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contrat)) {
            return false;
        }
        Contrat other = (Contrat) object;
        if ((this.idContrat == null && other.idContrat != null) || (this.idContrat != null && !this.idContrat.equals(other.idContrat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Contrat[ idContrat=" + idContrat + " ]";
    }
    
}
