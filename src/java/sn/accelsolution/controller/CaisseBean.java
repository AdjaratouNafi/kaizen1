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
import sn.accelsolution.dao.CaisseFacade;
import sn.accelsolution.dao.OperationFacade;
import sn.accelsolution.dao.TypeOperationFacade;
import sn.accelsolution.entities.Caisse;
import sn.accelsolution.entities.Operation;
import sn.accelsolution.entities.TypeOperation;
import sn.accelsolution.util.ReportContrat;

/**
 *
 * @author DELL
 */
@ManagedBean
@RequestScoped
public class CaisseBean implements Serializable {

    /**
     * Creates a new instance of CaisseBean
     */
    @EJB
    CaisseFacade ejbcaisse;
    @EJB
    TypeOperationFacade ejbTypeOperationFacade;
    @EJB
    OperationFacade ejbOperationFacade;

    private Caisse caisse;
    private TypeOperation typeOperation;
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
    private String montantTotalCaisse;
    private ReportContrat reportContrat;
    private String credit;
    private List<Operation> listCalculOperation;

    @PostConstruct
    public void init() {
        listCalculOperation = new ArrayList<Operation>();
        listCalculOperation = ejbOperationFacade.findAll();
        Double ttl = 0.0;
        Double ttll = 0.0;
        for (Operation oneOp : listCalculOperation) {
            if (oneOp.getTypeoperation().equals("Entree caisse")) {
                ttl = ttl + Double.parseDouble(oneOp.getDebit());
            }
            if (oneOp.getTypeoperation().equals("Sortie caisse")) {
                ttll = ttll + Double.parseDouble(oneOp.getValuecredit());
            }
        }

        String mt = String.format("%.3f", ttl);
        String mtc = mt.replace(",", ".");
        this.montantTotal = mtc;

        String mtt = String.format("%.3f", ttll);
        String mttc = mtt.replace(",", ".");
        this.montantTotall = mttc;

        this.montantTotalCaisse = ejbcaisse.find(1).getSolde();
    }

    public CaisseBean() {
        caisse = new Caisse();
        typeOperation = new TypeOperation();
        operation = new Operation();
        operation2 = new Operation();
        OperationFromEdit = new Operation();
        OperationFromDelete = new Operation();
        reportContrat = new ReportContrat();
    }

    public String getMontantTotalCaisse() {
        return montantTotalCaisse;
    }

    public void setMontantTotalCaisse(String montantTotalCaisse) {
        this.montantTotalCaisse = montantTotalCaisse;
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

    public CaisseFacade getEjbcaisse() {
        return ejbcaisse;
    }

    public void setEjbcaisse(CaisseFacade ejbcaisse) {
        this.ejbcaisse = ejbcaisse;
    }

    public TypeOperationFacade getEjbTypeOperationFacade() {
        return ejbTypeOperationFacade;
    }

    public void setEjbTypeOperationFacade(TypeOperationFacade ejbTypeOperationFacade) {
        this.ejbTypeOperationFacade = ejbTypeOperationFacade;
    }

    public OperationFacade getEjbOperationFacade() {
        return ejbOperationFacade;
    }

    public void setEjbOperationFacade(OperationFacade ejbOperationFacade) {
        this.ejbOperationFacade = ejbOperationFacade;
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

    public Caisse getCaisse() {
        return caisse;
    }

    public void setCaisse(Caisse caisse) {
        this.caisse = caisse;
    }

    public TypeOperation getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(TypeOperation typeOperation) {
        this.typeOperation = typeOperation;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public DataModel getItemOperation() {
        itemOperation = new ListDataModel();
        this.setListOperations(ejbOperationFacade.listOfOpEntree());
        itemOperation.setWrappedData(this.getListOperations());
        return itemOperation;
    }

    public void setItemOperation(DataModel itemOperation) {
        this.itemOperation = itemOperation;
    }

    public List getListOperations() {
        listOperations = ejbOperationFacade.listOfOpEntree();
        return listOperations;
    }

    public void setListOperations(List listOperations) {
        this.listOperations = listOperations;
    }

    public DataModel getItemOperation2() {
        itemOperation2 = new ListDataModel();
        this.setListOperations2(ejbOperationFacade.listOfOpSortie());
        itemOperation2.setWrappedData(this.getListOperations2());
        return itemOperation2;
    }

    public void setItemOperation2(DataModel itemOperation2) {
        this.itemOperation2 = itemOperation2;
    }

    public List getListOperations2() {
        listOperations2 = ejbOperationFacade.listOfOpSortie();
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
            this.caisse = ejbcaisse.find(1);
            /*Recupertion type operation*/
            this.typeOperation = ejbTypeOperationFacade.find(1);

            /*Calcule*/
            String ancienSold = this.caisse.getSolde();
            Double as = Double.parseDouble(ancienSold);
            Double dbt = Double.parseDouble(this.operation.getDebit());
            Double nouveauSolde = as + dbt;

            this.operation.setIdCaisse(caisse);
            this.operation.setTypeoperation("Entree caisse");
            this.operation.setAnciensolde(ancienSold);
            String nvsc = String.format("%.3f", nouveauSolde);
            String nvs = nvsc.replace(",", ".");
            this.operation.setNouveausolde(nvs);

            //System.out.println("Operation :"+this.operation);
            ejbOperationFacade.insertOperation(operation);

            String sldinit = this.caisse.getMontantInitiale();
            Double sldinitConvert = Double.parseDouble(sldinit);
            if (sldinitConvert == 0.0) {
                this.caisse.setMontantInitiale(this.operation.getNouveausolde());
                this.caisse.setSolde(this.operation.getNouveausolde());
                this.caisse.setSoldesortie(this.operation.getNouveausolde());
            } else {
                this.caisse.setSolde(this.operation.getNouveausolde());
            }
            ejbcaisse.edit(caisse);

            return "home";
        } catch (Exception e) {
            return "new_entree_caisse";
        }

    }

    public String saveSortie() {
        System.out.println("Bonjour Willi Sortie!!!");

        try {

            this.operation2.setValuecredit(this.credit);

            /*Recuperation de la caisse*/
            this.caisse = ejbcaisse.find(1);

            /*Calcule*/
            String ancienSold = this.caisse.getSolde();
            Double anciensold1 = Double.parseDouble(ancienSold);
            Double getCredit = Double.parseDouble(this.operation2.getValuecredit());
            Double nouveauSolde = anciensold1 - getCredit;

            this.operation2.setIdCaisse(caisse);
            this.operation2.setTypeoperation("Sortie caisse");
            this.operation2.setAnciensolde(ancienSold);
            String nvsc = String.format("%.3f", nouveauSolde);
            String nvs = nvsc.replace(",", ".");
            this.operation2.setNouveausolde(nvs);

            ejbOperationFacade.insertOperationSortie(operation2);

            this.caisse.setSoldesortie(this.operation2.getNouveausolde());
            this.caisse.setSolde(this.operation2.getNouveausolde());

            ejbcaisse.edit(caisse);

            return "home";
        } catch (Exception e) {
            return "new_sortie_caisse";
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
            Operation oprt = ejbOperationFacade.find(this.OperationFromEdit.getIdOperation());
            this.OperationFromEdit.setTypeoperation(oprt.getTypeoperation());
            this.OperationFromEdit.setAnciensolde(oprt.getAnciensolde());
            this.OperationFromEdit.setNouveausolde(oprt.getNouveausolde());

            String anciensolde = OperationFromEdit.getAnciensolde();
            Double anciensoldeCaster = Double.parseDouble(anciensolde);
            String nouveausolde = OperationFromEdit.getNouveausolde();
            Double nouveausoldeCaster = Double.parseDouble(nouveausolde);
            Double difsolde = nouveausoldeCaster - anciensoldeCaster;
            Double newSolde = Double.parseDouble(this.OperationFromEdit.getDebit());
            Double nsmodifier = difsolde + newSolde;

            String anciensoldeStringC = String.format("%.3f", difsolde);
            String anciensoldeString = anciensoldeStringC.replace(",", ".");

            String nouveausoldeStringC = String.format("%.3f", nsmodifier);
            String nouveausoldeString = nouveausoldeStringC.replace(",", ".");

            this.operation.setIdCaisse(caisse);
            this.operation.setTypeoperation("Entree caisse");
            this.operation.setAnciensolde(anciensoldeString);
            //String nvs = String.valueOf(nouveausoldeString);
            this.operation.setNouveausolde(nouveausoldeString);
            ejbOperationFacade.edit(OperationFromEdit);
            this.OperationFromEdit.getIdCaisse().setSolde(nouveausoldeString);

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
            /*OperationFromEdit = (Operation) itemOperation.getRowData();*/

            List<Operation> listOpEntres = ejbOperationFacade.findAll();
            Double ttl = 0.0;
            Double ttll = 0.0;
            for (Operation oneOp : listOpEntres) {
                if (oneOp.getTypeoperation().equals("Entree caisse")) {
                    ttl = ttl + Double.parseDouble(oneOp.getDebit());
                }
                if (oneOp.getTypeoperation().equals("Sortie caisse")) {
                    ttll = ttll + Double.parseDouble(oneOp.getValuecredit());
                }
            }

            String mt = String.format("%.3f", ttl);
            String mtc = mt.replace(",", ".");
            this.montantTotal = mtc;

            String mtt = String.format("%.3f", ttll);
            String mttc = mtt.replace(",", ".");
            this.montantTotall = mttc;

            this.montantTotalCaisse = ejbcaisse.find(1).getSolde();

            reportContrat.generateCaisseJour(listOpEntres, mttc, this.montantTotalCaisse);
            return "caisses";

        } catch (Exception e) {
        }
        return "caisses";
    }

    public String generateReport2() {
        try {
            /*Repport*/
            OperationFromEdit = (Operation) itemOperation2.getRowData();
            //reportContrat.generateSortieJour(OperationFromEdit);
            return "sortie_caisses";

        } catch (Exception e) {
        }
        return "sortie_caisses";
    }

}
