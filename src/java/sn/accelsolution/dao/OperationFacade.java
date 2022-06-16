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
import sn.accelsolution.entities.Operation;

/**
 *
 * @author DV7
 */
@Stateless
public class OperationFacade extends AbstractFacade<Operation> {

    @PersistenceContext(unitName = "KaisenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OperationFacade() {
        super(Operation.class);
    } 
    
    public void insertOperation(Operation operation) {
        Query query = em.createNativeQuery("INSERT INTO operation (dateoperation,objet,debit,montantlettre,nature,idCaisse,idClient,anciensolde,nouveausolde,typeoperation)"
                + " VALUES(?,?,?,?,?,?,?,?,?,?)");

        query.setParameter(1, operation.getDateoperation());
        query.setParameter(2, operation.getObjet());
        query.setParameter(3, operation.getDebit());
        query.setParameter(4, operation.getMontantlettre());
        query.setParameter(5, operation.getNature());
        query.setParameter(6, operation.getIdCaisse().getNumeroCaisse());
        query.setParameter(7, operation.getIdClient().getIdClient());
        query.setParameter(8, operation.getAnciensolde());
        query.setParameter(9, operation.getNouveausolde());
        query.setParameter(10, operation.getTypeoperation());
        query.executeUpdate();
    }

    public void insertOperationSortie(Operation operation) {
        Query query = em.createNativeQuery("INSERT INTO operation (dateoperation,objet,montantlettre,nature,idCaisse,idChantier,anciensolde,nouveausolde,typeoperation,valuecredit,validateur)"
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?)");

        query.setParameter(1, operation.getDateoperation());
        query.setParameter(2, operation.getObjet());
        query.setParameter(3, operation.getMontantlettre());
        query.setParameter(4, operation.getNature());
        query.setParameter(5, operation.getIdCaisse().getNumeroCaisse());
        query.setParameter(6, operation.getIdChantier().getIdChantier());
        query.setParameter(7, operation.getAnciensolde());
        query.setParameter(8, operation.getNouveausolde());
        query.setParameter(9, operation.getTypeoperation());
        query.setParameter(10, operation.getValuecredit());
        query.setParameter(11, operation.getValidateur());
        query.executeUpdate();
    }

    public List<Operation> listOfOpEntree() {
        String param = "Entree caisse";
        Query query = getEntityManager().createQuery("SELECT o FROM Operation o  WHERE o.typeoperation = :param");
        query.setParameter("param", param);
        List<Operation> lesoperations = query.getResultList();
        return lesoperations;
    }

    public List<Operation> listOfOpSortie() {
        String param = "Sortie caisse";
        Query query = getEntityManager().createQuery("SELECT o FROM Operation o  WHERE o.typeoperation = :param");
        query.setParameter("param", param);
        List<Operation> lesoperations = query.getResultList();
        return lesoperations;
    }
    
    public List<Operation> listOfOpEntreeB() {
        String param = "Entree banque";
        Query query = getEntityManager().createQuery("SELECT o FROM Operation o  WHERE o.typeoperation = :param");
        query.setParameter("param", param);
        List<Operation> lesoperations = query.getResultList();
        return lesoperations;
    }

    public List<Operation> listOfOpSortieB() {
        String param = "Sortie banque";
        Query query = getEntityManager().createQuery("SELECT o FROM Operation o  WHERE o.typeoperation = :param");
        query.setParameter("param", param);
        List<Operation> lesoperations = query.getResultList();
        return lesoperations;
    }
    
    
    public void insertOperationB(Operation operation) {
        Query query = em.createNativeQuery("INSERT INTO operation (dateoperation,objet,debit,montantlettre,nature,idBanque,anciensolde,nouveausolde,typeoperation)"
                + " VALUES(?,?,?,?,?,?,?,?,?)");

        query.setParameter(1, operation.getDateoperation());
        query.setParameter(2, operation.getObjet());
        query.setParameter(3, operation.getDebit());
        query.setParameter(4, operation.getMontantlettre());
        query.setParameter(5, operation.getNature());
        query.setParameter(6, operation.getIdBanque().getNumeroBanque());
        query.setParameter(7, operation.getAnciensolde());
        query.setParameter(8, operation.getNouveausolde());
        query.setParameter(9, operation.getTypeoperation());
        query.executeUpdate();
    }

    public void insertOperationSortieB(Operation operation) {
        Query query = em.createNativeQuery("INSERT INTO operation (dateoperation,objet,montantlettre,nature,idBanque,anciensolde,beneficiaire,nouveausolde,typeoperation,valuecredit)"
                + " VALUES(?,?,?,?,?,?,?,?,?,?)");

        query.setParameter(1, operation.getDateoperation());
        query.setParameter(2, operation.getObjet());
        query.setParameter(3, operation.getMontantlettre());
        query.setParameter(4, operation.getNature());
        query.setParameter(5, operation.getIdBanque().getNumeroBanque());
        query.setParameter(6, operation.getBeneficiaire());
        query.setParameter(7, operation.getAnciensolde());
        query.setParameter(8, operation.getNouveausolde());
        query.setParameter(9, operation.getTypeoperation());
        query.setParameter(10, operation.getValuecredit());
        query.executeUpdate();
    }
    
    
    public List<Operation> listOfOpEntreeByMarche(int idMarche) {
        String param1 = "Entree caisse";
        Query query = getEntityManager().createQuery("SELECT o FROM Operation o  WHERE o.typeoperation = :param1 AND o.idMarche.idMarche =:param2");
        query.setParameter("param1", param1);
        query.setParameter("param2", idMarche);
        List<Operation> lesoperations = query.getResultList();
        return lesoperations;
    }
    
}
