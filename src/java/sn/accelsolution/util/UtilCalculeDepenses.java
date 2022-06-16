/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sn.accelsolution.entities.Acompte;
import sn.accelsolution.entities.Commande;
import sn.accelsolution.entities.DetailleCommande;

/**
 *
 * @author DV7
 */
public class UtilCalculeDepenses {

    public UtilCalculeDepenses() {

    }

    public UtilDepenses acompteCalcule(List<Acompte> listAcompte) {
        UtilDepenses utilDepenses = new UtilDepenses();

        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date currentDate = new Date();
            String stringCurrentDate = df.format(currentDate);
            String substringAn = stringCurrentDate.substring(Math.max(stringCurrentDate.length() - 2, 0));
            String substringMois = stringCurrentDate.substring(3, 5);
            System.out.println("Année : " + substringAn);
            System.out.println("Moi : " + substringMois);
        } catch (Exception e) {
        }

        return utilDepenses;
    }

    public UtilDepenses calculeOfAcompte(List<Acompte> listAcompte) {

        UtilDepenses utilDepenses = new UtilDepenses();
        /*Acompte*/
        CalculeDepenseAcompte utilAcompte = this.calculeAcompte(listAcompte);
        MontantConverter mtc = new MontantConverter();

        String acptJ = mtc.DoubleToString(utilAcompte.getMttAcompte1());
        if (acptJ.equals("0")) {
            utilDepenses.setMontantAcomptesJanvier("null");
        } else {
            String mt1 = mtc.DoubleToString(utilAcompte.getMttAcompte1());
            String mt1p = mtc.removeCaractereString(mt1);
            utilDepenses.setMontantAcomptesJanvier(mt1p);
        }

        String acptF = mtc.DoubleToString(utilAcompte.getMttAcompte2());
        if (acptF.equals("0")) {
            utilDepenses.setMontantAcomptesFevrier("null");
        } else {
            String mt2 = mtc.DoubleToString(utilAcompte.getMttAcompte2());
            String mt2p = mtc.removeCaractereString(mt2);
            utilDepenses.setMontantAcomptesFevrier(mt2p);
        }

        String acptM = mtc.DoubleToString(utilAcompte.getMttAcompte3());
        if (acptM.equals("0")) {
            utilDepenses.setMontantAcomptesMars("null");
        } else {
            String mt3 = mtc.DoubleToString(utilAcompte.getMttAcompte3());
            String mt3p = mtc.removeCaractereString(mt3);
            utilDepenses.setMontantAcomptesMars(mt3p);
        }

        String acptA = mtc.DoubleToString(utilAcompte.getMttAcompte4());
        if (acptA.equals("0")) {
            utilDepenses.setMontantAcomptesAvril("null");
        } else {
            String mt4 = mtc.DoubleToString(utilAcompte.getMttAcompte4());
            String mt4p = mtc.removeCaractereString(mt4);
            utilDepenses.setMontantAcomptesAvril(mt4p);
        }

        String acptMa = mtc.DoubleToString(utilAcompte.getMttAcompte5());
        if (acptMa.equals("0")) {
            utilDepenses.setMontantAcomptesMai("null");
        } else {
            String mt5 = mtc.DoubleToString(utilAcompte.getMttAcompte5());
            String mt5p = mtc.removeCaractereString(mt5);
            utilDepenses.setMontantAcomptesMai(mt5p);
        }

        String acptJuin = mtc.DoubleToString(utilAcompte.getMttAcompte6());
        if (acptJuin.equals("0")) {
            utilDepenses.setMontantAcomptesJuin("null");
        } else {
            String mt6 = mtc.DoubleToString(utilAcompte.getMttAcompte6());
            String mt6p = mtc.removeCaractereString(mt6);
            utilDepenses.setMontantAcomptesJuin(mt6p);
        }

        String acptJuillet = mtc.DoubleToString(utilAcompte.getMttAcompte7());
        if (acptJuillet.equals("0")) {
            utilDepenses.setMontantAcomptesJuillet("null");
        } else {
            String mt7 = mtc.DoubleToString(utilAcompte.getMttAcompte7());
            String mt7p = mtc.removeCaractereString(mt7);
            utilDepenses.setMontantAcomptesJuillet(mt7p);
        }

        String acptAout = mtc.DoubleToString(utilAcompte.getMttAcompte8());
        if (acptAout.equals("0")) {
            utilDepenses.setMontantAcomptesAout("null");
        } else {
            String mt8 = mtc.DoubleToString(utilAcompte.getMttAcompte8());
            String mt8p = mtc.removeCaractereString(mt8);
            utilDepenses.setMontantAcomptesAout(mt8p);
        }

        String acptS = mtc.DoubleToString(utilAcompte.getMttAcompte9());
        if (acptS.equals("0")) {
            utilDepenses.setMontantAcomptesSeptembre("null");
        } else {
            String mt9 = mtc.DoubleToString(utilAcompte.getMttAcompte9());
            String mt9p = mtc.removeCaractereString(mt9);
            utilDepenses.setMontantAcomptesSeptembre(mt9p);
        }

        String acptO = mtc.DoubleToString(utilAcompte.getMttAcompte10());
        if (acptO.equals("0")) {
            utilDepenses.setMontantAcomptesOctobre("null");
        } else {
            String mt10 = mtc.DoubleToString(utilAcompte.getMttAcompte10());
            String mt10p = mtc.removeCaractereString(mt10);
            utilDepenses.setMontantAcomptesOctobre(mt10p);
        }

        String acptN = mtc.DoubleToString(utilAcompte.getMttAcompte11());
        if (acptN.equals("0")) {
            utilDepenses.setMontantAcomptesNovembre("null");
        } else {
            String mt11 = mtc.DoubleToString(utilAcompte.getMttAcompte11());
            String mt11p = mtc.removeCaractereString(mt11);
            utilDepenses.setMontantAcomptesNovembre(mt11p);
        }

        String acptD = mtc.DoubleToString(utilAcompte.getMttAcompte12());
        if (acptD.equals("0")) {
            utilDepenses.setMontantAcomptesDecembre("null");
        } else {
            String mt12 = mtc.DoubleToString(utilAcompte.getMttAcompte12());
            String mt12p = mtc.removeCaractereString(mt12);
            utilDepenses.setMontantAcomptesDecembre(mt12p);
        }

        return utilDepenses;
    }

    public UtilDepenses calculeOfCommande(List<DetailleCommande> listDetailleCommande) {

        UtilDepenses utilDepenses = new UtilDepenses();
        /*Commande*/
        CalculeDepenseAcompte utilAcompte = this.calculeCommande(listDetailleCommande);
        MontantConverter mtc = new MontantConverter();

        String acptJ = mtc.DoubleToString(utilAcompte.getMttAcompte1());
        if (acptJ.equals("0")) {
            utilDepenses.setMontantBonCommandeJanvier("null");
        } else {
            String mt1 = mtc.DoubleToString(utilAcompte.getMttAcompte1());
            String  mt1p = mtc.removeCaractereString(mt1);
            utilDepenses.setMontantBonCommandeJanvier(mt1p);
        }

        String acptF = mtc.DoubleToString(utilAcompte.getMttAcompte2());
        if (acptF.equals("0")) {
            utilDepenses.setMontantBonCommandeFevrier("null");
        } else {
            String mt2 = mtc.DoubleToString(utilAcompte.getMttAcompte2());
            String  mt2p = mtc.removeCaractereString(mt2);
            utilDepenses.setMontantBonCommandeFevrier(mt2p);
        }

        String acptM = mtc.DoubleToString(utilAcompte.getMttAcompte3());
        if (acptM.equals("0")) {
            utilDepenses.setMontantBonCommandeMars("null");
        } else {
            String mt3 = mtc.DoubleToString(utilAcompte.getMttAcompte3());
            String  mt3p = mtc.removeCaractereString(mt3);
            utilDepenses.setMontantBonCommandeMars(mt3p);
        }

        String acptA = mtc.DoubleToString(utilAcompte.getMttAcompte4());
        if (acptA.equals("0")) {
            utilDepenses.setMontantBonCommandeAvril("null");
        } else {
            String mt4 = mtc.DoubleToString(utilAcompte.getMttAcompte4());
            String  mt4p = mtc.removeCaractereString(mt4);
            utilDepenses.setMontantBonCommandeAvril(mt4p);
        }

        String acptMa = mtc.DoubleToString(utilAcompte.getMttAcompte5());
        if (acptMa.equals("0")) {
            utilDepenses.setMontantBonCommandeMai("null");
        } else {
            String mt5 = mtc.DoubleToString(utilAcompte.getMttAcompte5());
            String  mt5p = mtc.removeCaractereString(mt5);
            utilDepenses.setMontantBonCommandeMai(mt5p);
        }

        String acptJuin = mtc.DoubleToString(utilAcompte.getMttAcompte6());
        if (acptJuin.equals("0")) {
            utilDepenses.setMontantBonCommandeJuin("null");
        } else {
            String mt6 = mtc.DoubleToString(utilAcompte.getMttAcompte6());
            String  mt6p = mtc.removeCaractereString(mt6);
            utilDepenses.setMontantBonCommandeJuin(mt6p);
        }

        String acptJuillet = mtc.DoubleToString(utilAcompte.getMttAcompte7());
        if (acptJuillet.equals("0")) {
            utilDepenses.setMontantBonCommandeJuillet("null");
        } else {
            String mt7 = mtc.DoubleToString(utilAcompte.getMttAcompte7());
            String  mt7p = mtc.removeCaractereString(mt7);
            utilDepenses.setMontantBonCommandeJuillet(mt7p);
        }

        String acptAout = mtc.DoubleToString(utilAcompte.getMttAcompte8());
        if (acptAout.equals("0")) {
            utilDepenses.setMontantBonCommandeAout("null");
        } else {
            String mt8 = mtc.DoubleToString(utilAcompte.getMttAcompte8());
            String  mt8p = mtc.removeCaractereString(mt8);
            utilDepenses.setMontantBonCommandeAout(mt8p);
        }

        String acptS = mtc.DoubleToString(utilAcompte.getMttAcompte9());
        if (acptS.equals("0")) {
            utilDepenses.setMontantBonCommandeSeptembre("null");
        } else {
            String mt9 = mtc.DoubleToString(utilAcompte.getMttAcompte9());
            String  mt9p = mtc.removeCaractereString(mt9);
            utilDepenses.setMontantBonCommandeSeptembre(mt9p);
        }

        String acptO = mtc.DoubleToString(utilAcompte.getMttAcompte10());
        if (acptO.equals("0")) {
            utilDepenses.setMontantBonCommandeOctobre("null");
        } else {
            String mt10 = mtc.DoubleToString(utilAcompte.getMttAcompte10());
            String  mt10p = mtc.removeCaractereString(mt10);
            utilDepenses.setMontantBonCommandeOctobre(mt10p);
        }

        String acptN = mtc.DoubleToString(utilAcompte.getMttAcompte11());
        if (acptN.equals("0")) {
            utilDepenses.setMontantBonCommandeNovembre("null");
        } else {
            String mt11 = mtc.DoubleToString(utilAcompte.getMttAcompte11());
            String  mt11p = mtc.removeCaractereString(mt11);
            utilDepenses.setMontantBonCommandeNovembre(mt11p);
        }

        String acptD = mtc.DoubleToString(utilAcompte.getMttAcompte12());
        if (acptD.equals("0")) {
            utilDepenses.setMontantBonCommandeDecembre("null");
        } else {
            String mt12 = mtc.DoubleToString(utilAcompte.getMttAcompte12());
            String  mt12p = mtc.removeCaractereString(mt12);
            utilDepenses.setMontantBonCommandeDecembre(mt12p);
        }

        return utilDepenses;
    }

    public UtilDepenses calcule(List<Acompte> listAcompte, List<Commande> listCommande) {
        UtilDepenses utilDepenses = new UtilDepenses();
        /*Acompte*/
        CalculeDepenseAcompte utilAcompte = this.calculeAcompte(listAcompte);
        MontantConverter mtc = new MontantConverter();
        utilDepenses.setMontantAcomptesJanvier(mtc.DoubleToString(utilAcompte.getMttAcompte1()));
        utilDepenses.setMontantAcomptesFevrier(mtc.DoubleToString(utilAcompte.getMttAcompte2()));
        utilDepenses.setMontantAcomptesMars(mtc.DoubleToString(utilAcompte.getMttAcompte3()));
        utilDepenses.setMontantAcomptesAvril(mtc.DoubleToString(utilAcompte.getMttAcompte4()));
        utilDepenses.setMontantAcomptesMai(mtc.DoubleToString(utilAcompte.getMttAcompte5()));
        utilDepenses.setMontantAcomptesJuin(mtc.DoubleToString(utilAcompte.getMttAcompte6()));
        utilDepenses.setMontantAcomptesJuillet(mtc.DoubleToString(utilAcompte.getMttAcompte7()));
        utilDepenses.setMontantAcomptesAout(mtc.DoubleToString(utilAcompte.getMttAcompte8()));
        utilDepenses.setMontantAcomptesSeptembre(mtc.DoubleToString(utilAcompte.getMttAcompte9()));
        utilDepenses.setMontantAcomptesOctobre(mtc.DoubleToString(utilAcompte.getMttAcompte10()));
        utilDepenses.setMontantAcomptesNovembre(mtc.DoubleToString(utilAcompte.getMttAcompte11()));
        utilDepenses.setMontantAcomptesDecembre(mtc.DoubleToString(utilAcompte.getMttAcompte12()));
        return utilDepenses;
    }

    public Date stringTodate(String stringDate1) {
        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("dd/MM/yy").parse(stringDate1);
        } catch (ParseException ex) {
            Logger.getLogger(UtilCalculeDepenses.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date1;
    }

    public CalculeDepenseAcompte calculeAcompte(List<Acompte> listAcompte) {
        System.out.println("Je suis là: 2");
        MontantConverter mtc = new MontantConverter();
        CalculeDepenseAcompte utilAcompte = new CalculeDepenseAcompte();
        Double mttAcompte1 = 0.0;
        Double mttAcompte2 = 0.0;
        Double mttAcompte3 = 0.0;
        Double mttAcompte4 = 0.0;
        Double mttAcompte5 = 0.0;
        Double mttAcompte6 = 0.0;
        Double mttAcompte7 = 0.0;
        Double mttAcompte8 = 0.0;
        Double mttAcompte9 = 0.0;
        Double mttAcompte10 = 0.0;
        Double mttAcompte11 = 0.0;
        Double mttAcompte12 = 0.0;

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date currentDate = new Date();
        String stringCurrentDate = df.format(currentDate);
        String substringAAn = stringCurrentDate.substring(Math.max(stringCurrentDate.length() - 2, 0));
        System.out.println("Ancienne année: " + substringAAn);

        for (Acompte acompte : listAcompte) {
            Double mttIntermediaire = mtc.StringToDouble(acompte.getMontantAcompte());

            String substringAn = acompte.getDateAcompte().substring(Math.max(acompte.getDateAcompte().length() - 2, 0));
            String month = acompte.getDateAcompte().substring(3, 5);
            System.out.println("Nouvelle année: " + substringAn);
            System.out.println("Mois: " + month);

            if (substringAAn.equals(substringAn)) {
                System.out.println("Je suis là: 3");
                if (month.equals("01")) {
                    mttAcompte1 = mttAcompte1 + mttIntermediaire;
                }

                if (month.equals("02")) {
                    mttAcompte2 = mttAcompte2 + mttIntermediaire;
                }

                if (month.equals("03")) {
                    mttAcompte3 = mttAcompte3 + mttIntermediaire;
                }

                if (month.equals("04")) {
                    mttAcompte4 = mttAcompte4 + mttIntermediaire;
                }

                if (month.equals("05")) {
                    mttAcompte5 = mttAcompte5 + mttIntermediaire;
                }

                if (month.equals("06")) {
                    mttAcompte6 = mttAcompte6 + mttIntermediaire;
                }

                if (month.equals("07")) {
                    mttAcompte7 = mttAcompte7 + mttIntermediaire;
                }

                if (month.equals("08")) {
                    mttAcompte8 = mttAcompte8 + mttIntermediaire;
                }

                if (month.equals("09")) {
                    mttAcompte9 = mttAcompte9 + mttIntermediaire;
                }

                if (month.equals("10")) {
                    mttAcompte10 = mttAcompte10 + mttIntermediaire;
                }

                if (month.equals("11")) {
                    mttAcompte11 = mttAcompte11 + mttIntermediaire;
                }

                if (month.equals("12")) {
                    mttAcompte12 = mttAcompte12 + mttIntermediaire;
                }

            }

        }

        utilAcompte.setMttAcompte1(mttAcompte1);
        utilAcompte.setMttAcompte2(mttAcompte2);
        utilAcompte.setMttAcompte3(mttAcompte3);
        utilAcompte.setMttAcompte4(mttAcompte4);
        utilAcompte.setMttAcompte5(mttAcompte5);
        utilAcompte.setMttAcompte6(mttAcompte6);
        utilAcompte.setMttAcompte7(mttAcompte7);
        utilAcompte.setMttAcompte8(mttAcompte8);
        utilAcompte.setMttAcompte9(mttAcompte9);
        utilAcompte.setMttAcompte10(mttAcompte10);
        utilAcompte.setMttAcompte11(mttAcompte11);
        utilAcompte.setMttAcompte12(mttAcompte12);

        return utilAcompte;
    }

    public CalculeDepenseAcompte calculeCommande(List<DetailleCommande> listDetailleCommande) {
        System.out.println("Je suis là: 2");
        MontantConverter mtc = new MontantConverter();
        CalculeDepenseAcompte utilAcompte = new CalculeDepenseAcompte();
        Double mttAcompte1 = 0.0;
        Double mttAcompte2 = 0.0;
        Double mttAcompte3 = 0.0;
        Double mttAcompte4 = 0.0;
        Double mttAcompte5 = 0.0;
        Double mttAcompte6 = 0.0;
        Double mttAcompte7 = 0.0;
        Double mttAcompte8 = 0.0;
        Double mttAcompte9 = 0.0;
        Double mttAcompte10 = 0.0;
        Double mttAcompte11 = 0.0;
        Double mttAcompte12 = 0.0;

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date currentDate = new Date();
        String stringCurrentDate = df.format(currentDate);
        String substringAAn = stringCurrentDate.substring(Math.max(stringCurrentDate.length() - 2, 0));
        System.out.println("Ancienne année: " + substringAAn);

        for (DetailleCommande detailleCommande : listDetailleCommande) {

            Double pu = mtc.StringToDouble(detailleCommande.getPu());
            int qte = detailleCommande.getQuantite();
            
            Double mttIntermediaire = pu * qte;

            String substringAn = detailleCommande.getIdCommande().getDateechance().substring(Math.max(detailleCommande.getIdCommande().getDateechance().length() - 2, 0));
            String month = "";
            int nbchar = detailleCommande.getIdCommande().getDateechance().length();
            if(nbchar == 8){
              month = detailleCommande.getIdCommande().getDateechance().substring(3, 5);  
            }
            if(nbchar > 8){
              month = detailleCommande.getIdCommande().getDateechance().substring(3, 5);  
            }
            
            System.out.println("Nouvelle année: " + substringAn);
            System.out.println("Mois: " + month);

            if (substringAAn.equals(substringAn)) {
                System.out.println("Je suis là: 3");
                if (month.equals("01")) {
                    mttAcompte1 = mttAcompte1 + mttIntermediaire;
                }

                if (month.equals("02")) {
                    mttAcompte2 = mttAcompte2 + mttIntermediaire;
                }

                if (month.equals("03")) {
                    mttAcompte3 = mttAcompte3 + mttIntermediaire;
                }

                if (month.equals("04")) {
                    mttAcompte4 = mttAcompte4 + mttIntermediaire;
                }

                if (month.equals("05")) {
                    mttAcompte5 = mttAcompte5 + mttIntermediaire;
                }

                if (month.equals("06")) {
                    mttAcompte6 = mttAcompte6 + mttIntermediaire;
                }

                if (month.equals("07")) {
                    mttAcompte7 = mttAcompte7 + mttIntermediaire;
                }

                if (month.equals("08")) {
                    mttAcompte8 = mttAcompte8 + mttIntermediaire;
                }

                if (month.equals("09")) {
                    mttAcompte9 = mttAcompte9 + mttIntermediaire;
                }

                if (month.equals("10")) {
                    mttAcompte10 = mttAcompte10 + mttIntermediaire;
                }

                if (month.equals("11")) {
                    mttAcompte11 = mttAcompte11 + mttIntermediaire;
                }

                if (month.equals("12")) {
                    mttAcompte12 = mttAcompte12 + mttIntermediaire;
                }

            }

        }

        utilAcompte.setMttAcompte1(mttAcompte1);
        utilAcompte.setMttAcompte2(mttAcompte2);
        utilAcompte.setMttAcompte3(mttAcompte3);
        utilAcompte.setMttAcompte4(mttAcompte4);
        utilAcompte.setMttAcompte5(mttAcompte5);
        utilAcompte.setMttAcompte6(mttAcompte6);
        utilAcompte.setMttAcompte7(mttAcompte7);
        utilAcompte.setMttAcompte8(mttAcompte8);
        utilAcompte.setMttAcompte9(mttAcompte9);
        utilAcompte.setMttAcompte10(mttAcompte10);
        utilAcompte.setMttAcompte11(mttAcompte11);
        utilAcompte.setMttAcompte12(mttAcompte12);

        return utilAcompte;

    }

}
