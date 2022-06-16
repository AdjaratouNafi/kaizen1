/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import sn.accelsolution.dao.ClientFacade;
import sn.accelsolution.entities.Actionmenu;
import sn.accelsolution.entities.Client;
import sn.accelsolution.util.UtilControleMenu;
import sn.accelsolution.util.UtilUtfconvert;

/**
 *
 * @author DELL
 */
@ManagedBean
@ViewScoped
public class ClientBean implements Serializable {

    @EJB
    ClientFacade ejbclient;

    //private DataModel item;
    private List listClients;
    private Client client;
    private Client clientFromEdit;
    private String creerClient;
    private String modifierClient;
    private String supprimerClient;
    private String consulterClient;
    private List<Actionmenu> myllActionmenus;
    private List<Client> allUtilClient1;
    private List<Client> allUtilClient2;
    private List<Client> allUtilClient3;
    private List<Client> allUtilClient4;
    private List<Client> filteredListClient;
    private List<Client> allClient;
    private List<Client> item;

    /**
     * Creates a new instance of UtilisateurBean
     */
    @PostConstruct
    public void init() {

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UtilControleMenu utilControleMenu = new UtilControleMenu();

        myllActionmenus = (List<Actionmenu>) session.getAttribute("allActionmenusFromTraitementClients");

        this.setCreerClient(utilControleMenu.creerClient(myllActionmenus));
        this.setModifierClient(utilControleMenu.modifierClient(myllActionmenus));
        this.setSupprimerClient(utilControleMenu.supprimerClient(myllActionmenus));
        this.setConsulterClient(utilControleMenu.consulterClient(myllActionmenus));

        /*item = new ListDataModel();
        this.setListClients(ejbclient.findAll());
        item.setWrappedData(this.getListClients());*/
        this.setListClients(ejbclient.findAll());
        allClient = new ArrayList<>();
        allClient = ejbclient.findAll();       

    }

    public ClientBean() {
        client = new Client();
        clientFromEdit = new Client();

    }

    public List<Client> getAllClient() {
        return allClient;
    }

    public void setAllClient(List<Client> allClient) {
        this.allClient = allClient;
    }

    public List<Client> getFilteredListClient() {
        return filteredListClient;
    }

    public void setFilteredListClient(List<Client> filteredListClient) {
        this.filteredListClient = filteredListClient;
    }

    public List<Client> getAllUtilClient1() {
        return allUtilClient1;
    }

    public void setAllUtilClient1(List<Client> allUtilClient1) {
        this.allUtilClient1 = allUtilClient1;
    }

    public List<Client> getAllUtilClient2() {
        return allUtilClient2;
    }

    public void setAllUtilClient2(List<Client> allUtilClient2) {
        this.allUtilClient2 = allUtilClient2;
    }

    public List<Client> getAllUtilClient3() {
        return allUtilClient3;
    }

    public void setAllUtilClient3(List<Client> allUtilClient3) {
        this.allUtilClient3 = allUtilClient3;
    }

    public List<Client> getAllUtilClient4() {
        return allUtilClient4;
    }

    public void setAllUtilClient4(List<Client> allUtilClient4) {
        this.allUtilClient4 = allUtilClient4;
    }

    public String getCreerClient() {
        return creerClient;
    }

    public void setCreerClient(String creerClient) {
        this.creerClient = creerClient;
    }

    public String getModifierClient() {
        return modifierClient;
    }

    public void setModifierClient(String modifierClient) {
        this.modifierClient = modifierClient;
    }

    public String getSupprimerClient() {
        return supprimerClient;
    }

    public void setSupprimerClient(String supprimerClient) {
        this.supprimerClient = supprimerClient;
    }

    public String getConsulterClient() {
        return consulterClient;
    }

    public void setConsulterClient(String consulterClient) {
        this.consulterClient = consulterClient;
    }

    public List<Actionmenu> getMyllActionmenus() {
        return myllActionmenus;
    }

    public void setMyllActionmenus(List<Actionmenu> myllActionmenus) {
        this.myllActionmenus = myllActionmenus;
    }

//    public DataModel getItem() {
//
//        return item;
//    }
//
//    public void setItem(DataModel item) {
//        this.item = item;
//    } 
    
    public List<Client> getItem() {

        return item;
    }

    public void setItem(List<Client> item) {
        this.item = item;
    }

    public List getListClients() {
        return listClients;
    }

    public void setListClients(List listClients) {
        this.listClients = listClients;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClientFromEdit() {
        return clientFromEdit;
    }

    public void setClientFromEdit(Client clientFromEdit) {
        this.clientFromEdit = clientFromEdit;
    }

    public String save() {
        try {
            ejbclient.insertClient(this.client);
            //ejbclient.create(this.client);
            this.client = new Client();
            return "clients";
        } catch (Exception e) {
            return "new_client";
        }
    }

    public String reset() {
        this.client = new Client();
        return "new_client";
    }

    public String resetModif() {
        return "clients";
    }

    public String editer() {
        try {
            //clientFromEdit = (Client) item.getRowData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "edit_client";
    }

    public String update() {
        try {
            UtilUtfconvert utfconvert = new UtilUtfconvert();
            clientFromEdit.setNomClient(utfconvert.convertFromUTF8(clientFromEdit.getNomClient()));
            clientFromEdit.setAdresse(utfconvert.convertFromUTF8(clientFromEdit.getAdresse()));
            ejbclient.edit(clientFromEdit);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "clients";
    }

    public String supprimer() {
        try {
            //clientFromEdit = (Client) item.getRowData();
            ejbclient.remove(clientFromEdit);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "clients";
    }

    public String testImpression() {
        try {
            String sourceFileName = "C:\\Users\\DV7\\Desktop\\MyReport\\reportTest1.jasper";

            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("logoReport", FacesContext.getCurrentInstance().getExternalContext().getRealPath("/rapport"));

            JasperPrint jasperPrint = JasperFillManager.fillReport(sourceFileName, parameters, new JREmptyDataSource());
            System.out.println("Jasper Print" + jasperPrint);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            String name = "doctest";
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + name + ".pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "clients";
    }

}
