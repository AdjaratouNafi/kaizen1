/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.util;

import java.util.List;
import sn.accelsolution.entities.Actionactif;
import sn.accelsolution.entities.Droitacces;
import sn.accelsolution.entities.Menu;

/**
 *
 * @author DV7
 */
public class GestionMenuActif {
    private Droitacces droitacces;
    private Menu menu;
    private Actionactif actionactif;
    private List<Menu> listMenus;
    
    public GestionMenuActif (){
        
    } 
    
    public GestionMenuActif (Droitacces droitacce, Menu menu, Actionactif actionactif, List<Menu> listMenus){
        this.droitacces = droitacce;
        this.menu = menu;
        this.actionactif = actionactif;
        this.listMenus = listMenus;
    }

    public Droitacces getDroitacces() {
        return droitacces;
    }

    public void setDroitacces(Droitacces droitacces) {
        this.droitacces = droitacces;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Actionactif getActionactif() {
        return actionactif;
    }

    public void setActionactif(Actionactif actionactif) {
        this.actionactif = actionactif;
    } 

    public List<Menu> getListMenus() {
        return listMenus;
    }

    public void setListMenus(List<Menu> listMenus) {
        this.listMenus = listMenus;
    }
    
    
}
