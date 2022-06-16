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
import sn.accelsolution.entities.Detaillechantier;

/**
 *
 * @author DV7
 */
@Stateless
public class DetaillechantierFacade extends AbstractFacade<Detaillechantier> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetaillechantierFacade() {
        super(Detaillechantier.class);
    } 
    
    /*public void insertChantier(DetailleChantier detailleChantier) {
        Query query = em.createNativeQuery("INSERT INTO detailleChantier (dateAlocation,idChantier,idPrestataire,idprestatairePrim)"
                + " VALUES(?,?,?,?)");
        query.setParameter(1, detailleChantier.getDateAlocation());
        query.setParameter(2, detailleChantier.getIdChantier().getIdChantier());
        query.setParameter(3, detailleChantier.getIdPrestataire().getIdPrestataire());
        query.setParameter(4, detailleChantier.getIdprestatairePrim().getIdprestatairePrim());
        query.executeUpdate();
    } */ 
    
    public void insertChantierAffectation(Detaillechantier detailleChantier) {
        Query query = em.createNativeQuery("INSERT INTO detaillechantier (dateAlocation,idChantier,idPrestataire,idprestatairePrim)"
                + " VALUES(?,?,?,?)");
        query.setParameter(1, detailleChantier.getDateAlocation());
        query.setParameter(2, detailleChantier.getIdChantier().getIdChantier());
        query.setParameter(3, detailleChantier.getIdPrestataire().getIdPrestataire());
        query.setParameter(4, detailleChantier.getIdprestatairePrim().getIdprestatairePrim());
        query.executeUpdate();
    } 
    
    
   /* public void insertChantierr(DetailleChantier detailleChantier) {
        Query query = em.createNativeQuery("INSERT INTO detailleChantier (dateAlocation,idRessource,idChantier,qt)"
                + " VALUES(?,?,?,?)");
        query.setParameter(1, detailleChantier.getDateAlocation());
        query.setParameter(2, detailleChantier.getIdRessource().getIdRessource());
        query.setParameter(3, detailleChantier.getIdChantier().getIdChantier());
        query.setParameter(4, detailleChantier.getQt());
        query.executeUpdate();
    } */
    
    
    public List<Detaillechantier> listOfRessourceByChantier(int idChantier) {
        Query query = getEntityManager().createQuery("SELECT d FROM Detaillechantier d  WHERE d.idChantier.idChantier =:param AND d.idUtilisateur IS NULL");
        query.setParameter("param", idChantier);
        List<Detaillechantier> lesdetailles = query.getResultList();
        return lesdetailles;
    }
    
    
    public List<Detaillechantier> listOfPersonnelByChantier(int idChantier) {
        Query query = getEntityManager().createQuery("SELECT d FROM Detaillechantier d  WHERE d.idChantier.idChantier =:param AND d.idUtilisateur IS NOT NULL");
        query.setParameter("param", idChantier);
        List<Detaillechantier> lesdetailles = query.getResultList();
        return lesdetailles;
    }
    
    public List<Detaillechantier> listOfPrestataitreByChantier(int idChantier) {
        Query query = getEntityManager().createQuery("SELECT d FROM Detaillechantier d  WHERE d.idChantier.idChantier =:param AND d.idPrestataire IS NOT NULL");
        query.setParameter("param", idChantier);
        List<Detaillechantier> lesdetailles = query.getResultList();
        return lesdetailles;
    }
    
}
