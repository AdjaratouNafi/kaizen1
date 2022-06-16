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
import sn.accelsolution.entities.Decompte;

/**
 *
 * @author DV7
 */
@Stateless
public class DecompteFacade extends AbstractFacade<Decompte> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DecompteFacade() {
        super(Decompte.class);
    } 
    
    public void insertDecompte(Decompte decompte) {
        Query query = em.createNativeQuery("INSERT INTO decompte (dateDecompte,montantmarche, montantExecution, montantdemarage, avancesurappro, idMarche, idClient) "
                + " VALUES(?,?,?,?,?,?,?)");
        query.setParameter(1, decompte.getDateDecompte());
        query.setParameter(2, decompte.getMontantmarche());
        query.setParameter(3, decompte.getMontantExecution());
        query.setParameter(4, decompte.getMontantdemarage());
        query.setParameter(5, decompte.getAvancesurappro());
        query.setParameter(6, decompte.getIdMarche().getIdMarche());
        query.setParameter(7, decompte.getIdClient().getIdClient());
        query.executeUpdate();
    }
    
}
