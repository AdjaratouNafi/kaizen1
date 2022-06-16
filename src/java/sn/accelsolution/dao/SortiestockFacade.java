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
import sn.accelsolution.entities.Sortiestock;
import sn.accelsolution.entities.Stock;

/**
 *
 * @author DV7
 */
@Stateless
public class SortiestockFacade extends AbstractFacade<Sortiestock> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SortiestockFacade() {
        super(Sortiestock.class);
    } 
    
    public void insertSortie(Sortiestock sortieStock) {
        Query query = em.createNativeQuery("INSERT INTO sortieStock (dateSortiestock, quantite, pu,idStock, idUtilisateur,idChantier,idEntrepot) "
                + " VALUES(?,?,?,?,?,?,?)");
        query.setParameter(1, sortieStock.getDateSortiestock());
        query.setParameter(2, sortieStock.getQuantite());
        query.setParameter(3, sortieStock.getIdStock().getPuStock());
        query.setParameter(4, sortieStock.getIdStock().getIdSotk());
        query.setParameter(5, sortieStock.getIdUtilisateur().getIdUtilisateur());
        query.setParameter(6, sortieStock.getIdChantier().getIdChantier());
        query.setParameter(7, sortieStock.getIdEntrepot().getIdEntrepot());
        query.executeUpdate();
    }

    public List<Sortiestock> listOfSortieStockByStock(Stock stock) {
        Query query = getEntityManager().createQuery("SELECT s FROM Sortiestock s  WHERE s.idStock.idSotk  =:param");
        query.setParameter("param", stock.getIdSotk());
        List<Sortiestock> lesdetailles = query.getResultList();
        return lesdetailles;
    }
    
    public List<Sortiestock> listOfSortieStockByChantier(Chantier chantier) {
        Query query = getEntityManager().createQuery("SELECT s FROM Sortiestock s  WHERE s.idChantier.idChantier  =:param");
        query.setParameter("param", chantier.getIdChantier());
        List<Sortiestock> lesdetailles = query.getResultList();
        return lesdetailles;
    }
    
}
