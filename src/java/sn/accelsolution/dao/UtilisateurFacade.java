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
import sn.accelsolution.entities.Utilisateur;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DV7
 */
@Stateless
public class UtilisateurFacade extends AbstractFacade<Utilisateur> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UtilisateurFacade() {
        super(Utilisateur.class);
    } 
    
    public void insertUtilisateur(Utilisateur utilisateur) {
        UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO utilisateur (cni,nomUtilisateur, prenomUtilisateur, adresseUtilisateur, telUtilisateur, mailUtilisateur, passwordUtisateur, numeroSecuriteSociale, idRole,dateNaissanceUtilisateur, lieuNaissanceUtilisateur, situationfamille, fonction, hcreationpwd, mcreationpwd, screationpwd, etatpwd, etatcompte, firstconnection) "
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        query.setParameter(1, utilisateur.getCni());
        query.setParameter(2, utfconvert.convertFromUTF8(utilisateur.getNomUtilisateur()));
        query.setParameter(3, utfconvert.convertFromUTF8(utilisateur.getPrenomUtilisateur()));
        query.setParameter(4, utfconvert.convertFromUTF8(utilisateur.getAdresseUtilisateur()));
        query.setParameter(5, utilisateur.getTelUtilisateur());
        query.setParameter(6, utilisateur.getMailUtilisateur());
        query.setParameter(7, utilisateur.getPasswordUtisateur());
        query.setParameter(8, utilisateur.getNumeroSecuriteSociale());
        query.setParameter(9, utilisateur.getIdRole().getIdRole());
        query.setParameter(10, utilisateur.getDateNaissanceUtilisateur());
        query.setParameter(11, utfconvert.convertFromUTF8(utilisateur.getLieuNaissanceUtilisateur()));
        query.setParameter(12, utilisateur.getSituationfamille());
        query.setParameter(13, utfconvert.convertFromUTF8(utilisateur.getFonction()));
        query.setParameter(14, utilisateur.getHcreationpwd());
        query.setParameter(15, utilisateur.getMcreationpwd());
        query.setParameter(16, utilisateur.getScreationpwd());
        query.setParameter(17, utfconvert.convertFromUTF8(utilisateur.getEtatpwd()));
        query.setParameter(18, utfconvert.convertFromUTF8(utilisateur.getEtatcompte()));
        query.setParameter(19, utilisateur.getFirstconnection());
        query.executeUpdate();
    }

    public Utilisateur connexion(String mail, String password) {

        Query query = getEntityManager().createQuery("SELECT u FROM Utilisateur u WHERE u.mailUtilisateur =:mail AND u.passwordUtisateur =:password");
        query.setParameter("mail", mail);
        query.setParameter("password", password);
        List<Utilisateur> leusers = query.getResultList();
        if (leusers.size() > 0) {
            return leusers.get(0);
        }
        return null;

    }

    public List<Utilisateur> listOfUserWithoutAdmin() {
        String param = "Administrateur";
        Query query = getEntityManager().createQuery("SELECT u FROM Utilisateur u  WHERE u.idRole.libelleRole <> :param");
        query.setParameter("param", param);
        List<Utilisateur> lesusers = query.getResultList();
        return lesusers;
    }

    public int maxUser() {
        int rs = 0;
        Query query = getEntityManager().createQuery("SELECT MAX(u.idUtilisateur) FROM Utilisateur u");
        rs = (int) query.getSingleResult();

        return rs;

    } 
    
    public List<Utilisateur> listOfUserActif() {
        String param = "Valide";
        String paramm = "Invalide";
        Query query = getEntityManager().createQuery("SELECT u FROM Utilisateur u  WHERE u.etatcompte=:param OR u.etatcompte=:paramm");
        query.setParameter("param", param);
        query.setParameter("paramm", paramm);
        List<Utilisateur> lesusers = query.getResultList();
        return lesusers;
    }
    
}
