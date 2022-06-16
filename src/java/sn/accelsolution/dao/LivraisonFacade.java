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
import sn.accelsolution.entities.Chantier;
import sn.accelsolution.entities.Commande;
import sn.accelsolution.entities.Livraison;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DV7
 */
@Stateless
public class LivraisonFacade extends AbstractFacade<Livraison> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LivraisonFacade() {
        super(Livraison.class);
    } 
    
    public void insertLivraison(Livraison livraison) {
        UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO livraison (designation,qtLivre,qtRestante,pu,dateLivraison,idFournisseur,idCommande,idChantier) "
                + " VALUES(?,?,?,?,?,?,?,?)");
        query.setParameter(1, utfconvert.convertFromUTF8(livraison.getDesignation()));
        query.setParameter(2, livraison.getQtLivre());
        query.setParameter(3, livraison.getQtRestante());
        query.setParameter(4, livraison.getPu());
        query.setParameter(5, livraison.getDateLivraison());
        query.setParameter(6, livraison.getIdFournisseur().getIdFournisseur());
        query.setParameter(7, livraison.getIdCommande().getIdCommande());
        query.setParameter(8, livraison.getIdChantier().getIdChantier());
        query.executeUpdate();
    }

    public int maxLivraison() {
        int rs = 0;
        Query query = getEntityManager().createQuery("SELECT MAX(l.idLivraison) FROM Livraison l");
        rs = (int) query.getSingleResult();

        return rs;

    }

    public List<Livraison> listOfLivraisonByChantier(Chantier chantier) {

        Query query = getEntityManager().createQuery("SELECT l FROM Livraison l  WHERE l.idChantier.idChantier = :param");
        query.setParameter("param", chantier.getIdChantier());
        List<Livraison> leslivraisons = query.getResultList();
        return leslivraisons;
    }

    public List<Livraison> listOfLivraisonByCommande(Commande commande) {

        Query query = getEntityManager().createQuery("SELECT l FROM Livraison l  WHERE l.idCommande.idCommande = :param");
        query.setParameter("param", commande.getIdCommande());
        List<Livraison> leslivraisons = query.getResultList();
        return leslivraisons;
    }

    public List<Livraison> listOfAllLivraison() {
        List<Livraison> alllivraisons = new ArrayList<>();
        Query query = getEntityManager().createQuery("SELECT l FROM Livraison l");
        List<Livraison> leslivraisons = query.getResultList();
        int idCmd = 0;
        int indicateur = 1;
        for (Livraison lv : leslivraisons) {
            //System.out.println("Valeur livraison : " + lv.getIdCommande().getIdChantier().getIdMarche().getIdClient().getNomClient());
            if (indicateur == 1) {
                alllivraisons.add(lv);
            } else {
                if (idCmd != lv.getIdCommande().getIdCommande()) {
                    alllivraisons.add(lv);
                }
            }
            idCmd = lv.getIdCommande().getIdCommande();
            indicateur = indicateur + 1;
        }
        return alllivraisons;
    } 
    
    
    public List<Livraison> listOfAllLivraisonByChantier(Chantier chantier) {
     
        List<Livraison> alllivraisons = new ArrayList<>();
        Query query = getEntityManager().createQuery("SELECT l FROM Livraison l WHERE l.idChantier.idChantier =:param");
        query.setParameter("param", chantier.getIdChantier());
        List<Livraison> leslivraisons = query.getResultList();
        int idCmd = 0;
        int indicateur = 1;
        for (Livraison lv : leslivraisons) {
            //System.out.println("Valeur livraison : " + lv.getIdCommande().getIdChantier().getIdMarche().getIdClient().getNomClient());
            if (indicateur == 1) {
                alllivraisons.add(lv);
            } else {
                if (idCmd != lv.getIdCommande().getIdCommande()) {
                    alllivraisons.add(lv);
                }
            }
            idCmd = lv.getIdCommande().getIdCommande();
            indicateur = indicateur + 1;
        }
        return alllivraisons;
    }
    
}
