/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.dao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sn.accelsolution.entities.Fournisseur;
import sn.accelsolution.entities.Reffournisseur;

/**
 *
 * @author DV7
 */
@Stateless
public class ReffournisseurFacade extends AbstractFacade<Reffournisseur> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReffournisseurFacade() {
        super(Reffournisseur.class);
    } 
    
    public List<Reffournisseur> listOfFournisseurs() {   

        List<Reffournisseur> fournisseurList = new ArrayList<>();

        Query query = getEntityManager().createQuery("SELECT r FROM Reffournisseur r");
        fournisseurList = query.getResultList();
        if (fournisseurList.size() > 0) {
            return fournisseurList;
        }
        return null;

    } 
    
    
    public void insertRefFournisseur(Reffournisseur refFournisseur) {
        Query query = em.createNativeQuery("INSERT INTO reffournisseur (nomcomplet, telephone, accord, accompte, reliquant, voyant, echeance, idFournisseur, idCommande) "
                + " VALUES(?,?,?,?,?,?,?,?,?)");
        query.setParameter(1, refFournisseur.getNomcomplet());
        query.setParameter(2, refFournisseur.getTelephone());
        query.setParameter(3, refFournisseur.getAccord());
        query.setParameter(4, refFournisseur.getAccompte());
        query.setParameter(5, refFournisseur.getReliquant());
        query.setParameter(6, refFournisseur.getVoyant());
        query.setParameter(7, "Aucune");
        query.setParameter(8, refFournisseur.getIdFournisseur().getIdFournisseur());
        query.setParameter(9, refFournisseur.getIdCommande().getIdCommande());

        query.executeUpdate();
    } 
    
    public List<Reffournisseur> listOfRefFournisseurByFssr(Fournisseur fournisseur) {
        Query query = getEntityManager().createQuery("SELECT f FROM Reffournisseur f  WHERE f.idFournisseur.idFournisseur =:param");
        query.setParameter("param", fournisseur.getIdFournisseur());
        List<Reffournisseur> lalist = query.getResultList();
        return lalist;
    }
    
}
