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
import sn.accelsolution.entities.Decompte2;
import sn.accelsolution.entities.Detailledecompte2;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DV7
 */
@Stateless
public class Detailledecompte2Facade extends AbstractFacade<Detailledecompte2> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Detailledecompte2Facade() {
        super(Detailledecompte2.class);
    } 
    
    public void insertDetailleDecompte2(Detailledecompte2 detailleDecompte2) {
        UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO detailledecompte2 (designation,quantite,pu, execution, idDecompte2) "
                + " VALUES(?,?,?,?,?)");
        query.setParameter(1, utfconvert.convertFromUTF8(detailleDecompte2.getDesignation()));
        query.setParameter(2, detailleDecompte2.getQuantite());
        query.setParameter(3, detailleDecompte2.getPu());
        query.setParameter(4, detailleDecompte2.getExecution());
        query.setParameter(5, detailleDecompte2.getIdDecompte2().getIdDecompte2());
        query.executeUpdate();
    }

    public List<Detailledecompte2> listOfDetailleByDecompte(Decompte2 decompte2) {

        Query query = getEntityManager().createQuery("SELECT d FROM Detailledecompte2 d  WHERE d.idDecompte2.idDecompte2 = :param");
        query.setParameter("param", decompte2.getIdDecompte2());
        List<Detailledecompte2> lesdecomptes = query.getResultList();
        return lesdecomptes;
    }

    public void deleteByDecompte(int idDecompte) {
        Query query = em.createNativeQuery("DELETE FROM detailleDecompte2 WHERE idDecompte2=?");
        query.setParameter(1, idDecompte);
        query.executeUpdate();
    }
    
}
