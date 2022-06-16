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
import sn.accelsolution.entities.Devise;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DV7
 */
@Stateless
public class DeviseFacade extends AbstractFacade<Devise> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DeviseFacade() {
        super(Devise.class);
    } 
    
    public void insertDevise(Devise devise) { 
        UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO devise (libelle,symbole) "
                + " VALUES(?,?)");
        query.setParameter(1, utfconvert.convertFromUTF8(devise.getLibelle()));
        query.setParameter(2, devise.getSymbole());
        query.executeUpdate();
    }
    
}
