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
import sn.accelsolution.entities.Marchandise;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DV7
 */
@Stateless
public class MarchandiseFacade extends AbstractFacade<Marchandise> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MarchandiseFacade() {
        super(Marchandise.class);
    } 
    
    public void insertMarchandise(Marchandise marchandise) {
        UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO marchandise (libelle,idCorpsetat,idLottechnique) "
                + " VALUES(?,?,?)");
        query.setParameter(1, utfconvert.convertFromUTF8(marchandise.getLibelle()));
        query.setParameter(2, marchandise.getIdCorpsetat().getIdCorpsetat());
        query.setParameter(3, marchandise.getIdLottechnique().getIdLottehcnique());
        query.executeUpdate();
    }
    
    public Marchandise findMarchandiseByLibelle(String libelle) {

        Query query = getEntityManager().createQuery("SELECT m FROM Marchandise m WHERE m.libelle =:param");
        query.setParameter("param", libelle);
        List<Marchandise> leusers = query.getResultList();
        if (leusers.size() > 0) {
            return leusers.get(0);
        }
        return null;

    }
    
}
