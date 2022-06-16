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
import sn.accelsolution.entities.Prestataire;
import sn.accelsolution.entities.Prestataireprim;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DV7
 */
@Stateless
public class PrestataireFacade extends AbstractFacade<Prestataire> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrestataireFacade() {
        super(Prestataire.class);
    } 
    
    public void insertPrestataire(Prestataire prestataire) {
        UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO prestataire (ninea,nomcomplet,corps, telephone, accord, accompte, reliquant, idChantier, voyant, idprestatairePrim) "
                + " VALUES(?,?,?,?,?,?,?,?,?,?)");
        query.setParameter(1, prestataire.getNinea());
        query.setParameter(2, utfconvert.convertFromUTF8(prestataire.getNomcomplet()));
        query.setParameter(3, utfconvert.convertFromUTF8(prestataire.getCorps()));
        query.setParameter(4, prestataire.getTelephone());
        query.setParameter(5, prestataire.getAccord());
        query.setParameter(6, prestataire.getAccompte());
        query.setParameter(7, prestataire.getReliquant());
        query.setParameter(8, prestataire.getIdChantier().getIdChantier());
        query.setParameter(9, "non");
        query.setParameter(10, prestataire.getIdprestatairePrim().getIdprestatairePrim());

        query.executeUpdate();
    }

    public List<Prestataire> listOfPrestataires() {

        List<Prestataire> prestataireList = new ArrayList<>();

        Query query = getEntityManager().createQuery("SELECT p FROM Prestataire p");
        prestataireList = query.getResultList();
        if (prestataireList.size() > 0) {
            return prestataireList;
        }
        return null;

    }

    public List<Prestataire> listOfPrestataireByChantier(Chantier chantier) {

        Query query = getEntityManager().createQuery("SELECT p FROM Prestataire p  WHERE p.idChantier.idChantier = :param");
        query.setParameter("param", chantier.getIdChantier());
        List<Prestataire> lesprestataires = query.getResultList();
        return lesprestataires;
    } 
    
    public int maxPrestataire() {
        int rs = 0;
        Query query = getEntityManager().createQuery("SELECT MAX(p.idPrestataire) FROM Prestataire p");
        rs = (int) query.getSingleResult();

        return rs;

    } 
    
    public List<Prestataire> listOfPrestataireByPrestatairePrim(Prestataireprim prestatairePrim) {

        Query query = getEntityManager().createQuery("SELECT p FROM Prestataire p  WHERE p.idprestatairePrim.idprestatairePrim = :param");
        query.setParameter("param", prestatairePrim.getIdprestatairePrim());
        List<Prestataire> lesprestataires = query.getResultList();
        return lesprestataires;
    } 
    
}
