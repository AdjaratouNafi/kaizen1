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
import sn.accelsolution.entities.Actionmenu;
import sn.accelsolution.entities.Menu;

/**
 *
 * @author DV7
 */
@Stateless
public class ActionmenuFacade extends AbstractFacade<Actionmenu> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ActionmenuFacade() {
        super(Actionmenu.class);
    } 
    
    public void insertAction(Actionmenu actionmenu) {
        Query query = em.createNativeQuery("INSERT INTO actionmenu (libelleaction,idMenu) "
                + " VALUES(?,?)");
        query.setParameter(1, actionmenu.getLibelleaction());
        query.setParameter(2, actionmenu.getIdMenu().getIdMenu());
        query.executeUpdate();
    }

    public int maxMenu() {
        int rs = 0;
        Query query = getEntityManager().createQuery("SELECT MAX(m.idMenu) FROM Menu m");
        rs = (int) query.getSingleResult();
        return rs;
    }

    public List<Actionmenu> listOfMenuByModule(Menu menu) {

        Query query = getEntityManager().createQuery("SELECT a FROM Actionmenu a  WHERE a.idMenu.idMenu = :param");
        query.setParameter("param", menu.getIdMenu());
        List<Actionmenu> lesfactures = query.getResultList();
        return lesfactures;
    }

    
    public Actionmenu getActionByMenu(Menu menu) {

        Query query = getEntityManager().createQuery("SELECT a FROM Actionmenu a WHERE a.idMenu.idMenu = :param");
        query.setParameter("param", 2);
        List<Actionmenu> lesfactures = query.getResultList();
         if (lesfactures.size() > 0) {
            return lesfactures.get(0);
        }
        return null;
    } 
    
    public List<Actionmenu> listOfActionByMenu(Menu menu) {

        Query query = getEntityManager().createQuery("SELECT a FROM Actionmenu a  WHERE a.idMenu.idMenu = :param");
        query.setParameter("param", menu.getIdMenu());
        List<Actionmenu> lesfactures = query.getResultList();
        return lesfactures;
    } 
    
    public void deleteAction(Actionmenu actionmenu) {
        Query query = em.createNativeQuery("DELETE FROM actionmenu WHERE idActionmenu=:param");
        query.setParameter("param", actionmenu.getIdActionmenu());
        query.executeUpdate();
    }
    
}
