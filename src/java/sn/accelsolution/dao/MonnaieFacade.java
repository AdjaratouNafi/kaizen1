/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sn.accelsolution.entities.Monnaie;

/**
 *
 * @author DV7
 */
@Stateless
public class MonnaieFacade extends AbstractFacade<Monnaie> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MonnaieFacade() {
        super(Monnaie.class);
    } 
    
    public void insertMonnaie(Monnaie monnaie) {
        Query query = em.createNativeQuery("INSERT INTO monnaie (dateMonnaie,monnnaie, rendrepar, piece, numeropiece, montantlettre, nature, observation) "
                + " VALUES(?,?,?,?,?,?,?,?)");
        query.setParameter(1, monnaie.getDateMonnaie());
        query.setParameter(2, monnaie.getMonnnaie());
        query.setParameter(3, monnaie.getRendrepar());
        query.setParameter(4, monnaie.getPiece());
        query.setParameter(5, monnaie.getNumeropiece());
        query.setParameter(6, monnaie.getMontantlettre());
        query.setParameter(7, monnaie.getNature());
        query.setParameter(8, monnaie.getObservation());
        query.executeUpdate();
    }
    
}
