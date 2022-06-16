/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.util;

/**
 *
 * @author Will
 */
public class GenerateurCode {
    
    
    public GenerateurCode(){
        
    }
     public static int getRandomInteger(int maximum, int minimum) {
        return ((int) (Math.random() * (maximum - minimum))) + minimum;
    }

    public static String genCodeRepresentant(String CodePays) {
        return CodePays + getRandomInteger(000, 1000);
    }

    public static String genCodePays(String Osi) {
//        PaysMonde paysMonde = (this.PaysMondeService.findAll(pays.getDesignation(), "Libelle")).get(0);
//        String Osi = paysMonde.getCode3();
        return Osi + getRandomInteger(999, 100);
    }

    public static String  genCodeTransaction(String CodeRepresentant) {
        return CodeRepresentant + getRandomInteger(9999, 1000);
    }

    public static String genCodeGuichet(String CodePointVente) {
        return CodePointVente + getRandomInteger(999, 100);
    }

    public static String genCodeUtilisateur(String nom) {
        return nom + getRandomInteger(9999, 1000);
    }
    
    public static String genCodeActivation() {
        return getRandomInteger(9999, 1000)+"";
    }

    public static String genCodeClient(String CodePays) {
        return CodePays + getRandomInteger(9999, 1000) + "" + getRandomInteger(9999, 1000);
    }

    public static String genCodeConciergerie() {
        //Generation de 3 codes de (4 "+" 4 "+" 2) digits
        String Transact = getRandomInteger(9999, 1000) + "" + getRandomInteger(9999, 1000) + "" + getRandomInteger(99, 10);
        //Redécoupage du code sur (3 "+" 3 "+" 4) digits
        String CodeTransfert = Transact.substring(0, 3) + "" + Transact.substring(3, 6) + "" + Transact.substring(6, 10);
        return CodeTransfert;
    }
    
    public static String genMatricule() {
        //Generation de 3 codes de (4 "+" 4 "+" 2) digits
        String Transact = getRandomInteger(9999, 1000) + "" + getRandomInteger(9999, 1000) + "" + getRandomInteger(99, 10);
        //Redécoupage du code sur (3 "+" 3 "+" 4) digits
        String CodeTransfert = Transact.substring(0, 3) + "" + Transact.substring(3, 6) + "" + Transact.substring(6, 10);
        return CodeTransfert;
    }
    
    public int genererRandomInteger(int maximum, int minimum) {
        return ((int) (Math.random() * (maximum - minimum))) + minimum;
    } 
}
