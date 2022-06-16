/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.util;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 *
 * @author DV7
 */
public class GenerationCodePdf {

    public GenerationCodePdf() {

    }

    public String genererCodeDevis(String ancienCode) {
        String result = "";

        /*Geneeration du numero*/
        String sousChaine = ancienCode.substring(ancienCode.lastIndexOf("/") + 1);
        int lastNumber = Integer.parseInt(sousChaine);
        int newNumber = lastNumber + 1;

        /*Concatenation de la date*/
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        
        /*Concatenation finale*/
        result = "D"+day+""+month+""+year+"/"+newNumber;
        return result;
    }  
    
    public String genererCodeFacture(String ancienCode) {
        String result = "";

        /*Geneeration du numero*/
        String sousChaine = ancienCode.substring(ancienCode.lastIndexOf("/") + 1);
        int lastNumber = Integer.parseInt(sousChaine);
        int newNumber = lastNumber + 1;

        /*Concatenation de la date*/
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        
        /*Concatenation finale*/
        result = "F"+day+""+month+""+year+"/"+newNumber;
        return result;
    }  
    
    public String genererCodeDecompte(String ancienCode) {
        String result = "";

        /*Geneeration du numero*/
        String sousChaine = ancienCode.substring(ancienCode.lastIndexOf("/") + 1);
        int lastNumber = Integer.parseInt(sousChaine);
        int newNumber = lastNumber + 1;

        /*Concatenation de la date*/
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        
        /*Concatenation finale*/
        result = "FD"+day+""+month+""+year+"/"+newNumber;
        return result;
    } 
    
    public String genererCodeExpression(String ancienCode) {
        String result = "";

        /*Geneeration du numero*/
        String sousChaine = ancienCode.substring(ancienCode.lastIndexOf("/") + 1);
        int lastNumber = Integer.parseInt(sousChaine);
        int newNumber = lastNumber + 1;

        /*Concatenation de la date*/
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        
        /*Concatenation finale*/
        result = "EXP"+day+""+month+""+year+"/"+newNumber;
        return result;
    } 
    
    public String genererCodeCommande(String ancienCode) {
        String result = "";

        /*Geneeration du numero*/
        String sousChaine = ancienCode.substring(ancienCode.lastIndexOf("/") + 1);
        int lastNumber = Integer.parseInt(sousChaine);
        int newNumber = lastNumber + 1;

        /*Concatenation de la date*/
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        
        /*Concatenation finale*/
        result = "C"+day+""+month+""+year+"/"+newNumber;
        return result;
    }  
    
    public String genererCodeAcompte(String ancienCode) {
        String result = "";

        /*Geneeration du numero*/
        String sousChaine = ancienCode.substring(ancienCode.lastIndexOf("/") + 1);
        int lastNumber = Integer.parseInt(sousChaine);
        int newNumber = lastNumber + 1;

        /*Concatenation de la date*/
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        
        /*Concatenation finale*/
        result = "ACP"+day+""+month+""+year+"/"+newNumber;
        return result;
    } 
    
    public String genererCodeAcompteFournisseur(String ancienCode) {
        String result = "";

        /*Geneeration du numero*/
        String sousChaine = ancienCode.substring(ancienCode.lastIndexOf("/") + 1);
        int lastNumber = Integer.parseInt(sousChaine);
        int newNumber = lastNumber + 1;

        /*Concatenation de la date*/
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        
        /*Concatenation finale*/
        result = "ACF"+day+""+month+""+year+"/"+newNumber;
        return result;
    }
    
    public String genererInitiales(String nom, String prenom){
        char char1 = nom.charAt(0);
        char char2 = prenom.charAt(0);
        String resutl = char1+""+char2;
        return resutl;
    }

}
