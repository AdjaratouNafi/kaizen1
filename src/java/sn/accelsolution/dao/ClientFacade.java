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
import sn.accelsolution.entities.Client;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DV7
 */
@Stateless
public class ClientFacade extends AbstractFacade<Client> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClientFacade() {
        super(Client.class);
    } 
    
    public void insertClient(Client client) {
        
        UtilUtfconvert utfconvert = new UtilUtfconvert();
        
        Query query = em.createNativeQuery("INSERT INTO client (nomClient,adresse,telephoneClient,bpClient,mailClient) "
                + " VALUES(?,?,?,?,?)");
        query.setParameter(1, utfconvert.convertFromUTF8(client.getNomClient()));
        query.setParameter(2, utfconvert.convertFromUTF8(client.getAdresse()));
        query.setParameter(3, client.getTelephoneClient());
        query.setParameter(4, client.getBpClient());
        query.setParameter(5, client.getMailClient());
        query.executeUpdate();
    }
    
}
