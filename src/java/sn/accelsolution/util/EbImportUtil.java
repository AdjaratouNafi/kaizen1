/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.util;

/**
 *
 * @author DV7
 */
public class EbImportUtil {
    private String designation;
    private String reference;
    private String um;
    private String qt;
    
    public EbImportUtil(){
        
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getUm() {
        return um;
    }

    public void setUm(String um) {
        this.um = um;
    }

    public String getQt() {
        return qt;
    }

    public void setQt(String qt) {
        this.qt = qt;
    }

    @Override
    public String toString() {
        return "EbImportUtil{" + "designation=" + designation + ", reference=" + reference + ", um=" + um + ", qt=" + qt + '}';
    }

}
