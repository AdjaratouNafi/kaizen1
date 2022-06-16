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
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;
import sn.accelsolution.dao.RoleFacade;
import sn.accelsolution.entities.Actionmenu;
import sn.accelsolution.entities.Menu;    
import sn.accelsolution.entities.Role;
import sn.accelsolution.util.ReportAccelModel;  
import sn.accelsolution.util.UtilControleMenu;           
import sn.accelsolution.util.UtilUtfconvert;
 
/**
 *
 * @author DELL
 */
@ManagedBean
@RequestScoped 
public class RoleBean implements Serializable { 

    @EJB 
    RoleFacade ejbrole; 

    private DataModel item;
    private List listRoles;
    private Role role;
    private Role roleFromEdit;
    private String consulterRole;
    private String modifierRole;
    private String supprimerRole;
    private String creerRole;
    private List<Actionmenu> myllActionmenus;

    /**
     * Creates a new instance of RoleBean
     */
    @PostConstruct
    public void init() {

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UtilControleMenu utilControleMenu = new UtilControleMenu();
        
        
        myllActionmenus = (List<Actionmenu>) session.getAttribute("allActionmenusTraitement"); 
        
        this.setCreerRole(utilControleMenu.controleCreerRole(myllActionmenus));
        this.setModifierRole(utilControleMenu.controleModifierRole(myllActionmenus));
        this.setSupprimerRole(utilControleMenu.controleSupprimerRole(myllActionmenus));
        this.setConsulterRole(utilControleMenu.controleConsulterRole(myllActionmenus));

    }

    public RoleBean() {
        role = new Role();
        roleFromEdit = new Role();
    }

    public List<Actionmenu> getMyllActionmenus() {
        return myllActionmenus;
    }

    public void setMyllActionmenus(List<Actionmenu> myllActionmenus) {
        this.myllActionmenus = myllActionmenus;
    }

    public String getConsulterRole() {
        return consulterRole;
    }

    public void setConsulterRole(String consulterRole) {
        this.consulterRole = consulterRole;
    }

    public String getModifierRole() {
        return modifierRole;
    }

    public void setModifierRole(String modifierRole) {
        this.modifierRole = modifierRole;
    }

    public String getSupprimerRole() {
        return supprimerRole;
    }

    public void setSupprimerRole(String supprimerRole) {
        this.supprimerRole = supprimerRole;
    }

    public String getCreerRole() {
        return creerRole;
    }

    public void setCreerRole(String creerRole) {
        this.creerRole = creerRole;
    }

    public RoleFacade getEjbrole() {
        return ejbrole;
    }

    public void setEjbrole(RoleFacade ejbrole) {
        this.ejbrole = ejbrole;
    }

    public DataModel getItem() {
        item = new ListDataModel();
        this.setListRoles(ejbrole.findAll());
        item.setWrappedData(this.getListRoles());
        return item;
    }

    public void setItem(DataModel item) {
        this.item = item;
    }

    public List getListRoles() {
        this.listRoles = ejbrole.findAll();
        return listRoles;
    }

    public void setListRoles(List listRoles) {
        this.listRoles = listRoles;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRoleFromEdit() {
        return roleFromEdit;
    }

    public void setRoleFromEdit(Role roleFromEdit) {
        this.roleFromEdit = roleFromEdit;
    }

    public String save() {
        try {
            ejbrole.insertRole(role);
            return "roles";
        } catch (Exception e) {
            return "new_roles";
        }
    }

    public void reset() {
        this.role = new Role();
    }

    public String resetModif() {
        return "roles";
    }

    public String editer() {
        try {
            roleFromEdit = (Role) item.getRowData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "edit_roles";
    }

    public String update() {
        try { 
            UtilUtfconvert utfconvert = new UtilUtfconvert();
            roleFromEdit.setLibelleRole(utfconvert.convertFromUTF8(roleFromEdit.getLibelleRole()));
            ejbrole.edit(roleFromEdit);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "roles";
    }

    public String supprimer() {
        try {
            roleFromEdit = (Role) item.getRowData();
            ejbrole.remove(roleFromEdit);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "roles";
    } 
    
//    public String imprimer() {
//        try {
//            ReportAccelModel reportModel = new ReportAccelModel();
//            reportModel.GenererCMB(); 
//            return "roles";
//        } catch (Exception e) {
//            return "new_roles";
//        }
//    }

}
