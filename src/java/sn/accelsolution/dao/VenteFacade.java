/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sn.accelsolution.entities.Fournisseur;
import sn.accelsolution.entities.Vente;

/**
 *
 * @author DV7
 */
@Stateless
public class VenteFacade extends AbstractFacade<Vente> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VenteFacade() {
        super(Vente.class);
    } 
    
    public void insertVente(Vente vente) {
        Query query = em.createNativeQuery("INSERT INTO vente (idMarchandise,idFournisseur) "
                + " VALUES(?,?)");
        query.setParameter(1, vente.getIdMarchandise().getIdMarchandise());
        query.setParameter(2, vente.getIdFournisseur().getIdFournisseur());
        query.executeUpdate();
    }
    
     public List<Vente> listOfVenteByFssr(Fournisseur fournisseur) {
        
        Query query = getEntityManager().createQuery("SELECT v FROM Vente v  WHERE v.idFournisseur.idFournisseur =:param");
        query.setParameter("param", fournisseur.getIdFournisseur());
        List<Vente> lesventes = query.getResultList();
        return lesventes;
    }
    
}
