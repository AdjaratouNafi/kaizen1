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
import sn.accelsolution.entities.Entrepot;
import sn.accelsolution.entities.Fournisseur;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DV7
 */
@Stateless
public class CommandeFacade extends AbstractFacade<Commande> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommandeFacade() {
        super(Commande.class);
    } 
    
    //    public void insertCommande(Commande commande) {
//        Query query = em.createNativeQuery("INSERT INTO commande (code,dateechance,idMarche,idChantier,idFournisseur,typecommande) "
//                + " VALUES(?,?,?,?,?,?)");
//        query.setParameter(1, commande.getCode());
//        query.setParameter(2, commande.getDateechance());
//        query.setParameter(3, commande.getIdMarche().getIdMarche());
//        query.setParameter(4, commande.getIdChantier().getIdChantier());
//        query.setParameter(5, commande.getIdFournisseur().getIdFournisseur());
//        query.setParameter(6, commande.getTypecommande());
//        //query.setParameter(7, commande.getIdUtilisateur().getIdUtilisateur());
//        query.executeUpdate();
//    }
    public void insertCommande(Commande commande) { 
        
        UtilUtfconvert utfconvert = new UtilUtfconvert();
        
        Query query = em.createNativeQuery("INSERT INTO commande (code,dateechance,idFournisseur,etat,typecommande,idEntrepot,positionLivraison) "
                + " VALUES(?,?,?,?,?,?,?)");
        query.setParameter(1, commande.getCode());
        query.setParameter(2, commande.getDateechance());
        query.setParameter(3, commande.getIdFournisseur().getIdFournisseur());
        query.setParameter(4, utfconvert.convertFromUTF8(commande.getEtat()));
        query.setParameter(5, commande.getTypecommande());
        query.setParameter(6, commande.getIdEntrepot().getIdEntrepot());
        query.setParameter(7, commande.getPositionLivraison());
        //query.setParameter(7, commande.getIdUtilisateur().getIdUtilisateur());
        query.executeUpdate();
    }

    public void insertCommandeExp(Commande commande) {
        
        UtilUtfconvert utfconvert = new UtilUtfconvert();
        
        Query query = em.createNativeQuery("INSERT INTO commande (code,dateechance,idMarche,idChantier,etat,typecommande,idExpression) "
                + " VALUES(?,?,?,?,?,?,?)");
        query.setParameter(1, commande.getCode());
        query.setParameter(2, commande.getDateechance());
        query.setParameter(3, commande.getIdMarche().getIdMarche());
        query.setParameter(4, commande.getIdChantier().getIdChantier());
        query.setParameter(5, utfconvert.convertFromUTF8(commande.getEtat()));
        query.setParameter(6, commande.getTypecommande());
        query.setParameter(7, commande.getIdExpression().getIdExpression());
        query.executeUpdate();
    } 
    
    public void insertSecondCommandeExp(Commande commande) {
        
        UtilUtfconvert utfconvert = new UtilUtfconvert();
        
        Query query = em.createNativeQuery("INSERT INTO commande (code,dateechance,idMarche,idChantier,idFournisseur,etat,typecommande,livree,idExpression) "
                + " VALUES(?,?,?,?,?,?,?,?,?)");
        query.setParameter(1, commande.getCode());
        query.setParameter(2, commande.getDateechance());
        query.setParameter(3, commande.getIdMarche().getIdMarche());
        query.setParameter(4, commande.getIdChantier().getIdChantier());
        query.setParameter(5, commande.getIdFournisseur().getIdFournisseur());
        query.setParameter(6, utfconvert.convertFromUTF8(commande.getEtat()));
        query.setParameter(7, commande.getTypecommande());
        query.setParameter(8, utfconvert.convertFromUTF8(commande.getLivree()));
        query.setParameter(9, commande.getIdExpression().getIdExpression());
        query.executeUpdate();
    } 
    
    
    public void insertSecondCommandeExpPrim(Commande commande) {
        UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO commande (code,dateechance,idMarche,idChantier,idFournisseur,etat,motif,typecommande,livree,idExpression) "
                + " VALUES(?,?,?,?,?,?,?,?,?,?)");
        query.setParameter(1, commande.getCode());
        query.setParameter(2, commande.getDateechance());
        query.setParameter(3, commande.getIdMarche().getIdMarche());
        query.setParameter(4, commande.getIdChantier().getIdChantier());
        query.setParameter(5, commande.getIdFournisseur().getIdFournisseur());
        query.setParameter(6, utfconvert.convertFromUTF8(commande.getEtat()));
        query.setParameter(7, utfconvert.convertFromUTF8(commande.getMotif()));
        query.setParameter(8, commande.getTypecommande());
        query.setParameter(9, utfconvert.convertFromUTF8(commande.getLivree()));
        query.setParameter(10, commande.getIdExpression().getIdExpression());
        query.executeUpdate();
    }

    public void insertCommande2(Commande commande) {
        UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO commande (code,dateechance, modepaiment, observation ,idClient,typecommande) "
                + " VALUES(?,?,?,?,?,?)");
        query.setParameter(1, commande.getCode());
        query.setParameter(2, commande.getDateechance());
        query.setParameter(3, utfconvert.convertFromUTF8(commande.getModepaiment()));
        query.setParameter(4, utfconvert.convertFromUTF8(commande.getObservation()));
        query.setParameter(5, commande.getIdClient().getIdClient());
        query.setParameter(6, commande.getTypecommande());
        query.executeUpdate();
    }

    public void insertCommande2P(Commande commande) {
        UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO commande (code,dateechance, observation ,typecommande) "
                + " VALUES(?,?,?,?)");
        query.setParameter(1, commande.getCode());
        query.setParameter(2, commande.getDateechance());
        query.setParameter(3, utfconvert.convertFromUTF8(commande.getObservation()));
        query.setParameter(4, commande.getTypecommande());
        query.executeUpdate();
    }

    public int maxCommande() {
        int rs = 0;
        Query query = getEntityManager().createQuery("SELECT MAX(c.idCommande) FROM Commande c");
        rs = (int) query.getSingleResult();

        return rs;

    }

    public List<Commande> listOfCommande() {
        String prm = "Commande";
        Query query = getEntityManager().createQuery("SELECT c FROM Commande c  WHERE c.typecommande = :param");
        query.setParameter("param", prm);
        List<Commande> lesusers = query.getResultList();
        return lesusers;
    }

    public List<Commande> listOfFacture() {
        String prm = "Facture";
        Query query = getEntityManager().createQuery("SELECT c FROM Commande c  WHERE c.typecommande = :param");
        query.setParameter("param", prm);
        List<Commande> lesusers = query.getResultList();
        return lesusers;
    }

    public void deleteCommande(int idCommande) {
        Query query = em.createNativeQuery("DELETE FROM commande WHERE idCommande=?");
        query.setParameter(1, idCommande);
        query.executeUpdate();
    }

    public List<Commande> listOfCommandeByChantier(Chantier chantier) {
        String prm = "Facture";
        Query query = getEntityManager().createQuery("SELECT c FROM Commande c  WHERE c.idChantier.idChantier = :param");
        query.setParameter("param", chantier.getIdChantier());
        List<Commande> lesusers = query.getResultList();
        return lesusers;
    }

    public List<Commande> listOfExpression() {
        String prm1 = "En attente";
        String prm2 = "Exp besoin";
        Query query = getEntityManager().createQuery("SELECT c FROM Commande c  WHERE c.etat = :param1 AND c.typecommande =:param2");
        query.setParameter("param1", prm1);
        query.setParameter("param2", prm2);
        List<Commande> lecmds = query.getResultList();
        return lecmds;
    } 
    
    public List<Commande> listOfCommandeAttente() {
        String prm1 = "En attente";
        String prm2 = "Appro stock";
        Query query = getEntityManager().createQuery("SELECT c FROM Commande c  WHERE c.etat = :param1 AND c.typecommande =:param2");
        query.setParameter("param1", prm1);
        query.setParameter("param2", prm2);
        List<Commande> lecmds = query.getResultList();
        return lecmds;
    }

    public List<Commande> listOfExpressionNonLivrees() {
        String prm1 = "Traitée";
        String prm2 = "Exp besoin";
        String prm3 = "non";
        Query query = getEntityManager().createQuery("SELECT c FROM Commande c  WHERE c.etat = :param1 AND c.typecommande =:param2 AND c.livree =:param3");
        query.setParameter("param1", prm1);
        query.setParameter("param2", prm2);
        query.setParameter("param3", prm3);
        List<Commande> lecmds = query.getResultList();
        return lecmds;
    } 
    
    
    public List<Commande> listOfCommandeNonLivrees() {
        String prm1 = "Traitée";
        String prm2 = "Appro stock";
        String prm3 = "non";
        int prm4 = 1;
        Query query = getEntityManager().createQuery("SELECT c FROM Commande c  WHERE c.etat = :param1 AND c.typecommande =:param2 AND c.livree =:param3");
        query.setParameter("param1", prm1);
        query.setParameter("param2", prm2);
        query.setParameter("param3", prm3);
        List<Commande> lecmds = query.getResultList();
        return lecmds;
    } 
    
    public List<Commande> listOfCommandeNonLivrees2() {
        String prm1 = "Traitée";
        String prm2 = "Approvisionnement stock";
        String prm3 = "non";
        int prm4 = 2;
        Query query = getEntityManager().createQuery("SELECT c FROM Commande c  WHERE c.etat = :param1 AND c.typecommande =:param2 AND c.livree =:param3 AND c.positionLivraison =:param4");
        query.setParameter("param1", prm1);
        query.setParameter("param2", prm2);
        query.setParameter("param3", prm3);
        query.setParameter("param4", prm4);
        List<Commande> lecmds = query.getResultList();
        return lecmds;
    } 
    
    public List<Commande> listOfCommandeByEntrepot(Entrepot entrepot) {
        Query query = getEntityManager().createQuery("SELECT c FROM Commande c  WHERE c.idEntrepot.idEntrepot = :param");
        query.setParameter("param", entrepot.getIdEntrepot());
        List<Commande> lesusers = query.getResultList();
        return lesusers;
    } 
    
    public List<Commande> listOfCommandeByFournisseur(Fournisseur fournisseur) {
        Query query = getEntityManager().createQuery("SELECT c FROM Commande c  WHERE c.idFournisseur.idFournisseur=:param");
        query.setParameter("param", fournisseur.getIdFournisseur());
        List<Commande> lesusers = query.getResultList();
        return lesusers;
    }
    
}
