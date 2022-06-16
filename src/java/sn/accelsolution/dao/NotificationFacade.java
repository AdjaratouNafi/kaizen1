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
import sn.accelsolution.entities.Commande;
import sn.accelsolution.entities.Decomptep;
import sn.accelsolution.entities.Devis;
import sn.accelsolution.entities.Expressionbesoin;
import sn.accelsolution.entities.Newfacture;
import sn.accelsolution.entities.Notification;
import sn.accelsolution.entities.Salaire;

/**
 *
 * @author DV7
 */
@Stateless
public class NotificationFacade extends AbstractFacade<Notification> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NotificationFacade() {
        super(Notification.class);
    } 
    
    public void insertNotification(Notification notification) {
        //UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO notification (message,dateNotification,heure,minute,seconde,traitement,typeNotification,etatNotification,idUtilisateur,idExpression) "
                + " VALUES(?,?,?,?,?,?,?,?,?,?)");
        query.setParameter(1, notification.getMessage());
        query.setParameter(2, notification.getDateNotification());
        query.setParameter(3, notification.getHeure());
        query.setParameter(4, notification.getMinute());
        query.setParameter(5, notification.getSeconde());
        query.setParameter(6, notification.getTraitement());
        query.setParameter(7, notification.getTypeNotification());
        query.setParameter(8, notification.getEtatNotification());
        query.setParameter(9, notification.getIdUtilisateur().getIdUtilisateur());
        query.setParameter(10, notification.getIdExpression().getIdExpression());
        query.executeUpdate();
    }
    
    public void insertNotification2(Notification notification) {
        //UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO notification (message,dateNotification,heure,minute,seconde,traitement,typeNotification,etatNotification,idUtilisateur,idSalaire) "
                + " VALUES(?,?,?,?,?,?,?,?,?,?)");
        query.setParameter(1, notification.getMessage());
        query.setParameter(2, notification.getDateNotification());
        query.setParameter(3, notification.getHeure());
        query.setParameter(4, notification.getMinute());
        query.setParameter(5, notification.getSeconde());
        query.setParameter(6, notification.getTraitement());
        query.setParameter(7, notification.getTypeNotification());
        query.setParameter(8, notification.getEtatNotification());
        query.setParameter(9, notification.getIdUtilisateur().getIdUtilisateur());
        query.setParameter(10, notification.getIdSalaire().getIdSalaire());
        query.executeUpdate();
    }
    
    
    public void insertNotification3(Notification notification) {
        //UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO notification (message,dateNotification,heure,minute,seconde,traitement,typeNotification,etatNotification,idUtilisateur,idDevis) "
                + " VALUES(?,?,?,?,?,?,?,?,?,?)");
        query.setParameter(1, notification.getMessage());
        query.setParameter(2, notification.getDateNotification());
        query.setParameter(3, notification.getHeure());
        query.setParameter(4, notification.getMinute());
        query.setParameter(5, notification.getSeconde());
        query.setParameter(6, notification.getTraitement());
        query.setParameter(7, notification.getTypeNotification());
        query.setParameter(8, notification.getEtatNotification());
        query.setParameter(9, notification.getIdUtilisateur().getIdUtilisateur());
        query.setParameter(10, notification.getIdDevis().getIdDevis());
        query.executeUpdate();
    } 
    
    public void insertNotification4(Notification notification) {
        //UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO notification (message,dateNotification,heure,minute,seconde,traitement,typeNotification,etatNotification,idUtilisateur,idNewfacture) "
                + " VALUES(?,?,?,?,?,?,?,?,?,?)");
        query.setParameter(1, notification.getMessage());
        query.setParameter(2, notification.getDateNotification());
        query.setParameter(3, notification.getHeure());
        query.setParameter(4, notification.getMinute());
        query.setParameter(5, notification.getSeconde());
        query.setParameter(6, notification.getTraitement());
        query.setParameter(7, notification.getTypeNotification());
        query.setParameter(8, notification.getEtatNotification());
        query.setParameter(9, notification.getIdUtilisateur().getIdUtilisateur());
        query.setParameter(10, notification.getIdNewfacture().getIdNewfacture());
        query.executeUpdate();
    } 
    
    public void insertNotification5(Notification notification) {
        //UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO notification (message,dateNotification,heure,minute,seconde,traitement,typeNotification,etatNotification,idUtilisateur,idDecomptep) "
                + " VALUES(?,?,?,?,?,?,?,?,?,?)");
        query.setParameter(1, notification.getMessage());
        query.setParameter(2, notification.getDateNotification());
        query.setParameter(3, notification.getHeure());
        query.setParameter(4, notification.getMinute());
        query.setParameter(5, notification.getSeconde());
        query.setParameter(6, notification.getTraitement());
        query.setParameter(7, notification.getTypeNotification());
        query.setParameter(8, notification.getEtatNotification());
        query.setParameter(9, notification.getIdUtilisateur().getIdUtilisateur());
        query.setParameter(10, notification.getIdDecomptep().getIdDecomptep());
        query.executeUpdate();
    } 
    
    
    public void insertNotification6(Notification notification) {
        //UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO notification (message,dateNotification,heure,minute,seconde,traitement,typeNotification,etatNotification,idUtilisateur,idCommande) "
                + " VALUES(?,?,?,?,?,?,?,?,?,?)");
        query.setParameter(1, notification.getMessage());
        query.setParameter(2, notification.getDateNotification());
        query.setParameter(3, notification.getHeure());
        query.setParameter(4, notification.getMinute());
        query.setParameter(5, notification.getSeconde());
        query.setParameter(6, notification.getTraitement());
        query.setParameter(7, notification.getTypeNotification());
        query.setParameter(8, notification.getEtatNotification());
        query.setParameter(9, notification.getIdUtilisateur().getIdUtilisateur());
        query.setParameter(10, notification.getIdCommande().getIdCommande());
        query.executeUpdate();
    }

    public int nbNotificationExpression() {
        int rs = 0;
        String typeNotification = "Expression besoin";
        Query query = getEntityManager().createQuery("SELECT COUNT(n.idNotification) FROM Notification n WHERE n.typeNotification =:param");
        query.setParameter("param", typeNotification);
        rs = (int) query.getSingleResult();
        return rs;
    }

    public List<Notification> getListOfNotificationExpressions() {  
        String traitement = "Non fait";
        String typeNotification = "Expression besoin";

        Query query = getEntityManager().createQuery("SELECT n FROM Notification n WHERE n.traitement =:param1 AND n.typeNotification =:param2");
        query.setParameter("param1", traitement);
        query.setParameter("param2", typeNotification);

        List<Notification> leusers = query.getResultList();
        if (leusers.size() > 0) {
            return leusers;
        }
        return null;

    } 
    
    
    public List<Notification> getListOfNotificationSalaire() {
        String traitement = "Non fait";
        String typeNotification = "Salaire";

        Query query = getEntityManager().createQuery("SELECT n FROM Notification n WHERE n.traitement =:param1 AND n.typeNotification =:param2");
        query.setParameter("param1", traitement);
        query.setParameter("param2", typeNotification);

        List<Notification> leusers = query.getResultList();
        if (leusers.size() > 0) {
            return leusers;
        }
        return null;

    } 
    
    
    public List<Notification> getListOfNotificationDevis() { 
        String traitement = "Non fait";
        String typeNotification = "Devis";

        Query query = getEntityManager().createQuery("SELECT n FROM Notification n WHERE n.traitement =:param1 AND n.typeNotification =:param2");
        query.setParameter("param1", traitement);
        query.setParameter("param2", typeNotification);

        List<Notification> leusers = query.getResultList();
        if (leusers.size() > 0) {
            return leusers;
        }
        return null;

    } 
    
    
    public List<Notification> listOfNotificationByDevis(Devis devis) { 
        
        Query query = getEntityManager().createQuery("SELECT n FROM Notification n WHERE n.idDevis.idDevis =:param1");
        query.setParameter("param1", devis.getIdDevis());

        List<Notification> leusers = query.getResultList();
        if (leusers.size() > 0) {
            return leusers;
        }
        return null;

    } 
    
    public List<Notification> getListOfNotificationfacture() { 
        String traitement = "Non fait";
        String typeNotification = "Facture";

        Query query = getEntityManager().createQuery("SELECT n FROM Notification n WHERE n.traitement =:param1 AND n.typeNotification =:param2");
        query.setParameter("param1", traitement);
        query.setParameter("param2", typeNotification);

        List<Notification> leusers = query.getResultList();
        if (leusers.size() > 0) {
            return leusers;
        }
        return null;

    }  
    
    public List<Notification> getListOfNotificationDecompte() { 
        String traitement = "Non fait";
        String typeNotification = "Decompte";

        Query query = getEntityManager().createQuery("SELECT n FROM Notification n WHERE n.traitement =:param1 AND n.typeNotification =:param2");
        query.setParameter("param1", traitement);
        query.setParameter("param2", typeNotification);

        List<Notification> leusers = query.getResultList();
        if (leusers.size() > 0) {
            return leusers;
        }
        return null;

    } 
    
    public List<Notification> getListOfNotificationCommande() { 
        String traitement = "Non fait";
        String typeNotification = "Commande";

        Query query = getEntityManager().createQuery("SELECT n FROM Notification n WHERE n.traitement =:param1 AND n.typeNotification =:param2");
        query.setParameter("param1", traitement);
        query.setParameter("param2", typeNotification);

        List<Notification> leusers = query.getResultList();
        if (leusers.size() > 0) {
            return leusers;
        }
        return null;

    } 
    
    
    public Notification getNotificationByExpression(Expressionbesoin expressionbesoin) {
        String traitement = "Non fait";
        Query query = getEntityManager().createQuery("SELECT n FROM Notification n WHERE n.traitement =:param1 AND n.idExpression.idExpression =:param2");
        query.setParameter("param1", traitement);
        query.setParameter("param2", expressionbesoin.getIdExpression());
        List<Notification> leusers = query.getResultList();
        if (leusers.size() > 0) {
            return leusers.get(0);
        }
        return null;

    } 
    
    
    public Notification getNotificationBySalaire(Salaire salaire) {
        String traitement = "Non fait";
        Query query = getEntityManager().createQuery("SELECT n FROM Notification n WHERE n.traitement =:param1 AND n.idSalaire.idSalaire =:param2");
        query.setParameter("param1", traitement);
        query.setParameter("param2", salaire.getIdSalaire());
        List<Notification> leusers = query.getResultList();
        if (leusers.size() > 0) {
            return leusers.get(0);
        }
        return null;

    } 
    
    public Notification getNotificationByDevis(Devis devis) {
        String traitement = "Non fait";
        Query query = getEntityManager().createQuery("SELECT n FROM Notification n WHERE n.traitement =:param1 AND n.idDevis.idDevis =:param2");
        query.setParameter("param1", traitement);
        query.setParameter("param2", devis.getIdDevis());
        List<Notification> leusers = query.getResultList();
        if (leusers.size() > 0) {
            return leusers.get(0);
        }
        return null;

    } 
    
    public Notification getNotificationByfacture(Newfacture newfacture) {
        String traitement = "Non fait";
        Query query = getEntityManager().createQuery("SELECT n FROM Notification n WHERE n.traitement =:param1 AND n.idNewfacture.idNewfacture =:param2");
        query.setParameter("param1", traitement);
        query.setParameter("param2", newfacture.getIdNewfacture());
        List<Notification> leusers = query.getResultList();
        if (leusers.size() > 0) {
            return leusers.get(0);
        }
        return null;

    }
    
    
    public Notification getNotificationByDecompte(Decomptep decomptep) {
        String traitement = "Non fait";
        Query query = getEntityManager().createQuery("SELECT n FROM Notification n WHERE n.traitement =:param1 AND n.idDecomptep.idDecomptep =:param2");
        query.setParameter("param1", traitement);
        query.setParameter("param2", decomptep.getIdDecomptep());
        List<Notification> leusers = query.getResultList();
        if (leusers.size() > 0) {
            return leusers.get(0);
        }
        return null;

    } 
    
    
    public void deleteByCommande(int idCommande) {
        Query query = em.createNativeQuery("DELETE FROM notification WHERE idCommande=?");
        query.setParameter(1, idCommande);
        query.executeUpdate();
    } 
    
    public Notification getNotificationByCommande(Commande commande) {
        String traitement = "Non fait";
        Query query = getEntityManager().createQuery("SELECT n FROM Notification n WHERE n.traitement =:param1 AND n.idCommande.idCommande =:param2");
        query.setParameter("param1", traitement);
        query.setParameter("param2", commande.getIdCommande());
        List<Notification> leusers = query.getResultList();
        if (leusers.size() > 0) {
            return leusers.get(0);
        }
        return null;

    } 
    
}
