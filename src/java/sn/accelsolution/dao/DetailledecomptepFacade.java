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
import sn.accelsolution.entities.Detailledecomptep;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DV7
 */
@Stateless
public class DetailledecomptepFacade extends AbstractFacade<Detailledecomptep> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetailledecomptepFacade() {
        super(Detailledecomptep.class);
    }

    public void insertDetailleDecompte(Detailledecomptep detailleDecomptep) {
        UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO detailledecomptep (designation,typeitem,reference,unite,quantite,pu,execution,idDecomptep) "
                + " VALUES(?,?,?,?,?,?,?,?)");
        query.setParameter(1, utfconvert.convertFromUTF8(detailleDecomptep.getDesignation()));
        query.setParameter(2, detailleDecomptep.getTypeitem());
        query.setParameter(3, utfconvert.convertFromUTF8(detailleDecomptep.getReference()));
        query.setParameter(4, utfconvert.convertFromUTF8(detailleDecomptep.getUnite()));
        query.setParameter(5, detailleDecomptep.getQuantite());
        query.setParameter(6, detailleDecomptep.getPu());
        query.setParameter(7, detailleDecomptep.getExecution());
        query.setParameter(8, detailleDecomptep.getIdDecomptep().getIdDecomptep());
        query.executeUpdate();
    }

    public List<Detailledecomptep> listOfDetailleDecompetByDecompte(Decomptep decomptep) {

        Query query = getEntityManager().createQuery("SELECT d FROM Detailledecomptep d  WHERE d.idDecomptep.idDecomptep = :param");
        query.setParameter("param", decomptep.getIdDecomptep());
        List<Detailledecomptep> lesdecomptes = query.getResultList();
        return lesdecomptes;
    }

    public void deleteByDecompte(int idDecompte) {
        Query query = em.createNativeQuery("DELETE FROM detailleDecomptep WHERE idDetailleDecomptep=?");
        query.setParameter(1, idDecompte);
        query.executeUpdate();
    }

}
