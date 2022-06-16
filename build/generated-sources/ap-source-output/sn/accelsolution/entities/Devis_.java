package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Client;
import sn.accelsolution.entities.Detailledevis;
import sn.accelsolution.entities.Devise;
import sn.accelsolution.entities.Marche;
import sn.accelsolution.entities.Newfacture;
import sn.accelsolution.entities.Notification;
import sn.accelsolution.entities.Utilisateur;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:57")
@StaticMetamodel(Devis.class)
public class Devis_ { 

    public static volatile SingularAttribute<Devis, String> coef;
    public static volatile ListAttribute<Devis, Notification> notificationList;
    public static volatile ListAttribute<Devis, Newfacture> newfactureList;
    public static volatile SingularAttribute<Devis, Integer> idDevis;
    public static volatile SingularAttribute<Devis, String> montantlettre;
    public static volatile SingularAttribute<Devis, Client> idClient;
    public static volatile SingularAttribute<Devis, Marche> idMarche;
    public static volatile SingularAttribute<Devis, Utilisateur> idUtilisateur;
    public static volatile SingularAttribute<Devis, String> terme;
    public static volatile ListAttribute<Devis, Detailledevis> detailledevisList;
    public static volatile SingularAttribute<Devis, String> etat;
    public static volatile ListAttribute<Devis, Marche> marcheList;
    public static volatile SingularAttribute<Devis, String> numeroDevis;
    public static volatile SingularAttribute<Devis, String> nbjours;
    public static volatile SingularAttribute<Devis, String> nommarche;
    public static volatile SingularAttribute<Devis, String> dateDevis;
    public static volatile SingularAttribute<Devis, String> motif;
    public static volatile SingularAttribute<Devis, Devise> idDevise;

}