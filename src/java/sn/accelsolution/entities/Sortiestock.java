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
@Table(name = "sortiestock")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sortiestock.findAll", query = "SELECT s FROM Sortiestock s")
    , @NamedQuery(name = "Sortiestock.findByIdSortiestock", query = "SELECT s FROM Sortiestock s WHERE s.idSortiestock = :idSortiestock")
    , @NamedQuery(name = "Sortiestock.findByDateSortiestock", query = "SELECT s FROM Sortiestock s WHERE s.dateSortiestock = :dateSortiestock")
    , @NamedQuery(name = "Sortiestock.findByQuantite", query = "SELECT s FROM Sortiestock s WHERE s.quantite = :quantite")
    , @NamedQuery(name = "Sortiestock.findByPu", query = "SELECT s FROM Sortiestock s WHERE s.pu = :pu")})
public class Sortiestock implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSortiestock")
    private Integer idSortiestock;
    @Size(max = 50)
    @Column(name = "dateSortiestock")
    private String dateSortiestock;
    @Column(name = "quantite")
    private Integer quantite;
    @Size(max = 255)
    @Column(name = "pu")
    private String pu;
    @JoinColumn(name = "idChantier", referencedColumnName = "idChantier")
    @ManyToOne
    private Chantier idChantier;
    @JoinColumn(name = "idEntrepot", referencedColumnName = "idEntrepot")
    @ManyToOne
    private Entrepot idEntrepot;
    @JoinColumn(name = "idStock", referencedColumnName = "idSotk")
    @ManyToOne
    private Stock idStock;
    @JoinColumn(name = "idUtilisateur", referencedColumnName = "idUtilisateur")
    @ManyToOne
    private Utilisateur idUtilisateur;

    public Sortiestock() {
    }

    public Sortiestock(Integer idSortiestock) {
        this.idSortiestock = idSortiestock;
    }

    public Integer getIdSortiestock() {
        return idSortiestock;
    }

    public void setIdSortiestock(Integer idSortiestock) {
        this.idSortiestock = idSortiestock;
    }

    public String getDateSortiestock() {
        return dateSortiestock;
    }

    public void setDateSortiestock(String dateSortiestock) {
        this.dateSortiestock = dateSortiestock;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public String getPu() {
        return pu;
    }

    public void setPu(String pu) {
        this.pu = pu;
    }

    public Chantier getIdChantier() {
        return idChantier;
    }

    public void setIdChantier(Chantier idChantier) {
        this.idChantier = idChantier;
    }

    public Entrepot getIdEntrepot() {
        return idEntrepot;
    }

    public void setIdEntrepot(Entrepot idEntrepot) {
        this.idEntrepot = idEntrepot;
    }

    public Stock getIdStock() {
        return idStock;
    }

    public void setIdStock(Stock idStock) {
        this.idStock = idStock;
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
        hash += (idSortiestock != null ? idSortiestock.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sortiestock)) {
            return false;
        }
        Sortiestock other = (Sortiestock) object;
        if ((this.idSortiestock == null && other.idSortiestock != null) || (this.idSortiestock != null && !this.idSortiestock.equals(other.idSortiestock))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.accelsolution.entities.Sortiestock[ idSortiestock=" + idSortiestock + " ]";
    }
    
}
