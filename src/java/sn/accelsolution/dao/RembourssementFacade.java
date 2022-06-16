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
import sn.accelsolution.entities.Rembourssement;
import sn.accelsolution.entities.Salaire;
import sn.accelsolution.entities.Utilisateur;

/**
 *
 * @author DV7
 */
@Stateless
public class RembourssementFacade extends AbstractFacade<Rembourssement> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RembourssementFacade() {
        super(Rembourssement.class);
    } 
    
    public void insertRembourssement(Rembourssement rembourssement) {
        Query query = em.createNativeQuery("INSERT INTO rembourssement (idSalaire,idPret) "
                + " VALUES(?,?)");
        query.setParameter(1, rembourssement.getIdSalaire().getIdSalaire());
        query.setParameter(2, rembourssement.getIdPret().getIdPret());
        query.executeUpdate();
    }
    
    
    public List<Rembourssement> listOfRembourssementBySalaire(Salaire salaire, Utilisateur utilisateur) {
       
        Query query = getEntityManager().createQuery("SELECT r FROM Rembourssement r  WHERE r.idSalaire.idSalaire =:param1 AND r.idPret.idUtilisateur.idUtilisateur =:param2");
        query.setParameter("param1", salaire.getIdSalaire());
        query.setParameter("param2", utilisateur.getIdUtilisateur());
        List<Rembourssement> lesrembroussements = query.getResultList();
        return lesrembroussements;
    }
    
}
