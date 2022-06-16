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
import sn.accelsolution.entities.Opportunite;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DV7
 */
@Stateless
public class OpportuniteFacade extends AbstractFacade<Opportunite> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OpportuniteFacade() {
        super(Opportunite.class);
    } 
    
    public void insertOpportunite(Opportunite opportunite) {
        UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO opportunite (libelle,priorite, stadevente, valeur, dateCloture, probabilte, prochainetap, produit, derniercontact,nomcontact, email, telephone, idUtilisateur,rouge) "
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        query.setParameter(1, utfconvert.convertFromUTF8(opportunite.getLibelle()));
        query.setParameter(2, utfconvert.convertFromUTF8(opportunite.getPriorite()));
        query.setParameter(3, utfconvert.convertFromUTF8(opportunite.getStadevente()));
        query.setParameter(4, opportunite.getValeur());
        query.setParameter(5, opportunite.getDateCloture());
        query.setParameter(6, utfconvert.convertFromUTF8(opportunite.getProbabilte()));
        query.setParameter(7, opportunite.getProchainetap());
        query.setParameter(8, opportunite.getProduit());
        query.setParameter(9, opportunite.getDerniercontact());
        query.setParameter(10, utfconvert.convertFromUTF8(opportunite.getNomcontact()));
        query.setParameter(11, opportunite.getEmail());
        query.setParameter(12, opportunite.getTelephone());
        query.setParameter(13, opportunite.getIdUtilisateur().getIdUtilisateur());
        query.setParameter(14, opportunite.getRouge());
        query.executeUpdate();
    }
    
}
