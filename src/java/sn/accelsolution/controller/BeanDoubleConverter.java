/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.controller;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author 2000
 */
@ManagedBean
@FacesConverter
public class BeanDoubleConverter implements Converter {

    private static final Pattern PHONE_PATTERN = Pattern.compile("(\\d{3})-(\\d{3})-(\\d{4})");

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Matcher matcher = PHONE_PATTERN.matcher(value);
        if (!matcher.matches()) {
            throw new RuntimeException(
                    String.format("invalid input: %s. The valid format is of type %s%n",
                            value, "111-111-1111"));
        }
        String areaCode = matcher.group(1);
        String exchange = matcher.group(2);
        String line = matcher.group(3);
        Phone p = new Phone();
        p.setAreaCode(areaCode);
        p.setExchangeCode(exchange);
        p.setLineNumber(line);
        return p;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Phone phone = (Phone) value;
        return String.format("%s-%s-%s",
                phone.getAreaCode(), phone.getExchangeCode(), phone.getLineNumber());
    }
}
