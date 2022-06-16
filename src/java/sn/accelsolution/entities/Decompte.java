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
@Table(name = "decompte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Decompte.findAll", query = "SELECT d FROM Decompte d")
    , @NamedQuery(name = "Decompte.findByIdDecompte", query = "SELECT d FROM Decompte d WHERE d.idDecompte = :idDecompte")
    , @NamedQuery(name = "Decompte.findByDateDecompte", query = "SELECT d FROM Decompte d WHERE d.dateDecompte = :dateDecompte")
    , @NamedQuery(name = "Decompte.findByMontantmarche", query = "SELECT d FROM Decompte d WHERE d.montantmarche = :montantmarche")
    , @NamedQuery(name = "Decompte.findByMontantExecution", query = "SELECT d FROM Decompte d WHERE d.montantExecution = :montantExecution")
    , @NamedQuery(name = "Decompte.findByMontantdemarage", query = "SELECT d FROM Decompte d WHERE d.montantdemarage = :montantdemarage")
    , @NamedQuery(name = "Decompte.findByAvancesurappro", query = "SELECT d FROM Decompte d WHERE d.avancesurappro = :avancesurappro")
    , @NamedQuery(name = "Decompte.findByDepot", query = "SELECT d FROM Decompte d WHERE d.depot = :depot")
    , @NamedQuery(name = "Decompte.findByPeriodeConcerne", query = "SELECT d FROM Decompte d WHERE d.periodeConcerne = :periodeConcerne")
    , @NamedQuery(name = "Decompte.findByPartEnCfa", query = "SELECT d FROM Decompte d WHERE d.partEnCfa = :partEnCfa")
    , @NamedQuery(name = "Decompte.findByPartEnFf", query = "SELECT d FROM Decompte d WHERE d.partEnFf = :partEnFf")
    , @NamedQuery(name = "Decompte.findByMontantDecompetCfa", query = "SELECT d FROM Decompte d WHERE d.montantDecompetCfa = :montantDecompetCfa")
    , @NamedQuery(name = "Decompte.findByRemiseAvantDemarage", query = "SELECT d FROM Decompte d WHERE d.remiseAvantDemarage = :remiseAvantDemarage")
    , @NamedQuery(name = "Decompte.findByPayementDirect", query = "SELECT d FROM Decompte d WHERE d.payementDirect = :payementDirect")
    , @NamedQuery(name = "Decompte.findByRetenuGaranti", query = "SELECT d FROM Decompte d WHERE d.retenuGaranti = :retenuGaranti")
    , @NamedQuery(name = "Decompte.findByNetApaye", query = "SELECT d FROM Decompte d WHERE d.netApaye = :netApaye")
    , @NamedQuery(name = "Decompte.findByNetTotalAPaye", query = "SELECT d FROM Decompte d WHERE d.netTotalAPaye = :netTotalAPaye")
    , @NamedQuery(name = "Decompte.findByMontantNetAPaye", query = "SELECT d FROM Decompte d WHERE d.montantNetAPaye = :montantNetAPaye")})
public class Decompte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDecompte")
    private Integer idDecompte;
    @Size(max = 50)
    @Column(name = "dateDecompte")
    private String dateDecompte;
    @Size(max = 50)
    @Column(name = "montantmarche")
    private String montantmarche;
    @Size(max = 50)
    @Column(name = "montantExecution")
    private String montantExecution;
    @Size(max = 50)
    @Column(name = "montantdemarage")
    private String montantdemarage;
    @Size(max = 50)
    @Column(name = "avancesurappro")
    private String avancesurappro;
    @Size(max = 50)
    @Column(name = "depot")
    private String depot;
    @Size(max = 50)
    @Column(name = "periodeConcerne")
    private String periodeConcerne;
    @Size(max = 50)
    @Column(name = "partEnCfa")
    private String partEnCfa;
    @Size(max = 50)
    @Column(name = "partEnFf")
    private String partEnFf;
    @Size(max = 50)
    @Column(name = "montantDecompetCfa")
    private String montantDecompetCfa;
    @Size(max = 50)
    @Column(name = "remiseAvantDemarage")
    private String remiseAvantDemarage;
    @Size(max = 50)
    @Column(name = "payementDirect")
    private String payementDirect;
    @Size(max = 50)
    @Column(name = "retenuGaranti")
    private String retenuGaranti;
    @Size(max = 50)
    @Column(name = "netApaye")
    private String netApaye;
    @Size(max = 50)
    @Column(name = "netTotalAPaye")
    private String netTotalAPaye;
    @Size(max = 50)
    @Column(name = "montantNetAPaye")
    private String montantNetAPaye;
    @JoinColumn(name = "idBailleur", referencedColumnName = "idBailleur")
    @ManyToOne
    private Bailleur idBailleur;
    @JoinColumn(name = "idClient", referencedColumnName = "idClient")
    @ManyToOne
    private Client idClient;
    @JoinColumn(name = "idMarche", referencedColumnName = "idMarche")
    @ManyToOne
    private Marche idMarche;

    public Decompte() {
    }

    public Decompte(Integer idDecompte) {
        this.idDecompte = idDecompte;
    }

    public Integer getIdDecompte() {
        return idDecompte;
    }

    public void setIdDecompte(Integer idDecompte) {
        this.idDecompte = idDecompte;
    }

    public String getDateDecompte() {
        return dateDecompte;
    }

    public void setDateDecompte(String dateDecompte) {
        this.dateDecompte = dateDecompte;
    }

    public String getMontantmarche() {
        return montantmarche;
    }

    public void setMontantmarche(String montantmarche) {
        this.montantmarche = montantmarche;
    }

    public String getMontantExecution() {
        return montantExecution;
    }

    public void setMontantExecution(String montantExecution) {
        this.montantExecution = montantExecution;
    }

    public String getMontantdemarage() {
        return montantdemarage;
    }

    public void setMontantdemarage(String montantdemarage) {
        this.montantdemarage = montantdemarage;
    }

    public String getAvancesurappro() {
        return avancesurappro;
    }

    public void setAvancesurappro(String avancesurappro) {
        this.avancesurappro = avancesurappro;
    }

    public String getDepot() {
        return depot;
    }

    public void setDepot(String depot) {
        this.depot = depot;
    }

    public String getPeriodeConcerne() {
        return periodeConcerne;
    }

    public void setPeriodeConcerne(String periodeConcerne) {
        this.periodeConcerne = periodeConcerne;
    }

    public String getPartEnCfa() {
        return partEnCfa;
    }

    public void setPartEnCfa(String partEnCfa) {
        this.partEnCfa = partEnCfa;
    }

    public String getPartEnFf() {
        return partEnFf;
    }

    public void setPartEnFf(String partEnFf) {
        this.partEnFf = partEnFf;
    }

    public String getMontantDecompetCfa() {
        return montantDecompetCfa;
    }

    public void setMontantDecompetCfa(String montantDecompetCfa) {
        this.montantDecompetCfa = montantDecompetCfa;
    }

    public String getRemiseAvantDemarage() {
        return remiseAvantDemarage;
    }

    public void setRemiseAvantDemarage(String remiseAvantDemarage) {
        this.remiseAvantDemarage = remiseAvantDemarage;
    }

    public String getPayementDirect() {
        return payementDirect;
    }

    public void setPayementDirect(String payementDirect) {
        this.payementDirect = payementDirect;
    }

    public String getRetenuGaranti() {
        return retenuGaranti;
    }

    public void setRetenuGaranti(String retenuGaranti) {
        this.retenuGaranti = retenuGaranti;
    }

    public String getNetApaye() {
        return netApaye;
    }

    public void setNetApaye(String netApaye) {
        this.netApaye = netApaye;
    }

    public String getNetTotalAPaye() {
        return netTotalAPaye;
    }

    public void setNetTotalAPaye(String netTotalAPaye) {
        this.netTotalAPaye = netTotalAPaye;
    }

    public String getMontantNetAPaye() {
        return montantNetAPaye;
    }

    public void setMontantNetAPaye(String montantNetAPaye) {
        this.montantNetAPaye = montantNetAPaye;
    }

    public Bailleur getIdBailleur() {
        return idBailleur;
    }

    public void setIdBailleur(Bailleur idBailleur) {
        this.idBailleur = idBailleur;
    }

    public Client getIdClient() {
        return idClient;
    }

    public void setIdClient(Client idClient) {
        this.idClient = idClient;
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
        hash += (idDecompte != null ? idDecompte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Decompte)) {
            return false;
        }
        Decompte other = (Decompte) object;
        if ((this.idDecompte == null && other.idDecompte != null) || (this.idDecompte != null && !this.idDecompte.equals(other.idDecompte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Decompte[ idDecompte=" + idDecompte + " ]";
    }
    
}
