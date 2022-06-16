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
import sn.accelsolution.entities.Entrepot;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DV7
 */
@Stateless
public class EntrepotFacade extends AbstractFacade<Entrepot> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EntrepotFacade() {
        super(Entrepot.class);
    }

    public void insertEntrepot(Entrepot entrepot) {
        UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO entrepot (nom,adresse,telephone,idUtilisateur) "
                + " VALUES(?,?,?,?)");
        query.setParameter(1, utfconvert.convertFromUTF8(entrepot.getNom()));
        query.setParameter(2, utfconvert.convertFromUTF8(entrepot.getAdresse()));
        query.setParameter(3, entrepot.getTelephone());
        query.setParameter(4, entrepot.getIdUtilisateur().getIdUtilisateur());
        query.executeUpdate();
    }

}
