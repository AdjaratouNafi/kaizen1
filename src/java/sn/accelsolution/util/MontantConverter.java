/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 *
 * @author DV7
 */
public class MontantConverter {

    public MontantConverter() {

    }

    public Double StringToDouble(String number) {
        String numberTransform = this.removeCaractereString(number);
        Double result = Double.valueOf(numberTransform);

        return result;
    }

    public String DoubleToString(Double number) {

        Locale locale = new Locale("en", "UK");

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
        //symbols.setDecimalSeparator(';');
        symbols.setDecimalSeparator('.');
        symbols.setGroupingSeparator('.');

        String pattern = "#,##0.###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);

        String numberR = decimalFormat.format(number);

        return numberR;
    }

    public String removeCaractereString(String myString) {
        String numberTransform1 = myString.replaceAll("\\.", " ");
        String numberTransform2 = numberTransform1.replaceAll("\\s", "");
        return numberTransform2;
    }

    public String ExtractSubString(String myString) {
        String result;
        result = myString.substring(0, myString.indexOf(","));
        return result;
    }
    
    public String ExtractSubStringTwo(String myString) {
        String result;
        result = myString.substring(0, myString.indexOf("."));
        return result;
    }

    public String CalculeValeurInventaire(String pu, int montant) {

        String puc = this.removeCaractereString(pu);
        Double puD = this.StringToDouble(puc);

        Double rs = montant * puD;
        String rsC = this.DoubleToString(rs);

        return rsC;

    }

    public int CalculeNewQtStock(int oldQts, int newQt) {

        return oldQts + newQt;

    }
}
