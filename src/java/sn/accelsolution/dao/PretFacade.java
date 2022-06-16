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
import sn.accelsolution.entities.Pret;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DV7
 */
@Stateless
public class PretFacade extends AbstractFacade<Pret> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PretFacade() {
        super(Pret.class);
    } 
    
    public void insertPret(Pret pret) {
        UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO pret (datePret,motifPret, montantPret, montanAp, montantR, suivi, perioderestante, etatPret, cloture, premierpayement, idUtilisateur) "
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?)");
        query.setParameter(1, pret.getDatePret());
        query.setParameter(2, utfconvert.convertFromUTF8(pret.getMotifPret()));
        query.setParameter(3, pret.getMontantPret());
        query.setParameter(4, pret.getMontanAp());
        query.setParameter(5, pret.getMontantR());
        query.setParameter(6, pret.getSuivi());
        query.setParameter(7, pret.getPerioderestante());
        query.setParameter(8, utfconvert.convertFromUTF8(pret.getEtatPret()));
        query.setParameter(9, pret.getCloture());
        query.setParameter(10, pret.getPremierpayement());
        query.setParameter(11, pret.getIdUtilisateur().getIdUtilisateur());
        query.executeUpdate();
    }
    
    
    public List<Pret> listOfPretNonPayer() {
        Query query = getEntityManager().createQuery("SELECT p FROM Pret p WHERE p.etatPret =:parametre1 AND p.cloture =:parametre2");
        query.setParameter("parametre1", "Perçu");
        query.setParameter("parametre2", "Non");
        List<Pret> lesprets = query.getResultList();
        
        return lesprets;
    }
    
}
