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
import sn.accelsolution.entities.Ressource;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DV7
 */
@Stateless
public class RessourceFacade extends AbstractFacade<Ressource> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RessourceFacade() {
        super(Ressource.class);
    } 
    
    public void insertRessource(Ressource ressource) {
        UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO ressource (nomRessource,descriptionResssource,typeressource,etatressource,qtStock,qtSeuill,mesrure,pu,idFournisseur) "
                + " VALUES(?,?,?,?,?,?,?,?,?)");
        query.setParameter(1, utfconvert.convertFromUTF8(ressource.getNomRessource()));
        query.setParameter(2, utfconvert.convertFromUTF8(ressource.getDescriptionResssource()));
        query.setParameter(3, utfconvert.convertFromUTF8(ressource.getTyperessource()));
        query.setParameter(4, utfconvert.convertFromUTF8(ressource.getEtatressource()));
        query.setParameter(5, ressource.getQtStock());
        query.setParameter(6, ressource.getQtSeuill());
        query.setParameter(7, ressource.getMesrure());
        query.setParameter(8, ressource.getPu());
        query.setParameter(9, ressource.getIdFournisseur().getIdFournisseur());

        query.executeUpdate();
    }
    
}
