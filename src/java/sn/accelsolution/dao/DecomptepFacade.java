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
import sn.accelsolution.entities.Decomptep;
import sn.accelsolution.entities.Newfacture;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DV7
 */
@Stateless
public class DecomptepFacade extends AbstractFacade<Decomptep> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DecomptepFacade() {
        super(Decomptep.class);
    } 
    
    public void insertDecompte(Decomptep decomptep) {
        UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO decomptep (numeroDecomptep,dateDecomptep,montantlettre,etat,idNewfacture,idUtilisateur) "
                + " VALUES(?,?,?,?,?,?)");
        query.setParameter(1, decomptep.getNumeroDecomptep());
        query.setParameter(2, decomptep.getDateDecomptep());
        query.setParameter(3, decomptep.getMontantlettre());
        query.setParameter(4, utfconvert.convertFromUTF8(decomptep.getEtat()));
        query.setParameter(5, decomptep.getIdNewfacture().getIdNewfacture());
        query.setParameter(6, decomptep.getIdUtilisateur().getIdUtilisateur());
        query.executeUpdate();
    }

    public int maxDecompte() {
        int rs = 0;
        Query query = getEntityManager().createQuery("SELECT MAX(p.idDecomptep) FROM Decomptep p");
        rs = (int) query.getSingleResult();
        return rs;
    }

    public List<Decomptep> listOfDecompetByfacture(Decomptep decomptep) {

        Query query = getEntityManager().createQuery("SELECT d FROM Decomptep d  WHERE d.idNewfacture.idNewfacture = :param");
        query.setParameter("param", decomptep.getIdNewfacture().getIdNewfacture());
        List<Decomptep> lesdecomptes = query.getResultList();
        return lesdecomptes;
    }

    public Decomptep getDecompteByFacture(Newfacture newfacture) {

        Query query = getEntityManager().createQuery("SELECT d FROM Decomptep d WHERE d.idNewfacture.idNewfacture =:facture");
        query.setParameter("facture", newfacture.getIdNewfacture());
        List<Decomptep> leusers = query.getResultList();
        if (leusers.size() > 0) {
            return leusers.get(0);
        }
        return null;

    }

    public List<Decomptep> listOfDecompteByIdNewFacture(Newfacture newfacture) {

        Query query = getEntityManager().createQuery("SELECT d FROM Decomptep d WHERE d.idNewfacture.idNewfacture =:facture");
        query.setParameter("facture", newfacture.getIdNewfacture());
        List<Decomptep> lesdecomptes = query.getResultList();
        return lesdecomptes;
    }
    
}
