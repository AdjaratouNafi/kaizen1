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
import sn.accelsolution.entities.Prospection;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DV7
 */
@Stateless
public class ProspectionFacade extends AbstractFacade<Prospection> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProspectionFacade() {
        super(Prospection.class);
    } 
    
    public void insertProspection(Prospection prospection) {
        UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO prospection (dateProspection, descriptionProspection,idProspectClient) "
                + " VALUES(?,?,?)");
        query.setParameter(1, prospection.getDateProspection());
        query.setParameter(2, utfconvert.convertFromUTF8(prospection.getDescriptionProspection()));
        query.setParameter(3, prospection.getIdProspectClient().getIdProspectClient());
        query.executeUpdate();
    }
    
}
