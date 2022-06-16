/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sn.accelsolution.entities.Prestataireprim;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DV7
 */
@Stateless
public class PrestataireprimFacade extends AbstractFacade<Prestataireprim> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrestataireprimFacade() {
        super(Prestataireprim.class);
    } 
    
    public void insertPrestataire(Prestataireprim prestatairePrim) {
        UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO prestataireprim (ninea,nomcomplet,corps,telephone) "
                + " VALUES(?,?,?,?)");
        query.setParameter(1, prestatairePrim.getNinea());
        query.setParameter(2, utfconvert.convertFromUTF8(prestatairePrim.getNomcomplet()));
        query.setParameter(3, utfconvert.convertFromUTF8(prestatairePrim.getCorps()));
        query.setParameter(4, prestatairePrim.getTelephone());
        query.executeUpdate();
    }
    
    public int maxPrestataire() {
        int rs = 0;
        Query query = getEntityManager().createQuery("SELECT MAX(p.idprestatairePrim) FROM Prestataireprim p");
        rs = (int) query.getSingleResult();

        return rs;

    }
    
}
