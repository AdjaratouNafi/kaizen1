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
import sn.accelsolution.entities.Indemnite;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DV7
 */
@Stateless
public class IndemniteFacade extends AbstractFacade<Indemnite> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IndemniteFacade() {
        super(Indemnite.class);
    } 
    
    public void insertIndemnite(Indemnite indemnite) {
        UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO indemnite (libelleIndemnite,dateCreationIndemnite, frais, effet, etatIndemnite, idUtilisateur) "
                + " VALUES(?,?,?,?,?,?)");
        query.setParameter(1, utfconvert.convertFromUTF8(indemnite.getLibelleIndemnite()));
        query.setParameter(2, indemnite.getDateCreationIndemnite());
        query.setParameter(3, indemnite.getFrais());
        query.setParameter(4, indemnite.getEffet());
        query.setParameter(5, utfconvert.convertFromUTF8(indemnite.getEtatIndemnite()));
        query.setParameter(6, indemnite.getIdUtilisateur().getIdUtilisateur());
        query.executeUpdate();
    }
    
}
