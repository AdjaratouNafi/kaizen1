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
import sn.accelsolution.entities.Commande;
import sn.accelsolution.entities.Entrepot;
import sn.accelsolution.entities.Historiquelivraison;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DV7
 */
@Stateless
public class HistoriquelivraisonFacade extends AbstractFacade<Historiquelivraison> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistoriquelivraisonFacade() {
        super(Historiquelivraison.class);
    } 
    
    public void insertLivraison(Historiquelivraison historiqueLivraison) {
        UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO historiquelivraison (designation,qtLivree,qtRestante,pu,dateLivraison,idFournisseur,idCommande,idEntrepot) "
                + " VALUES(?,?,?,?,?,?,?,?)");
        query.setParameter(1, utfconvert.convertFromUTF8(historiqueLivraison.getDesignation()));
        query.setParameter(2, historiqueLivraison.getQtLivree());
        query.setParameter(3, historiqueLivraison.getQtRestante());
        query.setParameter(4, historiqueLivraison.getPu());
        query.setParameter(5, historiqueLivraison.getDateLivraison());
        query.setParameter(6, historiqueLivraison.getIdFournisseur().getIdFournisseur());
        query.setParameter(7, historiqueLivraison.getIdCommande().getIdCommande());
        query.setParameter(8, historiqueLivraison.getIdEntrepot().getIdEntrepot());
        query.executeUpdate();
    }

    public int maxLivraison() {
        int rs = 0;
        Query query = getEntityManager().createQuery("SELECT MAX(l.idHistoLivraison) FROM Historiquelivraison l");
        rs = (int) query.getSingleResult();

        return rs;

    } 
    
    
    public List<Historiquelivraison> listOfAllLivraisonByEntrepot(Entrepot entrepot) {
     
        List<Historiquelivraison> alllivraisons = new ArrayList<>();
        Query query = getEntityManager().createQuery("SELECT l FROM Historiquelivraison l WHERE l.idEntrepot.idEntrepot =:param");
        query.setParameter("param", entrepot.getIdEntrepot());
        List<Historiquelivraison> leslivraisons = query.getResultList();
        int idCmd = 0;
        int indicateur = 1;
        for (Historiquelivraison lv : leslivraisons) {
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
    
    public List<Historiquelivraison> listOfLivraisonByCommande(Commande commande) {

        Query query = getEntityManager().createQuery("SELECT l FROM Historiquelivraison l  WHERE l.idCommande.idCommande = :param");
        query.setParameter("param", commande.getIdCommande());
        List<Historiquelivraison> leslivraisons = query.getResultList();
        return leslivraisons;
    }
    
}
