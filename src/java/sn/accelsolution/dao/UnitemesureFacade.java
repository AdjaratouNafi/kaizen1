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
import sn.accelsolution.entities.Unitemesure;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DV7
 */
@Stateless
public class UnitemesureFacade extends AbstractFacade<Unitemesure> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UnitemesureFacade() {
        super(Unitemesure.class);
    } 
    
    public void insertUnite(Unitemesure unitemesure) {
        UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO unitemesure (libelle,abreviation) "
                + " VALUES(?,?)");
        query.setParameter(1, utfconvert.convertFromUTF8(unitemesure.getLibelle()));
        query.setParameter(2, unitemesure.getAbreviation());
        query.executeUpdate();
    }
    
}
