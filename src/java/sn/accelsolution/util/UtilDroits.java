/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DV7
 */
public class UtilDroits {

    public UtilDroits() {

    }

    public List<Module> listModules() {
        List<Module> listModules = new ArrayList<>();
        Module module1 = new Module();
        module1.setIdModule(1);
        module1.setNomModule("Accueil");
        listModules.add(module1);

        Module module2 = new Module();
        module2.setIdModule(2);
        module2.setNomModule("Configuration");
        listModules.add(module2);

        Module module3 = new Module();
        module3.setIdModule(3);
        module3.setNomModule("Personnel");
        listModules.add(module3);

        Module module4 = new Module();
        module4.setIdModule(4);
        module4.setNomModule("Projets");
        listModules.add(module4);

        Module module5 = new Module();
        module5.setIdModule(5);
        module5.setNomModule("Approvisionnement");
        listModules.add(module5);

        Module module6 = new Module();
        module6.setIdModule(6);
        module6.setNomModule("Stock");
        listModules.add(module6);

        Module module7 = new Module();
        module7.setIdModule(7);
        module7.setNomModule("Opportunités");
        listModules.add(module7);

        return listModules;
    }

    public List<MenuUtl> listMenus() {
        List<MenuUtl> listMenus = new ArrayList<>();

        MenuUtl menu0 = new MenuUtl();
        menu0.setIdMenu(0);
        menu0.setLibellemenu("* Configuration *");
        listMenus.add(menu0);

        MenuUtl menu1 = new MenuUtl();
        menu1.setIdMenu(1);
        menu1.setLibellemenu("Gestion des rôles");
        listMenus.add(menu1);

        MenuUtl menu2 = new MenuUtl();
        menu2.setIdMenu(2);
        menu2.setLibellemenu("Gestion des utilisateurs");
        listMenus.add(menu2);

        MenuUtl menu01 = new MenuUtl();
        menu01.setIdMenu(01);
        menu01.setLibellemenu("* Personnel *");
        listMenus.add(menu01);

        MenuUtl menu3 = new MenuUtl();
        menu3.setIdMenu(3);
        menu3.setLibellemenu("Recrutement");
        listMenus.add(menu3);

        MenuUtl menu4 = new MenuUtl();
        menu4.setIdMenu(4);
        menu4.setLibellemenu("Stage");
        listMenus.add(menu4);

        MenuUtl menu5 = new MenuUtl();
        menu5.setIdMenu(5);
        menu5.setLibellemenu("Salaire");
        listMenus.add(menu5);

        MenuUtl menu6 = new MenuUtl();
        menu6.setIdMenu(6);
        menu6.setLibellemenu("Prêts");
        listMenus.add(menu6);

        MenuUtl menu7 = new MenuUtl();
        menu7.setIdMenu(7);
        menu7.setLibellemenu("Indemnités");
        listMenus.add(menu7);

        MenuUtl menu02 = new MenuUtl();
        menu02.setIdMenu(02);
        menu02.setLibellemenu("* Projets *");
        listMenus.add(menu02);

        MenuUtl menu8 = new MenuUtl();
        menu8.setIdMenu(8);
        menu8.setLibellemenu("Clients");
        listMenus.add(menu8);

        MenuUtl menu9 = new MenuUtl();
        menu9.setIdMenu(9);
        menu9.setLibellemenu("Devis");
        listMenus.add(menu9);

        MenuUtl menu10 = new MenuUtl();
        menu10.setIdMenu(10);
        menu10.setLibellemenu("Marchés");
        listMenus.add(menu10);

        MenuUtl menu11 = new MenuUtl();
        menu11.setIdMenu(11);
        menu11.setLibellemenu("Factures");
        listMenus.add(menu11);

        MenuUtl menu12 = new MenuUtl();
        menu12.setIdMenu(12);
        menu12.setLibellemenu("Décomptes");
        listMenus.add(menu12);

        MenuUtl menu13 = new MenuUtl();
        menu13.setIdMenu(13);
        menu13.setLibellemenu("Chantiers");
        listMenus.add(menu13);

        MenuUtl menu14 = new MenuUtl();
        menu14.setIdMenu(14);
        menu14.setLibellemenu("Liste des prestataires");
        listMenus.add(menu14);

        MenuUtl menu15 = new MenuUtl();
        menu15.setIdMenu(15);
        menu15.setLibellemenu("Referenciel Main-d'oeuvre");
        listMenus.add(menu15);

        MenuUtl menu16 = new MenuUtl();
        menu16.setIdMenu(16);
        menu16.setLibellemenu("Acomptes");
        listMenus.add(menu16);

        MenuUtl menu17 = new MenuUtl();
        menu17.setIdMenu(17);
        menu17.setLibellemenu("Project Management");
        listMenus.add(menu17);

        MenuUtl menu03 = new MenuUtl();
        menu03.setIdMenu(03);
        menu03.setLibellemenu("* Approvisionnement *");
        listMenus.add(menu03);

        MenuUtl menu18 = new MenuUtl();
        menu18.setIdMenu(18);
        menu18.setLibellemenu("Devises");
        listMenus.add(menu18);

        MenuUtl menu19 = new MenuUtl();
        menu19.setIdMenu(19);
        menu19.setLibellemenu("Unite de mesure");
        listMenus.add(menu19);

        MenuUtl menu20 = new MenuUtl();
        menu20.setIdMenu(20);
        menu20.setLibellemenu("Lot technique");
        listMenus.add(menu20);

        MenuUtl menu21 = new MenuUtl();
        menu21.setIdMenu(21);
        menu21.setLibellemenu("Corps d'etat");
        listMenus.add(menu21);

        MenuUtl menu22 = new MenuUtl();
        menu22.setIdMenu(22);
        menu22.setLibellemenu("Materiels");
        listMenus.add(menu22);

        MenuUtl menu23 = new MenuUtl();
        menu23.setIdMenu(23);
        menu23.setLibellemenu("Fournisseurs");
        listMenus.add(menu23);

        MenuUtl menu24 = new MenuUtl();
        menu24.setIdMenu(24);
        menu24.setLibellemenu("Referenciel de prix");
        listMenus.add(menu24);

        MenuUtl menu25 = new MenuUtl();
        menu25.setIdMenu(25);
        menu25.setLibellemenu("Expression des besoins");
        listMenus.add(menu25);

        MenuUtl menu26 = new MenuUtl();
        menu26.setIdMenu(26);
        menu26.setLibellemenu("Bon de commande");
        listMenus.add(menu26);

        MenuUtl menu27 = new MenuUtl();
        menu27.setIdMenu(27);
        menu27.setLibellemenu("Livraison");
        listMenus.add(menu27);

        MenuUtl menu28 = new MenuUtl();
        menu28.setIdMenu(28);
        menu28.setLibellemenu("Approvisionnement chantier");
        listMenus.add(menu28);

        MenuUtl menu04 = new MenuUtl();
        menu04.setIdMenu(04);
        menu04.setLibellemenu("* Stock *");
        listMenus.add(menu04);

        MenuUtl menu29 = new MenuUtl();
        menu29.setIdMenu(29);
        menu29.setLibellemenu("Entrepots");
        listMenus.add(menu29);

        MenuUtl menu30 = new MenuUtl();
        menu30.setIdMenu(30);
        menu30.setLibellemenu("Inventaire");
        listMenus.add(menu30);

        MenuUtl menu31 = new MenuUtl();
        menu31.setIdMenu(31);
        menu31.setLibellemenu("Mise à jour stock");
        listMenus.add(menu31);

        MenuUtl menu32 = new MenuUtl();
        menu32.setIdMenu(32);
        menu32.setLibellemenu("Sortie de stock");
        listMenus.add(menu32);

        MenuUtl menu33 = new MenuUtl();
        menu33.setIdMenu(33);
        menu33.setLibellemenu("Historique livraison");
        listMenus.add(menu33);
        
        
        MenuUtl menu05 = new MenuUtl();
        menu05.setIdMenu(05);
        menu05.setLibellemenu("* Opportunités *");
        listMenus.add(menu05);

        MenuUtl menu34 = new MenuUtl();
        menu34.setIdMenu(34);
        menu34.setLibellemenu("Opportunités");
        listMenus.add(menu34);

        return listMenus;
    }

    public List<Action> listActions() {
        List<Action> listActions = new ArrayList<>();

        Action action0 = new Action();
        action0.setIdAction(0);
        action0.setLibelleAction("*** Roles ***");
        listActions.add(action0);

        Action action1 = new Action();
        action1.setIdAction(1);
        action1.setLibelleAction("Creer");
        listActions.add(action1);

        Action action2 = new Action();
        action2.setIdAction(2);
        action2.setLibelleAction("Modifier");
        listActions.add(action2);

        Action action3 = new Action();
        action3.setIdAction(3);
        action3.setLibelleAction("Supprimer");
        listActions.add(action3);

        Action action4 = new Action();
        action4.setIdAction(4);
        action4.setLibelleAction("Consulter");
        listActions.add(action4);

        Action action01 = new Action();
        action01.setIdAction(01);
        action01.setLibelleAction("*** Utilisateurs ***");
        listActions.add(action01);

        Action action6 = new Action();
        action6.setIdAction(6);
        action6.setLibelleAction("Creer");
        listActions.add(action6);

        Action action7 = new Action();
        action7.setIdAction(7);
        action7.setLibelleAction("Modifier");
        listActions.add(action7);

        Action action8 = new Action();
        action8.setIdAction(8);
        action8.setLibelleAction("Supprimer");
        listActions.add(action8);

        Action action9 = new Action();
        action9.setIdAction(9);
        action9.setLibelleAction("Consulter");
        listActions.add(action9);

        Action action02 = new Action();
        action02.setIdAction(01);
        action02.setLibelleAction("*** Recrutement ***");
        listActions.add(action02);

        Action action11 = new Action();
        action11.setIdAction(11);
        action11.setLibelleAction("Creer");
        listActions.add(action11);

        Action action12 = new Action();
        action12.setIdAction(12);
        action12.setLibelleAction("Modifier");
        listActions.add(action12);

        Action action13 = new Action();
        action13.setIdAction(13);
        action13.setLibelleAction("Supprimer");
        listActions.add(action13);

        Action action14 = new Action();
        action14.setIdAction(14);
        action14.setLibelleAction("Consulter");
        listActions.add(action14);

        Action action15 = new Action();
        action15.setIdAction(15);
        action15.setLibelleAction("Imprimer");
        listActions.add(action15);

        Action action03 = new Action();
        action03.setIdAction(03);
        action03.setLibelleAction("*** Stage ***");
        listActions.add(action03);

        Action action16 = new Action();
        action16.setIdAction(16);
        action16.setLibelleAction("Creer");
        listActions.add(action16);

        Action action17 = new Action();
        action17.setIdAction(17);
        action17.setLibelleAction("Modifier");
        listActions.add(action17);

        Action action18 = new Action();
        action18.setIdAction(18);
        action18.setLibelleAction("Supprimer");
        listActions.add(action18);

        Action action19 = new Action();
        action19.setIdAction(19);
        action19.setLibelleAction("Consulter");
        listActions.add(action19);

        Action action20 = new Action();
        action20.setIdAction(20);
        action20.setLibelleAction("Imprimer");
        listActions.add(action20);

        Action action04 = new Action();
        action04.setIdAction(03);
        action04.setLibelleAction("*** Salaire ***");
        listActions.add(action04);

        Action action21 = new Action();
        action21.setIdAction(21);
        action21.setLibelleAction("Creer");
        listActions.add(action21);

        Action action22 = new Action();
        action22.setIdAction(22);
        action22.setLibelleAction("Modifier");
        listActions.add(action22);

        Action action23 = new Action();
        action23.setIdAction(23);
        action23.setLibelleAction("Supprimer");
        listActions.add(action23);

        Action action24 = new Action();
        action24.setIdAction(24);
        action24.setLibelleAction("Consulter");
        listActions.add(action24);

        Action action25 = new Action();
        action25.setIdAction(25);
        action25.setLibelleAction("Imprimer");
        listActions.add(action25);

        Action action26 = new Action();
        action26.setIdAction(26);
        action26.setLibelleAction("Valider");
        listActions.add(action26);

        Action action27 = new Action();
        action27.setIdAction(27);
        action27.setLibelleAction("Notifier");
        listActions.add(action27);

        Action action05 = new Action();
        action05.setIdAction(05);
        action05.setLibelleAction("*** Prêts ***");
        listActions.add(action05);

        Action action28 = new Action();
        action28.setIdAction(28);
        action28.setLibelleAction("Creer");
        listActions.add(action28);

        Action action29 = new Action();
        action29.setIdAction(29);
        action29.setLibelleAction("Modifier");
        listActions.add(action29);

        Action action30 = new Action();
        action30.setIdAction(30);
        action30.setLibelleAction("Supprimer");
        listActions.add(action30);

        Action action31 = new Action();
        action31.setIdAction(31);
        action31.setLibelleAction("Consulter");
        listActions.add(action31);

        Action action32 = new Action();
        action32.setIdAction(32);
        action32.setLibelleAction("Imprimer");
        listActions.add(action32);

        Action action33 = new Action();
        action33.setIdAction(33);
        action33.setLibelleAction("Valider");
        listActions.add(action33);

        Action action34 = new Action();
        action34.setIdAction(34);
        action34.setLibelleAction("Notifier");
        listActions.add(action34);

        Action action06 = new Action();
        action06.setIdAction(06);
        action06.setLibelleAction("*** Indemnités ***");
        listActions.add(action06);

        Action action35 = new Action();
        action35.setIdAction(35);
        action35.setLibelleAction("Creer");
        listActions.add(action35);

        Action action36 = new Action();
        action36.setIdAction(36);
        action36.setLibelleAction("Modifier");
        listActions.add(action36);

        Action action37 = new Action();
        action37.setIdAction(37);
        action37.setLibelleAction("Supprimer");
        listActions.add(action37);

        Action action38 = new Action();
        action38.setIdAction(38);
        action38.setLibelleAction("Consulter");
        listActions.add(action38);

        Action action39 = new Action();
        action39.setIdAction(39);
        action39.setLibelleAction("Imprimer");
        listActions.add(action39);

        Action action40 = new Action();
        action40.setIdAction(40);
        action40.setLibelleAction("Valider");
        listActions.add(action40);

        Action action41 = new Action();
        action41.setIdAction(41);
        action41.setLibelleAction("Notifier");
        listActions.add(action41);

        Action action07 = new Action();
        action07.setIdAction(07);
        action07.setLibelleAction("*** Clients ***");
        listActions.add(action07);

        Action action42 = new Action();
        action42.setIdAction(42);
        action42.setLibelleAction("Creer");
        listActions.add(action42);

        Action action43 = new Action();
        action43.setIdAction(43);
        action43.setLibelleAction("Modifier");
        listActions.add(action43);

        Action action44 = new Action();
        action44.setIdAction(44);
        action44.setLibelleAction("Supprimer");
        listActions.add(action44);

        Action action45 = new Action();
        action45.setIdAction(45);
        action45.setLibelleAction("Consulter");
        listActions.add(action45);

        Action action46 = new Action();
        action46.setIdAction(46);
        action46.setLibelleAction("Imprimer");
        listActions.add(action46);

        Action action47 = new Action();
        action47.setIdAction(47);
        action47.setLibelleAction("Valider");
        listActions.add(action47);

        Action action48 = new Action();
        action48.setIdAction(48);
        action48.setLibelleAction("Notifier");
        listActions.add(action48);

        Action action49 = new Action();
        action49.setIdAction(49);
        action49.setLibelleAction("*** Devis ***");
        listActions.add(action49);

        Action action50 = new Action();
        action50.setIdAction(50);
        action50.setLibelleAction("Creer");
        listActions.add(action50);

        Action action51 = new Action();
        action51.setIdAction(51);
        action51.setLibelleAction("Modifier");
        listActions.add(action51);

        Action action52 = new Action();
        action52.setIdAction(52);
        action52.setLibelleAction("Supprimer");
        listActions.add(action52);

        Action action54 = new Action();
        action54.setIdAction(54);
        action54.setLibelleAction("Consulter");
        listActions.add(action54);

        Action action55 = new Action();
        action55.setIdAction(55);
        action55.setLibelleAction("Imprimer");
        listActions.add(action55);

        Action action56 = new Action();
        action56.setIdAction(56);
        action56.setLibelleAction("Valider");
        listActions.add(action56);

        Action action57 = new Action();
        action57.setIdAction(57);
        action57.setLibelleAction("Notifier");
        listActions.add(action57);

        Action action58 = new Action();
        action58.setIdAction(58);
        action58.setLibelleAction("*** Marchés ***");
        listActions.add(action58);

        Action action59 = new Action();
        action59.setIdAction(59);
        action59.setLibelleAction("Creer");
        listActions.add(action59);

        Action action60 = new Action();
        action60.setIdAction(60);
        action60.setLibelleAction("Modifier");
        listActions.add(action60);

        Action action61 = new Action();
        action61.setIdAction(61);
        action61.setLibelleAction("Supprimer");
        listActions.add(action61);

        Action action62 = new Action();
        action62.setIdAction(62);
        action62.setLibelleAction("Consulter");
        listActions.add(action62);

        Action action63 = new Action();
        action63.setIdAction(63);
        action63.setLibelleAction("Imprimer");
        listActions.add(action63);

        Action action64 = new Action();
        action64.setIdAction(64);
        action64.setLibelleAction("Valider");
        listActions.add(action64);

        Action action65 = new Action();
        action65.setIdAction(65);
        action65.setLibelleAction("Notifier");
        listActions.add(action65);

        Action action66 = new Action();
        action66.setIdAction(66);
        action66.setLibelleAction("*** Factures ***");
        listActions.add(action66);

        Action action67 = new Action();
        action67.setIdAction(67);
        action67.setLibelleAction("Creer");
        listActions.add(action67);

        Action action68 = new Action();
        action68.setIdAction(68);
        action68.setLibelleAction("Modifier");
        listActions.add(action68);

        Action action69 = new Action();
        action69.setIdAction(69);
        action69.setLibelleAction("Supprimer");
        listActions.add(action69);

        Action action70 = new Action();
        action70.setIdAction(70);
        action70.setLibelleAction("Consulter");
        listActions.add(action70);

        Action action71 = new Action();
        action71.setIdAction(71);
        action71.setLibelleAction("Imprimer");
        listActions.add(action71);

        Action action72 = new Action();
        action72.setIdAction(72);
        action72.setLibelleAction("Valider");
        listActions.add(action72);

        Action action73 = new Action();
        action73.setIdAction(73);
        action73.setLibelleAction("Notifier");
        listActions.add(action73);

        Action action74 = new Action();
        action74.setIdAction(74);
        action74.setLibelleAction("*** Décomptes ***");
        listActions.add(action74);

        Action action75 = new Action();
        action75.setIdAction(75);
        action75.setLibelleAction("Creer");
        listActions.add(action75);

        Action action76 = new Action();
        action76.setIdAction(76);
        action76.setLibelleAction("Modifier");
        listActions.add(action76);

        Action action77 = new Action();
        action77.setIdAction(77);
        action77.setLibelleAction("Supprimer");
        listActions.add(action77);

        Action action78 = new Action();
        action78.setIdAction(78);
        action78.setLibelleAction("Consulter");
        listActions.add(action78);

        Action action79 = new Action();
        action79.setIdAction(79);
        action79.setLibelleAction("Imprimer");
        listActions.add(action79);

        Action action80 = new Action();
        action80.setIdAction(80);
        action80.setLibelleAction("Valider");
        listActions.add(action80);

        Action action81 = new Action();
        action81.setIdAction(81);
        action81.setLibelleAction("Notifier");
        listActions.add(action81);

        Action action82 = new Action();
        action82.setIdAction(82);
        action82.setLibelleAction("*** Liste des prestataires ***");
        listActions.add(action82);

        Action action83 = new Action();
        action83.setIdAction(83);
        action83.setLibelleAction("Creer");
        listActions.add(action83);

        Action action84 = new Action();
        action84.setIdAction(84);
        action84.setLibelleAction("Modifier");
        listActions.add(action84);

        Action action85 = new Action();
        action85.setIdAction(85);
        action85.setLibelleAction("Supprimer");
        listActions.add(action85);

        Action action86 = new Action();
        action86.setIdAction(86);
        action86.setLibelleAction("Consulter");
        listActions.add(action86);

        Action action87 = new Action();
        action87.setIdAction(87);
        action87.setLibelleAction("Imprimer");
        listActions.add(action87);

        Action action88 = new Action();
        action88.setIdAction(88);
        action88.setLibelleAction("Valider");
        listActions.add(action88);

        Action action89 = new Action();
        action89.setIdAction(89);
        action89.setLibelleAction("Notifier");
        listActions.add(action89);

        Action action90 = new Action();
        action90.setIdAction(90);
        action90.setLibelleAction("*** Referenciel Main-d'oeuvre ***");
        listActions.add(action90);

        Action action91 = new Action();
        action91.setIdAction(91);
        action91.setLibelleAction("Creer");
        listActions.add(action91);

        Action action92 = new Action();
        action92.setIdAction(92);
        action92.setLibelleAction("Modifier");
        listActions.add(action92);

        Action action93 = new Action();
        action93.setIdAction(93);
        action93.setLibelleAction("Supprimer");
        listActions.add(action93);

        Action action94 = new Action();
        action94.setIdAction(94);
        action94.setLibelleAction("Consulter");
        listActions.add(action94);

        Action action95 = new Action();
        action95.setIdAction(95);
        action95.setLibelleAction("Imprimer");
        listActions.add(action95);

        Action action96 = new Action();
        action96.setIdAction(96);
        action96.setLibelleAction("Valider");
        listActions.add(action96);

        Action action97 = new Action();
        action97.setIdAction(97);
        action97.setLibelleAction("Notifier");
        listActions.add(action97);

        Action action98 = new Action();
        action98.setIdAction(98);
        action98.setLibelleAction("*** Acomptes ***");
        listActions.add(action98);

        Action action100 = new Action();
        action100.setIdAction(100);
        action100.setLibelleAction("Creer");
        listActions.add(action100);

        Action action101 = new Action();
        action101.setIdAction(101);
        action101.setLibelleAction("Modifier");
        listActions.add(action101);

        Action action102 = new Action();
        action102.setIdAction(102);
        action102.setLibelleAction("Supprimer");
        listActions.add(action102);

        Action action103 = new Action();
        action103.setIdAction(103);
        action103.setLibelleAction("Consulter");
        listActions.add(action103);

        Action action104 = new Action();
        action104.setIdAction(104);
        action104.setLibelleAction("Imprimer");
        listActions.add(action104);

        Action action105 = new Action();
        action105.setIdAction(105);
        action105.setLibelleAction("Valider");
        listActions.add(action105);

        Action action106 = new Action();
        action106.setIdAction(106);
        action106.setLibelleAction("Notifier");
        listActions.add(action106);

        Action action107 = new Action();
        action107.setIdAction(107);
        action107.setLibelleAction("*** Chantiers ***");
        listActions.add(action107);

        Action action108 = new Action();
        action108.setIdAction(108);
        action108.setLibelleAction("Creer");
        listActions.add(action108);

        Action action109 = new Action();
        action109.setIdAction(109);
        action109.setLibelleAction("Modifier");
        listActions.add(action109);

        Action action110 = new Action();
        action110.setIdAction(110);
        action110.setLibelleAction("Supprimer");
        listActions.add(action110);

        Action action111 = new Action();
        action111.setIdAction(111);
        action111.setLibelleAction("Consulter");
        listActions.add(action111);

        Action action112 = new Action();
        action112.setIdAction(112);
        action112.setLibelleAction("Imprimer");
        listActions.add(action112);

        Action action113 = new Action();
        action113.setIdAction(113);
        action113.setLibelleAction("Valider");
        listActions.add(action113);

        Action action114 = new Action();
        action114.setIdAction(114);
        action114.setLibelleAction("Notifier");
        listActions.add(action114);

        Action action115 = new Action();
        action115.setIdAction(115);
        action115.setLibelleAction("*** Project Management ***");
        listActions.add(action115);

        Action action116 = new Action();
        action116.setIdAction(116);
        action116.setLibelleAction("Creer");
        listActions.add(action116);

        Action action117 = new Action();
        action117.setIdAction(117);
        action117.setLibelleAction("Modifier");
        listActions.add(action117);

        Action action118 = new Action();
        action118.setIdAction(118);
        action118.setLibelleAction("Supprimer");
        listActions.add(action118);

        Action action119 = new Action();
        action119.setIdAction(119);
        action119.setLibelleAction("Consulter");
        listActions.add(action119);

        Action action120 = new Action();
        action120.setIdAction(120);
        action120.setLibelleAction("Imprimer");
        listActions.add(action120);

        Action action121 = new Action();
        action121.setIdAction(121);
        action121.setLibelleAction("Valider");
        listActions.add(action121);

        Action action122 = new Action();
        action122.setIdAction(122);
        action122.setLibelleAction("Notifier");
        listActions.add(action122);

        Action action123 = new Action();
        action123.setIdAction(123);
        action123.setLibelleAction("*** Devises ***");
        listActions.add(action123);

        Action action124 = new Action();
        action124.setIdAction(124);
        action124.setLibelleAction("Creer");
        listActions.add(action124);

        Action action125 = new Action();
        action125.setIdAction(125);
        action125.setLibelleAction("Modifier");
        listActions.add(action125);

        Action action126 = new Action();
        action126.setIdAction(126);
        action126.setLibelleAction("Supprimer");
        listActions.add(action126);

        Action action127 = new Action();
        action127.setIdAction(127);
        action127.setLibelleAction("Consulter");
        listActions.add(action127);

        Action action128 = new Action();
        action128.setIdAction(128);
        action128.setLibelleAction("Imprimer");
        listActions.add(action128);

        Action action129 = new Action();
        action129.setIdAction(129);
        action129.setLibelleAction("*** Unite de mesure ***");
        listActions.add(action129);

        Action action130 = new Action();
        action130.setIdAction(130);
        action130.setLibelleAction("Creer");
        listActions.add(action130);

        Action action131 = new Action();
        action131.setIdAction(131);
        action131.setLibelleAction("Modifier");
        listActions.add(action131);

        Action action132 = new Action();
        action132.setIdAction(132);
        action132.setLibelleAction("Supprimer");
        listActions.add(action132);

        Action action133 = new Action();
        action133.setIdAction(133);
        action133.setLibelleAction("Consulter");
        listActions.add(action133);

        Action action134 = new Action();
        action134.setIdAction(134);
        action134.setLibelleAction("Imprimer");
        listActions.add(action134);

        Action action135 = new Action();
        action135.setIdAction(135);
        action135.setLibelleAction("*** Lot technique ***");
        listActions.add(action135);

        Action action136 = new Action();
        action136.setIdAction(136);
        action136.setLibelleAction("Creer");
        listActions.add(action136);

        Action action137 = new Action();
        action137.setIdAction(137);
        action137.setLibelleAction("Modifier");
        listActions.add(action137);

        Action action138 = new Action();
        action138.setIdAction(138);
        action138.setLibelleAction("Supprimer");
        listActions.add(action138);

        Action action139 = new Action();
        action139.setIdAction(139);
        action139.setLibelleAction("Consulter");
        listActions.add(action139);

        Action action140 = new Action();
        action140.setIdAction(140);
        action140.setLibelleAction("Imprimer");
        listActions.add(action140);

        Action action141 = new Action();
        action141.setIdAction(141);
        action141.setLibelleAction("*** Corps d'etat ***");
        listActions.add(action141);

        Action action142 = new Action();
        action142.setIdAction(142);
        action142.setLibelleAction("Creer");
        listActions.add(action142);

        Action action143 = new Action();
        action143.setIdAction(143);
        action143.setLibelleAction("Modifier");
        listActions.add(action143);

        Action action144 = new Action();
        action144.setIdAction(144);
        action144.setLibelleAction("Supprimer");
        listActions.add(action144);

        Action action145 = new Action();
        action145.setIdAction(145);
        action145.setLibelleAction("Consulter");
        listActions.add(action145);

        Action action146 = new Action();
        action146.setIdAction(146);
        action146.setLibelleAction("Imprimer");
        listActions.add(action146);

        Action action148 = new Action();
        action148.setIdAction(148);
        action148.setLibelleAction("*** Materiels ***");
        listActions.add(action148);

        Action action149 = new Action();
        action149.setIdAction(149);
        action149.setLibelleAction("Creer");
        listActions.add(action149);

        Action action150 = new Action();
        action150.setIdAction(150);
        action150.setLibelleAction("Modifier");
        listActions.add(action150);

        Action action151 = new Action();
        action151.setIdAction(151);
        action151.setLibelleAction("Supprimer");
        listActions.add(action151);

        Action action152 = new Action();
        action152.setIdAction(152);
        action152.setLibelleAction("Consulter");
        listActions.add(action152);

        Action action153 = new Action();
        action153.setIdAction(153);
        action153.setLibelleAction("Imprimer");
        listActions.add(action153);

        Action action154 = new Action();
        action154.setIdAction(154);
        action154.setLibelleAction("*** Fournisseurs ***");
        listActions.add(action154);

        Action action155 = new Action();
        action155.setIdAction(155);
        action155.setLibelleAction("Creer");
        listActions.add(action155);

        Action action156 = new Action();
        action156.setIdAction(156);
        action156.setLibelleAction("Modifier");
        listActions.add(action156);

        Action action157 = new Action();
        action157.setIdAction(157);
        action157.setLibelleAction("Supprimer");
        listActions.add(action157);

        Action action158 = new Action();
        action158.setIdAction(158);
        action158.setLibelleAction("Consulter");
        listActions.add(action158);

        Action action159 = new Action();
        action159.setIdAction(159);
        action159.setLibelleAction("Imprimer");
        listActions.add(action159);

        Action action160 = new Action();
        action160.setIdAction(160);
        action160.setLibelleAction("*** Referenciel de prix ***");
        listActions.add(action160);

        Action action161 = new Action();
        action161.setIdAction(161);
        action161.setLibelleAction("Creer");
        listActions.add(action161);

        Action action162 = new Action();
        action162.setIdAction(162);
        action162.setLibelleAction("Modifier");
        listActions.add(action162);

        Action action163 = new Action();
        action163.setIdAction(163);
        action163.setLibelleAction("Supprimer");
        listActions.add(action163);

        Action action164 = new Action();
        action164.setIdAction(164);
        action164.setLibelleAction("Consulter");
        listActions.add(action164);

        Action action165 = new Action();
        action165.setIdAction(165);
        action165.setLibelleAction("Imprimer");
        listActions.add(action165);

        Action action166 = new Action();
        action166.setIdAction(166);
        action166.setLibelleAction("*** Expression des besoins ***");
        listActions.add(action166);

        Action action167 = new Action();
        action167.setIdAction(167);
        action167.setLibelleAction("Creer");
        listActions.add(action167);

        Action action168 = new Action();
        action168.setIdAction(168);
        action168.setLibelleAction("Modifier");
        listActions.add(action168);

        Action action169 = new Action();
        action169.setIdAction(169);
        action169.setLibelleAction("Supprimer");
        listActions.add(action169);

        Action action170 = new Action();
        action170.setIdAction(170);
        action170.setLibelleAction("Consulter");
        listActions.add(action170);

        Action action171 = new Action();
        action171.setIdAction(171);
        action171.setLibelleAction("Imprimer");
        listActions.add(action171);

        Action action190 = new Action();
        action190.setIdAction(190);
        action190.setLibelleAction("Valider");
        listActions.add(action190);

        Action action172 = new Action();
        action172.setIdAction(172);
        action172.setLibelleAction("*** Bon de commande ***");
        listActions.add(action172);

        Action action173 = new Action();
        action173.setIdAction(173);
        action173.setLibelleAction("Creer");
        listActions.add(action173);

        Action action174 = new Action();
        action174.setIdAction(174);
        action174.setLibelleAction("Modifier");
        listActions.add(action174);

        Action action175 = new Action();
        action175.setIdAction(175);
        action175.setLibelleAction("Supprimer");
        listActions.add(action175);

        Action action176 = new Action();
        action176.setIdAction(176);
        action176.setLibelleAction("Consulter");
        listActions.add(action176);

        Action action177 = new Action();
        action177.setIdAction(177);
        action177.setLibelleAction("Imprimer");
        listActions.add(action177);

        Action action191 = new Action();
        action191.setIdAction(191);
        action191.setLibelleAction("Valider");
        listActions.add(action191);

        Action action178 = new Action();
        action178.setIdAction(178);
        action178.setLibelleAction("*** Livraison ***");
        listActions.add(action178);

        Action action179 = new Action();
        action179.setIdAction(179);
        action179.setLibelleAction("Creer");
        listActions.add(action179);

        Action action180 = new Action();
        action180.setIdAction(180);
        action180.setLibelleAction("Modifier");
        listActions.add(action180);

        Action action181 = new Action();
        action181.setIdAction(181);
        action181.setLibelleAction("Supprimer");
        listActions.add(action181);

        Action action182 = new Action();
        action182.setIdAction(182);
        action182.setLibelleAction("Consulter");
        listActions.add(action182);

        Action action183 = new Action();
        action183.setIdAction(183);
        action183.setLibelleAction("Imprimer");
        listActions.add(action183);

        Action action184 = new Action();
        action184.setIdAction(184);
        action184.setLibelleAction("*** Approvisionnement chantier ***");
        listActions.add(action184);

        Action action185 = new Action();
        action185.setIdAction(185);
        action185.setLibelleAction("Creer");
        listActions.add(action185);

        Action action186 = new Action();
        action186.setIdAction(186);
        action186.setLibelleAction("Modifier");
        listActions.add(action186);

        Action action187 = new Action();
        action187.setIdAction(187);
        action187.setLibelleAction("Supprimer");
        listActions.add(action187);

        Action action188 = new Action();
        action188.setIdAction(188);
        action188.setLibelleAction("Consulter");
        listActions.add(action188);

        Action action189 = new Action();
        action189.setIdAction(189);
        action189.setLibelleAction("Imprimer");
        listActions.add(action189);
        
        
        Action action192 = new Action();
        action192.setIdAction(192);
        action192.setLibelleAction("*** Entrepots ***");
        listActions.add(action192);

        Action action193 = new Action();
        action193.setIdAction(193);
        action193.setLibelleAction("Creer");
        listActions.add(action193);

        Action action194 = new Action();
        action194.setIdAction(194);
        action194.setLibelleAction("Modifier");
        listActions.add(action194);

        Action action195= new Action();
        action195.setIdAction(195);
        action195.setLibelleAction("Supprimer");
        listActions.add(action195);

        Action action196 = new Action();
        action196.setIdAction(196);
        action196.setLibelleAction("Consulter");
        listActions.add(action196);

        Action action197 = new Action();
        action197.setIdAction(197);
        action197.setLibelleAction("Imprimer");
        listActions.add(action197);
        
        
        Action action198 = new Action();
        action198.setIdAction(198);
        action198.setLibelleAction("*** Inventaire ***");
        listActions.add(action198);

        Action action199 = new Action();
        action199.setIdAction(199);
        action199.setLibelleAction("Creer");
        listActions.add(action199);

        Action action200 = new Action();
        action200.setIdAction(200);
        action200.setLibelleAction("Modifier");
        listActions.add(action200);

        Action action201 = new Action();
        action201.setIdAction(201);
        action201.setLibelleAction("Supprimer");
        listActions.add(action201);

        Action action202 = new Action();
        action202.setIdAction(202);
        action202.setLibelleAction("Consulter");
        listActions.add(action202);

        Action action203 = new Action();
        action203.setIdAction(203);
        action203.setLibelleAction("Imprimer");
        listActions.add(action203);
        
        Action action204 = new Action();
        action204.setIdAction(204);
        action204.setLibelleAction("*** Mise à jour stock ***");
        listActions.add(action204);

        Action action205 = new Action();
        action205.setIdAction(205);
        action205.setLibelleAction("Creer");
        listActions.add(action205);

        Action action206 = new Action();
        action206.setIdAction(206);
        action206.setLibelleAction("Modifier");
        listActions.add(action206);

        Action action207 = new Action();
        action207.setIdAction(207);
        action207.setLibelleAction("Supprimer");
        listActions.add(action207);

        Action action208 = new Action();
        action208.setIdAction(208);
        action208.setLibelleAction("Consulter");
        listActions.add(action208);

        Action action209 = new Action();
        action209.setIdAction(209);
        action209.setLibelleAction("Imprimer");
        listActions.add(action209);
        
        
        Action action210 = new Action();
        action210.setIdAction(210);
        action210.setLibelleAction("*** Sortie de stock ***");
        listActions.add(action210);

        Action action211 = new Action();
        action211.setIdAction(211);
        action211.setLibelleAction("Creer");
        listActions.add(action211);

        Action action212 = new Action();
        action212.setIdAction(212);
        action212.setLibelleAction("Modifier");
        listActions.add(action212);

        Action action213 = new Action();
        action213.setIdAction(213);
        action213.setLibelleAction("Supprimer");
        listActions.add(action213);

        Action action214 = new Action();
        action214.setIdAction(214);
        action214.setLibelleAction("Consulter");
        listActions.add(action214);

        Action action215 = new Action();
        action215.setIdAction(215);
        action215.setLibelleAction("Imprimer");
        listActions.add(action215);
        
        Action action216 = new Action();
        action216.setIdAction(216);
        action216.setLibelleAction("*** Historique livraison ***");
        listActions.add(action216);

        Action action217 = new Action();
        action217.setIdAction(217);
        action217.setLibelleAction("Creer");
        listActions.add(action217);

        Action action218 = new Action();
        action218.setIdAction(218);
        action218.setLibelleAction("Modifier");
        listActions.add(action218);

        Action action219 = new Action();
        action219.setIdAction(219);
        action219.setLibelleAction("Supprimer");
        listActions.add(action219);

        Action action220 = new Action();
        action220.setIdAction(220);
        action220.setLibelleAction("Consulter");
        listActions.add(action220);

        Action action221 = new Action();
        action221.setIdAction(221);
        action221.setLibelleAction("Imprimer");
        listActions.add(action221);
        
        Action action222 = new Action();
        action222.setIdAction(222);
        action222.setLibelleAction("*** Opportunités ***");
        listActions.add(action222);

        Action action223 = new Action();
        action223.setIdAction(223);
        action223.setLibelleAction("Creer");
        listActions.add(action223);

        Action action224 = new Action();
        action224.setIdAction(224);
        action224.setLibelleAction("Modifier");
        listActions.add(action224);

        Action action225 = new Action();
        action225.setIdAction(225);
        action225.setLibelleAction("Supprimer");
        listActions.add(action225);

        Action action226 = new Action();
        action226.setIdAction(226);
        action226.setLibelleAction("Consulter");
        listActions.add(action226);

        Action action227 = new Action();
        action227.setIdAction(227);
        action227.setLibelleAction("Imprimer");
        listActions.add(action227);

        return listActions;
    }

}
