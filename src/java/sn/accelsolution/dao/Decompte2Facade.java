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
import sn.accelsolution.entities.Decompte2;

/**
 *
 * @author DV7
 */
@Stateless
public class Decompte2Facade extends AbstractFacade<Decompte2> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Decompte2Facade() {
        super(Decompte2.class);
    } 
    
    public void insertDecompte(Decompte2 decompte2) {
        Query query = em.createNativeQuery("INSERT INTO decompte2 (dateDecompte2,numeroDecompte2,idMarche) "
                + " VALUES(?,?,?)");
        query.setParameter(1, decompte2.getDateDecompte2());
        query.setParameter(2, decompte2.getNumeroDecompte2());
        query.setParameter(3, decompte2.getIdMarche().getIdMarche());
        query.executeUpdate();
    }

    public int maxCommande() {
        int rs = 0;
        Query query = getEntityManager().createQuery("SELECT MAX(d.idDecompte2) FROM Decompte2 d");
        rs = (int) query.getSingleResult();

        return rs;

    }
    
}
