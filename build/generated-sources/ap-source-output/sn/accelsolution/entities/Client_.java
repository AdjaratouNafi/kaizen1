package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Commande;
import sn.accelsolution.entities.Decompte;
import sn.accelsolution.entities.Devis;
import sn.accelsolution.entities.Facture;
import sn.accelsolution.entities.Marche;
import sn.accelsolution.entities.Operation;
import sn.accelsolution.entities.Prospection;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Client.class)
public class Client_ { 

    public static volatile SingularAttribute<Client, Integer> idClient;
    public static volatile ListAttribute<Client, Commande> commandeList;
    public static volatile SingularAttribute<Client, String> mailClient;
    public static volatile SingularAttribute<Client, String> telephoneClient;
    public static volatile ListAttribute<Client, Prospection> prospectionList;
    public static volatile ListAttribute<Client, Devis> devisList;
    public static volatile ListAttribute<Client, Marche> marcheList;
    public static volatile ListAttribute<Client, Decompte> decompteList;
    public static volatile ListAttribute<Client, Operation> operationList;
    public static volatile SingularAttribute<Client, String> adresse;
    public static volatile SingularAttribute<Client, String> bpClient;
    public static volatile ListAttribute<Client, Facture> factureList;
    public static volatile SingularAttribute<Client, String> nomClient;

}