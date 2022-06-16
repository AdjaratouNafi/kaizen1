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
import sn.accelsolution.entities.Deboursser;
import sn.accelsolution.entities.Marchandise;

/**
 *
 * @author DV7
 */
@Stateless
public class DeboursserFacade extends AbstractFacade<Deboursser> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DeboursserFacade() {
        super(Deboursser.class);
    } 
    
    public List<Deboursser> listOfDeboursserByMarchandise(Marchandise marchandise) {
        Query query = getEntityManager().createQuery("SELECT d FROM Deboursser d  WHERE d.idMateriel.idMarchandise = :param");
        query.setParameter("param", marchandise.getIdMarchandise());
        List<Deboursser> lesusers = query.getResultList();
        return lesusers;
    } 
    
}
