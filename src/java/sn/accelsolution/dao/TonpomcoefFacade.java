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
import sn.accelsolution.entities.Tonpomcoef;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DV7
 */
@Stateless
public class TonpomcoefFacade extends AbstractFacade<Tonpomcoef> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TonpomcoefFacade() {
        super(Tonpomcoef.class);
    } 
    
    public void insertTonpom(Tonpomcoef tonpomcoef) {
        UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO tonpomcoef (numerodevis,designation,typeitem,ref,unite,quantite,pu,passage,typepassage) "
                + " VALUES(?,?,?,?,?,?,?,?,?)");
        query.setParameter(1, tonpomcoef.getNumerodevis());
        //query.setParameter(2, utfconvert.convertFromUTF8(tonpomcoef.getDesignation()));
        query.setParameter(2, tonpomcoef.getDesignation());
        query.setParameter(3, tonpomcoef.getTypeitem());
        query.setParameter(4, utfconvert.convertFromUTF8(tonpomcoef.getRef()));
        query.setParameter(5, utfconvert.convertFromUTF8(tonpomcoef.getUnite()));
        query.setParameter(6, tonpomcoef.getQuantite());
        query.setParameter(7, tonpomcoef.getPu());
        query.setParameter(8, tonpomcoef.getPassage());
        query.setParameter(9, tonpomcoef.getTypepassage());
        query.executeUpdate();
    }

    public List<Tonpomcoef> listTonpom(String numerodevis) {
        String param2 = "Creation";
        Query query = getEntityManager().createQuery("SELECT t FROM Tonpomcoef t WHERE t.numerodevis =:param1 AND t.typepassage =:param2");
        query.setParameter("param1", numerodevis);
        query.setParameter("param2", param2);
        List<Tonpomcoef> leusers = query.getResultList();
        if (leusers.size() > 0) {
            return leusers;
        }
        return null;

    }

    public Boolean verifierTonpom(String numerodevis, int passage) {
        boolean result = false;
        String param3 = "Creation";
        Query query = getEntityManager().createQuery("SELECT COUNT(t.idTonpomcoef) FROM Tonpomcoef t WHERE t.numerodevis =:param1 AND t.passage =:param2 AND t.typepassage=:param3");
        query.setParameter("param1", numerodevis);
        query.setParameter("param2", passage);
        query.setParameter("param3", param3);
        long nbElement = (Long) query.getSingleResult();
        if (nbElement > 0) {
            result = true;
            return result;
        }
        return result;

    }

    public Boolean verifierTonpom2(String numerodevis, int passage) {
        boolean result = false;
        String param2 = "Modification";
        Query query = getEntityManager().createQuery("SELECT COUNT(t.idTonpomcoef) FROM Tonpomcoef t WHERE t.numerodevis =:param1 AND t.typepassage =:param2 AND t.passage=:param3");
        query.setParameter("param1", numerodevis);
        query.setParameter("param2", param2);
        query.setParameter("param3", passage);
        long nbElement = (Long) query.getSingleResult();
        if (nbElement > 0) {
            result = true;
            return result;
        }
        return result;

    }

    public Tonpomcoef getTonpomCreation(String numerodevis) {
        String param2 = "Creation";
        Query query = getEntityManager().createQuery("SELECT t FROM Tonpomcoef t WHERE t.numerodevis =:param1 AND t.typepassage =:param2");
        query.setParameter("param1", numerodevis);
        query.setParameter("param2", param2);
        List<Tonpomcoef> leusers = query.getResultList();
        if (leusers.size() > 0) {
            return leusers.get(0);
        }
        return null;

    } 
    
    public Tonpomcoef getTonpomCreationByNumAndDesignation(String numerodevis, String designation) {
        String param3 = "Creation";
        Query query = getEntityManager().createQuery("SELECT t FROM Tonpomcoef t WHERE t.numerodevis =:param1 AND t.designation =:param2 AND t.typepassage =:param3");
        query.setParameter("param1", numerodevis);
        query.setParameter("param2", designation);
        query.setParameter("param3", param3);
        List<Tonpomcoef> leusers = query.getResultList();
        if (leusers.size() > 0) {
            return leusers.get(0);
        }
        return null;

    }
    
    
    public Tonpomcoef getTonpomCreationByNum(String numerodevis) {
        String param3 = "Creation";
        Query query = getEntityManager().createQuery("SELECT t FROM Tonpomcoef t WHERE t.numerodevis =:param1 AND t.typepassage =:param3");
        query.setParameter("param1", numerodevis);
        query.setParameter("param3", param3);
        List<Tonpomcoef> leusers = query.getResultList();
        if (leusers.size() > 0) {
            return leusers.get(0);
        }
        return null;

    }

    public Tonpomcoef getTonpomModification(String numerodevis) {
        String param2 = "Modification";
        Query query = getEntityManager().createQuery("SELECT t FROM Tonpomcoef t WHERE t.numerodevis =:param1 AND t.typepassage =:param2");
        query.setParameter("param1", numerodevis);
        query.setParameter("param2", param2);
        List<Tonpomcoef> leusers = query.getResultList();
        if (leusers.size() > 0) {
            return leusers.get(0);
        }
        return null;

    } 
    
    public Tonpomcoef getTonpomModificationFromDelete(String numerodevis, int passage) {
        String param3 = "Modification";
        Query query = getEntityManager().createQuery("SELECT t FROM Tonpomcoef t WHERE t.numerodevis =:param1 AND t.passage =:param2 AND t.typepassage =:param3");
        query.setParameter("param1", numerodevis);
        query.setParameter("param2", passage);
        query.setParameter("param3", param3);
        List<Tonpomcoef> leusers = query.getResultList();
        if (leusers.size() > 0) {
            return leusers.get(0);
        }
        return null;

    } 
    
    public Tonpomcoef getTonpomCreationFromDelete(String numerodevis, int passage) {
        String param3 = "Creation";
        Query query = getEntityManager().createQuery("SELECT t FROM Tonpomcoef t WHERE t.numerodevis =:param1 AND t.passage =:param2 AND t.typepassage =:param3");
        query.setParameter("param1", numerodevis);
        query.setParameter("param2", passage);
        query.setParameter("param3", param3);
        List<Tonpomcoef> leusers = query.getResultList();
        if (leusers.size() > 0) {
            return leusers.get(0);
        }
        return null;

    }

    public List<Tonpomcoef> listTonpomModif(String numerodevis, int passage) {
        String parm3 = "Modification";
        Query query = getEntityManager().createQuery("SELECT t FROM Tonpomcoef t WHERE t.numerodevis =:param1 AND t.passage =:param2 AND t.typepassage=:param3");
        query.setParameter("param1", numerodevis);
        query.setParameter("param2", passage);
        query.setParameter("param3", parm3);
        List<Tonpomcoef> leusers = query.getResultList();
        if (leusers.size() > 0) {
            return leusers;
        }
        return null;

    } 
    
    public List<Tonpomcoef> listTonpomModifWithoutPassage(String numerodevis) {
        String parm2 = "Modification";
        Query query = getEntityManager().createQuery("SELECT t FROM Tonpomcoef t WHERE t.numerodevis =:param1 AND t.typepassage=:param2");
        query.setParameter("param1", numerodevis);
        query.setParameter("param2", parm2);
        List<Tonpomcoef> leusers = query.getResultList();
        if (leusers.size() > 0) {
            return leusers;
        }
        return null;

    }

    public void deleteByNumeDevis(String numerodevis) {
        Query query = em.createNativeQuery("DELETE FROM Tonpomcoef WHERE numerodevis=?");
        query.setParameter(1, numerodevis);
        query.executeUpdate();
    }

    public void deleteExistingTinponByNumeDevis(String numerodevis, int passage) {
        Query query = em.createNativeQuery("DELETE FROM tonpomcoef WHERE numerodevis=? AND passage=? AND typepassage=?");
        query.setParameter(1, numerodevis);
        query.setParameter(2, passage);
        query.setParameter(3, "Modification");
        query.executeUpdate();
    } 
    
    public void deleteExistingTinponByNumeDevis2(String numerodevis, int passage) {
        Query query = em.createNativeQuery("DELETE FROM tonpomcoef WHERE numerodevis=? AND passage=? AND typepassage=?");
        query.setParameter(1, numerodevis);
        query.setParameter(2, passage);
        query.setParameter(3, "Creation");
        query.executeUpdate();
    }

    public void deleteByNumeDevisModif(String numerodevis) {
        Query query = em.createNativeQuery("DELETE FROM Tonpomcoef WHERE numerodevis=? AND typepassage=?");
        query.setParameter(1, numerodevis);
        query.setParameter(2, "Modification");
        query.executeUpdate();
    }
    
}
