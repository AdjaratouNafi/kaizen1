package sn.accelsolution.entities;

import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Notification;
import sn.accelsolution.entities.Rembourssement;
import sn.accelsolution.entities.Utilisateur;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Salaire.class)
public class Salaire_ { 

    public static volatile SingularAttribute<Salaire, BigInteger> autreAvantage;
    public static volatile SingularAttribute<Salaire, String> periodeFin;
    public static volatile SingularAttribute<Salaire, Utilisateur> idUtilisateur;
    public static volatile SingularAttribute<Salaire, BigInteger> netImposable;
    public static volatile SingularAttribute<Salaire, String> etat;
    public static volatile SingularAttribute<Salaire, String> typesalaire;
    public static volatile SingularAttribute<Salaire, Integer> idSalaire;
    public static volatile SingularAttribute<Salaire, String> periode;
    public static volatile SingularAttribute<Salaire, BigInteger> montantBrut;
    public static volatile SingularAttribute<Salaire, BigInteger> tauxEmploye;
    public static volatile SingularAttribute<Salaire, BigInteger> cotisation;
    public static volatile SingularAttribute<Salaire, String> datepaiement;
    public static volatile SingularAttribute<Salaire, String> motif;
    public static volatile SingularAttribute<Salaire, BigInteger> net;
    public static volatile SingularAttribute<Salaire, String> netAPayer;
    public static volatile ListAttribute<Salaire, Notification> notificationList;
    public static volatile SingularAttribute<Salaire, BigInteger> valeurRetenu;
    public static volatile SingularAttribute<Salaire, BigInteger> retenu;
    public static volatile SingularAttribute<Salaire, String> nomEmployeur;
    public static volatile SingularAttribute<Salaire, BigInteger> tauxSalarie;
    public static volatile SingularAttribute<Salaire, String> periodeDebut;
    public static volatile SingularAttribute<Salaire, BigInteger> totalRetenu;
    public static volatile ListAttribute<Salaire, Rembourssement> rembourssementList;
    public static volatile SingularAttribute<Salaire, String> typeSalarie;
    public static volatile SingularAttribute<Salaire, String> numFicheDepaye;
    public static volatile SingularAttribute<Salaire, String> adreseEmployeur;
    public static volatile SingularAttribute<Salaire, BigInteger> base;

}