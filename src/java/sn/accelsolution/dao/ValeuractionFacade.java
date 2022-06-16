/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sn.accelsolution.entities.Valeuraction;

/**
 *
 * @author DV7
 */
@Stateless
public class ValeuractionFacade extends AbstractFacade<Valeuraction> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ValeuractionFacade() {
        super(Valeuraction.class);
    }
    
}
