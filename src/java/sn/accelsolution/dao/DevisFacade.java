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
import sn.accelsolution.entities.Devis;
import sn.accelsolution.entities.Utilisateur;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DV7
 */
@Stateless
public class DevisFacade extends AbstractFacade<Devis> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DevisFacade() {
        super(Devis.class);
    } 
    
     public void insertDevis(Devis devis) {
        UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO devis (numeroDevis,dateDevis,nommarche,montantlettre,etat,idClient,idDevise,idUtilisateur,terme,nbjours,coef) "
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?)");
        query.setParameter(1, devis.getNumeroDevis());
        query.setParameter(2, devis.getDateDevis());
        query.setParameter(3, devis.getNommarche());
        query.setParameter(4, devis.getMontantlettre());
        query.setParameter(5, devis.getEtat());
        query.setParameter(6, devis.getIdClient().getIdClient());
        query.setParameter(7, devis.getIdDevise().getIdDevise());
        query.setParameter(8, devis.getIdUtilisateur().getIdUtilisateur());
        query.setParameter(9, devis.getTerme());
        query.setParameter(10, devis.getNbjours());
        query.setParameter(11, devis.getCoef());
        query.executeUpdate();
    }

    public int maxDevis() {
        int rs = 0;
        Query query = getEntityManager().createQuery("SELECT MAX(d.idDevis) FROM Devis d");
        rs = (int) query.getSingleResult();
        return rs;
    } 
    
    public List<Devis> listOfDevisByUser(Utilisateur user) {

        Query query = getEntityManager().createQuery("SELECT d FROM Devis d  WHERE d.idUtilisateur.idUtilisateur = :param");
        query.setParameter("param", user.getIdUtilisateur());
        List<Devis> lesDevis = query.getResultList();
        return lesDevis;
    }
    
}
