/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sn.accelsolution.entities.Acomptefournisseur;
import sn.accelsolution.entities.Fournisseur;

/**
 *
 * @author DV7
 */
@Stateless
public class AcomptefournisseurFacade extends AbstractFacade<Acomptefournisseur> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AcomptefournisseurFacade() {
        super(Acomptefournisseur.class);
    } 
    
    public void insertAcompte(Acomptefournisseur acompteFournisseur) {
        Query query = em.createNativeQuery("INSERT INTO acomptefournisseur (echeanceAcompte, montantAcompte, dateAcompte, numerocheque,choixtva,idFournisseur,idCommande,idReffournisseur,numeroacomptef) "
                + " VALUES(?,?,?,?,?,?,?,?,?)");
        query.setParameter(1, acompteFournisseur.getEcheanceAcompte());
        query.setParameter(2, acompteFournisseur.getMontantAcompte());
        query.setParameter(3, acompteFournisseur.getDateAcompte());
        query.setParameter(4, acompteFournisseur.getNumerocheque());
        query.setParameter(5, acompteFournisseur.getChoixtva());
        query.setParameter(6, acompteFournisseur.getIdFournisseur().getIdFournisseur());
        query.setParameter(7, acompteFournisseur.getIdCommande().getIdCommande());
        query.setParameter(8, acompteFournisseur.getIdReffournisseur().getIdReffournisseur()); 
        query.setParameter(9, acompteFournisseur.getNumeroacomptef());
        query.executeUpdate();
    } 
    
    public int maxAcompte() {
        int rs = 0;
        Query query = getEntityManager().createQuery("SELECT MAX(a.idAcomptefournisseur) FROM AcompteFournisseur a");
        rs = (int) query.getSingleResult();

        return rs;

    }

    public int maxAcompteByFournisseur(Fournisseur fournisseur) {
        int rs = 0;
        Query query = getEntityManager().createQuery("SELECT MAX(a.idAcomptefournisseur) FROM AcompteFournisseur a WHERE a.idFournisseur.idFournisseur = :param");
        query.setParameter("param", fournisseur.getIdFournisseur());
        rs = (int) query.getSingleResult();
        return rs;
    }
    
}
