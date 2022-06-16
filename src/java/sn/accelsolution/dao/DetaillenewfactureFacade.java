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
import sn.accelsolution.entities.Detaillenewfacture;
import sn.accelsolution.entities.Newfacture;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DV7
 */
@Stateless
public class DetaillenewfactureFacade extends AbstractFacade<Detaillenewfacture> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetaillenewfactureFacade() {
        super(Detaillenewfacture.class);
    } 
    
    public void insertDetailleDevis(Detaillenewfacture detailleNewfacture) {
        UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO detaillenewfacture (reference,unite,designation,typeitem,quantite,pu,idNewfacture) "
                + " VALUES(?,?,?,?,?,?,?)");
        query.setParameter(1, detailleNewfacture.getReference());
        query.setParameter(2, detailleNewfacture.getUnite());
        query.setParameter(3, detailleNewfacture.getDesignation());
        query.setParameter(4, detailleNewfacture.getTypeitem());
        query.setParameter(5, detailleNewfacture.getQuantite());
        query.setParameter(6, detailleNewfacture.getPu());
        query.setParameter(7, detailleNewfacture.getIdNewfacture().getIdNewfacture());
        query.executeUpdate();
    }

    public List<Detaillenewfacture> listOfDetailleFactureByfacture(Newfacture newfacture) {

        Query query = getEntityManager().createQuery("SELECT d FROM Detaillenewfacture d  WHERE d.idNewfacture.idNewfacture = :param");
        query.setParameter("param", newfacture.getIdNewfacture());
        List<Detaillenewfacture> lesfactures = query.getResultList();
        return lesfactures;
    }

    public void deleteByDevis(int idfacture) {
        Query query = em.createNativeQuery("DELETE FROM detailleNewfacture WHERE idNewfacture=?");
        query.setParameter(1, idfacture);
        query.executeUpdate();
    }
    
}
