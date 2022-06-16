/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.converter;

import antlr.StringUtils;
import java.io.Serializable;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sn.accelsolution.util.NumberManager;

/**
 *
 * @author DELL
 */
@ManagedBean
@FacesConverter("numberConverter")
public class numberConverter implements Serializable, Converter {

    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        return string.replaceAll(",", ".");
    }

    public String getAsString(FacesContext fc, UIComponent uic, Object o) {

//        String text = o.toString();
//        String insert = ".";
//        int period = 3;
//
//        Pattern pattern = Pattern.compile("0");
//        Matcher matcher = pattern.matcher(text);
//        int count = 0;
//        while (matcher.find()) {
//            count++;
//        }
//
//        String v1 = "";
//        String v2 = "";
//        String v3 = "";
//        String v4 = "";
//        String v5 = "";
//        String v6 = "";
//        String v7 = "";
//
//        if (text.length() > 6) {
//            for (int i = 0; i < text.length(); i++) {
//                if (i == 0) {
//                    v1 = Character.toString(text.charAt(i));
//                    System.out.println("Valeur :" + v1);
//                }
//                if (i == 1) {
//                    v2 = Character.toString(text.charAt(i));
//                }
//                if (i == 2) {
//                    v3 = Character.toString(text.charAt(i));
//                }
//                if (i == 3) {
//                    v4 = Character.toString(text.charAt(i));
//                }
//                if (i == 4) {
//                    v5 = Character.toString(text.charAt(i));
//                }
//                if (i == 5) {
//                    v6 = Character.toString(text.charAt(i));
//                }
//                if (i == 6) {
//                    v7 = Character.toString(text.charAt(i));
//                }
//            }
//
//            text = v1 + "." + v2 + v3 + v4 + "." + v5 + v6 + v7;
//
//            System.out.println("Traitement million :" + text);
//
//            return text;
//
//        } else {
//            StringBuilder builder = new StringBuilder(text.length() + insert.length() * (text.length() / period) + 1);
//
//            int index = 0;
//            String prefix = "";
//            while (index < text.length()) {
//                // Don't put the insert in the very first iteration.
//                // This is easier than appending it *after* each substring
//                builder.append(prefix);
//                prefix = insert;
//                builder.append(text.substring(index, Math.min(index + period, text.length())));
//                index += period;
//            }
//            return builder.toString();
        NumberManager nbm = new NumberManager();

        String result = nbm.convertManager(o.toString());
        
        return result;

    }

} 
