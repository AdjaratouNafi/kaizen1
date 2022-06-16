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
import sn.accelsolution.entities.Bailleur;

/**
 *
 * @author DV7
 */
@Stateless
public class BailleurFacade extends AbstractFacade<Bailleur> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BailleurFacade() {
        super(Bailleur.class);
    } 
    
    public void insertBailleur(Bailleur bailleur) {
        Query query = em.createNativeQuery("INSERT INTO bailleur (nomBailleur,adresseBailleur,telBailleur,bpBailleur,mailBailleur) "
                + " VALUES(?,?,?,?,?)");
        query.setParameter(1, bailleur.getNomBailleur());
        query.setParameter(2, bailleur.getAdresseBailleur());
        query.setParameter(3, bailleur.getTelBailleur());
        query.setParameter(4, bailleur.getBpBailleur());
        query.setParameter(5, bailleur.getMailBailleur());
        query.executeUpdate();
    }
    
}
