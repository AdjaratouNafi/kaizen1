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
import sn.accelsolution.entities.DetailleCommande;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DV7
 */
@Stateless
public class DetailleCommandeFacade extends AbstractFacade<DetailleCommande> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetailleCommandeFacade() {
        super(DetailleCommande.class);
    } 
    
    public void insertDetailleCommandePrim(DetailleCommande detailleCommande) {
        UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO detaille_commande (reference,designation,quantite,idCommande,pu,unite,qtlivree,qtrestante) "
                + " VALUES(?,?,?,?,?,?,?,?)");
        query.setParameter(1, utfconvert.convertFromUTF8(detailleCommande.getReference()));
        query.setParameter(2, utfconvert.convertFromUTF8(detailleCommande.getDesignation()));
        query.setParameter(3, detailleCommande.getQuantite());
        query.setParameter(4, detailleCommande.getIdCommande().getIdCommande());
        query.setParameter(5, detailleCommande.getPu());
        query.setParameter(6, utfconvert.convertFromUTF8(detailleCommande.getUnite()));
        query.setParameter(7, detailleCommande.getQtlivree());
        query.setParameter(8, detailleCommande.getQtrestante());
        query.executeUpdate();
    }

    public List<DetailleCommande> listOfDetailleCommandeByCommande(Commande commande) {

        Query query = getEntityManager().createQuery("SELECT d FROM DetailleCommande d  WHERE d.idCommande.idCommande = :param");
        query.setParameter("param", commande.getIdCommande());
        List<DetailleCommande> lescmds = query.getResultList();
        return lescmds;
    }

    public void deleteByDevis(int idDevis) {
        Query query = em.createNativeQuery("DELETE FROM detailleDevis WHERE idDevis=?");
        query.setParameter(1, idDevis);
        query.executeUpdate();
    }

    public void insertDetailleCommande(DetailleCommande detailleCommande) {
        
        Query query = em.createNativeQuery("INSERT INTO detaille_commande (remise,montant,quantite, puhortaxe, montanthortaxe, ttc,idCommande,idMarchandise) "
                + " VALUES(?,?,?,?,?,?,?,?)");
        query.setParameter(1, detailleCommande.getRemise());
        query.setParameter(2, detailleCommande.getMontant());
        query.setParameter(3, detailleCommande.getQuantite());
        query.setParameter(4, detailleCommande.getPuhortaxe());
        query.setParameter(5, detailleCommande.getMontanthortaxe());
        query.setParameter(6, detailleCommande.getTtc());
        query.setParameter(7, detailleCommande.getIdCommande().getIdCommande());
        query.setParameter(8, detailleCommande.getIdMarchandise().getIdMarchandise());
        query.executeUpdate();
    }

    public void insertDetailleCommande2(DetailleCommande detailleCommande) {
        UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO detaille_commande (remise,montant,designation,quantite, puhortaxe, montanthortaxe, ttc,idCommande,pu) "
                + " VALUES(?,?,?,?,?,?,?,?,?)");
        query.setParameter(1, detailleCommande.getRemise());
        query.setParameter(2, detailleCommande.getMontant());
        query.setParameter(3, utfconvert.convertFromUTF8(detailleCommande.getDesignation()));
        query.setParameter(4, detailleCommande.getQuantite());
        query.setParameter(5, detailleCommande.getPu());
        query.setParameter(6, detailleCommande.getMontanthortaxe());
        query.setParameter(7, detailleCommande.getTtc());
        query.setParameter(8, detailleCommande.getIdCommande().getIdCommande());
        query.setParameter(9, detailleCommande.getPu());
        query.executeUpdate();
    }
    
    public void insertDetailleCommande2P(DetailleCommande detailleCommande) {
        UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO detaille_commande (montant,designation,quantite, puhortaxe, montanthortaxe, ttc,idCommande,pu,unite) "
                + " VALUES(?,?,?,?,?,?,?,?,?)");

        query.setParameter(1, detailleCommande.getMontant());
        query.setParameter(2, utfconvert.convertFromUTF8(detailleCommande.getDesignation()));
        query.setParameter(3, detailleCommande.getQuantite());
        query.setParameter(4, detailleCommande.getPu());
        query.setParameter(5, detailleCommande.getMontanthortaxe());
        query.setParameter(6, detailleCommande.getTtc());
        query.setParameter(7, detailleCommande.getIdCommande().getIdCommande());
        query.setParameter(8, detailleCommande.getPu());
        query.setParameter(9, utfconvert.convertFromUTF8(detailleCommande.getUnite()));
        query.executeUpdate();
    }

    public int deleteByCommandeAncien(int idCommande) {

        Query query = getEntityManager().createQuery("DELETE FROM DetailleCommande d  WHERE d.idCommande =:param");
        query.setParameter("param", idCommande);
        return 1;

    }
    
    public void deleteByCommande(int idCommande) {
        Query query = em.createNativeQuery("DELETE FROM detaille_commande WHERE idCommande=?");
        query.setParameter(1, idCommande);
        query.executeUpdate();
    }

    public List<DetailleCommande> listOfDetailleByCommande(Commande commande) {

        Query query = getEntityManager().createQuery("SELECT d FROM DetailleCommande d  WHERE d.idCommande.idCommande = :param");
        query.setParameter("param", commande.getIdCommande());
        List<DetailleCommande> lesusers = query.getResultList();
        return lesusers;
    }
    
}
