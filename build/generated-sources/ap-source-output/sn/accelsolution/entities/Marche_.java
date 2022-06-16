package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Bailleur;
import sn.accelsolution.entities.Brouillard;
import sn.accelsolution.entities.Chantier;
import sn.accelsolution.entities.Client;
import sn.accelsolution.entities.Commande;
import sn.accelsolution.entities.Decompte;
import sn.accelsolution.entities.Decompte2;
import sn.accelsolution.entities.Devis;
import sn.accelsolution.entities.Devise;
import sn.accelsolution.entities.Management;
import sn.accelsolution.entities.Newfacture;
import sn.accelsolution.entities.Operation;
import sn.accelsolution.entities.Paiement;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Marche.class)
public class Marche_ { 

    public static volatile SingularAttribute<Marche, String> cotion;
    public static volatile SingularAttribute<Marche, String> ordreservice;
    public static volatile SingularAttribute<Marche, Client> idClient;
    public static volatile SingularAttribute<Marche, Bailleur> idBailleur;
    public static volatile SingularAttribute<Marche, Integer> idMarche;
    public static volatile SingularAttribute<Marche, String> numeroFinancement;
    public static volatile SingularAttribute<Marche, String> etatmarche;
    public static volatile SingularAttribute<Marche, String> regimeFiscale;
    public static volatile ListAttribute<Marche, Decompte2> decompte2List;
    public static volatile ListAttribute<Marche, Brouillard> brouillardList;
    public static volatile ListAttribute<Marche, Decompte> decompteList;
    public static volatile ListAttribute<Marche, Management> managementList;
    public static volatile SingularAttribute<Marche, String> notification;
    public static volatile SingularAttribute<Marche, String> avenant;
    public static volatile ListAttribute<Marche, Operation> operationList;
    public static volatile SingularAttribute<Marche, String> montantDemarrage;
    public static volatile SingularAttribute<Marche, String> alias;
    public static volatile ListAttribute<Marche, Paiement> paiementList;
    public static volatile SingularAttribute<Marche, Devise> idDevise;
    public static volatile SingularAttribute<Marche, String> montantcotion;
    public static volatile SingularAttribute<Marche, String> mainlevee;
    public static volatile ListAttribute<Marche, Chantier> chantierList;
    public static volatile ListAttribute<Marche, Newfacture> newfactureList;
    public static volatile SingularAttribute<Marche, Devis> idDevis;
    public static volatile ListAttribute<Marche, Commande> commandeList;
    public static volatile SingularAttribute<Marche, String> observation;
    public static volatile SingularAttribute<Marche, String> avanceSurAppro;
    public static volatile SingularAttribute<Marche, String> montantExecution;
    public static volatile ListAttribute<Marche, Devis> devisList;
    public static volatile SingularAttribute<Marche, String> nomMarche;
    public static volatile SingularAttribute<Marche, String> dateAvenant;
    public static volatile SingularAttribute<Marche, String> primcaution;
    public static volatile SingularAttribute<Marche, String> montantMarche;
    public static volatile SingularAttribute<Marche, String> site;
    public static volatile SingularAttribute<Marche, String> dateDebut;
    public static volatile SingularAttribute<Marche, String> dureeContrat;
    public static volatile SingularAttribute<Marche, String> dateFin;
    public static volatile SingularAttribute<Marche, String> dateMarche;
    public static volatile SingularAttribute<Marche, Newfacture> idNewfacture;
    public static volatile SingularAttribute<Marche, String> tvaPrecompte;
    public static volatile SingularAttribute<Marche, String> objetMarche;

}