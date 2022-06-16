/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.dao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sn.accelsolution.entities.Fournisseur;
import sn.accelsolution.entities.Marchandise;
import sn.accelsolution.entities.Prix;
import sn.accelsolution.util.MontantConverter;
import sn.accelsolution.util.UtilPrix;

/**
 *
 * @author DV7
 */
@Stateless
public class PrixFacade extends AbstractFacade<Prix> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrixFacade() {
        super(Prix.class);
    } 
    
    public void insertPrix(Prix prix) {
        Query query = em.createNativeQuery("INSERT INTO prix (idMarchandise,idFournisseur,prix) "
                + " VALUES(?,?,?)");
        query.setParameter(1, prix.getIdMarchandise().getIdMarchandise());
        query.setParameter(2, prix.getIdFournisseur().getIdFournisseur());
        query.setParameter(3, prix.getPrix());
        query.executeUpdate();
    }

    public List<Prix> listOfPrixByMarchandise(Marchandise marchandise) {

        Query query = getEntityManager().createQuery("SELECT p FROM Prix p  WHERE p.idMarchandise.idMarchandise = :param");
        query.setParameter("param", marchandise.getIdMarchandise());
        List<Prix> lesprix = query.getResultList();
        return lesprix;
    }

    public Prix prixByProdAndFsse(Marchandise marchandise, Fournisseur fournisseur) {

        Query query = getEntityManager().createQuery("SELECT p FROM Prix p WHERE p.idMarchandise.idMarchandise=:march AND p.idFournisseur.idFournisseur=:four");
        query.setParameter("march", marchandise.getIdMarchandise());
        query.setParameter("four", fournisseur.getIdFournisseur());
        List<Prix> leusers = query.getResultList();
        if (leusers.size() > 0) {
            return leusers.get(0);
        }
        return null;

    }

    public List<UtilPrix> prixByMarchandise(String libelleMarchandise) {
        MontantConverter mttc = new MontantConverter();
        List<UtilPrix> allPrix = new ArrayList<>();

        Query query = getEntityManager().createQuery("SELECT p FROM Prix p WHERE p.idMarchandise.libelle=:param");
        query.setParameter("param", libelleMarchandise);
        List<Prix> lesprix = query.getResultList();

        if (lesprix.size() > 0) {
            for (Prix p : lesprix) {
                UtilPrix utilPrix = new UtilPrix();
                utilPrix.setIdPrix(p.getIdPrix());
                utilPrix.setIdMarchandise(p.getIdMarchandise());
                utilPrix.setIdFournisseur(p.getIdFournisseur());
                utilPrix.setPrix(p.getPrix());
                Double mt = mttc.StringToDouble(p.getPrix());
                utilPrix.setDoublePrix(mt);
                allPrix.add(utilPrix);
            } 
            
            for (int i=0; i<allPrix.size();i++) {
                
                for (int j=i+1; j<allPrix.size();j++) {
                    if(allPrix.get(i).getDoublePrix() > allPrix.get(j).getDoublePrix()){
                        allPrix.get(i).setDoublePrix(allPrix.get(j).getDoublePrix());
                        allPrix.get(j).setDoublePrix(allPrix.get(i).getDoublePrix());
                    }
                }
                
            }
            
            return allPrix;
        }

        return null;
    }

    public List<Prix> prixByMarchandise1(String libelleMarchandise) {

        Query query = getEntityManager().createQuery("SELECT p FROM Prix p WHERE p.idMarchandise.libelle=:param");
        query.setParameter("param", libelleMarchandise);
        List<Prix> lesprix = query.getResultList();
        if (lesprix.size() > 0) {
            return lesprix;
        }

        return null;
    }
    
}
