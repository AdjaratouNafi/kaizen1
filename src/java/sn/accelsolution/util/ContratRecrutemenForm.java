/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.util;

import java.io.Serializable;
import sn.accelsolution.entities.Utilisateur;

/**
 *
 * @author DELL
 */
public class ContratRecrutemenForm implements Serializable {
    
    private Utilisateur utilisateur;
    
    private String durreeTravail;
    private String classification;
    private String article1;
    private String article2;
    private String article3;
    private String article4;
    private String article5;
    private String article6;
    private String article7SalaireBase;
    private String article7SurSalaire;
    private String article7SalaireBrutFiscal;
    private String article7SalaireTransport;
    private String article8;
    private String article8P;
    private String article9;
    private String article10;
    private String article11;
    private String article12;
    private String article13;
    private String article14;
    private String article15;
    private String article16;
    private String diffTypeContrat;
            
    public ContratRecrutemenForm(){
    }

    public String getArticle7SalaireTransport() {
        return article7SalaireTransport;
    }

    public void setArticle7SalaireTransport(String article7SalaireTransport) {
        this.article7SalaireTransport = article7SalaireTransport;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getDurreeTravail() {
        return durreeTravail;
    }

    public void setDurreeTravail(String durreeTravail) {
        this.durreeTravail = durreeTravail;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    
    public String getArticle1() {
        return article1;
    }

    public void setArticle1(String article1) {
        this.article1 = article1;
    }

    public String getArticle2() {
        return article2;
    }

    public void setArticle2(String article2) {
        this.article2 = article2;
    }

    public String getArticle3() {
        return article3;
    }

    public void setArticle3(String article3) {
        this.article3 = article3;
    }

    public String getArticle4() {
        return article4;
    }

    public void setArticle4(String article4) {
        this.article4 = article4;
    }

    public String getArticle5() {
        return article5;
    }

    public void setArticle5(String article5) {
        this.article5 = article5;
    }

    public String getArticle6() {
        return article6;
    }

    public void setArticle6(String article6) {
        this.article6 = article6;
    }

    public String getArticle7SalaireBase() {
        return article7SalaireBase;
    }

    public void setArticle7SalaireBase(String article7SalaireBase) {
        this.article7SalaireBase = article7SalaireBase;
    }

    public String getArticle7SurSalaire() {
        return article7SurSalaire;
    }

    public void setArticle7SurSalaire(String article7SurSalaire) {
        this.article7SurSalaire = article7SurSalaire;
    }

    public String getArticle7SalaireBrutFiscal() {
        return article7SalaireBrutFiscal;
    }

    public void setArticle7SalaireBrutFiscal(String article7SalaireBrutFiscal) {
        this.article7SalaireBrutFiscal = article7SalaireBrutFiscal;
    }

    public String getArticle8() {
        return article8;
    }

    public void setArticle8(String article8) {
        this.article8 = article8;
    }

    public String getArticle9() {
        return article9;
    }

    public void setArticle9(String article9) {
        this.article9 = article9;
    }

    public String getArticle10() {
        return article10;
    }

    public void setArticle10(String article10) {
        this.article10 = article10;
    }

    public String getArticle11() {
        return article11;
    }

    public void setArticle11(String article11) {
        this.article11 = article11;
    }

    public String getArticle12() {
        return article12;
    }

    public void setArticle12(String article12) {
        this.article12 = article12;
    }

    public String getArticle13() {
        return article13;
    }

    public void setArticle13(String article13) {
        this.article13 = article13;
    }

    public String getArticle14() {
        return article14;
    }

    public void setArticle14(String article14) {
        this.article14 = article14;
    }

    public String getArticle15() {
        return article15;
    }

    public void setArticle15(String article15) {
        this.article15 = article15;
    }

    public String getArticle16() {
        return article16;
    }

    public void setArticle16(String article16) {
        this.article16 = article16;
    }

    public String getArticle8P() {
        return article8P;
    }

    public void setArticle8P(String article8P) {
        this.article8P = article8P;
    }

    public String getDiffTypeContrat() {
        return diffTypeContrat;
    }

    public void setDiffTypeContrat(String diffTypeContrat) {
        this.diffTypeContrat = diffTypeContrat;
    }

    @Override
    public String toString() {
        return "ContratRecrutemenForm{" + "utilisateur=" + utilisateur + ", durreeTravail=" + durreeTravail + ", article1=" + article1 + ", article2=" + article2 + ", article3=" + article3 + ", article4=" + article4 + ", article5=" + article5 + ", article6=" + article6 + ", article7SalaireBase=" + article7SalaireBase + ", article7SurSalaire=" + article7SurSalaire + ", article7SalaireBrutFiscal=" + article7SalaireBrutFiscal + ", article8=" + article8 + ", diffTypeContrat=" + diffTypeContrat + '}';
    }
    
    
    
    
}
