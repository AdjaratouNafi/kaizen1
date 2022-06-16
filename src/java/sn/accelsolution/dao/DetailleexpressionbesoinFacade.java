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
import sn.accelsolution.entities.Detailleexpressionbesoin;
import sn.accelsolution.entities.Expressionbesoin;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DV7
 */
@Stateless
public class DetailleexpressionbesoinFacade extends AbstractFacade<Detailleexpressionbesoin> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetailleexpressionbesoinFacade() {
        super(Detailleexpressionbesoin.class);
    } 
    
    public void insertDetailleExpression(Detailleexpressionbesoin detailleExpressionbesoin) {
        UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO detailleexpressionbesoin (reference,unite,designation,quantite,pu,idExpression) "
                + " VALUES(?,?,?,?,?,?)");
        query.setParameter(1, utfconvert.convertFromUTF8(detailleExpressionbesoin.getReference()));
        query.setParameter(2, utfconvert.convertFromUTF8(detailleExpressionbesoin.getUnite()));
        query.setParameter(3, utfconvert.convertFromUTF8(detailleExpressionbesoin.getDesignation()));
        query.setParameter(4, detailleExpressionbesoin.getQuantite());
        query.setParameter(5, detailleExpressionbesoin.getPu());
        query.setParameter(6, detailleExpressionbesoin.getIdExpression().getIdExpression());
        query.executeUpdate();
    }

    public List<Detailleexpressionbesoin> listOfDetailleExpressionbesoinByExpression(Expressionbesoin expressionbesoin) {

        Query query = getEntityManager().createQuery("SELECT d FROM Detailleexpressionbesoin d  WHERE d.idExpression.idExpression = :param");
        query.setParameter("param", expressionbesoin.getIdExpression());
        List<Detailleexpressionbesoin> lesExpressions = query.getResultList();
        return lesExpressions; 
    }

    public void deleteByExpression(int idExpression) {
        Query query = em.createNativeQuery("DELETE FROM detailleExpressionbesoin WHERE idExpression=?");
        query.setParameter(1, idExpression);
        query.executeUpdate();
    }
    
}
