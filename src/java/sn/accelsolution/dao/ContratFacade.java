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
import sn.accelsolution.entities.Contrat;
import sn.accelsolution.entities.Utilisateur;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DV7
 */
@Stateless
public class ContratFacade extends AbstractFacade<Contrat> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContratFacade() {
        super(Contrat.class);
    } 
    
    public List<Contrat> listOfContratRecru() {
        String param = "Recrutement";
        Query query = getEntityManager().createQuery("SELECT c FROM Contrat c  WHERE c.localTypeContrat = :param");
        query.setParameter("param", param);
        List<Contrat> lescontrats = query.getResultList();
        return lescontrats;
    }
    
    public List<Contrat> listOfContratStage() {
        String param = "Stage";
        Query query = getEntityManager().createQuery("SELECT c FROM Contrat c  WHERE c.localTypeContrat = :param");
        query.setParameter("param", param);
        List<Contrat> lescontrats = query.getResultList();
        return lescontrats;
    }
    
    public void insertContratCDD(Contrat contrat) {
        
        UtilUtfconvert utfconvert = new UtilUtfconvert();
        
        Query query = em.createNativeQuery("INSERT INTO contrat (dateEngagement,dureeTravail,salairebase,surSalaire,salaireBrutFiscal,idUtilisateur,localTypeContrat,diffTypeContrat,article1,article2,article3,article4,article5,article6,article8,article9,article10,article11,article12,article13,article14,article15,article16) "
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        query.setParameter(1, contrat.getDateEngagement());
        query.setParameter(2, contrat.getDureeTravail());
        query.setParameter(3, contrat.getSalairebase());
        query.setParameter(4, contrat.getSurSalaire());
        query.setParameter(5, contrat.getSalaireBrutFiscal());
        query.setParameter(6, contrat.getIdUtilisateur().getIdUtilisateur());
        query.setParameter(7, contrat.getLocalTypeContrat());
        query.setParameter(8, contrat.getDiffTypeContrat());
        query.setParameter(9,  utfconvert.convertFromUTF8(contrat.getArticle1()));
        query.setParameter(10, utfconvert.convertFromUTF8(contrat.getArticle2()));
        query.setParameter(11, utfconvert.convertFromUTF8(contrat.getArticle3()));
        query.setParameter(12, utfconvert.convertFromUTF8(contrat.getArticle4()));
        query.setParameter(13, utfconvert.convertFromUTF8(contrat.getArticle5())); 
        query.setParameter(14, utfconvert.convertFromUTF8(contrat.getArticle6()));
        query.setParameter(15, utfconvert.convertFromUTF8(contrat.getArticle8()));
        query.setParameter(16, utfconvert.convertFromUTF8(contrat.getArticle9()));
        query.setParameter(17, utfconvert.convertFromUTF8(contrat.getArticle10()));
        query.setParameter(18, utfconvert.convertFromUTF8(contrat.getArticle11()));
        query.setParameter(19, utfconvert.convertFromUTF8(contrat.getArticle12()));
        query.setParameter(20, utfconvert.convertFromUTF8(contrat.getArticle13()));
        query.setParameter(21, utfconvert.convertFromUTF8(contrat.getArticle14()));
        query.setParameter(22, utfconvert.convertFromUTF8(contrat.getArticle15()));
        query.setParameter(23, utfconvert.convertFromUTF8(contrat.getArticle16()));
        query.executeUpdate();
    }
    
    
    public Contrat findContratByUser(Utilisateur utilisateur) {

        Query query = getEntityManager().createQuery("SELECT c FROM Contrat c WHERE c.idUtilisateur.idUtilisateur =:idUser");
        query.setParameter("idUser", utilisateur.getIdUtilisateur());
        List<Contrat> lescontrats = query.getResultList();
        if (lescontrats.size() > 0) {
            return lescontrats.get(0);
        }
        return null;

    }
    
}
