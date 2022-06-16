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
import sn.accelsolution.entities.Prospect;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DV7
 */
@Stateless
public class ProspectFacade extends AbstractFacade<Prospect> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProspectFacade() {
        super(Prospect.class);
    } 
    
    public void insertProspect(Prospect prospect) {
        UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO prospect (nomProspect,adresse,telephoneProspect,bpProspect,mailProspect,client) "
                + " VALUES(?,?,?,?,?,?)");
        query.setParameter(1, utfconvert.convertFromUTF8(prospect.getNomProspect()));
        query.setParameter(2, utfconvert.convertFromUTF8(prospect.getAdresse()));
        query.setParameter(3, prospect.getTelephoneProspect());
        query.setParameter(4, prospect.getBpProspect());
        query.setParameter(5, prospect.getMailProspect());
        query.setParameter(6, prospect.getClient());
        query.executeUpdate();
    }
    
}
