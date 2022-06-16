/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.entities;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "brouillard")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Brouillard.findAll", query = "SELECT b FROM Brouillard b")
    , @NamedQuery(name = "Brouillard.findByIdBrouillard", query = "SELECT b FROM Brouillard b WHERE b.idBrouillard = :idBrouillard")
    , @NamedQuery(name = "Brouillard.findByTypebrouillard", query = "SELECT b FROM Brouillard b WHERE b.typebrouillard = :typebrouillard")
    , @NamedQuery(name = "Brouillard.findByObjetdebit", query = "SELECT b FROM Brouillard b WHERE b.objetdebit = :objetdebit")
    , @NamedQuery(name = "Brouillard.findByDebit", query = "SELECT b FROM Brouillard b WHERE b.debit = :debit")
    , @NamedQuery(name = "Brouillard.findByCredit", query = "SELECT b FROM Brouillard b WHERE b.credit = :credit")
    , @NamedQuery(name = "Brouillard.findByObjetcredit", query = "SELECT b FROM Brouillard b WHERE b.objetcredit = :objetcredit")
    , @NamedQuery(name = "Brouillard.findByTotal", query = "SELECT b FROM Brouillard b WHERE b.total = :total")})
public class Brouillard implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idBrouillard")
    private Integer idBrouillard;
    @Size(max = 50)
    @Column(name = "typebrouillard")
    private String typebrouillard;
    @Size(max = 50)
    @Column(name = "objetdebit")
    private String objetdebit;
    @Column(name = "debit")
    private BigInteger debit;
    @Column(name = "credit")
    private BigInteger credit;
    @Size(max = 50)
    @Column(name = "objetcredit")
    private String objetcredit;
    @Column(name = "total")
    private BigInteger total;
    @JoinColumn(name = "idChantier", referencedColumnName = "idChantier")
    @ManyToOne
    private Chantier idChantier;
    @JoinColumn(name = "idMarche", referencedColumnName = "idMarche")
    @ManyToOne
    private Marche idMarche;
    @JoinColumn(name = "idMonnaie", referencedColumnName = "idMonnaie")
    @ManyToOne
    private Monnaie idMonnaie;
    @JoinColumn(name = "idUtilisateur", referencedColumnName = "idUtilisateur")
    @ManyToOne
    private Utilisateur idUtilisateur;

    public Brouillard() {
    }

    public Brouillard(Integer idBrouillard) {
        this.idBrouillard = idBrouillard;
    }

    public Integer getIdBrouillard() {
        return idBrouillard;
    }

    public void setIdBrouillard(Integer idBrouillard) {
        this.idBrouillard = idBrouillard;
    }

    public String getTypebrouillard() {
        return typebrouillard;
    }

    public void setTypebrouillard(String typebrouillard) {
        this.typebrouillard = typebrouillard;
    }

    public String getObjetdebit() {
        return objetdebit;
    }

    public void setObjetdebit(String objetdebit) {
        this.objetdebit = objetdebit;
    }

    public BigInteger getDebit() {
        return debit;
    }

    public void setDebit(BigInteger debit) {
        this.debit = debit;
    }

    public BigInteger getCredit() {
        return credit;
    }

    public void setCredit(BigInteger credit) {
        this.credit = credit;
    }

    public String getObjetcredit() {
        return objetcredit;
    }

    public void setObjetcredit(String objetcredit) {
        this.objetcredit = objetcredit;
    }

    public BigInteger getTotal() {
        return total;
    }

    public void setTotal(BigInteger total) {
        this.total = total;
    }

    public Chantier getIdChantier() {
        return idChantier;
    }

    public void setIdChantier(Chantier idChantier) {
        this.idChantier = idChantier;
    }

    public Marche getIdMarche() {
        return idMarche;
    }

    public void setIdMarche(Marche idMarche) {
        this.idMarche = idMarche;
    }

    public Monnaie getIdMonnaie() {
        return idMonnaie;
    }

    public void setIdMonnaie(Monnaie idMonnaie) {
        this.idMonnaie = idMonnaie;
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
        hash += (idBrouillard != null ? idBrouillard.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Brouillard)) {
            return false;
        }
        Brouillard other = (Brouillard) object;
        if ((this.idBrouillard == null && other.idBrouillard != null) || (this.idBrouillard != null && !this.idBrouillard.equals(other.idBrouillard))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Brouillard[ idBrouillard=" + idBrouillard + " ]";
    }
    
}
