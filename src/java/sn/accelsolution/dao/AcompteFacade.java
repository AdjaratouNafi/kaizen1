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
import sn.accelsolution.entities.Acompte;
import sn.accelsolution.entities.Chantier;
import sn.accelsolution.entities.Prestataire;

/**
 *
 * @author DV7
 */
@Stateless
public class AcompteFacade extends AbstractFacade<Acompte> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AcompteFacade() {
        super(Acompte.class);
    }

    public void insertAcompte(Acompte acompte) {
        Query query = em.createNativeQuery("INSERT INTO acompte (echeanceAcompte, montantAcompte, dateAcompte, numerocheque,choixtva,idPrestataire,numeroacompte) "
                + " VALUES(?,?,?,?,?,?,?)");
        query.setParameter(1, acompte.getEcheanceAcompte());
        query.setParameter(2, acompte.getMontantAcompte());
        query.setParameter(3, acompte.getDateAcompte());
        query.setParameter(4, acompte.getNumerocheque());
        query.setParameter(5, acompte.getChoixtva());
        query.setParameter(6, acompte.getIdPrestataire().getIdPrestataire());
        query.setParameter(7, acompte.getNumeroacompte());
        query.executeUpdate();
    }

    public List<Acompte> listOfAcompteByPrestataire(Prestataire prestataire) {

        Query query = getEntityManager().createQuery("SELECT a FROM Acompte a  WHERE a.idPrestataire.idPrestataire = :param");
        query.setParameter("param", prestataire.getIdPrestataire());
        List<Acompte> lesAcomptes = query.getResultList();
        return lesAcomptes;
    }

    public List<Acompte> listOfAcompteByChantier(Chantier chantier) {

        Query query = getEntityManager().createQuery("SELECT a FROM Acompte a  WHERE a.idChantier.idChantier = :param");
        query.setParameter("param", chantier.getIdChantier());
        List<Acompte> lesAcomptes = query.getResultList();
        return lesAcomptes;
    }

    public int maxAcompte() {
        int rs = 0;
        Query query = getEntityManager().createQuery("SELECT MAX(a.idAcompte) FROM Acompte a");
        rs = (int) query.getSingleResult();

        return rs;

    }

    public int maxAcompteByPrestataire(Prestataire prestataire) {
        int rs = 0;
        Query query = getEntityManager().createQuery("SELECT MAX(a.idAcompte) FROM Acompte a WHERE a.idPrestataire.idPrestataire = :param");
        query.setParameter("param", prestataire.getIdPrestataire());
        rs = (int) query.getSingleResult();
        return rs;
    }

}
