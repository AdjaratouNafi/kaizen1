/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
//import javax.persistence.Converter;
import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;
import sn.accelsolution.util.MenuUtl;
import sn.accelsolution.util.Module;

/**
 *
 * @author toshiba
 */
@FacesConverter(value = "menuFacesPickListConverter")
public class MenuFacesPickListConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
        Object ret = null;
        if (arg1 instanceof PickList) {
            Object dualList = ((PickList) arg1).getValue();
            DualListModel dl = (DualListModel) dualList;
            for (Object o : dl.getSource()) {
                String id = "" + ((MenuUtl) o).getIdMenu();
                if (arg2.equals(id)) {
                    ret = o;
                    break;
                }
            }
            if (ret == null) {
                for (Object o : dl.getTarget()) {
                    String id = "" + ((MenuUtl) o).getIdMenu();
                    if (arg2.equals(id)) {
                        ret = o;
                        break;
                    }
                }
            }
        }
        return ret;
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
    String str = "";
    if (arg2 instanceof MenuUtl) {
        str = "" + ((MenuUtl) arg2).getIdMenu();
    }
    return str;
}
}
