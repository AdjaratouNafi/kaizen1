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
import sn.accelsolution.entities.Chantier;
import sn.accelsolution.entities.Deboursser;
import sn.accelsolution.entities.Mouvementdebourse;

/**
 *
 * @author DV7
 */
@Stateless
public class MouvementdebourseFacade extends AbstractFacade<Mouvementdebourse> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MouvementdebourseFacade() {
        super(Mouvementdebourse.class);
    }
    
    public List<Mouvementdebourse> listOfMouvementdebourseByChantier(Chantier chantier) {

        Query query = getEntityManager().createQuery("SELECT m FROM Mouvementdebourse m  WHERE m.idChantier.idChantier = :param");
        query.setParameter("param", chantier.getIdChantier());
        List<Mouvementdebourse> lesMouvementdebourses = query.getResultList();
        return lesMouvementdebourses;
    }
    
}
