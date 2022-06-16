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
import sn.accelsolution.entities.Marche;
import sn.accelsolution.entities.Newfacture;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DV7
 */
@Stateless
public class NewfactureFacade extends AbstractFacade<Newfacture> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NewfactureFacade() {
        super(Newfacture.class);
    } 
    
    public void insertfacture(Newfacture newfacture) {
        UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO newfacture (numeroNewfacture,dateNewfacture,mintantlettreNewfacture,etat,idDevis,idMarche) "
                + " VALUES(?,?,?,?,?,?)");
        query.setParameter(1, newfacture.getNumeroNewfacture());
        query.setParameter(2, newfacture.getDateNewfacture());
        query.setParameter(3, newfacture.getMintantlettreNewfacture());
        query.setParameter(4, utfconvert.convertFromUTF8(newfacture.getEtat()));
        query.setParameter(5, newfacture.getIdDevis().getIdDevis());
        query.setParameter(6, newfacture.getIdMarche().getIdMarche());
        query.executeUpdate();
    }

    public int maxDevis() {
        int rs = 0;
        Query query = getEntityManager().createQuery("SELECT MAX(f.idNewfacture) FROM Newfacture f");
        rs = (int) query.getSingleResult();
        return rs;
    } 
    
    public Newfacture getFactureByMarcher(Marche marche) {

        Query query = getEntityManager().createQuery("SELECT f FROM Newfacture f WHERE f.idMarche.idMarche =:marche");
        query.setParameter("marche", marche.getIdMarche());
        List<Newfacture> leusers = query.getResultList();
        if (leusers.size() > 0) {
            return leusers.get(0);
        }
        return null;

    }
    
}
