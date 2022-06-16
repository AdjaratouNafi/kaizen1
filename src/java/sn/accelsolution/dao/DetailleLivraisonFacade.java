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
import sn.accelsolution.entities.Chantier;
import sn.accelsolution.entities.Commande;
import sn.accelsolution.entities.DetailleLivraison;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DV7
 */
@Stateless
public class DetailleLivraisonFacade extends AbstractFacade<DetailleLivraison> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetailleLivraisonFacade() {
        super(DetailleLivraison.class);
    } 
    
    public void insertDetailleCommande(DetailleLivraison detailleLivraison) {
        UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO detaille_livraison (qtLivre,designation,pu,idLivraison) "
                + " VALUES(?,?,?,?)");
        query.setParameter(1, detailleLivraison.getQtLivre());
        query.setParameter(2, utfconvert.convertFromUTF8(detailleLivraison.getDesignation()));
        query.setParameter(3, detailleLivraison.getPu());
        query.setParameter(4, detailleLivraison.getIdLivraison().getIdLivraison());
        query.executeUpdate();
    }

    public List<DetailleLivraison> listOfDetailleByChantier(Chantier chantier) {

        Query query = getEntityManager().createQuery("SELECT d FROM DetailleLivraison d  WHERE d.idLivraison.idChantier.idChantier = :param");
        query.setParameter("param", chantier.getIdChantier());
        List<DetailleLivraison> leslivraisons = query.getResultList();
        return leslivraisons;
    }

    public List<DetailleLivraison> listOfDetailleByCommande(Commande commande) {

        Query query = getEntityManager().createQuery("SELECT d FROM DetailleLivraison d  WHERE d.idLivraison.idCommande.idCommande = :param");
        query.setParameter("param", commande.getIdCommande());
        List<DetailleLivraison> leslivraisons = query.getResultList();
        return leslivraisons;
    }
    
}
