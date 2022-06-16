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
import sn.accelsolution.entities.Salaire;

/**
 *
 * @author DV7
 */
@Stateless
public class SalaireFacade extends AbstractFacade<Salaire> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SalaireFacade() {
        super(Salaire.class);
    } 
    
     public void insertSalaire(Salaire salaire) {
        Query query = em.createNativeQuery("INSERT INTO salaire (periodeDebut,periodeFin,typeSalarie,netAPayer,idUtilisateur,periode,datepaiement) "
                + " VALUES(?,?,?,?,?,?,?)");
        query.setParameter(1, salaire.getPeriodeDebut());
        query.setParameter(2, salaire.getPeriodeFin());
        query.setParameter(3, salaire.getTypeSalarie());
        query.setParameter(4, salaire.getNetAPayer());
        query.setParameter(5, salaire.getIdUtilisateur().getIdUtilisateur());
        query.setParameter(6, salaire.getPeriode());
        query.setParameter(7, salaire.getDatepaiement()); 
        query.executeUpdate();
    }
    
    
    public void insertSalaire2(Salaire salaire) {
        Query query = em.createNativeQuery("INSERT INTO salaire (typeSalarie,periode,etat) "
                + " VALUES(?,?,?)");
        query.setParameter(1, salaire.getTypeSalarie());
        query.setParameter(2, salaire.getPeriode());
        query.setParameter(3, salaire.getEtat()); 
        query.executeUpdate();
    }
    
    
     public int maxSalaire() {
        int rs = 0;
        Query query = getEntityManager().createQuery("SELECT MAX(s.idSalaire) FROM Salaire s");
        rs = (int) query.getSingleResult();

        return rs;

    } 
    
    
    public List<Salaire> listOfSalaireWithoutImpayer() {
        String param = "Impayer";
        Query query = getEntityManager().createQuery("SELECT s FROM Salaire s  WHERE s.etat <> :param");
        query.setParameter("param", param);
        List<Salaire> lesusers = query.getResultList();
        return lesusers;
    }
    
    public Salaire findSalaireByPeriode(Salaire salaire) {

        Query query = getEntityManager().createQuery("SELECT s FROM Salaire s WHERE s.periode =:periode");
        query.setParameter("periode", salaire.getPeriode());
        List<Salaire> lesperiodes = query.getResultList();
        if (lesperiodes.size() > 0) {
            return lesperiodes.get(0);
        }
        return null;

    }
    
}
