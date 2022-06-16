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
import sn.accelsolution.entities.Expressionbesoin;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DV7
 */
@Stateless
public class ExpressionbesoinFacade extends AbstractFacade<Expressionbesoin> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ExpressionbesoinFacade() {
        super(Expressionbesoin.class);
    } 
    
    public void insertExpression(Expressionbesoin expressionbesoin) {
        UtilUtfconvert utfconvert = new UtilUtfconvert();
        Query query = em.createNativeQuery("INSERT INTO expressionbesoin (numeroExpression,dateExpression,montantlettre,nivovalidation,etat,motif,idUtilisateur,idChantier) "
                + " VALUES(?,?,?,?,?,?,?,?)");
        query.setParameter(1, expressionbesoin.getNumeroExpression());
        query.setParameter(2, expressionbesoin.getDateExpression());
        query.setParameter(3, expressionbesoin.getMontantlettre());
        query.setParameter(4, expressionbesoin.getNivovalidation());
        query.setParameter(5, utfconvert.convertFromUTF8(expressionbesoin.getEtat()));
        query.setParameter(6, expressionbesoin.getMotif());
        query.setParameter(7, expressionbesoin.getIdUtilisateur().getIdUtilisateur());
        query.setParameter(8, expressionbesoin.getIdChantier().getIdChantier());
        query.executeUpdate();
    }

    public int maxExpression() {
        int rs = 0;
        Query query = getEntityManager().createQuery("SELECT MAX(m.idExpression) FROM Expressionbesoin m");
        rs = (int) query.getSingleResult();
        return rs; 
    } 
    
    public List<Expressionbesoin> listOfExpressionAttente() {
        String prm1 = "En cours de validation";
        Query query = getEntityManager().createQuery("SELECT e FROM Expressionbesoin e  WHERE e.etat = :param1");
        query.setParameter("param1", prm1);
        List<Expressionbesoin> lecmds = query.getResultList();
        return lecmds;
    } 
    
    public List<Expressionbesoin> listOfExpressionAttenteByExpression(Expressionbesoin expressionbesoin) {
        String prm1 = "En cours de validation";
        Query query = getEntityManager().createQuery("SELECT e FROM Expressionbesoin e  WHERE e.etat = :param1 AND e.idExpression = :param2");
        query.setParameter("param1", prm1);
        query.setParameter("param2", expressionbesoin.getIdExpression());
        List<Expressionbesoin> lecmds = query.getResultList();
        return lecmds;
    } 
    
}
