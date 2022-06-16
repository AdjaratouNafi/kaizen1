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
import sn.accelsolution.entities.Fournisseur;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DV7
 */
@Stateless
public class FournisseurFacade extends AbstractFacade<Fournisseur> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FournisseurFacade() {
        super(Fournisseur.class);
    } 
    
    public void insertFournisseur(Fournisseur fournisseur) {
        UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO fournisseur (nomFournisseur,ninea,adresseFournisseur,telephoneFournisseur,mailFournisseur) "
                + " VALUES(?,?,?,?,?)");
        query.setParameter(1, utfconvert.convertFromUTF8(fournisseur.getNomFournisseur()));
        query.setParameter(2, fournisseur.getNinea());
        query.setParameter(3, utfconvert.convertFromUTF8(fournisseur.getAdresseFournisseur()));
        query.setParameter(4, fournisseur.getTelephoneFournisseur());
        query.setParameter(5, fournisseur.getMailFournisseur());
        query.executeUpdate();
    }
    
}
