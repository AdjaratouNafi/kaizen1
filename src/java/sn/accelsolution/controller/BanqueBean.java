/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.controller;         

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import sn.accelsolution.dao.BanqueFacade;
import sn.accelsolution.dao.OperationFacade;
import sn.accelsolution.entities.Banque;
import sn.accelsolution.entities.Operation;
import sn.accelsolution.util.ReportContrat;

/**
 *
 * @author DELL
 */
@ManagedBean
@RequestScoped
public class BanqueBean implements Serializable {

    /**
     * Creates a new instance of CaisseBean
     */
    @EJB
    BanqueFacade ejbbanque;
    @EJB
    OperationFacade ejbOperationFacade;

    private Banque banque;
    private Operation operation;
    private Operation operation2;
    private DataModel itemOperation;
    private DataModel itemOperation2;
    private List listOperations;
    private List listOperations2;
    private Operation OperationFromEdit;
    private Operation OperationFromDelete;
    private String nomAmeneur;
    private String montantTotal;
    private String montantTotall;
    private String montantTotalBanque;
    private ReportContrat reportContrat;
    private String credit;
    private List<Operation> listCalculOperation;

    @PostConstruct
    public void init() {
        listCalculOperation = new ArrayList<Operation>();
        listCalculOperation = ejbOperationFacade.findAll();
        Double ttl = 0.0;
        Double ttll = 0.0;
        for(Operation oneOp: listCalculOperation){
            if(oneOp.getTypeoperation().equals("Entree banque")){
                ttl = ttl + Double.parseDouble(oneOp.getDebit());
            }
            if(oneOp.getTypeoperation().equals("Sortie banque")){
                ttll = ttll + Double.parseDouble(oneOp.getValuecredit());
            }
        }
        
        String mt = String.format("%.3f", ttl);
        String mtc = mt.replace(",", ".");
        this.montantTotal = mtc;
        
        String mtt = String.format("%.3f", ttll);
        String mttc = mtt.replace(",", ".");
        this.montantTotall = mttc;
        
        this.montantTotalBanque = ejbbanque.find(1).getSolde();
    }

    public BanqueBean() {
        banque = new Banque();
        operation = new Operation();
        operation2 = new Operation();
        OperationFromEdit = new Operation();
        OperationFromDelete = new Operation();
        reportContrat = new ReportContrat();
    }

    public String getMontantTotalBanque() {
        return montantTotalBanque;
    }

    public void setMontantTotalBanque(String montantTotalBanque) {
        this.montantTotalBanque = montantTotalBanque;
    }
    

    public List<Operation> getListCalculOperation() {
        return listCalculOperation;
    }

    public void setListCalculOperation(List<Operation> listCalculOperation) {
        this.listCalculOperation = listCalculOperation;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public Operation getOperation2() {
        return operation2;
    }

    public void setOperation2(Operation operation2) {
        this.operation2 = operation2;
    }

    public ReportContrat getReportContrat() {
        return reportContrat;
    }

    public void setReportContrat(ReportContrat reportContrat) {
        this.reportContrat = reportContrat;
    }

    public String getMontantTotall() {
        return montantTotall;
    }

    public void setMontantTotall(String montantTotall) {
        this.montantTotall = montantTotall;
    }

    public String getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(String montantTotal) {
        this.montantTotal = montantTotal;
    }

    public String getNomAmeneur() {
        return nomAmeneur;
    }

    public void setNomAmeneur(String nomAmeneur) {
        this.nomAmeneur = nomAmeneur;
    }

    public Banque getBanque() {
        return banque;
    }

    public void setBanque(Banque banque) {
        this.banque = banque;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public DataModel getItemOperation() {
        itemOperation = new ListDataModel();
        this.setListOperations(ejbOperationFacade.listOfOpEntreeB());
        itemOperation.setWrappedData(this.getListOperations());
        return itemOperation;
    }

    public void setItemOperation(DataModel itemOperation) {
        this.itemOperation = itemOperation;
    }

    public List getListOperations() {
        listOperations = ejbOperationFacade.listOfOpEntreeB();
        return listOperations;
    }

    public void setListOperations(List listOperations) {
        this.listOperations = listOperations;
    }

    public DataModel getItemOperation2() {
        itemOperation2 = new ListDataModel();
        this.setListOperations2(ejbOperationFacade.listOfOpSortieB());
        itemOperation2.setWrappedData(this.getListOperations2());
        return itemOperation2;
    }

    public void setItemOperation2(DataModel itemOperation2) {
        this.itemOperation2 = itemOperation2;
    }

    public List getListOperations2() {
        listOperations2 = ejbOperationFacade.listOfOpSortieB();
        return listOperations2;
    }

    public void setListOperations2(List listOperations2) {
        this.listOperations2 = listOperations2;
    }
    

    public Operation getOperationFromEdit() {
        return OperationFromEdit;
    }

    public void setOperationFromEdit(Operation OperationFromEdit) {
        this.OperationFromEdit = OperationFromEdit;
    }

    public Operation getOperationFromDelete() {
        return OperationFromDelete;
    }

    public void setOperationFromDelete(Operation OperationFromDelete) {
        this.OperationFromDelete = OperationFromDelete;
    }

    public String save() {
        System.out.println("Bonjour Willi !!!");

        try {

            /*Recuperation de la caisse*/
            this.banque = ejbbanque.find(1);

            /*Calcule*/
            String ancienSold = this.banque.getSolde();
            Double as = Double.parseDouble(ancienSold);
            Double dbt = Double.parseDouble(this.operation.getDebit());
            Double nouveauSolde = as + dbt;

            this.operation.setIdBanque(banque);
            this.operation.setTypeoperation("Entree banque");
            this.operation.setAnciensolde(ancienSold);
            String nvsc = String.format("%.3f", nouveauSolde);
            String nvs = nvsc.replace(",", ".");
            this.operation.setNouveausolde(nvs);

            //System.out.println("Operation :"+this.operation);
            ejbOperationFacade.insertOperationB(operation);

            String sldinit = this.banque.getMontantInitiale();
            Double sldinitConvert = Double.parseDouble(sldinit);
            if (sldinitConvert == 0.0) {
                this.banque.setMontantInitiale(this.operation.getNouveausolde());
                this.banque.setSolde(this.operation.getNouveausolde());
                this.banque.setSoldesortie(this.operation.getNouveausolde());
            } else {
                this.banque.setSolde(this.operation.getNouveausolde());
            }
            ejbbanque.edit(banque);

            return "home";
        } catch (Exception e) {
            return "new_entree_banque";
        }

    }

    public String saveSortie() {
        System.out.println("Bonjour Willi Sortie!!!");

        try {

            this.operation2.setValuecredit(this.credit);
            
            /*Recuperation de la caisse*/
            this.banque = ejbbanque.find(1);

            /*Calcule*/
            String ancienSold = this.banque.getSolde();
            Double anciensold1 = Double.parseDouble(ancienSold);
            Double getCredit = Double.parseDouble(this.operation2.getValuecredit()); 
            Double nouveauSolde = anciensold1 - getCredit;

            this.operation2.setIdBanque(banque);
            this.operation2.setTypeoperation("Sortie banque");
            this.operation2.setAnciensolde(ancienSold);
            String nvsc = String.format("%.3f", nouveauSolde);
            String nvs = nvsc.replace(",", ".");
            this.operation2.setNouveausolde(nvs);
            
            ejbOperationFacade.insertOperationSortieB(operation2);

            this.banque.setSoldesortie(this.operation2.getNouveausolde());
            this.banque.setSolde(this.operation2.getNouveausolde());
            
            ejbbanque.edit(banque);

            return "home";
        } catch (Exception e) {
            return "new_sortie_banque";
        }

    }

    public void reset() {

    }

    public String editer() {
        try {
            OperationFromEdit = (Operation) itemOperation.getRowData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "edit_caisse";
    }

    public String update() {
        try {
            /*Calcule*/
            String anciensolde = OperationFromEdit.getAnciensolde();
            int anciensoldeCaster = Integer.parseInt(anciensolde);
            String nouveausolde = OperationFromEdit.getNouveausolde();
            int nouveausoldeCaster = Integer.parseInt(nouveausolde);
            int difsolde = nouveausoldeCaster - anciensoldeCaster;
            int newSolde = Integer.parseInt(this.OperationFromEdit.getDebit());
            int nsmodifier = difsolde + newSolde;

            String anciensoldeString = String.valueOf(difsolde);
            String nouveausoldeString = String.valueOf(nsmodifier);

            this.operation.setIdBanque(banque);
            this.operation.setTypeoperation("Entree caisse");
            this.operation.setAnciensolde(anciensoldeString);
            String nvs = String.valueOf(nouveausoldeString);
            this.operation.setNouveausolde(nvs);
            ejbOperationFacade.edit(OperationFromEdit);
            this.OperationFromEdit.getIdCaisse().setSolde(nvs);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "caisses";
    }

    public String supprimer() {
        try {
            OperationFromDelete = (Operation) itemOperation.getRowData();
            ejbOperationFacade.remove(OperationFromDelete);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "caisses";
    }

    public String generateReport() {
        try {
            /*Repport*/
            OperationFromEdit = (Operation) itemOperation.getRowData();
            reportContrat.generateBanqueJour(OperationFromEdit);
            return "banques";

        } catch (Exception e) {
        }
        return "banques";
    } 
    
    public String generateReport2() {
        try {
            /*Repport*/
            OperationFromEdit = (Operation) itemOperation2.getRowData();
            reportContrat.generateSortieBanqueJour(OperationFromEdit);
            return "sortie_banques";

        } catch (Exception e) {
        }
        return "sortie_banques";
    }

//    public String generateAllReport(){
//        try {
//            /*Repport*/
//            reportContrat.generateAllCaisse();
//            return "caisses";
//            
//        } catch (Exception e) {
//        }
//        return "caisses";
//    }
}
