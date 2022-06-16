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
import sn.accelsolution.entities.Corpsetat;
import sn.accelsolution.entities.Lottechnique;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DV7
 */
@Stateless
public class CorpsetatFacade extends AbstractFacade<Corpsetat> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CorpsetatFacade() {
        super(Corpsetat.class);
    } 
    
     public void insertCorpsetat(Corpsetat corpsetat) {
         UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO corpsetat (libellecoprsetat,idLottehcnique) "
                + " VALUES(?,?)");
        query.setParameter(1, utfconvert.convertFromUTF8(corpsetat.getLibellecoprsetat()));
        query.setParameter(2, corpsetat.getIdLottehcnique().getIdLottehcnique());
        query.executeUpdate();
    }

    public List<Corpsetat> listOfCorpsByLot(Lottechnique lottechnique) {

        Query query = getEntityManager().createQuery("SELECT c FROM Corpsetat c  WHERE c.idLottehcnique.idLottehcnique = :param");
        query.setParameter("param", lottechnique.getIdLottehcnique());
        List<Corpsetat> leslots = query.getResultList();
        return leslots;
    }
    
}
