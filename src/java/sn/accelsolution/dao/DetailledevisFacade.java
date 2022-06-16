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
import sn.accelsolution.entities.Detailledevis;
import sn.accelsolution.entities.Devis;
import sn.accelsolution.util.FactureUtil;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DV7
 */
@Stateless
public class DetailledevisFacade extends AbstractFacade<Detailledevis> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetailledevisFacade() {
        super(Detailledevis.class);
    } 
    
     public void insertDetailleDevis(Detailledevis detailleDevis) {
        UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO detailledevis (reference,unite,designation,typeitem,quantite,pu,idDevis) "
                + " VALUES(?,?,?,?,?,?,?)");
        query.setParameter(1, detailleDevis.getReference());
        query.setParameter(2, detailleDevis.getUnite());
        query.setParameter(3, detailleDevis.getDesignation());
        query.setParameter(4, detailleDevis.getTypeitem());
        query.setParameter(5, detailleDevis.getQuantite());
        query.setParameter(6, detailleDevis.getPu());
        query.setParameter(7, detailleDevis.getIdDevis().getIdDevis());
        query.executeUpdate();
    }

    public List<Detailledevis> listOfDetailleDevisByDevis(Devis devis) {

        Query query = getEntityManager().createQuery("SELECT d FROM Detailledevis d  WHERE d.idDevis.idDevis = :param");
        query.setParameter("param", devis.getIdDevis());
        List<Detailledevis> lesdevis = query.getResultList();
        return lesdevis;
    }

    public void deleteByDevis(int idDevis) {
        Query query = em.createNativeQuery("DELETE FROM detailleDevis WHERE idDevis=?");
        query.setParameter(1, idDevis);
        query.executeUpdate();
    } 
    
    public Boolean verifierDetaille(FactureUtil futl) {
        boolean result = false;
        Query query = getEntityManager().createQuery("SELECT COUNT(d.idDetailDevis) FROM Detailledevis d WHERE d.idDetailDevis=:param1");
        query.setParameter("param1", futl.getIdDetailleCommande());
        long nbElement = (Long) query.getSingleResult();
        if (nbElement > 0) {
            result = true;
            return result;
        }
        return result;

    } 
    
}
