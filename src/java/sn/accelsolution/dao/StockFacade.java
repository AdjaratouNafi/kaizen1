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
import sn.accelsolution.entities.Commande;
import sn.accelsolution.entities.Entrepot;
import sn.accelsolution.entities.Marchandise;
import sn.accelsolution.entities.Stock;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DV7
 */
@Stateless
public class StockFacade extends AbstractFacade<Stock> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StockFacade() {
        super(Stock.class);
    } 
    
    public void insertStock(Stock stock) {
        UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO stock (designation,dateEntreStock, qtStock, puStock , qtSeuille, dateLastAppro, qt, pu, idFournisseur, idCommande, idUtilisateur, commentaire,idEntrepot) "
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
        query.setParameter(1, utfconvert.convertFromUTF8(stock.getDesignation()));
        query.setParameter(2, stock.getDateEntreStock());
        query.setParameter(3, stock.getQtStock());
        query.setParameter(4, stock.getPuStock());
        query.setParameter(5, stock.getQtSeuille());
        query.setParameter(6, stock.getDateLastAppro());
        query.setParameter(7, stock.getQt());
        query.setParameter(8, stock.getPu());
        query.setParameter(9, stock.getIdFournisseur().getIdFournisseur());
        query.setParameter(10, stock.getIdCommande().getIdCommande());
        query.setParameter(11, stock.getIdUtilisateur().getIdUtilisateur());
        query.setParameter(12, utfconvert.convertFromUTF8(stock.getCommentaire()));
        query.setParameter(13, stock.getIdEntrepot().getIdEntrepot());
        query.executeUpdate();
    }

    public Stock findStockByMarchandise(Marchandise marchandise) {

        Query query = getEntityManager().createQuery("SELECT s FROM Stock s WHERE s.idMarchandise.idMarchandise=:idMarchandise");
        query.setParameter("idMarchandise", marchandise.getIdMarchandise());
        List<Stock> leusers = query.getResultList();
        if (leusers.size() > 0) {
            return leusers.get(0);
        }
        return null;

    }
    
    public List<Stock> listOfStockByMarchandise(Marchandise marchandise) {
        Query query = getEntityManager().createQuery("SELECT s FROM Stock s  WHERE s.designation = :param");
        query.setParameter("param", marchandise.getLibelle());
        List<Stock> lesusers = query.getResultList();
        return lesusers;
    } 

    public List<Stock> listOfStockByCommande(Commande commande) {
        Query query = getEntityManager().createQuery("SELECT s FROM Stock s  WHERE s.idCommande.idCommande = :param");
        query.setParameter("param", commande.getIdCommande());
        List<Stock> lesusers = query.getResultList();
        return lesusers;
    } 
    
    
    public List<Stock> listOfStockByEntrepot(Entrepot entrepot) {
        Query query = getEntityManager().createQuery("SELECT s FROM Stock s  WHERE s.idEntrepot.idEntrepot = :param");
        query.setParameter("param", entrepot.getIdEntrepot());
        List<Stock> lesusers = query.getResultList();
        return lesusers;
    } 
    
    public Stock findStockByMarchandiseAndStock(String marchandise, Entrepot entrepot) {

        Query query = getEntityManager().createQuery("SELECT s FROM Stock s WHERE s.designation=:param1 AND s.idEntrepot.idEntrepot=:param2");
        query.setParameter("param1", marchandise);
        query.setParameter("param2", entrepot.getIdEntrepot());
        List<Stock> leusers = query.getResultList();
        if (leusers.size() > 0) {
            return leusers.get(0);
        }
        return null;

    }
    
}
