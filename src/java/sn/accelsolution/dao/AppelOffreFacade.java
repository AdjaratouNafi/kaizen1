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
import sn.accelsolution.entities.AppelOffre;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DV7
 */
@Stateless
public class AppelOffreFacade extends AbstractFacade<AppelOffre> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AppelOffreFacade() {
        super(AppelOffre.class);
    } 
    
    public void insertAppelOffre(AppelOffre appelOffre) {
        UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO appel_offre (dateAppel,numAppel, typeAppel, etatApel, idUtilisateur) "
                + " VALUES(?,?,?,?,?)");
        query.setParameter(1, appelOffre.getDateAppel());
        query.setParameter(2, appelOffre.getNumAppel());
        query.setParameter(3, utfconvert.convertFromUTF8(appelOffre.getTypeAppel()));
        query.setParameter(4, utfconvert.convertFromUTF8(appelOffre.getEtatApel()));
        query.setParameter(5, appelOffre.getIdUtilisateur().getIdUtilisateur());
        query.executeUpdate();
    }
    
}
