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
import sn.accelsolution.entities.Droitacces;
import sn.accelsolution.entities.Utilisateur;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DV7
 */
@Stateless
public class DroitaccesFacade extends AbstractFacade<Droitacces> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DroitaccesFacade() {
        super(Droitacces.class);
    } 
    
    public void insertDroit(Droitacces droitacces) {
        UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO droitacces (module,idUtilisateur,idRole) "
                + " VALUES(?,?,?)");
        query.setParameter(1, utfconvert.convertFromUTF8(droitacces.getModule()));
        query.setParameter(2, droitacces.getIdUtilisateur().getIdUtilisateur());
        query.setParameter(3, droitacces.getIdRole().getIdRole());
        query.executeUpdate();
    }

    public List<Droitacces> listOfDroitByUser(Utilisateur utilisateur) {
        Query query = getEntityManager().createQuery("SELECT d FROM Droitacces d  WHERE d.idUtilisateur.idUtilisateur = :param1 AND d.idRole.idRole = :param2");
        query.setParameter("param1", utilisateur.getIdUtilisateur());
        query.setParameter("param2", utilisateur.getIdRole().getIdRole());
        List<Droitacces> lesusers = query.getResultList();
        return lesusers;
    }

    public int maxDroit() { 
        int rs = 0;
        Query query = getEntityManager().createQuery("SELECT MAX(d.idPrivilege) FROM Droitacces d");  
        rs = (int) query.getSingleResult();
        return rs;
    }

    public List<Droitacces> listOfDroitByUtilisateur(Utilisateur utilisateur) {
        Query query = getEntityManager().createQuery("SELECT d FROM Droitacces d  WHERE d.idUtilisateur.idUtilisateur = :param1");
        query.setParameter("param1", utilisateur.getIdUtilisateur());
        List<Droitacces> lesusers = query.getResultList();
        return lesusers;
    } 
    
    public void deleteDroitacces(Droitacces droitacces) {
        Query query = em.createNativeQuery("DELETE FROM droitacces WHERE idPrivilege=:param");
        query.setParameter("param", droitacces.getIdPrivilege());
        query.executeUpdate();
    }
    
}
