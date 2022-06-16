/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;  

/**
 *
 * @author 2000
 */
@ManagedBean
@FacesConverter   
public class BeanStringConverter implements Converter{ 

   @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
         return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value.toString();
    }  
    
}
 