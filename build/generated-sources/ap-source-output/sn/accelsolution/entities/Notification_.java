package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Commande;
import sn.accelsolution.entities.Decomptep;
import sn.accelsolution.entities.Devis;
import sn.accelsolution.entities.Expressionbesoin;
import sn.accelsolution.entities.Newfacture;
import sn.accelsolution.entities.Salaire;
import sn.accelsolution.entities.Utilisateur;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Notification.class)
public class Notification_ { 

    public static volatile SingularAttribute<Notification, String> traitement;
    public static volatile SingularAttribute<Notification, String> heure;
    public static volatile SingularAttribute<Notification, Devis> idDevis;
    public static volatile SingularAttribute<Notification, String> typeNotification;
    public static volatile SingularAttribute<Notification, Integer> idNotification;
    public static volatile SingularAttribute<Notification, Utilisateur> idUtilisateur;
    public static volatile SingularAttribute<Notification, String> etatNotification;
    public static volatile SingularAttribute<Notification, String> message;
    public static volatile SingularAttribute<Notification, String> dateNotification;
    public static volatile SingularAttribute<Notification, String> minute;
    public static volatile SingularAttribute<Notification, Salaire> idSalaire;
    public static volatile SingularAttribute<Notification, Expressionbesoin> idExpression;
    public static volatile SingularAttribute<Notification, String> seconde;
    public static volatile SingularAttribute<Notification, Decomptep> idDecomptep;
    public static volatile SingularAttribute<Notification, Newfacture> idNewfacture;
    public static volatile SingularAttribute<Notification, Commande> idCommande;

}