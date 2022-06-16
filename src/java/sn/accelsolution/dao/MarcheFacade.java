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
import sn.accelsolution.entities.Devis;
import sn.accelsolution.entities.Marche;
import sn.accelsolution.entities.Newfacture;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DV7
 */
@Stateless
public class MarcheFacade extends AbstractFacade<Marche> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MarcheFacade() {
        super(Marche.class);
    } 
    
    public void insertMarche(Marche marche) {
        UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO marche (nomMarche, montantExecution, objetMarche,numeroFinancement, dateMarche, montantDemarrage, montantMarche, dateAvenant, avenant,dureeContrat,dateDebut,dateFin,observation,notification,cotion,montantcotion,primcaution,mainlevee,ordreservice,idDevis,idDevise) "
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        query.setParameter(1, marche.getNomMarche());
        query.setParameter(2, marche.getMontantExecution());
        if (marche.getObjetMarche() == null || marche.getObjetMarche().equals("") || marche.getObjetMarche().length() == 0) {
            query.setParameter(3, marche.getObjetMarche());
        } else {
            query.setParameter(3, marche.getObjetMarche());
        }
        
        query.setParameter(4, marche.getNumeroFinancement());
        query.setParameter(5, marche.getDateMarche());
        query.setParameter(6, marche.getMontantDemarrage());
        query.setParameter(7, marche.getMontantMarche());
        query.setParameter(8, marche.getDateAvenant());
        query.setParameter(9, marche.getAvenant());
        query.setParameter(10, marche.getDureeContrat());
        query.setParameter(11, marche.getDateDebut());
        query.setParameter(12, marche.getDateFin());
        
        if (marche.getObservation() == null || marche.getObservation().equals("") || marche.getObservation().length() == 0) {
            query.setParameter(13, marche.getObservation());
        } else {
            query.setParameter(13, marche.getObservation());
        }

        query.setParameter(14, marche.getNotification());
        query.setParameter(15, marche.getCotion());
        query.setParameter(16, marche.getMontantcotion());
        query.setParameter(17, marche.getPrimcaution());
        query.setParameter(18, marche.getMainlevee());
        query.setParameter(19, marche.getOrdreservice());
        query.setParameter(20, marche.getIdDevis().getIdDevis());
        query.setParameter(21, marche.getIdDevise().getIdDevise());
        query.executeUpdate();
    }  
    
    
   /* public void insertMarche(Marche marche) {
        Query query = em.createNativeQuery("INSERT INTO marche (nomMarche, montantExecution, objetMarche,numeroFinancement, dateMarche, montantDemarrage, montantMarche, dateAvenant, avenant,dureeContrat,dateDebut,dateFin,observation,notification,cotion,montantcotion,primcaution,mainlevee,ordreservice,idDevis,idDevise) "
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        query.setParameter(1, marche.getNomMarche());
        query.setParameter(2, marche.getMontantExecution());
        query.setParameter(3, marche.getObjetMarche());
        query.setParameter(4, marche.getNumeroFinancement());
        query.setParameter(5, marche.getDateMarche());
        query.setParameter(6, marche.getMontantDemarrage());
        query.setParameter(7, marche.getMontantMarche());
        query.setParameter(8, marche.getDateAvenant());
        query.setParameter(9, marche.getAvenant());
        query.setParameter(10, marche.getDureeContrat());
        query.setParameter(11, marche.getDateDebut());
        query.setParameter(12, marche.getDateFin());
        query.setParameter(13, marche.getObservation());
        query.setParameter(14, marche.getNotification());
        query.setParameter(15, marche.getCotion());
        query.setParameter(16, marche.getMontantcotion());
        query.setParameter(17, marche.getPrimcaution());
        query.setParameter(18, marche.getMainlevee());
        query.setParameter(19, marche.getOrdreservice());
        query.setParameter(20, marche.getIdDevis().getIdDevis());
        query.setParameter(21, marche.getIdDevise().getIdDevise());
        query.executeUpdate();
    } */

    public Marche marcheByFacture(Newfacture newfacture) {

        Query query = getEntityManager().createQuery("SELECT m FROM Marche m WHERE m.idNewfacture.idNewfacture=:param");
        query.setParameter("param", newfacture.getIdNewfacture());
        List<Marche> leusers = query.getResultList();
        if (leusers.size() > 0) {
            return leusers.get(0);
        }
        return null;

    }

    public Marche marcheByDevis(Devis devis) {

        Query query = getEntityManager().createQuery("SELECT m FROM Marche m WHERE m.idDevis.idDevis=:param");
        query.setParameter("param", devis.getIdDevis());
        List<Marche> leusers = query.getResultList();
        if (leusers.size() > 0) {
            return leusers.get(0);
        }
        return null;

    }
    
}
