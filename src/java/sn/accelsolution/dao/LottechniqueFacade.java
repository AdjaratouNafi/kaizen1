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
import sn.accelsolution.entities.Lottechnique;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DV7
 */
@Stateless
public class LottechniqueFacade extends AbstractFacade<Lottechnique> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LottechniqueFacade() {
        super(Lottechnique.class);
    }
    
    public void insertLottechnique(Lottechnique lottechnique) {
        UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO lottechnique (libellelottechnique) "
                + " VALUES(?)");
        query.setParameter(1, utfconvert.convertFromUTF8(lottechnique.getLibellelottechnique()));
        query.executeUpdate();
    }
}
