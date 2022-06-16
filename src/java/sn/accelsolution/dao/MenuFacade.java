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
import sn.accelsolution.entities.Droitacces;
import sn.accelsolution.entities.Menu;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DV7
 */
@Stateless
public class MenuFacade extends AbstractFacade<Menu> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MenuFacade() {
        super(Menu.class);
    } 
    
    public void insertMenu(Menu menu) {
         UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO menu (libelemenu,idPrivilege) "
                + " VALUES(?,?)");
        query.setParameter(1, utfconvert.convertFromUTF8(menu.getLibelemenu()));
        query.setParameter(2, menu.getIdPrivilege().getIdPrivilege());
        query.executeUpdate();
    }

    public int maxMenu() {
        int rs = 0;
        Query query = getEntityManager().createQuery("SELECT MAX(m.idMenu) FROM Menu m");
        rs = (int) query.getSingleResult();
        return rs;
    }

    public List<Menu> listOfMenuByModule(Droitacces droitacces) {

        Query query = getEntityManager().createQuery("SELECT m FROM Menu m  WHERE m.idPrivilege.idPrivilege = :param");
        query.setParameter("param", droitacces.getIdPrivilege());
        List<Menu> lesfactures = query.getResultList();
        return lesfactures;
    }

    public Menu getMenuByDroit(Droitacces droitacces) {

        Query query = getEntityManager().createQuery("SELECT m FROM Menu m  WHERE m.idPrivilege.idPrivilege = :param");
        query.setParameter("param", droitacces.getIdPrivilege());
        List<Menu> lesfactures = query.getResultList();
        if (lesfactures.size() > 0) {
            return lesfactures.get(0);
        }
        return null;
    }

    public List<Menu> listOfMenuByDroitAcces(Droitacces droitacces) {
        Query query = getEntityManager().createQuery("SELECT m FROM Menu m  WHERE m.idPrivilege.idPrivilege = :param");
        query.setParameter("param", droitacces.getIdPrivilege());
        List<Menu> lesusers = query.getResultList();
        return lesusers;
    } 
    
    public void deleteMenu(Menu menu) {
        Query query = em.createNativeQuery("DELETE FROM menu WHERE idMenu=:param");
        query.setParameter("param", menu.getIdMenu());
        query.executeUpdate();
    }
    
}
