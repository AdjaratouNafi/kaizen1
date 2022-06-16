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
import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;
import sn.accelsolution.entities.Valeuraction;

/**
 *
 * @author DV7
 */
@FacesConverter(value = "actionConverter")
public class ActionConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
        Object ret = null;
        if (arg1 instanceof PickList) {
            Object dualList = ((PickList) arg1).getValue();
            DualListModel dl = (DualListModel) dualList;
            for (Object obj : dl.getSource()) {
                String id = "" + ((Valeuraction) obj).getIdValeurAction();
                if (arg2.equals(id)) {
                    ret = obj;
                    break;
                }
            }
            if (ret == null) {
                for (Object obj : dl.getTarget()) {
                    String id = "" + ((Valeuraction) obj).getIdValeurAction();
                    if (arg2.equals(id)) {
                        ret = obj;
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
    if (arg2 instanceof Valeuraction) {
        str = "" + ((Valeuraction) arg2).getIdValeurAction();
    }
    return str;
}
}
