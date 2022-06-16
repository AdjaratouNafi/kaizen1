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
import sn.accelsolution.entities.Management;
import sn.accelsolution.entities.Marche;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DV7
 */
@Stateless
public class ManagementFacade extends AbstractFacade<Management> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ManagementFacade() {
        super(Management.class);
    } 
    
    public void insertManagement(Management management, String typeCreation) {

         UtilUtfconvert utfconvert = new UtilUtfconvert();
         
        if (typeCreation.equals("Prestataire")) {

            Query query = em.createNativeQuery("INSERT INTO management (tache,duree,datedebut,datefin,predecesseur,terminer,etat,typeTache,idprestatairePrim,idMarche,idChantier,commentaire,couleur) "
                    + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            query.setParameter(1, utfconvert.convertFromUTF8(management.getTache()));
            query.setParameter(2, management.getDuree());
            query.setParameter(3, management.getDatedebut());
            query.setParameter(4, management.getDatefin());
            query.setParameter(5, management.getPredecesseur());
            query.setParameter(6, management.getTerminer());
            query.setParameter(7, management.getEtat());
            query.setParameter(8, typeCreation);
            query.setParameter(9, management.getIdprestatairePrim().getIdprestatairePrim());
            query.setParameter(10, management.getIdMarche().getIdMarche());
            query.setParameter(11, management.getIdChantier().getIdChantier());
            query.setParameter(12, management.getCommentaire());
            query.setParameter(13, management.getCouleur());
            query.executeUpdate();

        } else {

            Query query = em.createNativeQuery("INSERT INTO management (tache,duree,datedebut,datefin,predecesseur,terminer,etat,typeTache,idMarche,idChantier,idUtilisateur,commentaire,couleur) "
                    + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            query.setParameter(1, utfconvert.convertFromUTF8(management.getTache()));
            query.setParameter(2, management.getDuree());
            query.setParameter(3, management.getDatedebut());
            query.setParameter(4, management.getDatefin());
            query.setParameter(5, management.getPredecesseur());
            query.setParameter(6, management.getTerminer());
            query.setParameter(7, management.getEtat());
            query.setParameter(8, typeCreation);
            query.setParameter(9, management.getIdMarche().getIdMarche());
            query.setParameter(10, management.getIdChantier().getIdChantier());
            query.setParameter(11, management.getIdUtilisateur().getIdUtilisateur());
            query.setParameter(12, management.getCommentaire());
            query.setParameter(13, management.getCouleur());
            query.executeUpdate();

        }

    }

    public List<Management> getManagementByMarche(Marche marche) {

        Query query = getEntityManager().createQuery("SELECT m FROM Management m WHERE m.idMarche.idMarche =:param");
        query.setParameter("param", marche.getIdMarche());
        List<Management> leusers = query.getResultList(); 
        if (leusers.size() > 0) {
            return leusers;
        }
        return null;

    } 
    
    
    public List<Management> listOfManagementByProjet(Marche marche) {

        Query query = getEntityManager().createQuery("SELECT m FROM Management m  WHERE m.idMarche.idMarche=:param");
        query.setParameter("param", marche.getIdMarche());
        List<Management> lesManagements = query.getResultList();
        return lesManagements;
    }
    
}
