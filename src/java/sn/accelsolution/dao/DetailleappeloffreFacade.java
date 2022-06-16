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
import sn.accelsolution.entities.AppelOffre;
import sn.accelsolution.entities.Detailleappeloffre;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DV7
 */
@Stateless
public class DetailleappeloffreFacade extends AbstractFacade<Detailleappeloffre> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetailleappeloffreFacade() {
        super(Detailleappeloffre.class);
    } 
    
    public void insertDetailAppelOffre(Detailleappeloffre detailleAppelOffre) {
        UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO detailleappeloffre (dateaffectation,tache,idUtilisateur,idAppel)"
                + " VALUES(?,?,?,?)");
        query.setParameter(1, detailleAppelOffre.getDateaffectation());
        query.setParameter(2, utfconvert.convertFromUTF8(detailleAppelOffre.getTache()));
        query.setParameter(3, detailleAppelOffre.getIdUtilisateur().getIdUtilisateur());
        query.setParameter(4, detailleAppelOffre.getIdAppel().getIdAppel());
        query.executeUpdate();
    }

    public List<Detailleappeloffre> listOfDetailApelOfreByAppel(AppelOffre appelOffre) {

        Query query = getEntityManager().createQuery("SELECT d FROM Detailleappeloffre d  WHERE d.idAppel.idAppel =:param");
        query.setParameter("param", appelOffre.getIdAppel());
        List<Detailleappeloffre> lesdetails = query.getResultList();
        return lesdetails;
    }
    
}
