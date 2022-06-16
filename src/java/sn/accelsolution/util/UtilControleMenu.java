/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.util;

import java.util.List;
import sn.accelsolution.entities.Actionmenu;

/**
 *
 * @author DV7
 */
public class UtilControleMenu {

    public UtilControleMenu() {

    }

    public String controleCreerRole(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String creer = "";
        String getCreer = "";

        for (Actionmenu actionmenu : myllActionmenus) {

            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Creer")) {
                getCreer = actionmenu.getLibelleaction();
            }

        }
        System.out.println("Valeur creer: " + getCreer);
        if (getCreer.equals("Creer")) {
            creer = "yes";
        }else{
            creer = "no";
        }

        return creer;
    }

    public String controleModifierRole(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String modifier = "";
        String getModifier = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            //  System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Modifier")) {
                getModifier = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getModifier);
        if (getModifier.equals("Modifier")) {
            modifier = "yes";
        }else{
            modifier = "no";
        }

        return modifier;
    }

    public String controleSupprimerRole(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String supprimer = "";
        String getSupprimer = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Supprimer")) {
                getSupprimer = actionmenu.getLibelleaction();
            }
        }

        System.out.println("Valeur creer: " + getSupprimer);
        if (getSupprimer.equals("Supprimer")) {
            supprimer = "yes";
        }else{
            supprimer = "no";
        }

        return supprimer;
    }

    public String controleConsulterRole(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String consulter = "";
        String getConsulter = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Consulter")) {
                getConsulter = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getConsulter);
        if (getConsulter.equals("Consulter")) {
            consulter = "yes";
        }else{
            consulter = "no";
        }

        return consulter;
    }

    public String controleCreerUser(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String creer = "";
        String getCreer = "";

        for (Actionmenu actionmenu : myllActionmenus) {

            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Creer")) {
                getCreer = actionmenu.getLibelleaction();
            }

        }
        System.out.println("Valeur creer: " + getCreer);
        if (getCreer.equals("Creer")) {
            creer = "yes";
        }else{
            creer = "no";
        }

        return creer;
    }

    public String controleModifierUser(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String modifier = "";
        String getModifier = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            //  System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Modifier")) {
                getModifier = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getModifier);
        if (getModifier.equals("Modifier")) {
            modifier = "yes";
        }else{
             modifier = "no";
        }

        return modifier;
    }

    public String controleSupprimerUser(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String supprimer = "";
        String getSupprimer = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Supprimer")) {
                getSupprimer = actionmenu.getLibelleaction();
            }
        }

        System.out.println("Valeur creer: " + getSupprimer);
        if (getSupprimer.equals("Supprimer")) {
            supprimer = "yes";
        }else{
             supprimer = "no";
        }

        return supprimer;
    }

    public String controleConsulterUser(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String consulter = "";
        String getConsulter = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Consulter")) {
                getConsulter = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getConsulter);
        if (getConsulter.equals("Consulter")) {
            consulter = "yes";
        }else{
            consulter = "no"; 
        }

        return consulter;
    }

    public String creerRecrutement(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String creer = "";
        String getCreer = "";

        for (Actionmenu actionmenu : myllActionmenus) {

            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Creer")) {
                getCreer = actionmenu.getLibelleaction();
            }

        }
        System.out.println("Valeur creer: " + getCreer);
        if (getCreer.equals("Creer")) {
            creer = "yes";
        }else{
            creer = "no"; 
        }

        return creer;
    }

    public String modifierRecrutement(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String modifier = "";
        String getModifier = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            //  System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Modifier")) {
                getModifier = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getModifier);
        if (getModifier.equals("Modifier")) {
            modifier = "yes";
        }else{
            modifier = "no"; 
        }

        return modifier;
    }

    public String supprimerRecrutement(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String supprimer = "";
        String getSupprimer = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Supprimer")) {
                getSupprimer = actionmenu.getLibelleaction();
            }
        }

        System.out.println("Valeur creer: " + getSupprimer);
        if (getSupprimer.equals("Supprimer")) {
            supprimer = "yes";
        }else{
            supprimer = "no"; 
        }

        return supprimer;
    }

    public String consulterRecrutement(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String consulter = "";
        String getConsulter = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Consulter")) {
                getConsulter = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getConsulter);
        if (getConsulter.equals("Consulter")) {
            consulter = "yes";
        }else{
            consulter = "no"; 
        }

        return consulter;
    }

    public String imprimerRecrutement(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String imprimer = "";
        String getImprimer = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Imprimer")) {
                getImprimer = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getImprimer);
        if (getImprimer.equals("Imprimer")) {
            imprimer = "yes";
        }else{
            imprimer = "no"; 
        }

        return imprimer;
    }

    public String creerStage(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String creer = "";
        String getCreer = "";

        for (Actionmenu actionmenu : myllActionmenus) {

            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Creer")) {
                getCreer = actionmenu.getLibelleaction();
            }

        }
        System.out.println("Valeur creer: " + getCreer);
        if (getCreer.equals("Creer")) {
            creer = "yes";
        }else{
            creer = "no"; 
        }

        return creer;
    }

    public String modifierStage(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String modifier = "";
        String getModifier = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            //  System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Modifier")) {
                getModifier = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getModifier);
        if (getModifier.equals("Modifier")) {
            modifier = "yes";
        }else{
            modifier = "no"; 
        }

        return modifier;
    }

    public String supprimerStage(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String supprimer = "";
        String getSupprimer = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Supprimer")) {
                getSupprimer = actionmenu.getLibelleaction();
            }
        }

        System.out.println("Valeur creer: " + getSupprimer);
        if (getSupprimer.equals("Supprimer")) {
            supprimer = "yes";
        }else{
            supprimer = "no"; 
        }

        return supprimer;
    }

    public String consulterStage(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String consulter = "";
        String getConsulter = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Consulter")) {
                getConsulter = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getConsulter);
        if (getConsulter.equals("Consulter")) {
            consulter = "yes";
        }else{
            consulter = "no"; 
        }

        return consulter;
    }

    public String imprimerStage(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String imprimer = "";
        String getImprimer = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Imprimer")) {
                getImprimer = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getImprimer);
        if (getImprimer.equals("Imprimer")) {
            imprimer = "yes";
        }else{
            imprimer = "no"; 
        }

        return imprimer;
    }

    public String creerSalaire(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String creer = "";
        String getCreer = "";

        for (Actionmenu actionmenu : myllActionmenus) {

            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Creer")) {
                getCreer = actionmenu.getLibelleaction();
            }

        }
        System.out.println("Valeur creer: " + getCreer);
        if (getCreer.equals("Creer")) {
            creer = "yes";
        }else{
            creer = "no"; 
        }

        return creer;
    }

    public String modifierSalaire(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String modifier = "";
        String getModifier = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            //  System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Modifier")) {
                getModifier = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getModifier);
        if (getModifier.equals("Modifier")) {
            modifier = "yes";
        }else{
            modifier = "no"; 
        }

        return modifier;
    }

    public String supprimerSalaire(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String supprimer = "";
        String getSupprimer = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Supprimer")) {
                getSupprimer = actionmenu.getLibelleaction();
            }
        }

        System.out.println("Valeur creer: " + getSupprimer);
        if (getSupprimer.equals("Supprimer")) {
            supprimer = "yes";
        }else{
            supprimer = "no"; 
        }

        return supprimer;
    }

    public String consulterSalaire(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String consulter = "";
        String getConsulter = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Consulter")) {
                getConsulter = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getConsulter);
        if (getConsulter.equals("Consulter")) {
            consulter = "yes";
        }else{
            consulter = "no"; 
        }

        return consulter;
    }

    public String imprimerSalaire(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String imprimer = "";
        String getImprimer = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Imprimer")) {
                getImprimer = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getImprimer);
        if (getImprimer.equals("Imprimer")) {
            imprimer = "yes";
        }else{
            imprimer = "no"; 
        }

        return imprimer;
    }

    public String validerSalaire(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String valider = "";
        String getValider = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Valider")) {
                getValider = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getValider);
        if (getValider.equals("Valider")) {
            valider = "yes";
        }else{
            valider = "no"; 
        }

        return valider;
    }

    public String creerPret(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String creer = "";
        String getCreer = "";

        for (Actionmenu actionmenu : myllActionmenus) {

            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Creer")) {
                getCreer = actionmenu.getLibelleaction();
            }

        }
        System.out.println("Valeur creer: " + getCreer);
        if (getCreer.equals("Creer")) {
            creer = "yes";
        }else{
            creer = "no"; 
        }

        return creer;
    }

    public String modifierPret(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String modifier = "";
        String getModifier = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            //  System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Modifier")) {
                getModifier = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getModifier);
        if (getModifier.equals("Modifier")) {
            modifier = "yes";
        }else{
            modifier = "no"; 
        }

        return modifier;
    }

    public String supprimerPret(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String supprimer = "";
        String getSupprimer = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Supprimer")) {
                getSupprimer = actionmenu.getLibelleaction();
            }
        }

        System.out.println("Valeur creer: " + getSupprimer);
        if (getSupprimer.equals("Supprimer")) {
            supprimer = "yes";
        }else{
            supprimer = "no"; 
        }

        return supprimer;
    }

    public String consulterPret(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String consulter = "";
        String getConsulter = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Consulter")) {
                getConsulter = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getConsulter);
        if (getConsulter.equals("Consulter")) {
            consulter = "yes";
        }else{
            consulter = "no"; 
        }

        return consulter;
    }

    public String imprimerPret(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String imprimer = "";
        String getImprimer = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Imprimer")) {
                getImprimer = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getImprimer);
        if (getImprimer.equals("Imprimer")) {
            imprimer = "yes";
        }else{
            imprimer = "no"; 
        }

        return imprimer;
    }

    public String validerPret(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String valider = "";
        String getValider = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Valider")) {
                getValider = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getValider);
        if (getValider.equals("Valider")) {
            valider = "yes";
        }else{
            valider = "no"; 
        }

        return valider;
    }

    public String creerIndemnite(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String creer = "";
        String getCreer = "";

        for (Actionmenu actionmenu : myllActionmenus) {

            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Creer")) {
                getCreer = actionmenu.getLibelleaction();
            }

        }
        System.out.println("Valeur creer: " + getCreer);
        if (getCreer.equals("Creer")) {
            creer = "yes";
        }else{
            creer = "no"; 
        }

        return creer;
    }

    public String modifierIndemnite(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String modifier = "";
        String getModifier = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            //  System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Modifier")) {
                getModifier = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getModifier);
        if (getModifier.equals("Modifier")) {
            modifier = "yes";
        }else{
            modifier = "no"; 
        }

        return modifier;
    }

    public String supprimerIndemnite(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String supprimer = "";
        String getSupprimer = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Supprimer")) {
                getSupprimer = actionmenu.getLibelleaction();
            }
        }

        System.out.println("Valeur creer: " + getSupprimer);
        if (getSupprimer.equals("Supprimer")) {
            supprimer = "yes";
        }else{
            supprimer = "no"; 
        }

        return supprimer;
    }

    public String consulterIndemnite(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String consulter = "";
        String getConsulter = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Consulter")) {
                getConsulter = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getConsulter);
        if (getConsulter.equals("Consulter")) {
            consulter = "yes";
        }else{
            consulter = "no"; 
        }

        return consulter;
    }

    public String imprimerIndemnite(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String imprimer = "";
        String getImprimer = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Imprimer")) {
                getImprimer = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getImprimer);
        if (getImprimer.equals("Imprimer")) {
            imprimer = "yes";
        }else{
            imprimer = "no"; 
        }

        return imprimer;
    }

    public String validerIndemnite(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String valider = "";
        String getValider = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Valider")) {
                getValider = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getValider);
        if (getValider.equals("Valider")) {
            valider = "yes";
        }else{
            valider = "no"; 
        }

        return valider;
    }

    public String creerClient(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String creer = "";
        String getCreer = "";

        for (Actionmenu actionmenu : myllActionmenus) {

            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Creer")) {
                getCreer = actionmenu.getLibelleaction();
            }

        }
        System.out.println("Valeur creer: " + getCreer);
        if (getCreer.equals("Creer")) {
            creer = "yes";
        }else{
            creer = "no"; 
        }

        return creer;
    }

    public String modifierClient(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String modifier = "";
        String getModifier = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            //  System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Modifier")) {
                getModifier = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getModifier);
        if (getModifier.equals("Modifier")) {
            modifier = "yes";
        }else{
            modifier = "no"; 
        }

        return modifier;
    }

    public String supprimerClient(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String supprimer = "";
        String getSupprimer = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Supprimer")) {
                getSupprimer = actionmenu.getLibelleaction();
            }
        }

        System.out.println("Valeur creer: " + getSupprimer);
        if (getSupprimer.equals("Supprimer")) {
            supprimer = "yes";
        }else{
            supprimer = "no"; 
        }

        return supprimer;
    }

    public String consulterClient(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String consulter = "";
        String getConsulter = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Consulter")) {
                getConsulter = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getConsulter);
        if (getConsulter.equals("Consulter")) {
            consulter = "yes";
        }else{
            consulter = "no"; 
        }

        return consulter;
    }

    public String creerDevis(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String creer = "";
        String getCreer = "";

        for (Actionmenu actionmenu : myllActionmenus) {

            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Creer")) {
                getCreer = actionmenu.getLibelleaction();
            }

        }
        System.out.println("Valeur creer: " + getCreer);
        if (getCreer.equals("Creer")) {
            creer = "yes";
        }else{
            creer = "no"; 
        }

        return creer;
    }

    public String modifierDevis(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String modifier = "";
        String getModifier = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            //  System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Modifier")) {
                getModifier = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getModifier);
        if (getModifier.equals("Modifier")) {
            modifier = "yes";
        }else{
            modifier = "no"; 
        }

        return modifier;
    }

    public String supprimerDevis(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String supprimer = "";
        String getSupprimer = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Supprimer")) {
                getSupprimer = actionmenu.getLibelleaction();
            }
        }

        System.out.println("Valeur creer: " + getSupprimer);
        if (getSupprimer.equals("Supprimer")) {
            supprimer = "yes";
        }else{
            supprimer = "no"; 
        }

        return supprimer;
    }

    public String consulterDevis(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String consulter = "";
        String getConsulter = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Consulter")) {
                getConsulter = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getConsulter);
        if (getConsulter.equals("Consulter")) {
            consulter = "yes";
        }else{
            consulter = "no"; 
        }

        return consulter;
    }

    public String imprimerDevis(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String imprimer = "";
        String getImprimer = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Imprimer")) {
                getImprimer = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getImprimer);
        if (getImprimer.equals("Imprimer")) {
            imprimer = "yes";
        }else{
            imprimer = "no"; 
        }

        return imprimer;
    }

    public String creerMarcher(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String creer = "";
        String getCreer = "";

        for (Actionmenu actionmenu : myllActionmenus) {

            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Creer")) {
                getCreer = actionmenu.getLibelleaction();
            }

        }
        System.out.println("Valeur creer: " + getCreer);
        if (getCreer.equals("Creer")) {
            creer = "yes";
        }else{
            creer = "no"; 
        }

        return creer;
    }

    public String modifierMarcher(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String modifier = "";
        String getModifier = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            //  System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Modifier")) {
                getModifier = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getModifier);
        if (getModifier.equals("Modifier")) {
            modifier = "yes";
        }else{
            modifier = "no"; 
        }

        return modifier;
    }

    public String supprimerMarcher(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String supprimer = "";
        String getSupprimer = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Supprimer")) {
                getSupprimer = actionmenu.getLibelleaction();
            }
        }

        System.out.println("Valeur creer: " + getSupprimer);
        if (getSupprimer.equals("Supprimer")) {
            supprimer = "yes";
        }else{
            supprimer = "no"; 
        }

        return supprimer;
    }

    public String consulterMarcher(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String consulter = "";
        String getConsulter = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Consulter")) {
                getConsulter = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getConsulter);
        if (getConsulter.equals("Consulter")) {
            consulter = "yes";
        }else{
            consulter = "no"; 
        }

        return consulter;
    }

    public String imprimerMarcher(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String imprimer = "";
        String getImprimer = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Imprimer")) {
                getImprimer = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getImprimer);
        if (getImprimer.equals("Imprimer")) {
            imprimer = "yes";
        }else{
            imprimer = "no"; 
        }

        return imprimer;
    }

    public String creerFacture(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String creer = "";
        String getCreer = "";

        for (Actionmenu actionmenu : myllActionmenus) {

            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Creer")) {
                getCreer = actionmenu.getLibelleaction();
            }

        }
        System.out.println("Valeur creer: " + getCreer);
        if (getCreer.equals("Creer")) {
            creer = "yes";
        }else{
            creer = "no"; 
        }

        return creer;
    }

    public String modifierFacture(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String modifier = "";
        String getModifier = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            //  System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Modifier")) {
                getModifier = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getModifier);
        if (getModifier.equals("Modifier")) {
            modifier = "yes";
        }else{
            modifier = "no"; 
        }

        return modifier;
    }

    public String supprimerFacture(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String supprimer = "";
        String getSupprimer = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Supprimer")) {
                getSupprimer = actionmenu.getLibelleaction();
            }
        }

        System.out.println("Valeur creer: " + getSupprimer);
        if (getSupprimer.equals("Supprimer")) {
            supprimer = "yes";
        }else{
            supprimer = "no"; 
        }

        return supprimer;
    }

    public String consulterFacture(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String consulter = "";
        String getConsulter = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Consulter")) {
                getConsulter = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getConsulter);
        if (getConsulter.equals("Consulter")) {
            consulter = "yes";
        }else{
            consulter = "no"; 
        }

        return consulter;
    }

    public String imprimerFacture(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String imprimer = "";
        String getImprimer = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Imprimer")) {
                getImprimer = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getImprimer);
        if (getImprimer.equals("Imprimer")) {
            imprimer = "yes";
        }else{
            imprimer = "no"; 
        }

        return imprimer;
    }

    public String creerDecompte(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String creer = "";
        String getCreer = "";

        for (Actionmenu actionmenu : myllActionmenus) {

            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Creer")) {
                getCreer = actionmenu.getLibelleaction();
            }

        }
        System.out.println("Valeur creer: " + getCreer);
        if (getCreer.equals("Creer")) {
            creer = "yes";
        }else{
            creer = "no"; 
        }

        return creer;
    }

    public String modifierDecompte(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String modifier = "";
        String getModifier = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            //  System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Modifier")) {
                getModifier = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getModifier);
        if (getModifier.equals("Modifier")) {
            modifier = "yes";
        }else{
            modifier = "no"; 
        }

        return modifier;
    }

    public String supprimerDecompte(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String supprimer = "";
        String getSupprimer = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Supprimer")) {
                getSupprimer = actionmenu.getLibelleaction();
            }
        }

        System.out.println("Valeur creer: " + getSupprimer);
        if (getSupprimer.equals("Supprimer")) {
            supprimer = "yes";
        }else{
            supprimer = "no"; 
        }

        return supprimer;
    }

    public String consulterDecompte(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String consulter = "";
        String getConsulter = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Consulter")) {
                getConsulter = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getConsulter);
        if (getConsulter.equals("Consulter")) {
            consulter = "yes";
        }else{
            consulter = "no"; 
        }

        return consulter;
    }

    public String imprimerDecompte(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String imprimer = "";
        String getImprimer = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Imprimer")) {
                getImprimer = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getImprimer);
        if (getImprimer.equals("Imprimer")) {
            imprimer = "yes";
        }else{
            imprimer = "no"; 
        }

        return imprimer;
    }

    public String creerChantier(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String creer = "";
        String getCreer = "";

        for (Actionmenu actionmenu : myllActionmenus) {

            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Creer")) {
                getCreer = actionmenu.getLibelleaction();
            }

        }
        System.out.println("Valeur creer: " + getCreer);
        if (getCreer.equals("Creer")) {
            creer = "yes";
        }else{
            creer = "no"; 
        }

        return creer;
    }

    public String modifierChantier(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String modifier = "";
        String getModifier = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            //  System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Modifier")) {
                getModifier = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getModifier);
        if (getModifier.equals("Modifier")) {
            modifier = "yes";
        }else{
            modifier = "no"; 
        }

        return modifier;
    }

    public String supprimerChantier(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String supprimer = "";
        String getSupprimer = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Supprimer")) {
                getSupprimer = actionmenu.getLibelleaction();
            }
        }

        System.out.println("Valeur creer: " + getSupprimer);
        if (getSupprimer.equals("Supprimer")) {
            supprimer = "yes";
        }else{
            supprimer = "no"; 
        }

        return supprimer;
    }

    public String consulterChantier(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String consulter = "";
        String getConsulter = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Consulter")) {
                getConsulter = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getConsulter);
        if (getConsulter.equals("Consulter")) {
            consulter = "yes";
        }else{
            consulter = "no"; 
        }

        return consulter;
    }

    public String imprimerChantier(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String imprimer = "";
        String getImprimer = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Imprimer")) {
                getImprimer = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getImprimer);
        if (getImprimer.equals("Imprimer")) {
            imprimer = "yes";
        }else{
            imprimer = "no"; 
        }

        return imprimer;
    }

    public String creerPrestataire(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String creer = "";
        String getCreer = "";

        for (Actionmenu actionmenu : myllActionmenus) {

            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Creer")) {
                getCreer = actionmenu.getLibelleaction();
            }

        }
        System.out.println("Valeur creer: " + getCreer);
        if (getCreer.equals("Creer")) {
            creer = "yes";
        }else{
            creer = "no"; 
        }

        return creer;
    }

    public String modifierPrestataire(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String modifier = "";
        String getModifier = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            //  System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Modifier")) {
                getModifier = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getModifier);
        if (getModifier.equals("Modifier")) {
            modifier = "yes";
        }else{
            modifier = "no"; 
        }

        return modifier;
    }

    public String supprimerPrestataire(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String supprimer = "";
        String getSupprimer = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Supprimer")) {
                getSupprimer = actionmenu.getLibelleaction();
            }
        }

        System.out.println("Valeur creer: " + getSupprimer);
        if (getSupprimer.equals("Supprimer")) {
            supprimer = "yes";
        }else{
            supprimer = "no"; 
        }

        return supprimer;
    }

    public String consulterPrestataire(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String consulter = "";
        String getConsulter = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Consulter")) {
                getConsulter = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getConsulter);
        if (getConsulter.equals("Consulter")) {
            consulter = "yes";
        }else{
            consulter = "no"; 
        }

        return consulter;
    }

    public String creerFirstPrestataire(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String creer = "";
        String getCreer = "";

        for (Actionmenu actionmenu : myllActionmenus) {

            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Creer")) {
                getCreer = actionmenu.getLibelleaction();
            }

        }
        System.out.println("Valeur creer: " + getCreer);
        if (getCreer.equals("Creer")) {
            creer = "yes";
        }else{
            creer = "no"; 
        }

        return creer;
    }

    public String modifierFirstPrestataire(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String modifier = "";
        String getModifier = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            //  System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Modifier")) {
                getModifier = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getModifier);
        if (getModifier.equals("Modifier")) {
            modifier = "yes";
        }else{
            modifier = "no"; 
        }

        return modifier;
    }

    public String supprimerFirstPrestataire(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String supprimer = "";
        String getSupprimer = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Supprimer")) {
                getSupprimer = actionmenu.getLibelleaction();
            }
        }

        System.out.println("Valeur creer: " + getSupprimer);
        if (getSupprimer.equals("Supprimer")) {
            supprimer = "yes";
        }else{
            supprimer = "no"; 
        }

        return supprimer;
    }

    public String consulterFirstPrestataire(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String consulter = "";
        String getConsulter = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Consulter")) {
                getConsulter = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getConsulter);
        if (getConsulter.equals("Consulter")) {
            consulter = "yes";
        }else{
            consulter = "no"; 
        }

        return consulter;
    }

    public String creerAcompte(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String creer = "";
        String getCreer = "";

        for (Actionmenu actionmenu : myllActionmenus) {

            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Creer")) {
                getCreer = actionmenu.getLibelleaction();
            }

        }
        System.out.println("Valeur creer: " + getCreer);
        if (getCreer.equals("Creer")) {
            creer = "yes";
        }else{
            creer = "no"; 
        }

        return creer;
    }

    public String modifierAcompte(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String modifier = "";
        String getModifier = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            //  System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Modifier")) {
                getModifier = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getModifier);
        if (getModifier.equals("Modifier")) {
            modifier = "yes";
        }else{
            modifier = "no"; 
        }

        return modifier;
    }

    public String supprimerAcompte(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String supprimer = "";
        String getSupprimer = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Supprimer")) {
                getSupprimer = actionmenu.getLibelleaction();
            }
        }

        System.out.println("Valeur creer: " + getSupprimer);
        if (getSupprimer.equals("Supprimer")) {
            supprimer = "yes";
        }else{
            supprimer = "no"; 
        }

        return supprimer;
    }

    public String consulterAcompte(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String consulter = "";
        String getConsulter = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Consulter")) {
                getConsulter = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getConsulter);
        if (getConsulter.equals("Consulter")) {
            consulter = "yes";
        }else{
            consulter = "no"; 
        }

        return consulter;
    }

    public String creerDevise(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String creer = "";
        String getCreer = "";

        for (Actionmenu actionmenu : myllActionmenus) {

            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Creer")) {
                getCreer = actionmenu.getLibelleaction();
            }

        }
        System.out.println("Valeur creer: " + getCreer);
        if (getCreer.equals("Creer")) {
            creer = "yes";
        }else{
            creer = "no"; 
        }

        return creer;
    }

    public String modifierDevise(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String modifier = "";
        String getModifier = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            //  System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Modifier")) {
                getModifier = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getModifier);
        if (getModifier.equals("Modifier")) {
            modifier = "yes";
        }else{
            modifier = "no"; 
        }

        return modifier;
    }

    public String supprimerDevise(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String supprimer = "";
        String getSupprimer = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Supprimer")) {
                getSupprimer = actionmenu.getLibelleaction();
            }
        }

        System.out.println("Valeur creer: " + getSupprimer);
        if (getSupprimer.equals("Supprimer")) {
            supprimer = "yes";
        }else{
            supprimer = "no"; 
        }

        return supprimer;
    }

    public String consulterDevise(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String consulter = "";
        String getConsulter = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Consulter")) {
                getConsulter = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getConsulter);
        if (getConsulter.equals("Consulter")) {
            consulter = "yes";
        }else{
            consulter = "no"; 
        }

        return consulter;
    }

    public String creerUnitemesure(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String creer = "";
        String getCreer = "";

        for (Actionmenu actionmenu : myllActionmenus) {

            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Creer")) {
                getCreer = actionmenu.getLibelleaction();
            }

        }
        System.out.println("Valeur creer: " + getCreer);
        if (getCreer.equals("Creer")) {
            creer = "yes";
        }else{
            creer = "no"; 
        }

        return creer;
    }

    public String modifierUnitemesure(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String modifier = "";
        String getModifier = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            //  System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Modifier")) {
                getModifier = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getModifier);
        if (getModifier.equals("Modifier")) {
            modifier = "yes";
        }else{
            modifier = "no"; 
        }

        return modifier;
    }

    public String supprimerUnitemesure(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String supprimer = "";
        String getSupprimer = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Supprimer")) {
                getSupprimer = actionmenu.getLibelleaction();
            }
        }

        System.out.println("Valeur creer: " + getSupprimer);
        if (getSupprimer.equals("Supprimer")) {
            supprimer = "yes";
        }else{
            supprimer = "no"; 
        }

        return supprimer;
    }

    public String consulterUnitemesure(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String consulter = "";
        String getConsulter = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Consulter")) {
                getConsulter = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getConsulter);
        if (getConsulter.equals("Consulter")) {
            consulter = "yes";
        }else{
            consulter = "no"; 
        }

        return consulter;
    }

    public String creerLottechnique(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String creer = "";
        String getCreer = "";

        for (Actionmenu actionmenu : myllActionmenus) {

            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Creer")) {
                getCreer = actionmenu.getLibelleaction();
            }

        }
        System.out.println("Valeur creer: " + getCreer);
        if (getCreer.equals("Creer")) {
            creer = "yes";
        }else{
            creer = "no"; 
        }

        return creer;
    }

    public String modifierLottechnique(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String modifier = "";
        String getModifier = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            //  System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Modifier")) {
                getModifier = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getModifier);
        if (getModifier.equals("Modifier")) {
            modifier = "yes";
        }else{
            modifier = "no"; 
        }

        return modifier;
    }

    public String supprimerLottechnique(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String supprimer = "";
        String getSupprimer = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Supprimer")) {
                getSupprimer = actionmenu.getLibelleaction();
            }
        }

        System.out.println("Valeur creer: " + getSupprimer);
        if (getSupprimer.equals("Supprimer")) {
            supprimer = "yes";
        }else{
            supprimer = "no"; 
        }

        return supprimer;
    }

    public String consulterLottechnique(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String consulter = "";
        String getConsulter = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Consulter")) {
                getConsulter = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getConsulter);
        if (getConsulter.equals("Consulter")) {
            consulter = "yes";
        }else{
            consulter = "no"; 
        }

        return consulter;
    }

    public String creerCorspetat(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String creer = "";
        String getCreer = "";

        for (Actionmenu actionmenu : myllActionmenus) {

            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Creer")) {
                getCreer = actionmenu.getLibelleaction();
            }

        }
        System.out.println("Valeur creer: " + getCreer);
        if (getCreer.equals("Creer")) {
            creer = "yes";
        }else{
            creer = "no"; 
        }

        return creer;
    }

    public String modifierCorspetat(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String modifier = "";
        String getModifier = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            //  System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Modifier")) {
                getModifier = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getModifier);
        if (getModifier.equals("Modifier")) {
            modifier = "yes";
        }else{
            modifier = "no"; 
        }

        return modifier;
    }

    public String supprimerCorspetat(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String supprimer = "";
        String getSupprimer = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Supprimer")) {
                getSupprimer = actionmenu.getLibelleaction();
            }
        }

        System.out.println("Valeur creer: " + getSupprimer);
        if (getSupprimer.equals("Supprimer")) {
            supprimer = "yes";
        }else{
            supprimer = "no"; 
        }

        return supprimer;
    }

    public String consulterCorspetat(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String consulter = "";
        String getConsulter = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Consulter")) {
                getConsulter = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getConsulter);
        if (getConsulter.equals("Consulter")) {
            consulter = "yes";
        }else{
            consulter = "no"; 
        }

        return consulter;
    }

    public String creerMarchandise(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String creer = "";
        String getCreer = "";

        for (Actionmenu actionmenu : myllActionmenus) {

            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Creer")) {
                getCreer = actionmenu.getLibelleaction();
            }

        }
        System.out.println("Valeur creer: " + getCreer);
        if (getCreer.equals("Creer")) {
            creer = "yes";
        }else{
            creer = "no"; 
        }

        return creer;
    }

    public String modifierMarchandise(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String modifier = "";
        String getModifier = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            //  System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Modifier")) {
                getModifier = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getModifier);
        if (getModifier.equals("Modifier")) {
            modifier = "yes";
        }else{
            modifier = "no"; 
        }

        return modifier;
    }

    public String supprimerMarchandise(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String supprimer = "";
        String getSupprimer = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Supprimer")) {
                getSupprimer = actionmenu.getLibelleaction();
            }
        }

        System.out.println("Valeur creer: " + getSupprimer);
        if (getSupprimer.equals("Supprimer")) {
            supprimer = "yes";
        }else{
            supprimer = "no"; 
        }

        return supprimer;
    }

    public String consulterMarchandise(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String consulter = "";
        String getConsulter = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Consulter")) {
                getConsulter = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getConsulter);
        if (getConsulter.equals("Consulter")) {
            consulter = "yes";
        }else{
            consulter = "no"; 
        }

        return consulter;
    }

    public String creerFournisseur(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String creer = "";
        String getCreer = "";

        for (Actionmenu actionmenu : myllActionmenus) {

            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Creer")) {
                getCreer = actionmenu.getLibelleaction();
            }

        }
        System.out.println("Valeur creer: " + getCreer);
        if (getCreer.equals("Creer")) {
            creer = "yes";
        }else{
            creer = "no"; 
        }

        return creer;
    }

    public String modifierFournisseur(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String modifier = "";
        String getModifier = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            //  System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Modifier")) {
                getModifier = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getModifier);
        if (getModifier.equals("Modifier")) {
            modifier = "yes";
        }else{
            modifier = "no"; 
        }

        return modifier;
    }

    public String supprimerFournisseur(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String supprimer = "";
        String getSupprimer = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Supprimer")) {
                getSupprimer = actionmenu.getLibelleaction();
            }
        }

        System.out.println("Valeur creer: " + getSupprimer);
        if (getSupprimer.equals("Supprimer")) {
            supprimer = "yes";
        }else{
            supprimer = "no"; 
        }

        return supprimer;
    }

    public String consulterFournisseur(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String consulter = "";
        String getConsulter = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Consulter")) {
                getConsulter = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getConsulter);
        if (getConsulter.equals("Consulter")) {
            consulter = "yes";
        }else{
            consulter = "no"; 
        }

        return consulter;
    }

    public String creerPrix(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String creer = "";
        String getCreer = "";

        for (Actionmenu actionmenu : myllActionmenus) {

            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Creer")) {
                getCreer = actionmenu.getLibelleaction();
            }

        }
        System.out.println("Valeur creer: " + getCreer);
        if (getCreer.equals("Creer")) {
            creer = "yes";
        }else{
            creer = "no"; 
        }

        return creer;
    }

    public String modifierPrix(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String modifier = "";
        String getModifier = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            //  System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Modifier")) {
                getModifier = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getModifier);
        if (getModifier.equals("Modifier")) {
            modifier = "yes";
        }else{
            modifier = "no"; 
        }

        return modifier;
    }

    public String supprimerPrix(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String supprimer = "";
        String getSupprimer = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Supprimer")) {
                getSupprimer = actionmenu.getLibelleaction();
            }
        }

        System.out.println("Valeur creer: " + getSupprimer);
        if (getSupprimer.equals("Supprimer")) {
            supprimer = "yes";
        }else{
            supprimer = "no"; 
        }

        return supprimer;
    }

    public String consulterPrix(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String consulter = "";
        String getConsulter = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Consulter")) {
                getConsulter = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getConsulter);
        if (getConsulter.equals("Consulter")) {
            consulter = "yes";
        }else{
            consulter = "no"; 
        }

        return consulter;
    }

    public String creerExpression(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String creer = "";
        String getCreer = "";

        for (Actionmenu actionmenu : myllActionmenus) {

            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Creer")) {
                getCreer = actionmenu.getLibelleaction();
            }

        }
        System.out.println("Valeur creer: " + getCreer);
        if (getCreer.equals("Creer")) {
            creer = "yes";
        }else{
            creer = "no"; 
        }

        return creer;
    }

    public String modifierExpression(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String modifier = "";
        String getModifier = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            //  System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Modifier")) {
                getModifier = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getModifier);
        if (getModifier.equals("Modifier")) {
            modifier = "yes";
        }else{
            modifier = "no"; 
        }

        return modifier;
    }

    public String supprimerExpression(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String supprimer = "";
        String getSupprimer = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Supprimer")) {
                getSupprimer = actionmenu.getLibelleaction();
            }
        }

        System.out.println("Valeur creer: " + getSupprimer);
        if (getSupprimer.equals("Supprimer")) {
            supprimer = "yes";
        }else{
            supprimer = "no"; 
        }

        return supprimer;
    }

    public String consulterExpression(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String consulter = "";
        String getConsulter = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Consulter")) {
                getConsulter = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getConsulter);
        if (getConsulter.equals("Consulter")) {
            consulter = "yes";
        }else{
            consulter = "no"; 
        }

        return consulter;
    }
    
    public String imprimerExpression(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String imprimer = "";
        String getImprimer = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Imprimer")) {
                getImprimer = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getImprimer);
        if (getImprimer.equals("Imprimer")) {
            imprimer = "yes";
        }else{
            imprimer = "no"; 
        }

        return imprimer;
    }

    public String validerExpression(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String valider = "";
        String getValider = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Valider")) {
                getValider = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getValider);
        if (getValider.equals("Valider")) {
            valider = "yes";
        }else{
            valider = "no";
        }

        return valider;
    } 
    
    
    public String notifierExpression(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String notifier = "";
        String getNotifier = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Notifier")) {
                getNotifier = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getNotifier);
        if (getNotifier.equals("Notifier")) {
            notifier = "yes";
        }else{
            notifier = "no";
        }

        return notifier;
    } 
    
    
    public String creerCommande(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String creer = "";
        String getCreer = "";

        for (Actionmenu actionmenu : myllActionmenus) {

            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Creer")) {
                getCreer = actionmenu.getLibelleaction();
            }

        }
        System.out.println("Valeur creer: " + getCreer);
        if (getCreer.equals("Creer")) {
            creer = "yes";
        }else{
            creer = "no";
        }

        return creer;
    }

    public String modifierCommande(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String modifier = "";
        String getModifier = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            //  System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Modifier")) {
                getModifier = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getModifier);
        if (getModifier.equals("Modifier")) {
            modifier = "yes";
        }else{
            modifier = "no";
        }

        return modifier;
    }

    public String supprimerCommande(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String supprimer = "";
        String getSupprimer = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Supprimer")) {
                getSupprimer = actionmenu.getLibelleaction();
            }
        }

        System.out.println("Valeur creer: " + getSupprimer);
        if (getSupprimer.equals("Supprimer")) {
            supprimer = "yes";
        }else{
            supprimer = "no";
        }

        return supprimer;
    }

    public String consulterCommande(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String consulter = "";
        String getConsulter = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Consulter")) {
                getConsulter = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getConsulter);
        if (getConsulter.equals("Consulter")) {
            consulter = "yes";
        }else{
            consulter = "no";
        }

        return consulter;
    }
    
    public String imprimerCommande(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String imprimer = "";
        String getImprimer = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Imprimer")) {
                getImprimer = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getImprimer);
        if (getImprimer.equals("Imprimer")) {
            imprimer = "yes";
        }else{
            imprimer = "no";
        }

        return imprimer;
    }

    public String validerCommande(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String valider = "";
        String getValider = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Valider")) {
                getValider = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getValider);
        if (getValider.equals("Valider")) {
            valider = "yes";
        }else{
            valider = "no";
        }

        return valider;
    } 
    
    
    public String creerLivraison(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String creer = "";
        String getCreer = "";

        for (Actionmenu actionmenu : myllActionmenus) {

            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Creer")) {
                getCreer = actionmenu.getLibelleaction();
            }

        }
        System.out.println("Valeur creer: " + getCreer);
        if (getCreer.equals("Creer")) {
            creer = "yes";
        }else{
            creer = "no";
        }

        return creer;
    }

    public String modifierLivraison(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String modifier = "";
        String getModifier = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            //  System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Modifier")) {
                getModifier = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getModifier);
        if (getModifier.equals("Modifier")) {
            modifier = "yes";
        }else{
            modifier = "no";
        }

        return modifier;
    }

    public String supprimerLivraison(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String supprimer = "";
        String getSupprimer = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Supprimer")) {
                getSupprimer = actionmenu.getLibelleaction();
            }
        }

        System.out.println("Valeur creer: " + getSupprimer);
        if (getSupprimer.equals("Supprimer")) {
            supprimer = "yes";
        }else{
            supprimer = "no";
        }

        return supprimer;
    }

    public String consulterLivraison(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String consulter = "";
        String getConsulter = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Consulter")) {
                getConsulter = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getConsulter);
        if (getConsulter.equals("Consulter")) {
            consulter = "yes";
        }else{
            consulter = "no";
        }

        return consulter;
    } 
    
    
    public String creerEntrepot(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String creer = "";
        String getCreer = "";

        for (Actionmenu actionmenu : myllActionmenus) {

            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Creer")) {
                getCreer = actionmenu.getLibelleaction();
            }

        }
        System.out.println("Valeur creer: " + getCreer);
        if (getCreer.equals("Creer")) {
            creer = "yes";
        }else{
            creer = "no";
        }

        return creer;
    }

    public String modifierEntrepot(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String modifier = "";
        String getModifier = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            //  System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Modifier")) {
                getModifier = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getModifier);
        if (getModifier.equals("Modifier")) {
            modifier = "yes";
        }else{
            modifier = "no";
        }

        return modifier;
    }

    public String supprimerEntrepot(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String supprimer = "";
        String getSupprimer = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Supprimer")) {
                getSupprimer = actionmenu.getLibelleaction();
            }
        }

        System.out.println("Valeur creer: " + getSupprimer);
        if (getSupprimer.equals("Supprimer")) {
            supprimer = "yes";
        }else{
            supprimer = "no";
        }

        return supprimer;
    }

    public String consulterEntrepot(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String consulter = "";
        String getConsulter = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Consulter")) {
                getConsulter = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getConsulter);
        if (getConsulter.equals("Consulter")) {
            consulter = "yes";
        }else{
            consulter = "no";
        }

        return consulter;
    } 
    
    public String creerStock(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String creer = "";
        String getCreer = "";

        for (Actionmenu actionmenu : myllActionmenus) {

            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Creer")) {
                getCreer = actionmenu.getLibelleaction();
            }

        }
        System.out.println("Valeur creer: " + getCreer);
        if (getCreer.equals("Creer")) {
            creer = "yes";
        }else{
            creer = "no";
        }

        return creer;
    }

    public String modifierStock(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String modifier = "";
        String getModifier = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            //  System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Modifier")) {
                getModifier = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getModifier);
        if (getModifier.equals("Modifier")) {
            modifier = "yes";
        }else{
            modifier = "no";
        }

        return modifier;
    }

    public String supprimerStock(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String supprimer = "";
        String getSupprimer = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Supprimer")) {
                getSupprimer = actionmenu.getLibelleaction();
            }
        }

        System.out.println("Valeur creer: " + getSupprimer);
        if (getSupprimer.equals("Supprimer")) {
            supprimer = "yes";
        }else{
            supprimer = "no";
        }

        return supprimer;
    }

    public String consulterStock(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String consulter = "";
        String getConsulter = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Consulter")) {
                getConsulter = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getConsulter);
        if (getConsulter.equals("Consulter")) {
            consulter = "yes";
        }else{
            consulter = "no";
        }

        return consulter;
    } 
    
    
    public String creerSortieStock(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String creer = "";
        String getCreer = "";

        for (Actionmenu actionmenu : myllActionmenus) {

            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Creer")) {
                getCreer = actionmenu.getLibelleaction();
            }

        }
        System.out.println("Valeur creer: " + getCreer);
        if (getCreer.equals("Creer")) {
            creer = "yes";
        }else{
            creer = "no";
        }

        return creer;
    }

    public String modifierSortieStock(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String modifier = "";
        String getModifier = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            //  System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Modifier")) {
                getModifier = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getModifier);
        if (getModifier.equals("Modifier")) {
            modifier = "yes";
        }else{
            modifier = "no";
        }

        return modifier;
    }

    public String supprimerSortieStock(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String supprimer = "";
        String getSupprimer = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Supprimer")) {
                getSupprimer = actionmenu.getLibelleaction();
            }
        }

        System.out.println("Valeur creer: " + getSupprimer);
        if (getSupprimer.equals("Supprimer")) {
            supprimer = "yes";
        }else{
            supprimer = "no";
        }

        return supprimer;
    }

    public String consulterSortieStock(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String consulter = "";
        String getConsulter = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Consulter")) {
                getConsulter = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getConsulter);
        if (getConsulter.equals("Consulter")) {
            consulter = "yes";
        }else{
            consulter = "no";
        }

        return consulter;
    } 
    
    
    public String creerHistoLivraison(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String creer = "";
        String getCreer = "";

        for (Actionmenu actionmenu : myllActionmenus) {

            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Creer")) {
                getCreer = actionmenu.getLibelleaction();
            }

        }
        System.out.println("Valeur creer: " + getCreer);
        if (getCreer.equals("Creer")) {
            creer = "yes";
        }else{
            creer = "no";
        }

        return creer;
    }

    public String modifierHistoLivraison(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String modifier = "";
        String getModifier = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            //  System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Modifier")) {
                getModifier = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getModifier);
        if (getModifier.equals("Modifier")) {
            modifier = "yes";
        }else{
            modifier = "no";
        }

        return modifier;
    }

    public String supprimerHistoLivraison(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String supprimer = "";
        String getSupprimer = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Supprimer")) {
                getSupprimer = actionmenu.getLibelleaction();
            }
        }

        System.out.println("Valeur creer: " + getSupprimer);
        if (getSupprimer.equals("Supprimer")) {
            supprimer = "yes";
        }else{
            supprimer = "no";
        }

        return supprimer;
    }

    public String consulterHistoLivraison(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String consulter = "";
        String getConsulter = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Consulter")) {
                getConsulter = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getConsulter);
        if (getConsulter.equals("Consulter")) {
            consulter = "yes";
        }else{
            consulter = "no";
        }

        return consulter;
    } 
    
    public String creerOpportunite(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String creer = "";
        String getCreer = "";

        for (Actionmenu actionmenu : myllActionmenus) {

            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Creer")) {
                getCreer = actionmenu.getLibelleaction();
            }

        }
        System.out.println("Valeur creer: " + getCreer);
        if (getCreer.equals("Creer")) {
            creer = "yes";
        }else{
            creer = "no";
        }

        return creer;
    }

    public String modifierOpportunite(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String modifier = "";
        String getModifier = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            //  System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Modifier")) {
                getModifier = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getModifier);
        if (getModifier.equals("Modifier")) {
            modifier = "yes";
        }else{
            modifier = "no";
        }

        return modifier;
    }

    public String supprimerOpportunite(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String supprimer = "";
        String getSupprimer = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;
            // System.out.println("Valeur: " + actionmenu.getLibelleaction());
            if (actionmenu.getLibelleaction().equals("Supprimer")) {
                getSupprimer = actionmenu.getLibelleaction();
            }
        }

        System.out.println("Valeur creer: " + getSupprimer);
        if (getSupprimer.equals("Supprimer")) {
            supprimer = "yes";
        }else{
            supprimer = "no";
        }

        return supprimer;
    }

    public String consulterOpportunite(List<Actionmenu> myllActionmenus) {
        int i = 0;
        String consulter = "";
        String getConsulter = "";
        for (Actionmenu actionmenu : myllActionmenus) {
            i = i + 1;

            if (actionmenu.getLibelleaction().equals("Consulter")) {
                getConsulter = actionmenu.getLibelleaction();
            }

        }

        System.out.println("Valeur creer: " + getConsulter);
        if (getConsulter.equals("Consulter")) {
            consulter = "yes";
        }else{
            consulter = "no";
        }

        return consulter;
    }

}
