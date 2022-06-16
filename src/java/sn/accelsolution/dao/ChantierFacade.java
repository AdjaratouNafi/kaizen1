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
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DV7
 */
@Stateless
public class ChantierFacade extends AbstractFacade<Chantier> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ChantierFacade() {
        super(Chantier.class);
    } 
    
    public void insertChantier(Chantier chantier) {
        
        UtilUtfconvert utfconvert = new UtilUtfconvert();
        
        Query query = em.createNativeQuery("INSERT INTO chantier (etatchantier,siteChantier,dateCreation,idMarche,idUtilisateur)"
                + " VALUES(?,?,?,?,?)");
        query.setParameter(1, utfconvert.convertFromUTF8(chantier.getEtatchantier()));
        query.setParameter(2, utfconvert.convertFromUTF8(chantier.getSiteChantier()));
        query.setParameter(3, chantier.getDateCreation());
        query.setParameter(4, chantier.getIdMarche().getIdMarche());
        query.setParameter(5, chantier.getIdUtilisateur().getIdUtilisateur());
        query.executeUpdate();
    }
    
    
    public List<Chantier> listOfChantierByMarche(int idMarche) {
        Query query = getEntityManager().createQuery("SELECT c FROM Chantier c  WHERE c.idMarche.idMarche =:param");
        query.setParameter("param", idMarche);
        List<Chantier> leschantiers = query.getResultList();
        return leschantiers;
    }
    
}
